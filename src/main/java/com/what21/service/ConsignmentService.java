package com.what21.service;

import com.what21.model.ConsignmentQueryVo;
import com.what21.model.JjDelivery;
import com.what21.model.JjPickingSku;
import java.util.List;

public interface ConsignmentService {
  List<JjDelivery> findAll(ConsignmentQueryVo paramConsignmentQueryVo);
  
  int findCount();
  
  JjPickingSku checkBillInPickingSku(JjDelivery paramJjDelivery);
  
  int insertJjDelivery(ConsignmentQueryVo paramConsignmentQueryVo);
  
  int checkBillInDelivery(JjDelivery paramJjDelivery);
}


