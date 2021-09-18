 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.EndHeader;
 import com.what21.service.EndHeaderService;
 import com.what21.util.ResponseUtil;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.text.SimpleDateFormat;
 import java.util.Date;
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
 @RequestMapping({"endHeader"})
 public class EndHeaderAction
   extends BaseAction
 {
   @Autowired
   private EndHeaderService endHeaderService;
   
   @RequestMapping({"findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
     int pStart = Integer.parseInt(request.getParameter("page"));
     int pEnd = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pMap = new HashMap<>();
     map.put("pStart", Integer.valueOf((pStart - 1) * pEnd));
     map.put("pEnd", Integer.valueOf(pEnd));
     
     List<EndHeader> list = this.endHeaderService.findAll(map);
     int c = this.endHeaderService.count(map);
     pMap.put("rows", list);
     pMap.put("total", Integer.valueOf(c));
     String str = JSONObject.toJSONString(pMap);
     PrintWriter pw = response.getWriter();
     pw.write(str);
     pw.flush();
     pw.close();
   }
   
   @RequestMapping({"addHead"})
   public String addCom(EndHeader end, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String format = formatter.format(date);
     end.setCreatdatatime(format);
     resulT = this.endHeaderService.add(end);
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
   @RequestMapping({"/deleteHea"})
   public int deleteHea(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++)
     {
       result = this.endHeaderService.delete(Integer.parseInt(ids[i]));
     }
     return result;
   }
   
   @RequestMapping({"updeteHea"})
   public String updeteHea(EndHeader end, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     resulT = this.endHeaderService.update(end);
     JSONObject result = new JSONObject();
     if (resulT > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(resp, result);
     
     return null;
   }
 
 
 
   
   @RequestMapping({"/headerDetails"})
   public void headerDetails(HttpServletRequest request, HttpServletResponse respone) throws IOException {
     String param = request.getParameter("rowData");
     
     String name = "id";
     Map<String, Object> paramMap = new HashMap<>();
     paramMap.put(name, param);
     paramMap.put("pStart", Integer.valueOf(0));
     paramMap.put("pEnd", Integer.valueOf(1));
     List<EndHeader> list = this.endHeaderService.findCondition(paramMap);
     String str = JSONObject.toJSONString(list);
     respone.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/findCondition"})
   public void findCondition(HttpServletRequest request, HttpServletResponse respone) throws IOException {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     
     Map<String, Object> pageMap = new HashMap<>();
     Map<String, Object> map = new HashMap<>();
     
     pageMap.put("pStart", Integer.valueOf((page - 1) * rows));
     pageMap.put("pEnd", Integer.valueOf(rows));
     
     Object param = request.getParameter("paramValue");
     String name = request.getParameter("paramName");
     pageMap.put(name, param);
     int count = this.endHeaderService.count(pageMap);
     List<EndHeader> list = this.endHeaderService.findCondition(pageMap);
     map.put("rows", list);
     map.put("total", Integer.valueOf(count));
     String str = JSONObject.toJSONString(map);
     respone.getWriter().write(str);
   }
 }


