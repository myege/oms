 package com.what21.action;
 
 import com.what21.model.BePttsf;
 import com.what21.model.Collect;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.BePttsfService;
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
 @RequestMapping({"/bePttsf"})
 public class BePttsfAction
   extends BaseAction
 {
   @Autowired
   private BePttsfService bePttsfService;
   @Autowired
   private CollectService collectService;
   @Autowired
   private OrderMailService orderMailService;
   @Autowired
   private OrderBondedService orderBondedService;
   
   @RequestMapping({"/findByPramid"})
   @ResponseBody
   public DatagridResultInfo findByPramid(int rows, String tydh, int page, int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     map.put("tydh", tydh);
     map.put("startPage", Integer.valueOf((page - 1) * rows));
     map.put("endPage", Integer.valueOf(rows));
     int total = 0;
     List<BePttsf> list = null;
     total = this.bePttsfService.countByPramid(map).intValue();
     list = this.bePttsfService.findByPramid(map);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
   
   @RequestMapping({"/queryUI"})
   public String queryUI(int pramid, Model model) throws Exception {
     Collect collect = this.collectService.findById(pramid);
     String tydh = collect.getTydh();
     model.addAttribute("pramid", Integer.valueOf(pramid));
     model.addAttribute("tydh", tydh);
     return "queryBePttsf";
   } @RequestMapping({"/queryHzjeEtc"})
   @ResponseBody
   public List<Object> queryHzjeEtc(int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     Collect collect = this.collectService.findById(pramid);
     String tydh = collect.getTydh();
     int ds = this.bePttsfService.countByPramid(map).intValue();
     String hzje = collect.getPttsf();
     if (StringUtils.isEmpty(hzje)) {
       hzje = "0";
     }
     List<Object> list = new ArrayList();
     list.add(tydh);
     list.add(Integer.valueOf(ds));
     list.add(hzje);
     return list;
   }
   @RequestMapping({"/queryCheckAll"})
   @ResponseBody
   public int queryCheckAll(String tydh, int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("tydh", tydh);
     map.put("pramid", Integer.valueOf(pramid));
     int length = this.bePttsfService.countByPramid(map).intValue();
     int total = this.orderMailService.countByTydh(tydh);
     if (total == length) {
       return -1;
     }
     try {
       this.bePttsfService.findByTydh(map);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       this.bePttsfService.deleteByPramid(map);
       Collect collect = this.collectService.findById(pramid);
       collect.setPttsf("0");
       Tools.saveHj(collect);
       this.collectService.update(collect);
       return 0;
     } 
   }
   
   @RequestMapping({"/queryBePttsfBsjsUI"})
   public String queryBePttsfBsjsUI(int pramid, Model model) throws Exception {
     Collect collect = this.collectService.findById(pramid);
     model.addAttribute("pramid", Integer.valueOf(pramid));
     model.addAttribute("kssj", collect.getKssj());
     model.addAttribute("jssj", collect.getJssj());
     model.addAttribute("sj2", collect.getSj2());
     return "queryBePttsfBsjs";
   }
 
   
   @RequestMapping({"/queryCheckAllBsjs"})
   @ResponseBody
   public int queryCheckAllBsjs(int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Collect collect = this.collectService.findById(pramid);
     map.put("pramid", Integer.valueOf(pramid));
     int length = this.bePttsfService.countByPramid(map).intValue();
     int total = this.orderBondedService.countByCollect(collect);
     if (total == length) {
       return -1;
     }
     try {
       this.bePttsfService.insertByBsjs(map);
       return 1;
     } catch (Exception e) {
       this.bePttsfService.deleteByPramid(map);
       collect.setPttsf("0");
       Tools.saveHj(collect);
       this.collectService.update(collect);
       return 0;
     } 
   }
 }


