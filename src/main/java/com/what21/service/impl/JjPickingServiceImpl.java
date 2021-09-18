 package com.what21.service.impl;
 
 import com.what21.dao.JjPickingDao;
 import com.what21.dao.JjPickingSkuDao;
 import com.what21.model.JjPicking;
 import com.what21.model.JjPickingCustom;
 import com.what21.model.JjPickingSku;
 import com.what21.model.JjPickingVo;
 import com.what21.service.JjPickingService;
 import com.what21.util.ExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class JjPickingServiceImpl
   implements JjPickingService
 {
   @Autowired
   private JjPickingDao jjPickingDao;
   @Autowired
   private JjPickingSkuDao jjPickingSkuDao;
   
   public int count(JjPickingVo jjPickingVo) {
     return this.jjPickingDao.count(jjPickingVo);
   }
 
 
   
   public List<JjPicking> findAll(JjPickingVo jjPickingVo) {
     return this.jjPickingDao.findAll(jjPickingVo);
   }
 
 
   
   public int distributeLeaflets(JjPickingCustom jjPickingCustom) {
     this.jjPickingDao.distributeLeaflets(jjPickingCustom);
     return 1;
   }
   
   public GeneralResult importDistributeLeaflets(String string) {
     Long l1 = Long.valueOf(System.currentTimeMillis());
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> map = ExcelUtil.read2(string);
     Map<String, String> pickMap = new HashMap<>();
     List<JjPicking> list = new ArrayList<>();
     List<JjPickingSku> Skulist = new ArrayList<>();
     Map<String, String> pickMap1 = new HashMap<>();
     
     Map<String, Integer> count = new HashMap<>();
     
     Map<String, String> billMap = new HashMap<>();
     List<JjPicking> PickingList = this.jjPickingDao.findPick();
     List<JjPickingSku> PickingSkuList = this.jjPickingSkuDao.findBill(); int i;
     for (i = 0; i < PickingList.size(); i++) {
       pickMap.put(((JjPicking)PickingList.get(i)).getPick(), ((JjPicking)PickingList.get(i)).getPick());
     }
     for (i = 0; i < PickingSkuList.size(); i++) {
       billMap.put(((JjPickingSku)PickingSkuList.get(i)).getBill(), ((JjPickingSku)PickingSkuList.get(i)).getBill());
     }
     
     StringBuffer message = new StringBuffer();
     File delFile = new File(string);
     if (delFile.exists()) {
       delFile.delete();
     }
     int total = map.size();
     try {
       for (Map.Entry<Integer, Object[]> me : map.entrySet()) {
         Object[] nowRowData = me.getValue();
         int rowNum = ((Integer)me.getKey()).intValue() + 1;
         String pick = null;
         if (nowRowData[0] == null || StringUtils.isEmpty(nowRowData[0].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>拣货单号</font>没有输入！<br>");
           break;
         } 
         pick = nowRowData[0].toString();
         String bill = null;
         if (nowRowData[1] == null || StringUtils.isEmpty(nowRowData[1].toString().trim())) {
           message.append("导入第" + rowNum + "行数据失败，失败原因：<font color='red'>物流单号</font>没有输入！<br>");
           break;
         } 
         bill = nowRowData[1].toString();
 
         
         JjPicking jjPicking = new JjPicking();
         jjPicking.setPick(pick);
         count.put(pick, Integer.valueOf(count.containsKey(pick) ? (((Integer)count.get(pick)).intValue() + 1) : 1));
         jjPicking.setPicksum(count.get(pick));
 
         
         String pick1 = pickMap1.get(pick);
         if (pick1 == null) {
           list.add(jjPicking);
           pickMap1.put(pick, pick);
         } else {
           for (int j = 0; j < list.size(); j++) {
             if (((JjPicking)list.get(j)).getPick().equals(pick1)) {
               list.set(j, jjPicking);
             }
           } 
         } 
         JjPickingSku jjPickingsku = new JjPickingSku();
         jjPickingsku.setBill(bill);
         jjPickingsku.setPick(pick);
         Skulist.add(jjPickingsku);
       } 
       if (!message.toString().contains("导入")) {
         for (JjPicking jjPicking : list) {
           String pick = pickMap.get(jjPicking.getPick());
           if (pick != null) {
             message.append("导入失败,已存在拣货单号为：<font color=red>" + jjPicking.getPick() + "</font>的单号！");
             result.setMessage(message.toString());
             return result;
           } 
         } 
         for (JjPickingSku jjPickingsku : Skulist) {
           String bill = pickMap.get(jjPickingsku.getBill());
           if (bill != null) {
             message.append("导入失败,已存在物流单号为：<font color=red>" + jjPickingsku.getBill() + "</font>的单号！");
             result.setMessage(message.toString());
             return result;
           } 
           pickMap.put(jjPickingsku.getBill(), jjPickingsku.getBill());
         } 
         this.jjPickingDao.insertBatch(list);
         this.jjPickingSkuDao.insertBatchSku(Skulist);
         message.append("导入成功" + total + "条！");
       } 
       result.setMessage(message.toString());
       System.out.println(System.currentTimeMillis() - l1.longValue());
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
     } 
     return result;
   }
 }


