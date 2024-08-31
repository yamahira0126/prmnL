package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Budget;
import com.example.license.data.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Date;
import java.util.List;

@Repository
public class LicenseRepository implements ILicenseRepository{
    private final JdbcTemplate jdbc;

    @Autowired
    public LicenseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Integer insert(String softWare, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber, File licenseRemarks) {
        var sql = "insert into license_table(soft_ware, license_start_date, license_end_date, budget_id, terminal_id, account_id, serial_code, license_number, license_remarks) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //licenseの追加
        var n = jdbc.update(sql, softWare, licenseStartDate, licenseEndDate, budgetId, terminalId, accountId, serialCode, licenseNumber, licenseRemarks);
        var idSql = "select * from budget_table order by budget_id desc";
        //IDを降順に並び替える（新規予算は必ずIDが一番大きくなる）
        var license = jdbc.query(idSql, DataClassRowMapper.newInstance(License.class));
        //Listの一番上のbudgetを取り出し、IDを抽出
        Integer licenseId = license.get(0).licenseId();
        return licenseId;
    }

    @Override
    public int change(String selectedLicenseName, String softWare, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber, File licenseRemarks) {
        var sql = "update license_table set soft_ware = ?, license_start_date = ?, license_end_date = ?, budget_id = ?, terminal_id = ?, account_id = ?, serial_code = ?, license_number = ?, license_remarks = ? where license_name = ?";
        var n = jdbc.update(sql, softWare, licenseStartDate, licenseEndDate, budgetId, terminalId, accountId, serialCode, licenseNumber, licenseRemarks ,selectedLicenseName);
        return n;
    }

    @Override
    public List<License> find() {
        String sql = "select * from license_table";

        List<License> licenses = jdbc.query(sql, DataClassRowMapper.newInstance(License.class));

        return licenses;
    }

    @Override
    public List<License> find(Account account) {
        var accountId = account.getAccountId();
        //accountId=sectionId=licenseIdで検索
        String sql = "select license_table.license_id, soft_ware, license_start_date, license_end_date, budget_id, terminal_id, account_id, serial_code, license_number, license_remarks"
                + " from account_section_table"
                + " inner join license_section_table"
                + " on account_section_table.section_id = license_section_table.section_id"
                + " inner join budget_table"
                + " on license_table.license_id = license_section_table.license_id"
                + " where account_id = ?";
        List<License> licenses = jdbc.query(sql, DataClassRowMapper.newInstance(License.class), accountId);
        return licenses;
    }
}
