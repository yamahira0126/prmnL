package com.example.license.repository;

import com.example.license.data.AccountSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AccountSectionRepository implements IAccountSectionRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public AccountSectionRepository(JdbcTemplate jdbc){ this.jdbc = jdbc;}

    //account_section_tableからアカウントIDでセクションIDを検索する
    @Override
    public Integer findSectionId(Integer accountId){
        System.out.println("accountId:"+accountId);
        String sql = "select * from account_section_table where account_id = ?";

        Integer sectionId = jdbc.query(sql, DataClassRowMapper.newInstance(AccountSection.class), accountId).get(0).getSectionId();
        System.out.println("sectionId:"+sectionId);
        return sectionId;
    }

    @Override
    public int insert(Integer accountId, Integer sectionId) {
        var sql = "insert into account_section_table(account_id, section_id) values(?,?)";
        var n = jdbc.update(sql, accountId, sectionId);
        return n;
    }

    @Override
    public int change(Integer accountId, Integer sectionId) {
        var sql = "update account_section_table set section_id = ? where account_id = ?";
        var n = jdbc.update(sql, sectionId, accountId);
        return n;
    }

    @Override
    public int delete(Integer accountId) {
        var sql = "update account_section_table set account_section_exist = 0 where account_id = ?";
        var n = jdbc.update(sql, accountId);
        return n;
    }
}
