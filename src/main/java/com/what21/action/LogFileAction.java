 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.LogFile;
 import com.what21.model.LogFileSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.LogFileService;
 import com.what21.service.LogFileSkuService;
 import com.what21.util.ResponseUtil;
 import java.math.BigDecimal;
 import java.text.DecimalFormat;
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
 @RequestMapping({"/logfile"})
 public class LogFileAction
   extends BaseAction
 {
   @Autowired
   public LogFileService logFileService;
   @Autowired
   public LogFileSkuService logFileSkuService;
   
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
     List<LogFile> u = this.logFileService.findAll(pageMap);
     int total = this.logFileService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/addlogf"})
   public void addbond(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "userId", required = true) int userId) throws Exception {
     String rest, bondMoney = request.getParameter("bondMoney");
     String companybm = request.getParameter("companybm");
     String companyName = request.getParameter("companyName");
     
     LogFile l = new LogFile();
     l.setCompanyName(companyName);
     l.setBondMoney(bondMoney);
     l.setCompanybm(companybm);
     l.setResidualMargin(bondMoney);
     l.setFrostMamey("0");
     l.setUsedMargin("0");
     l.setUserId(userId);
     int res = this.logFileService.addlogf(l);
     
     if (res == 1) {
       rest = "1";
     } else {
       rest = "2";
     } 
     String str = JSONObject.toJSONString(rest);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/logfdelete"})
   public int bonddelete(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       this.logFileSkuService.deleteSku(this.logFileService.findAllLogFile(Integer.parseInt(ids[i])).getCompanybm());
       result = this.logFileService.logfdelete(ids[i]);
     } 
     return result;
   }
 
   
   @RequestMapping({"/logfedit"})
   public void logfedit(LogFile logFile, HttpServletResponse response, HttpServletRequest request) throws Exception {
     String bondMoney1 = request.getParameter("bondMoneyss");
     double bondMoney = Double.parseDouble(bondMoney1);
 
     
     LogFile logFiles = this.logFileService.findAllLogFile(logFile.getId());
     String b1 = logFiles.getBondMoney();
     String b2 = logFiles.getResidualMargin();
     BigDecimal bd = new BigDecimal(b1);
     BigDecimal bb = new BigDecimal(b2);
     
     DecimalFormat upMx = new DecimalFormat("#.00");
     BigDecimal bp = new BigDecimal(upMx.format(bondMoney));
 
     
     logFile.setBondMoney(bd.add(bp).toString());
     logFile.setResidualMargin(bb.add(bp).toString());
 
     
     int resultTotal = 0;
     if (Double.parseDouble(logFile.getResidualMargin()) < 0.0D) {
       resultTotal = -1;
     } else {
       LogFileSku fileSku = new LogFileSku();
       Date date = new Date();
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String creatTime = formatter.format(date);
       fileSku.setCreatTime(creatTime);
       fileSku.setCompanybm(logFiles.getCompanybm());
       fileSku.setSyMamey(bb.add(bp).toString());
       fileSku.setUpMamey(bondMoney1);
       
       int i = this.logFileSkuService.saveLog(fileSku);
       int j = this.logFileService.logfedit(logFile);
       resultTotal = i + j;
     } 
     ResponseUtil.write(response, Integer.valueOf(resultTotal));
   }
   @RequestMapping({"frostMo"})
   public String frostMo(LogFile logFile, double upMoney, String code, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     if (code.equals("E")) {
       upMoney = 0.0D - upMoney;
     }
     LogFile log = this.logFileService.findAllLogFile(logFile.getId());
     DecimalFormat upMx = new DecimalFormat("#.00");
     BigDecimal upM = new BigDecimal(upMx.format(upMoney));
     
     BigDecimal resM = new BigDecimal(log.getResidualMargin());
     BigDecimal frostM = new BigDecimal(log.getFrostMamey());
     
     log.setResidualMargin(resM.subtract(upM).toString());
     log.setFrostMamey(frostM.add(upM).toString());
     this.logFileService.frostMo(log);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
   
   @RequestMapping({"findAllSku"})
   public void findAllSku(String companybm, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String param = request.getParameter("companybm");
     param = new String(param.getBytes("ISO-8859-1"), "UTF-8");
     String name = "companybm";
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> paramMap = new HashMap<>();
     paramMap.put(name, param);
     paramMap.put("pStart", Integer.valueOf(0));
     paramMap.put("pEnd", Integer.valueOf(20));
     int c = this.logFileSkuService.countLogSku(paramMap);
     List<LogFileSku> list = this.logFileSkuService.findAll(paramMap);
     map.put("rows", list);
     map.put("total", Integer.valueOf(c));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 }


