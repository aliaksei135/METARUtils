package com.aliakseipilko.metarutils.Constants.Codes;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum CloudCoverCodes implements BaseMetarCode {

    SKC,
    CLR,
    NSC,
    FEW,
    SCT,
    BKN,
    OVC,
    VV;

    private final String decode;
    private final String regExp;

    CloudCoverCodes(String s, String r) {
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
