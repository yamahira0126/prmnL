package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Budget;

import java.util.Date;
import java.util.List;

public interface IBudgetRepository {
    public Integer insert(String budgetName, Date budgetStartDate, Date budgetEndDate);

    public int change(String selectedBudgetName, String budgetName, Date budgetStartDate, Date budgetEndDate);
    public int delete(Integer selectedBudgetId);
    public List<Budget> find();
    public List<Budget> find(Account account);
}
