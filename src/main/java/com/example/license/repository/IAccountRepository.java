package com.example.license.repository;

import com.example.license.data.Account;

import java.util.List;

public interface IAccountRepository {

    /**
     * ユーザー名、パスワードをlogin_tableテーブルに記録する
     *
     * @param accountName ユーザー名
     * @param accountPass パスワード
     * @return データベースの更新行数
     */
    public boolean exists(String accountName, String accountPass);

    public List<Account> termsFind(String accountName, String accountPass);

    public int insert(String accountName, String accountPass, String accountMaliAddress);
    public int change(Integer selectedAccountId, String accountName, String accountPass, String accountMailAddress);
    public int delete(Integer selectedAccountId);
    public List<Account> find();
    public List<Account> find(Account account);

}
