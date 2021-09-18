 package com.zt.kjybd;
 
 import com.rabbitmq.client.AMQP;
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
   
   public static void rabbitmq(String agr) throws IOException, TimeoutException {
     ConnectionFactory connectionFactory = new ConnectionFactory();
     connectionFactory.setHost(url);
     connectionFactory.setPort(port.intValue());
     connectionFactory.setUsername(username);
     connectionFactory.setPassword(password);
     Connection connection = connectionFactory.newConnection();
     Channel channel = connection.createChannel();
 
     
     AMQP.Queue.DeclareOk declareOk = channel.queueDeclare("DXPENT0000019448", false, false, false, null);
     String message = agr;
     
     channel.basicPublish("", "test", null, message.getBytes("UTF-8"));
     
     channel.close();
     connection.close();
   }
 }


