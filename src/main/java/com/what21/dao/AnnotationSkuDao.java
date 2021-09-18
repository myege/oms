package com.what21.dao;

import com.what21.model.AnnotationSku;
import java.util.List;
import java.util.Map;

public interface AnnotationSkuDao {
  List<AnnotationSku> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<AnnotationSku> findact(Map<String, Object> paramMap);
  
  void insert(AnnotationSku paramAnnotationSku);
  
  AnnotationSku findByA(String paramString);
  
  int actdelete(String paramString);
  
  void aflag(AnnotationSku paramAnnotationSku);
  
  void asInsert(Object[] paramArrayOfObject);
}


