package com.example.license.repository;

import com.example.license.data.Software;

import java.util.List;

public interface ISoftwareRepository {
    public int insert(String softwareName, String softwareType,String totalNumber, String softwareRemarks);

    public int change(String selectedSoftwareName, String softwareName, String softwareType, String totalNumber, String softwareRemarks);

    public List<Software> find();
}
