 package com.what21.service.impl;
 
 import com.what21.dao.YTOBillDao;
 import com.what21.dao.YTOBillSkuDao;
 import com.what21.model.YTOBillSku;
 import com.what21.model.YtoBill;
 import com.what21.model.YtoBillEx;
 import com.what21.service.YTOBillService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import com.what21.util.ItemExcelUtil;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class YTOBillServiceImpl
   implements YTOBillService
 {
   @Autowired
   private YTOBillDao dao;
   @Autowired
   private YTOBillSkuDao sdao;
   
   public int insertYTO(YtoBill yto) {
     return this.dao.insertYTO(yto);
   }
 
 
   
   public List<YtoBill> findAllOfYTO(Map<String, Object> paramMap) {
     return this.dao.findAllOfYTO(paramMap);
   }
 
 
   
   public int delete(int[] ids) {
     return this.dao.delete(ids);
   }
 
 
   
   public int updateByID(YtoBill yto) {
     return this.dao.updateByID(yto);
   }
 
 
   
   public int count(Map<String, Object> pageMap) {
     return this.dao.count(pageMap);
   }
 
 
   
   public List<YtoBill> findByWaybillNo(Map<String, Object> map) {
     return this.dao.findByWaybillNo(map);
   }
 
 
   
   public List<YtoBill> findById(int[] ids) {
     return this.dao.findById(ids);
   }
 
 
   
   public GeneralResult importOrder(String path, int userId, String is) throws Exception {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = ItemExcelUtil.read(path);
     Map<String, YtoBill> map = new HashMap<>();
     List<YtoBill> list1 = new ArrayList<>();
     List<YTOBillSku> list2 = new ArrayList<>();
     List<YtoBill> list3 = new ArrayList<>();
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     int i = 0;
     int k = 0;
     
     StringBuffer message = new StringBuffer();
     
     int totalData = items.entrySet().size();
     
     String now = Tools.format("yyyy-MM-dd HH:mm:ss", Tools.getCurrentTime());
     try {
       for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
 
 
 
         
         int l = 1;
         System.out.println(l);
         Object[] nowRowData = entry.getValue();
         String waybillNo = "";
         String orderNo = "";
         int row = ((Integer)entry.getKey()).intValue() + 1;
         Object data1 = nowRowData[0];
         if (data1 == null || StringUtils.isEmpty(data1.toString().trim())) {
           message.append("导入第" + row + "行数据失败，失败原因：运单号没有输入！<br>");
           continue;
         } 
         waybillNo = data1.toString().trim();
         
         Object data2 = nowRowData[1];
         if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
           data2 = "";
         }
         YtoBill yto = new YtoBill();
         yto.setOrderNo(data2.toString().trim());
         orderNo = data2.toString().trim();
 
         
         Object data3 = nowRowData[2];
         if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
           data3 = "";
         }
         Object data4 = nowRowData[3];
         if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
           data4 = "";
         }
         Object data5 = nowRowData[4];
         if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
           data5 = "";
         }
         Object data6 = nowRowData[5];
         Object data7 = nowRowData[6];
         Object data8 = nowRowData[7];
         Object data9 = nowRowData[8];
         if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
           data6 = "";
         }
         if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
           data7 = "";
         }
         if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
           data8 = "";
         }
         try {
           if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
             message.append("导入第" + (row - 1) + "行数据失败，失败原因：发货时间没有输入！<br>");
             continue;
           } 
         } catch (Exception e) {
           
           e.printStackTrace();
           result.setMessage("读取Excel文件失败！第" + l + "行时间格式错误，请更正后重新导入");
           return result;
         } 
         Object data10 = nowRowData[9];
         if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
           message.append("导入第" + row + "行数据失败，失败原因：走件CODE没有输入！<br>");
           continue;
         } 
         Object data11 = nowRowData[10];
         if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
           message.append("导入第" + row + "行数据失败，失败原因：走件详情没有输入！<br>");
           continue;
         } 
         Object data12 = nowRowData[11];
         Object data13 = nowRowData[12];
         Object data14 = nowRowData[13];
         Object data15 = nowRowData[14];
         Object data16 = nowRowData[15];
         Object data17 = nowRowData[16];
         if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
           data12 = "";
         }
         if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
           data13 = "";
         }
         if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
           data14 = "";
         }
         if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
           data15 = "";
         }
         if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
           data16 = "";
         }
         try {
           if (data17 == null || StringUtils.isEmpty(data17.toString().trim())) {
             message.append("导入第" + row + "行数据失败，失败原因：发生时间没有输入！<br>");
             continue;
           } 
         } catch (Exception e) {
           
           e.printStackTrace();
           result.setMessage("读取Excel文件失败！第" + l + "行时间格式错误，请更正后重新导入");
           return result;
         } 
         
         YtoBill yt = new YtoBill();
         yt.setWaybillNo(data1.toString().trim());
         yt.setOrderNo(data2.toString().trim());
         yt.setShipperName(data3.toString().trim());
         yt.setShipperTel(data4.toString().trim());
         yt.setShipperAddress(data5.toString().trim());
         yt.setConsigneeName(data6.toString().trim());
         yt.setConsigneeTel(data7.toString().trim());
         yt.setConsigneeAddress(data8.toString().trim());
         yt.setDeliveryTime(data9.toString().trim());
         yt.setCreateTime(now);
         yt.setIsSigned(is);
         list3.add(yt);
 
         
         YTOBillSku ys = new YTOBillSku();
         ys.setWaybillNo(data1.toString().trim());
         ys.setOrderNo(data2.toString().trim());
         ys.setEventCode(data10.toString().trim());
         ys.setEventDetail(data11.toString().trim());
         ys.setEventLocation(data12.toString().trim());
         ys.setEventOperater(data13.toString().trim());
         ys.setEventOperaterPhone(data14.toString().trim());
         ys.setCity(data15.toString().trim());
         ys.setNextCity(data16.toString().trim());
         ys.setEventTime(data17.toString().trim());
         ys.setCreateTime(now);
         ys.setIsPushed(Integer.valueOf(0));
         YtoBill y = new YtoBill();
         y.setWaybillNo(waybillNo);
         y.setIsSigned(is);
 
 
 
         
         l++;
         list2.add(ys);
       } 
       for (YtoBill ytoBill : list3) {
         map.put(ytoBill.getWaybillNo(), ytoBill);
       }
       for (String key : map.keySet()) {
         list1.add(map.get(key));
       }
       
       if (message.length() != 0) {
         StringBuffer stringBuffer = new StringBuffer();
         stringBuffer.append("总共" + totalData + "条数据<br/>");
         stringBuffer.append(message);
         result.setMessage(stringBuffer.toString());
         return result;
       } 
       if (list1.size() > 0) {
         this.dao.insertYtoBatch(list1);
       }
       this.sdao.insertSkuBatch(list2);
       StringBuffer resultMessage = new StringBuffer();
       resultMessage.append("总共" + totalData + "条数据，导入成功<br/>");
       resultMessage.append(message);
       result.setMessage(resultMessage.toString());
       return result;
     }
     catch (Exception e) {
       
       e.printStackTrace();
       result.setMessage("读取Excel文件失败！");
       return result;
     } 
   }
 
 
   
   public List<YtoBill> findByOrderNo(YtoBill yto) {
     return this.dao.findByOrderNo(yto);
   }
 
 
   
   public int updateForIs(YtoBill yto) {
     return this.dao.updateForIs(yto);
   }
 
 
   
   public List<YtoBillEx> leftJoin(int[] ids) {
     return this.dao.leftJoin(ids);
   }
 
 
   
   public List<YtoBill> findByWay_1(Map<String, Object> map) {
     return this.dao.findByWay_1(map);
   }
 
 
   
   public void insertYtoBatch(List<YtoBill> list) {
     this.dao.insertYtoBatch(list);
   }
 
 
   
   public int deleteAll(String isSigned) {
     return this.dao.deleteAll(isSigned);
   }
 
 
   
   public List<YtoBillEx> findExport() {
     return this.dao.findExport();
   }
 
 
   
   public List<YtoBill> findByWaybillNo_createTime(Map<String, Object> map) {
     return this.dao.findByWaybillNo_createTime(map);
   }
 
 
   
   public List<YtoBill> findByWaybillNo_deliveryTime(Map<String, Object> map) {
     return this.dao.findByWaybillNo_deliveryTime(map);
   }
 
 
   
   public List<YTOBillSku> findByIdsForSku(List<YtoBill> list) {
     return this.dao.findByIdsForSku(list);
   }
 }


