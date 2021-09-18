package com.what21.dao;

import com.what21.model.YTOBillSku;
import com.what21.model.YtoBill;
import com.what21.model.YtoBillEx;
import java.util.List;
import java.util.Map;

public interface YTOBillDao {
  int deleteAll(String paramString);
  
  void insertYtoBatch(List<YtoBill> paramList);
  
  List<YTOBillSku> findByIdsForSku(List<YtoBill> paramList);
  
  List<YtoBill> findByOrderNo(YtoBill paramYtoBill);
  
  int insertYTO(YtoBill paramYtoBill);
  
  List<YtoBill> findAllOfYTO(Map<String, Object> paramMap);
  
  int delete(int[] paramArrayOfint);
  
  int updateByID(YtoBill paramYtoBill);
  
  int updateForIs(YtoBill paramYtoBill);
  
  int count(Map<String, Object> paramMap);
  
  List<YtoBill> findByWaybillNo(Map<String, Object> paramMap);
  
  List<YtoBill> findByWaybillNo_createTime(Map<String, Object> paramMap);
  
  List<YtoBill> findByWaybillNo_deliveryTime(Map<String, Object> paramMap);
  
  List<YtoBill> findById(int[] paramArrayOfint);
  
  List<YtoBill> findByWay_1(Map<String, Object> paramMap);
  
  List<YtoBillEx> leftJoin(int[] paramArrayOfint);
  
  List<YtoBillEx> findExport();
  
  YtoBill findId(Map<String, Object> paramMap);
  
  void insertList(List<YtoBill> paramList);
}


