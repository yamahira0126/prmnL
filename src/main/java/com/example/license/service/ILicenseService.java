package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.License;

import java.util.Date;
import java.util.List;

public interface ILicenseService {
    public void registerLicense(Integer softwareId,
                                Date licenseStartDate,
                                Date licenseEndDate,
                                Integer budgetId,
                                Integer terminalId,
                                Integer accountId,
                                String serialCode,
                                String licenseNumber,
                                Account account);

    public void renewal(Integer selectedLicenseId, Integer softwareId, Date licenseStartDate, Date licenseEndDate, Integer budgetId, Integer terminalId, Integer accountId, String serialCode, String licenseNumber);
    public void deleteLicense(Integer selectedLicenseId);
    public List<License> findLicenses(Account account);
}




