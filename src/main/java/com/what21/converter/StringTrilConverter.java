 package com.what21.converter;
 
 import org.springframework.core.convert.converter.Converter;
 
 public class StringTrilConverter
   implements Converter<String, String>
 {
   public String convert(String str) {
     if (str == null)
       return null; 
     if ("".equals(str.trim())) {
       return null;
     }
     return str.trim();
   }
 }


