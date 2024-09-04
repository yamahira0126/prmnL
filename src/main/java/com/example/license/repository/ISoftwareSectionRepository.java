package com.example.license.repository;

public interface ISoftwareSectionRepository {
    public int insert(Integer softwareId,Integer sectionId);
    public int delete(Integer softwareId);
}
