 package com.what21.dao.impl;
 
 import com.what21.dao.TinventorySkuDao;
 import com.what21.model.TinventorySku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class TinventorySkuDaoImpl
   implements TinventorySkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int addTinSku(TinventorySku tinventorySku) {
     return this.sqlSessionTemplate.insert(String.valueOf(TinventorySku.class.getName()) + ".addTinSku", tinventorySku);
   }
 
   
   public List<TinventorySku> findtinSku(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(TinventorySku.class.getName()) + ".findtinSku", map);
   }
 
   
   public int countSku(Map<String, Object> map) {
     return ((Integer)this.sqlSessionTemplate.selectOne(String.valueOf(TinventorySku.class.getName()) + ".countSku", map)).intValue();
   }
 }


