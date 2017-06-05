package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum LocationCode implements BaseMetarCode {
    LOC_ID("");

    private String decode;

    LocationCode(String s) {
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
