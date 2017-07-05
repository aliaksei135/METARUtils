package com.aliakseipilko.metarutils.Constants.Codes;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;


public enum RunwayStateCodes implements BaseMetarCode {
    RWY("Runway", "^([0-3][0-9])"),
    ALL_RWYS("All Runways", "^(88)"),

    RWY_DEP_CLRD("Runway Deposits Cleared", "(CLRD)(?=(([0-9]{2})|\\/\\/))"),

    //Runway Deposits
    RWY_DEPST_CLR("Clear and Dry", "(0)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_DAMP("Damp", "(1)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_WET("Wet", "(2)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_FROST("Frost and Rime", "(3)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_DRY_SNOW("Dry Snow", "(4)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_WET_SNOW("Wet Snow", "(5)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_SLUSH("Slush", "(6)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_ICE("Ice", "(7)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_CMPT_SNOW("Compacted Snow", "(8)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_FRZ_RUT("Frozen Ruts", "(9)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),
    RWY_DEPST_UNKWN("Deposit type unknown", "(\\/)(?=(((1|2|5|9)|(\\/))(([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/))))"),

    //Deposit Extent
    RWY_DEPST_EXT_10("Contamination 10% or less", "(1)(?=((([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_EXT_25("Contamination 11% to 25%", "(2)(?=((([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_EXT_50("Contamination 25% to 50%", "(5)(?=((([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_EXT_100("Contamination 51% to 100%", "(9)(?=((([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_EXT_UNKWN("Contamination extent not reported", "(\\/)(?=((([0-9]{2})|(\\/\\/))(([0-9]{2})|(\\/\\/)))$)"),

    //Deposit Depth
    RWY_DEPST_DTH("Deposit Depth (mm)", "([0-8][0-9])(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_90MM("Deposit Depth 90mm", "(90)(?=((([0-9]{2})|(\\/\\/)))$)"),
    //91 is not used
    RWY_DEPST_DTH_10CM("Deposit Depth 10cm", "(92)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_15CM("Deposit Depth 15cm", "(93)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_20CM("Deposit Depth 20cm", "(94)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_25CM("Deposit Depth 25cm", "(95)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_30CM("Deposit Depth 30cm", "(96)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_35CM("Deposit Depth 35cm", "(97)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_40CM("Deposit Depth 40cm or more", "(98)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_UNREL("Runway Non-operational due Deposits", "(99)(?=((([0-9]{2})|(\\/\\/)))$)"),
    RWY_DEPST_DTH_UNKWN("Deposit Depth Not significant", "(\\/\\/)(?=((([0-9]{2})|(\\/\\/)))$)"),

    //Friction Coeff and Braking
    RWY_FRIC("Runway Friction Coefficient", "(([0-8][0-9])|90)$"),
    RWY_BRK_POOR("Runway Braking Action Poor", "(91)$"),
    RWY_BRK_MEDPOOR("Runway Braking Action Medium/Poor", "(92)$"),
    RWY_BRK_MED("Runway Braking Action Medium", "(93)$"),
    RWY_BRK_MEDGD("Runway Braking Action Medium/Good", "(94)$"),
    RWY_BRK_GD("Runway Braking Action Good", "(95)$"),
    RWY_BRK_UNREL("Runway Braking Figures Unreliable", "(99)$"),
    RWY_BRK_UNKWN("Runway Braking Action Not Reported", "(\\/\\/)$");

    private String decode;
    private String regExp;

    RunwayStateCodes(String decode, String regExp) {
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
