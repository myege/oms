 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.PageQuery;
 import com.what21.model.Winventory;
 import com.what21.model.WinventoryQueryVo;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.WinventoryService;
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
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/winventory"})
 public class WinventoryAction
   extends BaseAction
 {
   @Autowired
   private WinventoryService winventoryService;
   
   @RequestMapping({"/findAll2"})
   @ResponseBody
   public DatagridResultInfo findAll2(int page, int rows, WinventoryQueryVo winventoryQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     winventoryQueryVo.setPageQuery(pageQuery);
     int total = this.winventoryService.count2(winventoryQueryVo);
     List<Winventory> list = this.winventoryService.findAll2(winventoryQueryVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
 
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<Winventory> u = this.winventoryService.findAll(pageMap);
     int total = this.winventoryService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/wdelete"})
   public int wdelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.winventoryService.wdelete(ids[i]);
     }
     return result;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/findw"})
   public Map<String, Object> findw(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String mc = orderSearchDto.getParamValue();
     int mcx = Integer.parseInt(orderSearchDto.getParamName());
     if (mc != "" && mc != null) {
       if (mcx == 1) {
         pageMap.put("paramValue", mc);
         pageMap.put("type", Integer.valueOf(mcx));
       } else if (mcx == 2) {
         pageMap.put("paramValue", mc);
         pageMap.put("type", Integer.valueOf(mcx));
       } else if (mcx == 3) {
         pageMap.put("paramValue", mc);
         pageMap.put("type", Integer.valueOf(mcx));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<Winventory> lu = this.winventoryService.findw(pageMap);
     int total = this.winventoryService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
 
   
   @RequestMapping({"/inmerw"})
   @ResponseBody
   public Map<String, String> inmerw(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     GeneralResult result = new GeneralResult();
     Map<String, String> map = new HashMap<>();
     String i = "0";
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       
       excelFile.transferTo(targetFile);
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.winventoryService.inmerw(String.valueOf(path) + fileName, userId);
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
   @RequestMapping({"/wints"})
   public void wints(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
     String str = this.winventoryService.wints();
     response.getWriter().write(str);
   }
 }


