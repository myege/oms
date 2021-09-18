 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Inout;
 import com.what21.model.InoutCustom;
 import com.what21.model.InoutPageQueryVo;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.InoutService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.CreationHelper;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/inout"})
 public class InoutAction
   extends BaseAction
 {
   @Autowired
   private InoutService inoutService;
   
   @RequestMapping({"/findAll2"})
   @ResponseBody
   public DatagridResultInfo findAll2(int page, int rows, InoutPageQueryVo inoutPageQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     inoutPageQueryVo.setPageQuery(pageQuery);
     int total = this.inoutService.count2(inoutPageQueryVo);
     List<InoutCustom> list = this.inoutService.findAll2(inoutPageQueryVo);
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
     pageMap.put("flag", Integer.valueOf(2));
     List<Inout> u = this.inoutService.findAll(pageMap);
     int total = this.inoutService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/indelete"})
   public int indelete(@RequestParam(value = "id", required = true) String id) {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.inoutService.indelete(ids[i]);
     }
     return result;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/findin"})
   public Map<String, Object> findin(OrderSearchDto orderSearchDto, HttpServletRequest request, String flag) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("flag", flag);
     String mc = orderSearchDto.getParamValue();
     int mcx = Integer.parseInt(orderSearchDto.getParamName());
     if (mc != "" && mc != null) {
       pageMap.put("paramValue", mc);
       pageMap.put("type", Integer.valueOf(mcx));
     } 
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<Inout> lu = this.inoutService.findin(pageMap);
     int total = this.inoutService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
   
   @RequestMapping({"/inmeri"})
   @ResponseBody
   public Map<String, String> inmeri(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
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
       result = this.inoutService.inmeri(String.valueOf(path) + fileName, userId);
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
   @RequestMapping({"/ints"})
   public void ints(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String str = this.inoutService.ints(userId);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/export2"})
   public void exportSettle2(HttpServletRequest request, HttpServletResponse response, InoutPageQueryVo inoutPageQueryVo) throws Exception {
     SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
 
     
     Sheet sheet = sXSSFWorkbook.createSheet("sheet");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = sXSSFWorkbook.createCellStyle();
     
     CreationHelper createHelper = sXSSFWorkbook.getCreationHelper();
     CellStyle cellStyle1 = sXSSFWorkbook.createCellStyle();
     cellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
     cellStyle1.setAlignment((short)2);
     cellStyle.setAlignment((short)2);
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("出入库记录流水号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("出入库记录编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("账册编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("料号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("料件性质");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("出入库标志");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("出入库数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("出入库时间");
     cell.setCellStyle(cellStyle1);
     
     cell = row.createCell(8);
     cell.setCellValue("运单编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("核放单编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("口岸回执");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("用户id");
     cell.setCellStyle(cellStyle);
 
     
     if (inoutPageQueryVo.getInoutCustom().getIds().equals("cxqbdc")) {
       
       List<Inout> list = this.inoutService.findByPageQuery(inoutPageQueryVo);
       
       int a = 0;
       for (Inout inout : list) {
         a++;
         row = sheet.createRow(a);
         
         cell = row.createCell(0);
         cell.setCellValue(inout.getInOutSeq());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(inout.getInOutNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(inout.getManualId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(inout.getSourceNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(inout.getItemType());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(inout.getInOutFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(inout.getInOutAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(inout.getInOutTime());
         cell.setCellStyle(cellStyle1);
         
         cell = row.createCell(8);
         cell.setCellValue(inout.getWayBillNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(inout.getJobFormId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(inout.getFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(inout.getUserId());
         cell.setCellStyle(cellStyle);
       } 
     } else {
       
       String[] arr = inoutPageQueryVo.getInoutCustom().getIds().split(",");
       int a = 0;
       for (int i = 0; i < arr.length; i++) {
         
         Inout inout = this.inoutService.findBySelect(Integer.valueOf(Integer.parseInt(arr[i])));
         
         a++;
         row = sheet.createRow(a);
         cell = row.createCell(0);
         cell.setCellValue(inout.getInOutSeq());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(inout.getInOutNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(inout.getManualId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(inout.getSourceNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(inout.getItemType());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(inout.getInOutFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(inout.getInOutAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(inout.getInOutTime());
         cell.setCellStyle(cellStyle1);
         
         cell = row.createCell(8);
         cell.setCellValue(inout.getWayBillNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(inout.getJobFormId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(inout.getFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(inout.getUserId());
         cell.setCellStyle(cellStyle);
       } 
     } 
     
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", 
         "attachment;filename=inoutforzt_" + now + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     sXSSFWorkbook.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
   
   @RequestMapping({"/export"})
   public void exportSettle(HttpServletRequest request, HttpServletResponse response) throws Exception {
     SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
 
     
     Sheet sheet = sXSSFWorkbook.createSheet("sheet");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = sXSSFWorkbook.createCellStyle();
     
     CreationHelper createHelper = sXSSFWorkbook.getCreationHelper();
     CellStyle cellStyle1 = sXSSFWorkbook.createCellStyle();
     cellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
     cellStyle1.setAlignment((short)2);
     cellStyle.setAlignment((short)2);
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("出入库记录流水号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("出入库记录编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("账册编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("料号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("料件性质");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("出入库标志");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("出入库数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("出入库时间");
     cell.setCellStyle(cellStyle1);
     
     cell = row.createCell(8);
     cell.setCellValue("运单编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("核放单编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("口岸回执");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("用户id");
     cell.setCellStyle(cellStyle);
 
 
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String ids = request.getParameter("ids");
     String value = request.getParameter("value");
     String name = request.getParameter("name");
     String flag = request.getParameter("flags");
     
     if (ids.equals("cxqbdc")) {
       pageMap.put("paramValue", value);
       pageMap.put("type", name);
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("flag", flag);
       
       List<Inout> list = this.inoutService.findByIds(pageMap);
       
       int a = 0;
       for (Inout inout : list) {
         a++;
         row = sheet.createRow(a);
         
         cell = row.createCell(0);
         cell.setCellValue(inout.getInOutSeq());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(inout.getInOutNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(inout.getManualId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(inout.getSourceNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(inout.getItemType());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(inout.getInOutFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(inout.getInOutAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(inout.getInOutTime());
         cell.setCellStyle(cellStyle1);
         
         cell = row.createCell(8);
         cell.setCellValue(inout.getWayBillNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(inout.getJobFormId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(inout.getFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(inout.getUserId());
         cell.setCellStyle(cellStyle);
       } 
     } else {
       
       String[] arr = ids.split(",");
       int a = 0;
       for (int i = 0; i < arr.length; i++) {
         
         Inout inout = this.inoutService.findBySelect(Integer.valueOf(Integer.parseInt(arr[i])));
         
         a++;
         row = sheet.createRow(a);
         cell = row.createCell(0);
         cell.setCellValue(inout.getInOutSeq());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(inout.getInOutNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(inout.getManualId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(inout.getSourceNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(inout.getItemType());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(inout.getInOutFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(inout.getInOutAmount());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(inout.getInOutTime());
         cell.setCellStyle(cellStyle1);
         
         cell = row.createCell(8);
         cell.setCellValue(inout.getWayBillNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(inout.getJobFormId());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(inout.getFlag());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(inout.getUserId());
         cell.setCellStyle(cellStyle);
       } 
     } 
     
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", 
         "attachment;filename=inoutforzt_" + now + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     sXSSFWorkbook.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


