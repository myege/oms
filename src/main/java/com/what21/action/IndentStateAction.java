 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.IndentState;
 import com.what21.service.IndentStService;
 import java.io.IOException;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"indentState"})
 public class IndentStateAction
   extends BaseAction
 {
   @Autowired
   private IndentStService indentStService;
   
   @RequestMapping({"findIndentId"})
   public void findIndentId(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String indentId = request.getParameter("indentId");
     List<IndentState> indentIdSta = this.indentStService.findIndentId(indentId);
     
     String result = JSONObject.toJSONString(indentIdSta);
     response.getWriter().write(result);
   }
 }


