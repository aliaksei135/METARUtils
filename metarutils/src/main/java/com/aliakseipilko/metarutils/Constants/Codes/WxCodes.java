package com.aliakseipilko.metarutils.Constants.Codes;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum WxCodes implements BaseMetarCode {

    /* Precipitation codes */
    DZ("Drizzle", "(DZ)"),
    RA("Rain", "(RA)"),
    SN("Snow", "(SN)"),
    IC("Ice Crystals", "(IC)"),
    GR("Hail", "(GR)"),
    SG("Snow Grains", "(SG)"),
    PL("Ice Pellets", "(PL)"),
    GS("Small Hail", "(GS)"),
    UP("Unknown Precipitation", "(UP)"),

    /* Obscuration Codes */
    FG("Fog", "(FG)"),
    VA("Volcanic Ash", "(VA)"),
    BR("Mist", "(BR)"),
    HZ("Haze", "(HZ)"),
    DU("Widespread Dust", "(DU)"),
    FU("Smoke", "(FU)"),
    SA("Sand", "(SA)"),
    PY("Spray", "(PY)"),

    /* Other codes */
    SQ("Squalls", "(SQ)"),
    PO("Sand Whirls", "(PO)"),
    DS("Duststorm", "(DS)"),
    SS("Sandstorm", "(SS)"),
    FC("Funnel Clouds", "(FC)"),

    /* Descriptors */
    MI("Shallow", "(MI)"),
    PR("Partial", "(PR)"),
    BC("Patches of", "(BC)"),
    DR("Low Drifting", "(DR)"),
    BL("Blowing", "(BL)"),
    SH("Showers", "(SH)"),
    FZ("Freezing", "(FZ)"),
    RE("Recent", "(RE)"),
    VC("In the vicinity", "(VC)"),

    /* Intensity */
    LIGHT("Light", "^(-)"),
    //    MOD("Moderate", "^()"),
    HEAVY("Heavy", "^(\\+)");


    private final String decode;
    private final String regExp;

    WxCodes(String s, String r) {
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
