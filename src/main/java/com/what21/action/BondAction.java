 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Bond;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.BondService;
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
 @RequestMapping({"/bond"})
 public class BondAction
   extends BaseAction
 {
   @Autowired
   public BondService bondService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response, OrderSearchDto orderSearchDto) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     List<Bond> orderList = this.bondService.findAll(pageMap);
     int total = this.bondService.count(pageMap);
     map.put("rows", orderList);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/findbond"})
   public Map<String, Object> findbond(HttpServletRequest request, HttpServletResponse response, OrderSearchDto orderSearchDto) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String pv = orderSearchDto.getParamValue();
     int pn = Integer.parseInt(orderSearchDto.getParamName());
     if (pv != "" && pv != null) {
       if (pn == 1) {
         pageMap.put("paramValue", pv);
         pageMap.put("type", Integer.valueOf(pn));
       } else if (pn == 2) {
         pageMap.put("paramValue", pv);
         pageMap.put("type", Integer.valueOf(pn));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<Bond> lu = this.bondService.findbond(pageMap);
     int total = this.bondService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
   
   @ResponseBody
   @RequestMapping({"/bonddelete"})
   public int bonddelete(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.bondService.bonddelete(ids[i]);
     }
     return result;
   }
 
 
   
   @RequestMapping({"/bondedit"})
   public String bondedit(Bond bond, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.bondService.bondedit(bond);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/addbond"})
   public void addbond(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String bondMoney = request.getParameter("bondMoney");
     String companybm = request.getParameter("companybm");
     String companyName = request.getParameter("companyName");
     Bond b = new Bond();
     b.setBondMoney(bondMoney);
     b.setCompanybm(companybm);
     b.setCompanyName(companyName);
     int res = this.bondService.addbond(b);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 }


