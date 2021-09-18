 package com.what21.action;
 
 import com.what21.model.BeYf;
 import com.what21.model.Collect;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.BeYfService;
 import com.what21.service.CollectService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpSession;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/beYf"})
 public class BeYfAction
   extends BaseAction
 {
   @Autowired
   private BeYfService beYfService;
   @Autowired
   private CollectService collectService;
   
   @RequestMapping({"/findByPramid"})
   @ResponseBody
   public DatagridResultInfo findByPramid(int rows, int page, int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     map.put("startPage", Integer.valueOf((page - 1) * rows));
     map.put("endPage", Integer.valueOf(rows));
     int total = this.beYfService.countByPramid(map).intValue();
     List<BeYf> list = this.beYfService.findByPramid(map);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
 
   
   @RequestMapping({"/queryUI"})
   public String queryUI(int pramid, Model model, HttpSession session) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     Collect collect = this.collectService.findById(pramid);
     List<BeYf> list = this.beYfService.findByPramid(map);
     float zzl = 0.0F;
     float hzje = 0.0F;
     for (BeYf beYf : list) {
       hzje += Float.parseFloat(beYf.getYsyf());
       zzl += Float.parseFloat(beYf.getSjzl());
     } 
     DecimalFormat df = new DecimalFormat("0.00");
     String str_hzje = df.format(hzje);
     String str_zzl = df.format(zzl);
     model.addAttribute("pramid", Integer.valueOf(pramid));
     model.addAttribute("hzje", str_hzje);
     model.addAttribute("zzl", str_zzl);
     model.addAttribute("ds", Integer.valueOf(list.size()));
     model.addAttribute("tydh", collect.getTydh());
     model.addAttribute("sj2", collect.getSj2());
     model.addAttribute("kssj", collect.getKssj());
     model.addAttribute("jssj", collect.getJssj());
     session.setAttribute("pramid", Integer.valueOf(pramid));
     return "queryBeYf";
   }
 
 
   
   @RequestMapping({"/importBeYf"})
   public ModelAndView importBeYf(int pramid, MultipartFile excelFile) {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
       String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       excelFile.transferTo(targetFile);
       result = this.beYfService.importOrderNew(String.valueOf(path) + fileName, pramid);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   } @RequestMapping({"/queryHzjeEtc"})
   @ResponseBody
   public List<String> queryHzjeEtc(int pramid) {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     List<BeYf> list = this.beYfService.findByPramid(map);
     float zzl = 0.0F;
     float hzje = 0.0F;
     for (BeYf beYf : list) {
       hzje += Float.parseFloat(beYf.getYsyf());
       zzl += Float.parseFloat(beYf.getSjzl());
     } 
     List<String> list2 = new ArrayList<>();
     DecimalFormat df = new DecimalFormat("0.00");
     String str_hzje = df.format(hzje);
     String str_zzl = df.format(zzl);
     list2.add(str_zzl);
     list2.add(str_hzje);
     list2.add((new StringBuilder(String.valueOf(list.size()))).toString());
     return list2;
   }
 }


