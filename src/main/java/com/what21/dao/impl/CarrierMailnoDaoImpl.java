 package com.what21.dao.impl;
 
 import com.what21.dao.CarrierMailnoDao;
 import com.what21.model.TCarrierMailno;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class CarrierMailnoDaoImpl implements CarrierMailnoDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void insert(TCarrierMailno tCarrierMailno) {
     ((CarrierMailnoDao)this.sqlSessionTemplate.getMapper(CarrierMailnoDao.class)).insert(tCarrierMailno);
   }
 }


