 package com.what21.service.impl;
 
 import com.what21.dao.CustomsDepositSkuDao;
 import com.what21.model.CustomsDepositSkuCustom;
 import com.what21.model.CustomsDepositSkuQueryVo;
 import com.what21.model.TCustomsDepositSku;
 import com.what21.service.CustomsDepositSkuService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class CustomsDepositSkuServiceImpl
   implements CustomsDepositSkuService
 {
   @Autowired
   private CustomsDepositSkuDao customsDepositSkuDao;
   
   public Integer count(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return this.customsDepositSkuDao.count(customsDepositSkuQueryVo);
   }
 
   
   public List<CustomsDepositSkuCustom> find(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return this.customsDepositSkuDao.find(customsDepositSkuQueryVo);
   }
 
   
   public CustomsDepositSkuCustom findById(Integer id) {
     return this.customsDepositSkuDao.findById(id);
   }
 
   
   public void insert(TCustomsDepositSku tCustomsDepositSku) {
     this.customsDepositSkuDao.insert(tCustomsDepositSku);
   }
 
   
   public void update(TCustomsDepositSku tCustomsDepositSku) {
     this.customsDepositSkuDao.update(tCustomsDepositSku);
   }
 
 
   
   public Integer countByPid(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return this.customsDepositSkuDao.countByPid(customsDepositSkuQueryVo);
   }
 
   
   public List<CustomsDepositSkuCustom> findByPid(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return this.customsDepositSkuDao.findByPid(customsDepositSkuQueryVo);
   }
 
   
   public List<CustomsDepositSkuCustom> findByIds(int[] ids) {
     return this.customsDepositSkuDao.findByIds(ids);
   }
 }


