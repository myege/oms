 package com.what21.converter;
 
 import org.springframework.core.convert.converter.Converter;
 
 public class IntegerConverter
   implements Converter<String, Integer>
 {
   public Integer convert(String arg0) {
     if (arg0 == null || "".equals(arg0)) {
       return null;
     }
     int i = 0;
     try {
       i = Integer.parseInt(arg0);
     } catch (Exception e) {
       e.printStackTrace();
       return null;
     } 
     return Integer.valueOf(i);
   }
 }


