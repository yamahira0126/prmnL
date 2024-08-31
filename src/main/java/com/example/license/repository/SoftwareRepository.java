package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Budget;
import com.example.license.data.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SoftwareRepository implements ISoftwareRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public  SoftwareRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Integer insert(String softwareName, String softwareType,  String totalNumber, String softwareRemarks) {
        var sql = "insert into software_table(software_name,software_type,total_number,software_remarks) values(?,?,?,?)";
        var n = jdbc.update(sql,softwareName,softwareType,totalNumber,softwareRemarks);
        var idSql = "select * from software_table order by software_id desc";
        //IDを降順に並び替える（新規ソフトウェアは必ずIDが一番大きくなる）
        var software = jdbc.query(idSql, DataClassRowMapper.newInstance(Software.class));
        //Listの一番上のbudgetを取り出し、IDを抽出
        Integer softwareId = software.get(0).getSoftwareId();
        return softwareId;
    }

    @Override
    public int change(String selectedSoftwareName, String softwareName, String softwareType, String totalNumber,String softwareRemarks) {
        var sql = "update software_table set software_name = ?, software_type = ?, total_number = ?, software_remarks = ? where software_name = ?";
        var n = jdbc.update(sql, softwareName, softwareType, totalNumber, softwareRemarks, selectedSoftwareName);
        return n;
    }

    @Override
    public List<Software> find() {
        var sql = "select * from software_table";

        List<Software> softwares = jdbc.query(sql, DataClassRowMapper.newInstance(Software.class));

        return softwares;
    }
    @Override
    public List<Software> find(Account account) {
        var accountId = account.getAccountId();
        //accountId=sectionId=softwareIdで検索
        String sql = "select software_table.software_id, software_name, software_type, total_number, software_remarks"
                + " from account_section_table"
                + " inner join software_section_table"
                + " on account_section_table.section_id = software_section_table.section_id"
                + " inner join software_table"
                + " on software_table.software_id = software_section_table.software_id"
                + " where account_id = ?";
        List<Software> softwares = jdbc.query(sql, DataClassRowMapper.newInstance(Software.class), accountId);
        return softwares;
    }
}
