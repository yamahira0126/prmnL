package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Budget;
import com.example.license.data.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TerminalRepository implements ITerminalRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public TerminalRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Integer insert(String terminalName, String terminalNumber, String terminalRemarks) {
        var sql = "insert into terminal_table(terminal_name,terminal_number,terminal_remarks) values(?, ?, ?)";
        var n = jdbc.update(sql, terminalName, terminalNumber,terminalRemarks);
        var idSql = "select * from terminal_table order by terminal_id desc";
        var terminal = jdbc.query(idSql, DataClassRowMapper.newInstance(Terminal.class));
        Integer terminalId = terminal.get(0).getTerminalId();
        return terminalId;
    }

    @Override
    public int change(String selectedTerminalName, String terminalName, String terminalNumber, String terminalRemarks) {
        var sql = "update terminal_table set terminal_name = ?, terminal_number = ?, terminal_remarks = ? where terminal_name = ?";
        var n = jdbc.update(sql, terminalName, terminalNumber, terminalRemarks, selectedTerminalName);
        return n;
    }
    @Override
    public int delete(Integer selectedTerminalId) {
        var sql = "update terminal_table set terminal_exist = ? where terminal_id = ?";
        var n = jdbc.update(sql, 0, selectedTerminalId);
        return n;
    }

    @Override
    public List<Terminal> find() {
        String sql = "select * from terminal_table";

        List<Terminal> terminals = jdbc.query(sql, DataClassRowMapper.newInstance(Terminal.class));

        return terminals;
    }

    @Override
    public List<Terminal> find(Account account) {
        var accountId = account.getAccountId();
        //accountId=sectionId=terminalIdかつterminal_exist=1で検索
        String sql = "select terminal_table.terminal_id,terminal_name, terminal_number, terminal_remarks"
                + " from account_section_table"
                + " inner join terminal_section_table"
                + " on account_section_table.section_id = terminal_section_table.section_id"
                + " inner join terminal_table"
                + " on terminal_table.terminal_id = terminal_section_table.terminal_id"
                + " where account_id = ?"
                + " and terminal_exist = 1";
        List<Terminal> terminals = jdbc.query(sql, DataClassRowMapper.newInstance(Terminal.class), accountId);
        return terminals;
    }
}
