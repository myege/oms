 package com.zt.kjybd;
 
 import com.what21.model.OrderMail;
 import com.what21.model.OrderMailSku;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLEncoder;
 import java.util.List;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class Push
 {
   public static String sendPost(String url, String param) {
     PrintWriter out = null;
     BufferedReader in = null;
     String result = "";
     try {
       URL realUrl = new URL(url);
       URLConnection conn = realUrl.openConnection();
       conn.setRequestProperty("accept", "*/*");
       conn.setRequestProperty("connection", "Keep-Alive");
       conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
       
       conn.setDoOutput(true);
       conn.setDoInput(true);
       out = new PrintWriter(conn.getOutputStream());
 
 
 
       
       out.print(param);
       out.flush();
       in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
       
       String line;
       while ((line = in.readLine()) != null) {
         result = String.valueOf(result) + line;
       }
     } catch (Exception e) {
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
   
   public static String xml() {
     StringBuffer xml = new StringBuffer();
     xml.append("<logisticsEventsRequest>");
     xml.append("<logisticsEvent>");
     xml.append("<eventHeader>");
     xml.append("<eventTime>2016-6-21 14:32:32</eventTime>");
     xml.append("<eventSource>Test</eventSource>");
     xml.append("<eventTarget>Test</eventTarget>");
     xml.append("</eventHeader>");
     xml.append("<eventBody>");
     xml.append("<logisticsDetail>");
     
     xml.append("<eCommerceCode>785309525</eCommerceCode>");
     xml.append("<eCommerceName>杭州博奥国际货运代理有限公司</eCommerceName>");
     xml.append("<accountBookNo>W29916000069</accountBookNo>");
     xml.append("<orderNo>CS1116980622597</orderNo>");
     xml.append("<tradeId>111</tradeId>");
     xml.append("<logisticsServiceType>直邮</logisticsServiceType>");
     xml.append("<weight>16000</weight>");
     
     xml.append("<accountBookNo></accountBookNo>");
     xml.append("<orderTotalFee>100</orderTotalFee>");
     xml.append("<currency>142</currency>");
     xml.append("<innerTms>STO</innerTms>");
     xml.append("<innerMailNo>2202224545</innerMailNo>");
     xml.append("<totalMailNo>160-213213</totalMailNo>");
     xml.append("<transportNumber></transportNumber>");
     xml.append("<occurTime>2016-6-21 22:22:22</occurTime>");
     xml.append("<logisticsRemark></logisticsRemark>");
     
     xml.append("<goodsList>");
     xml.append("<orderGoods>");
     xml.append("<itemName>nutrilon牛栏奶粉4段800g</itemName>");
     xml.append("<itemId>ZY1901101000T8668291</itemId>");
     xml.append("<goodsItemNo>ZY1901101000T8668291</goodsItemNo >");
     xml.append("<count>3</count>");
     xml.append("<price>8500</price>");
     xml.append("<currency>142</currency>");
     xml.append("</orderGoods>");
     xml.append("<orderGoods>");
     xml.append("<itemName>hero baby美素奶粉3段700g</itemName>");
     xml.append("<itemId>ZY1901101000T2155145</itemId>");
     xml.append("<goodsItemNo>ZY1901101000T2155145</goodsItemNo>");
     xml.append("<count>1</count>");
     xml.append("<price>8000</price>");
     xml.append("<currency>142</currency>");
     xml.append("</orderGoods>");
     xml.append("</goodsList>");
     xml.append("</logisticsDetail>");
     
     xml.append("<receiverDetail>");
     xml.append("<name>罗芳</name>");
     xml.append("<tel>13510787379</tel>");
     xml.append("<email></email>");
     xml.append("<country>中国</country>");
     xml.append("<province>湖南省</province>");
     xml.append("<city>湘潭市</city>");
     xml.append("<district>雨湖区</district>");
     xml.append("<streetAddress>楠竹山镇勤俭村3栋16号</streetAddress>");
     xml.append("<zipCode>411100</zipCode>");
     xml.append("</receiverDetail>");
     xml.append("</eventBody>");
     xml.append("</logisticsEvent>");
     xml.append("</logisticsEventsRequest>");
     
     String url = "http://60.190.244.27:7045/home/GetPostDataTest";
     String key = "Test";
     StringBuffer param = new StringBuffer();
     
     byte[] md5Value = TestWebService.MD5(String.valueOf(xml.toString()) + key);
     String base64Value = TestWebService.getBase64(md5Value);
     
     base64Value = base64Value.replace("+", "%2B");
     base64Value = base64Value.replace("/", "%2F");
     base64Value = replaceBlank(base64Value);
 
 
     
     param.append("data=").append(xml.toString()).append("&key=Test&format=xml").append("&method=PACKAGE_ARRIVE_INFO&version=1.0&sign=").append(base64Value);
 
     
     String request = sendPost(url, param.toString());
 
     
     return request;
   }
 
   
   public static String xml(OrderMail order, List<OrderMailSku> goodsList) {
     StringBuffer xml = new StringBuffer();
     xml.append("<logisticsEventsRequest>");
     xml.append("<logisticsEvent>");
     xml.append("<eventHeader>");
     xml.append("<eventTime>2016-5-19 14:32:32</eventTime>");
     xml.append("<eventSource>Test</eventSource>");
     xml.append("<eventTarget>Test</eventTarget>");
     xml.append("</eventHeader>");
     xml.append("<eventBody>");
     xml.append("<logisticsDetail>");
     xml.append("<eCommerceCode>785309525</eCommerceCode>");
     xml.append("<eCommerceName>杭州博奥国际货运代理有限公司</eCommerceName>");
     xml.append("<accountBookNo>W29916000069</accountBookNo>");
     xml.append("<orderNo>CS1116980622597</orderNo>");
     xml.append("<tradeId>111</tradeId>");
     xml.append("<logisticsServiceType>直邮</logisticsServiceType>");
     xml.append("<weight>16000</weight>");
     
     xml.append("<accountBookNo></accountBookNo>");
     xml.append("<orderTotalFee>100</orderTotalFee>");
     xml.append("<currency>142</currency>");
     xml.append("<innerTms>STO</innerTms>");
     xml.append("<innerMailNo>2202224545</innerMailNo>");
     xml.append("<totalMailNo>160-213213</totalMailNo>");
     xml.append("<transportNumber></transportNumber>");
     xml.append("<occurTime>2016-5-19 22:22:22</occurTime>");
     xml.append("<logisticsRemark></logisticsRemark>");
     
     xml.append("<goodsList>");
     for (OrderMailSku goods : goodsList) {
       xml.append("<orderGoods>");
       xml.append("<itemName>" + goods.getItemName() + "</itemName>");
       xml.append("<itemId>" + goods.getItemsku() + "</itemId>");
       xml.append("<goodsItemNo>" + goods.getItemsku() + "</goodsItemNo >");
       xml.append("<count>" + goods.getItemCount() + "</count>");
       xml.append("<price>" + goods.getUnitPrice() + "</price>");
       xml.append("<currency>142</currency>");
       xml.append("</orderGoods>");
     } 
     xml.append("</goodsList>");
     xml.append("</logisticsDetail>");
     
     xml.append("<receiverDetail>");
     xml.append("<name>" + order.getReceiveMan() + "</name>");
     xml.append("<tel>" + order.getReceiveManPhone() + "</tel>");
     xml.append("<email></email>");
     xml.append("<country>中国</country>");
     xml.append("<province>" + order.getReceiveProvince() + "</province>");
     xml.append("<city>" + order.getReceiveCity() + "</city>");
     xml.append("<district>" + order.getReceiveCounty() + "</district>");
     xml.append("<streetAddress>" + order.getReceiveManAddress() + "</streetAddress>");
     xml.append("<zipCode></zipCode>");
     xml.append("</receiverDetail>");
     xml.append("</eventBody>");
     xml.append("</logisticsEvent>");
     xml.append("</logisticsEventsRequest>");
     
     String url = "http://60.190.244.27:7045/home/GetPostDataTest";
     String key = "Test";
     StringBuffer param = new StringBuffer();
     byte[] md5Value = TestWebService.MD5(String.valueOf(xml.toString()) + key);
     String base64Value = TestWebService.getBase64(md5Value);
 
     
     base64Value = replaceBlank(base64Value);
 
     
     param.append("data=").append(URLEncoder.encode(xml.toString())).append("&key=Test&format=xml").append("&method=PACKAGE_ARRIVE_INFO&version=1.0&sign=").append(URLEncoder.encode(base64Value));
     
     String request = sendPost(url, param.toString());
 
     
     return request;
   }
   
   public static String replaceBlank(String str) {
     String dest = "";
     if (str != null) {
       Pattern p = Pattern.compile("\\s*|\t|\r|\n");
       Matcher m = p.matcher(str);
       dest = m.replaceAll("");
     } 
     return dest;
   }
 }


