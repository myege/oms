 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.SzqgOrderDao;
 import com.what21.model.OrderBonded;
 import com.what21.model.ReceiveHghDataVo;
 import com.what21.model.ReceiveHghzData;
 import com.what21.model.SzqgOrder;
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.service.ReceiveHghzDataService;
 import com.zt.kjybd.newzs;
 import java.io.BufferedReader;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.codec.binary.Base64;
 @Controller
 @RequestMapping({"/receiveHghzData"})
 public class ReceiveHghzDataAction
   extends BaseAction
 {
   @Autowired
   public ReceiveHghzDataService receiveHghzDataService;
   @Autowired
   public OrderBondedDao orderBondedDao;
   @Autowired
   public SzqgOrderDao szqgOrderDao;
   String BILL_RECEIPT_PARSING_SECRETKEY = (String)ZtzConst.OPTIONS.get(ZtzPropertiesEnum.BILL_RECEIPT_PARSING_SECRETKEY.getValue());
   
   @RequestMapping({"/receiveData"})
   public void receiveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
     
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       
       System.out.println("responseStrBuilder===>>>>" + responseStrBuilder.toString());
       
       String name = newzs.getRandomCharAndNumr(Integer.valueOf(10));
       String fileName = "D:\\hz1210\\" + name + ".xml";
       saveFile(fileName, responseStrBuilder.toString());
       
       String zfc = responseStrBuilder.toString();
       
       if (zfc.contains("copNo")) {
         JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
         String copNo = jsonObject.get("copNo").toString();
         String returnInfo = jsonObject.get("returnInfo").toString();
         String returnStatus = jsonObject.get("returnStatus").toString();
         if (copNo.startsWith("YW1210")) {
           System.out.println("1210");
           if (returnStatus.equals("800")) {
             OrderBonded orderBonded = new OrderBonded();
             orderBonded.setPreEntryNumber(copNo);
             orderBonded.setReturnInfo(returnInfo);
             orderBonded.setAuditstatus(9);
             orderBonded.setReturncode(Integer.valueOf(9));
             orderBonded.setPreEntryNumber(copNo);
             orderBonded.setInvtNo("");
             this.orderBondedDao.updateAuditstatus3(orderBonded);
           } 
         } else {
           
           System.out.println("其他");
           
           if (returnStatus.equals("400")) {
             SzqgOrder szqgOrder = new SzqgOrder();
             szqgOrder.setStatus("9");
             szqgOrder.setQdzt(returnInfo);
             szqgOrder.setCopNo(copNo);
             this.szqgOrderDao.updateAuditstatus3(szqgOrder);
           } else {
             SzqgOrder szqgOrder = new SzqgOrder();
             szqgOrder.setStatus("4");
             szqgOrder.setQdzt(returnInfo);
             szqgOrder.setCopNo(copNo);
             this.szqgOrderDao.updateAuditstatus3(szqgOrder);
           } 
           System.out.println("ewt其他2");
         }
       
       }
     
     } catch (Exception e) {
       e.printStackTrace();
     } 
     
     String ret = "SUCCESS";
     response.getWriter().write(ret);
   }
 
   
   @RequestMapping({"/xcxjj"})
   public void xcxjj(String pickname, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String content = request.getParameter("pickname");
     String msg_type = request.getParameter("picksum");
     System.out.println("111=====" + pickname);
     System.out.println("111=====" + content);
     System.out.println("111=====" + msg_type);
     String ret = "1";
     
     response.getWriter().write(ret);
   }
 
 
   
   @RequestMapping({"/yzapi"})
   public void yzapi(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
     System.out.println("111=====" + code);
     String ret = "1";
   }
 
   
   @RequestMapping({"/findAll"})
   public void findAll(ReceiveHghDataVo re, HttpServletRequest request, HttpServletResponse response) throws Exception {
     int page = re.getPage();
     int rows = re.getRows();
     int isHandle = re.getIsHandle();
     Map<String, Object> map = new HashMap<>();
     Map<String, Object> pageMap = new HashMap<>();
     
     pageMap.put("startPage", Integer.valueOf((page - 1) * rows));
     pageMap.put("endPage", Integer.valueOf(rows));
     pageMap.put("isHandle", Integer.valueOf(isHandle));
     pageMap.put("startTime", request.getParameter("startTime"));
     pageMap.put("endTime", request.getParameter("endTime"));
     List<ReceiveHghzData> u = this.receiveHghzDataService.findAll(pageMap);
     int total = this.receiveHghzDataService.count(pageMap);
     map.put("rows", u);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @ResponseBody
   @RequestMapping({"/update"})
   public int update(@RequestParam(value = "id", required = true) String id) {
     int result = this.receiveHghzDataService.update(id);
     return result;
   }
 
   
   public static void saveFile(String fileName, String content) {
     try {
       OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
       osw.write(content);
       osw.close();
     } catch (IOException e) {
       e.printStackTrace();
     } 
   }
 }


