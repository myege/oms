package com.what21.service;

import com.what21.model.Inout;
import com.what21.model.InoutQueryVo;
import com.what21.model.WmsInoutForzt;
import java.util.List;
import java.util.Map;

public interface InoutForztService {
  int count(Map<String, Object> paramMap) throws Exception;
  
  List<WmsInoutForzt> findAll(Map<String, Object> paramMap) throws Exception;
  
  List<InoutQueryVo> selectByMan(String paramString1, String paramString2) throws Exception;
  
  List<Inout> selectBySou(String paramString1, String paramString2) throws Exception;
  
  int insert(WmsInoutForzt paramWmsInoutForzt) throws Exception;
  
  int updatezc() throws Exception;
  
  int updateforzt(WmsInoutForzt paramWmsInoutForzt) throws Exception;
  
  List<Inout> findSku(Map<String, Object> paramMap) throws Exception;
  
  List<Inout> findByIds(String paramString) throws Exception;
  
  List<Inout> findSkuByMS(String paramString1, String paramString2) throws Exception;
  
  List<WmsInoutForzt> findByIdsu(String paramString) throws Exception;
  
  int countByMs(String paramString1, String paramString2) throws Exception;
  
  List<InoutQueryVo> pullAll() throws Exception;
  
  List<WmsInoutForzt> findByMan(String paramString1, String paramString2) throws Exception;
  
  List<Inout> selectByManSou() throws Exception;
  
  int insertMap(Map<String, WmsInoutForzt> paramMap) throws Exception;
  
  int updateAll() throws Exception;
}


