package com.example.license.data;

import java.io.Serializable;

public class Account implements Serializable {

    private final Integer accountId;
    private final String accountName;
    private final String accountPass;
    private final String accountMailAddress;

    public Account(Integer accountId,String accountName, String accountPass, String accountMailAddress) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPass = accountPass;
        this.accountMailAddress = accountMailAddress;
    }

    public Integer getAccountId() {return accountId; }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public String getAccountMailAddress() {return accountMailAddress;}

}
