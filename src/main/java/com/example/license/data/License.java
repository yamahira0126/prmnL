package com.example.license.data;

import java.io.File;
import java.util.Date;

public record License(Integer licenseId, String softWare, Date licenseStartDate, Date licenseEndDate, String budgetId, String terminalId, String accountId, String serialCode, String licenseNumber) {
}
