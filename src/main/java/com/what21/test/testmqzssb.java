 package com.what21.test;
 
 import com.what21.mq.Send;
 import java.io.File;
 import java.io.IOException;
 import java.io.StringWriter;
 import org.jdom.Document;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
 
 public class testmqzssb {
   public static Document load(String url) {
     Document document = null;
     
     try {
       SAXBuilder reader = new SAXBuilder();
       document = reader.build(new File(url));
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return document;
   }
   public static String XmlToString(String url) {
     Document document = null;
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
 
 
 
   
   public static void main(String[] args) throws Exception {
     String url2 = "E:\\xml\\dd441.xml";
     String xml = XmlToString(url2);
     String name = "DXPENT0000453525";
     xml = String.valueOf(name) + "#####" + xml;
     System.out.println("xml=" + xml);
     Send.sendmq(xml);
   }
 }


