 package com.what21.dao.impl;
 
 import com.what21.dao.AddressDao;
 import com.what21.model.Address;
 import java.util.List;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class AddressDaoImpl
   implements AddressDao
 {
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
     this.sqlSessionTemplate = sqlSessionTemplate;
   }
   
   public int insert(Address address) {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).insert(address);
   }
 
   
   public int delete(long id) {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).delete(id);
   }
 
   
   public Address getOneOrAll(long id) {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).getOneOrAll(id);
   }
 
   
   public List<Address> getOneOrAll() {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).getOneOrAll();
   }
 
   
   public int update(Address address) {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).update(address);
   }
 
   
   public List<Address> getProvincesOrChildren() {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).getProvincesOrChildren();
   }
 
   
   public List<Address> getProvincesOrChildren(long id) {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).getProvincesOrChildren(id);
   }
 
   
   public int findChild(long id) {
     return ((AddressDao)this.sqlSessionTemplate.getMapper(AddressDao.class)).findChild(id);
   }
 }


