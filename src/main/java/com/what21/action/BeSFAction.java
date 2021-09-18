 package com.what21.action;
 
 import com.what21.model.BeSF;
 import com.what21.model.Collect;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.BeSFService;
 import com.what21.service.CollectService;
 import com.what21.service.OrderBondedService;
 import com.what21.service.OrderMailService;
 import com.what21.tools.Tools;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/beSF"})
 public class BeSFAction
   extends BaseAction
 {
   @Autowired
   private BeSFService beSFService;
   @Autowired
   private CollectService collectService;
   @Autowired
   private OrderMailService orderMailService;
   @Autowired
   private OrderBondedService orderBondedService;
   
   @RequestMapping({"/findByPramid"})
   @ResponseBody
   public DatagridResultInfo findByPramid(int rows, int page, int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("startPage", Integer.valueOf((page - 1) * rows));
     map.put("endPage", Integer.valueOf(rows));
     map.put("pramid", Integer.valueOf(pramid));
     int total = 0;
     List<BeSF> list = null;
     total = this.beSFService.countByPramid(map);
     list = this.beSFService.findByPramid(map);
     if (list != null && list.size() != 0) {
       for (int i = 0; i < list.size(); i++) {
         ((BeSF)list.get(i)).setXh(i + 1 + rows * (page - 1));
       }
     }
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
 
 
   
   @RequestMapping({"/queryBesfByTydh"})
   public String queryBesfByTydh(int pramid, String tydh, Model model) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     int ds = this.beSFService.countByPramid(map);
     String hzje = this.collectService.findById(pramid).getSf();
     if (StringUtils.isEmpty(hzje)) {
       hzje = "0";
     }
     model.addAttribute("ds", Integer.valueOf(ds));
     model.addAttribute("tydh", tydh);
     model.addAttribute("hzje", hzje);
     model.addAttribute("pramid", Integer.valueOf(pramid));
     return "queryBeSf";
   }
 
   
   @RequestMapping({"/updateBeSF"})
   @ResponseBody
   public int updateBeSF(String[] ids, String zzs, String tydh) throws Exception {
     try {
       this.beSFService.update(ids, zzs, tydh);
     } catch (Exception e) {
       return -1;
     } 
     return 1;
   }
   
   @RequestMapping({"/queryHzjeEtc"})
   @ResponseBody
   public List<String> queryHzjeEtc(String tydh, int pramid) {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     int ds = this.beSFService.countByPramid(map);
     String hzje = this.collectService.findById(pramid).getSf();
     if (StringUtils.isEmpty(hzje)) {
       hzje = "0";
     }
     List<String> list2 = new ArrayList<>();
     list2.add(hzje);
     list2.add((new StringBuilder(String.valueOf(ds))).toString());
     return list2;
   }
   @RequestMapping({"/queryCheckAll"})
   @ResponseBody
   public int queryCheckAll(String tydh, int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("tydh", tydh);
     map.put("pramid", Integer.valueOf(pramid));
     int length = this.beSFService.countByPramid(map);
     int total = this.orderMailService.countByTydh(tydh);
     if (total == length) {
       return -1;
     }
     try {
       this.beSFService.findByTydh(map);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       this.beSFService.deleteByPramid(map);
       Collect collect = this.collectService.findById(pramid);
       collect.setSf("0");
       Tools.saveHj(collect);
       this.collectService.update(collect);
       return 0;
     } 
   }
   
   @RequestMapping({"/queryBesfByBsjsUI"})
   public String queryBesfByBsjsUI(int pramid, Model model) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     Collect collect = this.collectService.findById(pramid);
     int ds = this.beSFService.countByPramid(map);
     String hzje = collect.getSf();
     if (StringUtils.isEmpty(hzje)) {
       hzje = "0";
     }
     model.addAttribute("ds", Integer.valueOf(ds));
     model.addAttribute("hzje", hzje);
     model.addAttribute("sj2", collect.getSj2());
     model.addAttribute("kssj", collect.getKssj());
     model.addAttribute("jssj", collect.getJssj());
     model.addAttribute("pramid", Integer.valueOf(pramid));
     return "queryBsjsBeSf";
   }
   
   @RequestMapping({"/queryCheckAllBsjs"})
   @ResponseBody
   public int queryCheckAllBsjs(int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     Collect collect = this.collectService.findById(pramid);
     int length = this.beSFService.countByPramid(map);
     int total = this.orderBondedService.countByCollect(collect);
     if (total == length) {
       return -1;
     }
     try {
       this.beSFService.insertByBsjs(map);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       this.beSFService.deleteByPramid(map);
       collect.setSf("0");
       Tools.saveHj(collect);
       this.collectService.update(collect);
       return 0;
     } 
   }
   
   @RequestMapping({"/updateBeSFByBsjs"})
   @ResponseBody
   public int updateBeSFByBsjs(String[] ids, String zzs, int pramid) throws Exception {
     try {
       this.beSFService.update(ids, zzs, pramid);
     } catch (Exception e) {
       return -1;
     } 
     return 1;
   }
   @RequestMapping({"/editAll"})
   @ResponseBody
   public int editAll(int pramid) throws Exception {
     try {
       this.beSFService.updateAll(pramid);
       return 1;
     } catch (Exception e) {
       return 0;
     } 
   }
 }


