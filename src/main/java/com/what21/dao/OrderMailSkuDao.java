package com.what21.dao;

import com.what21.model.CompanyZyAndOrderMail;
import com.what21.model.ItemForZy;
import com.what21.model.OrderMail;
import com.what21.model.OrderMailAndChangeOdd;
import com.what21.model.OrderMailPushSku;
import com.what21.model.OrderMailSku;
import com.what21.model.OrderMailSkuAll;
import java.util.List;
import java.util.Map;

public interface OrderMailSkuDao {
  int insert(OrderMailSku paramOrderMailSku);
  
  int delete(String paramString);
  
  List<OrderMailPushSku> findByTxLogisticID(String paramString);
  
  List<OrderMailSku> findById(String paramString);
  
  List<OrderMailSku> detailedMailSku(Map<String, Object> paramMap);
  
  int count(String paramString);
  
  ItemForZy findItemForZy(String paramString);
  
  int batchInsert(List<OrderMailSku> paramList);
  
  void updateData(OrderMailSku paramOrderMailSku);
  
  List<OrderMailSku> findAllPrice(String paramString);
  
  List<OrderMailSku> findCostxy(String paramString);
  
  List<OrderMailSku> findCostdy(String paramString);
  
  List<OrderMailSku> findAllPriceByTxLogisticID(List<OrderMail> paramList);
  
  List<OrderMailSkuAll> findALLaboutSku();
  
  List<OrderMailSkuAll> findALLaboutSku_byIDS(String paramString);
  
  List<OrderMail> findTxloginst(String paramString);
  
  String odmTax(int paramInt);
  
  int upodm(OrderMailSku paramOrderMailSku);
  
  List<OrderMailSku> findByIdArrToCz(String[] paramArrayOfString);
  
  List<OrderMailSku> findByTidList(List<OrderMail> paramList);
  
  List<OrderMailAndChangeOdd> findByTxLogisticID2(List<CompanyZyAndOrderMail> paramList);
}


