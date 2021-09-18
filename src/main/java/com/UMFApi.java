 package com;
 
 import java.io.ByteArrayInputStream;
 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import org.apache.commons.httpclient.HttpClient;
 import org.apache.commons.httpclient.HttpMethod;
 import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
 import org.apache.commons.httpclient.methods.PostMethod;
 import org.apache.commons.httpclient.methods.RequestEntity;
 
 
 public class UMFApi
 {
   public static void main(String[] args) {
     String params = "{\"client_id\": \"6bf3b12b9159f55e3863204ac06f19b7a076cfc9\", \"client_secret\": \"2dbfedf52da5036bde758189b1d27ebc1858655e\",\"grant_type\": \"client_credentials\"}";
     String requestUrl = "https://uatfx.soopay.net/cberest/v1/oauth/authorize";
     String authorization = "ea3b83b316d97bd78166475fe36a3f7219d79e8d04bfc784dec424fba0e9462f";
     try {
       sendPost(params, requestUrl, authorization);
     } catch (IOException e) {
       
       e.printStackTrace();
     } 
   }
   
   public static String sendPost(String params, String requestUrl, String authorization) throws IOException {
     byte[] requestBytes = params.getBytes("utf-8");
     HttpClient httpClient = new HttpClient();
     PostMethod postMethod = new PostMethod(requestUrl);
     
     postMethod.setRequestHeader("Authorization", "Basic " + authorization);
     
     postMethod.setRequestHeader("Content-Type", "application/json");
     InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, 
         requestBytes.length);
     InputStreamRequestEntity inputStreamRequestEntity = new InputStreamRequestEntity(inputStream, 
         requestBytes.length, "application/json; charset=utf-8");
     postMethod.setRequestEntity((RequestEntity)inputStreamRequestEntity);
     httpClient.executeMethod((HttpMethod)postMethod);
     InputStream soapResponseStream = postMethod.getResponseBodyAsStream();
     byte[] datas = null;
     try {
       datas = readInputStream(soapResponseStream);
     } catch (Exception e) {
       e.printStackTrace();
     } 
     String result = new String(datas, "UTF-8");
 
 
     
     return result;
   }
   
   public static byte[] readInputStream(InputStream inStream) throws Exception {
     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
     byte[] buffer = new byte[1024];
     int len = 0;
     while ((len = inStream.read(buffer)) != -1) {
       outStream.write(buffer, 0, len);
     }
     byte[] data = outStream.toByteArray();
     outStream.close();
     inStream.close();
     return data;
   }
 }


