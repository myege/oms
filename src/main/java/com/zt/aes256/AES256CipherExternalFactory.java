 package com.zt.aes256;
 
 import java.util.HashMap;
 import java.util.Map;
 
 public class AES256CipherExternalFactory {
   static Map<String, AES256CipherExternal> AES256CipherExternalMap = new HashMap<>();
   
   public static AES256CipherExternal getAES256CipherExternalObject(String key) {
     if (AES256CipherExternalMap.get(key) == null) {
       AES256CipherExternal AES256CipherExternal = new AES256CipherExternal(key);
       AES256CipherExternalMap.put(key, AES256CipherExternal);
     } 
     return AES256CipherExternalMap.get(key);
   }
   public static String AES256Encode(String data, String key) {
     String encodeText;
     AES256CipherExternal aes256 = getAES256CipherExternalObject(key);
     
     try {
       encodeText = aes256.AES_Encode(data);
     } catch (Exception e) {
       e.printStackTrace();
       return "error";
     } 
     return encodeText;
   }
   public static String AES256Decode(String data, String key) {
     String decodeText;
     AES256CipherExternal aes256 = getAES256CipherExternalObject(key);
     
     try {
       decodeText = aes256.AES_Decode(data);
     } catch (Exception e) {
       e.printStackTrace();
       return "error";
     } 
     return decodeText;
   }
   
   public static void main(String[] args) throws Exception {
     AES256CipherExternal aes256 = getAES256CipherExternalObject("abcdefghijklmnopqrstuvwxyz123456");
     
     String plainText = "this is a test 中文内容------";
     String encodeText = aes256.AES_Encode(plainText);
 
     
     String decodeText = aes256.AES_Decode(encodeText);
   }
 }


