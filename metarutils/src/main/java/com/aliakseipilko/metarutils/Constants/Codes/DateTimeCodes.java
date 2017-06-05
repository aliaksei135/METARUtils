package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum DateTimeCodes implements BaseMetarCode {
    DOM("Day of Month", "^([0-3][0-9])"),
    TOD("Time of Day", "([0-9]{4}(?=[A-Z]))"),
    MTZ("Military Time Zone", "([A-Z])$");

    private final String decode;
    private final String regExp;

    DateTimeCodes(String s, String r) {
        decode = s;
        regExp = r;
    }

    @Override
    public String getDecoded() {
        return this.decode;
    }

    @Override
    public String getRegExp() {
        return this.regExp;
    }
}
