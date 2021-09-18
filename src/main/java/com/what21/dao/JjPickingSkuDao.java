package com.what21.dao;

import com.what21.model.JjPickingCustom;
import com.what21.model.JjPickingSku;
import com.what21.model.JjPickingSkuVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JjPickingSkuDao {
  int count(JjPickingSkuVo paramJjPickingSkuVo);
  
  List<JjPickingSku> findAll(JjPickingSkuVo paramJjPickingSkuVo);
  
  List<JjPickingSku> findBill();
  
  void insertBatchSku(List<JjPickingSku> paramList);
  
  void distributeLeafletsSku(@Param("jjPickingCustom") JjPickingCustom paramJjPickingCustom);
}


