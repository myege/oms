 package com.what21.util;
 
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Random;
 
 public class timenew
 {
   public static String befortime() {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Date date = new Date();
     Calendar calendar = Calendar.getInstance();
     calendar.setTime(date);
     calendar.add(5, -1);
     date = calendar.getTime();
     
     Random rndHour = new Random();
     int hour = rndHour.nextInt(23);
     Random rndMinute = new Random();
     int minute = rndMinute.nextInt(60);
     Random rndSecond = new Random();
     int second = rndSecond.nextInt(60);
     if (hour < 6) {
       hour = 7;
     }
     
     return String.valueOf(sdf.format(date)) + " " + cp(hour) + ":" + cp(minute) + ":" + cp(second);
   }
   
   private static String cp(int num) {
     String Num = (new StringBuilder(String.valueOf(num))).toString();
     if (Num.length() == 1) {
       return "0" + Num;
     }
     return Num;
   }
   
   public static String newtime() {
     SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
     
     return df.format(new Date());
   }
   public static String newtime2() {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
 
     
     return String.valueOf(df.format(new Date())) + "T" + df2.format(new Date());
   }
   public static String newtime3() {
     SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
     
     return df.format(new Date());
   }
   
   public static String newtime4() {
     SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
     
     return df.format(new Date());
   }
   public static String newtime5() {
     SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
     
     return df.format(new Date());
   }
   
   public static Date gettime(String tt) throws Exception {
     SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
     
     return df.parse(tt);
   }
 
   
   public static Date gettime2(String tt) throws Exception {
     SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
     
     return df.parse(tt);
   }
   
   public static String dateToStamp(String s) throws ParseException {
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date = simpleDateFormat.parse(s);
     long ts = date.getTime();
     String res = String.valueOf(ts);
     System.out.println(String.valueOf(res) + "---" + s);
     return res;
   }
 
 
 
   
   public static void main(String[] args) {
     System.out.println(newtime4());
   }
 }


