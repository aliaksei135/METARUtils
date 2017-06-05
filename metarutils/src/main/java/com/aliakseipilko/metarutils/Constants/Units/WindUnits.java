package com.aliakseipilko.metarutils.Constants.Units;


public enum WindUnits {

    KT("Knots"),
    KPH("Kilometres per Hour"),
    MPS("Metres per Second");

    private final String decode;

    private WindUnits(String s){
        decode = s;
    }

    String getDecoded(){
        return this.decode;
    }
}
