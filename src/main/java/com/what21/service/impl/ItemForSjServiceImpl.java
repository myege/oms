 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.ItemForSjDao;
 import com.what21.model.ItemForSj;
 import com.what21.service.ItemForSjService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.kjybd.PushtoWQ;
 import java.io.File;
 import java.io.UnsupportedEncodingException;
 import java.net.URLEncoder;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
 @Service
 public class ItemForSjServiceImpl
   implements ItemForSjService
 {
   @Autowired
   private ItemForSjDao itemForSjDao;
   
   public List<ItemForSj> findAll(Map<String, Object> map) {
     return this.itemForSjDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.itemForSjDao.count(map);
   }
 
   
   public GeneralResult infor(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
     
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     boolean firstRow = true;
     StringBuffer message = new StringBuffer();
     int successData = 0;
     int totalData = items.entrySet().size() - 1;
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       Object data1 = nowRowData[0];
       String goods_sn = "";
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         goods_sn = data1.toString();
       }
       else {
         
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品货号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品名称没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：hs编码没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：成交币制没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：申报计量单位没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：第一单位没有输入！<br>");
         
         continue;
       } 
       Object data7 = nowRowData[6];
       
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品规格、型号没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品分类没有输入！<br>");
         
         continue;
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品品牌没有输入！<br>");
         
         continue;
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：本店售价没有输入！<br>");
         continue;
       } 
       Object data12 = nowRowData[11];
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品描述没有输入！<br>");
         continue;
       } 
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品图片没有输入！<br>");
         continue;
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品库存没有输入！<br>");
 
         
         continue;
       } 
       
       ItemForSj isData = this.itemForSjDao.findByInF(goods_sn);
       
       if (isData == null) {
         ItemForSj itemForSj = new ItemForSj();
         itemForSj.setGoods_sn(data1.toString());
         itemForSj.setGoods_name(data2.toString());
         itemForSj.setCodeTs(data3.toString());
         itemForSj.setTradeCurr(data4.toString());
         itemForSj.setGoodsUnit(data5.toString());
         itemForSj.setFirstUnit(data6.toString());
         if (data7 != null) {
           itemForSj.setSecondUnit(data7.toString());
         }
         itemForSj.setGoodsModel(data8.toString());
         itemForSj.setCat_id(data9.toString());
         itemForSj.setBrand_id(data10.toString());
         itemForSj.setShop_price(data11.toString());
         itemForSj.setGoods_desc(data12.toString());
         itemForSj.setGoods_thumb(data13.toString());
         itemForSj.setGoods_number(data14.toString());
         this.itemForSjDao.insertIn(itemForSj);
       } else {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：数据重复！<br>");
         
         continue;
       } 
       successData++;
     } 
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
 
   
   public List<ItemForSj> findintfo(Map<String, Object> pageMap) {
     return this.itemForSjDao.findintfo(pageMap);
   }
 
   
   public ItemForSj findByInF(String id) {
     return this.itemForSjDao.findByInF(id);
   }
 
   
   public void insertIn(ItemForSj itemForSj) {
     this.itemForSjDao.insertIn(itemForSj);
   }
 
 
   
   public int intfodelete(String id) {
     return this.itemForSjDao.intfodelete(id);
   }
 
   
   public int intfoedit(ItemForSj itemForSj) {
     return this.itemForSjDao.intfoedit(itemForSj);
   }
 
 
 
   
   public String ifsjsj(String ids) {
     return this.itemForSjDao.ifsjsj(ids);
   }
 
   
   public List<ItemForSj> hyjStatus(String merchantNum) {
     return this.itemForSjDao.hyjStatus(merchantNum);
   }
   
   public int updateStatus(String ids) {
     String[] idArr = ids.split(",");
     int result = -1;
     String resout = null; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       ItemForSj itemForSj = this.itemForSjDao.findById(id);
 
 
       
       String strJson = JSON.toJSONString(itemForSj);
       JSONObject ll = (JSONObject)JSONObject.parse(strJson);
       
       ll.remove("id");
       String strJson2 = JSON.toJSONString(ll);
       
       strJson2 = "[" + strJson2 + "]";
       String newstr = "";
       try {
         newstr = Base64.encodeBase64String(strJson2.getBytes("utf-8"));
       } catch (UnsupportedEncodingException e) {
         
         e.printStackTrace();
       } 
 
       
       String parm = "auth=6d1df3f2cb5f571ecc1d413e02785331&data=" + URLEncoder.encode(newstr);
       resout = PushtoWQ.sendPost2("http://www.haidai5.com/tools/import.php", parm).replace("\"", "");
       
       if (resout.contains("successNum:1")) {
         ItemForSj itorSj = new ItemForSj();
         itorSj.setId(Integer.parseInt(id));
         itorSj.setIs_on_sale("1");
         result = this.itemForSjDao.updatefsj(itorSj);
       } 
       b++; }
     
     return result;
   }
 
   
   public ItemForSj findById(String id) {
     return this.itemForSjDao.findById(id);
   }
 
   
   public int updatefsj(ItemForSj itorSj) {
     return this.itemForSjDao.updatefsj(itorSj);
   }
 }


