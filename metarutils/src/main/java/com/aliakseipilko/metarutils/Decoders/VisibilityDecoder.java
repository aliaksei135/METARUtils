package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.Constants.Units.CardinalDirections;
import com.aliakseipilko.metarutils.Constants.Units.VisibilityUnits;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisibilityDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new HashMap<>();

        for (VisibilityUnits unit : VisibilityUnits.values()) {
            Matcher m = Pattern.compile(unit.getRegExp()).matcher(block);

            if (m.find()) {
                String b = block.substring(m.start(), m.end());
                String d = b + " " + unit.getDecoded();
                result.put(d, unit);
                block = block.replace(b, "");
                break;
            }
        }

        if (block.length() != 0) {
            for (CardinalDirections direction : CardinalDirections.values()) {
                Matcher m = Pattern.compile(direction.getRegExp()).matcher(block);
                if (m.find()) {
                    result.put(direction.getDecoded(), direction);
                    block = block.replace(block.substring(m.start(), m.end()), "");
                }
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
