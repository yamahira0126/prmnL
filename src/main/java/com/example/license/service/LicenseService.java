package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.License;
import com.example.license.repository.IAccountSectionRepository;
import com.example.license.repository.ILicenseRepository;
import com.example.license.repository.ILicenseSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LicenseService implements ILicenseService {
    private ILicenseRepository licenseRepos;
    private ILicenseSectionRepository licenseSectionRepos;

    private IAccountSectionRepository accountSectionRepos;

    @Autowired
    public LicenseService(ILicenseRepository licenseRepos, ILicenseSectionRepository licenseSectionRepos, IAccountSectionRepository accountSectionRepos){
        this.licenseRepos = licenseRepos;
        this.licenseSectionRepos = licenseSectionRepos;
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
    public void registerLicense(String softwareId, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber, Account account) {
        //budgetの追加と追加したbudgetのIDをreturn
        int licenseId = licenseRepos.insert(softwareId, licenseStartDate, licenseEndDate, budgetId, terminalId, accountId, serialCode, licenseNumber);
        System.out.println("ライセンスID：" + licenseId);
        Integer sectionId = accountSectionRepos.findSectionId(account.getAccountId());
        System.out.println("sectionID:" + sectionId);
        //sectionIdとbudgetIdを紐づけ
        int n = licenseSectionRepos.insert(licenseId, sectionId);
    }

    @Override
    public void renewal(Integer selectedLicenseId, String softwareId, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber){
        int n = licenseRepos.change(selectedLicenseId, softwareId, licenseStartDate, licenseEndDate, budgetId, terminalId, accountId, serialCode, licenseNumber);
        System.out.println("記録行数：" + n);
    }

    @Override
    public void deleteLicense(Integer selectedLicenseId) {
        int n = licenseRepos.delete(selectedLicenseId);
        System.out.println("記録行数：" + n);
    }

    //budget_tableの情報をすべてListに保持させる　使ってない
//    @Override
//    public List<Budget> findBudgets() {
//        var budgets = budgetRepos.find();
//        System.out.println("データ件数 findBudgets()：" + budgets.size());
//        return budgets;
//    }

    //budget_tableのaccountに関係した情報をListに保持させる
    @Override
    public List<License> findLicenses(Account account) {
        var licenses = licenseRepos.find(account);
        System.out.println("データ件数 findBudgets(account)：" + licenses.size());
        return licenses;
    }
}
