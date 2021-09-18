 package com.what21.action;
 
 import com.what21.model.AutoCheckList;
 import com.what21.model.AutoCheckListVo;
 import com.what21.model.Company;
 import com.what21.model.PageQuery;
 import com.what21.result.AjaxResult;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.AutoCheckListService;
 import com.what21.service.CompanyService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @RequestMapping({"/autochecklist"})
 @Controller
 public class AutoCheckListAction
   extends BaseAction
 {
   @Autowired
   private AutoCheckListService autoCheckListService;
   @Autowired
   private CompanyService companyService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int page, int rows, AutoCheckListVo autoCheckListVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     autoCheckListVo.setPageQuery(pageQuery);
     int total = this.autoCheckListService.count(autoCheckListVo);
     List<AutoCheckList> list = this.autoCheckListService.findAll(autoCheckListVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
   
   @RequestMapping({"/editUi"})
   public String edit(Model model, Integer id) {
     AutoCheckList autoCheckList = this.autoCheckListService.findById(id);
     List<Company> companys = this.companyService.findCompanybmAndName();
     model.addAttribute("companys", companys);
     model.addAttribute("autoCheckList", autoCheckList);
     return "/autochecklist/editautochecklist";
   }
   
   @RequestMapping({"/getCompanyName"})
   @ResponseBody
   public Company getCompanyName(String companybm) {
     Company company = this.companyService.findCompanyNameByCompanybm(companybm);
     return company;
   }
   
   @ResponseBody
   @RequestMapping({"/edit"})
   public int edit(AutoCheckList autoCheckList) {
     int i = 0;
     if (autoCheckList.getId() == null) {
       i = this.autoCheckListService.save(autoCheckList);
     } else {
       i = this.autoCheckListService.updateById(autoCheckList);
     } 
     return i;
   }
   
   @RequestMapping({"/remove"})
   @ResponseBody
   public AjaxResult remove(String[] ids) {
     try {
       return toAjax(this.autoCheckListService.deleteCompanyByIds(ids));
     } catch (Exception e) {
       return error(e.getMessage());
     } 
   }
 }


