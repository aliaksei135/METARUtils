package com.aliakseipilko.metarutils.Constants;


public enum MetarBlock {

    /* With the help of http://www.skybrary.aero/index.php/Meteorological_Terminal_Air_Report_(METAR) and http://weatherfaqs.org.uk/node/197 */

    CODE("(METAR|SPECI|TREND)"),
    LOC_ID("(^[A-Za-z]{4}$)"),
    DATETIME("(^[0-9]{6}[A-Z]{1}$)"),
    STATUS("(AUTO)"),
    SFC_WIND("(^(((VRB)|([0-9]{3})|(P))([0-9]{2})(G([0-9]{2}))(KT|KPH|MPS))$)"),
    SFC_WIND_VAR("(^([0-9]{3})V([0-9]{3})$)"),
    VIS("(^(([0-9]{4}$)|(([0-9])\\/?([0-9])?(SM)))$)"),
    RVR("(^(R([0-9]([0-9])?(L|C|R)?)\\/((P|M)([0-9]{4})(U|D|N)?))$)"),
    WX("(^(NSW)|((-|\\+)?(MI|PR|BC|DR|BL|SH|FZ|RE|VC)?(DZ|RA|SN|IC|GR|SG|PL|GS|UP|FG|VA|BR|HZ|DU|FU|SA|PY|SQ|PO|DS|SS|FC)(DZ|RA|SN|IC|GR|SG|PL|GS|UP|FG|VA|BR|HZ|DU|FU|SA|PY|SQ|PO|DS|SS|FC)?)$)"),
    CLOUD("(^((NSC|CLR|SKC)|((FEW|SCT|BKN|OVC)([0-9]{3})))$)"),
    VV("(^((VV)((\\/\\/\\/)|([0-9]{3})))$)"),
    CAVOK("(^(CAVOK)$)"),
    TEMP("(^((M)?([0-9]{2})\\/(M)?([0-9]{2}))$)"),
    PRESS("(^((A|Q|M)([0-9]{4}))$)"),
    WS("(^(WS)$)"),
    MIL_CLR("(^((BLACK)?(BLU|WHT|GRN|YLO1|YLO2|AMB|RED))$)"),
    RSG("(^((([0-8][0-9])|99)((CLRD)|([0-9]{4}))([0-9]{2}))$)"),
    TREND("(^((BECMG|TEMPO))$)"),
    RMK("(^(RMK)$)"),
    UNK("(^$)");

    private final String regExp;

    private MetarBlock(String s){
        regExp = s;
    }

    public String getRegExp(){
        return this.regExp;
    }
}
