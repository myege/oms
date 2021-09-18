 package com.what21.action;
 
 import com.what21.model.OrderOutBS;
 import com.what21.model.OrderOutCustom;
 import com.what21.model.OrderOutForExportBS;
 import com.what21.model.OrderOutLog;
 import com.what21.model.OrderOutQueryVoBS;
 import com.what21.model.OrderOutSkuBS;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.OrderOutBSService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.io.OutputStream;
 import java.util.Date;
 import java.util.List;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.ss.util.CellRangeAddress;
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @RequestMapping({"/orderOutBS"})
 @Controller
 public class OrderOutBSAction
   extends BaseAction
 {
   @Autowired
   private OrderOutBSService orderOutService;
   
   @RequestMapping({"/find"})
   @ResponseBody
   public DatagridResultInfo find(int page, int rows, OrderOutQueryVoBS orderOutQueryVo, HttpSession session) throws Exception {
     String username = session.getAttribute("userName").toString();
     Integer userid = Integer.valueOf(Integer.parseInt(session.getAttribute("userId").toString()));
     if (!"admin".equals(username)) {
       System.out.println("in");
       OrderOutCustom orderOutCustom = (orderOutQueryVo.getOrderOutCustom() == null) ? new OrderOutCustom() : orderOutQueryVo.getOrderOutCustom();
       orderOutCustom.setUserid(userid);
       orderOutQueryVo.setOrderOutCustom(orderOutCustom);
     } 
     PageQuery pageQuery = new PageQuery(page, rows);
     orderOutQueryVo.setPageQuery(pageQuery);
     session.setAttribute("orderOutQueryVo", orderOutQueryVo);
     int total = this.orderOutService.count(orderOutQueryVo).intValue();
     List<OrderOutBS> list = this.orderOutService.find(orderOutQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public int deleteByIds(int[] ids) throws Exception {
     try {
       this.orderOutService.deleteByIds(ids);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
 
 
 
   
   @RequestMapping({"/importOrderOut"})
   public ModelAndView importOrderOut(MultipartFile excelFile, HttpSession session) {
     Integer userid = Integer.valueOf(Integer.parseInt(session.getAttribute("userId").toString()));
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
       result = this.orderOutService.importOrderNew(String.valueOf(path) + fileName, userid);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
   
   @RequestMapping({"/findSku"})
   @ResponseBody
   public DatagridResultInfo findSku(int page, int rows, OrderOutQueryVoBS orderOutQueryVo) throws Exception {
     int total = this.orderOutService.countSku(orderOutQueryVo).intValue();
     PageQuery pageQuery = new PageQuery(page, rows);
     orderOutQueryVo.setPageQuery(pageQuery);
     List<OrderOutSkuBS> list = this.orderOutService.findSku(orderOutQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setTotal(total);
     datagridResultInfo.setRows(list);
     return datagridResultInfo;
   }
   @RequestMapping({"/pushQd"})
   @ResponseBody
   public List<String> pushQd(int[] ids) throws Exception {
     return this.orderOutService.pushQd(ids);
   }
   
   @RequestMapping({"/pushSTO2"})
   @ResponseBody
   public List<String> pushSTO2(int[] ids) throws Exception {
     return this.orderOutService.pushSTO2(ids);
   }
 
   
   @RequestMapping({"/endOrders"})
   @ResponseBody
   public int endOrders(int[] ids) throws Exception {
     try {
       this.orderOutService.endOrders(ids);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @RequestMapping({"/updateSkuUI"})
   public String updateSkuUI(Integer id, Model model) throws Exception {
     OrderOutSkuBS orderOutSku = this.orderOutService.findSkuById(id);
     model.addAttribute("orderOutSku", orderOutSku);
     return "updateOutSkuBS";
   }
   @RequestMapping({"/updateSku"})
   @ResponseBody
   public int updateSku(OrderOutSkuBS orderOutSku) throws Exception {
     try {
       this.orderOutService.updateSku(orderOutSku);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   @RequestMapping({"/detail"})
   @ResponseBody
   public OrderOutLog detail(Integer id) {
     OrderOutBS orderOut = this.orderOutService.findById(id);
     OrderOutLog orderOutLog = this.orderOutService.findLogByWaybillno(orderOut.getWaybillno());
     return orderOutLog;
   }
   
   @RequestMapping({"/editGoodsno"})
   @ResponseBody
   public int editGoodsno(String goodsno1, String goodsno2) {
     try {
       Integer i = this.orderOutService.countSkuByGoodsno(goodsno1);
       if (i.intValue() == 0) {
         return -1;
       }
       this.orderOutService.updateGoodsno(goodsno1, goodsno2);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
     return 1;
   }
   
   @RequestMapping({"/exportOrderOut"})
   public void exportOrderOut(int[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     long l1 = System.currentTimeMillis();
     List<OrderOutForExportBS> list = null;
     if (ids.length != 0) {
       list = this.orderOutService.findExportByIds(ids);
     } else {
       OrderOutQueryVoBS orderOutQueryVo = (OrderOutQueryVoBS)request.getSession().getAttribute("orderOutQueryVo");
       orderOutQueryVo.setPageQuery(null);
       list = this.orderOutService.findExport(orderOutQueryVo);
     } 
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
     cellStyle.setAlignment((short)2);
     Cell cell = row.createCell(0);
     
     cell.setCellValue("订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("收货人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("详细地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("主运单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("运单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("汇率");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("运抵国");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("毛重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("净重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("商品编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("商品规格");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("成交数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("成交单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("成交总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("币制");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("商家编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("创建时间");
     cell.setCellStyle(cellStyle);
     int i = 1;
     for (OrderOutForExportBS orderOut : list) {
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(orderOut.getOrderno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(1);
       cell.setCellValue(orderOut.getConsignee());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(orderOut.getConsigneeaddress());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(orderOut.getConsigneetel());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(orderOut.getTotalwaybill());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(orderOut.getWaybillno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(orderOut.getRate());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(orderOut.getDestinationcountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(orderOut.getGrossweight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(orderOut.getNetweight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(orderOut.getGoodsname());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(orderOut.getGoodsno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(orderOut.getSpecifications());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(orderOut.getGoodsamount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue(orderOut.getUnitprice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue(orderOut.getTotalprice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(orderOut.getCurrency());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(orderOut.getItemcode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue(orderOut.getCreatetime());
       cell.setCellStyle(cellStyle);
     } 
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", 
         "attachment;filename=orderOut_" + now + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println(System.currentTimeMillis() - l1);
   }
 
   
   @RequestMapping({"/exportOrderOut2"})
   public void exportOrderOu2t(int[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     long l1 = System.currentTimeMillis();
     List<OrderOutForExportBS> list = null;
     if (ids.length != 0) {
       list = this.orderOutService.findExportByIds(ids);
     } else {
       OrderOutQueryVoBS orderOutQueryVo = (OrderOutQueryVoBS)request.getSession().getAttribute("orderOutQueryVo");
       orderOutQueryVo.setPageQuery(null);
       list = this.orderOutService.findExport(orderOutQueryVo);
     } 
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
     cellStyle.setAlignment((short)2);
     Cell cell = row.createCell(0);
     
     cell.setCellValue("订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("收货人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("详细地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("主运单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("运单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("汇率");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("运抵国");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("毛重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("净重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("商品编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("商品规格");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("成交数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("成交单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("成交总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("币制");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("商家编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("创建时间");
     cell.setCellStyle(cellStyle);
     int i = 1;
     String orderno = null;
     String str = null;
     int start = 0;
     int end = 0;
     Boolean flag = Boolean.valueOf(false);
     for (int j = 0; j < list.size(); j++) {
       OrderOutForExportBS orderOut = list.get(j);
       if (j == list.size() - 1) {
         if (orderno == null) {
           flag = Boolean.valueOf(true);
         } else {
           end = i;
           flag = Boolean.valueOf(false);
           if (!str.contains(orderOut.getGoodsname())) {
             str = String.valueOf(str) + "/" + orderOut.getGoodsname();
           }
         } 
       } else {
         OrderOutForExportBS orderOut2 = list.get(j + 1);
         
         if (!orderOut.getOrderno().equals(orderOut2.getOrderno())) {
           if (orderno == null) {
             flag = Boolean.valueOf(true);
           } else {
             end = i;
             flag = Boolean.valueOf(false);
             if (!str.contains(orderOut.getGoodsname())) {
               str = String.valueOf(str) + "/" + orderOut.getGoodsname();
             }
           } 
         } else {
           
           flag = null;
           if (orderno == null) {
             orderno = orderOut.getOrderno();
             start = i;
             str = orderOut.getGoodsname();
           }
           else if (!str.contains(orderOut.getGoodsname())) {
             str = String.valueOf(str) + "/" + orderOut.getGoodsname();
           } 
         } 
       }       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(orderOut.getOrderno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(1);
       cell.setCellValue(orderOut.getConsignee());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(orderOut.getConsigneeaddress());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(orderOut.getConsigneetel());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(orderOut.getTotalwaybill());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(orderOut.getWaybillno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(orderOut.getRate());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(orderOut.getDestinationcountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(orderOut.getGrossweight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(orderOut.getNetweight());
       cell.setCellStyle(cellStyle);
       
       if (flag != null && flag.booleanValue()) {
         cell = row.createCell(10);
         cell.setCellValue(orderOut.getGoodsname());
         cell.setCellStyle(cellStyle);
       } else if (flag != null && !flag.booleanValue()) {
         sheet.addMergedRegion(new CellRangeAddress(start, end, 10, 10));
         sheet.getRow(start).createCell(10).setCellValue(str);
         str = null;
         orderno = null;
         start = 0;
         end = 0;
       } 
 
       
       cell = row.createCell(11);
       cell.setCellValue(orderOut.getGoodsno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(orderOut.getSpecifications());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(orderOut.getGoodsamount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue(orderOut.getUnitprice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue(orderOut.getTotalprice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(orderOut.getCurrency());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(orderOut.getItemcode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue(orderOut.getCreatetime());
       cell.setCellStyle(cellStyle);
     } 
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", 
         "attachment;filename=orderOut_" + now + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println(System.currentTimeMillis() - l1);
   }
 
   
   @RequestMapping({"/orderOutSkuUI"})
   public String orderOutSkuUI(String orderno, Model model) throws Exception {
     model.addAttribute("orderOut_orderno", orderno);
     return "orderOutSkuBS";
   }
 
   
   @RequestMapping({"/updateWeight"})
   @ResponseBody
   public List<String> updateWeight(int id, String grossWeight, String netWeight) throws Exception {
     return this.orderOutService.updateWeight(id, grossWeight, netWeight);
   }
 }


