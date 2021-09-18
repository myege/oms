 package com.what21.interceptor;
 
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.web.servlet.ModelAndView;
 import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 public class AllInterceptor
   extends HandlerInterceptorAdapter
 {
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     Object obj = request.getSession().getAttribute("user");
     String handlerStr = request.getServletPath();
     if (obj != null)
       return true; 
     if (handlerStr.indexOf("/login/init.do") == -1 && handlerStr.indexOf("/login/loginUser.do") == -1 && 
       handlerStr.indexOf("/indentState/findIndentId.do") == -1 && 
       handlerStr.indexOf("/receiveHghzData/receiveData.do") == -1 && 
       handlerStr.indexOf("/receiveHghzData/yzapi.do") == -1 && 
       handlerStr.indexOf("/receiveHtData/receiveData.do") == -1 && 
       handlerStr.indexOf("/sto/sto.do") == -1 && 
       handlerStr.indexOf("/sto/acs.do") == -1 && 
       handlerStr.indexOf("/helipay/pay.do") == -1 && 
       handlerStr.indexOf("/tinventory/impKuChuen.do") == -1 && 
       handlerStr.indexOf("/sto/matchSDM.do") == -1 && 
       handlerStr.indexOf("/Welcome") == -1 && 
       handlerStr.indexOf("/GZZS/") == -1 && 
       handlerStr.indexOf("/JjPicking/xcxdistributeLeaflets.do") == -1 && 
       handlerStr.indexOf("/idcheckAll/idcheck.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/receiveData.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/receivebashData.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/CreateOrder.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/CreateItem.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/GetMailno.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/receiveEwtpoutData.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/YwjbgItem.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/YwjbgInventory.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/YwjbgReceipt.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/receiveCCData.do") == -1 && 
       handlerStr.indexOf("/receiveEwtpData/CreateBsOrder.do") == -1)
     {
       if (handlerStr.indexOf("/receiveEwtpData/YwjwlReceipt2.do") == -1) {
 
 
         
         response.sendRedirect(String.valueOf(request.getContextPath()) + "/login/init.do");
         return false;
       } 
     }
     return true;
   }
   
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}
   
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
 }


