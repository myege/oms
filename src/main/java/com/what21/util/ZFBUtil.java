 package com.what21.util;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedPushSku;
 import com.what21.tools.Tools;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.Date;
 import java.util.List;
 
 
 public class ZFBUtil
 {
   public static String pushOrder(OrderBonded orderBonded, List<OrderBondedPushSku> skus, Date date, int i) {
     Date now = new Date(date.getTime() - (i * 60000));
     String url = "http://haidai.kuaqu.hk/home.php?r=api/tsOrder";
     JSONArray jsonArray = new JSONArray();
     JSONObject object = new JSONObject();
     String merchantnum = orderBonded.getMerchantNum();
     String carrier = orderBonded.getCarrier();
     if ("PN".equals(merchantnum)) {
       object.put("username", "pn");
     } else if ("OPGJ".equals(merchantnum)) {
       object.put("username", "欧派");
     } else if ("AT".equals(merchantnum)) {
       object.put("username", "澳淘");
     } else if ("GNC".equals(merchantnum)) {
       object.put("username", "gnc");
     } 
     object.put("password", "123456");
     object.put("order_sn", orderBonded.getTxLogisticID());
     object.put("addtime", Tools.format("yyyy-MM-dd HH:mm:ss", now));
     object.put("tel", orderBonded.getReceiveManPhone());
     object.put("name", orderBonded.getBuyerName());
     object.put("card", orderBonded.getBuyerIdNumber());
     object.put("province", orderBonded.getReceiveProvince());
     object.put("city", orderBonded.getReceiveCity());
     object.put("area", orderBonded.getReceiveCounty());
     object.put("address", orderBonded.getReceiveManAddress());
     if ("STO".equals(carrier)) {
       object.put("kd_company", "申通");
     } else if ("HTO".equals(carrier)) {
       object.put("kd_company", "汇通");
     } else if ("YTO".equals(carrier)) {
       object.put("kd_company", "圆通");
     } else if ("SF".equals(carrier)) {
       object.put("kd_company", "顺丰");
     } 
     object.put("kd_number", orderBonded.getMailNo());
     object.put("total_amount", orderBonded.getWorth());
     JSONArray jsonArray2 = new JSONArray();
     for (OrderBondedPushSku sku : skus) {
       JSONObject object2 = new JSONObject();
       object2.put("goods_sn", sku.getGoodsSn());
       object2.put("goods_price", sku.getGoodsPrice());
       object2.put("goods_num", sku.getGoodsNumber());
       jsonArray2.add(object2);
     } 
     
     object.put("goodsList", jsonArray2);
     jsonArray.add(object);
 
 
     
     String res = sendPost(url, "data=" + jsonArray.toString());
     return res;
   }
 
   
   public static String sendPost(String url, String param) {
     String result = "";
     try {
       URL url2 = new URL(url);
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url2.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       out.write(param.getBytes(charset));
       out.flush();
       out.close();
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
       System.out.println("result====" + result);
     } catch (Exception ex) {
       ex.printStackTrace();
       return result;
     } 
     return result;
   }
 }


