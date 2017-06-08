package com.aliakseipilko.metarutils.Constants.Units;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum PressureUnits implements BaseMetarCode {
    HPA("HectoPascals", "^(Q(?=([0-9]{4})))"),
    INHG("Inches of Mercury", "^(A(?=([0-9]{4})))");

    private final String decode;
    private final String regExp;

    PressureUnits(String s, String r) {
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
