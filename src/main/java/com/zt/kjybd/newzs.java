 package com.zt.kjybd;
 
 import java.util.Random;
 import javax.xml.namespace.QName;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
 
 
 public class newzs
 {
   public static String getRandomCharAndNumr(Integer length) {
     String str = "";
     Random random = new Random();
     for (int i = 0; i < length.intValue(); i++) {
       boolean b = random.nextBoolean();
       if (b) {
         
         str = String.valueOf(str) + (char)(65 + random.nextInt(26));
       } else {
         str = String.valueOf(str) + String.valueOf(random.nextInt(10));
       } 
     } 
     return str;
   }
 
 
   
   public static String pushzs(String xmlStr) {
     String result = "";
 
     
     try {
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceiveCebDeclare?wsdl";
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
 
 
       
       result = (String)call.invoke(new Object[] { xmlStr });
 
 
     
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
   
   public static String thzf(String str) {
     str = str.replaceAll(" ", "");
     str = str.replaceAll("/", "");
     str = str.replaceAll("\\+", "");
     str = str.replaceAll("\\-", "");
     str = str.replaceAll("  ", "");
     str = str.replaceAll("　", "");
     str = str.replaceAll(" ", "");
     return str;
   }
 
   
   public static String getChineseOrEnglishOrNumber(String str) {
     StringBuffer sbf = new StringBuffer();
     char[] charArray = str.toCharArray();
     for (int i = 0; i < charArray.length; i++) {
 
       
       if (charArray[i] >= '一' && charArray[i] <= '龻') {
         sbf.append(charArray[i]);
       } else if (charArray[i] >= 'A' && charArray[i] <= 'z') {
         sbf.append(charArray[i]);
       } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
         sbf.append(charArray[i]);
       } 
     } 
     return sbf.toString();
   }
 
 
   
   public static void main(String[] args) {
     String str = "广东 茂名 电白县   霞洞+镇下岭村       das3   33       !@##  asd『阿斯顿552";
     
     System.out.println(getChineseOrEnglishOrNumber(str));
   }
 }


