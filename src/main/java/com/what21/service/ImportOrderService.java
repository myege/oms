package com.what21.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService
@BindingType("http://www.w3.org/2003/05/soap/bindings/HTTP/")
public interface ImportOrderService {
  @WebMethod
  String importOrderByJsonString(String paramString);
  
  @WebMethod
  String sendMergerInfo(@WebParam(name = "content") String paramString);
  
  @WebMethod
  String Logisticstrack(String paramString) throws Exception;
  
  @WebMethod
  String queryOrderStatus(String paramString) throws Exception;
  
  @WebMethod
  String importOrder(String paramString);
  
  @WebMethod
  String newLogisticstrack(String paramString) throws Exception;
  
  @WebMethod
  String sendStockDeleteInfo(String paramString) throws Exception;
  
  @WebMethod
  String importOrderNew(String paramString);
  
  @WebMethod
  String getHtoGj(String paramString) throws Exception;
  
  @WebMethod
  String cancleOrder(String paramString) throws Exception;
}


