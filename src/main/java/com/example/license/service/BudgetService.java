package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Budget;
import com.example.license.data.Section;
import com.example.license.repository.IAccountSectionRepository;
import com.example.license.repository.IBudgetRepository;
import com.example.license.repository.IBudgetSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BudgetService implements IBudgetService{
    private IBudgetRepository budgetRepos;
    private IBudgetSectionRepository budgetSectionRepos;

    private IAccountSectionRepository accountSectionRepos;

    @Autowired
    public BudgetService(IBudgetRepository budgetRepos, IBudgetSectionRepository budgetSectionRepos, IAccountSectionRepository accountSectionRepos){
        this.budgetRepos = budgetRepos;
        this.budgetSectionRepos = budgetSectionRepos;
        this.accountSectionRepos = accountSectionRepos;
    }

//    @Override
//    public void registerBudget(String budgetName, Date budgetStarDate, Date budgetEndDate, Section section) {
//        //budgetの追加と追加したbudgetのIDをreturn
//        int budgetId = budgetRepos.insert(budgetName, budgetStarDate, budgetEndDate);
//        System.out.println("予算ID：" + budgetId);
//        System.out.println("sectionID:" + section.getSectionId());
//        //sectionIdとbudgetIdを紐づけ
//        int n = budgetSectionRepos.insert(budgetId, section.getSectionId());
//    }

    @Override
    public void registerBudget(String budgetName, Date budgetStarDate, Date budgetEndDate, Account account) {
        //budgetの追加と追加したbudgetのIDをreturn
        int budgetId = budgetRepos.insert(budgetName, budgetStarDate, budgetEndDate);
        System.out.println("予算ID：" + budgetId);
        Integer sectionId = accountSectionRepos.findSectionId(account.getAccountId());
        System.out.println("sectionID:" + sectionId);
        //sectionIdとbudgetIdを紐づけ
        int n = budgetSectionRepos.insert(budgetId, sectionId);
    }

    @Override
    public void renewal(String selectedBudgetName, String budgetName, Date budgetStartDate, Date budgetEndDate){
        int n = budgetRepos.change(selectedBudgetName, budgetName, budgetStartDate, budgetEndDate);
        System.out.println("記録行数：" + n);
    }

    //budget_tableの情報をすべてListに保持させる
    @Override
    public List<Budget> findBudgets() {
        var budgets = budgetRepos.find();
        System.out.println("データ件数 findBudgets()：" + budgets.size());
        return budgets;
    }

    //budget_tableのaccountに関係した情報をListに保持させる
    @Override
    public List<Budget> findBudgets(Account account) {
        var budgets = budgetRepos.find(account);
        System.out.println("データ件数 findBudgets(account)：" + budgets.size());
        return budgets;
    }
}
