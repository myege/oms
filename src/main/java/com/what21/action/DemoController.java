 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.util.SSLUtils;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.net.ssl.HostnameVerifier;
 import javax.net.ssl.HttpsURLConnection;
 import javax.net.ssl.SSLContext;
 import javax.net.ssl.SSLSession;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"Shanh"})
 public class DemoController
 {
   @RequestMapping({"Shanh"})
   public String ShanH(HttpServletResponse response, HttpServletRequest request) throws Exception {
     Map<String, Object> payExchangeInfoHead = new HashMap<>();
     Map<String, Object> payExchangeInfo = new HashMap<>();
     Map<String, Object> goodsInfo = new HashMap<>();
     Map<String, Object> one = new HashMap<>();
     
     payExchangeInfoHead.put("guid", "123123123");
     payExchangeInfoHead.put("initalRequest", "123123");
     payExchangeInfoHead.put("initalResponse", "123123");
     payExchangeInfoHead.put("ebpCode", "123123");
     payExchangeInfoHead.put("payCode", "123123");
     payExchangeInfoHead.put("payTransactionId", "123123");
     payExchangeInfoHead.put("totalAmount", "123123");
     payExchangeInfoHead.put("currency", "123123");
     payExchangeInfoHead.put("verDept", "123123");
     payExchangeInfoHead.put("payType", "123123");
     payExchangeInfoHead.put("tradingTime", "12123");
     payExchangeInfoHead.put("note", "12123");
     
     goodsInfo.put("gname", "123123");
     goodsInfo.put("itemLink", "123123");
     
     payExchangeInfo.put("orderNo", "123123");
     payExchangeInfo.put("recpAccount", "123123");
     payExchangeInfo.put("recpCode", "123123");
     payExchangeInfo.put("recpName", "123123");
     payExchangeInfo.put("goodsInfo", goodsInfo);
     
     List<Map<String, Object>> list = new ArrayList<>();
     list.add(payExchangeInfo);
     
     one.put("sessionID", "fe2374-8fnejf97-32839218");
     one.put("payExchangeInfoHead", payExchangeInfoHead);
     one.put("payExchangeInfoList", list);
     one.put("serviceTime", Long.valueOf((new Date()).getTime()));
     
     String inData = "'sessionID':'fe2374-8fnejf97-32839218'||'payExchangeInfoHead': " + JSONObject.toJSONString(payExchangeInfoHead) + 
       "||'payExchangeInfoList': " + JSONObject.toJSONString(list) + "||'serviceTime':1533271903898";
     
     one.put("certNo", "0125e5cd");
     one.put("signValue", "EuBXPa2ah5z81KZhxGaQ/CyvOPYtKl7mDxxuhKYZCDoKh94z0D7+srJZ6N+3PPT0JQcco7iEov4nY2psqOppmxZ43MIrXfmxi27f/+lCyetdePP7xiZFNrKrIZonzLJO0aS9J/BKM+/QoZC/zRLtgjo/O2E1NNeTdrPNFMpTO7k=");
     
     index(JSONObject.toJSONString(one));
     return "";
   }
 
   
   public static void index(String testPayInfo) throws Exception {
     try {
       SSLContext sc = SSLUtils.createSslContext();
 
       
       URL requestUrl = new URL("https://swapptest.singlewindow.cn/ceb2grab/grab/realTimeDataUpload");
       HttpsURLConnection conn = (HttpsURLConnection)requestUrl.openConnection();
       
       conn.setSSLSocketFactory(sc.getSocketFactory());
       conn.setHostnameVerifier(new HostnameVerifier()
           {
             public boolean verify(String s, SSLSession sslSession) {
               return true;
             }
           });
       conn.setDoInput(true);
       conn.setDoOutput(true);
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       
       conn.connect();
       DataOutputStream out = new DataOutputStream(conn.getOutputStream());
       String jsonString = "payExInfoStr=" + testPayInfo;
       out.write(jsonString.getBytes("utf-8"));
       
       out.flush();
       out.close();
       StringBuilder result = new StringBuilder();
       Exception exception1 = null, exception2 = null; try { BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); 
         try { String line;
           while ((line = br.readLine()) != null)
             result.append(line).append("\n");  }
         finally
         { if (br != null) br.close();  }  } finally { exception2 = null; if (exception1 == null) { exception1 = exception2; } else if (exception1 != exception2) { exception1.addSuppressed(exception2); }
          }
     
     } catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


