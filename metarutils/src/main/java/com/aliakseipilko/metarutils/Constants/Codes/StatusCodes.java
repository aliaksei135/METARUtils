package com.aliakseipilko.metarutils.Constants.Codes;


public enum StatusCodes {
    METAR("METAR", "(^(METAR)$)"),
    SPECI("Special Report", "(^(SPECI)$)"),
    TREND("Trend Report", "(^(TREND)$)");

    private final String decode;
    private final String regExp;

    private StatusCodes(String s, String r) {
        decode = s;
        regExp = r;
    }

    public String getDecoded(){
        return this.decode;
    }

    public String getRegExp(){
        return this.regExp;
    }
}
