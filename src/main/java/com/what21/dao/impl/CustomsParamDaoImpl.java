 package com.what21.dao.impl;
 
 import com.what21.dao.CustomsParamDao;
 import com.what21.model.CustomsParamCustom;
 import com.what21.model.CustomsParamQueryVo;
 import com.what21.model.TCustomsParam;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 @Repository
 public class CustomsParamDaoImpl
   implements CustomsParamDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public Integer count(CustomsParamQueryVo customsParamQueryVo) {
     return ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).count(customsParamQueryVo);
   }
 
   
   public List<CustomsParamCustom> find(CustomsParamQueryVo customsParamQueryVo) {
     return ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).find(customsParamQueryVo);
   }
 
   
   public CustomsParamCustom findById(Integer id) {
     return ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).findById(id);
   }
 
   
   public void insert(TCustomsParam tCustomsParam) {
     ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).insert(tCustomsParam);
   }
 
 
   
   public void update(TCustomsParam tCustomsParam) {
     ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).update(tCustomsParam);
   }
 
 
   
   public void insertBatch(List<TCustomsParam> list) {
     ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).insertBatch(list);
   }
 
 
   
   public void deleteByIds(int[] ids) {
     ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).deleteByIds(ids);
   }
 
 
   
   public Integer countByHscode(String hscode) {
     return ((CustomsParamDao)this.sqlSessionTemplate.getMapper(CustomsParamDao.class)).countByHscode(hscode);
   }
 }


