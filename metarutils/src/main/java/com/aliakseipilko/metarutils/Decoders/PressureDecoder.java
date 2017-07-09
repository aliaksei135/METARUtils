package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.Constants.Units.PressureUnits;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PressureDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        for (PressureUnits unit : PressureUnits.values()) {
            Matcher m = Pattern.compile(unit.getRegExp()).matcher(block);
            if (m.find()) {
                block = block.replace(block.substring(m.start(), m.end()), "");
                String d;
                if (unit == PressureUnits.INHG) {
                    String c = block.substring(0, 2) + "." + block.substring(2, 4);
                    d = c + " " + unit.getDecoded();
                    block = block.replace(c, "");
                } else {
                    String c = block.substring(0, 4);
                    d = c + " " + unit.getDecoded();
                    block = block.replace(c, "");
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
        //TODO
        return null;
    }
}
