 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.GodownEntryCheck;
 import com.what21.model.GodownEntryCheckSku;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.GodownEntryCheckService;
 import com.what21.service.GodownEntryCheckSkuService;
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
 @RequestMapping({"/godownEntryCheck"})
 public class GodownEntryCheckAction
   extends BaseAction
 {
   @Autowired
   private GodownEntryCheckService godownEntryCheckService;
   @Autowired
   private GodownEntryCheckSkuService godownEntryCheckSkuService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<GodownEntryCheck> u = this.godownEntryCheckService.getAll(pageMap);
     int total = this.godownEntryCheckService.countGodownEntryCheck();
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
     List<GodownEntryCheck> lu = this.godownEntryCheckService.find(pageMap);
     int total = this.godownEntryCheckService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/addGo"})
   public void addGo(HttpServletResponse response, HttpServletRequest request) throws Exception {
     String tmn = request.getParameter("totalMailNo");
     String pc = request.getParameter("pc");
     String userName = request.getParameter("userName");
     String userId = request.getParameter("userId");
     SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     GodownEntryCheck g = new GodownEntryCheck();
     g.setTotalMailNo(tmn);
     g.setPc(pc);
     g.setUserid(Integer.parseInt(userId));
     g.setUsername(userName);
     g.setCreatedatatime(d.format(new Date()));
     int res = this.godownEntryCheckService.addGo(g);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/deleteG"})
   public int deleteG(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       GodownEntryCheck godownEntryCheck = this.godownEntryCheckService.findById(ids[i]);
       GodownEntryCheckSku godownEntryCheckSku = new GodownEntryCheckSku();
       godownEntryCheckSku.setTotalMailNo(godownEntryCheck.getTotalMailNo());
       godownEntryCheckSku.setPc(godownEntryCheck.getPc());
       this.godownEntryCheckSkuService.delete(godownEntryCheckSku);
       result = this.godownEntryCheckService.deleteG(ids[i]);
     } 
     return result;
   }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/findbillcode"})
   public void findbillcode(@RequestParam(value = "totalMailNo", required = true) String totalMailNo, @RequestParam(value = "pc", required = true) String pc, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("totalMailNo", totalMailNo);
     pageMap.put("pc", pc);
     List<GodownEntryCheckSku> u = this.godownEntryCheckSkuService.findBySku(pageMap);
     int total = this.godownEntryCheckSkuService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/ztotal"})
   public void ztotal(@RequestParam(value = "totalMailNo", required = true) String totalMailNo, @RequestParam(value = "pc", required = true) String pc, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("totalMailNo", totalMailNo);
     pageMap.put("pc", pc);
     pageMap.put("status", "放行");
     String total = String.valueOf(String.valueOf(this.godownEntryCheckSkuService.count(pageMap))) + "　　";
     String passTotal = String.valueOf(this.godownEntryCheckSkuService.passCount(pageMap));
     String dd = "总数量:" + total + "放行数量:" + passTotal;
     String str = JSONObject.toJSONString(dd);
     response.getWriter().write(str);
   }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/changeStatus"})
   public int changeStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String totalMailNo = request.getParameter("totalMailNo");
     String to = request.getParameter("to");
     String pc = request.getParameter("pc");
     
     GodownEntryCheckSku gec = new GodownEntryCheckSku();
     gec.setBillcode(to);
     gec.setTotalMailNo(totalMailNo);
     gec.setPc(pc);
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     gec.setCreatedatatime(df.format(new Date()));
     GodownEntryCheckSku godownEntryCheckSku = this.godownEntryCheckSkuService.findByMailNo(gec);
     if (godownEntryCheckSku == null) {
       return 0;
     }
     if (godownEntryCheckSku != null && godownEntryCheckSku.getStatus() != null) {
       return 9;
     }
     
     if (godownEntryCheckSku.getMailStatus() == 1) {
       gec.setStatus("放行");
       this.godownEntryCheckSkuService.updateByMailNo(gec);
       return 1;
     } 
     gec.setStatus("待查验");
     this.godownEntryCheckSkuService.updateByMailNo(gec);
     return 2;
   }
 
 
   
   @RequestMapping({"/checkGo"})
   public void checkGo(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String totalMailNo = request.getParameter("T");
     String pc = request.getParameter("P");
     GodownEntryCheck godownentry = new GodownEntryCheck();
     godownentry.setTotalMailNo(totalMailNo);
     godownentry.setPc(pc);
     GodownEntryCheck god = this.godownEntryCheckService.findByPc(godownentry);
     
     if (god == null) {
       String st = JSONObject.toJSONString(null);
       response.getWriter().write(st);
     } else {
       String str = JSONObject.toJSONString("存在");
       response.getWriter().write(str);
     } 
   }
 
 
 
   
   @RequestMapping({"/printout"})
   public void printout(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
     String ids = request.getParameter("exportGodown");
     String totalMailNo = request.getParameter("maiNo");
     
     String pc = request.getParameter("pici1");
 
     
     if (ids.equals("cxqbdc")) {
       pageMap.put("totalMailNo", totalMailNo);
       pageMap.put("pc", pc);
       
       List<GodownEntryCheckSku> list = this.godownEntryCheckSkuService.findAll(pageMap);
       
       int i = 0;
       for (GodownEntryCheckSku go : list) {
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
         List<GodownEntryCheckSku> u = this.godownEntryCheckSkuService.findByBill(arr[i]);
         for (int j = 0; j < u.size(); j++) {
           a = i + 1;
           row = sheet.createRow(a);
           
           cell = row.createCell(0);
           cell.setCellValue(((GodownEntryCheckSku)u.get(j)).getBillcode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(((GodownEntryCheckSku)u.get(j)).getStatus());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=godownEntry.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


