 package com.zt.aes256;
 
 import java.io.File;
 import java.io.FileInputStream;
 
 public class Aes256Test {
   public static void main(String[] args) {
     String str = null;
     
     str = "<?xml version=\"1.0\" encoding=\"utf-8\"?><orderRequest><orderId>1234567890</orderId></orderRequest>";
     String aesKey = "SvEpI11JO2QpqEPTCxA7JTspRa56OBDq";
     String hmacsha512Key = "VJ4fpM0q30/LwkbykbYa7HnBYTv9qB+t";
     String enc = AES256CipherExternalFactory.AES256Encode(str, aesKey);
     
     "Gu2xNHdYisiEEyqIv7Iv6olWaKjmsSmBH96h82c0ttxMl8d0t+nEcWjJjjIRsIv8uwBcduK5bBM6RKRWfnY+u/XLx64Z15zfrqa1DjpZnWf8cuLsFtMJVoMyU+Xz/JSvXnn0LjneVGiOCV0vE3CDog==".equals(enc);
 
     
     String dnc = AES256CipherExternalFactory.AES256Decode(enc, aesKey);
 
     
     String Hdata = HmacSha512CoderFactory.getHmacSha512Coder(hmacsha512Key, enc);
     
     "0189b22fabcf7f72302904ca8b9806e36750ad725a94db9856fdbb9673cbcc2522034cd464d658de47c0c6e859cf6f7c59fd2ad57942366e7759edc02441f914".equals(Hdata);
   }
 
 
   
   public static byte[] getFileContent(String filePath) {
     File f = new File(filePath);
     byte[] c = null;
     try {
       FileInputStream fis = new FileInputStream(f);
       long length = f.length();
       c = new byte[(int)length];
       fis.read(c, 0, c.length);
 
       
       fis.close();
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return c;
   }
 }


