package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.RunwayStateCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunwayStateDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        block = block.replace("/", "");

        for (RunwayStateCodes code : RunwayStateCodes.values()) {
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                String b = block.substring(m.start(), m.end());
                String d;
                switch (code) {
                    case RWY:
                        if (b.startsWith("R")) {
                            String h = b.substring(1, b.length());
                            d = code.getDecoded() + " " + h + ":";
                        } else {
                            d = code.getDecoded() + " " + b + ":";
                        }
                        break;
                    case RWY_DEPST_DTH:
                        d = "\t" + code.getDecoded() + ": " + b + "mm";
                        break;
                    case RWY_FRIC:
                        d = "\t" + code.getDecoded() + ": 0." + b;
                        break;
                    default:
                        d = "\t" + code.getDecoded();
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
