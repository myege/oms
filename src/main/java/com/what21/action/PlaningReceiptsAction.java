 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.PageQuery;
 import com.what21.model.PlaningReceipts;
 import com.what21.model.PlaningReceiptsQueryVo;
 import com.what21.model.PlaningReceiptsSku;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.PlaningReceiptsService;
 import com.what21.service.PlaningReceiptsSkuService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.io.IOException;
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
 @RequestMapping({"/planingReceipts"})
 public class PlaningReceiptsAction
   extends BaseAction
 {
   @Autowired
   public PlaningReceiptsService planingReceiptsService;
   @Autowired
   public PlaningReceiptsSkuService planingReceiptsSkuService;
   
   @RequestMapping({"/findAll2"})
   @ResponseBody
   public DatagridResultInfo findAll2(int page, int rows, PlaningReceiptsQueryVo planingReceiptsQueryVo, HttpServletRequest request) throws Exception {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     PageQuery pageQuery = new PageQuery(page, rows);
     planingReceiptsQueryVo.setPageQuery(pageQuery);
     planingReceiptsQueryVo.getPlaningReceiptsCustom().setUserId(userId);
     int total = this.planingReceiptsService.count2(planingReceiptsQueryVo);
     List<PlaningReceipts> list = this.planingReceiptsService.findAll2(planingReceiptsQueryVo);
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
     List<PlaningReceipts> u = this.planingReceiptsService.findAll(pageMap);
     int total = this.planingReceiptsService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/prDelete"})
   public int Matedelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.planingReceiptsService.delete(ids[i]);
     }
     return result;
   }
   
   @ResponseBody
   @RequestMapping({"/pr"})
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
     List<PlaningReceipts> lu = this.planingReceiptsService.pr(pageMap);
     int total = this.planingReceiptsService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
   
   @RequestMapping({"prDetailedUi"})
   public String findSkuUi(String planingReceiptsId, Model model) {
     model.addAttribute("planingReceiptsId", planingReceiptsId);
     return "/supervision/detailed/planingReceiptsDetailed";
   }
   
   @RequestMapping({"/prDetailed"})
   public void prDetailed(@RequestParam(value = "planingReceiptsId", required = true) String planingReceiptsId, OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("planingReceiptsId", planingReceiptsId);
     List<PlaningReceiptsSku> u = this.planingReceiptsSkuService.findAll(pageMap);
     int total = this.planingReceiptsSkuService.count(planingReceiptsId);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/importPr"})
   @ResponseBody
   public Map<String, String> importPr(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
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
       
       result = this.planingReceiptsService.importPr(String.valueOf(path) + fileName, userId);
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
   @RequestMapping({"/pushPr"})
   public void pushPr(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
     String str = this.planingReceiptsService.pushPr(ids);
     response.getWriter().write(str);
   }
 }


