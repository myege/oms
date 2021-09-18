 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.YTOBillSku;
 import com.what21.model.YtoBill;
 import com.what21.model.YtoBillEx;
 import com.what21.service.YTOBillService;
 import com.what21.service.YTOBillSkuService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/ytoBillAction"})
 public class YTOBillAction
   extends BaseAction
 {
   @Autowired
   private YTOBillService yser;
   @Autowired
   private YTOBillSkuService sser;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String isSigned = request.getSession().getAttribute("tz").toString();
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("isSigned", isSigned);
     pageMap.put("name", "findAll");
     List<YtoBill> u = this.yser.findAllOfYTO(pageMap);
     
     int total = this.yser.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   @RequestMapping({"/QianshouUi"})
   public String QianshouUi(int id, Model model, HttpServletRequest request) {
     model.addAttribute("id", Integer.valueOf(id));
     request.getSession().setAttribute("id", Integer.valueOf(id));
     return "QianshouUi";
   }
   
   @RequestMapping({"/updateForQianshou"})
   public String updateForQianshou(YtoBill yto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     yto.setIsSigned("1");
     int id = ((Integer)request.getSession().getAttribute("id")).intValue();
     JSONObject result = new JSONObject();
     yto.setId(Integer.valueOf(id));
     int i = this.yser.updateForIs(yto);
     if (i > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("error", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return "";
   }
   
   @RequestMapping({"/findByWaybillNo"})
   public void findByWaybillNo(OrderSearchDto orderSerachDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     String isSigned = request.getSession().getAttribute("tz").toString();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time1", orderSerachDto.getTime1());
     pageMap.put("time2", orderSerachDto.getTime2());
     pageMap.put("paramName", orderSerachDto.getParamName());
     pageMap.put("paramValue", orderSerachDto.getParamValue());
     pageMap.put("startPage", Integer.valueOf((orderSerachDto.getPage().intValue() - 1) * orderSerachDto.getRows().intValue()));
     pageMap.put("endPage", orderSerachDto.getRows());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("isSigned", isSigned);
     pageMap.put("value", orderSerachDto.getParamValue());
     pageMap.put("name", orderSerachDto.getParamName());
     pageMap.put("userId", Integer.valueOf(userId));
     
     List<YtoBill> u = this.yser.findByWaybillNo(pageMap);
     request.getSession().setAttribute("u", u);
     int total = this.yser.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/findByWaybillNo_create"})
   public void findByWaybillNo_create(OrderSearchDto orderSerachDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     String isSigned = request.getSession().getAttribute("tz").toString();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time1", orderSerachDto.getTime1());
     pageMap.put("time2", orderSerachDto.getTime2());
     pageMap.put("paramName", orderSerachDto.getParamName());
     pageMap.put("paramValue", orderSerachDto.getParamValue());
     pageMap.put("startPage", Integer.valueOf((orderSerachDto.getPage().intValue() - 1) * orderSerachDto.getRows().intValue()));
     pageMap.put("endPage", orderSerachDto.getRows());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("isSigned", isSigned);
     pageMap.put("value", orderSerachDto.getParamValue());
     pageMap.put("name", orderSerachDto.getParamName());
     pageMap.put("userId", Integer.valueOf(userId));
     
     List<YtoBill> u = this.yser.findByWaybillNo_createTime(pageMap);
     request.getSession().setAttribute("u", u);
     int total = this.yser.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/findByWaybillNo_deliveryTime"})
   public void findByWaybillNo_deliveryTime(OrderSearchDto orderSerachDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     String isSigned = request.getSession().getAttribute("tz").toString();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time1", orderSerachDto.getTime1());
     pageMap.put("time2", orderSerachDto.getTime2());
     pageMap.put("paramName", orderSerachDto.getParamName());
     pageMap.put("paramValue", orderSerachDto.getParamValue());
     pageMap.put("startPage", Integer.valueOf((orderSerachDto.getPage().intValue() - 1) * orderSerachDto.getRows().intValue()));
     pageMap.put("endPage", orderSerachDto.getRows());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("isSigned", isSigned);
     pageMap.put("value", orderSerachDto.getParamValue());
     pageMap.put("name", orderSerachDto.getParamName());
     pageMap.put("userId", Integer.valueOf(userId));
     
     List<YtoBill> u = this.yser.findByWaybillNo_deliveryTime(pageMap);
     request.getSession().setAttribute("u", u);
     int total = this.yser.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/delete"})
   public void delete(HttpServletRequest request, HttpServletResponse response, int[] ids) throws IOException {
     int res = 0;
     this.sser.delete(ids);
     res = this.yser.delete(ids);
     String str = JSONObject.toJSONString(Integer.valueOf(res));
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/deleteAll"})
   public void deleteAll(HttpServletRequest request, HttpServletResponse response) {
     String isSigned = request.getSession().getAttribute("tz").toString();
     this.sser.deleteAll();
     this.yser.deleteAll(isSigned);
   }
   
   @RequestMapping({"/exportOrder"})
   public void exportOrder(HttpServletRequest request, HttpServletResponse response, int[] ids) throws Exception {
     SXSSFWorkbook wb = new SXSSFWorkbook();
     
     Sheet sheet = wb.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = wb.createCellStyle();
     
     cellStyle.setAlignment((short)2);
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("运单号");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(1);
     cell.setCellValue("订单号");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(2);
     cell.setCellValue("寄件人");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(3);
     cell.setCellValue("寄件电话");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(4);
     cell.setCellValue("寄件地址");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(5);
     cell.setCellValue("收件人");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(6);
     cell.setCellValue("收件电话");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(7);
     cell.setCellValue("收件地址");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(8);
     cell.setCellValue("发货时间");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(9);
     cell.setCellValue("走件CODE");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(10);
     cell.setCellValue("走件详情");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(11);
     cell.setCellValue("轨迹发生地");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(12);
     cell.setCellValue("操作人");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(13);
     cell.setCellValue("操作人电话");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(14);
     cell.setCellValue("城市");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(15);
     cell.setCellValue("下级城市");
     cell.setCellStyle(cellStyle);
     cell = row.createCell(16);
     cell.setCellValue("发生时间");
     cell.setCellStyle(cellStyle);
     int j = 0;
     int i = 0;
     
     List<YtoBillEx> ex = new ArrayList<>();
     List<YtoBill> u = (List<YtoBill>)request.getSession().getAttribute("u");
     if (ids.length == 0) {
       if (u != null) {
         int[] ab = new int[u.size()];
         for (YtoBill ytoBill : u) {
           int z = ytoBill.getId().intValue();
           System.out.println(z);
           ab[i] = z;
           i++;
         } 
         ex = this.yser.leftJoin(ab);
         request.getSession().removeAttribute("u");
       } else {
         ex = this.yser.findExport();
       } 
     } else {
       ex = this.yser.leftJoin(ids);
     } 
     
     for (YtoBillEx ytoBillEx : ex) {
       j++;
       row = sheet.createRow(j);
       cell = row.createCell(0);
       cell.setCellValue(ytoBillEx.getWaybillNo());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(1);
       cell.setCellValue(ytoBillEx.getOrderNo());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(2);
       cell.setCellValue(ytoBillEx.getShipperName());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(3);
       cell.setCellValue(ytoBillEx.getShipperTel());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(4);
       cell.setCellValue(ytoBillEx.getShipperAddress());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(5);
       cell.setCellValue(ytoBillEx.getConsigneeName());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(6);
       cell.setCellValue(ytoBillEx.getConsigneeTel());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(7);
       cell.setCellValue(ytoBillEx.getConsigneeAddress());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(8);
       cell.setCellValue(ytoBillEx.getDeliveryTime());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(9);
       cell.setCellValue(ytoBillEx.getEventCode());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(10);
       cell.setCellValue(ytoBillEx.getEventDetail());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(11);
       cell.setCellValue(ytoBillEx.getEventLocation());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(12);
       cell.setCellValue(ytoBillEx.getEventOperater());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(13);
       cell.setCellValue(ytoBillEx.getEventOperaterPhone());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(14);
       cell.setCellValue(ytoBillEx.getCity());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(15);
       cell.setCellValue(ytoBillEx.getNextCity());
       cell.setCellStyle(cellStyle);
       cell = row.createCell(16);
       cell.setCellValue(ytoBillEx.getEventTime());
       cell.setCellStyle(cellStyle);
     } 
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=YTOBill.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     wb.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
   
   @RequestMapping({"/exportNoSucces"})
   public void exportNoSucces(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String set = request.getParameter("set");
     String url = request.getParameter("url");
     String[] arr = set.split(",");
     InputStream is = new FileInputStream(url);
 
 
     
     XSSFWorkbook hssfWorkbook = null;
     try {
       hssfWorkbook = new XSSFWorkbook(is);
       
       for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
         XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if (hssfSheet != null)
         {
 
           
           if (hssfSheet.getLastRowNum() != arr.length)
           {
             for (int i = 0; i < arr.length; i++) {
               
               XSSFRow row = hssfSheet.getRow(Integer.parseInt(arr[i]));
               hssfSheet.removeRow((Row)row);
             }  } 
         }
       } 
     } catch (IOException e) {
       
       e.printStackTrace();
     } 
 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=exportNoSuccess.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     hssfWorkbook.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     
     File delFile = new File(url);
     delFile.delete();
   }
   
   @RequestMapping({"/importOrder"})
   public ModelAndView importOrder(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/ytobillMsg");
     GeneralResult result = new GeneralResult();
     String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
     String fileName = "offline_" + System.currentTimeMillis() + ".xls";
     File targetFile = new File(path, fileName);
     try {
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
 
       
       excelFile.transferTo(targetFile);
 
       
       String isSign = request.getSession().getAttribute("tz").toString();
       result = this.yser.importOrder(String.valueOf(path) + fileName, userId, isSign);
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
   
   @RequestMapping({"/updateById"})
   public String updateById(YtoBill ytoBill, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int id = Integer.parseInt(request.getParameter("id"));
     int resultTotal = 0;
     ytoBill.setId(Integer.valueOf(id));
     resultTotal = this.yser.updateByID(ytoBill);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/insert"})
   public String insert(YtoBill ytoBill, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     String isSigned = request.getSession().getAttribute("tz").toString();
     Date date = new Date();
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     ytoBill.setCreateTime(df.format(date));
     ytoBill.setIsSigned(isSigned);
     YTOBillSku yto = new YTOBillSku();
     yto.setWaybillNo(ytoBill.getWaybillNo());
     yto.setOrderNo(ytoBill.getOrderNo());
     yto.setCreateTime(df.format(date));
     yto.setIsPushed(Integer.valueOf(0));
     resultTotal = this.yser.insertYTO(ytoBill);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/check"})
   public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String orderNo = request.getParameter("orderNo");
     YtoBill yto = new YtoBill();
     yto.setOrderNo(orderNo);
     List<YtoBill> lu = this.yser.findByOrderNo(yto);
     String str = JSONObject.toJSONString(lu);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/YtoUI"})
   public String YtoUI(String waybillNo, String orderNo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
     model.addAttribute("waybillNo", waybillNo);
     model.addAttribute("orderNo", orderNo);
     if (orderNo != null) { request.getSession().setAttribute("orderNo", orderNo); }
     else { request.getSession().setAttribute("orderNo", " "); }
     
     request.getSession().setAttribute("waybillNo_1", waybillNo);
     return "waybillNoInfo";
   }
   
   @RequestMapping({"/addUi"})
   public String addUi(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String waybillNo = request.getSession().getAttribute("waybillNo_1").toString();
     String orderNo = "";
     if (request.getSession().getAttribute("orderNo").toString() != null) {
       orderNo = request.getSession().getAttribute("orderNo").toString();
     }
     model.addAttribute("waybillNo", waybillNo);
     model.addAttribute("orderNo", orderNo);
     return "addUi";
   }
   
   @RequestMapping({"/check_1"})
   public void check_1(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String waybillNo = request.getParameter("waybillNo");
     String isSigned = request.getSession().getAttribute("tz").toString();
     Map<String, Object> map = new HashMap<>();
     map.put("waybillNo", waybillNo);
     map.put("isSigned", isSigned);
     List<YtoBill> lu = this.yser.findByWay_1(map);
     String str = JSONObject.toJSONString(lu);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/updateUI"})
   public String updateUI(int id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
     YTOBillSku yto = new YTOBillSku();
     yto.setId(id);
     List<YTOBillSku> list = this.sser.findbyId(yto);
     for (YTOBillSku sku : list) {
       yto.setId(sku.getId());
       yto.setEventCode(sku.getEventCode());
       yto.setEventCode(sku.getEventCode());
       yto.setEventDetail(sku.getEventDetail());
       yto.setEventLocation(sku.getEventLocation());
       yto.setEventOperater(sku.getEventOperater());
       yto.setEventOperaterPhone(sku.getEventOperaterPhone());
       yto.setCity(sku.getCity());
       yto.setNextCity(sku.getNextCity());
       yto.setEventTime(sku.getEventTime());
     } 
     request.getSession().setAttribute("eventTime", yto.getEventTime());
     model.addAttribute("YTOBillSku", yto);
     return "updateUI";
   }
 }


