 package com.what21.action;
 
 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import com.what21.dao.ItemDao;
 import com.what21.dao.SzqgAgentDao;
 import com.what21.dao.SzqgAgreementDao;
 import com.what21.model.Item;
 import com.what21.model.OrderSearchDto;
 import com.what21.model.SzqgAgent;
 import com.what21.model.SzqgAgreement;
 import com.what21.service.ItemService;
 import com.what21.service.SzqgAgentService;
 import com.what21.service.WebZuService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import com.what21.util.timenew;
 import com.zt.ewtp.hcszqgpost;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
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
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.xssf.streaming.SXSSFWorkbook;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.codec.binary.Base64;
 @Controller
 @RequestMapping({"/szqgament"})
 public class SzqgAction
   extends BaseAction
 {
   @Autowired
   public ItemService itemService;
   @Autowired
   public WebZuService webZuService;
   @Autowired
   public ItemDao itemdao;
   @Autowired
   public SzqgAgentDao szqgagentdao;
   @Autowired
   public SzqgAgreementDao szqgagreementdao;
   @Autowired
   public SzqgAgentService szqgAgentService;
   
   @RequestMapping({"/editUi"})
   public String editUi(Model model, String id) {
     Item item = this.itemService.findById(id);
     model.addAttribute("item", item);
     return "/item/operate/editUi";
   }
   @RequestMapping({"/addUi"})
   public String addUi() {
     return "/item/operate/editUi";
   }
   
   @ResponseBody
   @RequestMapping({"/edit"})
   public int edit(Item item) {
     int i = 0;
     if (item.getTwoUnitDesc() == null) {
       item.setTwoUnitDesc("");
     }
     if (item.getId() == null) {
       item.setCreateDateTime(Tools.format("yyyy-MM-dd hh:mm:ss", new Date()));
       i = this.itemService.save(item);
     } else {
       i = this.itemService.updateById(item);
     } 
     return i;
   }
   
   @ResponseBody
   @RequestMapping({"/save"})
   public int save(Item item) {
     item.setCreateDateTime(Tools.format("yyyy-MM-dd hh:mm:ss", new Date()));
     int i = this.itemService.save(item);
     return i;
   }
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     System.out.println("step 1");
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
 
 
     
     pageMap.put("time3", orderSearchDto.getTime3());
     pageMap.put("time4", orderSearchDto.getTime4());
     pageMap.put("text3", orderSearchDto.getText3());
     pageMap.put("text4", orderSearchDto.getText4());
     pageMap.put("text5", orderSearchDto.getText5());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
 
     
     List<SzqgAgent> u = this.szqgAgentService.findAll(pageMap);
     int total = this.szqgAgentService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     System.out.println("step 2" + u + "====" + total);
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/dateTime"})
   public void dateTime(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     Map<String, Object> pageMap = new HashMap<>();
     Map<String, Object> map = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time3", orderSearchDto.getTime3());
     pageMap.put("time4", orderSearchDto.getTime4());
     pageMap.put("text3", orderSearchDto.getText3());
     pageMap.put("text4", orderSearchDto.getText4());
     pageMap.put("text5", orderSearchDto.getText5());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("startPage", Integer.valueOf((orderSearchDto.getPage().intValue() - 1) * orderSearchDto.getRows().intValue()));
     pageMap.put("endPage", orderSearchDto.getRows());
     List<Item> itemList = this.itemService.dateTime(pageMap);
     int total = this.itemService.count(pageMap);
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
       result = this.itemService.deleteItem(ids[i]);
     }
     return result;
   }
   
   @ResponseBody
   @RequestMapping({"/deleteItem1"})
   public int deleteItem1(String[] ids) {
     System.out.print("11111111111"); byte b; int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
       System.out.print("id==" + id);
       this.itemService.deleteItem(id); b++; }
     
     return 1;
   }
   
   @ResponseBody
   @RequestMapping({"/addItem"})
   public int addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String userId = request.getParameter("userId");
     String itemSpec = request.getParameter("itemSpec");
     String unitWeight = request.getParameter("unitWeight");
     String unitDesc = request.getParameter("unitDesc");
     String country = request.getParameter("country");
     String itemSKU = request.getParameter("itemSKU");
     String itemName = request.getParameter("itemName");
     String companyCode = request.getParameter("companyCode");
     String xz = request.getParameter("xz");
     String xh = request.getParameter("xh");
     String hscode = request.getParameter("hscode");
     String oneUnitDesc = request.getParameter("oneUnitDesc");
     String twoUnitDesc = request.getParameter("twoUnitDesc");
     String taxRate = request.getParameter("taxRate");
     String productRecordNo = request.getParameter("productRecordNo");
     String code = request.getParameter("itemcode");
     
     Item i = new Item();
     i.setUserId(userId);
     i.setItemSpec(itemSpec);
     try {
       if (StringUtils.isNotEmpty(unitWeight)) {
         i.setUnitWeight(Double.parseDouble(unitWeight));
       }
     } catch (Exception e) {
       e.printStackTrace();
       return -1;
     } 
     
     i.setUnitDesc(unitDesc);
     i.setItemcode(code);
     Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String format = formatter.format(date);
     i.setCreateDateTime(format);
     i.setProductRecordNo(productRecordNo);
     i.setCountry(country);
     i.setItemSKU(itemSKU);
     i.setItemName(itemName);
     i.setCompanyCode(companyCode);
     i.setXz(xz);
     i.setXh(xh);
     i.setHscode(hscode);
     i.setOneUnitDesc(oneUnitDesc);
     i.setTwoUnitDesc(twoUnitDesc);
     i.setTaxRate(Double.parseDouble(taxRate));
     i.setWmstype("0");
     
     int result = this.itemService.insert(i);
     return result;
   }
 
   
   @ResponseBody
   @RequestMapping({"updateItem"})
   public int updateItem(HttpServletRequest request, HttpServletResponse response) {
     String userId = request.getParameter("userId");
     String str = request.getParameter("id");
     int id = Integer.parseInt(str);
     String itemSpec = request.getParameter("itemSpec");
     String unitWeight = request.getParameter("unitWeight");
     String unitDesc = request.getParameter("unitDesc");
     
     String country = request.getParameter("country");
     String itemSKU = request.getParameter("itemSKU");
     String itemName = request.getParameter("itemName");
     String companyCode = request.getParameter("companyCode");
     String xz = request.getParameter("xz");
     String xh = request.getParameter("xh");
     String hscode = request.getParameter("hscode");
     String oneUnitDesc = request.getParameter("oneUnitDesc");
     String twoUnitDesc = request.getParameter("twoUnitDesc");
     String taxRate = request.getParameter("taxRate");
     String productRecordNo = request.getParameter("productRecordNo");
     String code = request.getParameter("itemcode");
 
     
     Item i = new Item();
     i.setId(Integer.valueOf(id));
     i.setUserId(userId);
     i.setItemSpec(itemSpec);
     i.setItemcode(code);
     try {
       if (StringUtils.isNotEmpty(unitWeight)) {
         i.setUnitWeight(Double.parseDouble(unitWeight));
       }
     } catch (Exception e) {
       e.printStackTrace();
       return -1;
     } 
     i.setUnitDesc(unitDesc);
     
     i.setCountry(country);
     i.setItemSKU(itemSKU);
     i.setItemName(itemName);
     i.setCompanyCode(companyCode);
     i.setProductRecordNo(productRecordNo);
     i.setXz(xz);
     i.setXh(xh);
     i.setHscode(hscode);
     i.setOneUnitDesc(oneUnitDesc);
     i.setTwoUnitDesc(twoUnitDesc);
     i.setTaxRate(Double.parseDouble(taxRate));
     i.setWmstype("0");
     
     int result = this.itemService.updateItme(i);
     return result;
   }
 
 
 
   
   @RequestMapping({"/importAgent"})
   public ModelAndView importAgent(MultipartFile excelFile, HttpServletRequest request) {
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     int id = ((Integer)request.getSession().getAttribute("userId")).intValue();
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
       String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       excelFile.transferTo(targetFile);
       result = this.szqgAgentService.importAgent(id, String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
   @RequestMapping({"imputWMS"})
   public void implWMS(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String ids = request.getParameter("ids");
     List<Item> findAll = new ArrayList<>();
     if (ids.equals("code")) {
       findAll = this.itemService.finImpWms();
     } else {
       String[] arrayOfString = ids.split(",");
       findAll = this.itemService.findArray(arrayOfString);
     } 
     JSONObject jsonObject = new JSONObject();
     JSONArray jsonArray = new JSONArray();
     for (Item itemCustom : findAll) {
       JSONObject object = (JSONObject)JSON.toJSON(itemCustom);
       jsonArray.add(object);
     } 
     jsonObject.put("orderList", jsonArray);
     String param = jsonObject.toString();
     
     URL wsUrl = new URL("http://192.168.1.216:8080/wms/ws/importZTZItem?wsdl");
 
     
     HttpURLConnection conn = (HttpURLConnection)wsUrl.openConnection();
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestMethod("POST");
     conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
     
     OutputStream os = conn.getOutputStream();
     
     String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://service.zt.com/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body> <q0:importZTZItem><arg0>" + 
      Base64.encodeBase64String(param.getBytes("utf-8")) + "</arg0>  </q0:importZTZItem> </soapenv:Body> </soapenv:Envelope>";
     os.write(soap.getBytes());
     InputStream is = conn.getInputStream();
     byte[] b = new byte[1024];
     int len = 0;
     String s = "";
     while ((len = is.read(b)) != -1) {
       String ss = new String(b, 0, len, "UTF-8");
       s = String.valueOf(s) + ss;
     } 
     String v = s.substring(s.indexOf("<return>") + 8, s.indexOf("</return>"));
     String[] split = v.split(",");
     String msg = split[0].substring(23, split[0].length() - 6);
     String result = split[1].substring(25, split[1].length() - 7);
     Map<String, Object> map = new HashMap<>();
     if (result.equals("fail"))
     { map.put("msg", msg); }
     else { result.equals("success"); }
     
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
     is.close();
     os.close();
     conn.disconnect();
   }
   
   @RequestMapping({"/exportba"})
   public void exportOrderBonded_2(HttpServletRequest request, HttpServletResponse response) throws IOException {
     long l1 = System.currentTimeMillis();
     
     SXSSFWorkbook ob = new SXSSFWorkbook();
 
     
     Sheet sheet = ob.createSheet("sheet1");
     sheet.setDefaultColumnWidth(20);
 
     
     Row row = sheet.createRow(0);
     CellStyle cellStyle = ob.createCellStyle();
 
     
     cellStyle.setAlignment((short)2);
 
     
     Cell cell = row.createCell(0);
     
     cell.setCellValue("*商品SKU");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("商品条码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("商品规格");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("单位");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("商家编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("海关备案编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("第一计量单位");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("第二计量单位");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("产品国检备案编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("重量(kg)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("税率");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("原产国编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("金二序号");
     cell.setCellStyle(cellStyle);
     
     String ids = request.getParameter("ids");
     String gjz = "";
     if (ids.equals("cxqb")) {
 
       
       gjz = "1=1";
     } else {
       
       gjz = " id in (" + ids + ")";
     }     
     List<Item> list = this.itemdao.findgjz(gjz);
     
     int i = 1;
     int num = 1;
     for (Item order : list) {
 
 
 
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(order.getItemSKU());
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue(order.getItemName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(order.getItemcode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(order.getItemSpec());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(order.getUnitDesc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue(order.getCompanyCode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(order.getHscode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(order.getOneUnitDesc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(order.getTwoUnitDesc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(order.getProductRecordNo());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(order.getUnitWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(order.getTaxRate());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(order.getCountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(order.getInternalNumber());
       cell.setCellStyle(cellStyle);
     } 
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=item.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
   
   @ResponseBody
   @RequestMapping({"/toewtp"})
   public String toewtp(String[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String message = "";
     System.out.println("toewtp fishr"); byte b; int i; String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
       String gzj = id;
       System.out.println("=========" + id);
       List<SzqgAgent> lists = this.szqgagentdao.findgjz(gzj);
       
       for (SzqgAgent sa : lists) {
         System.out.println("=========" + sa.getDxpId());
         
         JSONObject jsonObject = new JSONObject();
         
         jsonObject.put("commitTime", timenew.newtime());
         jsonObject.put("lawName", sa.getLawName());
         jsonObject.put("appStatus", Integer.valueOf(2));
         jsonObject.put("fullName", sa.getFullName());
         jsonObject.put("socialCreditCode", sa.getSocialCreditCode());
         jsonObject.put("businessCode", sa.getBusinessCode());
         jsonObject.put("tradeCo", sa.getTradeCo());
         jsonObject.put("lawNameTel", sa.getLawNameTel());
         jsonObject.put("regCo", sa.getRegCo());
         jsonObject.put("enFullCo", sa.getEnFullCo());
         jsonObject.put("idNumber", sa.getIdNumber());
         jsonObject.put("busiType", sa.getBusiType());
         jsonObject.put("customsCode", sa.getCustomsCode());
         jsonObject.put("copEndDate", sa.getCopEndDate());
         jsonObject.put("dxpId", sa.getDxpId());
         jsonObject.put("dxpMode", "dxp");
         jsonObject.put("addrCo", sa.getAddrCo());
         jsonObject.put("valiDate", sa.getValiDate());
         jsonObject.put("enAddrCo", sa.getEnAddrCo());
         jsonObject.put("rgDate", sa.getRgDate());
         jsonObject.put("guid", "");
         
         List<SzqgAgreement> list = this.szqgagreementdao.findgjz("1");
         String PriKey = "";
         for (SzqgAgreement sam : list) {
           jsonObject.put("appid", sam.getAppid());
           jsonObject.put("version", sam.getVersion());
           jsonObject.put("merchantName", sam.getMerchantName());
           jsonObject.put("merchantId", sam.getMerchantId());
           PriKey = sam.getPersonPriKey();
         }         
         ApolloSdk apolloSdk = new ApolloSdk();
         
         String param = apolloSdk.wrapSign(SecurityUtils.getPriKey(PriKey), jsonObject.toString());
         
         System.out.println("param=" + param);
         String url = "http://apollo.jieztech.com/aoa/api/aoa/publicServicePlatformFacade/domesticAgentEnterpriseRecord";
         
         String res = hcszqgpost.ZYPost(url, param);
         System.out.println("res=" + res);
         
         JSONObject ret = JSONObject.parseObject(res);
         
         String code = ret.getString("code");
         
         System.out.println("code=====" + code);
         
         message = ret.getString("message");
         
         System.out.println("message=====" + message);
         
         if (code.equals("200")) {
           String data = ret.getString("data");
           JSONObject datas = JSONObject.parseObject(data);
           String guid = datas.getString("guid");
           System.out.println("guid=====" + guid);
           
           this.szqgagentdao.updatebygjz(sa.getId(), guid, timenew.newtime4());
         } 
       } 
 
       
       b++; }
 
 
     
     return URLEncoder.encode(message, "UTF-8");
   }
 }


