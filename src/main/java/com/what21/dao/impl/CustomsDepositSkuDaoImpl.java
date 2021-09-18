 package com.what21.dao.impl;
 
 import com.what21.dao.CustomsDepositSkuDao;
 import com.what21.model.CustomsDepositSkuCustom;
 import com.what21.model.CustomsDepositSkuQueryVo;
 import com.what21.model.TCustomsDepositSku;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class CustomsDepositSkuDaoImpl
   implements CustomsDepositSkuDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public Integer count(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).count(customsDepositSkuQueryVo);
   }
 
   
   public List<CustomsDepositSkuCustom> find(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).find(customsDepositSkuQueryVo);
   }
 
   
   public CustomsDepositSkuCustom findById(Integer id) {
     return ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).findById(id);
   }
 
   
   public void insert(TCustomsDepositSku tCustomsDepositSku) {
     ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).insert(tCustomsDepositSku);
   }
 
 
   
   public void update(TCustomsDepositSku tCustomsDepositSku) {
     ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).update(tCustomsDepositSku);
   }
 
 
   
   public Integer countByPid(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).countByPid(customsDepositSkuQueryVo);
   }
 
   
   public List<CustomsDepositSkuCustom> findByPid(CustomsDepositSkuQueryVo customsDepositSkuQueryVo) {
     return ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).findByPid(customsDepositSkuQueryVo);
   }
 
   
   public List<CustomsDepositSkuCustom> findByIds(int[] ids) {
     return ((CustomsDepositSkuDao)this.sqlSessionTemplate.getMapper(CustomsDepositSkuDao.class)).findByIds(ids);
   }
 }


