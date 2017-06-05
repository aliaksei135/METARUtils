package com.aliakseipilko.metarutils;


import com.aliakseipilko.metarutils.Constants.MetarBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetarUtils {

    public MetarUtils() {

    }

    public String decodeMetarToString(String metar) throws MetarDecodeException {
        // Tokenise string
        List<String> tokens = Arrays.asList(metar.split(" "));

        Map<String, MetarBlock> blockMap = new HashMap<>();
        String decodedMetar;
        boolean isAfterRMK = false;
        boolean isAfterTrend = false;

        // Assign blocks to METAR
        for (String token: tokens) {
            // Check for METAR end character
            if(token.equals("=")){
                break;
            }

            // Assign block identifiers to each token first
            for(MetarBlock block : MetarBlock.values()){
                // Check if after RMK block, if so then must be a remark
                if(isAfterRMK){
                    blockMap.put(token, MetarBlock.RMK);
                    continue;
                }
                if(isAfterTrend){
                    blockMap.put(token, MetarBlock.TREND);
                    continue;
                }
                if(token.matches(block.getRegExp())){
                    // Store block type in synced array
                    blockMap.put(token, block);

                    // Check if Remarks or Trend sections started, if so trigger bool switch
                    if(block == MetarBlock.RMK){
                        isAfterRMK = true;
                    }
                    if(block == MetarBlock.TREND){
                        isAfterTrend = true;
                    }
                }
            }
        }


        return decodedMetar;
    }

}
