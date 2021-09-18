 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.ItemDao;
 import com.what21.model.Item;
 import com.what21.service.ItemService;
 import com.what21.tools.Tools;
 import com.what21.util.ExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ItemServiceImpl
   implements ItemService
 {
   @Autowired
   private ItemDao itemDao;
   
   public List<Item> findAll(Map<String, Object> map) {
     return this.itemDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.itemDao.count(map);
   }
 
   
   public List<Item> findId(Map<String, Object> map) {
     return this.itemDao.findId(map);
   }
   
   public List<Item> dateTime(Map<String, Object> map) {
     return this.itemDao.dateTime(map);
   }
 
   
   public int deleteItem(String id) {
     return this.itemDao.deleteItem(id);
   }
   
   public GeneralResult importOrder(String path, int userId) {
     return null;
   }
 
   
   public Item findBySku(Item i) {
     return this.itemDao.findBySku(i);
   }
 
 
   
   public List<Item> condition(Map<String, Object> map) {
     return null;
   }
 
 
   
   public int insert(Item item) {
     return this.itemDao.insert(item);
   }
 
 
   
   public int updateItme(Item i) {
     return this.itemDao.updateItme(i);
   }
 
   
   public GeneralResult importItem(int id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read_Item(string);
     List<Item> list = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size() - 1;
     boolean firstRow = true;
     
     try {
       for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
         if (firstRow) {
           firstRow = false;
           continue;
         } 
         Object[] nowRowData = me.getValue();
         int rowNum = ((Integer)me.getKey()).intValue() + 1;
         
         Object itemSKU = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品SKU没有输入！<br>");
           break;
         } 
         itemSKU = nowRowData[0].toString();
         
         String itemName = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品名称没有输入！<br>");
           break;
         } 
         itemName = nowRowData[1].toString();
         
         String itemCode = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品条码没有输入！<br>");
           break;
         } 
         itemCode = nowRowData[2].toString();
         
         String itemSpec = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品规格没有输入！<br>");
           break;
         } 
         itemSpec = nowRowData[3].toString();
         
         String unitDesc = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：单位没有输入！<br>");
           break;
         } 
         unitDesc = nowRowData[4].toString();
         
         String companyCode = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>商家编码</font>没有输入！<br>");
           break;
         } 
         companyCode = nowRowData[5].toString();
 
         
         String hscode = null;
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>海关备案编号</font>没有输入！<br>");
           break;
         } 
         hscode = nowRowData[6].toString();
         
         String oneUnitDesc = null;
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>第一计量单位</font>没有输入！<br>");
           break;
         } 
         oneUnitDesc = nowRowData[7].toString();
 
         
         String twoUnitDesc = "";
         if (nowRowData[8] != null && StringUtils.isNotEmpty(nowRowData[8].toString().trim())) {
           twoUnitDesc = nowRowData[8].toString();
         }
         
         String productRecordNo = null;
         if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>产品国检备案编号</font>没有输入！<br>");
           break;
         } 
         productRecordNo = nowRowData[9].toString();
 
         
         String unitWeight = null;
         if (nowRowData[10] == null || StringUtils.isEmpty(nowRowData[10].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>重量</font>没有输入！<br>");
           break;
         } 
         unitWeight = nowRowData[10].toString();
         
         String taxRate = null;
         if (nowRowData[11] == null || StringUtils.isEmpty(nowRowData[11].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>税率</font>没有输入！<br>");
           break;
         } 
         taxRate = nowRowData[11].toString();
 
         
         String country = null;
         if (nowRowData[12] == null || StringUtils.isEmpty(nowRowData[12].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>原产国编码</font>没有输入！<br>");
           break;
         } 
         country = nowRowData[12].toString();
         
         String internalNumber = null;
         if (nowRowData[13] == null || StringUtils.isEmpty(nowRowData[13].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>金二序号</font>没有输入！<br>");
           break;
         } 
         internalNumber = nowRowData[13].toString();
         
         Item i = new Item();
         i.setItemSpec(itemSpec);
         i.setUnitDesc(unitDesc);
         i.setCreateDateTime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
         i.setItemSKU((String)itemSKU);
         i.setItemName(itemName);
         i.setCompanyCode(companyCode);
         i.setProductRecordNo(productRecordNo);
         i.setItemcode(itemCode);
         i.setHscode(hscode);
         i.setOneUnitDesc(oneUnitDesc);
         i.setTwoUnitDesc(twoUnitDesc);
         i.setUnitWeight(Double.parseDouble(unitWeight));
         i.setInternalNumber(Integer.parseInt(internalNumber));
         i.setCountry(country);
         i.setTaxRate(Double.parseDouble(taxRate));
         i.setWmstype("0");
         list.add(i);
       } 
       
       Map<String, Integer> checkMap = new HashMap<>();
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0) {
           for (Item item : list) {
             if (this.itemDao.findBySku(item) != null) {
               result.setMessage("导入失败，已存在SKU为" + item.getItemSKU() + "的商品");
               return result;
             } 
             if (checkMap.get(String.valueOf(item.getCompanyCode()) + "####" + item.getItemSKU()) == null) {
               checkMap.put(String.valueOf(item.getCompanyCode()) + "####" + item.getItemSKU(), Integer.valueOf(1)); continue;
             } 
             result.setMessage("导入失败，Excel已存在SKU为" + item.getItemSKU() + "商家为" + item.getCompanyCode() + "的商品");
             return result;
           } 
           
           this.itemDao.insertBatch(list);
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
   
   public String CreateItem(String str) throws Exception {
     System.out.println("str=====" + str);
     JSONArray detailDtoList = JSONArray.parseArray(str);
     
     for (int j = 0; j < detailDtoList.size(); j++) {
       
       JSONObject ret = detailDtoList.getJSONObject(j);
       StringBuffer error0 = new StringBuffer();
       if (!ret.containsKey("itemNo")) {
         error0.append("缺少必要参数key值 itemNo;");
       } else if (!ret.containsKey("skuName")) {
         error0.append("缺少必要参数key值 skuName;");
       } else if (!ret.containsKey("pznCode")) {
         error0.append("缺少必要参数key值 pznCode;");
       } else if (!ret.containsKey("hsCode")) {
         error0.append("缺少必要参数key值 hsCode;");
       } else if (!ret.containsKey("goodsModel")) {
         error0.append("缺少必要参数key值 goodsModel;");
       } else if (!ret.containsKey("placeOfOriginCode")) {
         error0.append("缺少必要参数key值 placeOfOriginCode;");
       } else if (!ret.containsKey("unitCode")) {
         error0.append("缺少必要参数key值 unitCode;");
       } else if (!ret.containsKey("firstUnitCode")) {
         error0.append("缺少必要参数key值 firstUnitCode;");
       } else if (!ret.containsKey("secondUnitCode")) {
         error0.append("缺少必要参数key值 secondUnitCode;");
       } else if (!ret.containsKey("inLawQty")) {
         error0.append("缺少必要参数key值 inLawQty;");
       } else if (!ret.containsKey("inSecondLawQty")) {
         error0.append("缺少必要参数key值 inSecondLawQty;");
       } else if (!ret.containsKey("dclCurrCd")) {
         error0.append("缺少必要参数key值 dclCurrCd;");
       } else if (!ret.containsKey("inQty")) {
         error0.append("缺少必要参数key值 inQty;");
       } 
       if (error0.length() > 4) {
         return error0.toString();
       }
       
       String itemNo = ret.getString("itemNo");
       System.out.println("itemNo=====" + itemNo);
       String skuName = ret.getString("skuName");
       String pznCode = ret.getString("pznCode");
       String hsCode = ret.getString("hsCode");
       String goodsModel = ret.getString("goodsModel");
       String placeOfOriginCode = ret.getString("placeOfOriginCode");
       String unitCode = ret.getString("unitCode");
       String firstUnitCode = ret.getString("firstUnitCode");
       String secondUnitCode = ret.getString("secondUnitCode");
       String inLawQty = ret.getString("inLawQty");
       String inSecondLawQty = ret.getString("inSecondLawQty");
       String dclCurrCd = ret.getString("dclCurrCd");
       String inQty = ret.getString("inQty");
       
       String ownPznCode = ret.getString("ownPznCode");
       String NetWeight = ret.getString("NetWeight");
 
 
 
       
       System.out.println();
       List<Item> list = new ArrayList<>();
       Item i = new Item();
       i.setItemSpec(goodsModel);
       i.setUnitDesc(unitCode);
       i.setCreateDateTime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
       i.setItemSKU(ownPznCode);
       i.setItemName(skuName);
       i.setCompanyCode("YWBJG");
       i.setProductRecordNo("YW");
       i.setItemcode("1");
       i.setHscode(hsCode);
       i.setOneUnitDesc(firstUnitCode);
       i.setTwoUnitDesc(secondUnitCode);
       i.setUnitWeight(Double.parseDouble(NetWeight));
       i.setInternalNumber(Integer.parseInt(itemNo));
       i.setCountry(placeOfOriginCode);
       i.setTaxRate(Double.parseDouble("0.91"));
       i.setWmstype("0");
       i.setFirstCount(inLawQty);
       i.setSecondCount(inSecondLawQty);
       i.setItemcode(pznCode);
       list.add(i);
       
       if (list.size() != 0) {
         for (Item item : list) {
           if (this.itemDao.findBySku(item) != null) {
             String a = "已存在SKU为" + item.getItemSKU() + "的商品";
             return a;
           } 
         } 
         this.itemDao.insertBatch(list);
       } 
     }     
     return "0";
   }
   
   public List<Item> finImpWms() {
     return this.itemDao.finImpWms();
   }
 
   
   public List<Item> findArray(String[] dis) {
     return this.itemDao.findArray(dis);
   }
 
   
   public void updateWmsType(List<Map<String, String>> i) {
     this.itemDao.updateWmsType(i);
   }
 
 
   
   public Item findById(String id) {
     return this.itemDao.findById(id);
   }
 
 
   
   public int updateById(Item item) {
     return this.itemDao.updateById(item);
   }
 
 
   
   public int save(Item item) {
     return this.itemDao.save(item);
   }
 }


