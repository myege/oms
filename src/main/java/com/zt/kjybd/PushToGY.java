 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.StringWriter;
 import java.io.UnsupportedEncodingException;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import java.security.GeneralSecurityException;
 import java.security.MessageDigest;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.Map;
 import org.apache.http.HttpEntity;
 import org.apache.http.client.methods.CloseableHttpResponse;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.client.methods.HttpUriRequest;
 import org.apache.http.entity.StringEntity;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClients;
 import org.apache.http.util.EntityUtils;
 import org.jdom.Document;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
import org.apache.commons.codec.binary.Base64;
 
 
 
 public class PushToGY
 {
   public static String ddsend(String data) throws Exception {
     URL url = new URL("http://139.129.116.198/cbes/api/report");
 
     
     String result = "";
     try {
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/json");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       out.write(data.getBytes(charset));
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
       
       System.out.println("result=" + result);
     } catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   public static String post(String strURL, String params) {
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
   } public static String sendPost(String url, String po) {
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
   
   public static String sendPostxml(String url, String po) {
     String result = "";
     System.out.println("po=" + po);
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
       connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
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
   
   public static String sha1(String data) throws IOException {
     return byte2hex(getSHA1Digest(data));
   }
   
   private static byte[] getSHA1Digest(String data) throws IOException {
     byte[] bytes = null;
     try {
       MessageDigest md = MessageDigest.getInstance("SHA-1");
       bytes = md.digest(data.getBytes("UTF-8"));
     } catch (GeneralSecurityException gse) {
       throw new IOException(gse);
     } 
     return bytes;
   }
   
   private static String byte2hex(byte[] bytes) {
     StringBuilder sign = new StringBuilder();
     for (int i = 0; i < bytes.length; i++) {
       String hex = Integer.toHexString(bytes[i] & 0xFF);
       if (hex.length() == 1) {
         sign.append("0");
       }
       sign.append(hex.toUpperCase());
     } 
     return sign.toString();
   }
   
   public static void main(String[] args) {
     String url2 = "E:\\xml\\622.xml";
     
     try {
       String str = "data=" + URLEncoder.encode(Base64.encodeBase64String(XmlToString(url2).getBytes("UTF-8")));
       
       sendPost("http://111.85.254.10:89/POSTAPI.ASPX", str);
     } catch (UnsupportedEncodingException e) {
       
       e.printStackTrace();
     } 
   }
   
   public static String putDataForOut(String data) throws Exception {
     URL url = new URL("http://storder.sto.cn:2227/order/partner/message_receiver");
     
     String result = "";
     try {
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       String signer = "01D5727887844620A2421990B47510D4";
       
       Date date = new Date();
       long stamp = date.getTime();
       String timestamp = String.valueOf(stamp);
       
       String partnerCode = "PORTZJZTXS";
       
       String logisticsInfo = data;
       String a = String.valueOf(signer) + "logisticsInfo" + logisticsInfo + "partnerCode" + partnerCode + "timestamp" + timestamp + signer;
       signer = sha1(a);
       String content = "partnerCode=" + partnerCode + "&timestamp=" + timestamp + "&logisticsInfo=" + logisticsInfo + "&signer=" + signer;
 
       
       System.out.println("content------>" + content);
       out.write(content.getBytes(charset));
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
     
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   
   public static String putDataOUt(String jsonString) throws Exception {
     URL url = new URL("http://storder.sto.cn:2227/order/partner/message_receiver");
     String result = "";
     try {
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       String signer = "5D9DAB5D3DE64BEDBA4715909E0BCA65";
       Date date = new Date();
       long stamp = date.getTime();
       String timestamp = String.valueOf(stamp);
       
       String partnerCode = "EXITZJZT";
       String logisticsInfo = jsonString;
       String a = String.valueOf(signer) + "logisticsInfo" + logisticsInfo + "partnerCode" + partnerCode + "timestamp" + timestamp + signer;
       signer = sha1(a);
       String content = "partnerCode=" + partnerCode + "&timestamp=" + timestamp + "&logisticsInfo=" + logisticsInfo + "&signer=" + signer;
 
       
       out.write(content.getBytes(charset));
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
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   public static String XmlToString(String url) {
     Document document = null;
     document = load(url);
     
     Format format = Format.getPrettyFormat();
     format.setEncoding("UTF-8");
     
     StringWriter out = null;
     String sReturn = "";
     XMLOutputter outputter = new XMLOutputter();
     out = new StringWriter();
     try {
       outputter.output(document, out);
     } catch (IOException e) {
       e.printStackTrace();
     } 
     sReturn = out.toString();
     return sReturn;
   }
   public static Document load(String url) {
     Document document = null;
     
     try {
       SAXBuilder reader = new SAXBuilder();
       document = reader.build(new File(url));
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return document;
   }
   
   public static String httpPostxml2(String url, String xml, Map<String, String> headers, String encode) {
     if (encode == null) {
       encode = "utf-8";
     }
     
     CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
     HttpPost httpost = new HttpPost(url);
     httpost.setHeader("Content-Type", "text/xml;charset=UTF-8");
     if (headers != null && headers.size() > 0) {
       Iterator<Map.Entry<String, String>> var6 = headers.entrySet().iterator();
       
       while (var6.hasNext()) {
         Map.Entry<String, String> entry = var6.next();
         httpost.setHeader(entry.getKey(), entry.getValue());
       } 
     } 
     
     StringEntity stringEntity = new StringEntity(xml, encode);
     httpost.setEntity((HttpEntity)stringEntity);
     String content = null;
     CloseableHttpResponse httpResponse = null;
     
     try {
       httpResponse = closeableHttpClient.execute((HttpUriRequest)httpost);
       HttpEntity entity = httpResponse.getEntity();
       content = EntityUtils.toString(entity, encode);
     } catch (Exception var20) {
       var20.printStackTrace();
     } finally {
       try {
         httpResponse.close();
       } catch (IOException var18) {
         var18.printStackTrace();
       } 
     } 
 
     
     try {
       closeableHttpClient.close();
     } catch (IOException var19) {
       var19.printStackTrace();
     } 
     
     return content;
   }
 }


