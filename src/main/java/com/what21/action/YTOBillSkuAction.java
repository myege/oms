 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.YTOBillSku;
 import com.what21.service.YTOBillService;
 import com.what21.service.YTOBillSkuService;
 import com.what21.util.ResponseUtil;
 import java.io.IOException;
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
 @Controller
 @RequestMapping({"/yTOBillSkuAction"})
 public class YTOBillSkuAction
   extends BaseAction
 {
   @Autowired
   private YTOBillSkuService yser;
   @Autowired
   private YTOBillService sser;
   
   @RequestMapping({"/findall"})
   public void findAll(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "waybillNo", required = true) String waybillNo) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("waybillNo", waybillNo);
     
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("name", "findall");
     List<YTOBillSku> u = this.yser.findByWaybillNo(pageMap);
     int total = this.yser.count(pageMap);
     
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     request.getSession().setAttribute("wail", waybillNo);
     
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/updateodm"})
   public void updateodm(@RequestParam(value = "id", required = true) int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> map = new HashMap<>();
     map.put("id", Integer.valueOf(id));
     List<YTOBillSku> ret = this.yser.odmTax(map);
     map.put("ret", ret);
     String str = JSONObject.toJSONString(ret);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/delete"})
   public void delete(HttpServletRequest request, HttpServletResponse response, int[] ids) throws IOException {
     int res = this.yser.delete_1(ids);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/updateById"})
   public String updateById(YTOBillSku ytoBillSku, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int id = Integer.parseInt(request.getParameter("id"));
     int resultTotal = 0;
     ytoBillSku.setId(id);
     ytoBillSku.setEventTime((String)request.getSession().getAttribute("eventTime"));
     resultTotal = this.yser.updateById(ytoBillSku);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("error", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/check"})
   public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String orderNo = request.getParameter("orderNo");
     YTOBillSku sku = new YTOBillSku();
     sku.setOrderNo(orderNo);
     List<YTOBillSku> lu = this.yser.findByOrderNo(sku);
     String str = JSONObject.toJSONString(lu);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/deleteAll"})
   public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
     int lu = this.yser.deleteAll();
     String str = JSONObject.toJSONString(Integer.valueOf(lu));
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/insertYto"})
   public String insertYto(YTOBillSku ytoBillSku, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     String waybillNo = request.getSession().getAttribute("wail").toString();
     String orderNo = request.getSession().getAttribute("orderNo").toString();
     Date date = new Date();
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     ytoBillSku.setCreateTime(df.format(date));
     ytoBillSku.setWaybillNo(waybillNo);
     ytoBillSku.setOrderNo(orderNo);
     ytoBillSku.setIsPushed(Integer.valueOf(0));
     resultTotal = this.yser.insertYTO(ytoBillSku);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("error", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 }


