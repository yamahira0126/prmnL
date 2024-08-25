package com.example.license.repository;

import com.example.license.data.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

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
    public int insert(String accountName, String accountPass) {
        var sql = "insert into account_table(account_name, account_pass) values (?, ?)";
        var n = jdbc.update(sql, accountName, accountPass);
        return n;
    }

    @Override
    public boolean exists(String accountName, String accountPass){
        var sql = "select true from account_table "
                + "where account_name = ? and account_pass = ?";

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

}
