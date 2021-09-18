 package com.what21.action;
 
 import com.what21.model.Company;
 import com.what21.model.CompanyQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.result.AjaxResult;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.CompanyService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/company"})
 public class CompanyAction
   extends BaseAction
 {
   @Autowired
   private CompanyService companyService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int page, int rows, CompanyQueryVo companyQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     companyQueryVo.setPageQuery(pageQuery);
     int total = this.companyService.count(companyQueryVo);
     List<Company> list = this.companyService.findAll(companyQueryVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
   
   @RequestMapping({"/editUi"})
   public String editUi(Model model, Integer id) {
     Company company = this.companyService.findById(id);
     model.addAttribute("company", company);
     return "/company/editCompany";
   }
   
   @ResponseBody
   @RequestMapping({"/edit"})
   public int edit(Company company) {
     int i = 0;
     if (company.getId() == null) {
       i = this.companyService.save(company);
     } else {
       i = this.companyService.updateById(company);
     } 
     return i;
   }
   
   @RequestMapping({"/changeAuto"})
   @ResponseBody
   public AjaxResult changeStatus(Company company) {
     return toAjax(this.companyService.changeAuto(company));
   }
   
   @RequestMapping({"/remove"})
   @ResponseBody
   public AjaxResult remove(String[] ids) {
     try {
       return toAjax(this.companyService.deleteCompanyByIds(ids));
     } catch (Exception e) {
       return error(e.getMessage());
     } 
   }
 }


