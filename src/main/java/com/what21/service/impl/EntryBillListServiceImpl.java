package com.what21.service.impl;

import com.what21.dao.EntryBillListDao;
import com.what21.service.EntryBillListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryBillListServiceImpl implements EntryBillListService {
  @Autowired
  private EntryBillListDao entryBillListDao;
}


