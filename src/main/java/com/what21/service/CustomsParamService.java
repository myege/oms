package com.what21.service;

import com.what21.model.CustomsParamCustom;
import com.what21.model.CustomsParamQueryVo;
import com.what21.model.TCustomsParam;
import com.what21.result.ResultInfo;
import com.what21.util.GeneralResult;
import java.util.List;

public interface CustomsParamService {
  Integer count(CustomsParamQueryVo paramCustomsParamQueryVo);
  
  List<CustomsParamCustom> find(CustomsParamQueryVo paramCustomsParamQueryVo);
  
  CustomsParamCustom findById(Integer paramInteger);
  
  ResultInfo insert(TCustomsParam paramTCustomsParam) throws Exception;
  
  ResultInfo update(TCustomsParam paramTCustomsParam) throws Exception;
  
  ResultInfo deleteByIds(int[] paramArrayOfint) throws Exception;
  
  GeneralResult importOrderNew(String paramString);
}


