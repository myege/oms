 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.GylFinance;
 import com.what21.model.OrderBonded;
 import com.what21.service.GylFinanceService;
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
 @Controller
 @RequestMapping({"/gylFinance"})
 public class GylFinanceAction
   extends BaseAction
 {
   @Autowired
   public GylFinanceService gylFinanceService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     String startTime = request.getParameter("startTime");
     String endTime = request.getParameter("endTime");
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     int confirmStatus = Integer.parseInt(request.getSession().getAttribute("tz").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
 
 
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("confirmStatus", Integer.valueOf(confirmStatus));
     pageMap.put("startTime", startTime);
     pageMap.put("endTime", endTime);
     List<GylFinance> u = this.gylFinanceService.findAll(pageMap);
     int total = this.gylFinanceService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/findOrderByNumber"})
   public void findOrderByNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     String gylFinanceNumber = request.getParameter("gylFinanceNumber");
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     int confirmStatus = Integer.parseInt(request.getSession().getAttribute("tz").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
 
 
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("confirmStatus", Integer.valueOf(confirmStatus));
     pageMap.put("gylFinanceNumber", gylFinanceNumber);
     List<OrderBonded> u = this.gylFinanceService.findOrderByNumber(pageMap);
     int total = this.gylFinanceService.countByNumber(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/confirm"})
   public int confirm(@RequestParam(value = "gylFinanceNumber", required = true) String gylFinanceNumber, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String userName = request.getSession().getAttribute("userName").toString();
     return this.gylFinanceService.updateConfirm(userName, gylFinanceNumber);
   }
 }


