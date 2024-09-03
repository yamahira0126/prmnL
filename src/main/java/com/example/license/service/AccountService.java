package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Section;
import com.example.license.repository.AccountSectionRepository;
import com.example.license.repository.IAccountRepository;
import com.example.license.repository.IAccountSectionRepository;
import com.example.license.repository.ISectionRepository;
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
    public void registerAccount(String accountName, String accountPass, Section section) {
        int accountId = accountRepos.insert(accountName, accountPass);
        System.out.println("アカウントID：" + accountId);
        System.out.println("sectionID：" + section.getSectionId());
        accountSectionRepos.insert(accountId, section.getSectionId());
    }

    @Override
    public void renewalAccount(Integer selectedAccountId, String accountName, String accountPassword, Section section){
        int n = accountRepos.change(selectedAccountId, accountName, accountPassword);
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

}
