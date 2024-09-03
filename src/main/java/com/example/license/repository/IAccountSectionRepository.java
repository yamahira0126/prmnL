package com.example.license.repository;

public interface IAccountSectionRepository {

    public Integer findSectionId(Integer accountId);

    public int insert(Integer accountId, Integer sectionId);
    public int change(Integer accountId, Integer sectionId);
    public int delete(Integer accountId);
}
