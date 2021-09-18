 package com.what21.service.impl;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.ShopOrderCreateLine;
 import com.iwilley.b1ec2.api.request.ShopOrderCreateRequest;
 import com.iwilley.b1ec2.api.response.ShopOrderCreateResponse;
 import com.iwilley.b1ec2.sample.ShopOrderCreateSample;
 import com.lycheepay.gateway.client.CbcsService;
 import com.lycheepay.gateway.client.KftService;
 import com.what21.dao.CarrierMailnoDao;
 import com.what21.dao.CompanyDao;
 import com.what21.dao.GoodsAcceptDao;
 import com.what21.dao.ItemDao;
 import com.what21.dao.Log_inventoryDao;
 import com.what21.dao.OrderAcceptDao;
 import com.what21.dao.OrderBondDao;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.OrderBondedRuleDao;
 import com.what21.dao.OrderBondedSkuDao;
 import com.what21.dao.OrderDsDao;
 import com.what21.dao.SzqgAgreementDao;
 import com.what21.dao.TimedTaskDao;
 import com.what21.dao.TinventoryDao;
 import com.what21.model.Collect;
 import com.what21.model.Company;
 import com.what21.model.GoodsAccept;
 import com.what21.model.IdCard;
 import com.what21.model.Item;
 import com.what21.model.Log_inventory;
 import com.what21.model.OrderAccept;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedForExport;
 import com.what21.model.OrderBondedPush;
 import com.what21.model.OrderBondedPushSku;
 import com.what21.model.OrderBondedRule;
 import com.what21.model.OrderBondedSku;
 import com.what21.model.OrderPush3;
 import com.what21.model.SzqgAgreement;
 import com.what21.model.TCarrierMailno;
 import com.what21.model.Tinventory;
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.service.OrderBondedService;
 import com.what21.tools.Tools;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.what21.util.MD5Util;
 import com.what21.util.timenew;
 import com.zt.ewtp.hcszqgpost;
 import com.zt.kjybd.CheckPhone;
 import com.zt.kjybd.GetBillcodeforSTO;
 import com.zt.kjybd.IDCardUtil;
 import com.zt.kjybd.MatchMailNo;
 import com.zt.kjybd.PushToGY;
 import com.zt.kjybd.PushToSTO;
 import com.zt.kjybd.PushToWms;
 import com.zt.kjybd.PushtoBG_yd;
 import com.zt.kjybd.PushtoWQ;
 import com.zt.kjybd.newzs;
 import com.zt.request.GetPayNumber;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.UnsupportedEncodingException;
 import java.math.BigDecimal;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.SignatureException;
 import java.security.spec.InvalidKeySpecException;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
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
 public class OrderBondedServiceImpl
   implements OrderBondedService
 {
   @Autowired
   public OrderBondedDao orderBondedDao;
   @Autowired
   public OrderBondDao orderBondDao;
   @Autowired
   public OrderBondedSkuDao orderBondedSkuDao;
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
   @Autowired
   private TimedTaskDao timedTaskDao;
   @Autowired
   private OrderBondedRuleDao orderBondedRuleDao;
   @Autowired
   public SzqgAgreementDao szqgagreementdao;
   @Autowired
   private CarrierMailnoDao carrierMailnoDao;
   @Autowired
   public OrderDsDao orderDsDao;
   
   public List<OrderBonded> findAll(Map<String, Object> map) {
     return this.orderBondedDao.findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.orderBondedDao.count(pageMap);
   }
 
 
   
   String YTO_URL = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_URL.getValue());
   String YTO_CLIENTID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_CLIENTID.getValue());
   String YTO_PARTNERID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_PARTNERID.getValue());
   String YTO_NAME = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_NAME.getValue());
   String YTO_PHONE = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_PHONE.getValue());
   String YTO_POSTCODE = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_POSTCODE.getValue());
   String YTO_PROV = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_PROV.getValue());
   String YTO_CITY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_CITY.getValue());
   String YTO_ADDRESS = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.YTO_ADDRESS.getValue());
   
   String BILL_ENTERPRISE_NAME = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_ENTERPRISE_NAME.getValue());
   String BILL_CUSTOMS_CODE = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_CUSTOMS_CODE.getValue());
   String BILL_DXPID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_DXPID.getValue());
   
   String ORDER_ENTERPRISE_NAME = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_ENTERPRISE_NAME.getValue());
   String ORDER_CUSTOMS_CODE = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_CUSTOMS_CODE.getValue());
   String ORDER_DXPID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.ORDER_DXPID.getValue());
   
   String CKDM = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.CKDM.getValue());
 
   
   String BFBMERCHANTID = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BFBMERCHANTID.getValue());
   
   String BFBMERCHANTPATH = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BFBMERCHANTPATH.getValue());
   
   private static CbcsService service;
   
   private static KftService kftService;
   String merchantId = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.MERCHANTID.getValue());
   String pftaes = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.PFTAES.getValue());
   String custom = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.CUSTOM.getValue());
   
   private static String crtPath = "C://cert_2d59.crt";
   private static String p8Path = "C://52489_.key.p8";
   private static String UMFp8Path = "C://52489_.key.p8";
   private static Map<String, String> orderMap = new HashMap<>();
 
   
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
       OrderBonded isData = this.orderBondedDao.findByTxLogisticID(txLogisticID);
       if (isData == null) {
         OrderBonded orderBonded = new OrderBonded();
         orderBonded.setTxLogisticID(data1.toString());
         orderBonded.setReceiveMan(data2.toString());
         orderBonded.setReceiveProvince(data3.toString());
         orderBonded.setReceiveCity(data4.toString());
         orderBonded.setReceiveCounty(data5.toString());
         orderBonded.setReceiveManAddress(data6.toString());
         orderBonded.setReceiveManPhone(data7.toString());
         orderBonded.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString())));
         orderBonded.setWorth(Double.valueOf(data9.toString()));
         orderBonded.setItemName(data10.toString());
         orderBonded.setItemWeight(Double.valueOf(data11.toString()));
         orderBonded.setFeeAmount(Double.valueOf(data13.toString()));
         orderBonded.setInsureAmount(Double.valueOf(data14.toString()));
         orderBonded.setBuyerIdNumber(data15.toString());
         orderBonded.setBuyerName(data16.toString());
         orderBonded.setCarrier(data18.toString());
         orderBonded.setSendWarehouse(data19.toString());
         orderBonded.setMerchantNum(data20.toString());
         if (data21 != null) {
           orderBonded.setMailNo(data21.toString());
         }
         if (data22 != null) {
           orderBonded.setPc(data22.toString());
         }
         orderBonded.setUserId(userId);
         orderBonded.setIspost(0);
         orderBonded.setIsCustoms(0);
         orderBonded.setAuditstatus(0);
         this.orderBondedDao.insert(orderBonded);
       } else {
         Integer itemCount = Integer.valueOf(isData.getItemCount().intValue() + Integer.parseInt(data8.toString()));
         Double itemWeight = Double.valueOf(isData.getItemWeight().doubleValue() + Double.valueOf(data11.toString()).doubleValue());
         Double worth = Double.valueOf(isData.getWorth().doubleValue() + Double.valueOf(data9.toString()).doubleValue());
         isData.setItemCount(itemCount);
         isData.setItemWeight(itemWeight);
         isData.setWorth(worth);
         this.orderBondedDao.updateCountAndWeight(isData);
       } 
       OrderBondedSku orderBondedSku = new OrderBondedSku();
       orderBondedSku.setTxLogisticID(txLogisticID);
       orderBondedSku.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString())));
       orderBondedSku.setAllPrice(Double.valueOf(data9.toString()));
       orderBondedSku.setItemName(data10.toString());
       orderBondedSku.setItemWeight(Double.valueOf(data11.toString()));
       orderBondedSku.setUnitPrice(Double.valueOf(data12.toString()));
       orderBondedSku.setItemsku(data17.toString());
       this.orderBondedSkuDao.insert(orderBondedSku);
       
       successData++;
     } 
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
 
 
   
   public GeneralResult importOrderNew(String path, int userId) {
     Map<String, String> Dpcmap = new HashMap<>();
     Dpcmap.put("8809393384023", "PDD000000001");
     Dpcmap.put("8809393384009", "PDD000000002");
     Dpcmap.put("8809393383989", "PDD000000003");
     Dpcmap.put("8809393385877", "PDD000000004");
     Dpcmap.put("8809393385884", "PDD000000005");
     Dpcmap.put("8809393385785", "PDD000000006");
     Dpcmap.put("8809393385792", "PDD000000007");
     Dpcmap.put("8809393385587", "PDD000000008");
     Dpcmap.put("8809393385761", "PDD000000009");
     Dpcmap.put("8809393385778", "PDD000000010");
     Dpcmap.put("8809393386409", "PDD000000011");
     Dpcmap.put("8809393386416", "PDD000000012");
     Dpcmap.put("8809393385921", "PDD000000013");
     Dpcmap.put("8809393385938", "PDD000000014");
     Dpcmap.put("8809393383927", "PDD000000015");
     Dpcmap.put("8809393383934", "PDD000000016");
     Dpcmap.put("8809393383941", "PDD000000017");
     Dpcmap.put("8809393383965", "PDD000000018");
     Dpcmap.put("8809393383972", "PDD000000019");
     Dpcmap.put("8809393383958", "PDD000000020");
     Dpcmap.put("8809393385068", "PDD000000021");
     Dpcmap.put("8809393385075", "PDD000000022");
     Dpcmap.put("8809393385082", "PDD000000023");
     Dpcmap.put("8809393385099", "PDD000000024");
     Dpcmap.put("8809393385105", "PDD000000025");
     Dpcmap.put("8809393385112", "PDD000000026");
     Dpcmap.put("8809393386478", "PDD000000027");
     Dpcmap.put("8809393386157", "PDD000000028");
     Dpcmap.put("8809393386140", "PDD000000029");
     Dpcmap.put("8809393386133", "PDD000000030");
     Dpcmap.put("8809393384252", "PDD000000031");
     Dpcmap.put("8809393384269", "PDD000000032");
     Dpcmap.put("8809393386263", "PDD000000033");
     Dpcmap.put("8809393381701", "PDD000000034");
 
     
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     boolean firstRow = true;
     StringBuffer message = new StringBuffer();
     Map<String, OrderBonded> orderBondedMap = new HashMap<>();
     Map<String, OrderBondedSku> orderBondedSkuMap = new HashMap<>();
     List<OrderBondedSku> obsList = new ArrayList<>();
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
         message.append("导入第" + row + "行数据失败，失败原因：客户订单号没有输入！<br>");
         break;
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
       boolean mark = IDCardUtil.CheckCertificateNo(data15.toString().trim());
       if (!mark) {
         message.append("导入第" + row + "行数据失败，失败原因：身份证号码输入有误！<br>");
         data5 = "";
         
         break;
       }       
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("导入第" + row + "行数据失败，失败原因：购买人没有输入！<br>");
         break;
       } 
       if (data16.toString().contains("小姐") || data16.toString().contains("先生") || data16.toString().contains("(") || data16.toString().contains("(")) {
         message.append("导入第" + row + "行数据失败，失败原因：购买人姓名不能为小姐先生等等！<br>");
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
       Object data23 = nowRowData[22];
       Object data24 = nowRowData[23];
       
       BigDecimal itemCount = new BigDecimal(data8.toString().trim());
       BigDecimal unitPrice = new BigDecimal(data12.toString().trim());
       BigDecimal allPrice = new BigDecimal(data9.toString().trim());
       BigDecimal price = unitPrice.multiply(itemCount);
       
       if (!allPrice.equals(price)) {
         message.append("导入第" + row + "行数据失败，失败原因：商品单价乘以商品数量不等于商品总价！<br>");
         
         break;
       } 
       OrderBonded orderBonded = new OrderBonded();
       orderBonded.setTxLogisticID(data1.toString().trim());
       orderBonded.setReceiveMan(data2.toString().trim());
       orderBonded.setReceiveProvince(data3.toString().trim());
       orderBonded.setReceiveCity(data4.toString().trim());
       orderBonded.setReceiveCounty(data5.toString().trim());
       orderBonded.setReceiveManAddress(data6.toString().trim());
       orderBonded.setReceiveManPhone(data7.toString().trim());
       orderBonded.setItemName(data10.toString().trim());
       orderBonded.setFeeAmount(Double.valueOf(data13.toString().trim()));
       orderBonded.setInsureAmount(Double.valueOf(data14.toString().trim()));
       orderBonded.setBuyerIdNumber(data15.toString().trim().replaceAll("x", "X"));
       orderBonded.setBuyerName(data16.toString().trim());
       orderBonded.setCarrier(data18.toString().trim());
       orderBonded.setSendWarehouse(data19.toString().trim());
       orderBonded.setMerchantNum(data20.toString().trim());
       orderBonded.setMark(Integer.valueOf(1));
       
       if (data21 != null) {
         orderBonded.setMailNo(data21.toString().trim());
       }
       if (data22 != null) {
         orderBonded.setPc(data22.toString().trim());
       }
       if (data23 != null) {
         orderBonded.setPayNumber(data23.toString().trim());
       }
       if (data24 != null) {
         orderBonded.setOrdercode(data24.toString().trim());
       }
       orderBonded.setUserId(userId);
       orderBonded.setIspost(0);
       orderBonded.setIsCustoms(0);
       orderBonded.setAuditstatus(0);
       
       OrderBonded ob = orderBondedMap.get(txLogisticID);
       if (ob != null) {
         ob.setItemCount(Integer.valueOf(ob.getItemCount().intValue() + Integer.parseInt(data8.toString().trim())));
         ob.setWorth(Double.valueOf(ob.getWorth().doubleValue() + Double.valueOf(data9.toString().trim()).doubleValue()));
         ob.setItemWeight(Double.valueOf(ob.getItemWeight().doubleValue() + Double.valueOf(data11.toString().trim()).doubleValue()));
 
 
 
         
         double weight = ob.getItemWeight().doubleValue();
         OrderBondedRule orderBondedRule = new OrderBondedRule();
         orderBondedRule.setCarrier(ob.getCarrier());
         orderBondedRule.setProvince(ob.getReceiveProvince());
         orderBondedRule = this.orderBondedRuleDao.findByProvinceAndCarrier(orderBondedRule);
         if (orderBondedRule != null) {
           if (orderBondedRule.getWeight().doubleValue() == 0.0D) {
             ob.setAuditstatus(7);
           }
           else if (weight > orderBondedRule.getWeight().doubleValue()) {
             ob.setAuditstatus(7);
           } 
         }
 
         
         orderBondedMap.put(txLogisticID, ob);
       } else {
         orderBonded.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString().trim())));
         orderBonded.setWorth(Double.valueOf(data9.toString().trim()));
         orderBonded.setItemWeight(Double.valueOf(data11.toString().trim()));
 
 
 
         
         double weight = orderBonded.getItemWeight().doubleValue();
         OrderBondedRule orderBondedRule = new OrderBondedRule();
         orderBondedRule.setCarrier(orderBonded.getCarrier());
         orderBondedRule.setProvince(orderBonded.getReceiveProvince());
         orderBondedRule = this.orderBondedRuleDao.findByProvinceAndCarrier(orderBondedRule);
         if (orderBondedRule != null) {
           if (orderBondedRule.getWeight().doubleValue() == 0.0D) {
             orderBonded.setAuditstatus(7);
           }
           else if (weight > orderBondedRule.getWeight().doubleValue()) {
             orderBonded.setAuditstatus(7);
           } 
         }
         
         orderBondedMap.put(txLogisticID, orderBonded);
       } 
       
       OrderBondedSku orderBondedSku = new OrderBondedSku();
       orderBondedSku.setTxLogisticID(txLogisticID);
       orderBondedSku.setItemCount(Integer.valueOf(Integer.parseInt(data8.toString().trim())));
       orderBondedSku.setAllPrice(Double.valueOf(data9.toString().trim()));
       orderBondedSku.setItemName(data10.toString().trim());
       orderBondedSku.setItemWeight(Double.valueOf(data11.toString().trim()));
       orderBondedSku.setUnitPrice(Double.valueOf(data12.toString().trim()));
       System.out.println("17=" + data17.toString().trim());
       if (data20.toString().equals("DPC")) {
         orderBondedSku.setItemsku(Dpcmap.get(data17.toString().trim()));
 
       
       }
       else {
 
         
         orderBondedSku.setItemsku(data17.toString().trim());
       } 
 
       
       OrderBondedSku obs = orderBondedSkuMap.get(String.valueOf(txLogisticID) + data17.toString().trim());
       if (obs != null) {
         message.append("导入第" + row + "行数据失败，失败原因：同一个订单有相同的商品SKU！<br>");
         break;
       } 
       orderBondedSkuMap.put(String.valueOf(txLogisticID) + data17.toString().trim(), orderBondedSku);
       obsList.add(orderBondedSku);
     } 
     if (message.toString().contains("导入")) {
       result.setMessage(message.toString());
       return result;
     } 
     Object[] obArr = orderBondedMap.values().toArray();
     List<OrderBonded> retList = this.orderBondedDao.findByObArr(obArr);
     if (retList != null && retList.size() > 0) {
       String retTxLogisticID = "";
       for (OrderBonded retOb : retList) {
         retTxLogisticID = String.valueOf(retTxLogisticID) + retOb.getTxLogisticID() + ",";
       }
       retTxLogisticID = retTxLogisticID.substring(0, retTxLogisticID.length() - 1);
       message.append("导入数据失败，失败原因：订单号（" + retTxLogisticID + "）已存在！<br>");
     } 
     if (message.toString().length() > 0) {
       result.setMessage(message.toString());
       return result;
     } 
     this.orderBondedDao.batchInsert(obArr);
     int count = this.orderBondedSkuDao.batchInsert(obsList);
     result.setMessage("导入成功" + count + "条！");
     return result;
   }
 
 
   
   public int deleteOrderBonded(String[] ids) {
     return this.orderBondedDao.delete(ids);
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
       
       String merchantNum = orderJSONObject.getString("merchantNum");
       String worth = orderJSONObject.getString("worth");
       String name = orderJSONObject.getString("buyerName");
       String nameId = orderJSONObject.getString("buyerIdNumber");
       
       OrderBonded orderBonded = new OrderBonded();
       orderBonded.setId(id.intValue());
       Item item = new Item();
       Tinventory tinventory = new Tinventory();
       Log_inventory log_inventory = new Log_inventory();
       List<OrderBondedSku> Bsku = this.orderBondedSkuDao.findById(txLogisticID);
       
       int one = 1;
       
       if (one != 11) {
         String preEntryNumber = "YW1210" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
         orderBonded.setPreEntryNumber(preEntryNumber);
         
         if (mailNo == null || mailNo.equals("") || mailNo.equals("undefined")) {
           orderBonded.setAuditstatus(1);
         } else {
           orderBonded.setAuditstatus(2);
         } 
       } 
 
       
       this.orderBondedDao.updateAuditstatus(orderBonded);
     } 
     return result;
   }
   
   public int updateExpressParam(String ids) {
     SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
     String date = df2.format(new Date());
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       int parseInt = Integer.parseInt(id);
       OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(parseInt));
       
       if (!orderBonded.getCarrier().equals("HTO"))
       {
 
         
         if (!orderBonded.getCarrier().equals("YTO"))
         {
           
           if (orderBonded.getCarrier().equals("STO"))
           
           { 
             if (this.CKDM.equals("LC"))
             
             { JSONObject content = new JSONObject();
               
               JSONObject order = new JSONObject();
               order.put("referenceNumber", orderBonded.getTxLogisticID());
               order.put("trackingNumber", "");
               order.put("terminalCode", "TTWD");
               order.put("warehouseCode", "TTCK");
               order.put("productCode", "LTCNS");
               order.put("weight", "1");
               order.put("weightUnit", "KG");
               order.put("remark", "");
               content.put("order", order);
               
               JSONObject shipper = new JSONObject();
               shipper.put("shipperName", "义乌海仓");
               shipper.put("shipperCountry", "CN");
               shipper.put("shipperCompany", "");
               shipper.put("shipperPhone", "15988108888");
               shipper.put("shipperProvince", "");
               shipper.put("shipperCity", "");
               shipper.put("shipperDistrict", "");
               shipper.put("shipperAddress", "义乌保税");
               content.put("shipper", shipper);
               
               JSONObject consignee = new JSONObject();
               consignee.put("consigneeName", orderBonded.getReceiveMan());
               consignee.put("idcard", "");
               consignee.put("consigneeCountry", "CN");
               consignee.put("consigneePhone", orderBonded.getReceiveManPhone());
               consignee.put("consigneeProvince", orderBonded.getReceiveProvince());
               consignee.put("consigneeCity", orderBonded.getReceiveCity());
               consignee.put("consigneeDistrict", orderBonded.getReceiveCounty());
               consignee.put("consigneeAddress", orderBonded.getReceiveManAddress());
               content.put("consignee", consignee);
               
               JSONArray jsonArray = new JSONArray();
               JSONObject goods = new JSONObject();
               goods.put("name", orderBonded.getItemName());
               goods.put("qty", orderBonded.getItemCount());
               goods.put("price", orderBonded.getWorth());
               goods.put("sku", "");
               goods.put("remark", "");
               jsonArray.add(goods);
               content.put("goods", jsonArray);
               System.out.println("content=" + content.toJSONString());
 
               
               String appToken = "TESTC78C-7923-404C-82CF-CD881539123C";
               String serviceMethod = "createorder";
               String paramsJson = content.toJSONString();
               String signature = "";
               String endTime = timenew.newtime4();
               
               String timestamp = (new StringBuilder(String.valueOf((int)(System.currentTimeMillis() / 1000L)))).toString();
               
               String mdStr = String.valueOf(timestamp) + appToken + paramsJson;
 
 
 
               
               System.out.println("mdStr=" + mdStr);
               
               signature = MD5Util.MD5S_forbm(mdStr, "utf-8");
 
               
               System.out.println("signature=" + signature);
               
               String url3 = "http://ldp.uat.stosolution.com/api/service/entrance";
               String parm3 = "appToken=" + appToken + "&serviceMethod=" + serviceMethod + "&paramsJson=" + URLEncoder.encode(paramsJson) + "&signature=" + signature + "&timestamp=" + timestamp;
               System.out.println("parm3=" + parm3);
               String resout3 = PushtoWQ.sendPost2(url3, parm3);
               System.out.println("resout3=" + resout3);
               JSONObject jsonObject = JSONObject.parseObject(resout3);
               String msg = jsonObject.get("msg").toString();
               
               if ("订单创建成功".equals(msg)) {
                 JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get("data").toString());
                 String bigchar = jsonObject2.get("bigchar").toString();
                 String trackingNumber = jsonObject2.get("trackingNumber").toString();
                 String packageName = jsonObject2.get("packageName").toString();
                 String labelFile = jsonObject2.get("labelFile").toString();
                 orderBonded.setMailNo(trackingNumber);
                 orderBonded.setBillProvideSiteCode(bigchar);
                 orderBonded.setMarkDestination(packageName);
                 orderBonded.setBillProvideSiteName(labelFile);
               } else {
                 System.out.println("获取申通 接口返回:" + msg);
               }  }
             else
             
             { JSONObject content = new JSONObject();
               content.put("orderNo", orderBonded.getTxLogisticID());
               content.put("orderSource", "VIPEO");
               content.put("billType", "00");
               content.put("orderType", "01");
               
               JSONObject sender = new JSONObject();
               sender.put("name", "义乌保税物流中心");
               sender.put("tel", "0571-87632363");
               sender.put("mobile", "");
               sender.put("postCode", "");
               sender.put("country", "");
               sender.put("province", "浙江省");
               sender.put("city", "义乌市");
               sender.put("area", "机场路");
               sender.put("town", "");
               sender.put("address", "义乌保税物流中心");
               content.put("sender", sender);
               
               JSONObject receiver = new JSONObject();
               receiver.put("name", orderBonded.getReceiveMan());
               receiver.put("tel", orderBonded.getReceiveManPhone());
               receiver.put("mobile", "");
               receiver.put("postCode", "");
               receiver.put("country", "中国");
               receiver.put("province", orderBonded.getReceiveProvince());
               receiver.put("city", orderBonded.getReceiveCity());
               receiver.put("area", orderBonded.getReceiveCounty());
               receiver.put("town", "");
               receiver.put("address", orderBonded.getReceiveManAddress());
               content.put("receiver", receiver);
 
               
               JSONObject customer = new JSONObject();
               customer.put("siteCode", "322000");
               customer.put("customerName", "322000016494");
               customer.put("sitePwd", "Ywsto033243");
               
               content.put("customer", customer);
               
               JSONObject cargo = new JSONObject();
               cargo.put("battery", "30");
               cargo.put("goodsType", "小件");
               cargo.put("goodsName", "跨境商品");
               content.put("cargo", cargo);
               
               String api_name = "OMS_EXPRESS_ORDER_CREATE";
               String from_appkey = "CAKNfFpqxsHCBYC";
               String content1 = content.toString();
               String from_code = "CAKNfFpqxsHCBYC";
               String to_appkey = "sto_oms";
               String to_code = "sto_oms";
               String secretKey = "IUDV1I5EE4wHad9vWURsge8SlgqwSMtx";
               
               String data_digest = PushtoWQ.getSignature(content1, secretKey);
               
               String url = "https://cloudinter-linkgatewayonline.sto.cn/gateway/link.do";
               String pam = "content=" + content1 + "&data_digest=" + URLEncoder.encode(data_digest) + "&api_name=" + api_name + "&from_appkey=" + from_appkey + "&from_code=" + from_code + "&to_appkey=" + to_appkey + "&to_code=" + to_code;
               System.out.println("pam=" + pam);
               String resout = PushtoWQ.sendPost2(url, pam);
               System.out.println("resout=" + resout);
               JSONObject jsonObject = JSONObject.parseObject(resout);
               String flag = jsonObject.get("success").toString();
               
               if ("false".equals(flag))
               { String errorMsg = jsonObject.get("errorMsg").toString();
                 
                 orderBonded.setAuditstatus(7);
                 orderBonded.setReturnInfo(errorMsg);
                 this.orderBondedDao.updateAuditstatus(orderBonded); }
               else
               { if ("true".equals(flag)) {
                   String data = jsonObject.get("data").toString();
                   JSONObject jsonObject2 = JSONObject.parseObject(data);
                   String bigWord = jsonObject2.get("bigWord").toString();
                   String waybillNo = jsonObject2.get("waybillNo").toString();
                   System.out.println(String.valueOf(bigWord) + "----" + waybillNo);
                   String packagePlace = jsonObject2.get("packagePlace").toString();
                   orderBonded.setBillProvideSiteCode(bigWord);
                   orderBonded.setMarkDestination(packagePlace);
                   orderBonded.setMailNo(waybillNo);
                 }                 
                 if (StringUtils.isNotEmpty(orderBonded.getMailNo()))
                 { orderBonded.setAuditstatus(2);
                   result = this.orderBondedDao.updateExpressParam(orderBonded); }  }  b++; }  } else if (!orderBonded.getCarrier().equals("SF")) { if (!orderBonded.getCarrier().equals("YD")) if (orderBonded.getCarrier().equals("JBG")) { JSONObject order = new JSONObject(); order.put("bagId", "100"); order.put("orderNo", orderBonded.getTxLogisticID()); order.put("userName", orderBonded.getReceiveMan()); order.put("tel", orderBonded.getReceiveManPhone()); order.put("postNumber", "311321"); order.put("province", orderBonded.getReceiveProvince()); order.put("city", orderBonded.getReceiveCity()); order.put("district", orderBonded.getReceiveCounty()); order.put("address", orderBonded.getReceiveManAddress()); order.put("sendUser", "义乌"); order.put("sendTel", "15988108699"); order.put("sendProvince", "浙江省"); order.put("sendCity", "金华市"); order.put("sendDistrict", "义乌"); order.put("sendAddress", "综保区"); order.put("srcPort", "2921"); JSONArray skuList = new JSONArray(); List<OrderBondedSku> orderBondedSkuList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID()); for (OrderBondedSku orderBondedSku : orderBondedSkuList) { JSONObject Item = new JSONObject(); Item.put("tyypeGoodName", orderBondedSku.getItemName()); Item.put("count", orderBondedSku.getItemCount()); skuList.add(Item); }  order.put("skuList", skuList); System.out.println("orderjson=" + order.toJSONString()); String abc = "{\"APP_ID\":\"880003\",\"SECRET\":\"AFB57D6F-B94B-4FFF-A909-AFBB344964AD\",\"REQUEST_DATA\":\"" + order.toJSONString() + "\"}"; System.out.println("abc=" + abc); }   }  }  }  if (StringUtils.isNotEmpty(orderBonded.getMailNo())) { orderBonded.setAuditstatus(2); result = this.orderBondedDao.updateExpressParam(orderBonded); }
        }
     
     return result;
   }
 
   
   public String updateIsPushToWms(String ids) throws Exception {
     String[] idArr = ids.split(",");
     String result = "推送成功！";
     List<OrderBonded> obList = new ArrayList<>();
     List<OrderBondedSku> obsList = new ArrayList<>(); byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderBonded orderBonded = this.orderBondedDao.findByIdToWms(Integer.valueOf(Integer.parseInt(id)));
       if (orderBonded != null && orderBonded.getCarrier() != null && 
         orderBonded.getMailNo() != null && orderBonded.getMerchantNum() != null) {
         String countyName = orderBonded.getReceiveCounty();
         String cityName = orderBonded.getReceiveCity();
         List<OrderBonded> cityNumberList = this.orderBondedDao.findAreaByName(cityName);
         List<OrderBonded> countyNumberList = this.orderBondedDao.findAreaByName(countyName);
         if (countyNumberList != null && cityNumberList != null && 
           countyNumberList.size() > 0 && cityNumberList.size() > 0) {
           if (countyNumberList.size() > 1) {
             String cityNumber = "";
             
             if (cityNumberList.size() > 1) {
               for (OrderBonded ob : cityNumberList) {
                 if (ob.getArea().toString().length() == 8) {
                   cityNumber = ob.getArea().toString();
                 }
               } 
             } else {
               cityNumber = ((OrderBonded)cityNumberList.get(0)).getArea().toString();
             } 
             
             for (OrderBonded countyNumber : countyNumberList) {
               String county = countyNumber.getArea().toString();
               String number = county.substring(0, county.length() - 2);
               
               if (number.equals(cityNumber)) {
                 orderBonded.setArea(Integer.valueOf(Integer.parseInt(county)));
               }
             } 
             if (orderBonded.getArea() == null) {
               return "推送失败！<br>订单号为：" + orderBonded.getTxLogisticID() + "，该订单【收件市】或者【收件区县】匹配有问题";
             }
           } else {
             orderBonded.setArea(((OrderBonded)countyNumberList.get(0)).getArea());
           } 
         } else {
           return "推送失败！<br>订单号为：" + orderBonded.getTxLogisticID() + "，该订单【收件市】或者【收件区县】匹配有问题";
         } 
         List<OrderBondedSku> goodsList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID());
         
         obsList.addAll(goodsList);
         orderBonded.setSkuCount(Integer.valueOf(goodsList.size()));
         obList.add(orderBonded);
       } else {
         return "推送失败！<br>订单号为：" + orderBonded.getTxLogisticID() + "，该订单【快递】、【快递单号】和【商家编码】至少有一项值为空";
       }  b++; }
     
     result = PushToWms.pushData(obList, obsList);
     if ("推送成功！".equals(result)) {
       this.orderBondedDao.updateIsPushToWms(idArr);
     }
     return result;
   }
   
   public String updateIsPushToWmsNew(String ids) throws Exception {
     String result = "推送失败！";
     String[] idArr = ids.split(",");
     
     String ret = "success"; byte b; int i;
     String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
 
       
       JSONObject order = new JSONObject();
       order.put("eCommerceCode", "3301960R63");
       order.put("eCommerceName", "杭州淘宝营销管理有限公司");
       order.put("msgid", orderBonded.getTxLogisticID());
       order.put("payCompanyCode", "31222699S7");
       order.put("payNumber", orderBonded.getPayNumber());
       order.put("orderTotalAmount", orderBonded.getWorth());
       order.put("orderGoodsAmount", orderBonded.getWorth());
       order.put("preferentialAmount", "0");
       order.put("orderNo", orderBonded.getTxLogisticID());
       order.put("orderTaxAmount", "0");
       order.put("totalCount", "1");
       order.put("fee", "0");
       order.put("purchaserName", orderBonded.getBuyerName());
       order.put("purchaserCardNo", orderBonded.getBuyerIdNumber());
       order.put("shipper", "坎");
       order.put("shipperPro", "浙江省");
       order.put("shipperCity", "义乌市");
       order.put("shipperDistrict", "四季路");
       order.put("shipperAdd", "义乌综合保税");
       order.put("shipperMobile", "13666666666");
       order.put("consignee", orderBonded.getReceiveMan());
       order.put("consigneePro", orderBonded.getReceiveProvince());
       order.put("consigneeCity", orderBonded.getReceiveCity());
       order.put("consigneeDistrict", orderBonded.getReceiveCounty());
       order.put("consigneeAdd", orderBonded.getReceiveManAddress());
       order.put("consigneeMobile", orderBonded.getReceiveManPhone());
       order.put("inputdate", orderBonded.getCreateTime());
       order.put("commerceCode", "33189630GS");
       order.put("commerceName", "义乌齐越电子商务有限公司");
       order.put("logisticNo", orderBonded.getMailNo());
 
       
       JSONArray skuList = new JSONArray();
       List<OrderBondedSku> orderBondedSkuList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID());
       
       for (OrderBondedSku orderBondedSku : orderBondedSkuList) {
         JSONObject Item = new JSONObject();
         Item.put("partno", orderBondedSku.getItemsku());
         Item.put("qty", orderBondedSku.getItemCount());
         Item.put("amount", orderBondedSku.getAllPrice());
 
         
         skuList.add(Item);
       } 
       order.put("sku_list", skuList);
       
       System.out.println("orderjson=" + order.toJSONString());
       
       String SECRET = "c0f8f3ee-adfa-4a33-aca6-7fcce3d1ad5e";
       SECRET = MD5Util.MD5(SECRET);
       
       JSONObject abc = new JSONObject();
       abc.put("APP_ID", "880140");
       abc.put("SECRET", SECRET);
       abc.put("REQUEST_DATA", order);
 
       
       System.out.println("abc=" + abc.toJSONString());
       String url = "http://storage.kjruyigou.com/api/ewtpServer.php";
       ret = PushtoWQ.sendPostewtpkc(url, abc.toJSONString());
       System.out.println("凯昌订单下发回执" + ret);
       
       b++; }
     
     if (ret.contains("success")) {
       result = "推送成功！";
       this.orderBondedDao.updateIsPushToWms(idArr);
     } 
     return result;
   }
   
   public int updateIspost(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderBondedPush orderBondedPush = this.orderBondedDao.findById(Integer.valueOf(Integer.parseInt(id)));
       orderBondedPush.setCreateTime("2018-03-22 10:59:01");
       List<OrderBondedPushSku> goodsList = this.orderBondedSkuDao.findByTxLogisticID(orderBondedPush.getOrderSn());
       orderBondedPush.setConsignee(orderBondedPush.getBuyerName());
       orderBondedPush.setGoodsList(goodsList);
 
       
       boolean mark = CheckPhone.isPhone(orderBondedPush.getTel());
       if (!mark) {
         orderBondedPush.setTel("13588508633");
       }
       
       boolean mark2 = CheckPhone.isPhone(orderBondedPush.getPurchaserTelNumber());
       if (!mark2) {
         orderBondedPush.setPurchaserTelNumber("13588508633");
       }
       
       String strJson = JSON.toJSONString(orderBondedPush);
       System.out.println(strJson);
       
       strJson = "[" + strJson + "]";
       String newstr = "";
       try {
         newstr = Base64.encodeBase64String(strJson.getBytes("utf-8"));
       } catch (UnsupportedEncodingException e) {
         
         e.printStackTrace();
       } 
       String parm = "auth=6d1df3f2cb5f571ecc1d413e02785331&data=" + URLEncoder.encode(newstr);
       String resout = PushtoWQ.sendPost2("http://www.haidai5.com/tools/import.php", parm).replace("\"", "");
       System.out.println("resout=" + resout);
       if (resout.contains("successNum:1")) {
         OrderBonded orderBonded = new OrderBonded();
         orderBonded.setId(Integer.parseInt(id));
         orderBonded.setIspost(1);
         result = this.orderBondedDao.updateIspost(orderBonded);
       }  b++; }
     
     return result;
   }
 
 
   
   public int pushOrderToDsds(String ids) throws Exception {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderBondedPush orderBondedPush = this.orderBondedDao.findById(Integer.valueOf(Integer.parseInt(id)));
       List<OrderBondedPushSku> goodsList = this.orderBondedSkuDao.findByTxLogisticID(orderBondedPush.getOrderSn());
       
       orderBondedPush.setGoodsList(goodsList);
       String ret = ShopOrderCreateSample.push(orderBondedPush);
       if (ret.contains(orderBondedPush.getOrderSn())) {
         OrderBonded orderBonded = new OrderBonded();
         orderBonded.setId(Integer.parseInt(id));
         orderBonded.setIspost(1);
         result = this.orderBondedDao.updateIspost(orderBonded);
       }  b++; }
     
     return result;
   }
   
   public String updateIsCustoms(String ids) throws Exception {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String[] idArr = ids.split(",");
     String result = "1"; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       try {
         OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
         if ("STO2".equals(orderBonded.getCarrier())) {
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
           
           LogisticsHead.addElement("ceb:logisticsCode").addText("3120980110");
           LogisticsHead.addElement("ceb:logisticsName").addText("申通快递有限公司");
           LogisticsHead.addElement("ceb:logisticsNo").addText(orderBonded.getMailNo());
           LogisticsHead.addElement("ceb:billNo").addText("");
           LogisticsHead.addElement("ceb:orderNo").addText(orderBonded.getTxLogisticID());
           LogisticsHead.addElement("ceb:freight").addText("0");
           LogisticsHead.addElement("ceb:insuredFee").addText("0");
           LogisticsHead.addElement("ceb:currency").addText("142");
           LogisticsHead.addElement("ceb:weight").addText("1");
           LogisticsHead.addElement("ceb:packNo").addText("1");
           LogisticsHead.addElement("ceb:goodsInfo").addText("跨境商品");
           LogisticsHead.addElement("ceb:consignee").addText(orderBonded.getReceiveMan());
           LogisticsHead.addElement("ceb:consigneeAddress").addText(orderBonded.getReceiveManAddress());
           LogisticsHead.addElement("ceb:consigneeTelephone").addText(orderBonded.getReceiveManPhone());
           LogisticsHead.addElement("ceb:note").addText("");
 
           
           Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
           BaseTransfer.addElement("ceb:copCode").addText("3120980110");
           BaseTransfer.addElement("ceb:copName").addText("申通快递有限公司");
           BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
           BaseTransfer.addElement("ceb:dxpId").addText("DXPENT0000013203");
           BaseTransfer.addElement("ceb:note").addText("test");
           
           String xml = document.asXML();
           
           System.out.println("xml=" + xml);
 
           
           String name = "DXPENT0000013203";
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
 
           
           orderBonded.setIsCustoms(1);
           this.orderBondedDao.updateIsCustoms(orderBonded);
         } else if ("STO".equals(orderBonded.getCarrier())) {
           
           String address = orderBonded.getReceiveManAddress();
           String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
           Pattern p = Pattern.compile(regEx);
           Matcher m = p.matcher(address);
           address = m.replaceAll("").trim();
           
           JSONObject jsonObject = new JSONObject();
           jsonObject.put("siteCode", "310064");
           jsonObject.put("siteName", "杭州仓储部");
           jsonObject.put("tradeNo", orderBonded.getTxLogisticID());
           jsonObject.put("waybillNo", orderBonded.getMailNo());
           jsonObject.put("height", "");
           jsonObject.put("length", "");
           jsonObject.put("logisticId", orderBonded.getMailNo());
           jsonObject.put("orderDate", orderBonded.getCreateTime());
           jsonObject.put("orderSource", "PORTZJZTXS");
           jsonObject.put("receiverProv", orderBonded.getReceiveProvince());
           jsonObject.put("receiverProvCode", "");
           jsonObject.put("receiverCity", orderBonded.getReceiveCity());
           jsonObject.put("receiverCityCode", "");
           jsonObject.put("receiverArea", orderBonded.getReceiveCounty());
           jsonObject.put("receiverAreaCode", "");
           jsonObject.put("receiverTown", "文一路");
           jsonObject.put("receiverTownCode", "");
           jsonObject.put("receiverAddress", address);
           jsonObject.put("receiverMobile", orderBonded.getReceiveManPhone());
           jsonObject.put("receiverName", orderBonded.getReceiveMan());
           jsonObject.put("receiverPhone", "024-84332654");
           jsonObject.put("receiverPostcode", "634569");
           jsonObject.put("senderAddress", "浙江省杭州市萧山区");
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
           String time = s1.format(orderBonded.getCreateTime());
           
           JSONObject otherInfos = new JSONObject();
           otherInfos.put("grossWeight", "3");
           otherInfos.put("totalWayBill", "");
           otherInfos.put("packNo", "1");
           otherInfos.put("goodsName", "口岸保税商品");
           otherInfos.put("currCode", "142");
           otherInfos.put("note", "");
           otherInfos.put("insureAmount", "0");
           otherInfos.put("feeAmount", "0");
           otherInfos.put("worth", orderBonded.getWorth());
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
               
               orderBonded.setIsCustoms(1);
               this.orderBondedDao.updateIsCustoms(orderBonded);
             } else {
               result = "推送失败";
             }
           
           }
           catch (Exception e) {
             e.printStackTrace();
           }
         
         }
         else if (!"YTO".equals(orderBonded.getCarrier())) {
 
 
 
           
           "EMS".equals(orderBonded.getCarrier());
         }
       
       } catch (Exception e) {
         e.printStackTrace();
       } 
       
       b++; }
 
     
     return result;
   }
   
   public String jkDd(String ids) {
     return "";
   }
   
   public int pushDd(String ids) {
     if (this.CKDM.equals("GY")) {
       String[] idArr = ids.split(","); byte b; int i;
       String[] arrayOfString1;
       for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
         jkDd(id); b++; }
     
     } else {
       jkDd(ids);
     } 
 
     
     this.orderBondDao.updateddzt(ids);
     int result = 1;
 
     
     return result;
   }
   public String jkQd(String ids) {
     DecimalFormat df_2 = new DecimalFormat("0.000");
     String str = "BQ1E1CFD-EDED-46B1-946C-" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
     String[] idArr = ids.split(",");
     
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
         OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
         Company company = this.companyDao.findByCompanyBm(orderBonded.getMerchantNum());
         InventoryHead.addElement("ceb:guid").addText(str);
         InventoryHead.addElement("ceb:appType").addText("1");
         InventoryHead.addElement("ceb:appTime").addText(timenew.newtime());
         InventoryHead.addElement("ceb:appStatus").addText("2");
         InventoryHead.addElement("ceb:orderNo").addText(orderBonded.getTxLogisticID());
         InventoryHead.addElement("ceb:ebpCode").addText(company.getCompanyCode());
         InventoryHead.addElement("ceb:ebpName").addText(company.getCompanyName());
         InventoryHead.addElement("ceb:ebcCode").addText(company.geteCommerceCode());
         InventoryHead.addElement("ceb:ebcName").addText(company.geteCommerceName());
         InventoryHead.addElement("ceb:logisticsNo").addText(orderBonded.getMailNo());
         if ("STO".equals(orderBonded.getCarrier())) {
           if (this.CKDM.equals("GY")) {
             InventoryHead.addElement("ceb:logisticsCode").addText("52019606FY");
             InventoryHead.addElement("ceb:logisticsName").addText("贵阳申通新快递有限公司");
           } else {
             
             InventoryHead.addElement("ceb:logisticsCode").addText("3120980110");
             InventoryHead.addElement("ceb:logisticsName").addText("申通快递有限公司");
           }
         
         }
         else if ("HTO".equals(orderBonded.getCarrier())) {
           InventoryHead.addElement("ceb:logisticsCode").addText("WL15041401");
           InventoryHead.addElement("ceb:logisticsName").addText("杭州百世网络技术有限公司");
         } else if ("SF".equals(orderBonded.getCarrier())) {
           InventoryHead.addElement("ceb:logisticsCode").addText("3301980093");
           InventoryHead.addElement("ceb:logisticsName").addText("浙江顺丰速运有限公司");
         }
         else if ("EMS".equals(orderBonded.getCarrier())) {
           InventoryHead.addElement("ceb:logisticsCode").addText("330198Z014");
           InventoryHead.addElement("ceb:logisticsName").addText("中国邮政速递物流股份有限公司杭州市分公司");
         } else if ("YTO".equals(orderBonded.getCarrier())) {
           InventoryHead.addElement("ceb:logisticsCode").addText("3316965375");
           InventoryHead.addElement("ceb:logisticsName").addText("浙江圆通速递有限公司");
         }
         else if ("JBD".equals(orderBonded.getCarrier())) {
           InventoryHead.addElement("ceb:logisticsCode").addText("11089609XE");
           InventoryHead.addElement("ceb:logisticsName").addText("北京京邦达贸易有限公司");
         } 
         String str1 = "HZlj3" + String.format("%013d", new Object[] { Integer.valueOf(orderBonded.getId()) });
         InventoryHead.addElement("ceb:copNo").addText(str1);
         InventoryHead.addElement("ceb:preNo");
         InventoryHead.addElement("ceb:assureCode").addText(company.getAssureCode());
         InventoryHead.addElement("ceb:emsNo").addText(company.getAccountBookNo());
         InventoryHead.addElement("ceb:invtNo");
         InventoryHead.addElement("ceb:ieFlag").addText("I");
         InventoryHead.addElement("ceb:declTime").addText(timenew.newtime3());
         InventoryHead.addElement("ceb:customsCode").addText(company.getDeclPort());
         InventoryHead.addElement("ceb:portCode").addText(company.getDeclPort());
         InventoryHead.addElement("ceb:ieDate").addText(timenew.newtime3());
         InventoryHead.addElement("ceb:buyerIdType").addText("1");
         InventoryHead.addElement("ceb:buyerIdNumber").addText(orderBonded.getBuyerIdNumber());
         InventoryHead.addElement("ceb:buyerName").addText(orderBonded.getBuyerName());
         InventoryHead.addElement("ceb:buyerTelephone").addText(orderBonded.getReceiveManPhone());
         InventoryHead.addElement("ceb:consigneeAddress").addText(orderBonded.getReceiveManAddress());
         InventoryHead.addElement("ceb:agentCode").addText(company.getDeclareCompanyCode());
         InventoryHead.addElement("ceb:agentName").addText(company.getDeclareCompanyName());
         InventoryHead.addElement("ceb:areaCode").addText(company.getInternalAreaCompanyNo());
         InventoryHead.addElement("ceb:areaName").addText(company.getInternalAreaCompanyName());
         InventoryHead.addElement("ceb:tradeMode").addText("1210");
         InventoryHead.addElement("ceb:trafMode").addText("W");
         InventoryHead.addElement("ceb:trafNo").addText("");
         InventoryHead.addElement("ceb:voyageNo").addText("");
         InventoryHead.addElement("ceb:billNo").addText("");
         InventoryHead.addElement("ceb:loctNo").addText(company.getCustomsField());
         InventoryHead.addElement("ceb:licenseNo").addText("");
         InventoryHead.addElement("ceb:country").addText("142");
         
         InventoryHead.addElement("ceb:freight").addText(orderBonded.getFeeAmount().toString());
         
         InventoryHead.addElement("ceb:insuredFee").addText("0");
         InventoryHead.addElement("ceb:currency").addText("142");
         InventoryHead.addElement("ceb:wrapType").addText("5");
         InventoryHead.addElement("ceb:packNo").addText("1");
         
         List<OrderBondedSku> orderBondedSkuList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID());
         Integer integer = Integer.valueOf(1);
         double gw = 0.0D;
         double jz = 0.0D;
         for (OrderBondedSku orderBondedSku : orderBondedSkuList) {
           Item item = this.orderBondedSkuDao.findItem(orderBondedSku.getItemsku());
           Element InventoryList = Logistics.addElement("ceb:InventoryList");
           InventoryList.addElement("ceb:gnum").addText(integer.toString());
           
           InventoryList.addElement("ceb:itemRecordNo").addText((new StringBuilder(String.valueOf(item.getInternalNumber()))).toString());
 
 
           
           InventoryList.addElement("ceb:itemNo").addText(item.getItemSKU());
           
           InventoryList.addElement("ceb:itemName").addText(item.getItemName());
           InventoryList.addElement("ceb:gcode").addText(item.getHscode());
           InventoryList.addElement("ceb:gname").addText(item.getItemName());
           InventoryList.addElement("ceb:gmodel").addText(item.getItemSpec());
           InventoryList.addElement("ceb:barCode").addText("无");
           InventoryList.addElement("ceb:country").addText(item.getCountry());
 
           
           InventoryList.addElement("ceb:currency").addText("142");
           InventoryList.addElement("ceb:qty").addText(orderBondedSku.getItemCount().toString());
           InventoryList.addElement("ceb:unit").addText(item.getUnitDesc());
           double ut = item.getUnitWeight() * orderBondedSku.getItemCount().intValue();
           gw += ut;
           
           if (item.getOneUnitDesc().equals("035")) {
             InventoryList.addElement("ceb:qty1").addText((new StringBuilder(String.valueOf(df_2.format(ut)))).toString());
           } else {
             InventoryList.addElement("ceb:qty1").addText(orderBondedSku.getItemCount().toString());
           } 
           InventoryList.addElement("ceb:unit1").addText(item.getOneUnitDesc());
           
           String twounitdesc = "";
           if (item.getTwoUnitDesc() == null || item.getTwoUnitDesc().equals("")) {
             twounitdesc = "";
           } else {
             twounitdesc = item.getTwoUnitDesc();
           } 
 
 
           
           if (!twounitdesc.equals(""))
           {
 
             
             if (twounitdesc.equals("035")) {
               InventoryList.addElement("ceb:qty2").addText((new StringBuilder(String.valueOf(df_2.format(ut)))).toString());
               InventoryList.addElement("ceb:unit2").addText(twounitdesc);
             } else {
               InventoryList.addElement("ceb:qty2").addText(orderBondedSku.getItemCount().toString());
               InventoryList.addElement("ceb:unit2").addText(twounitdesc);
             } 
           }
           InventoryList.addElement("ceb:price").addText(orderBondedSku.getUnitPrice().toString());
           InventoryList.addElement("ceb:totalPrice").addText(orderBondedSku.getAllPrice().toString());
           InventoryList.addElement("ceb:note");
           integer = Integer.valueOf(integer.intValue() + 1);
         } 
         jz = gw + 0.2D;
         InventoryHead.addElement("ceb:grossWeight").addText((new StringBuilder(String.valueOf(df_2.format(jz)))).toString());
         InventoryHead.addElement("ceb:netWeight").addText((new StringBuilder(String.valueOf(df_2.format(gw)))).toString());
         InventoryHead.addElement("ceb:note");
         
         b++; }
       
       Element BaseTransfer = cebRoot.addElement("ceb:BaseTransfer");
       BaseTransfer.addElement("ceb:copCode").addText(this.BILL_CUSTOMS_CODE);
       BaseTransfer.addElement("ceb:copName").addText(this.BILL_ENTERPRISE_NAME);
       BaseTransfer.addElement("ceb:dxpMode").addText("DXP");
       BaseTransfer.addElement("ceb:dxpId").addText(this.BILL_DXPID);
       BaseTransfer.addElement("ceb:note").addText("test");
       
       String xml = document.asXML();
       System.out.println("xmlzs=" + xml);
       if (this.CKDM.equals("GY")) {
         String str123 = "data=" + URLEncoder.encode(Base64.encodeBase64String(xml.getBytes("UTF-8")));
         
         PushToGY.sendPost("http://111.85.215.206:8090/KJPOSTWEB/Data.aspx", str123);
       }
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     return "";
   }
 
   
   public int pushQd(String ids) {
     int result = -1;
     
     DecimalFormat df_2 = new DecimalFormat("0.000");
     String[] idArr = ids.split(","); byte b; int i;
     String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       try {
         OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
         Company company = this.companyDao.findByCompanyBm(orderBonded.getMerchantNum());
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("version", "1.0.0");
 
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType").addText("PERSONAL_GOODS_DECLAR");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("goodsDeclareModuleList");
         Element element4 = element3.addElement("goodsDeclareModule");
         String str = "HZ" + this.CKDM + "3" + String.format("%013d", new Object[] { Integer.valueOf(orderBonded.getId()) });
         Element element5 = element4.addElement("jkfSign");
         element5.addElement("companyCode").addText(this.BILL_CUSTOMS_CODE);
         element5.addElement("businessNo").addText(str);
         element5.addElement("businessType").addText("PERSONAL_GOODS_DECLAR");
         element5.addElement("declareType").addText("1");
         
         if (this.CKDM.equals("YWJN")) {
           element5.addElement("cebFlag").addText("03");
         } else {
           element5.addElement("cebFlag").addText("02");
         } 
 
         
         element5.addElement("note").addText("进口清单");
         
         Element element6 = element4.addElement("goodsDeclare");
         
         element6.addElement("accountBookNo").addText(company.getAccountBookNo());
         
         element6.addElement("ieFlag").addText("I");
         element6.addElement("preEntryNumber").addText(str);
         element6.addElement("importType").addText("1");
         element6.addElement("inOutDateStr").addText(timenew.befortime());
         element6.addElement("iePort").addText(company.getDeclPort());
         element6.addElement("destinationPort").addText("142");
         element6.addElement("trafName").addText("");
         element6.addElement("voyageNo").addText("");
         element6.addElement("trafNo").addText("5");
         element6.addElement("trafMode").addText("7");
         element6.addElement("declareCompanyType").addText("个人委托第三方申报");
         element6.addElement("declareCompanyCode").addText(company.getDeclareCompanyCode());
         element6.addElement("declareCompanyName").addText(company.getDeclareCompanyName());
         element6.addElement("companyName").addText(company.getCompanyName());
         element6.addElement("companyCode").addText(company.getCompanyCode());
         element6.addElement("eCommerceCode").addText(company.geteCommerceCode());
         element6.addElement("eCommerceName").addText(company.geteCommerceName());
         
         if ("STO".equals(orderBonded.getCarrier())) {
           
           element6.addElement("logisCompanyName").addText("申通快递有限公司");
           element6.addElement("logisCompanyCode").addText("3120980110");
         } else if ("HTO".equals(orderBonded.getCarrier())) {
           element6.addElement("logisCompanyName").addText("杭州百世网络技术有限公司");
           element6.addElement("logisCompanyCode").addText("WL15041401");
         } else if ("SF".equals(orderBonded.getCarrier())) {
           element6.addElement("logisCompanyCode").addText("3301980093");
           element6.addElement("logisCompanyName").addText("浙江顺丰速运有限公司");
         } else if ("EMS".equals(orderBonded.getCarrier())) {
           element6.addElement("logisCompanyCode").addText("330198Z014");
           element6.addElement("logisCompanyName").addText("中国邮政速递物流股份有限公司杭州市分公司");
         } else if ("YTO".equals(orderBonded.getCarrier())) {
           element6.addElement("logisCompanyCode").addText("WL14122901");
           element6.addElement("logisCompanyName").addText("浙江圆通速递有限公司");
         } else if ("YD".equals(orderBonded.getCarrier())) {
           element6.addElement("logisCompanyCode").addText("3120980105");
           element6.addElement("logisCompanyName").addText("上海韵达货运有限公司");
         } else if ("JBD".equals(orderBonded.getCarrier())) {
           element6.addElement("logisCompanyName").addText("北京京邦达贸易有限公司");
           element6.addElement("logisCompanyCode").addText("WL15030901");
         } 
         
         element6.addElement("orderNo").addText(orderBonded.getTxLogisticID());
         element6.addElement("wayBill").addText(orderBonded.getMailNo());
         element6.addElement("billNo").addText("");
         element6.addElement("tradeCountry").addText("142");
         element6.addElement("packNo").addText("1");
         element6.addElement("warpType").addText("");
         element6.addElement("remark").addText("");
         element6.addElement("declPort").addText(company.getDeclPort());
         element6.addElement("enteringPerson").addText("9999");
         element6.addElement("enteringCompanyName").addText("9999");
         element6.addElement("declarantNo").addText("");
         element6.addElement("customsField").addText(company.getCustomsField());
         element6.addElement("senderName").addText(company.getCompanyName());
         element6.addElement("consignee").addText(orderBonded.getReceiveMan());
         element6.addElement("senderCountry").addText("142");
         element6.addElement("senderCity").addText("");
         element6.addElement("paperType").addText("1");
         element6.addElement("paperNumber").addText(orderBonded.getBuyerIdNumber());
         element6.addElement("consigneeAddress").addText(orderBonded.getReceiveManAddress());
         element6.addElement("purchaserTelNumber").addText(orderBonded.getReceiveManPhone());
         element6.addElement("buyerIdType").addText("1");
         element6.addElement("buyerIdNumber").addText(orderBonded.getBuyerIdNumber());
         element6.addElement("buyerName").addText(orderBonded.getBuyerName());
 
         
         BigDecimal worth = new BigDecimal(orderBonded.getWorth().doubleValue());
         BigDecimal taxTotal = new BigDecimal(orderBonded.getInsureAmount().doubleValue());
         BigDecimal acturalPaid = worth.add(taxTotal);
         
         element6.addElement("worth").addText(df_2.format(acturalPaid));
         element6.addElement("feeAmount").addText(orderBonded.getFeeAmount().toString());
         element6.addElement("insureAmount").addText("0");
         element6.addElement("currCode").addText("142");
         element6.addElement("mainGName").addText(orderBonded.getItemName());
         element6.addElement("internalAreaCompanyNo").addText(company.getInternalAreaCompanyNo());
         element6.addElement("internalAreaCompanyName").addText(company.getInternalAreaCompanyName());
         element6.addElement("assureCode").addText(company.getAssureCode());
         element6.addElement("applicationFormNo").addText("");
         element6.addElement("isAuthorize").addText("1");
         element6.addElement("licenseNo").addText("");
         
         List<OrderBondedSku> orderBondedSkuList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID());
         Element element7 = element4.addElement("goodsDeclareDetails");
         Integer integer = Integer.valueOf(1);
         double gw = 0.0D;
         double jz = 0.0D;
         for (OrderBondedSku orderBondedSku : orderBondedSkuList) {
           Item item = this.orderBondedSkuDao.findItem(orderBondedSku.getItemsku());
           Element element8 = element7.addElement("goodsDeclareDetail");
           double ut = item.getUnitWeight() * orderBondedSku.getItemCount().intValue();
           gw += ut;
           element8.addElement("goodsOrder").addText(integer.toString());
           element8.addElement("codeTs").addText(item.getHscode());
           element8.addElement("goodsItemNo").addText((new StringBuilder(String.valueOf(item.getItemSKU()))).toString());
           element8.addElement("itemRecordNo").addText((new StringBuilder(String.valueOf(item.getInternalNumber()))).toString());
           element8.addElement("itemName").addText("");
           element8.addElement("goodsName").addText(item.getItemName());
           element8.addElement("goodsModel").addText(item.getItemSpec());
           element8.addElement("originCountry").addText(item.getCountry());
 
           
           element8.addElement("tradeCountry").addText(item.getCountry());
           
           element8.addElement("tradeCurr").addText("142");
           element8.addElement("tradeTotal").addText("");
           element8.addElement("declPrice").addText(orderBondedSku.getUnitPrice().toString());
           element8.addElement("declTotalPrice").addText(orderBondedSku.getAllPrice().toString());
           element8.addElement("useTo").addText("");
           element8.addElement("declareCount").addText(orderBondedSku.getItemCount().toString());
           element8.addElement("goodsUnit").addText(item.getUnitDesc());
           element8.addElement("goodsGrossWeight").addText("");
           element8.addElement("firstUnit").addText(item.getOneUnitDesc());
           if (item.getOneUnitDesc().equals("035")) {
             
             element8.addElement("firstCount").addText((new StringBuilder(String.valueOf(df_2.format(ut)))).toString());
           } else {
             element8.addElement("firstCount").addText(orderBondedSku.getItemCount().toString());
           } 
           
           element8.addElement("secondUnit").addText(item.getTwoUnitDesc());
           if (item.getTwoUnitDesc().equals("")) {
             element8.addElement("secondCount").addText("");
           } else if (item.getTwoUnitDesc().equals("035")) {
             element8.addElement("secondCount").addText((new StringBuilder(String.valueOf(df_2.format(ut)))).toString());
           } else {
             element8.addElement("secondCount").addText(orderBondedSku.getItemCount().toString());
           } 
           element8.addElement("productRecordNo").addText("12");
           element8.addElement("webSite").addText("");
           element8.addElement("barCode").addText("");
           element8.addElement("note").addText("");
           integer = Integer.valueOf(integer.intValue() + 1);
         } 
         jz = gw + 0.2D;
         
         element6.addElement("grossWeight").addText((new StringBuilder(String.valueOf(df_2.format(jz)))).toString());
         element6.addElement("netWeight").addText((new StringBuilder(String.valueOf(df_2.format(gw)))).toString());
         System.out.println("---------->" + document.asXML());
         
         if (this.CKDM.equals("GY")) {
           orderBonded.setIsPushQd(1);
           orderBonded.setPreEntryNumber(document.asXML());
           result = this.orderBondedDao.updateIsPushQd(orderBonded);
           jkQd(id);
         
         }
       
       }
       catch (Exception e) {
         e.printStackTrace();
       } 
 
       
       b++; }
 
     
     return result;
   }
 
   
   public int pushQdTest(String ids) {
     int result = -1;
     try {
       String qdToken = "open";
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String[] idArr = ids.split(","); byte b; int i; String[] arrayOfString1;
       for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
         OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(Integer.parseInt(id)));
         Company company = this.companyDao.findByCompanyBm(orderBonded.getMerchantNum());
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("version", "1.0.0");
 
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType").addText("PERSONAL_GOODS_DECLAR");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("goodsDeclareModuleList");
         Element element4 = element3.addElement("goodsDeclareModule");
         String str = "HZBO3" + String.format("%013d", new Object[] { Integer.valueOf(orderBonded.getId()) });
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
 
 
 
         
         if ("STO".equals(orderBonded.getCarrier())) {
 
           
           element6.addElement("logisCompanyName").addText("申通快递有限公司");
           element6.addElement("logisCompanyCode").addText("669437562");
         } else if ("HTO".equals(orderBonded.getCarrier())) {
           element6.addElement("logisCompanyName").addText("杭州百世网络技术有限公司");
           element6.addElement("logisCompanyCode").addText("WL15041401");
         } 
         
         element6.addElement("orderNo").addText(orderBonded.getTxLogisticID());
         element6.addElement("wayBill").addText(orderBonded.getMailNo());
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
         element6.addElement("consignee").addText(orderBonded.getReceiveMan());
         element6.addElement("senderCountry").addText("142");
         element6.addElement("senderCity").addText("");
         element6.addElement("paperType").addText("1");
         element6.addElement("paperNumber").addText(orderBonded.getBuyerIdNumber());
         element6.addElement("consigneeAddress").addText(orderBonded.getReceiveManAddress());
         element6.addElement("purchaserTelNumber").addText(orderBonded.getReceiveManPhone());
         element6.addElement("buyerIdType").addText("1");
         element6.addElement("buyerIdNumber").addText(orderBonded.getBuyerIdNumber());
         element6.addElement("buyerName").addText(orderBonded.getBuyerName());
         element6.addElement("worth").addText(orderBonded.getWorth().toString());
         element6.addElement("feeAmount").addText("0");
         element6.addElement("insureAmount").addText("0");
         element6.addElement("currCode").addText("142");
         element6.addElement("mainGName").addText(orderBonded.getItemName());
         element6.addElement("internalAreaCompanyNo").addText(company.getInternalAreaCompanyNo());
         element6.addElement("internalAreaCompanyName").addText(company.getInternalAreaCompanyName());
         element6.addElement("assureCode").addText(company.getAssureCode());
         element6.addElement("applicationFormNo").addText(company.getApplicationFormNo());
         element6.addElement("isAuthorize").addText("1");
         element6.addElement("licenseNo").addText("");
         
         List<OrderBondedSku> orderBondedSkuList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID());
         Element element7 = element4.addElement("goodsDeclareDetails");
         Integer integer = Integer.valueOf(1);
         for (OrderBondedSku orderBondedSku : orderBondedSkuList) {
           Item item = this.orderBondedSkuDao.findItem(orderBondedSku.getItemsku());
           Element element8 = element7.addElement("goodsDeclareDetail");
           element8.addElement("goodsOrder").addText(integer.toString());
           element8.addElement("codeTs").addText(item.getHscode());
           element8.addElement("goodsItemNo").addText(orderBondedSku.getItemsku());
           element8.addElement("itemRecordNo").addText(orderBondedSku.getItemsku());
           element8.addElement("itemName").addText("");
           element8.addElement("goodsName").addText(orderBondedSku.getItemName());
           element8.addElement("goodsModel").addText(orderBondedSku.getItemName());
           element8.addElement("originCountry").addText(company.getCountry());
           element8.addElement("tradeCurr").addText("142");
           element8.addElement("tradeTotal").addText("");
           element8.addElement("declPrice").addText(orderBondedSku.getUnitPrice().toString());
           element8.addElement("declTotalPrice").addText(orderBondedSku.getAllPrice().toString());
           element8.addElement("useTo").addText("");
           element8.addElement("declareCount").addText(orderBondedSku.getItemCount().toString());
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
 
         
         String ret = PushtoBG_yd.testQd(document.asXML(), qdToken);
         
         if ("令牌无效".equals(ret)) {
           return 9;
         }
         qdToken = "close";
         
         if (ret.contains("<resultInfo>处理成功</resultInfo>")) {
           orderBonded.setIsPushQd(1);
           result = this.orderBondedDao.updateIsPushQd(orderBonded);
         }  b++; }
     
     } catch (Exception e) {
       System.err.println(e.toString());
     } 
     PushtoBG_yd.testQd("", "");
     return result;
   }
 
 
 
   
   public int insertAuditstatusByIds(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderBonded orderBonded = this.orderBondedDao.findByIdToWJ(Integer.valueOf(Integer.parseInt(id)));
       List<OrderAccept> ll = this.orderAcceptDao.findByOrderNumber(orderBonded.getTxLogisticID());
       if (ll.size() == 0) {
         OrderAccept orderAccept = new OrderAccept();
         orderAccept.setOrderNumber(orderBonded.getTxLogisticID());
         orderAccept.setExpressCode(orderBonded.getCarrier());
         orderAccept.setExpressNumber(orderBonded.getMailNo());
         orderAccept.setBuyerName(orderBonded.getReceiveMan());
         orderAccept.setBuyerNickName(orderBonded.getBuyerName());
         orderAccept.setBuyerIdCard(orderBonded.getBuyerIdNumber());
         orderAccept.setBuyerProvince(orderBonded.getReceiveProvince());
         orderAccept.setBuyerCity(orderBonded.getReceiveCity());
         orderAccept.setBuyerArea(orderBonded.getReceiveCounty());
         orderAccept.setBuyerAddress(orderBonded.getReceiveManAddress());
         orderAccept.setBuyerTel(orderBonded.getReceiveManPhone());
         orderAccept.setZyName(orderBonded.getBillProvideSiteName());
         orderAccept.setZyNumber(orderBonded.getBillProvideSiteCode());
         orderAccept.setSender(orderBonded.getSendName());
         orderAccept.setSenderTel(orderBonded.getSendTel());
         orderAccept.setSenderAddress(orderBonded.getSendAddress());
         orderAccept.setUserId(orderBonded.getUserId());
         orderAccept.setPrintType(0);
         this.orderAcceptDao.insert(orderAccept);
         
         List<OrderBondedSku> obsList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID());
         for (OrderBondedSku orderBondedSku : obsList) {
           GoodsAccept goodsAccept = new GoodsAccept();
           goodsAccept.setExpressNumber(orderBonded.getMailNo());
           goodsAccept.setGoodsName(orderBondedSku.getItemName());
           goodsAccept.setGoodsSKU(orderBondedSku.getItemsku());
           goodsAccept.setNum(orderBondedSku.getItemCount().intValue());
           goodsAccept.setPrice(BigDecimal.valueOf(orderBondedSku.getUnitPrice().doubleValue()));
           goodsAccept.setTotalPrice(BigDecimal.valueOf(orderBondedSku.getAllPrice().doubleValue()));
           goodsAccept.setWeight(Double.valueOf(orderBondedSku.getItemWeight().doubleValue()));
           this.goodsAcceptDao.insert(goodsAccept);
         }
       
       } else {
         
         result = 2; break;
       } 
       b++; }
     
     return result;
   }
 
   
   public int updateAuditstatusByTxLogisticID(String txLogisticID) {
     int result = -1;
     OrderBonded orderBonded = this.orderBondedDao.findByTxLogisticID(txLogisticID);
     if (orderBonded != null) {
       if (orderBonded.getAuditstatus() == 9) {
         return result;
       }
       OrderAccept orderAccept = new OrderAccept();
       orderAccept.setOrderNumber(orderBonded.getTxLogisticID());
       orderAccept.setExpressCode(orderBonded.getCarrier());
       orderAccept.setExpressNumber(orderBonded.getMailNo());
       orderAccept.setBuyerName(orderBonded.getReceiveMan());
       orderAccept.setBuyerNickName(orderBonded.getBuyerName());
       orderAccept.setBuyerIdCard(orderBonded.getBuyerIdNumber());
       orderAccept.setBuyerProvince(orderBonded.getReceiveProvince());
       orderAccept.setBuyerCity(orderBonded.getReceiveCity());
       orderAccept.setBuyerArea(orderBonded.getReceiveCounty());
       orderAccept.setBuyerAddress(orderBonded.getReceiveManAddress());
       orderAccept.setBuyerTel(orderBonded.getReceiveManPhone());
       orderAccept.setZyName(orderBonded.getBillProvideSiteName());
       orderAccept.setZyNumber(orderBonded.getBillProvideSiteCode());
       orderAccept.setSender(orderBonded.getSendName());
       orderAccept.setSenderTel(orderBonded.getSendTel());
       orderAccept.setSenderAddress(orderBonded.getSendAddress());
       orderAccept.setUserId(orderBonded.getUserId());
       orderAccept.setPrintType(0);
       this.orderAcceptDao.insert(orderAccept);
       
       List<OrderBondedSku> obsList = this.orderBondedSkuDao.findById(txLogisticID);
       for (OrderBondedSku orderBondedSku : obsList) {
         GoodsAccept goodsAccept = new GoodsAccept();
         goodsAccept.setExpressNumber(orderBonded.getMailNo());
         goodsAccept.setGoodsName(orderBondedSku.getItemName());
         goodsAccept.setGoodsSKU(orderBondedSku.getItemsku());
         goodsAccept.setNum(orderBondedSku.getItemCount().intValue());
         goodsAccept.setPrice(BigDecimal.valueOf(orderBondedSku.getUnitPrice().doubleValue()));
         goodsAccept.setTotalPrice(BigDecimal.valueOf(orderBondedSku.getAllPrice().doubleValue()));
         goodsAccept.setWeight(Double.valueOf(orderBondedSku.getItemWeight().doubleValue()));
         goodsAccept.setUserId(orderBonded.getUserId());
         this.goodsAcceptDao.insert(goodsAccept);
       } 
       orderBonded.setAuditstatus(9);
       result = this.orderBondedDao.updateAuditstatus(orderBonded);
     } 
     return result;
   }
 
 
   
   public OrderBonded findByIdToBG(Integer id) {
     return this.orderBondedDao.findByIdToBG(id);
   }
 
 
   
   public List<OrderBondedForExport> exportOrder(Map<String, Object> paramMap) {
     return this.orderBondedDao.exportOrder(paramMap);
   }
 
   
   public int updateIsCustomsByTxLogisticID(String txLogisticID) {
     int result = -1;
     OrderBonded orderBonded = new OrderBonded();
     orderBonded.setTxLogisticID(txLogisticID);
     orderBonded.setIsCustoms(2);
     result = this.orderBondedDao.updateIsCustomsByTxLogisticID(orderBonded);
     return result;
   }
 
 
   
   public List<OrderBonded> findTxLogisticID() {
     return this.orderBondedDao.findTxLogisticID();
   }
 
   
   public int payNumber(String txLogisticID1) {
     int ret = -1;
     try {
       OrderBonded orderBonded = new OrderBonded();
       if (txLogisticID1 == "") {
         List<OrderBonded> v = this.orderBondedDao.findTxLogisticID();
         if (v.size() == 0) {
           ret = 0;
         } else {
           for (OrderBonded n : v) {
             String str = GetPayNumber.get("http://www.haidai5.com/tools/getPay.php?order_sn=" + n.getTxLogisticID());
             
             String payNumber = str.substring(str.indexOf("支付单号是：") + 6, str.indexOf("<br />姓名是："));
             
             String buyerName = str.substring(str.indexOf("姓名是：") + 4, str.indexOf("<br />身份证是："));
             
             String buyerIdNumber = str.substring(str.indexOf("身份证是：") + 5, str.indexOf("<br />电话是："));
             
             String receiveManPhone = str.substring(str.indexOf("电话是：") + 4, str.lastIndexOf("<br />"));
             
             if (!"".equals(payNumber)) {
               orderBonded.setPayNumber(payNumber);
               orderBonded.setBuyerName(buyerName);
               orderBonded.setBuyerIdNumber(buyerIdNumber);
               orderBonded.setReceiveManPhone(receiveManPhone);
               orderBonded.setTxLogisticID(n.getTxLogisticID());
               ret = this.orderBondedDao.payNumber(orderBonded);
             } 
           } 
         } 
       } else {
         
         String[] arr = txLogisticID1.split(",");
         for (int i = 0; i < arr.length; i++) {
           String str = GetPayNumber.get("http://www.haidai5.com/tools/getPay.php?order_sn=" + arr[i]);
 
           
           String payNumber = str.substring(str.indexOf("支付单号是：") + 6, str.indexOf("<br />姓名是："));
           
           String buyerName = str.substring(str.indexOf("姓名是：") + 4, str.indexOf("<br />身份证是："));
           
           String buyerIdNumber = str.substring(str.indexOf("身份证是：") + 5, str.indexOf("<br />电话是："));
           
           String receiveManPhone = str.substring(str.indexOf("电话是：") + 4, str.lastIndexOf("<br />"));
           
           if (!"".equals(payNumber)) {
             orderBonded.setPayNumber(payNumber);
             orderBonded.setBuyerName(buyerName);
             orderBonded.setBuyerIdNumber(buyerIdNumber);
             orderBonded.setReceiveManPhone(receiveManPhone);
             orderBonded.setTxLogisticID(arr[i]);
             ret = this.orderBondedDao.payNumber(orderBonded);
           } 
         } 
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return ret;
   }
 
   
   public OrderBonded findByMailNo(String mailNo) {
     OrderBonded ob = this.orderBondedDao.findByMailNo(mailNo);
 
 
 
     
     return ob;
   }
 
   
   public int updateStatus(String merchantNum) {
     return this.orderBondedDao.updateStatus(merchantNum);
   }
 
   
   public int findaddress(OrderBonded orderBonded) {
     return this.orderBondedDao.findaddress(orderBonded);
   }
 
   
   public int updaddress(OrderBonded orderBonded) {
     return this.orderBondedDao.updaddress(orderBonded);
   }
 
   
   public List<OrderBonded> odbdateTime(Map<String, Object> pageMap) {
     return this.orderBondedDao.odbdateTime(pageMap);
   }
 
   
   public List<OrderBonded> findCs(String idd) {
     return this.orderBondedDao.findCs(idd);
   }
 
   
   public List<OrderBonded> findTxId(String id) {
     return this.orderBondedDao.findTxId(id);
   }
 
   
   public int upm(OrderBonded ob) {
     return this.orderBondedDao.upm(ob);
   }
 
   
   public void deletetx(String id) {
     this.orderBondedDao.deletetx(id);
   }
 
   
   public int editodIdN(OrderBonded orderBonded) {
     return this.orderBondedDao.editodIdN(orderBonded);
   }
 
   
   public void updateInvtno(OrderBonded orderBonded) {
     this.orderBondedDao.updateInvtno(orderBonded);
   }
 
 
   
   public OrderBonded findOneById(int id) {
     return this.orderBondedDao.findOneById(id);
   }
 
   
   public int countByCollect(Collect collect) {
     return this.orderBondedDao.countByCollect(collect);
   }
 
   
   public List<OrderBonded> findAllByCollect(Collect collect) {
     return this.orderBondedDao.findAllByCollect(collect);
   }
 
   
   public int editMatter(OrderBonded orderBonded) {
     return this.orderBondedDao.editMatter(orderBonded);
   }
 
   
   public int updateDSHKExpressParam(Integer[] ids) {
     try {
       if (ids == null || ids.length == 0 || ids.length > 1000) {
         return 0;
       }
       Integer ret = this.orderBondedDao.checkMailNo(ids);
       if (ret.intValue() != 0) {
         return 0;
       }
 
 
       
       URL wsUrl = new URL("http://183.129.231.82:8066/ecm/interface/rest/tendInterface/getbill?Sign=0888A1AFB593666C658BF5C315B3E3F3&User=DSTO&Password=123456&len=" + ids.length);
       HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
       conn.setDoInput(true);
       conn.setDoOutput(true);
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
       InputStream is = conn.getInputStream();
       
       byte[] b = new byte[1024];
       int len = 0;
       String s = "";
       while ((len = is.read(b)) != -1) {
         String ss = new String(b, 0, len, "UTF-8");
         s = String.valueOf(s) + ss;
       } 
       
       JSONObject object = (JSONObject)JSONObject.parse(s);
       String resultCode = object.getString("resultCode");
       String resultMsg = object.getString("resultMsg");
       if ("1005".equals(resultCode))
         return 0; 
       if ("1000".equals(resultCode)) {
         String[] mailNos = resultMsg.split("-");
         Long long1 = Long.valueOf(Long.parseLong(mailNos[0]));
         Long long2 = Long.valueOf(Long.parseLong(mailNos[1]));
         Long l = Long.valueOf(long2.longValue() - long1.longValue() + 1L);
         if (l.intValue() == ids.length) {
           for (int i = 0; i < ids.length; i++) {
             OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(ids[i]);
             orderBonded.setMailNo((new StringBuilder(String.valueOf(long1.longValue() + i))).toString());
             orderBonded.setAuditstatus(2);
             this.orderBondedDao.updateDSHKMailNo(orderBonded);
           } 
         }
       } else {
         return 0;
       } 
       return 1;
     } catch (Exception e) {
       return 0;
     } 
   }
 
 
   
   public int push3(String ids) throws Exception {
     String yunds = "";
     String dinds = "";
     String qinds = "";
     String[] split = ids.split(",");
     List<OrderPush3> list = this.timedTaskDao.findPush3(split);
     for (OrderPush3 map : list) {
       
       Object yund = map.getYund();
       if (yund.toString().equals("1"))
         yunds = String.valueOf(yunds) + map.getId() + ","; 
       Object dind = map.getDind();
       if (dind.toString().equals("1"))
         dinds = String.valueOf(dinds) + map.getId() + ","; 
       Object qind = map.getQind();
       if (qind.toString().equals("1"))
         qinds = String.valueOf(qinds) + map.getId() + ","; 
     } 
     updateIsCustoms(yunds);
     pushDd(dinds);
     pushQd(qinds);
 
     
     return 1;
   }
 
   
   public void insert(OrderBonded orderBonded) {
     this.orderBondedDao.insert(orderBonded);
   }
 
 
 
   
   public int updateAuditstatusByIds(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       OrderBonded orderBonded = this.orderBondedDao.findByIdToWJ(Integer.valueOf(Integer.parseInt(id)));
       orderBonded.setAuditstatus(9);
       
       result = this.orderBondedDao.updateAuditstatus(orderBonded); b++; }
     
     return result;
   }
 
   
   public synchronized int updateIspostToYf(String ids) throws Exception {
     String[] idArr = ids.split(",");
     int result = -1;
     List<OrderBonded> orderBondedList = this.orderBondedDao.findByIdArr(idArr);
 
 
 
     
     if (orderBondedList != null && orderBondedList.size() > 0)
     {
       
       for (OrderBonded ob : orderBondedList) {
 
         
         if (ob.getPayNumber() == null || ob.getPayNumber().equals("")) {
           System.out.println("正常匹配");
         } else {
           System.out.println("重复匹配");
           
           continue;
         } 
         
         B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
             "乐仓电子商务", "lecang");
         ShopOrderCreateRequest request = new ShopOrderCreateRequest();
 
         
         request.memberNick = "乐仓电子商务";
         request.shopOrderNo = ob.getTxLogisticID();
         request.shopId = Integer.valueOf(4);
         request.orderStatus = Integer.valueOf(10);
         request.shopCreatedTime = Tools.getCurrentTime();
         
         request.customIdNo = ob.getBuyerIdNumber();
         request.customName = ob.getBuyerName();
         request.receiverName = ob.getReceiveMan();
         request.receiverAddress = ob.getReceiveManAddress();
         request.receiverMobile = ob.getReceiveManPhone();
         request.receiverState = ob.getReceiveProvince();
         request.receiverCity = ob.getReceiveCity();
         request.receiverDistrict = ob.getReceiveCounty();
 
         
         request.expressName = ob.getCarrier();
         request.expressTrackNo = ob.getMailNo();
         
         List<ShopOrderCreateLine> line1 = new ArrayList<>();
         
         List<OrderBondedSku> orderBondedSkuList = this.orderBondedSkuDao.findById(ob.getTxLogisticID());
         
         for (OrderBondedSku orderBondedSku : orderBondedSkuList) {
           ShopOrderCreateLine shopOrderCreateLine1 = new ShopOrderCreateLine();
           shopOrderCreateLine1.shopLineNo = orderBondedSku.getTxLogisticID();
           shopOrderCreateLine1.outerId = (new StringBuilder(String.valueOf(orderBondedSku.getInternalNumber()))).toString();
           shopOrderCreateLine1.price = orderBondedSku.getUnitPrice().doubleValue();
           shopOrderCreateLine1.quantity = orderBondedSku.getItemCount().intValue();
           shopOrderCreateLine1.lineUdf1 = "自定义字段1";
           shopOrderCreateLine1.lineUdf2 = "自定义字段2";
           shopOrderCreateLine1.skuName = "无";
           shopOrderCreateLine1.itemName = orderBondedSku.getItemName();
           shopOrderCreateLine1.lineTotal = 0.0D;
           shopOrderCreateLine1.lineCustomTax = 0.0D;
           line1.add(shopOrderCreateLine1);
         } 
         request.setItemLines(line1);
         ShopOrderCreateResponse response = (ShopOrderCreateResponse)client.execute((B1EC2Request)request);
 
         
         System.out.println(response.getBody());
         if (response.getBody().contains(ob.getTxLogisticID())) {
           OrderBonded orderBonded = new OrderBonded();
           orderBonded.setId(ob.getId());
           orderBonded.setIspost(1);
           orderBonded.setPayNumber("11118888");
           result = this.orderBondedDao.updateIspostForYF(orderBonded);
         } 
       } 
     }
     return result;
   }
   
   public int updateExpressParam2ForHTO(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       int parseInt = Integer.parseInt(id);
       OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(parseInt));
       
       if (orderBonded.getCarrier().equals("HTO")) {
         Element root = DocumentHelper.createElement("PrintRequest");
         Document document = DocumentHelper.createDocument(root);
         
         root.addAttribute("xmlns", "ems=http://express.800best.com");
         root.addElement("deliveryConfirm").addText("false");
         String msgID = "DSHTO" + String.format("%014d", new Object[] { Integer.valueOf(orderBonded.getId()) });
         root.addElement("msgId").addText(msgID);
         
         Element element1 = root.addElement("EDIPrintDetailList");
         element1.addElement("sendMan").addText("保税物流中心");
         element1.addElement("sendManPhone").addText("wu");
         element1.addElement("sendManAddress").addText("保税物流中心");
         element1.addElement("sendPostcode").addText("");
         element1.addElement("sendProvince").addText("浙江省");
         element1.addElement("sendCity").addText("杭州市");
         element1.addElement("sendCounty").addText("萧山区");
         element1.addElement("receiveMan").addText(orderBonded.getReceiveMan());
         element1.addElement("receiveManPhone").addText(orderBonded.getReceiveManPhone());
         element1.addElement("receiveManAddress").addText(Tools.removeSpecialCharacter(orderBonded.getReceiveManAddress()));
         element1.addElement("receiveProvince").addText(orderBonded.getReceiveProvince());
         element1.addElement("receiveCity").addText(orderBonded.getReceiveCity());
         element1.addElement("receiveCounty").addText(orderBonded.getReceiveCounty());
         element1.addElement("txLogisticID").addText(orderBonded.getTxLogisticID());
         element1.addElement("itemName").addText(orderBonded.getItemName());
         element1.addElement("itemWeight").addText("0.2");
         element1.addElement("itemCount").addText(orderBonded.getItemCount().toString());
         element1.addElement("remark").addText("备注");
         element1.addElement("sortingcode").addText("");
         Element shippingListAttribute = element1.addElement("shippingListAttribute");
         shippingListAttribute.addElement("code").addText("BEST-COD");
         Element shippingListAttributeValue = shippingListAttribute.addElement("shippingListAttributeValue");
         shippingListAttributeValue.addElement("name").addText("MONEY");
         if (!"PN".equals(orderBonded.getMerchantNum())) {
           return 0;
         }
         String v = orderBonded.getTxLogisticID().substring(orderBonded.getTxLogisticID().indexOf("D") - 3, orderBonded.getTxLogisticID().indexOf("D"));
         try {
           double d = Double.parseDouble(v);
         } catch (Exception e) {
           System.out.println("单号解析价格错误！！！");
           e.printStackTrace();
           return 0;
         } 
         System.out.println("代收金额为:  " + v);
         
         shippingListAttributeValue.addElement("value").addText(v);
 
 
         
         MatchMailNo.hqmd2(document.asXML(), orderBonded);
       } 
 
       
       if (StringUtils.isNotEmpty(orderBonded.getMailNo())) {
         orderBonded.setAuditstatus(2);
         result = this.orderBondedDao.updateExpressParam(orderBonded);
       }  b++; }
     
     return result;
   }
 
   
   public void updateAuditstatus0(int[] ids) {
     this.orderBondedDao.updateAuditstatus0(ids);
   }
 
 
   
   public void updateCarrier(OrderBonded orderBonded) {
     this.orderBondedDao.updateCarrier(orderBonded);
   }
 
 
   
   public int findException() {
     return this.orderBondedDao.findException();
   }
 
   
   public List<String> getMailNoStraight(String carrier, int count) throws Exception {
     Date now = Tools.getCurrentTime();
     List<String> list = new ArrayList<>();
     if ("HTO".equals(carrier)) {
       for (int i = 0; i < count; i++) {
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
         element1.addElement("receiveMan").addText("张三");
         element1.addElement("receiveManPhone").addText("13855956478");
         element1.addElement("receiveManAddress").addText(Tools.removeSpecialCharacter("九堡"));
         element1.addElement("receiveProvince").addText("浙江省");
         element1.addElement("receiveCity").addText("杭州市");
         element1.addElement("receiveCounty").addText("江干区");
         element1.addElement("txLogisticID").addText("NDH" + System.currentTimeMillis() + i);
         element1.addElement("itemName").addText("商品");
         element1.addElement("itemWeight").addText("1.0");
         element1.addElement("itemCount").addText("1");
         element1.addElement("remark").addText("备注");
         element1.addElement("sortingcode").addText("");
 
 
         
         String mailNo = MatchMailNo.getHTOMailNo(document.asXML());
         if (mailNo.contains("错误")) {
           if (list.size() == 0) {
             list.add(mailNo);
             return list;
           } 
           list.add("单号不足");
           return list;
         } 
 
         
         TCarrierMailno tCarrierMailno = new TCarrierMailno();
         tCarrierMailno.setCarrier(carrier);
         tCarrierMailno.setMailno(mailNo);
         tCarrierMailno.setNum(Integer.valueOf(count));
         tCarrierMailno.setCreatetime(now);
         this.carrierMailnoDao.insert(tCarrierMailno);
         list.add(mailNo);
       }
     
     } else if ("STO".equals(carrier)) {
       for (int i = 0; i < count; i++) {
         try {
           String url2 = "http://122.224.67.130:8066/ecm/interface/rest/tendInterface/getbill";
           String param2 = "Sign=0888A1AFB593666C658BF5C315B3E3F3&User=ZTGYL&Password=123456&len=1";
           String res = GetBillcodeforSTO.sendPost(url2, param2);
 
           
           res = res.replaceAll("\\\\\"", "");
           res = res.replaceAll("\"", "");
           String[] str = res.replaceAll("}", "").split("-");
           String MailNo = "";
           if (str.length > 1) {
             byte b; int j; String[] arrayOfString; for (j = (arrayOfString = str).length, b = 0; b < j; ) { String s = arrayOfString[b];
               MailNo = s; b++; }
           
           } 
           TCarrierMailno tCarrierMailno = new TCarrierMailno();
           tCarrierMailno.setCarrier(carrier);
           tCarrierMailno.setMailno(MailNo);
           tCarrierMailno.setNum(Integer.valueOf(count));
           tCarrierMailno.setCreatetime(now);
           this.carrierMailnoDao.insert(tCarrierMailno);
           list.add(MailNo);
         } catch (Exception e) {
           e.printStackTrace();
           if (list.size() > 0) {
             return list;
           }
           list.add("错误");
           return list;
         } 
       } 
     }     
     return list;
   }
 
   
   public List<String> cancleOrder(int[] ids) {
     List<String> ret = new ArrayList<>();
     
     List<OrderBonded> list = this.orderBondedDao.findByIds(ids);
     for (OrderBonded orderBonded : list) {
       if (orderBonded.getIsPushQd() == 1) {
         ret.add("0");
         ret.add("取消失败：订单号" + orderBonded.getTxLogisticID() + "已经推过清单，不得取消订单！");
         return ret;
       } 
       if (orderBonded.getAuditstatus() == 8) {
         ret.add("0");
         ret.add("取消失败：订单号" + orderBonded.getTxLogisticID() + "已是取消订单！");
         return ret;
       } 
       int auditstatus = orderBonded.getAuditstatus();
       if (auditstatus == 2 || auditstatus == 1) {
         List list1 = this.orderBondedSkuDao.findByTxLogisticID(orderBonded.getTxLogisticID());
       }
       
       orderBonded.setAuditstatus(8);
       this.orderBondedDao.updateAuditstatusByTxLogisticID(orderBonded);
     } 
     
     ret.add("1");
     ret.add("取消成功,共" + list.size() + "条。");
     return ret;
   }
   
   public List<OrderBondedForExport> exportBondedByIds(String ids) {
     return this.orderBondedDao.exportBondedByIds(ids);
   }
 
   
   public int updateExpressParam3ForHTO(String ids) {
     String[] idArr = ids.split(",");
     int result = -1; byte b; int i; String[] arrayOfString1;
     for (i = (arrayOfString1 = idArr).length, b = 0; b < i; ) { String id = arrayOfString1[b];
       int parseInt = Integer.parseInt(id);
       OrderBonded orderBonded = this.orderBondedDao.findByIdToBG(Integer.valueOf(parseInt));
       
       if (orderBonded.getCarrier().equals("HTO")) {
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
         element1.addElement("receiveMan").addText(orderBonded.getReceiveMan());
         element1.addElement("receiveManPhone").addText(orderBonded.getReceiveManPhone());
         element1.addElement("receiveManAddress").addText(Tools.removeSpecialCharacter(orderBonded.getReceiveManAddress()));
         element1.addElement("receiveProvince").addText(orderBonded.getReceiveProvince());
         element1.addElement("receiveCity").addText(orderBonded.getReceiveCity());
         element1.addElement("receiveCounty").addText(orderBonded.getReceiveCounty());
         element1.addElement("txLogisticID").addText(orderBonded.getTxLogisticID());
         element1.addElement("itemName").addText(Tools.removeSpecialCharacter(orderBonded.getItemName()));
         element1.addElement("itemWeight").addText(orderBonded.getItemWeight().toString());
         element1.addElement("itemCount").addText(orderBonded.getItemCount().toString());
         element1.addElement("remark").addText("备注");
         element1.addElement("sortingcode").addText("");
 
 
         
         MatchMailNo.hqmd3(document.asXML(), orderBonded);
       } 
 
       
       if (StringUtils.isNotEmpty(orderBonded.getMailNo())) {
         orderBonded.setAuditstatus(2);
         result = this.orderBondedDao.updateExpressParam(orderBonded);
       }  b++; }
     
     return result;
   }
 
 
   
   public int payNumberTL(String ids) {
     return 1;
   }
   
   public void matchSDM(int[] ids) {
     System.out.println("=====" + ids);
     DecimalFormat df_2 = new DecimalFormat("0.000");
     List<OrderBonded> list = this.orderBondedDao.findByIds(ids);
     for (OrderBonded orderBonded : list) {
       System.out.println("=====" + orderBonded.getTxLogisticID());
       
       JSONObject jsonObject = new JSONObject();
       
       jsonObject.put("emsNo", "L2923B19A002");
       jsonObject.put("orderNo", orderBonded.getTxLogisticID());
       jsonObject.put("logisticsNo", orderBonded.getMailNo());
       jsonObject.put("logisticsCode", "3120980105");
       jsonObject.put("logisticsName", "上海韵达货运有限公司");
       jsonObject.put("customDeclco", "");
       jsonObject.put("portCode", "2923");
       jsonObject.put("copNo", orderBonded.getPreEntryNumber());
       jsonObject.put("assureCode", "33189630GS");
       jsonObject.put("agentCode", "33189630GS");
       jsonObject.put("agentName", "义乌齐越电子商务有限公司");
       jsonObject.put("areaCode", "3318W60026");
       jsonObject.put("areaName", "义乌凯昌进出口有限公司");
       jsonObject.put("trafMode", "8");
       jsonObject.put("country", "142");
       jsonObject.put("insuredFee", "0");
 
 
       
       double gw = 0.0D;
       double jz = 0.0D;
       JSONArray orderJSONArray = new JSONArray();
       List<OrderBondedSku> orderBondedSkuList = this.orderBondedSkuDao.findById(orderBonded.getTxLogisticID());
       int i = 0;
       for (OrderBondedSku orderBondedSku : orderBondedSkuList) {
         i++;
         Item item = this.orderBondedSkuDao.findItem(orderBondedSku.getItemsku());
         
         JSONObject jsonObjectlisy = new JSONObject();
         jsonObjectlisy.put("gnum", Integer.valueOf(i));
         jsonObjectlisy.put("itemSku", item.getItemSKU());
         jsonObjectlisy.put("itemRecordNo", Integer.valueOf(item.getInternalNumber()));
         jsonObjectlisy.put("itemNo", item.getItemcode());
         jsonObjectlisy.put("gname", item.getItemName());
         jsonObjectlisy.put("gcode", item.getHscode());
         jsonObjectlisy.put("hsCode", item.getHscode());
         jsonObjectlisy.put("gmodel", item.getItemSpec());
         jsonObjectlisy.put("barCode", item.getVendorItemCode());
         jsonObjectlisy.put("country", item.getCountry());
         
         jsonObjectlisy.put("currency", "142");
         jsonObjectlisy.put("price", orderBondedSku.getUnitPrice());
         jsonObjectlisy.put("totalPrice", orderBondedSku.getAllPrice());
         jsonObjectlisy.put("qty", orderBondedSku.getItemCount());
         jsonObjectlisy.put("unit", item.getUnitDesc());
         jsonObjectlisy.put("unit1", item.getOneUnitDesc());
         double ut = item.getUnitWeight() * orderBondedSku.getItemCount().intValue();
         gw += ut;
         if (item.getOneUnitDesc().equals("035")) {
           jsonObjectlisy.put("qty1", (new StringBuilder(String.valueOf(df_2.format(ut)))).toString());
         } else {
           
           jsonObjectlisy.put("qty1", orderBondedSku.getItemCount().toString());
         } 
         String twounitdesc = "";
         if (item.getTwoUnitDesc() == null || item.getTwoUnitDesc().equals("")) {
           twounitdesc = "";
         } else {
           twounitdesc = item.getTwoUnitDesc();
         } 
         if (!twounitdesc.equals(""))
         {
           if (twounitdesc.equals("035")) {
             jsonObjectlisy.put("unit2", twounitdesc);
             jsonObjectlisy.put("qty2", (new StringBuilder(String.valueOf(df_2.format(ut)))).toString());
           } else {
             
             jsonObjectlisy.put("unit2", twounitdesc);
             jsonObjectlisy.put("qty2", orderBondedSku.getItemCount().toString());
           } 
         }
         orderJSONArray.add(jsonObjectlisy);
       } 
       
       jz = gw + 0.1D;
       jsonObject.put("grossWeight", (new StringBuilder(String.valueOf(df_2.format(jz)))).toString());
       jsonObject.put("netWeight", (new StringBuilder(String.valueOf(df_2.format(gw)))).toString());
       
       jsonObject.put("InventoryList", orderJSONArray);
       
       List<SzqgAgreement> lists = this.szqgagreementdao.findgjz("5");
       String PriKey = "";
       for (SzqgAgreement sam : lists) {
         jsonObject.put("appid", sam.getAppid());
         jsonObject.put("version", sam.getVersion());
 
         
         jsonObject.put("commitTime", timenew.newtime());
         
         PriKey = sam.getPersonPriKey();
       } 
       
       ApolloSdk apolloSdk = new ApolloSdk();
       System.out.println("jsonObject=" + jsonObject.toString());
       
       try {
         String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey.replaceAll("\\n", "").replaceAll("\\r", "")), jsonObject.toString());
         
         System.out.println("param=" + param);
         
         String url = "http://apollo.jieztech.com/aoa/api/aoa/logistics/logisticsOrderorderCallBack";
 
         
         String res = "";
         try {
           res = hcszqgpost.ZYPost(url, param);
           System.out.println("res=" + res);
         } catch (Exception e) {
           
           e.printStackTrace();
         
         }
       
       }
       catch (InvalidKeyException e) {
         
         e.printStackTrace();
       } catch (NoSuchAlgorithmException e) {
         
         e.printStackTrace();
       } catch (InvalidKeySpecException e) {
         
         e.printStackTrace();
       } catch (SignatureException e) {
         
         e.printStackTrace();
       } catch (NoSuchPaddingException e) {
         
         e.printStackTrace();
       } catch (BadPaddingException e) {
         
         e.printStackTrace();
       } catch (IllegalBlockSizeException e) {
         
         e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
         
         e.printStackTrace();
       } 
     } 
   }
   
   public int payNumberKQ(String ids) {
     String[] idArr = ids.split(",");
     int i = 1;
     
     return i;
   }
 
 
 
   
   public String pushBfb(String id, String no) throws Exception {
     String buffer = "";
     if (no.equals("1")) {
       buffer = pushBfbUtil(id);
     } else if (no.equals("3")) {
       buffer = pushldUtil(id);
     } 
 
     
     return buffer;
   }
   
   public String pushBfbUtil(String id) throws Exception {
     return "success";
   }
 
 
 
   
   public String pushldUtil(String id) throws Exception {
     return "success";
   }
   
   public String getBfbUtil(String id) throws Exception {
     return "success";
   }
 
   
   public int updatePushOrderZFB(String ids) {
     String[] idArr = ids.split(",");
     int i = -1;
     
     return i;
   }
 
   
   public synchronized String pushForCFM(String ids) throws Exception {
     return "success";
   }
 
 
   
   public String checkIdCard(String ids) throws Exception {
     return "success";
   }
   
   public String pushForCFMByShiMing(IdCard idCard) throws Exception {
     return "success";
   }
 
 
 
   
   public void deleteByIds(int[] ids) {}
 
 
 
   
   public OrderBonded findInfo(String txLogisticID) {
     return this.orderBondedDao.findInfo(txLogisticID);
   }
 
 
   
   public int findInfoCount(String txLogisticID) {
     return this.orderBondedDao.findInfoCount(txLogisticID);
   }
 
   
   public JSONObject queryOrderByParamInsert(String json) throws Exception {
     Map<String, String> map = new HashMap<>();
     
     List<OrderBonded> lists = this.orderBondedDao.getMapTxLogisticID();
     
     for (OrderBonded orderBonded : lists) {
       map.put(orderBonded.getTxLogisticID(), orderBonded.getTxLogisticID());
     }
     
     List<OrderBonded> bondeds = new ArrayList<>();
     
     List<OrderBondedSku> bondedSkus = new ArrayList<>();
     json = json.replace("\\", "").replace("\"{", "{").replace("}\"", "}");
     System.out.println("cscscs======" + json);
     JSONObject ret = JSONObject.parseObject(json);
     JSONObject responce = ret.getJSONObject("jingdong_hangzhou_customs_queryOrderByParam_responce");
     JSONObject orderListResult = responce.getJSONObject("orderListResult");
     
     Integer ListTotalCount = orderListResult.getInteger("totalCount");
     if (ListTotalCount.intValue() > 0)
     { JSONArray body = orderListResult.getJSONArray("body");
       for (int i = 0; i < body.size(); i++) {
         JSONObject bodyobj = body.getJSONObject(i);
         JSONObject body2 = bodyobj.getJSONObject("body");
 
         
         if (bodyobj.getString("body") != null) {
 
 
           
           JSONArray orderInfoList = body2.getJSONArray("orderInfoList");
           for (int j = 0; j < orderInfoList.size(); j++) {
 
             
             JSONObject jkfGoodsPurchaser = orderInfoList.getJSONObject(j);
             
             JSONObject jkfOrderImportHead = jkfGoodsPurchaser.getJSONObject("jkfOrderImportHead");
             String orderNo = jkfOrderImportHead.getString("orderNo");
 
             
             if (map.get(orderNo) == null) {
 
               
               String buyerIdNumber = jkfOrderImportHead.getString("buyerIdNumber");
               
               String buyerName = jkfOrderImportHead.getString("buyerName");
               String city = jkfOrderImportHead.getString("city");
               String companyCode = jkfOrderImportHead.getString("companyCode");
 
               
               String consigneeAddress = jkfOrderImportHead.getString("consigneeAddress");
               
               String consigneeTel = jkfOrderImportHead.getString("consigneeTel");
               String county = jkfOrderImportHead.getString("county");
 
               
               Double discount = jkfOrderImportHead.getDouble("discount");
               String eCommerceCode = jkfOrderImportHead.getString("eCommerceCode");
               String eCommerceName = jkfOrderImportHead.getString("eCommerceName");
               Double feeAmount = Double.valueOf(Double.parseDouble(jkfOrderImportHead.getString("feeAmount")));
 
               
               Double insureAmount = Double.valueOf(Double.parseDouble(jkfOrderImportHead.getString("orderTaxAmount")));
 
 
 
               
               Double orderGoodsAmount = Double.valueOf(Double.parseDouble(jkfOrderImportHead.getString("orderGoodsAmount")));
 
               
               String payCompanyCode = jkfOrderImportHead.getString("payCompanyCode");
               
               String payNumber = jkfOrderImportHead.getString("payNumber");
 
               
               String province = jkfOrderImportHead.getString("province");
               
               Integer totalCount = Integer.valueOf(Integer.parseInt(jkfOrderImportHead.getString("totalCount")));
 
               
               String wayBills = jkfOrderImportHead.getString("wayBills");
               String venderId = jkfOrderImportHead.getString("venderId");
 
               
               JSONObject jkfSign = jkfGoodsPurchaser.getJSONObject("jkfSign");
               
               String MerchantNum = "JD";
               if (eCommerceCode.equals("4401962010")) {
                 MerchantNum = "ZYD";
               }
 
 
               
               OrderBonded orderBonded = new OrderBonded();
               orderBonded.setTxLogisticID(orderNo);
               
               orderBonded.setReceiveMan(buyerName);
               orderBonded.setReceiveManPhone(consigneeTel);
               orderBonded.setReceiveManAddress(consigneeAddress);
               orderBonded.setReceiveProvince(province);
               orderBonded.setReceiveCity(city);
               orderBonded.setReceiveCounty(county);
               orderBonded.setBuyerName(buyerName);
               orderBonded.setBuyerIdNumber(buyerIdNumber);
               orderBonded.setFeeAmount(feeAmount);
               orderBonded.setInsureAmount(insureAmount);
               orderBonded.setMerchantNum(MerchantNum);
               orderBonded.setWorth(orderGoodsAmount);
               orderBonded.setCarrier("JBD");
               orderBonded.setMailNo(wayBills);
               orderBonded.setSendWarehouse(venderId);
               orderBonded.setItemCount(totalCount);
               orderBonded.setPayCompany(payCompanyCode);
               orderBonded.setPayNumber(payNumber);
               orderBonded.setItemName("宠粮");
               orderBonded.setCustomsTax(discount.toString());
               bondeds.add(orderBonded);
 
               
               JSONArray jkfOrderDetailList = jkfGoodsPurchaser.getJSONArray("jkfOrderDetailList");
               for (int k = 0; k < jkfOrderDetailList.size(); k++)
               { JSONObject jkfOrderDetailList2 = jkfOrderDetailList.getJSONObject(k);
                 
                 String goodsName = jkfOrderDetailList2.getString("goodsName");
                 String productId = jkfOrderDetailList2.getString("productId");
                 Integer itemcount = Integer.valueOf(Integer.parseInt(jkfOrderDetailList2.getString("goodsCount")));
                 Double unitPrice = Double.valueOf(Double.parseDouble(jkfOrderDetailList2.getString("unitPrice")));
                 
                 OrderBondedSku sku = new OrderBondedSku();
                 sku.setItemName(goodsName);
                 sku.setItemsku(productId);
                 sku.setUnitPrice(unitPrice);
                 sku.setItemCount(itemcount);
                 sku.setTxLogisticID(orderNo);
                 sku.setAllPrice(Double.valueOf(unitPrice.doubleValue() * itemcount.intValue()));
                 bondedSkus.add(sku); } 
             } 
           } 
         } 
       }  }
     else { JSONObject jSONObject = orderListResult.getJSONObject("header");
       jSONObject.put("page", orderListResult.getInteger("page"));
       Tools.fileLog("=======响应数据==========" + json);
       return jSONObject; }
     
     if (bondeds.size() > 0) {
       
       this.orderBondedDao.insertBatch(bondeds);
       this.orderBondedDao.insertBatchSku(bondedSkus);
       
       Tools.fileLog("=======响应数据==========" + json);
     } else {
       JSONObject jSONObject = orderListResult.getJSONObject("header");
       jSONObject.put("page", orderListResult.getInteger("page"));
       
       Tools.fileLog("=======响应数据==========" + json);
       return jSONObject;
     } 
     JSONObject header = orderListResult.getJSONObject("header");
     header.put("page", orderListResult.getInteger("page"));
     return header;
   }
 
 
 
   
   public String CreateOrder(String str) throws Exception {
     long startTime = System.currentTimeMillis();
     
     if (orderMap.isEmpty()) {
       
       List<OrderBonded> lists = this.orderBondedDao.getMapTxLogisticID();
       
       for (OrderBonded orderBonded : lists) {
         orderMap.put(orderBonded.getTxLogisticID(), orderBonded.getTxLogisticID());
       }
     } 
     
     long endTime = System.currentTimeMillis();
     
     long runTime = endTime - startTime;
     
     System.out.println("map查询运行时间毫秒数是: " + runTime + "ms");
 
     
     System.out.println("json=" + str);
     String msg = "success";
     JSONObject ret = JSONObject.parseObject(str);
     String appId = ret.getString("appid");
     System.out.println("appId=====" + appId);
     String sign = ret.getString("sign");
     
     String orderList = ret.getString("orderList");
     JSONArray detailorderListList = JSONArray.parseArray(orderList);
     for (int i = 0; i < detailorderListList.size(); i++) {
       JSONObject orderJSONObject = detailorderListList.getJSONObject(i);
       String version = orderJSONObject.getString("version");
       String tcOrderId = orderJSONObject.getString("tcOrderId");
 
       
       System.out.println("tcOrderId=" + tcOrderId);
       System.out.println("orderMap.get(tcOrderId)=" + (String)orderMap.get(tcOrderId));
       
       if (orderMap.get(tcOrderId) != null) {
         
         this.orderBondedDao.deletefottxLogisticID(tcOrderId);
         this.orderBondedSkuDao.delete(tcOrderId);
       } 
       
       orderMap.put(tcOrderId, tcOrderId);
 
 
       
       String supplierUsername = orderJSONObject.getString("supplierUsername");
       String receiveMan = orderJSONObject.getString("receiveMan").replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", ",");
       String receiveProvince = orderJSONObject.getString("receiveProvince");
       String receiveCity = orderJSONObject.getString("receiveCity");
       String receiveCounty = orderJSONObject.getString("receiveCounty");
       String receiveManAddress = orderJSONObject.getString("receiveManAddress").replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", ",");
       String receiveManPhone = orderJSONObject.getString("receiveManPhone");
       String feeAmount = orderJSONObject.getString("feeAmount");
       String insureAmount = orderJSONObject.getString("insureAmount");
       
       OrderBonded orderBonded = new OrderBonded();
       orderBonded.setTxLogisticID(tcOrderId);
       orderBonded.setReceiveMan(receiveMan);
       orderBonded.setReceiveProvince(receiveProvince);
       orderBonded.setReceiveCity(receiveCity);
       orderBonded.setReceiveCounty(receiveCounty);
       orderBonded.setReceiveManAddress(receiveManAddress);
       orderBonded.setReceiveManPhone(receiveManPhone);
       orderBonded.setItemName("分销商品");
       orderBonded.setFeeAmount(Double.valueOf(feeAmount));
       orderBonded.setInsureAmount(Double.valueOf(insureAmount));
       orderBonded.setBuyerIdNumber("无");
       orderBonded.setBuyerName("无");
       orderBonded.setCarrier("JBG");
       orderBonded.setSendWarehouse(supplierUsername);
       orderBonded.setMerchantNum("YWBJG");
       orderBonded.setMark(Integer.valueOf(1));
       orderBonded.setUserId(1);
       orderBonded.setIspost(0);
       orderBonded.setIsCustoms(0);
       orderBonded.setAuditstatus(0);
 
 
       
       String skuList = orderJSONObject.getString("skuList");
       JSONArray detailDtoList = JSONArray.parseArray(skuList);
       
       List<OrderBondedSku> obsList = new ArrayList<>();
       for (int j = 0; j < detailDtoList.size(); j++) {
 
         
         JSONObject orderskuJSONObject = detailDtoList.getJSONObject(j);
         String itemName = orderskuJSONObject.getString("itemName");
         String itemSku = orderskuJSONObject.getString("itemSku");
         String itemCount = orderskuJSONObject.getString("itemCount");
         String unitPrice = orderskuJSONObject.getString("unitPrice");
         System.out.println("unitPrice=" + unitPrice);
         String allPrice = orderskuJSONObject.getString("allPrice");
         
         OrderBondedSku orderBondedSku = new OrderBondedSku();
         orderBondedSku.setTxLogisticID(tcOrderId);
         orderBondedSku.setItemCount(Integer.valueOf(Integer.parseInt(itemCount)));
         orderBondedSku.setAllPrice(Double.valueOf(allPrice));
         orderBondedSku.setItemName(itemName);
         orderBondedSku.setItemWeight(Double.valueOf("1"));
         orderBondedSku.setUnitPrice(Double.valueOf(unitPrice));
         orderBondedSku.setItemsku(itemSku);
         obsList.add(orderBondedSku);
       } 
 
       
       List<OrderBonded> orderlist = new ArrayList<>();
       orderlist.add(orderBonded);
       
       long endTime2 = System.currentTimeMillis();
       
       long runTime2 = endTime2 - endTime;
       
       System.out.println("解析查询运行时间毫秒数是: " + runTime2 + "ms");
       
       this.orderBondedDao.insertBatch(orderlist);
       
       int count = this.orderBondedSkuDao.batchInsert(obsList);
       long endTime3 = System.currentTimeMillis();
       long runTime3 = endTime3 - endTime2;
       
       System.out.println("插入查询运行时间毫秒数是: " + runTime3 + "ms");
     } 
     
     return msg;
   }
 
   
   public List<OrderBondedForExport> exportOrderBonded(OrderBondedForExport orderBondedForExport) {
     return this.orderBondedDao.exportOrderBonded(orderBondedForExport);
   }
 
 
   
   public int backOrder(OrderBondedForExport bondedForExport) {
     return this.orderBondedDao.backOrder(bondedForExport);
   }
 }


