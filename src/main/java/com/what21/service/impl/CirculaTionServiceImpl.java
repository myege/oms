 package com.what21.service.impl;
 
 import com.what21.dao.CirculaTionDao;
 import com.what21.model.CirculaTion;
 import com.what21.service.CirculaTionService;
 import com.what21.util.BondedExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class CirculaTionServiceImpl
   implements CirculaTionService
 {
   @Autowired
   private CirculaTionDao circulaTionDao;
   
   public int count(Map<String, Object> pageMap) {
     return this.circulaTionDao.count(pageMap);
   }
 
   
   public List<CirculaTion> findAll(Map<String, Object> pageMap) {
     return this.circulaTionDao.findAll(pageMap);
   }
 
   
   public int inserta(CirculaTion circulaTion) {
     return this.circulaTionDao.inserta(circulaTion);
   }
 
   
   public int deletecir(String id) {
     return this.circulaTionDao.deletecir(id);
   }
 
   
   public GeneralResult incir(String path, int userId) {
     GeneralResult result = new GeneralResult();
     Map<Integer, Object[]> items = BondedExcelUtil.read(path);
     boolean firstRow = true;
     StringBuffer message = new StringBuffer();
     int successData = 0;
     int totalData = items.entrySet().size() - 1;
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       if (firstRow) {
         firstRow = false;
         
         continue;
       } 
       Object[] nowRowData = entry.getValue();
       Object data1 = nowRowData[0];
       String id = "";
       if (data1 != null && StringUtils.isNotEmpty(data1.toString().trim())) {
         id = data1.toString();
       } else {
         message.append("上传数据失败，失败原因：出入库记录流水号没有输入！<br>");
         continue;
       } 
       Object data2 = nowRowData[1];
       if (data2 == null || StringUtils.isEmpty(data2.toString().trim())) {
         message.append("上传数据失败，失败原因：出入库记录编号没有输入！<br>");
         continue;
       } 
       Object data3 = nowRowData[2];
       if (data3 == null || StringUtils.isEmpty(data3.toString().trim())) {
         message.append("上传数据失败，失败原因：账册编号没有输入！<br>");
         continue;
       } 
       Object data4 = nowRowData[3];
       if (data4 == null || StringUtils.isEmpty(data4.toString().trim())) {
         message.append("上传数据失败，失败原因：料号没有输入！<br>");
         continue;
       } 
       Object data5 = nowRowData[4];
       if (data5 == null || StringUtils.isEmpty(data5.toString().trim())) {
         message.append("上传数据失败，失败原因：料件性质没有输入！<br>");
         continue;
       } 
       Object data6 = nowRowData[5];
       if (data6 == null || StringUtils.isEmpty(data6.toString().trim())) {
         message.append("上传数据失败，失败原因：出入库标志没有输入！<br>");
         
         continue;
       } 
       Object data7 = nowRowData[6];
       if (data7 == null || StringUtils.isEmpty(data7.toString().trim())) {
         message.append("上传数据失败，失败原因：出入库数量没有输入！<br>");
         continue;
       } 
       Object data8 = nowRowData[7];
       if (data8 == null || StringUtils.isEmpty(data8.toString().trim())) {
         message.append("上传数据失败，失败原因：出入库时间没有输入！<br>");
         continue;
       } 
       Object data9 = nowRowData[8];
       if (data9 == null || StringUtils.isEmpty(data9.toString().trim())) {
         message.append("上传数据失败，失败原因：出入库时间没有输入！<br>");
         continue;
       } 
       Object data10 = nowRowData[9];
       if (data10 == null || StringUtils.isEmpty(data10.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data11 = nowRowData[10];
       if (data11 == null || StringUtils.isEmpty(data11.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data12 = nowRowData[11];
       if (data12 == null || StringUtils.isEmpty(data12.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data13 = nowRowData[12];
       if (data13 == null || StringUtils.isEmpty(data13.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data14 = nowRowData[13];
       if (data14 == null || StringUtils.isEmpty(data14.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data15 = nowRowData[14];
       if (data15 == null || StringUtils.isEmpty(data15.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data16 = nowRowData[15];
       if (data16 == null || StringUtils.isEmpty(data16.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data17 = nowRowData[16];
       if (data17 == null || StringUtils.isEmpty(data17.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data18 = nowRowData[17];
       if (data18 == null || StringUtils.isEmpty(data18.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data19 = nowRowData[18];
       if (data19 == null || StringUtils.isEmpty(data19.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data20 = nowRowData[19];
       if (data20 == null || StringUtils.isEmpty(data20.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data21 = nowRowData[20];
       if (data21 == null || StringUtils.isEmpty(data21.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data22 = nowRowData[21];
       if (data22 == null || StringUtils.isEmpty(data22.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data23 = nowRowData[22];
       if (data23 == null || StringUtils.isEmpty(data23.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data24 = nowRowData[23];
       if (data24 == null || StringUtils.isEmpty(data24.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data25 = nowRowData[24];
       if (data25 == null || StringUtils.isEmpty(data25.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         continue;
       } 
       Object data26 = nowRowData[25];
       if (data26 == null || StringUtils.isEmpty(data26.toString().trim())) {
         message.append("上传数据失败，失败原因：核放单编号没有输入！<br>");
         
         continue;
       } 
       
       CirculaTion isData = this.circulaTionDao.findBycir(id);
       if (isData == null) {
         CirculaTion ct = new CirculaTion();
         ct.getSj_preEntry();
         ct.setAfleet(data7.toString().replaceAll(" ", ""));
         ct.setAfter(data7.toString().replaceAll(" ", ""));
         ct.setAgo(data7.toString().replaceAll(" ", ""));
         ct.setAport(data7.toString().replaceAll(" ", ""));
         ct.setArrange(data7.toString().replaceAll(" ", ""));
         ct.setBarter(data7.toString().replaceAll(" ", ""));
         
         ct.setConsumer(data7.toString().replaceAll(" ", ""));
         ct.setDnumber(data7.toString().replaceAll(" ", ""));
         ct.setInout_jh(data7.toString().replaceAll(" ", ""));
         ct.setList_zh(data7.toString().replaceAll(" ", ""));
         ct.setLockzg(data7.toString().replaceAll(" ", ""));
         ct.setNotice(data7.toString().replaceAll(" ", ""));
         ct.setNumber(Integer.parseInt(data7.toString().replaceAll(" ", "")));
         ct.setPacking(data7.toString().replaceAll(" ", ""));
         ct.setRemark(data7.toString().replaceAll(" ", ""));
         
         ct.setSkunumber(data7.toString().replaceAll(" ", ""));
         ct.setTransit(data7.toString().replaceAll(" ", ""));
         ct.setTrmode(data7.toString().replaceAll(" ", ""));
         ct.setVehicletype(data7.toString().replaceAll(" ", ""));
         ct.setWare(data7.toString().replaceAll(" ", ""));
         ct.setWeight(data7.toString().replaceAll(" ", ""));
         ct.setWnumber(data7.toString().replaceAll(" ", ""));
         
         SimpleDateFormat dnf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         ct.setSj_pass(dnf.format(new Date()));
         ct.setSj_preEntry(dnf.format(new Date()));
         ct.setSj_preEntry(dnf.format(new Date()));
         ct.setBg_cargo(dnf.format(new Date()));
         ct.setBg_pass(dnf.format(new Date()));
         ct.setBg_pass(dnf.format(new Date()));
         ct.setBg_preEntry(dnf.format(new Date()));
         ct.setBg_sbjd(dnf.format(new Date()));
         ct.setBg_tally(dnf.format(new Date()));
         
         this.circulaTionDao.incir(ct);
       } 
       
       File delFile = new File(path);
       if (delFile.exists()) {
         delFile.delete();
       }
       successData++;
     } 
     StringBuffer resultMessage = new StringBuffer();
     resultMessage.append("总共" + totalData + "条数据，导入成功" + successData + "条!<br>");
     resultMessage.append(message);
     result.setMessage(resultMessage.toString());
     return result;
   }
 
   
   public int insertc(CirculaTion circulaTion) {
     return this.circulaTionDao.insertc(circulaTion);
   }
 
   
   public int insertb(CirculaTion circulaTion) {
     return this.circulaTionDao.insertb(circulaTion);
   }
 
   
   public List<CirculaTion> findByBill(String id) {
     return this.circulaTionDao.findByBill(id);
   }
 
   
   public List<CirculaTion> findcirAll(Map<String, Object> pageMap) {
     return this.circulaTionDao.findcirAll(pageMap);
   }
 
   
   public int insertall(CirculaTion u) {
     return this.circulaTionDao.insertall(u);
   }
 
   
   public int insertqd(CirculaTion u) {
     return this.circulaTionDao.insertqd(u);
   }
 
   
   public int upcirsb(CirculaTion circulaTion) {
     return this.circulaTionDao.upcirsb(circulaTion);
   }
 
   
   public int insertaa(CirculaTion circulaTion) {
     return this.circulaTionDao.insertaa(circulaTion);
   }
 
   
   public int insciraa(CirculaTion circulaTion) {
     return this.circulaTionDao.insciraa(circulaTion);
   }
 }


