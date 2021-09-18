 package com.what21.dao.impl;
 
 import com.what21.dao.BeAccessoryOutDao;
 import com.what21.model.BeAccessoryOut;
 import com.what21.model.BeAccessoryOutQueryVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BeAccessoryOutDaoImpl
   implements BeAccessoryOutDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<BeAccessoryOut> findAll(BeAccessoryOutQueryVo accessoryOutQueryVo) {
     return ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).findAll(accessoryOutQueryVo);
   }
 
   
   public Integer countAll(BeAccessoryOutQueryVo accessoryOutQueryVo) {
     return ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).countAll(accessoryOutQueryVo);
   }
 
   
   public void insert(BeAccessoryOut accessoryOutQueryVo) {
     ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).insert(accessoryOutQueryVo);
   }
 
 
   
   public void deleteByIds(Integer[] ids) {
     ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).deleteByIds(ids);
   }
 
 
   
   public BeAccessoryOut findById(Integer id) {
     return ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).findById(id);
   }
 
   
   public Integer countByItemname(Integer id, String string) {
     return ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).countByItemname(id, string);
   }
 
   
   public void deleteById(int id) {
     ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).deleteById(id);
   }
 
 
   
   public void update(BeAccessoryOut beAccessoryOut) {
     ((BeAccessoryOutDao)this.sqlSessionTemplate.getMapper(BeAccessoryOutDao.class)).update(beAccessoryOut);
   }
 }


