 package com.what21.action;
 
 import com.what21.model.CustomsParamCustom;
 import com.what21.model.CustomsParamQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.model.TCustomsParam;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.CustomsParamService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.List;
 import javax.servlet.http.HttpSession;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/customsParam"})
 public class CustomsParamAction
   extends BaseAction
 {
   @Autowired
   private CustomsParamService customsParamService;
   
   @RequestMapping({"/find"})
   @ResponseBody
   public DatagridResultInfo findAll(int rows, int page, CustomsParamQueryVo customDepositQueryVo) throws Exception {
     customDepositQueryVo = (customDepositQueryVo == null) ? new CustomsParamQueryVo() : customDepositQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     customDepositQueryVo.setPageQuery(pageQuery);
     int total = this.customsParamService.count(customDepositQueryVo).intValue();
     List<CustomsParamCustom> list = this.customsParamService.find(customDepositQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
   
   @RequestMapping({"/addUI"})
   public String addUI() {
     return "addCustomsParam";
   }
   @RequestMapping({"/add"})
   @ResponseBody
   public ResultInfo add(CustomsParamQueryVo customDepositQueryVo) throws Exception {
     try {
       return this.customsParamService.insert((TCustomsParam)customDepositQueryVo.getCustomsParamCustom());
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
 
   
   @RequestMapping({"/updateUI"})
   public String updateUI(Integer id, Model model) {
     CustomsParamCustom customsParamCustom = this.customsParamService.findById(id);
     model.addAttribute("customsParamCustom", customsParamCustom);
     return "updateCustomsParam";
   }
   @RequestMapping({"/update"})
   @ResponseBody
   public ResultInfo update(CustomsParamQueryVo customDepositQueryVo) throws Exception {
     try {
       return this.customsParamService.update((TCustomsParam)customDepositQueryVo.getCustomsParamCustom());
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public ResultInfo deleteByIds(int[] ids) throws Exception {
     try {
       return this.customsParamService.deleteByIds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
   
   @RequestMapping({"/importCustomsParam"})
   public ModelAndView importCustomsParam(MultipartFile excelFile, HttpSession session) {
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
       result = this.customsParamService.importOrderNew(String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
 }


