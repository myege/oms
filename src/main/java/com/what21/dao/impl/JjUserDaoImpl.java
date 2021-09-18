 package com.what21.dao.impl;
 
 import com.what21.dao.JjUserDao;
 import com.what21.model.JjUser;
 import com.what21.model.JjUserVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class JjUserDaoImpl
   implements JjUserDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int count(JjUserVo jjUserVo) {
     return ((JjUserDao)this.sqlSessionTemplate.getMapper(JjUserDao.class)).count(jjUserVo);
   }
 
 
   
   public List<JjUser> findAll(JjUserVo jjUserVo) {
     return ((JjUserDao)this.sqlSessionTemplate.getMapper(JjUserDao.class)).findAll(jjUserVo);
   }
 
 
   
   public JjUser findById(Integer id) {
     return ((JjUserDao)this.sqlSessionTemplate.getMapper(JjUserDao.class)).findById(id);
   }
 
 
   
   public int save(JjUser jjUser) {
     return ((JjUserDao)this.sqlSessionTemplate.getMapper(JjUserDao.class)).save(jjUser);
   }
 
 
   
   public int updateById(JjUser jjUser) {
     return ((JjUserDao)this.sqlSessionTemplate.getMapper(JjUserDao.class)).updateById(jjUser);
   }
 
 
   
   public int deleteByIds(String[] ids) {
     return ((JjUserDao)this.sqlSessionTemplate.getMapper(JjUserDao.class)).deleteByIds(ids);
   }
 
 
   
   public List<JjUser> findJjUser() {
     return ((JjUserDao)this.sqlSessionTemplate.getMapper(JjUserDao.class)).findJjUser();
   }
 }


