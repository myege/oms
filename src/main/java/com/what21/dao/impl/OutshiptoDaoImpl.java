 package com.what21.dao.impl;
 
 import com.what21.dao.OutshiptoDao;
 import com.what21.model.Outshipto;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class OutshiptoDaoImpl
   implements OutshiptoDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<Outshipto> findAll(Outshipto vo) {
     return this.sqlSessionTemplate.selectList(String.valueOf(Outshipto.class.getName()) + ".findAll", vo);
   }
 
   
   public List<Outshipto> findId(String vo) {
     return this.sqlSessionTemplate.selectList(String.valueOf(Outshipto.class.getName()) + ".findId", vo);
   }
 
   
   public int findCount(Outshipto vo) {
     return ((Integer)this.sqlSessionTemplate.selectOne(String.valueOf(Outshipto.class.getName()) + ".findCount", vo)).intValue();
   }
 
   
   public void delete(String ids) {
     this.sqlSessionTemplate.delete(String.valueOf(Outshipto.class.getName()) + ".delete", ids);
   }
 
 
   
   public void deleteSku(String billNo) {
     this.sqlSessionTemplate.delete(String.valueOf(Outshipto.class.getName()) + ".deleteSku", billNo);
   }
 
   
   public void update(Outshipto vo) {
     this.sqlSessionTemplate.update(String.valueOf(Outshipto.class.getName()) + ".updateXqin", vo);
   }
 
 
   
   public void updateType(String vo) {
     this.sqlSessionTemplate.update(String.valueOf(Outshipto.class.getName()) + ".updateType", vo);
   }
 
 
   
   public void insert(List<Outshipto> list) {
     this.sqlSessionTemplate.insert(String.valueOf(Outshipto.class.getName()) + ".insertShiPto", list);
   }
 
 
   
   public List<String> findBillNo(Map<String, String> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(Outshipto.class.getName()) + ".findBillNo", map);
   }
 
   
   public List<String> findLogisticsNo(Map<String, String> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(Outshipto.class.getName()) + ".findLogisticsNo", map);
   }
 
   
   public void huizUpdate(Outshipto outshipto) {
     this.sqlSessionTemplate.update(String.valueOf(Outshipto.class.getName()) + ".huizUpdate", outshipto);
   }
 }


