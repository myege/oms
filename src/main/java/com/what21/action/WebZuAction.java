 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Webzu;
 import com.what21.service.WebZuService;
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
 @RequestMapping({"/webzu"})
 public class WebZuAction
   extends BaseAction
 {
   @Autowired
   private WebZuService webZuService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<Webzu> u = this.webZuService.findAll(pageMap);
     int total = this.webZuService.count(pageMap).intValue();
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"addweb"})
   public void addweb(Webzu webzu, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int add = this.webZuService.add(webzu);
     String str = JSONObject.toJSONString(Integer.valueOf(add));
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"delete"})
   public int delete(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.webZuService.delete(Integer.valueOf(Integer.parseInt(ids[i])));
     }
     return result;
   }
   
   @RequestMapping({"update"})
   public String updete(Webzu webzu, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     resulT = this.webZuService.update(webzu);
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


