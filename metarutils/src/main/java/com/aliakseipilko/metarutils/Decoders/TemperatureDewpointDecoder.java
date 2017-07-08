package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.TemperatureDewpointCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemperatureDewpointDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new HashMap<>();

        for (TemperatureDewpointCodes code : TemperatureDewpointCodes.values()) {
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                String b = block.substring(m.start(), m.end());
                block = block.replace(b, "");
                boolean isNegative = false;
                if (b.matches("(M(?=([0-9]{2})))")) {
                    isNegative = true;
                    b = b.replace("M", "");
                }
                String d;
                if (isNegative) {
                    d = code.getDecoded() + ": -" + b + "°C";
                } else {
                    d = code.getDecoded() + ": " + b + "°C";
                }
                result.put(d, code);
            }
        }

        // Add any uncomsumed text to map as unknown code
        // Account for middle "/" between temp and dew
        if (!(block.trim().length() <= 1)) {
            result.put(block, UnknownCodes.UKNWN);
        }

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        return null;
    }
}
