 package com.what21.service.impl;
 
 import com.what21.dao.WaybillDao;
 import com.what21.model.Waybill;
 import com.what21.service.WaybillService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ImportXxlsForWaybill;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class WaybillServiceImpl
   implements WaybillService
 {
   @Autowired
   private WaybillDao waybillDao;
   
   public List<Waybill> findAll(Map<String, Object> map) throws Exception {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     List<Waybill> findAll = this.waybillDao.findAll(map);
     for (Waybill waybill : findAll) {
       if (waybill.getIsSign() == 1) {
         String endTime = waybill.getEndTime();
         Date date1 = sdf.parse(endTime);
         String creatIme = waybill.getAcceptTime();
         Date date2 = sdf.parse(creatIme);
         long p = date1.getTime() - date2.getTime();
         long pres = p / 3600000L;
         String presc = String.valueOf(pres);
         waybill.setSx(presc);
       } 
     } 
     return findAll;
   }
 
 
   
   public int count(Map<String, Object> map) {
     return this.waybillDao.count(map);
   }
 
   
   public void insert(Waybill waybill) {
     this.waybillDao.insert(waybill);
   }
 
 
 
   
   public List<Waybill> findId(Map<String, Object> map) {
     return this.waybillDao.findId(map);
   }
 
 
   
   public List<Waybill> findSearch(Map<String, Object> map) {
     return this.waybillDao.findSearch(map);
   }
 
 
   
   public List<Waybill> findIsSign(Map<String, Object> map) {
     return this.waybillDao.findIsSign(map);
   }
 
 
   
   public int delete(Map<String, Object> map) {
     return this.waybillDao.delete(map);
   }
 
 
   
   public Waybill findByExpressNumber(Map<String, Object> map) {
     return this.waybillDao.findByExpressNumber(map);
   }
 
 
   
   public List<Waybill> dateTime(Map<String, Object> map) {
     return this.waybillDao.dateTime(map);
   }
 
   
   public GeneralResult importOrder(String path, int userId) {
     GeneralResult result = new GeneralResult();
     try {
       ImportXxlsForWaybill howto = new ImportXxlsForWaybill();
       howto.setPid(Integer.valueOf(userId));
       howto.processOneSheet(path, 1);
       if (howto.getSb().toString().contains("导入失败")) {
         result.setMessage(howto.getSb().toString());
         return result;
       } 
       List<Waybill> list = howto.getList();
       int total = list.size();
       int step = 1000;
       int num = (total % step == 0) ? (total / step) : (total / step + 1);
       for (int i = 0; i < num; i++) {
         if (i != num - 1) {
           
           this.waybillDao.insertList(list.subList(i * step, (i + 1) * step));
         } else {
           
           this.waybillDao.insertList(list.subList(i * step, total));
         } 
       } 
       result.setMessage("导入成功" + total + "条！");
       return result;
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
       
       return result;
     } 
   }
   
   public List<Waybill> findIdArray(String[] arr) {
     return this.waybillDao.findIdArray(arr);
   }
 }


