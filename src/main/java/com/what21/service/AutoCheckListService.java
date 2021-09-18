package com.what21.service;

import com.what21.model.AutoCheckList;
import com.what21.model.AutoCheckListExtend;
import com.what21.model.AutoCheckListVo;
import com.what21.model.OrderMail;
import java.util.List;

public interface AutoCheckListService {
  int count(AutoCheckListVo paramAutoCheckListVo);
  
  List<AutoCheckList> findAll(AutoCheckListVo paramAutoCheckListVo);
  
  int save(AutoCheckList paramAutoCheckList);
  
  int updateById(AutoCheckList paramAutoCheckList);
  
  int deleteCompanyByIds(String[] paramArrayOfString);
  
  AutoCheckList findById(Integer paramInteger);
  
  List<AutoCheckListExtend> findOrderByAuditstatus(String paramString1, String paramString2);
  
  List<OrderMail> findOrderById(String paramString);
}


