 package com.what21.dao.impl;
 
 import com.what21.dao.InoutForztDao;
 import com.what21.model.Inout;
 import com.what21.model.InoutQueryVo;
 import com.what21.model.WmsInoutForzt;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class InoutForztDaoImpl implements InoutForztDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).count(pageMap);
   }
 
 
   
   public List<WmsInoutForzt> findAll(Map<String, Object> pageMap) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).findAll(pageMap);
   }
 
 
   
   public List<InoutQueryVo> selectByMan(String manualid, String sourceno) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).selectByMan(manualid, sourceno);
   }
 
 
   
   public List<Inout> selectBySou(String manualid, String sourceno) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).selectBySou(manualid, sourceno);
   }
 
 
   
   public int insert(WmsInoutForzt wmsInoutForzt) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).insert(wmsInoutForzt);
   }
 
 
   
   public int updatezc() {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).updatezc();
   }
 
 
   
   public int updateforzt(WmsInoutForzt wmsInoutForzt) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).updateforzt(wmsInoutForzt);
   }
 
 
   
   public List<Inout> findSku(Map<String, Object> pageMap) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).findSku(pageMap);
   }
 
 
   
   public List<Inout> findByIds(String ids) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).findByIds(ids);
   }
 
 
   
   public List<Inout> findSkuByMs(String exmanualid, String exsourceno) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).findSkuByMs(exmanualid, exsourceno);
   }
 
 
   
   public List<WmsInoutForzt> findByIdsu(String idsu) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).findByIdsu(idsu);
   }
 
 
   
   public int countByMs(String manualid, String sourceno) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).countByMs(manualid, sourceno);
   }
 
 
   
   public List<InoutQueryVo> pullAll() {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).pullAll();
   }
 
 
   
   public List<WmsInoutForzt> findByMan(String manualId, String sourceNo) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).findByMan(manualId, sourceNo);
   }
 
 
   
   public List<Inout> selectByManSou() {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).selectByManSou();
   }
 
 
   
   public int insertMap(Map<String, WmsInoutForzt> map) {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).insertMap(map);
   }
 
 
   
   public int updateAll() {
     return ((InoutForztDao)this.sqlSessionTemplate.getMapper(InoutForztDao.class)).updateAll();
   }
 }


