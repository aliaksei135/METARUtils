package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.MetarBlock;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrendDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        if (block.trim().equals("NOSIG")) {
            result.put("No Significant Changes Forecast", MetarBlock.TREND);
        } else {
            block = block.replace("BECMG", "");
            block = block.replace("TEMPO", "");
            // This block is too freeform to decode effectively
            // This is something to implement in the future
            result.put(block, MetarBlock.TREND);
        }

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        return null;
    }
}
