package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.RVRCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RVRDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        for (RVRCodes code : RVRCodes.values()) {
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                String b = block.substring(m.start(), m.end());
                String d = "";
                if (code == RVRCodes.VIS_TREND_DWN
                        || code == RVRCodes.VIS_TREND_NONE
                        || code == RVRCodes.VIS_TREND_UP) {
                    d = code.getDecoded();
                    block = block.replace(b, "");
                } else if (code == RVRCodes.RWY) {
                    d = code.getDecoded() + ": " + b;
                    block = block.replace("R" + b, "");
                } else if (code == RVRCodes.RWY_VIS) {
                    // RVR is always in metres, so can hardcode
                    d = code.getDecoded() + ": " + b + "Metres";
                    block = block.replace("/" + b, "");
                }
                result.put(d, code);

            }
        }

        // Add any uncomsumed text to map as unknown code
        if (block.length() != 0) {
            result.put(block, UnknownCodes.UKNWN);
        }

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        // TODO
        return null;
    }
}
