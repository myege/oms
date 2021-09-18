package com.what21.dao;

import com.what21.model.NuclearRelease;
import java.util.List;
import java.util.Map;

public interface NuclearReleaseDao {
  int countNuclearRelease();
  
  int count(Map<String, Object> paramMap);
  
  List<NuclearRelease> find(Map<String, Object> paramMap);
  
  int addNu(NuclearRelease paramNuclearRelease);
  
  int deleteN(String paramString);
  
  List<NuclearRelease> findAll(Map<String, Object> paramMap);
  
  NuclearRelease findByPc(NuclearRelease paramNuclearRelease);
}


