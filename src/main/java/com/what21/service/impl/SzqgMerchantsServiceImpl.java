 package com.what21.service.impl;
 
 import com.what21.dao.SzqgMerchantsDao;
 import com.what21.model.SzqgMerchants;
 import com.what21.service.SzqgMerchantsService;
 import com.what21.tools.Tools;
 import com.what21.util.ExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class SzqgMerchantsServiceImpl
   implements SzqgMerchantsService
 {
   @Autowired
   private SzqgMerchantsDao szqgMerchantsDao;
   
   public List<SzqgMerchants> findAll(Map<String, Object> map) {
     return this.szqgMerchantsDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.szqgMerchantsDao.count(map);
   }
 
   
   public GeneralResult importAgentMerchants(int id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read_Item(string);
     List<SzqgMerchants> list = new ArrayList<>();
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
         
         String abroadCoName = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户名没有输入！<br>");
           break;
         } 
         abroadCoName = nowRowData[0].toString();
         
         String abroadLowMan = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户注册人没有输入！<br>");
           break;
         } 
         abroadLowMan = nowRowData[1].toString();
         
         String country = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户注册国编码没有输入！<br>");
           break;
         } 
         country = nowRowData[2].toString();
         
         String abroadCoChannel = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户渠道类型没有输入！<br>");
           break;
         } 
         abroadCoChannel = nowRowData[3].toString();
         
         String abroadContacts = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户联系人没有输入！<br>");
           break;
         } 
         abroadContacts = nowRowData[4].toString();
         
         String abroadTel = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户联系人电话没有输入！<br>");
           break;
         } 
         abroadTel = nowRowData[5].toString();
 
         
         String abroadEmail = null;
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户联系人邮箱没有输入！<br>");
           break;
         } 
         abroadEmail = nowRowData[6].toString();
         
         String abroadRegAddr = null;
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户注册地址没有输入！<br>");
           break;
         } 
         abroadRegAddr = nowRowData[7].toString();
 
         
         String abroadOfficeAddr = "";
         if (nowRowData[8] == null || StringUtils.isEmpty(nowRowData[8].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：境外商户办公地址没有输入！<br>");
           break;
         } 
         abroadOfficeAddr = nowRowData[8].toString();
         
         String abroadNo = null;
         if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：注册国相关贸易资质编号没有输入！<br>");
           break;
         } 
         abroadNo = nowRowData[9].toString();
 
 
         
         SzqgMerchants i = new SzqgMerchants();
         System.out.println("abroadCoName=" + abroadCoName);
         i.setAbroadCoName(abroadCoName);
         i.setAbroadLowMan(abroadLowMan);
         i.setCountry(country);
         i.setAbroadCoChannel(abroadCoChannel);
         i.setAbroadContacts(abroadContacts);
         i.setAbroadTel(abroadTel);
         i.setAbroadEmail(abroadEmail);
         i.setAbroadRegAddr(abroadRegAddr);
         i.setAbroadOfficeAddr(abroadOfficeAddr);
         i.setAbroadNo(abroadNo);
         
         i.setUserId((new StringBuilder(String.valueOf(id))).toString());
         i.setCreateDateTime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
 
         
         list.add(i);
       } 
 
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0)
         {
           this.szqgMerchantsDao.insertBatch(list);
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


