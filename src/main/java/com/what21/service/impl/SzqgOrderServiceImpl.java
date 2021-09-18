 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import com.what21.dao.SzqgAgreementDao;
 import com.what21.dao.SzqgItemDao;
 import com.what21.dao.SzqgOrderDao;
 import com.what21.dao.SzqgOrderSkuDao;
 import com.what21.dao.SzqgStrategyDao;
 import com.what21.dao.SzqgWlgjDao;
 import com.what21.model.SzqgAgreement;
 import com.what21.model.SzqgItem;
 import com.what21.model.SzqgOrder;
 import com.what21.model.SzqgOrderExpot;
 import com.what21.model.SzqgOrderSku;
 import com.what21.model.SzqgStrategy;
 import com.what21.model.SzqgwlGj;
 import com.what21.service.SzqgOrderService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.what21.util.timenew;
 import com.zt.ewtp.hcszqgpost;
 import com.zt.ewtp.zwyddpost;
 import com.zt.kjybd.PushtoWQ;
 import com.zt.kjybd.newzs;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.InputStreamReader;
 import java.math.BigDecimal;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class SzqgOrderServiceImpl
   implements SzqgOrderService
 {
   @Autowired
   private SzqgOrderDao szqgorderDao;
   @Autowired
   public SzqgAgreementDao szqgagreementdao;
   @Autowired
   private SzqgOrderSkuDao szqgorderSkuDao;
   @Autowired
   private SzqgStrategyDao szqgStrategyDao;
   @Autowired
   private SzqgOrderSkuDao szqgOrderSkuDao;
   @Autowired
   private SzqgWlgjDao szqgWlgjDao;
   @Autowired
   public SzqgItemDao szqgItemDao;
   
   public List<SzqgOrder> findAll(Map<String, Object> map) {
     return this.szqgorderDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return this.szqgorderDao.count(map);
   }
 
 
 
   
   public String GetMailno(String str) throws Exception {
     JSONObject ret = JSONObject.parseObject(str);
     String orderNo = ret.getString("orderNo");
 
     
     SzqgOrder szqgOrder = this.szqgorderDao.findByOrderNo(orderNo);
     String mailno = szqgOrder.getMailno();
     System.out.println("mailno=" + mailno);
     JSONObject backJson = new JSONObject();
     backJson.put("status", "0");
     backJson.put("msg", "查询成功");
     backJson.put("orderNo", orderNo);
     backJson.put("carrier", "STO");
     backJson.put("logisticsNo", mailno);
     
     return backJson.toJSONString();
   }
   
   public String CreateOrder(String str) throws Exception {
     List<SzqgOrder> orderList = new ArrayList<>();
     List<SzqgOrderSku> orderskuList = new ArrayList<>();
 
     
     String msg = "success";
 
 
 
     
     JSONObject ret = JSONObject.parseObject(str);
     String fromto = ret.getString("fromto");
     System.out.println("fromto=====" + fromto);
     String orderNo = ret.getString("orderNo");
     
     String ebpCode = ret.getString("ebpCode");
     String ebpName = ret.getString("ebpName");
     
     String ebcName = ret.getString("ebcName");
     String ebcCode = ret.getString("ebcCode");
     ebcName = "义乌市六六顺科技有限公司";
     ebcCode = "3318960DDU";
     String payCode = ret.getString("payCode");
     String payName = ret.getString("payName");
     String payTransactionId = ret.getString("payTransactionId");
     String buyerIdNumber = ret.getString("buyerIdNumber");
     String buyerName = ret.getString("buyerName");
     String buyerTelephone = ret.getString("buyerTelephone");
     String consignee = ret.getString("consignee");
     String consigneeTelephone = ret.getString("consigneeTelephone");
     String consigneeAddress = ret.getString("consigneeAddress");
     String consigneeProvince = ret.getString("consigneeProvince");
     String consigneeCity = ret.getString("consigneeCity");
     String consigneeCounty = ret.getString("consigneeCounty");
     String logisticsNo = ret.getString("logisticsNo");
     String logisticsName = ret.getString("logisticsName");
     String logisticsCode = ret.getString("logisticsCode");
     String voyageNo = ret.getString("voyageNo");
     String tradeMode = ret.getString("tradeMode");
     String trafMode = ret.getString("trafMode");
     String country = ret.getString("country");
     String wrapType = ret.getString("wrapType");
     String goodsValue = ret.getString("goodsValue");
     String grossWeight = ret.getString("grossWeight");
     String netWeight = ret.getString("netWeight");
     String billNo = ret.getString("billNo");
     String acturalPaid = ret.getString("acturalPaid");
     String taxTotal = ret.getString("insuredFee");
     String freight = ret.getString("freight");
     String discount = ret.getString("discount");
     
     BigDecimal goodsValue2 = new BigDecimal(goodsValue);
     BigDecimal taxTotal2 = new BigDecimal(taxTotal);
     BigDecimal freight2 = new BigDecimal(freight);
     BigDecimal worth = goodsValue2.add(taxTotal2);
     worth = worth.add(freight2);
 
 
     
     SzqgOrder szqgOrder = new SzqgOrder();
     String copNo = "YW" + newzs.getRandomCharAndNumr(Integer.valueOf(11));
     szqgOrder.setCopNo(copNo);
     szqgOrder.setStrategy(fromto);
     szqgOrder.setStrategy(fromto);
     szqgOrder.setConsigneeIdType("1");
     szqgOrder.setConsigneeIdNumber(buyerIdNumber);
     szqgOrder.setActuralPaid(worth.toString());
     szqgOrder.setTaxTotal(taxTotal);
     szqgOrder.setFreight(freight);
     szqgOrder.setDiscount("0");
     szqgOrder.setFromto(fromto);
     szqgOrder.setOrderNo(orderNo);
     szqgOrder.setEbpCode(ebpCode);
     szqgOrder.setEbpName(ebpName);
     szqgOrder.setProxyCode(ebcCode);
     szqgOrder.setProxyName(ebcName);
     szqgOrder.setPayCode(payCode);
     szqgOrder.setPayName(payName);
     szqgOrder.setPayTransactionId(payTransactionId);
     szqgOrder.setBuyerIdNumber(buyerIdNumber);
     szqgOrder.setBuyerName(buyerName);
     szqgOrder.setBuyerTelephone(buyerTelephone);
     szqgOrder.setConsignee(consignee);
     szqgOrder.setConsigneeAddress(consigneeAddress);
     szqgOrder.setConsigneeCity(consigneeCity);
     szqgOrder.setConsigneeCounty(consigneeCounty);
     szqgOrder.setConsigneePrvince(consigneeProvince);
     szqgOrder.setConsigneeTelephone(consigneeTelephone);
     szqgOrder.setMailno(logisticsNo);
     szqgOrder.setLogisticsCode(logisticsCode);
     szqgOrder.setLogisticsName(logisticsName);
     szqgOrder.setVoyageNo(voyageNo);
     szqgOrder.setTrafMode(trafMode);
     szqgOrder.setEntryType(tradeMode);
     szqgOrder.setCountry(country);
     szqgOrder.setGrossWeight(grossWeight);
     szqgOrder.setNetWeight(netWeight);
     szqgOrder.setGoodsValue(goodsValue);
     szqgOrder.setBillNo(billNo);
     
     String detailDtoList2 = ret.getString("detailDtoList");
     
     JSONArray detailDtoList = JSONArray.parseArray(detailDtoList2);
     int TotalCount = 0;
     for (int i = 0; i < detailDtoList.size(); i++) {
       
       SzqgOrderSku szqgOrdersku = new SzqgOrderSku();
       JSONObject orderskuJSONObject = detailDtoList.getJSONObject(i);
       String orderNo1 = orderskuJSONObject.getString("orderNo");
       String gcode = orderskuJSONObject.getString("gcode");
       String gname = orderskuJSONObject.getString("gname");
       String itemNo = orderskuJSONObject.getString("itemNo");
       String price = orderskuJSONObject.getString("price");
       String qty = orderskuJSONObject.getString("qty");
       
       TotalCount += Integer.parseInt(qty);
 
 
 
       
       String gjz = " skuCode='" + itemNo + "'";
       List<SzqgItem> lists = this.szqgItemDao.findgjzforcd(gjz);
       if (lists.size() == 0) {
         return "商品未创建";
       }
       System.out.println("size=" + lists.size());
       DecimalFormat df_2 = new DecimalFormat("0.000");
       BigDecimal dj = new BigDecimal(price);
       BigDecimal sl = new BigDecimal(qty);
       BigDecimal ttp = dj.multiply(sl);
 
       
       for (SzqgItem sa : lists) {
         
         String SecondUnit = "";
         if (sa.getSecondUnit() != null) {
           SecondUnit = sa.getSecondUnit();
         }
         szqgOrdersku.setBarCode(sa.getBarCode());
         szqgOrdersku.setUnit(sa.getGoodsUnit());
         szqgOrdersku.setCountry(sa.getGoodsFrom());
         szqgOrdersku.setHScode(sa.getHsCode());
         szqgOrdersku.setGrossWeight(sa.getGrossWeight());
         szqgOrdersku.setNetWeight(sa.getNetWeight());
         szqgOrdersku.setFirstUnit(sa.getFirstUnit());
         szqgOrdersku.setSecondUnit(SecondUnit);
         szqgOrdersku.setCodeTs(sa.getCodeTs());
         BigDecimal jz = new BigDecimal(sa.getNetWeight());
         BigDecimal zzl = jz.multiply(sl);
         if (sa.getFirstUnit().equals("035")) {
           szqgOrdersku.setFirstCount((new StringBuilder(String.valueOf(df_2.format(zzl)))).toString());
         } else {
           
           szqgOrdersku.setFirstCount(qty);
         } 
         
         if (SecondUnit.equals("")) {
           szqgOrdersku.setSecondCount("");
         } else if (SecondUnit.equals("035")) {
           szqgOrdersku.setSecondCount((new StringBuilder(String.valueOf(df_2.format(zzl)))).toString());
         } else {
           szqgOrdersku.setSecondCount(qty);
         } 
         
         szqgOrdersku.setgModel(sa.getGoodsModel());
       } 
 
       
       szqgOrdersku.setItemDescribe(gname);
       
       szqgOrdersku.setOrderNo(orderNo1);
       szqgOrdersku.setItemNo(itemNo);
       szqgOrdersku.setItemName(gname);
       szqgOrdersku.setPrice(price);
       szqgOrdersku.setQty(qty);
       
       szqgOrdersku.setTotalPrice(ttp.toString());
       
       orderskuList.add(szqgOrdersku);
       
       System.out.println("gname=====" + gname);
     } 
     szqgOrder.setTotalCount((new StringBuilder(String.valueOf(TotalCount))).toString());
     orderList.add(szqgOrder);
 
     
     if (this.szqgorderDao.findBySku(szqgOrder).size() > 0) {
       return String.valueOf(orderNo) + "订单已存在";
     }
 
     
     int count2 = this.szqgorderDao.batchInsert(orderList);
     int count = this.szqgOrderSkuDao.batchInsert(orderskuList);
     
     System.out.println("count2=" + count2);
     System.out.println("count=" + count);
     
     if (tradeMode.equals("B")) {
       return "10";
     }
     return "0";
   }
   
   public String updateIsCustoms(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1";
     String Token = ""; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       if ("ZWY".equals(szqgOrder.getCarrier())) {
         
         if (Token.equals("")) {
           Token = zwyddpost.refreshToken();
         }
         System.out.println(Token);
         
         JSONObject data = new JSONObject();
         
         JSONObject orderHead = new JSONObject();
         
         orderHead.put("abroadCo", "2020791207002");
         orderHead.put("abroadCoName", "测试商家2");
         orderHead.put("entryType", szqgOrder.getEntryType());
         orderHead.put("companyCode", "3318W6K011");
         orderHead.put("companyName", "义乌海仓供应链管理有限公司");
         orderHead.put("SupplyChannel", "义乌.BBC");
         orderHead.put("SupplyVendor", "义乌");
         orderHead.put("consignee", szqgOrder.getConsignee());
         orderHead.put("paperType", "1");
         orderHead.put("paperNumber", szqgOrder.getConsigneeIdNumber());
         orderHead.put("consigneeAddress", "0");
         orderHead.put("consigneeEmail", "");
         orderHead.put("consigneeTel", szqgOrder.getConsigneeTelephone());
         orderHead.put("consigneeProvince", szqgOrder.getConsigneePrvince());
         orderHead.put("consigneeCity", szqgOrder.getConsigneeCity());
         orderHead.put("consigneeCounty", szqgOrder.getConsigneeCounty());
         orderHead.put("currCode", "142");
         orderHead.put("eCommerceCode", "3318960C6H");
         orderHead.put("eCommerceName", "义乌易链科技有限公司");
         orderHead.put("feeAmount", "0");
         orderHead.put("ieFlag", "I");
         orderHead.put("Note", "备注");
         orderHead.put("orderGoodsAmount", szqgOrder.getGoodsValue());
         orderHead.put("orderNo", szqgOrder.getOrderNo());
         orderHead.put("orderTaxAmount", "0");
         orderHead.put("orderTotalAmount", szqgOrder.getActuralPaid());
         orderHead.put("payCompanyCode", szqgOrder.getPayCode());
         orderHead.put("payNumber", szqgOrder.getPayTransactionId());
         orderHead.put("payType", "1");
         orderHead.put("postMode", "2");
         orderHead.put("purchaserId", szqgOrder.getBuyerName());
         orderHead.put("senderCountry", "142");
         orderHead.put("senderName", "义乌海仓");
         orderHead.put("totalAmount", szqgOrder.getActuralPaid());
         orderHead.put("totalCount", szqgOrder.getTotalCount());
         orderHead.put("tradeTime", timenew.newtime4());
 
         
         orderHead.put("ParcelCode", szqgOrder.getMailno());
         orderHead.put("rate", "1");
         orderHead.put("StockId", "YWBS");
         orderHead.put("billNoType", "STO_STANDARD");
         orderHead.put("userProcotol", "本人承诺所购商品系个人合理自用,保证遵守《海关法》和国家相关法律 法规,保证所提供的信息真实完整,本人愿意接受海关、检验检疫机构及其他监管部门的监管, 并承担相应法律责任.");
         orderHead.put("insureAmount", "0");
         orderHead.put("discount", szqgOrder.getDiscount());
         orderHead.put("batchNumbers", "");
         orderHead.put("consigneeDitrict", "");
         orderHead.put("channelId", "YWYiLian");
 
         
         orderHead.put("abroadCo", szqgOrder.getAbroadCo());
         orderHead.put("abroadCoName", szqgOrder.getAbroadCoName());
         orderHead.put("mawbCode", szqgOrder.getBillNo());
         orderHead.put("flightNo", szqgOrder.getVoyageNo());
         orderHead.put("flightDate", timenew.newtime4());
         orderHead.put("ieDate", timenew.newtime4());
         orderHead.put("iePort", szqgOrder.getCustomsCode());
         orderHead.put("destinationPort", szqgOrder.getCountry());
         orderHead.put("trafName", szqgOrder.getVoyageNo());
         orderHead.put("trafMode", szqgOrder.getTrafMode());
         orderHead.put("ownerName", "义乌易链科技有限公司");
         orderHead.put("tradeCountry", szqgOrder.getCountry());
         orderHead.put("tradeMode", "");
         orderHead.put("cutMode", "");
         orderHead.put("transMode", "");
         orderHead.put("feeMark", "");
         orderHead.put("feeCurr", "");
         orderHead.put("feeRate", "");
         orderHead.put("insurMark", "");
         orderHead.put("insurCurr", "");
         orderHead.put("insurRate", "");
         orderHead.put("otherMark", "");
         orderHead.put("otherCurr", "");
         orderHead.put("otherRate", "");
         orderHead.put("wrapType", "2");
         orderHead.put("woodWrap", "否");
         orderHead.put("goodsUsed", "否");
         orderHead.put("lowTempTrans", "否");
         orderHead.put("coOwner", "1");
         orderHead.put("inputNo", "张玉松");
         orderHead.put("inputCompanyCo", "3318960C6H");
         orderHead.put("inputCompanyName", "义乌易链科技有限公司");
         orderHead.put("customsField", "");
         orderHead.put("kjId", "");
         orderHead.put("senderName", "海仓云商");
         orderHead.put("sendNameEn", "WaterMan");
         orderHead.put("sendePhone", "13605804656");
         orderHead.put("senderCompany", "海仓");
         orderHead.put("senderAddress", "南三里冢78-7");
         orderHead.put("sendAddressEn", "");
         orderHead.put("senderPostCode", "286-0113");
         orderHead.put("senderCity", "成田市");
         orderHead.put("stopCityEn", "JPN");
         orderHead.put("sendCityEn", "");
         
         orderHead.put("senderProvince", "JPN");
         orderHead.put("senderCountry", "JPN");
         orderHead.put("sendIdType", "1");
         orderHead.put("sendId", szqgOrder.getConsigneeIdNumber());
         orderHead.put("currCode", "142");
         orderHead.put("mainGName", "奢宠耀白丰盈泡沫洁面乳");
         orderHead.put("mainGNameEn", "AMPLEUR ");
         orderHead.put("receiveNameEn", "1");
         orderHead.put("receiveCountry", "142");
         orderHead.put("receiveAddressEn", "1");
 
 
 
         
         data.put("orderHead", orderHead);
         
         List<SzqgOrderSku> orderBondedSkuList = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
         
         JSONArray orderDetailList = new JSONArray();
         int j = 0;
         for (SzqgOrderSku orderBondedSku : orderBondedSkuList) {
           j++;
           JSONObject gyitem = new JSONObject();
           gyitem.put("codeTs", orderBondedSku.getHScode());
           gyitem.put("goodsCount", orderBondedSku.getQty());
           gyitem.put("goodsModel", orderBondedSku.getgModel());
           gyitem.put("goodsName", orderBondedSku.getItemName());
           gyitem.put("OrderIndex", Integer.valueOf(j));
           gyitem.put("goodsUnit", orderBondedSku.getUnit());
           gyitem.put("grossWeight", orderBondedSku.getGrossWeight());
           gyitem.put("netWeight", orderBondedSku.getNetWeight());
           gyitem.put("originCountry", orderBondedSku.getCountry());
           gyitem.put("unitPrice", orderBondedSku.getPrice());
           gyitem.put("goodsItemNo", orderBondedSku.getItemNo());
           gyitem.put("firstUnit", orderBondedSku.getFirstUnit());
           gyitem.put("firstCount", orderBondedSku.getFirstCount());
           gyitem.put("secondUnit", orderBondedSku.getSecondUnit());
           gyitem.put("secondCount", orderBondedSku.getSecondCount());
           gyitem.put("itemRecordNo", orderBondedSku.getItemNo());
           
           gyitem.put("goodsNameEn", "AMPLEUR luxury white foam cleansing foam 130g/ box");
           
           gyitem.put("itemName", "");
           gyitem.put("productRecordNo", "1");
           gyitem.put("website", "");
           gyitem.put("barCode", "");
           gyitem.put("Note", "");
           gyitem.put("currency", "142");
           
           orderDetailList.add(gyitem);
         } 
         data.put("orderDetailList", orderDetailList);
 
         
         JSONObject goodsPurchaser = new JSONObject();
         
         goodsPurchaser.put("id", szqgOrder.getBuyerName());
         goodsPurchaser.put("name", szqgOrder.getBuyerName());
         goodsPurchaser.put("email", "");
         goodsPurchaser.put("telNumber", szqgOrder.getBuyerTelephone());
         goodsPurchaser.put("address", "");
         goodsPurchaser.put("paperType", "1");
         goodsPurchaser.put("paperNumber", szqgOrder.getBuyerIdNumber());
         
         data.put("goodsPurchaser", goodsPurchaser);
         
         System.out.println("data=>>>" + data);
         result = zwyddpost.pushorder(Token, data.toString());
         System.out.println("ret=" + result);
         if (result.contains("处理成功")) {
           szqgOrder.setYdzt("1");
           this.szqgorderDao.updateYdzt(szqgOrder);
         
         }
       
       }
       else if ("ZWY2".equals(szqgOrder.getCarrier())) {
         System.out.println("ZWY2=");
         DecimalFormat df_2 = new DecimalFormat("0.000");
         String str = "WL1E1CFD-EDED-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
         Element cebRoot = DocumentHelper.createElement("ceb:CEB511Message");
         cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
         
         cebRoot.addAttribute("guid", str);
         cebRoot.addAttribute("version", "1.0");
         cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
         Document document = DocumentHelper.createDocument(cebRoot);
         
         Element Logistics = cebRoot.addElement("ceb:Logistics");
         Element LogisticsHead = Logistics.addElement("ceb:LogisticsHead");
         LogisticsHead.addElement("ceb:guid").addText(str);
         LogisticsHead.addElement("ceb:appType").addText("1");
         LogisticsHead.addElement("ceb:appTime").addText(timenew.newtime());
         LogisticsHead.addElement("ceb:appStatus").addText("2");
         
         LogisticsHead.addElement("ceb:logisticsCode").addText("331898Z004");
         LogisticsHead.addElement("ceb:logisticsName").addText("中外运跨境电商物流有限公司义乌分公司");
         LogisticsHead.addElement("ceb:logisticsNo").addText(szqgOrder.getMailno());
         LogisticsHead.addElement("ceb:billNo").addText("");
         LogisticsHead.addElement("ceb:orderNo").addText(szqgOrder.getOrderNo());
         LogisticsHead.addElement("ceb:freight").addText("0");
         LogisticsHead.addElement("ceb:insuredFee").addText("0");
         LogisticsHead.addElement("ceb:currency").addText("142");
         LogisticsHead.addElement("ceb:weight").addText("1");
         LogisticsHead.addElement("ceb:packNo").addText("1");
         LogisticsHead.addElement("ceb:goodsInfo").addText("跨境商品");
         LogisticsHead.addElement("ceb:consignee").addText(szqgOrder.getConsignee());
         LogisticsHead.addElement("ceb:consigneeAddress").addText(szqgOrder.getConsigneeAddress());
         LogisticsHead.addElement("ceb:consigneeTelephone").addText(szqgOrder.getConsigneeTelephone());
         LogisticsHead.addElement("ceb:note").addText("");
 
         
         Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
         BaseTransfer.addElement("ceb:copCode").addText("331898Z004");
         BaseTransfer.addElement("ceb:copName").addText("中外运跨境电商物流有限公司义乌分公司");
         BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
         BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000027681");
         BaseTransfer.addElement("ceb:note").addText("test");
         
         String xml = document.asXML();
         
         System.out.println("xml=" + xml);
 
         
         String name = "DXPENT0000027681";
         String pwd = "http://115.236.78.6:9090/newyorkTransferWebapps/rest/transferDeclare";
         
         String key = "123456";
         
         URL url = new URL("http://wwyhz.icu:8066/wlsd/twlsd/addSign.do");
 
 
         
         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
         connection.setRequestMethod("POST");
         connection.setDoOutput(true);
         connection.setDoInput(true);
         connection.setUseCaches(false);
         connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         connection.connect();
         DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
         
         String xmls = "xml=" + URLEncoder.encode(xml, "UTF-8") + "&name=" + name + "&pwd=" + pwd + "&key=" + key;
         byte[] bytes = xmls.getBytes("UTF-8");
 
         
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
         br.close();
       } 
 
       
       b++; }
 
     
     return result;
   }
   
   public String ddpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       
       JSONObject jsonObject = new JSONObject();
 
 
       
       if (szqgOrder.getDdguid() != null && !szqgOrder.getDdguid().equals(""))
       {
         
         jsonObject.put("guid", szqgOrder.getDdguid());
       }
       jsonObject.put("appStatus", Integer.valueOf(2));
       jsonObject.put("orderNo", szqgOrder.getOrderNo());
       jsonObject.put("merchantOrderNo", szqgOrder.getOrderNo());
       jsonObject.put("orderType", "I");
       jsonObject.put("entryType", szqgOrder.getEntryType());
       jsonObject.put("ebpCode", szqgOrder.getEbpCode());
       jsonObject.put("ebpName", szqgOrder.getEbpName());
       jsonObject.put("proxyCode", szqgOrder.getProxyCode());
       jsonObject.put("proxyName", szqgOrder.getProxyName());
       jsonObject.put("abroadCo", szqgOrder.getAbroadCo());
       jsonObject.put("abroadCoName", szqgOrder.getAbroadCoName());
       jsonObject.put("goodValue", szqgOrder.getGoodsValue());
       jsonObject.put("goodsValue", szqgOrder.getGoodsValue());
       jsonObject.put("freight", "0");
       jsonObject.put("discount", szqgOrder.getDiscount());
       jsonObject.put("taxTotal", szqgOrder.getTaxTotal());
       jsonObject.put("acturalPaid", szqgOrder.getActuralPaid());
       jsonObject.put("buyerRegNo", szqgOrder.getBuyerName());
       jsonObject.put("buyerName", szqgOrder.getBuyerName());
       jsonObject.put("buyerTelephone", szqgOrder.getBuyerTelephone());
       jsonObject.put("buyerIdType", "1");
       jsonObject.put("buyerIdNumber", szqgOrder.getBuyerIdNumber());
       jsonObject.put("payCode", szqgOrder.getPayCode());
       jsonObject.put("payName", szqgOrder.getPayName());
       jsonObject.put("payTransactionId", szqgOrder.getPayTransactionId());
       jsonObject.put("consignee", szqgOrder.getConsignee());
       jsonObject.put("consigneeTelephone", szqgOrder.getConsigneeTelephone());
       jsonObject.put("consigneeIdType", "1");
       jsonObject.put("consigneeIdNumber", szqgOrder.getConsigneeIdNumber());
       jsonObject.put("consigneePrvince", szqgOrder.getConsigneePrvince());
       jsonObject.put("consigneeCity", szqgOrder.getConsigneeCity());
       jsonObject.put("consigneeCounty", szqgOrder.getConsigneeCounty());
       jsonObject.put("consigneeAddress", szqgOrder.getConsigneeAddress());
       jsonObject.put("currency", "142");
       jsonObject.put("appSession", "6200404de3ffhjd065bb7aaf71734f89930bedd50e7494d2206641060636");
       jsonObject.put("businessId", szqgOrder.getPayTransactionId());
       jsonObject.put("pid", "PARTNER_TAOBAO_ORDER");
       jsonObject.put("appType", "1");
       
       jsonObject.put("subMerchantId", "130714748571879296");
       jsonObject.put("subMerchantName", "19999990003");
       
       List<SzqgAgreement> list = this.szqgagreementdao.findgjz("4");
       String PriKey = "";
       for (SzqgAgreement sam : list) {
         jsonObject.put("appid", sam.getAppid());
         jsonObject.put("version", sam.getVersion());
         jsonObject.put("merchantName", sam.getMerchantName());
         jsonObject.put("merchantId", sam.getMerchantId());
         
         jsonObject.put("commitTime", timenew.newtime());
 
         
         PriKey = sam.getPersonPriKey();
       } 
       
       List<SzqgOrderSku> orderBondedSkuList = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
 
       
       JSONArray status = new JSONArray();
       int j = 0;
       for (SzqgOrderSku orderBondedSku : orderBondedSkuList) {
         j++;
         JSONObject orderListDto = new JSONObject();
         orderListDto.put("itemNo", orderBondedSku.getItemNo());
         orderListDto.put("itemName", orderBondedSku.getItemName());
         orderListDto.put("gModel", orderBondedSku.getgModel());
         orderListDto.put("unit", orderBondedSku.getUnit());
         orderListDto.put("qty", orderBondedSku.getQty());
         orderListDto.put("price", orderBondedSku.getPrice());
         orderListDto.put("totalPrice", orderBondedSku.getTotalPrice());
         orderListDto.put("currency", orderBondedSku.getCurrency());
         
         orderListDto.put("gnum", Integer.valueOf(j));
         orderListDto.put("gmodel", orderBondedSku.getgModel());
         orderListDto.put("hscode", orderBondedSku.getHScode());
         orderListDto.put("codeTs", orderBondedSku.getCodeTs());
         orderListDto.put("country", orderBondedSku.getCountry());
 
         
         status.add(orderListDto);
       } 
       ApolloSdk apolloSdk = new ApolloSdk();
       jsonObject.put("orderListDto", status);
       
       String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
       
       System.out.println("param=" + param);
 
 
       
       String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/orderDeclare";
       
       System.out.println("url=" + url);
       String res = hcszqgpost.ZYPost(url, param);
       System.out.println("res=" + res);
       
       JSONObject ret = JSONObject.parseObject(res);
       
       String code = ret.getString("code");
       
       System.out.println("code=====" + code);
       
       result = ret.getString("message");
       
       System.out.println("message=====" + result);
       
       if (code.equals("200")) {
         String data = ret.getString("data");
         JSONObject datas = JSONObject.parseObject(data);
         String guid = datas.getString("guid");
         System.out.println("guid=====" + guid);
         guid = "ddguid='" + guid + "'";
         
         this.szqgorderDao.updatebygjz(szqgOrder.getId(), guid, timenew.newtime4());
       } 
       
       b++; }
     
     return result;
   }
   
   public String ctddpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       
       String str = "ZY000000-FDFD-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
       Element cebRoot = DocumentHelper.createElement("ceb:CEB311Message");
       cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
       
       cebRoot.addAttribute("guid", str);
       cebRoot.addAttribute("version", "1.0");
       cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
       Document document = DocumentHelper.createDocument(cebRoot);
       
       Element Order = cebRoot.addElement("ceb:Order");
       Element OrderHead = Order.addElement("ceb:OrderHead");
       OrderHead.addElement("ceb:guid").addText(str);
       OrderHead.addElement("ceb:appType").addText("1");
       OrderHead.addElement("ceb:appTime").addText(timenew.newtime());
       OrderHead.addElement("ceb:appStatus").addText("2");
       OrderHead.addElement("ceb:orderType").addText("I");
       OrderHead.addElement("ceb:orderNo").addText(szqgOrder.getOrderNo());
       OrderHead.addElement("ceb:ebpCode").addText(szqgOrder.getEbpCode());
       OrderHead.addElement("ceb:ebpName").addText(szqgOrder.getEbpName());
       OrderHead.addElement("ceb:ebcCode").addText(szqgOrder.getProxyCode());
       OrderHead.addElement("ceb:ebcName").addText(szqgOrder.getProxyName());
       OrderHead.addElement("ceb:goodsValue").addText(szqgOrder.getGoodsValue());
       OrderHead.addElement("ceb:freight").addText(szqgOrder.getFreight());
       OrderHead.addElement("ceb:discount").addText(szqgOrder.getDiscount());
       OrderHead.addElement("ceb:taxTotal").addText(szqgOrder.getTaxTotal());
       OrderHead.addElement("ceb:acturalPaid").addText(szqgOrder.getActuralPaid());
       OrderHead.addElement("ceb:currency").addText("142");
       OrderHead.addElement("ceb:buyerRegNo").addText("HC2021ASL");
       OrderHead.addElement("ceb:buyerName").addText(szqgOrder.getBuyerName());
       OrderHead.addElement("ceb:buyerTelephone").addText(szqgOrder.getBuyerTelephone());
       OrderHead.addElement("ceb:buyerIdType").addText("1");
       OrderHead.addElement("ceb:buyerIdNumber").addText(szqgOrder.getBuyerIdNumber());
       OrderHead.addElement("ceb:payCode").addText(szqgOrder.getPayCode());
       OrderHead.addElement("ceb:payName").addText(szqgOrder.getPayName());
       OrderHead.addElement("ceb:payTransactionId").addText(szqgOrder.getPayTransactionId());
       OrderHead.addElement("ceb:batchNumbers").addText("1");
       OrderHead.addElement("ceb:consignee").addText(szqgOrder.getConsignee());
       OrderHead.addElement("ceb:consigneeTelephone").addText(szqgOrder.getConsigneeTelephone());
       OrderHead.addElement("ceb:consigneeAddress").addText(szqgOrder.getConsigneeAddress());
       OrderHead.addElement("ceb:consigneeDistrict").addText("321408");
       OrderHead.addElement("ceb:note").addText("1");
       
       List<SzqgOrderSku> orderBondedSkuList = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
       int j = 0;
       for (SzqgOrderSku orderBondedSku : orderBondedSkuList) {
         j++;
         Element OrderList = Order.addElement("ceb:OrderList");
         OrderList.addElement("ceb:gnum").addText((new StringBuilder(String.valueOf(j))).toString());
         OrderList.addElement("ceb:itemNo").addText(orderBondedSku.getItemNo());
         OrderList.addElement("ceb:itemName").addText(newzs.getChineseOrEnglishOrNumber(orderBondedSku.getItemName()));
         OrderList.addElement("ceb:gmodel").addText(orderBondedSku.getgModel());
         OrderList.addElement("ceb:itemDescribe").addText(orderBondedSku.getItemDescribe());
         OrderList.addElement("ceb:barCode").addText("");
         OrderList.addElement("ceb:unit").addText(orderBondedSku.getUnit());
         OrderList.addElement("ceb:qty").addText(orderBondedSku.getQty());
         OrderList.addElement("ceb:price").addText(orderBondedSku.getPrice());
         OrderList.addElement("ceb:totalPrice").addText(orderBondedSku.getTotalPrice());
         OrderList.addElement("ceb:currency").addText("142");
         OrderList.addElement("ceb:country").addText(orderBondedSku.getCountry());
         OrderList.addElement("ceb:note").addText("备注");
       } 
       
       Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
       BaseTransfer.addElement("ceb:copCode").addText("3318960DDU");
       BaseTransfer.addElement("ceb:copName").addText("义乌市六六顺科技有限公司");
       BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
       BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000453525");
       BaseTransfer.addElement("ceb:note").addText("test");
 
       
       String xml = document.asXML();
       System.out.println("xml=" + xml);
       String name = "DXPENT0000453525";
       
       String pwd = "http://115.236.78.6:9090/newyorkTransferWebapps/rest/transferDeclare";
       
       String key = "123456";
       
       URL url = new URL("http://wwyhz.icu:8066/wlsd/twlsd/addSign.do");
 
 
       
       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
       connection.setRequestMethod("POST");
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       connection.connect();
       DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
       
       String xmls = "xml=" + URLEncoder.encode(xml, "UTF-8") + "&name=" + name + "&pwd=" + pwd + "&key=" + key;
       byte[] bytes = xmls.getBytes("UTF-8");
 
       
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
       br.close();
 
       
       b++; }
 
     
     return result;
   }
   
   public String hctbpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       
       JSONObject jsonObject = new JSONObject();
 
 
       
       jsonObject.put("orderNo", szqgOrder.getOrderNo());
       jsonObject.put("ebpCode", "312224099T");
       jsonObject.put("ebcCode", "3318960C6H");
       jsonObject.put("ebpName", "上海别样秀数据科技有限公司");
       jsonObject.put("ebcName", "义乌易链科技有限公司");
       jsonObject.put("payCode", szqgOrder.getPayCode());
       jsonObject.put("payName", szqgOrder.getPayName());
       jsonObject.put("payTransactionId", szqgOrder.getPayTransactionId());
       jsonObject.put("buyerName", szqgOrder.getBuyerName());
       jsonObject.put("buyerIdNumber", szqgOrder.getBuyerIdNumber());
       jsonObject.put("buyerTelephone", szqgOrder.getBuyerTelephone());
       jsonObject.put("consigneeTelephone", szqgOrder.getConsigneeTelephone());
       jsonObject.put("consignee", szqgOrder.getConsignee());
       jsonObject.put("consigneeProvince", szqgOrder.getConsigneePrvince());
       jsonObject.put("consigneeCity", szqgOrder.getConsigneeCity());
       jsonObject.put("consigneeCounty", szqgOrder.getConsigneeCounty());
       jsonObject.put("consigneeAddress", szqgOrder.getConsigneeAddress());
       jsonObject.put("logisticsNo", szqgOrder.getMailno());
       jsonObject.put("logisticsName", "中外运跨境电商物流有限公司义乌分公司");
       jsonObject.put("logisticsCode", "331898Z004");
       jsonObject.put("billNo", szqgOrder.getBillNo());
       jsonObject.put("voyageNo", szqgOrder.getVoyageNo());
       jsonObject.put("tradeMode", "9610");
       jsonObject.put("trafMode", szqgOrder.getTrafMode());
       jsonObject.put("country", szqgOrder.getCountry());
       jsonObject.put("wrapType", "2");
       jsonObject.put("goodsValue", szqgOrder.getGoodsValue());
       jsonObject.put("freight", szqgOrder.getFreight());
       jsonObject.put("insuredFee", szqgOrder.getTaxTotal());
       jsonObject.put("grossWeight", szqgOrder.getGrossWeight());
       jsonObject.put("netWeight", szqgOrder.getNetWeight());
       jsonObject.put("fromto", "SHBY");
 
       
       List<SzqgOrderSku> orderBondedSkuList = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
       
       JSONArray status = new JSONArray();
       int j = 0;
       for (SzqgOrderSku orderBondedSku : orderBondedSkuList) {
         j++;
         JSONObject orderListDto = new JSONObject();
         orderListDto.put("orderNo", orderBondedSku.getOrderNo());
         orderListDto.put("gname", orderBondedSku.getItemName());
         orderListDto.put("itemNo", orderBondedSku.getItemNo());
         orderListDto.put("price", orderBondedSku.getPrice());
         orderListDto.put("qty", orderBondedSku.getQty());
         orderListDto.put("totalPrice", orderBondedSku.getTotalPrice());
         orderListDto.put("ItemWeight", orderBondedSku.getNetWeight());
         orderListDto.put("country", orderBondedSku.getCountry());
         orderListDto.put("itemWeight", orderBondedSku.getNetWeight());
         
         status.add(orderListDto);
       } 
       jsonObject.put("detailDtoList", status);
       System.out.println("jsonObject.toString()=" + jsonObject.toString());
       String url = "http://59.111.89.9:8066/hcerp/receiveEwtpData/CreateOrder1.do";
       String resout3 = PushtoWQ.sendPost2(url, jsonObject.toString());
 
 
       
       String guid = "ddguid='111'";
       
       JSONObject datas = JSONObject.parseObject(resout3);
       String msg = datas.getString("msg");
       if (msg.equals("订单创建成功")) {
         this.szqgorderDao.updatebygjz(szqgOrder.getId(), guid, timenew.newtime4());
       }
       
       b++; }
     
     return result;
   }
 
 
 
   
   public String qdpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       JSONObject jsonObject = new JSONObject();
       if (szqgOrder.getQdguid() != null && !szqgOrder.getQdguid().equals(""))
       {
         jsonObject.put("guid", szqgOrder.getQdguid());
       }
       jsonObject.put("appStatus", Integer.valueOf(2));
       jsonObject.put("orderNo", szqgOrder.getOrderNo());
       
       jsonObject.put("ebpCode", szqgOrder.getEbpCode());
       jsonObject.put("ebpName", szqgOrder.getEbpName());
       jsonObject.put("ebcCode", szqgOrder.getProxyCode());
       jsonObject.put("ebcName", szqgOrder.getProxyName());
       jsonObject.put("logisticsNo", szqgOrder.getMailno());
       jsonObject.put("logisticsCode", szqgOrder.getLogisticsCode());
       jsonObject.put("logisticsName", szqgOrder.getLogisticsName());
       jsonObject.put("copNo", szqgOrder.getCopNo());
       
       jsonObject.put("assureCode", "3318W6K011");
       jsonObject.put("emsNo", szqgOrder.getEmsNo());
       jsonObject.put("ieFlag", "I");
       jsonObject.put("declTime", timenew.newtime3());
       jsonObject.put("customsCode", szqgOrder.getCustomsCode());
       jsonObject.put("portCode", szqgOrder.getPortCode());
       jsonObject.put("buyerIdType", "1");
       jsonObject.put("buyerIdNumber", szqgOrder.getBuyerIdNumber());
       jsonObject.put("buyerName", szqgOrder.getBuyerName());
       jsonObject.put("buyerTelephone", szqgOrder.getBuyerTelephone());
       jsonObject.put("consigneeAddress", szqgOrder.getConsigneeAddress());
 
 
       
       jsonObject.put("agentCode", "3318W6K011");
       jsonObject.put("agentName", "义乌海仓供应链管理有限公司");
       jsonObject.put("areaCode", szqgOrder.getAreaCode());
       jsonObject.put("areaName", szqgOrder.getAreaName());
       
       jsonObject.put("entryType", szqgOrder.getEntryType());
       jsonObject.put("trafMode", szqgOrder.getTrafMode());
       jsonObject.put("trafNo", szqgOrder.getTrafNo());
       jsonObject.put("voyageNo", szqgOrder.getVoyageNo());
       jsonObject.put("billNo", szqgOrder.getBillNo());
       jsonObject.put("country", szqgOrder.getCountry());
       jsonObject.put("freight", "0");
       jsonObject.put("insuredFee", "0");
       jsonObject.put("currency", "142");
       jsonObject.put("packNo", "1");
       jsonObject.put("grossWeight", szqgOrder.getGrossWeight());
       jsonObject.put("netWeight", szqgOrder.getNetWeight());
       jsonObject.put("note", "yw");
       jsonObject.put("appType", "1");
       jsonObject.put("ieDate", timenew.newtime3());
 
       
       List<SzqgAgreement> list = this.szqgagreementdao.findgjz("3");
       String PriKey = "";
       for (SzqgAgreement sam : list) {
         jsonObject.put("appid", sam.getAppid());
         jsonObject.put("version", sam.getVersion());
         jsonObject.put("merchantName", sam.getMerchantName());
         jsonObject.put("merchantId", sam.getMerchantId());
         jsonObject.put("commitTime", timenew.newtime());
         
         PriKey = sam.getPersonPriKey();
       } 
       
       List<SzqgOrderSku> orderBondedSkuList = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
 
       
       JSONArray status = new JSONArray();
       int j = 0;
       for (SzqgOrderSku orderBondedSku : orderBondedSkuList) {
         j++;
         JSONObject orderListDto = new JSONObject();
 
         
         orderListDto.put("gnum", Integer.valueOf(j));
         orderListDto.put("itemRecordNo", orderBondedSku.getItemNo());
         orderListDto.put("itemNo", orderBondedSku.getItemNo());
         orderListDto.put("itemName", orderBondedSku.getItemName());
         orderListDto.put("gcode", orderBondedSku.getHScode());
         orderListDto.put("gname", orderBondedSku.getItemName());
         orderListDto.put("gmodel", orderBondedSku.getgModel());
         orderListDto.put("barCode", orderBondedSku.getBarCode());
         orderListDto.put("country", orderBondedSku.getCountry());
         orderListDto.put("currency", "142");
         orderListDto.put("qty", orderBondedSku.getQty());
         orderListDto.put("unit", orderBondedSku.getUnit());
         orderListDto.put("qty1", orderBondedSku.getFirstCount());
         orderListDto.put("unit1", orderBondedSku.getFirstUnit());
         
         if (!orderBondedSku.getSecondUnit().equals("")) {
 
           
           orderListDto.put("qty2", orderBondedSku.getSecondCount());
           orderListDto.put("unit2", orderBondedSku.getSecondUnit());
         } 
         orderListDto.put("price", orderBondedSku.getPrice());
         orderListDto.put("totalPrice", orderBondedSku.getTotalPrice());
         orderListDto.put("note", "note");
         
         status.add(orderListDto);
       } 
       jsonObject.put("customsDeclareBodyDTO", status);
       ApolloSdk apolloSdk = new ApolloSdk();
       
       String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
       
       System.out.println("param=" + param);
 
 
 
       
       String url = "http://apollo.jieztech.com/aoa/api/aoa/publicServicePlatformFacade/customsDeclare";
 
       
       String res = hcszqgpost.ZYPost(url, param);
       System.out.println("res=" + res);
       
       JSONObject ret = JSONObject.parseObject(res);
       String code = ret.getString("code");
       System.out.println("code=====" + code);
       result = ret.getString("message");
       System.out.println("message=====" + result);
       
       if (code.equals("200")) {
         String data = ret.getString("data");
         JSONObject datas = JSONObject.parseObject(data);
         String guid = datas.getString("guid");
         System.out.println("guid=====" + guid);
         guid = "qdguid='" + guid + "'";
         
         this.szqgorderDao.updatebygjz(szqgOrder.getId(), guid, timenew.newtime4());
       } 
       
       b++; }
 
     
     return result;
   }
   
   public String ctqdpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       
       String str = "BQ1E1CFD-EDED-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
       Element cebRoot = DocumentHelper.createElement("ceb:CEB621Message");
       cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
       
       cebRoot.addAttribute("guid", str);
       cebRoot.addAttribute("version", "1.0");
       cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
       Document document = DocumentHelper.createDocument(cebRoot);
       Element Logistics = cebRoot.addElement("ceb:Inventory");
       Element InventoryHead = Logistics.addElement("ceb:InventoryHead");
       InventoryHead.addElement("ceb:guid").addText(str);
       InventoryHead.addElement("ceb:appType").addText("1");
       InventoryHead.addElement("ceb:appTime").addText(timenew.newtime());
       InventoryHead.addElement("ceb:appStatus").addText("2");
       InventoryHead.addElement("ceb:orderNo").addText(szqgOrder.getOrderNo());
       InventoryHead.addElement("ceb:ebpCode").addText(szqgOrder.getEbpCode());
       InventoryHead.addElement("ceb:ebpName").addText(szqgOrder.getEbpName());
       InventoryHead.addElement("ceb:ebcCode").addText(szqgOrder.getProxyCode());
       InventoryHead.addElement("ceb:ebcName").addText(szqgOrder.getProxyName());
       InventoryHead.addElement("ceb:logisticsNo").addText(szqgOrder.getMailno());
       
       InventoryHead.addElement("ceb:logisticsCode").addText(szqgOrder.getLogisticsCode());
       InventoryHead.addElement("ceb:logisticsName").addText(szqgOrder.getLogisticsName());
       
       InventoryHead.addElement("ceb:copNo").addText(szqgOrder.getCopNo());
       InventoryHead.addElement("ceb:preNo");
       InventoryHead.addElement("ceb:assureCode").addText(szqgOrder.getAssureCode());
       InventoryHead.addElement("ceb:emsNo").addText("");
       InventoryHead.addElement("ceb:invtNo");
       InventoryHead.addElement("ceb:ieFlag").addText("I");
       InventoryHead.addElement("ceb:declTime").addText(timenew.newtime3());
       InventoryHead.addElement("ceb:customsCode").addText(szqgOrder.getCustomsCode());
       InventoryHead.addElement("ceb:portCode").addText(szqgOrder.getPortCode());
       InventoryHead.addElement("ceb:ieDate").addText(timenew.newtime3());
       InventoryHead.addElement("ceb:buyerIdType").addText("1");
       InventoryHead.addElement("ceb:buyerIdNumber").addText(szqgOrder.getBuyerIdNumber());
       InventoryHead.addElement("ceb:buyerName").addText(szqgOrder.getBuyerName());
       InventoryHead.addElement("ceb:buyerTelephone").addText(szqgOrder.getBuyerTelephone());
       InventoryHead.addElement("ceb:consigneeAddress").addText(szqgOrder.getConsigneeAddress());
       InventoryHead.addElement("ceb:agentCode").addText(szqgOrder.getAgentCode());
       InventoryHead.addElement("ceb:agentName").addText(szqgOrder.getAgentName());
       InventoryHead.addElement("ceb:areaCode").addText("");
       InventoryHead.addElement("ceb:areaName").addText("");
       InventoryHead.addElement("ceb:tradeMode").addText("9610");
       InventoryHead.addElement("ceb:trafMode").addText(szqgOrder.getTrafMode());
       InventoryHead.addElement("ceb:trafNo").addText(szqgOrder.getTrafNo());
       InventoryHead.addElement("ceb:voyageNo").addText(szqgOrder.getVoyageNo());
       InventoryHead.addElement("ceb:billNo").addText(szqgOrder.getBillNo());
       InventoryHead.addElement("ceb:loctNo").addText(szqgOrder.getPortCode());
       InventoryHead.addElement("ceb:licenseNo").addText("");
       InventoryHead.addElement("ceb:country").addText(szqgOrder.getCountry());
       
       InventoryHead.addElement("ceb:freight").addText(szqgOrder.getFreight());
       
       InventoryHead.addElement("ceb:insuredFee").addText("0");
       InventoryHead.addElement("ceb:currency").addText("142");
       InventoryHead.addElement("ceb:wrapType").addText("2");
       InventoryHead.addElement("ceb:packNo").addText("1");
       InventoryHead.addElement("ceb:grossWeight").addText(szqgOrder.getGrossWeight());
       InventoryHead.addElement("ceb:netWeight").addText(szqgOrder.getNetWeight());
       InventoryHead.addElement("ceb:note");
 
       
       List<SzqgOrderSku> orderBondedSkuList = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
 
       
       int j = 0;
       for (SzqgOrderSku orderBondedSku : orderBondedSkuList) {
         j++;
         
         Element InventoryList = Logistics.addElement("ceb:InventoryList");
         InventoryList.addElement("ceb:gnum").addText((new StringBuilder(String.valueOf(j))).toString());
         InventoryList.addElement("ceb:itemRecordNo").addText("");
         
         InventoryList.addElement("ceb:itemNo").addText(orderBondedSku.getItemNo());
         
         InventoryList.addElement("ceb:itemName").addText(orderBondedSku.getItemName());
         InventoryList.addElement("ceb:gcode").addText(orderBondedSku.getHScode());
         InventoryList.addElement("ceb:gname").addText(orderBondedSku.getItemName());
         InventoryList.addElement("ceb:gmodel").addText(orderBondedSku.getgModel());
         InventoryList.addElement("ceb:barCode").addText("无");
         InventoryList.addElement("ceb:country").addText(orderBondedSku.getCountry());
 
         
         InventoryList.addElement("ceb:currency").addText("142");
         InventoryList.addElement("ceb:qty").addText(orderBondedSku.getQty());
         InventoryList.addElement("ceb:unit").addText(orderBondedSku.getUnit());
 
         
         InventoryList.addElement("ceb:qty1").addText(orderBondedSku.getFirstCount());
         
         InventoryList.addElement("ceb:unit1").addText(orderBondedSku.getFirstUnit());
         
         if (!orderBondedSku.getSecondUnit().equals("")) {
 
           
           InventoryList.addElement("ceb:qty2").addText(orderBondedSku.getSecondCount());
           InventoryList.addElement("ceb:unit2").addText(orderBondedSku.getSecondUnit());
         } 
         
         InventoryList.addElement("ceb:price").addText(orderBondedSku.getPrice());
         InventoryList.addElement("ceb:totalPrice").addText(orderBondedSku.getTotalPrice());
         InventoryList.addElement("ceb:note");
       } 
       
       Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
       BaseTransfer.addElement("ceb:copCode").addText("3318960DDU");
       BaseTransfer.addElement("ceb:copName").addText("义乌市六六顺科技有限公司");
       BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
       BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000453525");
       BaseTransfer.addElement("ceb:note").addText("test");
       
       String xml = document.asXML();
       System.out.println("xml=" + xml);
       String name = "DXPENT0000453525";
 
 
       
       String pwd = "http://115.236.78.6:9090/newyorkTransferWebapps/rest/transferDeclare";
       
       String key = "123456";
       
       URL url = new URL("http://wwyhz.icu:8066/wlsd/twlsd/addSign.do");
 
 
       
       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
       connection.setRequestMethod("POST");
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       connection.connect();
       DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
       
       String xmls = "xml=" + URLEncoder.encode(xml, "UTF-8") + "&name=" + name + "&pwd=" + pwd + "&key=" + key;
       byte[] bytes = xmls.getBytes("UTF-8");
 
       
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
       br.close();
       
       b++; }
     
     return result;
   }
 
 
 
   
   public String fydpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       
       JSONObject jsonObject = new JSONObject();
       
       if (szqgOrder.getYdzt() != null && !szqgOrder.getYdzt().equals(""))
       {
         
         jsonObject.put("guid", szqgOrder.getYdzt());
       }
       jsonObject.put("appStatus", Integer.valueOf(2));
       jsonObject.put("appType", "1");
       jsonObject.put("logisticsCode", "331898Z004");
       jsonObject.put("logisticsName", "中外运跨境电商物流有限公司义乌分公司");
       jsonObject.put("entryType", "9610");
       jsonObject.put("logisticsNo", szqgOrder.getMailno());
       jsonObject.put("abroadCo", "");
       jsonObject.put("abroadCoName", "");
       jsonObject.put("billNo", szqgOrder.getBillNo());
       jsonObject.put("orderNo", szqgOrder.getOrderNo());
       
       jsonObject.put("freight", "0");
       jsonObject.put("insuredFee", "0");
       jsonObject.put("currency", "142");
       jsonObject.put("weight", szqgOrder.getGrossWeight());
       jsonObject.put("packNo", "1");
       jsonObject.put("goodsInfo", "货物");
       jsonObject.put("consignee", szqgOrder.getConsignee());
       jsonObject.put("consigneeProvince", szqgOrder.getConsigneePrvince());
       jsonObject.put("consigneeCity", szqgOrder.getConsigneeCity());
       jsonObject.put("consigneeCounty", szqgOrder.getConsigneeCounty());
       jsonObject.put("consigneeAddress", szqgOrder.getConsigneeAddress());
       jsonObject.put("consigneeTelephone", szqgOrder.getConsigneeTelephone());
       jsonObject.put("note", "1");
       
       List<SzqgAgreement> list = this.szqgagreementdao.findgjz("4");
       String PriKey = "";
       for (SzqgAgreement sam : list) {
         jsonObject.put("appid", sam.getAppid());
         jsonObject.put("version", sam.getVersion());
         jsonObject.put("merchantName", sam.getMerchantName());
         jsonObject.put("merchantId", sam.getMerchantId());
         jsonObject.put("commitTime", timenew.newtime());
         
         jsonObject.put("subMerchantId", "130714748571879296");
         jsonObject.put("subMerchantName", "19999990003");
         PriKey = sam.getPersonPriKey();
       } 
       
       ApolloSdk apolloSdk = new ApolloSdk();
       
       String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
       
       System.out.println("param=" + param);
       
       String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/waybillOrderDeclare";
       
       String res = hcszqgpost.ZYPost(url, param);
       System.out.println("res=" + res);
       
       JSONObject ret = JSONObject.parseObject(res);
       String code = ret.getString("code");
       System.out.println("code=====" + code);
       result = ret.getString("message");
       System.out.println("message=====" + result);
       
       if (code.equals("200")) {
         String data = ret.getString("data");
         JSONObject datas = JSONObject.parseObject(data);
         String guid = datas.getString("guid");
         System.out.println("guid=====" + guid);
         guid = "qdguid='" + guid + "'";
       } 
 
       
       b++; }
 
 
     
     return result;
   }
 
 
 
   
   public String zydpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1";
 
     
     SzqgOrderExpot soexport = new SzqgOrderExpot();
     
     soexport.setBillNo("44979764844");
 
     
     List<SzqgOrderExpot> list = this.szqgorderDao.exportszqg(soexport);
     int i = 1;
 
     
     JSONObject jsonObject = new JSONObject();
     
     jsonObject.put("appStatus", Integer.valueOf(2));
     jsonObject.put("appType", "1");
     jsonObject.put("customsCode", "2921");
     jsonObject.put("entryType", "B");
     jsonObject.put("ieFlag", "I");
     jsonObject.put("copNo", "123456789");
     jsonObject.put("preNo", "");
     jsonObject.put("agentCode", "331898Z004");
     jsonObject.put("agentName", "中外运跨境电商物流有限公司义乌分公司");
     jsonObject.put("loctNo", "2921");
     
     jsonObject.put("trafMode", "5");
     jsonObject.put("trafName", "航运");
     jsonObject.put("voyageNo", "HB20200714");
     
     jsonObject.put("domesticTrafNo", "");
     
     jsonObject.put("logisticsCode", "331898Z004");
     jsonObject.put("logisticsName", "中外运跨境电商物流有限公司义乌分公司");
     jsonObject.put("msgCount", "1");
     jsonObject.put("msgSeqNo", "1");
     jsonObject.put("iEDate", "20210317020101");
     jsonObject.put("note", "");
     
     List<SzqgAgreement> list2 = this.szqgagreementdao.findgjz("4");
     String PriKey = "";
     for (SzqgAgreement sam : list2) {
       jsonObject.put("appid", sam.getAppid());
       jsonObject.put("version", sam.getVersion());
       jsonObject.put("merchantName", sam.getMerchantName());
       jsonObject.put("merchantId", sam.getMerchantId());
       jsonObject.put("commitTime", timenew.newtime());
       
       jsonObject.put("subMerchantId", "130714748571879296");
       jsonObject.put("subMerchantName", "19999990003");
       PriKey = sam.getPersonPriKey();
     } 
     
     String billNo = "";
     String grossWeight = "";
     JSONArray status = new JSONArray();
     for (SzqgOrderExpot szqgOrder : list) {
       System.out.println();
       
       if (szqgOrder.getZfzt() != null && !szqgOrder.getZfzt().equals(""))
       {
         
         jsonObject.put("guid", szqgOrder.getZfzt());
       }
       billNo = szqgOrder.getBillNo();
       grossWeight = szqgOrder.getGrossWeight();
       
       JSONObject orderListDto = new JSONObject();
 
       
       orderListDto.put("gnum", Integer.valueOf(i));
       orderListDto.put("totalPackageNo", "");
       orderListDto.put("logisticsNo", szqgOrder.getMailno());
       orderListDto.put("note", "");
       status.add(orderListDto);
       i++;
     } 
     jsonObject.put("billNo", billNo);
     jsonObject.put("grossWeight", grossWeight);
     jsonObject.put("wayBillItemDtoList", status);
     
     ApolloSdk apolloSdk = new ApolloSdk();
     
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
     
     System.out.println("param=" + param);
     
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/generalWaybillDeclare";
     
     String res = hcszqgpost.ZYPost(url, param);
     System.out.println("res=" + res);
     
     JSONObject ret = JSONObject.parseObject(res);
     String code = ret.getString("code");
     System.out.println("code=====" + code);
     result = ret.getString("message");
     System.out.println("message=====" + result);
     
     if (code.equals("200")) {
       String data = ret.getString("data");
       JSONObject datas = JSONObject.parseObject(data);
       String guid = datas.getString("guid");
       System.out.println("guid=====" + guid);
       guid = "qdguid='" + guid + "'";
     }     
     return result;
   }
 
 
 
   
   public String CCqdpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       
       JSONObject jsonObject = new JSONObject();
       
       if (szqgOrder.getQdguid() != null && !szqgOrder.getQdguid().equals(""))
       {
         
         jsonObject.put("guid", szqgOrder.getQdguid());
       }
       jsonObject.put("appStatus", Integer.valueOf(2));
       jsonObject.put("ieFlag", "I");
       jsonObject.put("iePort", szqgOrder.getCustomsCode());
       jsonObject.put("ieDate", timenew.newtime5());
       jsonObject.put("declareDate", timenew.newtime5());
       jsonObject.put("dDate", timenew.newtime5());
       
       jsonObject.put("destinationPort", "502");
       jsonObject.put("trafName", "航运");
       jsonObject.put("voyageNo", szqgOrder.getVoyageNo());
       jsonObject.put("trafMode", "5");
       jsonObject.put("ownerName", "义乌市六六顺科技有限公司");
       jsonObject.put("agentType", "0");
       jsonObject.put("agentCode", "3318960DDU");
       jsonObject.put("agentName", "义乌市六六顺科技有限公司");
       jsonObject.put("billNo", szqgOrder.getBillNo());
       jsonObject.put("assBillNo", szqgOrder.getMailno());
       jsonObject.put("tradeCountry", "502");
       jsonObject.put("tradeMode", "");
       jsonObject.put("cutMode", "");
       jsonObject.put("transMode", "");
       jsonObject.put("feeMark", "");
       jsonObject.put("feeCurr", "");
       jsonObject.put("feeRate", "");
       jsonObject.put("insurMark", "");
       jsonObject.put("insurCurr", "");
       jsonObject.put("insurRate", "");
       jsonObject.put("otherMark", "");
       jsonObject.put("otherCurr", "");
       jsonObject.put("otherRate", "");
       jsonObject.put("packNo", "1");
       jsonObject.put("grossWt", szqgOrder.getGrossWeight());
       jsonObject.put("netWt", szqgOrder.getNetWeight());
       jsonObject.put("wrapType", "2");
       jsonObject.put("woodWrap", "0");
       jsonObject.put("goodsUsed", "0");
       jsonObject.put("lowTempTrans", "0");
       jsonObject.put("noteS", "");
       jsonObject.put("declPort", "2921");
       jsonObject.put("coOwner", "7");
       jsonObject.put("relativeId", "");
       jsonObject.put("inputNo", "六六顺");
       jsonObject.put("inputCompanyCo", "3318960DDU");
       jsonObject.put("inputCompanyName", "义乌市六六顺科技有限公司");
       jsonObject.put("declareNo", "");
       jsonObject.put("customsField", "");
       jsonObject.put("kjId", "");
       jsonObject.put("sendName", "义乌市六六顺科技有限公司");
       jsonObject.put("sendNameEn", "Yiwu liuliushun Technology Co., Ltd");
       jsonObject.put("sendCountry", "142");
       jsonObject.put("sendCity", "义乌");
       jsonObject.put("sendCityEn", "Yiwu");
       jsonObject.put("stopCityEn", "hangzhou");
       jsonObject.put("sendAddress", "义乌市鸿运路315号陆港电商小镇8座902");
       jsonObject.put("sendAddressEn", "");
       jsonObject.put("sendTelNo", "18357183105");
       jsonObject.put("sendIdType", "1");
       jsonObject.put("sendId", "91330782MA0YBW5A");
       jsonObject.put("totalValue", szqgOrder.getActuralPaid());
       jsonObject.put("currCode", "142");
       jsonObject.put("entryType", "B");
       jsonObject.put("mainGName", "XX");
       jsonObject.put("mainGNameEn", "1");
       
       jsonObject.put("receiveName", szqgOrder.getConsignee());
       jsonObject.put("receiveNameEn", "");
       jsonObject.put("receiveCountry", "142");
       jsonObject.put("receiveProvince", szqgOrder.getConsigneePrvince());
       jsonObject.put("receiveCity", szqgOrder.getConsigneeCity());
       jsonObject.put("receiveDistrict", szqgOrder.getConsigneeCounty());
       jsonObject.put("receiveAddress", szqgOrder.getConsigneeAddress());
       jsonObject.put("receiveAddressEn", "");
       jsonObject.put("receiveTelNo", szqgOrder.getConsigneeTelephone());
       
       jsonObject.put("opType", "ADD");
       List<SzqgAgreement> list = this.szqgagreementdao.findgjz("7");
       String PriKey = "";
       for (SzqgAgreement sam : list) {
         jsonObject.put("appid", sam.getAppid());
         jsonObject.put("version", sam.getVersion());
         jsonObject.put("merchantName", sam.getMerchantName());
         jsonObject.put("merchantId", sam.getMerchantId());
         jsonObject.put("commitTime", timenew.newtime());
         
         jsonObject.put("subMerchantId", "130714748571879296");
         jsonObject.put("subMerchantName", "19999990003");
         PriKey = sam.getPersonPriKey();
       } 
       
       List<SzqgOrderSku> orderBondedSkuList = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
       
       JSONArray status = new JSONArray();
       int j = 0;
       for (SzqgOrderSku orderBondedSku : orderBondedSkuList) {
         j++;
         JSONObject orderListDto = new JSONObject();
 
         
         orderListDto.put("codeId", orderBondedSku.getItemNo());
         orderListDto.put("codeTS", orderBondedSku.getCodeTs());
         orderListDto.put("cargoName", orderBondedSku.getItemName());
         orderListDto.put("cargoNameEn", "item");
         orderListDto.put("cargoModel", orderBondedSku.getgModel());
         orderListDto.put("originCountry", orderBondedSku.getCountry());
         orderListDto.put("originCity", "New York");
         orderListDto.put("mName", "");
         orderListDto.put("tradeCurr", "142");
         orderListDto.put("tradeTotal", orderBondedSku.getTotalPrice());
         orderListDto.put("declPrice", orderBondedSku.getPrice());
         orderListDto.put("declTotal", orderBondedSku.getTotalPrice());
         orderListDto.put("useTo", "");
         orderListDto.put("dutyMode", "");
         orderListDto.put("qty", orderBondedSku.getQty());
         orderListDto.put("unit", orderBondedSku.getUnit());
         orderListDto.put("grossWT", orderBondedSku.getGrossWeight());
         
         orderListDto.put("qty1", "");
         orderListDto.put("unit1", "");
         orderListDto.put("qty2", "");
         orderListDto.put("unit2", "");
 
         
         status.add(orderListDto);
       } 
       jsonObject.put("expressCustomsDeclareBodyDTO", status);
       ApolloSdk apolloSdk = new ApolloSdk();
       
       String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
       
       System.out.println("param=" + param);
 
 
 
       
       String url = "http://apollo.jieztech.com/aoa/api/aoa/publicServicePlatformFacade/expressCustomsDeclare";
 
       
       String res = hcszqgpost.ZYPost(url, param);
       System.out.println("res=" + res);
       
       JSONObject ret = JSONObject.parseObject(res);
       String code = ret.getString("code");
       System.out.println("code=====" + code);
       result = ret.getString("message");
       System.out.println("message=====" + result);
       
       if (code.equals("200")) {
         String data = ret.getString("data");
         JSONObject datas = JSONObject.parseObject(data);
         String guid = datas.getString("guid");
         System.out.println("guid=====" + guid);
         guid = "qdguid='" + guid + "'";
         
         this.szqgorderDao.updatebygjz(szqgOrder.getId(), guid, timenew.newtime4());
       } 
       
       b++; }
 
     
     return result;
   }
   
   public String wlgjpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i;
     String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       
       System.out.println("masdsad============" + szqgOrder.getMailno());
       String gjz = "logisticsNo='" + szqgOrder.getMailno() + "'";
       
       JSONObject jsonObject = new JSONObject();
 
       
       List<SzqgAgreement> list = this.szqgagreementdao.findgjz("1");
       String PriKey = "";
       for (SzqgAgreement sam : list) {
         jsonObject.put("appid", sam.getAppid());
         jsonObject.put("version", sam.getVersion());
         jsonObject.put("merchantName", sam.getMerchantName());
         jsonObject.put("merchantId", sam.getMerchantId());
         
         jsonObject.put("commitTime", timenew.newtime());
 
         
         PriKey = sam.getPersonPriKey();
       } 
       
       jsonObject.put("logisticsCode", "3120980110");
       jsonObject.put("logisticsName", "申通快递有限公司");
       jsonObject.put("billNo", szqgOrder.getBillNo());
       jsonObject.put("logisticsNo", szqgOrder.getMailno());
 
       
       JSONArray pushTrackDataReqList = new JSONArray();
       
       List<SzqgwlGj> szqgwlGj = this.szqgWlgjDao.findgjz(gjz);
       for (SzqgwlGj wlgj : szqgwlGj) {
 
 
         
         JSONObject jo = new JSONObject();
         jo.put("logisticsLinkCode", wlgj.getLogisticsLinkCode());
         jo.put("logisticsLinkName", wlgj.getLogisticsLinkName());
         jo.put("logisticsTrajectory", wlgj.getLogisticsTrajectory());
         jo.put("generateTime", wlgj.getGenerateTime());
         jo.put("note", "note");
         pushTrackDataReqList.add(jo);
       } 
 
       
       jsonObject.put("trackListDtoList", pushTrackDataReqList);
       System.out.println("jsonObject=" + jsonObject.toString());
 
       
       ApolloSdk apolloSdk = new ApolloSdk();
       String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
       
       System.out.println("param=" + param);
       
       String url = "http://apollo.jieztech.com/aoa/api/aoa/publicServicePlatformFacade/logisticsTrackDeclare";
 
       
       String res = hcszqgpost.ZYPost(url, param);
       System.out.println("res=" + res);
       
       JSONObject ret = JSONObject.parseObject(res);
       String code = ret.getString("code");
       System.out.println("code=====" + code);
       result = ret.getString("message");
       System.out.println("message=====" + result);
       
       if (code.equals("200")) {
         String data = ret.getString("data");
         JSONObject datas = JSONObject.parseObject(data);
         String guid = datas.getString("guid");
         System.out.println("guid=====" + guid);
         guid = "qdguid='" + guid + "'";
       } 
 
       
       b++; }
 
 
     
     return result;
   }
   
   public String ptpush(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id))); b++; }
     
     return result;
   }
 
 
 
   
   public String shpush(String ids) throws Exception {
     System.out.println("shpushids=" + ids);
     
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i;
     String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       SzqgOrder szqgOrder = this.szqgorderDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
       String strategy = szqgOrder.getStrategy();
       System.out.println("strategy=" + strategy);
       
       SzqgStrategy szqgstrategy = this.szqgStrategyDao.findBySt(strategy);
       if (szqgstrategy == null) {
         System.out.println("无信息");
       } else {
         
         String OrderA = "";
         
         if (szqgOrder.getPayCode() == null || szqgOrder.getPayCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "payCode='" + szqgstrategy.getPayCode() + "',";
         }
         if (szqgOrder.getPayName() == null || szqgOrder.getPayName().equals("")) {
           OrderA = String.valueOf(OrderA) + "payName='" + szqgstrategy.getPayName() + "',";
         }
         
         if (szqgOrder.getLogisticsCode() == null || szqgOrder.getLogisticsCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "logisticsCode='" + szqgstrategy.getLogisticsCode() + "',";
         }
         if (szqgOrder.getLogisticsName() == null || szqgOrder.getLogisticsName().equals("")) {
           OrderA = String.valueOf(OrderA) + "logisticsName='" + szqgstrategy.getLogisticsName() + "',";
         }
         
         if (szqgOrder.getAbroadCo() == null || szqgOrder.getAbroadCo().equals("")) {
           OrderA = String.valueOf(OrderA) + "abroadCo='" + szqgstrategy.getAbroadCo() + "',";
         }
         if (szqgOrder.getAbroadCoName() == null || szqgOrder.getAbroadCoName().equals("")) {
           OrderA = String.valueOf(OrderA) + "abroadCoName='" + szqgstrategy.getAbroadCoName() + "',";
         }
         
         if (szqgOrder.getEbpCode() == null || szqgOrder.getEbpCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "ebpCode='" + szqgstrategy.getEbpCode() + "',";
         }
         if (szqgOrder.getEbpName() == null || szqgOrder.getEbpName().equals("")) {
           OrderA = String.valueOf(OrderA) + "ebpName='" + szqgstrategy.getEbpName() + "',";
         }
         
         if (szqgOrder.getProxyCode() == null || szqgOrder.getProxyCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "proxyCode='" + szqgstrategy.getProxyCode() + "',";
         }
         if (szqgOrder.getProxyName() == null || szqgOrder.getProxyName().equals("")) {
           OrderA = String.valueOf(OrderA) + "proxyName='" + szqgstrategy.getProxyName() + "',";
         }
         if (szqgOrder.getAssureCode() == null || szqgOrder.getAssureCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "assureCode='" + szqgstrategy.getAssureCode() + "',";
         }
         if (szqgOrder.getCustomsCode() == null || szqgOrder.getCustomsCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "customsCode='" + szqgstrategy.getCustomsCode() + "',";
         }
         if (szqgOrder.getPortCode() == null || szqgOrder.getPortCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "portCode='" + szqgstrategy.getPortCode() + "',";
         }
         
         if (szqgOrder.getAgentCode() == null || szqgOrder.getAgentCode().equals("")) {
           OrderA = String.valueOf(OrderA) + "agentCode='" + szqgstrategy.getAgentCode() + "',";
         }
         if (szqgOrder.getAgentName() == null || szqgOrder.getAgentName().equals("")) {
           OrderA = String.valueOf(OrderA) + "agentName='" + szqgstrategy.getAgentName() + "',";
         }
         
         if (OrderA.equals("")) {
           System.out.println("无变更信息");
         } else {
           String OrderB = "id='" + id + "'";
           OrderA = OrderA.substring(0, OrderA.length() - 1);
           this.szqgorderDao.updateAtB(OrderA, OrderB);
         }         
         System.out.println("strategy=" + strategy);
         List<SzqgOrderSku> oslist = this.szqgorderSkuDao.findById(szqgOrder.getOrderNo());
         
         int j = this.szqgorderSkuDao.batchUpdate(oslist);
         System.out.println("i==========================" + j);
       } 
       
       b++; }
 
     
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
     
     List<SzqgOrder> orderList = new ArrayList<>();
     List<SzqgOrderSku> orderskuList = new ArrayList<>();
     String txLogisticID = "";
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       
       int row = ((Integer)entry.getKey()).intValue() + 1;
       Object data1 = nowRowData[0];
       if (data1 == null || StringUtils.isEmpty(data1.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：快递公司编号没有输入！<br>");
         data1 = "";
       } 
       Object data2 = nowRowData[1];
       
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         data2 = "";
         
         message.append("导入第" + row + "行数据失败，失败原因：快件单号没有输入！<br>");
         
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人姓名没有输入！<br>");
         data3 = "";
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：身份证姓名没有输入！<br>");
         data4 = "";
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：身份证号码没有输入！<br>");
         data5 = "";
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人省份没有输入！<br>");
         data6 = "";
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人城市没有输入！<br>");
         data7 = "";
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人地区没有输入！<br>");
         data8 = "";
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人详细地址没有输入！<br>");
         data9 = "";
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人联系电话没有输入！<br>");
         data10 = "";
       } 
       
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：订单号没有输入！<br>");
         data11 = "";
       } 
       
       Object data12 = nowRowData[11];
       
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：重量没有输入！<br>");
         data12 = "";
       } 
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品货号(料号)没有输入！<br>");
         data13 = "";
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品品名没有输入！<br>");
         data14 = "";
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品单价没有输入！<br>");
         data15 = "";
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：商品数量没有输入！<br>");
         data16 = "";
       } 
       Object data17 = nowRowData[16];
       if (data17 == null || StringUtils.isEmpty(data17.toString().trim()))
       {
         data17 = "";
       }
       Object data18 = nowRowData[17];
       if (data18 == null || StringUtils.isEmpty(data18.toString().trim()))
       {
         data18 = "";
       }
       Object data19 = nowRowData[18];
       if (data19 == null || StringUtils.isEmpty(data19.toString().trim()))
       {
         data19 = "";
       }
       Object data20 = nowRowData[19];
       if (data20 == null || StringUtils.isEmpty(data20.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：产品编号没有输入！<br>");
         data20 = "";
       } 
       Object data21 = nowRowData[20];
       if (data21 == null || StringUtils.isEmpty(data21.toString().trim()))
       {
         data21 = "";
       }
       SzqgOrder szqgOrder = new SzqgOrder();
       szqgOrder.setCarrier(data1.toString().trim());
       szqgOrder.setMailno(data2.toString().trim());
       szqgOrder.setConsignee(data3.toString().trim());
       szqgOrder.setBuyerName(data4.toString().trim());
       szqgOrder.setBuyerIdNumber(data5.toString().trim().replaceAll("x", "X"));
       szqgOrder.setConsigneePrvince(data6.toString().trim());
       szqgOrder.setConsigneeCity(data7.toString().trim());
       szqgOrder.setConsigneeCounty(data8.toString().trim());
       szqgOrder.setConsigneeAddress(data9.toString().trim());
       szqgOrder.setConsigneeTelephone(data10.toString().trim());
       szqgOrder.setOrderNo(data11.toString().trim());
       szqgOrder.setGrossWeight(data12.toString());
       szqgOrder.setCountry(data17.toString());
       szqgOrder.setBillNo(data18.toString());
       szqgOrder.setVoyageNo(data19.toString());
       szqgOrder.setStrategy(data20.toString());
       szqgOrder.setPayTransactionId(data21.toString());
       szqgOrder.setUserId((new StringBuilder(String.valueOf(userId))).toString());
 
 
       
       if (!txLogisticID.equals(data11.toString())) {
 
         
         orderList.add(szqgOrder);
         txLogisticID = data11.toString().trim();
       } 
 
       
       SzqgOrderSku szqgOrderSku = new SzqgOrderSku();
       szqgOrderSku.setItemNo(data13.toString());
       szqgOrderSku.setPrice(data15.toString());
       szqgOrderSku.setQty(data16.toString());
       szqgOrderSku.setItemName(data14.toString());
       szqgOrderSku.setOrderNo(data11.toString());
 
       
       orderskuList.add(szqgOrderSku);
       
       if (message.toString().contains("导入")) {
         result.setMessage(message.toString());
         return result;
       }       
       if (message.toString().length() > 0) {
         result.setMessage(message.toString());
         return result;
       } 
     } 
 
     
     int count2 = this.szqgorderDao.batchInsert(orderList);
     int count = this.szqgOrderSkuDao.batchInsert(orderskuList);
     result.setMessage("导入成功" + count + "条！");
     System.out.println("count2=" + count2);
     System.out.println("count=" + count);
     return result;
   }
 }


