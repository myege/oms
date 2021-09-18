package com.what21.service;

import com.what21.model.JjPickingSku;
import com.what21.model.JjPickingSkuVo;
import java.util.List;

public interface JjPickingSkuService {
  int count(JjPickingSkuVo paramJjPickingSkuVo);
  
  List<JjPickingSku> findAll(JjPickingSkuVo paramJjPickingSkuVo);
}


