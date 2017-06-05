package com.aliakseipilko.metarutils.Constants.Units;


public enum VisibilityUnits {
    M("Metres"),
    SM("Statute Miles");

    private final String decode;

    private VisibilityUnits(String s){
        decode = s;
    }

    String getDecoded(){
        return this.decode;
    }
}
