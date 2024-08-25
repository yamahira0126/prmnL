package com.example.license.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BudgetSectionRepository implements IBudgetSectionRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public BudgetSectionRepository(JdbcTemplate jdbc) {this.jdbc = jdbc;};

    @Override
    public int insert(Integer budgetId, Integer sectionId) {
        var sql = "insert into budget_section_table values(?, ?)";
        var n = jdbc.update(sql, budgetId, sectionId);
        return n;
    }
}
