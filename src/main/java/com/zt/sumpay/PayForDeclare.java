 package com.zt.sumpay;
 
 import java.io.UnsupportedEncodingException;
 import java.security.MessageDigest;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.TreeMap;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 public class PayForDeclare
 {
   public static final String CFM_CUSTOMS_CLEARANCE_URL = "https://webgate.sumpay.cn/webgate/payForDeclare.htm";
   public static final int TRY_NUMBER = 1;
   public static final String CFM_PAY_ACCOUNT = "10000000561";
   public static final String CFM_SECRET_KEY = "30820122300d06092a864886f70d01010105000382010f003082010a02820101009d1d8882b6e0164250a529952dd19e5bfd8a9bbc6f95908b62e43e8e8663c7bf9f8dc61067dd248d2c0a5abaa629c6a775fb46b1a4e1c8c52a1d373261e3f9da895553404a6fbf119e85b60260010e0cf20e2014c27cbb033797273df233b473b08d157b8ad9ea662a720af37a778fef752f2a18acdcc03538c525f25f070ced8320cfc1dfff3097a78987b67d6cf31d7c30d7d7c31e3f53f9c405e52390a15c890558b9cbc3d418921c8303b618ce7a5e6e6771227dd749955ceb5dcb1148e7f74ed8ea5c8766c5c9715f65b961030dd29bdab8fa7b03d151bf5d4ca2f028e5b56f0db9eda4001ad2610873740f008afbd09ee72afaf899cfb8cb8ec183b8790203010001";
   public static final String REQ01022_ORDER_NO = "ORDER_NO";
   public static final String REQ01022_PAY_VOUCHER_SFT = "PAY_VOUCHER_SFT";
   public static final String RET01022_RESULT = "RESULT";
   public static final String RET01022_NOTE = "NOTE";
   public static final String RET01022_DATA = "DATA";
   public static final String VERSION = "version";
   public static final String PARTNER_ID = "partnerId";
   public static final String ORDER_ID = "orderId";
   public static final String SUBMIT_TIME = "submitTime";
   public static final String USOLV_TRADE_ID = "usolvTradeId";
   public static final String PAY_TYPE = "payType";
   public static final String CHARSET = "charset";
   public static final String SIGN_TYPE = "signType";
   public static final String SIGN_MSG = "signMsg";
   
   public static void main(String[] args) throws UnsupportedEncodingException {
     payForDeclare("1092714554360921246");
   }
   
   public static String payForDeclare(String mUsolvTradeId) throws UnsupportedEncodingException {
     SimpleDateFormat dateFormat = new SimpleDateFormat(
         "yyyyMMddHHmmss");
     
     SimpleDateFormat stampFormat = new SimpleDateFormat(
         "yyyyMMddHHmmssSSS");
     
     Date date = new Date();
     
     String orderId = String.valueOf(stampFormat.format(date)) + 
       "0001";
     
     Calendar calendar = Calendar.getInstance();
     calendar.add(12, -5);
     String submitTime = dateFormat.format(calendar.getTime());
 
     
     Map<String, String> mReqParams = new HashMap<>();
     mReqParams.put("version", "1.0");
     mReqParams.put("partnerId", "10000000561");
     mReqParams.put("orderId", orderId);
     mReqParams.put("submitTime", submitTime);
     mReqParams.put("usolvTradeId", mUsolvTradeId);
     mReqParams.put("payType", "0");
     
     mReqParams.put("charset", "1");
     mReqParams.put("signType", "2");
 
     
     String mSign = sing(mReqParams, "30820122300d06092a864886f70d01010105000382010f003082010a02820101009d1d8882b6e0164250a529952dd19e5bfd8a9bbc6f95908b62e43e8e8663c7bf9f8dc61067dd248d2c0a5abaa629c6a775fb46b1a4e1c8c52a1d373261e3f9da895553404a6fbf119e85b60260010e0cf20e2014c27cbb033797273df233b473b08d157b8ad9ea662a720af37a778fef752f2a18acdcc03538c525f25f070ced8320cfc1dfff3097a78987b67d6cf31d7c30d7d7c31e3f53f9c405e52390a15c890558b9cbc3d418921c8303b618ce7a5e6e6771227dd749955ceb5dcb1148e7f74ed8ea5c8766c5c9715f65b961030dd29bdab8fa7b03d151bf5d4ca2f028e5b56f0db9eda4001ad2610873740f008afbd09ee72afaf899cfb8cb8ec183b8790203010001");
     
     mReqParams.put("signMsg", mSign);
 
     
     String result = PayAction.sendPost("https://webgate.sumpay.cn/webgate/payForDeclare.htm", mReqParams);
     System.out.println("支付公司付款报关接口返回值-------------------->" + result);
     return result;
   }
   
   private static String sing(Map<String, String> params, String secretKey) {
     String mSign = "";
     
     if (params == null) {
       return mSign;
     }
 
 
     
     try {
       TreeMap<String, String> mParams = new TreeMap<>();
       for (String key : params.keySet()) {
         if (key == null) {
           continue;
         }
         mParams.put(key, params.get(key));
       } 
       
       for (String key : mParams.keySet()) {
         String value = mParams.get(key);
         
         if (value == null || "".equals(value) || 
           "null".equalsIgnoreCase(value)) {
           continue;
         }
 
         
         if ("".equals(mSign)) {
           mSign = String.valueOf(mSign) + key + "=" + value; continue;
         } 
         mSign = String.valueOf(mSign) + "&" + key + "=" + value;
       }       
       mSign = String.valueOf(mSign) + "&pkey=" + secretKey;
       
       System.out.println(mSign);
       mSign = md5(mSign);
       
       mSign = mSign.toLowerCase();
     }
     catch (Exception e) {
       System.out.println("商盟订单代扣计算签名错误，错误原因:" + e.getMessage());
     } 
     
     return mSign;
   }
   
   private static String md5(String content) {
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
   
   private Map<String, Object> xml2Map(String xmlStr, boolean needRootKey) {
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
   
   private Map xml2mapWithAttr(Element element) {
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
 }


