package com.example.license.service;

import com.example.license.data.Software;

import java.util.List;

public interface ISoftwareService {
    public void registerSoftware(String softwareName, String softwareType,String totalNumber, String softwareRemarks);

    public void renewal(String selectedSoftware,String softwareName, String softwareType,String totalNumber, String softwareRemarks);

    public List<Software> findSoftwares();
}
