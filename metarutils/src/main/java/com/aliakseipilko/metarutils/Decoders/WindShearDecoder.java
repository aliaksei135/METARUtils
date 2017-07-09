package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.SurfaceWindCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WindShearDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        if (block.equals("WS ALL RWY")) {
            result.put("Windshear reported for all runways", SurfaceWindCodes.WS);
            block = "";
        } else {
            Matcher m = Pattern.compile("(?<=(WS RWY))([0-9]{2})").matcher(block);
            if (m.find()) {
                String b = block.substring(m.start(), m.end());
                String d = "Windshear reported runway " + b;
                block = "";
            }
        }

        // Add any unconsumed text to map as unknown code
        if (block.trim().length() != 0) {
            result.put(block, UnknownCodes.UKNWN);
        }

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        return null;
    }
}
