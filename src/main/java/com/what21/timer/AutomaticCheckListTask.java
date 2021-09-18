 package com.what21.timer;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import com.what21.dao.ItemDao;
 import com.what21.dao.OrderBondDao;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.OrderBondedSkuDao;
 import com.what21.dao.OrderMailDao;
 import com.what21.dao.ReceiveHghzDataDao;
 import com.what21.dao.SzqgAgreementDao;
 import com.what21.model.AutoCheckListExtend;
 import com.what21.model.EchattsBonded;
 import com.what21.model.EchattsResult;
 import com.what21.model.Item;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedSku;
 import com.what21.model.OrderMail;
 import com.what21.model.ReceiveHghzData;
 import com.what21.model.SzqgAgreement;
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.service.AutoCheckListService;
 import com.what21.service.OrderBondedService;
 import com.what21.service.OrderMailService;
 import com.what21.tools.Tools;
 import com.what21.util.MD5Util;
 import com.what21.util.StringUtil;
 import com.what21.util.timenew;
 import com.zt.ewtp.hcszqgpost;
 import com.zt.kjybd.PushToWmsNew;
 import com.zt.kjybd.PushtoWQ;
 import com.zt.kjybd.newzs;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.StringWriter;
 import java.io.UnsupportedEncodingException;
 import java.math.BigDecimal;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.SignatureException;
 import java.security.spec.InvalidKeySpecException;
 import java.text.DecimalFormat;
 import java.util.List;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
 import javax.servlet.ServletContext;
 import org.jdom.Document;
 import org.jdom.input.SAXBuilder;
 import org.jdom.output.Format;
 import org.jdom.output.XMLOutputter;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.scheduling.annotation.Scheduled;
 import org.springframework.stereotype.Component;
 import org.springframework.web.context.ContextLoader;
 import org.springframework.web.context.WebApplicationContext;
 @Component
 public class AutomaticCheckListTask
 {
   @Autowired
   private ReceiveHghzDataDao receiveHghzDataDao;
   @Autowired
   OrderBondedService orderBondedService;
   @Autowired
   OrderMailService OrderMailService;
   @Autowired
   AutoCheckListService autoCheckListService;
   String CKDM = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.CKDM.getValue());
   String SERVER_URL = "https://api.jd.com/routerjson";
   String appKey = "3B03D933A76CF26E84CD449B63FEE94F";
   String accessToken = "a2b7cc35e5d240b3bd529a9fc9524610tjin";
   String appSecret = "33bd053e696c4cd9b6600148dea111ec";
   
   @Autowired
   public OrderBondedDao orderBondedDao;
   
   @Autowired
   public SzqgAgreementDao szqgagreementdao;
   
   @Autowired
   public OrderBondedSkuDao orderBondedSkuDao;
   
   @Autowired
   private OrderMailDao orderMailDao;
   
   @Autowired
   public OrderBondDao orderBondDao;
   
   @Autowired
   public ItemDao itemdao;
   @Autowired
   private OrderMailService orderMailService;
   
   public void AutomaticCheckList() throws Exception {
     System.out.println("启动凯仓定时任务！");
 
     
     OrderYs();
     
     OrderTokc();
 
 
     
     OrderZtTokc();
   }
   
   public void zshzjx2() throws Exception {
     String path = "D:\\kchz";
     File file = new File(path);
     File[] tempList = file.listFiles();
     for (int i = 0; i < tempList.length; i++) {
       if (tempList[i].isFile()) {
         System.out.println("文     件：" + tempList[i]);
         
         FileInputStream fis = new FileInputStream(tempList[i]);
         
         InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
         BufferedReader br = new BufferedReader(isr);
         
         while (br.ready()) {
           String zfc = br.readLine();
           System.out.println("zfc" + zfc);
           if (zfc.contains("copNo")) {
             JSONObject jsonObject = JSONObject.parseObject(zfc);
             String copNo = jsonObject.get("copNo").toString();
             String returnInfo = jsonObject.get("returnInfo").toString();
             String returnStatus = jsonObject.get("returnStatus").toString();
             if (copNo.startsWith("YW1210")) {
               System.out.println("ewt12103");
               if (returnStatus.equals("800")) {
                 String invtNo = jsonObject.get("invtNo").toString();
                 OrderBonded orderBonded1 = new OrderBonded();
                 orderBonded1.setPreEntryNumber(copNo);
                 orderBonded1.setReturnInfo(returnInfo);
                 orderBonded1.setAuditstatus(9);
                 orderBonded1.setReturncode(Integer.valueOf(9));
                 orderBonded1.setPreEntryNumber(copNo);
                 orderBonded1.setInvtNo(invtNo);
                 this.orderBondedDao.updateAuditstatus3(orderBonded1);
                 continue;
               } 
               if (returnStatus.equals("100")) {
                 OrderBonded orderBonded1 = new OrderBonded();
                 orderBonded1.setPreEntryNumber(copNo);
                 orderBonded1.setReturnInfo(returnInfo);
                 orderBonded1.setAuditstatus(7);
                 orderBonded1.setReturncode(Integer.valueOf(1));
                 orderBonded1.setPreEntryNumber(copNo);
                 orderBonded1.setInvtNo("");
                 this.orderBondedDao.updateAuditstatus3(orderBonded1); continue;
               } 
               OrderBonded orderBonded = new OrderBonded();
               orderBonded.setPreEntryNumber(copNo);
               orderBonded.setReturnInfo(returnInfo);
               orderBonded.setAuditstatus(7);
               orderBonded.setReturncode(Integer.valueOf(1));
               orderBonded.setPreEntryNumber(copNo);
               orderBonded.setInvtNo("");
               this.orderBondedDao.updateAuditstatus3(orderBonded);
               
               continue;
             } 
             System.out.println("ewt其他3");
           } 
         }         
         System.out.println("sc:" + tempList[i].toString());
         fis.close();
         File dfile = new File(tempList[i].toString());
         
         System.out.println("sc:" + dfile.delete());
       } 
     } 
   }
   
   public void OrderZtTokc() throws Exception {
     System.out.println("启动凯仓定时任务！3下发清关回执T凯昌 ");
     String A = "1=1 and auditstatus ='9' and ispushtowms ='0' ";
     List<OrderBonded> list = this.orderBondedDao.findbyA(A);
     for (OrderBonded orderBonded : list) {
       
       String SECRET = "";
       String APP_ID = "";
       if (orderBonded.getSendWarehouse().equals("赛隆电商供货商")) {
         SECRET = "c0f8f3ee-adfa-4a33-aca6-7fcce3d1ad5e";
         APP_ID = "880140";
       } else if (orderBonded.getSendWarehouse().equals("天行云供货商")) {
         SECRET = "C3E57D6F-ET6Y-IIJL-P9UC-XAERF3DQML9H";
         APP_ID = "880221";
       } else if (orderBonded.getSendWarehouse().equals("apex供货商")) {
         SECRET = "C3E57D6F-ET6Y-IIJL-P9UC-CRTBSDBT88JX";
         APP_ID = "880222";
       } else if (orderBonded.getSendWarehouse().equals("vitota供货商")) {
         SECRET = "8RT57D6F-VD9Y-IIJL-P9UC-CRTBSDBT88JX";
         APP_ID = "880219";
       } else if (orderBonded.getSendWarehouse().equals("亚伺贸易供货商")) {
         SECRET = "C3E57D6F-ET6Y-IIJL-P9UC-CRTBSDBT88JZ";
         APP_ID = "880226";
       }
       else if (orderBonded.getSendWarehouse().equals("日本良品严选供货商")) {
         SECRET = "C3E57E6F-ET8Y-IIJL-P9UC-ARCBSDBT48JC";
         APP_ID = "880227";
       } 
 
       
       SECRET = MD5Util.MD5(SECRET);
       
       JSONObject order = new JSONObject();
       order.put("msgid", orderBonded.getTxLogisticID());
       order.put("copNo", orderBonded.getPreEntryNumber());
       order.put("invtNo", orderBonded.getInvtNo());
       order.put("returnInfo", "[Code:2600;Desc:放行]");
       order.put("code", "88888");
 
       
       JSONObject abc = new JSONObject();
       abc.put("APP_ID", APP_ID);
       abc.put("SECRET", SECRET);
       abc.put("REQUEST_DATA", order);
 
       
       System.out.println("abc=" + abc.toJSONString());
       Tools.fileLog("=======EWTP凯昌清关回执==========" + abc.toJSONString());
       String url = "http://storage.kjruyigou.com/api/ewtpRequest.php";
       String ret = PushtoWQ.sendPostewtpkc(url, abc.toJSONString());
       Tools.fileLog("=======EWTP凯昌清关回执返回==========" + ret);
       
       System.out.println("凯昌订单下发回执" + ret);
       
       JSONObject hz = JSONObject.parseObject(ret);
       String code = hz.get("code").toString();
       if (code.equals("200")) {
         this.orderBondedDao.updateAtoB(" ispushtowms='1' ", " id = '" + orderBonded.getId() + "'"); continue;
       } 
       String msg = hz.get("msg").toString();
       this.orderBondedDao.updateAtoB(" returninfo='" + msg + "' ,auditstatus ='7' ", " id = '" + orderBonded.getId() + "'");
     } 
   }
   
   public void OrderToJz() throws Exception {
     System.out.println("启动凯仓定时任务！2回填介舟 ");
     String A = "1=1 and auditstatus ='2' and ispost='1' and isPushQd='0' ";
     List<OrderBonded> list = this.orderBondedDao.findbyA(A);
     DecimalFormat df_2 = new DecimalFormat("0.000");
     for (OrderBonded orderBonded : list) {
       JSONObject jsonObject = new JSONObject();
       jsonObject.put("emsNo", "L2923B19A002");
       jsonObject.put("orderNo", orderBonded.getTxLogisticID());
       jsonObject.put("logisticsNo", orderBonded.getMailNo());
       jsonObject.put("logisticsCode", orderBonded.getBillProvideSiteCode());
       jsonObject.put("logisticsName", orderBonded.getBillProvideSiteName());
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
         
         JSONObject jsonObjectlisy = new JSONObject();
         jsonObjectlisy.put("gnum", Integer.valueOf(i));
         jsonObjectlisy.put("itemSku", orderBondedSku.getItemsku());
         jsonObjectlisy.put("itemRecordNo", Integer.valueOf(orderBondedSku.getInternalNumber()));
         jsonObjectlisy.put("itemNo", orderBondedSku.getItemsku());
         jsonObjectlisy.put("gname", orderBondedSku.getItemName());
         jsonObjectlisy.put("gcode", orderBondedSku.getHsCode());
         jsonObjectlisy.put("hsCode", orderBondedSku.getHsCode());
         jsonObjectlisy.put("gmodel", orderBondedSku.getGmodel());
         jsonObjectlisy.put("barCode", orderBondedSku.getBarCode());
         jsonObjectlisy.put("country", orderBondedSku.getCountry());
 
         
         jsonObjectlisy.put("currency", "142");
         jsonObjectlisy.put("price", orderBondedSku.getUnitPrice());
         jsonObjectlisy.put("totalPrice", orderBondedSku.getAllPrice());
         jsonObjectlisy.put("qty", orderBondedSku.getItemCount());
         jsonObjectlisy.put("unit", orderBondedSku.getUnit());
         jsonObjectlisy.put("unit1", orderBondedSku.getUnit1());
         double ut = orderBondedSku.getItemWeight().doubleValue() * orderBondedSku.getItemCount().intValue();
         gw += ut;
         
         BigDecimal first = new BigDecimal(orderBondedSku.getQty1());
         
         BigDecimal Itemt = new BigDecimal(orderBondedSku.getItemCount().intValue());
         
         BigDecimal rfirst = first.multiply(Itemt);
         jsonObjectlisy.put("qty1", rfirst);
 
         
         String twounitdesc = "";
         if (orderBondedSku.getUnit2() == null || orderBondedSku.getUnit2().equals("")) {
           twounitdesc = "";
         } else {
           twounitdesc = orderBondedSku.getUnit2();
         } 
         if (!twounitdesc.equals("")) {
 
           
           BigDecimal s2cond = new BigDecimal(orderBondedSku.getQty2());
           
           jsonObjectlisy.put("unit2", twounitdesc);
           jsonObjectlisy.put("qty2", s2cond.multiply(Itemt));
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
       Tools.fileLog("=======EWTP介舟回填==========" + jsonObject.toJSONString());
       
       try {
         String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey.replaceAll("\\n", "").replaceAll("\\r", "")), jsonObject.toString());
         
         System.out.println("param=" + param);
         String url = "http://apollo.jieztech.com/aoa/api/aoa/logistics/logisticsOrderorderCallBack";
 
         
         String res = "";
         try {
           res = hcszqgpost.ZYPost(url, param);
           
           Tools.fileLog("=======EWTP介舟回填回执==========" + res);
           JSONObject hz = JSONObject.parseObject(res);
           String code = hz.get("code").toString();
           if (code.equals("200")) {
             this.orderBondedDao.updateAtoB(" isPushQd='1' ", " id = '" + orderBonded.getId() + "'");
           }
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
   
   public void OrderTokc() throws Exception {
     System.out.println("启动凯仓定时任务！1下发凯昌订单 ");
     String A = "1=1 and auditstatus in('1') and ispost='0' ";
     List<OrderBonded> list = this.orderBondedDao.findbyA(A);
     
     for (OrderBonded orderBonded : list) {
       System.out.println("order=" + orderBonded.getTxLogisticID());
       
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
       String sku = "";
       for (OrderBondedSku orderBondedSku : orderBondedSkuList) {
         JSONObject Item = new JSONObject();
         if (orderBondedSku.getItemsku().equals(sku)) {
           continue;
         }
         Item.put("partno", orderBondedSku.getItemsku());
         Item.put("qty", orderBondedSku.getItemCount());
         Item.put("amount", orderBondedSku.getAllPrice());
         
         sku = orderBondedSku.getItemsku();
         skuList.add(Item);
       } 
       order.put("sku_list", skuList);
       
       System.out.println("orderjson=" + order.toJSONString());
 
       
       String SECRET = "";
       String APP_ID = "";
       if (orderBonded.getSendWarehouse().equals("赛隆电商供货商")) {
         SECRET = "c0f8f3ee-adfa-4a33-aca6-7fcce3d1ad5e";
         APP_ID = "880140";
       } else if (orderBonded.getSendWarehouse().equals("天行云供货商")) {
         SECRET = "C3E57D6F-ET6Y-IIJL-P9UC-XAERF3DQML9H";
         APP_ID = "880221";
       } else if (orderBonded.getSendWarehouse().equals("apex供货商")) {
         SECRET = "C3E57D6F-ET6Y-IIJL-P9UC-CRTBSDBT88JX";
         APP_ID = "880222";
       } else if (orderBonded.getSendWarehouse().equals("vitota供货商")) {
         SECRET = "8RT57D6F-VD9Y-IIJL-P9UC-CRTBSDBT88JX";
         APP_ID = "880219";
       } else if (orderBonded.getSendWarehouse().equals("亚伺贸易供货商")) {
         SECRET = "C3E57D6F-ET6Y-IIJL-P9UC-CRTBSDBT88JZ";
         APP_ID = "880226";
       }
       else if (orderBonded.getSendWarehouse().equals("日本良品严选供货商")) {
         SECRET = "C3E57E6F-ET8Y-IIJL-P9UC-ARCBSDBT48JC";
         APP_ID = "880227";
       } 
 
       
       SECRET = MD5Util.MD5(SECRET);
       
       JSONObject abc = new JSONObject();
       abc.put("APP_ID", APP_ID);
       abc.put("SECRET", SECRET);
       abc.put("REQUEST_DATA", order);
 
       
       System.out.println("abc=" + abc.toJSONString());
       Tools.fileLog("=======EWTP凯昌订单下发报文==========" + abc.toJSONString());
       String url = "http://storage.kjruyigou.com/api/ewtpServer.php";
       String ret = PushtoWQ.sendPostewtpkc(url, abc.toJSONString());
       System.out.println("凯昌订单下发回执" + ret);
       Tools.fileLog("=======EWTP凯昌订单下发回执==========" + ret);
       
       JSONObject hz = JSONObject.parseObject(ret);
       String code = hz.get("code").toString();
       if (code.equals("200")) {
         this.orderBondedDao.updateAtoB(" ispost='1' ", " id = '" + orderBonded.getId() + "'"); continue;
       } 
       String msg = hz.get("msg").toString();
       this.orderBondedDao.updateAtoB(" returninfo='" + msg + "' ,auditstatus ='7' ", " id = '" + orderBonded.getId() + "'");
     } 
   }
   
   public void OrderYs() throws Exception {
     String A = "1=1 and auditstatus ='0' ";
     List<OrderBonded> list = this.orderBondedDao.findbyA(A);
     System.out.println("启动凯仓定时任务！0订单预审");
     for (OrderBonded order : list) {
       int one = 1;
       System.out.println("order=" + order.getTxLogisticID());
       Item item = new Item();
       OrderBonded orderBonded = new OrderBonded();
       
       if (one != 11) {
         String preEntryNumber = "YW1210" + newzs.getRandomCharAndNumr(Integer.valueOf(12));
         orderBonded.setPreEntryNumber(preEntryNumber);
         String mailNo = order.getMailNo();
         if (mailNo == null || mailNo.equals("") || mailNo.equals("undefined")) {
           orderBonded.setAuditstatus(1);
         } else {
           orderBonded.setAuditstatus(2);
         } 
       } 
       orderBonded.setId(order.getId());
       
       this.orderBondedDao.updateAuditstatus(orderBonded);
     } 
   }
   
   public void item() throws Exception {
     String gjz = "wmstype is null  or wmstype='' or wmstype='0'";
     List<Item> list = this.itemdao.findgjz(gjz);
     
     System.out.println("开始同步商品！！");
     for (Item item : list) {
       
       JSONObject gyorder = new JSONObject();
       gyorder.put("product_sku", item.getItemSKU());
       gyorder.put("reference_no", item.getItemSKU());
       gyorder.put("product_title", item.getItemName());
       gyorder.put("product_weight", Double.valueOf(item.getUnitWeight()));
       gyorder.put("product_length", "1");
       gyorder.put("product_width", "1");
       gyorder.put("product_height", "1");
       
       gyorder.put("contain_battery", "0");
       gyorder.put("product_declared_value", "30");
       gyorder.put("product_declared_name", item.getItemName());
       gyorder.put("product_declared_name_zh", item.getItemName());
       gyorder.put("cat_lang", "en");
       gyorder.put("cat_id_level0", "400001");
       gyorder.put("cat_id_level1", "500013");
       gyorder.put("cat_id_level2", "600109");
       
       gyorder.put("verify", "0");
       gyorder.put("warning_days", "30");
       gyorder.put("warning_qty", "100");
       gyorder.put("product_brand", "保税");
       gyorder.put("product_model", "无");
       gyorder.put("product_origin", "133");
       gyorder.put("product_material", "略");
       gyorder.put("product_desc_url", "www.baidu.com");
       gyorder.put("customerImg", "");
       System.out.println("gyorder=" + gyorder.toString());
       String ret = PushToWmsNew.ygpushDataforitem(gyorder.toString());
       System.out.println("ret=" + ret);
       if (ret.contains("Success")) {
         this.itemdao.updateWmsType2(item.getId().intValue());
       }
     } 
   }
   
   public void url() throws Exception {
     String gjz = " vid is null ";
   }
   
   public void updateAuditstatus() throws Exception {
     String auditstatus = "0";
     String plan = "orderPreview";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     if (lists.size() > 0) {
       String paramJson = "{'paramList':[";
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         String mailNo = "";
         if (autoCheckListExtend.getMailNo() != null) {
           mailNo = autoCheckListExtend.getMailNo();
         }
         String param = "{'id':" + autoCheckListExtend.getId() + ",'mailNo':'" + mailNo + 
           "','txLogisticID':'" + autoCheckListExtend.getTxLogisticID() + "','itemCount':'" + 
           autoCheckListExtend.getItemCount() + "','merchantNum':'" + autoCheckListExtend.getMerchantNum() + 
           "','worth':'" + autoCheckListExtend.getWorth() + "','buyerName':'" + 
           autoCheckListExtend.getBuyerName() + "','buyerIdNumber':'" + 
           autoCheckListExtend.getBuyerIdNumber() + "'},";
         paramJson = String.valueOf(paramJson) + param;
       } 
       paramJson = paramJson.substring(0, paramJson.length() - 1);
       paramJson = String.valueOf(paramJson) + "]}";
       System.out.println(paramJson);
       
       this.orderBondedService.updateAuditstatus(paramJson);
       System.out.println("订单预审执行成功！！");
     } else {
       System.out.println("没有需要审核的订单");
     } 
   }
   
   public void downPlatform() throws Exception {
     String auditstatus = "2";
     String plan = "ispost=0 and downPlatform";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
       } 
       System.out.println(ids);
       
       if (this.CKDM.equals("LO")) {
         
         this.orderBondedService.pushBfbUtil(ids.toString());
       } else if (this.CKDM.equals("JL")) {
         this.orderBondedService.pushBfbUtil(ids.toString());
       } else if (this.CKDM.equals("LC")) {
         this.orderBondedService.pushOrderToDsds(ids.toString());
       } 
       
       System.out.println("下发平台执行成功！！");
     } else {
       System.out.println("没有需要下发平台的订单");
     } 
   }
   
   public void getStream() throws Exception {
     String auditstatus = "2";
     String plan = "(payNumber is null or payNumber='')  and getStream";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
       } 
       
       System.out.println(ids);
       this.orderBondedService.getBfbUtil(ids.toString());
       System.out.println("获取流水执行成功！！");
     } else {
       System.out.println("没有需要获取流水的订单");
     } 
   }
   
   public void grabNumbers() throws Exception {
     System.out.println("5");
     String auditstatus = "1";
     String plan = "mailNo is null and grabNumbers";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
       } 
       
       System.out.println(ids);
       this.orderBondedService.updateExpressParam(ids.toString());
       System.out.println("抓取单号执行成功！！");
     } else {
       System.out.println("没有需要抓取单号的订单");
     } 
   }
   
   public void orderSubmitted() throws Exception {
     System.out.println("6");
     String auditstatus = "2,7";
     String plan = " (payNumber is not null and payNumber<>'')  and  isPushDd=0 and orderSubmitted";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       int i = 0;
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
         
         i++;
         if (i == 1) {
           System.out.println(ids);
           this.orderBondedService.pushDd(ids.toString());
           i = 0;
           ids = new StringBuilder();
           first = true;
         } 
       } 
 
       
       System.out.println("订单报送执行成功！！");
     } else {
       System.out.println("没有需要订单报送的订单");
     } 
   }
   
   public void logisticsSubmitted() throws Exception {
     System.out.println("7");
     String auditstatus = "2";
     String plan = "mailNo is not null and isCustoms=0 and logisticsSubmitted";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
       } 
       
       System.out.println(ids);
       this.orderBondedService.updateIsCustoms(ids.toString());
       System.out.println("物流报送执行成功！！");
     } else {
       System.out.println("没有需要物流报送的订单");
     } 
   }
   
   public void listSubmitted() throws Exception {
     System.out.println("8");
     String auditstatus = "2";
     String plan = "  isPushDd=1  and isPushQd=0  and listSubmitted";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
       } 
       
       System.out.println(ids);
       this.orderBondedService.pushQd(ids.toString());
       System.out.println("清单报送执行成功！！");
     } else {
       System.out.println("没有需要清单报送的订单");
     } 
   }
   
   public void downWarehouse() throws Exception {
     System.out.println("9");
     String auditstatus = "9";
     String plan = "isPushToWms=0 and downWarehouse";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
       } 
       
       System.out.println(ids);
       this.orderBondedService.updateIsPushToWmsNew(ids.toString());
       System.out.println("下发仓库执行成功！！");
     } else {
       System.out.println("没有需要下发仓库的订单");
     } 
   }
   
   public void automaticCompletion() throws Exception {
     System.out.println("10");
     String auditstatus = "2";
     String plan = "downPlatform";
     
     List<AutoCheckListExtend> lists = this.autoCheckListService.findOrderByAuditstatus(auditstatus, plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (AutoCheckListExtend autoCheckListExtend : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(autoCheckListExtend.getId());
       } 
       
       System.out.println(ids);
       
       System.out.println("订单预审执行成功！！");
     } else {
       System.out.println("没有需要审核的订单");
     } 
   }
   
   public void zysh() throws Exception {
     System.out.println("11");
     String plan = " auditstatus=0 ";
     
     List<OrderMail> lists = this.autoCheckListService.findOrderById(plan);
     
     if (lists.size() > 0) {
       String paramJson = "{'paramList':[";
       for (OrderMail autoCheckListExtend : lists) {
         String mailNo = "";
         if (autoCheckListExtend.getMailNo() != null) {
           mailNo = autoCheckListExtend.getMailNo();
         }
         String param = "{'id':" + autoCheckListExtend.getId() + ",'mailNo':'" + mailNo + 
           "','txLogisticID':'" + autoCheckListExtend.getTxLogisticID() + "','itemCount':'" + 
           autoCheckListExtend.getItemCount() + "','merchantNum':'" + autoCheckListExtend.getMerchantNum() + 
           "','worth':'" + autoCheckListExtend.getWorth() + "','buyerName':'" + 
           autoCheckListExtend.getBuyerName() + "','buyerIdNumber':'" + 
           autoCheckListExtend.getBuyerIdNumber() + "'},";
         paramJson = String.valueOf(paramJson) + param;
       } 
       paramJson = paramJson.substring(0, paramJson.length() - 1);
       paramJson = String.valueOf(paramJson) + "]}";
       System.out.println(paramJson);
       this.OrderMailService.updateAuditstatus(paramJson);
       System.out.println("直邮预审执行成功！！");
     } else {
       System.out.println("没有需要审核的直邮订单");
     } 
   }
 
 
 
   
   public void zyxfpt() throws Exception {
     System.out.println("11");
     String plan = " ispost=0 and auditstatus=2 and (payNumber is null or payNumber ='')";
     
     List<OrderMail> lists = this.autoCheckListService.findOrderById(plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (OrderMail mail : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(mail.getId());
       } 
       System.out.println(ids);
       this.OrderMailService.pushBfbUtil(ids.toString(), "330156K024");
       System.out.println("订单下发平台执行成功！！");
     } else {
       System.out.println("没有需要下发平台的订单");
     } 
   }
 
 
 
   
   public void zylsh() throws Exception {
     System.out.println("11");
     String plan = " ispost=1 and  auditstatus=2 and (payNumber is null or payNumber ='')";
     
     List<OrderMail> lists = this.autoCheckListService.findOrderById(plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       for (OrderMail mail : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(mail.getId());
       } 
       System.out.println(ids);
       this.OrderMailService.getBfbUtil(ids.toString());
       System.out.println("订单流水号成功！！");
     } else {
       System.out.println("没有需要流水号的订单");
     } 
   }
   public void zyddbg() throws Exception {
     System.out.println("11");
     String plan = "(payNumber is not null and payNumber<>'')  and  isPushDd=0 and auditstatus=2   ";
     
     List<OrderMail> lists = this.autoCheckListService.findOrderById(plan);
     
     boolean first = true;
     if (lists.size() > 0) {
       StringBuilder ids = new StringBuilder();
       int i = 0;
       for (OrderMail mail : lists) {
         if (first) {
           first = false;
         } else {
           ids.append(",");
         } 
         ids.append(mail.getId());
         i++;
         if (i == 1) {
           this.OrderMailService.pushDd(ids.toString());
           i = 0;
           ids = new StringBuilder();
           first = true;
         } 
       } 
       System.out.println(ids);
       this.OrderMailService.pushDd(ids.toString());
       System.out.println("订单预审执行成功！！");
     } else {
       System.out.println("没有需要审核的订单");
     } 
   }
 
 
   
   @Scheduled(cron = "0 0/10 * * * ? ")
   public void index() {
     int orderToday = this.orderMailService.findOrderToday().intValue();
     
     int orderYear = this.orderMailService.findOrderYear();
     
     int exceptionOrderYear = this.orderMailService.findExceptionOrderYear();
 
     
     String totalSumYear = (new BigDecimal(this.orderMailService.findTotalSumYear())).toString();
     
     int lastYearOrderNumber = this.orderMailService.findLastYearOrderNumber();
     
     int lastMonthOrderNumber = this.orderMailService.findLastMonthOrderNumber();
     
     String lastMonthTotalSum = (new BigDecimal(this.orderMailService.findLastMonthTotalSum())).toString();
     WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
     ServletContext model = webApplicationContext.getServletContext();
     model.setAttribute("orderToday", Integer.valueOf(orderToday));
     model.setAttribute("orderYear", Integer.valueOf(orderYear));
     model.setAttribute("exceptionOrderYear", Integer.valueOf(exceptionOrderYear));
     model.setAttribute("totalSumYear", totalSumYear);
     model.setAttribute("lastYearOrderNumber", Integer.valueOf(lastYearOrderNumber));
     model.setAttribute("lastMonthOrderNumber", Integer.valueOf(lastMonthOrderNumber));
     model.setAttribute("lastMonthTotalSum", lastMonthTotalSum);
     List<EchattsBonded> bondeds = this.orderMailService.findbonded();
     List<EchattsBonded> mails = this.orderMailService.findMail();
     model.setAttribute("echattsResult", new EchattsResult(bondeds, mails));
   }
   
   public void zshzjx() throws Exception {
     String path = "E:\\hz";
     File file = new File(path);
     File[] tempList = file.listFiles();
     for (int i = 0; i < tempList.length; i++) {
       if (tempList[i].isFile()) {
         
         String contentData = XmlToString(tempList[i].toString());
         
         String copNo = "";
         int orderType = 0;
         int id = 0;
         Pattern p = Pattern.compile("<copNo>(.*)</copNo>");
         Matcher m = p.matcher(contentData);
         while (m.find()) {
           copNo = m.group(1);
           orderType = Integer.parseInt(copNo.substring(4, 5));
           id = Integer.parseInt(copNo.substring(5, copNo.length()));
 
           
           String invtNo = "";
           Pattern p_invtNo = Pattern.compile("<invtNo>(.*)</invtNo>");
           Matcher m_invtNo = p_invtNo.matcher(contentData);
           while (m_invtNo.find()) {
             invtNo = m_invtNo.group(1);
             if (invtNo.length() > 5) {
               this.orderBondedDao.upgjz(invtNo, (new StringBuilder(String.valueOf(id))).toString());
             }
           } 
         } 
         
         if (contentData.indexOf("3301560013") == -1)
         {
           
           if (contentData.indexOf("Code:1800") != -1) {
             String ddh = "1";
             Pattern p_1 = Pattern.compile("<orderNo>(.*)</orderNo>");
             Matcher m_1 = p_1.matcher(contentData);
             while (m_1.find()) {
               ddh = m_1.group(1);
 
               
               this.orderBondDao.updateddzt_1(ddh);
               
               this.orderMailDao.updateddhzzt_1(ddh);
             
             }
           
           }
           else if (contentData.indexOf("新增申报成功[BS") != -1) {
             String ddh = "1";
             Pattern p_1 = Pattern.compile("<orderNo>(.*)</orderNo>");
             Matcher m_1 = p_1.matcher(contentData);
             while (m_1.find()) {
               ddh = m_1.group(1);
               this.orderMailDao.updateddhzzt_1(ddh);
               this.orderBondDao.updateddzt_1(ddh);
             }
           
           }
           else if (contentData.indexOf("新增申报成功[ZY") != -1) {
             String ddh = "1";
             Pattern p_1 = Pattern.compile("<orderNo>(.*)</orderNo>");
             Matcher m_1 = p_1.matcher(contentData);
             while (m_1.find()) {
               ddh = m_1.group(1);
               this.orderMailDao.updateddhzzt_1(ddh);
             }
           
           }
           else if (StringUtil.isNotEmpty(copNo).booleanValue()) {
             Pattern rP = Pattern.compile("<returnInfo>(.*)</returnInfo>");
             Matcher rM = rP.matcher(contentData);
             String returninfo = "";
             while (rM.find()) {
               returninfo = rM.group(1);
             }
             
             if (orderType == 4) {
               OrderMail orderMail = new OrderMail();
               orderMail.setId(id);
               
               if (contentData.indexOf("Code:2600") != -1) {
                 orderMail.setReturncode(Integer.valueOf(9));
                 orderMail.setReturnInfo(returninfo);
                 orderMail.setStatus(1);
                 orderMail.setAuditstatus(9);
               } else if (contentData.indexOf("Code:1700") != -1) {
                 orderMail.setReturncode(Integer.valueOf(2));
                 orderMail.setReturnInfo(returninfo);
                 orderMail.setStatus(0);
                 orderMail.setAuditstatus(9);
               } else if (contentData.indexOf("Code:2400") != -1) {
                 orderMail.setReturncode(Integer.valueOf(3));
                 orderMail.setReturnInfo(returninfo);
                 orderMail.setStatus(0);
                 orderMail.setAuditstatus(9);
               } else if (contentData.indexOf("Code:1313") != -1) {
                 orderMail.setReturncode(Integer.valueOf(1));
                 orderMail.setReturnInfo(returninfo);
                 orderMail.setStatus(0);
                 orderMail.setAuditstatus(2);
               } else {
                 
                 orderMail.setReturncode(Integer.valueOf(1));
                 orderMail.setReturnInfo(returninfo);
                 orderMail.setStatus(0);
                 orderMail.setAuditstatus(2);
               } 
               this.orderMailDao.updateAuditstatus2(orderMail);
             } else if (orderType == 3) {
               OrderBonded orderBonded = new OrderBonded();
               orderBonded.setId(id);
               
               if (contentData.indexOf("Code:2600") != -1) {
                 
                 orderBonded.setReturncode(Integer.valueOf(9));
                 orderBonded.setReturnInfo(returninfo);
                 orderBonded.setAuditstatus(9);
               
               }
               else if (contentData.indexOf("800-实货放行") != -1) {
                 orderBonded.setReturncode(Integer.valueOf(9));
                 orderBonded.setReturnInfo(returninfo);
                 orderBonded.setAuditstatus(9);
               } else if (contentData.indexOf("Code:2400") != -1) {
                 orderBonded.setReturncode(Integer.valueOf(3));
                 orderBonded.setReturnInfo(returninfo);
                 orderBonded.setAuditstatus(7);
               } else if (contentData.indexOf("Code:1313") != -1) {
                 orderBonded.setReturncode(Integer.valueOf(1));
                 orderBonded.setReturnInfo(returninfo);
                 orderBonded.setAuditstatus(7);
               } else {
                 
                 orderBonded.setReturncode(Integer.valueOf(1));
                 orderBonded.setReturnInfo(returninfo);
                 orderBonded.setAuditstatus(7);
               } 
               if (contentData.indexOf("清单新增申报成功") != -1) {
                 if (contentData.indexOf("不存在") != -1) {
                   this.orderBondedDao.updateAuditstatus2(orderBonded);
                 }
               } else {
                 this.orderBondedDao.updateAuditstatus2(orderBonded);
               } 
             } 
           } 
         }
         
         if (contentData.indexOf("TaxHeadRd") != -1) {
           OrderBonded orderBonded = new OrderBonded();
           String valueAddedTax = "";
           Pattern p_1 = Pattern.compile("<valueAddedTax>(.*)</valueAddedTax>");
           Matcher m_1 = p_1.matcher(contentData);
           while (m_1.find()) {
             valueAddedTax = m_1.group(1);
           }
           System.out.println("valueAddedTax=" + valueAddedTax);
           String consumptionTax = "";
           Pattern p_2 = Pattern.compile("<consumptionTax>(.*)</consumptionTax>");
           Matcher m_2 = p_2.matcher(contentData);
           while (m_2.find()) {
             consumptionTax = m_2.group(1);
           }
           System.out.println("consumptionTax=" + consumptionTax);
           String orderNo = "";
           Pattern p_3 = Pattern.compile("<orderNo>(.*)</orderNo>");
           Matcher m_3 = p_3.matcher(contentData);
           while (m_3.find()) {
             orderNo = m_3.group(1);
           }
           System.out.println("orderNo=" + orderNo);
           orderBonded.setTxLogisticID(orderNo);
           orderBonded.setConsumptionTax(consumptionTax);
           orderBonded.setValueAddedTax(valueAddedTax);
           this.orderBondedDao.updateInvtno(orderBonded);
         } 
         File dfile = new File(tempList[i].toString());
         dfile.delete();
         ReceiveHghzData receiveHghzData = new ReceiveHghzData();
         
         receiveHghzData.setContentData(contentData);
         if (contentData.indexOf("TaxHeadRd") != -1 || contentData.indexOf("Code:1800") != -1 || contentData.indexOf("新增申报成功") != -1 || contentData.indexOf("Code:2600") != -1 || contentData.indexOf("Code:1700") != -1) {
           receiveHghzData.setIsHandle(1);
         } else {
           receiveHghzData.setIsHandle(0);
         } 
         this.receiveHghzDataDao.insert(receiveHghzData);
       } 
     } 
   }
   
   public static String XmlToString(String url) {
     Document document = null;
     document = load(url);
     
     Format format = Format.getPrettyFormat();
     format.setEncoding("UTF-8");
     
     StringWriter out = null;
     String sReturn = "";
     XMLOutputter outputter = new XMLOutputter();
     out = new StringWriter();
     try {
       outputter.output(document, out);
     } catch (IOException e) {
       e.printStackTrace();
     } 
     sReturn = out.toString();
     return sReturn;
   }
   public static Document load(String url) {
     Document document = null;
     
     try {
       SAXBuilder reader = new SAXBuilder();
       document = reader.build(new File(url));
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return document;
   }
 }


