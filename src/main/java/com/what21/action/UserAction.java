 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.Users;
 import com.what21.service.UsersService;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
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
 @RequestMapping({"/users"})
 public class UserAction
   extends BaseAction
 {
   @Autowired
   private UsersService usersService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<Users> u = this.usersService.getAll(pageMap);
     int total = this.usersService.countUsers();
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/addUsers"})
   public void addUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String userName = request.getParameter("userName");
     String name = request.getParameter("name");
     String age = request.getParameter("age");
     String phone = request.getParameter("phone");
     String email = request.getParameter("email");
     String password = request.getParameter("password");
     Users u = new Users();
     u.setUserName(userName);
     u.setName(name);
     u.setPhone(phone);
     u.setAge(Integer.parseInt(age));
     u.setEmail(email);
     u.setPassword(password);
     int res = this.usersService.addUsers(u);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/updateUsers"})
   public void updateUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String id = request.getParameter("id");
     String password = request.getParameter("pwd");
     String[] arr = id.split(",");
     Users u = new Users();
     for (int i = 0; i < arr.length; i++) {
       u.setId(Integer.parseInt(arr[i]));
       u.setPassword(password);
       this.usersService.updateUsers(u);
     } 
     Map<String, Object> map = new HashMap<>();
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/deleteUsers"})
   public int deleteUsers(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.usersService.deleteUsers(ids[i]);
     }
     return result;
   }
 
 
   
   @RequestMapping({"/power"})
   public void power(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String id = request.getParameter("ida");
     String[] arr = id.split(",");
     String s = request.getParameter("s");
     s = s.substring(0, s.length() - 1);
     String[] ss = s.split(",");
     HashSet<String> permission = new HashSet();
     for (int j = 0; j < ss.length; j++) {
       permission.add(ss[j]);
     }
     String sss = "";
     Iterator<String> it = permission.iterator();
     while (it.hasNext()) {
       String str1 = it.next();
       sss = String.valueOf(sss) + str1 + ",";
     } 
     sss = sss.substring(0, sss.length() - 1);
     Users u = new Users();
     for (int i = 0; i < arr.length; i++) {
       u.setId(Integer.parseInt(arr[i]));
       u.setUserRole(sss);
       this.usersService.power(u);
     } 
     Map<String, Object> map = new HashMap<>();
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/cleanPower"})
   public void cleanPower(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String id = request.getParameter("ida");
     
     String[] ids = id.split(",");
     Users u = new Users();
     for (int i = 0; i < ids.length; i++) {
       u.setId(Integer.parseInt(ids[i]));
       this.usersService.cleanPower(u);
     } 
     Map<String, Object> map = new HashMap<>();
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/query"})
   public Map<String, Object> query(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
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
       } else if (pn == 3) {
         pageMap.put("paramValue", pv);
         pageMap.put("type", Integer.valueOf(pn));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<Users> lu = this.usersService.query(pageMap);
     int total = this.usersService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
   
   @RequestMapping({"/uname"})
   public void uname(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String userName = request.getParameter("userName");
     String email = request.getParameter("email");
     String phone = request.getParameter("phone");
     Map<String, Object> map = new HashMap<>();
     map.put("userName", userName);
     map.put("email", email);
     map.put("phone", phone);
     List<Users> lu = this.usersService.uname(map);
     String str = JSONObject.toJSONString(lu);
     response.getWriter().write(str);
   }
 }


