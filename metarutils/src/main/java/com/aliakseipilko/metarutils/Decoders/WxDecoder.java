package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.Constants.Codes.WxCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WxDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        //Only one of each of these codes in date time block
        for (WxCodes code : WxCodes.values()) {
            // Get the pattern and matcher for this code type
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                // Get the matching substring
                String b = block.substring(m.start(), m.end());
                String d = code.getDecoded() + " ";
                // Add onto result map
                result.put(d, code);
                // Consume matched substring
                block = block.replace(b, "");
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
