 package com.what21.service.impl;
 
 import cn.gov.zjport.manchester.encrypt.AESEncrypt;
 import com.what21.dao.WinventoryDao;
 import com.what21.model.Winventory;
 import com.what21.model.WinventoryQueryVo;
 import com.what21.service.WinventoryService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.tsjgq.postxml;
 import java.io.File;
 import java.io.UnsupportedEncodingException;
 import java.security.InvalidKeyException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class WinventoryServiceImpl
   implements WinventoryService
 {
   @Autowired
   private WinventoryDao winventoryDao;
   
   public List<Winventory> findAll(Map<String, Object> pageMap) {
     return this.winventoryDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.winventoryDao.count(pageMap);
   }
 
   
   public int wdelete(String id) {
     return this.winventoryDao.wdelete(id);
   }
 
   
   public List<Winventory> findw(Map<String, Object> pageMap) {
     return this.winventoryDao.findw(pageMap);
   }
 
   
   public GeneralResult inmerw(String path, int userId) {
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
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：账册编号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料号没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：库存数量没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料件性质没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品单位没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：库位没有输入！<br>");
 
         
         continue;
       } 
       
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：库存申报时间没有输入！<br>");
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：备注没有输入！<br>");
 
         
         continue;
       } 
 
       
       Winventory isData = this.winventoryDao.findByW(id);
       if (isData == null) {
         Winventory winventory = new Winventory();
         winventory.setManualId(data1.toString());
         winventory.setSourceNo(data2.toString());
         winventory.setGoodsQuantity(Integer.parseInt(data3.toString()));
         winventory.setItemType(data4.toString());
         winventory.setGoodsUnit(data5.toString());
         winventory.setStorageLocation(data6.toString());
         winventory.setInventoryTime(data7.toString());
         winventory.setRemark(data8.toString());
         winventory.setUserId(userId);
         SimpleDateFormat dnf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         winventory.setDatatime(dnf.format(new Date()));
         this.winventoryDao.insertw(winventory);
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
   
   public String wints() {
     String result = null;
     
     List<Winventory> winventory = this.winventoryDao.wadd();
 
     
     if (winventory.size() == 0 || winventory.isEmpty()) {
       result = "数据库不存在未推送或失败数据";
     } else {
       
       String ids = "";
       for (int i = 0; i < winventory.size(); i++) {
         
         if (i == winventory.size() - 1) {
           
           ids = String.valueOf(ids) + ((Winventory)winventory.get(i)).getId();
         } else {
           
           ids = String.valueOf(ids) + ((Winventory)winventory.get(i)).getId() + ",";
         } 
       } 
       
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("INVENTORY");
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("inventoryInfo");
       Element element4 = element3.addElement("manSign");
       element4.addElement("companyCode").addText("1234567890");
       element4.addElement("businessNo").addText("BusinessNo");
       element4.addElement("businessType").addText("INVENTORY");
       element4.addElement("declareType").addText("1");
       element4.addElement("note").addText("1");
       Element element5 = element3.addElement("manInventoryDetailList");
       
       for (int j = 0; j < winventory.size(); j++) {
         Element element6 = element5.addElement("manInventoryDetail");
         element6.addElement("manualId").addText(((Winventory)winventory.get(j)).getManualId());
         element6.addElement("sourceNo").addText(((Winventory)winventory.get(j)).getSourceNo());
         element6.addElement("itemType").addText(((Winventory)winventory.get(j)).getItemType());
         element6.addElement("goodsQuantity").addText(String.valueOf(((Winventory)winventory.get(j)).getGoodsQuantity()));
         element6.addElement("goodsUnit").addText(((Winventory)winventory.get(j)).getGoodsUnit());
         element6.addElement("storageLocation").addText(((Winventory)winventory.get(j)).getStorageLocation());
         element6.addElement("inventoryTime").addText(((Winventory)winventory.get(j)).getInventoryTime());
         element6.addElement("remark").addText(((Winventory)winventory.get(j)).getRemark());
       }       
       try {
         String ret = AESEncrypt.decryptor(postxml.toZG_kc(document.asXML()));
         Winventory w = new Winventory();
         if (ret.contains(" <processResult>S</processResult>") || ret.contains(" <processResult>R</processResult>")) {
           
           w.setFlag("seccess");
           w.setIds(ids);
           this.winventoryDao.wflag(w);
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
           
           w.setFlag("处理失败");
           w.setIds(ids);
           this.winventoryDao.wflag(w);
           result = resultMessage.toString();
         } 
       } catch (InvalidKeyException e) {
         e.printStackTrace();
       } catch (IllegalBlockSizeException e) {
         e.printStackTrace();
       } catch (BadPaddingException e) {
         e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
       } 
     } 
     return result;
   }
 
 
   
   public int wflag(Winventory winventory) {
     return this.winventoryDao.wflag(winventory);
   }
 
   
   public List<Winventory> findwId(String manualId) {
     return this.winventoryDao.findwId(manualId);
   }
 
   
   public List<Winventory> wadd() {
     return this.winventoryDao.wadd();
   }
 
   
   public int count2(WinventoryQueryVo winventoryQueryVo) {
     return this.winventoryDao.count2(winventoryQueryVo);
   }
 
   
   public List<Winventory> findAll2(WinventoryQueryVo winventoryQueryVo) {
     return this.winventoryDao.findAll2(winventoryQueryVo);
   }
 }


