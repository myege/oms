 package com;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.tools.Tools;
 import com.youzan.cloud.open.sdk.api.API;
 import com.youzan.cloud.open.sdk.api.ApiParams;
 import com.youzan.cloud.open.sdk.core.client.auth.Auth;
 import com.youzan.cloud.open.sdk.core.client.auth.Token;
 import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
 import com.youzan.cloud.open.sdk.gen.v1_0_1.api.YouzanPayCustomsDeclarationReportpaymentQuery;
 import com.youzan.cloud.open.sdk.gen.v1_0_1.api.YouzanPayCustomsDeclarationReportpaymentReport;
 import com.youzan.cloud.open.sdk.gen.v1_0_1.model.YouzanPayCustomsDeclarationReportpaymentQueryParams;
 import com.youzan.cloud.open.sdk.gen.v1_0_1.model.YouzanPayCustomsDeclarationReportpaymentQueryResult;
 import com.youzan.cloud.open.sdk.gen.v1_0_1.model.YouzanPayCustomsDeclarationReportpaymentReportParams;
 import com.youzan.cloud.open.sdk.gen.v1_0_1.model.YouzanPayCustomsDeclarationReportpaymentReportResult;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanItemsOnsaleGet;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanItemsOnsaleGetParams;
 import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanItemsOnsaleGetResult;
 import com.youzan.cloud.open.sdk.gen.v4_0_1.api.YouzanTradesSoldGet;
 import com.youzan.cloud.open.sdk.gen.v4_0_1.model.YouzanTradesSoldGetParams;
 import com.youzan.cloud.open.sdk.gen.v4_0_1.model.YouzanTradesSoldGetResult;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLDecoder;
 import org.apache.commons.codec.binary.Base64;
 public class YzApi
 {
   public static void main(String[] args) throws Exception {
     YouzanTradesSoldGetParams("");
   }
 
 
 
   
   public static void youzanPayCustomsDeclarationReportpaymentQuery(String tk) throws Exception {
     DefaultYZClient defaultYZClient = new DefaultYZClient();
     
     Token token = new Token("5e6abf4100ea0f8f93d23cff52896cf");
     
     YouzanPayCustomsDeclarationReportpaymentQuery youzanPayCustomsDeclarationReportpaymentQuery = new YouzanPayCustomsDeclarationReportpaymentQuery();
     
     YouzanPayCustomsDeclarationReportpaymentQueryParams youzanPayCustomsDeclarationReportpaymentQueryParams = new YouzanPayCustomsDeclarationReportpaymentQueryParams();
 
     
     Long kdtid = Long.valueOf(90524785L);
     youzanPayCustomsDeclarationReportpaymentQueryParams.setKdtId(kdtid);
     youzanPayCustomsDeclarationReportpaymentQueryParams.setTransaction("201207163211262421");
     youzanPayCustomsDeclarationReportpaymentQueryParams.setTid("E20201207163207004104263");
     
     youzanPayCustomsDeclarationReportpaymentQuery.setAPIParams((ApiParams)youzanPayCustomsDeclarationReportpaymentQueryParams);
     
     YouzanPayCustomsDeclarationReportpaymentQueryResult result = (YouzanPayCustomsDeclarationReportpaymentQueryResult)defaultYZClient.invoke((API)youzanPayCustomsDeclarationReportpaymentQuery, (Auth)token, YouzanPayCustomsDeclarationReportpaymentQueryResult.class);
     
     System.out.println("result:" + result.getCode());
     System.out.println("result:" + result.getMessage());
     System.out.println("result:" + result.getData().getTid());
     System.out.println("result:" + result.getData().getTransaction());
     System.out.println("result:" + result.getData().getCustomsInfo());
     System.out.println("result:" + result.getData().getCustomsStatus());
   }
   
   public static void YouzanPayCustomsDeclarationReportpaymentReportParams(String tk) throws Exception {
     DefaultYZClient defaultYZClient = new DefaultYZClient();
     
     Token token = new Token("5e6abf4100ea0f8f93d23cff52896cf");
     
     YouzanPayCustomsDeclarationReportpaymentReport youzanPayCustomsDeclarationReportpaymentReport = new YouzanPayCustomsDeclarationReportpaymentReport();
     
     YouzanPayCustomsDeclarationReportpaymentReportParams youzanPayCustomsDeclarationReportpaymentReportParams = new YouzanPayCustomsDeclarationReportpaymentReportParams();
     Long kdtid = Long.valueOf(90524785L);
     youzanPayCustomsDeclarationReportpaymentReportParams.setKdtId(kdtid);
     youzanPayCustomsDeclarationReportpaymentReportParams.setCustomsCode("YW");
     youzanPayCustomsDeclarationReportpaymentReportParams.setActionType(Integer.valueOf(1));
     youzanPayCustomsDeclarationReportpaymentReportParams.setTransaction("201207163211262421");
     youzanPayCustomsDeclarationReportpaymentReportParams.setTid("E20201207163207004104263");
 
     
     youzanPayCustomsDeclarationReportpaymentReport.setAPIParams((ApiParams)youzanPayCustomsDeclarationReportpaymentReportParams);
     
     YouzanPayCustomsDeclarationReportpaymentReportResult result = (YouzanPayCustomsDeclarationReportpaymentReportResult)defaultYZClient.invoke((API)youzanPayCustomsDeclarationReportpaymentReport, (Auth)token, YouzanPayCustomsDeclarationReportpaymentReportResult.class);
     System.out.println("result:" + result.getCode());
     System.out.println("result:" + result.getMessage());
     System.out.println("result:" + result.getData().getTid());
     System.out.println("result:" + result.getData().getTransaction());
     System.out.println("result:" + result.getData().getKdtId());
     System.out.println("result:" + result.getData().getCustomsStatus());
   }
 
   
   public static void YouzanTradesSoldGetParams(String tk) throws Exception {
     DefaultYZClient defaultYZClient = new DefaultYZClient();
     
     Token token = new Token("5e6abf4100ea0f8f93d23cff52896cf");
     
     YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
     
     YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
     youzanTradesSoldGetParams.setTid("E20201207163207004104263");
     
     youzanTradesSoldGet.setAPIParams((ApiParams)youzanTradesSoldGetParams);
     
     YouzanTradesSoldGetResult result = (YouzanTradesSoldGetResult)defaultYZClient.invoke((API)youzanTradesSoldGet, (Auth)token, YouzanTradesSoldGetResult.class);
     System.out.println("result:" + result.getCode());
     System.out.println("result:" + result.getMessage());
     System.out.println("result:" + result.getData());
     
     for (int i = 0; i < result.getData().getFullOrderInfoList().size(); i++) {
       YouzanTradesSoldGetResult.YouzanTradesSoldGetResultFullorderinfolist s = result.getData().getFullOrderInfoList().get(i);
       
       JSONObject orderListJson = new JSONObject();
       JSONArray orderJSONArray = new JSONArray();
       JSONObject jSONObject = new JSONObject();
       JSONArray jSONArray = new JSONArray();
       String txLogisticID = "";
       
       orderListJson.put("receiveMan", s.getFullOrderInfo().getAddressInfo().getReceiverName());
       orderListJson.put("receiveProvince", s.getFullOrderInfo().getAddressInfo().getDeliveryProvince());
       orderListJson.put("receiveCity", s.getFullOrderInfo().getAddressInfo().getDeliveryCity());
       orderListJson.put("receiveCounty", s.getFullOrderInfo().getAddressInfo().getDeliveryDistrict());
       orderListJson.put("receiveManAddress", s.getFullOrderInfo().getAddressInfo().getDeliveryAddress());
       orderListJson.put("receiveManPhone", s.getFullOrderInfo().getAddressInfo().getReceiverTel());
       orderListJson.put("feeAmount", Integer.valueOf(0));
       orderListJson.put("insureAmount", Integer.valueOf(0));
       orderListJson.put("buyerIdNumber", s.getFullOrderInfo().getOrderInfo().getOrderExtra().getIdCardNumber());
       orderListJson.put("buyerName", s.getFullOrderInfo().getOrderInfo().getOrderExtra().getIdCardName());
       orderListJson.put("carrier", "STO");
       orderListJson.put("sendWarehouse", "乐仓");
       orderListJson.put("merchantNum", "YZAN");
       orderListJson.put("mailNo", "");
       orderListJson.put("pc", "1");
       
       orderListJson.put("payCompany", s.getFullOrderInfo().getOrderInfo().getPayTypeStr());
       
       orderListJson.put("payNumber", s.getFullOrderInfo().getPayInfo().getTransaction().get(0));
 
       
       for (int j = 0; j < s.getFullOrderInfo().getOrders().size(); j++) {
         YouzanTradesSoldGetResult.YouzanTradesSoldGetResultOrders sku = s.getFullOrderInfo().getOrders().get(j);
         
         JSONObject skuListJson = new JSONObject();
         skuListJson.put("itemName", sku.getTitle());
         skuListJson.put("itemSku", sku.getOuterItemId());
         skuListJson.put("itemCount", sku.getNum());
         skuListJson.put("unitPrice", sku.getPrice());
         skuListJson.put("allPrice", sku.getPayment());
         skuListJson.put("itemWeight", Integer.valueOf(1));
         txLogisticID = sku.getSubOrderNo();
         orderJSONArray.add(skuListJson);
       } 
       orderListJson.put("txLogisticID", txLogisticID);
       orderListJson.put("skuList", orderJSONArray);
       
       jSONArray.add(orderListJson);
       
       jSONObject.put("orderList", jSONArray);
       jSONObject.put("userName", "test");
       jSONObject.put("password", "123");
       
       URL wsUrl = new URL("http://47.98.144.242:8081/lcoms/ws/importOrder?wsdl");
       HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
       
       conn.setDoInput(true);
       conn.setDoOutput(true);
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
       
       OutputStream os = conn.getOutputStream();
       
       System.out.println("--------->" + jSONObject);
 
 
 
       
       
       String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://service.what21.com/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body> <q0:importOrderNew><arg0>" + 
         Base64.encodeBase64String(jSONObject.toString().getBytes("UTF-8")) + "</arg0>  </q0:importOrderNew> </soapenv:Body> </soapenv:Envelope>";
       
       os.write(soap.getBytes());
       
       InputStream is = conn.getInputStream();
       
       byte[] b = new byte[1024];
       int len = 0;
       String s1 = "";
       while ((len = is.read(b)) != -1) {
         String ss = new String(b, 0, len, "UTF-8");
         s1 = String.valueOf(s1) + ss;
       } 
       String aaa = URLDecoder.decode(s1, "UTF-8");
       System.out.println("回参=" + URLDecoder.decode(s1, "UTF-8"));
       Tools.fileLog("=======响应数据拼多多出云报文回参==========" + URLDecoder.decode(s1, "UTF-8"));
       is.close();
       os.close();
       conn.disconnect();
     } 
   }
   
   public static void YouzanLogisticsExpressGet(String tk) throws Exception {
     DefaultYZClient defaultYZClient = new DefaultYZClient();
     
     Token token = new Token("5e6abf4100ea0f8f93d23cff52896cf");
     
     YouzanItemsOnsaleGet youzanItemsOnsaleGet = new YouzanItemsOnsaleGet();
     
     YouzanItemsOnsaleGetParams youzanItemsOnsaleGetParams = new YouzanItemsOnsaleGetParams();
 
 
     
     youzanItemsOnsaleGetParams.setPageNo(Integer.valueOf(1));
     
     youzanItemsOnsaleGetParams.setPageSize(Integer.valueOf(10));
     
     youzanItemsOnsaleGet.setAPIParams((ApiParams)youzanItemsOnsaleGetParams);
     
     YouzanItemsOnsaleGetResult result = (YouzanItemsOnsaleGetResult)defaultYZClient.invoke((API)youzanItemsOnsaleGet, (Auth)token, YouzanItemsOnsaleGetResult.class);
     System.out.println("result:" + result.getCode());
     System.out.println("result:" + result.getMessage());
     System.out.println("result:" + result.getData().getItems());
 
     
     for (int i = 0; i < result.getData().getItems().size(); i++) {
       YouzanItemsOnsaleGetResult.YouzanItemsOnsaleGetResultItems s = result.getData().getItems().get(i);
       System.out.println(s.toString());
     } 
   }
 }


