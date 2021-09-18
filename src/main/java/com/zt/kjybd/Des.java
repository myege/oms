 package com.zt.kjybd;
 
 import java.net.InetAddress;
 import java.net.UnknownHostException;
 import java.security.Key;
 import java.security.SecureRandom;
 import javax.crypto.Cipher;
 import javax.crypto.KeyGenerator;
 import org.apache.commons.codec.binary.Base64;
 
 
 public class Des
 {
   private Key key;
   private String ENCRYPT_ALGORITHM = "DES";
   
   public Des() {
     getKey(getDefaultKey());
   }
   
   public Des(String keyStr) {
     getKey(keyStr);
   }
   
   private String getDefaultKey() {
     InetAddress netAddress = null;
     try {
       netAddress = InetAddress.getLocalHost();
     } catch (UnknownHostException e) {
       e.printStackTrace();
     } 
     return netAddress.getHostName();
   }
   
   private void getKey(String strKey) {
     try {
       KeyGenerator generator = KeyGenerator.getInstance(this.ENCRYPT_ALGORITHM);
       SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
       secureRandom.setSeed(strKey.getBytes());
       generator.init(secureRandom);
       this.key = generator.generateKey();
       System.out.println("key");
       generator = null;
     } catch (Exception exception) {}
   }
 
 
   
   public String getEncString(String strMing) {
     byte[] byteMi = null;
     byte[] byteMing = null;
     String strMi = "";
     Base64 base64en = new Base64();
     try {
       byteMing = strMing.getBytes("UTF-8");
       byteMi = getEncCode(byteMing);
       strMi = base64en.encodeAsString(byteMi);
     } catch (Exception exception) {
     
     } finally {
       base64en = null;
       byteMing = null;
       byteMi = null;
     } 
     return strMi;
   }
   
   public String getDesString(String strMi) {
     Base64 base64De = new Base64();
     byte[] byteMing = null;
     byte[] byteMi = null;
     String strMing = "";
     try {
       byteMi = base64De.decode(strMi);
       byteMing = getDesCode(byteMi);
       strMing = new String(byteMing, "UTF-8");
     } catch (Exception exception) {
     
     } finally {
       base64De = null;
       byteMing = null;
       byteMi = null;
     } 
     return strMing;
   }
   private byte[] getEncCode(byte[] byteS) {
     Cipher cipher;
     byte[] byteFina = null;
     
     try {
       Cipher cipher1 = Cipher.getInstance(this.ENCRYPT_ALGORITHM);
       cipher1.init(1, this.key);
       byteFina = cipher1.doFinal(byteS);
     } catch (Exception exception) {
     
     } finally {
       Cipher cipher1 = null;
     } 
     return byteFina;
   }
   
   private byte[] getDesCode(byte[] byteD) {
     Cipher cipher;
     byte[] byteFina = null;
     try {
       Cipher cipher1 = Cipher.getInstance(this.ENCRYPT_ALGORITHM);
       cipher1.init(2, this.key);
       byteFina = cipher1.doFinal(byteD);
     } catch (Exception exception) {
     
     } finally {
       Cipher cipher1 = null;
     } 
     return byteFina;
   }
 }


