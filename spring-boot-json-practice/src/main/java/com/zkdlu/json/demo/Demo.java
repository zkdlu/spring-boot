package com.zkdlu.json.demo;

public class Demo {
    private int intValue;
    private String stringValue;
    private boolean booleanValue;
    private float floatValue;

    public Demo(int intValue, String stringValue, boolean booleanValue, float floatValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.floatValue = floatValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public boolean hasBooleanValue() {
        return booleanValue;
    }

    public float getFloatValue() {
        return floatValue;
    }
}
