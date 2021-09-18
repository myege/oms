 package com.what21.dao.impl;
 
 import com.what21.dao.OrderBondedDao;
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
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class OrderBondedDaoImpl
   implements OrderBondedDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderBonded> findAll(Map<String, Object> map) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).count(pageMap);
   }
 
   
   public OrderBonded findByTxLogisticID(String txLogisticID) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByTxLogisticID(txLogisticID);
   }
 
   
   public OrderBondedPush findById(Integer id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findById(id);
   }
 
   
   public OrderBonded findByIdToBG(Integer id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByIdToBG(id);
   }
 
   
   public OrderBonded findByIdToWJ(Integer id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByIdToWJ(id);
   }
 
   
   public OrderBonded findByIdToWms(Integer id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByIdToWms(id);
   }
 
   
   public int insert(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).insert(orderBonded);
   }
 
   
   public int delete(String[] ids) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).delete(ids);
   }
 
   
   public int updateAuditstatus(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateAuditstatus(orderBonded);
   }
 
   
   public int updateAuditstatusByTxLogisticID(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateAuditstatusByTxLogisticID(orderBonded);
   }
 
   
   public int updateExpressParam(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateExpressParam(orderBonded);
   }
 
   
   public int updateIspost(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIspost(orderBonded);
   }
 
   
   public int updateIsCustoms(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIsCustoms(orderBonded);
   }
 
   
   public void updateCountAndWeight(OrderBonded orderBonded) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateCountAndWeight(orderBonded);
   }
 
   
   public int updateIsCustomsByTxLogisticID(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIsCustomsByTxLogisticID(orderBonded);
   }
 
   
   public List<OrderBondedForExport> exportOrder(Map<String, Object> paramMap) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).exportOrder(paramMap);
   }
 
   
   public List<OrderBondedForExport> exportOrderBonded(OrderBondedForExport orderBondedForExport) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).exportOrderBonded(orderBondedForExport);
   }
 
   
   public int updateIsPushToWms(String[] idArr) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIsPushToWms(idArr);
   }
 
   
   public List<OrderBonded> findAreaByName(String paramName) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findAreaByName(paramName);
   }
 
   
   public List<OrderBonded> findTxLogisticID() {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findTxLogisticID();
   }
 
   
   public int payNumber(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).payNumber(orderBonded);
   }
 
   
   public int updateIsPushDd(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIsPushDd(orderBonded);
   }
 
   
   public int updateIsPushQd(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIsPushQd(orderBonded);
   }
 
   
   public OrderBonded findByMailNo(String mailNo) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByMailNo(mailNo);
   }
 
   
   public List<OrderBonded> hyjStatus(String merchantNum) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).hyjStatus(merchantNum);
   }
 
   
   public int updateStatus(String merchantNum) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateStatus(merchantNum);
   }
 
   
   public int batchInsert(Object[] ob) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).batchInsert(ob);
   }
 
   
   public List<OrderBonded> findOrderStatus(String[] idArr) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findOrderStatus(idArr);
   }
 
   
   public List<OrderBonded> findByObArr(Object[] obArr) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByObArr(obArr);
   }
 
   
   public int findaddress(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findaddress(orderBonded);
   }
 
   
   public int updaddress(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updaddress(orderBonded);
   }
 
   
   public List<OrderBonded> odbdateTime(Map<String, Object> pageMap) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).odbdateTime(pageMap);
   }
 
   
   public void deletetx(String id) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).deletetx(id);
   }
 
   
   public int upm(OrderBonded ob) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).upm(ob);
   }
 
   
   public List<OrderBonded> findTxId(String id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findTxId(id);
   }
 
   
   public List<OrderBonded> findCs(String idd) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findCs(idd);
   }
 
   
   public int editodIdN(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).editodIdN(orderBonded);
   }
 
   
   public void updateInvtno(OrderBonded orderBonded) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateInvtno(orderBonded);
   }
 
 
   
   public OrderBonded findOneById(int id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findOneById(id);
   }
 
   
   public int countByTydh(String tydh) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).countByTydh(tydh);
   }
 
   
   public int countByCollect(Collect collect) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).countByCollect(collect);
   }
 
   
   public List<OrderBonded> findAllByCollect(Collect collect) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findAllByCollect(collect);
   }
 
   
   public OrderBonded findOneByBeSF(BeSF besf) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findOneByBeSF(besf);
   }
 
 
   
   public int editMatter(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).editMatter(orderBonded);
   }
 
   
   public void updateDSHKMailNo(OrderBonded orderBonded) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateDSHKMailNo(orderBonded);
   }
 
 
   
   public Integer checkMailNo(Integer[] ids) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).checkMailNo(ids);
   }
 
   
   public int updateAuditstatus2(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateAuditstatus2(orderBonded);
   }
   
   public int updateAuditstatus3(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateAuditstatus3(orderBonded);
   }
 
   
   public List<OrderBonded> findBystate(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findBystate(orderBonded);
   }
 
   
   public void updateIsPushToWmsByTx(String txid) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIsPushToWmsByTx(txid);
   }
 
   
   public List<OrderBonded> findByIdArr(String[] idArr) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByIdArr(idArr);
   }
 
   
   public int updateIspostForYF(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIspostForYF(orderBonded);
   }
 
   
   public void updateAuditstatus0(int[] ids) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateAuditstatus0(ids);
   }
 
 
   
   public void updateCarrier(OrderBonded orderBonded) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateCarrier(orderBonded);
   }
 
 
   
   public void transferExcep(Integer id) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).transferExcep(id);
   }
 
 
   
   public int findException() {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findException();
   }
 
   
   public List<OrderBonded> findByIds(int[] ids) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findByIds(ids);
   }
 
   
   public List<OrderBondedForExport> exportBondedByIds(String ids) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).exportBondedByIds(ids);
   }
 
   
   public int payNumber2(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).payNumber2(orderBonded);
   }
 
 
   
   public BfbModel pushBfb(String id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).pushBfb(id);
   }
 
 
   
   public void updateByBfb(OrderBonded orderBonded) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateByBfb(orderBonded);
   }
 
   
   public void updateSerialNumber(List<Map<String, String>> maporderList) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateSerialNumber(maporderList);
   }
 
 
 
   
   public IdCard checkIdCard(OrderBonded orderBonded) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).checkIdCard(orderBonded);
   }
 
 
   
   public void insertIdCard(IdCard idCard) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).insertIdCard(idCard);
   }
 
 
   
   public void updateIDCARDverification(IdCard idCard) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateIDCARDverification(idCard);
   }
 
 
   
   public OrderBonded findInfo(String txLogisticID) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findInfo(txLogisticID);
   }
   
   public int findInfoCount(String txLogisticID) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findInfoCount(txLogisticID);
   }
 
   
   public List<OrderBonded> getMapTxLogisticID() {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).getMapTxLogisticID();
   }
 
 
   
   public void insertBatch(List<OrderBonded> bondeds) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).insertBatch(bondeds);
   }
 
   
   public void insertBatchSku(List<OrderBondedSku> bondedSkus) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).insertBatchSku(bondedSkus);
   }
 
 
   
   public int backOrder(OrderBondedForExport bondedForExport) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).backOrder(bondedForExport);
   }
   
   public List<OrderBonded> findbygjz(String id) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findbygjz(id);
   }
 
 
   
   public List<OrderBonded> upgjz(String invtNo, String ids) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).upgjz(invtNo, ids);
   }
 
   
   public List<OrderBonded> findbyA(@Param("A") String A) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).findbyA(A);
   }
 
 
   
   public void updateAtoB(@Param("A") String A, @Param("B") String B) {
     ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).updateAtoB(A, B);
   }
 
   
   public int deletefottxLogisticID(String txLogisticID) {
     return ((OrderBondedDao)this.sqlSessionTemplate.getMapper(OrderBondedDao.class)).deletefottxLogisticID(txLogisticID);
   }
 }


