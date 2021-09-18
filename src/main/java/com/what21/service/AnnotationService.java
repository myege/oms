package com.what21.service;

import com.what21.model.Annotation;
import com.what21.model.AnnotationCustom;
import com.what21.model.AnnotationQueryVo;
import com.what21.util.GeneralResult;
import java.util.List;
import java.util.Map;

public interface AnnotationService {
  List<Annotation> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  int countSku(Map<String, Object> paramMap);
  
  int actdelete(String paramString);
  
  List<Annotation> findact(Map<String, Object> paramMap);
  
  GeneralResult inmera(String paramString, int paramInt);
  
  int aflag(Annotation paramAnnotation);
  
  List<Annotation> findacId(String paramString);
  
  List<Annotation> actadd(Map<String, Object> paramMap);
  
  int count2(AnnotationQueryVo paramAnnotationQueryVo);
  
  List<AnnotationCustom> findAll2(AnnotationQueryVo paramAnnotationQueryVo);
}


