 package com.zt.kjybd;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.util.MD5Util;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.Date;
 import org.springframework.core.io.FileSystemResource;
 import org.springframework.http.HttpEntity;
 import org.springframework.http.HttpHeaders;
 import org.springframework.util.LinkedMultiValueMap;
 import org.springframework.util.MultiValueMap;
 import org.springframework.web.client.RestTemplate;
 
 
 
 public class RnameForSTO
 {
   public static String putData(String data) throws Exception {
     URL url = new URL("http://pda.sto.cn:3000/face-realname/waybill/individual");
     String result = "";
     try {
       String appId = "516353b4f8ed4705abb1a971abd396d8";
       
       String secretKey = "9defa97d7a094ee59642104e7cfabafb";
       Date time = new Date();
       long stamp = time.getTime();
       String timestamp = String.valueOf(stamp);
       String signature = MD5Util.MD5S(String.valueOf(secretKey) + timestamp);
       
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
       connection.setRequestProperty("appId", appId);
       connection.setRequestProperty("timestamp", timestamp);
       connection.setRequestProperty("signature", signature);
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
 
 
       
       String content = data;
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
   
   public static String putQY(String data) throws Exception {
     URL url = new URL("http://116.228.73.38:18082/face-realname/waybill/institution");
     
     String result = "";
     try {
       String appId = "516353b4f8ed4705abb1a971abd396d8";
       
       String secretKey = "9defa97d7a094ee59642104e7cfabafb";
       Date time = new Date();
       long stamp = time.getTime();
       String timestamp = String.valueOf(stamp);
       String signature = MD5Util.MD5S(String.valueOf(secretKey) + timestamp);
       
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
       connection.setRequestProperty("appId", appId);
       connection.setRequestProperty("timestamp", timestamp);
       connection.setRequestProperty("signature", signature);
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
 
 
       
       String content = data;
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
   
   public static String putImage(String filePath) throws Exception {
     String result = "";
     String url = "http://116.228.73.38:18082/face-rocket/upload";
 
     
     RestTemplate rest = new RestTemplate();
     FileSystemResource resource = new FileSystemResource(new File(filePath));
     LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap();
     linkedMultiValueMap.add("file", resource);
     
     HttpHeaders headers = new HttpHeaders();
     headers.add("appId", "516353b4f8ed4705abb1a971abd396d8");
     headers.add("timestamp", "1503388269628");
     headers.add("signature", "972c3d2338ccf0cefc660d44499abb43");
     
     HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(linkedMultiValueMap, 
         (MultiValueMap)headers);
     result = (String)rest.postForObject(url, httpEntity, String.class, new Object[0]);
     
     return result;
   }
   
   public static void main(String[] args) throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("waybillNo", "4024141546701");
     jsonObject.put("orgCode", "123456787");
     jsonObject.put("socialCreditCode", "");
     jsonObject.put("taxRegNo", "");
     jsonObject.put("internalsType", "01");
     jsonObject.put("innerProdName", "信件");
     jsonObject.put("internalsAmount", Integer.valueOf(1));
     jsonObject.put("isCheck", Integer.valueOf(1));
     jsonObject.put("lng", "");
     jsonObject.put("lat", "");
     jsonObject.put("sendProvince", Integer.valueOf(410000));
     jsonObject.put("sendCity", Integer.valueOf(410300));
     jsonObject.put("sendArea", Integer.valueOf(410302));
     jsonObject.put("sendAddress", "河南省 洛阳市 老城区 河南大");
     jsonObject.put("receiverName", "李亮");
     jsonObject.put("receiverMobilephone", "18859767594");
     jsonObject.put("receiverProvince", Integer.valueOf(110000));
     jsonObject.put("receiverCity", Integer.valueOf(110000));
     jsonObject.put("receiverArea", Integer.valueOf(110101));
     jsonObject.put("receiverAddress", "北京 北京市 东城区 北京市朝阳区狗里");
     jsonObject.put("innerProdPhoto", "453720191459449ca9676c8e242ec036");
     jsonObject.put("sendOrgCode", Integer.valueOf(988888));
     jsonObject.put("staffCode", "9888884221");
     jsonObject.put("dataSource", "07");
     
     String data = jsonObject.toString();
     System.out.println("---------------------" + data);
     try {
       String respCode = putQY(data);
       System.out.println("STO_UPDATE返回值：" + respCode);
     } catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 }


