package com.what21.service;

import com.what21.model.CustomsDepositSkuCustom;
import com.what21.model.CustomsDepositSkuQueryVo;
import com.what21.model.TCustomsDepositSku;
import java.util.List;

public interface CustomsDepositSkuService {
  Integer count(CustomsDepositSkuQueryVo paramCustomsDepositSkuQueryVo);
  
  List<CustomsDepositSkuCustom> find(CustomsDepositSkuQueryVo paramCustomsDepositSkuQueryVo);
  
  CustomsDepositSkuCustom findById(Integer paramInteger);
  
  void insert(TCustomsDepositSku paramTCustomsDepositSku);
  
  void update(TCustomsDepositSku paramTCustomsDepositSku);
  
  Integer countByPid(CustomsDepositSkuQueryVo paramCustomsDepositSkuQueryVo);
  
  List<CustomsDepositSkuCustom> findByPid(CustomsDepositSkuQueryVo paramCustomsDepositSkuQueryVo);
  
  List<CustomsDepositSkuCustom> findByIds(int[] paramArrayOfint);
}


