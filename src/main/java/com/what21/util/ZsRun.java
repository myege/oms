 package com.what21.util;
 
 public class ZsRun
   implements Runnable {
   private String str;
   private String userid;
   private int mesg;
   
   public ZsRun(String str, int mesg, String userid) {
     this.str = str;
     
     this.mesg = mesg;
     this.userid = userid;
   }
 
 
   
   public void run() {
     try {
       EncryptHG.sendGet(this.str, this.mesg, (new StringBuilder(String.valueOf(this.userid))).toString(), "123456", "123456");
     } catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 }


