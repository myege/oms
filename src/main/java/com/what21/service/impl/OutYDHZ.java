 package com.what21.service.impl;
 
 import com.rabbitmq.client.AMQP;
 import com.rabbitmq.client.Channel;
 import com.rabbitmq.client.Connection;
 import com.rabbitmq.client.ConnectionFactory;
 import com.rabbitmq.client.Consumer;
 import com.rabbitmq.client.DefaultConsumer;
 import com.rabbitmq.client.Envelope;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.io.StringReader;
 import java.util.List;
 import org.dom4j.Document;
 import org.dom4j.DocumentException;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
 import org.springframework.context.ApplicationEvent;
 import org.springframework.context.ApplicationListener;
 import org.springframework.context.event.ContextRefreshedEvent;
import org.apache.commons.codec.binary.Base64;
 
 
 
 public class OutYDHZ
   implements ApplicationListener<ContextRefreshedEvent>
 {
   public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
     for (int i = 0; i < 10000; i++) {
       System.out.println("顺丰收到回执了");
     }
     String url = "115.236.37.155";
     Integer port = Integer.valueOf(5672);
     String username = "EPORT_HZ";
     String password = "password";
     try {
       ConnectionFactory connectionFactory = new ConnectionFactory();
       connectionFactory.setHost(url);
       connectionFactory.setPort(port.intValue());
       connectionFactory.setUsername(username);
       connectionFactory.setPassword(password);
       Connection connection = connectionFactory.newConnection();
       Channel channel = connection.createChannel();
       
       AMQP.Queue.DeclareOk test = channel.queueDeclare("DXPENT0000019672_HZ", false, false, false, null);
 
       
       DefaultConsumer defaultConsumer = new DefaultConsumer(channel)
         {
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
             System.out.println("顺丰收到回执了");
             
             StringReader sr = new StringReader(new String(body, "UTF-8"));
             try {
               Document document = DocumentHelper.parseText(new String(body, "UTF-8"));
               Element rootElement = document.getRootElement();
               Element element = rootElement.element("Data");
               String text = element.getText();
               byte[] encode = Base64.decodeBase64(text);
               SAXReader saxReader = new SAXReader();
               Document rten = saxReader.read(new ByteArrayInputStream(encode));
               System.err.println(rten.asXML());
               Element data = rten.getRootElement();
               List<Element> ArrivalReturns = data.elements("ArrivalReturn");
               for (Element ArrivalReturn : ArrivalReturns) {
                 Element returnInfo = ArrivalReturn.element("returnInfo");
                 Element element1 = ArrivalReturn.element("returnStatus");
               
               }
             
             }
             catch (DocumentException e) {
               e.printStackTrace();
             } 
           }
         };
       
       channel.basicConsume("DXPENT0000019672_HZ", true, (Consumer)defaultConsumer);
     } catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


