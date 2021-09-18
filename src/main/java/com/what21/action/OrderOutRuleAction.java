 package com.what21.action;
 
 import com.what21.model.OrderOutRule;
 import com.what21.model.OrderOutRuleQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.OrderOutRuleService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/orderOutRule"})
 public class OrderOutRuleAction
   extends BaseAction
 {
   @Autowired
   private OrderOutRuleService orderOutRuleService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(OrderOutRuleQueryVo orderOutRuleQueryVo, int rows, int page) throws Exception {
     orderOutRuleQueryVo = (orderOutRuleQueryVo == null) ? new OrderOutRuleQueryVo() : orderOutRuleQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     orderOutRuleQueryVo.setPageQuery(pageQuery);
     int total = this.orderOutRuleService.count(orderOutRuleQueryVo).intValue();
     List<OrderOutRule> list = this.orderOutRuleService.find(orderOutRuleQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
   
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public ResultInfo deleteByIds(Integer[] ids) throws Exception {
     try {
       return this.orderOutRuleService.deleteByIds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
   
   @RequestMapping({"/addUI"})
   public String addUI() {
     return "addOrderOutRule";
   }
   @RequestMapping({"/add"})
   @ResponseBody
   public ResultInfo add(OrderOutRule orderOutRule) throws Exception {
     try {
       return this.orderOutRuleService.insert(orderOutRule);
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
 
   
   @RequestMapping({"/updateUI"})
   public String updateUI(Integer id, Model model) {
     OrderOutRule orderOutRule = this.orderOutRuleService.findById(id);
     model.addAttribute("orderOutRule", orderOutRule);
     return "updateOrderOutRule";
   }
   @RequestMapping({"/update"})
   @ResponseBody
   public ResultInfo update(OrderOutRule orderOutRule) throws Exception {
     try {
       return this.orderOutRuleService.update(orderOutRule);
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
 }


