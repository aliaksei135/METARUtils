package com.aliakseipilko.metarutils.Constants.Codes;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum CloudCoverCodes implements BaseMetarCode {

    SKC("Sky Clear", "^(SKC)$"),
    CLR("Sky Clear", "^(CLR)$"),
    NSC("No Significant Cloud", "^(NSC)$"),
    FEW("Few Clouds", "^(FEW)"),
    SCT("Scattered Clouds", "^(SCT)"),
    BKN("Broken Clouds", "^(BKN)"),
    OVC("Overcast Clouds", "^(OVC)"),
    VV("Vertical Visibility", "^(VV)");

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
