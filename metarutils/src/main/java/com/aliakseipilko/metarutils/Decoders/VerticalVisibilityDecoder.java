package com.aliakseipilko.metarutils.Decoders;

import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.CloudCoverCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.LinkedHashMap;
import java.util.Map;

public class VerticalVisibilityDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        Map<String, BaseMetarCode> result = new LinkedHashMap<>();

        block = block.replace("VV", "");
        String d = CloudCoverCodes.VV.getDecoded() + ": " + block;
        result.put(d, CloudCoverCodes.VV);

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        return null;
    }
}
