package com.sunset.grass.service.strategy;

import com.alibaba.druid.support.json.JSONUtils;
import com.grass.sdk.util.LoggerHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PayFactory {
    @Autowired
    List<PayStrategy> payStrategyList;
//    Map<String,PayStrategy> payStrategyMaps;
//
    public PayStrategy getPayStrategy(String type){
//        System.out.println("strategy方法："+ LoggerHelper.outputObj(payStrategyMaps));
        System.out.println("strategy方法："+ LoggerHelper.outputObj(payStrategyList));
//        return payStrategyMaps.get(type);
        for (PayStrategy payStrategy:payStrategyList) {
            if (StringUtils.equals(type,payStrategy.getType())){
                return payStrategy;
            }
        }
        return null;
    }


}
