 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.SzqgItemDao;
 import com.what21.model.SzqgItem;
 import com.what21.service.SzqgItemService;
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
 public class SzqgItemServiceImpl
   implements SzqgItemService
 {
   @Autowired
   private SzqgItemDao szqgItemDao;
   
   public List<SzqgItem> findAll(Map<String, Object> map) {
     return this.szqgItemDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.szqgItemDao.count(map);
   }
   
   public String CreateItem(String str) throws Exception {
     System.out.println("str=====" + str);
     List<SzqgItem> ItemList = new ArrayList<>();
     
     JSONObject ret = JSONObject.parseObject(str);
     StringBuffer error0 = new StringBuffer();
     if (!ret.containsKey("fromto")) {
       error0.append("缺少必要参数key值 fromto;");
     } else if (!ret.containsKey("skuCode")) {
       error0.append("缺少必要参数key值 skuCode;");
     } else if (!ret.containsKey("codeTs")) {
       error0.append("缺少必要参数key值 codeTs;");
     } else if (!ret.containsKey("barCode")) {
       error0.append("缺少必要参数key值 barCode;");
     } else if (!ret.containsKey("goodsName")) {
       error0.append("缺少必要参数key值 goodsName;");
     } else if (!ret.containsKey("goodsNameEn")) {
       error0.append("缺少必要参数key值 goodsNameEn;");
     } else if (!ret.containsKey("goodsDesc")) {
       error0.append("缺少必要参数key值 goodsDesc;");
     } else if (!ret.containsKey("goodsComposition")) {
       error0.append("缺少必要参数key值 goodsComposition;");
     } else if (!ret.containsKey("goodsFrom")) {
       error0.append("缺少必要参数key值 goodsFrom;");
     } else if (!ret.containsKey("goodsSpec")) {
       error0.append("缺少必要参数key值 goodsSpec;");
     } else if (!ret.containsKey("goodsModel")) {
       error0.append("缺少必要参数key值 goodsModel;");
     } else if (!ret.containsKey("hsCode")) {
       error0.append("缺少必要参数key值 hsCode;");
     } else if (!ret.containsKey("goodsUnit")) {
       error0.append("缺少必要参数key值 goodsUnit;");
     } else if (!ret.containsKey("firstUnit")) {
       error0.append("缺少必要参数key值 firstUnit;");
     } else if (!ret.containsKey("secondUnit")) {
       error0.append("缺少必要参数key值 secondUnit;");
     } else if (!ret.containsKey("highPrice")) {
       error0.append("缺少必要参数key值 highPrice;");
     } else if (!ret.containsKey("lowPrice")) {
       error0.append("缺少必要参数key值 lowPrice;");
     } else if (!ret.containsKey("supplier")) {
       error0.append("缺少必要参数key值 supplier;");
     } else if (!ret.containsKey("brand")) {
       error0.append("缺少必要参数key值 brand;");
     } else if (!ret.containsKey("brandEn")) {
       error0.append("缺少必要参数key值 brandEn;");
     } else if (!ret.containsKey("busRegion")) {
       error0.append("缺少必要参数key值 busRegion;");
     } else if (!ret.containsKey("grossWeight")) {
       error0.append("缺少必要参数key值 grossWeight;");
     } else if (!ret.containsKey("netWeight")) {
       error0.append("缺少必要参数key值 netWeight;");
     } 
     if (error0.length() > 4) {
       return error0.toString();
     }
     
     String fromto = ret.getString("fromto");
     System.out.println("fromto=====" + fromto);
     String skuCode = ret.getString("skuCode");
     String codeTs = ret.getString("codeTs");
     String barCode = ret.getString("barCode");
     String goodsName = ret.getString("goodsName");
     String goodsNameEn = ret.getString("goodsNameEn");
     String goodsDesc = ret.getString("goodsDesc");
     String goodsComposition = ret.getString("goodsComposition");
     String goodsFrom = ret.getString("goodsFrom");
     String goodsSpec = ret.getString("goodsSpec");
     String goodsModel = ret.getString("goodsModel");
     String hsCode = ret.getString("hsCode");
     String goodsUnit = ret.getString("goodsUnit");
     String firstUnit = ret.getString("firstUnit");
     String secondUnit = ret.getString("secondUnit");
     String highPrice = ret.getString("highPrice");
     String lowPrice = ret.getString("lowPrice");
     String supplier = ret.getString("supplier");
     String standardCountry = ret.getString("standardCountry");
     String brand = ret.getString("brand");
     String brandEn = ret.getString("brandEn");
     String busRegion = ret.getString("busRegion");
     String grossWeight = ret.getString("grossWeight");
     String netWeight = ret.getString("netWeight");
     System.out.println();
     
     StringBuffer error = new StringBuffer();
     if (codeTs.equals("")) {
       error.append("行邮税号codeTs不能为空且长度为8");
     } else if (codeTs.length() != 8) {
       error.append("行邮税号codeTs不能为空且长度为8");
     } 
     if (skuCode.equals("")) {
       error.append("申报商品 SKU skuCode不能为空");
     }
     if (goodsName.equals("")) {
       error.append("商品中文名 goodsName不能为空");
     }
     if (goodsNameEn.equals("")) {
       error.append("商品英文名 goodsNameEn不能为空");
     }
     if (goodsDesc.equals("")) {
       error.append("商品描述 goodsDesc不能为空");
     }
     if (goodsComposition.equals("")) {
       error.append("商品成份 goodsComposition不能为空");
     }
     if (goodsFrom.equals("")) {
       error.append("商品产地 goodsFrom不能为空");
     }
     if (goodsSpec.equals("")) {
       error.append("商品规格 goodsSpec不能为空");
     }
     if (goodsModel.equals("")) {
       error.append("商品型号goodsModel不能为空");
     }
     if (hsCode.equals("")) {
       error.append("商品 hsCode不能为空");
     }
     if (goodsUnit.equals("")) {
       error.append("单位goodsUnit不能为空且长度为3");
     } else if (goodsUnit.length() != 3) {
       error.append("单位goodsUnit不能为空且长度为3");
     } 
     if (firstUnit.equals("")) {
       error.append("单位firstUnit不能为空且长度为3");
     } else if (firstUnit.length() != 3) {
       error.append("单位firstUnit不能为空且长度为3");
     }     
     if (highPrice.equals("")) {
       error.append("商品最高价 highPrice不能为空");
     }
     if (lowPrice.equals("")) {
       error.append("低价lowPrice不能为空");
     }
     if (supplier.equals("")) {
       error.append("供应商 supplier不能为空");
     }
     if (brand.equals("")) {
       error.append("品牌中文名brand不能为空");
     }
     if (brandEn.equals("")) {
       error.append("品牌英文名brandEn不能为空");
     }
     if (busRegion.equals("")) {
       error.append("首次进口区域 busRegion不能为空");
     }
     if (grossWeight.equals("")) {
       error.append("产品毛重grossWeight不能为空");
     }
     if (netWeight.equals("")) {
       error.append("产品净重 netWeight不能为空");
     }
     
     if (error.length() > 4) {
       return error.toString();
     }
 
     
     SzqgItem szqgitem = new SzqgItem();
     szqgitem.setSkuCode(skuCode);
     szqgitem.setCodeTs(codeTs);
     szqgitem.setBarCode(barCode);
     szqgitem.setGoodsName(goodsName);
     szqgitem.setGoodsNameEn(goodsNameEn);
     szqgitem.setGoodsDesc(goodsDesc);
     szqgitem.setGoodsComposition(goodsComposition);
     szqgitem.setGoodsFrom(goodsFrom);
     szqgitem.setGoodsSpec(goodsSpec);
     szqgitem.setGoodsModel(goodsModel);
     szqgitem.setHsCode(hsCode);
     szqgitem.setGoodsUnit(goodsUnit);
     szqgitem.setFirstUnit(firstUnit);
     szqgitem.setSecondUnit(secondUnit);
     szqgitem.setHighPrice(highPrice);
     szqgitem.setLowPrice(lowPrice);
     szqgitem.setSupplier(supplier);
     szqgitem.setStandardCountry(standardCountry);
     szqgitem.setBrand(brand);
     szqgitem.setBrandEn(brandEn);
     szqgitem.setBusRegion(busRegion);
     szqgitem.setGrossWeight(grossWeight);
     szqgitem.setNetWeight(netWeight);
     ItemList.add(szqgitem);
 
 
     
     if (this.szqgItemDao.findBySku(szqgitem).size() > 0) {
       return String.valueOf(skuCode) + "商品已存在";
     }
 
     
     int count2 = this.szqgItemDao.batchInsert(ItemList);
 
     
     System.out.println("count2=" + count2);
     
     return "0";
   }
   
   public GeneralResult importAgentItem(int id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read_Item(string);
     List<SzqgItem> list = new ArrayList<>();
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
         
         String coCode = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商户备案编码没有输入！<br>");
           break;
         } 
         coCode = nowRowData[0].toString();
         
         String skuCode = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：申报商品 SKU没有输入！<br>");
           break;
         } 
         skuCode = nowRowData[1].toString();
         
         String codeTs = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品归类码没有输入！<br>");
           break;
         } 
         codeTs = nowRowData[2].toString();
         
         String barCode = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品条形码没有输入！<br>");
           break;
         } 
         barCode = nowRowData[3].toString();
         
         String goodsName = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品中文名没有输入！<br>");
           break;
         } 
         goodsName = nowRowData[4].toString();
         
         String goodsNameEn = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品英文名没有输入！<br>");
           break;
         } 
         goodsNameEn = nowRowData[5].toString();
 
         
         String goodsDesc = null;
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品描述没有输入！<br>");
           break;
         } 
         goodsDesc = nowRowData[6].toString();
         
         String goodsComposition = null;
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品成份没有输入！<br>");
           break;
         } 
         goodsComposition = nowRowData[7].toString();
 
         
         String goodsFrom = "";
         if (nowRowData[8] != null && StringUtils.isEmpty(nowRowData[8].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品产地（编码）没有输入！<br>");
           break;
         } 
         goodsFrom = nowRowData[8].toString();
 
         
         String goodsSpec = null;
         if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品规格没有输入！<br>");
           break;
         } 
         goodsSpec = nowRowData[9].toString();
 
         
         String goodsModel = null;
         if (nowRowData[10] == null || StringUtils.isEmpty(nowRowData[10].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品型号没有输入！<br>");
           break;
         } 
         goodsModel = nowRowData[10].toString();
         
         String hsCode = null;
         if (nowRowData[11] == null || StringUtils.isEmpty(nowRowData[11].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：hsCode没有输入！<br>");
           break;
         } 
         hsCode = nowRowData[11].toString();
 
         
         String goodsUnit = null;
         if (nowRowData[12] == null || StringUtils.isEmpty(nowRowData[12].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：单位没有输入！<br>");
           break;
         } 
         goodsUnit = nowRowData[12].toString();
         
         String firstUnit = null;
         if (nowRowData[13] == null || StringUtils.isEmpty(nowRowData[13].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：第一计量单位没有输入！<br>");
           break;
         } 
         firstUnit = nowRowData[13].toString();
         
         String secondUnit = null;
         if (nowRowData[14] == null || StringUtils.isEmpty(nowRowData[14].toString().trim())) {
           secondUnit = "";
         } else {
           secondUnit = nowRowData[14].toString();
         } 
 
         
         String highPrice = null;
         if (nowRowData[15] == null || StringUtils.isEmpty(nowRowData[15].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品最高价没有输入！<br>");
           break;
         } 
         highPrice = nowRowData[15].toString();
         
         String lowPrice = null;
         if (nowRowData[16] == null || StringUtils.isEmpty(nowRowData[16].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品最低价没有输入！<br>");
           break;
         } 
         lowPrice = nowRowData[16].toString();
 
         
         String standardCountry = null;
         if (nowRowData[17] == null || StringUtils.isEmpty(nowRowData[17].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：适用标准国别（编号）没有输入！<br>");
           break;
         } 
         standardCountry = nowRowData[17].toString();
 
         
         String brand = null;
         if (nowRowData[18] == null || StringUtils.isEmpty(nowRowData[18].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：品牌中文名称没有输入！<br>");
           break;
         } 
         brand = nowRowData[18].toString();
         
         String brandEn = null;
         if (nowRowData[19] == null || StringUtils.isEmpty(nowRowData[19].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：品牌英文名称没有输入！<br>");
           break;
         } 
         brandEn = nowRowData[19].toString();
         
         String busRegion = null;
         if (nowRowData[20] == null || StringUtils.isEmpty(nowRowData[20].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：首次进口区域没有输入！<br>");
           break;
         } 
         busRegion = nowRowData[20].toString();
         
         String supplier = null;
         if (nowRowData[21] == null || StringUtils.isEmpty(nowRowData[21].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：供应商没有输入！<br>");
           break;
         } 
         supplier = nowRowData[21].toString();
 
         
         SzqgItem i = new SzqgItem();
         i.setCoCode(coCode);
         i.setSkuCode(skuCode);
         i.setCodeTs(codeTs);
         i.setBarCode(barCode);
         i.setGoodsName(goodsName);
         i.setGoodsNameEn(goodsNameEn);
         i.setGoodsDesc(goodsDesc);
         i.setGoodsComposition(goodsComposition);
         i.setGoodsFrom(goodsFrom);
         i.setGoodsSpec(goodsSpec);
         i.setGoodsModel(goodsModel);
         i.setHsCode(hsCode);
         i.setGoodsUnit(goodsUnit);
         i.setFirstUnit(firstUnit);
         i.setSecondUnit(secondUnit);
         i.setHighPrice(highPrice);
         i.setLowPrice(lowPrice);
         i.setStandardCountry(standardCountry);
         i.setBrand(brand);
         i.setBrandEn(brandEn);
         i.setBusRegion(busRegion);
         i.setSupplier(supplier);
         
         i.setCreateDateTime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
         i.setUserId((new StringBuilder(String.valueOf(id))).toString());
         
         list.add(i);
       } 
       
       Map<String, Integer> checkMap = new HashMap<>();
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0)
         {
           
           this.szqgItemDao.insertBatch(list);
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


