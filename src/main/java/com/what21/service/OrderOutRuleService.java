package com.what21.service;

import com.what21.model.OrderOutRule;
import com.what21.model.OrderOutRuleQueryVo;
import com.what21.result.ResultInfo;
import java.util.List;

public interface OrderOutRuleService {
  List<OrderOutRule> find(OrderOutRuleQueryVo paramOrderOutRuleQueryVo);
  
  Integer count(OrderOutRuleQueryVo paramOrderOutRuleQueryVo);
  
  ResultInfo insert(OrderOutRule paramOrderOutRule) throws Exception;
  
  ResultInfo deleteByIds(Integer[] paramArrayOfInteger);
  
  ResultInfo update(OrderOutRule paramOrderOutRule) throws Exception;
  
  OrderOutRule findById(Integer paramInteger);
}


