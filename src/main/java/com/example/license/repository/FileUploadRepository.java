package com.example.license.repository;

import com.example.license.MySession;
import com.example.license.data.File;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileUploadRepository {

    @SpringBean
    private JdbcTemplate jdbc;

    public FileUploadRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void setToDatabese(FileUpload upload) {
        String fileName = upload.getClientFileName();
        byte[] fileData = upload.getBytes();
        var sql = "insert into file_table (file_name, file_data, section_id) values (?, ?, ?)";
        jdbc.update(sql, fileName, fileData, MySession.get().getAccount().getAccountId());
    }

    public List<File> findFile(Integer secitonId) {
        var sql = "select * from file_table where section_id = ?";
        List<File> files = jdbc.query(sql, DataClassRowMapper.newInstance(File.class), secitonId);
        return files;
    }

}
