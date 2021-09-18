 package com.zt.aes256;
 
 import org.apache.commons.codec.binary.Base64;
 
 public class Base64Codec extends BaseCodec {
   public byte[] encrypt(byte[] data) {
     return Base64.encodeBase64(data);
   }
   
   public byte[] decrypt(byte[] value) {
     return Base64.decodeBase64(value);
   }
 }


