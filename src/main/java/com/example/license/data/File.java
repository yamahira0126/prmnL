package com.example.license.data;

import java.io.Serializable;

public class File implements Serializable {
    private Integer id;
    private String fileName;
    private byte[] fileData;
    private Integer sectionId;

    public File(Integer id, String fileName, byte[] fileData, Integer sectionId) {
        this.id = id;
        this.fileName = fileName;
        this.fileData = fileData;
        this.sectionId = sectionId;
    }

    public Integer getId() {return id;}
    public String getFileName() {return fileName;}
    public byte[] getFileData() {return fileData;}
    public Integer getSectionId() {return sectionId;}
}
