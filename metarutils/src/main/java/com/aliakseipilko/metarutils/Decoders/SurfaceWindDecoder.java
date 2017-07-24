package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.SurfaceWindCodes;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.Constants.Units.WindUnits;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SurfaceWindDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        //Only one of each of these codes in date time block
        outer:
        for (SurfaceWindCodes code : SurfaceWindCodes.values()) {
            // Get the pattern and matcher for this code type
            Matcher m = Pattern.compile(code.getRegExp()).matcher(block);
            if (m.find()) {

                // Get the matching substring
                String b = block.substring(m.start(), m.end());
                String d = "";
                if (code == SurfaceWindCodes.VRB) {
                    d = code.getDecoded();
                } else if (code == SurfaceWindCodes.V) {
                    d = code.getDecoded() + b.replace("V", " to ");
                } else if (code == SurfaceWindCodes.G) {
                    d = code.getDecoded() + b.replace("G", " ");
                } else if (code == SurfaceWindCodes.SPEED) {
                    // No units will be present if no wind speed is displayed, so this can be nested
                    Matcher mu = Pattern.compile(SurfaceWindCodes.UNITS.getRegExp()).matcher(block);
                    if (mu.find()) {
                        // Get the matching substring
                        String u = block.substring(mu.start(), mu.end());
                        // Iterate over wind units
                        for (WindUnits unit : WindUnits.values()) {
                            Matcher ma = Pattern.compile(unit.getRegExp()).matcher(u);
                            if (ma.find()) {
                                // Append units
                                String du = unit.getDecoded();
                                // Set the decoded string
                                d = code.getDecoded() + ": " + b + " " + du;
                                // Consume units
                                block = block.replace(u, "");
                                break;
                            } else {
                                d = code.getDecoded() + b;
                            }
                        }
                    }
                } else {
                    // Append matching substring to code type name
                    d = code.getDecoded() + ": " + b;
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
