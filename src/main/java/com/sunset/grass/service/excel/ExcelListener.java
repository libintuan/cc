package com.sunset.grass.service.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;


public class ExcelListener extends AnalysisEventListener {
    //自定义用于暂时存储data。
    //可以通过实例获取该值
    public List<List<String>> datas = new ArrayList<>();
    public void invoke(Object object, AnalysisContext context) {
        List<String> stringList= (List<String>) object;
        System.out.println("当前sheet"+context.getCurrentSheet().getSheetNo()+ " 当前行：" + context.getCurrentRowNum()
                + " data:" + stringList.get(0));
        datas.add(stringList);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(stringList);//根据自己业务做处理
    }
    private void doSomething(List<String> string) {
        //1、入库调用接口
    }
    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }
    public List<List<String>> getDatas() { return datas; }
    public void setDatas(List<List<String>> datas) { this.datas = datas; }
}

