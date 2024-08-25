package com.example.license.repository;

import com.example.license.data.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SectionRepository implements ISectionRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public  SectionRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public List<Section> find() {
        var sql = "select * from section_table";

        List<Section> sections = jdbc.query(sql, DataClassRowMapper.newInstance(Section.class));

        return sections;
    }

}
