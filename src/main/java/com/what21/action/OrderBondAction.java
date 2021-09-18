 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Company;
 import com.what21.model.OrderBond;
 import com.what21.model.OrderBondSku;
 import com.what21.model.Users;
 import com.what21.service.OrderBondService;
 import com.what21.util.ResponseUtil;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/orderBond"})
 public class OrderBondAction
   extends BaseAction
 {
   @Autowired
   private OrderBondService orderBondService;
   
   @RequestMapping({"recharge"})
   public String recharge(Model model, String id) {
     OrderBond orderBond = this.orderBondService.findObjectById(id);
     model.addAttribute("orderBond", orderBond);
     return "/orderBonded/operate/rechargeUi";
   }
   
   @RequestMapping({"freeze"})
   public String freeze(Model model, String id) {
     OrderBond orderBond = this.orderBondService.findObjectById(id);
     model.addAttribute("orderBond", orderBond);
     return "/orderBonded/operate/freeze";
   }
   
   @RequestMapping({"release"})
   public String release(Model model, String id) {
     OrderBond orderBond = this.orderBondService.findObjectById(id);
     model.addAttribute("orderBond", orderBond);
     return "/orderBonded/operate/release";
   }
   
   @RequestMapping({"addUi"})
   public String addUi() {
     return "/orderBonded/operate/addUi";
   }
   
   @RequestMapping({"details"})
   public String details(String companybm, HttpServletRequest request) {
     request.getSession().setAttribute("companybmDetails", companybm);
     return "/orderBonded/operate/details";
   }
   
   @RequestMapping({"findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
     int pStart = Integer.parseInt(request.getParameter("page"));
     String name = (String)request.getSession().getAttribute("userName");
     Object object = request.getSession().getAttribute("userId");
     if (name.equals("admin")) {
       object = "%%";
     }
     int pEnd = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pMap = new HashMap<>();
     map.put("pStart", Integer.valueOf((pStart - 1) * pEnd));
     map.put("pEnd", Integer.valueOf(pEnd));
     map.put("userId", object);
     
     List<OrderBond> list = this.orderBondService.findAll(map);
     int c = this.orderBondService.count(map).intValue();
     pMap.put("rows", list);
     pMap.put("total", Integer.valueOf(c));
     String str = JSONObject.toJSONString(pMap);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"save"})
   public int save(OrderBond orderBond, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     String bm = orderBond.getCompanybm();
     resulT = this.orderBondService.save(orderBond);
     return resulT;
   }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/deleteBond"})
   public int deleteEmd(String[] ids) throws Exception {
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       OrderBond o = this.orderBondService.findObjectById(ids[i]);
       this.orderBondService.deleteBm(o.getCompanybm());
       result = this.orderBondService.delete(Integer.parseInt(ids[i]));
     } 
     return result;
   }
 
   
   @RequestMapping({"updeteBond"})
   public void updete(OrderBond orderBond, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     resulT = this.orderBondService.update(orderBond);
     
     ResponseUtil.write(resp, Integer.valueOf(resulT));
   }
 
 
 
   
   @RequestMapping({"findBm"})
   public void findBm(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String param = (String)request.getSession().getAttribute("companybmDetails");
     String name = "companybm";
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> paramMap = new HashMap<>();
     paramMap.put(name, param);
     paramMap.put("pStart", Integer.valueOf(0));
     paramMap.put("pEnd", Integer.valueOf(10));
     int c = this.orderBondService.countBm(paramMap);
     List<OrderBondSku> list = this.orderBondService.findBm(paramMap);
     map.put("rows", list);
     map.put("total", Integer.valueOf(c));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"frost"})
   public void frostBond(OrderBond orderBond, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     String updateMamey = orderBond.getUpdateMamey();
     BigDecimal b = new BigDecimal(Double.parseDouble(updateMamey));
     double b1 = b.setScale(2, 4).doubleValue();
     resulT = this.orderBondService.frostAndF(orderBond, b1);
     
     ResponseUtil.write(resp, Integer.valueOf(resulT + 1));
   }
 
   
   @RequestMapping({"free"})
   public void freeBond(OrderBond orderBond, HttpServletResponse resp) throws Exception {
     int resulT = 0;
     String freeMamey = orderBond.getUpdateMamey();
     BigDecimal b = new BigDecimal(Double.parseDouble(freeMamey));
     double b1 = 0.0D - b.setScale(2, 4).doubleValue();
     resulT = this.orderBondService.frostAndF(orderBond, b1);
     ResponseUtil.write(resp, Integer.valueOf(resulT + 1));
   }
   @ResponseBody
   @RequestMapping({"findByCompany"})
   public List<Company> findByCompany(HttpServletResponse respone) throws Exception {
     List<Company> list = this.orderBondService.findByCompany();
     
     return list;
   }
   @RequestMapping({"findByCompanyBm"})
   public void findByCompanyBm(String companyBm, HttpServletResponse respone) throws Exception {
     Company com = this.orderBondService.findByCompanyBm(companyBm);
     List<Company> list = new ArrayList<>();
     list.add(com);
     String str = JSONObject.toJSONString(list);
     
     PrintWriter w = respone.getWriter();
     w.write(str);
     w.flush();
     w.close();
   }
   
   @ResponseBody
   @RequestMapping({"findUserId"})
   public List<Users> findUserId(HttpServletRequest request, HttpServletResponse respone) throws Exception {
     List<Users> list = this.orderBondService.findByUserId();
     
     return list;
   }
 }


