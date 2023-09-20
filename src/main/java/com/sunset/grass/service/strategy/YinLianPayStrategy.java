package com.sunset.grass.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class YinLianPayStrategy extends BasePayStrategy {

    @Override
    public String toPayhtml() {
        System.out.println("调用银联支付方法");
        testAuHelp.testwuwu();
        return null;
    }

    @Override
    public String getType() {
        return "yinLianPayStrategy";
    }
}
