package com.aliakseipilko.metarutils.Constants.Units;


public enum PressureUnits {
    HPA("HectoPascals"),
    INHG("Inches of Mercury"),
    MMHG("Millimetres of Mercury");

    private final String decode;

    private PressureUnits(String s){
        decode = s;
    }

    String getDecoded(){
        return this.decode;
    }
}
