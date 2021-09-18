 package com.what21.service.impl;
 
 import cn.gov.zjport.manchester.encrypt.AESEncrypt;
 import com.what21.dao.StockDeleteDao;
 import com.what21.dao.StockDeleteSkuDao;
 import com.what21.model.StockDelete;
 import com.what21.model.StockDeleteCustom;
 import com.what21.model.StockDeleteQueryVo;
 import com.what21.model.StockDeleteSku;
 import com.what21.service.StockDeleteService;
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
 public class StockDeleteServiceImpl
   implements StockDeleteService
 {
   @Autowired
   private StockDeleteDao stockDeleteDao;
   @Autowired
   private StockDeleteSkuDao stockDeleteSkuDao;
   
   public List<StockDelete> findAll(Map<String, Object> map) {
     return this.stockDeleteDao.findAll(map);
   }
 
 
   
   public int count(Map<String, Object> map) {
     return this.stockDeleteDao.count(map);
   }
 
 
   
   public int delete(String stockDeleteId) {
     return this.stockDeleteDao.delete(stockDeleteId);
   }
 
 
   
   public List<StockDelete> sd(Map<String, Object> map) {
     return this.stockDeleteDao.sd(map);
   }
 
   
   public GeneralResult importSd(String path, int userId) {
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
       String stockDeleteId = "";
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         stockDeleteId = data1.toString();
       } else {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：删单申请单编号没有输入！<br>");
         
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：账册编号没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：关区代码没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：WMS系统权限代码没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：企业海关十位编码没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：出入库类型没有输入！<br>");
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data3.toString().trim())) {
         data7 = "";
       }
       
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：出入库单流水号没有输入！<br>");
         
         continue;
       } 
       StockDelete isData = this.stockDeleteDao.findByStockDeleteId(stockDeleteId);
       if (isData == null) {
         StockDelete stockDelete = new StockDelete();
         stockDelete.setStockDeleteId(stockDeleteId);
         stockDelete.setManualId(data2.toString());
         stockDelete.setCustomsCode(data3.toString());
         stockDelete.setRoleCode(data4.toString());
         stockDelete.setCompanyCode(data5.toString());
         stockDelete.setInOutFlag(data6.toString());
         stockDelete.setReason(data7.toString());
 
         
         stockDelete.setUserId(userId);
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         stockDelete.setDatatime(simpleDateFormat.format(new Date()));
         this.stockDeleteDao.insert(stockDelete);
       } 
       StockDeleteSku stockDeleteSku = new StockDeleteSku();
       stockDeleteSku.setStockDeleteId(stockDeleteId);
       stockDeleteSku.setInOutSeq(data8.toString());
       
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       stockDeleteSku.setDatatime(df.format(new Date()));
       stockDeleteSku.setUserId(userId);
       this.stockDeleteSkuDao.insert(stockDeleteSku);
       
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
   
   public int pushSd(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       StockDelete stockDelete = this.stockDeleteDao.findByIdToSD(Integer.valueOf(Integer.parseInt(id)));
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("STOCK_DELETE");
       
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("stockDeleteInfo");
       Element element4 = element3.addElement("manSign");
       
       element4.addElement("companyCode").addText("3316W60020");
       element4.addElement("businessNo").addText("BusinessNo");
       element4.addElement("businessType").addText("STOCK_DELETE");
       element4.addElement("declareType").addText("1");
       element4.addElement("note").addText("备注");
       
       Element element5 = element3.addElement("manStockDelete");
       element5.addElement("stockDeleteId").addText(stockDelete.getStockDeleteId());
       element5.addElement("manualId").addText(stockDelete.getManualId());
       element5.addElement("roleCode").addText(stockDelete.getRoleCode());
       element5.addElement("companyCode").addText(stockDelete.getCompanyCode());
       element5.addElement("customsCode").addText(stockDelete.getCustomsCode());
       element5.addElement("inOutFlag").addText(stockDelete.getInOutFlag());
       element5.addElement("reason").addText(stockDelete.getReason());
 
       
       Element element6 = element3.addElement("manStockDeleteDetailList");
       List<StockDeleteSku> u = this.stockDeleteSkuDao.findByStockDeleteId(stockDelete.getStockDeleteId());
       
       for (int j = 0; j < u.size(); j++) {
         Element element7 = element6.addElement("manStockDeleteDetail");
         element7.addElement("stockDeleteId").addText(((StockDeleteSku)u.get(j)).getStockDeleteId());
         element7.addElement("inOutSeq").addText(((StockDeleteSku)u.get(j)).getInOutSeq());
       } 
 
 
       
       String ret = postxml.toZG_sdsq(document.asXML());
       
       try {
         ret = AESEncrypt.decryptor(ret);
       } catch (InvalidKeyException e) {
         e.printStackTrace();
       } catch (IllegalBlockSizeException e) {
         e.printStackTrace();
       } catch (BadPaddingException e) {
         e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
       } 
       StockDelete s = new StockDelete();
       System.out.println("ret=----->" + ret);
       if (ret.contains("<processResult>A</processResult>")) {
         
         s.setFlag("申请成功！业务处理结果待处理完后会异步发送！");
         s.setStockDeleteId(stockDelete.getStockDeleteId());
         this.stockDeleteDao.insertFlagSD(s);
         result = 1;
       } else {
         byte b1; int k; String[] arrayOfString;
         for (k = (arrayOfString = idArr).length, b1 = 0; b1 < k; ) { String id2 = arrayOfString[b1];
           StockDelete s2 = this.stockDeleteDao.findByIdToSD(Integer.valueOf(Integer.parseInt(id2)));
           s.setFlag("处理失败");
           s.setStockDeleteId(s2.getStockDeleteId());
           this.stockDeleteDao.insertFlagSD(s); b1++; }
         
         break;
       } 
       b++; }
     
     return result;
   }
 
   
   public int count2(StockDeleteQueryVo stockDeleteQueryVo) {
     return this.stockDeleteDao.count2(stockDeleteQueryVo);
   }
 
   
   public List<StockDeleteCustom> findAll2(StockDeleteQueryVo stockDeleteQueryVo) {
     return this.stockDeleteDao.findAll2(stockDeleteQueryVo);
   }
 }


