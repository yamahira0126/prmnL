package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.repository.IAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{

    private IAccountRepository accountRepos;

    //@Autowired アカウント作成ページ
    public AccountService(IAccountRepository accountRepos) {
        this.accountRepos = accountRepos;
    }

    @Override
    public void registerAccount(String accountName, String accountPass) {
        int n = accountRepos.insert(accountName, accountPass);
        System.out.println("記録行数：" + n);
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

}
