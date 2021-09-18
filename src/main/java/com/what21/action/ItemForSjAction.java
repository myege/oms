 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.ItemForSj;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.ItemForSjService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
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
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/itemForSj"})
 public class ItemForSjAction
   extends BaseAction
 {
   @Autowired
   public ItemForSjService itemForSjService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<ItemForSj> u = this.itemForSjService.findAll(pageMap);
     int total = this.itemForSjService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/findintfo"})
   public Map<String, Object> findintfo(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
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
     List<ItemForSj> lu = this.itemForSjService.findintfo(pageMap);
     int total = this.itemForSjService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
 
   
   @RequestMapping({"/inforsj"})
   public ModelAndView inforsj(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
       
       String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       
       excelFile.transferTo(targetFile);
 
       
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.itemForSjService.infor(String.valueOf(path) + fileName, userId);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.csetCode(Integer.valueOf(-1)).csetMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/intfodelete"})
   public int intfodelete(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.itemForSjService.intfodelete(ids[i]);
     }
     return result;
   }
 
 
   
   @RequestMapping({"/updateifsj"})
   public String updateifsj(ItemForSj itemForSj, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.itemForSjService.intfoedit(itemForSj);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/ifsjsj"})
   public void ifsjsj(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
     int str = this.itemForSjService.updateStatus(ids);
     response.getWriter().write(str);
   }
 }


