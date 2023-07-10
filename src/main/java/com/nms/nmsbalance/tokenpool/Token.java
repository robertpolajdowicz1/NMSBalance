package com.nms.nmsbalance.tokenpool;

public class Token {

    int intType;
    String type;

    public Token(String type, int intType) {
        this.intType = intType;
        this.type = type;
    }

    public int getIntType() {
        return intType;
    }

    public String getType() {
        return type;
    }
}
