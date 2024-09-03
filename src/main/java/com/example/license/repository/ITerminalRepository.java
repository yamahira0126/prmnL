package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Terminal;

import java.util.List;

public interface ITerminalRepository {
    public Integer insert(String terminalName, String terminalNumber, String terminalRemarks);

    public int change(String selectedTerminalName, String terminalName, String terminalNumber, String terminalRemarks);
    public int delete(Integer selectedTerminalId);
    public List<Terminal> find();
    public List<Terminal> find(Account account);
}
