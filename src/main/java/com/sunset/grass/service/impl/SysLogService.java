package com.sunset.grass.service.impl;

import com.google.gson.Gson;
import com.sunset.grass.entity.SysLogBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysLogService {
    private static String aa="456";

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public boolean saveLog(SysLogBO sysLogBO){
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("进来了88888888888888888888888888888888");
        System.out.println(new Gson().toJson(sysLogBO));
        logger.info(new Gson().toJson(sysLogBO));
        System.out.println(aa);
        return true;
    }

}
