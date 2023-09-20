package com.sunset.grass.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class AliPayStrategy extends BasePayStrategy {
    @Override
    public String toPayhtml() {
        System.out.println("调用支付宝支付方法");
        testAuHelp.testhaha();
        return null;
    }

    @Override
    public String getType() {
        return "aliPayStrategy";
    }
}
