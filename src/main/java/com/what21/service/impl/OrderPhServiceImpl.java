 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.CompanyDao;
 import com.what21.dao.GoodsAcceptDao;
 import com.what21.dao.ItemDao;
 import com.what21.dao.Log_inventoryDao;
 import com.what21.dao.OrderAcceptDao;
 import com.what21.dao.OrderPhDao;
 import com.what21.dao.OrderPhSkuDao;
 import com.what21.dao.TinventoryDao;
 import com.what21.model.Company;
 import com.what21.model.GoodsAccept;
 import com.what21.model.Item;
 import com.what21.model.Log_inventory;
 import com.what21.model.OrderAccept;
 import com.what21.model.OrderPh;
 import com.what21.model.OrderPhSku;
 import com.what21.model.Tinventory;
 import com.what21.service.OrderPhService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.zt.kjybd.HTOydbg;
 import com.zt.kjybd.PushtoBG_yd;
 import java.io.File;
 import java.math.BigDecimal;
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
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class OrderPhServiceImpl
   implements OrderPhService
 {
   @Autowired
   public OrderPhDao OrderPhDao;
   @Autowired
   public OrderPhSkuDao OrderPhSkuDao;
   @Autowired
   private OrderAcceptDao orderAcceptDao;
   @Autowired
   private GoodsAcceptDao goodsAcceptDao;
   @Autowired
   private ItemDao itemDao;
   @Autowired
   private TinventoryDao tinventoryDao;
   @Autowired
   private Log_inventoryDao log_inventoryDao;
   @Autowired
   private CompanyDao companyDao;
   
   public List<OrderPh> findAll(Map<String, Object> map) {
     return this.OrderPhDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.OrderPhDao.count(pageMap);
   }
 
   
   public GeneralResult importOrder(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
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
       String txLogisticID = "";
       int row = ((Integer)entry.getKey()).intValue() + 1;
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         txLogisticID = data1.toString();
       } else {
         message.append("导入第" + row + "行数据失败，失败原因：客户订单号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件省没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件市没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件区没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人地址没有输入！<br>");
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人电话没有输入！<br>");
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品数量没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品总价没有输入！<br>");
         
         continue;
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品名称没有输入！<br>");
         continue;
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品重量没有输入！<br>");
         continue;
       } 
       Object data12 = nowRowData[11];
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：单价没有输入！<br>");
         continue;
       } 
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：运费没有输入！<br>");
         continue;
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：保费没有输入！<br>");
         continue;
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：购买人身份证没有输入！<br>");
         continue;
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：购买人没有输入！<br>");
         continue;
       } 
       Object data17 = nowRowData[16];
       if (data17 == null || StringUtils.isEmpty(data17.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品SKU没有输入！<br>");
         continue;
       } 
       Object data18 = nowRowData[17];
       if (data18 == null || StringUtils.isEmpty(data18.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：快递公司没有输入！<br>");
         continue;
       } 
       Object data19 = nowRowData[18];
       if (data19 == null || StringUtils.isEmpty(data19.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：发件仓库没有输入！<br>");
         continue;
       } 
       Object data20 = nowRowData[19];
       if (data20 == null || StringUtils.isEmpty(data20.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商家编码没有输入！<br>");
         continue;
       } 
       Object data21 = nowRowData[20];
       Object data22 = nowRowData[21];
       OrderPh isData = this.OrderPhDao.findByTxLogisticID(txLogisticID);
       if (isData == null) {
         OrderPh OrderPh = new OrderPh();
         OrderPh.setTxLogisticID(data1.toString());
         OrderPh.setReceiveMan(data2.toString());
         OrderPh.setReceiveProvince(data3.toString());
         OrderPh.setReceiveCity(data4.toString());
         OrderPh.setReceiveCounty(data5.toString());
         OrderPh.setReceiveManAddress(data6.toString());
         OrderPh.setReceiveManPhone(data7.toString());
         OrderPh.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString())));
         OrderPh.setWorth(Double.valueOf(data9.toString()));
         OrderPh.setItemName(data10.toString());
         OrderPh.setItemWeight(Double.valueOf(data11.toString()));
         OrderPh.setFeeAmount(Double.valueOf(data13.toString()));
         OrderPh.setInsureAmount(Double.valueOf(data14.toString()));
         OrderPh.setBuyerIdNumber(data15.toString());
         OrderPh.setBuyerName(data16.toString());
         OrderPh.setCarrier(data18.toString());
         OrderPh.setSendWarehouse(data19.toString());
         OrderPh.setMerchantNum(data20.toString());
         if (data21 != null) {
           OrderPh.setMailNo(data21.toString());
         }
         if (data22 != null) {
           OrderPh.setPc(data22.toString());
         }
         OrderPh.setUserId(userId);
         OrderPh.setIspost(0);
         OrderPh.setIsCustoms(0);
         OrderPh.setAuditstatus(0);
         this.OrderPhDao.insert(OrderPh);
       } else {
         Integer itemCount = Integer.valueOf(isData.getItemCount().intValue() + Integer.parseInt(data8.toString()));
         Double itemWeight = Double.valueOf(isData.getItemWeight().doubleValue() + Double.valueOf(data11.toString()).doubleValue());
         Double worth = Double.valueOf(isData.getWorth().doubleValue() + Double.valueOf(data9.toString()).doubleValue());
         isData.setItemCount(itemCount);
         isData.setItemWeight(itemWeight);
         isData.setWorth(worth);
         this.OrderPhDao.updateCountAndWeight(isData);
       } 
       OrderPhSku OrderPhSku = new OrderPhSku();
       OrderPhSku.setTxLogisticID(txLogisticID);
       OrderPhSku.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString())));
       OrderPhSku.setAllPrice(Double.valueOf(data9.toString()));
       OrderPhSku.setItemName(data10.toString());
       OrderPhSku.setItemWeight(Double.valueOf(data11.toString()));
       OrderPhSku.setUnitPrice(Double.valueOf(data12.toString()));
       OrderPhSku.setItemsku(data17.toString());
       this.OrderPhSkuDao.insert(OrderPhSku);
       
       successData++;
     } 
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
 
   
   public GeneralResult importOrderNew(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     boolean firstRow = true;
     StringBuffer message = new StringBuffer();
     Map<String, OrderPh> OrderPhMap = new HashMap<>();
     Map<String, OrderPhSku> OrderPhSkuMap = new HashMap<>();
     List<OrderPhSku> obsList = new ArrayList<>();
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       Object data1 = nowRowData[0];
       String txLogisticID = "";
       int row = ((Integer)entry.getKey()).intValue() + 1;
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         txLogisticID = data1.toString().trim();
       } else {
         
         txLogisticID = "''";
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人没有输入！<br>");
         break;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件省没有输入！<br>");
         break;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件市没有输入！<br>");
         break;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件区没有输入！<br>");
         break;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人地址没有输入！<br>");
         break;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人电话没有输入！<br>");
         break;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品数量没有输入！<br>");
         break;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品总价没有输入！<br>");
         break;
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品名称没有输入！<br>");
         break;
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品重量没有输入！<br>");
         break;
       } 
       Object data12 = nowRowData[11];
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：单价没有输入！<br>");
         break;
       } 
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：运费没有输入！<br>");
         break;
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：保费没有输入！<br>");
         break;
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：购买人身份证没有输入！<br>");
         break;
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：购买人没有输入！<br>");
         break;
       } 
       Object data17 = nowRowData[16];
       if (data17 == null || StringUtils.isEmpty(data17.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品SKU没有输入！<br>");
         break;
       } 
       Object data18 = nowRowData[17];
       if (data18 == null || StringUtils.isEmpty(data18.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：快递公司没有输入！<br>");
         break;
       } 
       Object data19 = nowRowData[18];
       if (data19 == null || StringUtils.isEmpty(data19.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：发件仓库没有输入！<br>");
         break;
       } 
       Object data20 = nowRowData[19];
       if (data20 == null || StringUtils.isEmpty(data20.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商家编码没有输入！<br>");
         break;
       } 
       Object data21 = nowRowData[20];
       Object data22 = nowRowData[21];
       
       BigDecimal unitPrice = new BigDecimal(data12.toString().trim());
       BigDecimal itemCount = new BigDecimal(data8.toString().trim());
       BigDecimal allPrice = new BigDecimal(data9.toString().trim());
       BigDecimal price = unitPrice.multiply(itemCount);
       
       if (!allPrice.equals(price)) {
         message.append("导入第" + row + "行数据失败，失败原因：商品单价乘以商品数量不等于商品总价！<br>");
         
         break;
       } 
       OrderPh OrderPh = new OrderPh();
       
       if (data1 != null && data1 != "") {
         OrderPh.setTxLogisticID(data1.toString().trim());
       }
       OrderPh.setReceiveMan(data2.toString().trim());
       OrderPh.setReceiveProvince(data3.toString().trim());
       OrderPh.setReceiveCity(data4.toString().trim());
       OrderPh.setReceiveCounty(data5.toString().trim());
       OrderPh.setReceiveManAddress(data6.toString().trim());
       OrderPh.setReceiveManPhone(data7.toString().trim());
       OrderPh.setItemName(data10.toString().trim());
       OrderPh.setFeeAmount(Double.valueOf(data13.toString().trim()));
       OrderPh.setInsureAmount(Double.valueOf(data14.toString().trim()));
       OrderPh.setBuyerIdNumber(data15.toString().trim());
       OrderPh.setBuyerName(data16.toString().trim());
       OrderPh.setCarrier(data18.toString().trim());
       OrderPh.setSendWarehouse(data19.toString().trim());
       OrderPh.setMerchantNum(data20.toString().trim());
       if (data21 != null) {
         OrderPh.setMailNo(data21.toString().trim());
       }
       if (data22 != null) {
         OrderPh.setPc(data22.toString().trim());
       }
       OrderPh.setUserId(userId);
       OrderPh.setIspost(0);
       OrderPh.setIsCustoms(0);
       OrderPh.setAuditstatus(0);
       
       OrderPh ob = OrderPhMap.get(txLogisticID);
       if (ob != null) {
         ob.setItemCount(Integer.valueOf(ob.getItemCount().intValue() + Integer.parseInt(data8.toString().trim())));
         ob.setWorth(Double.valueOf(ob.getWorth().doubleValue() + Double.valueOf(data9.toString().trim()).doubleValue()));
         ob.setItemWeight(Double.valueOf(ob.getItemWeight().doubleValue() + Double.valueOf(data11.toString().trim()).doubleValue()));
         OrderPhMap.put(txLogisticID, ob);
       } else {
         OrderPh.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString().trim())));
         OrderPh.setWorth(Double.valueOf(data9.toString().trim()));
         OrderPh.setItemWeight(Double.valueOf(data11.toString().trim()));
         OrderPhMap.put(txLogisticID, OrderPh);
       } 
       
       OrderPhSku OrderPhSku = new OrderPhSku();
       OrderPhSku.setTxLogisticID(txLogisticID);
       OrderPhSku.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString().trim())));
       OrderPhSku.setAllPrice(Double.valueOf(data9.toString().trim()));
       OrderPhSku.setItemName(data10.toString().trim());
       OrderPhSku.setItemWeight(Double.valueOf(data11.toString().trim()));
       OrderPhSku.setUnitPrice(Double.valueOf(data12.toString().trim()));
       OrderPhSku.setItemsku(data17.toString().trim());
       
       OrderPhSku obs = OrderPhSkuMap.get(String.valueOf(txLogisticID) + data17.toString().trim());
       if (obs != null) {
         message.append("导入第" + row + "行数据失败，失败原因：同一个订单有相同的商品SKU！<br>");
         break;
       } 
       OrderPhSkuMap.put(String.valueOf(txLogisticID) + data17.toString().trim(), OrderPhSku);
       obsList.add(OrderPhSku);
     } 
     Object[] obArr = OrderPhMap.values().toArray();
     List<OrderPh> retList = this.OrderPhDao.findByObArr(obArr);
     if (retList != null && retList.size() > 0) {
       String retTxLogisticID = "";
       for (OrderPh retOb : retList) {
         retTxLogisticID = String.valueOf(retTxLogisticID) + retOb.getTxLogisticID() + ",";
       }
       retTxLogisticID = retTxLogisticID.substring(0, retTxLogisticID.length() - 1);
       message.append("导入数据失败，失败原因：订单号（" + retTxLogisticID + "）已存在！<br>");
     } 
     if (message.toString().length() > 0) {
       result.setMessage(message.toString());
       return result;
     } 
     this.OrderPhDao.batchInsert(obArr);
     int count = this.OrderPhSkuDao.batchInsert(obsList);
     result.setMessage("导入成功" + count + "条！");
     return result;
   }
 
   
   public int deleteOrderPh(String txLogisticID) {
     String[] txLogisticIDArr = txLogisticID.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = txLogisticIDArr).length, b = 0; b < i; ) { String txLogisticIDStr = arrayOfString1[b];
       result = this.OrderPhDao.delete(txLogisticIDStr);
       result = this.OrderPhSkuDao.delete(txLogisticIDStr); b++; }
     
     return result;
   }
 
   
   public int updateAuditstatus(String paramJson) {
     JSONObject jSONObject = JSONObject.parseObject(paramJson);
     JSONArray orderJSONArray = jSONObject.getJSONArray("paramList");
     
     int result = -1;
     for (int i = 0; i < orderJSONArray.size(); i++) {
       JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
       Integer id = orderJSONObject.getInteger("id");
       String mailNo = orderJSONObject.getString("mailNo");
       String txLogisticID = orderJSONObject.getString("txLogisticID");
       
       String merchantNum = orderJSONObject.getString("merchantNum");
       
       OrderPh OrderPh = new OrderPh();
       OrderPh.setId(id.intValue());
       Item item = new Item();
       Tinventory tinventory = new Tinventory();
       Log_inventory log_inventory = new Log_inventory();
       List<OrderPhSku> Bsku = this.OrderPhSkuDao.findById(txLogisticID);
 
       
       for (OrderPhSku b : Bsku) {
         item.setItemSKU(b.getItemsku());
         item.setCompanyCode(merchantNum);
         Item item1 = this.itemDao.findBySku(item);
         if (item1 != null) {
           OrderPhSku ob = new OrderPhSku();
           ob.setItemsku(item1.getItemSKU());
           ob.setInternalNumber(item1.getInternalNumber());
           this.OrderPhSkuDao.update(ob);
         } 
       } 
 
 
       
       int one = 1;
       for (OrderPhSku b : Bsku) {
         item.setItemSKU(b.getItemsku());
         item.setCompanyCode(merchantNum);
         Item item1 = this.itemDao.findBySku(item);
         
         if (item1 == null) {
           OrderPh.setAuditstatus(3);
           one = 11;
 
           
           break;
         } 
       } 
       
       int two = 2;
       if (one != 11) {
         for (OrderPhSku b : Bsku) {
           tinventory.setItemsku(b.getItemsku());
           tinventory.setMerchant(merchantNum);
           List<Tinventory> Tsku = this.tinventoryDao.findBySku(tinventory);
           
           int sum = 0;
           for (Tinventory t : Tsku) {
             sum += t.getSurplusInventory();
           }
           
           if (sum < b.getItemCount().intValue()) {
             OrderPh.setAuditstatus(4);
             two = 22;
             
             break;
           } 
         } 
       }
       
       if (one != 11 && two != 22) {
         for (OrderPhSku b : Bsku) {
           tinventory.setItemsku(b.getItemsku());
           tinventory.setMerchant(merchantNum);
           List<Tinventory> Tsku = this.tinventoryDao.findBySku(tinventory);
           int sum = 0;
           String pc = null;
           
           for (Tinventory t : Tsku) {
             sum += t.getSurplusInventory();
             if (sum >= b.getItemCount().intValue()) {
               pc = t.getPc();
               
               break;
             } 
           } 
           int c = b.getItemCount().intValue();
           for (Tinventory t : Tsku) {
             if (pc.equals(t.getPc())) {
               tinventory.setPreUsedInventory(t.getPreUsedInventory() + c);
               tinventory.setSurplusInventory(t.getSurplusInventory() - c);
               tinventory.setPc(t.getPc());
               this.tinventoryDao.updateTotality(tinventory);
               log_inventory.setStocklot(t.getPc());
               log_inventory.setTxLogisticID(b.getTxLogisticID());
               log_inventory.setItemsku(b.getItemsku());
               log_inventory.setItemName(b.getItemName());
               log_inventory.setItemCount(Integer.valueOf(c));
               log_inventory.setHz(b.getHz());
               log_inventory.setMotion("0");
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               log_inventory.setCreateDate(simpleDateFormat.format(new Date()));
               this.log_inventoryDao.insert(log_inventory);
               break;
             } 
             c -= t.getSurplusInventory();
             tinventory.setPreUsedInventory(t.getPreUsedInventory() + t.getSurplusInventory());
             tinventory.setSurplusInventory(0);
             tinventory.setPc(t.getPc());
             this.tinventoryDao.updateTotality(tinventory);
             log_inventory.setStocklot(t.getPc());
             log_inventory.setTxLogisticID(b.getTxLogisticID());
             log_inventory.setItemsku(b.getItemsku());
             log_inventory.setItemName(b.getItemName());
             log_inventory.setItemCount(Integer.valueOf(t.getSurplusInventory()));
             log_inventory.setHz(b.getHz());
             log_inventory.setMotion("0");
             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             log_inventory.setCreateDate(f.format(new Date()));
             this.log_inventoryDao.insert(log_inventory);
           } 
         } 
       }
 
 
       
       if (one != 11 && two != 22)
       {
         if ("undefined".equals(mailNo) || "".equals(mailNo.trim())) {
           OrderPh.setAuditstatus(1);
         } else {
           OrderPh.setAuditstatus(2);
         } 
       }
       this.OrderPhDao.updateAuditstatus(OrderPh);
     } 
     return result;
   }
   
   public String updateIsCustoms(String ids) throws Exception {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderPh OrderPh = this.OrderPhDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       if ("HTO".equals(OrderPh.getCarrier())) {
         JSONObject ydJson = new JSONObject();
         ydJson.put("operationType", "1");
         JSONArray entityList = new JSONArray();
         JSONObject entity = new JSONObject();
         entity.put("tradeId", OrderPh.getTxLogisticID());
         entity.put("packageNo", "");
         entity.put("totalExpressNo", "");
         entity.put("expressNo", OrderPh.getMailNo());
         entity.put("expressCompanyCode", "");
         entity.put("senderCompany", "");
         entity.put("senderName", "侨缘贸易");
         entity.put("senderPhone", "057153686606");
         entity.put("senderProvince", "浙江省");
         entity.put("senderCity", "杭州市");
         entity.put("senderCountry", "萧山区");
         entity.put("senderAddress", "B型保税区");
         entity.put("senderZip", "");
         entity.put("receiverName", OrderPh.getReceiveMan());
         entity.put("receiverId", "");
         entity.put("receiverProvince", OrderPh.getReceiveProvince());
         entity.put("receiverCity", OrderPh.getReceiveCity());
         entity.put("receiverCountry", OrderPh.getReceiveCounty());
         entity.put("receiverAddress", OrderPh.getReceiveManAddress());
         entity.put("receiverZip", "");
         entity.put("receiverPhone", OrderPh.getReceiveManPhone());
         entity.put("sellerMemo", "123");
         entity.put("postFee", "0");
         entity.put("insuredFee", "0");
         entity.put("weight", "3");
         entity.put("totalFee", OrderPh.getWorth());
         entity.put("title", OrderPh.getItemName());
         entity.put("num", "1");
         entity.put("measureUnit", "");
         entity.put("skuPropertiesName", "");
         entity.put("countryCode", "");
         entity.put("customsAreaCode", "2992");
         entity.put("customsFieldCode", "299201");
         entity.put("moneyCode", "142");
         entity.put("productName", "");
         entity.put("productHs", "");
         entity.put("dutyParagraph", "");
         entityList.add(entity);
         ydJson.put("entityList", entityList);
         String ret = HTOydbg.htoYdbg(ydJson.toJSONString());
         JSONObject retJson = JSONObject.parseObject(ret);
         String isSuccess = retJson.get("isSuccess").toString();
         if ("SUCCESS".equals(isSuccess)) {
           OrderPh.setIsCustoms(1);
           this.OrderPhDao.updateIsCustoms(OrderPh);
         } else if ("FAILED".equals(isSuccess)) {
           String detailInfo = retJson.get("detailInfo").toString();
           return "推送失败：" + detailInfo;
         } 
       } else if ("STO".equals(OrderPh.getCarrier())) {
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("version", "1.0.0");
 
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType").addText("IMPORTBILL");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("wayBillList");
         Element element4 = element3.addElement("wayBill");
         Element element5 = element4.addElement("jkfSign");
         
         element5.addElement("companyCode").addText("669437562");
 
         
         element5.addElement("businessNo").addText(OrderPh.getMailNo());
         element5.addElement("businessType").addText("IMPORTBILL");
         element5.addElement("declareType").addText("1");
         
         element5.addElement("cebFlag").addText("03");
         
         element5.addElement("note");
         Element element6 = element4.addElement("wayBillImportDto");
         element6.addElement("wayBill").addText(OrderPh.getMailNo());
         element6.addElement("packNo").addText("1");
         element6.addElement("grossWeight").addText("3");
         element6.addElement("netWeight").addText("1.2");
         element6.addElement("goodsName").addText("杭州商品");
         element6.addElement("sendArea").addText("浙江省杭州市萧山B保");
         element6.addElement("consigneeArea").addText(OrderPh.getReceiveProvince());
         element6.addElement("consigneeTel").addText(OrderPh.getReceiveManPhone());
         element6.addElement("consignee").addText(OrderPh.getReceiveMan());
         element6.addElement("consigneeAddress").addText(OrderPh.getReceiveManAddress());
         element6.addElement("zipCode").addText("311300");
         element6.addElement("customsCode").addText("2992");
         element6.addElement("worth").addText(OrderPh.getWorth().toString());
         element6.addElement("importDateStr").addText(df.format(new Date()));
         element6.addElement("currCode").addText("142");
         
         element6.addElement("totalWayBill").addText("");
         element6.addElement("logisCompanyCode").addText("669437562");
         element6.addElement("logisCompanyName").addText("申通快递有限公司");
         element6.addElement("feeAmount").addText("0");
         element6.addElement("insureAmount").addText("0");
 
         
         String ret = PushtoBG_yd.toZJyd(document.asXML());
 
 
         
         if (ret.contains("<resultInfo>处理成功</resultInfo>")) {
           OrderPh.setIsCustoms(1);
           this.OrderPhDao.updateIsCustoms(OrderPh);
         } else {
           return "推送失败";
         } 
       }  b++; }
     
     return result;
   }
 
   
   public int pushDd(String ids) {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderPh OrderPh = this.OrderPhDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       Company company = this.companyDao.findByCompanyBm(OrderPh.getMerchantNum());
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("IMPORTORDER");
       
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("orderInfoList");
       Element element4 = element3.addElement("orderInfo");
       
       Element element5 = element4.addElement("jkfSign");
       element5.addElement("companyCode").addText(company.getCompanytszt());
       element5.addElement("businessNo").addText(OrderPh.getMailNo());
       element5.addElement("businessType").addText("IMPORTORDER");
       element5.addElement("declareType").addText("1");
       
       element5.addElement("cebFlag").addText("03");
       
       element5.addElement("note").addText("进口订单");
       
       Element element6 = element4.addElement("jkfOrderImportHead");
       element6.addElement("companyName").addText(company.getCompanyName());
       element6.addElement("companyCode").addText(company.getCompanyCode());
       element6.addElement("ieFlag").addText("I");
       element6.addElement("payType").addText("01");
       element6.addElement("payCompanyCode").addText(company.getPayCompanyCode());
       
       element6.addElement("payCompanyName").addText("上海富友支付服务有限公司 ");
       
       element6.addElement("payNumber").addText(OrderPh.getPayNumber());
       element6.addElement("orderTotalAmount").addText(OrderPh.getWorth().toString());
       element6.addElement("orderGoodsAmount").addText(OrderPh.getWorth().toString());
       element6.addElement("orderNo").addText(OrderPh.getTxLogisticID());
       element6.addElement("orderTaxAmount").addText("0");
       element6.addElement("feeAmount").addText("0");
       element6.addElement("insureAmount").addText("0");
       element6.addElement("eCommerceCode").addText(company.geteCommerceCode());
       element6.addElement("eCommerceName").addText(company.geteCommerceName());
       element6.addElement("tradeTime").addText(df.format(new Date()));
       element6.addElement("currCode").addText("142");
       element6.addElement("totalAmount").addText(OrderPh.getWorth().toString());
       element6.addElement("consigneeEmail").addText("");
       element6.addElement("consigneeTel").addText(OrderPh.getReceiveManPhone());
       element6.addElement("consignee").addText(OrderPh.getReceiveMan());
       element6.addElement("consigneeAddress").addText(OrderPh.getReceiveManAddress());
       element6.addElement("totalCount").addText(OrderPh.getItemCount().toString());
       element6.addElement("postMode").addText("");
       element6.addElement("senderCountry").addText("142");
       element6.addElement("senderName").addText(company.getCompanyName());
       element6.addElement("purchaserId").addText(OrderPh.getBuyerName());
 
 
 
       
       if ("STO".equals(OrderPh.getCarrier())) {
         element6.addElement("logisCompanyName").addText("申通快递有限公司");
         element6.addElement("logisCompanyCode").addText("669437562");
       } else if ("HTO".equals(OrderPh.getCarrier())) {
         element6.addElement("logisCompanyName").addText("杭州百世网络技术有限公司");
         element6.addElement("logisCompanyCode").addText("WL15041401");
       } 
       
       element6.addElement("zipCode").addText("");
       element6.addElement("note").addText("");
       element6.addElement("wayBills").addText(OrderPh.getMailNo());
       element6.addElement("userProcotol").addText("本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任。");
       element6.addElement("rate").addText("1");
       element6.addElement("discount").addText("0");
       
       List<OrderPhSku> OrderPhSkuList = this.OrderPhSkuDao.findById(OrderPh.getTxLogisticID());
       Element element7 = element4.addElement("jkfOrderDetailList");
       Integer integer = Integer.valueOf(1);
       for (OrderPhSku OrderPhSku : OrderPhSkuList) {
         Item item = this.OrderPhSkuDao.findItem(OrderPhSku.getItemsku());
         Element element8 = element7.addElement("jkfOrderDetail");
         element8.addElement("goodsOrder").addText(integer.toString());
         element8.addElement("goodsName").addText(OrderPhSku.getItemName());
         element8.addElement("codeTs").addText(item.getHscode());
         element8.addElement("goodsModel").addText(OrderPhSku.getItemName());
         element8.addElement("originCountry").addText(company.getCountry());
         element8.addElement("unitPrice").addText(OrderPhSku.getUnitPrice().toString());
         element8.addElement("goodsCount").addText(OrderPhSku.getItemCount().toString());
         element8.addElement("goodsUnit").addText(item.getUnitDesc());
         element8.addElement("grossWeight").addText("");
         element8.addElement("currency").addText("142");
         integer = Integer.valueOf(integer.intValue() + 1);
       } 
       Element element9 = element4.addElement("jkfGoodsPurchaser");
       element9.addElement("id").addText(OrderPh.getBuyerName());
       element9.addElement("name").addText(OrderPh.getBuyerName());
       element9.addElement("email").addText("");
       element9.addElement("telNumber").addText(OrderPh.getReceiveManPhone());
       element9.addElement("paperType").addText("01");
       element9.addElement("paperNumber").addText(OrderPh.getBuyerIdNumber());
       element9.addElement("address").addText(OrderPh.getReceiveManAddress());
       
       String ret = PushtoBG_yd.toZJdd(document.asXML());
 
       
       if (ret.contains("<resultInfo>处理成功</resultInfo>")) {
         OrderPh.setIsPushDd(1);
         result = this.OrderPhDao.updateIsPushDd(OrderPh);
       }  b++; }
     
     return result;
   }
 
   
   public int pushQd(String ids) {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderPh OrderPh = this.OrderPhDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       Company company = this.companyDao.findByCompanyBm(OrderPh.getMerchantNum());
       Element root = DocumentHelper.createElement("mo");
       Document document = DocumentHelper.createDocument(root);
       
       root.addAttribute("version", "1.0.0");
 
       
       Element element1 = root.addElement("head");
       element1.addElement("businessType").addText("PERSONAL_GOODS_DECLAR");
       
       Element element2 = root.addElement("body");
       Element element3 = element2.addElement("goodsDeclareModuleList");
       Element element4 = element3.addElement("goodsDeclareModule");
       String str = "HZBO3" + String.format("%013d", new Object[] { Integer.valueOf(OrderPh.getId()) });
       Element element5 = element4.addElement("jkfSign");
       element5.addElement("companyCode").addText("785309525");
       element5.addElement("businessNo").addText(str);
       element5.addElement("businessType").addText("PERSONAL_GOODS_DECLAR");
       element5.addElement("declareType").addText("1");
       
       element5.addElement("cebFlag").addText("03");
       
       element5.addElement("note").addText("进口清单");
       
       Element element6 = element4.addElement("goodsDeclare");
       
       element6.addElement("accountBookNo").addText(company.getAccountBookNo());
       
       element6.addElement("ieFlag").addText("I");
 
       
       element6.addElement("preEntryNumber").addText(str);
       
       element6.addElement("importType").addText("1");
       element6.addElement("inOutDateStr").addText(df.format(new Date()));
       element6.addElement("iePort").addText(company.getDeclPort());
       element6.addElement("destinationPort").addText("142");
       element6.addElement("trafName").addText("");
       element6.addElement("voyageNo").addText("");
       element6.addElement("trafNo").addText("5");
       element6.addElement("trafMode").addText("W");
       element6.addElement("declareCompanyType").addText("个人委托第三方申报");
       element6.addElement("declareCompanyCode").addText(company.getDeclareCompanyCode());
       element6.addElement("declareCompanyName").addText(company.getDeclareCompanyName());
       element6.addElement("companyName").addText(company.getCompanyName());
       element6.addElement("companyCode").addText(company.getCompanyCode());
       element6.addElement("eCommerceCode").addText(company.geteCommerceCode());
       element6.addElement("eCommerceName").addText(company.geteCommerceName());
 
 
 
       
       if ("STO".equals(OrderPh.getCarrier())) {
 
         
         element6.addElement("logisCompanyName").addText("申通快递有限公司");
         element6.addElement("logisCompanyCode").addText("669437562");
       } else if ("HTO".equals(OrderPh.getCarrier())) {
         element6.addElement("logisCompanyName").addText("杭州百世网络技术有限公司");
         element6.addElement("logisCompanyCode").addText("WL15041401");
       } 
       
       element6.addElement("orderNo").addText(OrderPh.getTxLogisticID());
       element6.addElement("wayBill").addText(OrderPh.getMailNo());
       element6.addElement("billNo").addText("");
       element6.addElement("tradeCountry").addText("142");
       element6.addElement("packNo").addText("1");
       element6.addElement("grossWeight").addText("3");
       element6.addElement("netWeight").addText("1.2");
       element6.addElement("warpType").addText("");
       element6.addElement("remark").addText("");
       element6.addElement("declPort").addText(company.getDeclPort());
       element6.addElement("enteringPerson").addText("9999");
       element6.addElement("enteringCompanyName").addText("9999");
       element6.addElement("declarantNo").addText("");
       element6.addElement("customsField").addText(company.getCustomsField());
       element6.addElement("senderName").addText(company.getCompanyName());
       element6.addElement("consignee").addText(OrderPh.getReceiveMan());
       element6.addElement("senderCountry").addText("142");
       element6.addElement("senderCity").addText("");
       element6.addElement("paperType").addText("1");
       element6.addElement("paperNumber").addText(OrderPh.getBuyerIdNumber());
       element6.addElement("consigneeAddress").addText(OrderPh.getReceiveManAddress());
       element6.addElement("purchaserTelNumber").addText(OrderPh.getReceiveManPhone());
       element6.addElement("buyerIdType").addText("1");
       element6.addElement("buyerIdNumber").addText(OrderPh.getBuyerIdNumber());
       element6.addElement("buyerName").addText(OrderPh.getBuyerName());
       element6.addElement("worth").addText(OrderPh.getWorth().toString());
       element6.addElement("feeAmount").addText("0");
       element6.addElement("insureAmount").addText("0");
       element6.addElement("currCode").addText("142");
       element6.addElement("mainGName").addText(OrderPh.getItemName());
       element6.addElement("internalAreaCompanyNo").addText(company.getInternalAreaCompanyNo());
       element6.addElement("internalAreaCompanyName").addText(company.getInternalAreaCompanyName());
       element6.addElement("assureCode").addText(company.getAssureCode());
       element6.addElement("applicationFormNo").addText(company.getApplicationFormNo());
       element6.addElement("isAuthorize").addText("1");
       element6.addElement("licenseNo").addText("");
       
       List<OrderPhSku> OrderPhSkuList = this.OrderPhSkuDao.findById(OrderPh.getTxLogisticID());
       Element element7 = element4.addElement("goodsDeclareDetails");
       Integer integer = Integer.valueOf(1);
       for (OrderPhSku OrderPhSku : OrderPhSkuList) {
         Item item = this.OrderPhSkuDao.findItem(OrderPhSku.getItemsku());
         Element element8 = element7.addElement("goodsDeclareDetail");
         element8.addElement("goodsOrder").addText(integer.toString());
         element8.addElement("codeTs").addText(item.getHscode());
         element8.addElement("goodsItemNo").addText(OrderPhSku.getItemsku());
         element8.addElement("itemRecordNo").addText(OrderPhSku.getItemsku());
         element8.addElement("itemName").addText("");
         element8.addElement("goodsName").addText(OrderPhSku.getItemName());
         element8.addElement("goodsModel").addText(OrderPhSku.getItemName());
         element8.addElement("originCountry").addText(company.getCountry());
         element8.addElement("tradeCurr").addText("142");
         element8.addElement("tradeTotal").addText("");
         element8.addElement("declPrice").addText(OrderPhSku.getUnitPrice().toString());
         element8.addElement("declTotalPrice").addText(OrderPhSku.getAllPrice().toString());
         element8.addElement("useTo").addText("");
         element8.addElement("declareCount").addText(OrderPhSku.getItemCount().toString());
         element8.addElement("goodsUnit").addText(item.getUnitDesc());
         element8.addElement("goodsGrossWeight").addText("");
         element8.addElement("firstUnit").addText(item.getOneUnitDesc());
         element8.addElement("firstCount").addText("1");
 
 
         
         element8.addElement("secondUnit").addText(item.getTwoUnitDesc());
         if (item.getTwoUnitDesc().equals("")) {
           element8.addElement("secondCount").addText("");
         } else {
           element8.addElement("secondCount").addText("1");
         } 
         element8.addElement("productRecordNo").addText(item.getProductRecordNo());
         element8.addElement("webSite").addText("");
         element8.addElement("barCode").addText("");
         element8.addElement("note").addText("");
         integer = Integer.valueOf(integer.intValue() + 1);
       } 
       
       String ret = PushtoBG_yd.toZJqd(document.asXML());
 
       
       if (ret.contains("<resultInfo>处理成功</resultInfo>")) {
         OrderPh.setIsPushQd(1);
         result = this.OrderPhDao.updateIsPushQd(OrderPh);
       }  b++; }
     
     return result;
   }
 
 
   
   public int updateAuditstatusByIds(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderPh OrderPh = this.OrderPhDao.findByIdToWJ(Integer.valueOf(Integer.parseInt(id)));
       
       OrderAccept orderAccept = new OrderAccept();
       orderAccept.setOrderNumber(OrderPh.getTxLogisticID());
       orderAccept.setExpressCode(OrderPh.getCarrier());
       orderAccept.setExpressNumber(OrderPh.getMailNo());
       orderAccept.setBuyerName(OrderPh.getReceiveMan());
       orderAccept.setBuyerNickName(OrderPh.getBuyerName());
       orderAccept.setBuyerIdCard(OrderPh.getBuyerIdNumber());
       orderAccept.setBuyerProvince(OrderPh.getReceiveProvince());
       orderAccept.setBuyerCity(OrderPh.getReceiveCity());
       orderAccept.setBuyerArea(OrderPh.getReceiveCounty());
       orderAccept.setBuyerAddress(OrderPh.getReceiveManAddress());
       orderAccept.setBuyerTel(OrderPh.getReceiveManPhone());
       orderAccept.setZyName(OrderPh.getBillProvideSiteName());
       orderAccept.setZyNumber(OrderPh.getBillProvideSiteCode());
       orderAccept.setSender(OrderPh.getSendName());
       orderAccept.setSenderTel(OrderPh.getSendTel());
       orderAccept.setSenderAddress(OrderPh.getSendAddress());
       orderAccept.setUserId(OrderPh.getUserId());
       orderAccept.setPrintType(0);
       this.orderAcceptDao.insert(orderAccept);
       
       List<OrderPhSku> obsList = this.OrderPhSkuDao.findById(OrderPh.getTxLogisticID());
       for (OrderPhSku OrderPhSku : obsList) {
         GoodsAccept goodsAccept = new GoodsAccept();
         goodsAccept.setExpressNumber(OrderPh.getMailNo());
         goodsAccept.setGoodsName(OrderPhSku.getItemName());
         goodsAccept.setGoodsSKU(OrderPhSku.getItemsku());
         goodsAccept.setNum(OrderPhSku.getItemCount().intValue());
         goodsAccept.setPrice(BigDecimal.valueOf(OrderPhSku.getUnitPrice().doubleValue()));
         goodsAccept.setTotalPrice(BigDecimal.valueOf(OrderPhSku.getAllPrice().doubleValue()));
         goodsAccept.setWeight(Double.valueOf(OrderPhSku.getItemWeight().doubleValue()));
         this.goodsAcceptDao.insert(goodsAccept);
       } 
       OrderPh.setAuditstatus(9);
       result = this.OrderPhDao.updateAuditstatus(OrderPh); b++; }
     
     return result;
   }
 
   
   public int updateAuditstatusByTxLogisticID(String txLogisticID) {
     int result = -1;
     OrderPh OrderPh = this.OrderPhDao.findByTxLogisticID(txLogisticID);
     if (OrderPh != null) {
       if (OrderPh.getAuditstatus() == 9) {
         return result;
       }
       OrderAccept orderAccept = new OrderAccept();
       orderAccept.setOrderNumber(OrderPh.getTxLogisticID());
       orderAccept.setExpressCode(OrderPh.getCarrier());
       orderAccept.setExpressNumber(OrderPh.getMailNo());
       orderAccept.setBuyerName(OrderPh.getReceiveMan());
       orderAccept.setBuyerNickName(OrderPh.getBuyerName());
       orderAccept.setBuyerIdCard(OrderPh.getBuyerIdNumber());
       orderAccept.setBuyerProvince(OrderPh.getReceiveProvince());
       orderAccept.setBuyerCity(OrderPh.getReceiveCity());
       orderAccept.setBuyerArea(OrderPh.getReceiveCounty());
       orderAccept.setBuyerAddress(OrderPh.getReceiveManAddress());
       orderAccept.setBuyerTel(OrderPh.getReceiveManPhone());
       orderAccept.setZyName(OrderPh.getBillProvideSiteName());
       orderAccept.setZyNumber(OrderPh.getBillProvideSiteCode());
       orderAccept.setSender(OrderPh.getSendName());
       orderAccept.setSenderTel(OrderPh.getSendTel());
       orderAccept.setSenderAddress(OrderPh.getSendAddress());
       orderAccept.setUserId(OrderPh.getUserId());
       orderAccept.setPrintType(0);
       this.orderAcceptDao.insert(orderAccept);
       
       List<OrderPhSku> obsList = this.OrderPhSkuDao.findById(txLogisticID);
       for (OrderPhSku OrderPhSku : obsList) {
         GoodsAccept goodsAccept = new GoodsAccept();
         goodsAccept.setExpressNumber(OrderPh.getMailNo());
         goodsAccept.setGoodsName(OrderPhSku.getItemName());
         goodsAccept.setGoodsSKU(OrderPhSku.getItemsku());
         goodsAccept.setNum(OrderPhSku.getItemCount().intValue());
         goodsAccept.setPrice(BigDecimal.valueOf(OrderPhSku.getUnitPrice().doubleValue()));
         goodsAccept.setTotalPrice(BigDecimal.valueOf(OrderPhSku.getAllPrice().doubleValue()));
         goodsAccept.setWeight(Double.valueOf(OrderPhSku.getItemWeight().doubleValue()));
         goodsAccept.setUserId(OrderPh.getUserId());
         this.goodsAcceptDao.insert(goodsAccept);
       } 
       OrderPh.setAuditstatus(9);
       result = this.OrderPhDao.updateAuditstatus(OrderPh);
     } 
     return result;
   }
 
 
 
   
   public int updateIsCustomsByTxLogisticID(String txLogisticID) {
     int result = -1;
     OrderPh OrderPh = new OrderPh();
     OrderPh.setTxLogisticID(txLogisticID);
     OrderPh.setIsCustoms(2);
     result = this.OrderPhDao.updateIsCustomsByTxLogisticID(OrderPh);
     return result;
   }
 
 
   
   public OrderPh findByIdToBG(Integer id) {
     return this.OrderPhDao.findByIdToBG(id);
   }
 
 
   
   public List<OrderPh> exportOrder(Map<String, Object> paramMap) {
     return this.OrderPhDao.exportOrder(paramMap);
   }
 
 
   
   public List<OrderPh> findTxLogisticID() {
     return this.OrderPhDao.findTxLogisticID();
   }
 
 
   
   public int payNumber(OrderPh orderPh) {
     return this.OrderPhDao.payNumber(orderPh);
   }
 
   
   public OrderPh findByMailNo(String mailNo) {
     OrderPh ob = this.OrderPhDao.findByMailNo(mailNo);
 
 
 
     
     return ob;
   }
 
   
   public int updateStatus(String merchantNum) {
     return this.OrderPhDao.updateStatus(merchantNum);
   }
 
   
   public int findaddress(OrderPh orderPh) {
     return this.OrderPhDao.findaddress(orderPh);
   }
 
   
   public int updaddress(OrderPh orderPh) {
     return this.OrderPhDao.updaddress(orderPh);
   }
 
   
   public List<OrderPh> odbdateTime(Map<String, Object> pageMap) {
     return this.OrderPhDao.odbdateTime(pageMap);
   }
 
   
   public List<OrderPh> findCs(String idd) {
     return this.OrderPhDao.findCs(idd);
   }
 
   
   public List<OrderPh> findTxId(String id) {
     return this.OrderPhDao.findTxId(id);
   }
 
   
   public int upm(OrderPh ob) {
     return this.OrderPhDao.upm(ob);
   }
 
   
   public void deletetx(String id) {
     this.OrderPhDao.deletetx(id);
   }
 
   
   public int editodIdN(OrderPh orderPh) {
     return this.OrderPhDao.editodIdN(orderPh);
   }
 
   
   public void updateInvtno(OrderPh orderPh) {
     this.OrderPhDao.updateInvtno(orderPh);
   }
 
 
   
   public OrderPh findOneById(int id) {
     return this.OrderPhDao.findOneById(id);
   }
 
 
   
   public int updateExpressParam(String ids) {
     return 0;
   }
 
 
   
   public int updateIspost(String ids) {
     return 0;
   }
 
 
   
   public String updateIsPushToWms(String ids) throws Exception {
     return null;
   }
 }


