package com.what21.dao;

import com.what21.model.OrderBondedRule;
import com.what21.model.OrderBondedRuleQueryVo;
import java.util.List;

public interface OrderBondedRuleDao {
  List<OrderBondedRule> findAll(OrderBondedRuleQueryVo paramOrderBondedRuleQueryVo);
  
  Integer countAll(OrderBondedRuleQueryVo paramOrderBondedRuleQueryVo);
  
  OrderBondedRule findByProvinceAndCarrier(OrderBondedRule paramOrderBondedRule);
  
  int insert(OrderBondedRule paramOrderBondedRule);
  
  void deleteByIds(int[] paramArrayOfint);
  
  int update(OrderBondedRule paramOrderBondedRule);
  
  OrderBondedRule findById(Integer paramInteger);
  
  void insertBatch(List<OrderBondedRule> paramList);
}


