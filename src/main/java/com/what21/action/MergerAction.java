 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Merger;
 import com.what21.model.MergerCustom;
 import com.what21.model.MergerQueryVo;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.MergerService;
 import com.what21.util.GeneralResult;
 import java.io.File;
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
 @RequestMapping({"/merger"})
 public class MergerAction
   extends BaseAction
 {
   @Autowired
   private MergerService mergerService;
   
   @RequestMapping({"/findAll2"})
   @ResponseBody
   public DatagridResultInfo findAll2(int page, int rows, MergerQueryVo mergerQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     mergerQueryVo.setPageQuery(pageQuery);
     int total = this.mergerService.count2(mergerQueryVo);
     List<MergerCustom> list = this.mergerService.findAll2(mergerQueryVo);
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
     List<Merger> u = this.mergerService.findAll(pageMap);
     int total = this.mergerService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/merdelete"})
   public int merdelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.mergerService.merdelete(ids[i]);
     }
     return result;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/cx"})
   public Map<String, Object> cx(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
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
     List<Merger> lu = this.mergerService.cx(pageMap);
     int total = this.mergerService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
 
   
   @RequestMapping({"/inmerm"})
   @ResponseBody
   public Map<String, String> inmerm(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     Map<String, String> map = new HashMap<>();
     String i = "0";
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       
       excelFile.transferTo(targetFile);
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.mergerService.inmerm(String.valueOf(path) + fileName, userId);
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
   @RequestMapping({"/mets"})
   public int mets(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.mergerService.mets(ids);
   }
 }


