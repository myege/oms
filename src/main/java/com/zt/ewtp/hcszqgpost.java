 package com.zt.ewtp;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import com.zt.kjybd.PushtoWQ;
 import java.io.UnsupportedEncodingException;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.SignatureException;
 import java.security.spec.InvalidKeySpecException;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
 import org.apache.http.HttpEntity;
 import org.apache.http.StatusLine;
 import org.apache.http.client.methods.CloseableHttpResponse;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.client.methods.HttpUriRequest;
 import org.apache.http.entity.StringEntity;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClients;
 import org.apache.http.util.EntityUtils;
 import org.springframework.http.HttpStatus;
 public class hcszqgpost
 {
   private static String personPriKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ4JW94GyjpfupFr2p3rwR4fWyw5u5DEUaKGyzpLiTlBdMXfDQnpB90vg2kui0z+wMxOPNNlUatbC65Q6h/pUnhorno/7mN0vn2XHS/qYorLD4QMMhV8PqVX6i5LUtETx0csJeBPlrUYG20NvMfUiirfosQc+/UP3XzkwdjFnV9hAgMBAAECgYBd7Af6EyT6vkveAzBhLGyQX2hLB2Cur84ofl2nnp7GNV2V/3L7UdAHpyY9S5kkQD8K7PB/XjbEVQ7DIXmTT5Jf4jGRq/pbnralEna+B9FQwZF5oXWcrkYJUnyD+sqrRGsPkokp5BmJJEDU4dWLWubEJ6qe0m5khhBy7h4rD5bPcQJBAPsabybeZqRpBAMqiOOEYfs/slNzcq3zHQk+iiaFLQznpdchUs1p2q6nR4gymBIvcodsg1Ss8zOaV0zirlztFy0CQQChHlBnRVIxU1BcHTAM/cU0Cz3eh45LD1KXfz1TNXZx0oSKLfON8bDT7nu5ikiuEEwMyqysRk/XqwxPmQOa9smFAkEAyMH4rh3P+JDRmWpvaV/VKphhjaDWxkaCJ3rt3yBIxXGLQPWuivaf1VV+tmCk/p0atzVFxvRDuTUjqR/vAfK4vQJBAIpbE8R63MgKuVlAn0Cosy2r8ackVObT86Kw2yKXKG050CMwrpNX1vdHNevwedUvnX7mlD0j6/BRrDK2PUhXYFkCQDjtt33u8wCqEE0aouF8BrttH/K9M22iFtKCnk9IkJGSl/GBp3NboWvGZ2/S6ob85uVmwHmBsXqqYcHs3M96o9c=";
   
   public static void main(String[] args) throws Exception, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidKeyException {
     pushstatus();
   }
 
   
   public static void pushstatus() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("orderNo", "43454050646209");
     
     jsonObject.put("logisticsNo", "5530296924591");
     jsonObject.put("status", "9");
     
     jsonObject.put("msg", "已发货");
     
     System.out.println("jsonObject=" + jsonObject.toString());
     String url = "http://gwfat.kldidi.com/cds/ywTaobao/hc/statusCallBack";
     PushtoWQ.postjson(url, jsonObject.toJSONString());
   }
 
 
 
   
   public static void pushTrack() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("logisticsCode", "3120980110");
     jsonObject.put("logisticsName", "申通快递有限公司");
     jsonObject.put("billNo", "");
     jsonObject.put("logisticsNo", "5530296924750");
     JSONArray pushTrackDataReqList = new JSONArray();
     
     JSONObject jo = new JSONObject();
     jo.put("logisticsLinkCode", "20201111010101");
     jo.put("logisticsLinkName", "海外中转仓揽收成功");
     jo.put("logisticsTrajectory", "海外中转仓揽收成功");
     jo.put("generateTime", "20201111010101");
     jo.put("note", "note");
     pushTrackDataReqList.add(jo);
 
     
     jsonObject.put("pushTrackDataReqList", pushTrackDataReqList);
     System.out.println("jsonObject=" + jsonObject.toString());
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/lstm/pushTrack";
     doPost(url, param);
   }
 
 
   
   public static void generalWaybillDeclare() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("commitTime", "20200709163300");
     jsonObject.put("merchantName", "海仓科技");
     jsonObject.put("merchantId", "9a6c6c6cd2464eb19765785781a8a266");
     
     jsonObject.put("appStatus", Integer.valueOf(2));
     jsonObject.put("customsCode", "2912");
     jsonObject.put("entryType", "9610");
     jsonObject.put("copNo", "");
     jsonObject.put("loctNo", "2912");
     jsonObject.put("trafMode", "5");
     jsonObject.put("trafName", "航运");
     jsonObject.put("voyageNo", "5");
     jsonObject.put("billNo", "9999-111");
     jsonObject.put("grossWeight", "0");
     jsonObject.put("logisticsCode", "3300510053");
     jsonObject.put("logisticsName", "义乌海仓供应链管理有限公司");
     jsonObject.put("msgCount", "1");
     jsonObject.put("msgSeqNo", "1");
     jsonObject.put("agentCode", "3300510053");
     jsonObject.put("agentName", "义乌海仓供应链管理有限公司");
     jsonObject.put("preNo", "123456789123456789");
     jsonObject.put("copNo", "12345678912345678900");
 
     
     JSONObject orderListDto = new JSONObject();
     JSONArray status = new JSONArray();
     orderListDto.put("gnum", "1");
     orderListDto.put("totalPackageNo", "3005109000");
     orderListDto.put("logisticsNo", "12345679");
     status.add(orderListDto);
     jsonObject.put("wayBillItemDtoList", status);
 
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/generalWaybillDeclare";
     doPost(url, param);
   }
 
   
   public static void customsDeclare() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("commitTime", "20200804020101");
     jsonObject.put("merchantName", "海仓科技");
     jsonObject.put("merchantId", "9a6c6c6cd2464eb19765785781a8a266");
     
     jsonObject.put("appStatus", Integer.valueOf(2));
     jsonObject.put("orderNo", "20200804001");
     
     jsonObject.put("ebpCode", "3300510053");
     jsonObject.put("ebpName", "义乌海仓供应链管理有限公司");
     jsonObject.put("ebcCode", "3300510053");
     jsonObject.put("ebcName", "义乌海仓供应链管理有限公司");
     jsonObject.put("logisticsNo", "3300510053111");
     jsonObject.put("logisticsCode", "3300510053");
     jsonObject.put("logisticsName", "义乌海仓供应链管理有限公司");
     jsonObject.put("copNo", "3300510053111");
     jsonObject.put("assureCode", "3300510053");
     jsonObject.put("emsNo", "");
     
     jsonObject.put("ieFlag", "I");
     jsonObject.put("declTime", "20200803");
     jsonObject.put("customsCode", "2912");
     jsonObject.put("portCode", "2912");
     jsonObject.put("buyerIdType", "1");
     jsonObject.put("buyerIdNumber", "33012419888");
     jsonObject.put("buyerName", "扎啊三");
     jsonObject.put("buyerTelephone", "15988");
     jsonObject.put("consigneeAddress", "杭州下沙");
     jsonObject.put("agentCode", "3300510053");
     jsonObject.put("agentName", "义乌海仓供应链管理有限公司");
     jsonObject.put("areaCode", "");
     jsonObject.put("areaName", "");
     jsonObject.put("tradeMode", "9610");
     jsonObject.put("trafMode", "5");
     jsonObject.put("trafNo", "CA911");
     jsonObject.put("voyageNo", "CA911");
     jsonObject.put("billNo", "5000-2123");
     jsonObject.put("country", "116");
     jsonObject.put("freight", "0");
     jsonObject.put("insuredFee", "0");
     jsonObject.put("currency", "142");
     jsonObject.put("packNo", "1");
     jsonObject.put("grossWeight", "1");
     jsonObject.put("netWeight", "0.6");
     jsonObject.put("note", "330124");
     jsonObject.put("appType", "2");
     
     JSONObject orderListDto = new JSONObject();
     JSONArray status = new JSONArray();
     orderListDto.put("gnum", "1");
     orderListDto.put("itemRecordNo", "");
     orderListDto.put("itemNo", "sku001");
     orderListDto.put("itemName", "测试商品2");
     orderListDto.put("gcode", "3005109000");
     orderListDto.put("gname", "测试商品2");
     orderListDto.put("gmodel", "规格");
     orderListDto.put("barCode", "1");
     orderListDto.put("country", "116");
     orderListDto.put("currency", "142");
     orderListDto.put("qty", "1");
     orderListDto.put("unit", "035");
     orderListDto.put("qty1", "1");
     orderListDto.put("unit1", "035");
     orderListDto.put("qty2", "1");
     orderListDto.put("unit2", "035");
     orderListDto.put("price", "10");
     orderListDto.put("totalPrice", "10");
     orderListDto.put("note", "10贴/袋");
     
     status.add(orderListDto);
     jsonObject.put("customsDeclareBodyDTO", status);
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/customsDeclare";
     doPost(url, param);
   }
 
 
   
   public static void expressCustomsDeclare() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("commitTime", "202007091633");
     jsonObject.put("merchantName", "海仓科技");
     jsonObject.put("merchantId", "9a6c6c6cd2464eb19765785781a8a266");
     
     jsonObject.put("appStatus", Integer.valueOf(2));
     jsonObject.put("iEFlag", "I");
     
     jsonObject.put("iEPort", "2912");
     jsonObject.put("ieDate", "202007140000");
     jsonObject.put("declareDate", "202007140000");
     jsonObject.put("destinationPort", "116");
     jsonObject.put("trafName", "航运");
     jsonObject.put("voyageNo", "HB20200714");
     jsonObject.put("trafMode", "5");
     jsonObject.put("ownerName", "义乌海仓供应链管理有限公司");
     jsonObject.put("agentType", "0");
     jsonObject.put("agentCode", "3300510053");
     
     jsonObject.put("agentName", "义乌海仓供应链管理有限公司");
     jsonObject.put("billNo", "999-999");
     jsonObject.put("assBillNo", "123456");
     jsonObject.put("tradeCountry", "116");
     jsonObject.put("packNo", "1");
     jsonObject.put("grossWt", "1");
     jsonObject.put("netWt", "0.9");
     jsonObject.put("woodWrap", "否");
     jsonObject.put("goodsUsed", "否");
     jsonObject.put("lowTempTrans", "否");
     jsonObject.put("declPort", "2912");
     jsonObject.put("coOwner", "1");
     jsonObject.put("inputNo", "张玉松");
     jsonObject.put("inputCompanyCo", "3300510053");
     jsonObject.put("inputCompanyName", "义乌海仓供应链管理有限公司");
     jsonObject.put("sendName", "jiesheng");
     jsonObject.put("sendNameEn", "jiesheng");
     jsonObject.put("sendCountry", "116");
     jsonObject.put("sendCity", "大阪");
     jsonObject.put("sendAddress", "大阪");
     jsonObject.put("sendTelNo", "01011212");
     jsonObject.put("totalValue", "20");
     jsonObject.put("currCode", "142");
     jsonObject.put("entryType", "D");
     jsonObject.put("mainGName", "LANNA兰纳足贴10贴/袋");
     jsonObject.put("mainGNameEn", "LANNA");
     jsonObject.put("receiveName", "章鱼");
     jsonObject.put("receiveCountry", "142");
     jsonObject.put("receiveProvince", "浙江省");
     jsonObject.put("receiveCity", "杭州市");
     jsonObject.put("receiveDistrict", "杭州市");
     jsonObject.put("receiveAddress", "钱塘新区");
     jsonObject.put("receiveTelNo", "15988106969");
     jsonObject.put("dDate", "202007140000");
     jsonObject.put("stopCityEn", "daban");
     jsonObject.put("iEDate", "202007140000");
     jsonObject.put("commitTime", "202007140000");
     jsonObject.put("opType", "1");
     jsonObject.put("wrapType", "2");
     jsonObject.put("sendIdType", "1");
     jsonObject.put("sendId", "330124");
     
     JSONObject orderListDto = new JSONObject();
     JSONArray status = new JSONArray();
     orderListDto.put("codeId", "XGSY8859312988801");
     orderListDto.put("codeTs", "3005109000");
     orderListDto.put("cargoName", "LANNA兰纳足贴10贴/袋");
     orderListDto.put("cargoNameEn", "LANNA");
     orderListDto.put("cargoModel", "10贴/袋");
     orderListDto.put("originCountry", "116");
     orderListDto.put("originCity", "1");
     orderListDto.put("tradeCurr", "142");
     orderListDto.put("tradeTotal", "20");
     orderListDto.put("declPrice", "20");
     orderListDto.put("declTotal", "20");
     orderListDto.put("qty", "1");
     orderListDto.put("unit", "035");
     orderListDto.put("qty1", "1");
     orderListDto.put("unit1", "035");
     orderListDto.put("qty2", "035");
     orderListDto.put("unit2", "1");
     orderListDto.put("grossWT", "1");
     
     orderListDto.put("opType", "1");
     orderListDto.put("gQty", "1");
     orderListDto.put("gNo", "1");
     orderListDto.put("gUnit", "035");
     orderListDto.put("gGrossWt", "1");
     orderListDto.put("gNameEn", "LANNA");
     orderListDto.put("gName", "LANNA兰纳足贴10贴/袋");
     orderListDto.put("gMode", "10贴/袋");
     
     status.add(orderListDto);
     jsonObject.put("expressCustomsDeclareBodyDTO", status);
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/expressCustomsDeclare";
     doPost(url, param);
   }
 
 
   
   public static void orderDeclare() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("commitTime", "20200709163349");
     jsonObject.put("merchantName", "海仓科技");
     jsonObject.put("merchantId", "9a6c6c6cd2464eb19765785781a8a266");
 
     
     jsonObject.put("appStatus", Integer.valueOf(2));
     jsonObject.put("orderNo", "202007140001");
     jsonObject.put("orderType", "I");
     jsonObject.put("entryType", "9610");
     jsonObject.put("ebpCode", "3300510053");
     jsonObject.put("ebpName", "义乌海仓供应链管理有限公司");
     jsonObject.put("proxyCode", "3300510053");
     jsonObject.put("proxyName", "义乌海仓供应链管理有限公司");
     jsonObject.put("abroadCo", "2020791207002");
     jsonObject.put("abroadCoName", "测试商家2");
     jsonObject.put("goodValue", "20");
     jsonObject.put("freight", "0");
     jsonObject.put("discount", "0");
     jsonObject.put("taxTotal", "0");
     jsonObject.put("acturalPaid", "20");
     jsonObject.put("buyerRegNo", "142");
     jsonObject.put("buyerName", "张玉松");
     jsonObject.put("buyerTelephone", "13605806456");
     jsonObject.put("buyerIdType", "1");
     jsonObject.put("buyerIdNumber", "331123131313");
     jsonObject.put("payCode", "312226T001");
     jsonObject.put("payName", "支付宝（中国）网络技术有限公司");
     jsonObject.put("payTransactionId", "123456789");
     jsonObject.put("consignee", "张玉松");
     jsonObject.put("consigneeTelephone", "13605806456");
     jsonObject.put("consigneeIdType", "1");
     jsonObject.put("consigneeIdNumber", "12346");
     jsonObject.put("consigneePrvince", "浙江省");
     jsonObject.put("consigneeCity", "杭州市");
     jsonObject.put("consigneeCounty", "钱塘新区");
     jsonObject.put("consigneeAddress", "旮旯里");
     jsonObject.put("currency", "142");
     
     JSONObject orderListDto = new JSONObject();
     JSONArray status = new JSONArray();
     orderListDto.put("itemNo", "XGSY8859312988801");
     orderListDto.put("itemName", "LANNA兰纳足贴10贴/袋");
     orderListDto.put("gModel", "10贴/袋");
     orderListDto.put("unit", "035");
     orderListDto.put("qty", "1");
     orderListDto.put("price", "20");
     orderListDto.put("totalPrice", "20");
     orderListDto.put("currency", "142");
     
     orderListDto.put("gnum", "1");
     orderListDto.put("gmodel", "规格型");
     orderListDto.put("hscode", "3005109000");
     orderListDto.put("codeTs", "30051090");
     orderListDto.put("country", "116");
     
     status.add(orderListDto);
     jsonObject.put("orderListDto", status);
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/orderDeclare";
     doPost(url, param);
   }
 
 
   
   public static void commodityRecord() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("commitTime", "20200709163349");
     jsonObject.put("merchantName", "海仓科技");
     jsonObject.put("merchantId", "9a6c6c6cd2464eb19765785781a8a266");
     jsonObject.put("coCode", "7627754");
 
     
     jsonObject.put("appStatus", Integer.valueOf(2));
     
     jsonObject.put("skuCode", "XGSY8859312988801");
     
     jsonObject.put("codeTs", "3005109000");
     jsonObject.put("barCode", "3005109000");
     jsonObject.put("goodsName", "LANNA兰纳足贴10贴/袋");
     jsonObject.put("goodsNameEn", "LANNA");
     jsonObject.put("goodsDesc", "LANNA兰纳足贴10贴/袋");
     jsonObject.put("goodsComposition", "成分");
     jsonObject.put("goodsFrom", "日本");
     jsonObject.put("goodsSpec", "10贴/袋");
     jsonObject.put("goodsModel", "no123456789");
     jsonObject.put("hsCode", "3005109000");
     jsonObject.put("firstUnit", "035");
     jsonObject.put("secondUnit", "133");
     jsonObject.put("highPrice", "20");
     jsonObject.put("lowPrice", "20");
     jsonObject.put("price1", "20");
     jsonObject.put("price2", "20");
     jsonObject.put("price3", "20");
     jsonObject.put("price4", "20");
     jsonObject.put("price5", "20");
     jsonObject.put("price6", "20");
     jsonObject.put("price7", "20");
     jsonObject.put("price8", "20");
     jsonObject.put("supplier", "no123456789");
     jsonObject.put("standardCountry", "116");
     jsonObject.put("brand", "兰纳");
     jsonObject.put("brandEn", "LANNA");
     jsonObject.put("busRegion", "2912");
 
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/commodityRecord";
     doPost(url, param);
   }
   
   public static void overseasMerchantsRecord() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("commitTime", "20200709163349");
     jsonObject.put("merchantName", "海仓科技");
     jsonObject.put("merchantId", "9a6c6c6cd2464eb19765785781a8a266");
 
     
     jsonObject.put("appStatus", Integer.valueOf(2));
     
     jsonObject.put("abroadCoName", "alibbdisk");
     
     jsonObject.put("abroadLowMan", "miszhang");
     jsonObject.put("country", "116");
     jsonObject.put("abroadCoChannel", "1");
     jsonObject.put("abroadContacts", "miszhang");
     jsonObject.put("abroadTel", "0101");
     jsonObject.put("abroadEmail", "11@163.com");
     jsonObject.put("abroadRegAddr", "日本大阪");
     jsonObject.put("abroadOfficeAddr", "日本大阪");
     jsonObject.put("abroadNo", "no123456789");
     jsonObject.put("abroadFile", "no123456789");
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/overseasMerchantsRecord";
     doPost(url, param);
   }
 
   
   public static void domesticAgentEnterpriseRecord() throws Exception {
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("appid", "0e8ee76eb84f42598f45fe4a317ca231");
     
     jsonObject.put("version", "1.0.0");
     jsonObject.put("commitTime", "20200709163349");
     jsonObject.put("lawName", "陈琼伟");
     jsonObject.put("appStatus", Integer.valueOf(2));
     jsonObject.put("fullName", "义乌海仓供应链管理有限公司");
     jsonObject.put("socialCreditCode", "91330782MA2DFE301F");
     jsonObject.put("businessCode", "4");
     jsonObject.put("tradeCo", "3300510053");
     jsonObject.put("lawNameTel", "111111");
     jsonObject.put("regCo", "义乌海仓");
     
     jsonObject.put("idNumber", "33145487156425");
     jsonObject.put("busiType", "清关公司");
     jsonObject.put("customsCode", "2921");
     jsonObject.put("copEndDate", "20210716000000");
     jsonObject.put("dxpId", "123123");
     jsonObject.put("dxpMode", "dxp");
     jsonObject.put("addrCo", "浙江省义乌市北苑街道机场路596号一楼");
     jsonObject.put("valiDate", "20200709000000");
     jsonObject.put("merchantName", "海仓科技");
     jsonObject.put("merchantId", "9a6c6c6cd2464eb19765785781a8a266");
     
     jsonObject.put("rgDate", "20200709000000");
 
 
     
     System.out.println("====>" + jsonObject.toJSONString());
     
     ApolloSdk apolloSdk = new ApolloSdk();
     String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), jsonObject.toJSONString());
     System.out.println("param=" + param);
     String url = "http://gwfat.kldidi.com/aoa/api/aoa/publicServicePlatformFacade/domesticAgentEnterpriseRecord";
     doPost(url, param);
   }
   
   private static void doPost(String url, String param) throws Exception {
     CloseableHttpClient httpclient = HttpClients.createDefault();
     HttpPost httpPost = new HttpPost(url);
     StringEntity entity = new StringEntity(param, "utf-8");
     entity.setContentEncoding("UTF-8");
     entity.setContentType("application/json");
     httpPost.setEntity((HttpEntity)entity);
     String jsonString = null;
     CloseableHttpResponse response = null;
 
     
     response = httpclient.execute((HttpUriRequest)httpPost);
     StatusLine status = response.getStatusLine();
     int state = status.getStatusCode();
     if (state == HttpStatus.OK.value()) {
       HttpEntity responseEntity = response.getEntity();
       jsonString = EntityUtils.toString(responseEntity);
       System.out.println("返回信息:" + jsonString);
     } else {
       
       System.out.println("请求返回:" + state + "(" + url + ")");
     } 
   }
   
   public static String ZYPost(String url, String param) throws Exception {
     CloseableHttpClient httpclient = HttpClients.createDefault();
     HttpPost httpPost = new HttpPost(url);
     StringEntity entity = new StringEntity(param, "utf-8");
     entity.setContentEncoding("UTF-8");
     entity.setContentType("application/json");
     httpPost.setEntity((HttpEntity)entity);
     String jsonString = null;
     CloseableHttpResponse response = null;
 
     
     response = httpclient.execute((HttpUriRequest)httpPost);
     StatusLine status = response.getStatusLine();
     int state = status.getStatusCode();
     
     HttpEntity responseEntity = response.getEntity();
     jsonString = EntityUtils.toString(responseEntity);
     System.out.println("返回信息:" + jsonString);
     
     return jsonString;
   }
 }


