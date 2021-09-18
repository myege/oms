 package com.what21.action;
 
 import com.what21.model.EchattsResult;
 import com.what21.service.OrderMailService;
 import javax.servlet.ServletContext;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/admin"})
 public class AdminIndexAction
   extends BaseAction
 {
   @Autowired
   private OrderMailService orderMailService;
   
   @RequestMapping({"/index"})
   public String index(Model model) {
     return "/index";
   }
 
 
 
   
   @RequestMapping({"/findbonded"})
   @ResponseBody
   public EchattsResult findbonded(HttpServletRequest request) {
     ServletContext model = request.getServletContext();
     EchattsResult attribute = (EchattsResult)model.getAttribute("echattsResult");
     return attribute;
   }
 }


