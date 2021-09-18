package com.what21.service;

import com.what21.model.CompanyZyAndOrderMail;
import com.what21.model.OrderMail;
import com.what21.model.OrderMailAndChangeOdd;
import com.what21.model.OrderMailSku;
import com.what21.model.OrderMailSkuAll;
import java.util.List;
import java.util.Map;

public interface OrderMailSkuService {
  int insert(OrderMailSku paramOrderMailSku);
  
  List<OrderMailSku> detailedMailSku(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  List<OrderMailSku> findAllPrice(String paramString);
  
  List<OrderMailSku> findCostxy(String paramString);
  
  List<OrderMailSku> findCostdy(String paramString);
  
  List<OrderMailSkuAll> findALLaboutSku();
  
  List<OrderMailSkuAll> findALLaboutSku_byIDS(String paramString);
  
  List<OrderMail> findTxloginst(String paramString);
  
  String odmTax(int paramInt);
  
  int upodm(OrderMailSku paramOrderMailSku);
  
  List<OrderMailAndChangeOdd> findByTxLogisticID2(List<CompanyZyAndOrderMail> paramList);
}


