package com.sunset.grass.service.strategy;

import com.sunset.grass.web.form.test.BaseForm;
import org.springframework.beans.factory.annotation.Autowired;

abstract class BasePayStrategy implements PayStrategy {

    @Autowired
    TestAuHelp testAuHelp;

    public void voliadParam(BaseForm form){

        System.out.println("啊啊啊啊啊啊啊哈哈哈！");
    }


}
