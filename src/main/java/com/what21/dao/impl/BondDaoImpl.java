 package com.what21.dao.impl;
 
 import com.what21.dao.BondDao;
 import com.what21.model.Bond;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class BondDaoImpl
   implements BondDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
   public List<Bond> findAll(Map<String, Object> paramMap) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).findAll(paramMap);
   }
 
   
   public int count(Map<String, Object> map) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).count(map);
   }
   
   public List<Bond> findBond() {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).findBond();
   }
   
   public Bond getTax(Bond bond) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).getTax(bond);
   }
 
   
   public List<Bond> findbond(Map<String, Object> pageMap) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).findbond(pageMap);
   }
   
   public int bonddelete(String id) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).bonddelete(id);
   }
   
   public int bondedit(Bond bond) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).bondedit(bond);
   }
   
   public int addbond(Bond bond) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).addbond(bond);
   }
   
   public int addlogf(Bond bond) {
     return ((BondDao)this.sqlSessionTemplate.getMapper(BondDao.class)).addlogf(bond);
   }
 }


