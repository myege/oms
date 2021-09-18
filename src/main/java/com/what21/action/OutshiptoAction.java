 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Outshipto;
 import com.what21.service.OutshiptoService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
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
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/outshipto"})
 public class OutshiptoAction
   extends BaseAction
 {
   @Autowired
   private OutshiptoService outshiptoService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response, Outshipto vo) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     vo.setPage(Integer.valueOf((page - 1) * rows));
     vo.setRows(Integer.valueOf(rows));
     List<Outshipto> u = this.outshiptoService.findAll(vo);
     int total = this.outshiptoService.findCount(vo);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/importShip"})
   public ModelAndView importOrder(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/waybillMsg");
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       
       excelFile.transferTo(targetFile);
       
       result = this.outshiptoService.implShip(String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.csetCode(Integer.valueOf(-1)).csetMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       if (targetFile.exists()) {
         targetFile.delete();
       }
       return modelAndView;
     } 
   }
   
   @RequestMapping({"/updeteShip"})
   public String updeteHea(Outshipto end, HttpServletResponse resp, int id) throws Exception {
     int resulT = 0;
     end.setId(Integer.valueOf(id));
     resulT = this.outshiptoService.update(end);
     JSONObject result = new JSONObject();
     if (resulT > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(resp, result);
     return null;
   }
 
   
   @RequestMapping({"/geviShiup"})
   public String geviShiup(HttpServletResponse resp, String id) throws Exception {
     int resulT = 1;
     JSONObject result = new JSONObject();
     String str = this.outshiptoService.geviShiup((new StringBuilder(String.valueOf(id))).toString());
     if (resulT > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(resp, result);
     return null;
   }
   
   @RequestMapping({"/delteShiup"})
   public String delteShiup(HttpServletResponse resp, String id) throws Exception {
     int resulT = 1;
     JSONObject result = new JSONObject();
     this.outshiptoService.delete((new StringBuilder(String.valueOf(id))).toString());
     if (resulT > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(resp, result);
     return null;
   }
 }


