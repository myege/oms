 package com.what21.action;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.jd.open.api.sdk.DefaultJdClient;
 import com.jd.open.api.sdk.request.JdRequest;
 import com.jd.open.api.sdk.request.customsglobalAPI.PopWareGpsApiOnlineRecordJosServiceCustomsUpdateRequest;
 import com.jd.open.api.sdk.response.customsglobalAPI.PopWareGpsApiOnlineRecordJosServiceCustomsUpdateResponse;
 import com.what21.dao.IjdxnDao;
 import com.what21.model.OnlineRecordingParamTo;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.IjdxnService;
 import com.what21.tools.Tools;
 import java.io.IOException;
 import java.io.OutputStream;
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
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/jdxn"})
 public class JdxnAction
   extends BaseAction
 {
   @Autowired
   public IjdxnService ijdxnService;
   @Autowired
   public IjdxnDao ijdxndao;
   String SERVER_URL = "https://api.jd.com/routerjson";
   String appKey = "3B03D933A76CF26E84CD449B63FEE94F";
   String accessToken = "a2b7cc35e5d240b3bd529a9fc9524610tjin";
   String appSecret = "33bd053e696c4cd9b6600148dea111ec";
 
   
   @RequestMapping({"/findAll"})
   public void findAll(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
     System.out.println("step 1");
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
     String userName = request.getSession().getAttribute("userName").toString();
 
     
     userId = getUserId(userName, userId);
     
     pageMap.put("time3", orderSearchDto.getTime3());
     pageMap.put("time4", orderSearchDto.getTime4());
     pageMap.put("text3", orderSearchDto.getText3());
     pageMap.put("text4", orderSearchDto.getText4());
     pageMap.put("text5", orderSearchDto.getText5());
     pageMap.put("userId", Integer.valueOf(userId));
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     List<OnlineRecordingParamTo> u = this.ijdxnService.findAll(pageMap);
     int total = this.ijdxnService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str); } @ResponseBody
   @RequestMapping({"/Tg"})
   public int tg(String[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception { byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String sellerRecord = arrayOfString[b];
       List<OnlineRecordingParamTo> lists = this.ijdxndao.findbygjz(sellerRecord);
       for (OnlineRecordingParamTo ijdxn : lists) {
         System.out.println("=========" + ijdxn.getSkuId());
         
         JSONObject recordingParamToJson = new JSONObject();
         recordingParamToJson.put("operation", "2");
         recordingParamToJson.put("skuId", ijdxn.getSkuId());
         recordingParamToJson.put("operationSign", "1");
         
         recordingParamToJson.put("customId", ijdxn.getCustomId());
         recordingParamToJson.put("ccProvider", ijdxn.getCcProvider());
         recordingParamToJson.put("customModel", ijdxn.getCustomModel());
         recordingParamToJson.put("customsRegionCode", ijdxn.getCustomsRegionCode());
         recordingParamToJson.put("eclpCode", ijdxn.getEclpCode());
         recordingParamToJson.put("eclpName", ijdxn.getEclpName());
         recordingParamToJson.put("id", ijdxn.getIds());
         recordingParamToJson.put("venderId", ijdxn.getVenderId());
         recordingParamToJson.put("email", ijdxn.getEmail());
         recordingParamToJson.put("netWeight", ijdxn.getNetWeight());
         recordingParamToJson.put("spe", ijdxn.getSpe());
         recordingParamToJson.put("taxCommitmentsway", ijdxn.getTaxCommitmentsway());
         recordingParamToJson.put("venderName", ijdxn.getVenderName());
         recordingParamToJson.put("brandEn", ijdxn.getBrandEn());
         recordingParamToJson.put("grossWeight", ijdxn.getGrossWeight());
         recordingParamToJson.put("unit", ijdxn.getUnit());
         recordingParamToJson.put("goodsName", ijdxn.getGoodsName());
         recordingParamToJson.put("goodsNameEn", ijdxn.getGoodsNameEn());
         recordingParamToJson.put("goodsSellerPrice", ijdxn.getGoodsSellerPrice());
         recordingParamToJson.put("brand", ijdxn.getBrand());
         recordingParamToJson.put("composition", ijdxn.getComposition());
         recordingParamToJson.put("function", ijdxn.getFunction());
         recordingParamToJson.put("goodsData", ijdxn.getGoodsData());
         recordingParamToJson.put("goodsPicture", ijdxn.getGoodsPicture());
         recordingParamToJson.put("hgsbys", ijdxn.getHgsbys());
         recordingParamToJson.put("hsCode", ijdxn.getHsCode());
         recordingParamToJson.put("manufacturer", ijdxn.getManufacturer());
         recordingParamToJson.put("modified", ijdxn.getModified());
         recordingParamToJson.put("originArea", ijdxn.getOriginArea());
         recordingParamToJson.put("originCountry", ijdxn.getOriginCountry());
         recordingParamToJson.put("phone", ijdxn.getPhone());
         recordingParamToJson.put("roduceAddress", ijdxn.getRoduceAddress());
         recordingParamToJson.put("safeDays", ijdxn.getSafeDays());
         recordingParamToJson.put("salesWebSite", ijdxn.getSalesWebSite());
         recordingParamToJson.put("supplier", ijdxn.getSupplier());
         recordingParamToJson.put("taxRate", ijdxn.getTaxRate());
         recordingParamToJson.put("upc", ijdxn.getUpc());
         recordingParamToJson.put("vatRate", ijdxn.getVatRate());
         recordingParamToJson.put("volume", ijdxn.getVolume());
         recordingParamToJson.put("xingHao", ijdxn.getXingHao());
         recordingParamToJson.put("use", ijdxn.getUse());
 
         
         DefaultJdClient defaultJdClient = new DefaultJdClient(this.SERVER_URL, this.accessToken, this.appKey, this.appSecret);
         PopWareGpsApiOnlineRecordJosServiceCustomsUpdateRequest requestjd = new PopWareGpsApiOnlineRecordJosServiceCustomsUpdateRequest();
         requestjd.setRecordingParamToJson(recordingParamToJson.toJSONString());
         requestjd.setRecordedParamToJson("");
         requestjd.setCustomsId("hangzhou");
         requestjd.setServiceId("020045");
         PopWareGpsApiOnlineRecordJosServiceCustomsUpdateResponse responsejd = (PopWareGpsApiOnlineRecordJosServiceCustomsUpdateResponse)defaultJdClient.execute((JdRequest)requestjd);
         
         System.out.println("back=====" + responsejd.getMsg());
         String result = responsejd.getMsg();
 
         
         JSONObject ret = JSONObject.parseObject(result);
         JSONObject responce = ret.getJSONObject("jingdong_pop_ware_gps_api_OnlineRecordJosService_customsUpdate_responce");
         JSONObject orderListResult = responce.getJSONObject("result");
         
         this.ijdxndao.updatebygjz(ijdxn.getSkuId(), orderListResult.getString("desc"), "1");
       } 
 
       
       b++; }
 
     
     return 1; }
   
   @ResponseBody
   @RequestMapping({"/Bh"})
   public int bohui(String[] ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String sellerRecord = arrayOfString[b];
       List<OnlineRecordingParamTo> lists = this.ijdxndao.findbygjz(sellerRecord);
       for (OnlineRecordingParamTo ijdxn : lists) {
         System.out.println("=========" + ijdxn.getSkuId());
         
         JSONObject recordingParamToJson = new JSONObject();
         recordingParamToJson.put("operation", "2");
         recordingParamToJson.put("skuId", ijdxn.getSkuId());
         recordingParamToJson.put("operationSign", "2");
         recordingParamToJson.put("rejectInfo", "数据有误");
         recordingParamToJson.put("customId", ijdxn.getCustomId());
         recordingParamToJson.put("ccProvider", ijdxn.getCcProvider());
         recordingParamToJson.put("customModel", ijdxn.getCustomModel());
         recordingParamToJson.put("customsRegionCode", ijdxn.getCustomsRegionCode());
         recordingParamToJson.put("eclpCode", ijdxn.getEclpCode());
         recordingParamToJson.put("eclpName", ijdxn.getEclpName());
         recordingParamToJson.put("id", ijdxn.getIds());
         recordingParamToJson.put("venderId", ijdxn.getVenderId());
         recordingParamToJson.put("email", ijdxn.getEmail());
         recordingParamToJson.put("netWeight", ijdxn.getNetWeight());
         recordingParamToJson.put("spe", ijdxn.getSpe());
         recordingParamToJson.put("taxCommitmentsway", ijdxn.getTaxCommitmentsway());
         recordingParamToJson.put("venderName", ijdxn.getVenderName());
         recordingParamToJson.put("brandEn", ijdxn.getBrandEn());
         recordingParamToJson.put("grossWeight", ijdxn.getGrossWeight());
         recordingParamToJson.put("unit", ijdxn.getUnit());
         recordingParamToJson.put("goodsName", ijdxn.getGoodsName());
         recordingParamToJson.put("goodsNameEn", ijdxn.getGoodsNameEn());
         recordingParamToJson.put("goodsSellerPrice", ijdxn.getGoodsSellerPrice());
         recordingParamToJson.put("brand", ijdxn.getBrand());
         recordingParamToJson.put("composition", ijdxn.getComposition());
         recordingParamToJson.put("function", ijdxn.getFunction());
         recordingParamToJson.put("goodsData", ijdxn.getGoodsData());
         recordingParamToJson.put("goodsPicture", ijdxn.getGoodsPicture());
         recordingParamToJson.put("hgsbys", ijdxn.getHgsbys());
         recordingParamToJson.put("hsCode", ijdxn.getHsCode());
         recordingParamToJson.put("manufacturer", ijdxn.getManufacturer());
         recordingParamToJson.put("modified", ijdxn.getModified());
         recordingParamToJson.put("originArea", ijdxn.getOriginArea());
         recordingParamToJson.put("originCountry", ijdxn.getOriginCountry());
         recordingParamToJson.put("phone", ijdxn.getPhone());
         recordingParamToJson.put("roduceAddress", ijdxn.getRoduceAddress());
         recordingParamToJson.put("safeDays", ijdxn.getSafeDays());
         recordingParamToJson.put("salesWebSite", ijdxn.getSalesWebSite());
         recordingParamToJson.put("supplier", ijdxn.getSupplier());
         recordingParamToJson.put("taxRate", ijdxn.getTaxRate());
         recordingParamToJson.put("upc", ijdxn.getUpc());
         recordingParamToJson.put("vatRate", ijdxn.getVatRate());
         recordingParamToJson.put("volume", ijdxn.getVolume());
         recordingParamToJson.put("xingHao", ijdxn.getXingHao());
         recordingParamToJson.put("use", ijdxn.getUse());
         recordingParamToJson.put("actualWeight", ijdxn.getGrossWeight());
         DefaultJdClient defaultJdClient = new DefaultJdClient(this.SERVER_URL, this.accessToken, this.appKey, this.appSecret);
 
         
         PopWareGpsApiOnlineRecordJosServiceCustomsUpdateRequest requestjd = new PopWareGpsApiOnlineRecordJosServiceCustomsUpdateRequest();
         requestjd.setRecordingParamToJson(recordingParamToJson.toJSONString());
         requestjd.setRecordedParamToJson("");
         requestjd.setCustomsId("hangzhou");
         requestjd.setServiceId("020045");
         PopWareGpsApiOnlineRecordJosServiceCustomsUpdateResponse responsejd = (PopWareGpsApiOnlineRecordJosServiceCustomsUpdateResponse)defaultJdClient.execute((JdRequest)requestjd);
         
         String result = responsejd.getMsg();
         System.out.println("back3=====" + result);
         JSONObject ret = JSONObject.parseObject(result);
         JSONObject responce = ret.getJSONObject("jingdong_pop_ware_gps_api_OnlineRecordJosService_customsUpdate_responce");
         JSONObject orderListResult = responce.getJSONObject("result");
         String bohui = orderListResult.getString("desc");
         if (bohui.indexOf("数据校验失败") == -1) {
           System.out.println("back2=====" + responsejd.getMsg());
           this.ijdxndao.updatebygjz(ijdxn.getSkuId(), orderListResult.getString("desc"), "2"); continue;
         } 
         System.out.println("back1=====" + responsejd.getMsg());
         this.ijdxndao.updatebygjz(ijdxn.getSkuId(), "数据校验失败", "2");
       } 
 
 
       
       b++; }
 
 
 
     
     return 1;
   }
 
 
   
   @ResponseBody
   @RequestMapping({"/Zq"})
   public int zhuaqu(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String[] sj = { "10151612", "10288514", "10169397", "713858", "1000282744", "1000282745", "10150385", "10056661", "10192404", "10047783", "10054899", "10098884", "663625", "10068075", "10074714", 
         "613766", "10010078", "604706", "752280", "770161", "714360", "665224", "10060254", "823645", "748649", "10110688", "10103735", "10129743" };
     
     for (int k = 0; k < sj.length; k++) {
       
       System.out.println(String.valueOf(sj[k]) + "------ids");
       String methodName = "jingdong.pop.ware.gps.api.OnlineRecordJosService.queryList";
       JSONObject jsonObject = new JSONObject();
       jsonObject.put("queryToJson", "{\"venderId\":\"" + sj[k] + "\",\"status\":\"1\"}");
       jsonObject.put("serviceId", "020045");
       jsonObject.put("customsId", "hangzhou");
 
 
 
       
       JSONObject ret = JSONObject.parseObject("");
       JSONObject responce = ret.getJSONObject("jingdong_pop_ware_gps_api_OnlineRecordJosService_queryList_responce");
 
       
       JSONObject Result = responce.getJSONObject("result");
       String list = Result.getString("result");
       JSONArray body = JSONArray.parseArray(list);
 
       
       for (int i = 0; i < body.size(); i++) {
         JSONObject lc = body.getJSONObject(i);
         
         List<OnlineRecordingParamTo> lists = this.ijdxndao.findbygjz(lc.getString("skuId"));
         if (lists.size() <= 0) {
 
           
           OnlineRecordingParamTo or = new OnlineRecordingParamTo();
           or.setBrand(lc.getString("brand"));
           or.setBrandEn(lc.getString("brandEn"));
           or.setCcProvider(lc.getString("ccProvider"));
           or.setComposition(lc.getString("composition"));
           or.setCreated(lc.getString("created"));
           or.setCustomId(lc.getString("customId"));
           or.setCustomModel(lc.getString("customModel"));
           or.setCustomsRegionCode(lc.getString("customsRegionCode"));
           or.setEclpCode(lc.getString("eclpCode"));
           or.setEclpName(lc.getString("eclpName"));
           or.setEmail(lc.getString("email"));
           or.setFunction(lc.getString("function"));
           or.setGoodsData(lc.getString("goodsData"));
           or.setGoodsName(lc.getString("goodsName"));
           or.setGoodsNameEn(lc.getString("goodsNameEn"));
           or.setGoodsPicture(lc.getString("goodsPicture"));
           or.setGoodsSellerPrice(lc.getString("goodsSellerPrice"));
           or.setGrossWeight(lc.getString("grossWeight"));
           or.setHgsbys(lc.getString("hgsbys"));
           or.setHsCode(lc.getString("hsCode"));
           or.setIds(lc.getString("id"));
           or.setManufacturer(lc.getString("manufacturer"));
           or.setModified(lc.getString("modified"));
           or.setNetWeight(lc.getString("netWeight"));
           or.setNote(lc.getString("note"));
           or.setOperation(lc.getString("operation"));
           or.setOriginArea(lc.getString("originArea"));
           or.setOriginCountry(lc.getString("originCountry"));
           or.setPhone(lc.getString("phone"));
           or.setRoduceAddress(lc.getString("roduceAddress"));
           or.setSafeDays(lc.getString("safeDays"));
           or.setSalesWebSite(lc.getString("salesWebSite"));
           or.setSkuId(lc.getString("skuId"));
           or.setSpe(lc.getString("spe"));
           or.setStatus(lc.getString("status"));
           or.setSupplier(lc.getString("supplier"));
           or.setTaxCommitmentsway(lc.getString("taxCommitmentsway"));
           or.setTaxRate(lc.getString("taxRate"));
           or.setUnit(lc.getString("unit"));
           or.setUpc(lc.getString("upc"));
           or.setUse(lc.getString("use"));
           or.setVatRate(lc.getString("vatRate"));
           or.setVenderId(lc.getString("venderId"));
           or.setVenderName(lc.getString("venderName"));
           or.setVolume(lc.getString("volume"));
           or.setXingHao(lc.getString("xingHao"));
           or.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
           or.setXtzt("0");
           List<OnlineRecordingParamTo> onto = new ArrayList<>();
           onto.add(or);
           this.ijdxndao.insertBatch(onto);
         } 
       } 
     } 
     return 1;
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
     
     cell.setCellValue("*HS编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(1);
     cell.setCellValue("*条形码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(2);
     cell.setCellValue("*商品描述");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(3);
     cell.setCellValue("*品牌");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(4);
     cell.setCellValue("*用途/功能");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(5);
     cell.setCellValue("*包装类型");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(6);
     cell.setCellValue("*采购国(地区)名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(7);
     cell.setCellValue("*品牌国名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(8);
     cell.setCellValue("生产国(地区)名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(9);
     cell.setCellValue("*生产企业");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(10);
     cell.setCellValue("配料及成分说明");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(11);
     cell.setCellValue("网络链接");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(12);
     cell.setCellValue("sku编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(13);
     cell.setCellValue("条形码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(14);
     cell.setCellValue("EMG");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(15);
     cell.setCellValue("税费承担方");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(16);
     cell.setCellValue("商品名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(17);
     cell.setCellValue("商品名称（英文）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(18);
     cell.setCellValue("品牌（中文）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(19);
     cell.setCellValue("品牌（英文）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(20);
     cell.setCellValue("型号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(21);
     cell.setCellValue("规格");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(22);
     cell.setCellValue("申报/合同单位");
     cell.setCellStyle(cellStyle);
 
     
     cell = row.createCell(23);
     cell.setCellValue("商品单价（人民币）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(24);
     cell.setCellValue("毛重(kg)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(25);
     cell.setCellValue("净重(kg)");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(26);
     cell.setCellValue("实物重量");
     cell.setCellStyle(cellStyle);
 
     
     cell = row.createCell(27);
     cell.setCellValue("体积");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(28);
     cell.setCellValue("保质期（天数）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(29);
     cell.setCellValue("销售网址");
     cell.setCellStyle(cellStyle);
 
     
     cell = row.createCell(30);
     cell.setCellValue("商品图片");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(31);
     cell.setCellValue("商品资料");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(32);
     cell.setCellValue("HS编码");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(33);
     cell.setCellValue("海关申报要素");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(34);
     cell.setCellValue("功能");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(35);
     cell.setCellValue("用途");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(36);
     cell.setCellValue("成份");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(37);
     cell.setCellValue("增值税率");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(38);
     cell.setCellValue("消费税率");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(39);
     cell.setCellValue("原产国");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(40);
     cell.setCellValue("原产地区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(41);
     cell.setCellValue("生产企业名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(42);
     cell.setCellValue("生产企业地址（奶制品必填）");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(43);
     cell.setCellValue("供应商");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(44);
     cell.setCellValue("备注");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(45);
     cell.setCellValue("商家ID");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(46);
     cell.setCellValue("eclp事业部编号");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(47);
     cell.setCellValue("eclp事业部名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(48);
     cell.setCellValue("跨境业务模式");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(49);
     cell.setCellValue("保税区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(50);
     cell.setCellValue("关区");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(51);
     cell.setCellValue("服务商");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(52);
     cell.setCellValue("店铺名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(53);
     cell.setCellValue("服务商名称");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(54);
     cell.setCellValue("商家联系方式");
     cell.setCellStyle(cellStyle);
     
     cell = row.createCell(55);
     cell.setCellValue("商家邮箱");
     cell.setCellStyle(cellStyle);
     
     String ids = request.getParameter("ids");
     String gjz = "";
     if (ids.equals("cxqb")) {
       String queryName = request.getParameter("queryName");
       String queryValue = request.getParameter("queryValue");
       if (queryName.equals("text5")) {
         gjz = "venderId=" + queryValue;
       } else if (queryName.equals("text4")) {
         gjz = "upc='" + queryValue + "'";
       } else if (queryName.equals("text5")) {
         gjz = "skuId='" + queryValue + "'";
       } 
       
       if (queryValue.equals("") || queryValue.length() == 0) {
         gjz = "1=1";
       }
     } else {
       gjz = " id in (" + ids + ")";
     }     
     List<OnlineRecordingParamTo> list = this.ijdxndao.findgjz(gjz);
     
     int i = 1;
     int num = 1;
     for (OnlineRecordingParamTo order : list) {
 
 
 
       
       row = sheet.createRow(i++);
       cell = row.createCell(0);
       cell.setCellValue(order.getHsCode());
       cell.setCellStyle(cellStyle);
       
       num++;
       
       cell = row.createCell(1);
       cell.setCellValue(order.getUpc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(2);
       cell.setCellValue(String.valueOf(order.getBrand()) + order.getGoodsName() + order.getSpe());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(3);
       cell.setCellValue(String.valueOf(order.getBrandEn()) + "/" + order.getBrand());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(4);
       cell.setCellValue(order.getUse());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(5);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(6);
       cell.setCellValue(order.getOriginCountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(7);
       cell.setCellValue(order.getOriginCountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(8);
       cell.setCellValue(order.getOriginCountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(9);
       cell.setCellValue(order.getManufacturer());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(10);
       cell.setCellValue(order.getHgsbys());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(11);
       cell.setCellValue(order.getSalesWebSite());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(12);
       cell.setCellValue(order.getSkuId());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(13);
       cell.setCellValue(order.getUpc());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(14);
       cell.setCellValue(order.getEmgSkuId());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(15);
       cell.setCellValue(order.getTaxCommitmentsway());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(16);
       cell.setCellValue(order.getGoodsName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(17);
       cell.setCellValue(order.getGoodsNameEn());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(18);
       cell.setCellValue(order.getBrand());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(19);
       cell.setCellValue(order.getBrandEn());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(20);
       cell.setCellValue(order.getXingHao());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(21);
       cell.setCellValue(order.getSpe());
       cell.setCellStyle(cellStyle);
 
       
       cell = row.createCell(22);
       cell.setCellValue(order.getUnit());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(23);
       cell.setCellValue(order.getGoodsSellerPrice());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(24);
       cell.setCellValue(order.getGrossWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(25);
       cell.setCellValue(order.getNetWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(26);
       cell.setCellValue(order.getActualWeight());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(27);
       cell.setCellValue(order.getVolume());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(28);
       cell.setCellValue(order.getSafeDays());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(29);
       cell.setCellValue(order.getSalesWebSite());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(30);
       cell.setCellValue(order.getGoodsPicture());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(31);
       cell.setCellValue(order.getGoodsData());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(32);
       cell.setCellValue(order.getHsCode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(33);
       cell.setCellValue(order.getHgsbys());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(34);
       cell.setCellValue(order.getFunction());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(35);
       cell.setCellValue(order.getUse());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(36);
       cell.setCellValue(order.getComposition());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(37);
       cell.setCellValue(order.getVatRate());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(38);
       cell.setCellValue(order.getTaxRate());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(39);
       cell.setCellValue(order.getOriginCountry());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(40);
       cell.setCellValue(order.getOriginArea());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(41);
       cell.setCellValue(order.getManufacturer());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(42);
       cell.setCellValue(order.getRoduceAddress());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(43);
       cell.setCellValue(order.getSupplier());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(44);
       cell.setCellValue("");
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(45);
       cell.setCellValue(order.getVenderId());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(46);
       cell.setCellValue(order.getEclpCode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(47);
       cell.setCellValue(order.getEclpName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(48);
       cell.setCellValue(order.getCustomModel());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(49);
       cell.setCellValue(order.getCustomId());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(50);
       cell.setCellValue(order.getCustomsRegionCode());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(51);
       cell.setCellValue(order.getCcProvider());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(52);
       cell.setCellValue(order.getVenderName());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(53);
       cell.setCellValue(order.getCcProvider());
       cell.setCellStyle(cellStyle);
       
       cell = row.createCell(54);
       cell.setCellValue(order.getPhone());
       cell.setCellStyle(cellStyle);
 
       
       cell = row.createCell(55);
       cell.setCellValue(order.getEmail());
       cell.setCellStyle(cellStyle);
     }     
     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     response.setHeader("Content-disposition", "attachment;filename=spba.xlsx");
     ServletOutputStream servletOutputStream = response.getOutputStream();
     ob.write((OutputStream)servletOutputStream);
     servletOutputStream.flush();
     servletOutputStream.close();
     System.out.println("保税完结导出花费时间：" + (System.currentTimeMillis() - l1));
   }
 }


