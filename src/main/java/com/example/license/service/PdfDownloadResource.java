package com.example.license.service;

import com.example.license.MySession;
import com.example.license.data.File;
import com.example.license.repository.FileUploadRepository;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class PdfDownloadResource extends AbstractResource {

    private byte[] fileData;

    public PdfDownloadResource(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse response = new ResourceResponse();
        response.setContentType("application/pdf");
        response.setWriteCallback(new WriteCallback() {
            @Override
            public void writeData(Attributes attributes) throws IOException {
                OutputStream out = attributes.getResponse().getOutputStream();
                out.write(fileData);

            }
        });
        return response;
    }
}
