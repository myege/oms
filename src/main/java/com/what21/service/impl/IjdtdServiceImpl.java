 package com.what21.service.impl;
 
 import com.what21.dao.IjdtdDao;
 import com.what21.dao.IjdtditemDao;
 import com.what21.model.Ijdtd;
 import com.what21.model.Ijdtditem;
 import com.what21.service.IjdtdService;
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
 public class IjdtdServiceImpl
   implements IjdtdService
 {
   @Autowired
   private IjdtdDao ijdtdDao;
   @Autowired
   private IjdtditemDao ijdtditemDao;
   
   public List<Ijdtd> findAll(Map<String, Object> map) {
     return this.ijdtdDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.ijdtdDao.count(map);
   }
 
   
   public GeneralResult importTd(int id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read_Item(string);
     List<Ijdtd> list = new ArrayList<>();
     List<Ijdtditem> listitem = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size() - 1;
     boolean firstRow = true;
     Ijdtd i = new Ijdtd();
     try {
       for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
         if (firstRow) {
           firstRow = false;
           continue;
         } 
         Object[] nowRowData = me.getValue();
         int rowNum = ((Integer)me.getKey()).intValue() + 1;
         
         String ladNo = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：提单号没有输入！<br>");
           break;
         } 
         ladNo = nowRowData[0].toString();
         
         String poNo = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：采购单号没有输入！<br>");
           break;
         } 
         poNo = nowRowData[1].toString();
         
         String intryWhere = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：入关地点没有输入！<br>");
           break;
         } 
         intryWhere = nowRowData[2].toString();
         
         String intryType = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：入关方式没有输入！<br>");
           break;
         } 
         intryType = nowRowData[3].toString();
         
         String transType = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：运输方式没有输入！<br>");
           break;
         } 
         transType = nowRowData[4].toString();
         
         String billWeight = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：计费重量没有输入！<br>");
           break;
         } 
         billWeight = nowRowData[5].toString();
 
         
         String interTransName = null;
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：承运方没有输入！<br>");
           break;
         } 
         interTransName = nowRowData[6].toString();
         
         String cabinetFlag = null;
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：整箱拼箱标识没有输入！<br>");
           break;
         } 
         cabinetFlag = nowRowData[7].toString();
 
         
         String cabinetType = null;
         if (nowRowData[8] == null || StringUtils.isEmpty(nowRowData[8].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：柜型没有输入！<br>");
           break;
         } 
         cabinetType = nowRowData[8].toString();
 
         
         String actualWeight = null;
         if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：实际重量没有输入！<br>");
           break;
         } 
         actualWeight = nowRowData[9].toString();
         
         String totalCabinet = null;
         if (nowRowData[10] == null || StringUtils.isEmpty(nowRowData[10].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：柜数没有输入！<br>");
           break;
         } 
         totalCabinet = nowRowData[10].toString();
         
         Ijdtditem it = new Ijdtditem();
         it.setCabinetFlag(cabinetFlag);
         it.setCabinetType(cabinetType);
         it.setLadNo(ladNo);
         it.setActualWeight(actualWeight);
         it.setTotalCabinet(totalCabinet);
         listitem.add(it);
         
         i.setLadNo(ladNo);
         i.setPoNo(poNo);
         i.setIntryType(intryType);
         i.setTransType(transType);
         i.setIntryWhere(intryWhere);
         i.setBillWeight(billWeight);
         i.setInterTransName(interTransName);
         i.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
       } 
 
       
       list.add(i);
       Map<String, Integer> checkMap = new HashMap<>();
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0) {
           
           this.ijdtdDao.insertBatch(list);
           this.ijdtditemDao.insertBatch(listitem);
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
   public int deleteTd(String[] ids) {
     byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String ladNo = arrayOfString[b];
       this.ijdtdDao.delete(ladNo);
       this.ijdtditemDao.delete(ladNo);
       b++; }
     
     return 1;
   }
 }


