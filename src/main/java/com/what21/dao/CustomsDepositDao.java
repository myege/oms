package com.what21.dao;

import com.what21.model.CustomsDepositCustom;
import com.what21.model.CustomsDepositQueryVo;
import com.what21.model.TCustomsDeposit;
import java.util.List;

public interface CustomsDepositDao {
  Integer count(CustomsDepositQueryVo paramCustomsDepositQueryVo);
  
  List<CustomsDepositCustom> find(CustomsDepositQueryVo paramCustomsDepositQueryVo);
  
  CustomsDepositCustom findById(Integer paramInteger);
  
  void insert(TCustomsDeposit paramTCustomsDeposit);
  
  void update(TCustomsDeposit paramTCustomsDeposit);
  
  Integer countByCompanycode(String paramString);
  
  List<CustomsDepositCustom> findWarning(int paramInt);
}


