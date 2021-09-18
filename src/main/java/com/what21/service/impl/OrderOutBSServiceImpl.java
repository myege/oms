 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.CompanyForOutBSDao;
 import com.what21.dao.CompanyForOutSTODao;
 import com.what21.dao.ItemForCkDao;
 import com.what21.dao.OrderOutBSDao;
 import com.what21.dao.OrderOutLogDao;
 import com.what21.dao.OrderOutRuleDao;
 import com.what21.dao.OrderOutSTOBSDao;
 import com.what21.model.CompanyForOutBS;
 import com.what21.model.CompanyforoutSto;
 import com.what21.model.ItemForCk;
 import com.what21.model.OrderOutBS;
 import com.what21.model.OrderOutCustom;
 import com.what21.model.OrderOutForExportBS;
 import com.what21.model.OrderOutLog;
 import com.what21.model.OrderOutQueryVoBS;
 import com.what21.model.OrderOutSkuBS;
 import com.what21.model.TOrderOutStoBS;
 import com.what21.service.OrderOutBSService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import com.what21.util.OrderOutExcelUtil;
 import com.zt.kjybd.OutQdForBS;
 import com.zt.kjybd.PushToSTO;
 import java.io.File;
 import java.math.RoundingMode;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class OrderOutBSServiceImpl
   implements OrderOutBSService
 {
   @Autowired
   private OrderOutBSDao orderOutDao;
   @Autowired
   private ItemForCkDao itemForCkDao;
   @Autowired
   private CompanyForOutBSDao companyForOutDao;
   @Autowired
   private OrderOutLogDao orderOutLogDao;
   @Autowired
   private CompanyForOutSTODao companyForOutSTODao;
   @Autowired
   private OrderOutSTOBSDao orderOutSTODao;
   @Autowired
   private OrderOutRuleDao orderOutRuleDao;
   
   public List<OrderOutBS> find(OrderOutQueryVoBS orderOutQueryVo) {
     return this.orderOutDao.find(orderOutQueryVo);
   }
   
   public Integer count(OrderOutQueryVoBS orderOutQueryVo) {
     return this.orderOutDao.count(orderOutQueryVo);
   }
   
   public List<OrderOutSkuBS> findSku(OrderOutQueryVoBS orderOutQueryVo) {
     return this.orderOutDao.findSku(orderOutQueryVo);
   }
   
   public Integer countSku(OrderOutQueryVoBS orderOutQueryVo) {
     return this.orderOutDao.countSku(orderOutQueryVo);
   }
   
   public void deleteByIds(int[] ids) {
     this.orderOutDao.deleteSkuByIds(ids);
     this.orderOutDao.deleteByIds(ids);
   }
 
 
 
   
   public GeneralResult importOrderNew(String string, Integer userid) {
     Long l1 = Long.valueOf(System.currentTimeMillis());
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = OrderOutExcelUtil.read(string);
     List<OrderOutBS> list = new ArrayList<>();
     List<OrderOutSkuBS> list2 = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size();
     String str_date = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
     Map<String, List<Double>> orderno_map = new HashMap<>();
     DecimalFormat df = new DecimalFormat("0.000");
     df.setRoundingMode(RoundingMode.HALF_UP);
     
     for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
       Object[] nowRowData = me.getValue();
       int rowNum = ((Integer)me.getKey()).intValue() + 1;
       String orderno = null;
       if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：订单号没有输入！<br>");
         break;
       } 
       orderno = nowRowData[0].toString();
       
       String consignee = null;
       if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：收货人没有输入！<br>");
         break;
       } 
       consignee = nowRowData[1].toString();
       
       String consigneeaddress = null;
       if (nowRowData[2] != null && StringUtils.isNotEmpty(nowRowData[2].toString().trim())) {
         consigneeaddress = nowRowData[2].toString();
       }
       
       String consigneetel = null;
       if (nowRowData[3] != null && StringUtils.isNotEmpty(nowRowData[3].toString().trim())) {
         consigneetel = nowRowData[3].toString();
       }
       
       String totalwaybill = null;
       if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>主运单号</font>没有输入！<br>");
         break;
       } 
       totalwaybill = nowRowData[4].toString();
       
       String waybillno = null;
       if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>运单号</font>没有输入！<br>");
         break;
       } 
       waybillno = nowRowData[5].toString();
       
       Double rate = null;
       if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>汇率</font>没有输入！<br>");
         break;
       } 
       try {
         rate = Double.valueOf(Double.parseDouble(nowRowData[6].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：请输入正确的<font color='red'>汇率</font>！<br>");
         
         break;
       } 
       String destinationcountry = null;
       if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>运抵国</font>没有输入！<br>");
         break;
       } 
       destinationcountry = nowRowData[7].toString();
 
       
       Double grossweight = null;
       if (nowRowData[8] == null || StringUtils.isEmpty(nowRowData[8].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>毛重</font>没有输入！<br>");
         break;
       } 
       try {
         grossweight = Double.valueOf(Double.parseDouble(nowRowData[8].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：请输入正确的<font color='red'>毛重</font>！<br>");
         
         break;
       } 
       Double netweight = null;
       if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>净重</font>没有输入！<br>");
         break;
       } 
       try {
         netweight = Double.valueOf(Double.parseDouble(nowRowData[9].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：请输入正确的<font color='red'>净重</font>！<br>");
         
         break;
       } 
       
       String goodsname = null;
       if (nowRowData[10] == null || StringUtils.isEmpty(nowRowData[10].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>商品名称</font>没有输入！<br>");
         break;
       } 
       goodsname = nowRowData[10].toString();
       
       String goodsno = null;
       if (nowRowData[11] == null || StringUtils.isEmpty(nowRowData[11].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>商品编码</font>没有输入！<br>");
         break;
       } 
       goodsno = nowRowData[11].toString();
       
       String specifications = null;
       if (nowRowData[12] == null || StringUtils.isEmpty(nowRowData[12].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>商品规格</font>没有输入！<br>");
         break;
       } 
       specifications = nowRowData[12].toString();
 
       
       Integer goodsamount = null;
       if (nowRowData[13] == null || StringUtils.isEmpty(nowRowData[13].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>成交数量</font>没有输入！<br>");
         break;
       } 
       try {
         goodsamount = Integer.valueOf(Integer.parseInt(nowRowData[13].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：请输入正确的<font color='red'>成交数量</font>！<br>");
         
         break;
       } 
       Double unitprice = null;
       if (nowRowData[14] == null || StringUtils.isEmpty(nowRowData[14].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>成交单价</font>没有输入！<br>");
         break;
       } 
       try {
         unitprice = Double.valueOf(Double.parseDouble(nowRowData[14].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：请输入正确的<font color='red'>成交单价</font>！<br>");
         
         break;
       } 
       Double totalprice2 = null;
       if (nowRowData[15] == null || StringUtils.isEmpty(nowRowData[15].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>成交总价</font>没有输入！<br>");
         break;
       } 
       try {
         totalprice2 = Double.valueOf(Double.parseDouble(nowRowData[15].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：请输入正确的<font color='red'>成交总价</font>！<br>");
         break;
       } 
       Double f = Double.valueOf(unitprice.doubleValue() * goodsamount.intValue());
       String str1 = df.format(f);
       String str2 = df.format(totalprice2);
       if (!str1.equals(str2)) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：订单成交总价不和实际的成交单价和数量相对应！<br>");
 
         
         break;
       } 
       
       String currency = null;
       if (nowRowData[16] == null || StringUtils.isEmpty(nowRowData[16].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>币制</font>没有输入！<br>");
         break;
       } 
       currency = nowRowData[16].toString();
       
       String companycode = null;
       if (nowRowData[17] == null || StringUtils.isEmpty(nowRowData[17].toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>商家编码</font>没有输入！<br>");
         break;
       } 
       companycode = nowRowData[17].toString();
       
       OrderOutBS orderOut = new OrderOutBS();
       orderOut.setConsignee(consignee);
       orderOut.setConsigneetel(consigneetel);
       orderOut.setConsigneeaddress(consigneeaddress);
       orderOut.setConsigneecountry(destinationcountry);
       orderOut.setConsigneetel(consigneetel);
       orderOut.setCurrency(currency);
       orderOut.setOrderno(orderno);
       orderOut.setWaybillno(waybillno);
       
       orderOut.setRate(rate.toString());
       
       orderOut.setDestinationcountry(destinationcountry);
 
       
       orderOut.setGrossweight(grossweight.toString());
       orderOut.setNetweight(netweight.toString());
       orderOut.setTotalwaybill(totalwaybill);
       orderOut.setCreatetime(str_date);
       orderOut.setItemcode(companycode);
       orderOut.setUserid(userid);
       orderOut.setStatus(Integer.valueOf(0));
       orderOut.setStostatus(Integer.valueOf(0));
       
       OrderOutSkuBS orderOutSku = new OrderOutSkuBS();
       orderOutSku.setOrderno(orderno);
       orderOutSku.setCreatetime(str_date);
       orderOutSku.setGoodsname(goodsname);
       orderOutSku.setGoodsno(goodsno);
       orderOutSku.setGoodsamount(goodsamount.toString());
       orderOutSku.setSpecifications(specifications);
       orderOutSku.setUnitprice(unitprice.toString());
       orderOutSku.setTotalprice(totalprice2.toString());
       orderOutSku.setWeight(grossweight.toString());
       orderOutSku.setNetweight(netweight.toString());
       
       List<Double> floats = orderno_map.get(orderno);
       if (floats == null) {
         List<Double> temp = new ArrayList<>();
         temp.add(f);
         temp.add(Double.valueOf(goodsamount.intValue()));
         temp.add(grossweight);
         temp.add(netweight);
         orderno_map.put(orderno, temp);
         list.add(orderOut);
       } else {
         floats.set(0, Double.valueOf(((Double)floats.get(0)).doubleValue() + f.doubleValue()));
         floats.set(1, Double.valueOf(((Double)floats.get(1)).doubleValue() + goodsamount.intValue()));
         floats.set(2, Double.valueOf(((Double)floats.get(2)).doubleValue() + grossweight.doubleValue()));
         floats.set(3, Double.valueOf(((Double)floats.get(3)).doubleValue() + netweight.doubleValue()));
         orderno_map.put(orderno, floats);
       } 
       list2.add(orderOutSku);
     } 
     
     if (!message.toString().contains("导入")) {
       for (OrderOutBS orderOut : list) {
         Integer sum = this.orderOutDao.sumByOrderno(orderOut.getOrderno());
         if (sum.intValue() != 0) {
           message.append("导入失败,订单中已有订单号为：<font color=red>" + orderOut.getOrderno() + "</font>的订单");
           result.setMessage(message.toString());
           return result;
         } 
         List<Double> temp = orderno_map.get(orderOut.getOrderno());
         String price = df.format(temp.get(0));
         Integer num = Integer.valueOf(((Double)temp.get(1)).intValue());
         orderOut.setTotalprice(price);
         orderOut.setOrdertotalamount(price);
         orderOut.setGoodsnum(num);
         orderOut.setPackageno(num);
         orderOut.setGrossweight(df.format(temp.get(2)));
         orderOut.setNetweight(df.format(temp.get(3)));
       } 
       this.orderOutDao.insertBatch(list);
       this.orderOutDao.insertSkuBatch(list2);
       message.append("导入成功" + total + "条！");
     } 
     result.setMessage(message.toString());
     System.out.println(System.currentTimeMillis() - l1.longValue());
 
 
 
     
     return result;
   }
   
   public List<String> pushQd(int[] ids) {
     int i = 0;
     List<OrderOutBS> orderOuts = this.orderOutDao.findByiIds(ids);
     List<String> ret = new ArrayList<>();
     StringBuffer sb = new StringBuffer();
     for (OrderOutBS orderOut : orderOuts) {
       try {
         CompanyForOutBS companyForOut = this.companyForOutDao.findByItemcode(orderOut.getItemcode());
         if (companyForOut == null) {
           sb.append("商家编码为：" + orderOut.getItemcode() + "未找到;  ");
           i++;
           break;
         } 
         OrderOutQueryVoBS orderOutQueryVo = new OrderOutQueryVoBS();
         OrderOutCustom orderOutCustom = new OrderOutCustom();
         orderOutCustom.setOrderno(orderOut.getOrderno());
         orderOutQueryVo.setOrderOutCustom(orderOutCustom);
         List<OrderOutSkuBS> list = this.orderOutDao.findSku(orderOutQueryVo);
         
         String str = "STOH2" + String.format("%014d", new Object[] { orderOut.getId() });
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("version", "1.0.0");
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType").addText("BILL");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("jkfGoodsList");
         Element element4 = element3.addElement("jkfGoods");
         Element element5 = element4.addElement("jkfSign");
         element5.addElement("companyCode").addText(companyForOut.getCompanyCode());
         element5.addElement("businessNo").addText(str);
         element5.addElement("businessType").addText("BILL");
         element5.addElement("declareType").addText("1");
         element5.addElement("note").addText("");
         
         Element element6 = element4.addElement("jkfOrderInfo");
         element6.addElement("orderNo").addText(Tools.removeNull(orderOut.getOrderno()));
         element6.addElement("shippingName").addText(companyForOut.getShippingName());
         element6.addElement("payName").addText(companyForOut.getPayName());
         long time = System.currentTimeMillis() / 1000L;
         element6.addElement("invoiceNo").addText(Tools.removeNull(orderOut.getWaybillno()));
         element6.addElement("payTime").addText((new StringBuilder(String.valueOf(time))).toString());
         element6.addElement("shippingTime").addText((new StringBuilder(String.valueOf(time))).toString());
         element6.addElement("goodsNum").addText(Tools.removeNull(orderOut.getGoodsnum()));
         element6.addElement("currency").addText(Tools.removeNull(orderOut.getCurrency()));
         element6.addElement("rate").addText(Tools.removeNull(orderOut.getRate()));
         element6.addElement("orderTotalAmount").addText(Tools.removeNull(orderOut.getOrdertotalamount()));
         element6.addElement("paypalTrade").addText("");
         element6.addElement("realShippingId").addText("2");
         
         element6.addElement("companyName").addText(companyForOut.getCompanyName());
         
         element6.addElement("ownerCode").addText(companyForOut.getOwnerCode());
         element6.addElement("ownerName").addText(companyForOut.getOwnerName());
         element6.addElement("agentCode").addText(companyForOut.getAgentCode());
         element6.addElement("agentName").addText(companyForOut.getAgentName());
         element6.addElement("consignee").addText(Tools.removeNull(orderOut.getConsignee()));
         element6.addElement("consigneeCountry").addText(Tools.removeNull(orderOut.getConsigneecountry()));
         element6.addElement("consigneeAddress").addText(Tools.removeNull(orderOut.getConsigneeaddress()));
         element6.addElement("consigneeTel").addText(Tools.removeNull(orderOut.getConsigneetel()));
         element6.addElement("consigneeEmail").addText(Tools.removeNull(orderOut.getConsigneeemail()));
         element6.addElement("customsCode").addText(companyForOut.getCustomsCode());
         element6.addElement("declareCode").addText(companyForOut.getDeclareCode());
         element6.addElement("declareName").addText(companyForOut.getDeclareName());
         element6.addElement("ebpCode").addText(Tools.removeNull(companyForOut.getEbpCode()));
         element6.addElement("ebpName").addText(Tools.removeNull(companyForOut.getEbpName()));
 
 
         
         Element element7 = element4.addElement("jkfMerchBills");
         Element element8 = element7.addElement("jkfMerchBill");
         element8.addElement("wayBillNo").addText(Tools.removeNull(orderOut.getWaybillno()));
         element8.addElement("orderNo").addText(Tools.removeNull(orderOut.getOrderno()));
         element8.addElement("destinationCountry").addText(Tools.removeNull(orderOut.getDestinationcountry()));
         element8.addElement("companyCode").addText(companyForOut.getCompanyCode());
         element8.addElement("companyName").addText(companyForOut.getCompanyName());
         element8.addElement("ieFlag").addText("E");
         element8.addElement("customsCode").addText(companyForOut.getCustomsCode());
         element8.addElement("totalPrice").addText(Tools.removeNull(orderOut.getTotalprice()));
         element8.addElement("packageNo").addText(Tools.removeNull(orderOut.getPackageno()));
         element8.addElement("grossWeight").addText(Tools.removeNull(orderOut.getGrossweight()));
         element8.addElement("postMode").addText("2");
         element8.addElement("netWeight").addText(Tools.removeNull(orderOut.getNetweight()));
         element8.addElement("logisCompanyName").addText(companyForOut.getLogisCompanyName());
         element8.addElement("taxMode").addText("1");
         element8.addElement("IEPort").addText(companyForOut.getCustomsCode());
         element8.addElement("wrapType").addText("6");
         element8.addElement("exchangeRate").addText(orderOut.getRate());
         element8.addElement("licenseNo").addText("");
         element8.addElement("licensedocu").addText("");
         element8.addElement("transMode").addText("5");
         element8.addElement("tradeMode").addText("1210");
         element8.addElement("logisCompanyCode").addText(companyForOut.getLogisCompanyCode());
         element8.addElement("exportType").addText("");
         element8.addElement("internalAreaCompanyNo").addText(companyForOut.getInternalAreaCompanyNo());
         element8.addElement("internalAreaCompanyName").addText(companyForOut.getInternalAreaCompanyName());
         element8.addElement("totalWayBill").addText(Tools.removeNull(orderOut.getTotalwaybill()));
         
         element8.addElement("manualNo").addText("W29918000005");
         element8.addElement("applicationFormNo").addText("A33015400082017100000001");
         element8.addElement("totalPackNo").addText(Tools.removeNull(orderOut.getTotalwaybill()));
         Element element9 = element8.addElement("jkfMerchBillDetails");
         
         for (OrderOutSkuBS orderOutSku : list) {
           ItemForCk itemForCk = this.itemForCkDao.findByItemSku("ZY" + orderOutSku.getGoodsno());
           if (itemForCk == null) {
             sb.append("商品编码为：ZY" + orderOutSku.getGoodsno() + "未找到; ");
             i++;
             break;
           } 
           Element element10 = element9.addElement("jkfMerchBillDetail");
           element10.addElement("goodsName").addText(Tools.removeNull(orderOutSku.getGoodsname()));
           element10.addElement("goodsNo").addText(Tools.removeNull(orderOutSku.getGoodsno()));
           element10.addElement("weight").addText("1");
           element10.addElement("goodsAmount").addText(Tools.removeNull(orderOutSku.getGoodsamount()));
           element10.addElement("unitPrice").addText(Tools.removeNull(orderOutSku.getUnitprice()));
           element10.addElement("totalPrice").addText(Tools.removeNull(orderOutSku.getTotalprice()));
           element10.addElement("goodsUnit").addText(itemForCk.getOneUnitDesc());
           element10.addElement("currency").addText(Tools.removeNull(orderOut.getCurrency()));
           element10.addElement("warehouseCode").addText("");
           element10.addElement("warehouseName").addText("");
           element10.addElement("locationCode").addText("");
           element10.addElement("locationType").addText("1");
           element10.addElement("goodsModel").addText(orderOutSku.getSpecifications());
           element10.addElement("dutyMode").addText("1");
           element10.addElement("goodsUnit1").addText(itemForCk.getOneUnitDesc());
           element10.addElement("goodsAmount1").addText(String.valueOf(itemForCk.getFirstCount()));
           if (itemForCk.getTwoUnitDesc() == null) {
             element10.addElement("goodsUnit2").addText("");
           } else {
             element10.addElement("goodsUnit2").addText(itemForCk.getTwoUnitDesc());
           } 
           
           element10.addElement("goodsAmount2").addText(String.valueOf(itemForCk.getFirstCount()));
           element10.addElement("sourceNo").addText("05-0201-0001");
         } 
         
         System.out.println("-OrderOut->" + document.asXML());
         String result = OutQdForBS.pushQd(document.asXML());
         
         if (result.contains("处理成功")) {
           this.orderOutDao.updateStatus1(orderOut.getId().intValue()); continue;
         } 
         Pattern p = Pattern.compile("<resultInfo>(.*)</resultInfo>");
         Matcher m = p.matcher(result);
         while (m.find()) {
           sb.append(" " + m.group(1));
         }
         i++;
       }
       catch (Exception e) {
         e.printStackTrace();
         i++;
       } 
     } 
     
     if (i == 0) {
       ret.add("1");
       ret.add("报关成功" + ids.length + "条");
     } else {
       ret.add("0");
       ret.add("报关成功" + (ids.length - i) + "条,失败<font color='red'>" + i + "</font>条.失败原因:" + sb.toString());
     } 
     return ret;
   }
   
   public List<OrderOutBS> findByIds(int[] ids) {
     return this.orderOutDao.findByiIds(ids);
   }
   
   public List<OrderOutSkuBS> findSkusByPid(int id) {
     OrderOutBS orderOut = this.orderOutDao.findById(Integer.valueOf(id));
     return this.orderOutDao.findSkusByOrderno(orderOut.getOrderno());
   }
 
   
   public void endOrders(int[] ids) {
     this.orderOutDao.updateEnd(ids);
   }
 
   
   public void updateSku(OrderOutSkuBS orderOutSku) {
     OrderOutSkuBS ret_sku = this.orderOutDao.findSkuById(orderOutSku.getId());
     ret_sku.setGoodsname(orderOutSku.getGoodsname());
     ret_sku.setGoodsno(orderOutSku.getGoodsno());
     ret_sku.setSpecifications(orderOutSku.getSpecifications());
     this.orderOutDao.updateSku(ret_sku);
   }
 
   
   public OrderOutSkuBS findSkuById(Integer id) {
     return this.orderOutDao.findSkuById(id);
   }
   
   public OrderOutLog findLogByWaybillno(String waybillno) {
     return this.orderOutLogDao.findLogByWaybillno(waybillno);
   }
 
   
   public void updateGoodsno(String goodsno1, String goodsno2) {
     this.orderOutDao.updateGoodsno(goodsno1, goodsno2);
   }
 
   
   public Integer countSkuByGoodsno(String goodsno1) {
     return this.orderOutDao.countSkuByGoodsno(goodsno1);
   }
   
   public OrderOutBS findById(Integer id) {
     return this.orderOutDao.findById(id);
   }
   
   public List<String> pushSTO2(int[] ids) throws Exception {
     List<OrderOutBS> orderOuts = this.orderOutDao.findByiIds(ids);
     List<String> ret = new ArrayList<>();
     List<String> waybillnos = new ArrayList<>();
     List<TOrderOutStoBS> orderOutStos = new ArrayList<>();
     List<Integer> orderOutIds = new ArrayList<>();
     StringBuffer sb = new StringBuffer();
     Date now = Tools.getCurrentTime();
     String strnow = Tools.format("yyyy-MM-dd HH:mm:ss", now);
     int i = 0;
     if (orderOuts.size() != 0) {
       for (OrderOutBS orderOut : orderOuts) {
         if (orderOut.getConsigneeaddress() == null || orderOut.getConsigneetel() == null) {
           i++;
           sb.append("运单号为:" + orderOut.getWaybillno() + "没有手机地址或手机电话" + "<br/>");
           continue;
         } 
         TOrderOutStoBS orderOutSto = new TOrderOutStoBS();
         orderOutSto.setCurrcode("142");
         orderOutSto.setTradeno(orderOut.getOrderno());
         orderOutSto.setLogisticid(orderOut.getWaybillno());
         orderOutSto.setOrderdate(now);
         orderOutSto.setReceiveraddress(orderOut.getConsigneeaddress());
         orderOutSto.setReceivermobile(orderOut.getConsigneetel());
         orderOutSto.setReceivername(orderOut.getConsignee());
         orderOutSto.setGrossweight(Double.valueOf(Double.parseDouble(orderOut.getGrossweight())));
         orderOutSto.setNetweight(Double.valueOf(Double.parseDouble(orderOut.getNetweight())));
         orderOutSto.setPackno(orderOut.getGoodsnum());
         orderOutSto.setBillno(orderOut.getTotalwaybill());
         orderOutSto.setItemcode(orderOut.getItemcode());
         orderOutSto.setStatus(Integer.valueOf(0));
         orderOutSto.setFeeamount(Double.valueOf(0.0D));
         orderOutSto.setInsureamount(Double.valueOf(0.0D));
         orderOutSto.setWaybillno(orderOut.getWaybillno());
         orderOutSto.setRecipientcountry(orderOut.getDestinationcountry());
         orderOutSto.setCreatetime(strnow);
         orderOutSto.setUserid(orderOut.getUserid());
         
         if (orderOutSto.getWaybillno().length() <= 10 || orderOutSto.getWaybillno().length() > 13) {
           waybillnos.add(orderOutSto.getWaybillno());
           orderOutStos.add(orderOutSto);
           
           continue;
         } 
         CompanyforoutSto companyforoutSto = this.companyForOutSTODao.findByItemcode(orderOutSto.getItemcode());
         if (companyforoutSto == null) {
           sb.append("商家编码为：" + orderOutSto.getItemcode() + "未找到;  " + "<br/>");
           i++;
           continue;
         } 
         JSONObject json = new JSONObject();
         json.put("siteCode", "310064");
         json.put("siteName", "杭州仓储部");
         json.put("tradeNo", orderOutSto.getTradeno());
         json.put("waybillNo", orderOutSto.getWaybillno());
 
         
         json.put("logisticId", orderOutSto.getLogisticid());
         json.put("orderDate", orderOutSto.getOrderdate());
         json.put("orderSource", "PORTZJZT");
         json.put("receiverProv", "浙江省");
         
         json.put("receiverCity", "杭州市");
         
         json.put("receiverArea", "江干区");
         
         json.put("receiverTown", "九堡镇");
         
         String address = orderOutSto.getReceiveraddress();
         String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
         Pattern p = Pattern.compile(regEx);
         Matcher m = p.matcher(address);
         address = m.replaceAll("").trim();
         json.put("receiverAddress", address);
         json.put("receiverMobile", orderOutSto.getReceivermobile());
         json.put("receiverName", orderOutSto.getReceivername());
         json.put("receiverPostcode", "000000");
         
         json.put("senderProv", "浙江省");
         
         json.put("senderCity", "杭州市");
         
         json.put("senderArea", "江干区");
         
         json.put("senderTown", "九堡镇");
         
         json.put("senderAddress", companyforoutSto.getSenderaddress());
         json.put("senderMobile", companyforoutSto.getSenderphone());
         json.put("senderName", companyforoutSto.getSendername());
         json.put("senderPostcode", companyforoutSto.getSenderpostcode());
 
         
         json.put("weight", orderOut.getGrossweight());
 
         
         JSONObject json2 = new JSONObject();
         JSONObject json3 = new JSONObject();
         json3.put("grossWeight", orderOut.getGrossweight());
         json3.put("companyCode", companyforoutSto.getCompanycode());
         json3.put("businessNo", companyforoutSto.getBusinessno());
         json3.put("companyName", companyforoutSto.getCompanyname());
         json3.put("recipientCountry", orderOutSto.getRecipientcountry());
         json3.put("insureAmount", orderOutSto.getInsureamount());
         json3.put("feeAmount", orderOutSto.getFeeamount());
         json3.put("billNo", orderOutSto.getBillno());
         json3.put("netWeight", orderOut.getNetweight());
         json3.put("packNo", orderOutSto.getPackno());
         if (orderOutSto.getGoosinfo() != null) {
           json3.put("goosInfo", orderOutSto.getGoosinfo());
         }
         json3.put("goosInfo", "");
         if (orderOutSto.getPackageinfo() != null) {
           json3.put("packageInfo", orderOutSto.getPackageinfo());
         }
         json3.put("packageInfo", "");
         
         json3.put("currCode", "142");
         json3.put("trafMode", "空运");
         json3.put("trafName", "");
         if (orderOutSto.getVoyageno() != null) {
           json3.put("voyageNo", orderOutSto.getVoyageno());
         }
         json3.put("voyageNo", "");
         
         json3.put("declPort", companyforoutSto.getDeclport());
         json3.put("note", "");
         json2.put("otherInfo", json3);
         json.put("items", json2);
 
         
         System.out.println("-OrderOutSTO->" + json.toJSONString());
         String result = PushToSTO.putDataForOut(json.toJSONString());
         
         System.out.println("STO_UPDATE返回值：" + result);
         JSONObject retJson_update = JSONObject.parseObject(result);
         String status_update = retJson_update.getString("success");
         if ("true".equals(status_update)) {
           orderOutSto.setStatus(Integer.valueOf(9));
           orderOutIds.add(orderOut.getId());
         } else {
           i++;
           sb.append(String.valueOf(orderOutSto.getTradeno()) + "：" + retJson_update.getString("errorMsg") + "<br/>");
         } 
         waybillnos.add(orderOutSto.getWaybillno());
         orderOutStos.add(orderOutSto);
       } 
       
       if (orderOutIds.size() != 0) {
         this.orderOutDao.updateSto1(orderOutIds);
       }
       if (waybillnos.size() != 0) {
         this.orderOutSTODao.deleteByWayBillnos(waybillnos);
       }
       if (orderOutStos.size() != 0) {
         this.orderOutSTODao.insertBatch(orderOutStos);
       }
     } 
 
     
     if (i == 0) {
       ret.add("1");
       ret.add("报关成功" + ids.length + "条");
     } else {
       ret.add("0");
       ret.add("报关成功" + (ids.length - i) + "条,失败<font color='red'>" + i + "</font>条.失败原因:" + sb.toString());
     } 
     return ret;
   }
   public List<String> updateWeight(int id, String grossWeight, String netWeight) {
     double d1, d2;
     DecimalFormat df = new DecimalFormat("0.000");
     df.setRoundingMode(RoundingMode.HALF_UP);
     List<String> list = new ArrayList<>();
 
     
     try {
       d1 = Double.parseDouble(grossWeight);
       d2 = Double.parseDouble(netWeight);
     } catch (Exception e) {
       e.printStackTrace();
       list.add("0");
       list.add("重量出现了不是数字的值，最好保留3位小数，三位后会四舍五入！");
       return list;
     } 
     this.orderOutDao.updateWeight(id, df.format(d1), df.format(d2));
     list.add("1");
     list.add("成功");
     return list;
   }
   
   public List<OrderOutForExportBS> findExportByIds(int[] ids) {
     return this.orderOutDao.findExportByIds(ids);
   }
   
   public List<OrderOutForExportBS> findExport(OrderOutQueryVoBS orderOutQueryVo) {
     return this.orderOutDao.findExport(orderOutQueryVo);
   }
 }


