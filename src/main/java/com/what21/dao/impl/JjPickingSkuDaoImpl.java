 package com.what21.dao.impl;
 
 import com.what21.dao.JjPickingSkuDao;
 import com.what21.model.JjPickingCustom;
 import com.what21.model.JjPickingSku;
 import com.what21.model.JjPickingSkuVo;
 import java.util.List;
 import org.apache.ibatis.annotations.Param;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class JjPickingSkuDaoImpl
   implements JjPickingSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int count(JjPickingSkuVo jjPickingSkuVo) {
     return ((JjPickingSkuDao)this.sqlSessionTemplate.getMapper(JjPickingSkuDao.class)).count(jjPickingSkuVo);
   }
 
   
   public List<JjPickingSku> findAll(JjPickingSkuVo jjPickingSkuVo) {
     return ((JjPickingSkuDao)this.sqlSessionTemplate.getMapper(JjPickingSkuDao.class)).findAll(jjPickingSkuVo);
   }
 
   
   public List<JjPickingSku> findBill() {
     return ((JjPickingSkuDao)this.sqlSessionTemplate.getMapper(JjPickingSkuDao.class)).findBill();
   }
 
   
   public void insertBatchSku(List<JjPickingSku> skulist) {
     ((JjPickingSkuDao)this.sqlSessionTemplate.getMapper(JjPickingSkuDao.class)).insertBatchSku(skulist);
   }
 
   
   public void distributeLeafletsSku(@Param("jjPickingCustom") JjPickingCustom jjPickingCustom) {
     ((JjPickingSkuDao)this.sqlSessionTemplate.getMapper(JjPickingSkuDao.class)).distributeLeafletsSku(jjPickingCustom);
   }
 }


