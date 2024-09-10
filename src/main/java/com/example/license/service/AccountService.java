package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Section;
import com.example.license.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService{

    private IAccountRepository accountRepos;
    private IAccountSectionRepository accountSectionRepos;
//    private ISectionRepository sectionRepos;

    @Autowired
    public AccountService(IAccountRepository accountRepos, IAccountSectionRepository accountSectionRepos) {
        this.accountRepos = accountRepos;
        this.accountSectionRepos = accountSectionRepos;
    }

    @Override
    public boolean existsAccount(String accountName, String accountPass) {
        var result = accountRepos.exists(accountName, accountPass);
        System.out.println(accountName + ", " + accountPass + " のユーザ照合結果：" + result);
        var result1 = accountRepos.termsFind(accountName, accountPass);
        if(result1.isEmpty()){
            System.out.println("null");
        } else {
            System.out.println(result1.get(0).getAccountName());
        }
        return result;
    }

    @Override
    public Account termsFindAccount(String accountName, String accountPass) {
        var result = accountRepos.termsFind(accountName, accountPass);
        System.out.println(result.get(0).getAccountName());
        return result.get(0);
    }

    @Override
    public void registerAccount(String accountName, String accountPass, String accountMailAddress, Section section) {
        int accountId = accountRepos.insert(accountName, accountPass, accountMailAddress);
        System.out.println("アカウントID：" + accountId);
        System.out.println("sectionID：" + section.getSectionId());
        accountSectionRepos.insert(accountId, section.getSectionId());
    }

    @Override
    public void renewalAccount(Integer selectedAccountId, String accountName, String accountPassword, String accountMailAddress, Section section){
        int n = accountRepos.change(selectedAccountId, accountName, accountPassword, accountMailAddress);
        accountSectionRepos.change(selectedAccountId, section.getSectionId());
        System.out.println("記録行数：" + n);
    }

    @Override
    public void deleteAccount(Integer selectedAccountId){
        int n = accountRepos.delete(selectedAccountId);
        accountSectionRepos.delete(selectedAccountId);
        System.out.println("記録行数：" + n);
    }

    @Override
    public List<Account> findAccounts(){
        var accounts = accountRepos.find();
        System.out.println("データ件数：" + accounts.size());
        return accounts;
    }

    @Override
    public List<Account> findAccounts(Account account) {
        var accounts = accountRepos.find(account);
        System.out.println("データ件数 findAccounts(account):" + accounts);
        return accounts;
    }

    @Override
    public boolean existsAccount(String mailAddress){
        var result = accountRepos.exists(mailAddress);
        System.out.println(mailAddress + " のユーザ照合結果：" + result);
        return result;
    }

    @Override
    public void renewalPassword(String mailAddress, String newPassword) {
        int n = accountRepos.changePassword(mailAddress, newPassword);
        System.out.println("記録行数：" + n);
    }

}
