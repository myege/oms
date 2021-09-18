 package com.what21.action;
 
 import com.what21.model.BeQgf;
 import com.what21.model.Collect;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.BeQgfService;
 import com.what21.service.CollectService;
 import com.what21.tools.Tools;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
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
 @RequestMapping({"/beQgf"})
 public class BeQgfAction
   extends BaseAction
 {
   @Autowired
   private BeQgfService beQgfService;
   @Autowired
   private CollectService collectService;
   
   @ResponseBody
   @RequestMapping({"/update"})
   public int update(BeQgf beQgf) throws Exception {
     try {
       this.beQgfService.update2(beQgf);
     } catch (Exception e) {
       e.printStackTrace();
       return -1;
     } 
     return 1;
   }
   
   @RequestMapping({"/updateUI"})
   public String updateUI(int pramid, Model model) throws Exception {
     BeQgf beQgf = this.beQgfService.findByPramid(pramid);
     if (beQgf == null) {
       Collect collect = this.collectService.findById(pramid);
       if (collect != null) {
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date sj = dateFormat.parse(collect.getSj());
         beQgf = new BeQgf();
         beQgf.setRq(sj);
         beQgf.setYdh(collect.getTydh());
         beQgf.setPramid(Integer.valueOf(pramid));
       } 
     } 
     model.addAttribute("beQgf", beQgf);
     return "updateQgf";
   }
   
   @RequestMapping({"/findByPramid"})
   @ResponseBody
   public DatagridResultInfo findByPramid(int rows, int page, int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("startPage", Integer.valueOf((page - 1) * rows));
     map.put("endPage", Integer.valueOf(rows));
     map.put("pramid", Integer.valueOf(pramid));
     int total = 0;
     List<BeQgf> list = null;
     total = this.beQgfService.countByPramid(map);
     list = this.beQgfService.findAllByPramid(map);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
 
   
   @RequestMapping({"/queryBsjsUI"})
   public String queryBsjsUI(int pramid, Model model) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     Collect collect = this.collectService.findById(pramid);
     int ds = this.beQgfService.countByPramid(map);
     model.addAttribute("sj2", collect.getSj2());
     model.addAttribute("ds", Integer.valueOf(ds));
     model.addAttribute("kssj", collect.getKssj());
     model.addAttribute("jssj", collect.getJssj());
     model.addAttribute("hzje", collect.getQgf());
     model.addAttribute("pramid", Integer.valueOf(pramid));
     return "queryBsjsBeQgf";
   }
   @RequestMapping({"/queryHzjeEtc"})
   @ResponseBody
   public List<String> queryHzjeEtc(int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     Collect collect = this.collectService.findById(pramid);
     int ds = this.beQgfService.countByPramid(map);
     String hzje = collect.getQgf();
     if (StringUtils.isEmpty(hzje)) {
       hzje = "0";
     }
     List<String> list = new ArrayList<>();
     list.add((new StringBuilder(String.valueOf(ds))).toString());
     list.add(hzje);
     return list;
   }
   @RequestMapping({"/queryCheckAllBsjs"})
   @ResponseBody
   public int queryCheckAllBsjs(int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Collect collect = this.collectService.findById(pramid);
     map.put("pramid", Integer.valueOf(pramid));
     int length = this.beQgfService.countByPramid(map);
     int total = this.collectService.countBySjAndSj2(collect);
     if (total == length) {
       return -1;
     }
     try {
       this.beQgfService.insertByBsjs(collect);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       this.beQgfService.deleteByPramid(pramid);
       collect.setPttsf("0");
       Tools.saveHj(collect);
       this.collectService.update(collect);
       return 0;
     } 
   }
 }


