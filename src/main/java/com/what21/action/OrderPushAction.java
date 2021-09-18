 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.GoodsPush;
 import com.what21.model.OrderPush;
 import com.what21.service.GoodsPushService;
 import com.what21.service.OrderPushService;
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
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/orderPush"})
 public class OrderPushAction
   extends BaseAction
 {
   @Autowired
   private OrderPushService orderService;
   @Autowired
   private GoodsPushService goodsService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     List<OrderPush> u = this.orderService.findAll(pageMap);
     int total = this.orderService.count(userId);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/importOrder"})
   public ModelAndView importOrder(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
 
       
       excelFile.transferTo(targetFile);
 
       
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.orderService.importOrder(String.valueOf(path) + fileName, userId);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.csetCode(Integer.valueOf(-1)).csetMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       if (targetFile.exists()) {
         targetFile.delete();
       }
       return modelAndView;
     } 
   }
 
   
   @RequestMapping({"/push"})
   public void push(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String ids = request.getParameter("ids");
     String[] arr = ids.split(",");
     
     StringBuffer res = new StringBuffer();
     for (int i = 0; i < arr.length; i++) {
       String id = arr[i];
       OrderPush order = this.orderService.findById(Integer.valueOf(id).intValue());
       String expressNumber = order.getExpressNumber();
       List<GoodsPush> goodsList = this.goodsService.findByExpressNumber(expressNumber);
       String result = "";
       if (order.getPushType() == 0) {
         result = this.orderService.push(order, goodsList);
       }
       if (result.indexOf("true") >= 0) {
         this.orderService.update(order.getExpressNumber());
       } else {
         res.append("单号：" + expressNumber + "没有推送成功！");
       } 
     } 
     if (res != null) {
       response.getWriter().write(res.toString());
     } else {
       response.getWriter().write("推送成功！");
     } 
   }
 }


