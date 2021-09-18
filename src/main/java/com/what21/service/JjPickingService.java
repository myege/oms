package com.what21.service;

import com.what21.model.JjPicking;
import com.what21.model.JjPickingCustom;
import com.what21.model.JjPickingVo;
import com.what21.util.GeneralResult;
import java.util.List;

public interface JjPickingService {
  int count(JjPickingVo paramJjPickingVo);
  
  List<JjPicking> findAll(JjPickingVo paramJjPickingVo);
  
  int distributeLeaflets(JjPickingCustom paramJjPickingCustom);
  
  GeneralResult importDistributeLeaflets(String paramString);
}


