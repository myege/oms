 package com.what21.action;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.jd.open.api.sdk.DefaultJdClient;
 import com.jd.open.api.sdk.request.JdRequest;
 import com.jd.open.api.sdk.request.guojiwuliu.GsPushPoConfirmRequest;
 import com.jd.open.api.sdk.response.guojiwuliu.GsPushPoConfirmResponse;
 import com.what21.dao.IjdcgdDao;
 import com.what21.dao.IjdcgditemDao;
 import com.what21.dao.IjdcgdysDao;
 import com.what21.dao.IjdlhdDao;
 import com.what21.dao.IjdtdDao;
 import com.what21.dao.IjdtditemDao;
 import com.what21.model.Ijdcgd;
 import com.what21.model.Ijdcgditem;
 import com.what21.model.Ijdcgdys;
 import com.what21.model.Ijdlhd;
 import com.what21.model.Ijdtd;
 import com.what21.model.Ijdtditem;
 import com.what21.model.OrderMail;
 import com.what21.model.OrderSearchDto;
 import com.what21.service.IjdcgdService;
 import com.what21.service.IjdcgdysService;
 import com.what21.service.IjdlhdService;
 import com.what21.service.IjdtdService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/jdwl"})
 public class JdwlAction
   extends BaseAction
 {
   @Autowired
   public IjdcgdService ijdcgdService;
   @Autowired
   public IjdcgdysService ijdcgdysService;
   @Autowired
   public IjdtdService ijdtdService;
   @Autowired
   public IjdlhdService ijdlhdService;
   @Autowired
   private IjdtdDao ijdtdDao;
   @Autowired
   private IjdtditemDao ijdtditemDao;
   @Autowired
   public IjdcgdysDao ijdcgdysDao;
   @Autowired
   private IjdcgdDao ijdcgdDao;
   @Autowired
   private IjdcgditemDao ijdcgditemDao;
   @Autowired
   private IjdlhdDao ijdlhdDao;
   String SERVER_URL = "https://api.jd.com/routerjson";
   String appKey = "3B03D933A76CF26E84CD449B63FEE94F";
   String accessToken = "b82343345a7a4daab1abcd446b278215zmzq";
   String appSecret = "addcd05bfe384d0483026fbe0150622c";
 
 
 
   
   @RequestMapping({"/findAllcgd"})
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
     List<Ijdcgd> u = this.ijdcgdService.findAll(pageMap);
     int total = this.ijdcgdService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
 
   
   @RequestMapping({"/importCgd"})
   public ModelAndView importCgd(MultipartFile excelFile, HttpServletRequest request) {
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
       result = this.ijdcgdService.importCgd(id, String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     }  } @ResponseBody
   @RequestMapping({"/deletecgd"})
   public int deletecgd(String[] ids) { byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String poNo = arrayOfString[b];
       this.ijdcgdDao.delete(poNo);
       this.ijdcgditemDao.delete(poNo);
       b++; }
     
     return 1; }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/pushcgd"})
   public int pushcgd(String[] ids) throws Exception {
     String methodName = "jingdong.gs.pushPoConfirm"; byte b; int i; String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String poNo = arrayOfString[b];
       List<Ijdcgd> lists = this.ijdcgdDao.findbygjz(poNo);
       for (Ijdcgd ijdcgd : lists) {
         
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("poNo", ijdcgd.getPoNo());
         jsonObject.put("serialNo", ijdcgd.getSerialNo());
         jsonObject.put("billNo", ijdcgd.getBillNo());
         jsonObject.put("provider", ijdcgd.getProvider());
         
         List<Ijdcgditem> listitem = this.ijdcgditemDao.findbygjz(ijdcgd.getPoNo());
         JSONArray jArray = new JSONArray();
         for (Ijdcgditem Ijdtcgditem : listitem) {
           JSONObject ladDetails = new JSONObject();
           ladDetails.put("goodsNo", Ijdtcgditem.getGoodsNo());
           ladDetails.put("sellerRecord", Ijdtcgditem.getSellerRecord());
           ladDetails.put("goodsName", Ijdtcgditem.getGoodsName());
           ladDetails.put("amount", Ijdtcgditem.getAmount());
           jArray.add(ladDetails);
         } 
         
         jsonObject.put("item", jArray);
         System.out.println("json3======" + jsonObject.toJSONString());
 
         
         DefaultJdClient defaultJdClient = new DefaultJdClient(this.SERVER_URL, this.accessToken, this.appKey, this.appSecret);
         
         GsPushPoConfirmRequest request = new GsPushPoConfirmRequest();
         request.setMessage(jsonObject.toJSONString());
         GsPushPoConfirmResponse response = (GsPushPoConfirmResponse)defaultJdClient.execute((JdRequest)request);
 
         
         String result = response.getMsg();
         
         System.out.println("cs======" + result);
         result = result.replace("\\", "").replace("\"{", "{").replace("}\"", "}");
         System.out.println("cscscs4======" + result);
         JSONObject ret = JSONObject.parseObject(result);
         JSONObject responce = ret.getJSONObject("jingdong_gs_pushPoConfirm_responce");
         JSONObject orderListResult = responce.getJSONObject("result");
         
         if (orderListResult.getString("status").equals("2")) {
           
           this.ijdcgdDao.updatebygjz(poNo, orderListResult.getString("notes"), "2"); continue;
         } 
         this.ijdcgdDao.updatebygjz(poNo, orderListResult.getString("notes"), "1");
       } 
 
 
       
       b++; }
 
 
 
     
     return 1;
   }
 
 
 
   
   @RequestMapping({"/findAllcgdys"})
   public void findAllcgdys(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
     List<Ijdcgdys> u = this.ijdcgdysService.findAll(pageMap);
     int total = this.ijdcgdysService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/importCgdys"})
   public ModelAndView importCgdys(MultipartFile excelFile, HttpServletRequest request) {
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
       result = this.ijdcgdysService.importCgdys(id, String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     }  } @ResponseBody
   @RequestMapping({"/deleteysd"})
   public int deleteysd(String[] ids) { byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String poNo = arrayOfString[b];
       this.ijdcgdysDao.delete(poNo); b++; }
     
     return 1; }
 
 
 
   
   @ResponseBody
   @RequestMapping({"/pushcgdys"})
   public int pushcgdys(String[] ids) throws Exception {
     String methodName = "jingdong.gs.pullCustomResponseMessages"; byte b; int i; String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String poNo = arrayOfString[b];
       List<Ijdcgdys> lists = this.ijdcgdysDao.findbygjz(poNo);
       for (Ijdcgdys ijdcgdys : lists) {
 
         
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("corrDocNo", ijdcgdys.getCorrDocNo());
         jsonObject.put("corrDocTime", ijdcgdys.getCorrDocTime());
         jsonObject.put("declNo", ijdcgdys.getDeclNo());
         jsonObject.put("declTime", ijdcgdys.getDeclTime());
         jsonObject.put("provider", "hangzhou");
         jsonObject.put("sheetId", ijdcgdys.getPoNo());
         
         JSONObject zh = new JSONObject();
         zh.put("request", jsonObject.toJSONString());
 
         
         String result = "";
         System.out.println("cscscs1======" + result);
         JSONObject ret = JSONObject.parseObject(result);
         JSONObject responce = ret.getJSONObject("jingdong_gs_pullCustomResponseMessages_responce");
         JSONObject orderListResult = responce.getJSONObject("result");
 
         
         System.out.println("cscscs2======" + orderListResult.getString("success"));
         System.out.println("cscscs3======" + orderListResult.getString("resultValue"));
         if (orderListResult.getString("success").equals("false")) {
           
           this.ijdcgdysDao.updatebygjz(poNo, orderListResult.getString("resultValue"), "2"); continue;
         } 
         this.ijdcgdysDao.updatebygjz(poNo, orderListResult.getString("resultValue"), "1");
       }       
       b++; }
     
     return 1;
   }
 
 
 
   
   @RequestMapping({"/findAlltd"})
   public void findAlltd(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
     List<Ijdtd> u = this.ijdtdService.findAll(pageMap);
     int total = this.ijdtdService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/importTd"})
   public ModelAndView importTd(MultipartFile excelFile, HttpServletRequest request) {
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
       result = this.ijdtdService.importTd(id, String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   }
   
   @ResponseBody
   @RequestMapping({"/deletetd"})
   public int deleteOrderBonded(String[] ids) {
     int result = this.ijdtdService.deleteTd(ids);
     return result;
   }
   @ResponseBody
   @RequestMapping({"/pushtd"})
   public int pushtd(String[] ids) throws Exception {
     byte b;
     int i;
     String[] arrayOfString;
     for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String ladNo = arrayOfString[b];
       List<Ijdtd> lists = this.ijdtdDao.findbygjz(ladNo);
       for (Ijdtd ijdtd : lists) {
         System.out.println("cs======" + ijdtd.getLadNo());
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("ladNo", ijdtd.getLadNo());
         jsonObject.put("poNo", ijdtd.getPoNo());
         jsonObject.put("intryWhere", ijdtd.getIntryWhere());
         jsonObject.put("intryType", ijdtd.getIntryType());
         jsonObject.put("transType", ijdtd.getTransType());
         jsonObject.put("billWeight", ijdtd.getBillWeight());
         jsonObject.put("interTransName", ijdtd.getInterTransName());
         
         List<Ijdtditem> listitem = this.ijdtditemDao.findbygjz(ladNo);
         JSONArray jArray = new JSONArray();
         for (Ijdtditem Ijdtditem : listitem) {
           JSONObject ladDetails = new JSONObject();
           ladDetails.put("cabinetFlag", Ijdtditem.getCabinetFlag());
           ladDetails.put("cabinetType", Ijdtditem.getCabinetType());
           ladDetails.put("actualWeight", Ijdtditem.getActualWeight());
           ladDetails.put("totalCabinet", Ijdtditem.getTotalCabinet());
           jArray.add(ladDetails);
         } 
 
         
         jsonObject.put("ladDetails", jArray);
         System.out.println("json======" + jsonObject.toJSONString());
         String methodName = "jingdong.gs.pullLadInfo";
         JSONObject zh = new JSONObject();
         zh.put("operation", "1");
         zh.put("message", jsonObject.toJSONString());
 
         
         String result = "";
         System.out.println("cscscs======" + result);
         JSONObject ret = JSONObject.parseObject(result);
         JSONObject responce = ret.getJSONObject("jingdong_gs_pullLadInfo_responce");
         JSONObject orderListResult = responce.getJSONObject("messageResult");
 
 
 
         
         if (orderListResult.getString("success").equals("false")) {
           this.ijdtdDao.updatebygjz(ladNo, orderListResult.getString("resultValue"), "2"); continue;
         } 
         this.ijdtdDao.updatebygjz(ladNo, orderListResult.getString("resultValue"), "1");
       } 
 
 
       
       b++; }
 
 
     
     return 1;
   }
 
 
   
   @RequestMapping({"/findAlllhd"})
   public void findAlllhd(OrderSearchDto orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
     List<Ijdlhd> u = this.ijdlhdService.findAll(pageMap);
     int total = this.ijdlhdService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 
 
   
   @RequestMapping({"/getlhd"})
   public String getlhd(Model model, HttpServletRequest request) {
     return "/item/operate/getlhd";
   }
 
   
   @RequestMapping({"/getjdlhd"})
   @ResponseBody
   public String getjdlhd(OrderMail orderMail, HttpServletRequest request, HttpServletResponse response) throws Exception {
     System.out.println("====" + orderMail.getTotalMailNo());
     System.out.println("=====" + orderMail.getTotalMailNoNew());
     
     String methodName = "jingdong.gs.checkPurchaseOrder";
     JSONObject jsonObject = new JSONObject();
     jsonObject.put("poNo", orderMail.getTotalMailNo());
     jsonObject.put("serProvider", orderMail.getTotalMailNoNew());
     
     String message = "[" + jsonObject.toJSONString() + "]";
     JSONObject zh = new JSONObject();
     zh.put("message", message);
 
 
     
     String result = "";
     
     JSONObject ret = JSONObject.parseObject(result);
     JSONObject responce = ret.getJSONObject("jingdong_gs_checkPurchaseOrder_responce");
     JSONObject orderListResult = responce.getJSONObject("result");
     
     if (orderListResult.getString("success").equals("true")) {
       JSONArray resultBody = orderListResult.getJSONArray("resultBody");
       this.ijdlhdDao.delete(orderMail.getTotalMailNo());
       List<Ijdlhd> list = new ArrayList<>();
       Ijdlhd lhd = new Ijdlhd();
       for (int j = 0; j < resultBody.size(); j++) {
         JSONObject rb = resultBody.getJSONObject(j);
         
         JSONArray declareDetails = rb.getJSONArray("declareDetails");
         for (int i = 0; i < declareDetails.size(); i++) {
           JSONObject lc = declareDetails.getJSONObject(i);
           
           lhd.setOwnerName(rb.getString("ownerName"));
           lhd.setOwnerNo(rb.getString("ownerNo"));
           lhd.setPoNo(rb.getString("poNo"));
           lhd.setSeqNo(rb.getString("seqNo"));
           lhd.setWarehouseName(rb.getString("warehouseName"));
           lhd.setQty(lc.getString("qty"));
           lhd.setExpectedQty(lc.getString("expectedQty"));
           lhd.setGoodsName(lc.getString("goodsName"));
           lhd.setGoodsid(lc.getString("goodsid"));
           lhd.setSellerRecord(lc.getString("sellerRecord"));
           lhd.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
           list.add(lhd);
         } 
       } 
       
       this.ijdlhdDao.insertBatch(list);
     } 
 
     
     response.getWriter().write(orderListResult.getString("resultValue"));
     return null;
   }
 }


