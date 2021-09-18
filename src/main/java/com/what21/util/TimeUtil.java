 package com.what21.util;
 
 import java.text.SimpleDateFormat;
 import java.util.Date;
 public class TimeUtil
 {
   public static String defaultTime(String geshi, Date date) {
     SimpleDateFormat sdf = new SimpleDateFormat(geshi);
     String defaultTime = new String();
     if (date == null) {
       Date dtime = new Date();
       defaultTime = sdf.format(dtime);
     } else {
       defaultTime = sdf.format(date);
     } 
     
     return defaultTime;
   }
 }


