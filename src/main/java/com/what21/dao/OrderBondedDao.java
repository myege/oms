package com.what21.dao;

import com.what21.model.BeSF;
import com.what21.model.BfbModel;
import com.what21.model.Collect;
import com.what21.model.IdCard;
import com.what21.model.OrderBonded;
import com.what21.model.OrderBondedForExport;
import com.what21.model.OrderBondedPush;
import com.what21.model.OrderBondedSku;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface OrderBondedDao {
  List<OrderBonded> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int insert(OrderBonded paramOrderBonded);
  
  OrderBonded findByTxLogisticID(String paramString);
  
  OrderBondedPush findById(Integer paramInteger);
  
  OrderBonded findByIdToBG(Integer paramInteger);
  
  OrderBonded findByIdToWJ(Integer paramInteger);
  
  OrderBonded findByIdToWms(Integer paramInteger);
  
  int delete(String[] paramArrayOfString);
  
  int updateAuditstatus(OrderBonded paramOrderBonded);
  
  int updateAuditstatusByTxLogisticID(OrderBonded paramOrderBonded);
  
  int updateExpressParam(OrderBonded paramOrderBonded);
  
  int updateIspost(OrderBonded paramOrderBonded);
  
  int updateIsCustoms(OrderBonded paramOrderBonded);
  
  void updateCountAndWeight(OrderBonded paramOrderBonded);
  
  int updateIsCustomsByTxLogisticID(OrderBonded paramOrderBonded);
  
  List<OrderBondedForExport> exportOrder(Map<String, Object> paramMap);
  
  List<OrderBondedForExport> exportOrderBonded(OrderBondedForExport paramOrderBondedForExport);
  
  int updateIsPushToWms(String[] paramArrayOfString);
  
  List<OrderBonded> findAreaByName(String paramString);
  
  List<OrderBonded> findTxLogisticID();
  
  int payNumber(OrderBonded paramOrderBonded);
  
  int updateIsPushDd(OrderBonded paramOrderBonded);
  
  int updateIsPushQd(OrderBonded paramOrderBonded);
  
  OrderBonded findByMailNo(String paramString);
  
  List<OrderBonded> hyjStatus(String paramString);
  
  int backOrder(OrderBondedForExport paramOrderBondedForExport);
  
  int updateStatus(String paramString);
  
  int batchInsert(Object[] paramArrayOfObject);
  
  List<OrderBonded> findOrderStatus(String[] paramArrayOfString);
  
  List<OrderBonded> findByObArr(Object[] paramArrayOfObject);
  
  int findaddress(OrderBonded paramOrderBonded);
  
  int updaddress(OrderBonded paramOrderBonded);
  
  List<OrderBonded> odbdateTime(Map<String, Object> paramMap);
  
  void deletetx(String paramString);
  
  int upm(OrderBonded paramOrderBonded);
  
  List<OrderBonded> findTxId(String paramString);
  
  List<OrderBonded> findCs(String paramString);
  
  int editodIdN(OrderBonded paramOrderBonded);
  
  void updateInvtno(OrderBonded paramOrderBonded);
  
  OrderBonded findOneById(int paramInt);
  
  int countByTydh(String paramString);
  
  int countByCollect(Collect paramCollect);
  
  List<OrderBonded> findAllByCollect(Collect paramCollect);
  
  OrderBonded findOneByBeSF(BeSF paramBeSF);
  
  int editMatter(OrderBonded paramOrderBonded);
  
  void updateDSHKMailNo(OrderBonded paramOrderBonded);
  
  Integer checkMailNo(Integer[] paramArrayOfInteger);
  
  int updateAuditstatus2(OrderBonded paramOrderBonded);
  
  int updateAuditstatus3(OrderBonded paramOrderBonded);
  
  List<OrderBonded> findBystate(OrderBonded paramOrderBonded);
  
  void updateIsPushToWmsByTx(String paramString);
  
  List<OrderBonded> findByIdArr(String[] paramArrayOfString);
  
  int updateIspostForYF(OrderBonded paramOrderBonded);
  
  void updateAuditstatus0(int[] paramArrayOfint);
  
  void updateCarrier(OrderBonded paramOrderBonded);
  
  void transferExcep(Integer paramInteger);
  
  int findException();
  
  List<OrderBonded> findByIds(int[] paramArrayOfint);
  
  List<OrderBondedForExport> exportBondedByIds(String paramString);
  
  int payNumber2(OrderBonded paramOrderBonded);
  
  BfbModel pushBfb(String paramString);
  
  void updateByBfb(OrderBonded paramOrderBonded);
  
  void updateSerialNumber(List<Map<String, String>> paramList);
  
  IdCard checkIdCard(OrderBonded paramOrderBonded);
  
  void insertIdCard(IdCard paramIdCard);
  
  void updateIDCARDverification(IdCard paramIdCard);
  
  OrderBonded findInfo(String paramString);
  
  int findInfoCount(String paramString);
  
  List<OrderBonded> getMapTxLogisticID();
  
  void insertBatch(List<OrderBonded> paramList);
  
  void insertBatchSku(List<OrderBondedSku> paramList);
  
  List<OrderBonded> findbygjz(String paramString);
  
  List<OrderBonded> upgjz(@Param("invtNo") String paramString1, @Param("ids") String paramString2);
  
  List<OrderBonded> findbyA(@Param("A") String paramString);
  
  void updateAtoB(@Param("A") String paramString1, @Param("B") String paramString2);
  
  int deletefottxLogisticID(String paramString);
}


