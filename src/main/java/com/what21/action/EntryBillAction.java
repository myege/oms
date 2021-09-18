 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.OutEntryBillHead;
 import com.what21.model.OutPc;
 import com.what21.service.OutEntryBillHeadService;
 import com.what21.service.OutEntryBillListService;
 import com.what21.service.OutPcService;
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
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/entryBill"})
 public class EntryBillAction
   extends BaseAction
 {
   @Autowired
   private OutPcService outPcService;
   @Autowired
   private OutEntryBillHeadService outEntryBillHeadService;
   @Autowired
   private OutEntryBillListService outEntryBillListService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<OutPc> u = this.outPcService.findAll(pageMap);
     int total = this.outPcService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/find"})
   public Map<String, Object> find(HttpServletRequest request, OrderSearchDto orderSearchDto) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String pv = orderSearchDto.getParamValue();
     int pn = Integer.parseInt(orderSearchDto.getParamName());
     if (pv != "" && pv != null && 
       pn == 2) {
       pageMap.put("paramValue", pv);
       pageMap.put("type", Integer.valueOf(pn));
     } 
     
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<OutPc> lu = this.outPcService.findAll(pageMap);
     int total = this.outPcService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
   
   @ResponseBody
   @RequestMapping({"/addGo"})
   public void addGo(HttpServletResponse response, HttpServletRequest request) throws Exception {
     String pc = request.getParameter("pc");
     String userName = request.getParameter("userName");
     int userId = Integer.parseInt(request.getParameter("userId").toString());
 
     
     userId = getUserId(userName, userId);
     
     SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     OutPc g = new OutPc();
     g.setPc(pc);
     g.setUserid(userId);
     g.setUserName(userName);
     g.setCreatedatatime(d.format(new Date()));
     int res = this.outPcService.addO(g);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/deleteG"})
   public int deleteG(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     int enh = 0;
     int ebl = 0;
     for (int i = 0; i < ids.length; i++) {
       ebl = this.outEntryBillListService.delete(ids[i]);
       enh = this.outEntryBillHeadService.delete(ids[i]);
       result = this.outPcService.deleteO(ids[i]);
     } 
     
     return result + ebl + enh;
   }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/findbillcode"})
   public void findbillcode(@RequestParam(value = "pcId", required = true) String pcId, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("pcId", pcId);
     List<OutEntryBillHead> u = this.outEntryBillHeadService.findAll(pageMap);
     int total = this.outEntryBillHeadService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/needEntryBill"})
   public void needEntryBill(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String to = request.getParameter("to");
     String tpc = request.getParameter("tpc");
   }
 }


