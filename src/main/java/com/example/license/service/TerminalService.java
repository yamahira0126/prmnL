package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Budget;
import com.example.license.data.Section;
import com.example.license.data.Terminal;
import com.example.license.repository.IAccountSectionRepository;
import com.example.license.repository.ITerminalRepository;
import com.example.license.repository.ITerminalSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService implements  ITerminalService{
    private ITerminalRepository terminalRepos;
    private ITerminalSectionRepository terminalSectionRepos;
    private IAccountSectionRepository accountSectionRepos;

    @Autowired
    public TerminalService(ITerminalRepository terminalRepos, ITerminalSectionRepository terminalSectionRepos,IAccountSectionRepository accountSectionRepos){

        this.terminalRepos = terminalRepos;
        this.terminalSectionRepos = terminalSectionRepos;
        this.accountSectionRepos = accountSectionRepos;
    }

    @Override
    public void registerTerminal(String terminalName, String terminalNumber, String terminalRemarks, Account account) {
        int terminalId = terminalRepos.insert(terminalName, terminalNumber,terminalRemarks);
        System.out.println("端末ID：" + terminalId);
        Integer sectionId = accountSectionRepos.findSectionId(account.getAccountId());
        System.out.println("sectionID:" + sectionId);
        int n = terminalSectionRepos.insert(terminalId, sectionId);
    }

    @Override
    public void renewal(String selectedTerminalName, String terminalName, String terminalNumber, String terminalRemarks){
        int n = terminalRepos.change(selectedTerminalName, terminalName, terminalNumber, terminalRemarks);
        System.out.println("記録行数：" + n);
    }

    @Override
    public void deleteTerminal(Integer selectedTerminalId) {
        int n = terminalRepos.delete(selectedTerminalId);
        System.out.println("記録行数：" + n);
    }

    /*@Override
    public List<Terminal> findTerminals() {
        var terminals = terminalRepos.find();
        System.out.println("データ件数：" + terminals.size());
        return terminals;
    }*/

    //terminal_tableのaccountに関係した情報をListに保持させる
    @Override
    public List<Terminal> findTerminals(Account account) {
        var terminals = terminalRepos.find(account);
        System.out.println("データ件数 findTerminals(account)：" + terminals.size());
        return terminals;
    }
}
