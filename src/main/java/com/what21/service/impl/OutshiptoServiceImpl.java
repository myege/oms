 package com.what21.service.impl;
 
 import com.what21.dao.OutshiptoDao;
 import com.what21.model.Outshipto;
 import com.what21.service.OutshiptoService;
 import com.what21.util.ExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.kjybd.BOAOh;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OutshiptoServiceImpl
   implements OutshiptoService
 {
   @Autowired
   private OutshiptoDao outshiptoDao;
   
   public List<Outshipto> findAll(Outshipto vo) {
     return this.outshiptoDao.findAll(vo);
   }
 
   
   public List<Outshipto> findId(String vo) {
     return this.outshiptoDao.findId(vo);
   }
 
   
   public int findCount(Outshipto vo) {
     return this.outshiptoDao.findCount(vo);
   }
 
   
   public void delete(String ids) {
     this.outshiptoDao.delete(ids);
   }
 
   
   public void deleteSku(String billNo) {
     this.outshiptoDao.deleteSku(billNo);
   }
 
   
   public int update(Outshipto vo) {
     this.outshiptoDao.update(vo);
     return 0;
   }
 
 
   
   public void updateType(Outshipto vo) {}
 
 
   
   public GeneralResult implShip(String vo) {
     Map<String, String> checkMap = new HashMap<>();
     checkMap.put("logisticsNo", "''");
     checkMap.put("billNo", "''");
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read(vo);
     List<Outshipto> list = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(vo);
     if (delFile.exists())
       delFile.delete(); 
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
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业唯一编号没有输入！<br>");
           break;
         } 
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：监管场所经营人代码没有输入！<br>");
           break;
         } 
         if (nowRowData[2] != null && StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：监管场所经营人名没有输入！<br>");
           break;
         } 
         if (nowRowData[3] != null && StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：提运单没有输入！<br>");
           break;
         } 
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境内运输工具编号没有输入！<br>");
           break;
         } 
         if (nowRowData[5] != null && StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：物流企业代码没有输入！<br>");
           break;
         } 
         if (nowRowData[6] != null && StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：物流企业名没有输入！<br>");
           break;
         } 
         if (nowRowData[7] != null && StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：传输企业代码没有输入！<br>");
           break;
         } 
         if (nowRowData[8] != null && StringUtils.isEmpty(nowRowData[8].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：传输企业名没有输入！<br>");
           break;
         } 
         if (nowRowData[9] != null && StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：DXPID没有输入！<br>");
           break;
         } 
         if (nowRowData[10] != null && StringUtils.isEmpty(nowRowData[10].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：物流运单编号没有输入！<br>");
           
           break;
         } 
         Outshipto ship = new Outshipto();
         ship.setLogisticsname(nowRowData[6].toString());
         ship.setMsgcount("1");
         ship.setMsgseqno(Integer.valueOf(1));
         ship.setCopcode(nowRowData[7].toString());
         ship.setCopname(nowRowData[8].toString());
         ship.setDxpmode("DXP");
         ship.setDxpid(nowRowData[9].toString());
         ship.setType(0);
         ship.setType(0);
         ship.setDomestictrafno(nowRowData[4].toString());
         ship.setLogisticscode(nowRowData[5].toString());
         ship.setLoctno(nowRowData[1].toString());
         ship.setApptype("1");
         ship.setAppstatus("2");
         ship.setCustomscode("2916");
         ship.setCopno(nowRowData[0].toString());
         ship.setOperatorcode(nowRowData[1].toString());
         ship.setOperatorname(nowRowData[2].toString());
         ship.setIeflag("E");
         ship.setTrafmode("5");
         ship.setBillno(nowRowData[3].toString());
         ship.setLogisticsNo(nowRowData[10].toString());
         list.add(ship); byte b; int i; String[] arrayOfString;
         for (i = (arrayOfString = nowRowData[10].toString().split(",")).length, b = 0; b < i; ) { String str = arrayOfString[b];
           checkMap.put("logisticsNo", String.valueOf(checkMap.get("logisticsNo")) + ",'" + str + "'");
           b++; }
         
         checkMap.put("billNo", String.valueOf(checkMap.get("billNo")) + ",'" + nowRowData[3].toString() + "'");
       } 
       
       if (!message.toString().contains("导入") && 
         list.size() != 0) {
         List<String> findBillNo = this.outshiptoDao.findBillNo(checkMap);
         List<String> findLogisticsNo = this.outshiptoDao.findLogisticsNo(checkMap);
         if (findBillNo.size() != 0) {
           String billNo = "";
           String logisticsNo = "";
           for (String maps : findBillNo) {
             billNo = String.valueOf(billNo) + maps + ",";
           }
           for (String maps : findLogisticsNo) {
             logisticsNo = String.valueOf(logisticsNo) + maps + ",";
           }
           message.append("导入失败提运单重复：" + billNo);
           message.append("导入失败物流单重复：" + logisticsNo);
         } else {
           this.outshiptoDao.insert(list);
           message.append("导入成功" + total + "条！");
         } 
       } 
       
       result.setMessage(message.toString());
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
     } 
     return result;
   }
 
   
   public String geviShiup(String id) throws Exception {
     List<Outshipto> findId = this.outshiptoDao.findId(id);
     BOAOh.installXml(findId);
     this.outshiptoDao.updateType(id);
     return "1";
   }
 }


