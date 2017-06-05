package com.aliakseipilko.metarutils.Constants.Codes;



public enum WxCodes {

    /* Precipitation codes */
    DZ("Drizzle"),
    RA("Rain"),
    SN("Snow"),
    IC("Ice Crystals"),
    GR("Hail"),
    SG("Snow Grains"),
    PL("Ice Pellets"),
    GS("Small Hail"),
    UP("Unknown Precipitation"),

    /* Obscuration Codes */
    FG("Fog"),
    VA("Volcanic Ash"),
    BR("Mist"),
    HZ("Haze"),
    DU("Widespread Dust"),
    FU("Smoke"),
    SA("Sand"),
    PY("Spray"),

    /* Other codes */
    SQ("Squalls"),
    PO("Sand Whirls"),
    DS("Duststorm"),
    SS("Sandstorm"),
    FC("Funnel Clouds"),

    /* Descriptors */
    MI("Shallow"),
    PR("Partial"),
    BC("Patches of"),
    DR("Low Drifting"),
    BL("Blowing"),
    SH("Showers"),
    FZ("Freezing"),
    RE("Recent"),
    VC("In the vicinity"),

    /* Intensity */
    LIGHT("Light"),
    MOD("Moderate"),
    HEAVY("Heavy");


    private final String decode;

    private WxCodes(String s){
        decode = s;
    }

    String getDecodedValue(){
        return this.decode;
    }


}
