 package com.what21.service.impl;
 
 import com.what21.dao.CustomsParamDao;
 import com.what21.model.CustomsParamCustom;
 import com.what21.model.CustomsParamQueryVo;
 import com.what21.model.TCustomsParam;
 import com.what21.result.ResultInfo;
 import com.what21.service.CustomsParamService;
 import com.what21.tools.Tools;
 import com.what21.util.GeneralResult;
 import com.what21.util.ImportXxlsForCustomsParam;
 import java.text.DecimalFormat;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 @Service
 @Transactional
 public class CustomsParamServiceImpl
   implements CustomsParamService
 {
   @Autowired
   private CustomsParamDao customsParamDao;
   
   public Integer count(CustomsParamQueryVo customsParamQueryVo) {
     return this.customsParamDao.count(customsParamQueryVo);
   }
 
   
   public List<CustomsParamCustom> find(CustomsParamQueryVo customsParamQueryVo) {
     return this.customsParamDao.find(customsParamQueryVo);
   }
 
   
   public CustomsParamCustom findById(Integer id) {
     return this.customsParamDao.findById(id);
   }
 
   
   public ResultInfo insert(TCustomsParam tCustomsParam) throws Exception {
     DecimalFormat df = new DecimalFormat("0.00");
     String hscode = tCustomsParam.getHscode();
     String name = tCustomsParam.getName();
     String ordinaryrate = tCustomsParam.getOrdinaryrate();
     String preferentialrate = tCustomsParam.getPreferentialrate();
     String exportrate = tCustomsParam.getExportrate();
     String consumptionrate = tCustomsParam.getConsumptionrate();
     String valueaddedrate = tCustomsParam.getValueaddedrate();
     
     if (hscode == null) {
       return new ResultInfo(0, "hscode不能为空！");
     }
     if (this.customsParamDao.countByHscode(hscode).intValue() > 0) {
       return new ResultInfo(0, "hscode已经存在于系统！");
     }
     
     if (name == null) {
       return new ResultInfo(0, "名称不能为空！");
     }
     double d_ordinaryrate = 0.0D;
     if (ordinaryrate != null) {
       try {
         d_ordinaryrate = Double.parseDouble(ordinaryrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "普通税率只能为数字！");
       } 
     }
     tCustomsParam.setOrdinaryrate(df.format(d_ordinaryrate));
     
     double d_preferentialrate = 0.0D;
     if (preferentialrate != null) {
       try {
         d_preferentialrate = Double.parseDouble(preferentialrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "优惠税率只能为数字！");
       } 
     }
     tCustomsParam.setPreferentialrate(df.format(d_preferentialrate));
     
     double d_exportrate = 0.0D;
     if (exportrate != null) {
       try {
         d_exportrate = Double.parseDouble(exportrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "出口税率只能为数字！");
       } 
     }
     tCustomsParam.setExportrate(df.format(d_exportrate));
     
     double d_consumptionrate = 0.0D;
     if (preferentialrate != null) {
       try {
         d_consumptionrate = Double.parseDouble(consumptionrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "消费税率只能为数字！");
       } 
     }
     tCustomsParam.setConsumptionrate(df.format(d_consumptionrate));
     
     double d_valueaddedrate = 0.0D;
     if (valueaddedrate != null) {
       try {
         d_valueaddedrate = Double.parseDouble(valueaddedrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "增值税率只能为数字！");
       } 
     }
     tCustomsParam.setValueaddedrate(df.format(d_valueaddedrate));
     
     tCustomsParam.setCreatetime(Tools.getCurrentTime());
     this.customsParamDao.insert(tCustomsParam);
     return new ResultInfo(1, "新增成功！");
   }
 
   
   public ResultInfo update(TCustomsParam tCustomsParam) throws Exception {
     tCustomsParam.setHscode(this.customsParamDao.findById(tCustomsParam.getId()).getHscode());
     DecimalFormat df = new DecimalFormat("0.00");
     String name = tCustomsParam.getName();
     String ordinaryrate = tCustomsParam.getOrdinaryrate();
     String preferentialrate = tCustomsParam.getPreferentialrate();
     String exportrate = tCustomsParam.getExportrate();
     String consumptionrate = tCustomsParam.getConsumptionrate();
     String valueaddedrate = tCustomsParam.getValueaddedrate();
     
     if (name == null) {
       return new ResultInfo(0, "名称不能为空！");
     }
     double d_ordinaryrate = 0.0D;
     if (ordinaryrate != null) {
       try {
         d_ordinaryrate = Double.parseDouble(ordinaryrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "普通税率只能为数字！");
       } 
     }
     tCustomsParam.setOrdinaryrate(df.format(d_ordinaryrate));
     
     double d_preferentialrate = 0.0D;
     if (preferentialrate != null) {
       try {
         d_preferentialrate = Double.parseDouble(preferentialrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "优惠税率只能为数字！");
       } 
     }
     tCustomsParam.setPreferentialrate(df.format(d_preferentialrate));
     
     double d_exportrate = 0.0D;
     if (exportrate != null) {
       try {
         d_exportrate = Double.parseDouble(exportrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "出口税率只能为数字！");
       } 
     }
     tCustomsParam.setExportrate(df.format(d_exportrate));
     
     double d_consumptionrate = 0.0D;
     if (preferentialrate != null) {
       try {
         d_consumptionrate = Double.parseDouble(consumptionrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "消费税率只能为数字！");
       } 
     }
     tCustomsParam.setConsumptionrate(df.format(d_consumptionrate));
     
     double d_valueaddedrate = 0.0D;
     if (valueaddedrate != null) {
       try {
         d_valueaddedrate = Double.parseDouble(valueaddedrate);
       } catch (Exception e) {
         e.printStackTrace();
         return new ResultInfo(0, "增值税率只能为数字！");
       } 
     }
     tCustomsParam.setValueaddedrate(df.format(d_valueaddedrate));
     this.customsParamDao.update(tCustomsParam);
     return new ResultInfo(1, "修改成功！");
   }
 
   
   public ResultInfo deleteByIds(int[] ids) throws Exception {
     this.customsParamDao.deleteByIds(ids);
     return new ResultInfo(1, "删除<font color='red'>" + ids.length + "</font>条成功！");
   }
 
   
   public GeneralResult importOrderNew(String string) {
     GeneralResult result = new GeneralResult();
     try {
       ImportXxlsForCustomsParam howto = new ImportXxlsForCustomsParam();
       howto.processOneSheet(string, 1);
       if (howto.getSb().toString().contains("导入失败")) {
         result.setMessage(howto.getSb().toString());
         return result;
       } 
       List<TCustomsParam> list = howto.getList();
       int total = list.size();
       int step = 1000;
       int num = (total % step == 0) ? (total / step) : (total / step + 1);
       for (int i = 0; i < num; i++) {
         if (i != num - 1) {
           this.customsParamDao.insertBatch(list.subList(i * step, (i + 1) * step));
         } else {
           this.customsParamDao.insertBatch(list.subList(i * step, total));
         } 
       } 
       
       result.setMessage("导入成功" + total + "条！");
     } catch (Exception e) {
       e.printStackTrace();
       result.setMessage("读取Excel文件失败，请确保格式正确工整！");
     } 
     return result;
   }
 }


