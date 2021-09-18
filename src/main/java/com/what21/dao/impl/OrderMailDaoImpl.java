 package com.what21.dao.impl;
 
 import com.what21.dao.OrderMailDao;
 import com.what21.model.BfbModel;
 import com.what21.model.EchattsBonded;
 import com.what21.model.FindBytxLogisticIDForYtoExcel;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderMailForExport;
 import com.what21.model.OrderMailPush;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class OrderMailDaoImpl
   implements OrderMailDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderMail> findAll(Map<String, Object> map) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).count(pageMap);
   }
   
   public OrderMail findByTxLogisticID(String txLogisticID) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByTxLogisticID(txLogisticID);
   }
   
   public OrderMailPush findById(Integer id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findById(id);
   }
   
   public OrderMail findByIdToBG(Integer id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByIdToBG(id);
   }
   
   public OrderMail findByIdToWJ(Integer id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByIdToWJ(id);
   }
 
   
   public int insert(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).insert(orderMail);
   }
 
   
   public int delete(String[] ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).delete(ids);
   }
 
   
   public int updateAuditstatus(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateAuditstatus(orderMail);
   }
 
   
   public int updateExpressParam(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateExpressParam(orderMail);
   }
 
   
   public int updateIspost(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIspost(orderMail);
   }
 
   
   public int updateIsCustoms(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIsCustoms(orderMail);
   }
 
   
   public void updateCountAndWeight(OrderMail orderMail) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateCountAndWeight(orderMail);
   }
 
   
   public OrderMail findByMailNo(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByMailNo(orderMail);
   }
 
   
   public List<OrderMail> findMailNo(Map<String, Object> pageMap) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findMailNo(pageMap);
   }
 
   
   public int updateIsPushCz(Integer id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIsPushCz(id);
   }
 
   
   public List<OrderMail> findTxLogisticID() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findTxLogisticID();
   }
 
   
   public int payNumber(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).payNumber(orderMail);
   }
 
   
   public int updateIsPushDd(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIsPushDd(orderMail);
   }
 
   
   public int updateIsPushQd(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIsPushQd(orderMail);
   }
 
   
   public List<OrderMail> findByTotalMailNo(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByTotalMailNo(totalMailNo);
   }
   
   public int countmx(int id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).countmx(id);
   }
 
   
   public List<OrderMail> findeXport(Map<String, Object> pageMap) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findeXport(pageMap);
   }
 
   
   public List<OrderMail> findeXportId(Integer id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findeXportId(id);
   }
 
   
   public int batchInsert(Object[] omArr) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).batchInsert(omArr);
   }
 
   
   public OrderMail getTax(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).getTax(orderMail);
   }
   
   public List<OrderMail> findBybatchNumber(Map<String, Object> pageMap) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findBybatchNumber(pageMap);
   }
 
   
   public int conbatchNumber(String batchNumber) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).conbatchNumber(batchNumber);
   }
 
   
   public List<OrderMail> deriveddetail(String batchNumber) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).deriveddetail(batchNumber);
   }
 
 
   
   public List<OrderMailForExport> exportMoil(Map<String, Object> pageMap) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).exportMoil(pageMap);
   }
 
 
   
   public List<OrderMail> exportMoilid(Integer id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).exportMoilid(id);
   }
 
   
   public List<OrderMail> findAllmx(int id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findAllmx(id);
   }
 
   
   public OrderMail findTxLogisticIDByTotalMailNo(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findTxLogisticIDByTotalMailNo(totalMailNo);
   }
 
   
   public OrderMail findOrderMailDy(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findOrderMailDy(totalMailNo);
   }
 
   
   public OrderMail findOrderMailXy(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findOrderMailXy(totalMailNo);
   }
 
   
   public OrderMail findOrderMai(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findOrderMai(totalMailNo);
   }
 
   
   public List<OrderMail> storageOrderMai(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).storageOrderMai(totalMailNo);
   }
 
   
   public List<OrderMail> odmdateTime(Map<String, Object> pageMap) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).odmdateTime(pageMap);
   }
 
   
   public int editIdN(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).editIdN(orderMail);
   }
 
   
   public void updateInvtno(OrderMail orderMail) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateInvtno(orderMail);
   }
 
 
   
   public OrderMail findOneById(int id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findOneById(id);
   }
 
   
   public int countByTydh(String tydh) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).countByTydh(tydh);
   }
 
   
   public void updateValueAddedTax(OrderMail orderMail) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateValueAddedTax(orderMail);
   }
 
 
   
   public List<OrderMail> findByTydh(Map<String, Object> map) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByTydh(map);
   }
 
   
   public int updateExpressParamsf(String ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateExpressParamsf(ids);
   }
 
   
   public List<OrderMail> iadd() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).iadd();
   }
 
   
   public OrderMail findByIdTosf(int parseInt) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByIdTosf(parseInt);
   }
 
   
   public void inztsf(OrderMail om) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).inztsf(om);
   }
 
   
   public int updateIsPrint(OrderMail om) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIsPrint(om);
   }
 
   
   public int editMatterm(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).editMatterm(orderMail);
   }
 
   
   public int updateMailNoById(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateMailNoById(orderMail);
   }
 
   
   public Map<String, String> findByTotalMailNoS(String totalMailNoS) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByTotalMailNoS(totalMailNoS);
   }
 
   
   public Integer deleteMailNos(String[] mails) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).deleteMailNos(mails);
   }
 
   
   public List<OrderMail> findByOmArr(Object[] omArr) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByOmArr(omArr);
   }
 
   
   public int updateAuditstatus2(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateAuditstatus2(orderMail);
   }
 
   
   public List<OrderMail> findByIdArrToCz(String[] idArr) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByIdArrToCz(idArr);
   }
 
   
   public List<OrderMail> findByTotalMailNoToCz(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByTotalMailNoToCz(totalMailNo);
   }
 
   
   public List<OrderMail> findReturnCode(String totalMailNo) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findReturnCode(totalMailNo);
   }
 
   
   public void batchUpdateIsPushCz(List<OrderMail> orderMailList) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).batchUpdateIsPushCz(orderMailList);
   }
 
   
   public List<OrderMail> findByIdArr(String[] idArr) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByIdArr(idArr);
   }
 
   
   public int updateIspostForYF(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIspostForYF(orderMail);
   }
 
   
   public int updateReturnCode(OrderMail om) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateReturnCode(om);
   }
 
   
   public void transferExcep(Integer id) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).transferExcep(id);
   }
 
 
   
   public int findException() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findException();
   }
 
   
   public void updateAuditstatus0(int[] ids) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateAuditstatus0(ids);
   }
 
 
   
   public List<OrderMailForExport> exportMailByIds(String ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).exportMailByIds(ids);
   }
 
 
   
   public int updateIsMailNo(OrderMail order) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateIsMailNo(order);
   }
 
 
   
   public List<OrderMail> findByIds(int[] ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByIds(ids);
   }
 
 
   
   public int updateByIds(int[] ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateByIds(ids);
   }
 
 
   
   public List<OrderMail> findtxLogisticID(int[] ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findtxLogisticID(ids);
   }
 
 
   
   public List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcels(String txLogisticID) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findBytxLogisticIDForYtoExcels(txLogisticID);
   }
 
 
   
   public List<FindBytxLogisticIDForYtoExcel> findBytxLogisticIDForYtoExcelById(int[] ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findBytxLogisticIDForYtoExcelById(ids);
   }
 
 
   
   public int findByIdToalOrderMailNo(String toal) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findByIdToalOrderMailNo(toal);
   }
 
 
   
   public void updateToal(OrderMail orderMail) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateToal(orderMail);
   }
 
   
   public void updateSerialNumber(List<Map<String, String>> maporderList) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateSerialNumber(maporderList);
   }
 
 
   
   public BfbModel pushBfb(String id) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).pushBfb(id);
   }
 
 
   
   public void updateByBfb(OrderMail orderBonded) {
     ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).updateByBfb(orderBonded);
   }
 
 
   
   public Integer findOrderToday() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findOrderToday();
   }
 
   
   public int findOrderYear() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findOrderYear();
   }
 
 
   
   public int findExceptionOrderYear() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findExceptionOrderYear();
   }
 
 
   
   public int findLastYearOrderNumber() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findLastYearOrderNumber();
   }
 
 
   
   public int findLastMonthOrderNumber() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findLastMonthOrderNumber();
   }
 
 
   
   public String findLastMonthTotalSum() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findLastMonthTotalSum();
   }
 
   
   public String findTotalSumYear() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findTotalSumYear();
   }
 
   
   public List<EchattsBonded> findbonded() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findbonded();
   }
 
   
   public List<EchattsBonded> findMail() {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).findMail();
   }
 
 
   
   public List<Map> neworderzycx(String id) {
     return this.sqlSessionTemplate.selectList(String.valueOf(OrderMailDao.class.getName()) + ".neworderzycx", id);
   }
 
 
   
   public int updateddzt_2(String id) {
     return this.sqlSessionTemplate.update(String.valueOf(OrderMailDao.class.getName()) + ".updateddzt_2", id);
   }
 
   
   public int updateptzt_2(String id) {
     return this.sqlSessionTemplate.update(String.valueOf(OrderMailDao.class.getName()) + ".updateptzt_2", id);
   }
 
   
   public int updateddhzzt_1(String id) {
     return this.sqlSessionTemplate.update(String.valueOf(OrderMailDao.class.getName()) + ".updateddhzzt_1", id);
   }
 
 
   
   public List<OrderMailForExport> exportGD(OrderMail orderMail) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).exportGD(orderMail);
   }
 
 
   
   public int goBackOrder(String[] ids) {
     return ((OrderMailDao)this.sqlSessionTemplate.getMapper(OrderMailDao.class)).goBackOrder(ids);
   }
 }


