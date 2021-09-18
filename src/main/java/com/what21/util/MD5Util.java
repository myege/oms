 package com.what21.util;
 
 import java.io.UnsupportedEncodingException;
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 import sun.misc.BASE64Encoder;
 
 public class MD5Util {
   public static final String MD5(String s) {
     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
     try {
       byte[] btInput = s.getBytes();
       
       MessageDigest mdInst = MessageDigest.getInstance("MD5");
       
       mdInst.update(btInput);
       
       byte[] md = mdInst.digest();
       
       int j = md.length;
       char[] str = new char[j * 2];
       int k = 0;
       for (int i = 0; i < j; i++) {
         byte byte0 = md[i];
         str[k++] = hexDigits[byte0 >>> 4 & 0xF];
         str[k++] = hexDigits[byte0 & 0xF];
       } 
       return new String(str);
     } catch (Exception e) {
       e.printStackTrace();
       return null;
     } 
   } public static final String MD5_forbm(String s, String charset) {
     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
     try {
       byte[] btInput = s.getBytes(charset);
       
       MessageDigest mdInst = MessageDigest.getInstance("MD5");
       
       mdInst.update(btInput);
       
       byte[] md = mdInst.digest();
       
       int j = md.length;
       char[] str = new char[j * 2];
       int k = 0;
       for (int i = 0; i < j; i++) {
         byte byte0 = md[i];
         str[k++] = hexDigits[byte0 >>> 4 & 0xF];
         str[k++] = hexDigits[byte0 & 0xF];
       } 
       return new String(str);
     } catch (Exception e) {
       e.printStackTrace();
       return null;
     } 
   }
   public static final String MD5S(String s) {
     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
     try {
       byte[] btInput = s.getBytes();
       
       MessageDigest mdInst = MessageDigest.getInstance("MD5");
       
       mdInst.update(btInput);
       
       byte[] md = mdInst.digest();
       
       int j = md.length;
       char[] str = new char[j * 2];
       int k = 0;
       for (int i = 0; i < j; i++) {
         byte byte0 = md[i];
         str[k++] = hexDigits[byte0 >>> 4 & 0xF];
         str[k++] = hexDigits[byte0 & 0xF];
       } 
       return new String(str);
     } catch (Exception e) {
       e.printStackTrace();
       return null;
     } 
   } public static final String MD5S_forbm(String s, String charset) {
     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
     try {
       byte[] btInput = s.getBytes(charset);
       
       MessageDigest mdInst = MessageDigest.getInstance("MD5");
       
       mdInst.update(btInput);
       
       byte[] md = mdInst.digest();
       
       int j = md.length;
       char[] str = new char[j * 2];
       int k = 0;
       for (int i = 0; i < j; i++) {
         byte byte0 = md[i];
         str[k++] = hexDigits[byte0 >>> 4 & 0xF];
         str[k++] = hexDigits[byte0 & 0xF];
       } 
       return new String(str);
     } catch (Exception e) {
       e.printStackTrace();
       return null;
     } 
   }
   public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
     MessageDigest md5 = MessageDigest.getInstance("MD5");
     BASE64Encoder base64en = new BASE64Encoder();
     
     String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
     return newstr;
   }
 
   
   public static String MD5Encode(String origin) {
     String resultString = null;
     try {
       resultString = origin;
       MessageDigest md = MessageDigest.getInstance("MD5");
       resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return resultString;
   }
 
 
 
   
   private static String byteArrayToHexString(byte[] digest) {
     return null;
   }
   
   public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
     String aa = "<txInfo><merchantId>109115711712009</merchantId><version>v5.2</version><payType>31</payType><signType>0</signType><charset>1</charset><orderNo>KX180122049103200</orderNo><orderDatetime>20180123103200</orderDatetime><customsInfo><CUSTOMS_TYPE>HG021</CUSTOMS_TYPE><ESHOP_ENT_CODE>3301560066</ESHOP_ENT_CODE><ESHOP_ENT_NAME>杭州潮购网络科技有限公司</ESHOP_ENT_NAME><BIZ_TYPE_CODE></BIZ_TYPE_CODE><APP_UID></APP_UID><APP_UNAME></APP_UNAME><TOTAL_FEE>55800</TOTAL_FEE><GOODS_FEE>55800</GOODS_FEE><TAX_FEE>0</TAX_FEE><FREIGHT_FEE>0</FREIGHT_FEE><OTHER_FEE>0</OTHER_FEE><IETYPE>E</IETYPE><ORIGINAL_ORDER_NO>KX180122049</ORIGINAL_ORDER_NO><PAY_TIME>20180123103200</PAY_TIME><CURRENCY>156</CURRENCY><PAYER_NAME>杨婷婷</PAYER_NAME><PAPER_TYPE>01</PAPER_TYPE><PAPER_NUMBER>440982199203241223</PAPER_NUMBER><PAPER_PHONE>13727840039</PAPER_PHONE><PAPER_EMAIL>aaa@123.com</PAPER_EMAIL><PAY_BANK_NAME></PAY_BANK_NAME><PAY_BANK_CODE></PAY_BANK_CODE><PAY_BANK_SERIALNO></PAY_BANK_SERIALNO><PAYER_COUNTRY_CODE></PAYER_COUNTRY_CODE><PAYER_ADDRESS></PAYER_ADDRESS><PAYER_SEX></PAYER_SEX><PAYER_BIRTHDAY></PAYER_BIRTHDAY><CHECK_ECP_CODE></CHECK_ECP_CODE><CHECK_ECP_NAME></CHECK_ECP_NAME><ORG_CODE></ORG_CODE><PAY_CARD_NO></PAY_CARD_NO><SHIPPER_NAME></SHIPPER_NAME><IS_CHECK><IS_CHECK><MEMO>备注</MEMO></customsInfo><remark>报文</remark></txInfo><key>1234567890</key>";
     System.out.println(MD5(aa));
     System.out.println(MD5Encode(aa));
   }
 }


