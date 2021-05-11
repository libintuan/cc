package com.sunset.grass.service.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.*;
import java.util.List;

public class ExtelRead {
    private static InputStream getInputStream(String fileName) {
        try {
            return new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<List<String>> testRead() {
        InputStream inputStream = getInputStream("/Users/tuantuan/IdeaProjects/grass/工作簿2.xlsx");
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
            excelReader.read();
            List<List<String>> datas = listener.getDatas();
            datas.remove(0);  //把表头去掉，如果需要表头的话，可以把这句话删除掉
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeExcel(){
        try(OutputStream out = new FileOutputStream("/Users/tuantuan/IdeaProjects/grass/6666.xlsx")) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,false);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            writer.write(ExtelRead.testRead(), sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

