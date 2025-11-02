package com.gameover.hub.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ConvertValues{

    public static BigDecimal convertBigDecimal(String value){
        try{
			value = value.replace(",", ".");
            return new BigDecimal(value);
        }catch (Exception e){
			log.error("Erro ao converter valor: {} para BigDecimal", value, e);
            return null;
        }
    }

    public static int convertInt(String value){
        try{
            return Integer.parseInt(value);
        }catch (Exception e){
            log.error("Erro ao converter valor: {} para int", value, e);
            return 0;
        }
    }

}
