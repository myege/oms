 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.CompanyForOutSTODao;
 import com.what21.dao.OrderOutSTODao;
 import com.what21.model.CompanyforoutSto;
 import com.what21.model.OrderOutSTOCustom;
 import com.what21.model.OrderOutSTOQueryVo;
 import com.what21.model.TOrderOutSto;
 import com.what21.service.OrderOutSTOService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import com.what21.util.OrderOutSTOExcelUtil;
 import com.zt.kjybd.OutWld;
 import com.zt.kjybd.PushToSTO;
 import java.io.File;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Date;
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
 public class OrderOutSTOServiceImpl
   implements OrderOutSTOService
 {
   @Autowired
   private OrderOutSTODao orderOutSTODao;
   @Autowired
   private CompanyForOutSTODao companyForOutSTODao;
   
   public List<OrderOutSTOCustom> findAll(OrderOutSTOQueryVo orderOutSTOQueryVo) {
     return this.orderOutSTODao.findAll(orderOutSTOQueryVo);
   }
 
   
   public Integer countAll(OrderOutSTOQueryVo orderOutSTOQueryVo) {
     return this.orderOutSTODao.countAll(orderOutSTOQueryVo);
   }
 
   
   public void insertBatch(List<TOrderOutSto> list) {
     this.orderOutSTODao.insertBatch(list);
   }
 
   
   public GeneralResult importOrderNew(Integer id, String string) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = OrderOutSTOExcelUtil.read(string);
     List<TOrderOutSto> list = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size();
     String now = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
     DecimalFormat df = new DecimalFormat("0.00");
     try {
       for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
         Object[] nowRowData = me.getValue();
         int rowNum = ((Integer)me.getKey()).intValue() + 1;
         String tradeno = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：交易订单号没有输入！<br>");
           break;
         } 
         tradeno = nowRowData[0].toString();
         
         String waybillno = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：运单号没有输入！<br>");
           break;
         } 
         waybillno = nowRowData[1].toString();
 
         
         String logisticid = null;
         if (nowRowData[2] == null || StringUtils.isEmpty(nowRowData[2].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：物流编号没有输入！<br>");
           break;
         } 
         logisticid = nowRowData[2].toString();
         
         Date orderdate = null;
         if (nowRowData[3] == null || StringUtils.isEmpty(nowRowData[3].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：订单日期没有输入！<br>");
           break;
         } 
         try {
           orderdate = Tools.parse("yyyy-MM-dd HH:mm:ss", nowRowData[3].toString());
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + rowNum + "行数据失败，失败原因：订单日期格式必须是2016-12-07 12:12:12！<br>");
           
           break;
         }         
         String receiverAddress = null;
         if (nowRowData[4] == null || StringUtils.isEmpty(nowRowData[4].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：收件人地址没有输入！<br>");
           break;
         } 
         receiverAddress = nowRowData[4].toString();
         
         String receiverMobile = null;
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：收件人电话/手机没有输入！<br>");
           break;
         } 
         receiverMobile = nowRowData[5].toString();
 
         
         String receiverName = null;
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：收件人姓名没有输入！<br>");
           break;
         } 
         receiverName = nowRowData[6].toString();
         
         String receiverPostcode = null;
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：收件人邮编没有输入！<br>");
           break;
         } 
         receiverPostcode = nowRowData[7].toString();
 
         
         String billNo = null;
         if (nowRowData[8] != null && StringUtils.isNotEmpty(nowRowData[8].toString().trim())) {
           billNo = nowRowData[8].toString();
         }
         
         String voyageNo = null;
         if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：航班班次号没有输入！<br>");
           break;
         } 
         voyageNo = nowRowData[9].toString();
         
         String grossWeight = null;
         double d_grossweight = 0.0D;
         if (nowRowData[10] == null || StringUtils.isEmpty(nowRowData[10].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：毛重没有输入！<br>");
           break;
         } 
         grossWeight = nowRowData[10].toString();
         try {
           d_grossweight = df.parse(grossWeight).doubleValue();
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + rowNum + "行数据失败，失败原因：毛重必须是数字！<br>");
 
           
           break;
         } 
 
         
         String netWeight = null;
         double d_netweight = 0.0D;
         if (nowRowData[11] == null || StringUtils.isEmpty(nowRowData[11].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：净重没有输入！<br>");
           break;
         } 
         netWeight = nowRowData[11].toString();
         try {
           d_netweight = df.parse(netWeight).doubleValue();
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + rowNum + "行数据失败，失败原因：净重必须是数字！<br>");
 
           
           break;
         } 
         
         String insureAmount = null;
         double d_insureamount = 0.0D;
         if (nowRowData[12] == null || StringUtils.isEmpty(nowRowData[12].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：保税没有输入！<br>");
           break;
         } 
         insureAmount = nowRowData[12].toString();
         try {
           d_insureamount = df.parse(insureAmount).doubleValue();
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + rowNum + "行数据失败，失败原因：保税必须是数字！<br>");
 
           
           break;
         } 
 
         
         String feeAmount = null;
         double d_feeamount = 0.0D;
         if (nowRowData[13] == null || StringUtils.isEmpty(nowRowData[13].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：运费没有输入！<br>");
           break;
         } 
         feeAmount = nowRowData[13].toString();
         try {
           d_feeamount = df.parse(feeAmount).doubleValue();
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + rowNum + "行数据失败，失败原因：运费必须是数字！<br>");
 
           
           break;
         } 
         
         String recipientCountry = null;
         if (nowRowData[14] == null || StringUtils.isEmpty(nowRowData[14].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：收件人所在国没有输入！<br>");
           break;
         } 
         recipientCountry = nowRowData[14].toString();
         
         String goosInfo = null;
         if (nowRowData[15] != null && StringUtils.isNotEmpty(nowRowData[15].toString().trim())) {
           goosInfo = nowRowData[15].toString();
         }
         
         String packageInfo = null;
         if (nowRowData[16] != null && StringUtils.isNotEmpty(nowRowData[16].toString().trim())) {
           packageInfo = nowRowData[16].toString();
         }
         
         String itemCode = null;
         if (nowRowData[17] == null || StringUtils.isEmpty(nowRowData[17].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商家编码没有输入！<br>");
           break;
         } 
         itemCode = nowRowData[17].toString();
         
         TOrderOutSto orderOutSto = new TOrderOutSto();
         orderOutSto.setStatus(Integer.valueOf(0));
         orderOutSto.setUserid(id);
         orderOutSto.setCreatetime(now);
         orderOutSto.setItemcode(itemCode);
         orderOutSto.setCurrcode("142");
         orderOutSto.setPackno(Integer.valueOf(1));
 
         
         orderOutSto.setTradeno(tradeno);
         orderOutSto.setWaybillno(waybillno);
         orderOutSto.setLogisticid(logisticid);
         orderOutSto.setOrderdate(orderdate);
         orderOutSto.setReceiveraddress(receiverAddress);
         orderOutSto.setReceivermobile(receiverMobile);
         orderOutSto.setReceivername(receiverName);
         orderOutSto.setReceiverpostcode(receiverPostcode);
         orderOutSto.setBillno(billNo);
         orderOutSto.setVoyageno(voyageNo);
         orderOutSto.setGrossweight(Double.valueOf(d_grossweight));
         orderOutSto.setNetweight(Double.valueOf(d_netweight));
         orderOutSto.setInsureamount(Double.valueOf(d_insureamount));
         orderOutSto.setFeeamount(Double.valueOf(d_feeamount));
         orderOutSto.setRecipientcountry(recipientCountry);
         orderOutSto.setGoosinfo(goosInfo);
         orderOutSto.setPackageinfo(packageInfo);
         
         list.add(orderOutSto);
       } 
 
       
       if (!message.toString().contains("导入")) {
         if (list.size() != 0) {
           this.orderOutSTODao.insertBatch(list);
         }
         message.append("导入成功" + total + "条！");
       } 
       result.setMessage(message.toString());
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
     } 
     return result;
   }
 
   
   public List<String> pushSTO(int[] ids) {
     int i = 0;
     List<OrderOutSTOCustom> orderOuts = this.orderOutSTODao.findByIds(ids);
     List<String> ret = new ArrayList<>();
     StringBuffer sb = new StringBuffer();
     for (OrderOutSTOCustom orderOutSTOCustom : orderOuts) {
       try {
         CompanyforoutSto companyforoutSto = this.companyForOutSTODao.findByItemcode(orderOutSTOCustom.getItemcode());
         if (companyforoutSto == null) {
           sb.append("商家编码为：" + orderOutSTOCustom.getItemcode() + "未找到;  ");
           i++;
           break;
         } 
         JSONObject json = new JSONObject();
         json.put("siteCode", orderOutSTOCustom.getSitecode());
         json.put("siteName", orderOutSTOCustom.getSitename());
         json.put("tradeNo", orderOutSTOCustom.getTradeno());
         json.put("waybillNo", orderOutSTOCustom.getWaybillno());
 
         
         json.put("logisticId", orderOutSTOCustom.getLogisticid());
         json.put("orderDate", orderOutSTOCustom.getOrderdate());
         json.put("orderSource", "PORTZJZT");
         json.put("receiverProv", "浙江省");
         
         json.put("receiverCity", "杭州市");
         
         json.put("receiverArea", "江干区");
         
         json.put("receiverTown", "九堡镇");
         
         json.put("receiverAddress", orderOutSTOCustom.getReceiveraddress());
         json.put("receiverMobile", orderOutSTOCustom.getReceivermobile());
         json.put("receiverName", orderOutSTOCustom.getReceivername());
         json.put("receiverPostcode", "000000");
         
         json.put("senderProv", "浙江省");
         
         json.put("senderCity", "杭州市");
         
         json.put("senderArea", "江干区");
         
         json.put("senderTown", "九堡镇");
         
         json.put("senderAddress", companyforoutSto.getSenderaddress());
         json.put("senderMobile", companyforoutSto.getSenderphone());
         json.put("senderName", companyforoutSto.getSendername());
         json.put("senderPostcode", companyforoutSto.getSenderpostcode());
 
         
         json.put("weight", orderOutSTOCustom.getGrossweight());
 
         
         JSONObject json2 = new JSONObject();
         JSONObject json3 = new JSONObject();
         json3.put("grossWeight", orderOutSTOCustom.getGrossweight());
         json3.put("companyCode", companyforoutSto.getCompanycode());
         json3.put("businessNo", companyforoutSto.getBusinessno());
         json3.put("companyName", companyforoutSto.getCompanyname());
         json3.put("recipientCountry", orderOutSTOCustom.getRecipientcountry());
         json3.put("insureAmount", orderOutSTOCustom.getInsureamount());
         json3.put("feeAmount", orderOutSTOCustom.getFeeamount());
         json3.put("billNo", orderOutSTOCustom.getBillno());
         json3.put("netWeight", orderOutSTOCustom.getNetweight());
         json3.put("packNo", orderOutSTOCustom.getPackno());
         if (orderOutSTOCustom.getGoosinfo() != null) {
           json3.put("goosInfo", orderOutSTOCustom.getGoosinfo());
         }
         json3.put("goosInfo", "");
         if (orderOutSTOCustom.getPackageinfo() != null) {
           json3.put("packageInfo", orderOutSTOCustom.getPackageinfo());
         }
         json3.put("packageInfo", "");
         
         json3.put("currCode", "142");
         json3.put("trafMode", "空运");
         json3.put("trafName", "");
         if (orderOutSTOCustom.getVoyageno() != null) {
           json3.put("voyageNo", orderOutSTOCustom.getVoyageno());
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
           this.orderOutSTODao.updateStatus(orderOutSTOCustom.getId()); continue;
         } 
         i++;
         sb.append(String.valueOf(orderOutSTOCustom.getTradeno()) + "：" + retJson_update.getString("errorMsg"));
       
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
 
   
   public void deleteByIds(int[] ids) {
     this.orderOutSTODao.deleteByIds(ids);
   }
 
 
   
   public List<String> pushkouAn(int[] ids, String totalWayBill) {
     List<String> ret = new ArrayList<>();
     try {
       List<OrderOutSTOCustom> orderOutStos = null;
       if (ids == null) {
         orderOutStos = this.orderOutSTODao.findByBillno(totalWayBill);
         if (orderOutStos.size() == 0) {
           ret.add("1");
           ret.add("提运单号为：" + totalWayBill + "未找到;  ");
           return ret;
         } 
       } else {
         orderOutStos = this.orderOutSTODao.findByIds(ids);
       } 
       StringBuffer sb = new StringBuffer();
       int i = 0;
       int success = 0;
       
       for (OrderOutSTOCustom orderOutSto : orderOutStos) {
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("version", "1.0.0");
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType").addText("EXPORTWAYBILL");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("JkfExportWaybillInfoList");
 
         
         CompanyforoutSto cfos = this.companyForOutSTODao.findByItemcode(orderOutSto.getItemcode());
         String businessNo = "STOH2" + String.format("%013d", new Object[] { orderOutSto.getId() });
         
         Element element4 = element3.addElement("JkfExportWaybill");
         
         Element element5 = element4.addElement("jkfSign");
         element5.addElement("companyCode").addText(cfos.getCompanycode());
         element5.addElement("businessNo").addText(businessNo);
         element5.addElement("businessType").addText("EXPORTWAYBILL");
         element5.addElement("declareType").addText("1");
         element5.addElement("note").addText("");
         
         Element element6 = element4.addElement("jkfExportWaybillInfo");
         element6.addElement("declPort").addText(cfos.getDeclport());
         element6.addElement("orderNo").addText(Tools.removeNull(orderOutSto.getTradeno()));
         element6.addElement("companyCode").addText("");
         element6.addElement("companyName").addText("");
         element6.addElement("logisCompanyCode").addText("669437562");
         element6.addElement("logisCompanyName").addText("申通快递有限公司");
         element6.addElement("wayBill").addText(Tools.removeNull(orderOutSto.getWaybillno()));
         element6.addElement("ieFlag").addText("E");
         element6.addElement("trafMode").addText("5");
         element6.addElement("trafName").addText("");
         element6.addElement("voyageNo").addText(Tools.removeNull(orderOutSto.getVoyageno()));
         element6.addElement("billNo").addText(Tools.removeNull(orderOutSto.getBillno()));
         element6.addElement("feeAmount").addText(Tools.removeNull(orderOutSto.getFeeamount()));
         element6.addElement("insureAmount").addText(Tools.removeNull(orderOutSto.getInsureamount()));
         element6.addElement("currCode").addText(orderOutSto.getCurrcode());
         element6.addElement("grossWeight").addText(Tools.removeNull(orderOutSto.getGrossweight()));
         element6.addElement("netWeight").addText(Tools.removeNull(orderOutSto.getNetweight()));
         element6.addElement("packNo").addText(Tools.removeNull(orderOutSto.getPackno()));
         element6.addElement("packageInfo").addText(Tools.removeNull(orderOutSto.getPackageinfo()));
         element6.addElement("goosInfo").addText(Tools.removeNull(orderOutSto.getGoosinfo()));
         element6.addElement("consignee").addText(Tools.removeNull(orderOutSto.getReceivername()));
         element6.addElement("consigneeAddress").addText(Tools.removeNull(orderOutSto.getReceiveraddress()));
         element6.addElement("consigneeTel").addText(Tools.removeNull(orderOutSto.getReceivermobile()));
         element6.addElement("recipientCountry").addText(Tools.removeNull(orderOutSto.getRecipientcountry()));
         element6.addElement("senderName").addText(cfos.getSendername());
         element6.addElement("senderAddress").addText(cfos.getSenderaddress());
         element6.addElement("senderPhone").addText(cfos.getSenderphone());
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
 
 
   
   public void deleteNotNeed() {
     this.orderOutSTODao.deleteNoNeed();
   }
 }


