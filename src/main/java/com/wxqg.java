 package com;
 
 import com.what21.util.MD5Util;
 import com.what21.util.StringUtil;
 import com.zt.kjybd.PushtoWQ;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Map;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 public class wxqg
 {
   public static void main(String[] args) throws Exception {
     bg("2019091697101102", "4200000387201909161899919879");
   }
   
   public static void ct(String dd, String lsh) throws Exception {
     String key = "huyeming18668111558hu18668111558";
     Map<String, String> params = new HashMap<>();
     params.put("mch_id", "1543243561");
     params.put("appid", "wxeca7446c5144104f");
     params.put("mch_customs_no", "3316W60025");
     params.put("out_trade_no", dd);
     params.put("transaction_id", lsh);
     params.put("customs", "HANGZHOU_ZS");
     
     String[] paramArr = (String[])params.keySet().toArray((Object[])new String[0]);
     Arrays.sort((Object[])paramArr);
 
     
     StringBuilder query = new StringBuilder();
     boolean hasParam = false;
     Element xml = DocumentHelper.createElement("xml"); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = paramArr).length, b = 0; b < i; ) { String param = arrayOfString1[b];
       String value = params.get(param);
       if (StringUtil.isNotEmpty(param).booleanValue() && StringUtil.isNotEmpty(value).booleanValue()) {
         if (hasParam) {
           query.append("&");
         } else {
           hasParam = true;
         } 
         query.append(param).append("=").append(value);
         Element element = xml.addElement(param);
         element.addText(value);
       }  b++; }
     
     query.append("&").append("key").append("=").append(key);
     System.out.println("asdasd===" + query);
     String sign = MD5Util.MD5_forbm(query.toString(), "UTF-8");
     Element keyEmt = xml.addElement("sign");
     keyEmt.addText(sign);
     
     System.out.println(sign);
     
     String url = "https://api.mch.weixin.qq.com/cgi-bin/mch/newcustoms/customdeclareredeclare";
     System.out.println(xml.asXML());
     PushtoWQ.sendPost2(url, xml.asXML());
   }
   public static void cx(String dd, String lsh) throws Exception {
     String key = "huyeming18668111558hu18668111558";
     Map<String, String> params = new HashMap<>();
     params.put("mch_id", "1543243561");
     params.put("appid", "wxeca7446c5144104f");
     
     params.put("out_trade_no", "2019072954971001");
     params.put("transaction_id", "4200000385201907291337524894");
     params.put("customs", "HANGZHOU_ZS");
     
     String[] paramArr = (String[])params.keySet().toArray((Object[])new String[0]);
     Arrays.sort((Object[])paramArr);
 
     
     StringBuilder query = new StringBuilder();
     boolean hasParam = false;
     Element xml = DocumentHelper.createElement("xml"); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = paramArr).length, b = 0; b < i; ) { String param = arrayOfString1[b];
       String value = params.get(param);
       if (StringUtil.isNotEmpty(param).booleanValue() && StringUtil.isNotEmpty(value).booleanValue()) {
         if (hasParam) {
           query.append("&");
         } else {
           hasParam = true;
         } 
         query.append(param).append("=").append(value);
         Element element = xml.addElement(param);
         element.addText(value);
       }  b++; }
     
     query.append("&").append("key").append("=").append(key);
     System.out.println("asdasd===" + query);
     String sign = MD5Util.MD5_forbm(query.toString(), "UTF-8");
     Element keyEmt = xml.addElement("sign");
     keyEmt.addText(sign);
     
     System.out.println(sign);
     
     String url = "https://api.mch.weixin.qq.com/cgi-bin/mch/customs/customdeclarequery";
     System.out.println(xml.asXML());
     PushtoWQ.sendPost2(url, xml.asXML());
   }
   public static void bg(String dd, String lsh) throws Exception {
     String key = "huyeming18668111558hu18668111558";
     Map<String, String> params = new HashMap<>();
     params.put("mch_id", "1543243561");
     params.put("appid", "wxeca7446c5144104f");
     params.put("mch_customs_no", "3316W60025");
     params.put("out_trade_no", dd);
     params.put("transaction_id", lsh);
     params.put("customs", "HANGZHOU_ZS");
     
     String[] paramArr = (String[])params.keySet().toArray((Object[])new String[0]);
     Arrays.sort((Object[])paramArr);
 
     
     StringBuilder query = new StringBuilder();
     boolean hasParam = false;
     Element xml = DocumentHelper.createElement("xml"); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = paramArr).length, b = 0; b < i; ) { String param = arrayOfString1[b];
       String value = params.get(param);
       if (StringUtil.isNotEmpty(param).booleanValue() && StringUtil.isNotEmpty(value).booleanValue()) {
         if (hasParam) {
           query.append("&");
         } else {
           hasParam = true;
         } 
         query.append(param).append("=").append(value);
         Element element = xml.addElement(param);
         element.addText(value);
       }  b++; }
     
     query.append("&").append("key").append("=").append(key);
     System.out.println("asdasd===" + query);
     String sign = MD5Util.MD5_forbm(query.toString(), "UTF-8");
     Element keyEmt = xml.addElement("sign");
     keyEmt.addText(sign);
     
     System.out.println(sign);
     
     String url = "https://api.mch.weixin.qq.com/cgi-bin/mch/customs/customdeclareorder";
     System.out.println(xml.asXML());
     PushtoWQ.sendPost2(url, xml.asXML());
   }
 }


