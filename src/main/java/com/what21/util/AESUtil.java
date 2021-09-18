 package com.what21.util;
 
 import java.security.Key;
 import javax.crypto.Cipher;
 import javax.crypto.KeyGenerator;
 import javax.crypto.SecretKey;
 import javax.crypto.spec.SecretKeySpec;
 public class AESUtil
 {
   public static final String KEY_ALGORITHM = "AES";
   public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
   
   private static Key toKey(byte[] key) throws Exception {
     SecretKey secretKey = new SecretKeySpec(key, "AES");
     
     return secretKey;
   }
   
   public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
     Key k = toKey(key);
     
     Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
 
     
     cipher.init(2, k);
 
     
     return cipher.doFinal(data);
   }
   
   public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
     Key k = toKey(key);
     
     Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
 
     
     cipher.init(1, k);
 
     
     return cipher.doFinal(data);
   }
   
   public static byte[] initKey() throws Exception {
     KeyGenerator kg = KeyGenerator.getInstance("AES");
 
 
 
     
     kg.init(128);
 
     
     SecretKey secretKey = kg.generateKey();
 
     
     return secretKey.getEncoded();
   }
 }


