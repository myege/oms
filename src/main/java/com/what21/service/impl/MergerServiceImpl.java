 package com.what21.service.impl;
 
 import com.what21.dao.MergerDao;
 import com.what21.model.Merger;
 import com.what21.model.MergerCustom;
 import com.what21.model.MergerQueryVo;
 import com.what21.service.MergerService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class MergerServiceImpl
   implements MergerService
 {
   @Autowired
   private MergerDao mergerDao;
   
   public List<Merger> findAll(Map<String, Object> pageMap) {
     return this.mergerDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.mergerDao.count(pageMap);
   }
 
   
   public int merdelete(String id) {
     return this.mergerDao.merdelete(id);
   }
 
   
   public List<Merger> cx(Map<String, Object> pageMap) {
     return this.mergerDao.cx(pageMap);
   }
 
   
   public GeneralResult inmerm(String path, int userId) {
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
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料件项号没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：料件性质没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品名称没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：商品编码没有输入！<br>");
         continue;
       } 
       Object data7 = nowRowData[6];
       
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：币制没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：申报计量单位没有输入！<br>");
         continue;
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：第一单位没有输入！<br>");
         continue;
       } 
       Object data11 = nowRowData[10];
       Object data12 = nowRowData[11];
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：停业标志没有输入！<br>");
         continue;
       } 
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：操作类型没有输入！<br>");
 
         
         continue;
       } 
       
       Merger isData = this.mergerDao.findByid(id);
       if (isData == null) {
         Merger merger = new Merger();
         merger.setManualId(data1.toString().trim());
         merger.setSourceNo(data2.toString().trim());
         merger.setItemNo(data3.toString().trim());
         merger.setItemType(data4.toString().trim());
         merger.setGoodsName(data5.toString().trim());
         merger.setGoodsNo(data6.toString().trim());
         merger.setGoodsSpec(data7.toString().trim());
         merger.setCurrencyType(data8.toString().trim());
         merger.setDeclareUnit(data9.toString().trim());
         merger.setUnit1(data10.toString().trim());
         merger.setUnit2(data11.toString().trim());
         merger.setUseFlag(data12.toString().trim());
         merger.setActionType(data13.toString().trim());
         this.mergerDao.insert1(merger);
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
 
   
   public int mets(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       Merger merger = this.mergerDao.meadd(Integer.valueOf(Integer.parseInt(id)));
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("MERGER");
       
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("mergerInfo");
       Element element4 = element3.addElement("manSign");
       element4.addElement("companyCode").addText("3301560023");
       element4.addElement("businessNo").addText("");
       element4.addElement("businessType").addText("MERGER");
       element4.addElement("declareType").addText("1");
       element4.addElement("note").addText("1");
       Element element5 = element3.addElement("manItemSourceList");
       Element element6 = element5.addElement("manItemSource");
       element6.addElement("manualId").addText(merger.getManualId());
       element6.addElement("itemNo").addText(merger.getItemNo());
       element6.addElement("itemType").addText(merger.getItemType());
       element6.addElement("sourceNo").addText(merger.getSourceNo());
       element6.addElement("goodsNo").addText(merger.getGoodsNo());
       element6.addElement("goodsName").addText(merger.getGoodsName());
       element6.addElement("goodsSpec").addText(merger.getGoodsSpec());
       element6.addElement("currencyType").addText(merger.getCurrencyType());
       element6.addElement("declareUnit").addText(merger.getDeclareUnit());
       element6.addElement("unit1").addText(merger.getUnit1());
       element6.addElement("unit2").addText(merger.getUnit2());
       Element element7 = element5.addElement("manItemSource");
       element7.addElement("manualId").addText(merger.getManualId());
       element7.addElement("itemNo").addText(merger.getItemNo());
       element7.addElement("itemType").addText(merger.getItemType());
       element7.addElement("sourceNo").addText(merger.getSourceNo());
       element7.addElement("goodsNo").addText(merger.getGoodsNo());
       element7.addElement("goodsName").addText(merger.getGoodsName());
       element7.addElement("goodsSpec").addText(merger.getGoodsSpec());
       element7.addElement("currencyType").addText(merger.getCurrencyType());
       element7.addElement("declareUnit").addText(merger.getDeclareUnit());
       element7.addElement("unit1").addText(merger.getUnit1());
       element7.addElement("unit2").addText(merger.getUnit2());
       element7.addElement("useFlag").addText(merger.getUseFlag());
       element7.addElement("actionType").addText(merger.getActionType());
       
       b++; }
     
     return result;
   }
 
   
   public Merger meadd(Integer id) {
     return this.mergerDao.meadd(id);
   }
 
   
   public int mflag(Merger merger) {
     return this.mergerDao.mflag(merger);
   }
 
   
   public int count2(MergerQueryVo mergerQueryVo) {
     return this.mergerDao.count2(mergerQueryVo);
   }
 
   
   public List<MergerCustom> findAll2(MergerQueryVo mergerQueryVo) {
     return this.mergerDao.findAll2(mergerQueryVo);
   }
 }


