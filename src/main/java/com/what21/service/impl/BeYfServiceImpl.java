 package com.what21.service.impl;
 
 import com.what21.dao.BeYfDao;
 import com.what21.dao.CollectDao;
 import com.what21.model.BeYf;
 import com.what21.model.Collect;
 import com.what21.service.BeYfService;
 import com.what21.tools.Tools;
 import com.what21.util.BeYfExcelUtil;
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
 public class BeYfServiceImpl
   implements BeYfService
 {
   @Autowired
   private BeYfDao beYfDao;
   @Autowired
   private CollectDao collectDao;
   
   public List<BeYf> findAll(Map<String, Object> map) {
     return this.beYfDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) {
     return this.beYfDao.count(map);
   }
 
   
   public void insert(BeYf beYf) {
     this.beYfDao.insert(beYf);
   }
 
   
   public void deleteById(Integer id) {
     this.beYfDao.deleteById(id);
   }
 
 
   
   public BeYf findById(Integer id) {
     return this.beYfDao.findById(id);
   }
 
   
   public List<BeYf> findByPramid(Map<String, Object> map) {
     return this.beYfDao.findByPramid(map);
   }
 
   
   public Integer countByPramid(Map<String, Object> map) {
     return this.beYfDao.countByPramid(map);
   }
 
   
   public GeneralResult importOrderNew(String string, int pramid) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = BeYfExcelUtil.read(string);
     List<BeYf> list = new ArrayList<>();
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size();
     float yf = 0.0F;
     for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
       Object[] nowRowData = me.getValue();
       int rowNum = ((Integer)me.getKey()).intValue() + 1;
       Integer xh = null;
       try {
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：序号没有输入！<br>");
           break;
         } 
         xh = Integer.valueOf(Integer.parseInt(nowRowData[0].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：序号必须是整数！<br>");
         
         break;
       } 
       Object jjkh = nowRowData[1];
       if (jjkh == null || StringUtils.isEmpty(jjkh.toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：寄件客户没有输入！<br>");
         break;
       } 
       Date jjrq = null;
       try {
         jjrq = (Date)nowRowData[2];
         if (jjrq == null) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：请输入寄件日期！<br>");
           break;
         } 
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：请输入正确的寄件日期！<br>");
         
         break;
       } 
       Object ydbh = nowRowData[3];
       if (ydbh == null || StringUtils.isEmpty(ydbh.toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：运单编号没有输入！<br>");
         break;
       } 
       Object mdd = nowRowData[4];
       if (mdd == null || StringUtils.isEmpty(mdd.toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：目的地没有输入！<br>");
         break;
       } 
       Float sjzl = null;
       try {
         if (nowRowData[5] == null || StringUtils.isEmpty(nowRowData[5].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：计费重量没有输入！<br>");
           break;
         } 
         sjzl = Float.valueOf(Float.parseFloat(nowRowData[5].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：计费重量必须是数字！<br>");
         
         break;
       } 
       Integer jfzl = null;
       try {
         if (nowRowData[6] == null || StringUtils.isEmpty(nowRowData[6].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：计费重量没有输入！<br>");
           break;
         } 
         jfzl = Integer.valueOf(Integer.parseInt(nowRowData[6].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：计费重量必须是整数！<br>");
         break;
       } 
       Float ysyf = null;
       try {
         if (nowRowData[7] == null || StringUtils.isEmpty(nowRowData[7].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：应收运费没有输入！<br>");
           break;
         } 
         ysyf = Float.valueOf(Float.parseFloat(nowRowData[7].toString()));
         yf += ysyf.floatValue();
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：应收运费必须是数字！<br>");
         break;
       } 
       Object bz = nowRowData[3];
       if (bz == null || StringUtils.isEmpty(bz.toString().trim())) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：备注没有输入！<br>");
         break;
       } 
       Integer xz = null;
       try {
         if (nowRowData[9] == null || StringUtils.isEmpty(nowRowData[9].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：续重没有输入！<br>");
           break;
         } 
         xz = Integer.valueOf(Integer.parseInt(nowRowData[9].toString()));
       } catch (Exception e) {
         message.append("导入第" + rowNum + "行数据失败，失败原因：续重必须是整数！<br>");
         break;
       } 
       BeYf beYf = new BeYf();
       beYf.setPramid(Integer.valueOf(pramid));
       beYf.setBz(bz.toString());
       beYf.setJfzl(jfzl.toString());
       beYf.setJjkh(jjkh.toString());
       beYf.setJjrq(jjrq);
       beYf.setMdd(mdd.toString());
       beYf.setSjzl(sjzl.toString());
       beYf.setXh(xh);
       beYf.setXz(xz.toString());
       beYf.setYdbh(ydbh.toString());
       beYf.setYsyf(ysyf.toString());
       beYf.setDrsj(Tools.format("yyyy-MM-dd HH-mm-ss", new Date()));
       list.add(beYf);
     } 
     if (!message.toString().contains("导入")) {
       for (BeYf beYf : list) {
         this.beYfDao.insert(beYf);
       }
       try {
         Collect collect = this.collectDao.findById(pramid);
         String past_yf = collect.getYf();
         DecimalFormat df = new DecimalFormat("0.00");
         String str_yf = df.format(yf);
         if (past_yf != null && StringUtils.isNotEmpty(past_yf)) {
           
           yf += Float.parseFloat(past_yf);
           str_yf = df.format(yf);
           collect.setYf(str_yf);
         } 
         collect.setYf(str_yf);
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
     this.beYfDao.deleteByPramid(map);
   }
 }


