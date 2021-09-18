 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.UserDao;
 import com.what21.dao.YTOBillDao;
 import com.what21.model.Users;
 import com.what21.model.YtoBill;
 import com.what21.service.ImportYtoService;
 import java.io.IOException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 @Service
 public class ImportYtoServiceImpl
   implements ImportYtoService
 {
   @Autowired
   private YTOBillDao dao;
   @Autowired
   private UserDao userDao;
   
   public String importOrder(String jsonStr) throws IOException {
     String result = "fail";
     JSONObject retJson = new JSONObject();
     byte[] b = Base64.decodeBase64(jsonStr);
     String param = new String(b, "utf-8");
     if (StringUtils.isNotEmpty(param)) {
       JSONObject jSONObject = JSONObject.parseObject(param);
       String userName = jSONObject.getString("userName");
       String password = jSONObject.getString("password");
       Users user = new Users();
       user.setUserName(userName);
       user.setPassword(password);
       Users retUser = this.userDao.findUser(user);
       if (retUser != null) {
         JSONArray ytoJSONArray = jSONObject.getJSONArray("ytoList");
         int j = 0; if (j < ytoJSONArray.size()) {
           JSONObject ytojSONArray = ytoJSONArray.getJSONObject(j);
           String orderNo = ytojSONArray.getString("orderNo");
           YtoBill y = new YtoBill();
           y.setOrderNo(orderNo);
           List<YtoBill> yto = this.dao.findByOrderNo(y);
           int i = 0;
           if (yto.size() == 0) {
             YtoBill yt = new YtoBill();
             yt.setWaybillNo(ytojSONArray.getString("waybillNo"));
             yt.setOrderNo(orderNo);
             yt.setShipperName(ytojSONArray.getString("shipperName"));
             yt.setShipperTel(ytojSONArray.getString("shipperTel"));
             yt.setShipperAddress(ytojSONArray.getString("shipperAddress"));
             yt.setConsigneeName(ytojSONArray.getString("consigneeName"));
             yt.setConsigneeTel(ytojSONArray.getString("consigneeTel"));
             yt.setConsigneeAddress(ytojSONArray.getString("consigneeAddress"));
             yt.setDeliveryTime(ytojSONArray.getString("deliveryTime"));
             Date date = new Date();
             SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             yt.setCreateTime(df.format(date));
             yt.setIsSigned("0");
             i++;
             this.dao.insertYTO(yt);
           } 
           retJson.put("msg", "导入成功,导入" + i + "条数据");
           retJson.put("result", "success");
           return retJson.toJSONString();
         } 
       } 
     } 
     return retJson.toJSONString();
   }
 }


