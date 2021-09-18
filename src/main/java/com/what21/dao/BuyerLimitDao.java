package com.what21.dao;

import com.what21.model.BuyerLimit;

public interface BuyerLimitDao {
  BuyerLimit findByNameAndNameId(String paramString1, String paramString2);
  
  void insert(BuyerLimit paramBuyerLimit);
  
  void update(BuyerLimit paramBuyerLimit);
}


