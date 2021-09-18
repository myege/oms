 package com.what21.util;
 
 import java.security.Key;
 import java.security.KeyFactory;
 import java.security.KeyPair;
 import java.security.KeyPairGenerator;
 import java.security.PrivateKey;
 import java.security.PublicKey;
 import java.security.Signature;
 import java.security.interfaces.RSAPrivateKey;
 import java.security.interfaces.RSAPublicKey;
 import java.security.spec.PKCS8EncodedKeySpec;
 import java.security.spec.X509EncodedKeySpec;
 import java.util.HashMap;
 import java.util.Map;
 public class RSAUtil
 {
   public static final String KEY_ALGORITHM = "RSA";
   public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
   private static final String PUBLIC_KEY = "RSAPublicKey";
   private static final String PRIVATE_KEY = "RSAPrivateKey";
   private static final int KEY_SIZE = 512;
   
   public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
     PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
 
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
 
     
     PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
 
     
     Signature signature = Signature.getInstance("SHA1withRSA");
 
     
     signature.initSign(priKey);
 
     
     signature.update(data);
 
     
     return signature.sign();
   }
   
   public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
     X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
 
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
 
     
     PublicKey pubKey = keyFactory.generatePublic(keySpec);
 
     
     Signature signature = Signature.getInstance("SHA1withRSA");
 
     
     signature.initVerify(pubKey);
 
     
     signature.update(data);
 
     
     return signature.verify(sign);
   }
   
   public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {
     Key key = (Key)keyMap.get("RSAPrivateKey");
     
     return key.getEncoded();
   }
   
   public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
     Key key = (Key)keyMap.get("RSAPublicKey");
     
     return key.getEncoded();
   }
   
   public static Map<String, Object> initKey() throws Exception {
     KeyPairGenerator keyPairGen = 
       KeyPairGenerator.getInstance("RSA");
 
     
     keyPairGen.initialize(512);
 
     
     KeyPair keyPair = keyPairGen.generateKeyPair();
 
     
     RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
 
     
     RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
 
     
     Map<String, Object> keyMap = new HashMap<>(2);
     
     keyMap.put("RSAPublicKey", publicKey);
     keyMap.put("RSAPrivateKey", privateKey);
     
     return keyMap;
   }
 }


