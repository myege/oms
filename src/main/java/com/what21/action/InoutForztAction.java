 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Inout;
 import com.what21.model.InoutQueryVo;
 import com.what21.model.WmsInoutForzt;
 import com.what21.service.InoutForztService;
 import com.what21.tools.Tools;
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
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"/inoutforzt"})
 public class InoutForztAction
   extends BaseAction
 {
   @Autowired
   private InoutForztService inoutForztService;
   
   @RequestMapping({"/findAll"})
   public void findAll(HttpServletRequest request, HttpServletResponse response, WmsInoutForzt wmsInoutForzt) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("wmsInoutForzt", wmsInoutForzt);
     List<WmsInoutForzt> u = this.inoutForztService.findAll(pageMap);
     int total = this.inoutForztService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/pull"})
   public void pull(HttpServletRequest request, HttpServletResponse response) throws Exception {
     System.out.println(">>>>>>>>>>>>>>>>>>>laqu");
     this.inoutForztService.updateAll();
     List<InoutQueryVo> list = this.inoutForztService.pullAll();
     for (InoutQueryVo ii : list) {
       List<WmsInoutForzt> wlist = this.inoutForztService.findByMan(ii.getManualId(), ii.getSourceNo());
       WmsInoutForzt wif = new WmsInoutForzt();
       if (wlist.size() == 0) {
         wif.setManualid(ii.getManualId());
         wif.setSourceno(ii.getSourceNo());
         wif.setCreattime(Tools.getCurrentTime());
         if ("A1".equals(ii.getInOutFlag())) {
           wif.setA1(Integer.valueOf(ii.getNum()));
         }
         if ("A2".equals(ii.getInOutFlag())) {
           wif.setA2(Integer.valueOf(ii.getNum()));
         }
         if ("A3".equals(ii.getInOutFlag())) {
           wif.setA3(Integer.valueOf(ii.getNum()));
         }
         if ("A4".equals(ii.getInOutFlag())) {
           wif.setA4(Integer.valueOf(ii.getNum()));
         }
         if ("A5".equals(ii.getInOutFlag())) {
           wif.setA5(Integer.valueOf(ii.getNum()));
         }
         if ("A6".equals(ii.getInOutFlag())) {
           wif.setA6(Integer.valueOf(ii.getNum()));
         }
         if ("A7".equals(ii.getInOutFlag())) {
           wif.setA7(Integer.valueOf(ii.getNum()));
         }
         if ("B".equals(ii.getInOutFlag())) {
           wif.setB(Integer.valueOf(ii.getNum()));
         }
         if ("C".equals(ii.getInOutFlag())) {
           wif.setC(Integer.valueOf(ii.getNum()));
         }
         if ("C2".equals(ii.getInOutFlag())) {
           wif.setC2(Integer.valueOf(ii.getNum()));
         }
         if ("D".equals(ii.getInOutFlag())) {
           wif.setD(Integer.valueOf(ii.getNum()));
         }
         if ("E".equals(ii.getInOutFlag())) {
           wif.setE(Integer.valueOf(ii.getNum()));
         }
         if ("F1".equals(ii.getInOutFlag())) {
           wif.setF1(Integer.valueOf(ii.getNum()));
         }
         if ("F2".equals(ii.getInOutFlag())) {
           wif.setF2(Integer.valueOf(ii.getNum()));
         }
         if ("F3".equals(ii.getInOutFlag())) {
           wif.setF3(Integer.valueOf(ii.getNum()));
         }
         if ("F4".equals(ii.getInOutFlag())) {
           wif.setF4(Integer.valueOf(ii.getNum()));
         }
         if ("F5".equals(ii.getInOutFlag())) {
           wif.setF5(Integer.valueOf(ii.getNum()));
         }
         if ("F6".equals(ii.getInOutFlag())) {
           wif.setF6(Integer.valueOf(ii.getNum()));
         }
         if ("F7".equals(ii.getInOutFlag())) {
           wif.setF7(Integer.valueOf(ii.getNum()));
         }
         if ("F8".equals(ii.getInOutFlag())) {
           wif.setF8(Integer.valueOf(ii.getNum()));
         }
         if ("G".equals(ii.getInOutFlag())) {
           wif.setG(Integer.valueOf(ii.getNum()));
         }
         if ("H".equals(ii.getInOutFlag())) {
           wif.setH(Integer.valueOf(ii.getNum()));
         }
         if ("I".equals(ii.getInOutFlag())) {
           wif.setI(Integer.valueOf(ii.getNum()));
         }
         if ("J".equals(ii.getInOutFlag())) {
           wif.setJ(Integer.valueOf(ii.getNum()));
         }
         if ("K".equals(ii.getInOutFlag())) {
           wif.setK(Integer.valueOf(ii.getNum()));
         }
         if ("L".equals(ii.getInOutFlag())) {
           wif.setL(Integer.valueOf(ii.getNum()));
         }
         this.inoutForztService.insert(wif); continue;
       } 
       WmsInoutForzt wm = wlist.get(0);
       wif.setManualid(ii.getManualId());
       System.out.println(ii.getManualId());
       wif.setSourceno(ii.getSourceNo());
       System.out.println(ii.getSourceNo());
       wif.setEndtime(Tools.getCurrentTime());
       if ("A1".equals(ii.getInOutFlag())) {
         if (wm.getA1() == null) {
           wif.setA1(Integer.valueOf(ii.getNum()));
         } else {
           wif.setA1(Integer.valueOf(ii.getNum() + wm.getA1().intValue()));
         } 
       } else {
         wif.setA1(wm.getA1());
       } 
       if ("A2".equals(ii.getInOutFlag())) {
         if (wm.getA2() == null) {
           wif.setA2(Integer.valueOf(ii.getNum()));
         } else {
           wif.setA2(Integer.valueOf(ii.getNum() + wm.getA2().intValue()));
         } 
       } else {
         wif.setA2(wm.getA2());
       } 
       if ("A3".equals(ii.getInOutFlag())) {
         if (wm.getA3() == null) {
           wif.setA3(Integer.valueOf(ii.getNum()));
         } else {
           wif.setA3(Integer.valueOf(ii.getNum() + wm.getA3().intValue()));
         } 
       } else {
         wif.setA3(wm.getA3());
       } 
       if ("A4".equals(ii.getInOutFlag())) {
         if (wm.getA4() == null) {
           wif.setA4(Integer.valueOf(ii.getNum()));
         } else {
           wif.setA4(Integer.valueOf(ii.getNum() + wm.getA4().intValue()));
         } 
       } else {
         wif.setA4(wm.getA4());
       } 
       if ("A5".equals(ii.getInOutFlag())) {
         if (wm.getA5() == null) {
           wif.setA5(Integer.valueOf(ii.getNum()));
         } else {
           wif.setA5(Integer.valueOf(ii.getNum() + wm.getA5().intValue()));
         } 
       } else {
         wif.setA5(wm.getA5());
       } 
       if ("A6".equals(ii.getInOutFlag())) {
         if (wm.getA6() == null) {
           wif.setA6(Integer.valueOf(ii.getNum()));
         } else {
           wif.setA6(Integer.valueOf(ii.getNum() + wm.getA6().intValue()));
         } 
       } else {
         wif.setA6(wm.getA6());
       } 
       if ("A7".equals(ii.getInOutFlag())) {
         if (wm.getA7() == null) {
           wif.setA7(Integer.valueOf(ii.getNum()));
         } else {
           wif.setA7(Integer.valueOf(ii.getNum() + wm.getA7().intValue()));
         } 
       } else {
         wif.setA7(wm.getA7());
       } 
       if ("B".equals(ii.getInOutFlag())) {
         if (wm.getB() == null) {
           wif.setB(Integer.valueOf(ii.getNum()));
         } else {
           wif.setB(Integer.valueOf(ii.getNum() + wm.getB().intValue()));
         } 
       } else {
         wif.setB(wm.getB());
       } 
       if ("C".equals(ii.getInOutFlag())) {
         if (wm.getC() == null) {
           wif.setC(Integer.valueOf(ii.getNum()));
         } else {
           wif.setC(Integer.valueOf(ii.getNum() + wm.getC().intValue()));
         } 
       } else {
         wif.setC(wm.getC());
       } 
       if ("C2".equals(ii.getInOutFlag())) {
         if (wm.getC2() == null) {
           wif.setC2(Integer.valueOf(ii.getNum()));
         } else {
           wif.setC2(Integer.valueOf(ii.getNum() + wm.getC2().intValue()));
         } 
       } else {
         wif.setC2(wm.getC2());
       } 
       if ("D".equals(ii.getInOutFlag())) {
         if (wm.getD() == null) {
           wif.setD(Integer.valueOf(ii.getNum()));
         } else {
           wif.setD(Integer.valueOf(ii.getNum() + wm.getD().intValue()));
         } 
       } else {
         wif.setD(wm.getD());
       } 
       if ("E".equals(ii.getInOutFlag())) {
         if (wm.getE() == null) {
           wif.setE(Integer.valueOf(ii.getNum()));
         } else {
           wif.setE(Integer.valueOf(ii.getNum() + wm.getE().intValue()));
         } 
       } else {
         wif.setE(wm.getE());
       } 
       if ("F1".equals(ii.getInOutFlag())) {
         if (wm.getF1() == null) {
           wif.setF1(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF1(Integer.valueOf(ii.getNum() + wm.getF1().intValue()));
         } 
       } else {
         wif.setF1(wm.getF1());
       } 
       if ("F2".equals(ii.getInOutFlag())) {
         if (wm.getF2() == null) {
           wif.setF2(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF2(Integer.valueOf(ii.getNum() + wm.getF2().intValue()));
         } 
       } else {
         wif.setF2(wm.getF2());
       } 
       if ("F3".equals(ii.getInOutFlag())) {
         if (wm.getF3() == null) {
           wif.setF3(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF3(Integer.valueOf(ii.getNum() + wm.getF3().intValue()));
         } 
       } else {
         wif.setF3(wm.getF3());
       } 
       if ("F4".equals(ii.getInOutFlag())) {
         if (wm.getF4() == null) {
           wif.setF4(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF4(Integer.valueOf(ii.getNum() + wm.getF4().intValue()));
         } 
       } else {
         wif.setF4(wm.getF4());
       } 
       if ("F5".equals(ii.getInOutFlag())) {
         if (wm.getF5() == null) {
           wif.setF5(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF5(Integer.valueOf(ii.getNum() + wm.getF5().intValue()));
         } 
       } else {
         wif.setF5(wm.getF5());
       } 
       if ("F6".equals(ii.getInOutFlag())) {
         if (wm.getF6() == null) {
           wif.setF6(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF6(Integer.valueOf(ii.getNum() + wm.getF6().intValue()));
         } 
       } else {
         wif.setF6(wm.getF6());
       } 
       if ("F7".equals(ii.getInOutFlag())) {
         if (wm.getF7() == null) {
           wif.setF7(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF7(Integer.valueOf(ii.getNum() + wm.getF7().intValue()));
         } 
       } else {
         wif.setF7(wm.getF7());
       } 
       if ("F8".equals(ii.getInOutFlag())) {
         if (wm.getF8() == null) {
           wif.setF8(Integer.valueOf(ii.getNum()));
         } else {
           wif.setF8(Integer.valueOf(ii.getNum() + wm.getF8().intValue()));
         } 
       } else {
         wif.setF8(wm.getF8());
       } 
       if ("G".equals(ii.getInOutFlag())) {
         if (wm.getG() == null) {
           wif.setG(Integer.valueOf(ii.getNum()));
         } else {
           wif.setG(Integer.valueOf(ii.getNum() + wm.getG().intValue()));
         } 
       } else {
         wif.setG(wm.getG());
       } 
       if ("H".equals(ii.getInOutFlag())) {
         if (wm.getH() == null) {
           wif.setH(Integer.valueOf(ii.getNum()));
         } else {
           wif.setH(Integer.valueOf(ii.getNum() + wm.getH().intValue()));
         } 
       } else {
         wif.setH(wm.getH());
       } 
       if ("I".equals(ii.getInOutFlag())) {
         if (wm.getI() == null) {
           wif.setI(Integer.valueOf(ii.getNum()));
         } else {
           wif.setI(Integer.valueOf(ii.getNum() + wm.getI().intValue()));
         } 
       } else {
         wif.setI(wm.getI());
       } 
       if ("J".equals(ii.getInOutFlag())) {
         if (wm.getJ() == null) {
           wif.setJ(Integer.valueOf(ii.getNum()));
         } else {
           wif.setJ(Integer.valueOf(ii.getNum() + wm.getJ().intValue()));
         } 
       } else {
         wif.setJ(wm.getJ());
       } 
       if ("K".equals(ii.getInOutFlag())) {
         if (wm.getK() == null) {
           wif.setK(Integer.valueOf(ii.getNum()));
         } else {
           wif.setK(Integer.valueOf(ii.getNum() + wm.getK().intValue()));
         } 
       } else {
         wif.setK(wm.getK());
       } 
       if ("L".equals(ii.getInOutFlag())) {
         if (wm.getL() == null) {
           wif.setL(Integer.valueOf(ii.getNum()));
         } else {
           wif.setL(Integer.valueOf(ii.getNum() + wm.getL().intValue()));
         } 
       } else {
         wif.setL(wm.getL());
       } 
       this.inoutForztService.updateforzt(wif);
     } 
     
     this.inoutForztService.updatezc();
   }
   
   @RequestMapping({"findSkuUi"})
   public String findSkuUi(String manualid, String sourceno, Model model) {
     model.addAttribute("manualid", manualid);
     model.addAttribute("sourceno", sourceno);
     return "/supervision/detailed/inoutforztDetailed";
   }
 
 
   
   @RequestMapping({"/findSku"})
   public void findSku(String manualid, String sourceno, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("manualid", manualid);
     pageMap.put("sourceno", sourceno);
     List<Inout> u = this.inoutForztService.findSku(pageMap);
     int total = this.inoutForztService.countByMs(manualid, sourceno);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/exportforzt"})
   public void exportSettle(String idsu, String ids, String exmanualid, String exsourceno, HttpServletRequest request, HttpServletResponse response) throws Exception {
     List<Inout> list = null;
     
     if (ids != null) {
       list = this.inoutForztService.findByIds(ids);
     } else {
       list = this.inoutForztService.findSkuByMS(exmanualid, exsourceno);
     } 
 
     
     SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
 
     
     Sheet sheet = sXSSFWorkbook.createSheet("sheet");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = sXSSFWorkbook.createCellStyle();
     
     CreationHelper createHelper = sXSSFWorkbook.getCreationHelper();
     CellStyle cellStyle1 = sXSSFWorkbook.createCellStyle();
     cellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
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
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("运单编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("核放单编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("口岸回执");
     cell.setCellStyle(cellStyle);
 
 
 
     
     int i = 1;
     for (Inout inout : list) {
       row = sheet.createRow(i++);
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
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(inout.getWayBillNo());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(inout.getJobFormId());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(inout.getFlag());
       cell.setCellStyle(cellStyle);
     } 
     row = sheet.createRow(i + 1);
     
     List<WmsInoutForzt> wlist = null;
     if (idsu != null) {
       wlist = this.inoutForztService.findByIdsu(idsu);
     }
     cell = row.createCell(0);
     cell.setCellValue("账册编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("料号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("A1（正常进库【先进后报】）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("F1(正常出库【快递出去】)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("A3(正常进库【区内转入】)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("F4(正常出库【区内转出】)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("创建时间");
     cell.setCellStyle(cellStyle1);
     
     cell = row.createCell(7);
     cell.setCellValue("最后操作时间");
     cell.setCellStyle(cellStyle1);
     
     for (WmsInoutForzt wif : wlist) {
       row = sheet.createRow(i + 2);
       cell = row.createCell(0);
       cell.setCellValue(wif.getManualid());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(1);
       cell.setCellValue(wif.getSourceno());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       if (wif.getA1() == null) {
         cell.setCellValue("");
       } else {
         cell.setCellValue(wif.getA1().intValue());
       } 
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       if (wif.getF1() == null) {
         cell.setCellValue("");
       } else {
         cell.setCellValue(wif.getF1().intValue());
       } 
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       if (wif.getA3() == null) {
         cell.setCellValue("");
       } else {
         cell.setCellValue(wif.getA3().intValue());
       } 
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       if (wif.getF4() == null) {
         cell.setCellValue("");
       } else {
         cell.setCellValue(wif.getF4().intValue());
       } 
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(wif.getCreattime());
       cell.setCellStyle(cellStyle1);
       
       cell = row.createCell(7);
       if (wif.getEndtime() == null) {
         cell.setCellValue("");
       } else {
         cell.setCellValue(wif.getEndtime());
       } 
       cell.setCellStyle(cellStyle1);
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


