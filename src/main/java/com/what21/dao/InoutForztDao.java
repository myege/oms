package com.what21.dao;

import com.what21.model.Inout;
import com.what21.model.InoutQueryVo;
import com.what21.model.WmsInoutForzt;
import java.util.List;
import java.util.Map;

public interface InoutForztDao {
  int count(Map<String, Object> paramMap);
  
  List<WmsInoutForzt> findAll(Map<String, Object> paramMap);
  
  List<InoutQueryVo> selectByMan(String paramString1, String paramString2);
  
  List<Inout> selectBySou(String paramString1, String paramString2);
  
  int insert(WmsInoutForzt paramWmsInoutForzt);
  
  int updatezc();
  
  int updateforzt(WmsInoutForzt paramWmsInoutForzt);
  
  List<Inout> findSku(Map<String, Object> paramMap);
  
  List<Inout> findByIds(String paramString);
  
  List<Inout> findSkuByMs(String paramString1, String paramString2);
  
  List<WmsInoutForzt> findByIdsu(String paramString);
  
  int countByMs(String paramString1, String paramString2);
  
  List<InoutQueryVo> pullAll();
  
  List<WmsInoutForzt> findByMan(String paramString1, String paramString2);
  
  List<Inout> selectByManSou();
  
  int insertMap(Map<String, WmsInoutForzt> paramMap);
  
  int updateAll();
}


