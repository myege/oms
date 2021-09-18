 package com.zt.suning;
 
 import com.suning.api.DefaultSuningClient;
 import com.suning.api.SuningRequest;
 import com.suning.api.entity.custom.historyOrderQueryQueryRequest;
 import com.suning.api.entity.custom.historyOrderQueryQueryResponse;
 import com.suning.api.entity.custom.orderGetGetRequest;
 import com.suning.api.entity.custom.orderGetGetResponse;
 import com.suning.api.entity.custom.orderQueryQueryRequest;
 import com.suning.api.entity.custom.orderQueryQueryResponse;
 import com.suning.api.exception.SuningApiException;
 public class suningApi
 {
   public static String orderGetGetResponse(String url) {
     orderGetGetRequest request = new orderGetGetRequest();
     request.setOrderCode("4511680451");
     request.setCheckParam(true);
     String serverUrl = "https://openpre.cnsuning.com/api/http/sopRequest";
     String appKey = "1939580813f4e65dd1edc370a002787d";
     String appSecret = "8f8c285916d433b6e851804d9227ca71";
     DefaultSuningClient client = new DefaultSuningClient(serverUrl, appKey, appSecret, "json");
     try {
       orderGetGetResponse response = (orderGetGetResponse)client.excute((SuningRequest)request);
       System.out.println("返回json/xml格式数据 :" + response.getBody());
     }
     catch (SuningApiException e) {
       e.printStackTrace();
     } 
     
     return "";
   }
   
   public static String SuningCustomHistoryorderQuery(String url) {
     historyOrderQueryQueryRequest request = new historyOrderQueryQueryRequest();
     request.setEndTime("2020-06-15 23:00:00");
     request.setPageNo(1);
     request.setPageSize(10);
     request.setStartTime("2020-05-16 21:06:12");
     request.setUserID("7017925352");
     request.setUserName("123@qq.com");
     
     request.setCheckParam(true);
     String serverUrl = "https://open.suning.com/api/http/sopRequest";
 
     
     String appKey = "42edd9f2cc20e341cff301eefd6e3e59";
     String appSecret = "330e671d5a20281c3975dbfd841da561";
     
     DefaultSuningClient client = new DefaultSuningClient(serverUrl, appKey, appSecret, "json");
     try {
       historyOrderQueryQueryResponse response = (historyOrderQueryQueryResponse)client.excute((SuningRequest)request);
       System.out.println("返回json/xml格式数据 :" + response.getBody());
     } catch (SuningApiException e) {
       e.printStackTrace();
     } 
 
     
     return "";
   }
   public static String SuningCustomOrderQuery(String url) {
     orderQueryQueryRequest request = new orderQueryQueryRequest();
     request.setEndTime("2020-09-16 23:59:00");
 
     
     request.setPageNo(1);
     request.setPageSize(10);
     request.setStartTime("2020-09-16 00:00:00");
 
 
     
     request.setCheckParam(true);
     String serverUrl = "https://open.suning.com/api/http/sopRequest";
     String appKey = "42edd9f2cc20e341cff301eefd6e3e59";
     String appSecret = "330e671d5a20281c3975dbfd841da561";
     
     DefaultSuningClient client = new DefaultSuningClient(serverUrl, appKey, appSecret, "json");
     try {
       orderQueryQueryResponse response = (orderQueryQueryResponse)client.excute((SuningRequest)request);
       System.out.println("返回json/xml格式数据 :" + response.getBody());
     } catch (SuningApiException e) {
       e.printStackTrace();
     } 
 
     
     return "";
   }
 
   
   public static void main(String[] args) {
     SuningCustomOrderQuery("");
   }
 }


