package com.example.license.data;

import java.io.Serializable;
import java.util.Date;

public class License implements Serializable {
    private Integer licenseId;
    private Integer softwareId;
    private Date licenseStartDate;
    private Date licenseEndDate;
    private Integer budgetId;
    private Integer terminalId;
    private Integer accountId;
    private String serialCode;
    private Integer licenseNumber;

    public License(Integer licenseId, Integer softwareId, Date licenseStartDate, Date licenseEndDate, Integer budgetId, Integer terminalId, Integer accountId, String serialCode, Integer licenseNumber) {
        this.licenseId = licenseId;
        this.softwareId = softwareId;
        this.licenseStartDate = licenseStartDate;
        this.licenseEndDate = licenseEndDate;
        this.budgetId = budgetId;
        this.terminalId = terminalId;
        this.accountId = accountId;
        this.serialCode = serialCode;
        this.licenseNumber = licenseNumber;
    }

    public Integer getLicenseId() {return licenseId;}
    public Integer getSoftwareId() {return softwareId;}
    public Date getLicenseStartDate() {return licenseStartDate;}
    public Date getLicenseEndDate() {return licenseEndDate;}
    public Integer getBudgetId() {return budgetId;}
    public Integer getTerminalId() {return terminalId;}
    public Integer getAccountId() {return accountId;}
    public String getSerialCode() {return serialCode;}
    public Integer getLicenseNumber() {return licenseNumber;}

}