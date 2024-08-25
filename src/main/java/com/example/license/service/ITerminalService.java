package com.example.license.service;

import com.example.license.data.Section;
import com.example.license.data.Terminal;

import java.util.List;

public interface ITerminalService {
    public void registerTerminal(String terminalName, String terminalNumber, String terminalRemarks , Section section);

    public void renewal(String selectedTerminalName, String terminalName, String terminalNumber, String terminalRemarks);

    public List<Terminal> findTerminals();
}
