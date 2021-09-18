package com.what21.service.impl;

import com.what21.dao.ImEntryBillListDao;
import com.what21.service.ImEntryBillListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImEntryBillListServiceImpl implements ImEntryBillListService {
  @Autowired
  private ImEntryBillListDao imEntryBillListDao;
}


