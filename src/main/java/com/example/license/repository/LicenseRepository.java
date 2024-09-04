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
    public Integer insert(Integer softwareId, Date licenseStartDate, Date licenseEndDate, Integer budgetId, Integer terminalId, Integer accountId, String serialCode, String licenseNumber) {
        var sql = "insert into license_table(software_id, license_start_date, license_end_date, budget_id, terminal_id, account_id, serial_code, license_number) values(?, ?, ?, ?, ?, ?, ?, ?)";
        //licenseの追加
        var n = jdbc.update(sql, softwareId, licenseStartDate, licenseEndDate, budgetId, terminalId, accountId, serialCode, licenseNumber);
        var idSql = "select * from license_table order by license_id desc";
        //IDを降順に並び替える（新規予算は必ずIDが一番大きくなる）
        var license = jdbc.query(idSql, DataClassRowMapper.newInstance(License.class));
        //Listの一番上のbudgetを取り出し、IDを抽出
        Integer licenseId = license.get(0).getLicenseId();
        return licenseId;
    }

    @Override
    public int change(Integer selectedLicenseId, Integer softwareId, Date licenseStartDate, Date licenseEndDate, Integer budgetId, Integer terminalId, Integer accountId, String serialCode, String licenseNumber) {
        var sql = "update license_table set software_id = ?, license_start_date = ?, license_end_date = ?, budget_id = ?, terminal_id = ?, account_id = ?, serial_code = ?, license_number = ? where license_Id = ?";
        var n = jdbc.update(sql, softwareId, licenseStartDate, licenseEndDate, budgetId, terminalId, accountId, serialCode, licenseNumber ,selectedLicenseId);
        return n;
    }

    @Override
    public List<License> find() {
        String sql = "select * from license_table";

        List<License> licenses = jdbc.query(sql, DataClassRowMapper.newInstance(License.class));

        return licenses;
    }

    @Override
    public int delete(Integer selectedLicenseId) {
        var sql = "update license_table set license_exist = ? where license_id = ?";
        var n = jdbc.update(sql, 0, selectedLicenseId);
        System.out.println("license削除件数：" + n);
        return n;
    }

    @Override
    public List<License> find(Account account) {
        var accountId = account.getAccountId();
        //accountId=sectionId=licenseIdで検索
        String sql = "select license_table.license_id, software_id, license_start_date, license_end_date, budget_id, terminal_id, license_table.account_id, serial_code, license_number"
                + " from account_section_table"
                + " inner join license_section_table"
                + " on account_section_table.section_id = license_section_table.section_id"
                + " inner join license_table"
                + " on license_table.license_id = license_section_table.license_id"
                + " where account_section_table.account_id = ?"
                + " and license_exist = 1";
        List<License> licenses = jdbc.query(sql, DataClassRowMapper.newInstance(License.class), accountId);
        return licenses;
    }
}
