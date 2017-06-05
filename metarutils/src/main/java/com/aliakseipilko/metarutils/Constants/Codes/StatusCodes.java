package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum StatusCodes implements BaseMetarCode {
    AUTO("Automatic Observation");

    private final String decode;

    StatusCodes(String s) {
        decode = s;
    }

    @Override
    public String getDecoded() {
        return this.decode;
    }

    @Override
    public String getRegExp() {
        return null;
    }
}
