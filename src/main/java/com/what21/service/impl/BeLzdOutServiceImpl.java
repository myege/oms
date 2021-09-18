 package com.what21.service.impl;
 
 import com.what21.dao.BeLzdOutDao;
 import com.what21.model.BeLzdOut;
 import com.what21.model.BeLzdOutCustom;
 import com.what21.model.BeLzdOutQueryVo;
 import com.what21.model.CountBeLzdOut;
 import com.what21.result.ResultInfo;
 import com.what21.service.BeLzdOutService;
 import com.what21.tools.Tools;
 import com.what21.util.BeLzdOutExcelUtil;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.math.RoundingMode;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Transactional
 @Service
 public class BeLzdOutServiceImpl
   implements BeLzdOutService
 {
   @Autowired
   private BeLzdOutDao beLzdOutDao;
   
   public List<BeLzdOut> findAll(BeLzdOutQueryVo beLzdOutQueryVo) throws Exception {
     return this.beLzdOutDao.findAll(beLzdOutQueryVo);
   }
 
   
   public int countAll(BeLzdOutQueryVo beLzdOutQueryVo) throws Exception {
     return this.beLzdOutDao.countAll(beLzdOutQueryVo);
   }
 
   
   public ResultInfo update(BeLzdOut beLzdOut) throws Exception {
     BeLzdOut res = this.beLzdOutDao.findByTydh(beLzdOut.getTdh());
     if (beLzdOut != null && res.getId().intValue() != beLzdOut.getId().intValue()) {
       return new ResultInfo(0, "提单号在出口流转单中已存在!");
     }
     if (beLzdOut.getTdjs() == null) {
       beLzdOut.setTdjs(Integer.valueOf(0));
     }
     if (beLzdOut.getMdjs() == null) {
       beLzdOut.setMdjs(Integer.valueOf(0));
     }
     if (beLzdOut.getZl() == null) {
       beLzdOut.setZl("0");
     }
     if (beLzdOut.getSbje() == null) {
       beLzdOut.setSbje("0");
     }
     beLzdOut.setCreatetime(this.beLzdOutDao.findById(beLzdOut.getId()).getCreatetime());
     this.beLzdOutDao.update(beLzdOut);
     return new ResultInfo(1, "修改成功!");
   }
 
   
   public BeLzdOut findById(Integer id) throws Exception {
     return this.beLzdOutDao.findById(id);
   }
 
 
 
   
   public ResultInfo insert(BeLzdOutQueryVo beLzdOutQueryVo) throws Exception {
     try {
       BeLzdOutCustom beLzdOutCustom = beLzdOutQueryVo.getBeLzdOutCustom();
       String tdh = beLzdOutCustom.getTdh();
       if (tdh == null) {
         return new ResultInfo(0, "提单号必须填写!");
       }
       BeLzdOut beLzdOut = this.beLzdOutDao.findByTydh(tdh);
       if (beLzdOut != null) {
         return new ResultInfo(0, "提单号在出口流转单中已存在!");
       }
 
       
       if (beLzdOutCustom.getTdjs() == null) {
         beLzdOutCustom.setTdjs(Integer.valueOf(0));
       }
       if (beLzdOutCustom.getMdjs() == null) {
         beLzdOutCustom.setMdjs(Integer.valueOf(0));
       }
       if (beLzdOutCustom.getZl() == null) {
         beLzdOutCustom.setZl("0");
       }
       if (beLzdOutCustom.getSbje() == null) {
         beLzdOutCustom.setSbje("0");
       }
       beLzdOutCustom.setQdbg(Integer.valueOf(0));
       beLzdOutCustom.setTdbg(Integer.valueOf(0));
       beLzdOutCustom.setCreatetime(Tools.getCurrentTime());
       this.beLzdOutDao.insert((BeLzdOut)beLzdOutCustom);
 
       
       return new ResultInfo(1, "添加成功!");
     } catch (Exception e) {
       return new ResultInfo(0, "添加失败，出现了错误!");
     } 
   }
 
 
 
   
   public void deleteByIds(Integer[] ids) throws Exception {
     this.beLzdOutDao.deleteByIds(ids);
   }
 
 
   
   public CountBeLzdOut count(BeLzdOutCustom beLzdOutCustom) throws Exception {
     return this.beLzdOutDao.count(beLzdOutCustom);
   }
 
   
   public GeneralResult importBeLzdOut(String string) throws Exception {
     GeneralResult generalResult = new GeneralResult();
     DecimalFormat df = new DecimalFormat("0.000");
     DecimalFormat df2 = new DecimalFormat("0.00");
     df2.setRoundingMode(RoundingMode.HALF_UP);
     df.setRoundingMode(RoundingMode.HALF_UP);
     Map<Integer, Object[]> items = BeLzdOutExcelUtil.read(string);
     List<BeLzdOut> list = new ArrayList<>();
     Map<String, Integer> map = new HashMap<>();
     File delFile = new File(string);
     if (delFile.exists()) {
       delFile.delete();
     }
     StringBuffer message = new StringBuffer();
     Date now = Tools.getCurrentTime();
     for (Map.Entry<Integer, Object[]> entry : items.entrySet()) {
       Object[] nowRowData = entry.getValue();
       int row = ((Integer)entry.getKey()).intValue() + 1;
       String khmc = Tools.getCellString(nowRowData[0]);
       String dhrq = Tools.getCellString(nowRowData[1]);
       String czrq = Tools.getCellString(nowRowData[2]);
       String jcdl = Tools.getCellString(nowRowData[3]);
       String hbh = Tools.getCellString(nowRowData[4]);
       String hbrq = Tools.getCellString(nowRowData[5]);
       String mdd = Tools.getCellString(nowRowData[6]);
       String tdjs = Tools.getCellString(nowRowData[7]);
       int i_tdjs = 0;
       if (tdjs != null) {
         try {
           i_tdjs = Integer.parseInt(tdjs);
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + row + "行数据失败，失败原因：提单件数可为空或者整数！<br>");
           
           continue;
         } 
       }
       String mdjs = Tools.getCellString(nowRowData[8]);
       int i_mdjs = 0;
       if (mdjs != null) {
         try {
           i_mdjs = Integer.parseInt(mdjs);
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + row + "行数据失败，失败原因：面单件数可为空或者整数！<br>");
           
           continue;
         } 
       }
       String zl = Tools.getCellString(nowRowData[9]);
       if (zl != null) {
         try {
           zl = df.format(Double.parseDouble(zl));
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + row + "行数据失败，失败原因：重量可为空或者数字！<br>");
           continue;
         } 
       } else {
         zl = "0";
       } 
       String tdh = Tools.getCellString(nowRowData[10]);
       if (tdh == null) {
         message.append("导入第" + row + "行数据失败，失败原因：提单号不能为空！<br>");
         continue;
       } 
       String sbqy = Tools.getCellString(nowRowData[11]);
       String fhr = Tools.getCellString(nowRowData[12]);
       String shr = Tools.getCellString(nowRowData[13]);
       String sbje = Tools.getCellString(nowRowData[14]);
       if (sbje != null) {
         try {
           sbje = df2.format(Double.parseDouble(sbje));
         } catch (Exception e) {
           e.printStackTrace();
           message.append("导入第" + row + "行数据失败，失败原因：申报金额必须为数字或为空！<br>");
           continue;
         } 
       } else {
         sbje = "0";
       } 
       String cb = Tools.getCellString(nowRowData[15]);
       String sj = Tools.getCellString(nowRowData[16]);
       String bz = Tools.getCellString(nowRowData[17]);
       
       BeLzdOut beLzdOut = new BeLzdOut();
       beLzdOut.setCreatetime(now);
       beLzdOut.setCb(cb);
       beLzdOut.setCzrq(czrq);
       beLzdOut.setDhrq(dhrq);
       beLzdOut.setFhr(fhr);
       beLzdOut.setHbh(hbh);
       beLzdOut.setHbrq(hbrq);
       beLzdOut.setJcdl(jcdl);
       beLzdOut.setKhmc(khmc);
       beLzdOut.setMdd(mdd);
       beLzdOut.setMdjs(Integer.valueOf(i_mdjs));
       beLzdOut.setSbqy(sbqy);
       beLzdOut.setShr(shr);
       beLzdOut.setTdh(tdh);
       beLzdOut.setTdjs(Integer.valueOf(i_tdjs));
       beLzdOut.setSj(sj);
       beLzdOut.setZl(zl);
       beLzdOut.setQdbg(Integer.valueOf(0));
       beLzdOut.setTdbg(Integer.valueOf(0));
       beLzdOut.setSbje(sbje);
       beLzdOut.setBz(bz);
       list.add(beLzdOut);
       if (map.get(tdh) != null) {
         message.append("导入第" + row + "行数据失败，失败原因：提单号已在excel表格中存在！<br>");
         continue;
       } 
       map.put(tdh, Integer.valueOf(row));
     } 
 
     
     if (message.toString().contains("导入")) {
       generalResult.setCode(Integer.valueOf(0));
       generalResult.setMessage(message.toString());
       return generalResult;
     } 
     Object[] set = map.keySet().toArray();
     if (set.length != 0) {
       List<String> act = this.beLzdOutDao.findByTdhs(set);
       if (act.size() != 0) {
         for (String string2 : act) {
           Integer row = map.get(string2);
           if (row != null) {
             message.append("导入第" + row + "行数据失败，失败原因：提单号已在系统中存在！<br>");
           }
         } 
       }
     } 
     if (message.toString().contains("导入")) {
       generalResult.setCode(Integer.valueOf(0));
       generalResult.setMessage(message.toString());
       return generalResult;
     } 
     
     if (list.size() != 0) {
       this.beLzdOutDao.insertBatch(list);
     }
     generalResult.setCode(Integer.valueOf(1));
     generalResult.setMessage("导出成功<font color='red'>" + list.size() + "</font>条");
     return generalResult;
   }
 
 
   
   public List<BeLzdOut> findByIds(int[] ids) {
     return this.beLzdOutDao.findByIds(ids);
   }
 }


