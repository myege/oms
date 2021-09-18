 package com.zt.kjybd;
 
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
 
 
 public class Mysql
 {
   public static Connection getConnection() {
     Connection con = null;
     
     String driver = "com.mysql.jdbc.Driver";
     String url = "jdbc:mysql://127.0.0.1:3306/ztz?useUnicode=true&characterEncoding=utf-8";
     
     String user = "root";
     
     String password = "123456";
     
     try {
       Class.forName(driver);
       try {
         con = DriverManager.getConnection(url, user, password);
       } catch (SQLException e) {
         
         e.printStackTrace();
       } 
     } catch (ClassNotFoundException e) {
       
       e.printStackTrace();
     } 
     return con;
   }
   
   public static void main(String[] args) {}
 }


