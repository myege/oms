 package com.zt.kjybd;
 
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;
 public class DBHelper
 {
   public static final String url = "jdbc:mysql://114.55.97.43:3306/wmstest?useUnicode=true&characterEncoding=utf-8";
   public static final String name = "com.mysql.jdbc.Driver";
   public static final String user = "root";
   public static final String password = "ztgyl2016";
   public Connection conn = null;
   public PreparedStatement pst = null;
   
   public DBHelper(String sql) {
     try {
       Class.forName("com.mysql.jdbc.Driver");
       this.conn = DriverManager.getConnection("jdbc:mysql://114.55.97.43:3306/wmstest?useUnicode=true&characterEncoding=utf-8", "root", "ztgyl2016");
       this.pst = this.conn.prepareStatement(sql);
     } catch (Exception e) {
       e.printStackTrace();
     } 
   }
   
   public void close() {
     try {
       this.conn.close();
       this.pst.close();
     } catch (SQLException e) {
       e.printStackTrace();
     } 
   }
 }


