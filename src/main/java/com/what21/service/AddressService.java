package com.what21.service;

import com.what21.model.Address;
import java.util.List;

public interface AddressService {
  int add(Address paramAddress);
  
  int delete(long paramLong);
  
  Address getOneOrAll(Integer paramInteger);
  
  List<Address> getOneOrAll();
  
  int update(Address paramAddress);
  
  List<Address> getProvincesOrChildren();
  
  List<Address> getProvincesOrChildren(long paramLong);
  
  String findChild(long paramLong);
}


