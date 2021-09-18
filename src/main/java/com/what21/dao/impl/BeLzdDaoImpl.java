 package com.what21.dao.impl;
 
 import com.what21.dao.BeLzdDao;
 import com.what21.model.BeLzd;
 import com.what21.model.BeLzdQueryVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeLzdDaoImpl
   implements BeLzdDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<BeLzd> findAll(BeLzdQueryVo beLzdQueryVo) throws Exception {
     return ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).findAll(beLzdQueryVo);
   }
 
   
   public int countAll(BeLzdQueryVo beLzdQueryVo) throws Exception {
     return ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).countAll(beLzdQueryVo);
   }
 
   
   public void update(BeLzd beLzd) throws Exception {
     ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).update(beLzd);
   }
 
 
   
   public void insert(BeLzd beLzd) throws Exception {
     ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).insert(beLzd);
   }
 
 
   
   public BeLzd findById(Integer id) throws Exception {
     return ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).findById(id);
   }
 
   
   public void deleteById(Integer id) throws Exception {
     ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).deleteById(id);
   }
 
 
   
   public String getMaxYwbh(String string) throws Exception {
     return ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).getMaxYwbh(string);
   }
 
   
   public BeLzd findByTydh(String tdh) throws Exception {
     return ((BeLzdDao)this.sqlSessionTemplate.getMapper(BeLzdDao.class)).findByTydh(tdh);
   }
 }


