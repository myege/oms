 package com.what21.service.impl;
 
 import com.what21.dao.ItemForCkDao;
 import com.what21.model.Item;
 import com.what21.model.ItemForCk;
 import com.what21.service.ItemForCkService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ItemForCkExcelUtil;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ItemForCkServiceImpl
   implements ItemForCkService
 {
   @Autowired
   private ItemForCkDao itemForCkDao;
   
   public List<ItemForCk> findAll(Map<String, Object> map) {
     return this.itemForCkDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.itemForCkDao.count(map);
   }
 
   
   public List<ItemForCk> findId(Map<String, Object> map) {
     return this.itemForCkDao.findId(map);
   }
   
   public List<ItemForCk> dateTime(Map<String, Object> map) {
     return this.itemForCkDao.dateTime(map);
   }
 
   
   public int deleteItem(String id) {
     return this.itemForCkDao.deleteItem(id);
   }
 
 
   
   public GeneralResult importOrder(String path, int userId) {
     return null;
   }
 
   
   public Item findBySku(ItemForCk i) {
     return this.itemForCkDao.findBySku(i);
   }
 
 
   
   public List<ItemForCk> condition(Map<String, Object> map) {
     return null;
   }
 
   
   public int insert(ItemForCk itemForCk) {
     return this.itemForCkDao.insert(itemForCk);
   }
 
 
   
   public int updateItemForCk(ItemForCk itemForCk) {
     return this.itemForCkDao.updateItemForCk(itemForCk);
   }
 
   
   public ItemForCk findByItemSku(String itemSku) {
     return this.itemForCkDao.findByItemSku(itemSku);
   }
 
   
   public List<ItemForCk> findByIdToBG(int id) {
     return this.itemForCkDao.findByIdToBG(id);
   }
 
   
   public List<ItemForCk> findByToBG(Map<String, Object> map) {
     return this.itemForCkDao.findByToBG(map);
   }
 
   
   public GeneralResult importItem(String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ItemForCkExcelUtil.read(string);
     List<ItemForCk> list = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size();
     try {
       for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
         Object[] nowRowData = me.getValue();
         int rowNum = ((Integer)me.getKey()).intValue() + 1;
         String genre = null;
         if (nowRowData[0] != null && StringUtils.isNotEmpty(nowRowData[0].toString().trim())) {
           genre = nowRowData[0].toString();
         }
         
         String itemSKU = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品编码没有输入！<br>");
           break;
         } 
         itemSKU = nowRowData[1].toString();
         
         String itemName = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品名称没有输入！<br>");
           break;
         } 
         itemName = nowRowData[2].toString();
         
         String unitDesc = null;
         if (nowRowData[3] != null && StringUtils.isNotEmpty(nowRowData[3].toString().trim())) {
           unitDesc = nowRowData[3].toString();
         }
         
         String hscode = null;
         if (nowRowData[4] != null && StringUtils.isNotEmpty(nowRowData[4].toString().trim())) {
           hscode = nowRowData[4].toString();
         }
         
         String oneUnitDesc = null;
         if (nowRowData[5] != null && StringUtils.isNotEmpty(nowRowData[5].toString().trim())) {
           oneUnitDesc = nowRowData[5].toString();
         }
         
         String twoUnitDesc = null;
         if (nowRowData[6] != null && StringUtils.isNotEmpty(nowRowData[6].toString().trim())) {
           twoUnitDesc = nowRowData[6].toString();
         }
         
         String productRecordNo = null;
         if (nowRowData[7] != null && StringUtils.isNotEmpty(nowRowData[7].toString().trim())) {
           productRecordNo = nowRowData[7].toString();
         }
         
         String country = null;
         if (nowRowData[8] != null && StringUtils.isNotEmpty(nowRowData[8].toString().trim())) {
           country = nowRowData[8].toString();
         }
         
         ItemForCk itemForCk = new ItemForCk();
         itemForCk.setGenre(genre);
         itemForCk.setItemSKU(itemSKU);
         itemForCk.setItemName(itemName);
         itemForCk.setUnitDesc(unitDesc);
         itemForCk.setHscode(hscode);
         itemForCk.setOneUnitDesc(oneUnitDesc);
         itemForCk.setTwoUnitDesc(twoUnitDesc);
         itemForCk.setProductRecordNo(productRecordNo);
         itemForCk.setCompanyCode("ZY");
         itemForCk.setCountry(country);
         
         list.add(itemForCk);
       } 
 
 
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0) {
           for (ItemForCk itemForCk : list) {
             if (this.itemForCkDao.findByItemSku(itemForCk.getItemSKU()) != null) {
               result.setMessage("导入失败，已存在商家编码为" + itemForCk.getCompanyCode() + "和SKU为" + itemForCk.getItemSKU() + "的商品");
               return result;
             } 
           } 
           this.itemForCkDao.insertBatch(list);
         } 
         
         message.append("导入成功" + total + "条！");
       } 
       result.setMessage(message.toString());
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
     } 
     return result;
   }
 }


