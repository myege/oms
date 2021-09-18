 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.CountRule;
 import com.what21.service.CountRuleService;
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
 @RequestMapping({"countRule"})
 public class CountRuleAction
   extends BaseAction
 {
   @Autowired
   private CountRuleService countRuleService;
   
   @RequestMapping({"findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse respone) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("pstart", Integer.valueOf((page - 1) * rows));
     pageMap.put("pend", Integer.valueOf(rows));
     List<CountRule> list = this.countRuleService.findAll(pageMap);
     int count = this.countRuleService.count(map).intValue();
     map.put("total", Integer.valueOf(count));
     map.put("rows", list);
     String str = JSONObject.toJSONString(map);
     respone.getWriter().write(str);
   }
   
   @RequestMapping({"addRule"})
   public void addRule(CountRule countRule, String ptfCode, String qgfCode, HttpServletResponse respone) throws Exception {
     int resultTotal = 0;
     countRule.setCode(String.valueOf(qgfCode) + ";" + ptfCode + ";");
     resultTotal = this.countRuleService.add(countRule).intValue();
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(respone, result);
   }
   @ResponseBody
   @RequestMapping({"delete"})
   public Integer delete(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     Integer result = Integer.valueOf(0);
     Integer delate = this.countRuleService.delate(ids);
     return result;
   }
 
   
   @RequestMapping({"update"})
   public void update(CountRule countRule, String ptfCode, String qgfCode, HttpServletResponse respone) throws Exception {
     countRule.setCode(String.valueOf(qgfCode) + ";" + ptfCode + ";");
     int resultTotal = this.countRuleService.update(countRule).intValue();
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(respone, result);
   }
   
   @RequestMapping({"findCombobox"})
   public void findCombobox(Integer id, HttpServletResponse respone) throws Exception {
     List<CountRule> findCombobox = this.countRuleService.findCombobox(id);
     String str = JSONObject.toJSONString(findCombobox);
     respone.getWriter().write(str);
   }
 }


