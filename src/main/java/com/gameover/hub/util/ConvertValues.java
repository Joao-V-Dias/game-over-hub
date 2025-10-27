package com.gameover.hub.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ConvertValues{

    public static BigDecimal convertBigDecimal(String value){
        try{
            return new BigDecimal(value);
        }catch (Exception e){
            log.error("Erro ao converter valor: " + value + " para BigDecimal", e);
            return null;
        }
    }

    public static Integer convertInteger(String value){
        try{
            return Integer.parseInt(value);
        }catch (Exception e){
            log.error("Erro ao converter valor: " + value + " para Integer", e);
            return null;
        }
    }

}
