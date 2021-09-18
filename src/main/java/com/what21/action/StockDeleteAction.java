 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.PageQuery;
 import com.what21.model.StockDelete;
 import com.what21.model.StockDeleteCustom;
 import com.what21.model.StockDeleteQueryVo;
 import com.what21.model.StockDeleteSku;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.StockDeleteService;
 import com.what21.service.StockDeleteSkuService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/stockDelete"})
 public class StockDeleteAction
   extends BaseAction
 {
   @Autowired
   public StockDeleteService stockDeleteService;
   @Autowired
   public StockDeleteSkuService stockDeleteSkuService;
   
   @RequestMapping({"/findAll2"})
   @ResponseBody
   public DatagridResultInfo findAll2(int page, int rows, HttpServletRequest request, StockDeleteQueryVo stockDeleteQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     stockDeleteQueryVo.setPageQuery(pageQuery);
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     stockDeleteQueryVo.getStockDeleteCustom().setUserId(userId);
     int total = this.stockDeleteService.count2(stockDeleteQueryVo);
     List<StockDeleteCustom> list = this.stockDeleteService.findAll2(stockDeleteQueryVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
 
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     List<StockDelete> u = this.stockDeleteService.findAll(pageMap);
     int total = this.stockDeleteService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/sdDelete"})
   public int sdDelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.stockDeleteService.delete(ids[i]);
     }
     return result;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/sd"})
   public Map<String, Object> inquiry(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String value = orderSearchDto.getParamValue();
     int name = Integer.parseInt(orderSearchDto.getParamName());
     if (value != "" && value != null) {
       if (name == 1) {
         pageMap.put("paramValue", value);
         pageMap.put("type", Integer.valueOf(name));
       } else if (name == 2) {
         pageMap.put("paramValue", value);
         pageMap.put("type", Integer.valueOf(name));
       } else if (name == 3) {
         pageMap.put("paramValue", value);
         pageMap.put("type", Integer.valueOf(name));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<StockDelete> lu = this.stockDeleteService.sd(pageMap);
     int total = this.stockDeleteService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
   
   @RequestMapping({"sdDetailedUi"})
   public String sdDetailedUi(String stockDeleteId, Model model) {
     model.addAttribute("stockDeleteId", stockDeleteId);
     return "/supervision/detailed/stockDeleteDetailed";
   }
   
   @RequestMapping({"/sdDetailed"})
   public void sdDetailed(@RequestParam(value = "stockDeleteId", required = true) String stockDeleteId, OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("stockDeleteId", stockDeleteId);
     List<StockDeleteSku> u = this.stockDeleteSkuService.findAll(pageMap);
     int total = this.stockDeleteSkuService.count(stockDeleteId);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/importSd"})
   @ResponseBody
   public Map<String, String> importSd(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     GeneralResult result = new GeneralResult();
     Map<String, String> map = new HashMap<>();
     String i = "0";
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
 
       
       excelFile.transferTo(targetFile);
 
       
       result = this.stockDeleteService.importSd(String.valueOf(path) + fileName, userId);
       if (result.getMessage().contains("导入成功")) {
         i = "1";
       } else {
         i = "0";
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage("导入失败！");
       i = "0";
       if (targetFile.exists()) {
         targetFile.delete();
       }
     } 
     map.put("code", i);
     map.put("msg", result.getMessage());
     return map;
   }
   
   @ResponseBody
   @RequestMapping({"/pushSd"})
   public int pushSd(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.stockDeleteService.pushSd(ids);
   }
 }


