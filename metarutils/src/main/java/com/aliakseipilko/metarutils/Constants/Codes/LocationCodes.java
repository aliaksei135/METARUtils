package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum LocationCodes implements BaseMetarCode {
    LOC_ID("");

    private String decode;

    LocationCodes(String s) {
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
