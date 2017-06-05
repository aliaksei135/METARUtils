package com.aliakseipilko.metarutils.Decoders;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.Codes.StatusCodes;
import com.aliakseipilko.metarutils.MetarDecodeException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CodeNameDecoder implements BaseBlockDecoder {
    @Override
    public Map<String, ? extends BaseMetarCode> decodeToMap(String block) throws MetarDecodeException {
        StatusCodes statusCode = null;
        // Check every code
        for (StatusCodes code : StatusCodes.values()) {
            if(block.matches(code.getRegExp())){
                statusCode = code;
            }
        }
        if(statusCode == null){
            // No match
            throw new MetarDecodeException("Bad indicator group");
        }

        Map<String, StatusCodes> result = new HashMap<>();
        result.put(block, statusCode);

        return result;
    }

    @Override
    public String decodeToHumanString(String block) throws MetarDecodeException{
        // Obtain iterator for block map
        Iterator<? extends Map.Entry<String, ? extends BaseMetarCode>> iter = decodeToMap(block).entrySet().iterator();
        String decodedBlock = "";

        while(iter.hasNext()){
            // Some dirty type casting here
            // Concatenate all decoded values with prepended space
            decodedBlock = decodedBlock.concat(" " + iter.next().getValue().getDecoded());
        }

        return decodedBlock;
    }
}
