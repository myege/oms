 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.ChangeOdd;
 import com.what21.model.CompanyZyAndOrderMail;
 import com.what21.model.FindBytxLogisticIDForYtoExcel;
 import com.what21.model.GoodsChangeodd;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderMailAndChangeOdd;
 import com.what21.model.OrderMailForExport;
 import com.what21.model.OrderMailSku;
 import com.what21.model.OrderMailSkuAll;
 import com.what21.model.OrderMailVo;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.Users;
 import com.what21.service.ChangeOddService;
 import com.what21.service.CompanyforzyService;
 import com.what21.service.GoodsChangeoddService;
 import com.what21.service.OrderMailService;
 import com.what21.service.OrderMailSkuService;
 import com.what21.service.UsersService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
 import com.zt.request.GetPayNumber;
 import java.io.File;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.text.ParseException;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.StringTokenizer;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFFont;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/orderMail"})
 public class OrderMailAction
   extends BaseAction
 {
   @Autowired
   public OrderMailService orderMailService;
   @Autowired
   public UsersService users;
   @Autowired
   public ChangeOddService changodd;
   @Autowired
   public OrderMailSkuService orderMailSkuService;
   @Autowired
   public CompanyforzyService comservice;
   @Autowired
   GoodsChangeoddService gservice;
   
   @RequestMapping({"/outMailOrder_GDUI"})
   public String outMailOrder_GDUI() {
     return "/directMailInsepection/operate/outMailOrder_GD";
   }
   
   @RequestMapping({"/findInfoUi"})
   public String findInfoUi(String txLogisticID, HttpServletRequest request, Model model) {
     request.getSession().setAttribute("txLogisticID", txLogisticID);
     return "/directMail/operate/directMailInfo";
   }
   @RequestMapping({"/pushCZUI"})
   public String pushCZUI(String txLogisticID, HttpServletRequest request, Model model) {
     request.getSession().setAttribute("txLogisticID", txLogisticID);
     return "/directMailInsepection/operate/pushCZ";
   }
   @RequestMapping({"/showPushCz"})
   public String showPushCzUI(String txLogisticID, HttpServletRequest request, Model model) {
     request.getSession().setAttribute("txLogisticID", txLogisticID);
     return "/directMailInsepection/operate/showPushCz";
   }
   @RequestMapping({"/deliveChangeUI"})
   public String deliveChangeUI(Model model, int[] ids, HttpServletRequest request, HttpServletResponse response) {
     request.getSession().setAttribute("ids", ids);
     List<Users> us = this.users.findAll_1(0);
     model.addAttribute("user", us);
     return "/directMailInsepection/operate/deliveChangeUi";
   }
   @RequestMapping({"/editMatterUI"})
   public String editMatterUI(Model model, int id, HttpServletRequest request, HttpServletResponse response) {
     OrderMail orderMail = this.orderMailService.findOneById(id);
     request.getSession().setAttribute("txLogisticID_editMatter", orderMail.getTxLogisticID());
     request.getSession().setAttribute("id_editMatter", Integer.valueOf(id));
     model.addAttribute("orderMail", this.orderMailService.findOneById(id));
     return "/directMailInsepection/operate/editMatter";
   }
 
 
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     int auditstatus = Integer.parseInt(request.getParameter("auditstatus"));
     pageMap.put("txLogisticID", orderSearchDto.getTxLogisticID());
     pageMap.put("receiveMan", orderSearchDto.getReceiveMan());
     pageMap.put("mailNo", orderSearchDto.getMailNo());
     pageMap.put("totalMailNo", orderSearchDto.getTotalMailNo());
     pageMap.put("fightNumber", orderSearchDto.getFightNumber());
     pageMap.put("payNumber", orderSearchDto.getPayNumber());
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("auditstatus", Integer.valueOf(auditstatus));
     if (auditstatus == 2) {
       int returncode = 2;
       pageMap.put("auditstatus", Integer.valueOf(9));
       pageMap.put("returncode", Integer.valueOf(returncode));
     } 
     if (auditstatus == 9) {
       int returncode = 9;
       pageMap.put("returncode", Integer.valueOf(returncode));
     } 
     if (auditstatus == 7) {
       pageMap.put("auditstatus", Integer.valueOf(7));
     }
     List<OrderMail> u = this.orderMailService.findAll(pageMap);
     int total = this.orderMailService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/findByParam"})
   public void findByParam(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("startPage", 
         Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     pageMap.put("userId", Integer.valueOf(userId));
     List<OrderMail> u = this.orderMailService.findAll(pageMap);
     int total = this.orderMailService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
   
   @RequestMapping({"/importOrderMail"})
   @ResponseBody
   public Map<String, String> importOrderMail(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
     GeneralResult result = new GeneralResult();
     Map<String, String> map = new HashMap<>();
     String i = "0";
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
       String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       excelFile.transferTo(targetFile);
       int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
       result = this.orderMailService.importOrderNew(String.valueOf(path) + fileName, userId);
       if (result.getMessage().contains("导入成功")) {
         i = "1";
       } else {
         i = "0";
       } 
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage("导入失败！");
       i = "0";
     } 
     map.put("code", i);
     map.put("msg", result.getMessage());
     return map;
   }
   
   @ResponseBody
   @RequestMapping({"/deleteOrderMail"})
   public int deleteOrderMail(String[] ids) {
     int result = this.orderMailService.deleteOrderMail(ids);
     return result;
   }
   
   @ResponseBody
   @RequestMapping({"/auditOrder"})
   public int auditOrder(String paramJson, HttpServletRequest request, HttpServletResponse response) {
     try {
       return this.orderMailService.updateAuditstatus(paramJson);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/matchMailNo"})
   public int matchMailNo(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.updateExpressParam(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrder"})
   public int pushOrder(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.updateIspost(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrderToDsds"})
   public int pushOrderToDsds(String ids, HttpServletRequest request, HttpServletResponse response) {
     try {
       return this.orderMailService.pushOrderToDsds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/pushOrderToYf"})
   public int pushOrderToYf(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     return this.orderMailService.updateIspostToYf(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushCz"})
   public void pushCz(OrderMailVo orderMailVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String totalMailNo = orderMailVo.getTotalMailNo_cz();
     String cpNo = orderMailVo.getCpNo_cz();
     String result = this.orderMailService.updateIsPushCzNew(totalMailNo, cpNo);
     response.getWriter().write(result);
   }
   
   @ResponseBody
   @RequestMapping({"/pushCzStatus"})
   public void pushCzStatus(OrderMailVo orderMailVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String totalMailNo = orderMailVo.getTotalMailNo_cz();
     String result = this.orderMailService.pushCzStatus(totalMailNo.trim());
     response.getWriter().write(result);
   }
   
   @ResponseBody
   @RequestMapping({"/customs"})
   public int customs(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.updateIsCustoms(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushDd"})
   public int pushDd(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.pushDd(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/pushQd"})
   public int pushQd(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.pushQd(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/push3"})
   public int push3(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     return this.orderMailService.push3(ids);
   }
   
   @ResponseBody
   @RequestMapping({"/storage"})
   public int storage(String totalMailNo, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.storage(totalMailNo);
   }
   
   @ResponseBody
   @RequestMapping({"/deliverGoods"})
   public int deliverGoods(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.updateAuditstatusByIds(ids);
   }
   
   @RequestMapping({"/Detailed"})
   public void Detailed(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = orderSearchDto.getPage().intValue();
     int rows = orderSearchDto.getRows().intValue();
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     String txLogisticID = (String)request.getSession().getAttribute("txLogisticID_editMatter");
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("txLogisticID", txLogisticID);
     List<OrderMailSku> u = this.orderMailSkuService.detailedMailSku(pageMap);
     int total = this.orderMailSkuService.count(txLogisticID);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/getMailBill"})
   public void getMailBill(String mailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String ret = this.orderMailService.getMailBill(mailNo);
     if (ret.contains("false") || !ret.contains("<detail>")) {
       response.getWriter().write("1");
     } else {
       response.getWriter().write(ret);
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/getPayNumber"})
   public int getPayNumber(String txLogisticID, HttpServletRequest request, HttpServletResponse response) {
     int ret = -1;
     try {
       String txLogisticID1 = request.getParameter("txLogisticID");
       OrderMail orderMail = new OrderMail();
       if (txLogisticID1 == "") {
         List<OrderMail> u = this.orderMailService.findTxLogisticID();
         
         if (u.size() == 0) {
           ret = 0;
         }
         for (OrderMail n : u) {
           String str = 
             GetPayNumber.get("http://www.haidai5.com/tools/getPay.php?order_sn=" + n.getTxLogisticID());
           
           String payNumber = str.substring(str.indexOf("支付单号是：") + 6, str.indexOf("<br />姓名是："));
           
           String buyerName = str.substring(str.indexOf("姓名是：") + 4, str.indexOf("<br />身份证是："));
           
           String buyerIdNumber = str.substring(str.indexOf("身份证是：") + 5, str.indexOf("<br />电话是："));
           
           String receiveManPhone = str.substring(str.indexOf("电话是：") + 4, str.lastIndexOf("<br />"));
           
           if (!"".equals(payNumber)) {
             orderMail.setPayNumber(payNumber);
             orderMail.setBuyerName(buyerName);
             orderMail.setBuyerIdNumber(buyerIdNumber);
             orderMail.setReceiveManPhone(receiveManPhone);
             orderMail.setTxLogisticID(n.getTxLogisticID());
             ret = this.orderMailService.payNumber(orderMail);
           } 
         } 
       } else {
         String[] arr = txLogisticID1.split(",");
         for (int i = 0; i < arr.length; i++) {
           String str = GetPayNumber.get("http://www.haidai5.com/tools/getPay.php?order_sn=" + arr[i]);
 
           
           String payNumber = str.substring(str.indexOf("支付单号是：") + 6, str.indexOf("<br />姓名是："));
           
           String buyerName = str.substring(str.indexOf("姓名是：") + 4, str.indexOf("<br />身份证是："));
           
           String buyerIdNumber = str.substring(str.indexOf("身份证是：") + 5, str.indexOf("<br />电话是："));
 
           
           String receiveManPhone = str.substring(str.indexOf("电话是：") + 4, str.lastIndexOf("<br />"));
 
           
           if (!"".equals(payNumber)) {
             orderMail.setPayNumber(payNumber);
             orderMail.setBuyerName(buyerName);
             orderMail.setBuyerIdNumber(buyerIdNumber);
             orderMail.setReceiveManPhone(receiveManPhone);
             orderMail.setTxLogisticID(arr[i]);
             ret = this.orderMailService.payNumber(orderMail);
           } 
         } 
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } 
     return ret;
   }
   
   @RequestMapping({"/getTax"})
   public void getTax(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     String ret = this.orderMailService.getTax(userId);
     response.getWriter().write(ret);
   }
 
   
   @ResponseBody
   @RequestMapping({"/findAllmx"})
   public ModelAndView findAllmx(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = true) int id) throws IOException {
     List<OrderMail> om = this.orderMailService.findAllmx(id);
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.addObject("om", om);
     modelAndView.setViewName("orderMailDetailed");
     return modelAndView;
   }
   
   @RequestMapping({"/exporttotalMailNo"})
   public void exporttotalMailNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle1 = ob.createCellStyle();
     hSSFCellStyle1.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     cell.setCellValue("快件公司名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(2);
     cell.setCellValue("物品详述");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(3);
     cell.setCellValue("单位名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(4);
     cell.setCellValue("单价");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(5);
     cell.setCellValue("物品数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(6);
     cell.setCellValue("总额");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(7);
     cell.setCellValue("起运国代码");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(8);
     cell.setCellValue("电商企业代码");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     cell = row.createCell(9);
     cell.setCellValue("电商企业名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle1);
     
     HSSFCellStyle style = ob.createCellStyle();
     
     style.setVerticalAlignment((short)1);
     style.setAlignment((short)2);
 
     
     HSSFFont font = ob.createFont();
     font.setColor((short)8);
     font.setFontHeightInPoints((short)10);
     font.setBoldweight((short)700);
     
     style.setFont(font);
     
     sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
     cell = sheet.getRow(0).createCell(0);
     cell = row.createCell(0);
     cell.setCellStyle(style);
     cell.setCellValue("杭州嘉联电子商务有限公司");
     
     row = sheet.createRow(1);
     cell = row.createCell(0);
     cell.setCellValue("快件公司名称");
     cell.setCellStyle(style);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle(style);
     
     cell = row.createCell(2);
     cell.setCellValue("物品详述");
     cell.setCellStyle(style);
     
     cell = row.createCell(3);
     cell.setCellValue("单位名称");
     cell.setCellStyle(style);
     
     cell = row.createCell(4);
     cell.setCellValue("单价");
     cell.setCellStyle(style);
     
     cell = row.createCell(5);
     cell.setCellValue("物品数量");
     cell.setCellStyle(style);
     
     cell = row.createCell(6);
     cell.setCellValue("总额");
     cell.setCellStyle(style);
     
     cell = row.createCell(7);
     cell.setCellValue("起运国代码");
     cell.setCellStyle(style);
     
     cell = row.createCell(8);
     cell.setCellValue("电商企业代码");
     cell.setCellStyle(style);
     
     cell = row.createCell(9);
     cell.setCellValue("电商企业名称");
     cell.setCellStyle(style);
     
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String ids = request.getParameter("totalMailed");
     String value = request.getParameter("totalValue");
     value = value.replaceAll("\\s*", "");
 
     
     if (ids == "cxqbdc" || ids.equals("cxqbdc")) {
       
       List<FindBytxLogisticIDForYtoExcel> list = this.orderMailService.findBytxLogisticIDForYtoExcels(value);
       
       int i = 1;
       for (FindBytxLogisticIDForYtoExcel findBytxLogisticIDForYtoExcel : list) {
         row = sheet.createRow(++i);
         cell = row.createCell(0);
         cell.setCellValue("浙江圆通速递邮箱公司");
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(1);
         cell.setCellValue(findBytxLogisticIDForYtoExcel.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(2);
         cell.setCellValue(findBytxLogisticIDForYtoExcel.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(3);
         cell.setCellValue(findBytxLogisticIDForYtoExcel.getUnitname());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(4);
         cell.setCellValue(findBytxLogisticIDForYtoExcel.getUnitPrice());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(5);
         cell.setCellValue(findBytxLogisticIDForYtoExcel.getItemCount());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(6);
         cell.setCellValue(findBytxLogisticIDForYtoExcel.getAllPrice());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(7);
         cell.setCellValue(findBytxLogisticIDForYtoExcel.getTradeCountry());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(8);
         cell.setCellValue("330156K024");
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(9);
         cell.setCellValue("杭州嘉联电子商务有限公司");
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
       } 
     } else {
       
       StringTokenizer toKenizer = new StringTokenizer(ids, ",");
       String[] strx = new String[toKenizer.countTokens()];
       int[] id = new int[toKenizer.countTokens()];
       for (int i = 0; i <= toKenizer.countTokens(); i++) {
         strx[i] = toKenizer.nextToken();
         id[i] = Integer.valueOf(strx[i]).intValue();
       } 
       int j = 2;
       List<FindBytxLogisticIDForYtoExcel> list = this.orderMailService.findBytxLogisticIDForYtoExcelById(id);
       for (FindBytxLogisticIDForYtoExcel findBytxLogisticIDForYtoExcel2 : list) {
         row = sheet.createRow(j++);
         cell = row.createCell(0);
         cell.setCellValue("浙江圆通速递邮箱公司");
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(1);
         cell.setCellValue(findBytxLogisticIDForYtoExcel2.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(2);
         cell.setCellValue(findBytxLogisticIDForYtoExcel2.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(3);
         cell.setCellValue(findBytxLogisticIDForYtoExcel2.getUnitname());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(4);
         cell.setCellValue(findBytxLogisticIDForYtoExcel2.getUnitPrice());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(5);
         cell.setCellValue(findBytxLogisticIDForYtoExcel2.getItemCount());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(6);
         cell.setCellValue(findBytxLogisticIDForYtoExcel2.getAllPrice());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(7);
         cell.setCellValue(findBytxLogisticIDForYtoExcel2.getTradeCountry());
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(8);
         cell.setCellValue("330156K024");
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
         
         cell = row.createCell(9);
         cell.setCellValue("杭州嘉联电子商务有限公司");
         cell.setCellStyle((CellStyle)hSSFCellStyle1);
       } 
     } 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=ytoExprot.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 
 
   
   @RequestMapping({"/exportorderMails"})
   public void exportorderMails(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("快件公司编号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("身份证姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("身份证号码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件人省份");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人城市");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人地区");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("收件人详细地址");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("收件人邮编");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("收件人国家");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("收件人联系电话");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("物品重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("物品名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("物品品名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("物品单价（RMB）");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("物品数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("物品总价");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("物品规格");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("物品计量单位名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("物品HS");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("起运国代码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("提运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(23);
     cell.setCellValue("航班号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("客户订单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(25);
     cell.setCellValue("消费税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(26);
     cell.setCellValue("增值税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(27);
     cell.setCellValue("综合税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String ids1 = request.getParameter("costexp1");
     String idd1 = request.getParameter("costexpp1");
     String id1 = request.getParameter("costexps1");
     
     if (ids1.equals("cxqbdc")) {
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("id", id1);
       List<OrderMail> list = this.orderMailService.findeXport(pageMap);
       int i = 1;
       for (OrderMail orderMail : list) {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderMail.getCarrier());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderMail.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderMail.getReceiveMan());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderMail.getBuyerName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderMail.getBuyerIdNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(orderMail.getReceiveProvince());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(orderMail.getReceiveCity());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(orderMail.getReceiveCounty());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(orderMail.getReceiveManAddress());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(orderMail.getReceivecode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(orderMail.getReceiveCountr());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(orderMail.getReceiveManPhone());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(orderMail.getItemWeight().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(orderMail.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(orderMail.getClassname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(orderMail.getPrices().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(orderMail.getIt());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(orderMail.getAllp().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(orderMail.getStandard());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(orderMail.getUnitname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(20);
         cell.setCellValue(orderMail.getHs());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(orderMail.getTradeCountry());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(22);
         cell.setCellValue(orderMail.getTotalMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(23);
         cell.setCellValue(orderMail.getFightNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(24);
         cell.setCellValue(orderMail.getTxLogisticID());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(25);
         Double du = orderMail.getExs();
         if (du == null) {
           du = Double.valueOf(0.0D);
         }
         cell.setCellValue(du.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(26);
         
         Double dv = orderMail.getVas();
         if (dv == null) {
           dv = Double.valueOf(0.0D);
         }
         cell.setCellValue(dv.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(27);
         
         Double dc = orderMail.getCons();
         if (dc == null) {
           dc = Double.valueOf(0.0D);
         }
         cell.setCellValue(dc.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       String[] arr = idd1.split(",");
       for (int i = 0; i < arr.length; i++) {
         List<OrderMail> u = this.orderMailService.findeXportId(Integer.valueOf(Integer.parseInt(arr[i])));
         for (int j = 0; j < u.size(); j++) {
           OrderMail orderMail = u.get(j);
           row = sheet.createRow(i + 1);
           cell = row.createCell(0);
           cell.setCellValue(orderMail.getCarrier());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(orderMail.getMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(orderMail.getReceiveMan());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(orderMail.getBuyerName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(orderMail.getBuyerIdNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(orderMail.getReceiveProvince());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(orderMail.getReceiveCity());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(orderMail.getReceiveCounty());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(orderMail.getReceiveManAddress());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(9);
           cell.setCellValue(orderMail.getReceivecode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(10);
           cell.setCellValue(orderMail.getReceiveCountr());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(11);
           cell.setCellValue(orderMail.getReceiveManPhone());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(12);
           cell.setCellValue(orderMail.getItemWeight().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(13);
           cell.setCellValue(orderMail.getItemName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(14);
           cell.setCellValue(orderMail.getClassname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(15);
           cell.setCellValue(orderMail.getPrices().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(16);
           cell.setCellValue(orderMail.getIt());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(17);
           cell.setCellValue(orderMail.getAllp().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(18);
           cell.setCellValue(orderMail.getStandard());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(19);
           cell.setCellValue(orderMail.getUnitname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(20);
           cell.setCellValue(orderMail.getHs());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(21);
           cell.setCellValue(orderMail.getTradeCountry());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(22);
           cell.setCellValue(orderMail.getTotalMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(23);
           cell.setCellValue(orderMail.getFightNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(24);
           cell.setCellValue(orderMail.getTxLogisticID());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(25);
           Double du = orderMail.getExs();
           if (du == null) {
             du = Double.valueOf(0.0D);
           }
           cell.setCellValue(du.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(26);
           
           Double dv = orderMail.getVas();
           if (dv == null) {
             dv = Double.valueOf(0.0D);
           }
           cell.setCellValue(dv.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(27);
           
           Double dc = orderMail.getCons();
           if (dc == null) {
             dc = Double.valueOf(0.0D);
           }
           cell.setCellValue(dc.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=cost.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 
 
   
   @RequestMapping({"/exportorderMail1"})
   public void exportorderMail(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("快件公司编号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("身份证姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("身份证号码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件人省份");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人城市");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人地区");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("收件人详细地址");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("收件人邮编");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("收件人国家");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("收件人联系电话");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("物品重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("物品名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("物品品名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("物品单价（RMB）");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("物品数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("物品总价");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("物品规格");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("物品计量单位名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("物品HS");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("起运国代码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("提运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(23);
     cell.setCellValue("航班号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("客户订单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(25);
     cell.setCellValue("消费税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(26);
     cell.setCellValue("增值税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(27);
     cell.setCellValue("综合税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String ids = request.getParameter("costexp");
     String idd = request.getParameter("costexpp");
     String id = request.getParameter("costexps");
     
     if (ids.equals("cxqbdc")) {
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("id", id);
       List<OrderMail> list = this.orderMailService.findeXport(pageMap);
       int i = 1;
       for (OrderMail orderMail : list) {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderMail.getCarrier());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderMail.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderMail.getReceiveMan());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderMail.getBuyerName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderMail.getBuyerIdNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(orderMail.getReceiveProvince());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(orderMail.getReceiveCity());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(orderMail.getReceiveCounty());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(orderMail.getReceiveManAddress());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(orderMail.getReceivecode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(orderMail.getReceiveCountr());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(orderMail.getReceiveManPhone());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(orderMail.getItemWeight().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(orderMail.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(orderMail.getClassname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(orderMail.getPrices().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(orderMail.getIt());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(orderMail.getAllp().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(orderMail.getStandard());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(orderMail.getUnitname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(20);
         cell.setCellValue(orderMail.getHs());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(orderMail.getTradeCountry());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(22);
         cell.setCellValue(orderMail.getTotalMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(23);
         cell.setCellValue(orderMail.getFightNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(24);
         cell.setCellValue(orderMail.getTxLogisticID());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(25);
         Double du = orderMail.getExs();
         if (du == null) {
           du = Double.valueOf(0.0D);
         }
         cell.setCellValue(du.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(26);
         
         Double dv = orderMail.getVas();
         if (dv == null) {
           dv = Double.valueOf(0.0D);
         }
         cell.setCellValue(dv.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(27);
         
         Double dc = orderMail.getCons();
         if (dc == null) {
           dc = Double.valueOf(0.0D);
         }
         cell.setCellValue(dc.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       String[] arr = idd.split(",");
       for (int i = 0; i < arr.length; i++) {
         List<OrderMail> u = this.orderMailService.findeXportId(Integer.valueOf(Integer.parseInt(arr[i])));
         for (int j = 0; j < u.size(); j++) {
           OrderMail orderMail = u.get(j);
           row = sheet.createRow(i + 1);
           cell = row.createCell(0);
           cell.setCellValue(orderMail.getCarrier());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(orderMail.getMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(orderMail.getReceiveMan());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(orderMail.getBuyerName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(orderMail.getBuyerIdNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(orderMail.getReceiveProvince());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(orderMail.getReceiveCity());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(orderMail.getReceiveCounty());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(orderMail.getReceiveManAddress());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(9);
           cell.setCellValue(orderMail.getReceivecode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(10);
           cell.setCellValue(orderMail.getReceiveCountr());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(11);
           cell.setCellValue(orderMail.getReceiveManPhone());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(12);
           cell.setCellValue(orderMail.getItemWeight().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(13);
           cell.setCellValue(orderMail.getItemName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(14);
           cell.setCellValue(orderMail.getClassname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(15);
           cell.setCellValue(orderMail.getPrices().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(16);
           cell.setCellValue(orderMail.getIt());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(17);
           cell.setCellValue(orderMail.getAllp().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(18);
           cell.setCellValue(orderMail.getStandard());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(19);
           cell.setCellValue(orderMail.getUnitname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(20);
           cell.setCellValue(orderMail.getHs());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(21);
           cell.setCellValue(orderMail.getTradeCountry());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(22);
           cell.setCellValue(orderMail.getTotalMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(23);
           cell.setCellValue(orderMail.getFightNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(24);
           cell.setCellValue(orderMail.getTxLogisticID());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(25);
           Double du = orderMail.getExs();
           if (du == null) {
             du = Double.valueOf(0.0D);
           }
           cell.setCellValue(du.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(26);
           
           Double dv = orderMail.getVas();
           if (dv == null) {
             dv = Double.valueOf(0.0D);
           }
           cell.setCellValue(dv.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(27);
           
           Double dc = orderMail.getCons();
           if (dc == null) {
             dc = Double.valueOf(0.0D);
           }
           cell.setCellValue(dc.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=cost.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 
 
   
   @RequestMapping({"/exportorderMailss"})
   public void exportorderMailss(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("快件公司编号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("身份证姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("身份证号码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件人省份");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人城市");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人地区");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("收件人详细地址");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("收件人邮编");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("收件人国家");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("收件人联系电话");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("物品重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("物品名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("物品品名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("物品单价（RMB）");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("物品数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("物品总价");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("物品规格");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("物品计量单位名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("物品HS");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("起运国代码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("提运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(23);
     cell.setCellValue("航班号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("客户订单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(25);
     cell.setCellValue("消费税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(26);
     cell.setCellValue("增值税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(27);
     cell.setCellValue("综合税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String ids1 = request.getParameter("costexppd");
     String idd1 = request.getParameter("costexppdss");
     String id1 = request.getParameter("costexppds");
     
     if (ids1.equals("cxqbdc")) {
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("id", id1);
       List<OrderMail> list = this.orderMailService.findeXport(pageMap);
       int i = 1;
       for (OrderMail orderMail : list) {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderMail.getCarrier());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderMail.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderMail.getReceiveMan());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderMail.getBuyerName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderMail.getBuyerIdNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(orderMail.getReceiveProvince());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(orderMail.getReceiveCity());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(orderMail.getReceiveCounty());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(orderMail.getReceiveManAddress());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(orderMail.getReceivecode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(orderMail.getReceiveCountr());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(orderMail.getReceiveManPhone());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(orderMail.getItemWeight().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(orderMail.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(orderMail.getClassname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(orderMail.getPrices().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(orderMail.getIt());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(orderMail.getAllp().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(orderMail.getStandard());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(orderMail.getUnitname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(20);
         cell.setCellValue(orderMail.getHs());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(orderMail.getTradeCountry());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(22);
         cell.setCellValue(orderMail.getTotalMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(23);
         cell.setCellValue(orderMail.getFightNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(24);
         cell.setCellValue(orderMail.getTxLogisticID());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(25);
         Double du = orderMail.getExs();
         if (du == null) {
           du = Double.valueOf(0.0D);
         }
         cell.setCellValue(du.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(26);
         
         Double dv = orderMail.getVas();
         if (dv == null) {
           dv = Double.valueOf(0.0D);
         }
         cell.setCellValue(dv.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(27);
         
         Double dc = orderMail.getCons();
         if (dc == null) {
           dc = Double.valueOf(0.0D);
         }
         cell.setCellValue(dc.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       String[] arr = idd1.split(",");
       for (int i = 0; i < arr.length; i++) {
         List<OrderMail> u = this.orderMailService.findeXportId(Integer.valueOf(Integer.parseInt(arr[i])));
         for (int j = 0; j < u.size(); j++) {
           OrderMail orderMail = u.get(j);
           row = sheet.createRow(i + 1);
           cell = row.createCell(0);
           cell.setCellValue(orderMail.getCarrier());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(orderMail.getMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(orderMail.getReceiveMan());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(orderMail.getBuyerName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(orderMail.getBuyerIdNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(orderMail.getReceiveProvince());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(orderMail.getReceiveCity());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(orderMail.getReceiveCounty());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(orderMail.getReceiveManAddress());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(9);
           cell.setCellValue(orderMail.getReceivecode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(10);
           cell.setCellValue(orderMail.getReceiveCountr());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(11);
           cell.setCellValue(orderMail.getReceiveManPhone());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(12);
           cell.setCellValue(orderMail.getItemWeight().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(13);
           cell.setCellValue(orderMail.getItemName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(14);
           cell.setCellValue(orderMail.getClassname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(15);
           cell.setCellValue(orderMail.getPrices().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(16);
           cell.setCellValue(orderMail.getIt());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(17);
           cell.setCellValue(orderMail.getAllp().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(18);
           cell.setCellValue(orderMail.getStandard());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(19);
           cell.setCellValue(orderMail.getUnitname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(20);
           cell.setCellValue(orderMail.getHs());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(21);
           cell.setCellValue(orderMail.getTradeCountry());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(22);
           cell.setCellValue(orderMail.getTotalMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(23);
           cell.setCellValue(orderMail.getFightNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(24);
           cell.setCellValue(orderMail.getTxLogisticID());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(25);
           Double du = orderMail.getExs();
           if (du == null) {
             du = Double.valueOf(0.0D);
           }
           cell.setCellValue(du.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(26);
           
           Double dv = orderMail.getVas();
           if (dv == null) {
             dv = Double.valueOf(0.0D);
           }
           cell.setCellValue(dv.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(27);
           
           Double dc = orderMail.getCons();
           if (dc == null) {
             dc = Double.valueOf(0.0D);
           }
           cell.setCellValue(dc.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=cost.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 
   
   @RequestMapping({"/HZimport"})
   public void HZimport(HttpServletRequest request, HttpServletResponse response) throws IOException {
     HSSFWorkbook ob = new HSSFWorkbook();
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle1 = ob.createCellStyle();
     
     hSSFCellStyle1.setAlignment((short)2);
     HSSFFont font = ob.createFont();
     font.setColor((short)8);
     font.setFontHeightInPoints((short)10);
     font.setBoldweight((short)700);
     HSSFCellStyle style = ob.createCellStyle();
     
     style.setVerticalAlignment((short)1);
     style.setAlignment((short)2);
     
     style.setFont(font);
     
     sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
 
     
     HSSFCell cell = row.createCell(0);
     cell = sheet.getRow(0).createCell(0);
     cell = row.createCell(0);
     cell.setCellStyle(style);
     cell.setCellValue("货物明细汇总");
     
     row = sheet.createRow(1);
     cell = row.createCell(0);
     cell.setCellValue("序号");
     cell.setCellStyle(style);
     
     cell = row.createCell(1);
     cell.setCellValue("商品编号");
     cell.setCellStyle(style);
     
     cell = row.createCell(2);
     cell.setCellValue("品名");
     cell.setCellStyle(style);
     
     cell = row.createCell(3);
     cell.setCellValue("件数");
     cell.setCellStyle(style);
     
     cell = row.createCell(4);
     cell.setCellValue("毛重（KG）");
     cell.setCellStyle(style);
     
     cell = row.createCell(5);
     cell.setCellValue("总个数");
     cell.setCellStyle(style);
     
     cell = row.createCell(6);
     cell.setCellValue("单位");
     cell.setCellStyle(style);
     
     cell = row.createCell(7);
     cell.setCellValue("币种");
     cell.setCellStyle(style);
     
     cell = row.createCell(8);
     cell.setCellValue("货值");
     cell.setCellStyle(style);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String value = request.getParameter("hzValue");
     if (value != null) {
       value = value.trim();
     }
     
     List<OrderMailSkuAll> list = this.orderMailSkuService.findALLaboutSku_byIDS(value);
     int i = 1;
     int j = 1;
     for (OrderMailSkuAll orderMailSkuAll : list) {
       row = sheet.createRow(++i);
       cell = row.createCell(0);
       cell.setCellValue("货物明细汇总");
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(0);
       cell.setCellValue(j++);
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(1);
       cell.setCellValue(orderMailSkuAll.getItemsku());
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(2);
       cell.setCellValue(orderMailSkuAll.getItemName());
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(3);
       cell.setCellValue(0.0D);
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(4);
       cell.setCellValue(Tools.getDouble3(orderMailSkuAll.getAllWeight()));
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(5);
       cell.setCellValue(orderMailSkuAll.getAllNum());
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(6);
       cell.setCellValue(orderMailSkuAll.getUnitName());
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(7);
       cell.setCellValue("RMB");
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
       
       cell = row.createCell(8);
       cell.setCellValue(Tools.getDouble2(orderMailSkuAll.getAllPrice()));
       cell.setCellStyle((CellStyle)hSSFCellStyle1);
     } 
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=OrderMailSkuHZ.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 
 
   
   @RequestMapping({"/exportorderMailsss"})
   public void exportorderMailsss(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("快件公司编号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("身份证姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("身份证号码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件人省份");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人城市");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人地区");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("收件人详细地址");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("收件人邮编");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("收件人国家");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("收件人联系电话");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("物品重量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("物品名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("物品品名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("物品单价（RMB）");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("物品数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("物品总价");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("物品规格");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("物品计量单位名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("物品HS");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("起运国代码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("提运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(23);
     cell.setCellValue("航班号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("客户订单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(25);
     cell.setCellValue("消费税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(26);
     cell.setCellValue("增值税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(27);
     cell.setCellValue("综合税");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String ids1 = request.getParameter("costzhs");
     String idd1 = request.getParameter("costzhsss");
     String id1 = request.getParameter("costzhss");
     
     if (ids1.equals("cxqbdc")) {
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("id", id1);
       List<OrderMail> list = this.orderMailService.findeXport(pageMap);
       int i = 1;
       for (OrderMail orderMail : list) {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderMail.getCarrier());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderMail.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderMail.getReceiveMan());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderMail.getBuyerName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderMail.getBuyerIdNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(orderMail.getReceiveProvince());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(orderMail.getReceiveCity());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(orderMail.getReceiveCounty());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(orderMail.getReceiveManAddress());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(orderMail.getReceivecode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(orderMail.getReceiveCountr());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(orderMail.getReceiveManPhone());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(orderMail.getItemWeight().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(orderMail.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(orderMail.getClassname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(orderMail.getPrices().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(orderMail.getIt());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(orderMail.getAllp().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(orderMail.getStandard());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(orderMail.getUnitname());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(20);
         cell.setCellValue(orderMail.getHs());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(orderMail.getTradeCountry());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(22);
         cell.setCellValue(orderMail.getTotalMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(23);
         cell.setCellValue(orderMail.getFightNumber());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(24);
         cell.setCellValue(orderMail.getTxLogisticID());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(25);
         Double du = orderMail.getExs();
         if (du == null) {
           du = Double.valueOf(0.0D);
         }
         cell.setCellValue(du.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(26);
         
         Double dv = orderMail.getVas();
         if (dv == null) {
           dv = Double.valueOf(0.0D);
         }
         cell.setCellValue(dv.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(27);
         
         Double dc = orderMail.getCons();
         if (dc == null) {
           dc = Double.valueOf(0.0D);
         }
         cell.setCellValue(dc.doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       String[] arr = idd1.split(",");
       for (int i = 0; i < arr.length; i++) {
         List<OrderMail> u = this.orderMailService.findeXportId(Integer.valueOf(Integer.parseInt(arr[i])));
         for (int j = 0; j < u.size(); j++) {
           OrderMail orderMail = u.get(j);
           row = sheet.createRow(i + 1);
           cell = row.createCell(0);
           cell.setCellValue(orderMail.getCarrier());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(orderMail.getMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(orderMail.getReceiveMan());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(orderMail.getBuyerName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(orderMail.getBuyerIdNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(orderMail.getReceiveProvince());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(orderMail.getReceiveCity());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(orderMail.getReceiveCounty());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(orderMail.getReceiveManAddress());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(9);
           cell.setCellValue(orderMail.getReceivecode());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(10);
           cell.setCellValue(orderMail.getReceiveCountr());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(11);
           cell.setCellValue(orderMail.getReceiveManPhone());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(12);
           cell.setCellValue(orderMail.getItemWeight().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(13);
           cell.setCellValue(orderMail.getItemName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(14);
           cell.setCellValue(orderMail.getClassname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(15);
           cell.setCellValue(orderMail.getPrices().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(16);
           cell.setCellValue(orderMail.getIt());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(17);
           cell.setCellValue(orderMail.getAllp().doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(18);
           cell.setCellValue(orderMail.getStandard());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(19);
           cell.setCellValue(orderMail.getUnitname());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(20);
           cell.setCellValue(orderMail.getHs());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(21);
           cell.setCellValue(orderMail.getTradeCountry());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(22);
           cell.setCellValue(orderMail.getTotalMailNo());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(23);
           cell.setCellValue(orderMail.getFightNumber());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(24);
           cell.setCellValue(orderMail.getTxLogisticID());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(25);
           Double du = orderMail.getExs();
           if (du == null) {
             du = Double.valueOf(0.0D);
           }
           cell.setCellValue(du.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(26);
           
           Double dv = orderMail.getVas();
           if (dv == null) {
             dv = Double.valueOf(0.0D);
           }
           cell.setCellValue(dv.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(27);
           
           Double dc = orderMail.getCons();
           if (dc == null) {
             dc = Double.valueOf(0.0D);
           }
           cell.setCellValue(dc.doubleValue());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=cost.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 
   
   @RequestMapping({"/batchNumber"})
   public ModelAndView DetailedBatch(@RequestParam(value = "batchNumber", required = true) String batchNumber, OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("batchNumber", batchNumber);
     List<OrderMail> u = this.orderMailService.findBybatchNumber(pageMap);
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.addObject("u", u);
     modelAndView.setViewName("displayDetail");
     return modelAndView;
   }
 
 
   
   @RequestMapping({"/deriveddetail"})
   public void deriveddetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("客户订单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("运单号");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("商品");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("物品总价");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("物品数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("税费");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人地址");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     String ids1 = request.getParameter("batchexp");
     String ids2 = request.getParameter("batchexps");
     
     if (ids1.equals("cxqbdc")) {
       List<OrderMail> list = this.orderMailService.deriveddetail(ids2);
       int i = 1;
       for (OrderMail orderMail : list) {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderMail.getTxLogisticID());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderMail.getMailNo());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderMail.getItemnamexqs());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderMail.getAllPriceorders().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderMail.getItemCountorders().intValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(orderMail.getSis().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(orderMail.getReceiveMan());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(orderMail.getReceiveManAddress());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } 
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=orderbatch.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
 
   
   @RequestMapping({"/exportOrderMail"})
   public void exportOrderMail(HttpServletRequest request, HttpServletResponse response) throws Exception {
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("快件公司编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("快递单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("身份证姓名");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("身份证号码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件人省份");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人城市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人区县");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("收件人详细地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("收件人邮编");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("收件人国家");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("收件人联系电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("物品重量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("物品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("物品品名");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("物品单价（RMB）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("物品数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("物品总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("物品规格");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("物品计量单位名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("物品HS");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("起运国代码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("提运单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(23);
     cell.setCellValue("航班号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("客户订单号");
     cell.setCellStyle(cellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     int auditstatus = Integer.parseInt(request.getParameter("auditstatus"));
 
     
     userId = getUserId(userName, userId);
     String ids = request.getParameter("orderMailed");
     String value = request.getParameter("value");
     String name = request.getParameter("name");
     String time1 = request.getParameter("time1");
     String time2 = request.getParameter("time2");
 
     
     if (ids.equals("cxqbdc")) {
       
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("value", value);
       pageMap.put("name", name);
       pageMap.put("auditstatus", Integer.valueOf(auditstatus));
       pageMap.put("time1", time1);
       pageMap.put("time2", time2);
       if (auditstatus == 2) {
         int returncode = 2;
         pageMap.put("auditstatus", Integer.valueOf(9));
         pageMap.put("returncode", Integer.valueOf(returncode));
       } 
       if (auditstatus == 9) {
         int returncode = 9;
         pageMap.put("returncode", Integer.valueOf(returncode));
       } 
       List<OrderMailForExport> list = this.orderMailService.exportMoil(pageMap);
       int i = 1;
       for (OrderMailForExport orderMailForExport : list)
       {
 
         
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderMailForExport.getCarrier());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderMailForExport.getMailNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderMailForExport.getReceiveMan());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderMailForExport.getBuyerName());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderMailForExport.getBuyerIdNumber());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(orderMailForExport.getReceiveProvince());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(orderMailForExport.getReceiveCity());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(orderMailForExport.getReceiveCounty());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(orderMailForExport.getReceiveManAddress());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(orderMailForExport.getReceivecode());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(orderMailForExport.getReceiveCountr());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(orderMailForExport.getReceiveManPhone());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(orderMailForExport.getItemWeight2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(orderMailForExport.getItemName2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(orderMailForExport.getItemClass());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(orderMailForExport.getUnitPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(orderMailForExport.getItemCount2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(orderMailForExport.getAllPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(orderMailForExport.getStandard());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(orderMailForExport.getUnitname());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(20);
         cell.setCellValue(orderMailForExport.getItemsku().substring(2));
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(orderMailForExport.getTradeCountry());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(22);
         cell.setCellValue(orderMailForExport.getTotalMailNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(23);
         cell.setCellValue(orderMailForExport.getFightNumber());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(24);
         cell.setCellValue(orderMailForExport.getTxLogisticID());
         cell.setCellStyle(cellStyle);
       }
     
     }
     else {
       
       List<OrderMailForExport> list = this.orderMailService.exportMailByIds(ids);
       int i = 1;
       for (OrderMailForExport orderMailForExport : list) {
 
 
         
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(orderMailForExport.getCarrier());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(orderMailForExport.getMailNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(orderMailForExport.getReceiveMan());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(orderMailForExport.getBuyerName());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(orderMailForExport.getBuyerIdNumber());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(orderMailForExport.getReceiveProvince());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(orderMailForExport.getReceiveCity());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(orderMailForExport.getReceiveCounty());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(orderMailForExport.getReceiveManAddress());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(orderMailForExport.getReceivecode());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(orderMailForExport.getReceiveCountr());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(11);
         cell.setCellValue(orderMailForExport.getReceiveManPhone());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(12);
         cell.setCellValue(orderMailForExport.getItemWeight2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(13);
         cell.setCellValue(orderMailForExport.getItemName2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(14);
         cell.setCellValue(orderMailForExport.getItemClass());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(15);
         cell.setCellValue(orderMailForExport.getUnitPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(16);
         cell.setCellValue(orderMailForExport.getItemCount2());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(17);
         cell.setCellValue(orderMailForExport.getAllPrice());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(18);
         cell.setCellValue(orderMailForExport.getStandard());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(19);
         cell.setCellValue(orderMailForExport.getUnitname());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(20);
         cell.setCellValue(orderMailForExport.getItemsku().substring(2));
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(21);
         cell.setCellValue(orderMailForExport.getTradeCountry());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(22);
         cell.setCellValue(orderMailForExport.getTotalMailNo());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(23);
         cell.setCellValue(orderMailForExport.getFightNumber());
         cell.setCellStyle(cellStyle);
         
         cell = row.createCell(24);
         cell.setCellValue(orderMailForExport.getTxLogisticID());
         cell.setCellStyle(cellStyle);
       } 
     } 
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=orderMail.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("直邮导出花费时间：" + (System.currentTimeMillis() - l1));
   }
 
   
   @ResponseBody
   @RequestMapping({"/odmdateTime"})
   public void odmdateTime(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     Map<String, Object> map = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
     
     userId = getUserId(userName, userId);
     pageMap.put("time1", orderSearchDto.getTime1());
     pageMap.put("time2", orderSearchDto.getTime2());
     pageMap.put("paramName", orderSearchDto.getParamName());
     pageMap.put("paramValue", orderSearchDto.getParamValue());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("auditstatus", Integer.valueOf(9));
     
     pageMap.put("startPage", 
         Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     List<OrderMail> odm = this.orderMailService.odmdateTime(pageMap);
     int total = this.orderMailService.count(pageMap);
     map.put("rows", odm);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
 
   
   @RequestMapping({"/updateodm"})
   public void updateodm(@RequestParam(value = "id", required = true) int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String ret = this.orderMailSkuService.odmTax(id);
     response.getWriter().write(ret);
   }
 
 
   
   @RequestMapping({"/upodm"})
   public String upodm(OrderMailSku orderMailSku, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.orderMailSkuService.upodm(orderMailSku);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
 
   
   @RequestMapping({"/editIdN"})
   public String editIdN(OrderMail orderMail, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.orderMailService.editIdN(orderMail);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/matchMailNoSf"})
   public int matchMailNoSf(String ids, HttpServletRequest request, HttpServletResponse response) {
     return this.orderMailService.updateExpressParamsf(ids);
   }
   
   @RequestMapping({"/editMatterM"})
   @ResponseBody
   public int editMatterM(OrderMail orderMail, HttpServletResponse response, HttpServletRequest request) throws Exception {
     int id = Integer.parseInt(request.getSession().getAttribute("id_editMatter").toString());
     
     orderMail.setId(id);
     int resultTotal = 0;
     resultTotal = this.orderMailService.editMatterm(orderMail);
     return resultTotal;
   }
 
   
   @ResponseBody
   @RequestMapping({"/detail"})
   public OrderMail detail(int id) {
     OrderMail orderMail = this.orderMailService.findOneById(id);
     return orderMail;
   }
   
   @ResponseBody
   @RequestMapping({"/updateReturnCode"})
   public int updateReturnCode(HttpServletRequest request, HttpServletResponse response) {
     int id = Integer.parseInt(request.getParameter("id"));
     int returnCode = Integer.parseInt(request.getParameter("returnCode"));
     OrderMail om = new OrderMail();
     om.setId(id);
     om.setReturncode(Integer.valueOf(returnCode));
     return this.orderMailService.updateReturnCode(om);
   }
   @RequestMapping({"/updateAuditstatus0"})
   @ResponseBody
   public int updateAuditstatus0(int[] ids) throws Exception {
     try {
       this.orderMailService.updateAuditstatus0(ids);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
   
   @RequestMapping({"/deliveChange"})
   @ResponseBody
   public String deliveChange(Users u, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int[] ids = (int[])request.getSession().getAttribute("ids");
     List<OrderMail> order = new ArrayList<>();
     List<CompanyZyAndOrderMail> cz = new ArrayList<>();
     JSONObject result = new JSONObject();
     order = this.orderMailService.findByIds(ids);
     for (OrderMail or : order) {
       if (or.getMerchantNum() == null) {
         response.getWriter().write("推送错误!公司编号不存在");
         return null;
       } 
     } 
     List<OrderMail> o = new ArrayList<>();
     o = this.orderMailService.findtxLogisticID(ids);
     List<ChangeOdd> j = this.changodd.findByExpressNumberCount(o);
     if (j.size() > 0) {
       response.getWriter().write("推送错误!存在多条换单重复数据");
       return null;
     } 
     try {
       cz = this.comservice.findAllByCompanyCode(order);
     } catch (Exception e) {
       
       response.getWriter().write("公司编号为空");
       return null;
     } 
     int[] z = new int[cz.size()];
     int i = 0;
     int x = 0;
     List<GoodsChangeodd> list_goods = new ArrayList<>();
     List<ChangeOdd> list_changeOdd = new ArrayList<>();
     try {
       for (CompanyZyAndOrderMail companyZyAndOrderMail : cz) {
         ChangeOdd changeOdd = new ChangeOdd();
         changeOdd.setOrderNumber(companyZyAndOrderMail.getTxLogisticID());
         changeOdd.setExpressCode(companyZyAndOrderMail.getCarrier());
         changeOdd.setExpressNumber(companyZyAndOrderMail.getTxLogisticID());
         changeOdd.setBuyerName(companyZyAndOrderMail.getBuyerName());
         changeOdd.setBuyerProvince(companyZyAndOrderMail.getReceiveProvince());
         changeOdd.setBuyerCity(companyZyAndOrderMail.getReceiveCity());
         changeOdd.setBuyerArea(companyZyAndOrderMail.getReceiveCounty());
         changeOdd.setBuyerCountry(companyZyAndOrderMail.getReceiveCountr());
         changeOdd.setBuyerTel(companyZyAndOrderMail.getReceiveManPhone());
         changeOdd.setSender(companyZyAndOrderMail.getSendName());
         changeOdd.setSenderTel(companyZyAndOrderMail.getSendTel());
         changeOdd.setSenderAddress(companyZyAndOrderMail.getSendAddress());
         changeOdd.setBuyerAddress(companyZyAndOrderMail.getReceiveManAddress());
         changeOdd.setNewexpressNumber(companyZyAndOrderMail.getMailNo());
         changeOdd.setUserId(u.getId());
         changeOdd.setZyNumber(companyZyAndOrderMail.getBillProvideSiteCode());
         list_changeOdd.add(changeOdd);
       } 
       i = this.changodd.inserts(list_changeOdd);
       if (i > 0) {
         List<ChangeOdd> cod = this.changodd.findByExpressNumberForAlls(list_changeOdd);
         
         for (ChangeOdd changeOdd : cod) {
           z[x] = changeOdd.getId();
           x++;
         } 
         List<OrderMailAndChangeOdd> list = this.orderMailSkuService.findByTxLogisticID2(cz);
         if (list.size() == 0) {
           this.changodd.deleteByIds(z);
           response.getWriter().write("推送错误!未查询到详情表信息");
           return null;
         } 
         
         for (OrderMailAndChangeOdd orderMailSku : list) {
           GoodsChangeodd goodsChangeodd = new GoodsChangeodd();
           goodsChangeodd.setChangeoId(orderMailSku.getId());
           goodsChangeodd.setExpressNumber(orderMailSku.getTxLogisticID());
           goodsChangeodd.setOrderNumber(orderMailSku.getTxLogisticID());
           goodsChangeodd.setNewnum(orderMailSku.getItemCount());
           goodsChangeodd.setNweight(orderMailSku.getItemWeight());
           goodsChangeodd.setPnname(orderMailSku.getItemName());
           list_goods.add(goodsChangeodd);
         } 
 
 
         
         response.getWriter().write("推送成功!");
       } else {
         response.getWriter().write("推送错误!公司编号不存在");
       } 
     } catch (Exception e) {
       
       this.changodd.deleteByIds(z);
       response.getWriter().write("推送错误!");
     } 
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/payNumberTL"})
   public int payNumberTL(String ids, HttpServletRequest request, HttpServletResponse response) {
     try {
       return this.orderMailService.payNumberTL(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
 
 
   
   @RequestMapping({"/UpdatetotalMailNoUi"})
   public String UpdatetotalMailNoUi(Model model, HttpServletRequest request) {
     return "/directMailInsepection/operate/UpdatetotalMailNoUi";
   }
 
   
   @RequestMapping({"/findByToalMailNo"})
   @ResponseBody
   public String findByToalMailNo(OrderMail orderMail, HttpServletRequest request, HttpServletResponse response) throws Exception {
     try {
       int i = this.orderMailService.findByIdToalOrderMailNo(orderMail.getTotalMailNo());
       
       if (i > 0) {
         this.orderMailService.updateToal(orderMail);
         response.getWriter().write("修改成功!");
       } else {
         response.getWriter().write("提运单号不存在");
         return null;
       } 
     } catch (Exception e) {
       
       response.getWriter().write("修改失败！请检查数据是否输入正确！");
       return null;
     } 
     
     return null;
   }
   
   @ResponseBody
   @RequestMapping({"/pushBfb"})
   public String pushBfb(String id, String no) throws Exception {
     System.out.println(String.valueOf(id) + "," + no);
     String buffer = this.orderMailService.pushBfb(id, no);
     return buffer;
   }
   
   @RequestMapping({"/export_Gd"})
   public void export_Gd(OrderMail orderMail, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("序号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("订单编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("订单日期");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("进出口日期");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("订单运费");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("收件人所在国家");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("收件人所在省");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("收件人所在市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("收件人所在区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("收件人详细地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("收件人姓名");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("收件人电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("发货人");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("发货人所在国家");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("发货人所在省");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("发货人所在市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("发货人所在区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("发货人地址");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("发货人电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("订单人姓名");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("订单人证件类型");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("订单人证件号码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("订单人注册号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(23);
     cell.setCellValue("订单人电话");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("订单人所在国家");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(25);
     cell.setCellValue("订单人所在城市");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(26);
     cell.setCellValue("运输方式");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(27);
     cell.setCellValue("运输工具名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(28);
     cell.setCellValue("运输工具代码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(29);
     cell.setCellValue("航班航次编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(30);
     cell.setCellValue("航班号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(31);
     cell.setCellValue("启运国");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(32);
     cell.setCellValue("启运港");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(33);
     cell.setCellValue("集装箱号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(34);
     cell.setCellValue("集装箱尺寸");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(35);
     cell.setCellValue("集装箱类型");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(36);
     cell.setCellValue("是否转关");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(37);
     cell.setCellValue("支付企业代码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(38);
     cell.setCellValue("支付企业名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(39);
     cell.setCellValue("支付流水号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(40);
     cell.setCellValue("电子订单状态");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(41);
     cell.setCellValue("支付状态");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(42);
     cell.setCellValue("其他费用");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(43);
     cell.setCellValue("支付交易类型");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(44);
     cell.setCellValue("出仓进境日期");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(45);
     cell.setCellValue("货物存放地");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(46);
     cell.setCellValue("路由状态");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(47);
     cell.setCellValue("电子运单状态");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(48);
     cell.setCellValue("运单二维码编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(49);
     cell.setCellValue("备注");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(50);
     cell.setCellValue("物流订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(51);
     cell.setCellValue("运单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(52);
     cell.setCellValue("进/出境口岸");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(53);
     cell.setCellValue("快递公司");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(54);
     cell.setCellValue("商品货号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(55);
     cell.setCellValue("品牌");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(56);
     cell.setCellValue("商品信息");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(57);
     cell.setCellValue("商品海关备案编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(58);
     cell.setCellValue("商检备案号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(59);
     cell.setCellValue("规格型号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(60);
     cell.setCellValue("原产国");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(61);
     cell.setCellValue("包装种类");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(62);
     cell.setCellValue("计量单位");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(63);
     cell.setCellValue("申报数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(64);
     cell.setCellValue("净重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(65);
     cell.setCellValue("毛重");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(66);
     cell.setCellValue("件数");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(67);
     cell.setCellValue("商品单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(68);
     cell.setCellValue("商品总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(69);
     cell.setCellValue("商品批次号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(70);
     cell.setCellValue("抵付金额");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(71);
     cell.setCellValue("抵付说明");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(72);
     cell.setCellValue("ERP订单号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(73);
     cell.setCellValue("ERP单价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(74);
     cell.setCellValue("ERP总价");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(75);
     cell.setCellValue("ERP商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(76);
     cell.setCellValue("第一法定数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(77);
     cell.setCellValue("第二法定数量");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(78);
     cell.setCellValue("HS编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(79);
     cell.setCellValue("行邮税号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(80);
     cell.setCellValue("法定单位");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(81);
     cell.setCellValue("第二单位");
     cell.setCellStyle(cellStyle);
     String totalMailNo = request.getParameter("totalMailNo");
     orderMail.setTotalMailNo(totalMailNo);
     List<OrderMailForExport> list = this.orderMailService.exportGD(orderMail);
     int i = 1;
     int num = 1;
     for (OrderMailForExport orderMailForExport : list) {
 
 
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(num);
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue(orderMailForExport.getTxLogisticID());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(Tools.format("yyyy-MM-dd HH:mm:ss", orderMailForExport.getCreateTime()));
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(Tools.format("yyyy-MM-dd", orderMailForExport.getCreateTime()));
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(0.0D);
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(142.0D);
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(orderMailForExport.getReceiveProvince());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(orderMailForExport.getReceiveCity());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(orderMailForExport.getReceiveCounty());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(orderMailForExport.getReceiveManAddress());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(orderMailForExport.getReceiveMan());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(orderMailForExport.getReceiveManPhone());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue("copons");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue("香港");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue("香港");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue("元朗");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue("福喜");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue("跨境云仓");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue("23348818");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(19);
       cell.setCellValue(orderMailForExport.getBuyerName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(20);
       cell.setCellValue("01");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(21);
       cell.setCellValue(orderMailForExport.getBuyerIdNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(22);
       cell.setCellValue(orderMailForExport.getBuyerName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(23);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(24);
       cell.setCellValue("142");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(25);
       cell.setCellValue(orderMailForExport.getReceiveCity());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(26);
       cell.setCellValue("公路运输");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(27);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(28);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(29);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(30);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(31);
       cell.setCellValue(110.0D);
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(32);
       cell.setCellValue(110.0D);
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(33);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(34);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(35);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(36);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(37);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(38);
       cell.setCellValue("邦付宝支付科技有限公司");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(39);
       cell.setCellValue(orderMailForExport.getPayNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(40);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(41);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(42);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(43);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(44);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(45);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(46);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(47);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(48);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(49);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(50);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(51);
       cell.setCellValue(orderMailForExport.getMailNo());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(52);
       cell.setCellValue("5157");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(53);
       cell.setCellValue(orderMailForExport.getCarrier());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(54);
       cell.setCellValue(orderMailForExport.getItemsku());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(55);
       cell.setCellValue(orderMailForExport.getItemClass());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(56);
       cell.setCellValue(orderMailForExport.getItemName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(57);
       cell.setCellValue(orderMailForExport.getHs());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(58);
       cell.setCellValue(orderMailForExport.getProductRecordNo());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(59);
       cell.setCellValue(orderMailForExport.getStandard());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(60);
       cell.setCellValue(orderMailForExport.getCountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(61);
       cell.setCellValue("2");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(62);
       cell.setCellValue(orderMailForExport.getUnitname());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(63);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(64);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(65);
       cell.setCellValue(orderMailForExport.getItemWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(66);
       cell.setCellValue(1.0D);
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(67);
       cell.setCellValue(orderMailForExport.getUnitPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(68);
       cell.setCellValue(orderMailForExport.getAllPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(69);
       cell.setCellValue(orderMailForExport.getBatchNumber());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(70);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       cell = row.createCell(71);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       cell = row.createCell(72);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       cell = row.createCell(73);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       cell = row.createCell(74);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       cell = row.createCell(75);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(76);
       cell.setCellValue(orderMailForExport.getFirstCount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(77);
       cell.setCellValue(orderMailForExport.getSecondCount());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(78);
       cell.setCellValue(orderMailForExport.getHs());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(79);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(80);
       cell.setCellValue(orderMailForExport.getOneUnitDesc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(81);
       cell.setCellValue(orderMailForExport.getTwoUnitDesc());
       cell.setCellStyle(cellStyle);
     } 
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=orderMail.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("直邮导出花费时间：" + (System.currentTimeMillis() - l1));
   }
   
   @RequestMapping({"/goBackOrder"})
   @ResponseBody
   public int goBackOrder(String[] ids) {
     return this.orderMailService.goBackOrder(ids);
   }
 }


