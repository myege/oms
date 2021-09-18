package com.what21.dao;

import com.what21.model.OnlineRecordingParamTo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface IjdxnDao {
  List<OnlineRecordingParamTo> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
  
  List<OnlineRecordingParamTo> findbygjz(String paramString);
  
  void updatebygjz(@Param("sellerRecord") String paramString1, @Param("notes") String paramString2, @Param("status") String paramString3);
  
  void insertBatch(List<OnlineRecordingParamTo> paramList);
  
  List<OnlineRecordingParamTo> findgjz(@Param("gjz") String paramString);
}


