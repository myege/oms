package com.what21.dao;

import com.what21.model.OrderDs;

public interface OrderDsDao {
  void insert(OrderDs paramOrderDs);
  
  OrderDs findByOrder(String paramString);
}


