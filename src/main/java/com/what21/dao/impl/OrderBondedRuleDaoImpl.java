 package com.what21.dao.impl;
 
 import com.what21.dao.OrderBondedRuleDao;
 import com.what21.model.OrderBondedRule;
 import com.what21.model.OrderBondedRuleQueryVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class OrderBondedRuleDaoImpl
   implements OrderBondedRuleDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public OrderBondedRule findByProvinceAndCarrier(OrderBondedRule orderBondedRule) {
     return ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).findByProvinceAndCarrier(orderBondedRule);
   }
 
   
   public List<OrderBondedRule> findAll(OrderBondedRuleQueryVo orderBondedRuleQueryVo) {
     return ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).findAll(orderBondedRuleQueryVo);
   }
 
   
   public Integer countAll(OrderBondedRuleQueryVo orderBondedRuleQueryVo) {
     return ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).countAll(orderBondedRuleQueryVo);
   }
 
   
   public int insert(OrderBondedRule orderBondedRule) {
     return ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).insert(orderBondedRule);
   }
 
 
   
   public void deleteByIds(int[] ids) {
     ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).deleteByIds(ids);
   }
 
 
   
   public int update(OrderBondedRule orderBondedRule) {
     return ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).update(orderBondedRule);
   }
 
 
   
   public OrderBondedRule findById(Integer id) {
     return ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).findById(id);
   }
 
   
   public void insertBatch(List<OrderBondedRule> list) {
     ((OrderBondedRuleDao)this.sqlSessionTemplate.getMapper(OrderBondedRuleDao.class)).insertBatch(list);
   }
 }


