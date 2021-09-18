package com.what21.dao;

import com.what21.model.OrderOutLog;

public interface OrderOutLogDao {
  void insert(OrderOutLog paramOrderOutLog);
  
  OrderOutLog findLogByWaybillno(String paramString);
}


