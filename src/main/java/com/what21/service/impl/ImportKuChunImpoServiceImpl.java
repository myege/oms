 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.Log_inventoryDao;
 import com.what21.dao.TinventoryDao;
 import com.what21.model.Log_inventory;
 import com.what21.model.Tinventory;
 import com.what21.service.ImportKuChunImpoService;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 
 
 @Service
 public class ImportKuChunImpoServiceImpl
   implements ImportKuChunImpoService
 {
   @Autowired
   private TinventoryDao tinventoryDao;
   @Autowired
   private Log_inventoryDao log_inventoryDao;
   
   public String importKuChun(String jsonStr) throws Exception {
     System.out.println("---------------------------->接收到来自ztz的访问");
     
     String ids = "";
     byte[] b = Base64.decodeBase64(jsonStr);
     String param = new String(b, "utf-8");
     List<Tinventory> list = new ArrayList<>();
     List<Log_inventory> log = new ArrayList<>();
     if (StringUtils.isNotEmpty(param)) {
       JSONObject jSONObject = JSONObject.parseObject(param);
       JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
       for (int i = 0; i < orderJSONArray.size(); i++) {
         JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
         String companyCode = orderJSONObject.getString("companyCode");
         
         String id = orderJSONObject.getString("id");
         String storage = orderJSONObject.getString("storage");
         String itemSku = orderJSONObject.getString("itemSku");
         String SUM = orderJSONObject.getString("sum");
         String InventoryFrozen = orderJSONObject.getString("InventoryFrozen");
         String inventory = orderJSONObject.getString("inventory");
         String itemName = orderJSONObject.getString("itemName");
         String InventoryOccupy = orderJSONObject.getString("InventoryOccupy");
         Tinventory tin = new Tinventory();
         
         tin.setItemsku(itemSku);
         tin.setItemName(itemName);
         tin.setTotality(Integer.parseInt(SUM));
         tin.setSurplusInventory(Integer.parseInt(inventory));
         tin.setUsedInventory(Integer.parseInt(InventoryFrozen));
         tin.setPreUsedInventory(Integer.parseInt(InventoryOccupy));
         
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String time = df.format(new Date());
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
         String pc = format.format(new Date());
         tin.setPc(pc);
         tin.setDate(time);
         tin.setMerchant(companyCode);
         tin.setCreateDate(time);
         tin.setStorage(storage);
         
         Log_inventory login = new Log_inventory();
         login.setCreateDate(time);
         
         login.setItemCount(Integer.valueOf(Integer.parseInt(SUM)));
         login.setItemName(itemName);
         login.setItemsku(itemSku);
         
         login.setType("导入");
         login.setRemark("导入： 商家编码为：" + companyCode + "商品编码为：" + itemSku + "数量为" + SUM);
         login.setStocklot(pc);
         login.setStorage(storage);
         
         list.add(tin);
         ids = String.valueOf(ids) + id + ",";
         this.log_inventoryDao.insert(login);
       } 
     } 
     if (list.size() != 0) {
       this.tinventoryDao.addBig(list);
     }
     return ids;
   }
 }


