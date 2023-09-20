package com.sunset.grass.db.jta;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> DATA_CONTEXT=new ThreadLocal<String>();

    /**
     *
     * @param dateType
     */
    public static void setDataSourceType(String dateType){
        DATA_CONTEXT.set(dateType);
    }

    /**
     *
     * @return
     */
    public static String getDateSourceType(){

        String dateType= DATA_CONTEXT.get();
        if (dateType==null){
            DATA_CONTEXT.set("");
        }
        return DATA_CONTEXT.get();
    }

    /**
     *
     */
    public void clearDataSourceType(){
        DATA_CONTEXT.remove();
    }
}
