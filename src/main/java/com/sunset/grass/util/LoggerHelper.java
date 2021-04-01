package com.sunset.grass.util;

import com.google.gson.Gson;

public class LoggerHelper {
    public static String outputObj(Object object){
        return new Gson().toJson(object);
    }

}
