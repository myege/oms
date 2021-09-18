package com.what21.service;

import com.what21.model.OrderBondedRule;
import com.what21.model.OrderBondedRuleQueryVo;
import com.what21.result.ResultInfo;
import com.what21.util.GeneralResult;
import java.util.List;

public interface OrderBondedRuleService {
  List<OrderBondedRule> findAll(OrderBondedRuleQueryVo paramOrderBondedRuleQueryVo);
  
  Integer countAll(OrderBondedRuleQueryVo paramOrderBondedRuleQueryVo);
  
  int insert(OrderBondedRule paramOrderBondedRule) throws Exception;
  
  ResultInfo deleteByIds(int[] paramArrayOfint);
  
  int update(OrderBondedRule paramOrderBondedRule) throws Exception;
  
  OrderBondedRule findById(Integer paramInteger);
  
  GeneralResult importOrderNew(Integer paramInteger, String paramString);
}


