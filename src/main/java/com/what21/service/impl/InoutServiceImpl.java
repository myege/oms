 package com.what21.service.impl;
 
 import com.what21.dao.InoutDao;
 import com.what21.model.Inout;
 import com.what21.model.InoutCustom;
 import com.what21.model.InoutPageQueryVo;
 import com.what21.model.LogInout;
 import com.what21.service.InoutService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.tsjgq.postxml;
 import java.io.File;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class InoutServiceImpl
   implements InoutService
 {
   @Autowired
   private InoutDao inoutDao;
   
   public List<Inout> findAll(Map<String, Object> pageMap) {
     return this.inoutDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.inoutDao.count(pageMap);
   }
 
   
   public int indelete(String id) {
     return this.inoutDao.indelete(id);
   }
 
   
   public List<Inout> findin(Map<String, Object> pageMap) {
     return this.inoutDao.findin(pageMap);
   }
 
   
   public GeneralResult inmeri(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
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
       String id = "";
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         id = data1.toString();
       } else {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：出入库记录流水号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：出入库记录编号没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：账册编号没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料号没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料件性质没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：出入库标志没有输入！<br>");
         
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：出入库数量没有输入！<br>");
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：出入库时间没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       String das9 = "";
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         das9 = "";
       } else {
         das9 = data9.toString().replaceAll(" ", "");
       } 
       Object data10 = nowRowData[9];
       
       String das = "";
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         das = "";
       } else {
         das = data10.toString().replaceAll(" ", "");
       } 
 
 
       
       Inout isData = this.inoutDao.findByI(id);
       if (isData == null) {
         Inout inout = new Inout();
         inout.setInOutSeq(data1.toString().replaceAll(" ", ""));
         inout.setInOutNo(data2.toString().replaceAll(" ", ""));
         inout.setManualId(data3.toString().replaceAll(" ", ""));
         inout.setSourceNo(data4.toString().replaceAll(" ", ""));
         inout.setItemType(data5.toString().replaceAll(" ", ""));
         inout.setInOutFlag(data6.toString().replaceAll(" ", ""));
         inout.setInOutAmount(data7.toString().replaceAll(" ", ""));
         inout.setInOutTime(data8.toString());
         inout.setWayBillNo(das9);
         inout.setJobFormId(das);
         inout.setUserId(userId);
         SimpleDateFormat dnf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         inout.setDatatime(dnf.format(new Date()));
         
         this.inoutDao.inserti(inout);
       } 
       
       File delFile = new File(path);
       if (delFile.exists()) {
         delFile.delete();
       }
       successData++;
     } 
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
   
   public String ints(int userId) {
     String result = "";
     List<Inout> inout = this.inoutDao.inadd();
 
     
     if (inout.size() == 0 || inout.isEmpty()) {
       result = "数据库不存在未推送或失败数据";
     } else {
       
       String ids = "";
       for (int i = 0; i < inout.size(); i++) {
         if (i == inout.size() - 1) {
           ids = String.valueOf(ids) + ((Inout)inout.get(i)).getId();
         } else {
           ids = String.valueOf(ids) + ((Inout)inout.get(i)).getId() + ",";
         } 
       } 
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("STOCK_IN_OUT");
       
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("stockInOutInfo");
       Element element4 = element3.addElement("manSign");
       element4.addElement("companyCode").addText("330156K004");
       element4.addElement("businessNo").addText("BusinessNo");
       element4.addElement("businessType").addText("STOCK_IN_OUT");
       element4.addElement("declareType").addText("1");
       element4.addElement("note").addText("");
       Element element5 = element3.addElement("manStockInOutDetailList");
 
       
       for (int j = 0; j < inout.size(); j++) {
         Element element6 = element5.addElement("manStockInOutDetail");
         element6.addElement("inOutSeq").addText(((Inout)inout.get(j)).getInOutSeq());
         element6.addElement("inOutNo").addText(((Inout)inout.get(j)).getInOutNo());
         element6.addElement("manualId").addText(((Inout)inout.get(j)).getManualId());
         element6.addElement("sourceNo").addText(((Inout)inout.get(j)).getSourceNo());
         element6.addElement("itemType").addText(((Inout)inout.get(j)).getItemType());
         element6.addElement("inOutFlag").addText(((Inout)inout.get(j)).getInOutFlag());
         element6.addElement("inOutAmount").addText(((Inout)inout.get(j)).getInOutAmount());
         element6.addElement("inOutTime").addText(((Inout)inout.get(j)).getInOutTime());
         String WayBillNo = "";
         if (((Inout)inout.get(j)).getWayBillNo() != null) {
           WayBillNo = ((Inout)inout.get(j)).getWayBillNo();
         }
         element6.addElement("wayBillNo").addText(WayBillNo);
         element6.addElement("jobFormId").addText(((Inout)inout.get(j)).getJobFormId());
       } 
       
       LogInout logInout = new LogInout();
       logInout.setUserId(userId);
       logInout.setContent("推送数据ID：" + ids);
       this.inoutDao.insertLog(logInout);
       String ret = postxml.toZG_crkjr(document.asXML());
       Inout inou = new Inout();
       
       if (ret.contains(" <processResult>S</processResult>") || ret.contains(" <processResult>R</processResult>")) {
         inou.setFlag("success");
         inou.setIds(ids);
         this.inoutDao.inflag(inou);
         result = "1";
       } else {
         
         ret = ret.replaceAll("/", "");
         String[] abc = ret.split("<information>");
         StringBuffer resultMessage = new StringBuffer();
         
         for (int k = 0; k < abc.length; k++) {
           if (k % 2 == 1 && k != 0) {
             resultMessage.append(String.valueOf(abc[k]) + "<br>");
           }
         } 
         inou.setFlag("处理失败");
         inou.setIds(ids);
         this.inoutDao.inflag(inou);
         result = resultMessage.toString();
       } 
     } 
     return result;
   }
 
 
   
   public int inflag(Inout inout) {
     return this.inoutDao.inflag(inout);
   }
 
   
   public List<Inout> findinId(String jobFormId) {
     return this.inoutDao.findinId(jobFormId);
   }
 
   
   public List<Inout> inadd() {
     return this.inoutDao.inadd();
   }
 
   
   public List<Inout> findByIds(Map<String, Object> pageMap) {
     return this.inoutDao.findByIds(pageMap);
   }
 
   
   public Inout findBySelect(Integer id) {
     return this.inoutDao.findBySelect(id);
   }
 
   
   public int count2(InoutPageQueryVo inoutPageQueryVo) {
     return this.inoutDao.count2(inoutPageQueryVo);
   }
 
   
   public List<InoutCustom> findAll2(InoutPageQueryVo inoutPageQueryVo) {
     return this.inoutDao.findAll2(inoutPageQueryVo);
   }
 
 
   
   public List<Inout> findByPageQuery(InoutPageQueryVo inoutPageQueryVo) {
     return this.inoutDao.findByPageQuery(inoutPageQueryVo);
   }
 }


