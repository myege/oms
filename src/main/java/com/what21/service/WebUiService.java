package com.what21.service;

import com.what21.model.WebUi;
import java.util.List;

public interface WebUiService {
  List<WebUi> getAboutusMx();
  
  List<WebUi> findAll(WebUi paramWebUi);
  
  Integer count(WebUi paramWebUi);
  
  List<WebUi> findByCode(String paramString);
  
  void update(List<WebUi> paramList);
  
  List<WebUi> findByBegCode(String paramString);
  
  List<WebUi> findByCodeThree(int paramInt);
}


