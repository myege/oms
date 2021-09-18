 package com.zt.kjybd;
 
 import java.util.Calendar;
 import java.util.Date;
 import java.util.GregorianCalendar;
 import java.util.HashMap;
 import java.util.Map;
 public class IDCardUtil
 {
   static final Map<Integer, String> zoneNum = new HashMap<>();
   static {
     zoneNum.put(Integer.valueOf(11), "北京");
     zoneNum.put(Integer.valueOf(12), "天津");
     zoneNum.put(Integer.valueOf(13), "河北");
     zoneNum.put(Integer.valueOf(14), "山西");
     zoneNum.put(Integer.valueOf(15), "内蒙古");
     zoneNum.put(Integer.valueOf(21), "辽宁");
     zoneNum.put(Integer.valueOf(22), "吉林");
     zoneNum.put(Integer.valueOf(23), "黑龙江");
     zoneNum.put(Integer.valueOf(31), "上海");
     zoneNum.put(Integer.valueOf(32), "江苏");
     zoneNum.put(Integer.valueOf(33), "浙江");
     zoneNum.put(Integer.valueOf(34), "安徽");
     zoneNum.put(Integer.valueOf(35), "福建");
     zoneNum.put(Integer.valueOf(36), "江西");
     zoneNum.put(Integer.valueOf(37), "山东");
     zoneNum.put(Integer.valueOf(41), "河南");
     zoneNum.put(Integer.valueOf(42), "湖北");
     zoneNum.put(Integer.valueOf(43), "湖南");
     zoneNum.put(Integer.valueOf(44), "广东");
     zoneNum.put(Integer.valueOf(45), "广西");
     zoneNum.put(Integer.valueOf(46), "海南");
     zoneNum.put(Integer.valueOf(50), "重庆");
     zoneNum.put(Integer.valueOf(51), "四川");
     zoneNum.put(Integer.valueOf(52), "贵州");
     zoneNum.put(Integer.valueOf(53), "云南");
     zoneNum.put(Integer.valueOf(54), "西藏");
     zoneNum.put(Integer.valueOf(61), "陕西");
     zoneNum.put(Integer.valueOf(62), "甘肃");
     zoneNum.put(Integer.valueOf(63), "青海");
     zoneNum.put(Integer.valueOf(64), "新疆");
     zoneNum.put(Integer.valueOf(71), "台湾");
     zoneNum.put(Integer.valueOf(81), "香港");
     zoneNum.put(Integer.valueOf(82), "澳门");
     zoneNum.put(Integer.valueOf(91), "外国");
   }
   
   static final int[] PARITYBIT = new int[] { 49, 48, 88, 57, 56, 55, 54, 53, 52, 51, 50 };
   static final int[] POWER_LIST = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
 
 
 
   
   public static boolean isIDCard(String certNo) {
     if (certNo == null || (certNo.length() != 15 && certNo.length() != 18))
       return false; 
     char[] cs = certNo.toUpperCase().toCharArray();
     
     int power = 0;
     for (int i = 0; i < cs.length && (
       i != cs.length - 1 || cs[i] != 'X'); i++) {
       
       if (cs[i] < '0' || cs[i] > '9')
         return false; 
       if (i < cs.length - 1) {
         power += (cs[i] - 48) * POWER_LIST[i];
       }
     } 
 
     
     if (!zoneNum.containsKey(Integer.valueOf(certNo.substring(0, 2)))) {
       return false;
     }
 
     
     String year = (certNo.length() == 15) ? (String.valueOf(getIdcardCalendar()) + 
       certNo.substring(6, 8)) : certNo.substring(6, 10);
     
     int iyear = Integer.parseInt(year);
     if (iyear < 1900 || iyear > Calendar.getInstance().get(1)) {
       return false;
     }
     
     String month = (certNo.length() == 15) ? certNo.substring(8, 10) : certNo
       .substring(10, 12);
     int imonth = Integer.parseInt(month);
     if (imonth < 1 || imonth > 12) {
       return false;
     }
 
     
     String day = (certNo.length() == 15) ? certNo.substring(10, 12) : certNo
       .substring(12, 14);
     int iday = Integer.parseInt(day);
     if (iday < 1 || iday > 31) {
       return false;
     }
     
     if (certNo.length() == 15)
       return true; 
     return (cs[cs.length - 1] == PARITYBIT[power % 11]);
   }
   
   private static int getIdcardCalendar() {
     GregorianCalendar curDay = new GregorianCalendar();
     int curYear = curDay.get(1);
     int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));
     return year2bit;
   }
 
   
   public static boolean CheckCertificateNo(String certificateNo) {
     if (certificateNo.length() == 15)
       return false; 
     if (certificateNo.length() == 18) {
       String address = certificateNo.substring(0, 6);
       String birthday = certificateNo.substring(6, 14);
       String sequenceCode = certificateNo.substring(14, 17);
       String checkCode = certificateNo.substring(17);
 
       
       String[] provinceArray = { "11:北京", "12:天津", "13:河北", "14:山西", 
           "15:内蒙古", "21:辽宁", "22:吉林", "23:黑龙江", "31:上海", "32:江苏", 
           "33:浙江", "34:安徽", "35:福建", "36:江西", "37:山东", "41:河南", 
           "42:湖北 ", "43:湖南", "44:广东", "45:广西", "46:海南", "50:重庆", 
           "51:四川", "52:贵州", "53:云南", "54:西藏 ", "61:陕西", "62:甘肃", 
           "63:青海", "64:宁夏", "65:新疆", "71:台湾", "81:香港", "82:澳门", 
           "91:国外" };
       
       boolean valideAddress = false;
       
       for (int i = 0; i < provinceArray.length; i++) {
         String provinceKey = provinceArray[i].split(":")[0];
         if (provinceKey.equals(address.substring(0, 2))) {
           valideAddress = true;
         }
       } 
       
       if (valideAddress) {
         String year = birthday.substring(0, 4);
         String month = birthday.substring(4, 6);
         String day = birthday.substring(6);
         
         Date tempDate = new Date(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
         
         if (tempDate.getYear() != Integer.parseInt(year) || 
           tempDate.getMonth() != Integer.parseInt(month) - 1 || tempDate
           .getDate() != Integer.parseInt(day)) {
           
           System.err.println("身份证号码无效，请重新输入！！！");
         }
         else {
           
           int[] weightedFactors = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
           int[] valideCode = { 1, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
           int sum = 0;
           
           String[] certificateNoArray = new String[certificateNo.length()]; int j;
           for (j = 0; j < certificateNo.length(); j++) {
             certificateNoArray[j] = String.valueOf(certificateNo.charAt(j));
           }
           
           if ("x".equals(certificateNoArray[17].toLowerCase())) {
             certificateNoArray[17] = "10";
           }
           
           for (j = 0; j < 17; j++) {
             sum += weightedFactors[j] * Integer.parseInt(certificateNoArray[j]);
           }
           
           int valCodePosition = sum % 11;
           
           if (Integer.parseInt(certificateNoArray[17]) == valideCode[valCodePosition]) {
             return true;
           }
           return false;
         } 
       } else {
         
         return false;
       } 
     } else {
       return false;
     } 
     return true;
   }
 
   
   public static void main(String[] args) {
     boolean mark = CheckCertificateNo("310226199210124318");
     System.out.println(mark);
     if (mark)
       System.out.println("111111111"); 
   }
 }


