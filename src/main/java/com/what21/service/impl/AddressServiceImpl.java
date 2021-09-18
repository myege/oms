 package com.what21.service.impl;
 
 import com.what21.dao.AddressDao;
 import com.what21.model.Address;
 import com.what21.service.AddressService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class AddressServiceImpl
   implements AddressService
 {
   @Autowired
   private AddressDao addressMapper;
   
   public int add(Address address) {
     return this.addressMapper.insert(address);
   }
   
   public int delete(long id) {
     return this.addressMapper.delete(id);
   }
   
   public Address getOneOrAll(Integer id) {
     return this.addressMapper.getOneOrAll(id.intValue());
   }
   
   public List<Address> getOneOrAll() {
     return this.addressMapper.getOneOrAll();
   }
   
   public int update(Address address) {
     return this.addressMapper.update(address);
   }
   
   public List<Address> getProvincesOrChildren() {
     return this.addressMapper.getProvincesOrChildren();
   }
   
   public List<Address> getProvincesOrChildren(long id) {
     return this.addressMapper.getProvincesOrChildren(id);
   }
   
   public String findChild(long id) {
     return (this.addressMapper.findChild(id) > 0) ? "closed" : "open";
   }
 }


