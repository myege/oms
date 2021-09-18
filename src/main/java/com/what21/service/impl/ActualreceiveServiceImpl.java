 package com.what21.service.impl;
 
 import com.what21.dao.ActualreceiveDao;
 import com.what21.dao.ActualreceiveSkuDao;
 import com.what21.model.Actualreceive;
 import com.what21.model.ActualreceiveCustom;
 import com.what21.model.ActualreceiveQueryVo;
 import com.what21.model.ActualreceiveSku;
 import com.what21.service.ActualreceiveService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.tsjgq.postxml;
 import java.io.File;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ActualreceiveServiceImpl
   implements ActualreceiveService
 {
   @Autowired
   private ActualreceiveDao actualreceiveDao;
   @Autowired
   private ActualreceiveSkuDao actualreceiveSkuDao;
   
   public List<Actualreceive> findAll(Map<String, Object> pageMap) {
     return this.actualreceiveDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.actualreceiveDao.count(pageMap);
   }
 
   
   public int actdelete(String id) {
     return this.actualreceiveDao.actdelete(id);
   }
 
   
   public List<Actualreceive> findact(Map<String, Object> pageMap) {
     return this.actualreceiveDao.findact(pageMap);
   }
 
   
   public GeneralResult inmera(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
     boolean firstRow = true;
     StringBuffer message = new StringBuffer();
     int successData = 0;
     int totalData = items.entrySet().size() - 1;
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     
     Map<String, ActualreceiveSku> actualreceiveSkuMap = new HashMap<>();
     List<Actualreceive> actualreceiveList = new ArrayList<>();
     
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       Object data1 = nowRowData[0];
       String id = "";
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         id = data1.toString();
       } else {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料号没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：项号没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料件性质没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：实收数量没有输入！<br>");
         
         continue;
       } 
       ActualreceiveSku isData = this.actualreceiveSkuDao.findByA(id);
 
 
       
       if (isData == null) {
         
         ActualreceiveSku actualreceiveSku = new ActualreceiveSku();
         
         actualreceiveSku.setJobFormId(data1.toString());
         SimpleDateFormat dnf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         actualreceiveSku.setDatatime(dnf.format(new Date()));
         actualreceiveSku.setUserId(userId);
 
         
         this.actualreceiveSkuDao.insert(actualreceiveSku);
       } 
       
       Actualreceive actualreceive = new Actualreceive();
       actualreceive.setJobFormId(data1.toString().trim());
       actualreceive.setSourceNo(data2.toString().trim());
       actualreceive.setItemNo(data3.toString().trim());
       actualreceive.setItemType(data4.toString().trim());
       actualreceive.setReceiveAmount(data5.toString().trim());
       actualreceiveList.add(actualreceive);
 
 
       
       successData++;
     } 
 
 
     
     this.actualreceiveDao.aInserta(actualreceiveList);
     
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
   
   public String actts(String ids) {
     String[] idArr = ids.split(",");
     String result = null;
     ActualreceiveSku ac = new ActualreceiveSku();
     StringBuffer resultMessage = new StringBuffer(); byte b; int i;
     String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       
       Map<String, Object> Map = new HashMap<>();
       Map.put("JobFormId", id);
       List<Actualreceive> actualreceive = this.actualreceiveDao.actadd(Map);
       
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("ACTUAL_RECEIVE");
       
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("actualReceiveInfo");
       Element element4 = element3.addElement("manSign");
       element4.addElement("companyCode").addText("3316W60020");
       element4.addElement("businessNo").addText(id);
       element4.addElement("businessType").addText("ACTUAL_RECEIVE");
       element4.addElement("declareType").addText("1");
       element4.addElement("note").addText("1");
       Element element5 = element3.addElement("manActualReceiveDetailList");
       
       for (int j = 0; j < actualreceive.size(); j++) {
         Element element6 = element5.addElement("manActualReceiveDetail");
         element6.addElement("jobFormId").addText(((Actualreceive)actualreceive.get(j)).getJobFormId());
         element6.addElement("sourceNo").addText(((Actualreceive)actualreceive.get(j)).getSourceNo());
         element6.addElement("itemNo").addText(((Actualreceive)actualreceive.get(j)).getItemNo());
         element6.addElement("itemType").addText(((Actualreceive)actualreceive.get(j)).getItemType());
         element6.addElement("receiveAmount").addText(((Actualreceive)actualreceive.get(j)).getReceiveAmount());
       } 
 
 
       
       String ret = postxml.toZG_sjrk(document.asXML());
       
       if (ret.contains(" <processResult>S</processResult>") || ret.contains("<processResult>R</processResult>")) {
         byte b1; int k; String[] arrayOfString; for (k = (arrayOfString = idArr).length, b1 = 0; b1 < k; ) { String id3 = arrayOfString[b1];
           ac.setFlag("success");
           ac.setJobFormId(id3);
           this.actualreceiveSkuDao.aflag(ac); b1++; }
         
         result = "1";
       }
       else {
         
         ret = ret.replaceAll("/", "");
         String[] abc = ret.split("<information>");
         
         for (int k = 0; k < abc.length; k++) {
           
           if (k == 1) {
             resultMessage.append(String.valueOf(abc[k]) + "<br>");
           }
         } 
       } 
       
       b++; }
     
     if (!resultMessage.toString().equals("")) {
       
       for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id2 = arrayOfString1[b];
         ac.setFlag("处理失败");
         ac.setJobFormId(id2);
         this.actualreceiveSkuDao.aflag(ac);
         b++; }
       
       result = resultMessage.toString();
     } 
     return result;
   }
 
 
   
   public int aflag(Actualreceive actualreceive) {
     return this.actualreceiveDao.aflag(actualreceive);
   }
 
   
   public List<Actualreceive> findacId(String jobFormId) {
     return this.actualreceiveDao.findacId(jobFormId);
   }
 
   
   public List<Actualreceive> actadd(Map<String, Object> map) {
     return this.actualreceiveDao.actadd(map);
   }
 
 
   
   public int countSku(Map<String, Object> pageMap) {
     return this.actualreceiveDao.countSku(pageMap);
   }
 
   
   public int count2(ActualreceiveQueryVo actualreceiveQueryVo) {
     return this.actualreceiveDao.count2(actualreceiveQueryVo);
   }
 
 
   
   public List<ActualreceiveCustom> findAll2(ActualreceiveQueryVo actualreceiveQueryVo) {
     return this.actualreceiveDao.findAll2(actualreceiveQueryVo);
   }
 }


