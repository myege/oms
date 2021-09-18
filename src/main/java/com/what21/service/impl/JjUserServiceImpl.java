 package com.what21.service.impl;
 
 import com.what21.dao.JjUserDao;
 import com.what21.model.JjUser;
 import com.what21.model.JjUserVo;
 import com.what21.service.JjUserService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class JjUserServiceImpl
   implements JjUserService
 {
   @Autowired
   private JjUserDao jjUserDao;
   
   public int count(JjUserVo jjUserVo) {
     return this.jjUserDao.count(jjUserVo);
   }
 
 
   
   public List<JjUser> findAll(JjUserVo jjUserVo) {
     return this.jjUserDao.findAll(jjUserVo);
   }
 
 
   
   public JjUser findById(Integer id) {
     return this.jjUserDao.findById(id);
   }
 
 
   
   public int save(JjUser jjUser) {
     return this.jjUserDao.save(jjUser);
   }
 
 
   
   public int updateById(JjUser jjUser) {
     return this.jjUserDao.updateById(jjUser);
   }
 
 
   
   public int deleteByIds(String[] ids) {
     return this.jjUserDao.deleteByIds(ids);
   }
 
 
   
   public List<JjUser> findJjUser() {
     return this.jjUserDao.findJjUser();
   }
 }


