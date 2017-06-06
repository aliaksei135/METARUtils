package com.aliakseipilko.metarutils.Constants.Units;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum WindUnits implements BaseMetarCode {

    KT("Knots", "(KT)$"),
    // Never seen this used but apparently its a thing ¯\_(ツ)_/¯
    KPH("Kilometres per Hour", "(KPH)$"),
    MPS("Metres per Second", "(MPS)$");

    private final String decode;
    private final String regExp;

    WindUnits(String s, String r) {
        decode = s;
        regExp = r;
    }

    public String getDecoded() {
        return this.decode;
    }

    @Override
    public String getRegExp() {
        return this.regExp;
    }


}
