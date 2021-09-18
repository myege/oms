 package com.what21.action;
 
 import com.what21.model.BeAccessoryOut;
 import com.what21.model.BeAccessoryOutCustom;
 import com.what21.model.BeAccessoryOutQueryVo;
 import com.what21.model.BeLzdOut;
 import com.what21.model.BeLzdOutCustom;
 import com.what21.model.BeLzdOutQueryVo;
 import com.what21.model.CountBeLzdOut;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.BeAccessoryOutService;
 import com.what21.service.BeLzdOutService;
 import com.what21.service.BeUploadService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.commons.lang.StringUtils;
 import org.apache.poi.hssf.util.CellRangeAddress;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Font;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/beLzdOut"})
 public class BeLzdOutAction
   extends BaseAction
 {
   @Autowired
   private BeLzdOutService beLzdOutService;
   @Autowired
   private BeUploadService beUploadService;
   @Autowired
   private BeAccessoryOutService beAccessoryOutService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int rows, int page, BeLzdOutQueryVo beLzdOutQueryVo) throws Exception {
     beLzdOutQueryVo = (beLzdOutQueryVo == null) ? new BeLzdOutQueryVo() : beLzdOutQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     beLzdOutQueryVo.setPageQuery(pageQuery);
     int total = this.beLzdOutService.countAll(beLzdOutQueryVo);
     List<BeLzdOut> list = this.beLzdOutService.findAll(beLzdOutQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
   
   @RequestMapping({"/addUI"})
   public String addUI() {
     return "addBeLzdOut";
   }
   @RequestMapping({"/add"})
   @ResponseBody
   public ResultInfo add(BeLzdOutQueryVo beLzdOutQueryVo) throws Exception {
     beLzdOutQueryVo = (beLzdOutQueryVo == null) ? new BeLzdOutQueryVo() : beLzdOutQueryVo;
     BeLzdOutCustom beLzdOutCustom = beLzdOutQueryVo.getBeLzdOutCustom();
     beLzdOutQueryVo.setBeLzdOutCustom(beLzdOutCustom);
     ResultInfo resultInfo = this.beLzdOutService.insert(beLzdOutQueryVo);
     return resultInfo;
   }
   
   @RequestMapping({"/download"})
   public void download(int id, String xzlx, HttpServletResponse response) throws Exception {
     BeLzdOut beLzdOut = this.beLzdOutService.findById(Integer.valueOf(id));
     File tarFile = null;
     String fileName = null;
     if (StringUtils.isNotEmpty(xzlx)) {
       if ("lading".equals(xzlx)) {
         BeAccessoryOutQueryVo beAccessoryOutQueryVo = new BeAccessoryOutQueryVo();
         BeAccessoryOutCustom beAccessoryOutCustom = new BeAccessoryOutCustom();
         beAccessoryOutCustom.setLzdid(Integer.valueOf(id));
         beAccessoryOutCustom.setItemname("lading");
         beAccessoryOutQueryVo.setBeAccessoryOutCustom(beAccessoryOutCustom);
         List<BeAccessoryOut> list = this.beAccessoryOutService.findAll(beAccessoryOutQueryVo);
         String path = this.beUploadService.findById("005").getAddress();
         if (list != null) {
           if (list.size() == 1) {
             String tarfilename = ((BeAccessoryOut)list.get(0)).getTarfilename();
             fileName = ((BeAccessoryOut)list.get(0)).getResfilename();
             tarFile = new File(path, tarfilename);
           } else {
             List<File> files = new ArrayList<>();
             List<String> filenames = new ArrayList<>();
             Map<String, Integer> map1 = new HashMap<>();
             for (BeAccessoryOut beAccessoryOut : list) {
               String tarfilename = beAccessoryOut.getTarfilename();
               String resfilename = beAccessoryOut.getResfilename();
               Integer i = map1.get(resfilename);
               if (i == null) {
                 filenames.add(resfilename);
                 map1.put(resfilename, Integer.valueOf(1));
               } else {
                 String suffix = resfilename.substring(resfilename.lastIndexOf("."));
                 String prefix = resfilename.substring(0, resfilename.lastIndexOf("."));
                 filenames.add(String.valueOf(prefix) + "副本" + i + suffix);
                 map1.put(resfilename, Integer.valueOf(i.intValue() + 1));
               } 
               files.add(new File(path, tarfilename));
             } 
             
             fileName = "提单_" + beLzdOut.getTdh() + ".rar";
             tarFile = new File(path, fileName);
             Tools.packageLoad(files, filenames, response, tarFile, beLzdOut.getTdh());
             
             return;
           } 
         }
       } else if (StringUtils.isNotEmpty(xzlx) && 
         "inventory_out".equals(xzlx)) {
         BeAccessoryOutQueryVo beAccessoryOutQueryVo = new BeAccessoryOutQueryVo();
         BeAccessoryOutCustom beAccessoryOutCustom = new BeAccessoryOutCustom();
         beAccessoryOutCustom.setLzdid(Integer.valueOf(id));
         beAccessoryOutCustom.setItemname("inventory_out");
         beAccessoryOutQueryVo.setBeAccessoryOutCustom(beAccessoryOutCustom);
         List<BeAccessoryOut> list = this.beAccessoryOutService.findAll(beAccessoryOutQueryVo);
         String path = this.beUploadService.findById("006").getAddress();
         if (list != null) {
           if (list.size() == 1) {
             String tarfilename = ((BeAccessoryOut)list.get(0)).getTarfilename();
             fileName = ((BeAccessoryOut)list.get(0)).getResfilename();
             tarFile = new File(path, tarfilename);
           } else {
             List<File> files = new ArrayList<>();
             List<String> filenames = new ArrayList<>();
             Map<String, Integer> map1 = new HashMap<>();
             for (BeAccessoryOut beAccessoryOut : list) {
               String tarfilename = beAccessoryOut.getTarfilename();
               String resfilename = beAccessoryOut.getResfilename();
               Integer i = map1.get(resfilename);
               if (i == null) {
                 filenames.add(resfilename);
                 map1.put(resfilename, Integer.valueOf(1));
               } else {
                 String suffix = resfilename.substring(resfilename.lastIndexOf("."));
                 String prefix = resfilename.substring(0, resfilename.lastIndexOf("."));
                 filenames.add(String.valueOf(prefix) + "副本" + i + suffix);
                 map1.put(resfilename, Integer.valueOf(i.intValue() + 1));
               } 
               files.add(new File(path, tarfilename));
             } 
             
             fileName = "清单_" + beLzdOut.getTdh() + ".rar";
             tarFile = new File(path, fileName);
             Tools.packageLoad(files, filenames, response, tarFile, beLzdOut.getTdh());
             
             return;
           } 
         }
       } 
       
       if (tarFile != null) {
         InputStream is = new FileInputStream(tarFile);
         int total = is.available();
         byte[] b = new byte[total];
         is.read(b, 0, total);
         is.close();
         fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
         response.reset();
         response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
         response.addHeader("Content-Length", total+"");
         response.setContentType("application/octet-stream;charset=UTF-8");
         OutputStream outputStream = new BufferedOutputStream((OutputStream)response.getOutputStream());
         outputStream.write(b);
         outputStream.flush();
         outputStream.close();
       } 
     } 
   }
 
 
   
   @RequestMapping({"/updateUI"})
   public String updateUI(int id, Model model) throws Exception {
     BeLzdOut beLzdOut = this.beLzdOutService.findById(Integer.valueOf(id));
     model.addAttribute("beLzdOutCustom", beLzdOut);
     return "updateBeLzdOut";
   }
   @RequestMapping({"/update"})
   @ResponseBody
   public ResultInfo update(BeLzdOutQueryVo beLzdOutQueryVo) {
     try {
       return this.beLzdOutService.update((BeLzdOut)beLzdOutQueryVo.getBeLzdOutCustom());
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "出现了未知错误");
     } 
   }
 
 
 
   
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public int deleteByIds(Integer[] ids) throws Exception {
     try {
       this.beLzdOutService.deleteByIds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
     return 1;
   }
   @RequestMapping({"/count"})
   @ResponseBody
   public CountBeLzdOut count(BeLzdOutCustom beLzdOutCustom) throws Exception {
     CountBeLzdOut countBeLzdOut = this.beLzdOutService.count(beLzdOutCustom);
     if (countBeLzdOut.getZps().intValue() == 0) {
       return null;
     }
     if (countBeLzdOut.getZje().contains(".")) {
       Double d = Double.valueOf(Double.parseDouble(countBeLzdOut.getZje()));
       countBeLzdOut.setZje(Tools.getDouble2(d));
     } 
     if (countBeLzdOut.getZzl().contains(".")) {
       Double d = Double.valueOf(Double.parseDouble(countBeLzdOut.getZzl()));
       countBeLzdOut.setZzl(Tools.getDouble3(d));
     } 
     return countBeLzdOut;
   }
 
   
   @RequestMapping({"/uploadBgUI"})
   public String uploadUI(int id, Model model) throws Exception {
     BeLzdOut beLzdOut = this.beLzdOutService.findById(Integer.valueOf(id));
     model.addAttribute("beLzdOut", beLzdOut);
     return "uploadBgBeLzdOut";
   }
 
 
 
   
   @RequestMapping({"/importBeLzdOut"})
   public ModelAndView importBeLzdOut(MultipartFile excelFile, HttpSession session) {
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
       result = this.beLzdOutService.importBeLzdOut(String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
 
   
   @RequestMapping({"/export"})
   public void export(int[] ids, HttpServletRequest request, HttpServletResponse response, BeLzdOutQueryVo beLzdOutQueryVo) throws Exception {
     List<BeLzdOut> list = null;
     if (ids == null || ids.length == 0) {
       list = this.beLzdOutService.findAll(beLzdOutQueryVo);
     } else {
       list = this.beLzdOutService.findByIds(ids);
     } 
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
     cellStyle.setAlignment((short)2);
     Cell cell = row.createCell(0);
     
     cell.setCellValue("客户名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("到货日期");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("操作日期");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("进仓代理");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("航班号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("航班日期");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("目的地");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("提单件数");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("面单件数");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("重量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("提单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("申报企业");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("发货人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("收货人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("申报金额");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("成本");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("售价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("备注");
     cell.setCellStyle(cellStyle);
     
     int i = 1;
     for (BeLzdOut beLzdOut : list) {
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(beLzdOut.getKhmc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(1);
       cell.setCellValue(beLzdOut.getDhrq());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(beLzdOut.getCzrq());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(beLzdOut.getJcdl());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(beLzdOut.getHbh());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(beLzdOut.getHbrq());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(beLzdOut.getMdd());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(beLzdOut.getTdjs().intValue());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(beLzdOut.getMdjs().intValue());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(beLzdOut.getZl());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(beLzdOut.getTdh());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(beLzdOut.getSbqy());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(beLzdOut.getFhr());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(beLzdOut.getShr());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue(beLzdOut.getSbje());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue(beLzdOut.getCb());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(beLzdOut.getSj());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(beLzdOut.getBz());
       cell.setCellStyle(cellStyle);
     } 
 
 
     
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     String str = new String("出口流转单".getBytes(), "ISO-8859-1");
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", 
         "attachment;filename=" + str + "_" + now + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
   
   @RequestMapping({"/outExpress"})
   public void outExpress(int ids, HttpServletResponse response) throws Exception {
     int[] id = { ids };
     List<BeLzdOut> list = this.beLzdOutService.findByIds(id);
     BeLzdOut beLzdOut = list.get(0);
 
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
     cellStyle.setAlignment((short)1);
     cellStyle.setBorderBottom((short)1);
     cellStyle.setBorderLeft((short)1);
     cellStyle.setBorderTop((short)1);
     cellStyle.setBorderRight((short)1);
     cellStyle.setAlignment((short)2);
     
     Font font = ob.createFont();
     font.setFontName("宋体");
     font.setFontHeightInPoints((short)16);
     
     cellStyle.setFont(font);
 
 
 
     
     Cell cell = row.createCell(0);
     cell.setCellStyle(cellStyle);
     cell.setCellValue("业务流转单");
 
 
     
     row.setHeightInPoints(60.0F);
     sheet.addMergedRegion((CellRangeAddress)new CellRangeAddress(10, 10, 1, 7));
     sheet.addMergedRegion((CellRangeAddress)new CellRangeAddress(0, 0, 0, 7));
     for (int i = 1; i < 10; i++) {
       sheet.addMergedRegion((CellRangeAddress)new CellRangeAddress(i, (short)i, 1, 3));
       sheet.addMergedRegion((CellRangeAddress)new CellRangeAddress(i, (short)i, 5, 7));
     } 
     sheet.setColumnHidden(3000, true);
     
     cell.setCellStyle(cellStyle);
     
     Row row1 = sheet.createRow(1);
     row1.setHeightInPoints(60.0F);
     cell = row1.createCell(0);
     cell.setCellValue("工作号");
     cell.setCellStyle(cellStyle);
     
     cell = row1.createCell(4);
     cell.setCellValue("提单号");
     cell.setCellStyle(cellStyle);
 
     
     cell = row1.createCell(5);
     cell.setCellValue(beLzdOut.getTdh());
     cell.setCellStyle(cellStyle);
 
     
     Row row2 = sheet.createRow(2);
     row2.setHeightInPoints(60.0F);
     cell = row2.createCell(0);
     cell.setCellValue("操作日期");
     cell.setCellStyle(cellStyle);
     
     cell = row2.createCell(1);
     cell.setCellValue(beLzdOut.getCzrq());
     cell.setCellStyle(cellStyle);
     
     cell = row2.createCell(4);
     cell.setCellValue("客户名称");
     cell.setCellStyle(cellStyle);
     
     cell = row2.createCell(5);
     cell.setCellValue(beLzdOut.getJcdl());
     cell.setCellStyle(cellStyle);
     
     Row row3 = sheet.createRow(3);
     row3.setHeightInPoints(60.0F);
     cell = row3.createCell(0);
     cell.setCellValue("目的地");
     cell.setCellStyle(cellStyle);
     
     cell = row3.createCell(1);
     cell.setCellValue(beLzdOut.getMdd());
     cell.setCellStyle(cellStyle);
     
     cell = row3.createCell(4);
     cell.setCellValue("航班号");
     cell.setCellStyle(cellStyle);
     
     cell = row3.createCell(5);
     cell.setCellValue(beLzdOut.getHbh());
     cell.setCellStyle(cellStyle);
     
     Row row4 = sheet.createRow(4);
     row4.setHeightInPoints(60.0F);
     cell = row4.createCell(0);
     cell.setCellValue("航班日期");
     cell.setCellStyle(cellStyle);
     
     cell = row4.createCell(1);
     cell.setCellValue(beLzdOut.getHbrq());
     cell.setCellStyle(cellStyle);
 
     
     cell = row4.createCell(4);
     cell.setCellValue("进仓代理");
     cell.setCellStyle(cellStyle);
     
     cell = row4.createCell(5);
     cell.setCellValue(beLzdOut.getJcdl());
     cell.setCellStyle(cellStyle);
     
     Row row5 = sheet.createRow(5);
     row5.setHeightInPoints(60.0F);
     cell = row5.createCell(0);
     cell.setCellValue("申报企业");
     cell.setCellStyle(cellStyle);
     
     cell = row5.createCell(1);
     cell.setCellValue(beLzdOut.getSbqy());
     cell.setCellStyle(cellStyle);
     
     cell = row5.createCell(4);
     cell.setCellValue("发货人");
     cell.setCellStyle(cellStyle);
     
     cell = row5.createCell(5);
     cell.setCellValue(beLzdOut.getFhr());
     cell.setCellStyle(cellStyle);
     
     Row row6 = sheet.createRow(6);
     row6.setHeightInPoints(60.0F);
     cell = row6.createCell(0);
     cell.setCellValue("收货人");
     cell.setCellStyle(cellStyle);
     
     cell = row6.createCell(1);
     cell.setCellValue(beLzdOut.getShr());
     cell.setCellStyle(cellStyle);
     
     cell = row6.createCell(4);
     cell.setCellValue("提单件数");
     cell.setCellStyle(cellStyle);
     
     cell = row6.createCell(5);
     cell.setCellValue(beLzdOut.getTdjs().intValue());
     cell.setCellStyle(cellStyle);
     
     Row row7 = sheet.createRow(7);
     row7.setHeightInPoints(60.0F);
     cell = row7.createCell(0);
     cell.setCellValue("面单件数");
     cell.setCellStyle(cellStyle);
     
     cell = row7.createCell(1);
     cell.setCellValue(beLzdOut.getMdjs().intValue());
     cell.setCellStyle(cellStyle);
     
     cell = row7.createCell(4);
     cell.setCellValue("重量");
     cell.setCellStyle(cellStyle);
     
     cell = row7.createCell(5);
     cell.setCellValue(beLzdOut.getZl());
     cell.setCellStyle(cellStyle);
 
     
     Row row8 = sheet.createRow(8);
     row8.setHeightInPoints(60.0F);
     cell = row8.createCell(0);
     cell.setCellValue("运抵提单件数");
     cell.setCellStyle(cellStyle);
     
     cell = row8.createCell(1);
     cell.setCellValue("");
     cell.setCellStyle(cellStyle);
     
     cell = row8.createCell(4);
     cell.setCellValue("运抵重量");
     cell.setCellStyle(cellStyle);
 
     
     cell = row8.createCell(5);
     cell.setCellValue("");
     cell.setCellStyle(cellStyle);
     
     Row row9 = sheet.createRow(9);
     row9.setHeightInPoints(60.0F);
     cell = row9.createCell(0);
     cell.setCellValue("申报金额");
     cell.setCellStyle(cellStyle);
 
     
     cell = row9.createCell(1);
     cell.setCellValue(beLzdOut.getSbje());
     cell.setCellStyle(cellStyle);
     
     Row row10 = sheet.createRow(10);
     row10.setHeightInPoints(60.0F);
     cell = row10.createCell(0);
     cell.setCellValue("备注：");
     cell.setCellStyle(cellStyle);
 
     
     cell = row10.createCell(1);
     cell.setCellValue(beLzdOut.getBz());
     cell.setCellStyle(cellStyle);
 
     
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     String str = new String("出口流转单".getBytes(), "ISO-8859-1");
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", 
         "attachment;filename=" + str + "_" + now + ".xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


