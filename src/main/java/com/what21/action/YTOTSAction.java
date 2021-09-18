 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.YTOBillSku;
 import com.what21.model.YtoBill;
 import com.what21.service.YTOBillService;
 import com.what21.service.YTOBillSkuService;
 import com.what21.util.MD5Util;
 import com.what21.util.ResponseUtil;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"/ytoTSAction"})
 public class YTOTSAction
 {
   private static final int UTF = 0;
   @Autowired
   private YTOBillSkuService yser;
   @Autowired
   private YTOBillService sser;
   
   public List<YTOBillSku> getYto(int[] ids) {
     List<YTOBillSku> list = this.yser.findByIds(ids);
     
     return list;
   }
   
   @RequestMapping({"/ytoTS"})
   public void ytoTS(int[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     URL url = null;
     List<YTOBillSku> list = new ArrayList<>();
     Map<String, Object> map = new HashMap<>();
     if (ids != null) {
       list = getYto(ids);
     } else {
       String waybillNo = (String)request.getSession().getAttribute("waybillNo_1");
       map.put("waybillNo", waybillNo);
       list = this.yser.findByWailNoFor(map);
     } 
     String str = tsUtil(list);
     HttpURLConnection connection = null;
     url = new URL("http://test.edi.ytoglobal.com/outerEdiTrack/receiveEdiTrack");
     
     connection = (HttpURLConnection)url.openConnection();
     connection.setConnectTimeout(600000);
     connection.setReadTimeout(600000);
     connection.setDoOutput(true);
     connection.setDoInput(true);
     connection.setRequestMethod("POST");
     connection.setUseCaches(false);
     connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
     connection.connect();
     
     String message = str;
     String client_id = "PFYTO_PAC";
     String sign = MD5Util.EncoderByMd5(String.valueOf(str) + client_id);
     String param = "message=" + message + "&sign=" + sign + "&client_id=" + client_id;
     System.out.println(sign);
     
     DataOutputStream out = new DataOutputStream(connection.getOutputStream());
     out.write(param.getBytes("UTF-8"));
     
     out.flush();
     out.close();
     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
     StringBuffer buffer = new StringBuffer();
     String line = "";
     while ((line = reader.readLine()) != null) {
       buffer.append(line);
     }
     System.out.println(message);
     System.out.println(buffer.toString());
     String jieguo = buffer.substring(buffer.indexOf("<Status>") + 8, buffer.indexOf("</Status>"));
     
     JSONObject result = new JSONObject();
     if (jieguo.equals("true")) {
       if (ids != null) {
         this.yser.updateByIds(ids);
       } else {
         this.yser.updateByWayBillNo(map);
       } 
       result.put("success", "success");
     } else {
       String error = buffer.substring(buffer.indexOf("<Message>") + 9, buffer.indexOf("</Message>"));
       String error1 = error.substring(error.indexOf("<Message>") + 9);
       System.out.println(error1.equals("Tracking不能为空"));
       if (error1.equals("Tracking不能为空")) {
         error1 = "已存在推送过的数据！请重试";
       } else if (error1.equals("数字签名失败!")) {
         error1 = "签名错误请重试";
       } 
       result.put("success", error1);
     } 
     
     System.out.println(buffer.toString());
     
     ResponseUtil.write(response, result);
   }
   
   @RequestMapping({"/ytoTSAll"})
   public void ytoTSAll(int[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     URL url = null;
     List<YTOBillSku> list = new ArrayList<>();
     Map<String, Object> map = new HashMap<>();
     if (ids != null) {
       List<YtoBill> list1 = this.sser.findById(ids);
       list = this.sser.findByIdsForSku(list1);
       System.out.println("111");
     } else {
       list = this.yser.findAll();
     } 
     String str = tsUtil(list);
     HttpURLConnection connection = null;
     
     url = new URL("http://edi.ytoglobal.com/outerEdiTrack/receiveEdiTrack");
 
     
     connection = (HttpURLConnection)url.openConnection();
     connection.setConnectTimeout(600000);
     connection.setReadTimeout(600000);
     connection.setDoOutput(true);
     connection.setDoInput(true);
     connection.setRequestMethod("POST");
     connection.setUseCaches(false);
     connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
     connection.connect();
     
     System.out.println(str);
     String message = str;
     String client_id = "PFYTO_PAC";
     String sign = MD5Util.EncoderByMd5(String.valueOf(str) + client_id);
     String param = "message=" + message + "&sign=" + sign + "&client_id=" + client_id;
     System.out.println(sign);
     
     DataOutputStream out = new DataOutputStream(connection.getOutputStream());
     out.write(param.getBytes("UTF-8"));
     
     out.flush();
     out.close();
     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
     StringBuffer buffer = new StringBuffer();
     String line = "";
     while ((line = reader.readLine()) != null) {
       buffer.append(line);
     }
     String jieguo = buffer.substring(buffer.indexOf("<Status>") + 8, buffer.indexOf("</Status>"));
     JSONObject result = new JSONObject();
     if (jieguo.equals("true")) {
       if (ids != null) {
         List<YtoBill> list1 = this.sser.findById(ids);
         list = this.sser.findByIdsForSku(list1);
         this.yser.updateByWayBillNos(list);
       } else {
         this.yser.updateAOP();
       } 
       result.put("success", "success");
     } else {
       String error = buffer.substring(buffer.indexOf("<Message>") + 9, buffer.indexOf("</Message>"));
       String error1 = error.substring(error.indexOf("<Message>") + 9);
       System.out.println(error1.equals("Tracking不能为空"));
       if (error1.equals("Tracking不能为空")) {
         error1 = "已存在推送过的数据！请重试";
       }
       result.put("success", error1);
     } 
     
     System.out.println(buffer.toString());
     
     ResponseUtil.write(response, result);
   }
   
   public String tsUtil(List<YTOBillSku> list) throws IOException {
     Date date = new Date();
     SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
     SimpleDateFormat formate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String seqno = formate.format(date);
     String timeStamp = formate1.format(date);
     String str = 
       "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\r\n<Message>\r\r\n<Header>\r\r\n<SeqNo>" + 
 
       
       seqno + "</SeqNo>\r\n" + 
       "<TimeStamp>" + timeStamp + "</TimeStamp>\r\n" + 
       "</Header>\r\n" + 
       "<Trackings>\r\n";
     for (YTOBillSku ytoBillSku : list) {
       if (ytoBillSku.getIsPushed().intValue() > 0) {
         continue;
       }
       str = String.valueOf(str) + 
         "<Tracking>\r\n<WaybillNo>" + 
         ytoBillSku.getWaybillNo() + "</WaybillNo>\r\n" + 
         "<ReferenceOrderNo>" + ytoBillSku.getOrderNo() + "</ReferenceOrderNo>\r\n" + 
         "<ReferenceChangeNo>第三方转单号</ReferenceChangeNo>\r\n" + 
         "<EventCode>" + ytoBillSku.getEventCode() + "</EventCode>\r\n" + 
         "<EventDetail>" + ytoBillSku.getEventDetail() + "</EventDetail>\r\n" + 
         "<EventLocation>" + ytoBillSku.getEventLocation() + "</EventLocation>\r\n" + 
         "<EventOperater>" + ytoBillSku.getEventOperater() + "</EventOperater>\r\n" + 
         "<EventOperaterPhone>" + ytoBillSku.getEventOperaterPhone() + "</EventOperaterPhone>\r\n" + 
         "<City>" + ytoBillSku.getCity() + "</City>\r\n" + 
         "<NextCity>" + ytoBillSku.getNextCity() + "</NextCity>\r\n" + 
         "<EventTime>" + ytoBillSku.getEventTime() + "</EventTime>\r\n" + 
         "<ServiceCode>PFYTO_PAC</ServiceCode>\r\n" + 
         "<CountryCode>CN</CountryCode>\r\n" + 
         "</Tracking>\r\n";
     } 
 
     
     str = String.valueOf(str) + "</Trackings>\r\n</Message>";
     
     return str;
   }
 }


