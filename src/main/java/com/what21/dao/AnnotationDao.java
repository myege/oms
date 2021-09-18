package com.what21.dao;

import com.what21.model.Annotation;
import com.what21.model.AnnotationCustom;
import com.what21.model.AnnotationQueryVo;
import com.what21.model.AnnotationSku;
import java.util.List;
import java.util.Map;

public interface AnnotationDao {
  List<Annotation> findAll(Map<String, Object> paramMap);
  
  List<AnnotationSku> findAllSku(Map<String, Object> paramMap);
  
  int countSku(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int actdelete(String paramString);
  
  List<Annotation> findact(Map<String, Object> paramMap);
  
  Annotation findByA(String paramString);
  
  void inserta(Annotation paramAnnotation);
  
  int actts(String paramString);
  
  int aflag(Annotation paramAnnotation);
  
  List<Annotation> findacId(String paramString);
  
  List<Annotation> actadd(Map<String, Object> paramMap);
  
  void aInserta(List<Annotation> paramList);
  
  int count2(AnnotationQueryVo paramAnnotationQueryVo);
  
  List<AnnotationCustom> findAll2(AnnotationQueryVo paramAnnotationQueryVo);
  
  void insert(Annotation paramAnnotation);
}


