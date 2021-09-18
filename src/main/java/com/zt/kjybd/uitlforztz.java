 package com.zt.kjybd;
 
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 public class uitlforztz
 {
   public static boolean isNumeric(String str) {
     Pattern pattern = Pattern.compile("[0-9]*");
     Matcher isNum = pattern.matcher(str);
     if (!isNum.matches()) {
       return false;
     }
     return true;
   }
   
   public static void main(String[] args) {
     String strCode = "3550321";
     int fdStart = strCode.indexOf("55");
     if (fdStart == 0) {
       System.out.println(".{表示strCode是以ssss开头；}");
     } else if (fdStart == -1) {
       System.out.println(".{表s开头；}");
     } 
   }
 }


