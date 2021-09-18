 package com.what21.service.impl;
 
 import com.what21.dao.BeAccessoryOutDao;
 import com.what21.dao.BeLzdOutDao;
 import com.what21.model.BeAccessoryOut;
 import com.what21.model.BeAccessoryOutQueryVo;
 import com.what21.model.BeLzdOut;
 import com.what21.result.ResultInfo;
 import com.what21.service.BeAccessoryOutService;
 import com.what21.tools.Tools;
 import java.io.File;
 import java.util.Date;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Transactional
 @Service
 public class BeAccessoryOutServiceImpl
   implements BeAccessoryOutService
 {
   @Autowired
   private BeAccessoryOutDao beAccessoryOutDao;
   @Autowired
   private BeLzdOutDao beLzdOutDao;
   
   public List<BeAccessoryOut> findAll(BeAccessoryOutQueryVo accessoryOutQueryVo) {
     return this.beAccessoryOutDao.findAll(accessoryOutQueryVo);
   }
 
   
   public Integer countAll(BeAccessoryOutQueryVo accessoryOutQueryVo) {
     return this.beAccessoryOutDao.countAll(accessoryOutQueryVo);
   }
 
   
   public ResultInfo insert(BeAccessoryOut beAccessoryOut) {
     ResultInfo resultInfo = new ResultInfo();
     try {
       this.beAccessoryOutDao.insert(beAccessoryOut);
       BeLzdOut beLzdOut = this.beLzdOutDao.findById(beAccessoryOut.getLzdid());
       if ("lading".equals(beAccessoryOut.getItemname())) {
         beLzdOut.setTdbg(Integer.valueOf(1));
       } else if ("inventory_out".equals(beAccessoryOut.getItemname())) {
         beLzdOut.setQdbg(Integer.valueOf(1));
       } 
       this.beLzdOutDao.update(beLzdOut);
       resultInfo.setCode(1);
       resultInfo.setMessage("上传成功！");
       return resultInfo;
     } catch (Exception e) {
       e.printStackTrace();
       resultInfo.setCode(0);
       resultInfo.setMessage("上传失败，出现了错误！");
       return resultInfo;
     } 
   }
 
   
   public BeAccessoryOut findById(Integer id) {
     return this.beAccessoryOutDao.findById(id);
   }
 
   
   public void deleteByIds(Integer[] ids) throws Exception {
     BeAccessoryOut beAccessoryOut = this.beAccessoryOutDao.findById(ids[0]);
     BeLzdOut beLzdOut = this.beLzdOutDao.findById(beAccessoryOut.getLzdid());
     this.beAccessoryOutDao.deleteByIds(ids);
     Integer num1 = this.beAccessoryOutDao.countByItemname(beLzdOut.getId(), "lading");
     if (num1.intValue() == 0) {
       beLzdOut.setTdbg(Integer.valueOf(0));
     }
     Integer num2 = this.beAccessoryOutDao.countByItemname(beLzdOut.getId(), "inventory_out");
     if (num2.intValue() == 0) {
       beLzdOut.setQdbg(Integer.valueOf(0));
     }
     this.beLzdOutDao.update(beLzdOut);
   }
 
 
   
   public void deleteById(int id) throws Exception {
     BeAccessoryOut beAccessoryOut = this.beAccessoryOutDao.findById(Integer.valueOf(id));
     BeLzdOut beLzdOut = this.beLzdOutDao.findById(beAccessoryOut.getLzdid());
     this.beAccessoryOutDao.deleteById(id);
     Integer num1 = this.beAccessoryOutDao.countByItemname(beLzdOut.getId(), "lading");
     if (num1.intValue() == 0) {
       beLzdOut.setTdbg(Integer.valueOf(0));
     }
     Integer num2 = this.beAccessoryOutDao.countByItemname(beLzdOut.getId(), "inventory_out");
     if (num2.intValue() == 0) {
       beLzdOut.setQdbg(Integer.valueOf(0));
     }
     this.beLzdOutDao.update(beLzdOut);
   }
 
 
   
   public ResultInfo update(int id, String resFilename, String tarFilename, String path) {
     try {
       BeAccessoryOut beAccessoryOut = this.beAccessoryOutDao.findById(Integer.valueOf(id));
       String tar = beAccessoryOut.getTarfilename();
       beAccessoryOut.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
       beAccessoryOut.setTarfilename(tarFilename);
       beAccessoryOut.setResfilename(resFilename);
       this.beAccessoryOutDao.update(beAccessoryOut);
       File file = new File(path, tar);
       if (file.exists()) {
         file.delete();
       }
       return new ResultInfo(1, "修改文件成功");
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "修改文件失败");
     } 
   }
 }


