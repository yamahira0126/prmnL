package com.example.license.data;

import java.io.Serializable;

public class AccountSection implements Serializable{

    private final Integer accountId;
    private final Integer sectionId;

    public AccountSection(Integer accountId, Integer sectionId){
        this.accountId = accountId;
        this.sectionId = sectionId;
    }

    public Integer getaccountId() {return accountId;}
    public Integer getSectionId() {return sectionId;}
}
