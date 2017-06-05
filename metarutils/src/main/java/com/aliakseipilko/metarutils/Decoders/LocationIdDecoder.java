package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.HashMap;
import java.util.Map;

public class LocationIdDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(final String block) throws MetarDecodeException {
        BaseMetarCode code = new BaseMetarCode() {
            @Override
            public String getDecoded() {
                return block;
            }

            @Override
            public String getRegExp() {
                return null;
            }
        };

        Map<String, BaseMetarCode> result = new HashMap<>();
        result.put(block, code);

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException {
        return null;
    }
}
