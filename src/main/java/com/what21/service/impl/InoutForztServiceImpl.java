 package com.what21.service.impl;
 
 import com.what21.dao.InoutForztDao;
 import com.what21.model.Inout;
 import com.what21.model.InoutQueryVo;
 import com.what21.model.WmsInoutForzt;
 import com.what21.service.InoutForztService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class InoutForztServiceImpl
   implements InoutForztService
 {
   @Autowired
   private InoutForztDao inoutForztDao;
   
   public int count(Map<String, Object> pageMap) throws Exception {
     return this.inoutForztDao.count(pageMap);
   }
 
 
 
   
   public List<WmsInoutForzt> findAll(Map<String, Object> pageMap) throws Exception {
     return this.inoutForztDao.findAll(pageMap);
   }
 
 
 
   
   public List<InoutQueryVo> selectByMan(String manualid, String sourceno) throws Exception {
     return this.inoutForztDao.selectByMan(manualid, sourceno);
   }
 
 
 
   
   public List<Inout> selectBySou(String manualid, String sourceno) throws Exception {
     return this.inoutForztDao.selectBySou(manualid, sourceno);
   }
 
 
   
   public int insert(WmsInoutForzt wmsInoutForzt) {
     return this.inoutForztDao.insert(wmsInoutForzt);
   }
 
 
   
   public int updatezc() throws Exception {
     return this.inoutForztDao.updatezc();
   }
 
 
   
   public int updateforzt(WmsInoutForzt wmsInoutForzt) throws Exception {
     return this.inoutForztDao.updateforzt(wmsInoutForzt);
   }
 
 
 
   
   public List<Inout> findSku(Map<String, Object> pageMap) throws Exception {
     return this.inoutForztDao.findSku(pageMap);
   }
 
 
   
   public List<Inout> findByIds(String ids) throws Exception {
     return this.inoutForztDao.findByIds(ids);
   }
 
 
 
   
   public List<Inout> findSkuByMS(String exmanualid, String exsourceno) throws Exception {
     return this.inoutForztDao.findSkuByMs(exmanualid, exsourceno);
   }
 
 
   
   public List<WmsInoutForzt> findByIdsu(String idsu) throws Exception {
     return this.inoutForztDao.findByIdsu(idsu);
   }
 
 
   
   public int countByMs(String manualid, String sourceno) throws Exception {
     return this.inoutForztDao.countByMs(manualid, sourceno);
   }
 
 
   
   public List<InoutQueryVo> pullAll() throws Exception {
     return this.inoutForztDao.pullAll();
   }
 
 
 
   
   public List<WmsInoutForzt> findByMan(String manualId, String sourceNo) throws Exception {
     return this.inoutForztDao.findByMan(manualId, sourceNo);
   }
 
 
   
   public List<Inout> selectByManSou() throws Exception {
     return this.inoutForztDao.selectByManSou();
   }
 
 
   
   public int insertMap(Map<String, WmsInoutForzt> map) throws Exception {
     return this.inoutForztDao.insertMap(map);
   }
 
 
   
   public int updateAll() throws Exception {
     return this.inoutForztDao.updateAll();
   }
 }


