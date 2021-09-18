 package com.what21.dao.impl;
 
 import com.what21.dao.ActualreceiveDao;
 import com.what21.model.Actualreceive;
 import com.what21.model.ActualreceiveCustom;
 import com.what21.model.ActualreceiveQueryVo;
 import com.what21.model.ActualreceiveSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class ActualreceiveDaoImpl
   implements ActualreceiveDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<Actualreceive> findAll(Map<String, Object> pageMap) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).count(pageMap);
   }
 
   
   public int actdelete(String id) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).actdelete(id);
   }
 
   
   public List<Actualreceive> findact(Map<String, Object> pageMap) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).findact(pageMap);
   }
 
 
   
   public Actualreceive findByA(String id) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).findByA(id);
   }
 
 
   
   public void inserta(Actualreceive actualreceive) {
     ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).inserta(actualreceive);
   }
 
 
   
   public int actts(String ids) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).actts(ids);
   }
   
   public int aflag(Actualreceive actualreceive) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).aflag(actualreceive);
   }
 
 
   
   public List<Actualreceive> findacId(String jobFormId) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).findacId(jobFormId);
   }
 
 
   
   public List<Actualreceive> actadd(Map<String, Object> map) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).actadd(map);
   }
 
 
 
   
   public List<ActualreceiveSku> findAllSku(Map<String, Object> pageMap) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).findAllSku(pageMap);
   }
 
 
 
   
   public int countSku(Map<String, Object> pageMap) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).count(pageMap);
   }
 
 
   
   public void aInserta(List<Actualreceive> list) {
     ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).aInserta(list);
   }
 
 
   
   public int count2(ActualreceiveQueryVo actualreceiveQueryVo) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).count2(actualreceiveQueryVo);
   }
 
 
   
   public List<ActualreceiveCustom> findAll2(ActualreceiveQueryVo actualreceiveQueryVo) {
     return ((ActualreceiveDao)this.sqlSessionTemplate.getMapper(ActualreceiveDao.class)).findAll2(actualreceiveQueryVo);
   }
 }


