package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Software;

import java.util.List;

public interface ISoftwareService {
    public void registerSoftware(String softwareName, String softwareType, String totalNumber, String softwareRemarks, Account account);

    public void renewal(String selectedSoftware,String softwareName, String softwareType,String totalNumber, String softwareRemarks);
    public void deleteSoftware(Integer selectedSoftwareId);
    //public List<Software> findSoftwares();
    public List<Software> findSoftwares(Account account);
}
