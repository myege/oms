 package com.what21.dao.impl;
 
 import com.what21.dao.AnnotationSkuDao;
 import com.what21.model.AnnotationSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class AnnotationSkuDaoImpl
   implements AnnotationSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).count(pageMap);
   }
 
 
   
   public List<AnnotationSku> findAll(Map<String, Object> pageMap) {
     return ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).findAll(pageMap);
   }
 
 
   
   public List<AnnotationSku> findact(Map<String, Object> pageMap) {
     return ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).findact(pageMap);
   }
 
 
 
   
   public void insert(AnnotationSku annotationSku) {
     ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).insert(annotationSku);
   }
 
 
 
   
   public AnnotationSku findByA(String id) {
     return ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).findByA(id);
   }
 
 
   
   public int actdelete(String id) {
     return ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).actdelete(id);
   }
 
 
   
   public void aflag(AnnotationSku annotationSku) {
     ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).aflag(annotationSku);
   }
 
 
   
   public void asInsert(Object[] ob) {
     ((AnnotationSkuDao)this.sqlSessionTemplate.getMapper(AnnotationSkuDao.class)).asInsert(ob);
   }
 }


