 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.ItemForCk;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.ItemForCkService;
 import com.what21.util.GeneralResult;
 import com.what21.util.ResponseUtil;
 import java.io.File;
 import java.io.OutputStream;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
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
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 
 @Controller
 @RequestMapping({"/itemForCk"})
 public class ItemForCkAction
   extends BaseAction
 {
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("textForZy", orderSearchDto.getTextForZy());
     pageMap.put("textForZy1", orderSearchDto.getTextForZy1());
 
     
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<ItemForCk> u = this.itemForCkService.findAll(pageMap);
     int total = this.itemForCkService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   } @Autowired
   public ItemForCkService itemForCkService;
   @ResponseBody
   @RequestMapping({"/dateTime"})
   public void dateTime(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     Map<String, Object> map = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("textForZy", orderSearchDto.getTextForZy());
     pageMap.put("textForZy1", orderSearchDto.getTextForZy1());
 
     
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     List<ItemForCk> itemList = this.itemForCkService.dateTime(pageMap);
     int total = this.itemForCkService.count(pageMap);
     map.put("rows", itemList);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/deleteItem"})
   public int deleteItem(@RequestParam(value = "id", required = true) String id) throws Exception {
     String[] ids = id.split(",");
     int result = 0;
     for (int i = 0; i < ids.length; i++) {
       result = this.itemForCkService.deleteItem(ids[i]);
     }
     return result;
   }
   
   @RequestMapping({"/updateItemForCk"})
   public String updateCost(ItemForCk itemForCk, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.itemForCkService.updateItemForCk(itemForCk);
     JSONObject result = new JSONObject();
     if (resultTotal > 0) {
       result.put("success", Boolean.valueOf(true));
     } else {
       result.put("success", Boolean.valueOf(false));
     } 
     ResponseUtil.write(response, result);
     return null;
   }
   
   @RequestMapping({"/addItemForCk"})
   public String addCost(ItemForCk itemForCk, HttpServletResponse response) throws Exception {
     int resultTotal = 0;
     resultTotal = this.itemForCkService.insert(itemForCk);
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
   @RequestMapping({"/addItem"})
   public int addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String userId = request.getParameter("userId");
     String itemSpec = request.getParameter("itemSpec");
     String unitWeight = request.getParameter("unitWeight");
     String unitDesc = request.getParameter("unitDesc");
     String createDateTime = request.getParameter("createDateTime");
     String country = request.getParameter("country");
     String costPrice = request.getParameter("costPrice");
     String itemSKU = request.getParameter("itemSKU");
     String itemName = request.getParameter("itemName");
     String companyCode = request.getParameter("companyCode");
     String vendorItemCode = request.getParameter("vendorItemCode");
     String remark = request.getParameter("remark");
     String xz = request.getParameter("xz");
     String xh = request.getParameter("xh");
     String listPrice = request.getParameter("listPrice");
     String hscode = request.getParameter("hscode");
     String oneUnitDesc = request.getParameter("oneUnitDesc");
     String twoUnitDesc = request.getParameter("twoUnitDesc");
     String countryOfOrigin = request.getParameter("countryOfOrigin");
     String internalNumber = request.getParameter("internalNumber");
     String taxRate = request.getParameter("taxRate");
     
     ItemForCk i = new ItemForCk();
     i.setUserId(userId);
     i.setItemSpec(itemSpec);
     i.setUnitWeight(Double.parseDouble(unitWeight));
     i.setUnitDesc(unitDesc);
     i.setCreateDateTime(createDateTime);
     i.setCountry(country);
     i.setCostPrice(Double.parseDouble(costPrice));
     i.setItemSKU(itemSKU);
     i.setItemName(itemName);
     i.setCompanyCode(companyCode);
     i.setVendorItemCode(vendorItemCode);
     i.setRemark(remark);
     i.setXz(xz);
     i.setXh(xh);
     i.setListPrice(Double.parseDouble(listPrice));
     i.setHscode(hscode);
     i.setOneUnitDesc(oneUnitDesc);
     i.setTwoUnitDesc(twoUnitDesc);
     i.setCountryOfOrigin(countryOfOrigin);
     i.setInternalNumber(Integer.parseInt(internalNumber));
     i.setTaxRate(Double.parseDouble(taxRate));
     int result = this.itemForCkService.insert(i);
     return result;
   }
   
   @RequestMapping({"/exportForCk"})
   public void exportForCk(HttpServletRequest request, HttpServletResponse response) throws Exception {
     HSSFWorkbook ob = new HSSFWorkbook();
 
     
     HSSFSheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     HSSFRow row = sheet.createRow(0);
     HSSFCellStyle hSSFCellStyle = ob.createCellStyle();
 
     
     hSSFCellStyle.setAlignment((short)2);
 
     
     HSSFCell cell = row.createCell(0);
     
     cell.setCellValue("商品类别");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("商品编码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("商品名称");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("单位");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("商家编码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("消费税率");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("增值税率");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("海关备案编码");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("第一计量单位");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("第二计量单位");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("第一数量");
     cell.setCellStyle((CellStyle)hSSFCellStyle);
     
     Map<String, Object> pageMap = new HashMap<>();
 
     
     String ids = request.getParameter("ids");
     String name = request.getParameter("name");
     String code = request.getParameter("code");
     
     if (ids.equals("cxqbdc")) {
       pageMap.put("itemName", name);
       pageMap.put("itemSKU", code);
       List<ItemForCk> list = this.itemForCkService.findByToBG(pageMap);
       int l = 0;
       for (ItemForCk u : list) {
         l++;
         row = sheet.createRow(l);
         cell = row.createCell(0);
         cell.setCellValue(u.getGenre());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(1);
         cell.setCellValue(u.getItemSKU());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(2);
         cell.setCellValue(u.getItemName());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(3);
         cell.setCellValue(u.getUnitDesc());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(4);
         cell.setCellValue(u.getCompanyCode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(5);
         cell.setCellValue(u.getExcise().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(6);
         cell.setCellValue(u.getVat().doubleValue());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(7);
         cell.setCellValue(u.getHscode());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(8);
         cell.setCellValue(u.getOneUnitDesc());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(9);
         cell.setCellValue(u.getTwoUnitDesc());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
         
         cell = row.createCell(10);
         cell.setCellValue(u.getFirstCount());
         cell.setCellStyle((CellStyle)hSSFCellStyle);
       } 
     } else {
       
       String[] arr = ids.split(",");
       int a = 0;
       for (int i = 0; i < arr.length; i++) {
         
         List<ItemForCk> it = this.itemForCkService.findByIdToBG(Integer.parseInt(arr[i]));
         int l = a;
         for (int j = 0; j < it.size(); j++) {
           for (ItemForCk u : it) {
             a = 1 + j + l;
             row = sheet.createRow(a);
             
             cell = row.createCell(0);
             cell.setCellValue(u.getGenre());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(1);
             cell.setCellValue(u.getItemSKU());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(2);
             cell.setCellValue(u.getItemName());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(3);
             cell.setCellValue(u.getUnitDesc());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(4);
             cell.setCellValue(u.getCompanyCode());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(5);
             cell.setCellValue(u.getExcise().doubleValue());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(6);
             cell.setCellValue(u.getVat().doubleValue());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(7);
             cell.setCellValue(u.getHscode());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(8);
             cell.setCellValue(u.getOneUnitDesc());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(9);
             cell.setCellValue(u.getTwoUnitDesc());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
             
             cell = row.createCell(10);
             cell.setCellValue(u.getFirstCount());
             cell.setCellStyle((CellStyle)hSSFCellStyle);
           } 
         } 
       } 
     } 
     
     response.reset();
     response.setContentType("application/vnd.ms-excel;charset=UTF-8");
     response.setHeader("Content-disposition", "attachment;filename=itemForCk.xls");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
   }
   
   @RequestMapping({"/importItemForCk"})
   public ModelAndView importItemForCk(MultipartFile excelFile) {
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
       result = this.itemForCkService.importItem(String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
 }


