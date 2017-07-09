package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.StatusCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;

public class StatusDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, StatusCodes> result = new LinkedHashMap<>();
        result.put(StatusCodes.AUTO.getDecoded(), StatusCodes.AUTO);

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        //TODO
        return null;
    }
}
