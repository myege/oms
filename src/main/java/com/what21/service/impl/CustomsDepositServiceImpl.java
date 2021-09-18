 package com.what21.service.impl;
 
 import com.what21.dao.CustomsDepositDao;
 import com.what21.model.CustomsDepositCustom;
 import com.what21.model.CustomsDepositQueryVo;
 import com.what21.model.TCustomsDeposit;
 import com.what21.result.ResultInfo;
 import com.what21.service.CustomsDepositService;
 import com.what21.tools.Tools;
 import java.text.DecimalFormat;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class CustomsDepositServiceImpl
   implements CustomsDepositService
 {
   @Autowired
   private CustomsDepositDao customsDepositDao;
   
   public Integer count(CustomsDepositQueryVo customsDepositQueryVo) {
     return this.customsDepositDao.count(customsDepositQueryVo);
   }
 
   
   public List<CustomsDepositCustom> find(CustomsDepositQueryVo customsDepositQueryVo) {
     return this.customsDepositDao.find(customsDepositQueryVo);
   }
 
   
   public CustomsDepositCustom findById(Integer id) {
     return this.customsDepositDao.findById(id);
   }
 
   
   public ResultInfo insert(TCustomsDeposit tCustomsDeposit) throws Exception {
     DecimalFormat df = new DecimalFormat("0.0000");
     String companycode = tCustomsDeposit.getCompanycode();
     String companyname = tCustomsDeposit.getCompanyname();
     if (companyname == null || companycode == null) {
       return new ResultInfo(0, "信息不能为空！");
     }
     if (this.customsDepositDao.countByCompanycode(companycode).intValue() == 1) {
       return new ResultInfo(0, "已存在担保企业编码！");
     }
     double money = 0.0D;
     try {
       money = Double.parseDouble(tCustomsDeposit.getTotalmoney());
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "充值金额只能为数字！");
     } 
     if (money <= 0.0D) {
       return new ResultInfo(0, "充值金额必须为大于0的整数！");
     }
     String totalmoney = df.format(money);
     tCustomsDeposit.setCreatetime(Tools.getCurrentTime());
     tCustomsDeposit.setTotalmoney(totalmoney);
     tCustomsDeposit.setSurplusmoney(totalmoney);
     tCustomsDeposit.setUsedmoney("0");
     this.customsDepositDao.insert(tCustomsDeposit);
     return new ResultInfo(1, "保存成功！");
   }
 
   
   public ResultInfo topUp(CustomsDepositCustom customsDepositCustom) throws Exception {
     DecimalFormat decimalFormat = new DecimalFormat("0.0000");
     CustomsDepositCustom customsDepositCustom1 = this.customsDepositDao.findById(customsDepositCustom.getId());
     double money = 0.0D;
     try {
       money = Double.parseDouble(customsDepositCustom.getTotalmoney());
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "充值金额只能为数字！");
     } 
     if (money <= 0.0D) {
       return new ResultInfo(0, "充值金额必须为大于0的整数！");
     }
     String totalmoney = decimalFormat.format(Double.parseDouble(customsDepositCustom1.getTotalmoney()) + money);
     String surplusmoney = customsDepositCustom1.getSurplusmoney();
     double d = Double.parseDouble(surplusmoney);
     d += money;
     surplusmoney = decimalFormat.format(d);
     customsDepositCustom1.setTotalmoney(totalmoney);
     customsDepositCustom1.setSurplusmoney(surplusmoney);
     this.customsDepositDao.update((TCustomsDeposit)customsDepositCustom1);
     return new ResultInfo(1, "充值成功！");
   }
 
 
 
   
   public List<CustomsDepositCustom> findWarning(int i) {
     return this.customsDepositDao.findWarning(i);
   }
 }


