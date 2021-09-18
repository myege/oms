 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.LogFile;
 import com.what21.model.OrderBatch;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.BondService;
 import com.what21.service.LogFileService;
 import com.what21.service.OrderBatchService;
 import java.math.BigDecimal;
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
 @RequestMapping({"/orderBatch"})
 public class OrderBatchAction
   extends BaseAction
 {
   @Autowired
   public OrderBatchService orderBatchService;
   @Autowired
   public LogFileService logFileService;
   @Autowired
   public BondService bondService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
     List<OrderBatch> u = this.orderBatchService.findAll(pageMap);
     int total = this.orderBatchService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/payment"})
   public int payment(@RequestParam(value = "id", required = true) int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String zfTime = df.format(new Date());
     
     LogFile logFile = this.logFileService.findlog();
     String bondMoney = logFile.getBondMoney();
     String bzi = logFile.getResidualMargin();
     
     OrderBatch orderBatch = this.orderBatchService.findBarchBond(id);
     Double leadOrderTax = orderBatch.getLeadOrderTax();
     Double sy = Double.valueOf(Double.parseDouble(bzi) - leadOrderTax.doubleValue());
     
     Double yy = Double.valueOf(Double.parseDouble(bondMoney) - sy.doubleValue());
     BigDecimal sd = new BigDecimal(sy.doubleValue());
     double f1 = sd.setScale(3, 4).doubleValue();
     BigDecimal bd = new BigDecimal(yy.doubleValue());
     double f2 = bd.setScale(3, 4).doubleValue();
     logFile.getCompanybm();
     logFile.setResidualMargin((new StringBuilder(String.valueOf(f1))).toString());
     logFile.setUsedMargin((new StringBuilder(String.valueOf(f2))).toString());
     this.logFileService.update(logFile);
     orderBatch.setId(id);
     orderBatch.setZfTime(zfTime);
     int result = 0;
     result = this.orderBatchService.updateid(orderBatch);
     return result;
   }
   @RequestMapping({"/findBond"})
   public void findBond(HttpServletRequest request, HttpServletResponse response) throws Exception {
     LogFile logFile = this.logFileService.findlog();
     String bzj = logFile.getResidualMargin();
     String str = JSONObject.toJSONString(bzj);
     response.getWriter().write(str);
   }
 }


