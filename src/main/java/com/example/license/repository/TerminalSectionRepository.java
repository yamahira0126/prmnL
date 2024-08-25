package com.example.license.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TerminalSectionRepository implements ITerminalSectionRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public TerminalSectionRepository(JdbcTemplate jdbc) {this.jdbc = jdbc;}

    @Override
    public int insert(Integer terminalId, Integer sectionId) {
        var sql = "insert into terminal_section_table(terminal_id, section_id) values(?, ?)";
        var n = jdbc.update(sql, terminalId, sectionId);
        return n;
    }


}
