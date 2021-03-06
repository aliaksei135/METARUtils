package com.aliakseipilko.metarutils.Constants.Units;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum VisibilityUnits implements BaseMetarCode {
    M("Metres", "^([0-9]{4})"),
    SM("Statute Miles", "^(([0-9])\\/?([0-9])?(SM))");

    private final String decode;
    private final String regExp;

    VisibilityUnits(String s, String r) {
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
