 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.ReceiveHtData;
 import com.what21.service.ReceiveHtDataService;
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
 @RequestMapping({"/receiveHtData"})
 public class ReceiveHtDataAction
   extends BaseAction
 {
   @Autowired
   public ReceiveHtDataService receiveHtDataService;
   
   @RequestMapping({"/receiveData"})
   public void receiveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String param = request.getParameter("bizData");
     String ret = this.receiveHtDataService.receiveData(param);
     response.getWriter().write(ret);
   }
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     int isHandle = Integer.parseInt(request.getSession().getAttribute("tz").toString());
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("isHandle", Integer.valueOf(isHandle));
     pageMap.put("startTime", request.getParameter("startTime"));
     pageMap.put("endTime", request.getParameter("endTime"));
     List<ReceiveHtData> u = this.receiveHtDataService.findAll(pageMap);
     int total = this.receiveHtDataService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/update"})
   public int update(@RequestParam(value = "id", required = true) String id) {
     int result = this.receiveHtDataService.update(id);
     return result;
   }
 }


