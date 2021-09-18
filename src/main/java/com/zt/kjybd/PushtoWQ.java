 package com.zt.kjybd;
 
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import org.apache.commons.codec.binary.Base64;
 import org.apache.commons.codec.digest.DigestUtils;
 
 
 public class PushtoWQ
 {
   public static String sendPost2(String url, String po) {
     String result = "";
 
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       out.write(po.getBytes(charset));
 
 
       
       out.flush();
       out.close();
       
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       result = result.replaceAll("(null)\\s*,", "\"\",");
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   public static String sendPostewtp(String url, String po) {
     String result = "";
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       out.write(po.getBytes(charset));
       out.flush();
       out.close();
       
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       result = result.replaceAll("(null)\\s*,", "\"\",");
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   
   public static String sendPostewtpkc(String url, String po) {
     String result = "";
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       out.write(po.getBytes(charset));
       out.flush();
       out.close();
       
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       result = result.replaceAll("(null)\\s*,", "\"\",");
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   public static String getSignature(String content, String secretKey) {
     String text = String.valueOf(content) + secretKey;
     byte[] md5 = DigestUtils.md5(text);
     return Base64.encodeBase64String(md5);
   }
   public static String sendPost_ht(String url, String po) {
     String result = "";
 
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       out.write(po.getBytes(charset));
 
 
       
       out.flush();
       out.close();
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       result = result.replaceAll("(null)\\s*,", "\"\",");
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   public static String sendPost_zwyjmj(String url, String po) {
     String result = "";
 
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       out.write(po.getBytes(charset));
 
 
       
       out.flush();
       out.close();
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       result = result.replaceAll("(null)\\s*,", "\"\",");
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   public static String sendPostYTO_bg(String xmlStr) {
     String clientId = "LECANG";
     String partnered = clientId;
     String url = "http://lmtest.yto.net.cn/globalunion/outcall/blcWayBill";
     
     String desKey = "INTERFACESTANDARDENCRYPTKEY2014";
     
     System.out.println("加密前报文:" + xmlStr);
     System.out.println("加密key:" + desKey);
     
     String logistics_interface = SendContent.encryptSendData(desKey, xmlStr);
     System.out.println("logistics_interface:" + logistics_interface);
     String data_digest = SendContent.signuatureSendData(partnered, logistics_interface);
     System.out.println("data_digest:" + data_digest);
     
     String queryString = "";
     try {
       queryString = "logistics_interface=" + URLEncoder.encode(logistics_interface, "UTF-8") + 
         "&data_digest=" + URLEncoder.encode(data_digest, "UTF-8") + 
         "&clientID=" + URLEncoder.encode(clientId, "UTF-8");
     } catch (Exception e) {
       e.printStackTrace();
     } 
     System.out.println("发送报文" + queryString);
     String result = SendContent.sendAndGetStr(url, queryString);
     return result;
   }
   public static String sendPostsf(String url, String po) {
     String result = "";
 
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       out.write(po.getBytes(charset));
 
 
       
       out.flush();
       out.close();
       
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       result = result.replaceAll("(null)\\s*,", "\"\",");
 
     
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 
   
   public static void main(String[] args) {}
   
   public static String postjson(String strURL, String params) {
     System.out.println(strURL);
     System.out.println(params);
     BufferedReader reader = null;
     try {
       URL url = new URL(strURL);
       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setUseCaches(false);
       connection.setInstanceFollowRedirects(true);
       connection.setRequestMethod("POST");
       
       connection.setRequestProperty("Content-Type", "application/json");
       connection.connect();
       
       OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
       out.append(params);
       out.flush();
       out.close();
       
       reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
       
       String res = ""; String line;
       while ((line = reader.readLine()) != null) {
         res = String.valueOf(res) + line;
       }
       reader.close();
       System.out.println("res=" + res);
       return res;
     } catch (IOException e) {
       
       e.printStackTrace();
       
       return "error";
     } 
   } public static String sendPostYTO(String url, String logistics_interface, String partnerId, String clientId) {
     String result = "";
 
     
     try {
       String data_digest = MD5Util.EncoderByMd5(String.valueOf(logistics_interface) + partnerId);
 
 
       
       String charset = "UTF-8";
       String str = "logistics_interface=" + URLEncoder.encode(logistics_interface, charset) + "&data_digest=" + URLEncoder.encode(data_digest, charset) + "&type=offline&clientId=" + clientId;
       
       URL urla = null;
       
       HttpURLConnection connection = null;
       
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       System.out.println("------->" + str);
       out.write(str.getBytes(charset));
 
 
       
       out.flush();
       out.close();
       
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       result = result.replaceAll("(null)\\s*,", "\"\",");
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 }


