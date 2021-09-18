 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.TimedTask;
 import com.what21.service.TimedTaskService;
 import com.what21.util.ResponseUtil;
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
 @RequestMapping({"timedTask"})
 public class TimedTaskAction
   extends BaseAction
 {
   @Autowired
   private TimedTaskService timedTaskService;
   
   @RequestMapping({"findAll"})
   public void finAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int pStart = Integer.parseInt(request.getParameter("page"));
     int pEnd = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pMap = new HashMap<>();
     map.put("pStart", Integer.valueOf((pStart - 1) * pEnd));
     map.put("pEnd", Integer.valueOf(pEnd));
     
     List<TimedTask> list = this.timedTaskService.findAll(map);
     int c = this.timedTaskService.count(map).intValue();
     pMap.put("rows", list);
     pMap.put("total", Integer.valueOf(c));
     String str = JSONObject.toJSONString(pMap);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"add"})
   public String addCom(TimedTask end, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     resulT = this.timedTaskService.add(end);
     JSONObject result = new JSONObject();
     if (resulT > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(resp, result);
     
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/delete"})
   public int delete(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.timedTaskService.delete(ids[i]);
     }
     return result;
   }
   
   @RequestMapping({"update"})
   public String updete(TimedTask end, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     resulT = this.timedTaskService.update(end);
     JSONObject result = new JSONObject();
     if (resulT > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(resp, result);
     
     return null;
   }
 }


