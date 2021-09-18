 package com.what21.service.impl;
 
 import com.what21.dao.MaterielDao;
 import com.what21.model.Materiel;
 import com.what21.model.OrderCommoditie;
 import com.what21.service.MaterielService;
 import com.zt.kjybd.GetCommoditieFromWms;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class MaterielServiceImpl
   implements MaterielService
 {
   @Autowired
   private MaterielDao materielDao;
   
   public List<Materiel> findAll(Map<String, Object> pageMap) {
     return this.materielDao.findAll(pageMap);
   }
 
   
   public int Mateadd(Materiel m) {
     return this.materielDao.Mateadd(m);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.materielDao.count(pageMap);
   }
 
   
   public int Matedelete(String id) {
     return this.materielDao.Matedelete(id);
   }
 
   
   public List<Materiel> mTime(Map<String, Object> pageMap) {
     return this.materielDao.mTime(pageMap);
   }
 
   
   public List<Materiel> inquiry(Map<String, Object> pageMap) {
     return this.materielDao.inquiry(pageMap);
   }
 
   
   public List<Materiel> validate(Map<String, Object> map) {
     return this.materielDao.validate(map);
   }
 
   
   public List<Materiel> port(Map<String, Object> pageMap) {
     return this.materielDao.port(pageMap);
   }
 
   
   public List<Materiel> findByIdMateriel(Integer id) {
     return this.materielDao.findByIdMateriel(id);
   }
 
   
   public int pullCommotidie() throws Exception {
     List<OrderCommoditie> list = GetCommoditieFromWms.getCommoditie();
     if (list != null && list.size() > 0) {
       this.materielDao.insertOrderCommoditie(list);
       return GetCommoditieFromWms.updateOrderCommoditie(list);
     } 
     return 0;
   }
 
   
   public int updateInventory() throws Exception {
     List<OrderCommoditie> list = this.materielDao.findInventory();
     int result = 0;
     for (OrderCommoditie orderCommoditie : list) {
       this.materielDao.updateInventory(orderCommoditie);
       result++;
     } 
     return result;
   }
 
   
   public int updateTotality(Materiel materiel) {
     return this.materielDao.updateTotality(materiel);
   }
 }


