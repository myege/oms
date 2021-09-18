 package com.what21.test;
 
 import com.sf.module.bspcommon.util.bspcodec.impl.AES256CipherExternalFactory;
 
 public class TestSF
 {
   public static void main(String[] args) {
     String str = "123";
     
     String enc = AES256CipherExternalFactory.AES256Encode(str, "8Y+e9B8vjPGRwe9YdkUgHxyz4HXC8o9w");
     System.out.println(enc);
   }
 }


