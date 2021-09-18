 package com.what21.listener;
 
 import javax.servlet.http.HttpSessionEvent;
 import javax.servlet.http.HttpSessionListener;
 
 public class OnlineCounterListener implements HttpSessionListener {
   public static long online = 0L;
 
   
   public void sessionCreated(HttpSessionEvent arg0) {
     online++;
   }
 
   
   public void sessionDestroyed(HttpSessionEvent arg0) {
     online--;
   }
 }


