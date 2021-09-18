 package com.what21.dao.impl;
 
 import com.what21.dao.GoodsChangeoddDao;
 import com.what21.model.ChangeOdd;
 import com.what21.model.GoodsChangeodd;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class GoodsChangeoddDaoImpl
   implements GoodsChangeoddDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void insertand(GoodsChangeodd goods) {
     ((GoodsChangeoddDao)this.sqlSessionTemplate.getMapper(GoodsChangeoddDao.class)).insertand(goods);
   }
 
   
   public GoodsChangeodd findByExpressNumber(String expressNumber) {
     return ((GoodsChangeoddDao)this.sqlSessionTemplate.getMapper(GoodsChangeoddDao.class)).findByExpressNumber(expressNumber);
   }
 
   
   public List<GoodsChangeodd> findExpressNumber(String expressNumber) {
     return ((GoodsChangeoddDao)this.sqlSessionTemplate.getMapper(GoodsChangeoddDao.class)).findExpressNumber(expressNumber);
   }
 
   
   public ChangeOdd updatecod(String expressNumber) {
     return ((GoodsChangeoddDao)this.sqlSessionTemplate.getMapper(GoodsChangeoddDao.class)).updatecod(expressNumber);
   }
 
   
   public void deleteByPids(int[] ids) {
     ((GoodsChangeoddDao)this.sqlSessionTemplate.getMapper(GoodsChangeoddDao.class)).deleteByPids(ids);
   }
 
 
 
   
   public void insertAlls(List<GoodsChangeodd> list) {
     ((GoodsChangeoddDao)this.sqlSessionTemplate.getMapper(GoodsChangeoddDao.class)).insertAlls(list);
   }
 }


