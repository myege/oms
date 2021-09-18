 package com.zt.aes256;
 
 import java.util.HashMap;
 import java.util.Map;
 
 public class HmacSha512CoderFactory {
   static Map<String, HmacSha512Coder> hmacSha512CoderMap = new HashMap<>();
   
   public static HmacSha512Coder getAESCBCCoderObject(String keySeeds) {
     if (hmacSha512CoderMap.get(keySeeds) == null) {
       HmacSha512Coder hmacSha512Coder = new HmacSha512Coder();
       
       hmacSha512Coder.setKeySeeds(keySeeds);
       
       hmacSha512CoderMap.put(keySeeds, hmacSha512Coder);
     } 
     return hmacSha512CoderMap.get(keySeeds);
   }
   
   public static String getHmacSha512Coder(String keySeeds, String data) {
     String Hdata;
     if (hmacSha512CoderMap.get(keySeeds) == null) {
       HmacSha512Coder hmacSha512Coder = new HmacSha512Coder();
       
       hmacSha512Coder.setKeySeeds(keySeeds);
       
       hmacSha512CoderMap.put(keySeeds, hmacSha512Coder);
     } 
     try {
       Hdata = ((HmacSha512Coder)hmacSha512CoderMap.get(keySeeds))
         .generateHMAC(data);
     } catch (Exception e) {
       e.printStackTrace();
       return "error";
     } 
     return Hdata;
   }
 }


