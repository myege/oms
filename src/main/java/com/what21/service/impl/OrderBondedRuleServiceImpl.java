 package com.what21.service.impl;
 
 import com.what21.dao.OrderBondedRuleDao;
 import com.what21.model.OrderBondedRule;
 import com.what21.model.OrderBondedRuleQueryVo;
 import com.what21.result.ResultInfo;
 import com.what21.service.OrderBondedRuleService;
 import com.what21.util.GeneralResult;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 
 @Transactional
 @Service
 public class OrderBondedRuleServiceImpl
   implements OrderBondedRuleService
 {
   @Autowired
   private OrderBondedRuleDao orderBondedRuleDao;
   
   public List<OrderBondedRule> findAll(OrderBondedRuleQueryVo orderBondedRuleQueryVo) {
     return this.orderBondedRuleDao.findAll(orderBondedRuleQueryVo);
   }
 
   
   public Integer countAll(OrderBondedRuleQueryVo orderBondedRuleQueryVo) {
     return this.orderBondedRuleDao.countAll(orderBondedRuleQueryVo);
   }
 
 
   
   public int insert(OrderBondedRule orderBondedRule) throws Exception {
     return this.orderBondedRuleDao.insert(orderBondedRule);
   }
 
 
   
   public ResultInfo deleteByIds(int[] ids) {
     this.orderBondedRuleDao.deleteByIds(ids);
     return new ResultInfo(1, "删除<font color='red'>" + ids.length + "</font>条成功！");
   }
 
 
 
   
   public int update(OrderBondedRule orderBondedRule) throws Exception {
     return this.orderBondedRuleDao.update(orderBondedRule);
   }
 
 
   
   public OrderBondedRule findById(Integer id) {
     return this.orderBondedRuleDao.findById(id);
   }
 
 
   
   public GeneralResult importOrderNew(Integer id, String string) {
     return null;
   }
 }


