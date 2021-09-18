package com.what21.dao;

import com.what21.model.TCountsku;
import java.util.List;

public interface CountSkuDao {
  Integer addSku(List<TCountsku> paramList) throws Exception;
  
  TCountsku findMailNo(String paramString) throws Exception;
  
  Integer delete(String[] paramArrayOfString) throws Exception;
}


