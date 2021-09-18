 package com.what21.dao.impl;
 
 import com.what21.dao.PlaningReceiptsDao;
 import com.what21.model.PlaningReceipts;
 import com.what21.model.PlaningReceiptsQueryVo;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class PlaningReceiptsDaoImpl
   implements PlaningReceiptsDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public List<PlaningReceipts> findAll(Map<String, Object> map) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).findAll(map);
   }
 
 
   
   public int count(Map<String, Object> map) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).count(map);
   }
 
 
   
   public int delete(String planingReceiptsId) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).delete(planingReceiptsId);
   }
 
 
   
   public List<PlaningReceipts> pr(Map<String, Object> map) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).pr(map);
   }
 
 
   
   public int insert(PlaningReceipts planingReceipts) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).insert(planingReceipts);
   }
 
 
   
   public PlaningReceipts findByPlaningReceiptsId(String planingReceiptsId) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).findByPlaningReceiptsId(planingReceiptsId);
   }
 
 
   
   public int pushPr(PlaningReceipts planingReceipts) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).pushPr(planingReceipts);
   }
 
 
   
   public PlaningReceipts findByIdToPR(Integer id) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).findByIdToPR(id);
   }
 
 
   
   public int insertFlag(PlaningReceipts planingReceipts) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).insertFlag(planingReceipts);
   }
 
   
   public int count2(PlaningReceiptsQueryVo planingReceiptsQueryVo) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).count2(planingReceiptsQueryVo);
   }
 
   
   public List<PlaningReceipts> findAll2(PlaningReceiptsQueryVo planingReceiptsQueryVo) {
     return ((PlaningReceiptsDao)this.sqlSessionTemplate.getMapper(PlaningReceiptsDao.class)).findAll2(planingReceiptsQueryVo);
   }
 }


