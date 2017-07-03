package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum TemperatureDewpointCodes implements BaseMetarCode {
    TEMP("Temperature", "^(M?[0-9]{2}(?=(\\/M?[0-9]{2})))"),
    DEWPT("Dewpoint", "(M?[0-9]{2}$(?<=(M?[0-9]{2}\\/)))");

    private final String decode;
    private final String regExp;

    TemperatureDewpointCodes(String decode, String regExp) {
        this.decode = decode;
        this.regExp = regExp;
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
