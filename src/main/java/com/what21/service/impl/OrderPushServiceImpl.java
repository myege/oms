 package com.what21.service.impl;
 
 import com.what21.dao.GoodsPushDao;
 import com.what21.dao.OrderPushDao;
 import com.what21.model.GoodsPush;
 import com.what21.model.OrderPush;
 import com.what21.service.OrderPushService;
 import com.what21.util.ExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.math.BigDecimal;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OrderPushServiceImpl
   implements OrderPushService
 {
   @Autowired
   private OrderPushDao orderPushDao;
   @Autowired
   private GoodsPushDao goodsPushDao;
   
   public Boolean findByExpressNumber(String expressNumber) {
     OrderPush order = this.orderPushDao.findByExpressNumber(expressNumber);
     if (order != null) {
       return Boolean.valueOf(true);
     }
     return Boolean.valueOf(false);
   }
 
   
   public void insert(OrderPush order) {
     this.orderPushDao.insert(order);
   }
 
   
   public List<OrderPush> findAll(Map<String, Object> map) {
     return this.orderPushDao.findAll(map);
   }
 
   
   public int count(int userId) {
     return this.orderPushDao.count(userId);
   }
 
   
   public OrderPush findById(int id) {
     return this.orderPushDao.findById(id);
   }
 
   
   public List<OrderPush> findByPushType(int pushType) {
     return this.orderPushDao.findByPushType(pushType);
   }
 
   
   public void update(String expressNumber) {
     this.orderPushDao.update(expressNumber);
   }
 
 
   
   public String push(OrderPush order, List<GoodsPush> goodsList) {
     return "";
   }
 
   
   public GeneralResult importOrder(String path, int userId) {
     GeneralResult result = new GeneralResult();
 
     
     Map<Integer, Object[]> items = ExcelUtil.read(path);
     boolean firstRow = true;
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       
       Object data1 = nowRowData[0];
       
       Object data2 = nowRowData[1];
       String expressNumber = data2.toString();
       
       Object data3 = nowRowData[2];
       Object data4 = nowRowData[3];
       Object data5 = nowRowData[4];
       Object data6 = nowRowData[5];
       Object data7 = nowRowData[6];
       Object data8 = nowRowData[7];
       Object data9 = nowRowData[8];
       Object data10 = nowRowData[9];
       Object data11 = nowRowData[10];
       Object data12 = nowRowData[11];
 
 
       
       OrderPush isData = this.orderPushDao.findByExpressNumber(expressNumber);
       if (isData == null) {
         OrderPush order = new OrderPush();
         if (data1 != null) {
           order.setExpressCode(data1.toString());
         }
         if (data2 != null) {
           order.setExpressNumber(data2.toString());
         }
         if (data3 != null) {
           order.setBuyerNickName(data3.toString());
         }
         if (data4 != null) {
           order.setBuyerName(data4.toString());
         }
         if (data5 != null) {
           order.setBuyerIdCard(data5.toString());
         }
         if (data6 != null) {
           order.setBuyerProvince(data6.toString());
         }
         if (data7 != null) {
           order.setBuyerCity(data7.toString());
         }
         if (data8 != null) {
           order.setBuyerArea(data8.toString());
         }
         if (data9 != null) {
           order.setBuyerAddress(data9.toString());
         }
         if (data10 != null) {
           order.setBuyerPostCode(data10.toString());
         }
         if (data11 != null) {
           order.setBuyerCountry(data11.toString());
         }
         if (data12 != null) {
           order.setBuyerTel(data12.toString());
         }
         order.setPushType(0);
         order.setUserId(userId);
         this.orderPushDao.insert(order);
       } 
       Object data13 = nowRowData[12];
       Object data14 = nowRowData[13];
       Object data15 = nowRowData[14];
       Object data16 = nowRowData[15];
       Object data17 = nowRowData[16];
       Object data18 = nowRowData[17];
       Object data19 = nowRowData[18];
       Object data20 = nowRowData[19];
       Object data21 = nowRowData[20];
       Object data22 = nowRowData[21];
       Object data23 = nowRowData[22];
       Object data24 = nowRowData[23];
       GoodsPush goods = new GoodsPush();
       if (data13 != null) {
         goods.setWeight(Double.valueOf(data13.toString()));
       }
       if (data14 != null) {
         goods.setGoodsName(data14.toString());
       }
       if (data15 != null) {
         goods.setGoodsType(data15.toString());
       }
       if (data16 != null) {
         goods.setPrice(BigDecimal.valueOf(Double.valueOf(data16.toString()).doubleValue()));
       }
       if (data17 != null) {
         goods.setNum(Integer.valueOf(data17.toString()).intValue());
       }
       if (data18 != null) {
         goods.setTotalPrice(BigDecimal.valueOf(Double.valueOf(data18.toString()).doubleValue()));
       }
       if (data19 != null) {
         goods.setGoodsSpec(data19.toString());
       }
       if (data20 != null) {
         goods.setGoodsUnit(data20.toString());
       }
       if (data21 != null) {
         goods.setGoodsDutyNumber(data21.toString());
       }
       if (data22 != null) {
         goods.setGoodsHS(data22.toString());
       }
       if (data23 != null) {
         goods.setFromCountryCode(data23.toString());
       }
       if (data24 != null) {
         goods.setGoodsSKU(data24.toString());
       }
       goods.setExpressNumber(expressNumber);
       goods.setUserId(userId);
       this.goodsPushDao.insert(goods);
     } 
     
     File delFile = new File(path);
     if (delFile.exists()) {
       delFile.delete();
     }
     
     result.setCode(Integer.valueOf(1));
     result.setMessage("导入订单成功");
     return result;
   }
 }


