/*
package com.sunset.grass.service.impl;

import com.sunset.grass.service.ExcelFtpService;

import java.io.InputStream;
import java.util.List;

public class ExcelFtpServiceImpl implements ExcelFtpService {
    @Override
    public boolean exportToExcel(String fileName, List<String[]> data) {
        InputStream inputStream = getInputStream("loan1.xls");
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
            excelReader.read();
        } catch (Exception e) {

        } finally {
            try {inputStream
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/
