 package com.zt.sumpay;
 
 import com.alibaba.fastjson.JSONObject;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.UnsupportedEncodingException;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLEncoder;
 import java.security.MessageDigest;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"slipPay"})
 public class PayAction
 {
   public static void main(String[] args) {}
   
   @RequestMapping({"returnPay"})
   @ResponseBody
   public String returnPay() {
     Map<String, Object> map = new HashMap<>();
     map.put("resultCode", "100");
     map.put("resultMsg", "ok");
     return JSONObject.toJSONString(map);
   }
   
   public static String sendPost(String url, Map<String, String> params) {
     OutputStreamWriter out = null;
     BufferedReader in = null;
     StringBuilder result = new StringBuilder();
     try {
       URL realUrl = new URL(url);
       HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
       
       conn.setDoOutput(true);
       conn.setDoInput(true);
       
       conn.setRequestMethod("POST");
       
       conn.setRequestProperty("accept", "*/*");
       conn.setRequestProperty("connection", "Keep-Alive");
       conn.setRequestProperty("user-agent", 
           "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       conn.connect();
       
       out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
       
       if (params != null) {
         StringBuilder param = new StringBuilder();
         for (Map.Entry<String, String> entry : params.entrySet()) {
           if (param.length() > 0) {
             param.append("&");
           }
           param.append(entry.getKey());
           param.append("=");
 
           
           String value = entry.getValue();
           if ("goodsName".equals(entry.getKey())) {
             value = value.replaceAll("\\&", "%26");
           }
           param.append(value);
         } 
         out.write(param.toString().replaceAll("\\+", "%2B"));
       } 
       
       out.flush();
       
       in = new BufferedReader(
           new InputStreamReader(conn.getInputStream(), "UTF-8"));
       String line;
       while ((line = in.readLine()) != null) {
         result.append(line);
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
     return result.toString();
   }
   
   public static String sendPostUrlDecode(String url, Map<String, String> params) {
     OutputStreamWriter out = null;
     BufferedReader in = null;
     StringBuilder result = new StringBuilder();
     try {
       URL realUrl = new URL(url);
       HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
       
       conn.setDoOutput(true);
       conn.setDoInput(true);
       
       conn.setRequestMethod("POST");
       
       conn.setRequestProperty("accept", "*/*");
       conn.setRequestProperty("connection", "Keep-Alive");
       conn.setRequestProperty("user-agent", 
           "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       conn.connect();
       
       out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
       
       if (params != null) {
         StringBuilder param = new StringBuilder();
         for (Map.Entry<String, String> entry : params.entrySet()) {
           if (param.length() > 0) {
             param.append("&");
           }
           param.append(entry.getKey());
           param.append("=");
           param.append(entry.getValue());
         } 
         String p = URLEncoder.encode(param.toString());
         System.out.println("URLDECODE之后" + p);
         out.write(p);
       } 
       
       out.flush();
       
       in = new BufferedReader(
           new InputStreamReader(conn.getInputStream(), "UTF-8"));
       String line;
       while ((line = in.readLine()) != null) {
         result.append(line);
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
     return result.toString();
   }
   
   public static String sendGet(String url, Map<String, String> param) throws UnsupportedEncodingException {
     StringBuilder _param = new StringBuilder();
     for (Map.Entry<String, String> entry : param.entrySet()) {
       if (_param.length() > 0) {
         _param.append("&");
       }
       _param.append(entry.getKey());
       _param.append("=");
       _param.append(URLEncoder.encode(entry.getValue(), "utf-8"));
     } 
     String result = "";
     BufferedReader in = null;
     try {
       String urlNameString = String.valueOf(url) + "?" + _param;
       URL realUrl = new URL(urlNameString);
       
       URLConnection connection = realUrl.openConnection();
       
       connection.setRequestProperty("accept", "*/*");
       connection.setRequestProperty("connection", "Keep-Alive");
       connection.setRequestProperty("user-agent", 
           "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
       
       connection.connect();
 
 
       
       in = new BufferedReader(new InputStreamReader(
             connection.getInputStream()));
       String line;
       while ((line = in.readLine()) != null) {
         result = String.valueOf(result) + line;
       }
     } catch (Exception e) {
       System.out.println("发送GET请求出现异常！" + e);
       e.printStackTrace();
     } finally {
 
       
       try {
         if (in != null) {
           in.close();
         }
       } catch (Exception e2) {
         e2.printStackTrace();
       } 
     } 
     return result;
   }
   
   public static Map<String, Object> xml2Map(String xmlStr, boolean needRootKey) {
     Map<String, Object> map = new HashMap<>();
     try {
       Document doc = DocumentHelper.parseText(xmlStr);
       Element root = doc.getRootElement();
       map = xml2mapWithAttr(root);
       if (root.elements().size() == 0 && root.attributes().size() == 0) {
         return map;
       }
       if (needRootKey) {
         
         Map<String, Object> rootMap = new HashMap<>();
         rootMap.put(root.getName(), map);
         return rootMap;
       } 
     } catch (Exception exception) {}
 
     
     return map;
   }
   
   public static Map xml2mapWithAttr(Element element) {
     Map<String, Object> map = new LinkedHashMap<>();
     
     List<Element> list = element.elements();
     List<Attribute> listAttr0 = element.attributes();
     for (Attribute attr : listAttr0) {
       map.put("@" + attr.getName(), attr.getValue());
     }
     if (list.size() > 0) {
       
       for (int i = 0; i < list.size(); i++) {
         Element iter = list.get(i);
         List<Object> mapList = new ArrayList();
         
         if (iter.elements().size() > 0) {
           Map m = xml2mapWithAttr(iter);
           if (map.get(iter.getName()) != null) {
             Object obj = map.get(iter.getName());
             if (!(obj instanceof List)) {
               mapList = new ArrayList();
               mapList.add(obj);
               mapList.add(m);
             } 
             if (obj instanceof List) {
               mapList = (List<Object>)obj;
               mapList.add(m);
             } 
             map.put(iter.getName(), mapList);
           } else {
             map.put(iter.getName(), m);
           } 
         } else {
           List<Attribute> listAttr = iter.attributes();
           Map<String, Object> attrMap = null;
           boolean hasAttributes = false;
           if (listAttr.size() > 0) {
             hasAttributes = true;
             attrMap = new LinkedHashMap<>();
             for (Attribute attr : listAttr) {
               attrMap.put("@" + attr.getName(), attr.getValue());
             }
           } 
           
           if (map.get(iter.getName()) != null) {
             Object obj = map.get(iter.getName());
             if (!(obj instanceof List)) {
               mapList = new ArrayList();
               mapList.add(obj);
               
               if (hasAttributes) {
                 attrMap.put("#text", iter.getText());
                 mapList.add(attrMap);
               } else {
                 mapList.add(iter.getText());
               } 
             } 
             if (obj instanceof List) {
               mapList = (List<Object>)obj;
               
               if (hasAttributes) {
                 attrMap.put("#text", iter.getText());
                 mapList.add(attrMap);
               } else {
                 mapList.add(iter.getText());
               } 
             } 
             map.put(iter.getName(), mapList);
           
           }
           else if (hasAttributes) {
             attrMap.put("#text", iter.getText());
             map.put(iter.getName(), attrMap);
           } else {
             map.put(iter.getName(), iter.getText());
           }
         
         }
       
       }
     
     } else if (listAttr0.size() > 0) {
       map.put("#text", element.getText());
     } else {
       map.put(element.getName(), element.getText());
     } 
     
     return map;
   }
   
   public static String map2xml(Map<String, Object> map) {
     StringBuffer buffer = new StringBuffer();
     
     Set<String> set = map.keySet();
     for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
       String key = it.next();
       Object value = map.get(key);
       if (value == null)
         value = ""; 
       if (value.getClass().getName().equals("java.util.ArrayList")) {
         ArrayList<HashMap> list = (ArrayList)map.get(key);
         buffer.append("<" + key + ">");
         for (int i = 0; i < list.size(); i++) {
           HashMap<String, Object> hm = list.get(i);
           buffer.append(map2xml(hm));
         } 
         buffer.append("</" + key + ">");
         continue;
       } 
       if (value instanceof HashMap) {
         buffer.append("<" + key + ">");
         buffer.append(map2xml((HashMap)value));
         buffer.append("</" + key + ">"); continue;
       } 
       buffer.append("<" + key + ">" + value + "</" + key + ">");
     }     
     return buffer.toString();
   }
   
   public static String md5(String content) {
     String result = "";
     try {
       MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(content.getBytes());
       byte[] b = md.digest();
       
       StringBuffer buf = new StringBuffer("");
       for (int offset = 0; offset < b.length; offset++) {
         int i = b[offset];
         if (i < 0)
           i += 256; 
         if (i < 16)
           buf.append("0"); 
         buf.append(Integer.toHexString(i));
       } 
       result = buf.toString();
     } catch (Exception e) {
       System.out.println(e);
     } 
     return result;
   }
 }


