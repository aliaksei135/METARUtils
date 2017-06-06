package com.aliakseipilko.metarutils.Constants.Units;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum WindUnits implements BaseMetarCode {

    KT("Knots"),
    KPH("Kilometres per Hour"),
    MPS("Metres per Second");

    private final String decode;

    WindUnits(String s) {
        decode = s;
    }

    public String getDecoded() {
        return this.decode;
    }

    @Override
    public String getRegExp() {
        return null;
    }


}
