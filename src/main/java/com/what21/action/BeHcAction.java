 package com.what21.action;
 
 import com.what21.model.BeHc;
 import com.what21.model.Collect;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.BeHcService;
 import com.what21.service.CollectService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpSession;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/beHc"})
 public class BeHcAction
   extends BaseAction
 {
   @Autowired
   private BeHcService beHcService;
   @Autowired
   private CollectService collectService;
   
   @RequestMapping({"/findByPramid"})
   @ResponseBody
   public DatagridResultInfo findByPramid(int rows, int page, int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     map.put("startPage", Integer.valueOf((page - 1) * rows));
     map.put("endPage", Integer.valueOf(rows));
     int total = this.beHcService.countByPramid(map).intValue();
     List<BeHc> list = this.beHcService.findByPramid(map);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
   
   @RequestMapping({"/queryBsjsUI"})
   public String queryBsjsUI(int pramid, Model model, HttpSession session) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     Collect collect = this.collectService.findById(pramid);
     int ds = this.beHcService.countByPramid(map).intValue();
     model.addAttribute("sj2", collect.getSj2());
     model.addAttribute("ds", Integer.valueOf(ds));
     model.addAttribute("kssj", collect.getKssj());
     model.addAttribute("jssj", collect.getJssj());
     model.addAttribute("hzje", collect.getHcf());
     model.addAttribute("tydh", collect.getTydh());
     model.addAttribute("pramid", Integer.valueOf(pramid));
     session.setAttribute("pramid", Integer.valueOf(pramid));
     return "queryBsjsBeHc";
   }
 
 
   
   @RequestMapping({"/importBeHc"})
   public ModelAndView importBeHc(int pramid, MultipartFile excelFile) {
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
       result = this.beHcService.importOrderNew(String.valueOf(path) + fileName, pramid);
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
   public List<String> queryHzjeEtc(int pramid) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("pramid", Integer.valueOf(pramid));
     int ds = this.beHcService.countByPramid(map).intValue();
     String hzje = this.collectService.findById(pramid).getHcf();
     if (StringUtils.isEmpty(hzje)) {
       hzje = "0";
     }
     List<String> list = new ArrayList<>();
     list.add(hzje);
     list.add((new StringBuilder(String.valueOf(ds))).toString());
     return list;
   }
 }


