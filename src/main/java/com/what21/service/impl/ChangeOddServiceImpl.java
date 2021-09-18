 package com.what21.service.impl;
 
 import com.what21.dao.ChangeOddDao;
 import com.what21.dao.GoodsChangeoddDao;
 import com.what21.model.ChangeOdd;
 import com.what21.model.ExportChangeOdd;
 import com.what21.model.GoodsChangeodd;
 import com.what21.model.OrderMail;
 import com.what21.service.ChangeOddService;
 import com.what21.util.ChangeOddExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ChangeOddServiceImpl
   implements ChangeOddService
 {
   @Autowired
   private ChangeOddDao changeOddDao;
   @Autowired
   private GoodsChangeoddDao goodsChangeoddDao;
   
   public List<ChangeOdd> findAll(Map<String, Object> paramMap) {
     return this.changeOddDao.findAll(paramMap);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.changeOddDao.count(map);
   }
 
 
   
   public ChangeOdd findById(int paramInt) {
     return this.changeOddDao.findById(paramInt);
   }
 
   
   public void update(String paramString) {
     this.changeOddDao.update(paramString);
   }
   
   public int updates(String paramString) {
     return this.changeOddDao.updates(paramString);
   }
 
   
   public ChangeOdd findByExpressNumber(String paramString) {
     return this.changeOddDao.findByExpressNumber(paramString);
   }
 
   
   public List<ChangeOdd> findByExpressNumberForAlls(List<ChangeOdd> list) {
     return this.changeOddDao.findByExpressNumberForAlls(list);
   }
 
   
   public void insert(ChangeOdd paramChangeOdd) {
     this.changeOddDao.insert(paramChangeOdd);
   }
 
 
   
   public List<ChangeOdd> findExpressNumber(Map<String, Object> paramMap) {
     return this.changeOddDao.findExpressNumber(paramMap);
   }
 
   
   public GeneralResult importOrder(String path, int userId) {
     GeneralResult result = new GeneralResult();
     
     Map<Integer, Object[]> items = ChangeOddExcelUtil.read(path);
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
       if (data1 == null || StringUtils.isEmpty(data1.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：订单号没有输入！<br>");
         
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：快递单号没有输入！<br>");
         
         continue;
       } 
       String expressNumber = data2.toString();
       
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：快递公司编号没有输入！<br>");
         
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人姓名没有输入！<br>");
         
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人省份没有输入！<br>");
         
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人城市没有输入！<br>");
         
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人地区没有输入！<br>");
         
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人详细地址没有输入！<br>");
         
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：收件人联系电话没有输入！<br>");
 
         
         continue;
       } 
       
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因： 寄件人没有输入！<br>");
         continue;
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：寄件人电话没有输入！<br>");
         
         continue;
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：寄件人地址没有输入！<br>");
         
         continue;
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：换(新)单号没有输入！<br>");
         
         continue;
       } 
       
       ChangeOdd isData = this.changeOddDao.findByExpressNumber(expressNumber);
       if (isData == null) {
         ChangeOdd changeOdd = new ChangeOdd();
         changeOdd.setOrderNumber(data1.toString());
         changeOdd.setExpressNumber(data2.toString());
         changeOdd.setExpressCode(data3.toString());
         changeOdd.setBuyerName(data4.toString());
         changeOdd.setBuyerProvince(data5.toString());
         changeOdd.setBuyerCity(data6.toString());
         changeOdd.setBuyerArea(data7.toString());
         changeOdd.setBuyerAddress(data8.toString());
         changeOdd.setBuyerTel(data9.toString());
         changeOdd.setSender(data13.toString());
         changeOdd.setSenderTel(data14.toString());
         changeOdd.setSenderAddress(data15.toString());
         changeOdd.setNewexpressNumber(data16.toString());
         changeOdd.setPrintType(0);
         changeOdd.setUserId(userId);
         this.changeOddDao.insert(changeOdd);
       } 
       
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：物品名称没有输入！<br>");
         continue;
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：物品重量没有输入！<br>");
         
         continue;
       } 
       Object data12 = nowRowData[11];
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：物品数量没有输入！<br>");
         
         continue;
       }       
       GoodsChangeodd gchangeOdd = new GoodsChangeodd();
       gchangeOdd.setChangeoId(this.changeOddDao.findByExpressNumber(expressNumber).getId());
       gchangeOdd.setPnname(data10.toString());
       gchangeOdd.setNweight(Double.valueOf(data11.toString()).doubleValue());
       gchangeOdd.setNewnum(Integer.parseInt(data12.toString()));
       
       gchangeOdd.setExpressNumber(expressNumber);
       gchangeOdd.setUserId(userId);
       this.goodsChangeoddDao.insertand(gchangeOdd);
       successData++;
 
       
       File delFile = new File(path);
       if (delFile.exists()) {
         delFile.delete();
       }
     } 
 
 
     
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setCode(Integer.valueOf(1));
     result.setMessage(resultMessage.toString());
     
     return result;
   }
   
   public List<ChangeOdd> findexpn(Map<String, Object> pageMap) {
     return this.changeOddDao.findexpn(pageMap);
   }
 
 
   
   public void updatePrintType(String intValue) {
     this.changeOddDao.updatePrintType(intValue);
   }
 
 
 
   
   public ChangeOdd updateepn(String expressNumber) {
     return this.changeOddDao.updateepn(expressNumber);
   }
 
   
   public List<ChangeOdd> allfind() {
     return this.changeOddDao.allfind();
   }
 
   
   public void insertand(ChangeOdd changeOdd) {
     this.changeOddDao.insertand(changeOdd);
   }
 
 
   
   public void updateCod(ChangeOdd changeOdd) {
     this.changeOddDao.insertand(changeOdd);
   }
 
 
 
   
   public void updatechangeodd(ChangeOdd changeOdd) {
     this.changeOddDao.updatechangeodd(changeOdd);
   }
 
   
   public void deleteByIds(int[] ids) {
     this.changeOddDao.deleteByIds(ids);
   }
 
 
   
   public List<ExportChangeOdd> exportByIds(int[] ids) {
     return this.changeOddDao.exportByIds(ids);
   }
 
   
   public List<ExportChangeOdd> exportByCons(ExportChangeOdd exportChangeOdd2) {
     return this.changeOddDao.exportByCons(exportChangeOdd2);
   }
 
 
   
   public int inserts(List<ChangeOdd> change) {
     return this.changeOddDao.inserts(change);
   }
 
 
   
   public int insert_int(ChangeOdd changeOdd) {
     return this.changeOddDao.insert_int(changeOdd);
   }
 
 
   
   public List<ChangeOdd> findByExpressNumberCount(List<OrderMail> o) {
     return this.changeOddDao.findByExpressNumberCount(o);
   }
 
 
   
   public void deleteById(int id) {
     this.changeOddDao.deleteById(id);
   }
 }


