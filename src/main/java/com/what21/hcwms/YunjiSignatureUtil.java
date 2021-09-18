 package com.what21.hcwms;
 
 import java.security.MessageDigest;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 public class YunjiSignatureUtil
 {
   public static String signRequest(Map<String, String> params, String body, String secretKey) throws Exception {
     String[] keys = (String[])params.keySet().toArray((Object[])new String[0]);
     Arrays.sort((Object[])keys);
     
     String joinedParams = joinRequestParams(params, body, secretKey, keys);
     System.out.println("joinedParams=" + joinedParams);
     
     byte[] abstractMesaage = digest(joinedParams);
     
     String md5 = byte2Hex(abstractMesaage);
     return md5;
   }
   private static String byte2Hex(byte[] bytes) {
     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
     int j = bytes.length;
     char[] str = new char[j * 2];
     int k = 0; byte b; int i; byte[] arrayOfByte;
     for (i = (arrayOfByte = bytes).length, b = 0; b < i; ) { byte byte0 = arrayOfByte[b];
       str[k++] = hexDigits[byte0 >>> 4 & 0xF];
       str[k++] = hexDigits[byte0 & 0xF]; b++; }
     
     return new String(str);
   }
 
 
 
   
   private static byte[] digest(String message) throws Exception {
     MessageDigest md5Instance = MessageDigest.getInstance("MD5");
     md5Instance.update(message.getBytes("UTF-8"));
     return md5Instance.digest();
   }
   private static String joinRequestParams(Map<String, String> params, String body, String secretKey, String[] sortedKes) {
     StringBuilder sb = new StringBuilder(secretKey); byte b; int i; String[] arrayOfString;
     for (i = (arrayOfString = sortedKes).length, b = 0; b < i; ) { String key = arrayOfString[b];
       if (!"sign".equals(key)) {
 
         
         String value = params.get(key);
         if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value))
           sb.append(key).append(value); 
       } 
       b++; }
     
     sb.append(body);
     sb.append(secretKey);
     return sb.toString();
   }
 
   
   public static void main(String[] args) throws Exception {
     String method = "entryorder.create";
     String timestamp = "2015-04-26 00:00:07";
     String format = "xml";
     String app_key = "testerp_appkey";
     String v = "1.0";
     String sign_method = "md5";
     String customerId = "testCustomerId";
     String secret = "test";
     
     String body = "body";
     String sj = String.valueOf(secret) + "app_key" + app_key + "customerId" + customerId + "format" + format + "method" + method + "sign_method" + sign_method + "timestamp" + timestamp + "v" + v + body + secret;
 
     
     Map<String, String> params = new HashMap<>();
     params.put("app_key", "testerp_appkey");
     params.put("customerId", "testCustomerId");
     params.put("format", "xml");
     params.put("method", "yunji.esb.entryorder.create");
     params.put("sign_method", "md5");
     params.put("timestamp", "2015-04-26 00:00:07");
     params.put("v", "1.0");
 
     
     String sign = signRequest(params, body, secret);
     System.out.println("sign=" + sign);
     
     String joinedParams = "20201124bapp_key20201124acustomerId20201124cformatxmlmethoddeliveryorder.createsign_methodmd5timestamp2020-11-24 10:11:36v1.0body20201124b";
     byte[] abstractMesaage = digest(joinedParams);
     
     String md5 = byte2Hex(abstractMesaage);
     System.out.println("md5=" + md5);
   }
 }


