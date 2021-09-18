 package com.zt.kjybd;
 
 import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;
 
 
 public class TestWebService
 {
   public static void main(String[] args) {
     String xml = "<logisticsEventsRequest><logisticsEvent><eventHeader><eventType> LOGISTICS_CUSTOMS_DECLARATION_INFO </eventType><eventTime>2016-1-12 14:14:07</eventTime><eventSource>FSEWL</eventSource><eventTarget>ZDZK</eventTarget></eventHeader><eventBody><logisticsDetail><taobaoLogisticsId>WEB01928410611191</taobaoLogisticsId><logisticsServiceType>warehouseToDoor</logisticsServiceType><orderTotalFee>32000</orderTotalFee><eCommerceCode>DS15042907</eCommerceCode><eCommerceName>费舍尔国际贸易（香港）有限公司</eCommerceName><accountBookNo>W29916000069</accountBookNo><weight>1000</weight><transportNumber>CZ452</transportNumber><goodsList><orderGoods><itemName>爱他美 2+</itemName><itemId>ZY1901101000T1037444</itemId><goodsItemNo>ZY1901101000T1037444</goodsItemNo><count>4</count><price>8000</price></orderGoods></goodsList><innerTms>STO</innerTms><innerMailNo>220263748768</innerMailNo><totalMailNo>784-16523776</totalMailNo><occurTime>2016-1-12 14:14:07</occurTime><logisticsRemark>无</logisticsRemark></logisticsDetail><receiverDetail><code>fisher</code><name>黄若琳</name><phone></phone><mobile>18689089876</mobile><email>jiyun@jiyun.com</email><province>四川省</province><city>成都市</city><district>武侯区</district><streetAddress>簇桥簇锦横街93号龙井苑一区</streetAddress><zipCode>610043</zipCode><wangwangId>587696</wangwangId></receiverDetail></eventBody></logisticsEvent></logisticsEventsRequest>";
     String signKey = "123456";
     
     byte[] md5Value = MD5(String.valueOf(xml) + signKey);
     
     String base64Value = getBase64(md5Value);
   }
 
 
   
   public static String getBase64(byte[] bytes) {
     String s = null;
     if (bytes != null) {
       s = Base64.encodeBase64String(bytes);
     }
     return s;
   }
   
   public static final String MD5toHexDigits(byte[] bytes) {
     char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
         'A', 'B', 'C', 'D', 'E', 'F' };
     try {
       int j = bytes.length;
       char[] str = new char[j * 2];
       int k = 0;
       for (int i = 0; i < j; i++) {
         byte byte0 = bytes[i];
         str[k++] = hexDigits[byte0 >>> 4 & 0xF];
         str[k++] = hexDigits[byte0 & 0xF];
       } 
       return new String(str);
     } catch (Exception e) {
       e.printStackTrace();
       return null;
     } 
   }
 
   
   public static final byte[] MD5(String s) {
     try {
       byte[] btInput = s.getBytes("utf-8");
       MessageDigest mdInst = MessageDigest.getInstance("MD5");
       mdInst.update(btInput);
       byte[] md = mdInst.digest();
       return md;
     } catch (Exception e) {
       e.printStackTrace();
       return null;
     } 
   }
 }


