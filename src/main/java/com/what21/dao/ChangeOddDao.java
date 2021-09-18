package com.what21.dao;

import com.what21.model.ChangeOdd;
import com.what21.model.ExportChangeOdd;
import com.what21.model.OrderMail;
import java.util.List;
import java.util.Map;

public interface ChangeOddDao {
  List<ChangeOdd> findAll(Map<String, Object> paramMap);
  
  int inserts(List<ChangeOdd> paramList);
  
  int count(Map<String, Object> paramMap);
  
  ChangeOdd findByExpressNumber(String paramString);
  
  List<ChangeOdd> findByExpressNumberForAlls(List<ChangeOdd> paramList);
  
  List<ChangeOdd> findByExpressNumberCount(List<OrderMail> paramList);
  
  void deleteById(int paramInt);
  
  void insert(ChangeOdd paramChangeOdd);
  
  int insert_int(ChangeOdd paramChangeOdd);
  
  ChangeOdd findById(int paramInt);
  
  void update(String paramString);
  
  List<ChangeOdd> findExpressNumber(Map<String, Object> paramMap);
  
  int updates(String paramString);
  
  List<ChangeOdd> findexpn(Map<String, Object> paramMap);
  
  void updatePrintType(String paramString);
  
  ChangeOdd updateepn(String paramString);
  
  List<ChangeOdd> allfind();
  
  void insertand(ChangeOdd paramChangeOdd);
  
  void updatechangeodd(ChangeOdd paramChangeOdd);
  
  void deleteByIds(int[] paramArrayOfint);
  
  List<ExportChangeOdd> exportByIds(int[] paramArrayOfint);
  
  List<ExportChangeOdd> exportByCons(ExportChangeOdd paramExportChangeOdd);
}


