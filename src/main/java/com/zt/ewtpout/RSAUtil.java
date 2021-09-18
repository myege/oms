 package com.zt.ewtpout;
 
 import java.io.IOException;
 import java.security.KeyFactory;
 import java.security.KeyPair;
 import java.security.KeyPairGenerator;
 import java.security.PrivateKey;
 import java.security.PublicKey;
 import java.security.spec.PKCS8EncodedKeySpec;
 import java.security.spec.X509EncodedKeySpec;
 import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
 
 public class RSAUtil
 {
   public static KeyPair getKeyPair() throws Exception {
     KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
     keyPairGenerator.initialize(2048);
     KeyPair keyPair = keyPairGenerator.generateKeyPair();
     return keyPair;
   }
 
   
   public static String getPublicKey(KeyPair keyPair) {
     PublicKey publicKey = keyPair.getPublic();
     byte[] bytes = publicKey.getEncoded();
     return byte2Base64(bytes);
   }
 
   
   public static String getPrivateKey(KeyPair keyPair) {
     PrivateKey privateKey = keyPair.getPrivate();
     byte[] bytes = privateKey.getEncoded();
     return byte2Base64(bytes);
   }
 
   
   public static PublicKey string2PublicKey(String pubStr) throws Exception {
     byte[] keyBytes = base642Byte(pubStr);
     X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     PublicKey publicKey = keyFactory.generatePublic(keySpec);
     return publicKey;
   }
 
   
   public static PrivateKey string2PrivateKey(String priStr) throws Exception {
     byte[] keyBytes = base642Byte(priStr);
     PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
     return privateKey;
   }
 
   
   public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
     Cipher cipher = Cipher.getInstance("RSA");
     cipher.init(1, publicKey);
     byte[] bytes = cipher.doFinal(content);
     return bytes;
   }
 
   
   public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
     Cipher cipher = Cipher.getInstance("RSA");
     cipher.init(2, privateKey);
     byte[] bytes = cipher.doFinal(content);
     return bytes;
   }
 
   
   public static String byte2Base64(byte[] bytes) {
     return Base64.encodeBase64String(bytes);
   }
 
   
   public static byte[] base642Byte(String base64Key) throws IOException {
     return Base64.decodeBase64(base64Key);
   }
 }


