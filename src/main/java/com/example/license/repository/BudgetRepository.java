package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BudgetRepository implements IBudgetRepository{
    private final JdbcTemplate jdbc;

    @Autowired
    public BudgetRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Integer insert(String budgetName, Date budgetStartDate, Date budgetEndDate) {
        var sql = "insert into budget_table(budget_name, budget_start_date, budget_end_date) values(?, ?, ?)";
        //budgetの追加
        var n = jdbc.update(sql, budgetName, budgetStartDate, budgetEndDate);
        var idSql = "select * from budget_table order by budget_id desc";
        //IDを降順に並び替える（新規予算は必ずIDが一番大きくなる）
        var budget = jdbc.query(idSql, DataClassRowMapper.newInstance(Budget.class));
        //Listの一番上のbudgetを取り出し、IDを抽出
        Integer budgetId = budget.get(0).getBudgetId();
        return budgetId;
    }

    @Override
    public int change(String selectedBudgetName, String budgetName, Date budgetStartDate, Date budgetEndDate) {
        var sql = "update budget_table set budget_name = ?, budget_start_date = ?, budget_end_date = ? where budget_name = ?";
        var n = jdbc.update(sql, budgetName, budgetStartDate, budgetEndDate, selectedBudgetName);
        return n;
    }

    @Override
    public List<Budget> find() {
        String sql = "select * from budget_table";

        List<Budget> budgets = jdbc.query(sql, DataClassRowMapper.newInstance(Budget.class));

        return budgets;
    }

    @Override
    public List<Budget> find(Account account) {
        var accountId = account.getAccountId();
        //accountId=sectionId=budgetIdで検索
        String sql = "select budget_table.budget_id, budget_name, budget_start_date, budget_end_date"
                        + " from account_section_table"
                        + " inner join budget_section_table"
                        + " on account_section_table.section_id = budget_section_table.section_id"
                        + " inner join budget_table"
                        + " on budget_table.budget_id = budget_section_table.budget_id"
                        + " where account_id = ?";
        List<Budget> budgets = jdbc.query(sql, DataClassRowMapper.newInstance(Budget.class), accountId);
        return budgets;
    }
}
