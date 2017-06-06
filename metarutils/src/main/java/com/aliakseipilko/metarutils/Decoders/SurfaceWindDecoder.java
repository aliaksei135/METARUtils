package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.SurfaceWindCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.Constants.Units.WindUnits;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SurfaceWindDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new HashMap<>();

        //Only one of each of these codes in date time block
        outer:
        for (SurfaceWindCodes code : SurfaceWindCodes.values()) {
            // Get the pattern and matcher for this code type
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {
                if (code == SurfaceWindCodes.UNITS) {
                    // Get the matching substring
                    String b = block.substring(m.start(), m.end());
                    // Iterate over wind units
                    for (WindUnits unit : WindUnits.values()) {
                        Matcher ma = Pattern.compile(unit.getRegExp()).matcher(b);
                        if (ma.find()) {
                            // Append units
                            String d = unit.getDecoded();
                            // Add onto result map
                            result.put(d, code);
                            // Consume matched substring
                            block = block.replace(b, "");
                            // Break from *inner* loop and continue *outer* loop
                            continue outer;
                        }
                    }
                }
                // Get the matching substring
                String b = block.substring(m.start(), m.end());
                if (code == SurfaceWindCodes.VRB) {
                    String d = code.getDecoded();
                } else if (code == SurfaceWindCodes.V) {
                    String d = code.getDecoded() + b.replace("V", " to ");
                } else if (code == SurfaceWindCodes.G) {
                    String d = code.getDecoded() + b.replace("G", " ");
                } else {
                    // Append matching substring to code type name
                    String d = code.getDecoded() + ": " + b;
                }
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
        //TODO
        return null;
    }
}
