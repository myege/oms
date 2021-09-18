 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Materiel;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.MaterielService;
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
 @RequestMapping({"/materiel"})
 public class MaterielAction
   extends BaseAction
 {
   @Autowired
   public MaterielService materielService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("tea", orderSearchDto.getTea());
     pageMap.put("teb", orderSearchDto.getTeb());
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     List<Materiel> u = this.materielService.findAll(pageMap);
     int total = this.materielService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/Mateadd"})
   public void Mateadd(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String materielsku = request.getParameter("materielsku");
     String materielName = request.getParameter("materielName");
     String merchant = request.getParameter("merchant");
     String pc = request.getParameter("pc");
     int totality = Integer.parseInt(request.getParameter("totality"));
     int surplusInventory = totality;
     String userId = request.getParameter("userId");
     SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Materiel m = new Materiel();
     m.setMaterielsku(materielsku);
     m.setMaterielName(materielName);
     m.setMerchant(merchant);
     m.setPc(pc);
     m.setTotality(totality);
     m.setSurplusInventory(surplusInventory);
     m.setUserId(userId);
     m.setDate(date.format(new Date()));
     int res = this.materielService.Mateadd(m);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/Matedelete"})
   public int Matedelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.materielService.Matedelete(ids[i]);
     }
     return result;
   }
 
   
   @ResponseBody
   @RequestMapping({"/mTime"})
   public void mTime(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("tea", orderSearchDto.getTea());
     pageMap.put("teb", orderSearchDto.getTeb());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     List<Materiel> u = this.materielService.mTime(pageMap);
     int total = this.materielService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/inquiry"})
   public Map<String, Object> inquiry(OrderSearchDto orderSearchDto, HttpServletRequest request) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     String pvv = orderSearchDto.getParamValue();
     int pnn = Integer.parseInt(orderSearchDto.getParamName());
     if (pvv != "" && pvv != null) {
       if (pnn == 1) {
         pageMap.put("paramValue", pvv);
         pageMap.put("type", Integer.valueOf(pnn));
       } else if (pnn == 2) {
         pageMap.put("paramValue", pvv);
         pageMap.put("type", Integer.valueOf(pnn));
       } else if (pnn == 3) {
         pageMap.put("paramValue", pvv);
         pageMap.put("type", Integer.valueOf(pnn));
       } 
     }
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<Materiel> lu = this.materielService.inquiry(pageMap);
     int total = this.materielService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
   
   @ResponseBody
   @RequestMapping({"/pullCommotidie"})
   public int pullCommotidie(HttpServletRequest request, HttpServletResponse response) throws Exception {
     return this.materielService.pullCommotidie();
   }
   
   @ResponseBody
   @RequestMapping({"/updateInventory"})
   public int updateInventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
     return this.materielService.updateInventory();
   }
 
   
   @RequestMapping({"/validate"})
   public void validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String materielsku = request.getParameter("materielsku");
     Map<String, Object> map = new HashMap<>();
     map.put("materielsku", materielsku);
     List<Materiel> luu = this.materielService.validate(map);
     String str = JSONObject.toJSONString(luu);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/updateTotality"})
   public int updateTotality(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String id = request.getParameter("id");
     String totality = request.getParameter("totality");
     Materiel materiel = new Materiel();
     materiel.setId(Integer.parseInt(id));
     materiel.setTotality(Integer.parseInt(totality));
     return this.materielService.updateTotality(materiel);
   }
 
 
 
   
   @RequestMapping({"/printmate"})
   public void printmate(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("物料编码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("物料名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("总库存数");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("已用库存");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("占用库存");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("可用库存");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("入库批次");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("入库时间");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("商家编码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     String ids = request.getParameter("mater");
     String value = request.getParameter("value");
     String name = request.getParameter("name");
     String tea = request.getParameter("tea1");
     String teb = request.getParameter("teb1");
     
     if (ids.equals("cxqbdc")) {
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("value", value);
       pageMap.put("name", name);
       pageMap.put("tea1", tea);
       pageMap.put("teb1", teb);
 
       
       List<Materiel> list = this.materielService.port(pageMap);
       int i = 1;
       for (Materiel materiel : list)
       {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(materiel.getMaterielsku());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(materiel.getMaterielName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(materiel.getTotality());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(materiel.getUsedInventory());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(materiel.getPreUsedInventory());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(materiel.getSurplusInventory());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(materiel.getPc());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(materiel.getDate());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(materiel.getMerchant());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       }
     
     }
     else {
       
       String[] arr = ids.split(",");
       for (int i = 0; i < arr.length; i++) {
         List<Materiel> u = this.materielService.findByIdMateriel(Integer.valueOf(Integer.parseInt(arr[i])));
         for (int j = 0; j < u.size(); j++) {
           Materiel materiel = u.get(j);
           row = sheet.createRow(i + 1);
           
           cell = row.createCell(0);
           cell.setCellValue(materiel.getMaterielsku());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(materiel.getMaterielName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(materiel.getTotality());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(materiel.getUsedInventory());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(materiel.getPreUsedInventory());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(materiel.getSurplusInventory());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(materiel.getPc());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(materiel.getDate());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(materiel.getMerchant());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=materiel.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


