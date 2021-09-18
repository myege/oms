 package com.what21.util;
 
 import java.io.IOException;
 import java.security.NoSuchAlgorithmException;
 import java.security.SecureRandom;
 import java.util.Date;
 import javax.crypto.Cipher;
 import javax.crypto.SecretKey;
 import javax.crypto.SecretKeyFactory;
 import javax.crypto.spec.DESKeySpec;
 import sun.misc.BASE64Decoder;
 import sun.misc.BASE64Encoder;
 public class DESUtil2
 {
   private static final String DES = "DES";
   private static final String UTF8 = "GBK";
   static SecretKeyFactory keyFactory = null;
   static {
     try {
       keyFactory = SecretKeyFactory.getInstance("DES");
     } catch (NoSuchAlgorithmException e) {
       
       e.printStackTrace();
     } 
   }
   
   public static void main(String[] args) throws Exception {
     long begin = (new Date()).getTime();
     String data = "123456";
     
     String key = "INTERFACESTANDARDENCRYPTKEY2014";
     System.err.println(encrypt(data, key));
     System.err.println(decrypt(encrypt(data, key), key));
     long end = (new Date()).getTime();
     System.out.println(end - begin);
     System.out.println((new BASE64Encoder()).encode("2mTbsNsR+r0=".getBytes()));
   }
   
   public static String encrypt(String data, String key) throws Exception {
     byte[] bt = encrypt(data.getBytes("GBK"), key.getBytes("GBK"));
 
     
     String strs = (new BASE64Encoder()).encode(bt);
     return strs;
   }
   
   public static String decrypt(String data, String key) throws IOException, Exception {
     if (data == null) {
       return null;
     }
     BASE64Decoder decoder = new BASE64Decoder();
     byte[] buf = decoder.decodeBuffer(data);
     byte[] bt = decrypt(buf, key.getBytes());
     return new String(bt, "GBK");
   }
   
   private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
     SecureRandom sr = new SecureRandom();
     
     DESKeySpec dks = new DESKeySpec(key);
 
 
 
     
     SecretKey securekey = keyFactory.generateSecret(dks);
     
     Cipher cipher = Cipher.getInstance("DES");
     
     cipher.init(1, securekey, sr);
     
     return cipher.doFinal(data);
   }
   
   private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
     SecureRandom sr = new SecureRandom();
     
     DESKeySpec dks = new DESKeySpec(key);
 
 
 
     
     SecretKey securekey = keyFactory.generateSecret(dks);
     
     Cipher cipher = Cipher.getInstance("DES");
     
     cipher.init(2, securekey, sr);
     
     return cipher.doFinal(data);
   }
 }


