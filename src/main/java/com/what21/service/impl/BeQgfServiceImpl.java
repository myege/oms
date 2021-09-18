 package com.what21.service.impl;
 
 import com.what21.dao.BeQgfDao;
 import com.what21.dao.CollectDao;
 import com.what21.model.BeQgf;
 import com.what21.model.Collect;
 import com.what21.service.BeQgfService;
 import com.what21.tools.Tools;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 @Service
 @Transactional
 public class BeQgfServiceImpl
   implements BeQgfService
 {
   @Autowired
   private BeQgfDao beQgfDao;
   @Autowired
   private CollectDao collectDao;
   
   public List<BeQgf> findAll(Map<String, Object> map) throws Exception {
     return this.beQgfDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) throws Exception {
     return this.beQgfDao.count(map);
   }
 
   
   public void insert(BeQgf beQgf) throws Exception {
     this.beQgfDao.insert(beQgf);
   }
 
 
   
   public void deleteById(Integer id) throws Exception {
     this.beQgfDao.deleteById(id);
   }
 
 
   
   public BeQgf findById(Integer id) throws Exception {
     return this.beQgfDao.findById(id);
   }
 
   
   public BeQgf findByPramid(int pramid) throws Exception {
     return this.beQgfDao.findByPramid(pramid);
   }
 
 
   
   public void update(BeQgf beQgf) throws Exception {
     this.beQgfDao.update(beQgf);
   }
 
 
   
   public int update2(BeQgf beQgf) throws Exception {
     int pramid = beQgf.getPramid().intValue();
     BeQgf BeQgf_res = this.beQgfDao.findByPramid(pramid);
     float baf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getBaf())) {
       baf = Float.parseFloat(beQgf.getBaf());
     }
     float bb = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getBb())) {
       bb = Float.parseFloat(beQgf.getBb());
     }
     float bhbjf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getBgbjf())) {
       bhbjf = Float.parseFloat(beQgf.getBgbjf());
     }
     float Bgf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getBgf())) {
       Bgf = Float.parseFloat(beQgf.getBgf());
     }
     float cyf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getCyf())) {
       cyf = Float.parseFloat(beQgf.getCyf());
     }
     float fbf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getFbf())) {
       fbf = Float.parseFloat(beQgf.getFbf());
     }
     float hdf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getHdf())) {
       hdf = Float.parseFloat(beQgf.getHdf());
     }
     float hff = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getHff())) {
       hff = Float.parseFloat(beQgf.getHff());
     }
     float jgcf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getJgcf())) {
       jgcf = Float.parseFloat(beQgf.getJgcf());
     }
     float zf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getZf())) {
       zf = Float.parseFloat(beQgf.getZf());
     }
     float jyjyf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getJyjyf())) {
       jyjyf = Float.parseFloat(beQgf.getJyjyf());
     }
     float ldf = 0.0F;
     if (StringUtils.isNotEmpty(beQgf.getLdf())) {
       ldf = Float.parseFloat(beQgf.getLdf());
     }
     
     float hj = baf + bb + bhbjf + Bgf + cyf + fbf + hdf + hff + zf + jgcf + jyjyf + ldf;
     DecimalFormat df = new DecimalFormat("0.00");
     String str_hj = df.format(hj);
     beQgf.setHj(str_hj);
     Collect collect = this.collectDao.findById(pramid);
     collect.setQgf(str_hj);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
     if (BeQgf_res != null) {
       beQgf.setId(BeQgf_res.getId());
       this.beQgfDao.update(beQgf);
       return 1;
     } 
     this.beQgfDao.insert(beQgf);
     return 1;
   }
 
   
   public int countByPramid(Map<String, Object> map) {
     return this.beQgfDao.countByPramid(map);
   }
 
   
   public List<BeQgf> findAllByPramid(Map<String, Object> map) {
     return this.beQgfDao.findAllByPramid(map);
   }
 
   
   public void insertByBsjs(Collect collect) {
     int pramid = collect.getId().intValue();
     this.beQgfDao.deleteByPramid(pramid);
     List<Collect> list = this.collectDao.findBySjAndSj2(collect);
     List<BeQgf> beQgfs = new ArrayList<>();
     float hj = 0.0F;
     for (Collect collect2 : list) {
       BeQgf beQgf = this.beQgfDao.findByPramid(collect2.getId().intValue());
       if (beQgf == null) {
         continue;
       }
       String str = beQgf.getHj();
       if (StringUtils.isEmpty(str)) {
         hj += 0.0F;
       } else {
         hj += Float.parseFloat(str);
       } 
       beQgf.setId(null);
       beQgf.setPramid(Integer.valueOf(pramid));
       beQgfs.add(beQgf);
     } 
     for (BeQgf beQgf : beQgfs) {
       this.beQgfDao.insert(beQgf);
     }
     DecimalFormat df = new DecimalFormat("0.00");
     String str_hj = df.format(hj);
     collect.setQgf(str_hj);
     Tools.saveHj(collect);
     this.collectDao.update(collect);
   }
 
 
 
   
   public void deleteByPramid(int pramid) {
     this.beQgfDao.deleteByPramid(pramid);
   }
 }


