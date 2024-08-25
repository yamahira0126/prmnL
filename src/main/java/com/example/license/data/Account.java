package com.example.license.data;

import java.io.Serializable;

public class Account implements Serializable {

    private final Integer accountId;
    private final String accountName;
    private final String accountPass;

    //private final boolean exist;

    public Account(Integer accountId,String accountName, String accountPass) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPass = accountPass;
        //this.exist = exist;
    }

    public Integer getAccountId() {return accountId; }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPass() {
        return accountPass;
    }

    //public boolean getExist() {return exist;}

}
