 package com;
 
 import com.alibaba.fastjson.JSONObject;
 import com.youzan.cloud.open.sdk.api.API;
 import com.youzan.cloud.open.sdk.api.ApiParams;
 import com.youzan.cloud.open.sdk.common.exception.SDKException;
 import com.youzan.cloud.open.sdk.core.client.auth.Auth;
 import com.youzan.cloud.open.sdk.core.client.auth.Token;
 import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
 import com.youzan.cloud.open.sdk.gen.v4_0_0.api.YouzanTradesSoldGet;
 import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetParams;
 import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetResult;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 public class YouzanUtil
 {
   public static void main(String[] args) throws Exception {
     youzanRequest("1cedad71aa951388de3f07e72c0ad591");
   }
   
   public static void youzanRequest(String token) throws SDKException {
     DefaultYZClient defaultYZClient = new DefaultYZClient();
     
     Token tokent = new Token(token);
     
     YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
     
     YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
     youzanTradesSoldGet.setAPIParams((ApiParams)youzanTradesSoldGetParams);
     
     YouzanTradesSoldGetResult result = (YouzanTradesSoldGetResult)defaultYZClient.invoke((API)youzanTradesSoldGet, (Auth)tokent, YouzanTradesSoldGetResult.class);
     
     System.out.println("-------------------" + result);
   }
 
 
   
   public static String getToken() throws Exception {
     URL url = new URL("https://open.youzanyun.com/auth/token");
     
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
       
       JSONObject json = new JSONObject();
       json.put("client_id", "65f30c6b6c49b43d19");
       json.put("client_secret", "35e1eac95f4bfc7a1867c2fcfcbc522e");
       json.put("authorize_type", "authorization_code");
       
       json.put("code", "192da62daf8e157c858b94f7c9c246b9");
       String content = json.toJSONString();
       System.out.println(content);
       out.write(content.getBytes(charset));
       out.flush();
       out.close();
       BufferedReader reader = new BufferedReader(new InputStreamReader(
             connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       
       System.out.println("result=" + result);
       JSONObject resultJson = JSONObject.parseObject(result);
       JSONObject dataJson = resultJson.getJSONObject("data");
       result = dataJson.getString("access_token");
     
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   
   public static String post(String url1, String json) throws Exception {
     URL url = new URL(url1);
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
 
       
       String content = json;
       System.out.println(content);
       out.write(content.getBytes(charset));
       out.flush();
       out.close();
       BufferedReader reader = new BufferedReader(new InputStreamReader(
             connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 }


