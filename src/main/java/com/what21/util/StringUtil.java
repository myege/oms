 package com.what21.util;
 
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class StringUtil {
   public static int totalPage(int count, int rows) {
     int total = 0;
     if (count % rows != 0) {
       total = count / rows + 1;
     } else {
       total = count / rows;
     } 
     return total;
   }
   
   public static void main(String[] args) {
     int a = totalPage(21, 5);
   }
 
   
   public static Boolean isEmpty(String str) {
     if (str == null)
       return Boolean.valueOf(true); 
     if ("".equals(str.trim())) {
       return Boolean.valueOf(true);
     }
     return Boolean.valueOf(false);
   }
   
   public static Boolean isNotEmpty(String str) {
     if (str == null)
       return Boolean.valueOf(false); 
     if ("".equals(str.trim())) {
       return Boolean.valueOf(false);
     }
     return Boolean.valueOf(true);
   }
   
   public static String replaceSpecialStr(String str) {
     String repl = "";
     if (str != null) {
       Pattern p = Pattern.compile("\\s*|\t|\r|\n");
       Matcher m = p.matcher(str);
       repl = m.replaceAll("");
     } 
     return repl;
   }
 }


