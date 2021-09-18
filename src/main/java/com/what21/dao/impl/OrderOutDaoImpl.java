 package com.what21.dao.impl;
 
 import com.what21.dao.OrderOutDao;
 import com.what21.model.OrderOut;
 import com.what21.model.OrderOutForExport;
 import com.what21.model.OrderOutQueryVo;
 import com.what21.model.OrderOutSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class OrderOutDaoImpl
   implements OrderOutDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderOut> find(OrderOutQueryVo orderOutQueryVo) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).find(orderOutQueryVo);
   }
 
   
   public Integer count(OrderOutQueryVo orderOutQueryVo) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).count(orderOutQueryVo);
   }
 
   
   public void insert(OrderOut orderOut) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).insert(orderOut);
   }
 
 
   
   public OrderOut findById(Integer id) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findById(id);
   }
 
   
   public void deleteById(Integer id) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).deleteById(id);
   }
 
 
   
   public void update(OrderOut orderOut) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).update(orderOut);
   }
 
 
   
   public List<OrderOutSku> findSku(OrderOutQueryVo orderOutQueryVo) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findSku(orderOutQueryVo);
   }
 
   
   public Integer countSku(OrderOutQueryVo orderOutQueryVo) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).countSku(orderOutQueryVo);
   }
 
   
   public void insertSku(OrderOutSku orderOutSku) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).insertSku(orderOutSku);
   }
 
 
   
   public void deleteByOrderno(String orderno) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).deleteByOrderno(orderno);
   }
 
 
   
   public OrderOut findByOrderno(String orderno) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findByOrderno(orderno);
   }
 
   
   public List<OrderOut> findByiIds(int[] ids) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findByiIds(ids);
   }
 
   
   public void updateStatus1(int id) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateStatus1(id);
   }
 
   
   public String findSumPriceByOrderno(String orderno) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findSumPriceByOrderno(orderno);
   }
 
   
   public void updateSumPriceByOrderno(List<OrderOut> list) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateSumPriceByOrderno(list);
   }
 
 
   
   public Integer findNumByOrderno(String orderno) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findNumByOrderno(orderno);
   }
 
   
   public List<OrderOutSku> findSkusByOrderno(String orderno) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findSkusByOrderno(orderno);
   }
 
   
   public void insertBatch(List<OrderOut> list) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).insertBatch(list);
   }
 
 
   
   public void insertSkuBatch(List<OrderOutSku> list2) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).insertSkuBatch(list2);
   }
 
 
   
   public Integer sumByOrderno(String orderno) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).sumByOrderno(orderno);
   }
 
   
   public void updateEnd(int[] ids) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateEnd(ids);
   }
 
 
   
   public OrderOutSku findSkuById(Integer id) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findSkuById(id);
   }
 
   
   public void updateSku(OrderOutSku sku) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateSku(sku);
   }
 
 
   
   public String findCompanycodeByUserid(Integer userid) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findCompanycodeByUserid(userid);
   }
 
   
   public void updateStatus(int status, String wayBill) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateStatus(status, wayBill);
   }
 
 
   
   public void updateGoodsno(String goodsno1, String goodsno2) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateGoodsno(goodsno1, goodsno2);
   }
 
 
   
   public Integer countSkuByGoodsno(String goodsno1) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).countSkuByGoodsno(goodsno1);
   }
 
   
   public void deleteSkuByIds(int[] ids) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).deleteSkuByIds(ids);
   }
 
 
   
   public void deleteByIds(int[] ids) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).deleteByIds(ids);
   }
 
 
   
   public void updateSto1(List<Integer> orderOutIds) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateSto1(orderOutIds);
   }
 
 
   
   public void updateWeight(int id, String format, String format2) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateWeight(id, format, format2);
   }
 
 
   
   public List<OrderOutForExport> findExportByIds(int[] ids) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findExportByIds(ids);
   }
 
   
   public List<OrderOutForExport> findExport(OrderOutQueryVo orderOutQueryVo) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findExport(orderOutQueryVo);
   }
 
   
   public List<OrderOut> findByTotalWayBill(String totalWayBill) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findByTotalWayBill(totalWayBill);
   }
 
   
   public List<OrderOutSku> findSkuByIdS(int[] id) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findSkuByIdS(id);
   }
 
   
   public List<OrderOut> findByiIdsList(List<Integer> ids) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findByiIdsList(ids);
   }
 
   
   public List<OrderOutSku> findSkuByIdSList(List<Integer> ids) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).findSkuByIdSList(ids);
   }
 
 
 
   
   public void updateDD(List<OrderOut> orderList) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateDD(orderList);
   }
 
   
   public void updateQD(List<OrderOut> orderList) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateQD(orderList);
   }
 
   
   public void updateZF(List<OrderOut> orderList) {
     ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).updateZF(orderList);
   }
 
   
   public List<Map<String, Object>> jxsh(String id) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).jxsh(id);
   }
 
 
 
   
   public Integer sumByWayBillNo(String waybillno) {
     return ((OrderOutDao)this.sqlSessionTemplate.getMapper(OrderOutDao.class)).sumByWayBillNo(waybillno);
   }
 }


