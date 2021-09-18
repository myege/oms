 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.CirculaTion;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.CirculaTionService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletException;
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
 @RequestMapping({"/circulaTion"})
 public class CirculaTionAction
   extends BaseAction
 {
   @Autowired
   private CirculaTionService circulaTionService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     int auditstatus = Integer.parseInt(request.getSession().getAttribute("tz").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     userId = getUserId(userName, userId);
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("auditstatus", Integer.valueOf(auditstatus));
     List<CirculaTion> u = this.circulaTionService.findAll(pageMap);
     int total = this.circulaTionService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/addcira"})
   public String addcira(CirculaTion circulaTion, HttpServletResponse response, HttpServletRequest request) throws Exception {
     int resultTotal = 0;
     int auditstatus = Integer.parseInt(request.getSession().getAttribute("tz").toString());
     if (auditstatus == 9) {
       resultTotal = this.circulaTionService.inserta(circulaTion);
     } else if (auditstatus == 0) {
       resultTotal = this.circulaTionService.insertaa(circulaTion);
     } 
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/addcirqd"})
   public String addcirqd(CirculaTion circulaTion, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.circulaTionService.upcirsb(circulaTion);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
   
   @RequestMapping({"/addcirb"})
   public String addcirb(CirculaTion circulaTion, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.circulaTionService.insertb(circulaTion);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
 
   
   @RequestMapping({"/addcirc"})
   public String addcirc(CirculaTion circulaTion, HttpServletResponse response, HttpServletRequest request) throws Exception {
     int resultTotal = 0;
     int auditstatus = Integer.parseInt(request.getSession().getAttribute("tz").toString());
     if (auditstatus == 9) {
       resultTotal = this.circulaTionService.insertc(circulaTion);
     } else if (auditstatus == 0) {
       resultTotal = this.circulaTionService.insciraa(circulaTion);
     } 
     
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
   @RequestMapping({"/deletecir"})
   public int deletecir(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.circulaTionService.deletecir(ids[i]);
     }
     return result;
   }
   
   @RequestMapping({"/upload"})
   public ModelAndView upload(@RequestParam("circulaTionFile") MultipartFile circulaTionFile, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
 
       
       String fileName = circulaTionFile.getOriginalFilename();
       
       int a = fileName.lastIndexOf(".");
       String fileType = fileName.substring(a, fileName.length());
       
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       } else {
         result.setMessage("文件名重复！");
         modelAndView.addObject("result", result);
         return modelAndView;
       } 
       circulaTionFile.transferTo(targetFile);
       result.setMessage("上传成功！");
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage("上传失败！");
     } 
     modelAndView.addObject("result", result);
     return modelAndView;
   }
   
   @RequestMapping({"filedownload"})
   public void filedownload(HttpServletResponse response, HttpServletRequest request, String fileName) throws Exception {
     response.setContentType("text/html;charset=UTF-8");
     
     BufferedInputStream in = null;
     BufferedOutputStream out = null;
     request.setCharacterEncoding("UTF-8");
     String rootpath = request.getSession().getServletContext().getRealPath("C:/Users/Administrator/upload");
 
     
     try {
       File f = new File(String.valueOf(rootpath) + "uploadFile/" + fileName);
       response.setContentType("application/x-excel");
       response.setCharacterEncoding("UTF-8");
       response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
       response.setHeader("Content-Length", String.valueOf(f.length()));
       in = new BufferedInputStream(new FileInputStream(f));
       out = new BufferedOutputStream((OutputStream)response.getOutputStream());
       byte[] data = new byte[1024];
       int len = 0;
       while (-1 != (len = in.read(data, 0, data.length))) {
         out.write(data, 0, len);
       }
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       if (in != null) {
         in.close();
       }
       if (out != null)
         out.close(); 
     } 
   }
 }


