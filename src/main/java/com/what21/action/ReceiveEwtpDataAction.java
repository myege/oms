 package com.what21.action;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.dao.OrderBondedDao;
 import com.what21.dao.OrderBondedSkuDao;
 import com.what21.dao.SzqgOrderDao;
 import com.what21.model.OrderBonded;
 import com.what21.model.OrderBondedSku;
 import com.what21.model.ReceiveHghDataVo;
 import com.what21.model.ReceiveHghzData;
 import com.what21.model.SzqgOrder;
 import com.what21.model.ZtzConst;
 import com.what21.model.enums.ZtzPropertiesEnum;
 import com.what21.service.ItemService;
 import com.what21.service.OrderBondedService;
 import com.what21.service.ReceiveHghzDataService;
 import com.what21.service.SzqgItemService;
 import com.what21.service.SzqgOrderService;
 import com.what21.service.TinventoryService;
 import com.what21.service.impl.ReceiveHghzDataServiceImpl;
 import com.what21.tools.Tools;
 import com.zt.kjybd.PushtoWQ;
 import com.zt.kjybd.newzs;
 import java.io.BufferedReader;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.net.URLDecoder;
 import java.util.ArrayList;
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
 @Controller
 @RequestMapping({"/receiveEwtpData"})
 public class ReceiveEwtpDataAction
   extends BaseAction
 {
   @Autowired
   public ReceiveHghzDataService receiveHghzDataService;
   @Autowired
   public SzqgOrderService szqgOrderService;
   @Autowired
   public SzqgItemService szqgItemService;
   @Autowired
   public OrderBondedDao orderBondedDao;
   @Autowired
   public SzqgOrderDao szqgOrderDao;
   @Autowired
   public OrderBondedSkuDao orderBondedSkuDao;
   @Autowired
   public ItemService itemService;
   @Autowired
   public TinventoryService tinventoryService;
   @Autowired
   public OrderBondedService orderBondedService;
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
       String fileName = "D:\\yw9610\\" + name + ".xml";
       saveFile(fileName, responseStrBuilder.toString());
       
       String zfc = responseStrBuilder.toString();
       
       if (zfc.contains("copNo")) {
         JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
         String copNo = jsonObject.get("copNo").toString();
         String returnInfo = jsonObject.get("returnInfo").toString();
         String returnStatus = jsonObject.get("returnStatus").toString();
         if (copNo.startsWith("YW1210")) {
           System.out.println("ewt1210");
           if (returnStatus.equals("800")) {
             OrderBonded orderBonded = new OrderBonded();
             orderBonded.setPreEntryNumber(copNo);
             orderBonded.setReturnInfo(returnInfo);
             orderBonded.setAuditstatus(9);
             orderBonded.setReturncode(Integer.valueOf(9));
             orderBonded.setPreEntryNumber(copNo);
             orderBonded.setInvtNo("");
             this.orderBondedDao.updateAuditstatus3(orderBonded);
           } else if (returnStatus.equals("100")) {
             OrderBonded orderBonded = new OrderBonded();
             orderBonded.setPreEntryNumber(copNo);
             orderBonded.setReturnInfo(returnInfo);
             orderBonded.setAuditstatus(7);
             orderBonded.setReturncode(Integer.valueOf(1));
             orderBonded.setPreEntryNumber(copNo);
             orderBonded.setInvtNo("");
             this.orderBondedDao.updateAuditstatus3(orderBonded);
           } 
         } else {
           
           System.out.println("ewt其他");
           if (returnStatus.equals("800")) {
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
   
   @RequestMapping({"/receivebashData"})
   public void receivebashData(HttpServletRequest request, HttpServletResponse response) throws Exception {
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null)
         responseStrBuilder.append(inputStr); 
       Tools.fileLog("=======EWTP清关回执==========" + responseStrBuilder.toString());
       
       System.out.println("responseStrBuilder===>>>>" + responseStrBuilder.toString());
       
       String zfc = responseStrBuilder.toString();
       String name = newzs.getRandomCharAndNumr(Integer.valueOf(10));
       String na = "D:\\kchz\\" + name + ".xml";
       
       ReceiveHghzDataServiceImpl.saveFile(na, zfc);
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     JSONObject orderJson = new JSONObject();
     orderJson.put("success", "true");
     orderJson.put("message", "");
     orderJson.put("code", "200");
     orderJson.put("time", "20210414102608");
 
 
 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   
   @RequestMapping({"/receiveCCData"})
   public void receiveCCData(HttpServletRequest request, HttpServletResponse response) throws Exception {
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       
       System.out.println("responseStrBuilder===>>>>" + responseStrBuilder.toString());
       
       String name = newzs.getRandomCharAndNumr(Integer.valueOf(10));
       String fileName = "D:\\ywcc\\" + name + ".xml";
       saveFile(fileName, responseStrBuilder.toString());
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     
     String ret = "SUCCESS";
     response.getWriter().write(ret);
   }
   
   @RequestMapping({"/receiveEwtpoutData"})
   public void receiveEwtpoutData(HttpServletRequest request, HttpServletResponse response) throws Exception {
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       
       String name = newzs.getRandomCharAndNumr(Integer.valueOf(10));
       String fileName = "C:\\xml\\hz\\" + name + ".xml";
       saveFile(fileName, responseStrBuilder.toString());
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     
     JSONObject orderJson = new JSONObject();
     orderJson.put("status", "200");
     orderJson.put("msg", "报文接收成功");
 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   @RequestMapping({"/CreateOrder"})
   public void CreateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String resout3 = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======响应订单数据别样==========" + data);
 
 
 
       
       String result = this.szqgOrderService.CreateOrder(URLDecoder.decode(data));
 
       
       if (result.equals("0")) {
         orderJson.put("status", "0");
         orderJson.put("msg", "9610订单创建成功");
         String url = "http://59.111.89.9:8066/hcerp/receiveEwtpData/CreateOrder1.do";
         resout3 = PushtoWQ.sendPost2(url, data);
         Tools.fileLog("=======响应订单数据海仓==========" + resout3);
       } else if (result.equals("10")) {
         orderJson.put("status", "0");
         orderJson.put("msg", "B类订单创建成功");
       } else {
         orderJson.put("status", "1");
         orderJson.put("msg", result);
       }
     
     } catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   @RequestMapping({"/CreateBsOrder"})
   public void CreateBsOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String ret = "SUCCESS";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======佳贝购响应EWTP订单数据==========" + data);
       String name = newzs.getRandomCharAndNumr(Integer.valueOf(10));
       String na = "D:\\kcdd\\" + name + ".xml";
       
       ReceiveHghzDataServiceImpl.saveFile(na, data);
       
       orderJson.put("success", "true");
       orderJson.put("message", "");
       orderJson.put("code", "200");
       orderJson.put("time", "20210414102608");
     } catch (Exception e) {
       e.printStackTrace();
     } 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   @RequestMapping({"/CreateItem"})
   public void CreateItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String resout3 = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======响应商品数据别样==========" + data);
       
       String url = "http://59.111.89.9:8066/hcerp/receiveEwtpData/CreateItem1.do";
       resout3 = PushtoWQ.sendPost2(url, data);
       this.szqgItemService.CreateItem(URLDecoder.decode(data));
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(resout3);
   }
   @RequestMapping({"/GetMailno"})
   public void GetMailno(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String result = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       
       System.out.println("responseStrBuilder===>>>>" + URLDecoder.decode(responseStrBuilder.toString()));
 
 
 
       
       result = this.szqgOrderService.GetMailno(URLDecoder.decode(responseStrBuilder.toString()));
 
 
 
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(result);
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
   
   @RequestMapping({"/YwjbgItem"})
   public void YwjbgItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String resout3 = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======佳贝购响应账册数据==========" + data);
       
       String result = this.itemService.CreateItem(URLDecoder.decode(data));
       
       if (result.equals("0")) {
         orderJson.put("status", "0");
         orderJson.put("msg", "账册数据创建成功");
       } else {
         orderJson.put("status", "1");
         orderJson.put("msg", result);
       }
     
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   
   @RequestMapping({"/YwjbgInventory"})
   public void YwjbgInventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String resout3 = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======佳贝购响应库存数据==========" + data);
       
       String result = this.tinventoryService.CreateInventory(URLDecoder.decode(data));
       orderJson.put("status", "0");
       orderJson.put("msg", "库存数据创建成功");
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   @RequestMapping({"/YwjwlReceipt"})
   public void YwjwlReceipt(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String resout3 = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======佳贝购响应物流数据==========" + data);
       
       JSONObject hz = JSONObject.parseObject(data);
       String orderNo = hz.get("orderNo").toString();
       String bagNo = hz.get("bagNo").toString();
       
       this.orderBondedDao.updateAtoB(" auditstatus='2' ,mailno='" + bagNo + "' ", " txlogisticid = '" + orderNo + "'");
       
       orderJson.put("status", "0");
       orderJson.put("msg", "物流信息已回填");
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   
   @RequestMapping({"/YwjwlReceipt2"})
   public void YwjwlReceipt2(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String resout3 = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======佳贝购响应物流数据2==========" + data);
 
       
       JSONObject hz = JSONObject.parseObject(data);
       String orderNo = hz.get("orderNo").toString();
       String bagNo = hz.get("bagNo").toString();
       String bagCode = hz.get("bagCode").toString();
       String bagName = hz.get("bagName").toString();
       String List = hz.getString("skuList");
       JSONArray skuList = JSONArray.parseArray(List);
       List<OrderBondedSku> obsList = new ArrayList<>();
       for (int i = 0; i < skuList.size(); i++) {
         JSONObject orderskuJSONObject = skuList.getJSONObject(i);
         String itemsku = orderskuJSONObject.getString("itemsku");
         String itemName = orderskuJSONObject.getString("itemName");
         String itemWeight = orderskuJSONObject.getString("itemWeight");
         String itemCount = orderskuJSONObject.getString("itemCount");
         String unitPrice = orderskuJSONObject.getString("unitPrice");
         String allPrice = orderskuJSONObject.getString("allPrice");
         String internalNumber = orderskuJSONObject.getString("internalNumber");
         String itemNo = orderskuJSONObject.getString("itemNo");
         String gcode = orderskuJSONObject.getString("gcode");
         String hsCode = orderskuJSONObject.getString("hsCode");
         
         String gmodel = orderskuJSONObject.getString("gmodel");
         String barCode = orderskuJSONObject.getString("barCode");
         String country = orderskuJSONObject.getString("country");
         String unit1 = orderskuJSONObject.getString("unit1");
         String unit = orderskuJSONObject.getString("unit");
         String unit2 = orderskuJSONObject.getString("unit2");
         String qty1 = orderskuJSONObject.getString("qty1");
         String qty2 = orderskuJSONObject.getString("qty2");
         
         OrderBondedSku orderBondedSku = new OrderBondedSku();
         orderBondedSku.setTxLogisticID(orderNo);
         orderBondedSku.setItemsku(itemsku);
         orderBondedSku.setItemName(itemName);
         orderBondedSku.setItemWeight(Double.valueOf(itemWeight));
         orderBondedSku.setItemCount(Integer.valueOf(Integer.parseInt(itemCount)));
         orderBondedSku.setUnitPrice(Double.valueOf(unitPrice));
         orderBondedSku.setAllPrice(Double.valueOf(allPrice));
         orderBondedSku.setInternalNumber(Integer.parseInt(internalNumber));
         
         orderBondedSku.setItemNo(itemNo);
         orderBondedSku.setGcode(gcode);
         orderBondedSku.setHsCode(hsCode);
         orderBondedSku.setGmodel(gmodel);
         orderBondedSku.setBarCode(barCode);
         orderBondedSku.setCountry(country);
         orderBondedSku.setUnit(unit);
         orderBondedSku.setUnit1(unit1);
         orderBondedSku.setUnit2(unit2);
         orderBondedSku.setQty1(qty1);
         orderBondedSku.setQty2(qty2);
         obsList.add(orderBondedSku);
       } 
       this.orderBondedSkuDao.delete(orderNo);
       int count = this.orderBondedSkuDao.batchInsert(obsList);
       
       this.orderBondedDao.updateAtoB(" ispost='1', billProvideSiteName='" + bagName + "',billProvideSiteCode='" + bagCode + "' ,auditstatus='2',isPushQd='0' ,mailno='" + bagNo + "' ", " txlogisticid = '" + orderNo + "'");
       
       orderJson.put("status", "0");
       orderJson.put("msg", "物流信息已回填");
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(orderJson.toJSONString());
   }
   
   @RequestMapping({"/YwjbgReceipt"})
   public void YwjbgReceipt(HttpServletRequest request, HttpServletResponse response) throws Exception {
     JSONObject orderJson = new JSONObject();
     String resout3 = "";
     try {
       BufferedReader streamReader = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream(), "UTF-8"));
       StringBuilder responseStrBuilder = new StringBuilder();
       String inputStr;
       while ((inputStr = streamReader.readLine()) != null) {
         responseStrBuilder.append(inputStr);
       }
       String data = responseStrBuilder.toString();
       System.out.println("responseStrBuilder===>>>>" + data);
       
       data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
       data = data.replaceAll("\\+", "%2B");
       System.out.println("responseStrBuilder===>>>>" + data);
       
       Tools.fileLog("=======佳贝购响应回执数据==========" + data);
 
       
       orderJson.put("status", "0");
       orderJson.put("msg", "订单状态已变更");
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
 
     
     response.getWriter().write(orderJson.toJSONString());
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


