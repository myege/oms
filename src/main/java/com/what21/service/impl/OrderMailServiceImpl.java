 package com.what21.service.impl;
 
 import com.UMFapi2;
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.iwilley.b1ec2.sample.ShopOrderCreateSample;
 import com.lycheepay.gateway.client.CbcsService;
 import com.lycheepay.gateway.client.KftService;
 import com.lycheepay.gateway.client.dto.cbcs.CustomsDeclaredQueryRespDTO;
 import com.lycheepay.gateway.client.dto.cbcs.CustomsDeclaredReqDTO;
 import com.lycheepay.gateway.client.dto.cbcs.ReportCustomInfoDTO;
 import com.lycheepay.gateway.client.security.KeystoreSignProvider;
 import com.lycheepay.gateway.client.security.SignProvider;
 import com.umf.base.ReqUMF;
 import com.umf.base.rest.APIContext;
 import com.what21.dao.BuyerLimitDao;
 import com.what21.dao.CompanyDao;
 import com.what21.dao.CompanyforzyDao;
 import com.what21.dao.GoodsAcceptDao;
 import com.what21.dao.ItemForZyDao;
 import com.what21.dao.LogFileDao;
 import com.what21.dao.LogFileSkuDao;
 import com.what21.dao.OrderAcceptDao;
 import com.what21.dao.OrderBatchDao;
 import com.what21.dao.OrderDsDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.dao.OrderMailSkuDao;
 import com.what21.model.Companyforzy;
 import com.what21.model.EchattsBonded;
 import com.what21.model.FindBytxLogisticIDForYtoExcel;
 import com.what21.model.GoodsAccept;
 import com.what21.model.ItemForZy;
 import com.what21.model.LogFile;
 import com.what21.model.OrderAccept;
 import com.what21.model.OrderBatch;
 import com.what21.model.OrderDs;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderMailForExport;
 import com.what21.model.OrderMailPush;
 import com.what21.model.OrderMailPushSku;
 import com.what21.model.OrderMailSku;
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.service.OrderMailService;
 import com.what21.util.GeneralResult;
 import com.what21.util.MD5Util;
 import com.what21.util.MailExcelUtil;
 import com.what21.util.PayNumberUtil;
 import com.what21.util.StringUtil;
 import com.what21.util.timenew;
 import com.zt.kjybd.CheckPhone;
 import com.zt.kjybd.GetBillcodeforSTO;
 import com.zt.kjybd.GetWLGJ;
 import com.zt.kjybd.HTOydbg;
 import com.zt.kjybd.MatchMailNo;
 import com.zt.kjybd.Push;
 import com.zt.kjybd.PushToCzNew;
 import com.zt.kjybd.PushToSTO;
 import com.zt.kjybd.PushToTTK;
 import com.zt.kjybd.PushToYf;
 import com.zt.kjybd.PushToZTO;
 import com.zt.kjybd.PushtoBG_yd;
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
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.LinkedList;
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
import org.apache.commons.codec.binary.Base64;
 @Service
 @Transactional
 public class OrderMailServiceImpl
   implements OrderMailService
 {
   @Autowired
   public OrderMailDao orderMailDao;
   @Autowired
   public OrderBatchDao orderBatchDao;
   @Autowired
   private ItemForZyDao itemForZyDao;
   @Autowired
   public OrderMailSkuDao orderMailSkuDao;
   @Autowired
   private OrderAcceptDao orderAcceptDao;
   @Autowired
   private GoodsAcceptDao goodsAcceptDao;
   @Autowired
   public LogFileSkuDao logFileSkuDao;
   @Autowired
   public LogFileDao logFileDao;
   @Autowired
   public CompanyforzyDao companyforzyDao;
   @Autowired
   private BuyerLimitDao buyerLimitDao;
   @Autowired
   private CompanyDao companyDao;
   @Autowired
   public OrderDsDao orderDsDao;
   private static CbcsService service;
   private static KftService kftService;
   
   public List<OrderMail> findAll(Map<String, Object> map) {
     return this.orderMailDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.orderMailDao.count(pageMap);
   }
   
   String BILL_CUSTOMS_CODE = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_CUSTOMS_CODE.getValue());
 
 
   
   String BFBMERCHANTID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BFBMERCHANTID.getValue());
   
   String BFBMERCHANTPATH = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BFBMERCHANTPATH.getValue());
   String merchantId = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.MERCHANTID.getValue());
   String pftaes = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.PFTAES.getValue());
   String custom = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.CUSTOM.getValue());
   private static String crtPath = "C://cert_2d59.crt";
   private static String p8Path = "C://52489_.key.p8";
   private static String UMFp8Path = "C://52489_.key.p8";
   
   public GeneralResult importOrder(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = MailExcelUtil.read(path);
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
       String txLogisticID = "";
       int row = ((Integer)entry.getKey()).intValue() + 1;
       Object data1 = nowRowData[0];
       if (data1 == null || StringUtils.isEmpty(data1.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：快递公司编号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         
         message.append("导入第" + row + "行数据失败，失败原因：快件单号没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人姓名没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：身份证姓名没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：身份证号码没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人省份没有输入！<br>");
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人城市没有输入！<br>");
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人地区没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人详细地址没有输入！<br>");
         continue;
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人邮编没有输入！<br>");
         continue;
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人国家没有输入！<br>");
         continue;
       } 
       Object data12 = nowRowData[11];
       
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人联系电话没有输入！<br>");
         continue;
       } 
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品重量没有输入！<br>");
         continue;
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品名称没有输入！<br>");
         continue;
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品品名没有输入！<br>");
         continue;
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品单价没有输入！<br>");
         continue;
       } 
       Object data17 = nowRowData[16];
       if (data17 == null || StringUtils.isEmpty(data17.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品数量没有输入！<br>");
         continue;
       } 
       Object data18 = nowRowData[17];
       if (data18 == null || StringUtils.isEmpty(data18.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品总价没有输入！<br>");
         continue;
       } 
       Object data19 = nowRowData[18];
       if (data19 == null || StringUtils.isEmpty(data19.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品规格没有输入！<br>");
         continue;
       } 
       Object data20 = nowRowData[19];
       if (data20 == null || StringUtils.isEmpty(data20.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：'物品计量单位名称没有输入！<br>");
 
 
 
         
         continue;
       } 
 
 
       
       Object data21 = nowRowData[20];
       if (data21 == null || StringUtils.isEmpty(data21.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：'物品HS没有输入！<br>");
         continue;
       } 
       Object data22 = nowRowData[21];
       if (data22 == null || StringUtils.isEmpty(data22.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：起运国代码没有输入！<br>");
         
         continue;
       } 
       
       Object data23 = nowRowData[22];
       
       Object data24 = nowRowData[23];
       
       Object data25 = nowRowData[24];
       if (data25 != null && 
         StringUtils.isNotEmpty(data25.toString().trim())) {
         txLogisticID = data25.toString().trim();
       } else {
         message.append("导入第" + row + "行数据失败，失败原因：客户订单号没有输入！<br>");
         
         continue;
       } 
       OrderMail isData = this.orderMailDao.findByTxLogisticID(txLogisticID);
       if (isData == null) {
         OrderMail orderMail = new OrderMail();
         orderMail.setCarrier(data1.toString().trim());
         orderMail.setMailNo(data2.toString().trim());
         orderMail.setReceiveMan(data3.toString().trim());
         orderMail.setBuyerName(data4.toString().trim());
         orderMail.setBuyerIdNumber(data5.toString().trim());
         orderMail.setReceiveProvince(data6.toString().trim());
         orderMail.setReceiveCity(data7.toString().trim());
         orderMail.setReceiveCounty(data8.toString().trim());
         orderMail.setReceiveManAddress(data9.toString().trim());
         orderMail.setReceivecode(data10.toString().trim());
         orderMail.setReceiveCountr(data11.toString().trim());
         orderMail.setReceiveManPhone(data12.toString().trim());
         orderMail.setItemWeight(Double.valueOf(data13.toString()));
         orderMail.setItemName(data14.toString().trim());
         orderMail.setClassname(data15.toString().trim());
         
         orderMail.setItemCount(Integer.valueOf(data17.toString()));
         orderMail.setWorth(Double.valueOf(data18.toString()));
         orderMail.setStandard(data19.toString().trim());
         orderMail.setUnitname(data20.toString().trim());
         
         orderMail.setHs(data21.toString().trim());
         orderMail.setTradeCountry(data22.toString().trim());
         orderMail.setDestinationPort(data22.toString().trim());
         orderMail.setTxLogisticID(data25.toString());
         
         if (data23 != null) {
           orderMail.setTotalMailNo(data23.toString().trim());
         }
         
         if (data24 != null) {
           orderMail.setFightNumber(data24.toString().trim());
         }
         orderMail.setUserId(userId);
         orderMail.setIspost(0);
         orderMail.setIsCustoms(0);
         orderMail.setAuditstatus(0);
         
         this.orderMailDao.insert(orderMail);
       } else {
         Integer itemCount = Integer.valueOf(isData.getItemCount().intValue() + 
             Integer.parseInt(data17.toString()));
         Double itemWeight = Double.valueOf(isData.getItemWeight().doubleValue() + 
             Double.valueOf(data13.toString()).doubleValue());
         Double worth = Double.valueOf(isData.getWorth().doubleValue() + 
             Double.valueOf(data18.toString()).doubleValue());
         isData.setItemCount(itemCount);
         isData.setItemWeight(itemWeight);
         isData.setWorth(worth);
         this.orderMailDao.updateCountAndWeight(isData);
       } 
       OrderMailSku orderMailSku = new OrderMailSku();
       orderMailSku.setTxLogisticID(txLogisticID);
       orderMailSku.setItemsku("ZY" + data21.toString().trim());
       orderMailSku.setItemWeight(Double.valueOf(data13.toString()));
       orderMailSku.setItemName(data14.toString().trim().replace("'", ""));
       orderMailSku.setUnitPrice(Double.valueOf(data16.toString()));
       orderMailSku.setItemCount(Integer.valueOf(Integer.parseInt(data17.toString())));
       orderMailSku.setAllPrice(Double.valueOf(data18.toString()));
       this.orderMailSkuDao.insert(orderMailSku);
       
       successData++;
     } 
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + 
         "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
 
   
   public GeneralResult importOrderNew(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = MailExcelUtil.read(path);
     
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     boolean firstRow = true;
     StringBuffer message = new StringBuffer();
     Map<String, OrderMail> orderMailMap = new HashMap<>();
 
     
     List<OrderMailSku> omsList = new LinkedList<>();
     
     SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
     String batchNumber = "ZY" + df.format(new Date());
     Double totals = Double.valueOf(0.0D);
     
     Map<String, String> itemSkuMap = new HashMap<>();
     List<String> itemSkuList = new ArrayList<>();
     int j = 1;
     String strC = "%04d";
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       String txLogisticID = "";
       int row = ((Integer)entry.getKey()).intValue() + 1;
       Object data1 = nowRowData[0];
       if (data1 == null || StringUtils.isEmpty(data1.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：快递公司编号没有输入！<br>");
         data1 = "";
       } 
       Object data2 = nowRowData[1];
       
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         data2 = "";
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
         message.append("导入第" + row + "行数据失败，失败原因：收件人邮编没有输入！<br>");
         data10 = "";
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人国家没有输入！<br>");
         data11 = "";
       } 
       Object data12 = nowRowData[11];
       
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：收件人联系电话没有输入！<br>");
         data12 = "";
       }       
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品重量没有输入！<br>");
         data13 = Integer.valueOf(0);
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品名称没有输入！<br>");
         data14 = "";
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品品名没有输入！<br>");
         data15 = "";
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品单价没有输入！<br>");
         data16 = Integer.valueOf(0);
       } 
       Object data17 = nowRowData[16];
       if (data17 == null || StringUtils.isEmpty(data17.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品数量没有输入！<br>");
         data17 = Integer.valueOf(0);
       } 
       Object data18 = nowRowData[17];
       if (data18 == null || StringUtils.isEmpty(data18.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品总价没有输入！<br>");
         data18 = Integer.valueOf(0);
       } 
       Object data19 = nowRowData[18];
       if (data19 == null || StringUtils.isEmpty(data19.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：物品规格没有输入！<br>");
         data19 = "";
       } 
       Object data20 = nowRowData[19];
       if (data20 == null || StringUtils.isEmpty(data20.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：'物品计量单位名称没有输入！<br>");
         data20 = "";
       } 
       
       Object data21 = nowRowData[20];
       if (data21 == null || StringUtils.isEmpty(data21.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：'物品HS没有输入！<br>");
         data21 = "";
       } 
       Object data22 = nowRowData[21];
       if (data22 == null || StringUtils.isEmpty(data22.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：起运国代码没有输入！<br>");
         data22 = "";
       } 
 
       
       Object data23 = nowRowData[22];
       
       Object data24 = nowRowData[23];
       
       Object data25 = nowRowData[24];
       if (data25 != null && StringUtils.isNotEmpty(data25.toString().trim())) {
         txLogisticID = data25.toString().trim();
       } else {
         message.append("导入第" + row + "行数据失败，失败原因：客户订单号没有输入！<br>");
       } 
       
       Object data26 = nowRowData[25];
       
       BigDecimal itemCount = new BigDecimal(data17.toString().trim());
       BigDecimal unitPrice = new BigDecimal(data16.toString().trim());
       BigDecimal allPrice = new BigDecimal(data18.toString().trim());
       BigDecimal price = unitPrice.multiply(itemCount);
       
       if (!allPrice.equals(price)) {
         message.append("导入第" + row + "行数据失败，失败原因：物品单价乘以物品数量不等于物品总价！<br>");
       }
       
       OrderMail orderMail = new OrderMail();
       orderMail.setMerchantNum(data26.toString().trim());
       orderMail.setCarrier(data1.toString().trim());
       orderMail.setMailNo(data2.toString().trim());
       orderMail.setReceiveMan(data3.toString().trim());
       orderMail.setBuyerName(data4.toString().trim());
       orderMail.setBuyerIdNumber(data5.toString().trim().replaceAll("x", "X"));
       orderMail.setReceiveProvince(data6.toString().trim());
       orderMail.setReceiveCity(data7.toString().trim());
       orderMail.setReceiveCounty(data8.toString().trim());
       orderMail.setReceiveManAddress(data9.toString().trim());
       orderMail.setReceivecode(data10.toString().trim());
       orderMail.setReceiveCountr(data11.toString().trim());
       orderMail.setReceiveManPhone(data12.toString().trim());
       
       String wpmc = data14.toString().trim();
       wpmc = wpmc.replaceAll("(?:成人|蜂蜜|奶酪|奶油|黄油|鲜奶|酸奶|鲜蛋|皮蛋|蛋液|咸蛋|蛋壳|提高免疫力|治疗|预防|控制|止痛片|咳嗽|止咳|孕妇|染发剂)", "");
       wpmc = wpmc.replaceAll("蛋黄酱", "BBQ酱");
       wpmc = wpmc.replaceAll("前列腺片", "番茄红素");
       wpmc = wpmc.replaceAll("蜂胶", "蜂胶胶囊");
       wpmc = wpmc.replaceAll("鱼油", "复合鱼油");
       wpmc = wpmc.replaceAll("燕窝", "罐头燕窝");
       wpmc = wpmc.replaceAll("蜂蜜", "果泥");
       wpmc = wpmc.replaceAll("护眼", "蓝莓");
       wpmc = wpmc.replaceAll("糖浆", "液体钙");
       wpmc = wpmc.replaceAll("关节灵", "维骨力");
       wpmc = wpmc.replaceAll("(?:指甲油|卸甲水|香水)", "精油");
       wpmc = wpmc.replaceAll("(?:睡眠|护肝|感冒|护心)", "维生素");
       orderMail.setItemName(wpmc);
       orderMail.setClassname(data15.toString().trim());
 
       
       orderMail.setStandard(data19.toString().trim());
       orderMail.setUnitname(data20.toString().trim());
       orderMail.setHs(data21.toString().trim());
       orderMail.setTradeCountry(data22.toString().trim());
       orderMail.setDestinationPort(data22.toString().trim());
       orderMail.setTxLogisticID(txLogisticID);
       
       if (data23 != null) {
         orderMail.setTotalMailNo(data23.toString().trim());
       }
       
       if (data24 != null) {
         orderMail.setFightNumber(data24.toString().trim());
       }
       
       orderMail.setUserId(userId);
       orderMail.setIspost(0);
       orderMail.setIsCustoms(0);
       orderMail.setAuditstatus(0);
       orderMail.setBatchNumber(batchNumber);
       OrderMail om = orderMailMap.get(txLogisticID);
       
       totals = Double.valueOf(totals.doubleValue() + Double.valueOf(data18.toString().trim()).doubleValue());
       
       if (om != null) {
         om.setItemCount(Integer.valueOf(om.getItemCount().intValue() + Integer.parseInt(data17.toString().trim())));
         
         om.setWorth(Double.valueOf(om.getWorth().doubleValue() + Double.valueOf(data18.toString().trim()).doubleValue()));
         om.setItemWeight(Double.valueOf(om.getItemWeight().doubleValue() + Double.valueOf(data13.toString().trim()).doubleValue()));
         if (om.getWorth().doubleValue() > 5000.0D) {
           message.append("导入第" + row + "行数据失败，失败原因：金额超5000！<br>");
         }
         orderMailMap.put(txLogisticID, om);
       } else {
         orderMail.setItemCount(Integer.valueOf(Integer.parseInt(data17.toString().trim())));
         orderMail.setWorth(Double.valueOf(data18.toString().trim()));
         if (Double.valueOf(data18.toString().trim()).doubleValue() >= 5000.0D) {
           message.append("导入第" + row + "行数据失败，失败原因：金额超5000！<br>");
         }
         orderMail.setItemWeight(Double.valueOf(data13.toString().trim()));
         orderMailMap.put(txLogisticID, orderMail);
       } 
       
       OrderMailSku orderMailSku = new OrderMailSku();
       orderMailSku.setTxLogisticID(txLogisticID);
       String itemSku = "ZY" + data21.toString().trim();
       orderMailSku.setItemsku(itemSku);
       orderMailSku.setItemWeight(Double.valueOf(data13.toString().trim()));
       orderMailSku.setItemName(wpmc);
       orderMailSku.setItemClass(data22.toString().trim());
       orderMailSku.setUnitPrice(Double.valueOf(data16.toString().trim()));
       orderMailSku.setItemCount(Integer.valueOf(Integer.parseInt(data17.toString().trim())));
       orderMailSku.setAllPrice(Double.valueOf(data18.toString().trim()));
       
       orderMailSku.setBatchNumber(String.format("%05d", new Object[] { Integer.valueOf(row) }));
       
       orderMailSku.setStandard(data19.toString().trim());
       orderMailSku.setUnitname(data20.toString().trim());
       
       omsList.add(orderMailSku);
       
       if (data21.toString().length() > 0) {
         if (!"1".equals(itemSkuMap.get(itemSku))) {
           itemSkuList.add(itemSku);
         }
         itemSkuMap.put(itemSku, "1");
       } 
     } 
     if (itemSkuList != null && itemSkuList.size() > 0) {
       List<String> retItemSkuList = this.itemForZyDao.findByItemSkuList(itemSkuList);
       itemSkuList.removeAll(retItemSkuList);
       if (itemSkuList.size() > 0) {
         String hsCode = "";
         for (String str : itemSkuList) {
           str = str.substring(2);
           hsCode = String.valueOf(hsCode) + str + "，";
         } 
         hsCode = hsCode.substring(0, hsCode.length() - 1);
         message.append("上传失败，物品HS(" + hsCode + ")没有匹配到");
       } 
     } 
 
     
     Object[] omArr = orderMailMap.values().toArray();
     List<OrderMail> retList = this.orderMailDao.findByOmArr(omArr);
     if (retList != null && retList.size() > 0) {
       String retTxLogisticID = "";
       for (OrderMail retOm : retList) {
         retTxLogisticID = String.valueOf(retTxLogisticID) + retOm.getTxLogisticID() + ",";
       }
       retTxLogisticID = retTxLogisticID.substring(0, retTxLogisticID.length() - 1);
       message.append("导入数据失败，失败原因：订单号（" + retTxLogisticID + "）已存在！<br>");
     } 
     
     if (message.toString().length() > 0) {
       result.setMessage(message.toString());
       return result;
     } 
     
     int mailCount = this.orderMailDao.batchInsert(omArr);
     int count = this.orderMailSkuDao.batchInsert(omsList);
     
     OrderBatch orderBatch = new OrderBatch();
     orderBatch.setBatchNumber(batchNumber);
     orderBatch.setLeadOrderNumber(mailCount);
     orderBatch.setLeadOrderTotal(totals);
     Double leadOrderTax = Double.valueOf(totals.doubleValue() * 0.119D);
     orderBatch.setLeadOrderTax(leadOrderTax);
     this.orderBatchDao.insertBatch(orderBatch);
     result.setMessage("导入成功" + count + "条！");
     return result;
   }
 
   
   public int deleteOrderMail(String[] ids) {
     int result = -1;
     result = this.orderMailDao.delete(ids);
     return result;
   }
 
   
   public int updateAuditstatus(String paramJson) throws Exception {
     JSONObject jSONObject = JSONObject.parseObject(paramJson);
     JSONArray orderJSONArray = jSONObject.getJSONArray("paramList");
     int result = -1;
     for (int i = 0; i < orderJSONArray.size(); i++) {
       JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
       Integer id = orderJSONObject.getInteger("id");
       String mailNo = orderJSONObject.getString("mailNo");
       String txLogisticID = orderJSONObject.getString("txLogisticID");
       String worth2 = orderJSONObject.getString("worth");
       String name = orderJSONObject.getString("buyerName");
       String nameId = orderJSONObject.getString("buyerIdNumber");
       
       OrderMail orderMail = new OrderMail();
       orderMail.setId(id.intValue());
       if ("undefined".equals(mailNo) || "".equals(mailNo.trim())) {
         orderMail.setAuditstatus(1);
       } else {
         orderMail.setAuditstatus(2);
         List<OrderMailSku> list = this.orderMailSkuDao
           .findById(txLogisticID);
         BigDecimal allTax = new BigDecimal("0");
         for (OrderMailSku orderMailSku : list) {
           ItemForZy itemForZy = this.itemForZyDao
             .findByItemSku(orderMailSku.getItemsku());
           BigDecimal vat = itemForZy.getVat();
           
           if (itemForZy == null) {
             orderMail.setAuditstatus(3);
             break;
           } 
           String hscode = itemForZy.getHscode();
           if (hscode.startsWith("3303") || hscode.startsWith("3304")) {
             if (hscode.equals("3304100013") || hscode.equals("3304100093") || hscode.equals("3304200013") || hscode.equals("3304200093") || 
               hscode.equals("3304300003") || hscode.equals("3304990041") || hscode.equals("3304990049")) {
               if (orderMailSku.getUnitPrice().doubleValue() < 15.0D) {
                 vat = new BigDecimal("0");
               
               }
             }
             else if (orderMailSku.getUnitPrice().doubleValue() / orderMailSku.getItemWeight().doubleValue() < 10.0D) {
               vat = new BigDecimal("0");
             } 
           }
 
 
 
           
           BigDecimal allprice = new BigDecimal(orderMailSku
               .getAllPrice().toString());
           BigDecimal excise = itemForZy.getExcise();
           
           BigDecimal bd = new BigDecimal("1");
           BigDecimal bd4 = new BigDecimal("0.7");
           BigDecimal exciseB = allprice.divide(
               bd.subtract(excise), 2, 
               4).multiply(excise);
           BigDecimal vatB = allprice.add(exciseB).multiply(vat);
           BigDecimal zhs = exciseB.add(vatB).multiply(bd4);
           if (orderMailSku.getAllPrice().doubleValue() > 2500.0D) {
             zhs = exciseB.add(vatB);
           }
           orderMailSku.setExcise(exciseB);
           orderMailSku.setVat(vatB);
           orderMailSku.setConsolidatedTax(zhs);
           this.orderMailSkuDao.updateData(orderMailSku);
           allTax = allTax.add(zhs);
         } 
 
         
         allTax = allTax.setScale(2, 4);
         orderMail.setCustomsTax(allTax.toString());
       } 
 
 
       
       OrderMail mail = this.orderMailDao.findOneById(id.intValue());
       LogFile tax = new LogFile();
       tax.setUserId(mail.getUserId());
       
       result = this.orderMailDao.updateAuditstatus(orderMail);
     } 
     return result;
   }
 
 
 
   
   public int updateExpressParam(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderMail orderMail = this.orderMailDao.findByIdToBG(
           Integer.valueOf(Integer.parseInt(id)));
       if (orderMail.getCarrier().equals("HTO")) {
         Element root = DocumentHelper.createElement("PrintRequest");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("xmlns", "ems=http://express.800best.com");
         root.addElement("deliveryConfirm").addText("false");
         
         Element element1 = root.addElement("EDIPrintDetailList");
         element1.addElement("sendMan").addText("保税物流中心");
         element1.addElement("sendManPhone").addText("wu");
         element1.addElement("sendManAddress").addText("保税物流中心");
         element1.addElement("sendPostcode").addText("");
         element1.addElement("sendProvince").addText("浙江省");
         element1.addElement("sendCity").addText("杭州市");
         element1.addElement("sendCounty").addText("萧山区");
         element1.addElement("receiveMan").addText(orderMail.getReceiveMan());
         element1.addElement("receiveManPhone").addText(orderMail.getReceiveManPhone());
         element1.addElement("receiveManAddress").addText(orderMail.getReceiveManAddress());
         element1.addElement("receiveProvince").addText(orderMail.getReceiveProvince());
         element1.addElement("receiveCity").addText(orderMail.getReceiveCity());
         element1.addElement("receiveCounty").addText(orderMail.getReceiveCounty());
         element1.addElement("txLogisticID").addText(orderMail.getTxLogisticID());
         element1.addElement("itemName").addText(orderMail.getItemName());
         element1.addElement("itemWeight").addText(orderMail.getItemWeight().toString());
         element1.addElement("itemCount").addText(orderMail.getItemCount().toString());
         element1.addElement("remark").addText("备注");
         element1.addElement("sortingcode").addText("");
 
 
         
         MatchMailNo.hqmd_zy(document.asXML(), orderMail);
       }       
       if (StringUtils.isNotEmpty(orderMail.getMailNo())) {
         orderMail.setAuditstatus(2);
         result = this.orderMailDao.updateExpressParam(orderMail);
       }  b++; }
     
     return result;
   }
 
 
   
   public int updateIspost(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderMailPush orderMailPush = this.orderMailDao.findById(
           Integer.valueOf(Integer.parseInt(id)));
       orderMailPush.setConsignee(orderMailPush.getBuyerName());
       List<OrderMailPushSku> goodsList = this.orderMailSkuDao
         .findByTxLogisticID(orderMailPush.getOrderSn());
       
       orderMailPush.setGoodsList(goodsList);
       
       boolean mark = CheckPhone.isPhone(orderMailPush.getTel());
       if (!mark) {
         orderMailPush.setTel("13588508633");
       }
       
       boolean mark2 = CheckPhone.isPhone(orderMailPush.getPurchaserTelNumber());
       if (!mark2) {
         orderMailPush.setPurchaserTelNumber("13588508633");
       }
 
       
       String strJson = JSON.toJSONString(orderMailPush);
       System.out.println("--------->" + strJson);
       strJson = "[" + strJson + "]";
       String newstr = "";
       try {
         newstr = Base64.encodeBase64String(strJson.getBytes("utf-8"));
       } catch (Exception e) {
         
         e.printStackTrace();
       } 
       String parm = "auth=6d1df3f2cb5f571ecc1d413e02785331&data=" + 
         URLEncoder.encode(newstr);
       String resout = PushtoWQ.sendPost2(
           "http://www.haidai5.com/tools/import.php", parm).replace(
           "\"", "");
       if (resout.contains("successNum:1")) {
         OrderMail orderMail = new OrderMail();
         orderMail.setId(Integer.parseInt(id));
         orderMail.setIspost(1);
         result = this.orderMailDao.updateIspost(orderMail);
       }  b++; }
     
     return result;
   }
 
   
   public int updateIsPushCz(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderMail orderMail = this.orderMailDao.findByIdToBG(
           Integer.valueOf(Integer.parseInt(id)));
       List<OrderMailSku> goodsList = this.orderMailSkuDao.findById(orderMail
           .getTxLogisticID());
 
       
       String res = Push.xml(orderMail, goodsList);
       
       if (res.contains("<success>true</success>"))
         result = this.orderMailDao.updateIsPushCz(Integer.valueOf(Integer.parseInt(id))); 
       b++; }
     
     return result;
   }
   
   public String updateIsPushCzNew(String totalMailNo, String cpNo) {
     String result = "推送失败!";
     try {
       List<OrderMail> orderMailList = this.orderMailDao.findByTotalMailNoToCz(totalMailNo);
       if (orderMailList != null && orderMailList.size() > 0) {
         String ids = "";
         for (OrderMail orderMail : orderMailList) {
           ids = String.valueOf(ids) + orderMail.getTxLogisticID() + ",";
         }
         ids = ids.substring(0, ids.length() - 1);
         String[] idArr = ids.split(",");
         List<OrderMailSku> orderMailSkuList = this.orderMailSkuDao.findByIdArrToCz(idArr);
         System.out.println(orderMailSkuList.size());
         Map<String, List<OrderMailSku>> omsMap = new HashMap<>();
         for (OrderMailSku orderMailSku : orderMailSkuList) {
           List<OrderMailSku> omsList = omsMap.get(orderMailSku.getTxLogisticID());
           if (omsList != null && omsList.size() > 0) {
             omsList.add(orderMailSku);
             omsMap.put(orderMailSku.getTxLogisticID(), omsList); continue;
           } 
           List<OrderMailSku> omsListNew = new ArrayList<>();
           omsListNew.add(orderMailSku);
           omsMap.put(orderMailSku.getTxLogisticID(), omsListNew);
         } 
 
 
         
         JSONObject mainJson = new JSONObject();
         mainJson.put("contactPerson", "张玉松");
         mainJson.put("contactPhone", "13605806456");
         mainJson.put("declareId", "杭州海仓科技有限公司");
         mainJson.put("wayBillNo", totalMailNo);
         mainJson.put("palletsNum", Integer.valueOf(1));
         mainJson.put("logisticsId", "杭州海仓科技有限公司");
         mainJson.put("logisticsCompany", "中外运跨境电商物流有限公司杭州分公司");
         mainJson.put("carNo", cpNo);
         mainJson.put("flightNo", ((OrderMail)orderMailList.get(0)).getFightNumber());
         mainJson.put("arrivalDate", "2020-11-04 10:29:00");
         
         JSONArray orderJsonArr = new JSONArray();
         for (OrderMail orderMail : orderMailList) {
           JSONObject orderJson = new JSONObject();
           orderJson.put("companyCode", "330166K00K");
           orderJson.put("lpNo", orderMail.getTxLogisticID());
           orderJson.put("localWaybillNo", orderMail.getMailNo());
           orderJson.put("companyName", "杭州海仓科技有限公司");
           orderJson.put("orderUserName", orderMail.getReceiveMan());
           orderJson.put("orderCountry", orderMail.getReceiveCountr());
           orderJson.put("orderProvince", orderMail.getReceiveProvince());
           orderJson.put("orderCity", orderMail.getReceiveCity());
           orderJson.put("orderDistrict", orderMail.getReceiveCounty());
           orderJson.put("orderContact", orderMail.getReceiveManPhone());
           orderJson.put("orderAddress", orderMail.getReceiveManAddress());
 
           
           JSONArray goodsJsonArr = new JSONArray();
           List<OrderMailSku> omsList = omsMap.get(orderMail.getTxLogisticID());
           for (OrderMailSku orderMailSku : omsList) {
             JSONObject goodsJson = new JSONObject();
             goodsJson.put("goodsSeq", Integer.valueOf(orderMailSku.getId()));
             goodsJson.put("goodsName", orderMailSku.getItemName());
             goodsJson.put("goodsNum", orderMailSku.getItemCount());
             goodsJson.put("goodsPrice", orderMailSku.getUnitPrice());
             goodsJsonArr.add(goodsJson);
           } 
           orderJson.put("goodsList", goodsJsonArr);
           
           orderJsonArr.add(orderJson);
         } 
         mainJson.put("inoutParkDataDetailList", orderJsonArr);
         System.out.println("json=" + mainJson.toString());
         
         String token = PushToCzNew.login();
 
         
         String pushOrderUrl = "http://183.129.233.106:9081/kmb/a/api/inoutparkOrder?token=" + token;
         String ret = PushToCzNew.sendPost(pushOrderUrl, mainJson.toString());
         if (StringUtil.isNotEmpty(ret).booleanValue()) {
           JSONObject retJson = JSONObject.parseObject(ret);
           Integer status = retJson.getInteger("status");
           if (status.intValue() == 201) {
             Integer goodsNum = retJson.getInteger("goodsNum");
             Integer packsNum = retJson.getInteger("packsNum");
             if (orderMailList.size() == packsNum.intValue() && orderMailSkuList.size() == goodsNum.intValue()) {
               this.orderMailDao.batchUpdateIsPushCz(orderMailList);
               result = "推送成功!";
             } else {
               JSONObject failGoods = retJson.getJSONObject("failGoods");
               result = "推送失败!失败单号如下所示:\n" + failGoods.toJSONString();
             } 
           } else if (status.intValue() == 202) {
             result = "场站系统数据校验失败!";
           } else if (status.intValue() == 203) {
             result = "场站系统服务器异常!";
           } 
         } 
       } else {
         result = "该提运单号相关订单已全部推送或无相关订单!";
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return result;
   }
   
   public String pushCzStatus(String totalMailNo) {
     String result = "推送失败!";
     try {
       List<OrderMail> orderMailList = this.orderMailDao.findReturnCode(totalMailNo);
       if (orderMailList != null && orderMailList.size() > 0) {
         
         JSONObject mainJson = new JSONObject();
         mainJson.put("wayBillNo", totalMailNo);
         JSONArray orderJsonArr = new JSONArray();
         for (OrderMail orderMail : orderMailList) {
           JSONObject orderJson = new JSONObject();
           orderJson.put("lpNo", orderMail.getTxLogisticID());
           orderJson.put("localWaybillNo", orderMail.getMailNo());
           String customsFlag = "";
           if (orderMail.getReturncode().intValue() == 3) {
             customsFlag = "500";
           } else if (orderMail.getReturncode().intValue() == 9) {
             customsFlag = "800";
           } else {
             continue;
           } 
           orderJson.put("customsFlag", customsFlag);
           orderJsonArr.add(orderJson);
         } 
         mainJson.put("inoutParkDataDetailList", orderJsonArr);
 
         
         String token = PushToCzNew.login();
 
         
         String pushStatusUrl = "http://183.129.233.106:9081/kmb/a/api/inoutparkOrderStatus?token=" + token;
         String ret = PushToCzNew.sendPost(pushStatusUrl, mainJson.toString());
         if (StringUtil.isNotEmpty(ret).booleanValue()) {
           JSONObject retJson = JSONObject.parseObject(ret);
           Integer status = retJson.getInteger("status");
           if (status.intValue() == 201) {
             result = "推送成功!";
           }
         } 
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return result;
   }
   
   public int updateIsCustoms(String ids) {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       try {
         OrderMail orderMail = this.orderMailDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
         if ("HTO".equals(orderMail.getCarrier())) {
           JSONObject ydJson = new JSONObject();
           ydJson.put("operationType", "1");
           JSONArray entityList = new JSONArray();
           JSONObject entity = new JSONObject();
           entity.put("tradeId", orderMail.getTxLogisticID());
           entity.put("packageNo", "");
           entity.put("totalExpressNo", "");
           entity.put("expressNo", orderMail.getMailNo());
           entity.put("expressCompanyCode", "");
           entity.put("senderCompany", "");
           entity.put("senderName", "侨缘贸易");
           entity.put("senderPhone", "057153686606");
           entity.put("senderProvince", "浙江省");
           entity.put("senderCity", "杭州市");
           entity.put("senderCountry", "萧山区");
           entity.put("senderAddress", "B型保税区");
           entity.put("senderZip", "");
           entity.put("receiverName", orderMail.getReceiveMan());
           entity.put("receiverId", "");
           entity.put("receiverProvince", orderMail.getReceiveProvince());
           entity.put("receiverCity", orderMail.getReceiveCity());
           entity.put("receiverCountry", orderMail.getReceiveCounty());
           entity.put("receiverAddress", orderMail.getReceiveManAddress());
           entity.put("receiverZip", "");
           entity.put("receiverPhone", orderMail.getReceiveManPhone());
           entity.put("sellerMemo", "123");
           entity.put("postFee", "0");
           entity.put("insuredFee", "0");
           entity.put("weight", "3");
           entity.put("totalFee", orderMail.getWorth());
           entity.put("title", orderMail.getItemName());
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
             orderMail.setIsCustoms(1);
             this.orderMailDao.updateIsCustoms(orderMail);
           } 
         } else if ("ZWYHZ".equals(orderMail.getCarrier())) {
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
           
           LogisticsHead.addElement("ceb:logisticsCode").addText("331698Z001");
           LogisticsHead.addElement("ceb:logisticsName").addText("中外运跨境电商物流有限公司杭州分公司");
           LogisticsHead.addElement("ceb:logisticsNo").addText(orderMail.getMailNo());
           LogisticsHead.addElement("ceb:billNo").addText("");
           LogisticsHead.addElement("ceb:orderNo").addText(orderMail.getTxLogisticID());
           LogisticsHead.addElement("ceb:freight").addText("0");
           LogisticsHead.addElement("ceb:insuredFee").addText("0");
           LogisticsHead.addElement("ceb:currency").addText("142");
           LogisticsHead.addElement("ceb:weight").addText("1");
           LogisticsHead.addElement("ceb:packNo").addText("1");
           LogisticsHead.addElement("ceb:goodsInfo").addText("跨境商品");
           LogisticsHead.addElement("ceb:consignee").addText(orderMail.getReceiveMan());
           LogisticsHead.addElement("ceb:consigneeAddress").addText(orderMail.getReceiveManAddress());
           LogisticsHead.addElement("ceb:consigneeTelephone").addText(orderMail.getReceiveManPhone());
           LogisticsHead.addElement("ceb:note").addText("");
 
           
           Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
           BaseTransfer.addElement("ceb:copCode").addText("331698Z001");
           BaseTransfer.addElement("ceb:copName").addText("中外运跨境电商物流有限公司杭州分公司");
           BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
           BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000027105");
           BaseTransfer.addElement("ceb:note").addText("test");
           
           String xml = document.asXML();
           
           System.out.println("xml=" + xml);
 
 
 
           
           String name = "DXPENT0000027105";
           String pwd = "http://115.236.78.6:9090/newyorkTransferWebapps/rest/transferDeclare";
           
           String key = "123456";
           
           URL url = new URL("http://116.62.156.170:8066/wlsd/twlsd/addSign.do");
 
 
           
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
         else if ("STO".equals(orderMail.getCarrier())) {
           
           JSONObject jsonObject = new JSONObject();
           jsonObject.put("siteCode", "310064");
           jsonObject.put("siteName", "杭州仓储部");
           jsonObject.put("tradeNo", orderMail.getTxLogisticID());
           jsonObject.put("waybillNo", orderMail.getMailNo());
           jsonObject.put("height", "");
           jsonObject.put("length", "");
           jsonObject.put("logisticId", orderMail.getMailNo());
           jsonObject.put("orderDate", orderMail.getCreateTime());
           jsonObject.put("orderSource", "PORTZJZTXS");
           jsonObject.put("receiverProv", orderMail.getReceiveProvince());
           jsonObject.put("receiverProvCode", "");
           jsonObject.put("receiverCity", orderMail.getReceiveCity());
           jsonObject.put("receiverCityCode", "");
           jsonObject.put("receiverArea", orderMail.getReceiveCounty());
           jsonObject.put("receiverAreaCode", "");
           jsonObject.put("receiverTown", "文一路");
           jsonObject.put("receiverTownCode", "");
           jsonObject.put("receiverAddress", orderMail.getReceiveManAddress());
           jsonObject.put("receiverMobile", orderMail.getReceiveManPhone());
           jsonObject.put("receiverName", orderMail.getReceiveMan());
           jsonObject.put("receiverPhone", "024-84332654");
           jsonObject.put("receiverPostcode", "634569");
           jsonObject.put("senderAddress", "浙江省杭州市余杭区文一西路823号");
           jsonObject.put("senderProv", "浙江");
           jsonObject.put("senderProvCode", "");
           jsonObject.put("senderCity", "杭州");
           jsonObject.put("senderCityCode", "");
           jsonObject.put("senderArea", "萧山");
           jsonObject.put("senderAreaCode", "");
           jsonObject.put("senderTown", "B保");
           jsonObject.put("senderTownCode", "");
           jsonObject.put("senderMobile", "13989203824");
           jsonObject.put("senderName", "刘某");
           jsonObject.put("senderPhone", "0571-84292224");
           jsonObject.put("senderPostcode", "311200");
           jsonObject.put("volume", "");
           jsonObject.put("weight", "3");
           jsonObject.put("width", "");
 
           
           JSONArray otherInfo = new JSONArray();
           
           SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd");
           String time = s1.format(orderMail.getCreateTime());
           
           JSONObject otherInfos = new JSONObject();
           otherInfos.put("grossWeight", "3");
           otherInfos.put("totalWayBill", orderMail.getTotalMailNo());
           otherInfos.put("packNo", "1");
           otherInfos.put("goodsName", "口岸直邮商品");
           otherInfos.put("currCode", "142");
           otherInfos.put("note", "");
           otherInfos.put("insureAmount", "0");
           otherInfos.put("feeAmount", "0");
           otherInfos.put("worth", orderMail.getWorth());
           otherInfos.put("netWeight", "1");
           otherInfos.put("importDateStr", time);
           otherInfo.add(otherInfos);
 
           
           JSONObject obj = new JSONObject();
           obj.put("otherInfo", otherInfo);
           jsonObject.put("items", obj);
 
           
           try {
             String secret = "123456";
             String json = jsonObject.toString();
             json = json.replace("[", "");
             json = json.replace("]", "");
             System.out.println(json);
             String success = PushToSTO.putData(json, secret);
             System.out.println("STO_UPDATE返回值：" + success);
             JSONObject retJson_update = JSONObject.parseObject(success);
             String status_update = retJson_update.getString("success");
             if ("true".equals(status_update)) {
               orderMail.setIsCustoms(1);
               result = this.orderMailDao.updateIsCustoms(orderMail);
             }
           
           } catch (Exception e) {
             e.printStackTrace();
           } 
         } else if ("ZTO".equals(orderMail.getCarrier())) {
           JSONObject jSONObject = new JSONObject();
           jSONObject.put("logisticsno", "");
           jSONObject.put("orderno", orderMail.getTxLogisticID());
           jSONObject.put("shipper", "海外直邮");
           jSONObject.put("shipperprov", "浙江省");
           jSONObject.put("shippercity", "杭州市");
           jSONObject.put("shipperdistrict", "萧山区");
           jSONObject.put("shipperaddress", "B型保税区");
           jSONObject.put("shippermobile", "88888888");
           jSONObject.put("shippertelephone", "");
           jSONObject.put("shippercountry", "中国");
           jSONObject.put("shipperzipcode", "");
           jSONObject.put("consignee", orderMail.getReceiveMan());
           jSONObject.put("consigneeprov", orderMail.getReceiveProvince());
           jSONObject.put("consigneecity", orderMail.getReceiveCity());
           jSONObject.put("consigneedistrict", orderMail.getReceiveCounty());
           jSONObject.put("consigneeaddress", orderMail.getReceiveManAddress());
           jSONObject.put("consigneemobile", orderMail.getReceiveManPhone());
           jSONObject.put("consigneetelephone", "");
           jSONObject.put("consigneecountry", "中国");
           jSONObject.put("consigneezipcode", "");
           jSONObject.put("shippingFee", orderMail.getWorth());
           jSONObject.put("shippingFeeUnit", "元");
           jSONObject.put("shipType", Integer.valueOf(5));
           jSONObject.put("warehouseCode", "US001");
           jSONObject.put("customerid", orderMail.getBuyerIdNumber());
           jSONObject.put("idtype", Integer.valueOf(1));
           jSONObject.put("ietype", "I");
           jSONObject.put("netweight", Integer.valueOf(0));
           jSONObject.put("platformSource", Integer.valueOf(1053));
           jSONObject.put("sortContent", "");
           jSONObject.put("stockflag", Integer.valueOf(1));
           jSONObject.put("weight", Integer.valueOf(0));
           jSONObject.put("totallogisticsno", orderMail.getTotalMailNo());
           jSONObject.put("flightCode", orderMail.getFightNumber());
           jSONObject.put("cumstomscode", "HZCUSTOMSNEW");
           jSONObject.put("ebpCode", "3122480063");
           List<OrderMailSku> orderMailSkuList = this.orderMailSkuDao.findById(orderMail.getTxLogisticID());
           JSONArray intlOrderItemList = new JSONArray();
           for (OrderMailSku orderMailSku : orderMailSkuList) {
             JSONObject jSONObjectSku = new JSONObject();
             jSONObjectSku.put("itemId", Integer.valueOf(orderMailSku.getId()));
             jSONObjectSku.put("itemName", orderMailSku.getItemName());
             jSONObjectSku.put("itemUnitPrice", orderMailSku.getUnitPrice());
             jSONObjectSku.put("itemQuantity", orderMailSku.getItemCount());
             jSONObjectSku.put("itemRemark", "");
             jSONObjectSku.put("dutyMoney", Integer.valueOf(0));
             jSONObjectSku.put("blInsure", Integer.valueOf(0));
             jSONObjectSku.put("length", Integer.valueOf(0));
             jSONObjectSku.put("width", Integer.valueOf(0));
             jSONObjectSku.put("high", Integer.valueOf(0));
             jSONObjectSku.put("itemMaterial", "");
             jSONObjectSku.put("itemWeight", orderMailSku.getItemWeight());
             jSONObjectSku.put("currencyType", "CNY");
             jSONObjectSku.put("itemRule", "");
             jSONObjectSku.put("makeCountry", "");
             jSONObjectSku.put("itemUnit", "件");
             intlOrderItemList.add(jSONObjectSku);
           } 
           jSONObject.put("intlOrderItemList", intlOrderItemList);
           JSONObject orderEntity = new JSONObject();
           orderEntity.put("buyerEmail", "");
           orderEntity.put("buyerQq", "");
           orderEntity.put("buyerWangwangId", "");
           orderEntity.put("carrierCode", "");
           orderEntity.put("clearCode", "2992");
           orderEntity.put("eventtype", "");
           orderEntity.put("extendCode", "");
           orderEntity.put("mailNo", "");
           orderEntity.put("mergeCode", "");
           orderEntity.put("payableWeight", orderMail.getItemWeight());
           orderEntity.put("remark", "");
           orderEntity.put("senderEmail", "");
           orderEntity.put("senderQq", "");
           orderEntity.put("senderWangwangId", "");
           orderEntity.put("totalShippingFee", Integer.valueOf(0));
           orderEntity.put("totalShippingFeeUnit", "元");
           orderEntity.put("tradeOrderValue", orderMail.getWorth());
           orderEntity.put("tradeOrderValueUnit", "元");
           orderEntity.put("volumWeight1", "");
           orderEntity.put("senderProvinceCh", "");
           orderEntity.put("trunkCode", "");
           jSONObject.put("orderEntity", orderEntity);
           
           try {
             if (StringUtil.isNotEmpty(orderMail.getMailNo()).booleanValue()) {
               jSONObject.put("logisticsno", orderMail.getMailNo());
               String msg_type_update = "zto.intlbillorder.update";
               String ret_update = PushToZTO.putData(jSONObject.toString(), msg_type_update);
               System.out.println("ZTO_UPDATE返回值：" + ret_update);
               JSONObject retJson_update = JSONObject.parseObject(ret_update);
               String status_update = retJson_update.getString("status");
               if ("true".equals(status_update)) {
                 orderMail.setIsCustoms(1);
                 result = this.orderMailDao.updateIsCustoms(orderMail);
               } 
             } 
           } catch (Exception e) {
             e.printStackTrace();
           } 
         } else if ("TTK".equals(orderMail.getCarrier())) {
           Document document = DocumentHelper.createDocument();
           
           Element root = DocumentHelper.createElement("RequestOrder");
           document.setRootElement(root);
           
           Element siteCode = root.addElement("siteCode");
           siteCode.setText("DIBAIHZ");
           Element order = root.addElement("order");
           Element code = order.addElement("code");
           code.setText(orderMail.getTxLogisticID());
           Element logisticsNo = order.addElement("logisticsNo");
           logisticsNo.setText(orderMail.getMailNo());
           Element billNo = order.addElement("billNo");
           billNo.setText(orderMail.getTotalMailNo());
           Element weight = order.addElement("weight");
           weight.setText("3");
           Element customsType = order.addElement("customsType");
           customsType.setText("3");
           Element remark = order.addElement("remark");
           remark.setText("");
           Element port = order.addElement("port");
           port.setText("HZ-POST");
           Element customsCode = order.addElement("CustomsCode");
           customsCode.setText("2992");
           Element cIQOrgCode = order.addElement("CIQOrgCode");
           cIQOrgCode.setText("1500004693");
 
           
           Element sender = root.addElement("sender");
           Element name = sender.addElement("name");
           name.setText("迪拜");
           Element postcode = sender.addElement("postcode");
           postcode.setText("");
           Element phone = sender.addElement("phone");
           phone.setText("88888888");
           Element address = sender.addElement("address");
           address.setText("迪拜");
 
           
           Element receiver = root.addElement("receiver");
           Element receiverName = receiver.addElement("name");
           Element receiverPhone = receiver.addElement("phone");
           Element receiverProvince = receiver.addElement("province");
           Element receiverCity = receiver.addElement("city");
           Element receiverDistrict = receiver.addElement("district");
           Element receiverAddress = receiver.addElement("address");
           Element receiverPostcode = receiver.addElement("postcode");
           
           receiverName.setText(orderMail.getReceiveMan());
           receiverPhone.setText(orderMail.getReceiveManPhone());
           receiverProvince.setText(orderMail.getReceiveProvince());
           receiverCity.setText(orderMail.getReceiveCity());
           receiverDistrict.setText(orderMail.getReceiveCounty());
           receiverAddress.setText(orderMail.getReceiveManAddress());
           receiverPostcode.setText(orderMail.getReceivecode());
           
           Element goods = root.addElement("goods");
           
           Element item = goods.addElement("item");
           Element itemName = item.addElement("name");
           Element itemNum = item.addElement("num");
           Element itemValue = item.addElement("value");
           
           itemName.setText(orderMail.getItemName());
           itemNum.setText("1");
           itemValue.setText(orderMail.getWorth().toString());
           
           System.out.println("ttk=>>>" + document.asXML());
           String success = PushToTTK.putData(document.asXML());
           System.out.println("TTK返回值：" + success);
           
           if (success.indexOf("success") != -1) {
             orderMail.setIsCustoms(1);
             result = this.orderMailDao.updateIsCustoms(orderMail);
           
           }
         
         }
         else {
           
           Element root = DocumentHelper.createElement("mo");
           Document document = DocumentHelper.createDocument(root);
           
           root.addAttribute("version", "1.0.0");
 
           
           Element element1 = root.addElement("head");
           element1.addElement("businessType").addText("IMPORTBILL");
           
           Element element2 = root.addElement("body");
           Element element3 = element2.addElement("wayBillList");
           Element element4 = element3.addElement("wayBill");
           Element element5 = element4.addElement("jkfSign");
           
           if ("SF".equals(orderMail.getCarrier())) {
             element5.addElement("companyCode").addText("595784712");
           } else if ("STO".equals(orderMail.getCarrier())) {
             element5.addElement("companyCode").addText("669437562");
           } else if ("ZTO".equals(orderMail.getCarrier())) {
             element5.addElement("companyCode").addText("3122480063");
           } else if ("YDA".equals(orderMail.getCarrier())) {
             element5.addElement("companyCode").addText("631556216");
           } else if ("TTK".equals(orderMail.getCarrier())) {
             element5.addElement("companyCode").addText("3301962G91");
           }
           else if ("EMS备用".equals(orderMail.getCarrier())) {
             element5.addElement("companyCode").addText("330198Z014");
           }
           else if ("YTO".equals(orderMail.getCarrier())) {
             element5.addElement("companyCode").addText("3316965375");
           } 
           
           element5.addElement("businessNo").addText(orderMail.getMailNo());
           element5.addElement("businessType").addText("IMPORTBILL");
           element5.addElement("declareType").addText("1");
           
           element5.addElement("cebFlag").addText("03");
 
 
           
           element5.addElement("note");
           Element element6 = element4.addElement("wayBillImportDto");
           element6.addElement("wayBill").addText(orderMail.getMailNo());
           element6.addElement("packNo").addText("1");
           element6.addElement("grossWeight").addText("3");
           element6.addElement("netWeight").addText("1.2");
           element6.addElement("goodsName").addText("杭州商品");
           element6.addElement("sendArea").addText("浙江省杭州市萧山B保");
           element6.addElement("consigneeArea").addText(orderMail.getReceiveProvince());
           element6.addElement("consigneeTel").addText(orderMail.getReceiveManPhone());
           element6.addElement("consignee").addText(orderMail.getReceiveMan());
           element6.addElement("consigneeAddress").addText(orderMail.getReceiveManAddress());
           element6.addElement("zipCode").addText("311300");
           element6.addElement("customsCode").addText("2992");
           element6.addElement("worth").addText(orderMail.getWorth().toString());
           element6.addElement("importDateStr").addText(df.format(new Date()));
           element6.addElement("currCode").addText("142");
           element6.addElement("totalWayBill").addText(orderMail.getTotalMailNo().toString());
           
           if ("SF".equals(orderMail.getCarrier())) {
             element6.addElement("logisCompanyCode").addText("595784712");
             
             element6.addElement("logisCompanyName").addText("浙江顺路物流有限公司");
           } else if ("STO".equals(orderMail.getCarrier())) {
             element6.addElement("logisCompanyCode").addText("669437562");
             
             element6.addElement("logisCompanyName").addText("申通快递有限公司");
           } else if ("ZTO".equals(orderMail.getCarrier())) {
             element6.addElement("logisCompanyCode").addText("3122480063");
             
             element6.addElement("logisCompanyName").addText("上海大誉国际物流有限公司");
           
           }
           else if ("YDA".equals(orderMail.getCarrier())) {
             element6.addElement("logisCompanyCode").addText("669437562");
             
             element6.addElement("logisCompanyName").addText("申通快递有限公司");
           } else if ("ZTO".equals(orderMail.getCarrier())) {
             element6.addElement("logisCompanyCode").addText("3122480063");
             
             element6.addElement("logisCompanyName").addText("上海大誉国际物流有限公司");
           
           }
           else if ("YDA".equals(orderMail.getCarrier())) {
             
             element6.addElement("logisCompanyName").addText("上海韵达货运有限公司");
             element6.addElement("logisCompanyCode").addText("631556216");
           } else if ("TTK".equals(orderMail.getCarrier())) {
             
             element6.addElement("logisCompanyName").addText("天天快递有限公司");
             element6.addElement("logisCompanyCode").addText("3301962G91");
           } else if ("EMS备用".equals(orderMail.getCarrier())) {
             
             element6.addElement("logisCompanyCode").addText("330198Z014");
             element6.addElement("logisCompanyName").addText("中国邮政速递物流股份有限公司杭州市分公司");
           } else if ("YTO".equals(orderMail.getCarrier())) {
             
             element6.addElement("logisCompanyCode").addText("060983088");
             element6.addElement("logisCompanyName").addText("浙江圆通速递有限公司");
           } 
           element6.addElement("feeAmount").addText("0");
           element6.addElement("insureAmount").addText("0");
 
           
           if ("STO".equals(orderMail.getCarrier())) {
             String ret = PushtoBG_yd.toZJyd_sto(document.asXML());
             if (ret.contains("<resultInfo>处理成功</resultInfo>")) {
               orderMail.setIsCustoms(1);
               result = this.orderMailDao.updateIsCustoms(orderMail);
             } 
           } else {
             String ret = PushtoBG_yd.toZJyd(document.asXML());
             if (ret.contains("<resultInfo>处理成功</resultInfo>")) {
               orderMail.setIsCustoms(1);
               result = this.orderMailDao.updateIsCustoms(orderMail);
             
             }
           
           }
         
         }
       
       }
       catch (Exception e) {
         e.printStackTrace();
       } 
       
       b++; }
     
     return result;
   }
 
 
   
   public String jkDd(String ids) {
     Companyforzy findByZy = this.companyforzyDao.findByZy("ZYDD4");
     String str = "ZY000000-FDFD-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
     int result = -1;
     Element cebRoot = DocumentHelper.createElement("ceb:CEB311Message");
     cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
     
     cebRoot.addAttribute("guid", str);
     cebRoot.addAttribute("version", "1.0");
     cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
     Document document = DocumentHelper.createDocument(cebRoot);
     try {
       List<Map> list = this.orderMailDao.neworderzycx(ids);
       
       Integer i = Integer.valueOf(0);
       String zsid = "";
       Element Order = null;
       for (Map map : list) {
         
         if (!zsid.equals(map.get("id").toString())) {
           
           zsid = "";
           i = Integer.valueOf(0);
         } 
 
         
         if (zsid.equals("")) {
           zsid = map.get("id").toString();
           Order = cebRoot.addElement("ceb:Order");
           Element OrderHead = Order.addElement("ceb:OrderHead");
           OrderHead.addElement("ceb:guid").addText(str);
           OrderHead.addElement("ceb:appType").addText("1");
           OrderHead.addElement("ceb:appTime").addText(timenew.newtime());
           OrderHead.addElement("ceb:appStatus").addText("2");
           OrderHead.addElement("ceb:orderType").addText("I");
           OrderHead.addElement("ceb:orderNo").addText(map.get("txLogisticID").toString());
           OrderHead.addElement("ceb:ebpCode").addText(findByZy.getCompanyCode());
           OrderHead.addElement("ceb:ebpName").addText(findByZy.getCompanyName());
           OrderHead.addElement("ceb:ebcCode").addText(findByZy.geteCommerceCode());
           OrderHead.addElement("ceb:ebcName").addText(findByZy.geteCommerceName());
           OrderHead.addElement("ceb:goodsValue").addText(map.get("worth").toString());
           OrderHead.addElement("ceb:freight").addText("0");
           OrderHead.addElement("ceb:discount").addText("0");
           OrderHead.addElement("ceb:taxTotal").addText("0");
           OrderHead.addElement("ceb:acturalPaid").addText(map.get("worth").toString());
           OrderHead.addElement("ceb:currency").addText("142");
           OrderHead.addElement("ceb:buyerRegNo").addText("HC2020ASL");
           OrderHead.addElement("ceb:buyerName").addText(map.get("buyerName").toString());
           OrderHead.addElement("ceb:buyerTelephone").addText(map.get("receiveManPhone").toString());
           OrderHead.addElement("ceb:buyerIdType").addText("1");
           OrderHead.addElement("ceb:buyerIdNumber").addText(map.get("buyerIdNumber").toString());
           OrderHead.addElement("ceb:payCode").addText(findByZy.getPayCompanyCode());
           OrderHead.addElement("ceb:payName").addText(findByZy.getAccountBookNo());
           OrderHead.addElement("ceb:payTransactionId").addText(map.get("payNumber").toString());
           OrderHead.addElement("ceb:batchNumbers").addText("1");
           OrderHead.addElement("ceb:consignee").addText(map.get("receiveMan").toString());
           OrderHead.addElement("ceb:consigneeTelephone").addText(map.get("receiveManPhone").toString());
           OrderHead.addElement("ceb:consigneeAddress").addText(newzs.getChineseOrEnglishOrNumber(map.get("receiveManAddress").toString()));
           OrderHead.addElement("ceb:consigneeDistrict").addText("321408");
           OrderHead.addElement("ceb:note").addText("1");
           i = Integer.valueOf(i.intValue() + 1);
           Element element1 = Order.addElement("ceb:OrderList");
           element1.addElement("ceb:gnum").addText(i.toString());
           element1.addElement("ceb:itemNo").addText("");
           element1.addElement("ceb:itemName").addText(newzs.getChineseOrEnglishOrNumber(map.get("itemName").toString()));
           element1.addElement("ceb:gmodel").addText(map.get("standard").toString());
           element1.addElement("ceb:itemDescribe").addText("");
           element1.addElement("ceb:barCode").addText("");
           element1.addElement("ceb:unit").addText(map.get("unitDesc").toString());
           element1.addElement("ceb:qty").addText(map.get("ic").toString());
           element1.addElement("ceb:price").addText(map.get("unitPrice").toString());
           element1.addElement("ceb:totalPrice").addText(map.get("allPrice").toString());
           element1.addElement("ceb:currency").addText("142");
           element1.addElement("ceb:country").addText(map.get("itemClass").toString());
           element1.addElement("ceb:note").addText("备注");
           continue;
         } 
         i = Integer.valueOf(i.intValue() + 1);
         Element OrderList = Order.addElement("ceb:OrderList");
         OrderList.addElement("ceb:gnum").addText(i.toString());
         OrderList.addElement("ceb:itemNo").addText("");
         OrderList.addElement("ceb:itemName").addText(newzs.getChineseOrEnglishOrNumber(map.get("itemName").toString()));
         OrderList.addElement("ceb:gmodel").addText(map.get("standard").toString());
         OrderList.addElement("ceb:itemDescribe").addText("");
         OrderList.addElement("ceb:barCode").addText("");
         OrderList.addElement("ceb:unit").addText(map.get("unitDesc").toString());
         OrderList.addElement("ceb:qty").addText(map.get("ic").toString());
         OrderList.addElement("ceb:price").addText(map.get("unitPrice").toString());
         OrderList.addElement("ceb:totalPrice").addText(map.get("allPrice").toString());
         OrderList.addElement("ceb:currency").addText("142");
         OrderList.addElement("ceb:country").addText(map.get("itemClass").toString());
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
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     return "";
   }
 
 
   
   public int pushDd(String ids) {
     jkDd(ids);
     this.orderMailDao.updateddzt_2(ids);
     int result = 1;
     
     return result;
   }
 
 
 
   
   public int pushQd(String ids) {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String[] idArr = ids.split(",");
     int result = -1;
     DecimalFormat df1 = new DecimalFormat("######0.00"); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       
       jkQdZs(id);
       
       b++; }
     
     return result;
   }
   public void jkQdZs(String ids) {
     DecimalFormat df_2 = new DecimalFormat("0.000");
     String str = "BQ1E1CFD-EDED-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
     String[] idArr = ids.split(",");
     int bz = 0;
     
     try {
       Element cebRoot = DocumentHelper.createElement("ceb:CEB621Message");
       cebRoot.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
       
       cebRoot.addAttribute("guid", str);
       cebRoot.addAttribute("version", "1.0");
       cebRoot.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
       Document document = DocumentHelper.createDocument(cebRoot); byte b;
       int i;
       String[] arrayOfString;
       for (i = (arrayOfString = idArr).length, b = 0; b < i; ) { String id = arrayOfString[b];
         Element Logistics = cebRoot.addElement("ceb:Inventory");
         Element InventoryHead = Logistics.addElement("ceb:InventoryHead");
         OrderMail orderMail = this.orderMailDao.findByIdToBG(
             Integer.valueOf(Integer.parseInt(id)));
         Companyforzy findByZy = this.companyforzyDao.findByZy("ZYQD4");
         
         if (orderMail == null) {
           bz = 1;
         }
         else {
           
           String str2 = "BQ1E1CFD-EDED-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
           InventoryHead.addElement("ceb:guid").addText(str2);
           InventoryHead.addElement("ceb:appType").addText("1");
           InventoryHead.addElement("ceb:appTime").addText(timenew.newtime());
           InventoryHead.addElement("ceb:appStatus").addText("2");
           InventoryHead.addElement("ceb:orderNo").addText(orderMail.getTxLogisticID());
           InventoryHead.addElement("ceb:ebpCode").addText(findByZy.getCompanyCode());
           InventoryHead.addElement("ceb:ebpName").addText(findByZy.getCompanyName());
           InventoryHead.addElement("ceb:ebcCode").addText(findByZy.geteCommerceCode());
           InventoryHead.addElement("ceb:ebcName").addText(findByZy.geteCommerceName());
           InventoryHead.addElement("ceb:logisticsNo").addText(orderMail.getMailNo());
           
           InventoryHead.addElement("ceb:logisticsCode").addText("331898Z004");
           InventoryHead.addElement("ceb:logisticsName").addText("中外运跨境电商物流有限公司义乌分公司");
           
           InventoryHead.addElement("ceb:copNo").addText(orderMail.getPreEntryNumber());
           InventoryHead.addElement("ceb:preNo");
           InventoryHead.addElement("ceb:assureCode").addText(findByZy.getAssureCode());
           InventoryHead.addElement("ceb:emsNo").addText("");
           InventoryHead.addElement("ceb:invtNo");
           InventoryHead.addElement("ceb:ieFlag").addText("I");
           InventoryHead.addElement("ceb:declTime").addText(timenew.newtime3());
           InventoryHead.addElement("ceb:customsCode").addText(findByZy.getDeclPort());
           InventoryHead.addElement("ceb:portCode").addText(findByZy.getDeclPort());
           InventoryHead.addElement("ceb:ieDate").addText(timenew.newtime3());
           InventoryHead.addElement("ceb:buyerIdType").addText("1");
           InventoryHead.addElement("ceb:buyerIdNumber").addText(orderMail.getBuyerIdNumber());
           InventoryHead.addElement("ceb:buyerName").addText(orderMail.getBuyerName());
           InventoryHead.addElement("ceb:buyerTelephone").addText(orderMail.getReceiveManPhone());
           InventoryHead.addElement("ceb:consigneeAddress").addText(orderMail.getReceiveManAddress());
           InventoryHead.addElement("ceb:agentCode").addText(findByZy.getDeclareCompanyCode());
           InventoryHead.addElement("ceb:agentName").addText(findByZy.getDeclareCompanyName());
           InventoryHead.addElement("ceb:areaCode").addText("");
           InventoryHead.addElement("ceb:areaName").addText("");
           InventoryHead.addElement("ceb:tradeMode").addText("9610");
           InventoryHead.addElement("ceb:trafMode").addText("5");
           InventoryHead.addElement("ceb:trafNo").addText("5");
           InventoryHead.addElement("ceb:voyageNo").addText(orderMail.getFightNumber());
           InventoryHead.addElement("ceb:billNo").addText(orderMail.getTotalMailNo());
           InventoryHead.addElement("ceb:loctNo").addText(findByZy.getCustomsField());
           InventoryHead.addElement("ceb:licenseNo").addText("");
           InventoryHead.addElement("ceb:country").addText(orderMail.getDestinationPort());
           
           InventoryHead.addElement("ceb:freight").addText("0");
           
           InventoryHead.addElement("ceb:insuredFee").addText("0");
           InventoryHead.addElement("ceb:currency").addText("142");
           InventoryHead.addElement("ceb:wrapType").addText("2");
           InventoryHead.addElement("ceb:packNo").addText("1");
 
 
 
           
           List<OrderMailSku> orderMailSkuList = this.orderMailSkuDao
             .findById(orderMail.getTxLogisticID());
           double gw = 0.0D;
           double jz = 0.0D;
           Integer integer = Integer.valueOf(1);
           for (OrderMailSku orderMailSku : orderMailSkuList) {
 
             
             ItemForZy itemForZy = this.orderMailSkuDao
               .findItemForZy(orderMailSku.getItemsku());
 
             
             Element InventoryList = Logistics.addElement("ceb:InventoryList");
             InventoryList.addElement("ceb:gnum").addText(integer.toString());
             
             InventoryList.addElement("ceb:itemRecordNo").addText("");
             
             InventoryList.addElement("ceb:itemNo").addText(itemForZy.getItemSKU());
             
             InventoryList.addElement("ceb:itemName").addText(orderMailSku.getItemName());
             InventoryList.addElement("ceb:gcode").addText(itemForZy.getHscode());
             InventoryList.addElement("ceb:gname").addText(orderMailSku.getItemName());
             InventoryList.addElement("ceb:gmodel").addText(orderMailSku.getStandard());
             InventoryList.addElement("ceb:barCode").addText("无");
             InventoryList.addElement("ceb:country").addText(orderMail.getDestinationPort());
 
             
             InventoryList.addElement("ceb:currency").addText("142");
             InventoryList.addElement("ceb:qty").addText(orderMailSku.getItemCount().toString());
             InventoryList.addElement("ceb:unit").addText(itemForZy.getUnitDesc());
             
             if (itemForZy.getOneUnitDesc().equals("035")) {
               InventoryList.addElement("ceb:qty1").addText(orderMailSku.getItemWeight().toString());
               
               InventoryList.addElement("ceb:unit1").addText("035");
             } else {
               InventoryList.addElement("ceb:qty1").addText(orderMailSku.getItemCount().toString());
               
               InventoryList.addElement("ceb:unit1").addText(itemForZy.getOneUnitDesc());
             } 
             
             if (!itemForZy.getTwoUnitDesc().equals(""))
             {
               if (itemForZy.getTwoUnitDesc().equals("035")) {
                 InventoryList.addElement("ceb:qty2").addText(orderMailSku.getItemWeight().toString());
                 InventoryList.addElement("ceb:unit2").addText("035");
               } else {
                 InventoryList.addElement("ceb:qty2").addText(orderMailSku.getItemCount().toString());
                 InventoryList.addElement("ceb:unit2").addText(itemForZy.getTwoUnitDesc());
               } 
             }
 
             
             InventoryList.addElement("ceb:price").addText(orderMailSku.getUnitPrice().toString());
             InventoryList.addElement("ceb:totalPrice").addText(orderMailSku.getAllPrice().toString());
             InventoryList.addElement("ceb:note");
             integer = Integer.valueOf(integer.intValue() + 1);
           } 
           
           InventoryHead.addElement("ceb:grossWeight").addText("1.4");
           InventoryHead.addElement("ceb:netWeight").addText("1.2");
           InventoryHead.addElement("ceb:note");
         }  b++; }
       
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
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
   }
 
   
   public int pushQdTest(String ids) {
     int result = -1;
     try {
       String qdToken = "open";
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String[] idArr = ids.split(","); byte b; int i; String[] arrayOfString1;
       for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
         OrderMail orderMail = this.orderMailDao.findByIdToBG(
             Integer.valueOf(Integer.parseInt(id)));
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("version", "1.0.0");
         String str = "HZBO4" + String.format("%013d", new Object[] { Integer.valueOf(orderMail.getId()) });
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType")
           .addText("PERSONAL_GOODS_DECLAR");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("goodsDeclareModuleList");
         Element element4 = element3.addElement("goodsDeclareModule");
         
         Element element5 = element4.addElement("jkfSign");
         element5.addElement("companyCode").addText("785309525");
         element5.addElement("businessNo").addText(str);
         element5.addElement("businessType")
           .addText("PERSONAL_GOODS_DECLAR");
         element5.addElement("declareType").addText("1");
         
         element5.addElement("cebFlag").addText("03");
 
 
         
         element5.addElement("note").addText("进口清单");
         
         Element element6 = element4.addElement("goodsDeclare");
         element6.addElement("accountBookNo").addText("");
         element6.addElement("ieFlag").addText("I");
         element6.addElement("preEntryNumber").addText(str);
         
         element6.addElement("importType").addText("0");
         element6.addElement("inOutDateStr").addText(df.format(new Date()));
         element6.addElement("iePort").addText("2992");
         element6.addElement("destinationPort").addText("142");
         element6.addElement("trafName").addText("");
         element6.addElement("voyageNo").addText(orderMail.getFightNumber());
         element6.addElement("trafNo").addText("5");
         element6.addElement("trafMode").addText("5");
         element6.addElement("declareCompanyType").addText("个人委托第三方申报");
         element6.addElement("declareCompanyCode").addText("785309525");
         element6.addElement("declareCompanyName").addText("杭州博奥国际货运代理有限公司");
         element6.addElement("companyName").addText("杭州网趣科技有限公司");
         element6.addElement("companyCode").addText("91330106MA27W6WF4G");
         element6.addElement("eCommerceCode").addText("91330106MA27W6WF4G");
 
 
 
         
         element6.addElement("eCommerceName").addText("杭州网趣科技有限公司");
         if ("SF".equals(orderMail.getCarrier())) {
           element6.addElement("logisCompanyCode").addText("595784712");
           
           element6.addElement("logisCompanyName").addText("浙江顺路物流有限公司");
         } else if ("STO".equals(orderMail.getCarrier())) {
           
           element6.addElement("logisCompanyName").addText("申通快递有限公司");
           element6.addElement("logisCompanyCode").addText("669437562");
         } else if ("YDA".equals(orderMail.getCarrier())) {
           
           element6.addElement("logisCompanyName").addText("上海韵达货运有限公司");
           element6.addElement("logisCompanyCode").addText("631556216");
         }         
         element6.addElement("orderNo").addText(orderMail.getTxLogisticID());
         element6.addElement("wayBill").addText(orderMail.getMailNo());
         element6.addElement("billNo").addText(orderMail.getTotalMailNo());
         element6.addElement("tradeCountry").addText(
             orderMail.getTradeCountry());
         element6.addElement("packNo").addText("1");
         element6.addElement("grossWeight").addText("3");
         element6.addElement("netWeight").addText("1.2");
         element6.addElement("warpType").addText("");
         element6.addElement("remark").addText("");
         element6.addElement("declPort").addText("2992");
         element6.addElement("enteringPerson").addText("9999");
         element6.addElement("enteringCompanyName").addText("9999");
         element6.addElement("declarantNo").addText("");
         element6.addElement("customsField").addText("299201");
         element6.addElement("senderName").addText("杭州网趣科技有限公司");
         element6.addElement("consignee").addText(orderMail.getReceiveMan());
         element6.addElement("senderCountry").addText("142");
         element6.addElement("senderCity").addText("");
         element6.addElement("paperType").addText("1");
         element6.addElement("paperNumber").addText(
             orderMail.getBuyerIdNumber());
         element6.addElement("consigneeAddress").addText(
             orderMail.getReceiveManAddress());
         element6.addElement("purchaserTelNumber").addText(
             orderMail.getReceiveManPhone());
         element6.addElement("buyerIdType").addText("1");
         element6.addElement("buyerIdNumber").addText(
             orderMail.getBuyerIdNumber());
         element6.addElement("buyerName").addText(orderMail.getBuyerName());
         element6.addElement("worth").addText(
             orderMail.getWorth().toString());
         element6.addElement("feeAmount").addText("0");
         element6.addElement("insureAmount").addText("0");
         element6.addElement("currCode").addText("142");
         element6.addElement("mainGName").addText(orderMail.getItemName());
         element6.addElement("internalAreaCompanyNo").addText("");
         element6.addElement("internalAreaCompanyName").addText("");
         element6.addElement("assureCode").addText("785309525");
         
         element6.addElement("applicationFormNo").addText("");
         element6.addElement("isAuthorize").addText("1");
         element6.addElement("licenseNo").addText("");
         
         List<OrderMailSku> orderMailSkuList = this.orderMailSkuDao
           .findById(orderMail.getTxLogisticID());
         Element element7 = element4.addElement("goodsDeclareDetails");
         Integer integer = Integer.valueOf(1);
         for (OrderMailSku orderMailSku : orderMailSkuList) {
           ItemForZy itemForZy = this.orderMailSkuDao
             .findItemForZy(orderMailSku.getItemsku());
           Element element8 = element7.addElement("goodsDeclareDetail");
           element8.addElement("goodsOrder").addText(integer.toString());
           
           element8.addElement("codeTs").addText(itemForZy.getHscode());
           
           element8.addElement("goodsItemNo").addText("");
           element8.addElement("itemRecordNo").addText("");
           element8.addElement("itemName").addText("");
           element8.addElement("goodsName").addText(
               orderMailSku.getItemName());
           element8.addElement("goodsModel").addText(
               orderMailSku.getItemName());
           element8.addElement("originCountry").addText(
               orderMail.getTradeCountry());
           element8.addElement("tradeCurr").addText("142");
           element8.addElement("tradeTotal").addText("");
           element8.addElement("declPrice").addText(
               orderMailSku.getUnitPrice().toString());
           element8.addElement("declTotalPrice").addText(
               orderMailSku.getAllPrice().toString());
           element8.addElement("useTo").addText("");
           element8.addElement("declareCount").addText(
               orderMailSku.getItemCount().toString());
           
           element8.addElement("goodsUnit").addText(
               itemForZy.getUnitDesc());
           element8.addElement("goodsGrossWeight").addText("");
           element8.addElement("firstUnit").addText(
               itemForZy.getOneUnitDesc());
           element8.addElement("firstCount").addText("1");
           
           if (itemForZy.getTwoUnitDesc() == null || 
             itemForZy.getTwoUnitDesc().equals("")) {
             element8.addElement("secondUnit").addText("");
             element8.addElement("secondCount").addText("");
           } else {
             element8.addElement("secondUnit").addText(
                 itemForZy.getTwoUnitDesc());
             element8.addElement("secondCount").addText("1");
           } 
           element8.addElement("productRecordNo").addText(
               itemForZy.getProductRecordNo());
           element8.addElement("webSite").addText("");
           element8.addElement("barCode").addText("");
           element8.addElement("note").addText("");
           integer = Integer.valueOf(integer.intValue() + 1);
         } 
 
         
         String ret = PushtoBG_yd.testQd(document.asXML(), qdToken);
         
         if ("令牌无效".equals(ret)) {
           return 9;
         }
         qdToken = "close";
         
         if (ret.contains("<resultInfo>处理成功</resultInfo>")) {
           orderMail.setIsPushQd(1);
           result = this.orderMailDao.updateIsPushQd(orderMail);
         }  b++; }
     
     } catch (Exception e) {
       System.err.println(e.toString());
     } 
     PushtoBG_yd.testQd("", "");
     return result;
   }
 
 
   
   public int storage(String totalMailNo) {
     List<OrderMail> orderMail = this.orderMailDao.storageOrderMai(totalMailNo);
     Element root = DocumentHelper.createElement("ceb:CEB711Message");
     Document document = DocumentHelper.createDocument(root);
     
     root.addNamespace("ceb", "http://www.chinaport.gov.cn/ceb");
     root.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
     root.addAttribute("version", "1.0");
     root.addAttribute("guid", "TRF4DGGB-9940-415B-B666-C09BOO7F40B2");
     Element element1 = root.addElement("ceb:Delivery");
     Element element2 = element1.addElement("ceb:DeliveryHead");
     element2.addElement("ceb:guid").addText(
         "5923DGGB-9940-415B-B666-C09BOO7F40B2");
     element2.addElement("ceb:appType").addText("1");
     element2.addElement("ceb:appTime").addText("20160310163311");
     element2.addElement("ceb:appStatus").addText("2");
     element2.addElement("ceb:customsCode").addText("3700");
     element2.addElement("ceb:copNo").addText("NBBH20160308005");
     element2.addElement("ceb:preNo").addText("B20160308000000005");
     element2.addElement("ceb:rkdNo").addText("010220160308000005");
     element2.addElement("ceb:operatorCode").addText("1101110326");
     element2.addElement("ceb:operatorName").addText("监管人");
     
     element2.addElement("ceb:ieFlag").addText("I");
     element2.addElement("ceb:trafMode").addText("1");
     element2.addElement("ceb:trafNo").addText("0");
     element2.addElement("ceb:voyageNo").addText((
         (OrderMail)orderMail.get(0)).getFightNumber());
     element2.addElement("ceb:billNo").addText(totalMailNo);
     element2.addElement("ceb:logisticsCode").addText("1105910159");
     
     element2.addElement("ceb:logisticsName").addText("测试物流企业");
     element2.addElement("ceb:unloadLocation").addText("");
     element2.addElement("ceb:note").addText("");
     
     Element element3 = element1.addElement("ceb:DeliveryList");
     
     for (int i = 0; i < orderMail.size(); i++) {
       String j = String.valueOf(i + 1);
       element3.addElement("ceb:gnum").addText(j);
       element3.addElement("ceb:logisticsNo").addText((
           (OrderMail)orderMail.get(i)).getMailNo());
       element3.addElement("ceb:note").addText("");
     } 
     
     Element element4 = root.addElement("ceb:copCode");
     element4.addElement("ceb:copCode").addText("1101180326");
     element4.addElement("ceb:copName").addText("物流企业");
     element4.addElement("ceb:dxpMode").addText("DXP");
     element4.addElement("ceb:dxpId").addText("DXPLGS0000000001");
     element4.addElement("ceb:note").addText("");
 
     
     return 0;
   }
 
   
   public int updateAuditstatusByIds(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderMail orderMail = this.orderMailDao.findByIdToWJ(
           Integer.valueOf(Integer.parseInt(id)));
       
       OrderAccept orderAccept = new OrderAccept();
       orderAccept.setOrderNumber(orderMail.getTxLogisticID());
       orderAccept.setExpressCode(orderMail.getCarrier());
       orderAccept.setExpressNumber(orderMail.getMailNo());
       orderAccept.setBuyerName(orderMail.getReceiveMan());
       orderAccept.setBuyerNickName(orderMail.getBuyerName());
       orderAccept.setBuyerIdCard(orderMail.getBuyerIdNumber());
       orderAccept.setBuyerProvince(orderMail.getReceiveProvince());
       orderAccept.setBuyerCity(orderMail.getReceiveCity());
       orderAccept.setBuyerArea(orderMail.getReceiveCounty());
       orderAccept.setBuyerAddress(orderMail.getReceiveManAddress());
       orderAccept.setBuyerTel(orderMail.getReceiveManPhone());
       orderAccept.setZyName(orderMail.getBillProvideSiteName());
       orderAccept.setZyNumber(orderMail.getBillProvideSiteCode());
       orderAccept.setSender(orderMail.getSendName());
       orderAccept.setSenderTel(orderMail.getSendTel());
       orderAccept.setSenderAddress(orderMail.getSendAddress());
       orderAccept.setUserId(orderMail.getUserId());
       orderAccept.setBuyerPostCode(orderMail.getMarkDestination());
       orderAccept.setPrintType(0);
       this.orderAcceptDao.insert(orderAccept);
       
       List<OrderMailSku> obsList = this.orderMailSkuDao.findById(orderMail
           .getTxLogisticID());
       for (OrderMailSku orderMailSku : obsList) {
         GoodsAccept goodsAccept = new GoodsAccept();
         goodsAccept.setExpressNumber(orderMail.getMailNo());
         goodsAccept.setGoodsName(orderMailSku.getItemName());
         goodsAccept.setGoodsSKU(orderMailSku.getItemsku());
         goodsAccept.setNum(orderMailSku.getItemCount().intValue());
         goodsAccept.setPrice(BigDecimal.valueOf(
               Double.valueOf(orderMailSku.getUnitPrice().doubleValue()).doubleValue()));
         goodsAccept.setTotalPrice(BigDecimal.valueOf(
               Double.valueOf(orderMailSku.getAllPrice().doubleValue()).doubleValue()));
         goodsAccept.setWeight(Double.valueOf(orderMailSku
               .getItemWeight().doubleValue()));
         result = this.goodsAcceptDao.insert(goodsAccept);
       } 
       OrderMail om = new OrderMail();
       om.setId(Integer.parseInt(id));
       om.setIsPrint(1);
       result = this.orderMailDao.updateIsPrint(om); b++; }
     
     return result;
   }
 
   
   public OrderMail findByMailNo(OrderMail orderMail) {
     return this.orderMailDao.findByMailNo(orderMail);
   }
   
   public List<OrderMail> findMailNo(Map<String, Object> pageMap) {
     return this.orderMailDao.findMailNo(pageMap);
   }
 
   
   public List<OrderMail> findTxLogisticID() {
     return this.orderMailDao.findTxLogisticID();
   }
 
   
   public int payNumber(OrderMail orderMail) {
     return this.orderMailDao.payNumber(orderMail);
   }
 
   
   public List<OrderMail> findAllmx(int id) {
     return this.orderMailDao.findAllmx(id);
   }
 
   
   public int countmx(int id) {
     return this.orderMailDao.countmx(id);
   }
 
   
   public List<OrderMail> findeXport(Map<String, Object> pageMap) {
     return this.orderMailDao.findeXport(pageMap);
   }
 
   
   public List<OrderMail> findeXportId(Integer id) {
     return this.orderMailDao.findeXportId(id);
   }
 
   
   public String getTax(int userId) {
     OrderMail orderMail = new OrderMail();
     orderMail.setUserId(userId);
     orderMail.setAuditstatus(4);
     orderMail = this.orderMailDao.getTax(orderMail);
     
     BigDecimal allPrice = new BigDecimal(0);
     BigDecimal consolidatedTax = new BigDecimal(0);
     if (orderMail != null) {
       if (orderMail.getAllPrice() != null) {
         allPrice = orderMail.getAllPrice();
       }
       if (orderMail.getConsolidatedTax() != null) {
         consolidatedTax = orderMail.getConsolidatedTax();
       }
     } 
     
     String ret = "商品总价：" + allPrice + "元；" + "综合税：" + consolidatedTax + "元";
     return ret;
   }
 
   
   public List<OrderMail> findBybatchNumber(Map<String, Object> pageMap) {
     return this.orderMailDao.findBybatchNumber(pageMap);
   }
 
   
   public int conbatchNumber(String batchNumber) {
     return this.orderMailDao.conbatchNumber(batchNumber);
   }
 
   
   public List<OrderMail> deriveddetail(String batchNumber) {
     return this.orderMailDao.deriveddetail(batchNumber);
   }
 
 
   
   public List<OrderMailForExport> exportMoil(Map<String, Object> pageMap) {
     return this.orderMailDao.exportMoil(pageMap);
   }
 
 
   
   public List<OrderMail> exportMoilid(Integer id) {
     return this.orderMailDao.exportMoilid(id);
   }
 
   
   public OrderMail findOrderMailDy(String totalMailNo) {
     return this.orderMailDao.findOrderMailDy(totalMailNo);
   }
 
   
   public OrderMail findOrderMailXy(String totalMailNo) {
     return this.orderMailDao.findOrderMailXy(totalMailNo);
   }
 
   
   public OrderMail findOrderMai(String totalMailNo) {
     return this.orderMailDao.findOrderMai(totalMailNo);
   }
 
   
   public String getMailBill(String mailNo) throws Exception {
     String ret = GetWLGJ.queryWlgj(mailNo);
     
     return ret;
   }
 
   
   public List<OrderMail> odmdateTime(Map<String, Object> pageMap) {
     return this.orderMailDao.odmdateTime(pageMap);
   }
 
   
   public int editIdN(OrderMail orderMail) {
     return this.orderMailDao.editIdN(orderMail);
   }
 
   
   public void updateInvtno(OrderMail orderMail) {
     this.orderMailDao.updateInvtno(orderMail);
   }
 
 
   
   public OrderMail findOneById(int id) {
     return this.orderMailDao.findOneById(id);
   }
 
   
   public int countByTydh(String tydh) {
     return this.orderMailDao.countByTydh(tydh);
   }
 
   
   public int updateExpressParamsf(String ids) {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String[] idArr = ids.split(",");
     int result = 1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderMail orderMail = this.orderMailDao.findByIdToBG(
           Integer.valueOf(Integer.parseInt(id)));
       if (orderMail.getCarrier().equals("STO")) {
         String url2 = "http://183.129.231.82:8066/ecm/interface/rest/tendInterface/getbill";
         String param2 = "Sign=0888A1AFB593666C658BF5C315B3E3F3&User=ZTGYL&Password=123456&len=1";
         String res = GetBillcodeforSTO.sendPost(url2, param2);
 
         
         res = res.replaceAll("\\\\\"", "");
         res = res.replaceAll("\"", "");
         String[] str = res.replaceAll("}", "").split("-");
         String MailNo = "";
         if (str.length > 1) {
           byte b1; int j; String[] arrayOfString; for (j = (arrayOfString = str).length, b1 = 0; b1 < j; ) { String s = arrayOfString[b1];
             MailNo = s; b1++; }
           
           OrderMail om = new OrderMail();
           om.setMailNo(MailNo);
           om.setId(Integer.parseInt(id));
           this.orderMailDao.inztsf(om);
         } 
       } else if (orderMail.getCarrier().equals("SF")) {
 
         
         Element root = DocumentHelper.createElement("Request");
         Document document = DocumentHelper.createDocument(root);
         String Province = orderMail.getReceiveProvince();
 
 
         
         Element element1 = root.addElement("Header");
         element1.addElement("PartnerID").addText("ISP");
         element1.addElement("PartnerToken").addText("ISP");
         element1.addElement("VersionID").addText("V1.0");
         element1.addElement("DocumentType").addText("OrderService");
         element1.addElement("SenderID").addText("ISP");
         element1.addElement("ReceiverID").addText("SFCP");
         element1.addElement("Timestamp").addText(df.format(new Date()));
         element1.addElement("RequestID").addText("202150910161444212492");
         element1.addElement("Language").addText("zh-CN");
         element1.addElement("TimeZone").addText("+08:00");
         
         Element element2 = root.addElement("Body");
         Element element3 = element2.addElement("OrderList");
         Element element4 = element3.addElement("Order");
         element4.addElement("OrderNo").addText(orderMail.getTxLogisticID());
 
         
         if (Province.indexOf("江苏") >= 0 || Province.indexOf("浙江") >= 0 || Province.indexOf("上海") >= 0 || Province.indexOf("安徽") >= 0) {
           element4.addElement("ExpressServiceCode").addText("37");
         } else {
           
           element4.addElement("ExpressServiceCode").addText("38");
         } 
         
         element4.addElement("IsGenMailNo").addText("1");
         element4.addElement("ParcelQuantity").addText("1");
         element4.addElement("MailNo").addText("");
         element4.addElement("TotalWeight").addText("3.0");
         element4.addElement("TotalVolume").addText("100.0");
         element4.addElement("PaymentMethod").addText("1");
         element4.addElement("PaymentType").addText("1");
         element4.addElement("PaymentAccountNo").addText("5716000265");
         element4.addElement("IsDoCall").addText("0");
         element4.addElement("NeedReturnTrackingNo").addText("0");
         element4.addElement("ReturnTrackingNo").addText("");
         element4.addElement("PickupMethod").addText("1");
         element4.addElement("DeliveryMethod").addText("1");
         element4.addElement("RequestPickupTimeStart").addText(
             df.format(new Date()));
         element4.addElement("RequestPickupTimeEnd").addText(
             df.format(new Date()));
         element4.addElement("Remark").addText("");
         
         Element element5 = element4.addElement("AdditionalDataList");
         
         Element element61 = element5.addElement("AdditionalData");
         
         element61.addElement("Key").addText("cargoCount");
         element61.addElement("Value").addText("1");
         Element element62 = element5.addElement("AdditionalData");
         
         element62.addElement("Key").addText("NetWeight");
         element62.addElement("Value").addText("1");
         Element element63 = element5.addElement("AdditionalData");
         
         element63.addElement("Key").addText("CBDateTime");
         element63.addElement("Value").addText(df.format(new Date()));
         Element element64 = element5.addElement("AdditionalData");
         
         element64.addElement("Key").addText("Freight");
         element64.addElement("Value").addText("10");
         Element element65 = element5.addElement("AdditionalData");
         
         element65.addElement("Key").addText("insuredFee");
         element65.addElement("Value").addText("10");
         Element element66 = element5.addElement("AdditionalData");
         
         element66.addElement("Key").addText("e-commerceCode");
         element66.addElement("Value").addText("abc12345");
         Element element67 = element5.addElement("AdditionalData");
         element67.addElement("Key").addText("cebFlag");
         element67.addElement("Value").addText("03");
         
         Element element7 = element4.addElement("ShipFromAddress");
         
         element7.addElement("CompanyName").addText("");
         element7.addElement("Contact").addText("曌通供应链");
         element7.addElement("SFStoreCode").addText("");
         element7.addElement("Telephone").addText("89776977");
         element7.addElement("Mobile").addText("");
         element7.addElement("Email").addText("");
         element7.addElement("CountryCode").addText("CN");
         element7.addElement("StateOrProvince").addText("浙江省");
         element7.addElement("City").addText("杭州市");
         element7.addElement("County").addText("萧山区");
         element7.addElement("AddressLine1").addText("B型保税区");
         element7.addElement("AddressLine2").addText("");
         element7.addElement("ShipperCode").addText("");
         element7.addElement("PostalCode").addText("");
         element7.addElement("AdditionalDataList").addText("");
         
         Element element10 = element4.addElement("ShipToAddress");
 
         
         element10.addElement("CompanyName").addText("");
         element10.addElement("Contact").addText(orderMail.getReceiveMan());
         element10.addElement("SFStoreCode").addText("");
         element10.addElement("Telephone").addText("");
         element10.addElement("Mobile").addText(
             orderMail.getReceiveManPhone());
         element10.addElement("Email").addText("");
         element10.addElement("CountryCode").addText("CN");
         element10.addElement("StateOrProvince").addText(
             orderMail.getReceiveProvince());
         element10.addElement("City").addText(orderMail.getReceiveCity());
         element10.addElement("County").addText(orderMail.getTradeCountry());
         element10.addElement("AddressLine1").addText(
             orderMail.getReceiveManAddress());
         element10.addElement("AddressLine2").addText("");
         element10.addElement("DeliveryCode").addText("");
         element10.addElement("PostalCode").addText("");
         element10.addElement("AdditionalDataList").addText("");
 
         
         Element element16 = element4.addElement("Parcels");
         
         element16.addElement("PackageType").addText("");
         element16.addElement("Length").addText("0");
         element16.addElement("Width").addText("0");
         element16.addElement("Height").addText("0");
         element16.addElement("Weight").addText("0");
         element16.addElement("Volume").addText("0");
         element16.addElement("TempRange").addText("1");
 
         
         Element element23 = element4.addElement("CustomsItem");
         
         element23.addElement("CustomsBatchs").addText("2017-03-10");
         
         element23.addElement("InProcessWaybillNo").addText("");
         element23.addElement("DeclaredValueCurrencyCode").addText("CNY");
         element23.addElement("DeclaredValue").addText("99.000");
         element23.addElement("TaxAccountNo").addText("");
         element23.addElement("TaxPaymentType").addText("1");
         
         element23.addElement("TaxSettlementAccount").addText("");
         element23.addElement("AdditionalDataList").addText("");
 
         
         try {
           OrderMail om = new OrderMail();
           String re = "";
           Pattern p = Pattern.compile("<Head>(.*)</Head>");
           Matcher m = p.matcher(re);
           String head = "";
           while (m.find()) {
             head = m.group(1);
           }
           
           if ("OK".equals(head)) {
             Pattern p1 = Pattern.compile("<MailNo>(.*)</MailNo>");
             Matcher m1 = p1.matcher(re);
             while (m1.find()) {
               String MailNo = m1.group(1);
               
               om.setMailNo(MailNo);
             } 
             Pattern p2 = 
               Pattern.compile("<OriginCode>(.*)</OriginCode>");
             Matcher m2 = p2.matcher(re);
             while (m2.find()) {
               String OriginCode = m2.group(1);
               
               om.setBillProvideSiteCode(OriginCode);
             } 
             
             Pattern p3 = Pattern.compile("<DestCode>(.*)</DestCode>");
             Matcher m3 = p3.matcher(re);
             while (m3.find()) {
               String DestCode = m3.group(1);
               
               om.setMarkDestination(DestCode);
             } 
             
             om.setId(Integer.parseInt(id));
             this.orderMailDao.inztsf(om);
           
           }
           else if ("ERR".equals(head)) {
             Pattern p4 = Pattern.compile("<ERROR(.*)</ERROR>");
             Matcher m4 = p4.matcher(re);
             while (m4.find()) {
               String str = m4.group(1);
             }
           }
         
         }
         catch (Exception e) {
           e.printStackTrace();
           return -1;
         } 
       }  b++; }
     
     return result;
   }
 
   
   public void inztsf(OrderMail om) {
     this.orderMailDao.inztsf(om);
   }
 
   
   public int editMatterm(OrderMail orderMail) {
     return this.orderMailDao.editMatterm(orderMail);
   }
 
   
   public int push3(String ids) {
     if (updateIsCustoms(ids) == 0) {
       return 0;
     }
     int i = pushDd(ids);
     if (i == 0) {
       return 0;
     }
     int j = pushQd(ids);
     if (j == 0) {
       return 0;
     }
     return 1;
   }
 
   
   public int updateIspostToYf(String ids) throws Exception {
     String[] idArr = ids.split(",");
     int result = -1;
     List<OrderMail> orderMailList = this.orderMailDao.findByIdArr(idArr);
     if (orderMailList != null && orderMailList.size() > 0) {
       List<OrderMailSku> orderMailSkuList = this.orderMailSkuDao.findByTidList(orderMailList);
       Map<String, List<OrderMailSku>> omsMap = new HashMap<>();
       for (OrderMailSku orderMailSku : orderMailSkuList) {
         List<OrderMailSku> obsList = omsMap.get(orderMailSku.getTxLogisticID());
         if (obsList != null && obsList.size() > 0) {
           obsList.add(orderMailSku);
           omsMap.put(orderMailSku.getTxLogisticID(), obsList); continue;
         } 
         List<OrderMailSku> obsListNew = new ArrayList<>();
         obsListNew.add(orderMailSku);
         omsMap.put(orderMailSku.getTxLogisticID(), obsListNew);
       } 
       
       System.out.println(omsMap.size());
 
       
       for (OrderMail om : orderMailList) {
         JSONObject orderMailJson = new JSONObject();
         orderMailJson.put("orderno", om.getTxLogisticID());
         SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
         orderMailJson.put("orderdatetime", f.format(new Date()));
         Double worth = Double.valueOf(om.getWorth().doubleValue() * 100.0D);
         orderMailJson.put("total_fee", Integer.valueOf(worth.intValue()));
         orderMailJson.put("goods_fee", Integer.valueOf(worth.intValue()));
         orderMailJson.put("tax_fee", "0");
         orderMailJson.put("payer_name", om.getBuyerName());
         orderMailJson.put("paper_number", om.getBuyerIdNumber());
         
         JSONArray skuArray = new JSONArray();
         for (OrderMailSku oms : omsMap.get(om.getTxLogisticID())) {
           JSONObject skuJson = new JSONObject();
           skuJson.put("goods_name", oms.getItemName());
           Double unitPrice = Double.valueOf(oms.getUnitPrice().doubleValue() * 100.0D);
           skuJson.put("price", Integer.valueOf(unitPrice.intValue()));
           skuJson.put("qty", oms.getItemCount());
           skuJson.put("tax_fee", "0");
           skuArray.add(skuJson);
         } 
         orderMailJson.put("goodsList", skuArray);
 
 
         
         System.out.println(orderMailJson.toString());
         String ret = PushToYf.sendPost(Base64.encodeBase64String(orderMailJson.toString().getBytes("utf-8")));
         System.out.println("sada=" + ret);
         if (ret.contains("推送成功")) {
           JSONObject retJson = JSONObject.parseObject(ret);
           
           OrderMail orderMail = new OrderMail();
           orderMail.setId(om.getId());
           orderMail.setIspost(1);
           orderMail.setPayNumber(retJson.getString("detail"));
           result = this.orderMailDao.updateIspostForYF(orderMail);
         } 
       } 
     } 
     
     return result;
   }
 
   
   public int updateReturnCode(OrderMail om) {
     return this.orderMailDao.updateReturnCode(om);
   }
 
 
 
   
   public int pushOrderToDsds(String ids) throws Exception {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderMailPush orderMailPush = this.orderMailDao.findById(
           Integer.valueOf(Integer.parseInt(id)));
       orderMailPush.setConsignee(orderMailPush.getBuyerName());
       List<OrderMailPushSku> goodsList = this.orderMailSkuDao
         .findByTxLogisticID(orderMailPush.getOrderSn());
       
       orderMailPush.setGoodsList(goodsList);
       String ret = ShopOrderCreateSample.push(orderMailPush);
       if (ret.contains(orderMailPush.getOrderSn())) {
         OrderMail orderMail = new OrderMail();
         orderMail.setId(Integer.parseInt(id));
         orderMail.setIspost(1);
         result = this.orderMailDao.updateIspost(orderMail);
       }  b++; }
     
     return result;
   }
 
   
   public int findException() {
     return this.orderMailDao.findException();
   }
 
   
   public void updateAuditstatus0(int[] ids) {
     this.orderMailDao.updateAuditstatus0(ids);
   }
 
 
   
   public int payNumberTL(String ids) {
     String[] idArr = ids.split(",");
     int result = -1;
     SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss"); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       try {
         OrderMail orderMail = this.orderMailDao.findByIdToBG(
             Integer.valueOf(Integer.parseInt(id)));
         Double worth = Double.valueOf(orderMail.getWorth().doubleValue() * 100.0D);
 
         
         String jm = "<txInfo><merchantId>109123456123456</merchantId><version>v5.2</version><payType>31</payType><signType>0</signType><charset>1</charset><orderNo>" + 
           orderMail.getTxLogisticID() + "</orderNo><orderDatetime>" + f.format(new Date()) + "</orderDatetime><customsInfo><CUSTOMS_TYPE>HG021</CUSTOMS_TYPE><ESHOP_ENT_CODE>3301560066</ESHOP_ENT_CODE><ESHOP_ENT_NAME>杭州潮购网络科技有限公司</ESHOP_ENT_NAME><BIZ_TYPE_CODE></BIZ_TYPE_CODE><APP_UID></APP_UID><APP_UNAME></APP_UNAME>" + 
           "<TOTAL_FEE>" + worth.intValue() + "</TOTAL_FEE><GOODS_FEE>" + worth.intValue() + "</GOODS_FEE><TAX_FEE>0</TAX_FEE><FREIGHT_FEE>0</FREIGHT_FEE><OTHER_FEE>0</OTHER_FEE><IETYPE>E</IETYPE>" + 
           "<ORIGINAL_ORDER_NO>" + orderMail.getTxLogisticID() + "</ORIGINAL_ORDER_NO><PAY_TIME>" + f.format(new Date()) + "</PAY_TIME><CURRENCY>156</CURRENCY>" + 
           "<PAYER_NAME>" + orderMail.getBuyerName() + "</PAYER_NAME><PAPER_TYPE>01</PAPER_TYPE><PAPER_NUMBER>" + orderMail.getBuyerIdNumber() + "</PAPER_NUMBER><PAPER_PHONE>" + orderMail.getReceiveManPhone() + "</PAPER_PHONE><PAPER_EMAIL>aaa@123.com</PAPER_EMAIL><PAY_BANK_NAME></PAY_BANK_NAME><PAY_BANK_CODE></PAY_BANK_CODE><PAY_BANK_SERIALNO></PAY_BANK_SERIALNO><PAYER_COUNTRY_CODE></PAYER_COUNTRY_CODE><PAYER_ADDRESS></PAYER_ADDRESS><PAYER_SEX></PAYER_SEX><PAYER_BIRTHDAY></PAYER_BIRTHDAY><CHECK_ECP_CODE></CHECK_ECP_CODE><CHECK_ECP_NAME></CHECK_ECP_NAME><ORG_CODE></ORG_CODE><PAY_CARD_NO></PAY_CARD_NO><SHIPPER_NAME></SHIPPER_NAME><IS_CHECK><IS_CHECK><MEMO>报关备注2</MEMO></customsInfo><remark>测试报文</remark></txInfo>";
         String signMsg = MD5Util.MD5(String.valueOf(jm) + "<key>1234567890</key>");
         String xml = "<body>" + jm + "<signMsg>" + signMsg + "</signMsg></body>";
         System.out.println("xml=" + xml);
         System.out.println("--------匹配通联signMsg-->" + signMsg);
         
         String verificationText = Base64.encodeBase64String(xml.getBytes("UTF-8"));
         verificationText = new String(verificationText.getBytes(), "UTF-8");
         String ret = PayNumberUtil.getFromTL(verificationText);
         System.out.println("result2====" + new String(Base64.decodeBase64(ret), "UTF-8"));
 
         
         String returnBody = new String(Base64.decodeBase64(ret), "UTF-8");
         
         String recode = returnBody.substring(returnBody.indexOf("retCode>") + 8, returnBody.lastIndexOf("</retCode"));
         System.out.println(recode);
         if (recode.equals("0000")) {
           String payNumber = returnBody.substring(returnBody.indexOf("paymentOrderNo>") + 15, returnBody.lastIndexOf("</paymentOrderNo"));
           System.out.println(payNumber);
           
           orderMail.setIspost(1);
           orderMail.setPayNumber(payNumber);
           result = this.orderMailDao.payNumber(orderMail);
 
         
         }
 
       
       }
       catch (Exception e) {
         e.printStackTrace();
       } 
       
       b++; }
     
     return result;
   }
 
   
   public List<OrderMailForExport> exportMailByIds(String ids) {
     return this.orderMailDao.exportMailByIds(ids);
   }
 
 
   
   public List<OrderMail> findByIds(int[] ids) {
     return this.orderMailDao.findByIds(ids);
   }
 
 
   
   public int updateByIds(int[] ids) {
     return this.orderMailDao.updateByIds(ids);
   }
 
 
   
   public List<OrderMail> findtxLogisticID(int[] ids) {
     return this.orderMailDao.findtxLogisticID(ids);
   }
 
 
   
   public List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcels(String txLogisticID) {
     return this.orderMailDao.findBytxLogisticIDForYtoExcels(txLogisticID);
   }
 
 
   
   public List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcelById(int[] ids) {
     return this.orderMailDao.findBytxLogisticIDForYtoExcelById(ids);
   }
 
 
   
   public int findByIdToalOrderMailNo(String toal) {
     return this.orderMailDao.findByIdToalOrderMailNo(toal);
   }
 
 
   
   public void updateToal(OrderMail orderMail) {
     this.orderMailDao.updateToal(orderMail);
   }
 
 
 
   
   public String pushBfb(String id, String no) throws Exception {
     String buffer = "";
     if (no.equals("1")) {
       buffer = pushBfbUtil(id, "330166K00A");
     } else if (no.equals("2")) {
       buffer = pushBfbUtil(id, "C011111100397078");
     } else if (no.equals("3")) {
       buffer = pushldUtil(id);
     } else {
       buffer = getBfbUtil(id);
     } 
     return buffer;
   }
 
   
   public String pushldUtil(String id) throws Exception {
     APIContext apiContext = new APIContext();
     String clientId = "";
     String clientSecret = "";
     String oauthUrl = "";
     String customscode = "";
     
     clientId = "f30090a3c96dd3e84e9113c5f71c54c8cc78bda7";
     clientSecret = "7dc7b6a56546a8768a1a40e7068b6dfe480ddf36";
     oauthUrl = "https://fx.soopay.net/cberest/v1/oauth/authorize";
     customscode = "330166K00A";
 
     
     String url = "https://fx.soopay.net/cberest/v1/declare_again";
     apiContext.setClientId(clientId);
     apiContext.setClientSecret(clientSecret);
     apiContext.setOauthUrl(oauthUrl);
     String token = ReqUMF.getOauth(apiContext);
     token = token.split(",")[0].split(":")[1];
     token = token.substring(1, token.length() - 1);
     System.out.println("token1=" + token);
     apiContext.setToken(token);
     apiContext.setCrtPath(crtPath);
     apiContext.setP8Path(p8Path);
     apiContext.setUMFp8Path(UMFp8Path);
     apiContext.setUrl(url);
     String[] idArr = id.split(","); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String ids = arrayOfString1[b];
       OrderMail orderMail = this.orderMailDao.findByIdToBG(Integer.valueOf(Integer.parseInt(ids)));
       String mer_reference_id = orderMail.getTxLogisticID();
       String json = "{\"mer_reference_id\":\"" + mer_reference_id + "\"," + 
         "\"declare_type\":\"" + Character.MIN_VALUE + "\"}";
       String response = UMFapi2.postRefund(apiContext, json);
       System.out.println("response-----" + response); b++; }
     
     return "success";
   }
   
   public String pushBfbUtil(String id, String platSupplierNo) throws Exception {
     APIContext apiContext = new APIContext();
     String clientId = "f30090a3c96dd3e84e9113c5f71c54c8cc78bda7";
     String clientSecret = "7dc7b6a56546a8768a1a40e7068b6dfe480ddf36";
     String oauthUrl = "https://fx.soopay.net/cberest/v1/oauth/authorize";
     String url = "https://fx.soopay.net/cberest/v1/apply_to_customes_only";
     apiContext.setClientId(clientId);
     apiContext.setClientSecret(clientSecret);
     apiContext.setOauthUrl(oauthUrl);
     String token = ReqUMF.getOauth(apiContext);
     token = token.split(",")[0].split(":")[1];
     token = token.substring(1, token.length() - 1);
     System.out.println("token1=" + token);
     apiContext.setToken(token);
     apiContext.setCrtPath(crtPath);
     apiContext.setP8Path(p8Path);
     apiContext.setUMFp8Path(UMFp8Path);
     apiContext.setUrl(url);
     String[] idArr = id.split(","); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String ids = arrayOfString1[b];
       OrderMail orderMail = this.orderMailDao.findByIdToBG(Integer.valueOf(Integer.parseInt(ids)));
       String name1 = UMFapi2.Sensitivity("张三", "utf-8");
       String mer_reference_id = orderMail.getTxLogisticID();
       String total = orderMail.getWorth().toString();
       String name = UMFapi2.Sensitivity(orderMail.getBuyerName(), "utf-8");
       String citizen_id_number = UMFapi2.Sensitivity(orderMail.getBuyerIdNumber(), "utf-8");
       String mer_pay_date = timenew.newtime3();
       String phone = orderMail.getReceiveManPhone();
       String tracking_number = "";
       if (orderMail.getCarrier().equals("SF")) {
         tracking_number = "顺丰 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("YTO")) {
         tracking_number = "圆通 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("STO")) {
         tracking_number = "申通 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("ZTO")) {
         tracking_number = "中通 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("YD")) {
         tracking_number = "韵达 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("HHTT")) {
         tracking_number = "海航天天 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("EMS")) {
         tracking_number = "EMS " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("ZJS")) {
         tracking_number = "宅急送 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("FEDEX")) {
         tracking_number = "联邦快递 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("DBL")) {
         tracking_number = "德邦物流 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("UC")) {
         tracking_number = "优速物流 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("YZPY")) {
         tracking_number = "邮政平邮 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("YFSD")) {
         tracking_number = "亚风 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("GTO")) {
         tracking_number = "国通 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("LBEX")) {
         tracking_number = "龙邦快递 " + orderMail.getMailNo();
       } else if (orderMail.getCarrier().equals("BST")) {
         tracking_number = "百世 " + orderMail.getMailNo();
       } else {
         tracking_number = "圆通 " + orderMail.getMailNo();
       } 
       String goods_info = "";
       
       List<OrderMailSku> orderMailSkuList = this.orderMailSkuDao
         .findById(orderMail.getTxLogisticID());
       for (OrderMailSku orderMailSku : orderMailSkuList) {
         goods_info = String.valueOf(orderMailSku.getItemName()) + "*" + orderMailSku.getItemCount() + ";";
       }
       String json = "{\"mer_reference_id\":\"" + mer_reference_id + "\"," + 
         "\"order_amount\":{\"total\":\"" + total + "\",\"currency\":\"CNY\"}," + 
         "\"name\":\"" + name + "\",\"citizen_id_type\":\"IDENTITY_CARD\"," + 
         "\"citizen_id_number\":\"" + citizen_id_number + "\"," + 
         "\"mer_pay_date\":\"" + mer_pay_date + "\",\"mer_pay_time\":\"000000\",\"phone\":\"" + phone + "\",\"customs_id\":\"HZHG\"," + 
         "\"mer_customs_code\":\"330166K00A\",\"ec_plat_id\":\"330166K00A\"," + 
         "\"freight_amount\":{\"total\":\"0.00\",\"currency\":\"CNY\"}," + 
         "\"tax_amount\":{\"total\":\"0.00\",\"currency\":\"CNY\"}," + 
         "\"paye_code\":\"C011111100394175\"," + 
         "\"goods_info\":\"" + goods_info + "\",\"tracking_number\":\"" + tracking_number + "\"}";
 
 
 
       
       String response = UMFapi2.postRefund(apiContext, json);
       System.out.println("-----" + response);
       if (response.indexOf("successful") != -1) {
         JSONObject obj = JSONObject.parseObject(response);
         JSONObject obj2 = obj.getJSONObject("customes_declaration_only");
         String pay_trace = (String)obj2.get("pay_trace");
         
         OrderMail orderBonded1 = this.orderMailDao.findByTxLogisticID(orderMail.getTxLogisticID());
         orderBonded1.setPayNumber(pay_trace);
         this.orderMailDao.updateByBfb(orderBonded1);
         
         this.orderMailDao.updateptzt_2(ids);
         OrderDs ds = new OrderDs();
         ds.setCreatetime(timenew.newtime4());
         ds.setLx("9610");
         ds.setMailno(orderMail.getMailNo());
         ds.setTxlogisticid(orderMail.getTxLogisticID());
         ds.setPayback(response);
         ds.setPayinfo(json.toString());
         this.orderDsDao.insert(ds);
       }       
       b++; }
     
     return "success";
   }
   
   public String getBfbUtil(String id) throws Exception {
     System.out.println("cs2=diao++++++++++++++++++++++++");
     
     KeystoreSignProvider keystoreSignProvider = new KeystoreSignProvider("PKCS12", "E:\\pfx1.pfx", 
         this.pftaes.toCharArray(), null, this.pftaes.toCharArray());
     service = new CbcsService((SignProvider)keystoreSignProvider, "10.36.160.23", "zh_CN", "E:\\pfx1.pfx");
     service.setSftpAccountName(this.merchantId);
     service.setSftpPassword("admin1234");
     service.setSftpDomain("sftp.kftpay.com.cn");
     service.setConnectionTimeoutSeconds(Integer.valueOf(30));
     
     kftService = new KftService((SignProvider)keystoreSignProvider, "10.36.160.23", "zh_CN", "E:\\pfx1.pfx");
     kftService.setConnectionTimeoutSeconds(Integer.valueOf(30));
     
     String[] idArr = id.split(","); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String ids = arrayOfString1[b];
       OrderMail orderBonded = this.orderMailDao.findByIdToBG(Integer.valueOf(Integer.parseInt(ids)));
       CustomsDeclaredReqDTO dto = new CustomsDeclaredReqDTO();
       dto.setService("cbcs_common_customs_declare");
       dto.setVersion("1.0.0-PRD");
       dto.setMerchantId(this.merchantId);
       
       List<ReportCustomInfoDTO> list = new ArrayList<>();
       ReportCustomInfoDTO info = new ReportCustomInfoDTO();
       BigDecimal worth = new BigDecimal(orderBonded.getWorth().toString());
       BigDecimal js = new BigDecimal("100");
       BigDecimal number = worth.multiply(js);
       BigDecimal newNumber = number.setScale(0, 4);
       info.setAmount(newNumber.toString());
       info.setCustom(this.custom);
       info.setMerchantOrderNo(orderBonded.getTxLogisticID());
       info.setMerchantSn(orderBonded.getTxLogisticID());
       info.setRegisterMerchantSn(orderBonded.getTxLogisticID());
       info.setRemark("备注1");
       info.setFunctionCode("C");
       info.setGoodsAmount(newNumber.toString());
       info.setFee("0");
       info.setTransportAmount("0");
       info.setCustomCode("6861");
       list.add(info);
       
       dto.setReportCustomInfos(list);
       System.out.println("请求信息为：" + dto);
       CustomsDeclaredQueryRespDTO result = service.customsDeclared(dto);
       System.out.println("响应信息为：" + JSON.toJSONString(result));
       
       JSONObject obj = JSONObject.parseObject(JSON.toJSONString(result));
       String status = (String)obj.get("status");
       if (status.equals("1")) {
         System.out.println("响应信息为：" + obj.get("reportCustomResponses"));
         JSONArray obj2 = obj.getJSONArray("reportCustomResponses");
         for (int j = 0; j < obj2.size(); j++) {
           JSONObject object = (JSONObject)obj2.get(j);
           String paySubOrderNo = (String)object.get("paySubOrderNo");
           System.out.println("响应信息为：" + paySubOrderNo);
           OrderMail orderBonded1 = this.orderMailDao.findByTxLogisticID(orderBonded.getTxLogisticID());
           orderBonded1.setPayNumber(paySubOrderNo);
           this.orderMailDao.updateByBfb(orderBonded1);
         } 
       } 
       b++; }
     
     return "success";
   }
 
 
   
   public Integer findOrderToday() {
     return this.orderMailDao.findOrderToday();
   }
 
   
   public int findOrderYear() {
     return this.orderMailDao.findOrderYear();
   }
 
   
   public int findExceptionOrderYear() {
     return this.orderMailDao.findExceptionOrderYear();
   }
 
   
   public String findTotalSumYear() {
     return this.orderMailDao.findTotalSumYear();
   }
 
   
   public int findLastYearOrderNumber() {
     return this.orderMailDao.findLastYearOrderNumber();
   }
 
   
   public int findLastMonthOrderNumber() {
     return this.orderMailDao.findLastMonthOrderNumber();
   }
 
   
   public String findLastMonthTotalSum() {
     return this.orderMailDao.findLastMonthTotalSum();
   }
 
   
   public List<EchattsBonded> findbonded() {
     return this.orderMailDao.findbonded();
   }
 
 
   
   public List<EchattsBonded> findMail() {
     return this.orderMailDao.findMail();
   }
 
 
   
   public List<OrderMailForExport> exportGD(OrderMail orderMail) {
     return this.orderMailDao.exportGD(orderMail);
   }
   
   public int goBackOrder(String[] ids) {
     return this.orderMailDao.goBackOrder(ids);
   }
 }


