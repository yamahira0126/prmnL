package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Section;

import java.util.Date;
import java.util.List;

public interface IAccountService {

    //public void registerAccount(String accountName, String accountPass);

    /**
     * ユーザ名とパスワードをデータベースに照合する
     *
     * @param accountName ユーザーネーム
     * @param accountPass パスワード
     * @return 照合成功であれば<code>true</code>, 照合失敗は<code>false</code>
     */
    public boolean existsAccount(String accountName, String accountPass);

    public Account termsFindAccount(String accountName, String accountPass);

    public void registerAccount(String accountName, String accountPassword, String accountMailAddress, Section section);
    public void renewalAccount(Integer selectedAccountId, String accountName, String accountPassword, String accountMailAddress, Section section);
    public void deleteAccount(Integer selectedAccountId);
    public List<Account> findAccounts();
    public List<Account> findAccounts(Account account);
}
