package com.aliakseipilko.metarutils.Constants;


import com.aliakseipilko.metarutils.Decoders.BaseBlockDecoder;
import com.aliakseipilko.metarutils.Decoders.CAVOKDecoder;
import com.aliakseipilko.metarutils.Decoders.CloudCoverDecoder;
import com.aliakseipilko.metarutils.Decoders.CodeNameDecoder;
import com.aliakseipilko.metarutils.Decoders.DateTimeDecoder;
import com.aliakseipilko.metarutils.Decoders.LocationIdDecoder;
import com.aliakseipilko.metarutils.Decoders.MilitaryColourDecoder;
import com.aliakseipilko.metarutils.Decoders.PressureDecoder;
import com.aliakseipilko.metarutils.Decoders.RVRDecoder;
import com.aliakseipilko.metarutils.Decoders.RemarkDecoder;
import com.aliakseipilko.metarutils.Decoders.RunwayStateDecoder;
import com.aliakseipilko.metarutils.Decoders.StatusDecoder;
import com.aliakseipilko.metarutils.Decoders.SurfaceWindDecoder;
import com.aliakseipilko.metarutils.Decoders.TemperatureDewpointDecoder;
import com.aliakseipilko.metarutils.Decoders.TrendDecoder;
import com.aliakseipilko.metarutils.Decoders.UnknownDecoder;
import com.aliakseipilko.metarutils.Decoders.VerticalVisibilityDecoder;
import com.aliakseipilko.metarutils.Decoders.VisibilityDecoder;
import com.aliakseipilko.metarutils.Decoders.WindShearDecoder;
import com.aliakseipilko.metarutils.Decoders.WxDecoder;

public enum MetarBlock implements BaseMetarCode {

    /* With the help of http://www.skybrary.aero/index.php/Meteorological_Terminal_Air_Report_(METAR) and http://weatherfaqs.org.uk/node/197 */

    CODE("Indicator", "(METAR|SPECI|TREND)", CodeNameDecoder.class),
    LOC_ID("Station ID", "(^[A-Za-z]{4}$)?", LocationIdDecoder.class),
    DATETIME("Date Time", "(^[0-9]{6}[A-Z]{1}$)", DateTimeDecoder.class),
    STATUS("Observation Status", "(AUTO)", StatusDecoder.class),
    SFC_WIND("Surface Wind", "(^(((VRB)|([0-9]{3})|(P))([0-9]{2})(G([0-9]{2}))?(KT|KPH|MPS))$)", SurfaceWindDecoder.class),
    SFC_WIND_VAR("Surface Wind Direction Variance", "(^([0-9]{3})V([0-9]{3})$)", SurfaceWindDecoder.class),
    VIS("Visibility", "(^(([0-9]{4})|(([0-9])\\/?([0-9])?(SM)))((N|NE|E|SE|S|SW|W|NW)?)$)", VisibilityDecoder.class),
    RVR("Runway Visual Range", "(^(R([0-9]([0-9])?(L|C|R)?)\\/((P|M)?([0-9]{4})(U|D|N)?))$)", RVRDecoder.class),
    WX("Weather", "(^(NSW)|((-|\\+)?(MI|PR|BC|DR|BL|SH|FZ|RE|VC)?(DZ|RA|SN|IC|GR|SG|PL|GS|UP|FG|VA|BR|HZ|DU|FU|SA|PY|SQ|PO|DS|SS|FC)(DZ|RA|SN|IC|GR|SG|PL|GS|UP|FG|VA|BR|HZ|DU|FU|SA|PY|SQ|PO|DS|SS|FC)?)$)", WxDecoder.class),
    CLOUD("Cloud Cover", "(^((NSC|CLR|SKC)|((FEW|SCT|BKN|OVC)([0-9]{3})))$)", CloudCoverDecoder.class),
    VV("Vertical Visibility", "(^((VV)((\\/ \\/\\/)|([0-9]{3})))$)", VerticalVisibilityDecoder.class),
    CAVOK("Ceiling and Visibility Ok", "(^(CAVOK)$)", CAVOKDecoder.class),
    TEMP("Temperature and Dewpoint", "(^((M)?([0-9]{2})\\/(M)?([0-9]{2}))$)", TemperatureDewpointDecoder.class),
    PRESS("Atmospheric pressure", "(^((A|Q|M)([0-9]{4}))$)", PressureDecoder.class),
    WS("Windshear", "(^(WS)$)", WindShearDecoder.class),
    MIL_CLR("Military Colour Code", "(^((BLACK)?(BLU|WHT|GRN|YLO1|YLO2|AMB|RED))$)", MilitaryColourDecoder.class),
    RSG("Runway State Group", "(^(R)?((([0-8][0-9])|99)(L|C|R)?(\\/)?((CLRD)|([0-9]{4}))([0-9]{2}))$)", RunwayStateDecoder.class),
    TREND("Trends", "(^((BECMG|TEMPO|NOSIG))$)", TrendDecoder.class),
    RMK("Remarks", "(^(RMK)$)", RemarkDecoder.class),
    UNK("Unknown METAR Block", "(^$)", UnknownDecoder.class);

    private final String decode;
    private final String regExp;
    private final Class<? extends BaseBlockDecoder> decoder;

    MetarBlock(String s, String r, Class<? extends BaseBlockDecoder> d) {
        decode = s;
        regExp = r;
        decoder = d;
    }

    public String getDecoded() {
        return this.decode;
    }

    public String getRegExp(){
        return this.regExp;
    }

    public Class<? extends BaseBlockDecoder> getDecodingClass() {
        return this.decoder;
    }
}
