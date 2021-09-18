 package com.what21.action;
 
 import com.what21.model.PWC;
 import com.what21.model.PageQuery;
 import com.what21.model.Stojs;
 import com.what21.model.StojsBb;
 import com.what21.model.StojsQd;
 import com.what21.model.StojsQueryVo;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.StojsService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.io.OutputStream;
 import java.math.RoundingMode;
 import java.text.DecimalFormat;
 import java.util.Date;
 import java.util.List;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 
 
 
 @RequestMapping({"/stojs"})
 @Controller
 public class StojsAction
   extends BaseAction
 {
   @Autowired
   private StojsService stojsService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int page, int rows, StojsQueryVo stojsQueryVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     stojsQueryVo.setPageQuery(pageQuery);
     int total = this.stojsService.count(stojsQueryVo).intValue();
     List<Stojs> list = this.stojsService.findAll(stojsQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public int deleteByIds(Integer[] ids) throws Exception {
     try {
       this.stojsService.deleteByIds(ids);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
 
 
 
   
   @RequestMapping({"/importStojsQd"})
   public ModelAndView importStojsQd(MultipartFile excelFile, HttpSession session) {
     Integer id = (Integer)session.getAttribute("stojsid");
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
       result = this.stojsService.importOrderNew(id, String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
   
   @RequestMapping({"/findAllQd"})
   @ResponseBody
   public DatagridResultInfo findAllQd(int page, int rows, StojsQueryVo stojsQueryVo) throws Exception {
     int total = this.stojsService.countQd(stojsQueryVo).intValue();
     PageQuery pageQuery = new PageQuery(page, rows);
     stojsQueryVo.setPageQuery(pageQuery);
     List<StojsQd> list = this.stojsService.findAllQd(stojsQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setTotal(total);
     datagridResultInfo.setRows(list);
     return datagridResultInfo;
   }
   @RequestMapping({"/insert"})
   @ResponseBody
   public int insert(Stojs stojs) throws Exception {
     try {
       stojs.setBbzt("0");
       stojs.setQdzt("0");
       this.stojsService.insert(stojs);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @RequestMapping({"/putPid"})
   public void putPid(Integer pid, HttpSession session) throws Exception {
     session.setAttribute("stojsid", pid);
   }
   
   @RequestMapping({"/geneBb"})
   @ResponseBody
   public int geneBb(Integer id) throws Exception {
     try {
       this.stojsService.geneBb(id);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
 
   
   @RequestMapping({"/findAllBb"})
   @ResponseBody
   public DatagridResultInfo findAllBb(int page, int rows, StojsQueryVo stojsQueryVo) throws Exception {
     int total = this.stojsService.countBb(stojsQueryVo).intValue();
     PageQuery pageQuery = new PageQuery(page, rows);
     stojsQueryVo.setPageQuery(pageQuery);
     List<StojsBb> list = this.stojsService.findAllBb(stojsQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setTotal(total);
     datagridResultInfo.setRows(list);
     return datagridResultInfo;
   }
 
 
   
   @RequestMapping({"/exportStojsBb"})
   public void exportStojsBb(String ids, HttpServletRequest request, HttpServletResponse response, StojsQueryVo stojsQueryVo) throws Exception {
     Integer id = stojsQueryVo.getStojsBb().getPid();
     List<StojsBb> list = this.stojsService.findAllBb(stojsQueryVo);
     
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
     hSSFCellStyle.setAlignment((short)2);
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("省份");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("0-0.3KG单量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("0-0.3KG重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("0.3-0.5KG单量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("0.3-0.5KG重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("0.5-1.0KG单量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("0.5-1.0KG重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("1.0-3.0KG单量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("1.0-3.0KG重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("3.0KG以上单量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("3.0KG以上重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     int i = 1;
     for (StojsBb stojsBb : list) {
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(stojsBb.getProvince());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(1);
       cell.setCellValue(stojsBb.getLine1dl().intValue());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(stojsBb.getLine1weight());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(stojsBb.getLine2dl().intValue());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(stojsBb.getLine2weight());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(stojsBb.getLine3dl().intValue());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(stojsBb.getLine3weight());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(stojsBb.getLine4dl().intValue());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(stojsBb.getLine4weight());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(stojsBb.getLine5dl().intValue());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(stojsBb.getLine5weight());
       cell.setCellStyle((CellStyle)hSSFCellStyle);
     } 
     
     DecimalFormat df = new DecimalFormat("0.00");
     df.setRoundingMode(RoundingMode.HALF_UP);
     PWC pwc1 = this.stojsService.getPWCs1NotGroup(id);
     PWC pwc2 = this.stojsService.getPWCs2NotGroup(id);
     PWC pwc3 = this.stojsService.getPWCs3NotGroup(id);
     PWC pwc4 = this.stojsService.getPWCs4NotGroup(id);
     PWC pwc5 = this.stojsService.getPWCs5NotGroup(id);
     if (pwc1.getCount() == null) {
       pwc1.setCount(Integer.valueOf(0));
     }
     if (pwc1.getWeight() == null) {
       pwc1.setWeight("0.00");
     } else {
       Float f = Float.valueOf(Float.parseFloat(pwc1.getWeight()));
       pwc1.setWeight(df.format(f));
     } 
     
     if (pwc2.getCount() == null) {
       pwc2.setCount(Integer.valueOf(0));
     }
     if (pwc2.getWeight() == null) {
       pwc2.setWeight("0.00");
     } else {
       Float f = Float.valueOf(Float.parseFloat(pwc2.getWeight()));
       pwc2.setWeight(df.format(f));
     } 
     
     if (pwc3.getCount() == null) {
       pwc3.setCount(Integer.valueOf(0));
     }
     if (pwc3.getWeight() == null) {
       pwc3.setWeight("0.00");
     } else {
       Float f = Float.valueOf(Float.parseFloat(pwc3.getWeight()));
       pwc3.setWeight(df.format(f));
     } 
     
     if (pwc4.getCount() == null) {
       pwc4.setCount(Integer.valueOf(0));
     }
     if (pwc4.getWeight() == null) {
       pwc4.setWeight("0.00");
     } else {
       Float f = Float.valueOf(Float.parseFloat(pwc4.getWeight()));
       pwc4.setWeight(df.format(f));
     } 
     
     if (pwc5.getCount() == null) {
       pwc5.setCount(Integer.valueOf(0));
     }
     if (pwc5.getWeight() == null) {
       pwc5.setWeight("0.00");
     } else {
       Float f = Float.valueOf(Float.parseFloat(pwc5.getWeight()));
       pwc5.setWeight(df.format(f));
     } 
     
     row = sheet.createRow(i++);
     cell = row.createCell(0);
     cell.setCellValue("合计");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue(pwc1.getCount().intValue());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue(pwc1.getWeight());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue(pwc2.getCount().intValue());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue(pwc2.getWeight());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue(pwc3.getCount().intValue());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue(pwc3.getWeight());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue(pwc4.getCount().intValue());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue(pwc4.getWeight());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue(pwc5.getCount().intValue());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue(pwc5.getWeight());
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     String now = Tools.format("yyyy-MM-dd-HH_mm_ss", new Date());
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", 
         "attachment;filename=stojsBb_" + now + ".xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 }


