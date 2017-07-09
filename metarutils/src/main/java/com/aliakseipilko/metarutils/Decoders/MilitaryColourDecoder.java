package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.MilitaryColourCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.aliakseipilko.metarutils.Constants.Codes.MilitaryColourCodes.BLK;

public class MilitaryColourDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        boolean isBlack = false;

        for (MilitaryColourCodes code : MilitaryColourCodes.values()) {
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                if (code == BLK) {
                    isBlack = true;
                    String b = block.substring(m.start(), m.end());
                    block = block.replace(b, "");
                    continue;
                } else {
                    String b = block.substring(m.start(), m.end());
                    String d;
                    if (isBlack) {
                        d = "Military Colour Code: " + MilitaryColourCodes.BLK.getDecoded() + " " + code.getDecoded();
                    } else {
                        d = "Military Colour Code: " + code.getDecoded();
                    }
                    result.put(d, code);
                }
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
