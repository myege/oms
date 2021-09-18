 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Waybill;
 import com.what21.service.WaybillService;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"/entryBillList"})
 public class EntryBillListAction
   extends BaseAction
 {
   @Autowired
   private WaybillService waybillService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     String isSign = "0";
     
     userId = getUserId(userName, userId);
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("name", "findAll");
     pageMap.put("isSign", isSign);
     List<Waybill> u = this.waybillService.findAll(pageMap);
     
     int total = this.waybillService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 }


