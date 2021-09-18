 package com.what21.dao.impl;
 
 import com.what21.dao.OrderOutBSDao;
 import com.what21.model.OrderOutBS;
 import com.what21.model.OrderOutForExportBS;
 import com.what21.model.OrderOutQueryVoBS;
 import com.what21.model.OrderOutSkuBS;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class OrderOutBSDaoImpl
   implements OrderOutBSDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderOutBS> find(OrderOutQueryVoBS orderOutQueryVo) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).find(orderOutQueryVo);
   }
 
   
   public Integer count(OrderOutQueryVoBS orderOutQueryVo) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).count(orderOutQueryVo);
   }
 
   
   public void insert(OrderOutBS orderOut) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).insert(orderOut);
   }
 
 
   
   public OrderOutBS findById(Integer id) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findById(id);
   }
 
   
   public void deleteById(Integer id) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).deleteById(id);
   }
 
 
   
   public void update(OrderOutBS orderOut) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).update(orderOut);
   }
 
 
   
   public List<OrderOutSkuBS> findSku(OrderOutQueryVoBS orderOutQueryVo) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findSku(orderOutQueryVo);
   }
 
   
   public Integer countSku(OrderOutQueryVoBS orderOutQueryVo) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).countSku(orderOutQueryVo);
   }
 
   
   public void insertSku(OrderOutSkuBS orderOutSku) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).insertSku(orderOutSku);
   }
 
 
   
   public void deleteByOrderno(String orderno) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).deleteByOrderno(orderno);
   }
 
 
   
   public OrderOutBS findByOrderno(String orderno) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findByOrderno(orderno);
   }
 
   
   public List<OrderOutBS> findByiIds(int[] ids) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findByiIds(ids);
   }
 
   
   public void updateStatus1(int id) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateStatus1(id);
   }
 
   
   public String findSumPriceByOrderno(String orderno) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findSumPriceByOrderno(orderno);
   }
 
   
   public void updateSumPriceByOrderno(List<OrderOutBS> list) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateSumPriceByOrderno(list);
   }
 
 
   
   public Integer findNumByOrderno(String orderno) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findNumByOrderno(orderno);
   }
 
   
   public List<OrderOutSkuBS> findSkusByOrderno(String orderno) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findSkusByOrderno(orderno);
   }
 
   
   public void insertBatch(List<OrderOutBS> list) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).insertBatch(list);
   }
 
 
   
   public void insertSkuBatch(List<OrderOutSkuBS> list2) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).insertSkuBatch(list2);
   }
 
 
   
   public Integer sumByOrderno(String orderno) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).sumByOrderno(orderno);
   }
 
   
   public void updateEnd(int[] ids) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateEnd(ids);
   }
 
 
   
   public OrderOutSkuBS findSkuById(Integer id) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findSkuById(id);
   }
 
   
   public void updateSku(OrderOutSkuBS sku) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateSku(sku);
   }
 
 
   
   public String findCompanycodeByUserid(Integer userid) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findCompanycodeByUserid(userid);
   }
 
   
   public void updateStatus(int status, String wayBill) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateStatus(status, wayBill);
   }
 
 
   
   public void updateGoodsno(String goodsno1, String goodsno2) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateGoodsno(goodsno1, goodsno2);
   }
 
 
   
   public Integer countSkuByGoodsno(String goodsno1) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).countSkuByGoodsno(goodsno1);
   }
 
   
   public void deleteSkuByIds(int[] ids) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).deleteSkuByIds(ids);
   }
 
 
   
   public void deleteByIds(int[] ids) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).deleteByIds(ids);
   }
 
 
   
   public void updateSto1(List<Integer> orderOutIds) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateSto1(orderOutIds);
   }
 
 
   
   public void updateWeight(int id, String format, String format2) {
     ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).updateWeight(id, format, format2);
   }
 
 
   
   public List<OrderOutForExportBS> findExportByIds(int[] ids) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findExportByIds(ids);
   }
 
   
   public List<OrderOutForExportBS> findExport(OrderOutQueryVoBS orderOutQueryVo) {
     return ((OrderOutBSDao)this.sqlSessionTemplate.getMapper(OrderOutBSDao.class)).findExport(orderOutQueryVo);
   }
 }


