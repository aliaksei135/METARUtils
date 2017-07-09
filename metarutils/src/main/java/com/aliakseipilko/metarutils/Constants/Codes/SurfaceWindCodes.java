package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum SurfaceWindCodes implements BaseMetarCode {
    VRB("Variable Direction", "(^(VRB)(?=([0-9]{2}(KT|MPS|KPH)$)))"),
    G("Gusting", "(?<=([0-9]{2}))G([0-9]{2})"),
    V("Varying direction", "^([0-3][0-9]0)V([0-3][0-9]0)$"),
    DIRECTION("Wind bearing", "([0-3][0-9]0)(?=([0-9]{2}))"),
    SPEED("Wind speed", "((?<=([0-3][0-9]0))([0-9]{2}))|((?=(KT|MPS|KPH)$)([0-9]{2}))"),
    UNITS("", "(KT|MPS|KPH)$"),
    WS("WindShear", "^(WS)$");

    private final String decode;
    private final String regExp;

    SurfaceWindCodes(String s, String r) {
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
