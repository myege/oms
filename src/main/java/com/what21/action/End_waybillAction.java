 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.EndCompany;
 import com.what21.model.End_waybill;
 import com.what21.service.End_waybillService;
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
 @Controller
 @RequestMapping({"/end_waybill"})
 public class End_waybillAction
   extends BaseAction
 {
   @Autowired
   private End_waybillService end_waybillService;
   
   @RequestMapping({"/findAll"})
   public void getAll(HttpServletRequest request, HttpServletResponse respone) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     
     pageMap.put("pstart", Integer.valueOf((page - 1) * rows));
     pageMap.put("pend", Integer.valueOf(rows));
     
     List<End_waybill> list = this.end_waybillService.findCompany(pageMap);
     
     int count = this.end_waybillService.count(map);
     map.put("total", Integer.valueOf(count));
     map.put("rows", list);
     String str = JSONObject.toJSONString(map);
     respone.getWriter().write(str);
   }
 
 
 
   
   @RequestMapping({"/findCompany"})
   public void findCompany(HttpServletRequest request, HttpServletResponse respone) throws IOException {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     
     Map<String, Object> pageMap = new HashMap<>();
     Map<String, Object> map = new HashMap<>();
     
     pageMap.put("pstart", Integer.valueOf((page - 1) * rows));
     pageMap.put("pend", Integer.valueOf(rows));
     
     Object param = request.getParameter("paramValue");
     String name = request.getParameter("paramName");
     param = "%" + param + "%";
     pageMap.put(name, param);
     int count = this.end_waybillService.count(pageMap);
     List<End_waybill> list = this.end_waybillService.findCompany(pageMap);
     map.put("rows", list);
     map.put("total", Integer.valueOf(count));
     String str = JSONObject.toJSONString(map);
     respone.getWriter().write(str);
   }
 
 
 
   
   @RequestMapping({"/addEnd"})
   public String addEndWay(End_waybill end_waybill, HttpServletResponse respone) throws Exception {
     int resultTotal = 0;
     Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String format = formatter.format(date);
     end_waybill.setStartDate(format);
     resultTotal = this.end_waybillService.addEnd(end_waybill);
     
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(respone, result);
     
     return null;
   }
   
   @RequestMapping({"/details"})
   public void details(HttpServletRequest request, HttpServletResponse respone) throws IOException {
     String param = request.getParameter("rowData");
     
     String name = "id";
     Map<String, Object> paramMap = new HashMap<>();
     paramMap.put(name, param);
     paramMap.put("pstart", Integer.valueOf(0));
     paramMap.put("pend", Integer.valueOf(1));
     List<End_waybill> list = this.end_waybillService.findCompany(paramMap);
     String str = JSONObject.toJSONString(list);
     respone.getWriter().write(str);
   }
 
   
   @RequestMapping({"/findCom"})
   public void findCom(HttpServletResponse respone) throws IOException {
     List<EndCompany> list = this.end_waybillService.findCompany();
     EndCompany end = new EndCompany();
     end.setCompany("申通测试");
     end.setCompanyid("ST");
     list.add(end);
     String str = JSONObject.toJSONString(list);
     PrintWriter w = respone.getWriter();
     w.write(str);
     w.flush();
     w.close();
   }
 }


