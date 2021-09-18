 package com.what21.test;
 
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import com.zt.ewtp.hcszqgpost;
 
 public class ApolloSdkTest
 {
   public static void main(String[] args) {
     String personPriKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJGKWUhlG6ZAWyDyNrcadpEL7yHbA3et4I+jsYnOrvPuJObO/8sqaOeocLazowgI4hu1SwuTVJijyBEkAV78k13coWzg05rC2dMqhd/HO/Q8kvrQ8tnLWnOpM8UomkQzVpl6vI6qY3DgjzHym47rcqBwRcDH8YNKkidJyi4Jq7mFAgMBAAECgYAICN048+v815ywSu7eWOWmyEPhWJfuaXj2NRE+DFz4NnNvEopoiuTTtSbTz0L21Ja29OE5uF55NsF6wMQanFL+TCI3lHC6RidPnHM3b0WAgnO7KrGoRadJPDily1kz/Mcdk7Hbm7wMzOoiNsF+Vx0W97XpR5bOXLMFvRWuVIREWQJBANPcAfS7nuzmnjUlrSUROM+X5dKzTtMAMhpimfdTay0md6MMAA50Dob0XNwxXZKGVqesvqlKYtLjQ/af86CYukcCQQCv3RIlYqclyyzEfqGwHFApLaE0P52y5m8pE4m5fBqwNejBOpVKBbSkrtj8rgrP+iahnnUQowa/MQzsLvCGm8fTAkA6vNKmAaasU7wlTA7XKhEwLRMA+cmu4m0Bt7isQ6qBWBrvT5Jsb2zfqbxdKCqjRy+bSV+LHB6ODYh4RhyLuAxdAkEAqIu9imsEOuqAW0np+HkKMozyxLp7jyveOYXqJwTUJzFjg118GtdhC7wvZev/nNfoElllZK56Ft8RvYI3Lys51QJBAIiyTKHC55Y4aFeRYJ4Jlt7MOVsvFr0/+FNCOUK10C9Z50+ybVbwGG//bZJT8yb60pIZF6bcJ7fuvQTLalKfqAQ=";
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String json = "{\"InventoryList\":[{\"barCode\":\"11111111111\",\"country\":\"502\",\"currency\":\"142\",\"gcode\":\"2106909090\",\"gmodel\":\"4|3|米糖浆31.5%、糖23%、植物油混合物5%、牛奶蛋白浓缩物13.4%、玉米麦芽糖糊精6%、大豆分离蛋白6%、短链低聚果糖6%、天然和人造香料1%、中链甘油三酯1.5%、大豆卵磷脂1%|400g/罐|雅培\",\"gname\":\"美国雅培小安素非转基因婴幼儿儿童营养粉草莓味400g\",\"gnum\":1,\"hsCode\":\"2106909090\",\"itemNo\":\"KC070074669580\",\"itemRecordNo\":141,\"itemSku\":\"SL070074669588E\",\"price\":77.91,\"qty\":1,\"qty1\":\"0.400\",\"totalPrice\":77.91,\"unit\":\"122\",\"unit1\":\"035\"}],\"agentCode\":\"33189630GS\",\"agentName\":\"义乌齐越电子商务有限公司\",\"appid\":\"0ecf02fc6ed34aaab73a2db5d1944342\",\"areaCode\":\"3318W60026\",\"areaName\":\"义乌凯昌进出口有限公司\",\"assureCode\":\"33189630GS\",\"commitTime\":\"20210425134901\",\"copNo\":\"YW12106E87OEI412F4\",\"country\":\"142\",\"customDeclco\":\"\",\"emsNo\":\"L2923B19A002\",\"grossWeight\":\"0.500\",\"insuredFee\":\"0\",\"logisticsCode\":\"3120980105\",\"logisticsName\":\"上海韵达货运有限公司\",\"logisticsNo\":\"7700199472125\",\"netWeight\":\"0.400\",\"orderNo\":\"1743772250699840443\",\"portCode\":\"2923\",\"trafMode\":\"8\",\"version\":\"1.0.0\"}";
 
 
 
     
     try {
       String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), json);
       System.out.println("param=" + param);
       
       String url = "http://apollo.jieztech.com/aoa/api/aoa/logistics/logisticsOrderorderCallBack";
       
       String res = hcszqgpost.ZYPost(url, param);
       System.out.println("res=" + res);
     } catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


