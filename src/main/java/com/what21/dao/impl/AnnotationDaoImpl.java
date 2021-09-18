 package com.what21.dao.impl;
 
 import com.what21.dao.AnnotationDao;
 import com.what21.model.Annotation;
 import com.what21.model.AnnotationCustom;
 import com.what21.model.AnnotationQueryVo;
 import com.what21.model.AnnotationSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class AnnotationDaoImpl
   implements AnnotationDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<Annotation> findAll(Map<String, Object> pageMap) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).count(pageMap);
   }
 
   
   public int actdelete(String id) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).actdelete(id);
   }
 
   
   public List<Annotation> findact(Map<String, Object> pageMap) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).findact(pageMap);
   }
 
 
   
   public Annotation findByA(String id) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).findByA(id);
   }
 
 
   
   public void inserta(Annotation annotation) {
     ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).inserta(annotation);
   }
 
 
   
   public int actts(String ids) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).actts(ids);
   }
   
   public int aflag(Annotation annotation) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).aflag(annotation);
   }
 
 
   
   public List<Annotation> findacId(String jobFormId) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).findacId(jobFormId);
   }
 
 
   
   public List<Annotation> actadd(Map<String, Object> map) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).actadd(map);
   }
 
 
 
   
   public List<AnnotationSku> findAllSku(Map<String, Object> pageMap) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).findAllSku(pageMap);
   }
 
 
 
   
   public int countSku(Map<String, Object> pageMap) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).count(pageMap);
   }
 
 
   
   public void aInserta(List<Annotation> list) {
     ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).aInserta(list);
   }
 
 
   
   public int count2(AnnotationQueryVo annotationQueryVo) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).count2(annotationQueryVo);
   }
 
 
   
   public List<AnnotationCustom> findAll2(AnnotationQueryVo annotationQueryVo) {
     return ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).findAll2(annotationQueryVo);
   }
 
 
   
   public void insert(Annotation annotation) {
     ((AnnotationDao)this.sqlSessionTemplate.getMapper(AnnotationDao.class)).insert(annotation);
   }
 }


