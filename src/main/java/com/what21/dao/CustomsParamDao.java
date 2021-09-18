package com.what21.dao;

import com.what21.model.CustomsParamCustom;
import com.what21.model.CustomsParamQueryVo;
import com.what21.model.TCustomsParam;
import java.util.List;

public interface CustomsParamDao {
  Integer count(CustomsParamQueryVo paramCustomsParamQueryVo);
  
  List<CustomsParamCustom> find(CustomsParamQueryVo paramCustomsParamQueryVo);
  
  CustomsParamCustom findById(Integer paramInteger);
  
  void insert(TCustomsParam paramTCustomsParam);
  
  void update(TCustomsParam paramTCustomsParam);
  
  void insertBatch(List<TCustomsParam> paramList);
  
  void deleteByIds(int[] paramArrayOfint);
  
  Integer countByHscode(String paramString);
}


