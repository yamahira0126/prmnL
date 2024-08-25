package com.example.license.service;

import com.example.license.data.Section;
import com.example.license.data.Terminal;
import com.example.license.repository.ITerminalRepository;
import com.example.license.repository.ITerminalSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService implements  ITerminalService{
    private ITerminalRepository terminalRepos;
    private ITerminalSectionRepository terminalSectionRepos;

    @Autowired
    public TerminalService(ITerminalRepository terminalRepos, ITerminalSectionRepository terminalSectionRepos){

        this.terminalRepos = terminalRepos;
        this.terminalSectionRepos = terminalSectionRepos;
    }

    @Override
    public void registerTerminal(String terminalName, String terminalNumber, String terminalRemarks, Section section) {
        int terminalId = terminalRepos.insert(terminalName, terminalNumber,terminalRemarks);
        System.out.println("端末ID：" + terminalId);
        System.out.println("sectionID:" + section.getSectionId());
        int n = terminalSectionRepos.insert(terminalId, section.getSectionId());
    }

    @Override
    public void renewal(String selectedTerminalName, String terminalName, String terminalNumber, String terminalRemarks){
        int n = terminalRepos.change(selectedTerminalName, terminalName, terminalNumber, terminalRemarks);
        System.out.println("記録行数：" + n);
    }

    @Override
    public List<Terminal> findTerminals() {
        var terminals = terminalRepos.find();
        System.out.println("データ件数：" + terminals.size());
        return terminals;
    }
}
