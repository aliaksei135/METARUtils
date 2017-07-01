package com.aliakseipilko.metarutils.Constants.Codes;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum CloudCoverCodes implements BaseMetarCode {

    // Coverage
    SKC("Sky Clear", "^(SKC)$"),
    CLR("Sky Clear", "^(CLR)$"),
    NSC("No Significant Cloud", "^(NSC)$"),
    FEW("Few Clouds", "^((?<=FEW)([0-9]{4}))"),
    SCT("Scattered Clouds", "^((?<=SCT)([0-9]{4}))"),
    BKN("Broken Clouds", "^((?<=BKN)([0-9]{4}))"),
    OVC("Overcast Clouds", "^((?<=OVC)([0-9]{4}))"),
    VV("Vertical Visibility", "^(VV)"),
    // Types
    TCU("Towering Cumulus", "(TCU)$"),
    CB("Cumulonimbus", "(CB)$"),
    // Lesser used Unofficial types
    CBMAM("Cumulonimbus mammatus", "(CBMAM)$"),
    ACC("Altocumulus castellatus", "(ACC)$"),
    CLD("Standing Leticular/Rotor", "(CLD)$"),

    UNDET_CLD("Undetermined Cloud", "(\\/\\/\\/)$");

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
