 package com.what21.service.impl;
 
 import com.what21.dao.ItemForZyDao;
 import com.what21.model.Item;
 import com.what21.model.ItemForZy;
 import com.what21.service.ItemForZyService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ItemForZyExcelUtil;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ItemForZyServiceImpl
   implements ItemForZyService
 {
   @Autowired
   private ItemForZyDao itemForZyDao;
   
   public List<ItemForZy> findAll(Map<String, Object> map) {
     return this.itemForZyDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.itemForZyDao.count(map);
   }
 
   
   public List<ItemForZy> findId(Map<String, Object> map) {
     return this.itemForZyDao.findId(map);
   }
   
   public List<ItemForZy> dateTime(Map<String, Object> map) {
     return this.itemForZyDao.dateTime(map);
   }
 
   
   public int deleteItem(String id) {
     return this.itemForZyDao.deleteItem(id);
   }
 
 
   
   public GeneralResult importOrder(String path, int userId) {
     return null;
   }
 
   
   public Item findBySku(ItemForZy i) {
     return this.itemForZyDao.findBySku(i);
   }
 
 
   
   public List<ItemForZy> condition(Map<String, Object> map) {
     return null;
   }
 
   
   public int insert(ItemForZy itemForZy) {
     return this.itemForZyDao.insert(itemForZy);
   }
 
 
   
   public int updateItemForZy(ItemForZy itemForZy) {
     return this.itemForZyDao.updateItemForZy(itemForZy);
   }
 
   
   public ItemForZy findByItemSku(String itemSku) {
     return this.itemForZyDao.findByItemSku(itemSku);
   }
 
   
   public List<ItemForZy> findByIdToBG(int id) {
     return this.itemForZyDao.findByIdToBG(id);
   }
 
   
   public List<ItemForZy> findByToBG(Map<String, Object> map) {
     return this.itemForZyDao.findByToBG(map);
   }
 
   
   public GeneralResult importItem(String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ItemForZyExcelUtil.read(string);
     List<ItemForZy> list = new ArrayList<>();
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
         
         ItemForZy itemForZy = new ItemForZy();
         itemForZy.setGenre(genre);
         itemForZy.setItemSKU(itemSKU);
         itemForZy.setItemName(itemName);
         itemForZy.setUnitDesc(unitDesc);
         itemForZy.setHscode(hscode);
         itemForZy.setOneUnitDesc(oneUnitDesc);
         itemForZy.setTwoUnitDesc(twoUnitDesc);
         itemForZy.setProductRecordNo(productRecordNo);
         itemForZy.setCompanyCode("ZY");
         
         list.add(itemForZy);
       } 
 
 
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0) {
           for (ItemForZy itemForZy : list) {
             if (this.itemForZyDao.findByItemSku(itemForZy.getItemSKU()) != null) {
               result.setMessage("导入失败，已存在商家编码为" + itemForZy.getCompanyCode() + "和SKU为" + itemForZy.getItemSKU() + "的商品");
               return result;
             } 
           } 
           this.itemForZyDao.insertBatch(list);
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


