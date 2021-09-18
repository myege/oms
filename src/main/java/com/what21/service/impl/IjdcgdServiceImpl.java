 package com.what21.service.impl;
 
 import com.what21.dao.IjdcgdDao;
 import com.what21.dao.IjdcgditemDao;
 import com.what21.model.Ijdcgd;
 import com.what21.model.Ijdcgditem;
 import com.what21.service.IjdcgdService;
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
 public class IjdcgdServiceImpl
   implements IjdcgdService
 {
   @Autowired
   private IjdcgdDao ijdcgdDao;
   @Autowired
   private IjdcgditemDao ijdcgditemDao;
   
   public List<Ijdcgd> findAll(Map<String, Object> map) {
     return this.ijdcgdDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.ijdcgdDao.count(map);
   }
 
   
   public GeneralResult importCgd(int id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read_Item(string);
     List<Ijdcgd> list = new ArrayList<>();
     List<Ijdcgditem> listitem = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size() - 1;
     boolean firstRow = true;
     Ijdcgd i = new Ijdcgd();
     try {
       for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
         if (firstRow) {
           firstRow = false;
           continue;
         } 
         Object[] nowRowData = me.getValue();
         int rowNum = ((Integer)me.getKey()).intValue() + 1;
         
         String poNo = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：京东采购单没有输入！<br>");
           break;
         } 
         poNo = nowRowData[0].toString();
         
         String serialNo = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：服务商系统流水号没有输入！<br>");
           break;
         } 
         serialNo = nowRowData[1].toString();
         
         String billNo = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：提单号没有输入！<br>");
           break;
         } 
         billNo = nowRowData[2].toString();
         
         String provider = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：服务商简码没有输入！<br>");
           break;
         } 
         provider = nowRowData[3].toString();
         
         String goodsNo = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：京东SKU没有输入！<br>");
           break;
         } 
         goodsNo = nowRowData[4].toString();
         
         String sellerRecord = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品货号没有输入！<br>");
           break;
         } 
         sellerRecord = nowRowData[5].toString();
 
         
         String goodsName = null;
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品名称没有输入！<br>");
           break;
         } 
         goodsName = nowRowData[6].toString();
         
         String amount = null;
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：数量没有输入！<br>");
           break;
         } 
         amount = nowRowData[7].toString();
 
         
         Ijdcgditem it = new Ijdcgditem();
         it.setPoNo(poNo);
         it.setSellerRecord(sellerRecord);
         it.setGoodsName(goodsName);
         it.setGoodsNo(goodsNo);
         it.setAmount(amount);
         listitem.add(it);
 
         
         i.setPoNo(poNo);
         i.setSerialNo(serialNo);
         i.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
         i.setProvider(provider);
         i.setBillNo(billNo);
       } 
       
       list.add(i);
       Map<String, Integer> checkMap = new HashMap<>();
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0) {
           
           this.ijdcgdDao.insertBatch(list);
           this.ijdcgditemDao.insertBatch(listitem);
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


