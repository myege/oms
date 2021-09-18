package com.what21.service;

import com.what21.model.TCountorder;
import com.what21.model.TCountsku;
import java.util.List;

public interface CountOrderService {
  List<TCountorder> findAll(TCountorder paramTCountorder) throws Exception;
  
  Integer count(TCountorder paramTCountorder) throws Exception;
  
  Integer add(TCountorder paramTCountorder) throws Exception;
  
  Integer countOrder(String[] paramArrayOfString, String paramString) throws Exception;
  
  TCountsku findMail(String paramString) throws Exception;
  
  Integer delete(String[] paramArrayOfString) throws Exception;
}


