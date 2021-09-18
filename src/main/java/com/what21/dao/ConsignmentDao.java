package com.what21.dao;

import com.what21.model.ConsignmentQueryVo;
import com.what21.model.JjDelivery;
import com.what21.model.JjPickingSku;
import java.util.List;

public interface ConsignmentDao {
  List<JjDelivery> findAll(ConsignmentQueryVo paramConsignmentQueryVo);
  
  int findCount();
  
  JjPickingSku checkBillInPickingSku(JjDelivery paramJjDelivery);
  
  int checkBillInDelivery(JjDelivery paramJjDelivery);
  
  int insertJjDelivery(ConsignmentQueryVo paramConsignmentQueryVo);
}


