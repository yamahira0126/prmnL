package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository{

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean exists(String accountName, String accountPass){
        var sql = "select true from account_table "
                + "where account_name = ? and account_pass = ? and account_exist = 1";

        var booles = jdbc.query(sql,
                SingleColumnRowMapper.newInstance(Boolean.class),
                accountName, accountPass);

        return !booles.isEmpty();
    }

    @Override
    public List<Account> termsFind(String accountName, String accountPass){
        var sql = "select * from account_table "
                + "where account_name = ? and account_pass = ?";

        var accounts = jdbc.query(sql,
                DataClassRowMapper.newInstance(Account.class), accountName, accountPass);

        return accounts;
    }

    @Override
    public int insert(String accountName, String accountPass) {
        var sql = "insert into account_table(account_name, account_pass) values (?, ?)";
        jdbc.update(sql, accountName, accountPass);
        var idSql = "select * from account_table order by account_id desc";
        var account = jdbc.query(idSql, DataClassRowMapper.newInstance(Account.class));
        int accountId = account.get(0).getAccountId();
        return accountId;
    }

    @Override
    public int change(Integer selectedAccountId, String accountName, String accountPass) {
        var sql = "update account_table set account_name = ?, account_pass = ? where account_id = ?";
        var n = jdbc.update(sql, accountName, accountPass, selectedAccountId);
        return n;
    }

    @Override
    public int delete(Integer selectedAccountId) {
        var sql = "update account_table set account_exist = 0 where account_id = ?";
        var n = jdbc.update(sql,selectedAccountId);
        return n;
    }

    @Override
    public List<Account> find(){
        String sql = "select * from account_table　where account_exist = 1";
        List<Account> accounts = jdbc.query(sql, DataClassRowMapper.newInstance(Account.class));
        return accounts;
    }

}
