 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.CostDao;
 import com.what21.dao.CostSkuDao;
 import com.what21.model.Cost;
 import com.what21.model.CostSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.CostService;
 import com.what21.service.CostSkuService;
 import com.what21.service.OrderMailSkuService;
 import com.what21.util.ResponseUtil;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/costsku"})
 public class CostSkuAction
 {
   @Autowired
   public OrderMailSkuService orderMailSkuService;
   @Autowired
   public CostSkuService costskuService;
   @Autowired
   public CostSkuDao costSkuDao;
   @Autowired
   public CostDao costDao;
   @Autowired
   public CostService costService;
   
   @ResponseBody
   @RequestMapping({"/addCostsku"})
   public int addCostsku(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = true) int id) throws Exception {
     int result = 0;
     Double freightPrice = Double.valueOf(Double.parseDouble(request.getParameter("freightPrice")));
     Double freightNumber = Double.valueOf(Double.parseDouble(request.getParameter("freightNumber")));
     Double freightMoney = Double.valueOf(Double.parseDouble(request.getParameter("freightMoney")));
     String freightRemark = request.getParameter("freightRemark");
     Double operationPrice = Double.valueOf(Double.parseDouble(request.getParameter("operationPrice")));
     Integer operationNumber = Integer.valueOf(Integer.parseInt(request.getParameter("operationNumber")));
     Double operationMoney = Double.valueOf(Double.parseDouble(request.getParameter("operationMoney")));
     String operationRemark = request.getParameter("operationRemark");
     Double groundPrice = Double.valueOf(Double.parseDouble(request.getParameter("groundPrice")));
     Double groundNumber = Double.valueOf(Double.parseDouble(request.getParameter("groundNumber")));
     Double groundMoney = Double.valueOf(Double.parseDouble(request.getParameter("groundMoney")));
     String groundRemark = request.getParameter("groundRemark");
     String taxRemark = request.getParameter("taxRemark");
     String platformRemark = request.getParameter("platformRemark");
     Double total = Double.valueOf(Double.parseDouble(request.getParameter("total")));
     CostSku cs = new CostSku();
     cs.setCostId(id);
     cs.setFreightPrice(freightPrice);
     cs.setFreightNumber(freightNumber);
     cs.setFreightMoney(freightMoney);
     cs.setFreightRemark(freightRemark);
     cs.setOperationPrice(operationPrice);
     cs.setOperationNumber(operationNumber.intValue());
     cs.setOperationMoney(operationMoney);
     cs.setOperationRemark(operationRemark);
     cs.setGroundPrice(groundPrice);
     cs.setGroundNumber(groundNumber);
     cs.setGroundMoney(groundMoney);
     cs.setGroundRemark(groundRemark);
     cs.setTaxRemark(taxRemark);
     cs.setPlatformRemark(platformRemark);
     cs.setTotal(total);
     result = this.costskuService.addCostSku(cs);
     Cost cost = new Cost();
     cost.setId(id);
     cost.setTotalTax(total);
     cost.setSurplusFunds(total);
     this.costDao.updateCostTotalTax(cost);
     if (result > 0) {
       result = this.costService.updateCostisTj(id);
     }
     return result;
   }
   @ResponseBody
   @RequestMapping({"/updateCostsku"})
   public String updateCostsku(CostSku costSku, @RequestParam(value = "id", required = true) int id, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     costSku.setCostId(id);
     int costId = costSku.getCostId();
     Cost cost = new Cost();
     cost.setId(costId);
     Double totalTax = costSku.getTotal();
     cost.setTotalTax(totalTax);
     cost.setSurplusFunds(totalTax);
     this.costDao.updateCostTotalTax(cost);
     resultTotal = this.costskuService.updateCostSku(costSku);
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
   @RequestMapping({"/findCostSku"})
   public void findCostSku(CostSku costsku, @RequestParam(value = "id", required = true) String id, OrderSearchDto orderSearchDto, @RequestParam(value = "totalMailNo", required = true) String totalMailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Cost cost = this.costService.findCost(id, totalMailNo);
     String str = JSONObject.toJSONString(cost);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/findById"})
   public void findById(CostSku costsku, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = true) String id, @RequestParam(value = "totalMailNo", required = true) String totalMailNo) throws Exception {
     CostSku costSku = this.costskuService.findById(id, totalMailNo);
     String str = JSONObject.toJSONString(costSku);
     
     response.getWriter().write(str);
   }
 }


