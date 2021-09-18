 package com.what21.service.impl;
 
 import com.what21.dao.BeCategoryDao;
 import com.what21.dao.BeHcDao;
 import com.what21.dao.CollectDao;
 import com.what21.model.BeHc;
 import com.what21.model.Collect;
 import com.what21.service.BeHcService;
 import com.what21.tools.Tools;
 import com.what21.util.BeHcExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class BeHcServiceImpl
   implements BeHcService
 {
   @Autowired
   private BeHcDao beHcDao;
   @Autowired
   private BeCategoryDao beCategoryDao;
   @Autowired
   private CollectDao collectDao;
   
   public List<BeHc> findAll(Map<String, Object> map) {
     return this.beHcDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) {
     return this.beHcDao.count(map);
   }
 
   
   public void insert(BeHc beHc) {
     this.beHcDao.insert(beHc);
   }
 
   
   public void deleteById(Integer id) {
     this.beHcDao.deleteById(id);
   }
 
   
   public BeHc findById(Integer id) {
     return this.beHcDao.findById(id);
   }
 
   
   public List<BeHc> findByPramid(Map<String, Object> map) {
     return this.beHcDao.findByPramid(map);
   }
 
   
   public Integer countByPramid(Map<String, Object> map) {
     return this.beHcDao.countByPramid(map);
   }
 
   
   public GeneralResult importOrderNew(String string, int pramid) throws Exception {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = BeHcExcelUtil.read(string);
     List<BeHc> list = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size();
     float hcf = 0.0F;
     Date date = new Date();
     String str_date = Tools.format("yyyy-MM-dd HH-mm-ss", date);
     date = Tools.parse("yyyy-MM-dd HH-mm-ss", str_date);
     DecimalFormat df = new DecimalFormat("0.00");
     for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
       Object[] nowRowData = me.getValue();
       int rowNum = ((Integer)me.getKey()).intValue() + 1;
       String pm = null;
       if (nowRowData[0] != null && StringUtils.isNotEmpty(nowRowData[0].toString())) {
         pm = nowRowData[0].toString().trim();
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：品名必须填写！<br>");
         break;
       } 
       String ddbh = null;
       if (nowRowData[1] != null && StringUtils.isNotEmpty(nowRowData[1].toString())) {
         ddbh = nowRowData[1].toString().trim();
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：订单编号必须填写！<br>");
         break;
       } 
       String ydbh = null;
       if (nowRowData[2] != null && StringUtils.isNotEmpty(nowRowData[2].toString())) {
         ydbh = nowRowData[2].toString().trim();
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：运单编号必须填写！<br>");
         break;
       } 
       Integer spjs = null;
       if (nowRowData[3] != null && StringUtils.isNotEmpty(nowRowData[3].toString())) {
         try {
           spjs = Integer.valueOf(Integer.parseInt(nowRowData[3].toString().trim()));
         } catch (Exception e) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：商品件数必须是整数！<br>");
           break;
         } 
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：商品件数必须填写！<br>");
         break;
       } 
       String sjr = null;
       if (nowRowData[4] != null && StringUtils.isNotEmpty(nowRowData[4].toString())) {
         sjr = nowRowData[4].toString().trim();
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：收件人必须填写！<br>");
         
         break;
       } 
       Float qpm = null;
       if (nowRowData[5] != null && StringUtils.isNotEmpty(nowRowData[5].toString())) {
         try {
           qpm = Float.valueOf(Float.parseFloat(nowRowData[5].toString().trim()));
         } catch (Exception e) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：气泡膜必须是数字！<br>");
           break;
         } 
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：气泡膜必须填写！<br>");
         break;
       } 
       Integer qzd = null;
       if (nowRowData[6] != null && StringUtils.isNotEmpty(nowRowData[6].toString())) {
         try {
           qzd = Integer.valueOf(Integer.parseInt(nowRowData[6].toString().trim()));
         } catch (Exception e) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：气柱袋必须是整数！<br>");
           break;
         } 
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：气柱袋必须填写！<br>");
         break;
       } 
       Float qzddj = null;
       if (nowRowData[7] != null && StringUtils.isNotEmpty(nowRowData[7].toString())) {
         try {
           qzddj = Float.valueOf(Float.parseFloat(nowRowData[7].toString().trim()));
         } catch (Exception e) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：气柱袋单价必须是数字！<br>");
           break;
         } 
       }
       Integer zxgg = null;
       if (nowRowData[8] != null && StringUtils.isNotEmpty(nowRowData[8].toString())) {
         try {
           zxgg = Integer.valueOf(Integer.parseInt(nowRowData[8].toString().trim()));
         } catch (Exception e) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：纸箱规格必须是整数！<br>");
           break;
         } 
       } else {
         message.append("导入第" + rowNum + "行数据失败，失败原因：纸箱规格必须填写！<br>");
         break;
       } 
       Float zxggdj = null;
       if (nowRowData[9] != null && StringUtils.isNotEmpty(nowRowData[9].toString())) {
         try {
           zxggdj = Float.valueOf(Float.parseFloat(nowRowData[9].toString().trim()));
         } catch (Exception e) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：纸箱规格单价必须是数字！<br>");
           break;
         } 
       }
       BeHc beHc = new BeHc();
       beHc.setPm(pm);
       beHc.setDdbh(ddbh);
       beHc.setYdbh(ydbh);
       beHc.setSpjs(spjs);
       beHc.setSjr(sjr);
       beHc.setQpm(qpm.toString());
       beHc.setQzd(qzd.toString());
       beHc.setZxgg(zxgg.toString());
       if (qzddj == null) {
         qzddj = Float.valueOf(Float.parseFloat(this.beCategoryDao.findQzddjByName(qzd.toString())));
       }
       if (zxggdj == null) {
         zxggdj = Float.valueOf(Float.parseFloat(this.beCategoryDao.findZxggdjByName(zxgg.toString())));
       }
       float qpmdj = Float.parseFloat(this.beCategoryDao.findQpmdj());
       float hj = qzddj.floatValue() + zxggdj.floatValue() + qpmdj * qpm.floatValue();
       hcf += hj;
       beHc.setHj(df.format(hj));
       beHc.setQzddj(qzddj.toString());
       beHc.setZxggdj(zxggdj.toString());
       beHc.setDrsj(date);
       beHc.setPramid(Integer.valueOf(pramid));
       list.add(beHc);
     } 
     if (!message.toString().contains("导入")) {
       for (BeHc beHc : list) {
         this.beHcDao.insert(beHc);
       }
       try {
         Collect collect = this.collectDao.findById(pramid);
         String past_hcf = collect.getHcf();
         String str_hcf = df.format(hcf);
         if (StringUtils.isNotEmpty(past_hcf)) {
           hcf += Float.parseFloat(past_hcf);
           str_hcf = df.format(hcf);
         } 
         collect.setHcf(str_hcf);
         Tools.saveHj(collect);
         this.collectDao.update(collect);
       } catch (Exception e) {
         throw new IllegalArgumentException("未知错误");
       } 
       message.append("导入成功" + total + "条！");
     } 
     result.setMessage(message.toString());
     return result;
   }
 
 
   
   public void deleteByPramid(Map<String, Object> map) {
     this.beHcDao.deleteByPramid(map);
   }
 }


