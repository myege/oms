 package com.what21.service.impl;
 
 import com.what21.dao.NuclearReleaseDao;
 import com.what21.model.NuclearRelease;
 import com.what21.service.NuclearReleaseService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class NuclearReleaseServiceImpl
   implements NuclearReleaseService
 {
   @Autowired
   private NuclearReleaseDao nuclearReleaseDao;
   
   public List<NuclearRelease> getAll(Map<String, Object> map) {
     return this.nuclearReleaseDao.findAll(map);
   }
 
   
   public int countNuclearRelease() {
     return this.nuclearReleaseDao.countNuclearRelease();
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.nuclearReleaseDao.count(pageMap);
   }
 
   
   public List<NuclearRelease> find(Map<String, Object> pageMap) {
     return this.nuclearReleaseDao.find(pageMap);
   }
 
   
   public int addNu(NuclearRelease n) {
     return this.nuclearReleaseDao.addNu(n);
   }
 
   
   public int deleteN(String id) {
     return this.nuclearReleaseDao.deleteN(id);
   }
 
 
   
   public NuclearRelease findByPc(NuclearRelease n) {
     return this.nuclearReleaseDao.findByPc(n);
   }
 }


