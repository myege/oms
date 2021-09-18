package com.what21.dao;

import com.what21.model.Address;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddressDao {
  int insert(Address paramAddress);
  
  int delete(long paramLong);
  
  Address getOneOrAll(@Param("id") long paramLong);
  
  List<Address> getOneOrAll();
  
  int update(Address paramAddress);
  
  List<Address> getProvincesOrChildren();
  
  List<Address> getProvincesOrChildren(@Param("id") long paramLong);
  
  int findChild(@Param("id") long paramLong);
}


