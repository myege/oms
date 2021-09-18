 package com.zt.kjybd;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.UnsupportedEncodingException;
 import java.net.HttpURLConnection;
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 import java.util.Iterator;
 import java.util.List;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.DocumentException;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
 
 public class stocx {
   public static Connection con;
   
   public static String url(String sURL) {
     URL l_url = null;
     try {
       l_url = new URL(sURL);
     } catch (MalformedURLException e) {
       
       e.printStackTrace();
     } 
     HttpURLConnection l_connection = null;
     InputStream l_urlStream = null;
     try {
       l_connection = (HttpURLConnection)l_url.openConnection();
       l_connection.connect();
       l_urlStream = l_connection.getInputStream();
     } catch (IOException e) {
       
       e.printStackTrace();
     } 
     BufferedReader l_reader = null;
     try {
       l_reader = new BufferedReader(new InputStreamReader(l_urlStream, "GBK"));
     } catch (UnsupportedEncodingException e1) {
       
       e1.printStackTrace();
     } 
     String sCurrentLine = "";
     String sTotalString = "";
     try {
       while ((sCurrentLine = l_reader.readLine()) != null)
       {
         sTotalString = String.valueOf(sTotalString) + sCurrentLine;
       }
     } catch (IOException e) {
       
       e.printStackTrace();
     } 
     
     return sTotalString;
   }
   
   public static Statement statement;
   
   public static void test(String xmlStr) throws Exception {
     SAXReader reader = new SAXReader();
     
     Document document = DocumentHelper.parseText(xmlStr);
 
 
 
     
     Element root = document.getRootElement();
     
     listNodes(root);
   }
 
   
   public static Statement statement2;
   
   public static void listNodes(Element node) {
     List<Attribute> list = node.attributes();
     
     for (Attribute attribute : list);
 
 
     
     node.getTextTrim().equals("");
 
 
 
     
     Iterator<Element> iterator = node.elementIterator();
     while (iterator.hasNext()) {
       Element e = iterator.next();
       listNodes(e);
     } 
   }
 
 
   
   public static void parserXml(String fileName) {
     try {
       Document document = DocumentHelper.parseText(fileName);
       
       Element employees = document.getRootElement();
       String djsj = "";
       String qssj = "";
       String bzsj = "";
       String qsmemo = "";
       String billcode = "";
       String djmemo = "";
       String bzmemo = "";
       String bz = "1";
       String bz2 = "1";
       String bz3 = "1";
       for (Iterator<Element> i = employees.elementIterator(); i.hasNext(); ) {
         Element employee = i.next();
         for (Iterator<Element> j = employee.elementIterator(); j.hasNext(); ) {
           Element node = j.next();
           
           if (node.getName().equals("billcode")) {
             billcode = node.getText();
             qsmemo = bzmemo;
             
             String sql = "";
             if (!djsj.equals("")) {
               if (bz3.equals("2")) {
                 sql = "UPDATE t_waybill set acceptTime='" + djsj + "',acceptState='" + djmemo + "',endTime='" + qssj + "',endState='" + qsmemo + "',isSign='1' where expressNumber='" + billcode + "'";
               } else {
                 sql = "UPDATE t_waybill set acceptTime='" + djsj + "',acceptState='" + djmemo + "',endTime='" + bzsj + "',endState='" + bzmemo + "' where expressNumber='" + billcode + "'";
               } 
               djsj = ""; djmemo = ""; qssj = ""; qsmemo = "";
               try {
                 statement2.executeUpdate(sql);
               } catch (SQLException e) {
                 
                 e.printStackTrace();
               } 
             } 
             bz = "1";
             bz2 = "1";
             bz3 = "1";
           } 
           for (Iterator<Element> k = node.elementIterator(); k.hasNext(); ) {
             Element node2 = k.next();
             
             if (node2.getName().equals("memo")) {
               bzmemo = node2.getText();
               if (bz.equals("1")) {
                 djmemo = bzmemo;
                 bz = "2";
               } 
             } 
             
             if (node2.getName().equals("time")) {
               bzsj = node2.getText();
               if (bz2.equals("1")) {
                 djsj = bzsj;
                 bz2 = "2";
               } 
             } 
             
             if (node2.getName().equals("scantype") && node2.getText().equals("签收")) {
               qssj = bzsj;
               bz3 = "2";
             }
           
           }
         
         } 
       } 
     } catch (DocumentException documentException) {}
   }
 
 
 
   
   public static void dbcon2() {
     String urlString = "http://222.66.109.133/track.aspx?billcode=";
 
     
     String driver = "com.mysql.jdbc.Driver";
 
     
     String url = "jdbc:mysql://114.55.105.214:3306/ztz-zhengshi?characterEncoding=UTF-8";
     
     String user = "root";
     
     String password = "123456";
 
     
     try {
       Class.forName(driver);
       
       con = DriverManager.getConnection(url, user, password);
       if (!con.isClosed())
       {
         
         statement = con.createStatement(); } 
       statement2 = con.createStatement();
       
       String sql = "SELECT * from t_waybill where  issign<>'1'  ORDER BY expressNumber";
       
       ResultSet rs = statement.executeQuery(sql);
       
       while (rs.next()) {
         urlString = String.valueOf(urlString) + rs.getString("expressNumber") + ",";
         parserXml(url(urlString));
         urlString = "http://222.66.109.133/track.aspx?billcode=";
       } 
 
 
       
       con.close();
     } catch (ClassNotFoundException e) {
 
       
       e.printStackTrace();
     } catch (SQLException e) {
       
       e.printStackTrace();
     } catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
   
   public static void main(String[] args) {
     String data = "billcode=221031124100,221031124101,221031124102,221031124009";
     String url = "http://222.66.109.133/track.aspx?" + data;
     dbcon2();
   }
 }


