 package com.what21.dao.impl;
 
 import com.what21.dao.AutoCheckListDao;
 import com.what21.model.AutoCheckList;
 import com.what21.model.AutoCheckListExtend;
 import com.what21.model.AutoCheckListVo;
 import com.what21.model.OrderMail;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class AutoCheckListDaoImpl
   implements AutoCheckListDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int count(AutoCheckListVo autoCheckListVo) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).count(autoCheckListVo);
   }
 
   
   public List<AutoCheckList> findAll(AutoCheckListVo autoCheckListVo) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).findAll(autoCheckListVo);
   }
 
   
   public int save(AutoCheckList autoCheckList) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).save(autoCheckList);
   }
 
   
   public int updateById(AutoCheckList autoCheckList) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).updateById(autoCheckList);
   }
 
   
   public int deleteCompanyByIds(String[] ids) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).deleteCompanyByIds(ids);
   }
 
   
   public AutoCheckList findById(Integer id) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).findById(id);
   }
 
   
   public List<AutoCheckListExtend> findOrderByAuditstatus(String auditstatus, String plan) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).findOrderByAuditstatus(auditstatus, plan);
   }
 
   
   public List<OrderMail> findOrderById(String plan) {
     return ((AutoCheckListDao)this.sqlSessionTemplate.getMapper(AutoCheckListDao.class)).findOrderById(plan);
   }
 }


