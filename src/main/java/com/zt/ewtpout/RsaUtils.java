 package com.zt.ewtpout;
 
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.security.KeyFactory;
 import java.security.PrivateKey;
 import java.security.PublicKey;
 import java.security.Signature;
 import java.security.spec.PKCS8EncodedKeySpec;
 import java.security.spec.X509EncodedKeySpec;
 import javax.crypto.Cipher;
 import org.apache.commons.codec.binary.Base64;
 public class RsaUtils
 {
   public static final String KEY_ALGORITHM = "RSA";
   public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
   private static final int MAX_DECRYPT_BLOCK = 117;
   
   public static byte[] getSignByPriKey(byte[] data, byte[] privateKey) throws Exception {
     PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
 
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
 
     
     PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
 
     
     Signature signature = Signature.getInstance("SHA1withRSA");
 
     
     signature.initSign(priKey);
 
     
     signature.update(data);
 
     
     return signature.sign();
   }
   
   public static Boolean verifyByPubKey(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
     X509EncodedKeySpec pkcs8KeySpec = new X509EncodedKeySpec(publicKey);
 
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
 
     
     PublicKey pubKey = keyFactory.generatePublic(pkcs8KeySpec);
 
     
     Signature signature = Signature.getInstance("SHA1withRSA");
 
     
     signature.initVerify(pubKey);
 
     
     signature.update(data);
 
     
     return Boolean.valueOf(signature.verify(sign));
   }
   
   public static byte[] encryptByPubKey(byte[] data, byte[] publicKey) throws Exception {
     X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     
     PublicKey pubKey = keyFactory.generatePublic(keySpec);
     
     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
     cipher.init(1, pubKey);
     return getSplitBytes(data, cipher);
   }
   
   public static byte[] decryptByPubKey(byte[] data, byte[] publicKey) throws Exception {
     X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     
     PublicKey pubKey = keyFactory.generatePublic(keySpec);
     
     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
     cipher.init(2, pubKey);
     return getSplitBytes(data, cipher);
   }
   
   public static byte[] encryptByPriKey(byte[] data, byte[] privateKey) throws Exception {
     PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     
     PrivateKey privateKeys = keyFactory.generatePrivate(keySpec);
     
     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
     cipher.init(1, privateKeys);
     
     return getSplitBytes(data, cipher);
   }
   
   public static byte[] decryptByPriKey(byte[] data, byte[] privateKey) throws Exception {
     PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
     
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     
     PrivateKey pubKey = keyFactory.generatePrivate(keySpec);
     
     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
     cipher.init(2, pubKey);
     
     return getSplitBytes(data, cipher);
   }
   
   private static byte[] getSplitBytes(byte[] data, Cipher cipher) throws IOException {
     byte[] result = null;
     ByteArrayOutputStream out = null;
     try {
       int inputLen = data.length;
       out = new ByteArrayOutputStream();
       int offSet = 0;
       
       int i = 0;
       while (inputLen - offSet > 0) {
         byte[] cache; if (inputLen - offSet > 117) {
           cache = cipher.doFinal(data, offSet, 117);
         } else {
           cache = cipher.doFinal(data, offSet, inputLen - offSet);
         } 
         out.write(cache, 0, cache.length);
         i++;
         offSet = i * 117;
       } 
       result = out.toByteArray();
     } catch (Exception exception) {
     
     } finally {
       if (out != null) {
         out.close();
       }
     } 
     return result;
   }
 
   
   public static void main(String[] args) throws Exception {
     String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJRpJpJ9SaLCU4jpE3YcH39nWN7pUqTP/mLhYuamri2bJ8EKIw0JMtZEDB9qxAGopVviOZfRQwvBOGp+K0xobu0CAwEAAQ==";
     String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlGkmkn1JosJTiOkTdhwff2dY3ulSpM/+YuFi5qauLZsnwQojDQky1kQMH2rEAailW+I5l9FDC8E4an4rTGhu7QIDAQABAkASgOWxP/OtWkMl/8FQaYM31D/NUxPdvpz94RH+petzfgHMlIQEbtHc48mobRlJf0cNSLJuHtAhnJxuHBQfO5hBAiEA9n9nsNh8UvjKpBWuI07wiem4lmMWHawkvMX79XSao1ECIQCaIcMCy9l3w3sky+X+I0Vzz6kBE8uWJjjh8eDqI6HS3QIhAOyVSbtFfMXR/gRQvqsq19k2VrEYdyCRmU0JpRNoEAeRAiBJgaZ2M++/rSuSbed+3yVlSPjpuZ4TOqFQKEHCiEHJxQIgY97FgtJUnFXMRruOsCgOisLvVyYKeoTU9VTQrlCk+fA=";
     
     byte[] inputContent = "1".getBytes("UTF-8");
     byte[] publicKeyCode = Base64.decodeBase64(publicKey.getBytes("UTF-8"));
     byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("UTF-8"));
     
     String signedXml = new String(Base64.encodeBase64(encryptByPubKey(inputContent, publicKeyCode)), "UTF-8");
     
     String sign = new String(Base64.encodeBase64(getSignByPriKey(inputContent, privateKeyCode)));
     System.out.println(signedXml);
     System.out.println(sign);
     System.out.println(verifyByPubKey(inputContent, publicKeyCode, Base64.decodeBase64(sign)));
     System.out.println(new String(decryptByPriKey(Base64.decodeBase64(signedXml.getBytes("UTF-8")), privateKeyCode)));
 
     
     String signedXml2 = new String(Base64.encodeBase64(encryptByPriKey(inputContent, privateKeyCode)), "UTF-8");
     System.out.println(signedXml2);
     System.out.println(new String(decryptByPubKey(Base64.decodeBase64(signedXml2.getBytes("UTF-8")), publicKeyCode)));
   }
 }


