 package com.what21.action;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Log_inventory;
 import com.what21.model.Log_inventory_action;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.Tinventory;
 import com.what21.model.TinventorySku;
 import com.what21.service.Log_inventoryService;
 import com.what21.service.Log_inventory_actionService;
 import com.what21.service.TinventoryService;
 import com.what21.service.TinventorySkuService;
 import com.what21.util.ResponseUtil;
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
 import org.apache.commons.lang.StringUtils;
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
  import org.apache.commons.codec.binary.Base64;
 @Controller
 @RequestMapping({"/tinventory"})
 public class TinventoryAction
   extends BaseAction
 {
   @Autowired
   public TinventoryService tinventorybsService;
   @Autowired
   public Log_inventoryService log_inventoryService;
   @Autowired
   public Log_inventory_actionService log_inventory_actionService;
   @Autowired
   private TinventoryService tinventoryService;
   @Autowired
   private TinventorySkuService tinventorySkuService;
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time1", orderSearchDto.getTime1());
     pageMap.put("time2", orderSearchDto.getTime2());
     pageMap.put("text1", orderSearchDto.getText1());
     pageMap.put("text2", orderSearchDto.getText2());
     pageMap.put("findsku", orderSearchDto.getFindsku());
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("userId", Integer.valueOf(userId));
     List<Tinventory> u = this.tinventorybsService.findAll(pageMap);
     int total = this.tinventorybsService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/dateTime"})
   public void dateTime(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time1", orderSearchDto.getTime1());
     pageMap.put("time2", orderSearchDto.getTime2());
     pageMap.put("text1", orderSearchDto.getText1());
     pageMap.put("text2", orderSearchDto.getText2());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("findsku", orderSearchDto.getFindsku());
     pageMap.put("overdue", orderSearchDto.getOverdue());
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     List<Tinventory> u = this.tinventorybsService.dateTime(pageMap);
     int total = this.tinventorybsService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/exportTinventory"})
   public void exportTinventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("SKU");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("商品名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("总库存数");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("锁定库存");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("占用库存");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("可用库存");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("预警库存");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("入库批次");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("入库时间");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("商家编码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("过期时间");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     String ids = request.getParameter("exporta");
     String text1 = request.getParameter("texta");
     String text2 = request.getParameter("textb");
     String time1 = request.getParameter("timea");
     String time2 = request.getParameter("timeb");
     String findsku = request.getParameter("findskua");
     String overdue = request.getParameter("overduea");
     
     if (ids.equals("cxqbdc")) {
       pageMap.put("userId", Integer.valueOf(userId));
       pageMap.put("text1", text1);
       pageMap.put("text2", text2);
       pageMap.put("time1", time1);
       pageMap.put("time2", time2);
       pageMap.put("findsku", findsku);
       pageMap.put("overdue", overdue);
       pageMap.put("startPage", null);
       pageMap.put("endPage", null);
       List<Tinventory> list = this.tinventorybsService.findeXport(pageMap);
       int i = 1;
       for (Tinventory tinventory : list) {
         row = sheet.createRow(i++);
         cell = row.createCell(0);
         cell.setCellValue(tinventory.getItemsku());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(tinventory.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(tinventory.getTotality());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(tinventory.getUsedInventory());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(tinventory.getPreUsedInventory());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(tinventory.getSurplusInventory());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(tinventory.getWarningInventory());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(tinventory.getPc());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(tinventory.getDate());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(tinventory.getMerchant());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
 
         
         cell = row.createCell(10);
         cell.setCellValue(tinventory.getOverduedate());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       
       String[] arr = ids.split(",");
       for (int i = 0; i < arr.length; i++) {
         List<Tinventory> u = this.tinventorybsService.findByIdTinventory(Integer.valueOf(Integer.parseInt(arr[i])));
         for (int j = 0; j < u.size(); j++) {
           Tinventory tinventory = u.get(j);
           row = sheet.createRow(i + 1);
           cell = row.createCell(0);
           cell.setCellValue(tinventory.getItemsku());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(1);
           cell.setCellValue(tinventory.getItemName());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(2);
           cell.setCellValue(tinventory.getTotality());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(3);
           cell.setCellValue(tinventory.getUsedInventory());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(4);
           cell.setCellValue(tinventory.getPreUsedInventory());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(5);
           cell.setCellValue(tinventory.getSurplusInventory());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(6);
           cell.setCellValue(tinventory.getWarningInventory());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(7);
           cell.setCellValue(tinventory.getPc());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(8);
           cell.setCellValue(tinventory.getDate());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
           
           cell = row.createCell(9);
           cell.setCellValue(tinventory.getMerchant());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
 
           
           cell = row.createCell(10);
           cell.setCellValue(tinventory.getOverduedate());
           cell.setCellStyle((CellStyle)hSSFCellStyle);
         } 
       } 
     } 
     
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-disposition", "attachment;filename=tinventory.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
   
   @RequestMapping({"/TinvenDetailed"})
   public void TinvenDetailed(@RequestParam(value = "itemsku", required = true) String itemsku, @RequestParam(value = "Stocklot", required = true) String Stocklot, OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("itemsku", itemsku);
     pageMap.put("Stocklot", Stocklot);
     List<Log_inventory> u = this.log_inventoryService.findtoTinven(pageMap);
 
 
     
     int total = this.log_inventoryService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/updateTinventory"})
   public int updateTinventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int result = 0;
     String id = request.getParameter("id");
     String warningInventory = request.getParameter("warningInventory");
     String[] arr = id.split(",");
     Tinventory u = new Tinventory();
     for (int i = 0; i < arr.length; i++) {
       u.setId(Integer.parseInt(arr[i]));
       u.setWarningInventory(Integer.parseInt(warningInventory));
       result = this.tinventorybsService.updateTinventory(u);
     } 
     return result;
   }
   
   @RequestMapping({"/lock"})
   public void lock(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String itemsku = request.getParameter("id");
     String nm = request.getParameter("nm");
     String ny = request.getParameter("ny");
     String pc = request.getParameter("pc");
     String merchant = request.getParameter("merchant");
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     
     Tinventory t = new Tinventory();
     t.setItemsku(itemsku);
     t.setPc(pc);
     t.setMerchant(merchant);
     
     Tinventory t1 = this.tinventorybsService.findLR(t);
     
     if (Integer.parseInt(nm) > t1.getSurplusInventory()) {
       String str = JSONObject.toJSONString("锁定失败，锁定数量超过可用数量");
       response.getWriter().write(str);
     } else {
       Tinventory t2 = new Tinventory();
       t2.setUsedInventory(t1.getUsedInventory() + Integer.parseInt(nm));
       t2.setSurplusInventory(t1.getSurplusInventory() - Integer.parseInt(nm));
       t2.setItemsku(itemsku);
       t2.setPc(pc);
       t2.setMerchant(merchant);
       int ret = this.tinventorybsService.updateLR(t2);
       
       Log_inventory_action ll = new Log_inventory_action();
       ll.setItemsku(itemsku);
       ll.setNumber(nm);
       ll.setAction("锁定");
       SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       ll.setCreateTime(sim.format(new Date()));
       ll.setUserId(userId);
       ll.setReason(ny);
       this.log_inventory_actionService.insert(ll);
       
       String str = JSONObject.toJSONString(null);
       response.getWriter().write(str);
     } 
   }
   
   @RequestMapping({"/release"})
   public void release(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String itemsku = request.getParameter("id");
     String nm = request.getParameter("nm");
     String pc = request.getParameter("pc");
     String merchant = request.getParameter("merchant");
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     
     Tinventory t = new Tinventory();
     t.setItemsku(itemsku);
     t.setPc(pc);
     t.setMerchant(merchant);
     
     Tinventory t1 = this.tinventorybsService.findLR(t);
     
     if (Integer.parseInt(nm) > t1.getUsedInventory()) {
       String str = JSONObject.toJSONString("释放失败，释放数量超过锁定数量");
       response.getWriter().write(str);
     } else {
       Tinventory t2 = new Tinventory();
       t2.setUsedInventory(t1.getUsedInventory() - Integer.parseInt(nm));
       t2.setSurplusInventory(t1.getSurplusInventory() + Integer.parseInt(nm));
       t2.setItemsku(itemsku);
       t2.setPc(pc);
       t2.setMerchant(merchant);
       int ret = this.tinventorybsService.updateLR(t2);
       
       Log_inventory_action ll = new Log_inventory_action();
       ll.setItemsku(itemsku);
       ll.setNumber(nm);
       ll.setAction("释放");
       SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       ll.setCreateTime(sim.format(new Date()));
       ll.setUserId(userId);
       this.log_inventory_actionService.insert(ll);
       
       String str = JSONObject.toJSONString(null);
       response.getWriter().write(str);
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/addTinventory"})
   public String addTinventory(Tinventory tinventory, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.tinventorybsService.addTinventory(tinventory);
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
   @RequestMapping({"/makeOver"})
   public String makeOver(Tinventory tinventory, String merchantx, String updateM, HttpServletResponse response) throws Exception {
     int resulT = 0;
     
     Tinventory t1 = this.tinventorybsService.findByIdTinventory(Integer.valueOf(tinventory.getId())).get(0);
     String sku = t1.getItemsku();
     String pc = t1.getPc();
     
     Map<String, Object> map = new HashMap<>();
     map.put("itemsku", sku);
     map.put("merchant", tinventory.getUserId());
     map.put("pc", pc);
     
     Tinventory t2 = this.tinventorybsService.findSkuAndCode(map);
     Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String format = formatter.format(date);
     if (t2 == null) {
       t2 = new Tinventory();
       t2.setItemsku(sku);
       t2.setItemName(t1.getItemName());
       t2.setSurplusInventory(Integer.parseInt(updateM)); t2.setMerchant(tinventory.getUserId());
       t2.setTotality(Integer.parseInt(updateM));
       t2.setUserId(merchantx);
       t2.setDate(format);
       t2.setPc(t1.getPc());
       
       t1.setSurplusInventory(t1.getSurplusInventory() - Integer.parseInt(updateM));
       t1.setTotality(t1.getTotality() - Integer.parseInt(updateM));
 
       
       int i = this.tinventorybsService.addTinventory(t2);
       int j = this.tinventorybsService.upSurplus(t1);
       resulT = i + j;
     } else {
       t2.setSurplusInventory(t2.getSurplusInventory() + Integer.parseInt(updateM));
       t2.setTotality(t2.getTotality() + Integer.parseInt(updateM));
       t2.setUserId(t1.getUserId());
       
       t1.setSurplusInventory(t1.getSurplusInventory() - Integer.parseInt(updateM));
       t1.setTotality(t1.getTotality() - Integer.parseInt(updateM));
       
       int i = this.tinventorybsService.upSurplus(t2);
       int j = this.tinventorybsService.upSurplus(t1);
       resulT = i + j;
     } 
     if (resulT == 2) {
       TinventorySku tSku = new TinventorySku();
       tSku.setDate(format); tSku.setItemName(t1.getItemName()); tSku.setItemsku(sku);
       tSku.setMerchant(t1.getMerchant()); tSku.setMerchantx(tinventory.getUserId());
       tSku.setPc(t1.getPc()); tSku.setUpdateInventory(updateM);
       this.tinventorySkuService.addTinSku(tSku);
     } 
     JSONObject result = new JSONObject();
     if (resulT == 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     
     return null;
   }
   @RequestMapping({"/findTinSku"})
   public void findTinSku(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
 
     
     String merchant = request.getParameter("merchant");
     String sku = request.getParameter("sku");
     String pc = request.getParameter("pc");
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     
     pageMap.put("merchant", merchant);
     pageMap.put("sku", sku);
     pageMap.put("pc", pc);
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<TinventorySku> u = this.tinventorySkuService.findtinSku(pageMap);
     int total = this.tinventorySkuService.countSku(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/impKuChuen"})
   public void impKuChuen(String jsonStr, HttpServletRequest request, HttpServletResponse response) throws Exception {
     System.out.println("---------------------------->接收到来自wms的访问");
     
     String ids = "";
     byte[] b = Base64.decodeBase64(jsonStr);
     String param = new String(b, "utf-8");
     List<Tinventory> list = new ArrayList<>();
     List<Tinventory> upList = new ArrayList<>();
     List<Log_inventory> log = new ArrayList<>();
     if (StringUtils.isNotEmpty(param)) {
       JSONObject jSONObject = JSONObject.parseObject(param);
       JSONArray orderJSONArray = jSONObject.getJSONArray("orderList");
       for (int i = 0; i < orderJSONArray.size(); i++) {
         JSONObject orderJSONObject = orderJSONArray.getJSONObject(i);
         String companyCode = orderJSONObject.getString("companyCode");
         
         String id = orderJSONObject.getString("id");
         String storage = orderJSONObject.getString("storage");
         String itemSku = orderJSONObject.getString("itemSku");
         String SUM = orderJSONObject.getString("sum");
         String InventoryFrozen = orderJSONObject.getString("InventoryFrozen");
         String inventory = orderJSONObject.getString("inventory");
         String itemName = orderJSONObject.getString("itemName");
         String InventoryOccupy = orderJSONObject.getString("InventoryOccupy");
         String omstype = orderJSONObject.getString("omwtype");
         
         if (Integer.parseInt(omstype) == 0) {
           Tinventory tin = new Tinventory();
           tin.setItemsku(itemSku);
           tin.setItemName(itemName);
           tin.setTotality(Integer.parseInt(SUM));
           tin.setSurplusInventory(Integer.parseInt(inventory));
           tin.setUsedInventory(Integer.parseInt(InventoryFrozen));
           tin.setPreUsedInventory(Integer.parseInt(InventoryOccupy));
           
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String time = df.format(new Date());
           SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
           String pc = format.format(new Date());
           tin.setPc(pc);
           tin.setDate(time);
           tin.setMerchant(companyCode);
           tin.setCreateDate(time);
           tin.setStorage(storage);
           
           Log_inventory login = new Log_inventory();
           login.setCreateDate(time);
           
           login.setItemCount(Integer.valueOf(Integer.parseInt(SUM)));
           login.setItemName(itemName);
           login.setItemsku(itemSku);
           
           login.setType("导入");
           login.setRemark("导入： 商家编码为：" + companyCode + "商品编码为：" + itemSku + "数量为" + SUM);
           login.setStocklot(pc);
           login.setStorage(storage);
           
           list.add(tin);
           ids = String.valueOf(ids) + id + ",";
           this.log_inventoryService.insert(login);
         } else if (Integer.parseInt(omstype) == 2) {
           
           Tinventory tin = new Tinventory();
           tin.setItemsku(itemSku);
           tin.setItemName(itemName);
           tin.setTotality(Integer.parseInt(SUM));
           tin.setSurplusInventory(Integer.parseInt(inventory));
           tin.setUsedInventory(Integer.parseInt(InventoryFrozen));
           tin.setPreUsedInventory(Integer.parseInt(InventoryOccupy));
           
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String time = df.format(new Date());
           SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
           String pc = format.format(new Date());
           tin.setPc(pc);
           tin.setDate(time);
           tin.setMerchant(companyCode);
           tin.setCreateDate(time);
           tin.setStorage(storage);
           
           Log_inventory login = new Log_inventory();
           login.setCreateDate(time);
           
           login.setItemCount(Integer.valueOf(Integer.parseInt(SUM)));
           login.setItemName(itemName);
           login.setItemsku(itemSku);
           
           login.setType("导入");
           login.setRemark("导入： 商家编码为：" + companyCode + "商品编码为：" + itemSku + "数量为" + SUM);
           login.setStocklot(pc);
           login.setStorage(storage);
 
           
           int findStorage = this.tinventoryService.findStorage(storage);
           if (findStorage != 1) {
             list.add(tin);
           } else {
             upList.add(tin);
           } 
           ids = String.valueOf(ids) + id + ",";
           this.log_inventoryService.insert(login);
         } 
       } 
     } 
     if (list.size() != 0) {
       this.tinventoryService.addBig(list);
     }
     if (upList.size() != 0) {
       this.tinventoryService.updateBig(upList);
     }
     
     response.getWriter().write(ids);
   }
 }


