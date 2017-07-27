package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.Constants.Units.CardinalDirections;
import com.aliakseipilko.metarutils.Constants.Units.VisibilityUnits;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisibilityDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        for (VisibilityUnits unit : VisibilityUnits.values()) {
            Matcher m = Pattern.compile(unit.getRegExp()).matcher(block);

            if (m.find()) {
                String b = block.substring(m.start(), m.end());
                block = block.replace(b, "");
                b = b.replace("SM", "");
                String d = b + " " + unit.getDecoded();
                block = block.replace(b, "");
                if (block.length() != 0) {
                    for (CardinalDirections direction : CardinalDirections.values()) {
                        Matcher md = Pattern.compile(direction.getRegExp()).matcher(block);
                        if (md.find()) {
                            d = d + " to the " + direction.getDecoded();
                            block = block.replace(block.substring(md.start(), md.end()), "");
                            break;
                        }
                    }
                }
                result.put(d, unit);
                break;
            }
        }


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
