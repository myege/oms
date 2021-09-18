 package com.what21.action;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 
 
 
 @Controller
 @RequestMapping({"/GZZS"})
 public class GZZSReceiptAction
 {
   @RequestMapping({"GZZSReceipt"})
   public void GZZSReceipt(String clientid, String key, String messageType, HttpServletRequest request, HttpServletResponse response) throws Exception {
     BufferedReader reader = null;
     StringBuilder sb = new StringBuilder();
     try {
       reader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "utf-8"));
       String line = null;
       while ((line = reader.readLine()) != null) {
         sb.append(line);
       }
     } catch (IOException e) {
       e.printStackTrace();
     } finally {
       try {
         if (reader != null) {
           reader.close();
         }
       } catch (IOException iOException) {}
     } 
     Document parseText = DocumentHelper.parseText(sb.toString());
     String asXML = parseText.asXML();
     System.out.println(asXML);
     Element root = parseText.getRootElement();
     
     String Status = root.element("Status").getText();
     if (!Status.equals("S"))
     {
       
       String str = root.element("Notes").getText();
     }
     
     try {
       response.getWriter().write("success");
     } catch (IOException e) {
       e.printStackTrace();
     } 
   }
 }


