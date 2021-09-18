package com.what21.service;

import com.what21.model.JjUser;
import com.what21.model.JjUserVo;
import java.util.List;

public interface JjUserService {
  int count(JjUserVo paramJjUserVo);
  
  List<JjUser> findAll(JjUserVo paramJjUserVo);
  
  JjUser findById(Integer paramInteger);
  
  int save(JjUser paramJjUser);
  
  int updateById(JjUser paramJjUser);
  
  int deleteByIds(String[] paramArrayOfString);
  
  List<JjUser> findJjUser();
}


