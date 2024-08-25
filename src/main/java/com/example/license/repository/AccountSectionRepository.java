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
}
