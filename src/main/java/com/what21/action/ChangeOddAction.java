 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.ChangeOdd;
 import com.what21.model.ExportChangeOdd;
 import com.what21.model.GoodsChangeodd;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.Users;
 import com.what21.service.ChangeOddService;
 import com.what21.service.GoodsChangeoddService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.io.OutputStream;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang.StringUtils;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/changeOdd"})
 public class ChangeOddAction
   extends BaseAction
 {
   @Autowired
   private ChangeOddService changeOddService;
   @Autowired
   private GoodsChangeoddService goodschangeoddService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response, OrderSearchDto orderSearchDto) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     Users user = (Users)request.getSession().getAttribute("user");
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     if (!"admin".equals(user.getUserName())) {
       pageMap.put("userId", Integer.valueOf(user.getId()));
     }
     List<ChangeOdd> orderList = this.changeOddService.findAll(pageMap);
     for (ChangeOdd changeOdd : orderList) {
       List<GoodsChangeodd> gList = this.goodschangeoddService.findExpressNumber(changeOdd.getExpressNumber());
       changeOdd.setGoodsList(gList);
     } 
     int total = this.changeOddService.count(pageMap);
     map.put("rows", orderList);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
 
   
   @RequestMapping({"/importOrder"})
   public ModelAndView importOrder(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       
       excelFile.transferTo(targetFile);
 
       
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.changeOddService.importOrder(String.valueOf(path) + fileName, userId);
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
 
 
 
   
   @ResponseBody
   @RequestMapping({"/updateepn"})
   public ChangeOdd updateepn(@RequestParam(value = "aen", required = true) String expressNumber, HttpServletRequest request, HttpServletResponse response) throws Exception {
     ChangeOdd cd = this.changeOddService.updateepn(expressNumber);
     if (cd != null) {
       this.changeOddService.update(expressNumber);
     }
     
     return cd;
   }
 
   
   @ResponseBody
   @RequestMapping({"/updatecod"})
   public ChangeOdd updatecod(@RequestParam(value = "aen", required = true) String expressNumber, HttpServletRequest request, HttpServletResponse response) throws Exception {
     ChangeOdd cod = this.goodschangeoddService.updatecod(expressNumber);
     
     return cod;
   }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/findexpressn"})
   public Map<String, Object> findexpressn(HttpServletRequest request, HttpServletResponse response, OrderSearchDto orderSearchDto) throws Exception {
     Users user = (Users)request.getSession().getAttribute("user");
     
     Map<String, Object> pageMap = new HashMap<>();
     if (!"admin".equals(user.getUserName())) {
       pageMap.put("userId", Integer.valueOf(user.getId()));
     }
     String pv = orderSearchDto.getParamValue();
     int pn = Integer.parseInt(orderSearchDto.getParamName());
     if (pv != null && pv != "") {
       pageMap.put("paramValue", pv);
       pageMap.put("type", Integer.valueOf(pn));
     } 
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     Map<String, Object> map = new HashMap<>();
     List<ChangeOdd> lu = this.changeOddService.findexpn(pageMap);
     int total = this.changeOddService.count(pageMap);
     map.put("rows", lu);
     map.put("total", Integer.valueOf(total));
     return map;
   }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/deleteByIds"})
   public int deleteByIds(int[] ids) throws Exception {
     try {
       this.goodschangeoddService.deleteByPids(ids);
       this.changeOddService.deleteByIds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
     return 1;
   }
 
 
 
   
   @RequestMapping({"/exportChangeOdd"})
   public void exportChangeOdd(int[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int i = 0;
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(i);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("快件公司编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("收件人省份");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件人城市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人地区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人详细地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("收件人联系电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("物品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("物品重量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("物品数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("寄件人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("换(新)单号");
     cell.setCellStyle(cellStyle);
     
     Row row2 = null;
     Cell cell2 = null;
     if (ids.length != 0) {
       List<ExportChangeOdd> exportChangeOdds = this.changeOddService.exportByIds(ids);
       for (ExportChangeOdd exportChangeOdd : exportChangeOdds) {
         row2 = sheet.createRow(++i);
         cell2 = row2.createCell(0);
         
         cell2.setCellValue(exportChangeOdd.getOrderNumber());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(1);
         cell2.setCellValue(exportChangeOdd.getExpressNumber());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(2);
         cell2.setCellValue(exportChangeOdd.getExpressCode());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(3);
         cell2.setCellValue(exportChangeOdd.getBuyerName());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(4);
         cell2.setCellValue(exportChangeOdd.getBuyerProvince());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(5);
         cell2.setCellValue(exportChangeOdd.getBuyerCity());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(6);
         cell2.setCellValue(exportChangeOdd.getBuyerArea());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(7);
         cell2.setCellValue(exportChangeOdd.getBuyerAddress());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(8);
         cell2.setCellValue(exportChangeOdd.getBuyerTel());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(9);
         cell2.setCellValue(exportChangeOdd.getPnname());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(10);
         cell2.setCellValue(exportChangeOdd.getNweight());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(11);
         cell2.setCellValue(exportChangeOdd.getNewnum());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(12);
         cell2.setCellValue(exportChangeOdd.getSender());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(13);
         cell2.setCellValue(exportChangeOdd.getSenderTel());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(14);
         cell2.setCellValue(exportChangeOdd.getSenderAddress());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(15);
         cell2.setCellValue(exportChangeOdd.getNewexpressNumber());
         cell2.setCellStyle(cellStyle);
       } 
     } else {
       String expressNumber = request.getParameter("expressNumber");
       ExportChangeOdd exportChangeOdd2 = new ExportChangeOdd();
       if (StringUtils.isNotEmpty(expressNumber)) {
         exportChangeOdd2.setExpressNumber(expressNumber.trim());
       }
       List<ExportChangeOdd> exportChangeOdds = this.changeOddService.exportByCons(exportChangeOdd2);
       for (ExportChangeOdd exportChangeOdd : exportChangeOdds) {
         row2 = sheet.createRow(++i);
         cell2 = row2.createCell(0);
         
         cell2.setCellValue(exportChangeOdd.getOrderNumber());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(1);
         cell2.setCellValue(exportChangeOdd.getExpressNumber());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(2);
         cell2.setCellValue(exportChangeOdd.getExpressCode());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(3);
         cell2.setCellValue(exportChangeOdd.getBuyerName());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(4);
         cell2.setCellValue(exportChangeOdd.getBuyerProvince());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(5);
         cell2.setCellValue(exportChangeOdd.getBuyerCity());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(6);
         cell2.setCellValue(exportChangeOdd.getBuyerArea());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(7);
         cell2.setCellValue(exportChangeOdd.getBuyerAddress());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(8);
         cell2.setCellValue(exportChangeOdd.getBuyerTel());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(9);
         cell2.setCellValue(exportChangeOdd.getPnname());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(10);
         cell2.setCellValue(exportChangeOdd.getNweight());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(11);
         cell2.setCellValue(exportChangeOdd.getNewnum());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(12);
         cell2.setCellValue(exportChangeOdd.getSender());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(13);
         cell2.setCellValue(exportChangeOdd.getSenderTel());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(14);
         cell2.setCellValue(exportChangeOdd.getSenderAddress());
         cell2.setCellStyle(cellStyle);
         
         cell2 = row2.createCell(15);
         cell2.setCellValue(exportChangeOdd.getNewexpressNumber());
         cell2.setCellStyle(cellStyle);
       } 
     }     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", 
         "attachment;filename=changeodd");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


