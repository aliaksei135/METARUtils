package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum UnknownCodes implements BaseMetarCode {
    UKNWN("Unknown Code");

    private final String decode;

    UnknownCodes(String s) {
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
