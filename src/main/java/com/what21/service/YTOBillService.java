package com.what21.service;

import com.what21.model.YTOBillSku;
import com.what21.model.YtoBill;
import com.what21.model.YtoBillEx;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface YTOBillService {
  List<YTOBillSku> findByIdsForSku(List<YtoBill> paramList);
  
  void insertYtoBatch(List<YtoBill> paramList);
  
  int insertYTO(YtoBill paramYtoBill);
  
  List<YtoBill> findByOrderNo(YtoBill paramYtoBill);
  
  List<YtoBill> findAllOfYTO(Map<String, Object> paramMap);
  
  int deleteAll(String paramString);
  
  int delete(int[] paramArrayOfint);
  
  List<YtoBill> findByWay_1(Map<String, Object> paramMap);
  
  int updateByID(YtoBill paramYtoBill);
  
  int updateForIs(YtoBill paramYtoBill);
  
  int count(Map<String, Object> paramMap);
  
  List<YtoBillEx> leftJoin(int[] paramArrayOfint);
  
  List<YtoBill> findByWaybillNo(Map<String, Object> paramMap);
  
  List<YtoBillEx> findExport();
  
  List<YtoBill> findById(int[] paramArrayOfint);
  
  List<YtoBill> findByWaybillNo_createTime(Map<String, Object> paramMap);
  
  List<YtoBill> findByWaybillNo_deliveryTime(Map<String, Object> paramMap);
  
  GeneralResult importOrder(String paramString1, int paramInt, String paramString2) throws Exception;
}


