 package com.what21.service.impl;
 
 import com.what21.dao.BeAccessoryDao;
 import com.what21.dao.BeLzdDao;
 import com.what21.dao.CollectDao;
 import com.what21.model.BeLzd;
 import com.what21.model.BeLzdCustom;
 import com.what21.model.BeLzdQueryVo;
 import com.what21.model.BeQgf;
 import com.what21.model.Collect;
 import com.what21.result.ResultInfo;
 import com.what21.service.BeLzdService;
 import com.what21.service.BeQgfService;
 import com.what21.tools.Tools;
 import java.util.Date;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Transactional
 @Service
 public class BeLzdServiceImpl
   implements BeLzdService
 {
   @Autowired
   private BeLzdDao beLzdDao;
   @Autowired
   private CollectDao collectDao;
   @Autowired
   private BeQgfService beQgfService;
   @Autowired
   private BeAccessoryDao beAccessoryDao;
   
   public List<BeLzd> findAll(BeLzdQueryVo beLzdQueryVo) throws Exception {
     return this.beLzdDao.findAll(beLzdQueryVo);
   }
 
   
   public int countAll(BeLzdQueryVo beLzdQueryVo) throws Exception {
     return this.beLzdDao.countAll(beLzdQueryVo);
   }
 
   
   public void update(BeLzd beSzd) throws Exception {
     this.beLzdDao.update(beSzd);
   }
 
   
   public BeLzd findById(Integer id) throws Exception {
     return this.beLzdDao.findById(id);
   }
 
   
   public void deleteById(Integer id) throws Exception {
     this.beLzdDao.deleteById(id);
   }
 
 
   
   public ResultInfo insert(BeLzdQueryVo beLzdQueryVo) throws Exception {
     try {
       BeLzdCustom beLzdCustom = beLzdQueryVo.getBeLzdCustom();
       int ywlx = beLzdCustom.getYwlx().intValue();
       String ywbh = null;
       if (ywlx == 1) {
         BeLzd beLzd = this.beLzdDao.findByTydh(beLzdCustom.getTdh());
         if (beLzd != null) {
           return new ResultInfo(0, "提单号在直邮流转单中已存在!");
         }
         String maxYwbh = this.beLzdDao.getMaxYwbh("Z-");
         ywbh = Tools.parseYwbh(maxYwbh, "Z-");
         beLzdCustom.setYwbh(ywbh);
       } else if (ywlx == 2) {
         String maxYwbh = this.beLzdDao.getMaxYwbh("B-");
         ywbh = Tools.parseYwbh(maxYwbh, "B-");
         beLzdCustom.setYwbh(ywbh);
       } 
       beLzdCustom.setFlag(Integer.valueOf(1));
       beLzdCustom.setCreateTime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
       this.beLzdDao.insert((BeLzd)beLzdCustom);
       Collect collect = new Collect();
       collect.setWtrq(beLzdCustom.getWtrq());
       collect.setBz(beLzdCustom.getBz());
       collect.setTydh(beLzdCustom.getTdh());
       collect.setMdg(beLzdCustom.getDdka());
       collect.setJs(beLzdCustom.getJs());
       collect.setTpcc(beLzdCustom.getTpcc());
       collect.setDs(beLzdCustom.getDs());
       collect.setJjzl(beLzdCustom.getZl());
       collect.setSj2(beLzdCustom.getKhmc());
       collect.setTj(beLzdCustom.getTj());
       collect.setZt(beLzdCustom.getZt());
       collect.setFdh(beLzdCustom.getFdh());
       collect.setYwbh(ywbh);
       
       collect.setSj(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
       this.collectDao.insert(collect);
       return new ResultInfo(1, "添加成功!");
     } catch (Exception e) {
       return new ResultInfo(0, "添加失败，出现了错误!");
     } 
   }
 
 
   
   public void insertZgxx(BeLzdQueryVo beLzdQueryVo) throws Exception {
     BeLzdCustom beLzdCustom = beLzdQueryVo.getBeLzdCustom();
     BeLzd beLzd = this.beLzdDao.findById(beLzdCustom.getId());
     beLzd.setClxx(beLzdCustom.getClxx());
     beLzd.setSjdhsj(beLzdCustom.getSjdhsj());
     beLzd.setZgsj(beLzdCustom.getZgsj());
     this.beLzdDao.update(beLzd);
     
     String ywbh = beLzd.getYwbh();
     Collect collect = this.collectDao.findByYwbh(ywbh);
     BeQgf beQgf = beLzdQueryVo.getBeQgf();
     String sj = collect.getSj();
     Date rq = Tools.parse("yyyy-MM-dd HH:mm:ss", sj);
     beQgf.setRq(rq);
     beQgf.setPramid(collect.getId());
     beQgf.setYdh(beLzd.getTdh());
     this.beQgfService.update2(beQgf);
   }
   public void updateFlagByIds(String[] ids, int flag) throws Exception {
     byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String str = arrayOfString[b];
       int id = Integer.parseInt(str);
       BeLzd beLzd = this.beLzdDao.findById(Integer.valueOf(id));
       beLzd.setFlag(Integer.valueOf(flag));
       this.beLzdDao.update(beLzd);
       b++; }
   
   }
 
 
   
   public void updateLzd(BeLzdQueryVo beLzdQueryVo) throws Exception {
     BeLzdCustom beLzdCustom = beLzdQueryVo.getBeLzdCustom();
     BeLzd beLzd = this.beLzdDao.findById(beLzdCustom.getId());
     Collect collect = this.collectDao.findByYwbh(beLzd.getYwbh());
     beLzd.setKhmc(beLzdCustom.getKhmc());
     beLzd.setDdka(beLzdCustom.getDdka());
     beLzd.setDs(beLzdCustom.getDs());
     beLzd.setJs(beLzdCustom.getJs());
     beLzd.setZl(beLzdCustom.getZl());
     beLzd.setTpcc(beLzdCustom.getTpcc());
     beLzd.setQysj(beLzdCustom.getQysj());
     beLzd.setTdh(beLzdCustom.getTdh());
     beLzd.setFdh(beLzdCustom.getFdh());
     this.beLzdDao.update(beLzd);
     
     collect.setTydh(beLzdCustom.getTdh());
     collect.setSj2(beLzdCustom.getKhmc());
     collect.setMdg(beLzdCustom.getDdka());
     collect.setDs(beLzdCustom.getDs());
     collect.setJs(beLzdCustom.getJs());
     collect.setJjzl(beLzdCustom.getZl());
     collect.setTpcc(beLzdCustom.getTpcc());
     collect.setFdh(beLzdCustom.getFdh());
     this.collectDao.update(collect);
   }
   
   public void deleteByIds(int[] ids) throws Exception {
     byte b;
     int i;
     int[] arrayOfInt;
     for (i = (arrayOfInt = ids).length, b = 0; b < i; ) { int id = arrayOfInt[b];
       BeLzd beLzd = this.beLzdDao.findById(Integer.valueOf(id));
       String ywbh = beLzd.getYwbh();
       Collect collect = this.collectDao.findByYwbh(ywbh);
       this.beAccessoryDao.deleteByLzdId(id);
       this.beLzdDao.deleteById(Integer.valueOf(id));
       this.beQgfService.deleteByPramid(collect.getId().intValue());
       this.collectDao.delete(collect.getId().intValue());
       b++; }
   
   }
 }


