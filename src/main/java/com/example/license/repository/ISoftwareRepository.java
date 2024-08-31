package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.Software;

import java.util.List;

public interface ISoftwareRepository {
    public Integer insert(String softwareName, String softwareType,String totalNumber, String softwareRemarks);

    public int change(String selectedSoftwareName, String softwareName, String softwareType, String totalNumber, String softwareRemarks);

    public List<Software> find();

    public List<Software> find(Account account);
}
