package com.example.license.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SoftwareSectionRepository implements ISoftwareSectionRepository{
    private final JdbcTemplate jdbc;

    @Autowired
    public SoftwareSectionRepository(JdbcTemplate jdbc) {this.jdbc = jdbc;};

    @Override
    public int insert(Integer softwareId, Integer sectionId) {
        var sql = "insert into software_section_table values(?, ?)";
        var n = jdbc.update(sql, softwareId, sectionId);
        return n;
    }
}
