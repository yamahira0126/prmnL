package com.example.license.repository;

import com.example.license.data.Terminal;

import java.util.List;

public interface ITerminalRepository {
    public Integer insert(String terminalName, String terminalNumber, String terminalRemarks);

    public int change(String selectedTerminalName, String terminalName, String terminalNumber, String terminalRemarks);

    public List<Terminal> find();
}
