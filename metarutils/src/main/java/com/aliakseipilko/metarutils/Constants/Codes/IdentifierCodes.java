package com.aliakseipilko.metarutils.Constants.Codes;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum IdentifierCodes implements BaseMetarCode {
    METAR("METAR", "(^(METAR)$)"),
    SPECI("Special Report", "(^(SPECI)$)"),
    TREND("Trend Report", "(^(TREND)$)");

    private final String decode;
    private final String regExp;

    IdentifierCodes(String s, String r) {
        decode = s;
        regExp = r;
    }

    public String getDecoded() {
        return this.decode;
    }

    public String getRegExp() {
        return this.regExp;
    }
}
