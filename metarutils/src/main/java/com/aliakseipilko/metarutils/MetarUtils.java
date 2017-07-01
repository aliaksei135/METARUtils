package com.aliakseipilko.metarutils;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.MetarBlock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MetarUtils {

    public MetarUtils() {

    }

    public String decodeMetarToString(String metar) throws MetarDecodeException {
        Map<MetarBlock, Map<String, ? extends BaseMetarCode>> decodedMap = decodeMetarToMap(metar);
        Iterator<Map.Entry<MetarBlock, Map<String, ? extends BaseMetarCode>>> blockIter = decodedMap.entrySet().iterator();
        String decodedMetar = "";

        while (blockIter.hasNext()) {
            // Get block level entry
            Map.Entry<MetarBlock, Map<String, ? extends BaseMetarCode>> entry = blockIter.next();
            // Get and append title of block to decoded metar string
            decodedMetar = decodedMetar.concat(entry.getKey().getDecoded() + ":\n");
            // Obtain iterator for inner map
            Iterator<? extends Map.Entry<String, ? extends BaseMetarCode>> elementIter = entry.getValue().entrySet().iterator();
            // Iterate over inner elements of block and append them to decoded metar string
            while (elementIter.hasNext()) {
                // Append decoded element value to decoded metar string
                decodedMetar = decodedMetar.concat(elementIter.next().getKey() + "\n");
            }
        }

        return decodedMetar;
    }

    public Map<MetarBlock, Map<String, ? extends BaseMetarCode>> decodeMetarToMap(String metar) throws MetarDecodeException {
        // Tokenise string
        List<String> tokens = Arrays.asList(metar.split(" "));

        Map<String, MetarBlock> blockMap = new HashMap<>();
        boolean isAfterRMK = false;
        boolean isAfterTrend = false;
        boolean isAfterWS = false;

        // Assign blocks to METAR
        for (String token: tokens) {
            // Check for METAR end character
            if(token.equals("=")){
                break;
            }

            // Assign block identifiers to each token first
            for(MetarBlock block : MetarBlock.values()){
                // Check for new section
                if (token.matches(MetarBlock.RMK.getRegExp())) {
                    isAfterRMK = true;
                    isAfterTrend = false;
                    isAfterWS = false;
                }
                if (token.matches(MetarBlock.TREND.getRegExp())) {
                    isAfterTrend = true;
                    isAfterRMK = false;
                    isAfterWS = false;
                }
                if (token.matches(MetarBlock.WS.getRegExp())) {
                    isAfterWS = true;
                    isAfterRMK = false;
                    isAfterTrend = false;
                }
                // Check if after RMK block, if so then must be a remark
                if(isAfterRMK){
                    blockMap.put(token, MetarBlock.RMK);
                    continue;
                }
                // Check if after TREND block, if so then must be a Trend
                if(isAfterTrend){
                    blockMap.put(token, MetarBlock.TREND);
                    continue;
                }
                // Check if after WS block, if so then must be windshear rwys
                if (isAfterWS) {
                    blockMap.put(token, MetarBlock.WS);
                    continue;
                }
                if(token.matches(block.getRegExp())){
                    // Store block type in synced array
                    blockMap.put(token, block);

                    // Check if Remarks or Trend sections started, if so trigger bool switch
                    if(block == MetarBlock.RMK){
                        isAfterRMK = true;
                        isAfterTrend = false;
                        isAfterWS = false;
                    }
                    if(block == MetarBlock.TREND){
                        isAfterTrend = true;
                        isAfterRMK = false;
                        isAfterWS = false;
                    }
                    if (block == MetarBlock.WS) {
                        isAfterWS = true;
                        isAfterRMK = false;
                        isAfterTrend = false;
                    }
                }
            }
        }


    }

}
