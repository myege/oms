 package com.what21.dao.impl;
 
 import com.what21.dao.ItemDao;
 import com.what21.dao.SzqgOrderDao;
 import com.what21.model.Item;
 import com.what21.model.SzqgOrder;
 import com.what21.model.SzqgOrderExpot;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class SzqgOrderDaoImpl
   implements SzqgOrderDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<SzqgOrder> findAll(Map<String, Object> map) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).findAll(map);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).count(map);
   }
 
   
   public List<Item> findId(Map<String, Object> map) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findId(map);
   }
 
   
   public List<Item> dateTime(Map<String, Object> map) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).dateTime(map);
   }
 
   
   public int deleteItem(String id) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).deleteItem(id);
   }
 
   
   public int insert(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).insert(item);
   }
 
   
   public List<SzqgOrder> findBySku(SzqgOrder i) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).findBySku(i);
   }
 
   
   public int updateItme(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateItme(item);
   }
 
   
   public void insertBatch(List<Item> list) {
     ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).insertBatch(list);
   }
 
 
   
   public List<Item> finImpWms() {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).finImpWms();
   }
 
   
   public List<Item> findArray(String[] dis) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findArray(dis);
   }
 
   
   public void updateWmsType(List<Map<String, String>> i) {
     ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateWmsType(i);
   }
 
 
   
   public Item findById(String id) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findById(id);
   }
 
 
   
   public int updateById(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateById(item);
   }
 
 
   
   public int save(Item item) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).save(item);
   }
   
   public List<Item> findgjz(String gzj) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findgjz(gzj);
   }
   
   public List<Item> findhzqd(String gzj) {
     return ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).findhzqd(gzj);
   }
 
   
   public void updateWmsType2(int gjz) {
     ((ItemDao)this.sqlSessionTemplate.getMapper(ItemDao.class)).updateWmsType2(gjz);
   }
   
   public SzqgOrder findByIdToBG(Integer id) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).findByIdToBG(id);
   }
   
   public SzqgOrder findByOrderNo(String OrderNo) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).findByOrderNo(OrderNo);
   }
   
   public void updatebygjz(String sellerRecord, String notes, String status) {
     ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).updatebygjz(sellerRecord, notes, status);
   }
 
 
   
   public void updateAtB(String A, String B) {
     ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).updateAtB(A, B);
   }
 
   
   public int batchInsert(List<SzqgOrder> list) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).batchInsert(list);
   }
 
   
   public int updateAuditstatus3(SzqgOrder szqgOrder) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).updateAuditstatus3(szqgOrder);
   }
 
   
   public int updateYdzt(SzqgOrder szqgOrder) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).updateYdzt(szqgOrder);
   }
   
   public List<SzqgOrderExpot> exportszqg(SzqgOrderExpot szqgOrderExpot) {
     return ((SzqgOrderDao)this.sqlSessionTemplate.getMapper(SzqgOrderDao.class)).exportszqg(szqgOrderExpot);
   }
 }


