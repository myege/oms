 package com.what21.service;
 
 import com.what21.dao.BeAccessoryDao;
 import com.what21.model.BeAccessory;
 import com.what21.result.ResultInfo;
 import com.what21.tools.Tools;
 import java.io.File;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 
 @Transactional
 @Service
 public class BeAccessoryServiceImpl
   implements BeAccessoryService
 {
   @Autowired
   private BeAccessoryDao beAccessoryDao;
   
   public BeAccessory findById(Integer id) {
     return this.beAccessoryDao.findById(id);
   }
 
   
   public ResultInfo insert(BeAccessory beAccessory) throws Exception {
     ResultInfo resultInfo = new ResultInfo();
     try {
       this.beAccessoryDao.insert(beAccessory);
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
   
   public List<BeAccessory> findByLzdIdAndItemName(Map<String, Object> map) {
     return this.beAccessoryDao.findByLzdIdAndItemName(map);
   }
 
   
   public int countByLzdIdAndItemName(Map<String, Object> map) {
     return this.beAccessoryDao.countByLzdIdAndItemName(map);
   }
 
   
   public ResultInfo update(int id, String resfilename, String tarfilename, String address) throws Exception {
     try {
       BeAccessory beAccessory = this.beAccessoryDao.findById(Integer.valueOf(id));
       String tar = beAccessory.getTarfilename();
       beAccessory.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
       beAccessory.setTarfilename(tarfilename);
       beAccessory.setResfilename(resfilename);
       this.beAccessoryDao.update(beAccessory);
       File file = new File(address, tar);
       if (file.exists()) {
         file.delete();
       }
       return new ResultInfo(1, "修改文件成功");
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "修改文件失败");
     } 
   }
 
 
   
   public void deleteById(int id) throws Exception {
     this.beAccessoryDao.deleteById(id);
   }
   
   public void deleteByIds(int[] ids) throws Exception {
     byte b;
     int i;
     int[] arrayOfInt;
     for (i = (arrayOfInt = ids).length, b = 0; b < i; ) { int id = arrayOfInt[b];
       this.beAccessoryDao.deleteById(id);
       b++; }
   
   }
 
   
   public List<BeAccessory> findByLzdIdAndItemName2(Map<String, Object> map) {
     return this.beAccessoryDao.findByLzdIdAndItemName2(map);
   }
 
   
   public int countByLzdIdAndItemName2(Map<String, Object> map) {
     return this.beAccessoryDao.countByLzdIdAndItemName2(map);
   }
 }


