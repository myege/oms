 package com.what21.service.impl;
 
 import cn.gov.zjport.manchester.encrypt.AESEncrypt;
 import com.what21.dao.PlaningReceiptsDao;
 import com.what21.dao.PlaningReceiptsSkuDao;
 import com.what21.model.PlaningReceipts;
 import com.what21.model.PlaningReceiptsQueryVo;
 import com.what21.model.PlaningReceiptsSku;
 import com.what21.service.PlaningReceiptsService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.tsjgq.postxml;
 import java.io.File;
 import java.io.UnsupportedEncodingException;
 import java.math.BigDecimal;
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
 public class PlaningReceiptsServiceImpl
   implements PlaningReceiptsService
 {
   @Autowired
   private PlaningReceiptsDao planingReceiptsDao;
   @Autowired
   private PlaningReceiptsSkuDao planingReceiptsSkuDao;
   
   public List<PlaningReceipts> findAll(Map<String, Object> map) {
     return this.planingReceiptsDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.planingReceiptsDao.count(map);
   }
 
   
   public int delete(String planingReceiptsId) {
     return this.planingReceiptsDao.delete(planingReceiptsId);
   }
 
 
   
   public List<PlaningReceipts> pr(Map<String, Object> map) {
     return this.planingReceiptsDao.pr(map);
   }
 
   
   public GeneralResult importPr(String path, int userId) {
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
       String planingReceiptsId = "";
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         planingReceiptsId = data1.toString();
       } else {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：计划入库单编号没有输入！<br>");
         
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：账册编码没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：关区代码没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：企业海关十位编码没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：企业名称没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：毛重没有输入！<br>");
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：净重没有输入！<br>");
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：件数没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：包装种类没有输入！<br>");
         continue;
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：申报关区没有输入！<br>");
         continue;
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：厂商编码没有输入！<br>");
         continue;
       } 
       Object data12 = nowRowData[11];
       if (data12 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：业务类别没有输入！<br>");
 
 
         
         continue;
       } 
 
 
       
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料号没有输入！<br>");
         continue;
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：项号没有输入！<br>");
         continue;
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料件性质没有输入！<br>");
         continue;
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品编码没有输入！<br>");
         continue;
       } 
       Object data17 = nowRowData[16];
       if (data17 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：申报数量没有输入！<br>");
         continue;
       } 
       Object data18 = nowRowData[17];
       if (data18 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：申报总价没有输入！<br>");
         continue;
       } 
       Object data19 = nowRowData[18];
       if (data19 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：单价没有输入！<br>");
         continue;
       } 
       Object data20 = nowRowData[19];
       if (data20 == null || StringUtils.isEmpty(data3.toString().trim()))
       {
         data20 = "";
       }
       Object data21 = nowRowData[20];
       if (data21 == null || StringUtils.isEmpty(data3.toString().trim()))
       {
         data21 = "";
       }
 
       
       PlaningReceipts isData = this.planingReceiptsDao.findByPlaningReceiptsId(planingReceiptsId);
       if (isData == null) {
         PlaningReceipts planingReceipts = new PlaningReceipts();
         planingReceipts.setPlaningReceiptsId(planingReceiptsId.trim());
         planingReceipts.setManualId(data2.toString().trim());
         planingReceipts.setCustomsCode(data3.toString().trim());
         planingReceipts.setCompanyCode(data4.toString().trim());
         planingReceipts.setCompanyName(data5.toString().trim());
         planingReceipts.setGrossWeight(data6.toString().trim());
         planingReceipts.setNetWeight(data7.toString().trim());
         planingReceipts.setAmount(data8.toString().trim());
         planingReceipts.setWrapType(data9.toString().trim());
         planingReceipts.setPort(Integer.parseInt(data10.toString().trim()));
         planingReceipts.setProviderCode(data11.toString().trim());
         planingReceipts.setType(data12.toString().trim());
         planingReceipts.setUserId(userId);
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         planingReceipts.setDatatime(simpleDateFormat.format(new Date()));
         this.planingReceiptsDao.insert(planingReceipts);
       } 
       PlaningReceiptsSku planingReceiptsSku = new PlaningReceiptsSku();
       planingReceiptsSku.setPlaningReceiptsId(planingReceiptsId.trim());
       planingReceiptsSku.setSourceNo(data13.toString().trim());
       planingReceiptsSku.setItemNo(data14.toString().trim());
       planingReceiptsSku.setItemType(data15.toString().trim());
       planingReceiptsSku.setGoodsNo(data16.toString().trim());
       planingReceiptsSku.setDeclareAmount(data17.toString().trim());
       planingReceiptsSku.setTotalPrice(BigDecimal.valueOf(Double.valueOf(data18.toString().trim()).doubleValue()));
       planingReceiptsSku.setPrice(BigDecimal.valueOf(Double.valueOf(data19.toString().trim()).doubleValue()));
       planingReceiptsSku.setCountryCode(data20.toString().trim());
       planingReceiptsSku.setCurrency(data21.toString().trim());
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       planingReceiptsSku.setDatatime(df.format(new Date()));
       this.planingReceiptsSkuDao.insert(planingReceiptsSku);
       
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
 
 
   
   public String pushPr(String ids) {
     String[] idArr = ids.split(",");
     String result = null;
     StringBuffer resultMessage = new StringBuffer();
     PlaningReceipts flag = new PlaningReceipts(); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       PlaningReceipts planingReceipts = this.planingReceiptsDao.findByIdToPR(Integer.valueOf(Integer.parseInt(id)));
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("PLANING_RECEIPTS");
       
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("planingReceiptsInfo");
       Element element4 = element3.addElement("manSign");
       
       element4.addElement("companyCode").addText("3316W60020");
       element4.addElement("businessNo").addText(planingReceipts.getPlaningReceiptsId());
       element4.addElement("businessType").addText("PLANING_RECEIPTS");
       element4.addElement("declareType").addText("1");
       element4.addElement("note").addText("1234");
       
       Element element5 = element3.addElement("manPlaningReceipts");
       element5.addElement("planingReceiptsId").addText(planingReceipts.getPlaningReceiptsId());
       element5.addElement("manualId").addText(planingReceipts.getManualId());
       element5.addElement("customsCode").addText(planingReceipts.getCustomsCode());
       element5.addElement("companyCode").addText(planingReceipts.getCompanyCode());
       element5.addElement("companyName").addText(planingReceipts.getCompanyName());
       element5.addElement("grossWeight").addText(planingReceipts.getGrossWeight());
       element5.addElement("netWeight").addText(planingReceipts.getNetWeight());
       element5.addElement("amount").addText(planingReceipts.getAmount());
       element5.addElement("wrapType").addText(planingReceipts.getWrapType());
       element5.addElement("port").addText(String.valueOf(planingReceipts.getPort()));
       element5.addElement("providerCode").addText(planingReceipts.getProviderCode());
       element5.addElement("type").addText(planingReceipts.getType());
       
       Element element6 = element3.addElement("manPlaningReceiptsDetailList");
       List<PlaningReceiptsSku> u = this.planingReceiptsSkuDao.findByPlaningReceiptsId(planingReceipts.getPlaningReceiptsId());
       
       for (int j = 0; j < u.size(); j++) {
         Element element7 = element6.addElement("manPlaningReceiptsDetail");
         element7.addElement("planingReceiptsId").addText(((PlaningReceiptsSku)u.get(j)).getPlaningReceiptsId());
         element7.addElement("sourceNo").addText(((PlaningReceiptsSku)u.get(j)).getSourceNo());
         element7.addElement("itemNo").addText(((PlaningReceiptsSku)u.get(j)).getItemNo());
         element7.addElement("itemType").addText(((PlaningReceiptsSku)u.get(j)).getItemType());
         element7.addElement("goodsNo").addText(((PlaningReceiptsSku)u.get(j)).getGoodsNo());
         element7.addElement("declareAmount").addText(((PlaningReceiptsSku)u.get(j)).getDeclareAmount());
         element7.addElement("totalPrice").addText(((PlaningReceiptsSku)u.get(j)).getTotalPrice().toString());
         element7.addElement("price").addText(((PlaningReceiptsSku)u.get(j)).getPrice().toString());
         element7.addElement("countryCode").addText(((PlaningReceiptsSku)u.get(j)).getCountryCode());
         element7.addElement("currency").addText(((PlaningReceiptsSku)u.get(j)).getCurrency());
       } 
 
       
       String ret = postxml.toZG_jhrkd(document.asXML());
       
       try {
         ret = AESEncrypt.decryptor(ret);
       } catch (InvalidKeyException e1) {
         e1.printStackTrace();
       } catch (IllegalBlockSizeException e1) {
         e1.printStackTrace();
       } catch (BadPaddingException e1) {
         e1.printStackTrace();
       } catch (UnsupportedEncodingException e1) {
         e1.printStackTrace();
       } 
 
       
       if (ret.contains(" <processResult>S</processResult>") || ret.contains(" <processResult>R</processResult>")) {
         ret = ret.replaceAll("/", "");
         String[] abc = ret.split("<processComment>");
         for (int k = 0; k < abc.length; k++) {
           
           if (k == 1) {
             flag.setFlag(abc[k]);
             flag.setId(Integer.parseInt(id));
             this.planingReceiptsDao.insertFlag(flag);
           } 
         } 
 
         
         result = "1";
       } else {
         
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
         flag.setFlag("处理失败");
         flag.setId(Integer.parseInt(id2));
         this.planingReceiptsDao.insertFlag(flag); b++; }
       
       result = resultMessage.toString();
     } 
     return result;
   }
 
 
   
   public int count2(PlaningReceiptsQueryVo planingReceiptsQueryVo) {
     return this.planingReceiptsDao.count2(planingReceiptsQueryVo);
   }
 
   
   public List<PlaningReceipts> findAll2(PlaningReceiptsQueryVo planingReceiptsQueryVo) {
     return this.planingReceiptsDao.findAll2(planingReceiptsQueryVo);
   }
 }


