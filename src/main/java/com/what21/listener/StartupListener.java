 package com.what21.listener;
 
 import com.what21.model.EchattsBonded;
 import com.what21.model.EchattsResult;
 import com.what21.model.ExpressOptions;
 import com.what21.model.ZtzConst;
 import com.what21.service.ExpressOptionsService;
 import com.what21.service.OrderMailService;
 import java.math.BigDecimal;
 import java.util.List;
 import javax.servlet.ServletContext;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.ApplicationEvent;
 import org.springframework.context.ApplicationListener;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.event.ContextRefreshedEvent;
 import org.springframework.web.context.ContextLoader;
 import org.springframework.web.context.WebApplicationContext;
 @Configuration
 public class StartupListener
   implements ApplicationListener<ContextRefreshedEvent>
 {
   @Autowired
   public ExpressOptionsService expressOptionsService;
   @Autowired
   private OrderMailService orderMailService;
   
   public void onApplicationEvent(ContextRefreshedEvent arg0) {
     List<ExpressOptions> ListOptions = this.expressOptionsService.selectList();
     for (ExpressOptions expressOptions : ListOptions) {
       ZtzConst.OPTIONS.put(expressOptions.getOptionName(), expressOptions.getOptionValue());
     }
     
     index();
   }
 
 
 
   
   public void index() {
     int orderToday = this.orderMailService.findOrderToday().intValue();
     
     int orderYear = this.orderMailService.findOrderYear();
     
     int exceptionOrderYear = this.orderMailService.findExceptionOrderYear();
 
     
     String totalSumYear = (new BigDecimal(this.orderMailService.findTotalSumYear())).toString();
     
     int lastYearOrderNumber = this.orderMailService.findLastYearOrderNumber();
     
     int lastMonthOrderNumber = this.orderMailService.findLastMonthOrderNumber();
     
     String lastMonthTotalSum = (new BigDecimal(this.orderMailService.findLastMonthTotalSum())).toString();
     WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
     ServletContext model = webApplicationContext.getServletContext();
     model.setAttribute("orderToday", Integer.valueOf(orderToday));
     model.setAttribute("orderYear", Integer.valueOf(orderYear));
     model.setAttribute("exceptionOrderYear", Integer.valueOf(exceptionOrderYear));
     model.setAttribute("totalSumYear", totalSumYear);
     model.setAttribute("lastYearOrderNumber", Integer.valueOf(lastYearOrderNumber));
     model.setAttribute("lastMonthOrderNumber", Integer.valueOf(lastMonthOrderNumber));
     model.setAttribute("lastMonthTotalSum", lastMonthTotalSum);
     List<EchattsBonded> bondeds = this.orderMailService.findbonded();
     List<EchattsBonded> mails = this.orderMailService.findMail();
     model.setAttribute("echattsResult", new EchattsResult(bondeds, mails));
   }
 }


