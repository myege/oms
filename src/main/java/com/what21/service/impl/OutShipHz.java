package com.what21.service.impl;

import com.what21.dao.OutshiptoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class OutShipHz implements ApplicationListener<ContextRefreshedEvent> {
  @Autowired
  OutshiptoDao outshiptoDao;
  
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {}
}


