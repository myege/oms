 package com.what21.service.impl;
 
 import com.what21.dao.IjdcgdysDao;
 import com.what21.model.Ijdcgdys;
 import com.what21.service.IjdcgdysService;
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
 public class IjdcgdysServiceImpl
   implements IjdcgdysService
 {
   @Autowired
   private IjdcgdysDao ijdcgdysDao;
   
   public List<Ijdcgdys> findAll(Map<String, Object> map) {
     return this.ijdcgdysDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.ijdcgdysDao.count(map);
   }
 
   
   public GeneralResult importCgdys(int id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read_Item(string);
     List<Ijdcgdys> list = new ArrayList<>();
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
         
         String poNo = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：京东采购单没有输入！<br>");
           break;
         } 
         poNo = nowRowData[0].toString();
         
         String corrDocNo = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：报关单号没有输入！<br>");
           break;
         } 
         corrDocNo = nowRowData[1].toString();
         
         String corrDocTime = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：报关时间没有输入！<br>");
           break;
         } 
         corrDocTime = nowRowData[2].toString();
         
         String declNo = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：报检单号没有输入！<br>");
           break;
         } 
         declNo = nowRowData[3].toString();
         
         String declTime = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：报检时间 没有输入！<br>");
           break;
         } 
         declTime = nowRowData[4].toString();
         
         String provider = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：服务商编号没有输入！<br>");
           break;
         } 
         provider = nowRowData[5].toString();
 
 
         
         Ijdcgdys i = new Ijdcgdys();
         i.setPoNo(poNo);
         i.setCorrDocNo(corrDocNo);
         i.setCorrDocTime(corrDocTime);
         i.setDeclNo(declNo);
         i.setDeclTime(declTime);
         i.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
         i.setProvider(provider);
         list.add(i);
       } 
 
       
       Map<String, Integer> checkMap = new HashMap<>();
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0)
         {
           
           this.ijdcgdysDao.insertBatch(list);
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


