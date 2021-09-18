package com.what21.service;

import com.what21.model.NuclearRelease;
import java.util.List;
import java.util.Map;

public interface NuclearReleaseService {
  List<NuclearRelease> getAll(Map<String, Object> paramMap);
  
  int countNuclearRelease();
  
  int count(Map<String, Object> paramMap);
  
  List<NuclearRelease> find(Map<String, Object> paramMap);
  
  int addNu(NuclearRelease paramNuclearRelease);
  
  int deleteN(String paramString);
  
  NuclearRelease findByPc(NuclearRelease paramNuclearRelease);
}


