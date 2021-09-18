package com.what21.service;

import com.what21.model.CustomsDepositCustom;
import com.what21.model.CustomsDepositQueryVo;
import com.what21.model.TCustomsDeposit;
import com.what21.result.ResultInfo;
import java.util.List;

public interface CustomsDepositService {
  Integer count(CustomsDepositQueryVo paramCustomsDepositQueryVo);
  
  List<CustomsDepositCustom> find(CustomsDepositQueryVo paramCustomsDepositQueryVo);
  
  CustomsDepositCustom findById(Integer paramInteger);
  
  ResultInfo insert(TCustomsDeposit paramTCustomsDeposit) throws Exception;
  
  ResultInfo topUp(CustomsDepositCustom paramCustomsDepositCustom) throws Exception;
  
  List<CustomsDepositCustom> findWarning(int paramInt);
}


