package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.RunwayStateCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunwayStateDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new HashMap<>();

        for (RunwayStateCodes code : RunwayStateCodes.values()) {
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                String b = block.substring(m.start(), m.end());
                String d;
                switch (code) {
                    case RWY:
                        d = code.getDecoded() + " " + b;
                        break;
                    case RWY_DEPST_DTH:
                        d = code.getDecoded() + ": " + b + "mm";
                        break;
                    case RWY_FRIC:
                        d = code.getDecoded() + ": 0." + b;
                        break;
                    default:
                        d = code.getDecoded();
                }
                block = block.replace(b, "");
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
        return null;
    }
}
