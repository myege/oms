 package com.what21.mq;
 
 import com.rabbitmq.client.Channel;
 import com.rabbitmq.client.Connection;
 
 
 
 public class Send
 {
   private static final String QUEUE_NAME = "Lcqg";
   
   public static void sendmq(String xml) throws Exception {
     Connection connection = ConnectionUtil.getConnection();
     
     Channel channel = connection.createChannel();
     
     channel.queueDeclare("Lcqg", false, false, false, null);
     channel.basicPublish("", "Lcqg", null, xml.getBytes());
     
     channel.close();
     connection.close();
   }
 
   
   public static void main(String[] args) throws Exception {
     Connection connection = ConnectionUtil.getConnection();
     
     Channel channel = connection.createChannel();
     
     channel.queueDeclare("Lcqg", false, false, false, null);
     for (int i = 11; i <= 900; i++) {
       String message = String.valueOf(i) + "===hello world在资质";
 
 
       
       channel.basicPublish("", "Lcqg", null, message.getBytes());
       System.out.println("生产者已发送：" + message);
     } 
     
     channel.close();
     connection.close();
   }
 }


