package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum MilitaryColourCodes implements BaseMetarCode {
    BLK("Black", "^(BLACK)"),
    BLU("Blue", "(BLU)$"),
    WHT("White", "(WHT)$"),
    GRN("Green", "(GRN)$"),
    YLO1("Yellow 1", "(YLO1)$"),
    YLO2("Yellow 2", "(YLO2)$"),
    AMB("Amber", "(AMB)$"),
    RED("Red", "(RED)$");

    private final String decode;
    private final String regExp;

    MilitaryColourCodes(String decode, String regExp) {
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
