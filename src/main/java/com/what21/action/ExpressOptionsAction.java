 package com.what21.action;
 
 import com.what21.model.ExpressOptions;
 import com.what21.model.ZtzConst;
 import com.what21.result.AjaxResult;
 import com.what21.service.ExpressOptionsService;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/expressoptions"})
 public class ExpressOptionsAction
   extends BaseAction
 {
   @Autowired
   private ExpressOptionsService expressOptionsService;
   
   @RequestMapping({"/zheelectric"})
   public ModelAndView zheElectric() {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("/express/zheelectric");
     mv.addObject("options", ZtzConst.OPTIONS);
     return mv;
   }
 
   
   @RequestMapping({"/jd"})
   public ModelAndView jd() {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("/express/jd_options");
     mv.addObject("options", ZtzConst.OPTIONS);
     return mv;
   }
 
   
   @RequestMapping({"/yc"})
   public ModelAndView yc() {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("/express/yc_options");
     mv.addObject("options", ZtzConst.OPTIONS);
     return mv;
   }
   
   @RequestMapping({"/save"})
   @ResponseBody
   public AjaxResult save(@RequestParam Map<String, String> map) {
     try {
       this.expressOptionsService.save(map);
       
       ZtzConst.OPTIONS.clear();
       List<ExpressOptions> ListOptions = this.expressOptionsService.selectList();
       for (ExpressOptions expressOptions : ListOptions) {
         ZtzConst.OPTIONS.put(expressOptions.getOptionName(), expressOptions.getOptionValue());
       }
     } catch (Exception e) {
       e.printStackTrace();
       return error(e.getMessage());
     } 
     return toAjax(true);
   }
 }


