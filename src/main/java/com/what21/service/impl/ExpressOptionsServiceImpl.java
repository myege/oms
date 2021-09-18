 package com.what21.service.impl;
 
 import com.what21.dao.ExpressOptionsDao;
 import com.what21.model.ExpressOptions;
 import com.what21.service.ExpressOptionsService;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class ExpressOptionsServiceImpl
   implements ExpressOptionsService
 {
   @Autowired
   private ExpressOptionsDao expressOptionsDao;
   
   public void save(Map<String, String> map) {
     if (!map.isEmpty() && map != null) {
       for (Map.Entry<String, String> entry : map.entrySet()) {
         saveOption(entry.getKey(), entry.getValue());
       }
     }
   }
   
   public void saveOption(String key, String value) {
     if (StringUtils.isNotEmpty(key)) {
       ExpressOptions expressOptions = this.expressOptionsDao.findByKey(key);
       if (expressOptions == null) {
         ExpressOptions options = new ExpressOptions();
         options.setOptionName(key);
         options.setOptionValue(value);
         this.expressOptionsDao.insert(options);
       } else {
         ExpressOptions options = new ExpressOptions();
         options.setOptionName(expressOptions.getOptionName());
         options.setOptionValue(value);
         this.expressOptionsDao.updateByKey(options);
       } 
     } 
   }
   
   public List<ExpressOptions> selectList() {
     return this.expressOptionsDao.selectList();
   }
 }


