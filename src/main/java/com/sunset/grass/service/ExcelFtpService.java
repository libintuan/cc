package com.sunset.grass.service;

import java.util.List;

public interface ExcelFtpService {

    public boolean exportToExcel(String fileName, List<String[]> data);
}
