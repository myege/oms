 package com.what21.service.impl;
 
 import com.what21.dao.SzqgAgentDao;
 import com.what21.model.SzqgAgent;
 import com.what21.service.SzqgAgentService;
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
 public class SzqgAgentServiceImpl
   implements SzqgAgentService
 {
   @Autowired
   private SzqgAgentDao szqgAgentDao;
   
   public List<SzqgAgent> findAll(Map<String, Object> map) {
     return this.szqgAgentDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.szqgAgentDao.count(map);
   }
 
   
   public GeneralResult importAgent(int id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read_Item(string);
     List<SzqgAgent> list = new ArrayList<>();
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
         
         String lawName = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：法人姓名没有输入！<br>");
           break;
         } 
         lawName = nowRowData[0].toString();
         
         String fullName = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业注册名称(全)没有输入！<br>");
           break;
         } 
         fullName = nowRowData[1].toString();
         
         String socialCreditCode = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：统一社会信用代码没有输入！<br>");
           break;
         } 
         socialCreditCode = nowRowData[2].toString();
         
         String regCo = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业注册名称(简）没有输入！<br>");
           break;
         } 
         regCo = nowRowData[3].toString();
         
         String customsCode = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：注册海关代码没有输入！<br>");
           break;
         } 
         customsCode = nowRowData[4].toString();
         
         String tradeCo = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：海关备案编号没有输入！<br>");
           break;
         } 
         tradeCo = nowRowData[5].toString();
 
         
         String copEndDate = null;
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业通关期限没有输入！<br>");
           break;
         } 
         copEndDate = nowRowData[6].toString();
         
         String businessCode = null;
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业身份没有输入！<br>");
           break;
         } 
         businessCode = nowRowData[7].toString();
 
         
         String addrCo = "";
         if (nowRowData[8] != null && StringUtils.isNotEmpty(nowRowData[8].toString().trim())) {
           addrCo = nowRowData[8].toString();
         }
         
         String busiType = null;
         if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：行业种类没有输入！<br>");
           break;
         } 
         busiType = nowRowData[9].toString();
 
         
         String enFullCo = null;
         if (nowRowData[10] == null || StringUtils.isEmpty(nowRowData[10].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业英文名称没有输入！<br>");
           break;
         } 
         enFullCo = nowRowData[10].toString();
         
         String enAddrCo = null;
         if (nowRowData[11] == null || StringUtils.isEmpty(nowRowData[11].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业英文地址没有输入！<br>");
           break;
         } 
         enAddrCo = nowRowData[11].toString();
 
         
         String rgDate = null;
         if (nowRowData[12] == null || StringUtils.isEmpty(nowRowData[12].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业注册日期没有输入！<br>");
           break;
         } 
         rgDate = nowRowData[12].toString();
         
         String valiDate = null;
         if (nowRowData[13] == null || StringUtils.isEmpty(nowRowData[13].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：企业有效日期没有输入！<br>");
           break;
         } 
         valiDate = nowRowData[13].toString();
         
         String idNumber = null;
         if (nowRowData[14] == null || StringUtils.isEmpty(nowRowData[14].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：身份证号码没有输入！<br>");
           break;
         } 
         idNumber = nowRowData[14].toString();
         
         String lawNameTel = null;
         if (nowRowData[15] == null || StringUtils.isEmpty(nowRowData[15].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：法人联系电话没有输入！<br>");
           break;
         } 
         lawNameTel = nowRowData[15].toString();
         
         String dxpId = null;
         if (nowRowData[16] == null || StringUtils.isEmpty(nowRowData[16].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：dxpId没有输入！<br>");
           break;
         } 
         dxpId = nowRowData[16].toString();
 
 
         
         SzqgAgent i = new SzqgAgent();
         i.setLawName(lawName);
         
         i.setLawNameTel(lawNameTel);
         i.setFullName(fullName);
         i.setSocialCreditCode(socialCreditCode);
         i.setRegCo(regCo);
         i.setCustomsCode(customsCode);
         i.setTradeCo(tradeCo);
         i.setCopEndDate(copEndDate);
         i.setBusinessCode(businessCode);
         i.setAddrCo(addrCo);
         i.setBusiType(busiType);
         i.setEnFullCo(enFullCo);
         i.setEnAddrCo(enAddrCo);
         i.setRgDate(rgDate);
         i.setValiDate(valiDate);
         i.setIdNumber(idNumber);
         i.setLawNameTel(lawNameTel);
         i.setDxpId(dxpId);
         i.setUserId((new StringBuilder(String.valueOf(id))).toString());
         i.setCreateDateTime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
 
         
         list.add(i);
       } 
       
       Map<String, Integer> checkMap = new HashMap<>();
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0)
         {
           
           this.szqgAgentDao.insertBatch(list);
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


