 package com.what21.dao.impl;
 
 import com.what21.dao.OrderOutRuleDao;
 import com.what21.model.OrderOutRule;
 import com.what21.model.OrderOutRuleQueryVo;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class OrderOutRuleDaoImpl
   implements OrderOutRuleDao {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public List<OrderOutRule> find(OrderOutRuleQueryVo orderOutRuleQueryVo) {
     return ((OrderOutRuleDao)this.sqlSessionTemplate.getMapper(OrderOutRuleDao.class)).find(orderOutRuleQueryVo);
   }
 
   
   public Integer count(OrderOutRuleQueryVo orderOutRuleQueryVo) {
     return ((OrderOutRuleDao)this.sqlSessionTemplate.getMapper(OrderOutRuleDao.class)).count(orderOutRuleQueryVo);
   }
 
   
   public void insert(OrderOutRule orderOutRule) {
     ((OrderOutRuleDao)this.sqlSessionTemplate.getMapper(OrderOutRuleDao.class)).insert(orderOutRule);
   }
 
 
   
   public void update(OrderOutRule orderOutRule) {
     ((OrderOutRuleDao)this.sqlSessionTemplate.getMapper(OrderOutRuleDao.class)).update(orderOutRule);
   }
 
 
   
   public void deleteByIds(Integer[] ids) {
     ((OrderOutRuleDao)this.sqlSessionTemplate.getMapper(OrderOutRuleDao.class)).deleteByIds(ids);
   }
 
 
   
   public List<OrderOutRule> findByGoodsname(String goodsname) {
     return ((OrderOutRuleDao)this.sqlSessionTemplate.getMapper(OrderOutRuleDao.class)).findByGoodsname(goodsname);
   }
 
   
   public OrderOutRule findById(Integer id) {
     return ((OrderOutRuleDao)this.sqlSessionTemplate.getMapper(OrderOutRuleDao.class)).findById(id);
   }
 }


