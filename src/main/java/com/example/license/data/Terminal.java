package com.example.license.data;

import java.io.Serializable;

public class Terminal implements Serializable {

    private final Integer terminalId;
    private final String terminalName;
    private final String terminalNumber;
    private final String terminalRemarks;
    //private final boolean exist;

    public Terminal(Integer terminalId,String terminalName, String terminalNumber, String terminalRemarks) {
        this.terminalId = terminalId;
        this.terminalName = terminalName;
        this.terminalNumber = terminalNumber;
        this.terminalRemarks = terminalRemarks;
        //this.exist = exist;
    }

    public Integer getTerminalId() {return terminalId; }

    public String getTerminalName() {
        return terminalName;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public String getTerminalRemarks() {
        return terminalRemarks;
    }
    //public boolean getExist(){ return exist;}

}
