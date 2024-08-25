package com.example.license.data;

import java.io.Serializable;

public class TerminalSection implements Serializable {

    private final Integer terminalId;
    private final Integer sectionId;

    public TerminalSection(Integer terminalId, Integer sectionId){
        this.terminalId = terminalId;
        this.sectionId = sectionId;
    }

    public Integer getTerminalId() {return terminalId;}
    public Integer getSectionId() {return sectionId;}
}
