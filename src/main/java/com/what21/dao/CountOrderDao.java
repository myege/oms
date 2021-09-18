package com.what21.dao;

import com.what21.model.TCountorder;
import java.util.List;

public interface CountOrderDao {
  List<TCountorder> findAll(TCountorder paramTCountorder) throws Exception;
  
  Integer count(TCountorder paramTCountorder) throws Exception;
  
  Integer add(TCountorder paramTCountorder) throws Exception;
  
  TCountorder findMailNo(String paramString) throws Exception;
  
  Integer updateState(List<TCountorder> paramList) throws Exception;
  
  Integer delete(String[] paramArrayOfString) throws Exception;
  
  List<TCountorder> findMailNoOne(String paramString) throws Exception;
}


