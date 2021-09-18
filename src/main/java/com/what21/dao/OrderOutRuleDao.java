package com.what21.dao;

import com.what21.model.OrderOutRule;
import com.what21.model.OrderOutRuleQueryVo;
import java.util.List;

public interface OrderOutRuleDao {
  List<OrderOutRule> find(OrderOutRuleQueryVo paramOrderOutRuleQueryVo);
  
  Integer count(OrderOutRuleQueryVo paramOrderOutRuleQueryVo);
  
  void insert(OrderOutRule paramOrderOutRule);
  
  void update(OrderOutRule paramOrderOutRule);
  
  void deleteByIds(Integer[] paramArrayOfInteger);
  
  List<OrderOutRule> findByGoodsname(String paramString);
  
  OrderOutRule findById(Integer paramInteger);
}


