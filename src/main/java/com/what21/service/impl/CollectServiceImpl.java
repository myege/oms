 package com.what21.service.impl;
 
 import com.what21.dao.BeHcDao;
 import com.what21.dao.BePttsfDao;
 import com.what21.dao.BeQgfDao;
 import com.what21.dao.BeSFDao;
 import com.what21.dao.BeYfDao;
 import com.what21.dao.CollectDao;
 import com.what21.model.Collect;
 import com.what21.model.CollectCustom;
 import com.what21.model.CollectQueryVo;
 import com.what21.service.CollectService;
 import com.what21.tools.Tools;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class CollectServiceImpl
   implements CollectService
 {
   @Autowired
   private CollectDao collectDao;
   @Autowired
   private BeSFDao beSFDao;
   @Autowired
   private BeYfDao beYfDao;
   @Autowired
   private BePttsfDao bePttsfDao;
   @Autowired
   private BeQgfDao beQgfDao;
   @Autowired
   private BeHcDao beHcDao;
   
   public List<Collect> findAll(CollectQueryVo collectQueryVo) {
     return this.collectDao.findAll(collectQueryVo);
   }
 
   
   public int count(CollectQueryVo collectQueryVo) {
     return this.collectDao.count(collectQueryVo);
   }
 
   
   public void insert(Collect collect) {
     this.collectDao.insert(collect);
   }
 
 
   
   public void delete(int id) {
     this.collectDao.delete(id);
   }
 
   
   public Collect findById(int id) {
     return this.collectDao.findById(id);
   }
 
 
   
   public int countByTydh(String tydh) {
     return this.collectDao.countByTydh(tydh);
   }
 
   
   public void update(Collect collect) {
     this.collectDao.update(collect);
   }
 
 
   
   public Collect findByTydh(String tydh) {
     return this.collectDao.findByTydh(tydh);
   }
 
   
   public List<Collect> findAllBs(CollectQueryVo collectQueryVo) {
     return this.collectDao.findAllBs(collectQueryVo);
   }
 
   
   public int countBs(CollectQueryVo collectQueryVo) {
     return this.collectDao.countBs(collectQueryVo);
   }
 
   
   public void insertBsjs(CollectQueryVo collectQueryVo) {
     CollectCustom collectCustom = collectQueryVo.getCollectCustom();
     String kssj = collectCustom.getKssj();
     String jssj = collectCustom.getJssj();
     kssj = kssj.substring(0, 11).concat("00:00:00");
     jssj = jssj.substring(0, 11).concat("23:59:59");
     collectCustom.setKssj(kssj);
     collectCustom.setJssj(jssj);
     collectCustom.setBsflag(Integer.valueOf(1));
     collectCustom.setSj(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
     this.collectDao.insert((Collect)collectCustom);
   }
 
 
   
   public List<Collect> findBySjAndSj2(Collect collect) {
     return this.collectDao.findBySjAndSj2(collect);
   }
 
   
   public int countBySjAndSj2(Collect collect) {
     return this.collectDao.countBySjAndSj2(collect);
   }
 
   
   public Collect findByYwbh(String ywbh) {
     return this.collectDao.findByYwbh(ywbh);
   } public void deleteByIds(int[] ids) {
     byte b;
     int i;
     int[] arrayOfInt;
     for (i = (arrayOfInt = ids).length, b = 0; b < i; ) { int j = arrayOfInt[b];
       Map<String, Object> map = new HashMap<>();
       map.put("pramid", Integer.valueOf(j));
       this.beSFDao.deleteByPramid(map);
       this.beQgfDao.deleteByPramid(j);
       this.beHcDao.deleteByPramid(map);
       this.beYfDao.deleteByPramid(map);
       this.bePttsfDao.deleteByPramid(map);
       
       Collect collect = this.collectDao.findById(j);
       this.collectDao.delete(collect.getId().intValue());
       b++; }
   
   }
 }


