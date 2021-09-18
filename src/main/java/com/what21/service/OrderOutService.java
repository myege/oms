package com.what21.service;

import com.what21.model.OrderOut;
import com.what21.model.OrderOutForExport;
import com.what21.model.OrderOutLog;
import com.what21.model.OrderOutQueryVo;
import com.what21.model.OrderOutSku;
import com.what21.util.GeneralResult;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.dom4j.DocumentException;

public interface OrderOutService {
  List<OrderOut> find(OrderOutQueryVo paramOrderOutQueryVo);
  
  Integer count(OrderOutQueryVo paramOrderOutQueryVo);
  
  List<OrderOutSku> findSku(OrderOutQueryVo paramOrderOutQueryVo);
  
  Integer countSku(OrderOutQueryVo paramOrderOutQueryVo);
  
  void deleteByIds(int[] paramArrayOfint);
  
  GeneralResult importOrderNew(String paramString, Integer paramInteger);
  
  List<String> pushQd(int[] paramArrayOfint, String paramString);
  
  List<OrderOut> findByIds(int[] paramArrayOfint);
  
  List<OrderOutSku> findSkusByPid(int paramInt);
  
  void endOrders(int[] paramArrayOfint);
  
  void updateSku(OrderOutSku paramOrderOutSku);
  
  OrderOutSku findSkuById(Integer paramInteger);
  
  OrderOutLog findLogByWaybillno(String paramString);
  
  void updateGoodsno(String paramString1, String paramString2);
  
  Integer countSkuByGoodsno(String paramString);
  
  OrderOut findById(Integer paramInteger);
  
  List<String> pushSTO2(int[] paramArrayOfint, String paramString) throws Exception;
  
  List<String> updateWeight(int paramInt, String paramString1, String paramString2);
  
  List<OrderOutForExport> findExportByIds(int[] paramArrayOfint);
  
  List<OrderOutForExport> findExport(OrderOutQueryVo paramOrderOutQueryVo);
  
  List<String> pushWLD(int[] paramArrayOfint, String paramString);
  
  int pushZsYd(int[] paramArrayOfint) throws IOException, DocumentException;
  
  String outYD(int[] paramArrayOfint, int paramInt1, int paramInt2) throws Exception;
  
  String outCloseQd(int[] paramArrayOfint, int paramInt) throws Exception;
  
  String outDD3J(int[] paramArrayOfint, int paramInt, Integer paramInteger) throws Exception;
  
  String outQDZFD3J(int[] paramArrayOfint, int paramInt, Integer paramInteger) throws Exception;
  
  String outLJD(int[] paramArrayOfint, int paramInt1, int paramInt2) throws Exception;
  
  String outST(int[] paramArrayOfint, int paramInt) throws Exception;
  
  List<Map<String, Object>> jxsh(String paramString);
}


