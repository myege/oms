 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.EndCompany;
 import com.what21.service.EndCompanyService;
 import com.what21.util.ResponseUtil;
 import java.io.IOException;
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
 @RequestMapping({"endCompany"})
 public class EndCompanyAction
   extends BaseAction
 {
   @Autowired
   private EndCompanyService companyService;
   
   @RequestMapping({"findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
     int pStart = Integer.parseInt(request.getParameter("page"));
     int pEnd = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pMap = new HashMap<>();
     map.put("pStart", Integer.valueOf((pStart - 1) * pEnd));
     map.put("pEnd", Integer.valueOf(pEnd));
     
     List<EndCompany> list = this.companyService.findAll(map);
     int c = this.companyService.count(map);
     pMap.put("rows", list);
     pMap.put("total", Integer.valueOf(c));
     String str = JSONObject.toJSONString(pMap);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"findCond"})
   public void findCond(HttpServletRequest request, HttpServletResponse response) throws IOException {
     int pStart = Integer.parseInt(request.getParameter("page"));
     int pEnd = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     map.put("pStart", Integer.valueOf((pStart - 1) * pEnd));
     map.put("pEnd", Integer.valueOf(pEnd));
     
     String name = request.getParameter("paramName");
     String value = request.getParameter("paramValue");
     if (!name.equals("id")) {
       value = "%" + value + "%";
     }
     map.put(name, value);
     
     List<EndCompany> list = this.companyService.findCond(map);
     
     int c = this.companyService.count(map);
     
     Map<String, Object> pMap = new HashMap<>();
     pMap.put("rows", list);
     pMap.put("total", Integer.valueOf(c));
     String str = JSONObject.toJSONString(pMap);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"addCom"})
   public String addCom(EndCompany end, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     resulT = this.companyService.addCom(end);
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
   @RequestMapping({"/deleteEnd"})
   public int deleteEmd(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.companyService.delete(Integer.parseInt(ids[i]));
     }
     return result;
   }
 
 
   
   @RequestMapping({"updeteEnd"})
   public String updete(EndCompany end, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     resulT = this.companyService.update(end);
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


