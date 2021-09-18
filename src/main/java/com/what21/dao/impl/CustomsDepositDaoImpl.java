 package com.what21.dao.impl;
 
 import com.what21.dao.CustomsDepositDao;
 import com.what21.model.CustomsDepositCustom;
 import com.what21.model.CustomsDepositQueryVo;
 import com.what21.model.TCustomsDeposit;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class CustomsDepositDaoImpl
   implements CustomsDepositDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public Integer count(CustomsDepositQueryVo customsDepositQueryVo) {
     return ((CustomsDepositDao)this.sqlSessionTemplate.getMapper(CustomsDepositDao.class)).count(customsDepositQueryVo);
   }
 
   
   public List<CustomsDepositCustom> find(CustomsDepositQueryVo customsDepositQueryVo) {
     return ((CustomsDepositDao)this.sqlSessionTemplate.getMapper(CustomsDepositDao.class)).find(customsDepositQueryVo);
   }
 
   
   public CustomsDepositCustom findById(Integer id) {
     return ((CustomsDepositDao)this.sqlSessionTemplate.getMapper(CustomsDepositDao.class)).findById(id);
   }
 
   
   public void insert(TCustomsDeposit tCustomsDeposit) {
     ((CustomsDepositDao)this.sqlSessionTemplate.getMapper(CustomsDepositDao.class)).insert(tCustomsDeposit);
   }
 
 
   
   public void update(TCustomsDeposit tCustomsDeposit) {
     ((CustomsDepositDao)this.sqlSessionTemplate.getMapper(CustomsDepositDao.class)).update(tCustomsDeposit);
   }
 
 
   
   public Integer countByCompanycode(String companycode) {
     return ((CustomsDepositDao)this.sqlSessionTemplate.getMapper(CustomsDepositDao.class)).countByCompanycode(companycode);
   }
 
   
   public List<CustomsDepositCustom> findWarning(int i) {
     return ((CustomsDepositDao)this.sqlSessionTemplate.getMapper(CustomsDepositDao.class)).findWarning(i);
   }
 }


