package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum StatusCodes implements BaseMetarCode {
    AUTO("Automatic Observation", "^(AUTO)$"),
    CAVOK("Ceiling and Visibility Ok", "^(CAVOK)$");

    private final String decode;
    private final String regExp;

    StatusCodes(String s, String r) {
        decode = s;
        regExp = r;
    }

    @Override
    public String getDecoded() {
        return this.decode;
    }

    @Override
    public String getRegExp() {
        return regExp;
    }
}
