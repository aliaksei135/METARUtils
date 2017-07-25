package com.aliakseipilko.metarutils.Constants.Codes;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;

public enum WxCodes implements BaseMetarCode {

    /* Intensity */
    LIGHT("Light", "^(-)", true),
    HEAVY("Heavy", "^(\\+)", true),

    /* Descriptors */
    MI("Shallow", "(MI)", true),
    PR("Partial", "(PR)", true),
    BC("Patches of", "(BC)", true),
    DR("Low Drifting", "(DR)", true),
    BL("Blowing", "(BL)", true),
    SH("Showers", "(SH)", true),
    FZ("Freezing", "(FZ)", true),
    RE("Recent", "(RE)", true),
    VC("In the vicinity", "(VC)", true),

    /* Precipitation codes */
    DZ("Drizzle", "(DZ)", false),
    RA("Rain", "(RA)", false),
    SN("Snow", "(SN)", false),
    IC("Ice Crystals", "(IC)", false),
    GR("Hail", "(GR)", false),
    SG("Snow Grains", "(SG)", false),
    PL("Ice Pellets", "(PL)", false),
    GS("Small Hail", "(GS)", false),
    UP("Unknown Precipitation", "(UP)", false),

    /* Obscuration Codes */
    FG("Fog", "(FG)", false),
    VA("Volcanic Ash", "(VA)", false),
    BR("Mist", "(BR)", false),
    HZ("Haze", "(HZ)", false),
    DU("Widespread Dust", "(DU)", false),
    FU("Smoke", "(FU)", false),
    SA("Sand", "(SA)", false),
    PY("Spray", "(PY)", false),

    /* Other codes */
    SQ("Squalls", "(SQ)", false),
    PO("Sand Whirls", "(PO)", false),
    DS("Duststorm", "(DS)", false),
    SS("Sandstorm", "(SS)", false),
    FC("Funnel Clouds", "(FC)", false);

    private final String decode;
    private final String regExp;
    private final boolean isDesc;

    WxCodes(String s, String r, boolean d) {
        decode = s;
        regExp = r;
        isDesc = d;
    }

    public String getDecoded() {
        return this.decode;
    }

    public String getRegExp() {
        return this.regExp;
    }

    public boolean isDesc() {
        return this.isDesc;
    }

}
