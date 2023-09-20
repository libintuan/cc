package com.sunset.grass.entity;

public enum StrategyEnum {
    ALI("aliPayStrategy","aliPayStrategy"),
    YUN("yinLianPayStrategy","yinLianPayStrategy")
    ;

    StrategyEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;
    private String name;

    public StrategyEnum getStrategyType(String type){
        for (StrategyEnum strategyEnum:StrategyEnum.values()) {
            if (strategyEnum.getType().equals(type)){
                return strategyEnum;
            }
        }
        return null;
    }

}
