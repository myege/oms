 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Cost;
 import com.what21.model.LogFile;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.CostService;
 import com.what21.service.CostSkuService;
 import com.what21.service.LogFileService;
 import com.what21.service.OrderMailService;
 import com.what21.service.OrderMailSkuService;
 import com.what21.util.ResponseUtil;
 import java.math.BigDecimal;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/cost"})
 public class CostAction
   extends BaseAction
 {
   @Autowired
   public OrderMailService orderMailService;
   @Autowired
   public CostService costService;
   @Autowired
   public LogFileService logFileService;
   @Autowired
   public CostSkuService costSkuService;
   @Autowired
   public OrderMailSkuService orderMailSkuService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     List<Cost> u = this.costService.findAll(pageMap);
     int total = this.costService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   @RequestMapping({"/updateCost"})
   public String updateCost(Cost cos, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCost(cos);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/addCost"})
   public String addCost(@RequestParam(value = "userId", required = true) int userId, Cost cos, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     String str = cos.getTotanumber();
     String str2 = str.replaceAll(" ", "");
     cos.setTotanumber(str2);
     cos.setUserId(userId);
     resultTotal = this.costService.addCost(cos);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/deleteCost"})
   public int wdelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.costService.deleteCost(ids[i]);
       result = this.costSkuService.deleteCostSku(ids[i]);
     } 
     return result;
   }
 
 
   
   @RequestMapping({"/findCost"})
   public ModelAndView findCost(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "totalMailNo", required = true) String totalMailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Cost cost = this.costService.findCost(id, totalMailNo);
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.addObject("cost", cost);
     modelAndView.setViewName("print");
     return modelAndView;
   }
   
   @ResponseBody
   @RequestMapping({"/updatexg"})
   public String updatexg(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCostxg(id);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/updatehl"})
   public String updatehl(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCosthl(id);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/updatedb"})
   public String updatedb(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCostdb(id);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
   
   @ResponseBody
   @RequestMapping({"/paymentis"})
   public void Paymentis(@RequestParam(value = "id", required = true) int id, HttpServletResponse response) throws Exception {
     String result = "";
     LogFile logFile = this.logFileService.findlogzf(id);
     String bondMoney = logFile.getBondMoney();
     String bzi = logFile.getResidualMargin();
     int ids = logFile.getId();
     BigDecimal bzis = new BigDecimal(bzi);
     BigDecimal bondMoneys = new BigDecimal(bondMoney);
     Cost cost = this.costService.findCostAll(id);
     Double leadOrderTax = cost.getTotalTax();
     BigDecimal leadOrderTaxs = new BigDecimal(leadOrderTax.doubleValue());
     BigDecimal sy = bzis.subtract(leadOrderTaxs);
     if (leadOrderTaxs.compareTo(bzis) == 1) {
       result = "保证金不足";
     } else {
       BigDecimal yy = bondMoneys.subtract(sy);
       String f1 = sy.setScale(3, 4).toString();
       String f2 = yy.setScale(3, 4).toString();
       logFile.setId(ids);
       logFile.setResidualMargin(f1);
       logFile.setUsedMargin(f2);
       this.logFileService.update(logFile);
       this.costService.isPayment(id);
       result = "支付成功！";
     } 
     String str = JSONObject.toJSONString(result);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/updateCostldt"})
   public String updateCostldt(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCostldt(id);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/updateCosthkzy"})
   public String updateCosthkzy(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCosthkzy(id);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/updateCosthdtx"})
   public String updateCosthdtx(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCosthdtx(id);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
   
   @ResponseBody
   @RequestMapping({"/updateCostmg"})
   public String updateCostmg(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.costService.updateCostmg(id);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
   
   @RequestMapping({"/findcostzf"})
   public void findBond(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     String ret = this.logFileService.getTax(userId);
     response.getWriter().write(ret);
   }
 
   
   @RequestMapping({"/viewDetails"})
   public ModelAndView viewDetails(@RequestParam(value = "userId", required = true) int userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
     List<Cost> ob = this.costService.viewDetails(userId);
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.addObject("ob", ob);
     modelAndView.setViewName("viewDetail");
     return modelAndView;
   }
 
   
   @ResponseBody
   @RequestMapping({"/findByTotalMailNo"})
   public void findByTotalMailNo(@RequestParam(value = "totalMailNo", required = true) String totalMailNo, HttpServletResponse response) throws Exception {
     OrderMail orderMail = this.orderMailService.findOrderMai(totalMailNo);
     String str = JSONObject.toJSONString(orderMail);
     response.getWriter().write(str);
   }
 }


