package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Budget;
//import com.example.license.data.Section;

import java.util.Date;
import java.util.List;

public interface IBudgetService {
    //public void registerBudget(String budgetName, Date budgetStartDate, Date budgetEndDate, Section section);

    public void registerBudget(String budgetName, Date budgetStartDate, Date budgetEndDate, Account account);

    public void renewal(String selectedBudgetName, String budgetName, Date budgetStartDate, Date budgetEndDate);
    public void deleteBudget(Integer selectedBudgetId);
    //public List<Budget> findBudgets();
    public List<Budget> findBudgets(Account account);
}
