package com.example.license.repository;

public interface ITerminalSectionRepository {

    public int insert(Integer terminalId, Integer sectionId);
    public int delete(Integer terminalId);

}
