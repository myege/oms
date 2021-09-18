 package com.what21.test;
 
 import com.zt.kjybd.PushtoWQ;
 import java.io.File;
 import java.io.IOException;
 import java.io.StringWriter;
 import java.net.URLEncoder;
 import org.jdom.Document;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
 public class zwyjjmj
 {
   public static void main(String[] args) {
     String sy = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIBdBExjHcoXZ2TjDm9RxrzwemCE9vvI4g4VOS+jj0j27+iIozSAlZzWu2fhYWkimwyoeymzGmDlWs2ktpS18AyUDP1xq3mjHnpiw14puAg9j3gT/tw8rPg0dOj35Gyl/LrsBjtmMkNIEHKNg+PCvxnPyGDOE189exUGLNMK2+x/AgMBAAECgYBx9iMCzlx8NxsMbqk6G5gd1kJPvryn/Uj0x9LlgIiNdu63u9dpnV82x03KhEY/WQHe0xXFh8U7kWR0fm+6x2w97A7ft8G/b1DGcoy9rgctSd/6vhj+JgzG9MDo2pCagiF1JJCYVq2oxbbhFyYE4mzcQhrjn8akei2HVh0zWrJ3gQJBAPmtaOuwcVSgxCHckX+4hxFph6TNImafJ/WLPjrhKpfjOYzLMoP/2brkSkQ58mp6Ge4G3RAI3mIQDVIkRZXs/WkCQQCDnSlHbRRpB6pXfC6BE2mVr5S3DbLxANacJ5eKLe6EPoCbOBd1gyATtBCSMHDwJOd1YgfUzfplADsKxzekkRWnAkAii+f0lJlZyFTbCpXnQjUOJ7fpCak04di5lOarZvRpx7ryzDrVINWTknrkLdBpAzQLR0/XJCMBFo+BShuGvx/hAkBMY62PzVnpVA+kU2lSdYf030H7tTHE++2UGM613s7vZq+SZ84F4KN/jCWKFB+Y4nKNCObutta8RJQAP3ymQadFAkEA8A7x0wZjD5CJc9unP7NBs8eH3j1jn2EjyASwsHqGmpAymqLh2ZxXo+zqZxQKBJx7MSDxA2PS9FnN5a7z+Oo9Ng==";
     String url = "http://pms.sinoair.com/api/webservice/hz/returnXML";
     
     String path = "E:\\sjz";
     File file = new File(path);
     File[] tempList = file.listFiles();
     for (int i = 0; i < tempList.length; i++) {
       
       if (tempList[i].isFile()) {
         System.out.println("文     件：" + tempList[i]);
         System.out.println("数量：" + i);
         String contentData = XmlToString(tempList[i].toString());
         
         String parm = "content=" + URLEncoder.encode(contentData) + "&sign=" + URLEncoder.encode(sy);
         System.out.println("parm：" + parm);
         
         String result = PushtoWQ.sendPost_zwyjmj(url, parm);
         System.out.println("result：" + result);
       } 
     } 
   }
 
   
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
 }


