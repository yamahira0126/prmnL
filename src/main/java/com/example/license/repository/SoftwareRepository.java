package com.example.license.repository;

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
    public int insert(String softwareName, String softwareType,  String totalNumber, String softwareRemarks) {
        var sql = "insert into software_table(software_name,software_type,total_number,software_remarks) values(?,?,?,?)";
        var n = jdbc.update(sql,softwareName,softwareType,totalNumber,softwareRemarks);
        return n;
    }

    @Override
    public int change(String selectedSoftwareName, String softwareName, String softwareType, String totalNumber,String softwareRemarks) {
        var sql = "update software_table set software_name = ?, software_type = ?, total_number = ?, software_remarks = ? where software_name = ?";
        var n = jdbc.update(sql, softwareName, softwareType, totalNumber, softwareRemarks, selectedSoftwareName);
        return n;
    }

    @Override
    public List<Software> find() {
        var sql = "select software_id, software_name, software_type, total_number, software_remarks from software_table";

        List<Software> softwares = jdbc.query(sql, DataClassRowMapper.newInstance(Software.class));

        return softwares;
    }
}
