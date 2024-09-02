package com.example.license.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LicenseSectionRepository implements ILicenseSectionRepository{
    private final JdbcTemplate jdbc;

    @Autowired
    public LicenseSectionRepository(JdbcTemplate jdbc) {this.jdbc = jdbc;};

    @Override
    public int insert(Integer licenseId, Integer sectionId) {
        var sql = "insert into license_section_table values(?, ?)";
        var n = jdbc.update(sql, licenseId, sectionId);
        return n;
    }
}
