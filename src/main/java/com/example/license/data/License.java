package com.example.license.data;

import java.io.File;
import java.util.Date;

public record License(Integer licenseId, String softwareId, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber) {
}
