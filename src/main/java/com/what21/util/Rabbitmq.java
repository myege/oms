 package com.what21.util;
 
 import com.rabbitmq.client.Channel;
 import com.rabbitmq.client.Connection;
 import com.rabbitmq.client.ConnectionFactory;
 import java.io.IOException;
 import java.util.concurrent.TimeoutException;
 public class Rabbitmq
 {
   private static String url = "115.236.37.155";
   private static Integer port = Integer.valueOf(5672);
   private static String username = "EPORT_HZ";
   private static String password = "password";
   
   public static void rabbitmq(String agr, String str) throws IOException, TimeoutException {
     ConnectionFactory connectionFactory = new ConnectionFactory();
     connectionFactory.setHost(url);
     connectionFactory.setPort(port.intValue());
     connectionFactory.setUsername(username);
     connectionFactory.setPassword(password);
 
     
     ConnectionFactory factory = new ConnectionFactory();
     factory.setHost("115.236.37.155");
     factory.setPort(5672);
     factory.setUsername("EPORT_HZ");
     factory.setPassword("password");
     Connection connection = factory.newConnection();
     Channel channel = connection.createChannel();
     
     channel.queueDeclare(str, false, false, false, null);
     channel.basicPublish("", str, null, agr.getBytes("utf-8"));
     System.out.println(String.valueOf(str) + "生产了个" + agr);
     
     channel.close();
     connection.close();
   }
 }


