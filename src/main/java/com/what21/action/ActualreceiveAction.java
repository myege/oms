 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.ActualreceiveSkuDao;
 import com.what21.model.Actualreceive;
 import com.what21.model.ActualreceiveCustom;
 import com.what21.model.ActualreceiveQueryVo;
 import com.what21.model.ActualreceiveSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.ActualreceiveService;
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
 @RequestMapping({"/actualreceive"})
 public class ActualreceiveAction
   extends BaseAction
 {
   @Autowired
   private ActualreceiveService actualreceiveService;
   @Autowired
   private ActualreceiveSkuDao actualreceiveSkuDao;
   
   @RequestMapping({"/findAll2"})
   @ResponseBody
   public DatagridResultInfo findAll2(int page, int rows, ActualreceiveQueryVo actualreceiveQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     actualreceiveQueryVo.setPageQuery(pageQuery);
     int total = this.actualreceiveService.count2(actualreceiveQueryVo);
     List<ActualreceiveCustom> list = this.actualreceiveService.findAll2(actualreceiveQueryVo);
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
     List<ActualreceiveSku> u = this.actualreceiveSkuDao.findAll(pageMap);
     int total = this.actualreceiveSkuDao.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/actdelete"})
   public int actdelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.actualreceiveSkuDao.actdelete(ids[i]);
     }
     return result;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/findact"})
   public Map<String, Object> findact(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
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
     List<ActualreceiveSku> lu = this.actualreceiveSkuDao.findact(pageMap);
     int total = this.actualreceiveSkuDao.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
 
   
   @RequestMapping({"/inmera"})
   @ResponseBody
   public Map<String, String> inmera(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
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
       result = this.actualreceiveService.inmera(String.valueOf(path) + fileName, userId);
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
   
   @RequestMapping({"arDetailedUi"})
   public String findSkuUi(String jobFormId, Model model) {
     model.addAttribute("jobFormId", jobFormId);
     return "/supervision/detailed/arDetailedDetailed";
   }
   
   @RequestMapping({"/arDetailed"})
   public void prDetailed(@RequestParam(value = "jobFormId", required = true) String jobFormId, OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("jobFormId", jobFormId);
     List<Actualreceive> u = this.actualreceiveService.findAll(pageMap);
     int total = this.actualreceiveService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/actts"})
   public void actts(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
     String str = this.actualreceiveService.actts(ids);
     response.getWriter().write(str);
   }
 }


