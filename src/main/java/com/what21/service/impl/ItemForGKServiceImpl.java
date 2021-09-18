 package com.what21.service.impl;
 
 import com.what21.dao.ItemForGKDao;
 import com.what21.model.ItemForGk;
 import com.what21.service.ItemForGKService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ItemExcelUtil;
 import com.what21.util.TimeUtil;
 import java.io.File;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.math.RoundingMode;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ItemForGKServiceImpl
   implements ItemForGKService
 {
   @Autowired
   ItemForGKDao gkdao;
   
   public List<ItemForGk> findAll(Map<String, Object> paramMap) {
     return this.gkdao.findAll(paramMap);
   }
 
 
   
   public int count(Map<String, Object> paramMap) {
     return this.gkdao.count(paramMap);
   }
 
 
   
   public int insertItem(ItemForGk ig) {
     if (ig.getWeight() == null) {
       ig.setWeight("0");
     }
     if (ig.getPrice() == null) {
       ig.setPrice("0");
     }
     DecimalFormat df = new DecimalFormat("0.000");
 
     
     DecimalFormat cf = new DecimalFormat("0.00");
     df.setRoundingMode(RoundingMode.HALF_UP);
 
     
     try {
       Double price = Double.valueOf(Double.parseDouble(ig.getPrice()));
       String al = cf.format(price);
       
       Double weight = Double.valueOf(Double.parseDouble(ig.getWeight()));
       String bl = df.format(weight);
       
       ig.setWeight(bl);
       ig.setPrice(al);
       
       ig.setCreateTime(TimeUtil.defaultTime("yyyy-MM-dd HH:mm:ss", new Date()));
     } catch (Exception e) {
       
       e.printStackTrace();
       return 0;
     } 
     return this.gkdao.insertItem(ig);
   }
 
 
   
   public int deleteItem(int[] ids) {
     return this.gkdao.deleteItem(ids);
   }
   
   public ItemForGk queryOne(int id) {
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf(0));
     pageMap.put("endPage", Integer.valueOf(1));
     pageMap.put("lhyid", Integer.valueOf(id));
     List<ItemForGk> list = this.gkdao.findAll(pageMap);
     ItemForGk gk = new ItemForGk();
     for (ItemForGk itemForGk : list) {
       gk.setId(id);
       gk.setItemCode(itemForGk.getItemCode());
       gk.setItemSku(itemForGk.getItemSku());
       gk.setPrice(itemForGk.getPrice());
       gk.setWeight(itemForGk.getWeight());
       gk.setCreateTime(itemForGk.getCreateTime());
       gk.setHsCode(itemForGk.getHsCode());
       gk.setItemName(itemForGk.getItemName());
       gk.setItemClass(itemForGk.getItemClass());
       gk.setStandard(itemForGk.getStandard());
       gk.setUnitName(itemForGk.getUnitName());
     } 
     return gk;
   }
 
 
   
   public int updateForItem(ItemForGk ig) {
     DecimalFormat df = new DecimalFormat("0.000");
     df.setRoundingMode(RoundingMode.HALF_UP);
     
     DecimalFormat cf = new DecimalFormat("0.00");
     df.setRoundingMode(RoundingMode.HALF_UP);
     try {
       Double price = Double.valueOf(Double.parseDouble(ig.getPrice()));
       String al = cf.format(price);
       
       Double weight = Double.valueOf(Double.parseDouble(ig.getWeight()));
       String bl = df.format(weight);
       ig.setWeight(bl);
       ig.setPrice(al);
     } catch (Exception e) {
       
       return 0;
     } 
 
     
     return this.gkdao.updateForItem(ig);
   }
 
 
   
   public GeneralResult importExcel(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = ItemExcelUtil.readForGK(path);
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     StringBuffer message = new StringBuffer();
     int totalData = items.entrySet().size();
     int success = 0;
     DecimalFormat df = new DecimalFormat("0.000");
     df.setRoundingMode(RoundingMode.HALF_UP);
     DecimalFormat cf = new DecimalFormat("0.00");
     cf.setRoundingMode(RoundingMode.HALF_UP);
     List<ItemForGk> list = new ArrayList<>();
     String newDate = TimeUtil.defaultTime("yyyy-MM-dd HH:mm:ss", new Date());
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       ItemForGk ig = new ItemForGk();
       Object[] nowRowData = entry.getValue();
       int row = ((Integer)entry.getKey()).intValue() + 1;
       Object data1 = nowRowData[0];
       if (data1 == null || StringUtils.isEmpty(data1.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品SKU没有输入！<br>");
         
         continue;
       } 
       int i = this.gkdao.findByItemSku(data1.toString().trim());
       if (i > 0) {
         message.append("导入第" + row + "行数据失败，失败原因：商品SKU已存在！<br>");
         
         continue;
       } 
       Object data2 = nowRowData[1];
       Double price = null;
       String al = null;
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品价格没有输入！<br>");
         
         continue;
       }       
       Object data3 = nowRowData[2];
       Double weight = null;
       String bl = null;
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品重量没有输入！<br>");
         
         continue;
       }       
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：海关备案编号没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品条码没有输入！<br>");
         
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品名称没有输入！<br>");
         
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品品名没有输入！<br>");
         
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品规格没有输入！<br>");
         
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品计量单位没有输入！<br>");
         
         continue;
       } 
       
       ig.setItemSku(data1.toString().trim());
       ig.setPrice(data2.toString().trim());
       ig.setWeight(data3.toString().trim());
       ig.setHsCode(data4.toString().trim());
       ig.setItemCode(data5.toString().trim());
       ig.setItemName(data6.toString().trim());
       ig.setItemClass(data7.toString().trim());
       ig.setStandard(data8.toString().trim());
       ig.setUnitName(data9.toString().trim());
       ig.setCreateTime(newDate);
       list.add(ig);
       success++;
     } 
     if (message.indexOf("数据失败") != -1) {
       StringBuffer stringBuffer = new StringBuffer();
       stringBuffer.append("导入失败！请检查数据！</br>");
       stringBuffer.append("<hr>");
       stringBuffer.append(message);
       result.setMessage(stringBuffer.toString());
       return result;
     } 
     if (list.size() > 0) {
       try { this.gkdao.insertItems(list); }
       catch (Exception e)
       
       { StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);
         e.printStackTrace(pw);
         String errorMsg = sw.toString();
         System.out.println(sw);
         StringBuffer stringBuffer = new StringBuffer();
         if (errorMsg.indexOf("Data too long for column 'weight'") != -1) {
           stringBuffer.append("导入失败！请检查数据！</br>");
           stringBuffer.append("<hr>");
           stringBuffer.append("重量输入值过大！请调整或联系官方人员进行修改！");
           result.setMessage(stringBuffer.toString());
           return result;
         } 
         if (errorMsg.indexOf("Data too long for column 'price'") != -1) {
           stringBuffer.append("导入失败！请检查数据！</br>");
           stringBuffer.append("<hr>");
           stringBuffer.append("金额输入值过大！请调整或联系官方人员进行修改！");
           result.setMessage(stringBuffer.toString());
           return result;
         } 
         stringBuffer.append("导入失败！</br>");
         stringBuffer.append("<hr>");
         stringBuffer.append("服务器错误！请联系官方人员");
         result.setMessage(stringBuffer.toString());
         return result; }
     
     }
     
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，有" + success + "条数据导入成功！");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
   
   public List<ItemForGk> findByIDS(int[] ids) {
     return this.gkdao.findByIDS(ids);
   }
 
 
   
   public List<ItemForGk> findAllByEx() {
     return this.gkdao.findAllByEx();
   }
 
 
   
   public int findByItemSku(String itemSku) {
     return this.gkdao.findByItemSku(itemSku);
   }
 }


