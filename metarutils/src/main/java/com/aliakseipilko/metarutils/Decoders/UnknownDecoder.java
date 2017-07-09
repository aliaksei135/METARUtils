package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.UnknownCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;

public class UnknownDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, UnknownCodes> result = new LinkedHashMap<>();
        result.put(block, UnknownCodes.UKNWN);
        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        //TODO
        return null;
    }
}
