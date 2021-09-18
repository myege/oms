 package com.zt.kjybd;
 
 import java.util.regex.Pattern;
 public class CheckPhone
 {
   private static final String PHONE_CALL_PATTERN = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$";
   private static final String CHINA_TELECOM_PATTERN = "(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)";
   private static final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";
   private static final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";
   private static final String PHONE_PATTERN = (new StringBuilder(300)).append("(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)")
     .append("|")
     .append("(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)")
     .append("|")
     .append("(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)")
     .toString();
 
 
 
   
   private static final String PHONE_TEL_PATTERN = (new StringBuilder(350)).append(PHONE_PATTERN)
     .append("|")
     .append("(")
     .append("^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$")
     .append(")")
     .toString();
   
   private static final String MULTI_PHONE_TEL_PATTERN = "^(?:(?:(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)[,\\s、])+)?(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)$";
   
   public static boolean checkMultiPhone(String input) {
     return match("^(?:(?:(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)[,\\s、])+)?(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)$", input);
   }
   
   public static boolean isPhone(String input) {
     return match(PHONE_PATTERN, input);
   }
   
   public static boolean isPhoneOrTel(String input) {
     System.out.println(PHONE_TEL_PATTERN);
     return match(PHONE_TEL_PATTERN, input);
   }
   
   public static boolean isPhoneCallNum(String str) {
     return match("^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$", str);
   }
   
   public static boolean isChinaTelecomPhoneNum(String str) {
     return match("(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)", str);
   }
   
   public static boolean isChinaUnicomPhoneNum(String str) {
     return match("(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)", str);
   }
   
   public static boolean isChinaMobilePhoneNum(String str) {
     return match("(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)", str);
   }
   
   private static boolean match(String regex, String input) {
     return Pattern.matches(regex, input);
   }
   
   public static void main(String[] args) {
     System.out.println(isPhone("13067709690"));
   }
 }


