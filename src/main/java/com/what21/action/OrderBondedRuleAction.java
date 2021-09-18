 package com.what21.action;
 
 import com.what21.model.OrderBondedRule;
 import com.what21.model.OrderBondedRuleQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.OrderBondedRuleService;
 import com.what21.tools.Tools;
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
 @RequestMapping({"/orderBondedRule"})
 public class OrderBondedRuleAction
   extends BaseAction
 {
   @Autowired
   private OrderBondedRuleService orderBondedRuleService;
   
   @RequestMapping({"/updateRule"})
   @ResponseBody
   public int updateRule(OrderBondedRule orderBondedRule) throws Exception {
     int i = 0;
     if (orderBondedRule.getId() == null) {
       orderBondedRule.setCreatetime(Tools.getCurrentTime());
       i = this.orderBondedRuleService.insert(orderBondedRule);
     } else {
       i = this.orderBondedRuleService.update(orderBondedRule);
     } 
     return i;
   }
 
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(OrderBondedRuleQueryVo orderBondedRuleQueryVo, int rows, int page) throws Exception {
     orderBondedRuleQueryVo = (orderBondedRuleQueryVo == null) ? new OrderBondedRuleQueryVo() : orderBondedRuleQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     orderBondedRuleQueryVo.setPageQuery(pageQuery);
     int total = this.orderBondedRuleService.countAll(orderBondedRuleQueryVo).intValue();
     List<OrderBondedRule> list = this.orderBondedRuleService.findAll(orderBondedRuleQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
 
 
   
   @RequestMapping({"/importList"})
   public ModelAndView importStojsQd(MultipartFile excelFile, HttpSession session) {
     Integer id = (Integer)session.getAttribute("stojsid");
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
       result = this.orderBondedRuleService.importOrderNew(id, String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
 
 
   
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public ResultInfo deleteByIds(int[] ids) throws Exception {
     try {
       return this.orderBondedRuleService.deleteByIds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
   
   @RequestMapping({"/addUI"})
   public String addUI() {
     return "addOrderBondedRule";
   }
 
   
   @RequestMapping({"/updateUI"})
   public String updateUI(Integer id, Model model) {
     OrderBondedRule orderBondedRule = this.orderBondedRuleService.findById(id);
     model.addAttribute("orderBondedRule", orderBondedRule);
     return "updateOrderBondedRule";
   }
 }


