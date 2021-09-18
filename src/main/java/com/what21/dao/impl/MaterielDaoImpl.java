 package com.what21.dao.impl;
 
 import com.what21.dao.MaterielDao;
 import com.what21.model.Materiel;
 import com.what21.model.OrderCommoditie;
 import java.util.List;
 import java.util.Map;
 import org.apache.ibatis.annotations.Param;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class MaterielDaoImpl
   implements MaterielDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
   
   public List<Materiel> findAll(Map<String, Object> pageMap) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).findAll(pageMap);
   }
 
   
   public int Mateadd(Materiel m) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).Mateadd(m);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).count(pageMap);
   }
 
   
   public int Matedelete(String id) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).Matedelete(id);
   }
 
   
   public List<Materiel> mTime(Map<String, Object> pageMap) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).mTime(pageMap);
   }
 
   
   public List<Materiel> inquiry(Map<String, Object> pageMap) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).inquiry(pageMap);
   }
 
   
   public List<Materiel> validate(Map<String, Object> map) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).validate(map);
   }
 
   
   public List<Materiel> port(Map<String, Object> pageMap) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).port(pageMap);
   }
 
   
   public List<Materiel> findByIdMateriel(Integer id) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).findByIdMateriel(id);
   }
 
   
   public void insertOrderCommoditie(@Param("list") List<OrderCommoditie> list) {
     ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).insertOrderCommoditie(list);
   }
 
   
   public List<OrderCommoditie> findInventory() {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).findInventory();
   }
 
   
   public int updateInventory(OrderCommoditie orderCommoditie) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).updateInventory(orderCommoditie);
   }
 
   
   public int updateTotality(Materiel materiel) {
     return ((MaterielDao)this.sqlSessionTemplate.getMapper(MaterielDao.class)).updateTotality(materiel);
   }
 }


