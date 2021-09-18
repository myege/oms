 package com.what21.dao.impl;
 
 import com.what21.dao.BeLzdOutDao;
 import com.what21.model.BeLzdOut;
 import com.what21.model.BeLzdOutCustom;
 import com.what21.model.BeLzdOutQueryVo;
 import com.what21.model.CountBeLzdOut;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class BeLzdOutDaoImpl
   implements BeLzdOutDao
 {
   @Autowired
   private SqlSessionTemplate sqSessionTemplate;
   
   public List<BeLzdOut> findAll(BeLzdOutQueryVo beLzdQueryVo) throws Exception {
     return ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).findAll(beLzdQueryVo);
   }
 
   
   public int countAll(BeLzdOutQueryVo beLzdQueryVo) throws Exception {
     return ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).countAll(beLzdQueryVo);
   }
 
   
   public void update(BeLzdOut beLzd) throws Exception {
     ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).update(beLzd);
   }
 
 
   
   public void insert(BeLzdOut beLzd) throws Exception {
     ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).insert(beLzd);
   }
 
 
   
   public BeLzdOut findById(Integer id) throws Exception {
     return ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).findById(id);
   }
 
   
   public void deleteByIds(Integer[] ids) throws Exception {
     ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).deleteByIds(ids);
   }
 
 
   
   public BeLzdOut findByTydh(String tdh) throws Exception {
     return ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).findByTydh(tdh);
   }
 
   
   public CountBeLzdOut count(BeLzdOutCustom beLzdOutCustom) {
     return ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).count(beLzdOutCustom);
   }
 
   
   public void insertBatch(List<BeLzdOut> list) {
     ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).insertBatch(list);
   }
 
 
   
   public List<String> findByTdhs(Object[] tdhs) {
     return ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).findByTdhs(tdhs);
   }
 
 
   
   public List<BeLzdOut> findByIds(int[] ids) {
     return ((BeLzdOutDao)this.sqSessionTemplate.getMapper(BeLzdOutDao.class)).findByIds(ids);
   }
 }


