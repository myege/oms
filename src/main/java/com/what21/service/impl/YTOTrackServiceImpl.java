 package com.what21.service.impl;
 
 import com.what21.service.YTOTrackService;
 import com.zt.kjybd.YTOUtil;
 import org.springframework.stereotype.Service;
 @Service
 public class YTOTrackServiceImpl
   implements YTOTrackService
 {
   public String checkTrack(String mailno) {
     try {
       return YTOUtil.queryByMailno(mailno);
     } catch (Exception e) {
       e.printStackTrace();
       return "<Response> <success>false</success><reason>database connection fails, please check!</reason></Response> ";
     } 
   }
 }


