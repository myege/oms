 package com.zt.aes256;
 
 import java.nio.charset.Charset;
 import javax.crypto.Mac;
 import javax.crypto.spec.SecretKeySpec;
 import org.apache.commons.codec.binary.Hex;
 
 public class HmacSha512Coder {
   private static final String algorithm = "HmacSHA512";
   private byte[] keySeeds;
   private final Charset charset = Charset.forName("UTF-8");
   
   public void setKeySeeds(String keeySeedsString) {
     this.keySeeds = (new Base64Codec()).encrypt(keeySeedsString);
   }
   
   public String generateHMAC(String datas) throws Exception {
     SecretKeySpec secretKey = new SecretKeySpec(this.keySeeds, "HmacSHA512");
     Mac mac = Mac.getInstance("HmacSHA512");
     mac.init(secretKey);
     byte[] macData = mac.doFinal(datas.getBytes(this.charset));
     byte[] hashed = (new Hex()).encode(macData);
     return new String(hashed, this.charset);
   }
   
   public static void main(String[] args) throws Exception {
     HmacSha512Coder sha = new HmacSha512Coder();
     sha.setKeySeeds("0123456789ertyuiolkjhgfcvbnm");
     String vStr = "SF-EXPRESS:BSP2015-06-03 14:42:36";
   }
 }


