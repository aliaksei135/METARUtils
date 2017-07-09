package com.aliakseipilko.metarutils;


import com.aliakseipilko.metarutils.Constants.BaseMetarCode;
import com.aliakseipilko.metarutils.Constants.MetarBlock;
import com.aliakseipilko.metarutils.Decoders.BaseBlockDecoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.aliakseipilko.metarutils.Constants.MetarBlock.TREND;

public class MetarUtils {

    public MetarUtils() {

    }

    public String decodeMetarToString(String metar) throws MetarDecodeException, InstantiationException, IllegalAccessException {

        Map<MetarBlock, Map<String, ? extends BaseMetarCode>> decodedMap = decodeMetarToMap(metar);
        Iterator<Map.Entry<MetarBlock, Map<String, ? extends BaseMetarCode>>> blockIter = decodedMap.entrySet().iterator();
        String decodedMetar = "";

        while (blockIter.hasNext()) {
            // Get block level entry
            Map.Entry<MetarBlock, Map<String, ? extends BaseMetarCode>> entry = blockIter.next();
            // Get and append title of block to decoded metar string
            decodedMetar = decodedMetar.concat(entry.getKey().getDecoded() + ":\n\t\t");
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

    public Map<MetarBlock, Map<String, ? extends BaseMetarCode>> decodeMetarToMap(String metar) throws MetarDecodeException, IllegalAccessException, InstantiationException {
        // Tokenise string
        List<String> tokens = Arrays.asList(metar.split(" "));

        Map<String, MetarBlock> blockMap = new HashMap<>();
        boolean isAfterRMK = false;
        String RMKString = null;

        boolean isAfterTrend = false;
        String TRENDString = null;

        boolean isAfterWS = false;
        String WSString = null;

        // Assign blocks to METAR
        tokenLoop:
        for (String token: tokens) {
            // Check for METAR end character
            if(token.equals("=")){
                if (RMKString != null) {
                    blockMap.put(RMKString, MetarBlock.RMK);
                    RMKString = null;
                }
                if (TRENDString != null) {
                    blockMap.put(TRENDString, MetarBlock.TREND);
                    TRENDString = null;
                }
                if (WSString != null) {
                    blockMap.put(WSString, MetarBlock.WS);
                    WSString = null;
                }
                break;
            }

            // Assign block identifiers to each token first
            blockLoop:
            for(MetarBlock block : MetarBlock.values()){
                // Check for new section
                if (token.matches(MetarBlock.RMK.getRegExp())) {
                    isAfterRMK = true;
                    isAfterTrend = false;
                    isAfterWS = false;
                    if (RMKString != null) {
                        blockMap.put(RMKString, MetarBlock.RMK);
                        RMKString = null;
                    }
                    if (TRENDString != null) {
                        blockMap.put(TRENDString, MetarBlock.TREND);
                        TRENDString = null;
                    }
                    if (WSString != null) {
                        blockMap.put(WSString, MetarBlock.WS);
                        WSString = null;
                    }
                }
                if (token.matches(TREND.getRegExp())) {
                    isAfterTrend = true;
                    isAfterRMK = false;
                    isAfterWS = false;
                    if (RMKString != null) {
                        blockMap.put(RMKString, MetarBlock.RMK);
                        RMKString = null;
                    }
                    if (TRENDString != null) {
                        blockMap.put(TRENDString, MetarBlock.TREND);
                        TRENDString = null;
                    }
                    if (WSString != null) {
                        blockMap.put(WSString, MetarBlock.WS);
                        WSString = null;
                    }

                }
                if (token.matches(MetarBlock.WS.getRegExp())) {
                    isAfterWS = true;
                    isAfterRMK = false;
                    isAfterTrend = false;
                    if (RMKString != null) {
                        blockMap.put(RMKString, MetarBlock.RMK);
                        RMKString = null;
                    }
                    if (TRENDString != null) {
                        blockMap.put(TRENDString, MetarBlock.TREND);
                        TRENDString = null;
                    }
                    if (WSString != null) {
                        blockMap.put(WSString, MetarBlock.WS);
                        WSString = null;
                    }
                }
                // Check if after RMK block, if so then must be a remark
                if(isAfterRMK){
                    if (RMKString == null) {
                        RMKString = "";
                    }
                    RMKString = RMKString + " " + token;
                    continue blockLoop;
                }
                // Check if after TREND block, if so then must be a Trend
                if(isAfterTrend){
                    if (TRENDString == null) {
                        TRENDString = "";
                    }
                    TRENDString = TRENDString + " " + token;
                    continue blockLoop;
                }
                // Check if after WS block, if so then must be windshear rwys
                if (isAfterWS) {
                    if (WSString == null) {
                        WSString = "";
                    }
                    WSString = WSString + " " + token;
                    continue blockLoop;
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
                    if (block == TREND) {
                        isAfterTrend = true;
                        isAfterRMK = false;
                        isAfterWS = false;
                    }
                    if (block == MetarBlock.WS) {
                        isAfterWS = true;
                        isAfterRMK = false;
                        isAfterTrend = false;
                    }
                    continue tokenLoop;
                }
            }
        }

        // Add any loose sections on
        if (RMKString != null) {
            blockMap.put(RMKString, MetarBlock.RMK);
            RMKString = null;
        }
        if (TRENDString != null) {
            blockMap.put(TRENDString, MetarBlock.TREND);
            TRENDString = null;
        }
        if (WSString != null) {
            blockMap.put(WSString, MetarBlock.WS);
            WSString = null;
        }

        // NOTE: Key and value have changed places here
        Map<MetarBlock, Map<String, ? extends BaseMetarCode>> decodedMap = new LinkedHashMap<>();

        for (Map.Entry<String, MetarBlock> entry : blockMap.entrySet()) {
            //Get the METARBlock for this block
            MetarBlock block = entry.getValue();
            //Get the decoder for this block and instantiate it
            BaseBlockDecoder decoder = block.getDecodingClass().newInstance();
            //Add the block with the decoded pair from the decoder
            decodedMap.put(block, decoder.decodeToMap(entry.getKey()));
        }

        return decodedMap;
    }

}
