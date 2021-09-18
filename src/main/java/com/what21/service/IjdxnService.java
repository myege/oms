package com.what21.service;

import com.what21.model.OnlineRecordingParamTo;
import java.util.List;
import java.util.Map;

public interface IjdxnService {
  List<OnlineRecordingParamTo> findAll(Map<String, Object> paramMap);
  
  int count(Map<String, Object> paramMap);
}


