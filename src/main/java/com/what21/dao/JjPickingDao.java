package com.what21.dao;

import com.what21.model.JjPicking;
import com.what21.model.JjPickingCustom;
import com.what21.model.JjPickingVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JjPickingDao {
  int count(JjPickingVo paramJjPickingVo);
  
  List<JjPicking> findAll(JjPickingVo paramJjPickingVo);
  
  int distributeLeaflets(@Param("jjPickingCustom") JjPickingCustom paramJjPickingCustom);
  
  List<JjPicking> findPick();
  
  void insertBatch(List<JjPicking> paramList);
  
  List<JjPicking> findbygjz(String paramString);
  
  List<JjPicking> findbyby(String paramString);
  
  List<JjPicking> findbybn(String paramString);
  
  JjPicking findById(String paramString);
  
  int updateById(JjPicking paramJjPicking);
  
  List<JjPicking> findbygjz2(@Param("startTime") String paramString1, @Param("endTime") String paramString2);
  
  List<JjPicking> findbygjz3(@Param("startTime") String paramString1, @Param("endTime") String paramString2);
}


