 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.CompanyForOutDao;
 import com.what21.dao.CompanyForOutSTODao;
 import com.what21.dao.ItemForCkDao;
 import com.what21.dao.OrderOutDao;
 import com.what21.dao.OrderOutLogDao;
 import com.what21.dao.OrderOutRuleDao;
 import com.what21.dao.OrderOutSTODao;
 import com.what21.model.CompanyForOut;
 import com.what21.model.CompanyforoutSto;
 import com.what21.model.ItemForCk;
 import com.what21.model.OrderOut;
 import com.what21.model.OrderOutCustom;
 import com.what21.model.OrderOutForExport;
 import com.what21.model.OrderOutLog;
 import com.what21.model.OrderOutQueryVo;
 import com.what21.model.OrderOutRule;
 import com.what21.model.OrderOutSku;
 import com.what21.model.TOrderOutSto;
 import com.what21.service.OrderOutService;
 import com.what21.tools.Tools;
 import com.what21.util.EncryptHG;
 import com.what21.util.GeneralResult;
 import com.what21.util.OrderOutExcelUtil;
 import com.what21.util.Rabbitmq;
 import com.what21.util.timenew;
 import com.zt.kjybd.OutQd;
 import com.zt.kjybd.OutWld;
 import com.zt.kjybd.PushToSTO;
 import com.zt.kjybd.newzs;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.math.BigDecimal;
 import java.math.RoundingMode;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.xml.namespace.QName;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentException;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.binary.Base64;
 @org.springframework.stereotype.Service
 @Transactional
 public class OrderOutServiceImpl
   implements OrderOutService
 {
   @Autowired
   private OrderOutDao orderOutDao;
   @Autowired
   private ItemForCkDao itemForCkDao;
   @Autowired
   private CompanyForOutDao companyForOutDao;
   @Autowired
   private OrderOutLogDao orderOutLogDao;
   @Autowired
   private CompanyForOutSTODao companyForOutSTODao;
   @Autowired
   private OrderOutSTODao orderOutSTODao;
   @Autowired
   private OrderOutRuleDao orderOutRuleDao;
   
   public List<OrderOut> find(OrderOutQueryVo orderOutQueryVo) {
     return this.orderOutDao.find(orderOutQueryVo);
   }
   
   public Integer count(OrderOutQueryVo orderOutQueryVo) {
     return this.orderOutDao.count(orderOutQueryVo);
   }
   
   public List<OrderOutSku> findSku(OrderOutQueryVo orderOutQueryVo) {
     return this.orderOutDao.findSku(orderOutQueryVo);
   }
   
   public Integer countSku(OrderOutQueryVo orderOutQueryVo) {
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
     List<OrderOut> list = new ArrayList<>();
     List<OrderOutSku> list2 = new ArrayList<>();
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
     try {
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
         String locationCode = null;
         if (nowRowData[18] == null || StringUtils.isEmpty(nowRowData[18].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>航班号</font>没有输入！<br>");
           
           break;
         } 
         String totalBag = null;
         if (nowRowData[19] == null || StringUtils.isEmpty(nowRowData[19].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>总包号</font>没有输入！<br>");
           
           break;
         } 
         totalBag = nowRowData[19].toString();
         companycode = nowRowData[17].toString();
         locationCode = nowRowData[18].toString();
         OrderOut orderOut = new OrderOut();
         orderOut.setConsignee(consignee);
         orderOut.setTotalBag(totalBag);
         orderOut.setConsigneetel(consigneetel);
         orderOut.setConsigneeaddress(consigneeaddress);
         orderOut.setConsigneecountry(destinationcountry);
         orderOut.setConsigneetel(consigneetel);
         orderOut.setLocationtype(locationCode);
         orderOut.setLocationcode(locationCode);
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
         
         OrderOutSku orderOutSku = new OrderOutSku();
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
         
         goodsname = goodsname.replaceAll("(.*)(\\(.*|\\（.*)", "$1");
         List<OrderOutRule> orderOutRules = this.orderOutRuleDao.findByGoodsname(goodsname);
         if (orderOutRules.size() == 1) {
           OrderOutRule orderOutRule = orderOutRules.get(0);
           orderOutSku.setGoodsno(orderOutRule.getHscode());
         } 
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
       String WayBillNo = "";
       Boolean check = Boolean.valueOf(false);
       if (!message.toString().contains("导入")) {
         for (OrderOut orderOut : list) {
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
 
           
           Integer sum_waybillno = this.orderOutDao.sumByWayBillNo(orderOut.getWaybillno());
           if (sum_waybillno.intValue() != 0) {
             WayBillNo = String.valueOf(WayBillNo) + orderOut.getWaybillno() + ",";
             check = Boolean.valueOf(true);
           } 
         } 
         if (check.booleanValue()) {
           message.append("导入失败,订单中已有运单号为：<font color=red>" + WayBillNo + "</font>的订单");
           result.setMessage(message.toString());
           return result;
         } 
         this.orderOutDao.insertBatch(list);
         this.orderOutDao.insertSkuBatch(list2);
         message.append("导入成功" + total + "条！");
       } 
       result.setMessage(message.toString());
       System.out.println(System.currentTimeMillis() - l1.longValue());
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
     } 
     return result;
   }
   
   public List<String> pushQd(int[] ids, String totalWayBill) {
     List<String> ret = new ArrayList<>();
     try {
       int i = 0;
       int success = 0;
       
       StringBuffer sb = new StringBuffer();
       List<OrderOut> orderOuts = null;
       if (ids == null) {
         orderOuts = this.orderOutDao.findByTotalWayBill(totalWayBill);
         if (orderOuts.size() == 0) {
           ret.add("0");
           ret.add("提运单号为：" + totalWayBill + "未找到;  ");
           return ret;
         } 
       } else {
         orderOuts = this.orderOutDao.findByiIds(ids);
       } 
       
       for (OrderOut orderOut : orderOuts) {
         try {
           CompanyForOut companyForOut = this.companyForOutDao.findByItemcode(orderOut.getItemcode());
           if (companyForOut == null) {
             sb.append("商家编码为：" + orderOut.getItemcode() + "未找到;  ");
             i++;
             break;
           } 
           OrderOutQueryVo orderOutQueryVo = new OrderOutQueryVo();
           OrderOutCustom orderOutCustom = new OrderOutCustom();
           orderOutCustom.setOrderno(orderOut.getOrderno());
           orderOutQueryVo.setOrderOutCustom(orderOutCustom);
           List<OrderOutSku> list = this.orderOutDao.findSku(orderOutQueryVo);
           
           String str = "STOH2" + String.format("%013d", new Object[] { orderOut.getId() });
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
           element6.addElement("goodsNum").addText(orderOut.getGoodsnum().toString());
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
           element6.addElement("ebpCode").addText(companyForOut.getEbpCode());
           element6.addElement("ebpName").addText(companyForOut.getEbpName());
 
 
           
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
           
           element8.addElement("grossWeight").addText(Tools.removeNull(orderOut.getGrossweight()));
           if ("YZCK".equals(orderOut.getItemcode())) {
             element8.addElement("postMode").addText("3");
             element8.addElement("packageNo").addText("1");
           } else {
             element8.addElement("postMode").addText("2");
             element8.addElement("packageNo").addText(orderOut.getPackageno().toString());
           } 
           
           element8.addElement("netWeight").addText(Tools.removeNull(orderOut.getNetweight()));
           element8.addElement("logisCompanyName").addText(companyForOut.getLogisCompanyName());
           element8.addElement("taxMode").addText(companyForOut.getInternalAreaCompanyName());
           element8.addElement("IEPort").addText(companyForOut.getCustomsCode());
           element8.addElement("wrapType").addText("6");
           element8.addElement("exchangeRate").addText(orderOut.getRate());
           element8.addElement("licenseNo").addText("");
           element8.addElement("licensedocu").addText("");
           if ("YZCK".equals(orderOut.getItemcode())) {
             element8.addElement("transMode").addText("6");
           } else {
             element8.addElement("transMode").addText("5");
           } 
           element8.addElement("tradeMode").addText("9610");
           element8.addElement("logisCompanyCode").addText(companyForOut.getLogisCompanyCode());
           element8.addElement("exportType").addText("");
           element8.addElement("internalAreaCompanyNo").addText(companyForOut.getInternalAreaCompanyNo());
           element8.addElement("internalAreaCompanyName").addText(companyForOut.getInternalAreaCompanyName());
           element8.addElement("totalWayBill").addText(Tools.removeNull(orderOut.getTotalwaybill()));
           element8.addElement("ebpCode").addText(companyForOut.getEbpCode());
           element8.addElement("ebpName").addText(companyForOut.getEbpName());
           
           element8.addElement("totalPackNo").addText(Tools.removeNull(orderOut.getTotalwaybill()));
           Element element9 = element8.addElement("jkfMerchBillDetails");
           
           for (OrderOutSku orderOutSku : list) {
             ItemForCk itemForCk = this.itemForCkDao.findByItemSku("ZY" + orderOutSku.getGoodsno());
             if (itemForCk == null) {
               sb.append("商品编码为：ZY" + orderOutSku.getGoodsno() + "未找到; ");
               i++;
               break;
             } 
             Element element10 = element9.addElement("jkfMerchBillDetail");
             element10.addElement("goodsName").addText(Tools.removeNull(orderOutSku.getGoodsname()));
             element10.addElement("goodsNo").addText(Tools.removeNull(orderOutSku.getGoodsno()));
             element10.addElement("weight").addText(orderOutSku.getWeight());
             element10.addElement("goodsAmount").addText(Tools.removeNull(orderOutSku.getGoodsamount()));
             element10.addElement("unitPrice").addText(Tools.removeNull(orderOutSku.getUnitprice()));
             element10.addElement("totalPrice").addText(Tools.removeNull(orderOutSku.getTotalprice()));
             element10.addElement("goodsUnit").addText(itemForCk.getUnitDesc());
             element10.addElement("currency").addText(Tools.removeNull(orderOut.getCurrency()));
             element10.addElement("warehouseCode").addText("");
             element10.addElement("warehouseName").addText("");
             element10.addElement("locationCode").addText("");
             element10.addElement("locationType").addText("1");
             element10.addElement("goodsModel").addText(orderOutSku.getSpecifications());
             element10.addElement("dutyMode").addText("1");
 
 
             
             if (itemForCk.getOneUnitDesc() == null) {
               element10.addElement("goodsUnit1").addText("");
               element10.addElement("goodsAmount1").addText("");
             } else if (itemForCk.getOneUnitDesc().equals("035")) {
               element10.addElement("goodsUnit1").addText(itemForCk.getOneUnitDesc());
               element10.addElement("goodsAmount1").addText(orderOutSku.getWeight());
             } else {
               element10.addElement("goodsUnit1").addText(itemForCk.getOneUnitDesc());
               element10.addElement("goodsAmount1").addText(String.valueOf(orderOutSku.getGoodsamount()));
             } 
 
             
             if (itemForCk.getTwoUnitDesc() == null) {
               element10.addElement("goodsUnit2").addText("");
               element10.addElement("goodsAmount2").addText(""); continue;
             }  if (itemForCk.getTwoUnitDesc().equals("035")) {
               element10.addElement("goodsUnit2").addText(itemForCk.getTwoUnitDesc());
               element10.addElement("goodsAmount2").addText(orderOutSku.getWeight()); continue;
             } 
             element10.addElement("goodsUnit2").addText(itemForCk.getTwoUnitDesc());
             element10.addElement("goodsAmount2").addText(String.valueOf(orderOutSku.getGoodsamount()));
           }           
           System.out.println("-OrderOut->" + document.asXML());
           String result = OutQd.pushQd(document.asXML());
           
           if (result.contains("处理成功")) {
             this.orderOutDao.updateStatus1(orderOut.getId().intValue());
             success++; continue;
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
         ret.add("报关成功" + success + "条");
       } else {
         ret.add("0");
         ret.add("报关成功" + success + "条,失败<font color='red'>" + i + "</font>条.失败原因:" + sb.toString());
       } 
       return ret;
     } catch (Exception e) {
       ret.add("0");
       ret.add("未知错误！");
       return ret;
     } 
   }
   
   public List<OrderOut> findByIds(int[] ids) {
     return this.orderOutDao.findByiIds(ids);
   }
   
   public List<OrderOutSku> findSkusByPid(int id) {
     OrderOut orderOut = this.orderOutDao.findById(Integer.valueOf(id));
     return this.orderOutDao.findSkusByOrderno(orderOut.getOrderno());
   }
 
   
   public void endOrders(int[] ids) {
     this.orderOutDao.updateEnd(ids);
   }
 
   
   public void updateSku(OrderOutSku orderOutSku) {
     OrderOutSku ret_sku = this.orderOutDao.findSkuById(orderOutSku.getId());
     ret_sku.setGoodsname(orderOutSku.getGoodsname());
     ret_sku.setGoodsno(orderOutSku.getGoodsno());
     ret_sku.setSpecifications(orderOutSku.getSpecifications());
     this.orderOutDao.updateSku(ret_sku);
   }
 
   
   public OrderOutSku findSkuById(Integer id) {
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
   
   public OrderOut findById(Integer id) {
     return this.orderOutDao.findById(id);
   }
   
   public List<String> pushSTO2(int[] ids, String totalWayBill) throws Exception {
     List<String> ret = new ArrayList<>();
     try {
       List<OrderOut> orderOuts = null;
       if (ids == null) {
         orderOuts = this.orderOutDao.findByTotalWayBill(totalWayBill);
         if (orderOuts.size() == 0) {
           ret.add("0");
           ret.add("提运单号为：" + totalWayBill + "未找到;  ");
           return ret;
         } 
       } else {
         orderOuts = this.orderOutDao.findByiIds(ids);
       } 
       List<String> waybillnos = new ArrayList<>();
       List<TOrderOutSto> orderOutStos = new ArrayList<>();
       List<Integer> orderOutIds = new ArrayList<>();
       StringBuffer sb = new StringBuffer();
       Date now = Tools.getCurrentTime();
       String strnow = Tools.format("yyyy-MM-dd HH:mm:ss", now);
       int i = 0;
       int success = 0;
       if (orderOuts.size() != 0) {
         for (OrderOut orderOut : orderOuts) {
           if (orderOut.getConsigneeaddress() == null || orderOut.getConsigneetel() == null) {
             i++;
             sb.append("运单号为:" + orderOut.getWaybillno() + "没有手机地址或手机电话" + "<br/>");
             continue;
           } 
           TOrderOutSto orderOutSto = new TOrderOutSto();
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
           System.out.println(orderOutSto.getWaybillno().length());
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
             success++;
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
         ret.add("报关成功" + success + "条");
       } else {
         ret.add("0");
         ret.add("报关成功" + i + "条,失败<font color='red'>" + i + "</font>条.失败原因:" + sb.toString());
       } 
       return ret;
     } catch (Exception e) {
       ret.add("0");
       ret.add("未知错误！");
       return ret;
     } 
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
   
   public List<OrderOutForExport> findExportByIds(int[] ids) {
     return this.orderOutDao.findExportByIds(ids);
   }
   
   public List<OrderOutForExport> findExport(OrderOutQueryVo orderOutQueryVo) {
     return this.orderOutDao.findExport(orderOutQueryVo);
   }
 
 
   
   public List<String> pushWLD(int[] ids, String totalWayBill) {
     List<String> ret = new ArrayList<>();
     try {
       List<OrderOut> orderOutStos = null;
       if (ids == null) {
         orderOutStos = this.orderOutDao.findByTotalWayBill(totalWayBill);
         if (orderOutStos.size() == 0) {
           ret.add("1");
           ret.add("提运单号为：" + totalWayBill + "未找到;  ");
           return ret;
         } 
       } else {
         orderOutStos = this.orderOutDao.findByiIds(ids);
       } 
       StringBuffer sb = new StringBuffer();
       int i = 0;
       int success = 0;
       
       for (OrderOut orderOutSto : orderOutStos) {
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("version", "1.0.0");
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType").addText("EXPORTWAYBILL");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("JkfExportWaybillInfoList");
 
         
         CompanyForOut companyForOut = this.companyForOutDao.findByItemcode(orderOutSto.getItemcode());
         String businessNo = "STOH2" + String.format("%013d", new Object[] { orderOutSto.getId() });
         
         Element element4 = element3.addElement("JkfExportWaybill");
         
         Element element5 = element4.addElement("jkfSign");
         element5.addElement("companyCode").addText(companyForOut.getCompanyCode());
         element5.addElement("businessNo").addText(businessNo);
         element5.addElement("businessType").addText("EXPORTWAYBILL");
         element5.addElement("declareType").addText("1");
         element5.addElement("note").addText("");
         
         Element element6 = element4.addElement("jkfExportWaybillInfo");
         element6.addElement("declPort").addText(companyForOut.getCustomsCode());
         element6.addElement("orderNo").addText(Tools.removeNull(orderOutSto.getOrderno()));
         element6.addElement("companyCode").addText("");
         element6.addElement("companyName").addText("");
         element6.addElement("logisCompanyCode").addText(companyForOut.getLogisCompanyCode());
         element6.addElement("logisCompanyName").addText(companyForOut.getLogisCompanyName());
         element6.addElement("wayBill").addText(Tools.removeNull(orderOutSto.getWaybillno()));
         element6.addElement("ieFlag").addText("E");
         element6.addElement("trafMode").addText("5");
         element6.addElement("trafName").addText("");
         element6.addElement("voyageNo").addText(Tools.removeNull(""));
         element6.addElement("billNo").addText(Tools.removeNull(orderOutSto.getTotalwaybill()));
         element6.addElement("feeAmount").addText(Tools.removeNull("0.00"));
         element6.addElement("insureAmount").addText(Tools.removeNull("0.00"));
         element6.addElement("currCode").addText("142");
         element6.addElement("grossWeight").addText(Tools.removeNull(orderOutSto.getGrossweight()));
         element6.addElement("netWeight").addText(Tools.removeNull(orderOutSto.getNetweight()));
         element6.addElement("packNo").addText(Tools.removeNull(orderOutSto.getGoodsnum()));
         element6.addElement("packageInfo").addText(Tools.removeNull(""));
         element6.addElement("goosInfo").addText(Tools.removeNull(""));
         element6.addElement("consignee").addText(Tools.removeNull(orderOutSto.getConsignee()));
         element6.addElement("consigneeAddress").addText(Tools.removeNull(orderOutSto.getConsigneeaddress()));
         element6.addElement("consigneeTel").addText(Tools.removeNull(orderOutSto.getConsigneetel()));
         element6.addElement("recipientCountry").addText(Tools.removeNull(orderOutSto.getDestinationcountry()));
         element6.addElement("senderName").addText("张三");
         element6.addElement("senderAddress").addText("杭州江干");
         element6.addElement("senderPhone").addText("13719171726");
         element6.addElement("remarks").addText("");
         
         System.out.println("---------->" + document.asXML());
         String result = OutWld.pushWld(document.asXML());
         if (result.contains("处理成功")) {
           this.orderOutSTODao.updateStatus(orderOutSto.getId());
           success++; continue;
         } 
         Pattern p = Pattern.compile("<resultInfo>(.*)</resultInfo>");
         Matcher m = p.matcher(result);
         while (m.find()) {
           sb.append(" " + m.group(1));
         }
         i++;
       } 
       
       if (i == 0) {
         ret.add("1");
         ret.add("推送成功" + success + "条");
       } else {
         ret.add("0");
         ret.add("推送成功" + success + "条,失败<font color='red'>" + i + "</font>条.失败原因:" + sb.toString());
       } 
       return ret;
     } catch (Exception e) {
       ret.add("0");
       ret.add("未知错误！");
       return ret;
     } 
   }
 
 
 
   
   public int pushZsYd(int[] ids) throws IOException, DocumentException {
     List<OrderOut> list = this.orderOutDao.findByiIds(ids);
     
     Element cebRoot = DocumentHelper.createElement("ceb:CEB505Message");
     cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
 
     
     cebRoot.addAttribute("guid", "311af125-6fed-4603-8c5d-49b1fa4b4b9b");
     cebRoot.addAttribute("version", "1.0");
     cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
 
     
     Document document = DocumentHelper.createDocument(cebRoot);
 
 
     
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
     String date = dateFormat.format(new Date());
     
     for (OrderOut orderOut : list) {
       
       CompanyForOut companyForOut = this.companyForOutDao.findByItemcode(orderOut.getItemcode());
       
       List<OrderOutSku> orderOutSkus = this.orderOutDao.findSkusByOrderno(orderOut.getOrderno());
       OrderOutSku orderOutSku = orderOutSkus.get(0);
       Element Logistics = cebRoot.addElement("ceb:Logistics");
       
       Logistics.addElement("ceb:guid").addText("4CDE1CFD-EDED-46B1-946C-B8022E42FC94");
       Logistics.addElement("ceb:appType").addText("1");
       Logistics.addElement("ceb:appTime").addText(date);
       Logistics.addElement("ceb:appStatus").addText("2");
       Logistics.addElement("ceb:logisticsCode").addText(companyForOut.getLogisCompanyCode());
       Logistics.addElement("ceb:logisticsName").addText(companyForOut.getLogisCompanyName());
       Logistics.addElement("ceb:logisticsNo").addText(orderOut.getWaybillno());
       Logistics.addElement("ceb:freight").addText("0.00");
       Logistics.addElement("ceb:insuredFee").addText("0.00");
       Logistics.addElement("ceb:currency").addText("142");
       Logistics.addElement("ceb:grossWeight").addText(orderOut.getGrossweight());
       Logistics.addElement("ceb:packNo").addText(String.valueOf(orderOut.getPackageno()));
       Logistics.addElement("ceb:goodsInfo").addText(orderOutSku.getGoodsname());
       Logistics.addElement("ceb:ebcCode").addText("");
       Logistics.addElement("ceb:ebcName").addText("");
       Logistics.addElement("ceb:ebcTelephone").addText("");
       Logistics.addElement("ceb:note").addText("");
     } 
 
     
     Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
     BaseTransfer.addElement("ceb:copCode").addText("1105910159");
     BaseTransfer.addElement("ceb:copName").addText("东方物通科技(北京)有限公司");
     BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
     BaseTransfer.addElement("ceb:dxpId").addText("DXPLGS0000000001");
     BaseTransfer.addElement("ceb:note").addText("test");
     
     URL url = new URL("http://60.190.249.117:9090/newyorkTransferWebapps/rest/transferDeclare");
     
     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
     connection.setRequestMethod("POST");
     connection.setDoOutput(true);
     connection.setDoInput(true);
     connection.setUseCaches(false);
     connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
     connection.connect();
     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
 
     
     byte[] bytes = ("xml=" + document.asXML()).getBytes();
     outputStream.write(bytes);
     outputStream.flush();
     outputStream.close();
     InputStreamReader is = new InputStreamReader(connection.getInputStream());
     BufferedReader br = new BufferedReader(is);
     String line = "";
     StringBuffer sb = new StringBuffer();
     while ((line = br.readLine()) != null) {
       line = new String(line.getBytes(), "UTF-8");
       sb.append(line);
     } 
     System.out.println(sb);
     br.close();
     String xml = null;
     if (sb.toString().indexOf("true") != -1) {
       xml = sb.toString().substring(23, sb.length() - 2);
     }
     
     System.out.println(xml);
     
     Element root = DocumentHelper.createElement("dxp:DxpMsg").addAttribute("xmlns:dxp", "http://www.chinaport.gov.cn/dxp")
       .addAttribute("ver", "1.0").addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
     root.addNamespace("dxp", "http://www.chinaport.gov.cn/dxp");
     Document document1 = DocumentHelper.createDocument(root);
     Element TransInfo = root.addElement("dxp:TransInfo");
     Element Data = root.addElement("dxp:Data");
     Element CopMsgId = TransInfo.addElement("dxp:CopMsgId");
     Element SenderId = TransInfo.addElement("dxp:SenderId");
     Element ReceiverIds = TransInfo.addElement("dxp:ReceiverIds");
     Element ReceiverId = ReceiverIds.addElement("dxp:ReceiverId");
     Element CreaTime = TransInfo.addElement("dxp:CreatTime");
     Element MsgType = TransInfo.addElement("dxp:MsgType");
     Data.addText(Base64.encodeBase64String(xml.toString().getBytes()));
 
     
     SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
     String date1 = dateFormat1.format(new Date());
     CopMsgId.addText("DXPENT0000019448201805021557170007");
     SenderId.addText("DXPENT0000019448");
     ReceiverId.addText("DXPEDCCEB0000002");
 
     
     SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
     String date2 = dateFormat2.format(new Date());
     CreaTime.addText(date2);
     MsgType.addText("");
     System.out.println(document1.asXML());
     String message = pushQd(document1.asXML());
     if (message.equals("处理成功")) {
       int n = ids.length;
       Integer[] iarr = new Integer[n];
       for (int i = 0; i < n; i++) {
         iarr[i] = new Integer(ids[i]);
       }
       List<Integer> lis = Arrays.asList(iarr);
       this.orderOutDao.updateSto1(lis);
       return 0;
     } 
     return 1;
   }
   
   public static String pushQd(String xmlStr) {
     String result = "";
     
     try {
       String endpoint = "http://122.224.230.4:18003/newyorkWS/ws/ReceiveCebDeclare?wsdl";
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
       call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_STRING);
       byte[] inputContent = xmlStr.getBytes("utf-8");
       
       String content = new String(inputContent);
       result = (String)call.invoke(new Object[] { content });
       
       System.out.println("result is------------>" + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     String message = result.substring(result.indexOf("<resultInfo>") + 12, result.lastIndexOf("</resultInfo>"));
     return message;
   }
   
   public String outDD3J(int[] ids, int mesg, Integer userid) throws Exception {
     List<Integer> idList = new ArrayList<>();
     for (int c = 1; c <= ids.length; c++) {
       idList.add(Integer.valueOf(ids[c - 1]));
       if (c % 100 == 0 || c == ids.length) {
         
         String xml = "";
         List<OrderOut> findByiIds = this.orderOutDao.findByiIdsList(idList);
         List<OrderOutSku> skuList = this.orderOutDao.findSkuByIdSList(idList);
 
         
         if (mesg == 303) {
           String str = "CK0E1CFD-MCMC-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
           Element cebRoot = DocumentHelper.createElement("ceb:CEB303Message");
           cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
           
           cebRoot.addAttribute("guid", str);
           cebRoot.addAttribute("version", "1.0");
           cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
           Document document = DocumentHelper.createDocument(cebRoot);
           for (OrderOut out : findByiIds) {
             Element Order = cebRoot.addElement("ceb:Order");
             Element OrderHead = Order.addElement("ceb:OrderHead");
             OrderHead.addElement("ceb:guid").addText(str);
             OrderHead.addElement("ceb:appType").addText("1");
             OrderHead.addElement("ceb:appTime").addText(timenew.newtime());
             OrderHead.addElement("ceb:appStatus").addText("2");
             OrderHead.addElement("ceb:orderType").addText("E");
             OrderHead.addElement("ceb:orderNo").addText(out.getOrderno());
             OrderHead.addElement("ceb:ebpCode").addText("3316965628");
             OrderHead.addElement("ceb:ebpName").addText("浙江保宏境通供应链管理有限公司");
             OrderHead.addElement("ceb:ebcCode").addText("3316965628");
             OrderHead.addElement("ceb:ebcName").addText("浙江保宏境通供应链管理有限公司");
             OrderHead.addElement("ceb:goodsValue").addText(out.getTotalprice());
             OrderHead.addElement("ceb:freight").addText("0");
             OrderHead.addElement("ceb:currency").addText("502");
             OrderHead.addElement("ceb:note");
             int i = 1;
             for (OrderOutSku sku : skuList) {
               if (sku.getOrderno().equals(out.getOrderno())) {
                 ItemForCk itemForCk = this.itemForCkDao.findByItemSku("ZY" + sku.getGoodsno());
                 if (itemForCk == null) {
                   return "商品条码" + sku.getGoodsno() + "不存在请重新申报";
                 }
                 Element OrderList = Order.addElement("ceb:OrderList");
                 OrderList.addElement("ceb:gnum").addText((i++)+"");
                 OrderList.addElement("ceb:itemNo").addText("AHG");
                 OrderList.addElement("ceb:itemName").addText(sku.getGoodsname());
                 OrderList.addElement("ceb:itemDescribe");
                 OrderList.addElement("ceb:barCode").addText(sku.getGoodsno());
                 OrderList.addElement("ceb:unit").addText(itemForCk.getUnitDesc());
                 OrderList.addElement("ceb:currency").addText("502");
                 OrderList.addElement("ceb:qty").addText(sku.getGoodsamount());
                 OrderList.addElement("ceb:price").addText(sku.getUnitprice());
                 OrderList.addElement("ceb:totalPrice").addText(sku.getTotalprice());
                 OrderList.addElement("ceb:note").addText("备注");
               } 
             } 
           } 
           Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
           BaseTransfer.addElement("ceb:copCode").addText("3316965628");
           BaseTransfer.addElement("ceb:copName").addText("浙江保宏境通供应链管理有限公司");
           BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
           BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000019798");
           
           BaseTransfer.addElement("ceb:note").addText("备注");
 
 
 
           
           EncryptHG.sendGet(document.asXML(), mesg, userid.toString(), "123456", "123456");
         
         }
         else if (603 == mesg) {
           String str = "CK0E1CFD-MGMG-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
           
           Element cebRoot = DocumentHelper.createElement("ceb:CEB603Message");
           cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
           
           cebRoot.addAttribute("guid", str);
           cebRoot.addAttribute("version", "1.0");
           cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
           Document document = DocumentHelper.createDocument(cebRoot);
           for (OrderOut out : findByiIds) {
             String copNo = "STOH2" + String.format("%013d", new Object[] { out.getId() });
             Element Logistics = cebRoot.addElement("ceb:Inventory");
             Element InventoryHead = Logistics.addElement("ceb:InventoryHead");
             InventoryHead.addElement("ceb:guid").addText(str);
             InventoryHead.addElement("ceb:appType").addText("1");
             InventoryHead.addElement("ceb:appTime").addText(timenew.newtime());
             InventoryHead.addElement("ceb:appStatus").addText("2");
             InventoryHead.addElement("ceb:customsCode").addText("2916");
             InventoryHead.addElement("ceb:ebpCode").addText("3316965628");
             InventoryHead.addElement("ceb:ebpName").addText("浙江保宏境通供应链管理有限公司");
             InventoryHead.addElement("ceb:orderNo").addText(out.getOrderno());
             
             int length = String.valueOf(out.getId()).length();
             int lsh = 9 - length;
             String lshstr = ""; int i;
             for (i = 0; i < lsh; i++) {
               lshstr = String.valueOf(lshstr) + "0";
             }
             if (userid.intValue() == 108) {
               InventoryHead.addElement("ceb:logisticsCode").addText("3301980093");
               InventoryHead.addElement("ceb:logisticsName").addText("浙江顺丰速运有限公司");
             } else if (userid.intValue() == 9) {
               InventoryHead.addElement("ceb:logisticsCode").addText("3301980080");
               InventoryHead.addElement("ceb:logisticsName").addText("杭州日晟国际货运代理有限公司");
             } else if (userid.intValue() == 80) {
               InventoryHead.addElement("ceb:logisticsCode").addText("3120980110");
               InventoryHead.addElement("ceb:logisticsName").addText("申通快递有限公司");
             } 
 
 
             
             InventoryHead.addElement("ceb:logisticsNo").addText(out.getWaybillno());
             InventoryHead.addElement("ceb:copNo").addText(copNo);
             InventoryHead.addElement("ceb:ieFlag").addText("E");
             InventoryHead.addElement("ceb:portCode").addText("2916");
             InventoryHead.addElement("ceb:ieDate").addText(timenew.newtime3());
             InventoryHead.addElement("ceb:statisticsFlag").addText("A");
             InventoryHead.addElement("ceb:agentCode").addText("3316965628");
             InventoryHead.addElement("ceb:agentName").addText("浙江保宏境通供应链管理有限公司");
             InventoryHead.addElement("ceb:ebcCode").addText("3316965628");
             InventoryHead.addElement("ceb:ebcName").addText("浙江保宏境通供应链管理有限公司");
             InventoryHead.addElement("ceb:ownerCode").addText("3316965628");
             InventoryHead.addElement("ceb:ownerName").addText("浙江保宏境通供应链管理有限公司");
             InventoryHead.addElement("ceb:iacCode");
             InventoryHead.addElement("ceb:iacName");
             InventoryHead.addElement("ceb:emsNo");
             InventoryHead.addElement("ceb:tradeMode").addText("9610");
             InventoryHead.addElement("ceb:trafMode").addText("5");
             InventoryHead.addElement("ceb:voyageNo").addText("");
             InventoryHead.addElement("ceb:billNo").addText(out.getTotalwaybill());
             InventoryHead.addElement("ceb:totalPackageNo").addText(out.getTotalBag());
             InventoryHead.addElement("ceb:loctNo");
             InventoryHead.addElement("ceb:licenseNo");
             InventoryHead.addElement("ceb:country").addText(out.getDestinationcountry());
             InventoryHead.addElement("ceb:POD").addText(out.getDestinationcountry());
             InventoryHead.addElement("ceb:freight").addText("0");
             InventoryHead.addElement("ceb:fCurrency").addText("502");
             InventoryHead.addElement("ceb:fFlag").addText("3");
             InventoryHead.addElement("ceb:insuredFee").addText("0");
             InventoryHead.addElement("ceb:iCurrency").addText("502");
             InventoryHead.addElement("ceb:iFlag").addText("3");
             InventoryHead.addElement("ceb:wrapType").addText("1");
             InventoryHead.addElement("ceb:packNo").addText("1");
             InventoryHead.addElement("ceb:grossWeight").addText(out.getGrossweight());
             InventoryHead.addElement("ceb:netWeight").addText(out.getNetweight());
             InventoryHead.addElement("ceb:note");
             i = 1;
             for (OrderOutSku sku : skuList) {
               if (sku.getOrderno().equals(out.getOrderno())) {
                 ItemForCk itemForCk = this.itemForCkDao.findByItemSku("ZY" + sku.getGoodsno());
                 if (itemForCk == null) {
                   return "商品条码" + sku.getGoodsno() + "不存在请重新申报";
                 }
                 String substring = itemForCk.getHscode().substring(0, 4);
                 Element InventoryList = Logistics.addElement("ceb:InventoryList");
                 InventoryList.addElement("ceb:gnum").addText((i++)+"");
                 InventoryList.addElement("ceb:itemNo").addText("AF001-001");
                 InventoryList.addElement("ceb:itemRecordNo").addText("");
                 InventoryList.addElement("ceb:itemName").addText(sku.getGoodsname());
                 InventoryList.addElement("ceb:gcode").addText(String.valueOf(substring) + "000000");
                 InventoryList.addElement("ceb:gname").addText(sku.getGoodsname());
                 InventoryList.addElement("ceb:gmodel").addText("无型号");
                 InventoryList.addElement("ceb:barCode").addText(sku.getGoodsno());
                 InventoryList.addElement("ceb:country").addText(out.getDestinationcountry());
                 InventoryList.addElement("ceb:currency").addText("502");
                 
                 if (itemForCk.getUnitDesc().equals("035")) {
                   InventoryList.addElement("ceb:qty").addText(sku.getWeight());
                 } else {
                   InventoryList.addElement("ceb:qty").addText(sku.getGoodsamount());
                 } 
                 
                 InventoryList.addElement("ceb:qty1").addText(String.format("%.3f", new Object[] { Double.valueOf(Double.parseDouble(sku.getWeight())) }));
                 
                 if (itemForCk.getTwoUnitDesc() != null && !itemForCk.getTwoUnitDesc().equals(""))
                 {
                   if (itemForCk.getTwoUnitDesc().equals("035")) {
                     InventoryList.addElement("ceb:qty2").addText(String.format("%.3f", new Object[] { Double.valueOf(Double.parseDouble(sku.getWeight())) }));
                   } else {
                     InventoryList.addElement("ceb:qty2").addText(String.valueOf(String.format("%.3f", new Object[] { Double.valueOf(Double.parseDouble(sku.getGoodsamount())) })));
                   } 
                 }
                 InventoryList.addElement("ceb:unit").addText(itemForCk.getUnitDesc());
                 
                 InventoryList.addElement("ceb:unit1").addText("035");
                 
                 if (itemForCk.getTwoUnitDesc() != null)
                 {
                   
                   InventoryList.addElement("ceb:unit2").addText(itemForCk.getTwoUnitDesc());
                 }
                 
                 InventoryList.addElement("ceb:price").addText(sku.getUnitprice());
                 InventoryList.addElement("ceb:totalPrice").addText(sku.getTotalprice());
                 InventoryList.addElement("ceb:note");
               } 
             } 
           } 
           Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
           BaseTransfer.addElement("ceb:copCode").addText("3316965628");
           BaseTransfer.addElement("ceb:copName").addText("浙江保宏境通供应链管理有限公司");
           BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
           BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000019798");
           
           EncryptHG.sendGet(document.asXML(), mesg,userid.toString(), "123456", "123456");
         }         
         idList.clear();
       } 
     } 
     return "";
   }
 
   
   public String outQDZFD3J(int[] id, int mesg, Integer userid) throws Exception {
     List<OrderOut> findByiIds = this.orderOutDao.findByiIds(id);
     double weight = 0.0D;
     String str = "CK0E1CFD-MZFD-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
     Element cebRoot = DocumentHelper.createElement("ceb:CEB607Message");
     cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
     
     cebRoot.addAttribute("guid", str);
     cebRoot.addAttribute("version", "1.0");
     cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
     Document document = DocumentHelper.createDocument(cebRoot);
     OrderOut orderOut = findByiIds.get(0);
     int x = 1;
     int count = (int)Math.ceil((findByiIds.size() / 1000));
     for (int i = 1; i <= findByiIds.size(); i++) {
       weight += Double.parseDouble(((OrderOut)findByiIds.get(i - 1)).getGrossweight());
     }
     List<OrderOut> orderList = new ArrayList<>();
     for (int j = 1; j <= findByiIds.size(); j++) {
       int b = 1;
       orderList.add(findByiIds.get(j - 1));
       if (j % 1000 == 0 || j == findByiIds.size()) {
         Element WayBill = cebRoot.addElement("ceb:WayBill");
         Element WayBillHead = WayBill.addElement("ceb:WayBillHead");
         WayBillHead.addElement("ceb:guid").addText(str);
         WayBillHead.addElement("ceb:appType").addText("1");
         WayBillHead.addElement("ceb:appTime").addText(timenew.newtime());
         WayBillHead.addElement("ceb:appStatus").addText("2");
         WayBillHead.addElement("ceb:customsCode").addText("2916");
         String copNo = "zfdH2" + String.format("%013d", new Object[] { orderOut.getId() });
         WayBillHead.addElement("ceb:copNo").addText(copNo);
         WayBillHead.addElement("ceb:agentCode").addText("3316965628");
         WayBillHead.addElement("ceb:agentName").addText("浙江保宏境通供应链管理有限公司");
         WayBillHead.addElement("ceb:trafMode").addText("5");
         WayBillHead.addElement("ceb:trafName").addText("航空运输");
         WayBillHead.addElement("ceb:voyageNo").addText(orderOut.getLocationcode());
         WayBillHead.addElement("ceb:billNo").addText(orderOut.getTotalwaybill());
         WayBillHead.addElement("ceb:grossWeight").addText((new StringBuilder(String.valueOf((new BigDecimal(weight)).setScale(2, 4).doubleValue()))).toString());
         if (userid.intValue() == 108) {
           WayBillHead.addElement("ceb:logisticsCode").addText("3301980093");
           WayBillHead.addElement("ceb:logisticsName").addText("浙江顺丰速运有限公司");
         } else if (userid.intValue() == 9) {
           WayBillHead.addElement("ceb:logisticsCode").addText("3301980080");
           WayBillHead.addElement("ceb:logisticsName").addText("杭州日晟国际货运代理有限公司");
         } else if (userid.intValue() == 80) {
           WayBillHead.addElement("ceb:logisticsCode").addText("3120980110");
           WayBillHead.addElement("ceb:logisticsName").addText("申通快递有限公司");
         } 
         WayBillHead.addElement("ceb:msgCount").addText((new StringBuilder(String.valueOf(count + 1))).toString());
         WayBillHead.addElement("ceb:msgSeqNo").addText((new StringBuilder(String.valueOf(x++))).toString());
         for (OrderOut out : orderList) {
           Element WayBillList = WayBill.addElement("ceb:WayBillList");
           WayBillList.addElement("ceb:gnum").addText((new StringBuilder(String.valueOf(b++))).toString());
           WayBillList.addElement("ceb:totalPackageNo").addText(out.getTotalBag());
           WayBillList.addElement("ceb:logisticsNo").addText(out.getWaybillno());
         } 
         orderList.clear();
       } 
     } 
     Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
     BaseTransfer.addElement("ceb:copCode").addText("3316965628");
     BaseTransfer.addElement("ceb:copName").addText("浙江保宏境通供应链管理有限公司");
     BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
     BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000019798");
     
     BaseTransfer.addElement("ceb:note");
 
 
 
     
     EncryptHG.sendGet(document.asXML(), mesg, userid+"", "123456", "123456");
     return null;
   }
 
 
 
   
   public String outYD(int[] id, int mesg, int userid) throws Exception {
     List<OrderOut> findByiIds = this.orderOutDao.findByiIds(id);
     List<OrderOut> orderList = new ArrayList<>();
     for (int i = 1; i <= findByiIds.size(); i++) {
       orderList.add(findByiIds.get(i - 1));
       if (i % 100 == 0 || i == findByiIds.size()) {
         Element cebRoot = DocumentHelper.createElement("ceb:CEB505Message");
         cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
         
         cebRoot.addAttribute("guid", "311af125-6fed-4603-8c5d-49b1fa4b4b9b");
         cebRoot.addAttribute("version", "1.0");
         cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
         Document document = DocumentHelper.createDocument(cebRoot);
         for (OrderOut out : orderList) {
           Element Arrival = cebRoot.addElement("ceb:Logistics");
           Arrival.addElement("ceb:guid").addText("4CDE1CFD-EDED-46B1-946C-B8022E42FC94");
           Arrival.addElement("ceb:appType").addText("1");
           Arrival.addElement("ceb:appTime").addText(timenew.newtime());
           Arrival.addElement("ceb:appStatus").addText("2");
           if (userid == 80) {
             Arrival.addElement("ceb:logisticsCode").addText("3120980110");
             Arrival.addElement("ceb:logisticsName").addText("申通快递有限公司");
           } else {
             Arrival.addElement("ceb:logisticsCode").addText("3301980093");
             Arrival.addElement("ceb:logisticsName").addText("浙江顺丰速运有限公司");
           } 
 
           
           Arrival.addElement("ceb:logisticsNo").addText(out.getWaybillno());
           Arrival.addElement("ceb:freight").addText("0");
           Arrival.addElement("ceb:insuredFee").addText("0");
           Arrival.addElement("ceb:currency").addText("502");
           Arrival.addElement("ceb:grossWeight").addText(out.getGrossweight());
           Arrival.addElement("ceb:packNo").addText("1");
           Arrival.addElement("ceb:goodsInfo").addText("包裹");
           Arrival.addElement("ceb:ebcCode").addText("3316965628");
           Arrival.addElement("ceb:ebcName").addText("浙江保宏境通供应链管理有限公司");
           
           Arrival.addElement("ceb:note");
         } 
         Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
         if (userid == 80) {
           BaseTransfer.addElement("ceb:copCode").addText("3120980110");
           BaseTransfer.addElement("ceb:copName").addText("申通快递有限公司");
           BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
           BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000020123");
           BaseTransfer.addElement("ceb:note");
         } else {
           BaseTransfer.addElement("ceb:copCode").addText("3301980093");
           BaseTransfer.addElement("ceb:copName").addText("浙江顺丰速运有限公司");
           BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
           BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000019229");
           BaseTransfer.addElement("ceb:note");
         } 
         String xmlMW = EncryptHG.getXmlMW(document);
         
         if (userid == 80) {
           Document zsXml = EncryptHG.getZSXml(xmlMW, "DXPENT0000020123", 1, mesg);
           Rabbitmq.rabbitmq(zsXml.asXML(), "DXPENT0000020123");
         } else {
           Document zsXml = EncryptHG.getZSXml(xmlMW, "DXPENT0000019229", 1, mesg);
           Rabbitmq.rabbitmq(zsXml.asXML(), "DXPENT0000019229");
         } 
         orderList.clear();
       } 
     } 
     return null;
   }
   
   public String outLJD(int[] id, int mesg, int userid) throws Exception {
     String str = "CK0E1CFD-MZFD-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
     List<OrderOut> findByiIds = this.orderOutDao.findByiIds(id);
     String l = timenew.newtime();
     List<Integer> orderId = new ArrayList<>();
     List<OrderOut> orderList = new ArrayList<>();
     Element cebRoot = DocumentHelper.createElement("ceb:CEB509Message");
     cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
     
     cebRoot.addAttribute("guid", str);
     cebRoot.addAttribute("version", "1.0");
     cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
     Document document = DocumentHelper.createDocument(cebRoot);
     int count = (int)Math.ceil((findByiIds.size() / 1000));
     int j = 1;
     for (int i = 1; i <= findByiIds.size(); i++) {
       orderList.add(findByiIds.get(i - 1));
       orderId.add(((OrderOut)findByiIds.get(i - 1)).getId());
       if (i % 1000 == 0 || i == findByiIds.size()) {
         String copNo = "STOH2" + String.format("%013d", new Object[] { ((OrderOut)findByiIds.get(0)).getId() });
         Element Departure = cebRoot.addElement("ceb:Departure");
         Element DepartureHead = Departure.addElement("ceb:DepartureHead");
         DepartureHead.addElement("ceb:guid").addText(str);
         DepartureHead.addElement("ceb:appType").addText("2");
         DepartureHead.addElement("ceb:appTime").addText(timenew.newtime());
         DepartureHead.addElement("ceb:appStatus").addText("2");
         DepartureHead.addElement("ceb:customsCode").addText("2916");
         DepartureHead.addElement("ceb:copNo").addText(copNo);
         if (userid == 108) {
           DepartureHead.addElement("ceb:logisticsCode").addText("3301980093");
           DepartureHead.addElement("ceb:logisticsName").addText("浙江顺丰速运有限公司");
         } else if (userid == 9) {
           DepartureHead.addElement("ceb:logisticsCode").addText("3301980080");
           DepartureHead.addElement("ceb:logisticsName").addText("杭州日晟国际货运代理有限公司");
         } 
         
         DepartureHead.addElement("ceb:trafMode").addText("5");
         DepartureHead.addElement("ceb:billNo").addText(((OrderOut)findByiIds.get(0)).getTotalwaybill());
         DepartureHead.addElement("ceb:leaveTime").addText(l);
         DepartureHead.addElement("ceb:msgCount").addText((new StringBuilder(String.valueOf(count + 1))).toString());
         DepartureHead.addElement("ceb:msgSeqNo").addText((new StringBuilder(String.valueOf(j++))).toString());
         DepartureHead.addElement("ceb:note");
         int a = 1;
         List<OrderOutSku> skuList = this.orderOutDao.findSkuByIdSList(orderId);
         for (OrderOutSku sku : skuList) {
           Element DepartureList = Departure.addElement("ceb:DepartureList");
           DepartureList.addElement("ceb:gnum").addText((new StringBuilder(String.valueOf(a++))).toString());
           DepartureList.addElement("ceb:logisticsNo").addText(sku.getOrderno());
           DepartureList.addElement("ceb:note");
         }  orderList.clear();
         orderId.clear();
       } 
     } 
     Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
     BaseTransfer.addElement("ceb:copCode").addText("3301980093");
     BaseTransfer.addElement("ceb:copName").addText("浙江顺丰速运有限公司");
     BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
     BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000019672");
     BaseTransfer.addElement("ceb:note");
     
     String xmlMW = EncryptHG.getXmlMW(document);
 
     
     Document zsXml = EncryptHG.getZSXml(xmlMW, "DXPENT0000019672", 1, mesg);
     Rabbitmq.rabbitmq(zsXml.asXML(), "DXPENT0000019229");
     
     return null;
   }
 
 
 
   
   public String outCloseQd(int[] id, int mesg) throws Exception {
     int i = 1;
     InputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\499.txt");
     byte[] bytes = new byte[2048];
     int n = -1;
     StringBuffer sb = new StringBuffer();
     while ((n = in.read(bytes, 0, bytes.length)) != -1) {
       String str1 = new String(bytes, 0, n, "GBK");
       sb.append(str1);
     } 
     String str = sb.toString();
     String[] split = str.split("\n");
     List<Map<String, String>> list = new ArrayList<>(); byte b; int j; String[] arrayOfString1;
     for (j = (arrayOfString1 = split).length, b = 0; b < j; ) { String s = arrayOfString1[b];
       Map<String, String> map = new HashMap<>();
       map.put("invtNo", s.split(",")[0]);
       map.put("copNo", s.split(",")[1].substring(0, s.split(",")[1].length() - 1));
       list.add(map);
       i++;
       if (i % 100 == 0 || split.length == i - 1) {
         Element cebRoot = DocumentHelper.createElement("ceb:CEB605Message");
         cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
         cebRoot.addAttribute("guid", "311af125-6fed-4603-8c5d-49b1fa4b4b9b");
         cebRoot.addAttribute("version", "1.0");
         cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
         Document document = DocumentHelper.createDocument(cebRoot);
         for (Map<String, String> sd : list) {
           Element Departure = cebRoot.addElement("ceb:InvtCancel");
           Departure.addElement("ceb:guid").addText("311af125-6fed-4603-8c5d-49b1fa4b4b9b");
           Departure.addElement("ceb:appType").addText("1");
           Departure.addElement("ceb:appTime").addText(timenew.newtime());
           Departure.addElement("ceb:appStatus").addText("2");
           Departure.addElement("ceb:customsCode").addText("2916");
           Departure.addElement("ceb:copNo").addText(sd.get("copNo"));
           Departure.addElement("ceb:invtNo").addText(sd.get("invtNo"));
           Departure.addElement("ceb:reason").addText("提运单重复");
           Departure.addElement("ceb:agentCode").addText("3316965628");
           Departure.addElement("ceb:agentName").addText("浙江保宏境通供应链管理有限公司");
           Departure.addElement("ceb:ebcCode").addText("3316965628");
           Departure.addElement("ceb:ebcName").addText("浙江保宏境通供应链管理有限公司");
         } 
         Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
         BaseTransfer.addElement("ceb:copCode").addText("3316965628");
         BaseTransfer.addElement("ceb:copName").addText("浙江保宏境通供应链管理有限公司");
         BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
         BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000019229");
         BaseTransfer.addElement("ceb:note");
         String xmlMW = EncryptHG.getXmlMW(document);
         System.out.println(xmlMW.toString());
         Document zsXml = EncryptHG.getZSXml(xmlMW, "DXPENT0000019229", 1, mesg);
 
         
         list.clear();
       }  b++; }
     
     return "";
   }
 
   
   public String outST(int[] id, int parseInt) throws Exception {
     List<OrderOut> findByiIds = this.orderOutDao.findByiIds(id);
     List<OrderOutSku> skuList = this.orderOutDao.findSkuByIdS(id);
     String str_date = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
     for (OrderOut out : findByiIds) {
       Map<Object, Object> map = new HashMap<>();
       map.put("siteCode", "310064");
       map.put("siteName", "浙江杭州仓储部");
       map.put("tradeNo", out.getOrderno());
       map.put("waybillNo", out.getWaybillno());
       map.put("logisticId", out.getWaybillno());
       map.put("orderDate", str_date);
       map.put("orderSource", "EXITZJZT");
 
 
       
       map.put("weight", out.getGrossweight());
       map.put("senderProv", "浙江");
       map.put("senderAddress", "杭州萧山国际机场翔飞路二级监管曌通仓库");
 
       
       map.put("senderCity", "杭州");
       
       map.put("senderArea", "萧山");
       map.put("senderAddress", "杭州萧山国际机场翔飞路二级监管曌通仓库");
 
 
       
       map.put("senderMobile", "13989203824");
       map.put("senderName", "刘某");
       map.put("senderPhone", "0571-84292224");
       map.put("senderPostcode", "638293");
       
       map.put("receiverProv", out.getConsigneecountry());
       
       map.put("receiverCity", out.getConsigneecountry());
       
       map.put("receiverArea", out.getConsigneecountry());
 
 
       
       map.put("receiverAddress", out.getConsigneeaddress());
       map.put("receiverMobile", out.getConsigneetel());
       map.put("receiverName", out.getConsignee());
       
       map.put("receiverPostcode", "634569");
       Map<String, Object> item = new HashMap<>();
       map.put("items", item);
       for (OrderOutSku sku : skuList) {
         if (sku.getOrderno().equals(out.getOrderno())) {
           Map<String, Object> otherInfomap = new HashMap<>();
           item.put("otherInfo", otherInfomap);
           
           otherInfomap.put("appType", "1");
           otherInfomap.put("appStatus", "2");
           otherInfomap.put("freight", "0");
           otherInfomap.put("insuredFee", "0");
           otherInfomap.put("grossWeight", out.getGrossweight());
           otherInfomap.put("currency", "502");
           otherInfomap.put("packNo", "1");
           otherInfomap.put("goodsInfo", sku.getGoodsname());
           otherInfomap.put("ebcCode", "3316965628");
           otherInfomap.put("ebcName", "浙江保宏境通供应链管理有限公司");
           otherInfomap.put("ebcTelephone", "15236258632");
         } 
       } 
 
       
       String jsonString = JSONObject.toJSONString(map);
       
       String putData = PushToSTO.putDataOUt(jsonString);
       System.out.println(putData);
     }     
     return null;
   }
   
   public List<Map<String, Object>> jxsh(String id) {
     return this.orderOutDao.jxsh(id);
   }
 }


