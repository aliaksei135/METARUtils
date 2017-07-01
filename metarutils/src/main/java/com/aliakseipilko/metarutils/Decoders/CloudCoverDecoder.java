package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.CloudCoverCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudCoverDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new HashMap<>();

        //Only one of each of these codes in date time block
        for (CloudCoverCodes code : CloudCoverCodes.values()) {
            // Get the pattern and matcher for this code type
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                // Get the matching substring
                String b = block.substring(m.start(), m.end());
                String d;
                if (code == CloudCoverCodes.TCU
                        || code == CloudCoverCodes.CB
                        || code == CloudCoverCodes.CBMAM
                        || code == CloudCoverCodes.ACC
                        || code == CloudCoverCodes.CLD
                        || code == CloudCoverCodes.UNDET_CLD) {
                    d = code.getDecoded() + " Cloud";
                } else {
                    // Hardcode Feet, as its the only unit used for this
                    d = code.getDecoded() + "clouds at " + b + " feet";
                }
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
        //TODO
        return null;
    }
}
