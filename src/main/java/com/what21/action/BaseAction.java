 package com.what21.action;
 
 import com.what21.model.ExpressOptions;
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.result.AjaxResult;
 import com.what21.service.ExpressOptionsService;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.servlet.ModelAndView;
 
 
 
 @Controller
 public class BaseAction
 {
   String yhqx = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.yhqx.getValue());
 
   
   @RequestMapping({"/init"})
   public ModelAndView init(String pageName, String tz) {
     System.out.println("进入init");
     if (StringUtils.isEmpty(pageName)) {
       return new ModelAndView("login");
     }
     Map<String, String> map = new HashMap<>();
     map.put("tz", tz);
     System.out.println("tz:" + tz + "pageName:" + pageName);
     
     if (pageName.contains("/express/express")) {
       ModelAndView mv = new ModelAndView();
       Map<String, String> options = new HashMap<>();
       List<ExpressOptions> ListOptions = this.expressOptionsService.selectList();
       for (ExpressOptions expressOptions : ListOptions) {
         options.put(expressOptions.getOptionName(), expressOptions.getOptionValue());
       }
       mv.setViewName(pageName);
       mv.addObject("options", options);
       return mv;
     } 
     return new ModelAndView(pageName, map);
   } @Autowired
   private ExpressOptionsService expressOptionsService;
   public int getUserId(String userName, int userId) {
     System.out.println(String.valueOf(this.yhqx) + "  abcabcabc " + userName);
     if (this.yhqx.contains(userName)) {
       return 0;
     }
     
     return userId;
   }
   
   protected AjaxResult toAjax(int rows) {
     return (rows > 0) ? success() : error();
   }
   
   protected AjaxResult toAjax(boolean result) {
     return result ? success() : error();
   }
   
   public AjaxResult success() {
     return AjaxResult.success();
   }
   
   public AjaxResult error() {
     return AjaxResult.error();
   }
   
   public AjaxResult success(String message) {
     return AjaxResult.success(message);
   }
   
   public AjaxResult error(String message) {
     return AjaxResult.error(message);
   }
   
   public AjaxResult error(int code, String message) {
     return AjaxResult.error(code, message);
   }
 }


