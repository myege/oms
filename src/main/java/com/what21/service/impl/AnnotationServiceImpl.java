 package com.what21.service.impl;
 
 import com.what21.dao.AnnotationDao;
 import com.what21.dao.AnnotationSkuDao;
 import com.what21.model.Annotation;
 import com.what21.model.AnnotationCustom;
 import com.what21.model.AnnotationQueryVo;
 import com.what21.model.AnnotationSku;
 import com.what21.service.AnnotationService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import com.what21.util.timenew;
 import java.io.File;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class AnnotationServiceImpl
   implements AnnotationService
 {
   @Autowired
   private AnnotationDao annotationDao;
   @Autowired
   private AnnotationSkuDao annotationSkuDao;
   
   public List<Annotation> findAll(Map<String, Object> pageMap) {
     return this.annotationDao.findAll(pageMap);
   }
 
   
   public int count(Map<String, Object> pageMap) {
     return this.annotationDao.count(pageMap);
   }
 
   
   public int actdelete(String id) {
     return this.annotationDao.actdelete(id);
   }
 
   
   public List<Annotation> findact(Map<String, Object> pageMap) {
     return this.annotationDao.findact(pageMap);
   }
 
   
   public GeneralResult inmera(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
     boolean firstRow = true;
     StringBuffer message = new StringBuffer();
     int successData = 0;
     int totalData = items.entrySet().size() - 1;
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
 
     
     List<Annotation> actualreceiveList = new ArrayList<>();
     String jonn = timenew.newtime();
     int mbz = 0;
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       Object data1 = nowRowData[0];
       String id = "";
       
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         id = data1.toString();
       } else {
         message.append("导入第" + entry.getKey() + "行数据失败，失败原因：物流单号没有输入！<br>");
         continue;
       } 
       mbz++;
       System.out.println("data1.toString()==" + data1.toString());
       
       AnnotationSku isData = this.annotationSkuDao.findByA(id);
 
 
       
       if (isData == null) {
         
         AnnotationSku actualreceiveSku = new AnnotationSku();
         
         actualreceiveSku.setJobFormId(jonn);
         actualreceiveSku.setMailno(data1.toString());
         
         SimpleDateFormat dnf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         actualreceiveSku.setCreatetime(dnf.format(new Date()));
         this.annotationSkuDao.insert(actualreceiveSku);
       } 
     } 
 
 
     
     Annotation annotation = new Annotation();
     annotation.setJobFormId(jonn);
     
     this.annotationDao.insert(annotation);
     
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + mbz + "条数据，导入成功" + mbz + "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
 
 
   
   public int aflag(Annotation annotation) {
     return this.annotationDao.aflag(annotation);
   }
 
   
   public List<Annotation> findacId(String jobFormId) {
     return this.annotationDao.findacId(jobFormId);
   }
 
   
   public List<Annotation> actadd(Map<String, Object> map) {
     return this.annotationDao.actadd(map);
   }
 
 
   
   public int countSku(Map<String, Object> pageMap) {
     return this.annotationDao.countSku(pageMap);
   }
 
   
   public int count2(AnnotationQueryVo annotationQueryVo) {
     return this.annotationDao.count2(annotationQueryVo);
   }
 
 
   
   public List<AnnotationCustom> findAll2(AnnotationQueryVo annotationQueryVo) {
     return this.annotationDao.findAll2(annotationQueryVo);
   }
 }


