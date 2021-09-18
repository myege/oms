 package com.zt.sumpay;
 
 import com.alibaba.fastjson.JSONObject;
 import java.security.MessageDigest;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.TreeMap;
 public class CollectionApply
 {
   public static final int TRANSFER_NUMBER = 10;
   public static final String CFM_PAY_URL = "https://webgate.sumpay.cn/webgate/collectionApply.htm";
   public static final int TRY_NUMBER = 1;
   public static final String CFM_PARTNER_ID = "10000000560";
   public static final String CFM_SECRET_KEY = "30820122300d06092a864886f70d01010105000382010f003082010a02820101009d1d8882b6e0164250a529952dd19e5bfd8a9bbc6f95908b62e43e8e8663c7bf9f8dc61067dd248d2c0a5abaa629c6a775fb46b1a4e1c8c52a1d373261e3f9da895553404a6fbf119e85b60260010e0cf20e2014c27cbb033797273df233b473b08d157b8ad9ea662a720af37a778fef752f2a18acdcc03538c525f25f070ced8320cfc1dfff3097a78987b67d6cf31d7c30d7d7c31e3f53f9c405e52390a15c890558b9cbc3d418921c8303b618ce7a5e6e6771227dd749955ceb5dcb1148e7f74ed8ea5c8766c5c9715f65b961030dd29bdab8fa7b03d151bf5d4ca2f028e5b56f0db9eda4001ad2610873740f008afbd09ee72afaf899cfb8cb8ec183b8790203010001";
   public static final String CFM_RETURN_URL = "http://47.98.144.242:8081/lcoms/receiveHghzData/receiveData.do";
   public static final String REQ01021_ORDER_NO = "ORDER_NO";
   public static final String REQ01021_USER_NAME = "USER_NAME";
   public static final String REQ01021_ID_CARD = "ID_CARD";
   public static final String REQ01021_TEL = "TEL";
   public static final String REQ01021_BILL_AMOUNT = "BILL_AMOUNT";
   public static final String REQ01021_BILL_TAX = "BILL_TAX";
   public static final String REQ01021_COMMERCE_CODE = "COMMERCE_CODE";
   public static final String REQ01021_GOODS_NAME = "GOODS_NAME";
   public static final String REQ01021_GOODS_NUMBER = "GOODS_NUMBER";
   public static final String VERSION = "version";
   public static final String ORDER_ID = "orderId";
   public static final String SUBMIT_TIME = "submitTime";
   public static final String FAILURE_TIME = "failureTime";
   public static final String PARTNER_ID = "partnerId";
   public static final String GOODS_NAME = "goodsName";
   public static final String GOODS_COUNT = "goodsCount";
   public static final String CURR_CODE = "currCode";
   public static final String PAY_AMOUNT = "payAmount";
   public static final String SYSTEM_ACCOUNT = "systemAccount";
   public static final String RETURN_URL = "returnUrl";
   public static final String DECLARE_ORDER_NO = "declareOrderNo";
   public static final String EPORT_CODE = "eportCode";
   public static final String ECOMPANY_CODE = "eCompanyCode";
   public static final String PAYER_NAME = "payerName";
   public static final String PAYER_TYPE = "payerType";
   public static final String PAYER_NUMBER = "payerNumber";
   public static final String PAY_GOODS_AMOUNT = "payGoodsAmount";
   public static final String PAY_TAX_AMOUNT = "payTaxAmount";
   public static final String PAY_FEE_AMOUNT = "payFeeAmount";
   public static final String CHARSET = "charset";
   public static final String SIGN_TYPE = "signType";
   public static final String SIGN_MSG = "signMsg";
   public static final String mCommerceCode = "3318W6K011";
   
   public static void main(String[] args) {
     sumPay(1000, 0, "ampleur防嗮霜小白伞SPF50", 1, 
         "sm20200927001", "341021198906010772", "张玉松");
   }
 
 
   
   public static String sumPay(int fBillAmount, int fBillTaxs, String mGoodsName, int mGoodsCount, String mOrderNo, String mIdCard, String mRealName) {
     String result = collectionApply(fBillAmount, fBillTaxs, mGoodsName, mGoodsCount, mOrderNo, mIdCard, mRealName);
     System.out.println("商户收款请求接口返回值:" + result);
     
     return "";
   }
 
 
 
   
   public static String collectionApply(int fBillAmount, int fBillTaxs, String mGoodsName, int mGoodsCount, String mOrderNo, String mIdCard, String mRealName) {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
 
     
     Calendar calendar = Calendar.getInstance();
     calendar.add(12, -5);
     String submitTime = dateFormat.format(calendar.getTime());
     
     String orderId = String.valueOf(submitTime) + "0001";
     System.out.println("orderId:--------->" + orderId);
     Calendar failureCalendar = Calendar.getInstance();
     failureCalendar.add(5, 365);
     String failureTime = dateFormat.format(failureCalendar.getTime());
 
     
     int iPayAmount = fBillAmount + fBillTaxs;
     
     Map<String, String> mReqParams = new HashMap<>();
     
     mReqParams.put("version", "1.0");
     mReqParams.put("orderId", orderId);
     mReqParams.put("submitTime", submitTime);
     mReqParams.put("failureTime", failureTime);
     mReqParams.put("partnerId", "10000000560");
     mReqParams.put("goodsName", mGoodsName);
     mReqParams.put("goodsCount", String.valueOf(mGoodsCount));
     mReqParams.put("currCode", "CNY");
     mReqParams.put("payAmount", String.valueOf(iPayAmount));
     mReqParams.put("systemAccount", "137170869@qq.com");
     
     mReqParams.put("returnUrl", "http://47.98.144.242:8081/lcoms/receiveHghzData/receiveData.do");
     mReqParams.put("declareOrderNo", mOrderNo);
     mReqParams.put("eportCode", "01");
     mReqParams.put("eCompanyCode", "3318W6K011");
     mReqParams.put("payerName", mRealName);
     mReqParams.put("payerType", "01");
     mReqParams.put("payerNumber", mIdCard);
     mReqParams.put("payGoodsAmount", String.valueOf(fBillAmount));
     mReqParams.put("payTaxAmount", String.valueOf(fBillTaxs));
     mReqParams.put("payFeeAmount", "0");
     
     mReqParams.put("charset", "1");
     mReqParams.put("signType", "2");
     
     String mSign = sing(mReqParams, "30820122300d06092a864886f70d01010105000382010f003082010a02820101009d1d8882b6e0164250a529952dd19e5bfd8a9bbc6f95908b62e43e8e8663c7bf9f8dc61067dd248d2c0a5abaa629c6a775fb46b1a4e1c8c52a1d373261e3f9da895553404a6fbf119e85b60260010e0cf20e2014c27cbb033797273df233b473b08d157b8ad9ea662a720af37a778fef752f2a18acdcc03538c525f25f070ced8320cfc1dfff3097a78987b67d6cf31d7c30d7d7c31e3f53f9c405e52390a15c890558b9cbc3d418921c8303b618ce7a5e6e6771227dd749955ceb5dcb1148e7f74ed8ea5c8766c5c9715f65b961030dd29bdab8fa7b03d151bf5d4ca2f028e5b56f0db9eda4001ad2610873740f008afbd09ee72afaf899cfb8cb8ec183b8790203010001");
     if ("".equals(mSign) || mSign.contains("异常")) {
       System.out.println("异常签名");
     }
     
     mReqParams.put("signMsg", mSign);
 
     
     String result = PayAction.sendPost("https://webgate.sumpay.cn/webgate/collectionApply.htm", mReqParams);
     System.out.println("支付公司商户收款请求接口获取单号返回值-------------------->" + result);
     
     return result;
   }
 
   
   private static String sing(Map<String, String> mReqParams, String secretKey) {
     String mSign = "";
     
     if (mReqParams == null) {
       return mSign;
     }
 
 
     
     try {
       TreeMap<String, String> mParams = new TreeMap<>();
       for (String key : mReqParams.keySet()) {
         if (key == null || mReqParams.get(key) == null) {
           continue;
         }
         mParams.put(key, ((String)mReqParams.get(key)).toString());
       } 
       
       for (String key : mParams.keySet()) {
         String value = mParams.get(key);
         
         if (value == null || "".equals(value) || 
           "null".equalsIgnoreCase(value)) {
           continue;
         }
 
         
         if ("".equals(mSign)) {
           mSign = String.valueOf(mSign) + key + "=" + value; continue;
         } 
         mSign = String.valueOf(mSign) + "&" + key + "=" + value;
       }       
       mSign = String.valueOf(mSign) + "&pkey=" + secretKey;
       
       System.out.println("签名前字符串:" + mSign);
       mSign = md5(mSign);
       
       mSign = mSign.toLowerCase();
       System.out.println("签名后字符串:" + mSign);
     }
     catch (Exception e) {
       
       List<Map<String, String>> mExcList = new ArrayList<>();
       StackTraceElement[] messages = e.getStackTrace();
       int length = messages.length;
       for (int i = 0; i < length; i++) {
         Map<String, String> excMap = new HashMap<>();
         excMap.put("ClassName", messages[i].getClassName());
         excMap.put("FileName", messages[i].getFileName());
         excMap.put("LineNumber", messages[i].getLineNumber()+"");
         excMap.put("MethodName", messages[i].getMethodName());
         excMap.put("ExcMessage", messages[i].toString());
         mExcList.add(excMap);
       } 
       
       mSign = "签名异常：" + JSONObject.toJSONString(mExcList);
       System.out.println("商盟订单代扣计算签名错误，错误原因:" + e.getMessage());
     } 
     
     return mSign;
   }
   private static String md5(String content) {
     String result = "";
     try {
       MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(content.getBytes());
       byte[] b = md.digest();
       
       StringBuffer buf = new StringBuffer("");
       for (int offset = 0; offset < b.length; offset++) {
         int i = b[offset];
         if (i < 0)
           i += 256; 
         if (i < 16)
           buf.append("0"); 
         buf.append(Integer.toHexString(i));
       } 
       result = buf.toString();
     } catch (Exception e) {
       System.out.println(e);
     } 
     return result;
   }
 }


