 package com.what21.action;
 
 import com.what21.service.YTOPayService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 @Controller
 @RequestMapping({"/yTOPayAction"})
 public class YTOPayAction
 {
   @Autowired
   private YTOPayService yto;
   
   @ResponseBody
   @RequestMapping({"/Yto"})
   public int Yto(String ids) {
     return this.yto.YTOPiPei(ids);
   }
 }


