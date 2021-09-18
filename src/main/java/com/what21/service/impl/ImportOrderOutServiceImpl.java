 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.OrderOutDao;
 import com.what21.dao.UserDao;
 import com.what21.model.OrderOut;
 import com.what21.model.OrderOutSku;
 import com.what21.model.Users;
 import com.what21.service.ImportOrderOutService;
 import com.what21.tools.Tools;
 import com.what21.util.StringUtil;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 
 @Service
 public class ImportOrderOutServiceImpl
   implements ImportOrderOutService
 {
   @Autowired
   private UserDao userDao;
   @Autowired
   private OrderOutDao orderOutDao;
   
   public String importOrderOut(String jsonStr) {
     String result = "fail";
     JSONObject retJson = new JSONObject();
     
     try {
       byte[] b = Base64.decodeBase64(jsonStr);
       String param = new String(b, "utf-8");
       System.out.println(param);
       if (StringUtils.isNotEmpty(param)) {
         
         JSONObject jSONObject = JSONObject.parseObject(param);
         String username = jSONObject.getString("username");
         String password = jSONObject.getString("password");
         Users user = new Users();
         user.setUserName(username);
         user.setPassword(password);
         Users retUser = this.userDao.findUser(user);
         StringBuffer message = new StringBuffer();
         List<OrderOut> orderOuts = new ArrayList<>();
         List<OrderOutSku> orderOutSkus = new ArrayList<>();
         DecimalFormat df = new DecimalFormat("0.00");
         if (retUser != null) {
           String str_date = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
           Integer userid = Integer.valueOf(retUser.getId());
           JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
           for (int i = 0; i < orderJSONArray.size(); i++) {
             int count = 0;
             float total = 0.0F;
             int flag = 1;
             JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
             String orderno = orderJSONObject.getString("orderno");
             if (StringUtil.isEmpty(orderno).booleanValue()) {
               message.append("推送失败，失败原因：订单号没有输入！");
               
               break;
             } 
             String consignee = orderJSONObject.getString("consignee");
             if (StringUtil.isEmpty(consignee).booleanValue()) {
               message.append("推送失败，失败原因：订单号为" + orderno + "收货人没有输入！");
               
               break;
             } 
             String consigneeaddress = orderJSONObject.getString("consigneeaddress");
             
             String consigneetel = orderJSONObject.getString("consigneetel");
             
             String totalwaybill = orderJSONObject.getString("totalwaybill");
             if (StringUtils.isEmpty(totalwaybill)) {
               message.append("推送失败，失败原因：订单号为" + orderno + "主运单号没有输入！");
               
               break;
             } 
             String waybillno = orderJSONObject.getString("waybillno");
             if (StringUtils.isEmpty(waybillno)) {
               message.append("推送失败，失败原因：订单号为" + orderno + "运单号没有输入！");
               
               break;
             } 
             Float rate = null;
             String s_rate = orderJSONObject.getString("rate");
             if (StringUtils.isEmpty(s_rate)) {
               message.append("推送失败，失败原因：订单号为" + orderno + "汇率没有输入！");
               break;
             } 
             try {
               rate = Float.valueOf(Float.parseFloat(s_rate));
             } catch (Exception e) {
               message.append("推送失败，失败原因：订单号为" + orderno + "请输入正确的汇率！");
               
               break;
             } 
             String destinationcountry = orderJSONObject.getString("destinationcountry");
             if (StringUtils.isEmpty(destinationcountry)) {
               message.append("推送失败，失败原因：订单号为" + orderno + "运抵国没有输入！");
               
               break;
             } 
             
             Float grossweight = null;
             String s_grossweight = orderJSONObject.getString("grossweight");
             if (StringUtils.isEmpty(s_grossweight)) {
               message.append("推送失败，失败原因：订单号为" + orderno + "毛重没有输入！");
               break;
             } 
             try {
               grossweight = Float.valueOf(Float.parseFloat(s_grossweight));
             } catch (Exception e) {
               message.append("推送失败，失败原因：订单号为" + orderno + "请输入正确的毛重！");
               
               break;
             } 
             Float netweight = null;
             String s_netweight = orderJSONObject.getString("netweight");
             if (StringUtils.isEmpty(s_netweight)) {
               message.append("推送失败，失败原因：订单号为" + orderno + "净重没有输入！");
               break;
             } 
             try {
               netweight = Float.valueOf(Float.parseFloat(s_netweight));
             } catch (Exception e) {
               message.append("推送失败，失败原因：订单号为" + orderno + "请输入正确的净重！");
               
               break;
             } 
             String currency = orderJSONObject.getString("currency");
             if (StringUtils.isEmpty(currency)) {
               message.append("推送失败，失败原因：订单号为" + orderno + "币制没有输入！");
               
               break;
             } 
             String companycode = this.orderOutDao.findCompanycodeByUserid(userid);
             
             OrderOut orderOut = new OrderOut();
             orderOut.setConsignee(consignee);
             orderOut.setConsigneetel(consigneetel);
             orderOut.setConsigneeaddress(consigneeaddress);
             orderOut.setConsigneecountry(destinationcountry);
             orderOut.setConsigneetel(consigneetel);
             orderOut.setCurrency(currency);
             orderOut.setOrderno(orderno);
             orderOut.setWaybillno(waybillno);
             orderOut.setRate(rate.toString());
             orderOut.setDestinationcountry(destinationcountry);
             orderOut.setGrossweight(grossweight.toString());
             orderOut.setNetweight(netweight.toString());
             orderOut.setTotalwaybill(totalwaybill);
             orderOut.setCreatetime(str_date);
             orderOut.setItemcode(companycode);
             orderOut.setUserid(userid);
             orderOut.setStatus(Integer.valueOf(0));
             
             JSONArray orderJSONArray2 = orderJSONObject.getJSONArray("orderSkuList");
             if (orderJSONArray2 == null || orderJSONArray2.size() == 0) {
               message.append("推送失败，失败原因：订单号为" + orderno + "没有子订单数据！");
               break;
             } 
             for (int j = 0; j < orderJSONArray2.size(); j++) {
               JSONObject orderJSONObject2 = orderJSONArray2.getJSONObject(j);
               String goodsname = orderJSONObject2.getString("goodsname");
               if (StringUtils.isEmpty(goodsname)) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单商品名称没有输入！");
                 flag = 0;
                 
                 break;
               } 
               String goodsno = orderJSONObject2.getString("goodsno");
               if (StringUtils.isEmpty(goodsno)) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单商品编码没有输入！");
                 flag = 0;
                 
                 break;
               } 
               String specifications = orderJSONObject2.getString("specifications");
               if (StringUtils.isEmpty(specifications)) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单商品规格没有输入！");
                 flag = 0;
                 
                 break;
               } 
               Integer goodsamount = null;
               String s_goodsamount = orderJSONObject2.getString("goodsamount");
               if (StringUtils.isEmpty(s_goodsamount)) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单成交数量没有输入！");
                 flag = 0;
                 break;
               } 
               try {
                 goodsamount = Integer.valueOf(Integer.parseInt(s_goodsamount));
               } catch (Exception e) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单请输入正确的成交数量！");
                 flag = 0;
                 
                 break;
               } 
               Float unitprice = null;
               String s_unitprice = orderJSONObject2.getString("unitprice");
               if (StringUtils.isEmpty(s_unitprice)) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单成交单价没有输入！");
                 flag = 0;
                 break;
               } 
               try {
                 unitprice = Float.valueOf(Float.parseFloat(s_unitprice));
               } catch (Exception e) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单请输入正确的成交单价！");
                 flag = 0;
                 
                 break;
               } 
               Float totalprice = null;
               String s_totalprice = orderJSONObject2.getString("totalprice");
               if (StringUtils.isEmpty(s_totalprice)) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单成交总价没有输入！");
                 flag = 0;
                 break;
               } 
               try {
                 totalprice = Float.valueOf(Float.parseFloat(s_totalprice));
               } catch (Exception e) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单请输入正确的成交总价！");
                 flag = 0;
                 break;
               } 
               Float f = Float.valueOf(unitprice.floatValue() * goodsamount.intValue());
               String str1 = df.format(f);
               String str2 = df.format(totalprice);
               if (!str1.equals(str2)) {
                 message.append("推送失败，失败原因：订单号为" + orderno + "的子订单成交总价不和实际的成交单价和数量相对应！");
                 flag = 0;
                 
                 break;
               } 
               if (flag == 0) {
                 break;
               }
               
               count += goodsamount.intValue();
               total += totalprice.floatValue();
               OrderOutSku orderOutSku = new OrderOutSku();
               orderOutSku.setOrderno(orderno);
               orderOutSku.setCreatetime(str_date);
               orderOutSku.setGoodsname(goodsname);
               orderOutSku.setGoodsno(goodsno);
               orderOutSku.setGoodsamount(goodsamount.toString());
               orderOutSku.setSpecifications(specifications);
               orderOutSku.setUnitprice(unitprice.toString());
               orderOutSku.setTotalprice(totalprice.toString());
               orderOutSkus.add(orderOutSku);
             } 
             orderOut.setTotalprice(df.format(total));
             orderOut.setOrdertotalamount(df.format(total));
             orderOut.setGoodsnum(Integer.valueOf(count));
             orderOut.setPackageno(Integer.valueOf(count));
             orderOuts.add(orderOut);
           } 
           
           if (!message.toString().contains("推送失败")) {
             if (orderOuts.size() == 0 || orderOutSkus.size() == 0) {
               retJson.put("msg", "推送失败,没有订单数据");
               retJson.put("result", result);
               return retJson.toJSONString();
             } 
             for (OrderOut orderOut : orderOuts) {
               Integer sum = this.orderOutDao.sumByOrderno(orderOut.getOrderno());
               if (sum.intValue() != 0) {
                 message.append("推送失败,订单中已有订单号为：" + orderOut.getOrderno() + "的订单");
                 retJson.put("msg", message.toString());
                 retJson.put("result", result);
                 return retJson.toJSONString();
               } 
             } 
             this.orderOutDao.insertBatch(orderOuts);
             this.orderOutDao.insertSkuBatch(orderOutSkus);
           } else {
             retJson.put("msg", message.toString());
             retJson.put("result", result);
             return retJson.toJSONString();
           } 
         } else {
           retJson.put("msg", "username or password is error, please check!");
           retJson.put("result", result);
           return retJson.toJSONString();
         } 
       } 
       
       retJson.put("msg", "import order success");
       retJson.put("result", "success");
       return retJson.toJSONString();
     } catch (Exception e) {
       e.printStackTrace();
       retJson.put("msg", "database connection fails, please check!");
       retJson.put("result", result);
       return retJson.toJSONString();
     } 
   }
 }


