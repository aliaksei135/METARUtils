package com.aliakseipilko.metarutils.Constants.Units;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum CardinalDirections implements BaseMetarCode {
    N("North", "N$"),
    NE("North East", "NE$"),
    E("East", "E$"),
    SE("South East", "SE$"),
    S("South", "S$"),
    SW("South West", "SW$"),
    W("West", "W$"),
    NW("North West", "NW$");

    private final String decode;
    private final String regExp;

    CardinalDirections(String s, String r) {
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
