package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum RVRCodes implements BaseMetarCode {
    RWY("Runway", "^((?<=R)[0-3][0-9](L|C|R)?)"),
    RWY_VIS("Runway Visibility", "((?<=\\/)([0-9]{4}))"),
    VIS_TREND_UP("and Increasing", "((?<=[0-9]{4})U)$"),
    VIS_TREND_DWN("and Decreasing", "((?<=[0-9]{4})D)$"),
    VIS_TREND_NONE("and Not Changing", "((?<=[0-9]{4})N)$");


    private final String decode;
    private final String regExp;

    RVRCodes(String decode, String regExp) {
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
