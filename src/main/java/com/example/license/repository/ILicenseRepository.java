package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.License;

import java.io.File;
import java.util.Date;
import java.util.List;

public interface ILicenseRepository {
    public Integer insert(String softWare, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber);

    public int change(String selectedLicenseName, String softWare, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber);

    public List<License> find();
    public List<License> find(Account account);
}
