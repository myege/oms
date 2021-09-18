 package com.what21.service.impl;
 
 import com.what21.dao.OrderOutRuleDao;
 import com.what21.model.OrderOutRule;
 import com.what21.model.OrderOutRuleQueryVo;
 import com.what21.result.ResultInfo;
 import com.what21.service.OrderOutRuleService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 @Transactional
 @Service
 public class OrderOutRuleServiceImpl
   implements OrderOutRuleService
 {
   @Autowired
   private OrderOutRuleDao orderOutRuleDao;
   
   public List<OrderOutRule> find(OrderOutRuleQueryVo orderOutRuleQueryVo) {
     return this.orderOutRuleDao.find(orderOutRuleQueryVo);
   }
 
   
   public Integer count(OrderOutRuleQueryVo orderOutRuleQueryVo) {
     return this.orderOutRuleDao.count(orderOutRuleQueryVo);
   }
 
   
   public ResultInfo insert(OrderOutRule orderOutRule) throws Exception {
     if (orderOutRule.getGoodsname() == null || orderOutRule.getHscode() == null) {
       return new ResultInfo(0, "操作失败，信息请填写完整！");
     }
     this.orderOutRuleDao.insert(orderOutRule);
     return new ResultInfo(1, "新增成功！");
   }
 
 
   
   public ResultInfo deleteByIds(Integer[] ids) {
     this.orderOutRuleDao.deleteByIds(ids);
     return new ResultInfo(1, "删除<font color='red'>" + ids.length + "</font>条成功！");
   }
 
 
   
   public ResultInfo update(OrderOutRule orderOutRule) throws Exception {
     if (orderOutRule.getGoodsname() == null || orderOutRule.getHscode() == null) {
       return new ResultInfo(0, "操作失败，信息请填写完整！");
     }
     this.orderOutRuleDao.update(orderOutRule);
     return new ResultInfo(1, "修改成功！");
   }
 
 
   
   public OrderOutRule findById(Integer id) {
     return this.orderOutRuleDao.findById(id);
   }
 }


