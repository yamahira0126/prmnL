package com.example.license.data;

import java.io.Serializable;

public class Section implements Serializable {

    private final int sectionId;
    private final String sectionName;

    public Section(int sectionId, String sectionName) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
    }

    public int getSectionId() {return sectionId;}
    public String getSectionName() {
        return sectionName;
    }
}
