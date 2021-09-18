 package com.what21.mq;
 
 import com.rabbitmq.client.Connection;
 import com.rabbitmq.client.ConnectionFactory;
 public class ConnectionUtil
 {
   public static Connection getConnection() throws Exception {
     ConnectionFactory factory = new ConnectionFactory();
 
     
     factory.setHost("47.98.144.242");
     
     factory.setPort(5672);
     
     factory.setUsername("zhuhai");
     factory.setPassword("zhuhai");
     factory.setVirtualHost("zhuhai");
     
     Connection connection = factory.newConnection();
     return connection;
   }
 }


