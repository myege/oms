 package com.what21.dao.impl;
 
 import com.what21.dao.OrderBondDao;
 import com.what21.model.OrderBond;
 import com.what21.model.OrderBondSku;
 import java.util.List;
 import java.util.Map;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class OrderBondDaoImpl
   implements OrderBondDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public int save(OrderBond t) {
     return this.sqlSessionTemplate.insert(String.valueOf(OrderBond.class.getName()) + ".save", t);
   }
   
   public int delete(int id) {
     return this.sqlSessionTemplate.delete(String.valueOf(OrderBond.class.getName()) + ".delete", Integer.valueOf(id));
   }
 
   
   public List<OrderBond> findAll(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(OrderBond.class.getName()) + ".findAll", map);
   }
   
   public Integer count(Map<String, Object> map) {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(OrderBond.class.getName()) + ".count", map);
   }
   
   public OrderBond findObjectById(String id) {
     return (OrderBond)this.sqlSessionTemplate.selectOne(String.valueOf(OrderBond.class.getName()) + ".findObjectById", id);
   }
   
   public int updete(OrderBond orderBond) {
     return this.sqlSessionTemplate.update(String.valueOf(OrderBond.class.getName()) + ".update", orderBond);
   }
 
   
   public List<OrderBondSku> findBm(Map<String, Object> map) {
     return this.sqlSessionTemplate.selectList(String.valueOf(OrderBondSku.class.getName()) + ".findBm", map);
   }
   
   public int countBm(Map<String, Object> map) {
     return ((Integer)this.sqlSessionTemplate.selectOne(String.valueOf(OrderBondSku.class.getName()) + ".countBm", map)).intValue();
   }
   
   public int saveBm(OrderBondSku orderBond) {
     return this.sqlSessionTemplate.insert(String.valueOf(OrderBondSku.class.getName()) + ".saveBm", orderBond);
   }
 
   
   public String findManey(String sre) {
     return (String)this.sqlSessionTemplate.selectOne(String.valueOf(OrderBond.class.getName()) + ".findManey", sre);
   }
 
   
   public OrderBond findCom(String srt) {
     return (OrderBond)this.sqlSessionTemplate.selectOne(String.valueOf(OrderBond.class.getName()) + ".findCom", srt);
   }
 
   
   public void updateSurplus(OrderBond orderBond) {
     this.sqlSessionTemplate.update(String.valueOf(OrderBond.class.getName()) + ".updateSurplus", orderBond);
   }
 
   
   public int frostAndF(OrderBond orderBond) {
     return this.sqlSessionTemplate.update(String.valueOf(OrderBond.class.getName()) + ".frostAndF", orderBond);
   }
 
   
   public int deleteBm(String companybm) {
     return this.sqlSessionTemplate.delete(String.valueOf(OrderBondSku.class.getName()) + ".deleteBm", companybm);
   }
 
   
   public OrderBondSku findMailNo(String mailNo) {
     return (OrderBondSku)this.sqlSessionTemplate.selectOne(String.valueOf(OrderBondSku.class.getName()) + ".findMailNo", mailNo);
   }
 
   
   public OrderBondSku findSku(String txLogisticID) {
     return (OrderBondSku)this.sqlSessionTemplate.selectOne(String.valueOf(OrderBond.class.getName()) + ".findSku", txLogisticID);
   }
 
 
   
   public void deleteSku(int id) {
     this.sqlSessionTemplate.selectOne(String.valueOf(OrderBond.class.getName()) + ".deleteSku", Integer.valueOf(id));
   }
 
 
 
   
   public List<Map> newordercx(String id) {
     return this.sqlSessionTemplate.selectList(String.valueOf(OrderBond.class.getName()) + ".newordercx", id);
   }
 
 
   
   public int updateddzt(String id) {
     return this.sqlSessionTemplate.update(String.valueOf(OrderBond.class.getName()) + ".updateddzt", id);
   }
 
 
   
   public int updateddzt_1(String id) {
     return this.sqlSessionTemplate.update(String.valueOf(OrderBond.class.getName()) + ".updateddzt_1", id);
   }
 }


