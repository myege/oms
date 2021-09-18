package com.what21.service;

import com.what21.model.ChangeOdd;
import com.what21.model.ExportChangeOdd;
import com.what21.model.OrderMail;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface ChangeOddService {
  List<ChangeOdd> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  ChangeOdd findByExpressNumber(String paramString);
  
  List<ChangeOdd> findByExpressNumberCount(List<OrderMail> paramList);
  
  void deleteById(int paramInt);
  
  List<ChangeOdd> findByExpressNumberForAlls(List<ChangeOdd> paramList);
  
  void insert(ChangeOdd paramChangeOdd);
  
  int insert_int(ChangeOdd paramChangeOdd);
  
  int inserts(List<ChangeOdd> paramList);
  
  ChangeOdd findById(int paramInt);
  
  void update(String paramString);
  
  List<ChangeOdd> findExpressNumber(Map<String, Object> paramMap);
  
  int updates(String paramString);
  
  GeneralResult importOrder(String paramString, int paramInt);
  
  List<ChangeOdd> findexpn(Map<String, Object> paramMap);
  
  void updatePrintType(String paramString);
  
  ChangeOdd updateepn(String paramString);
  
  List<ChangeOdd> allfind();
  
  void insertand(ChangeOdd paramChangeOdd);
  
  void updateCod(ChangeOdd paramChangeOdd);
  
  void updatechangeodd(ChangeOdd paramChangeOdd);
  
  void deleteByIds(int[] paramArrayOfint);
  
  List<ExportChangeOdd> exportByIds(int[] paramArrayOfint);
  
  List<ExportChangeOdd> exportByCons(ExportChangeOdd paramExportChangeOdd);
}


