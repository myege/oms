 package com.what21.dao.impl;
 
 import com.what21.dao.WebUiDao;
 import com.what21.model.WebUi;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 @Repository
 public class WebUiDaoImpl
   implements WebUiDao
 {
   @Autowired
   SqlSessionTemplate sqlSessionTemplate;
   
   public List<WebUi> getAboutusMx() {
     return this.sqlSessionTemplate.selectList(String.valueOf(WebUi.class.getName()) + ".getAboutusMx");
   }
 
 
   
   public List<WebUi> findAll(WebUi webUi) {
     return this.sqlSessionTemplate.selectList(String.valueOf(WebUi.class.getName()) + ".findAll", webUi);
   }
 
 
   
   public Integer count(WebUi webUi) {
     return (Integer)this.sqlSessionTemplate.selectOne(String.valueOf(WebUi.class.getName()) + ".count", webUi);
   }
 
   
   public List<WebUi> findByCode(String code) {
     return this.sqlSessionTemplate.selectList(String.valueOf(WebUi.class.getName()) + ".findByCode", code);
   }
 
 
   
   public void update(List<WebUi> obj) {
     this.sqlSessionTemplate.update(String.valueOf(WebUi.class.getName()) + ".update", obj);
   }
 
 
   
   public List<WebUi> findByBegCode(String string) {
     return this.sqlSessionTemplate.selectList(String.valueOf(WebUi.class.getName()) + ".findByBegCode", string);
   }
 
   
   public List<WebUi> findByCodeThree(int id) {
     return this.sqlSessionTemplate.selectList(String.valueOf(WebUi.class.getName()) + ".findByCodeThree", Integer.valueOf(id));
   }
 }


