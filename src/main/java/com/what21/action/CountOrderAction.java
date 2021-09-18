 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.TCountorder;
 import com.what21.model.TCountsku;
 import com.what21.service.CountOrderService;
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
 @RequestMapping({"countOrder"})
 public class CountOrderAction
   extends BaseAction
 {
   @Autowired
   private CountOrderService countOrderService;
   
   @RequestMapping({"findAll"})
   public void findAll(TCountorder tCountorder, HttpServletRequest request, HttpServletResponse respone) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     tCountorder.setMailno((new StringBuilder(String.valueOf((page - 1) * rows))).toString());
     tCountorder.setSum((new StringBuilder(String.valueOf(rows))).toString());
 
     
     List<TCountorder> list = this.countOrderService.findAll(tCountorder);
     int count = this.countOrderService.count(tCountorder).intValue();
     map.put("total", Integer.valueOf(count));
     map.put("rows", list);
     String str = JSONObject.toJSONString(map);
     respone.getWriter().write(str);
   }
   
   @RequestMapping({"add"})
   public void add(TCountorder tCountorder, HttpServletResponse respone) throws Exception {
     Integer resulT = this.countOrderService.add(tCountorder);
     
     ResponseUtil.write(respone, resulT);
   }
   
   @RequestMapping({"countOrder"})
   public void countOrder(String[] allmailno, String rule, HttpServletResponse respone) throws Exception {
     int i = this.countOrderService.countOrder(allmailno, rule).intValue();
     ResponseUtil.write(respone, Integer.valueOf(i));
   }
 
   
   @RequestMapping({"showSku"})
   public void showSku(String allmailno, HttpServletResponse respone, HttpServletRequest request) throws Exception {
     TCountsku findMail = this.countOrderService.findMail(allmailno);
     if (findMail == null) {
       ResponseUtil.write(respone, Integer.valueOf(9));
       return;
     } 
     String str = JSONObject.toJSONString(findMail);
     ResponseUtil.write(respone, str);
   }
   
   @ResponseBody
   @RequestMapping({"delete"})
   public int delete(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     this.countOrderService.delete(ids);
     return result;
   }
 }


