 package com.what21.action;
 
 import com.what21.model.CustomsDepositCustom;
 import com.what21.model.Users;
 import com.what21.service.CustomsDepositService;
 import com.what21.service.OrderBondedService;
 import com.what21.service.OrderMailService;
 import com.what21.service.UsersService;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/login"})
 public class LoginAction
   extends BaseAction
 {
   @Autowired
   private UsersService usersService;
   @Autowired
   private CustomsDepositService customsDepositService;
   @Autowired
   private OrderBondedService orderBondedService;
   @Autowired
   private OrderMailService orderMailService;
   
   @RequestMapping({"/loginUser"})
   public ModelAndView loginUser(Users users, HttpServletRequest request) {
     ModelAndView modelAndView = new ModelAndView();
     Users user = this.usersService.findUser(users);
     if (user != null) {
       request.getSession().setAttribute("user", user);
       request.getSession().setAttribute("userId", Integer.valueOf(user.getId()));
       request.getSession().setAttribute("userName", user.getUserName());
       request.getSession().setAttribute("userRole", user.getUserRole());
       request.getSession().setAttribute("name", user.getName());
       request.getSession().setAttribute("weight001", "0.000");
       modelAndView.setViewName("main");
       
       if ("admin".equals(user.getUserName())) {
         int numForOBE = this.orderBondedService.findException();
         int numForOME = this.orderMailService.findException();
         request.getSession().setAttribute("numForOBE", Integer.valueOf(numForOBE));
         request.getSession().setAttribute("numForOME", Integer.valueOf(numForOME));
         
         List<CustomsDepositCustom> customsDepositCustoms = this.customsDepositService.findWarning(100000);
         request.getSession().setAttribute("warningCustomsDeposit", customsDepositCustoms);
       } 
       
       return modelAndView;
     } 
     modelAndView.setViewName("login");
     modelAndView.addObject("message", "账号或密码有误，请重新输入！");
     return modelAndView;
   }
   
   @RequestMapping({"/loginOut"})
   public ModelAndView loginOut(HttpServletRequest request) {
     request.getSession().invalidate();
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("login");
     return modelAndView;
   }
 }


