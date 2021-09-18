 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 
 public class Stovip
 {
   public static String sendPost(String url, String po) {
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
 
     
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 
 
   
   public static void main(String[] args) {
     String url = "http://vip.sto.cn/PreviewInterfaceAction.action";
     
     String data = "code=vip0009&data_digest=053c044e0389874dcf6ff44f7c5e6673&cuspwd=!1qaz@2wsx&cusname=曌通供应链&cusite=杭州仓储部&len=1";
     sendPost(url, data);
   }
 }


