package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Section;
import com.example.license.data.Terminal;

import java.util.List;

public interface ITerminalService {
    public void registerTerminal(String terminalName, String terminalNumber, String terminalRemarks , Account account);

    public void renewal(String selectedTerminalName, String terminalName, String terminalNumber, String terminalRemarks);
    public void deleteTerminal(Integer selectedTerminalId);
    //public List<Terminal> findTerminals();
    public List<Terminal> findTerminals(Account account);
}
