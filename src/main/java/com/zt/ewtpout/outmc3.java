 package com.zt.ewtpout;
 
 import com.alibaba.fastjson.JSONObject;
 import io.highstore.platform.utils.HttpClientUtils;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.security.KeyManagementException;
 import java.security.NoSuchAlgorithmException;
 import java.security.cert.CertificateException;
 import java.security.cert.X509Certificate;
 import javax.net.ssl.HostnameVerifier;
 import javax.net.ssl.HttpsURLConnection;
 import javax.net.ssl.SSLContext;
 import javax.net.ssl.SSLSession;
 import javax.net.ssl.SSLSessionContext;
 import javax.net.ssl.TrustManager;
 import javax.net.ssl.X509TrustManager;
 import org.apache.commons.codec.binary.Base64;
 import org.apache.http.HttpEntity;
 import org.apache.http.client.entity.EntityBuilder;
 import org.apache.http.client.methods.CloseableHttpResponse;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.client.methods.HttpUriRequest;
 import org.apache.http.entity.ContentType;
 import org.apache.http.entity.StringEntity;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClients;
 import org.apache.http.util.EntityUtils;
 import org.dom4j.Document;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
 public class outmc3
 {
   public static void main(String[] args) throws Exception {
     String path = "E:\\sjz";
     File file = new File(path);
     File[] tempList = file.listFiles();
     for (int i = 0; i < tempList.length; i++) {
       if (tempList[i].isFile()) {
         
         System.out.println("文     件：" + tempList[i]);
         System.out.println("数量：" + i);
         String contentData = XmlToString(tempList[i].toString());
         
         SAXReader reader = new SAXReader();
         
         Document document = reader.read(new File(tempList[i].toString()));
         Element rootElement = document.getRootElement();
         String messageType = rootElement.getName();
         System.out.println("messageType：" + messageType);
 
         
         String guid = rootElement.attributeValue("guid");
         
         System.out.println("guid：" + guid);
         
         JSONObject jsons = new JSONObject();
         jsons.put("messageId", guid);
         jsons.put("entCode", "330166K00K");
         jsons.put("dxpId", "DXPENT0000028271");
         jsons.put("dxpMsg", "0");
         jsons.put("messageType", messageType);
         
         String mmm = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrGoPKmUNZH2Txeyil7KIwRlDg8wZXuHhZr4KpPsm0ZYQpIhy7M4kING+XBlT4RHbURG5VuJR7H2VsiL4Cp60wy1C1OVk1wPBm7zADfvyB4u3hmsYzWwJElkMufpdcreZ3Ie8SpMX3JUAzHvVOJ5WvnMp5+WSCfImOXjFQkLu+7wIDAQAB";
 
         
         String signXml = XmlToString(tempList[i].toString());
         
         byte[] publicKeyCode = Base64.decodeBase64(mmm.getBytes("utf-8"));
         byte[] inputContent = signXml.getBytes("utf-8");
         
         String signedXml = new String(Base64.encodeBase64(RsaUtils.encryptByPubKey(inputContent, publicKeyCode)), "utf-8");
 
         
         jsons.put("content", signedXml);
         String parm = jsons.toJSONString();
         
         String url = "https://hangzhou.ewtp.com/message/report/uniform";
         String resout3 = sendPost(url, parm);
         System.out.println("resout3：" + resout3);
       } 
     } 
   }
   
   public static String sendPost3(String url, String data) {
     HttpEntity entity = EntityBuilder.create()
       .setContentType(ContentType.APPLICATION_JSON)
       .setContentEncoding("utf-8")
       .setText(data)
       .build();
     String sendResult = HttpClientUtils.post(url, entity);
 
 
 
     
     return sendResult;
   }
   
   public static String sendPost(String url, String param) {
     PrintWriter out = null;
     BufferedReader in = null;
     String result = "";
     try {
       skipSslValidation();
       URL realUrl = new URL(url);
       
       URLConnection conn = realUrl.openConnection();
       conn.setRequestProperty("accept", "*/*");
       conn.setRequestProperty("connection", "Keep-Alive");
       conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
       conn.setRequestProperty("user-agent", 
           "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
       
       conn.setDoOutput(true);
       conn.setDoInput(true);
       
       out = new PrintWriter(conn.getOutputStream());
       
       out.print(param);
       
       out.flush();
       
       in = new BufferedReader(
           new InputStreamReader(conn.getInputStream()));
       String line;
       while ((line = in.readLine()) != null) {
         result = String.valueOf(result) + line;
       }
     } catch (Exception e) {
       System.out.println("发送 POST 请求出现异常！" + e);
       e.printStackTrace();
     } finally {
 
       
       try {
         if (out != null) {
           out.close();
         }
         if (in != null) {
           in.close();
         }
       }
       catch (IOException ex) {
         ex.printStackTrace();
       } 
     } 
     return result;
   }
   
   public static String sendPost2(String url, String data) {
     String response = null;
     
     try {
       skipSslValidation();
       CloseableHttpClient httpclient = null;
       CloseableHttpResponse httpresponse = null;
       try {
         httpclient = HttpClients.createDefault();
         HttpPost httppost = new HttpPost(url);
         StringEntity stringentity = new StringEntity(data, 
             ContentType.create("application/json", "UTF-8"));
         httppost.setEntity((HttpEntity)stringentity);
         httpresponse = httpclient.execute((HttpUriRequest)httppost);
         response = 
           EntityUtils.toString(httpresponse.getEntity());
       } finally {
         
         if (httpclient != null) {
           httpclient.close();
         }
         if (httpresponse != null) {
           httpresponse.close();
         }
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return response;
   }
 
 
   
   static class TrustAllTrustManager
     implements TrustManager, X509TrustManager
   {
     public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
 
 
     
     public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
 
     
     public X509Certificate[] getAcceptedIssuers() {
       return null;
     }
   }
   
   public static void skipSslValidation() throws NoSuchAlgorithmException, KeyManagementException {
     HostnameVerifier hv = new HostnameVerifier()
       {
         public boolean verify(String urlHostName, SSLSession session) {
           return true;
         }
       };
     TrustManager[] trustAllCerts = { new TrustAllTrustManager() };
     SSLContext sc = SSLContext.getInstance("SSL");
     SSLSessionContext sslsc = sc.getServerSessionContext();
     sslsc.setSessionTimeout(0);
     sc.init(null, trustAllCerts, null);
     HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
     
     HttpsURLConnection.setDefaultHostnameVerifier(hv);
   }
   
   public static org.jdom.Document load(String url) {
     org.jdom.Document document = null;
     
     try {
       SAXBuilder reader = new SAXBuilder();
       document = reader.build(new File(url));
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return document;
   }
   public static String XmlToString(String url) {
     org.jdom.Document document = null;
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
 }


