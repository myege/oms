 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.NuclearRelease;
 import com.what21.model.NuclearReleaseSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.NuclearReleaseService;
 import com.what21.service.NuclearReleaseSkuService;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/nuclearRelease"})
 public class NuclearReleaseAction
   extends BaseAction
 {
   @Autowired
   private NuclearReleaseService nuclearReleaseService;
   @Autowired
   private NuclearReleaseSkuService nuclearReleaseSkuService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<NuclearRelease> u = this.nuclearReleaseService.getAll(pageMap);
     int total = this.nuclearReleaseService.countNuclearRelease();
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/find"})
   public Map<String, Object> find(HttpServletRequest request, OrderSearchDto orderSearchDto) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String pv = orderSearchDto.getParamValue();
     int pn = Integer.parseInt(orderSearchDto.getParamName());
     if (pv != "" && pv != null) {
       if (pn == 1) {
         pageMap.put("paramValue", pv);
         pageMap.put("type", Integer.valueOf(pn));
       } else if (pn == 2) {
         pageMap.put("paramValue", pv);
         pageMap.put("type", Integer.valueOf(pn));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<NuclearRelease> lu = this.nuclearReleaseService.find(pageMap);
     int total = this.nuclearReleaseService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
   
   @ResponseBody
   @RequestMapping({"/addNu"})
   public void addNu(HttpServletResponse response, HttpServletRequest request) throws Exception {
     String tmn = request.getParameter("totalMailNo");
     String pc = request.getParameter("pc");
     String userName = request.getParameter("userName");
     String userId = request.getParameter("userId");
     SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     NuclearRelease n = new NuclearRelease();
     n.setTotalMailNo(tmn);
     n.setPc(pc);
     n.setUserid(Integer.parseInt(userId));
     n.setUsername(userName);
     n.setCreatedatatime(d.format(new Date()));
     int res = this.nuclearReleaseService.addNu(n);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
   
   @ResponseBody
   @RequestMapping({"/deleteN"})
   public int deleteN(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.nuclearReleaseService.deleteN(ids[i]);
     }
     return result;
   }
   
   @ResponseBody
   @RequestMapping({"/findByNuclear"})
   public void findByNuclear(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "totalMailNo", required = true) String totalMailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("totalMailNo", totalMailNo);
     pageMap.put("totalid", id);
     
     List<NuclearReleaseSku> u = this.nuclearReleaseSkuService.findBySku(pageMap);
     
     int total = this.nuclearReleaseSkuService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/changenu"})
   public void changenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String totalMailNo = request.getParameter("totalMailNo");
     String t1 = request.getParameter("t1");
     String totalid = request.getParameter("totalid");
     
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("totalMailNo", totalMailNo);
     pageMap.put("billcode", t1);
     pageMap.put("totalid", totalid);
     NuclearReleaseSku nu = this.nuclearReleaseSkuService.findId(pageMap);
     
     String str = null;
     if (nu != null) {
       str = JSONObject.toJSONString("重复");
     } else {
       NuclearReleaseSku n = new NuclearReleaseSku();
       n.setBillcode(t1);
       n.setStatus("已出库");
       n.setTotalMailNo(totalMailNo);
       n.setTotalid(Integer.parseInt(totalid));
       SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       n.setCreatedatatime(d.format(new Date()));
       this.nuclearReleaseSkuService.add(n);
     } 
     str = JSONObject.toJSONString(null);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/nuclearOrder"})
   public void nuclearOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String mailNo1 = request.getParameter("mailNo1");
     String pc2 = request.getParameter("pc2");
     
     NuclearRelease nuclearRelease = new NuclearRelease();
     nuclearRelease.setTotalMailNo(mailNo1);
     nuclearRelease.setPc(pc2);
     
     NuclearRelease nu = this.nuclearReleaseService.findByPc(nuclearRelease);
     
     if (nu == null) {
       String str1 = JSONObject.toJSONString(null);
       response.getWriter().write(str1);
     } else {
       String str2 = JSONObject.toJSONString("存在");
       response.getWriter().write(str2);
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/print"})
   public void print(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
     sheet.createFreezePane(22, 1);
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
     
     hSSFCellStyle.setAlignment((short)2);
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("分运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("状态");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     Map<String, Object> pageMap = new HashMap<>();
     String ids = request.getParameter("Nu");
     String totalMailNo = request.getParameter("Nid");
     String totalId = request.getParameter("Nnid");
     
     if (ids.equals("cxqbdc")) {
       pageMap.put("Nid", totalMailNo);
       pageMap.put("Nnid", totalId);
       
       List<NuclearReleaseSku> y = this.nuclearReleaseSkuService.findAll(pageMap);
       
       int i = 0;
       for (NuclearReleaseSku go : y) {
         i++;
         row = sheet.createRow(i);
         
         cell = row.createCell(0);
         cell.setCellValue(go.getBillcode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(go.getStatus());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       String[] arr = ids.split(",");
       int a = 0;
       for (int i = 0; i < arr.length; i++) {
         List<NuclearReleaseSku> u = this.nuclearReleaseSkuService.findByB(arr[i]);
         for (int j = 0; j < u.size(); j++) {
           a = i + 1;
           row = sheet.createRow(a);
           
           cell = row.createCell(0);
           cell.setCellValue(((NuclearReleaseSku)u.get(j)).getBillcode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(((NuclearReleaseSku)u.get(j)).getStatus());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
 
 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=nuclearRelease.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


