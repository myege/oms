 package com.what21.service.impl;
 
 import com.what21.dao.StojsDao;
 import com.what21.model.PWC;
 import com.what21.model.Stojs;
 import com.what21.model.StojsBb;
 import com.what21.model.StojsQd;
 import com.what21.model.StojsQueryVo;
 import com.what21.service.StojsService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ImportXxlsForStojsQd;
 import java.math.RoundingMode;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 
 @Transactional
 @Service
 public class StojsServiceImpl
   implements StojsService
 {
   @Autowired
   private StojsDao stojsDao;
   
   public List<Stojs> findAll(StojsQueryVo stojsQueryVo) {
     return this.stojsDao.findAll(stojsQueryVo);
   }
 
   
   public Integer count(StojsQueryVo stojsQueryVo) {
     return this.stojsDao.count(stojsQueryVo);
   }
 
   
   public List<StojsQd> findAllQd(StojsQueryVo stojsQueryVo) {
     return this.stojsDao.findAllQd(stojsQueryVo);
   }
 
   
   public Integer countQd(StojsQueryVo stojsQueryVo) {
     return this.stojsDao.countQd(stojsQueryVo);
   }
 
   
   public void insert(Stojs stojs) {
     this.stojsDao.insert(stojs);
   }
   
   public void deleteByIds(Integer[] ids) {
     this.stojsDao.deleteByIds(ids);
   }
 
 
 
   
   public GeneralResult importOrderNew(Integer pid, String string) {
     GeneralResult result = new GeneralResult();
     Stojs stojs = this.stojsDao.findById(pid);
     if (stojs == null) {
       result.setMessage("导入失败，不存在的主订单ID！");
       return result;
     } 
     
     try {
       ImportXxlsForStojsQd howto = new ImportXxlsForStojsQd();
       howto.setPid(pid);
       howto.processOneSheet(string, 1);
       if (howto.getSb().toString().contains("导入失败")) {
         result.setMessage(howto.getSb().toString());
         return result;
       } 
       List<StojsQd> list = howto.getList();
       int total = list.size();
       int step = 1000;
       int num = (total % step == 0) ? (total / step) : (total / step + 1);
       for (int i = 0; i < num; i++) {
         if (i != num - 1) {
           this.stojsDao.insertQdBatch(list.subList(i * step, (i + 1) * step));
         } else {
           this.stojsDao.insertQdBatch(list.subList(i * step, total));
         } 
       } 
       
       this.stojsDao.updateQdztById(pid);
       result.setMessage("导入成功" + total + "条！");
       return result;
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
       
       return result;
     } 
   }
   
   public void geneBb(Integer id) {
     Stojs stojs = this.stojsDao.findById(id);
     if ("1".equals(stojs.getBbzt())) {
       throw new IllegalArgumentException("该订单已经生成报表了!");
     }
     DecimalFormat df = new DecimalFormat("0.00");
     df.setRoundingMode(RoundingMode.HALF_UP);
     Map<String, StojsBb> map = new HashMap<>();
     List<PWC> list1 = this.stojsDao.getPWCs1(id);
     List<PWC> list2 = this.stojsDao.getPWCs2(id);
     List<PWC> list3 = this.stojsDao.getPWCs3(id);
     List<PWC> list4 = this.stojsDao.getPWCs4(id);
     List<PWC> list5 = this.stojsDao.getPWCs5(id);
     for (PWC pwc : list1) {
       String province = pwc.getProvince();
       StojsBb stojsBb = map.get(province);
       String weight = pwc.getWeight();
       float f = Float.parseFloat(weight);
       weight = df.format(f);
       if (stojsBb == null) {
         stojsBb = new StojsBb();
         stojsBb.setPid(id);
         stojsBb.setProvince(province);
         stojsBb.setLine1dl(pwc.getCount());
         stojsBb.setLine1weight(weight);
       } else {
         stojsBb.setLine1dl(pwc.getCount());
         stojsBb.setLine1weight(weight);
       } 
       map.put(province, stojsBb);
     } 
     
     for (PWC pwc : list2) {
       String province = pwc.getProvince();
       StojsBb stojsBb = map.get(province);
       String weight = pwc.getWeight();
       float f = Float.parseFloat(weight);
       weight = df.format(f);
       if (stojsBb == null) {
         stojsBb = new StojsBb();
         stojsBb.setPid(id);
         stojsBb.setProvince(province);
         stojsBb.setLine2dl(pwc.getCount());
         stojsBb.setLine2weight(weight);
       } else {
         stojsBb.setLine2dl(pwc.getCount());
         stojsBb.setLine2weight(weight);
       } 
       map.put(province, stojsBb);
     } 
     
     for (PWC pwc : list3) {
       String province = pwc.getProvince();
       StojsBb stojsBb = map.get(province);
       String weight = pwc.getWeight();
       float f = Float.parseFloat(weight);
       weight = df.format(f);
       if (stojsBb == null) {
         stojsBb = new StojsBb();
         stojsBb.setPid(id);
         stojsBb.setProvince(province);
         stojsBb.setLine3dl(pwc.getCount());
         stojsBb.setLine3weight(weight);
       } else {
         stojsBb.setLine3dl(pwc.getCount());
         stojsBb.setLine3weight(weight);
       } 
       map.put(province, stojsBb);
     } 
     
     for (PWC pwc : list4) {
       String province = pwc.getProvince();
       StojsBb stojsBb = map.get(province);
       String weight = pwc.getWeight();
       float f = Float.parseFloat(weight);
       weight = df.format(f);
       if (stojsBb == null) {
         stojsBb = new StojsBb();
         stojsBb.setPid(id);
         stojsBb.setProvince(province);
         stojsBb.setLine4dl(pwc.getCount());
         stojsBb.setLine4weight(weight);
       } else {
         stojsBb.setLine4dl(pwc.getCount());
         stojsBb.setLine4weight(weight);
       } 
       map.put(province, stojsBb);
     } 
     
     for (PWC pwc : list5) {
       String province = pwc.getProvince();
       StojsBb stojsBb = map.get(province);
       String weight = pwc.getWeight();
       float f = Float.parseFloat(weight);
       weight = df.format(f);
       if (stojsBb == null) {
         stojsBb = new StojsBb();
         stojsBb.setPid(id);
         stojsBb.setProvince(province);
         stojsBb.setLine5dl(pwc.getCount());
         stojsBb.setLine5weight(weight);
       } else {
         stojsBb.setLine5dl(pwc.getCount());
         stojsBb.setLine5weight(weight);
       } 
       map.put(province, stojsBb);
     } 
     
     List<StojsBb> bbs = new ArrayList<>();
     bbs.addAll(map.values());
     
     for (StojsBb stojsBb : bbs) {
       if (stojsBb.getLine1dl() == null) {
         stojsBb.setLine1dl(Integer.valueOf(0));
       }
       if (stojsBb.getLine2dl() == null) {
         stojsBb.setLine2dl(Integer.valueOf(0));
       }
       if (stojsBb.getLine3dl() == null) {
         stojsBb.setLine3dl(Integer.valueOf(0));
       }
       if (stojsBb.getLine4dl() == null) {
         stojsBb.setLine4dl(Integer.valueOf(0));
       }
       if (stojsBb.getLine5dl() == null) {
         stojsBb.setLine5dl(Integer.valueOf(0));
       }
       if (stojsBb.getLine1weight() == null) {
         stojsBb.setLine1weight("0.00");
       }
       if (stojsBb.getLine2weight() == null) {
         stojsBb.setLine2weight("0.00");
       }
       if (stojsBb.getLine3weight() == null) {
         stojsBb.setLine3weight("0.00");
       }
       if (stojsBb.getLine4weight() == null) {
         stojsBb.setLine4weight("0.00");
       }
       if (stojsBb.getLine5weight() == null) {
         stojsBb.setLine5weight("0.00");
       }
     } 
     this.stojsDao.inserBbBatch(bbs);
     this.stojsDao.updateBbztById(id);
   }
 
 
 
   
   public Integer countBb(StojsQueryVo stojsQueryVo) {
     return this.stojsDao.countBb(stojsQueryVo);
   }
 
   
   public List<StojsBb> findAllBb(StojsQueryVo stojsQueryVo) {
     return this.stojsDao.findAllBb(stojsQueryVo);
   }
 
   
   public PWC getPWCs1NotGroup(Integer id) {
     return this.stojsDao.getPWCs1NotGroup(id);
   }
 
   
   public PWC getPWCs2NotGroup(Integer id) {
     return this.stojsDao.getPWCs2NotGroup(id);
   }
 
   
   public PWC getPWCs3NotGroup(Integer id) {
     return this.stojsDao.getPWCs3NotGroup(id);
   }
 
   
   public PWC getPWCs4NotGroup(Integer id) {
     return this.stojsDao.getPWCs4NotGroup(id);
   }
 
   
   public PWC getPWCs5NotGroup(Integer id) {
     return this.stojsDao.getPWCs5NotGroup(id);
   }
 }


