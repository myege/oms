 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import java.io.IOException;
 import java.util.Date;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"/platDataOpen"})
 public class PlatDataOpen
 {
   @RequestMapping({"/platDataOpen"})
   public void platDataOpen(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String parameter = request.getParameter("orderNo");
     String sessionID = request.getParameter("sessionID");
     String serviceTime = request.getParameter("serviceTime");
 
     
     JSONObject json = new JSONObject();
     json.put("code", "10000");
     json.put("message", "");
     Date date = new Date();
     json.put("serviceTime", Long.valueOf(date.getTime()));
     
     response.getWriter().write(json.toJSONString());
   }
 }


