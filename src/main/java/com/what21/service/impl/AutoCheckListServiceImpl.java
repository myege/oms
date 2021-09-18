 package com.what21.service.impl;
 
 import com.what21.dao.AutoCheckListDao;
 import com.what21.model.AutoCheckList;
 import com.what21.model.AutoCheckListExtend;
 import com.what21.model.AutoCheckListVo;
 import com.what21.model.OrderMail;
 import com.what21.service.AutoCheckListService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class AutoCheckListServiceImpl
   implements AutoCheckListService
 {
   @Autowired
   private AutoCheckListDao autoCheckListDao;
   
   public int count(AutoCheckListVo autoCheckListVo) {
     return this.autoCheckListDao.count(autoCheckListVo);
   }
 
   
   public List<AutoCheckList> findAll(AutoCheckListVo autoCheckListVo) {
     return this.autoCheckListDao.findAll(autoCheckListVo);
   }
 
   
   public int save(AutoCheckList autoCheckList) {
     return this.autoCheckListDao.save(autoCheckList);
   }
 
   
   public int updateById(AutoCheckList autoCheckList) {
     return this.autoCheckListDao.updateById(autoCheckList);
   }
 
   
   public int deleteCompanyByIds(String[] ids) {
     return this.autoCheckListDao.deleteCompanyByIds(ids);
   }
 
   
   public AutoCheckList findById(Integer id) {
     return this.autoCheckListDao.findById(id);
   }
 
   
   public List<AutoCheckListExtend> findOrderByAuditstatus(String auditstatus, String plan) {
     return this.autoCheckListDao.findOrderByAuditstatus(auditstatus, plan);
   }
 
   
   public List<OrderMail> findOrderById(String plan) {
     return this.autoCheckListDao.findOrderById(plan);
   }
 }


