 package com.zt.youzhan;
 
 import com.youzan.cloud.open.sdk.api.API;
 import com.youzan.cloud.open.sdk.api.ApiParams;
 import com.youzan.cloud.open.sdk.core.client.auth.Auth;
 import com.youzan.cloud.open.sdk.core.client.auth.Token;
 import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanDeliveryRegionCrossboardGet;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanRegionsGet;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanDeliveryRegionCrossboardGetParams;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanDeliveryRegionCrossboardGetResult;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanRegionsGetParams;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanRegionsGetResult;
 
 
 
 public class yzapi2
 {
   public static String youzanDeliveryRegionCrossboardGetParams(String tk) throws Exception {
     DefaultYZClient defaultYZClient = new DefaultYZClient();
     
     Token token = new Token(tk);
     
     YouzanDeliveryRegionCrossboardGet youzanDeliveryRegionCrossboardGet = new YouzanDeliveryRegionCrossboardGet();
     
     YouzanDeliveryRegionCrossboardGetParams youzanDeliveryRegionCrossboardGetParams = new YouzanDeliveryRegionCrossboardGetParams();
     youzanDeliveryRegionCrossboardGet.setAPIParams((ApiParams)youzanDeliveryRegionCrossboardGetParams);
     
     YouzanDeliveryRegionCrossboardGetResult result = (YouzanDeliveryRegionCrossboardGetResult)defaultYZClient.invoke((API)youzanDeliveryRegionCrossboardGet, (Auth)token, YouzanDeliveryRegionCrossboardGetResult.class);
     
     System.out.println("result:" + result);
     return "";
   }
   
   public static String youzanRegionsGetParams(String tk) throws Exception {
     DefaultYZClient defaultYZClient = new DefaultYZClient();
     
     Token token = new Token(tk);
     
     YouzanRegionsGet youzanRegionsGet = new YouzanRegionsGet();
     
     YouzanRegionsGetParams youzanRegionsGetParams = new YouzanRegionsGetParams();
     youzanRegionsGet.setAPIParams((ApiParams)youzanRegionsGetParams);
     
     YouzanRegionsGetResult result = (YouzanRegionsGetResult)defaultYZClient.invoke((API)youzanRegionsGet, (Auth)token, YouzanRegionsGetResult.class);
     System.out.println("result:" + result);
     return "";
   }
   
   public static void main(String[] args) {
     try {
       youzanRegionsGetParams("1cedad71aa951388de3f07e72c0ad591");
 
     
     }
     catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 }


