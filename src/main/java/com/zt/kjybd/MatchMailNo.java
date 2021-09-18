 package com.zt.kjybd;
 
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderMail;
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.util.MD5Util;
 import java.net.URLEncoder;
 import java.util.Iterator;
 import org.dom4j.Document;
 import org.dom4j.DocumentException;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 public class MatchMailNo
 {
   static String CKDM = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.CKDM.getValue());
   
   public static void hqmd(String xml, OrderBonded orderBonded) {
     String url = "http://ebill.ns.800best.com/ems/api/process";
     String parnerid = "";
     String Key = "";
     if (CKDM.equals("LO")) {
       parnerid = "";
       Key = "";
     } 
     
     String bizData = xml;
     
     String serviceType = "BillPrintRequest";
     
     String digest = String.valueOf(bizData) + Key;
     
     try {
       digest = MD5Util.EncoderByMd5(digest);
     } catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     String param = "parternID=" + parnerid + "&digest=" + URLEncoder.encode(digest) + "&msgId=" + URLEncoder.encode(digest) + "&serviceType=" + serviceType + "&bizData=" + bizData;
     String result = PushtoWQ.sendPost_ht(url, param);
     System.out.println(result);
     
     String mailNo = "";
     String markDestination = "";
     String billProvideSiteName = "";
     String billProvideSiteCode = "";
     if (result.indexOf("SUCCESS") != -1) {
       try {
         Document document = DocumentHelper.parseText(result);
         Element employees = document.getRootElement();
         
         for (Iterator<Element> i = employees.elementIterator(); i.hasNext(); ) {
           Element employee = i.next();
           for (Iterator<Element> j = employee.elementIterator(); j.hasNext(); ) {
             Element node = j.next();
 
             
             if (node.getName().equals("mailNo")) {
               mailNo = node.getText();
             }
             if (node.getName().equals("markDestination")) {
               markDestination = node.getText();
             }
             if (node.getName().equals("pkgCode")) {
               billProvideSiteName = node.getText();
             }
             if (node.getName().equals("sortingCode")) {
               billProvideSiteCode = String.valueOf(markDestination) + node.getText();
             }
           } 
         } 
       } catch (DocumentException e) {
         e.printStackTrace();
       } 
     }
     orderBonded.setMailNo(mailNo);
     orderBonded.setMarkDestination(markDestination);
     orderBonded.setBillProvideSiteName(billProvideSiteName);
     orderBonded.setBillProvideSiteCode(billProvideSiteCode);
   }
 
   
   public static void hqmd2(String xml, OrderBonded orderBonded) {
     String url = "http://ebill.ns.800best.com/ems/api/process";
     
     String parnerid = "310038_0025";
     
     String bizData = xml;
     String Key = "7Egv60MfCwgs";
     
     String serviceType = "BillPrintRequest";
     
     String digest = String.valueOf(bizData) + Key;
     
     try {
       digest = MD5Util.EncoderByMd5(digest);
     } catch (Exception e) {
       e.printStackTrace();
     } 
 
 
     
     String param = "parternID=" + parnerid + "&digest=" + URLEncoder.encode(digest) + "&serviceType=" + serviceType + "&bizData=" + bizData;
     String result = PushtoWQ.sendPost_ht(url, param);
     System.out.println(result);
     
     String mailNo = "";
     String markDestination = "";
     String billProvideSiteName = "";
     String billProvideSiteCode = "";
     if (result.indexOf("SUCCESS") != -1) {
       try {
         Document document = DocumentHelper.parseText(result);
         Element employees = document.getRootElement();
         
         for (Iterator<Element> i = employees.elementIterator(); i.hasNext(); ) {
           Element employee = i.next();
           for (Iterator<Element> j = employee.elementIterator(); j.hasNext(); ) {
             Element node = j.next();
 
             
             if (node.getName().equals("mailNo")) {
               mailNo = node.getText();
             }
             if (node.getName().equals("markDestination")) {
               markDestination = node.getText();
             }
             if (node.getName().equals("pkgCode")) {
               billProvideSiteName = node.getText();
             }
             if (node.getName().equals("sortingCode")) {
               billProvideSiteCode = String.valueOf(markDestination) + node.getText();
             }
           } 
         } 
       } catch (DocumentException e) {
         e.printStackTrace();
       } 
     }
     orderBonded.setMailNo(mailNo);
     orderBonded.setMarkDestination(markDestination);
     orderBonded.setBillProvideSiteName(billProvideSiteName);
     orderBonded.setBillProvideSiteCode(billProvideSiteCode);
   }
 
   
   public static void hqmd_zy(String xml, OrderMail orderMail) {
     String url = "http://ebill.ns.800best.com/ems/api/process";
     String parnerid = "310038_0010";
     String bizData = xml;
     String Key = "RykhQeaTTlTK";
     String serviceType = "BillPrintRequest";
     
     String digest = String.valueOf(bizData) + Key;
     
     try {
       digest = MD5Util.EncoderByMd5(digest);
     } catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     String param = "parternID=" + parnerid + "&digest=" + URLEncoder.encode(digest) + "&msgId=" + URLEncoder.encode(digest) + "&serviceType=" + serviceType + "&bizData=" + bizData;
     String result = PushtoWQ.sendPost_ht(url, param);
     
     String mailNo = "";
     String markDestination = "";
     String billProvideSiteName = "";
     String billProvideSiteCode = "";
     if (result.indexOf("SUCCESS") != -1) {
       try {
         Document document = DocumentHelper.parseText(result);
         Element employees = document.getRootElement();
         
         for (Iterator<Element> i = employees.elementIterator(); i.hasNext(); ) {
           Element employee = i.next();
           for (Iterator<Element> j = employee.elementIterator(); j.hasNext(); ) {
             Element node = j.next();
 
             
             if (node.getName().equals("mailNo")) {
               mailNo = node.getText();
             }
             if (node.getName().equals("markDestination")) {
               markDestination = node.getText();
             }
             if (node.getName().equals("billProvideSiteName")) {
               billProvideSiteName = node.getText();
             }
             if (node.getName().equals("billProvideSiteCode")) {
               billProvideSiteCode = node.getText();
             }
           } 
         } 
       } catch (DocumentException e) {
         e.printStackTrace();
       } 
     }
     orderMail.setMailNo(mailNo);
     orderMail.setMarkDestination(markDestination);
     orderMail.setBillProvideSiteName(billProvideSiteName);
     orderMail.setBillProvideSiteCode(billProvideSiteCode);
   }
 
   
   public static String getHTOMailNo(String xml) {
     String url = "http://ebill.ns.800best.com/ems/api/process";
     String parnerid = "310038_0010";
     String bizData = xml;
     String Key = "RykhQeaTTlTK";
     String serviceType = "BillPrintRequest";
     
     String digest = String.valueOf(bizData) + Key;
     
     try {
       digest = MD5Util.EncoderByMd5(digest);
     } catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     String param = "parternID=" + parnerid + "&digest=" + URLEncoder.encode(digest) + "&msgId=" + URLEncoder.encode(digest) + "&serviceType=" + serviceType + "&bizData=" + bizData;
     String result = PushtoWQ.sendPost_ht(url, param);
     System.out.println(result);
     
     String mailNo = "";
     String markDestination = "";
     String billProvideSiteName = "";
     String billProvideSiteCode = "";
     if (result.indexOf("SUCCESS") != -1) {
       try {
         Document document = DocumentHelper.parseText(result);
         Element employees = document.getRootElement();
         
         for (Iterator<Element> i = employees.elementIterator(); i.hasNext(); ) {
           Element employee = i.next();
           for (Iterator<Element> j = employee.elementIterator(); j.hasNext(); ) {
             Element node = j.next();
 
             
             if (node.getName().equals("mailNo")) {
               mailNo = node.getText();
             }
             if (node.getName().equals("markDestination")) {
               markDestination = node.getText();
             }
             if (node.getName().equals("pkgCode")) {
               billProvideSiteName = node.getText();
             }
             if (node.getName().equals("sortingCode")) {
               billProvideSiteCode = String.valueOf(markDestination) + node.getText();
             }
           } 
         } 
         return mailNo;
       } catch (DocumentException e) {
         e.printStackTrace();
       } 
     }
     return "错误：匹配不成功可能没有单号了！";
   }
 
 
 
   
   public static void hqmd3(String xml, OrderBonded orderBonded) {
     String url = "http://ebill.ns.800best.com/ems/api/process";
     String parnerid = "310038_0025";
     String bizData = xml;
     String Key = "7Egv60MfCwgs";
     String serviceType = "BillPrintRequest";
     
     String digest = String.valueOf(bizData) + Key;
     
     try {
       digest = MD5Util.EncoderByMd5(digest);
     } catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     String param = "parternID=" + parnerid + "&digest=" + URLEncoder.encode(digest) + "&msgId=" + URLEncoder.encode(digest) + "&serviceType=" + serviceType + "&bizData=" + bizData;
     String result = PushtoWQ.sendPost_ht(url, param);
     System.out.println(result);
     
     String mailNo = "";
     String markDestination = "";
     String billProvideSiteName = "";
     String billProvideSiteCode = "";
     if (result.indexOf("SUCCESS") != -1) {
       try {
         Document document = DocumentHelper.parseText(result);
         Element employees = document.getRootElement();
         
         for (Iterator<Element> i = employees.elementIterator(); i.hasNext(); ) {
           Element employee = i.next();
           for (Iterator<Element> j = employee.elementIterator(); j.hasNext(); ) {
             Element node = j.next();
 
             
             if (node.getName().equals("mailNo")) {
               mailNo = node.getText();
             }
             if (node.getName().equals("markDestination")) {
               markDestination = node.getText();
             }
             if (node.getName().equals("pkgCode")) {
               billProvideSiteName = node.getText();
             }
             if (node.getName().equals("sortingCode")) {
               billProvideSiteCode = String.valueOf(markDestination) + node.getText();
             }
           } 
         } 
       } catch (DocumentException e) {
         e.printStackTrace();
       } 
     }
     orderBonded.setMailNo(mailNo);
     orderBonded.setMarkDestination(markDestination);
     orderBonded.setBillProvideSiteName(billProvideSiteName);
     orderBonded.setBillProvideSiteCode(billProvideSiteCode);
   }
 }


