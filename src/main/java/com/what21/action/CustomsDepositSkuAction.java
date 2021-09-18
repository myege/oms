 package com.what21.action;
 
 import com.what21.model.CustomsDepositSkuCustom;
 import com.what21.model.CustomsDepositSkuQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.CustomsDepositSkuService;
 import com.what21.tools.Tools;
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
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/customsDepositSku"})
 public class CustomsDepositSkuAction
   extends BaseAction
 {
   @Autowired
   private CustomsDepositSkuService customsDepositSkuService;
   
   @RequestMapping({"/find"})
   @ResponseBody
   public DatagridResultInfo findAll(int rows, int page, CustomsDepositSkuQueryVo customsDepositSkuQueryVo, HttpSession session) throws Exception {
     customsDepositSkuQueryVo = (customsDepositSkuQueryVo == null) ? new CustomsDepositSkuQueryVo() : customsDepositSkuQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     customsDepositSkuQueryVo.setPageQuery(pageQuery);
     int total = this.customsDepositSkuService.count(customsDepositSkuQueryVo).intValue();
     List<CustomsDepositSkuCustom> list = this.customsDepositSkuService.find(customsDepositSkuQueryVo);
     session.setAttribute("customsDepositSkuQueryVo", customsDepositSkuQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
   
   @RequestMapping({"/findByPid"})
   @ResponseBody
   public DatagridResultInfo findByPid(int rows, int page, CustomsDepositSkuQueryVo customsDepositSkuQueryVo) throws Exception {
     customsDepositSkuQueryVo = (customsDepositSkuQueryVo == null) ? new CustomsDepositSkuQueryVo() : customsDepositSkuQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     customsDepositSkuQueryVo.setPageQuery(pageQuery);
     int total = this.customsDepositSkuService.countByPid(customsDepositSkuQueryVo).intValue();
     List<CustomsDepositSkuCustom> list = this.customsDepositSkuService.findByPid(customsDepositSkuQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
   
   @RequestMapping({"/export"})
   public void exportCustomsDepositSkuCustom(int[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     List<CustomsDepositSkuCustom> list = null;
     if (ids.length != 0) {
       list = this.customsDepositSkuService.findByIds(ids);
     } else {
       CustomsDepositSkuQueryVo customsDepositSkuQueryVo = (CustomsDepositSkuQueryVo)request.getSession().getAttribute("customsDepositSkuQueryVo");
       customsDepositSkuQueryVo.setPageQuery(null);
       list = this.customsDepositSkuService.find(customsDepositSkuQueryVo);
     } 
     
     SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
 
     
     Sheet sheet = sXSSFWorkbook.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = sXSSFWorkbook.createCellStyle();
     cellStyle.setAlignment((short)2);
     Cell cell = row.createCell(0);
     
     cell.setCellValue("订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("运单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("总金额");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("税费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("担保企业编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("创建时间");
     cell.setCellStyle(cellStyle);
     
     int i = 1;
     for (CustomsDepositSkuCustom customsDepositSkuCustom : list) {
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(customsDepositSkuCustom.getOrderno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(1);
       cell.setCellValue(customsDepositSkuCustom.getMailno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(customsDepositSkuCustom.getTotalmoney());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(customsDepositSkuCustom.getTax());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(customsDepositSkuCustom.getCompanycode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(Tools.format("yyyy-MM-dd HH:mm:ss", customsDepositSkuCustom.getCreatetime()));
       cell.setCellStyle(cellStyle);
     } 
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", 
         "attachment;filename=customsDepositSku_" + now + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     sXSSFWorkbook.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


