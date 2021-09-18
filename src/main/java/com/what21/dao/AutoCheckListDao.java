package com.what21.dao;

import com.what21.model.AutoCheckList;
import com.what21.model.AutoCheckListExtend;
import com.what21.model.AutoCheckListVo;
import com.what21.model.OrderMail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AutoCheckListDao {
  int count(AutoCheckListVo paramAutoCheckListVo);
  
  List<AutoCheckList> findAll(AutoCheckListVo paramAutoCheckListVo);
  
  int save(AutoCheckList paramAutoCheckList);
  
  int updateById(AutoCheckList paramAutoCheckList);
  
  int deleteCompanyByIds(String[] paramArrayOfString);
  
  AutoCheckList findById(Integer paramInteger);
  
  List<AutoCheckListExtend> findOrderByAuditstatus(@Param("auditstatus") String paramString1, @Param("plan") String paramString2);
  
  List<OrderMail> findOrderById(String paramString);
}


