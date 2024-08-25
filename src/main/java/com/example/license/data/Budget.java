package com.example.license.data;

import java.io.Serializable;
import java.util.Date;

public class Budget implements Serializable {

    private final Integer budgetId;
    private final String budgetName;
    private final Date budgetStartDate;
    private final Date budgetEndDate;
    //private final Integer budgetExist;

    public Budget(Integer budgetId, String budgetName, Date budgetStartDate, Date budgetEndDate) {
        this.budgetId = budgetId;
        this.budgetName = budgetName;
        this.budgetStartDate = budgetStartDate;
        this.budgetEndDate = budgetEndDate;
        //this.budgetExist = budgetExist;
    }

    public Integer getBudgetId() {return budgetId;}

    public String getBudgetName() {
        return budgetName;
    }

    public Date getBudgetStartDate() {
        return budgetStartDate;
    }

    public Date getBudgetEndDate() {
        return budgetEndDate;
    }

    //public Integer getBudgetExist(){ return budgetExist;}

}
