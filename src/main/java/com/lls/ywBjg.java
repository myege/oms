 package com.lls;
 
 import com.alibaba.fastjson.JSONObject;
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import com.what21.util.timenew;
 import com.zt.ewtp.hcszqgpost;
 public class ywBjg
 {
   public static void ywbjggeimail() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("bagId", "101");
     jsonObject.put("orderNo ", "5645646545645");
     jsonObject.put("userName", "hi先申");
     jsonObject.put("tel", "15988108696");
     jsonObject.put("postNumber ", Integer.valueOf(8229));
     jsonObject.put("city", "hangzhou");
     jsonObject.put("district", Integer.valueOf(11111));
     jsonObject.put("address ", Integer.valueOf(8229));
     jsonObject.put("customsId", "hangzhou");
     jsonObject.put("sendUser", Integer.valueOf(11111));
     jsonObject.put("sendTel ", Integer.valueOf(8229));
     jsonObject.put("sendProvince", "hangzhou");
     
     jsonObject.put("sendCity", "hangzhou");
     jsonObject.put("sendDistrict", Integer.valueOf(11111));
     jsonObject.put("sendAddress ", Integer.valueOf(8229));
     jsonObject.put("srcPort", "hangzhou");
 
     
     jsonObject.put("skuList", "hangzhou");
   }
   
   public static void statusCallBack() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("orderNo", "20202020210210");
     jsonObject.put("logisticsNo ", "5645646545645");
     jsonObject.put("status", "9");
     jsonObject.put("msg", "15988108696");
     
     jsonObject.put("appid", "6c0b855b55e34494a1d67dfb1809f34f");
     jsonObject.put("version", "1.0.0");
 
     
     jsonObject.put("commitTime", timenew.newtime());
     
     String PriKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL2vbMY24hWWPOe045622JwWc6vuuXJ419UCn6sFKhoq69iTkhN/uSmXumurOxs8CjVfR+f4uHXoRt5mHMYO5jeBe8SgfvfXARf5AVL6d41PxH9I/ncIaRfWUWACF5svRKpf+Egv6k1JRe2sCelml5m+WtMEm4Tzo4cUs/qjJDTvAgMBAAECgYAbLvi3r0XXMiCoVBQKuslwFQeerCeHcCn+LNIVADh4Z45FC2DzymoOu9/Lbk6aKJCN9YmohMxqmU8OsDOPbrMvIVRLdFHuz4K6yfPzjE7C1S8GrBAvxL7LpN4DTff3pzCsGSsqRbyYgtPOmZpyJQ0EQa2EP2xjFHYB696xyBsxgQJBAP+RnsXqSy7w1M2jhZ9raOoGl6KJiu2wVf1oSLwsQMG1TPe4QbL65ioSlw1hGchypzVbwi43KXVpYMT47sZkCoMCQQC+AVmETRDsfeIBiJwRR+nZsq3H2cdWEpYiyXfDpksv1zv+gxfod/Rwax9Nuz5npxej/LlC97JBy6Ns7wIVF5AlAkEAvNiy1RZixeXpqaaad4mb9co3RECUazyw3dawYHPmyfyZSjdaPNIPP6mK+rT0o1ytV81c+F+EgCEFA6facLi91wJADl8Rw0UE+65F0vHfRBtZX8L5C/236xW6z2THrz+7viGcgxKtU0MHdR1VH88C2Fo2Gow6AwuzKvDZKpslYC0JFQJAP9aIG+B56DJb7CEyz5tnKeepmJ9a/dLFbd+iivDYKZxsMqBmpX+kZix0+e7NCf3wT1DAWdjScdWyTdH+ojsAkw==";
     ApolloSdk apolloSdk = new ApolloSdk();
     
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
     
     String url = "http://gwfat.kldidi.com/aoa/ywTaobao/hc/statusCallBack";
     String res = hcszqgpost.ZYPost(url, param);
     System.out.println("res=" + res);
   }
 
 
   
   public static void orderCallBack() throws Exception {
     String json = "{\"InventoryList\":[{\"barCode\":\"11111111111\",\"country\":\"305\",\"currency\":\"142\",\"gcode\":\"3304990039\",\"gmodel\":\"卸妆用|500ml/瓶|BIODERMA/贝德玛\",\"gname\":\"法国贝德玛舒妍多效粉瓶卸妆水\",\"gnum\":1,\"hsCode\":\"3304990039\",\"itemNo\":\"BIO3401345935571\",\"itemRecordNo\":47,\"itemSku\":\"SL3401345935571\",\"price\":127.4,\"qty\":1,\"qty1\":\"0.500\",\"qty2\":\"1\",\"totalPrice\":127.4,\"unit\":\"142\",\"unit1\":\"035\",\"unit2\":\"011\"}],\"agentCode\":\"33189630GS\",\"agentName\":\"义乌齐越电子商务有限公司\",\"appid\":\"6c0b855b55e34494a1d67dfb1809f34f\",\"assureCode\":\"33189630GS\",\"commitTime\":\"20210329171143\",\"copNo\":\"YW1210AJI993MD0W90\",\"country\":\"142\",\"customDeclco\":\"\",\"emsNo\":\"L2923B19A002\",\"grossWeight\":\"0.600\",\"insuredFee\":\"0\",\"logisticsCode\":\"3120980110\",\"logisticsName\":\"申通快递有限公司\",\"logisticsNo\":\"123456\",\"netWeight\":\"0.500\",\"orderNo\":\"cs20210324\",\"portCode\":\"2923\",\"trafMode\":\"8\",\"version\":\"1.0.0\"}";
     String PriKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL2vbMY24hWWPOe045622JwWc6vuuXJ419UCn6sFKhoq69iTkhN/uSmXumurOxs8CjVfR+f4uHXoRt5mHMYO5jeBe8SgfvfXARf5AVL6d41PxH9I/ncIaRfWUWACF5svRKpf+Egv6k1JRe2sCelml5m+WtMEm4Tzo4cUs/qjJDTvAgMBAAECgYAbLvi3r0XXMiCoVBQKuslwFQeerCeHcCn+LNIVADh4Z45FC2DzymoOu9/Lbk6aKJCN9YmohMxqmU8OsDOPbrMvIVRLdFHuz4K6yfPzjE7C1S8GrBAvxL7LpN4DTff3pzCsGSsqRbyYgtPOmZpyJQ0EQa2EP2xjFHYB696xyBsxgQJBAP+RnsXqSy7w1M2jhZ9raOoGl6KJiu2wVf1oSLwsQMG1TPe4QbL65ioSlw1hGchypzVbwi43KXVpYMT47sZkCoMCQQC+AVmETRDsfeIBiJwRR+nZsq3H2cdWEpYiyXfDpksv1zv+gxfod/Rwax9Nuz5npxej/LlC97JBy6Ns7wIVF5AlAkEAvNiy1RZixeXpqaaad4mb9co3RECUazyw3dawYHPmyfyZSjdaPNIPP6mK+rT0o1ytV81c+F+EgCEFA6facLi91wJADl8Rw0UE+65F0vHfRBtZX8L5C/236xW6z2THrz+7viGcgxKtU0MHdR1VH88C2Fo2Gow6AwuzKvDZKpslYC0JFQJAP9aIG+B56DJb7CEyz5tnKeepmJ9a/dLFbd+iivDYKZxsMqBmpX+kZix0+e7NCf3wT1DAWdjScdWyTdH+ojsAkw==";
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), json);
     
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/logistics/logisticsOrderorderCallBack";
     String res = hcszqgpost.ZYPost(url, param);
     System.out.println("res=" + res);
   }
   
   public static void main(String[] args) {
     try {
       orderCallBack();
     
     }
     catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 }


