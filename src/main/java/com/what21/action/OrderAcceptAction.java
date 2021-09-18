 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.GoodsAccept;
 import com.what21.model.OrderAccept;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.GoodsAcceptService;
 import com.what21.service.OrderAcceptService;
 import com.what21.util.GeneralResult;
 import java.io.File;
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
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/orderAccept"})
 public class OrderAcceptAction
   extends BaseAction
 {
   @Autowired
   private OrderAcceptService orderAcceptService;
   @Autowired
   private GoodsAcceptService goodsAcceptService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response, OrderSearchDto orderSearchDto) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     int printType = Integer.parseInt(orderSearchDto.getParamValue());
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("printType", Integer.valueOf(printType));
     List<OrderAccept> orderList = this.orderAcceptService.findAll(pageMap);
     for (OrderAccept orderAccept : orderList) {
       List<GoodsAccept> gList = this.goodsAcceptService.findByExpressNumber(orderAccept.getExpressNumber());
       orderAccept.setGoodsList(gList);
     } 
     int total = this.orderAcceptService.count(pageMap);
     map.put("rows", orderList);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   @ResponseBody
   @RequestMapping({"/findExpressNumber"})
   public Map<String, Object> findExpressNumber(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String bb = orderSearchDto.getParamValue();
     int printType = orderSearchDto.getType();
     
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     int ac = Integer.parseInt(orderSearchDto.getParamName());
     
     if (ac == 3 && (!bb.equals("") || bb != null || bb != "")) {
       int aa = 0;
       if (bb.equals("未") || bb.equals("未打") || bb.equals("未打印")) {
         aa = 0;
         pageMap.put("paramValue", Integer.valueOf(aa));
         pageMap.put("type", Integer.valueOf(ac));
         pageMap.put("userId", Integer.valueOf(userId));
       } else if (bb.equals("已") || bb.equals("已打") || bb.equals("已打印")) {
         aa = 1;
         pageMap.put("paramValue", Integer.valueOf(aa));
         pageMap.put("type", Integer.valueOf(ac));
         pageMap.put("userId", Integer.valueOf(userId));
       } else {
         pageMap.put("paramValue", orderSearchDto.getParamValue());
         pageMap.put("type", Integer.valueOf(ac));
         pageMap.put("userId", Integer.valueOf(userId));
       } 
     } else {
       pageMap.put("paramValue", orderSearchDto.getParamValue());
       pageMap.put("type", orderSearchDto.getParamName());
       pageMap.put("userId", Integer.valueOf(userId));
     } 
     pageMap.put("printType", Integer.valueOf(printType));
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<OrderAccept> orderList = this.orderAcceptService.findExpressNumber(pageMap);
     for (OrderAccept orderAccept : orderList) {
       List<GoodsAccept> gList = this.goodsAcceptService.findByExpressNumber(orderAccept.getExpressNumber());
       orderAccept.setGoodsList(gList);
     } 
     int total = this.orderAcceptService.count(pageMap);
     map.put("rows", orderList);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
   
   @RequestMapping({"/importOrder"})
   @ResponseBody
   public Map<String, String> importOrder(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     Map<String, String> map = new HashMap<>();
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     String i = "0";
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
 
       
       excelFile.transferTo(targetFile);
 
       
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.orderAcceptService.importOrder(String.valueOf(path) + fileName, userId);
       if (result.getMessage().contains("导入成功")) {
         i = "1";
       } else {
         i = "0";
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       result.csetCode(Integer.valueOf(-1)).csetMessage(ex.getMessage());
       result.setMessage("导入失败！");
       i = "0";
       if (targetFile.exists()) {
         targetFile.delete();
       }
     } 
     map.put("code", i);
     map.put("msg", result.getMessage());
     return map;
   }
 
   
   @RequestMapping({"/updatePrintType"})
   public void push(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String ids = request.getParameter("ids");
     
     ids = ids.substring(0, ids.length() - 1);
     String[] arr = ids.split(",");
     for (int i = 0; i < arr.length; i++) {
       String id = arr[i];
       this.orderAcceptService.updatePrintType(Integer.valueOf(id).intValue());
     } 
     response.getWriter().write("打印成功！");
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/deleteOrderAccept"})
   public int deleteOrderAccept(@RequestParam(value = "expressNumber", required = true) String expressNumber) throws Exception {
     String[] expressNumbers = expressNumber.split(",");
 
 
 
     
     return this.orderAcceptService.delete(expressNumbers);
   }
   
   @ResponseBody
   @RequestMapping({"/matching"})
   public int matching(String ids, String name, HttpServletRequest request, HttpServletResponse response) {
     return this.orderAcceptService.updateExpressmatch(ids, name);
   }
 }


