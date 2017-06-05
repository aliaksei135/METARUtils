package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.Map;

public interface BaseBlockDecoder {

    Map<String, ?> decodeToMap(String block) throws MetarDecodeException;

    String decodeToHumanString(String block) throws MetarDecodeException;
}
