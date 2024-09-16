package com.example.license.repository;

import com.example.license.data.Account;
import com.example.license.data.License;

import java.io.File;
import java.util.Date;
import java.util.List;

public interface ILicenseRepository {
    public Integer insert(Integer softwareId, Date licenseStartDate, Date licenseEndDate, Integer budgetId, Integer terminalId, Integer accountId, String serialCode, String licenseNumber, String licenseRemarksName, byte[] licenseRemarksData);

    public int change(Integer selectedLicenseId, Integer softwareId, Date licenseStartDate, Date licenseEndDate, Integer budgetId, Integer terminalId, Integer accountId, String serialCode, String licenseNumber, String licenseRemarksName, byte[] licenseRemarksData);

    public int delete(Integer selectedLicenseId);

    public List<License> find();
    public List<License> find(Account account);
}
