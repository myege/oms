 package com.what21.action;
 
 import cn.gov.zjport.manchester.utils.ZipUtils;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import net.sf.json.JSONObject;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"/KinTowReceipt"})
 public class KinTowReceipt
 {
   @RequestMapping({"/KinTowReceipt"})
   public void kinTowReceipt(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) throws IOException {
     System.out.println(json.toString());
 
     
     JSONObject str = JSONObject.fromObject(json);
     Map<String, String> object = (Map<String, String>)str.get("manSign");
     System.out.println(object.get("companyCode"));
     System.out.println(object.get("businessNo"));
     System.out.println(object.get("businessType"));
     System.out.println(object.get("declareType"));
     
     Map<String, String> manZip = (Map<String, String>)str.get("manZip");
     
     String gunzip = ZipUtils.gunzip(manZip.get("zipContent"));
     OutputStream out = new FileOutputStream("C:\\xml\\" + (new Date()).getTime() + ".txt");
     out.write(gunzip.getBytes());
     out.close();
     
     Map<String, String> manResultHead = new LinkedHashMap<>();
     manResultHead.put("companyCode", object.get("companyCode"));
     manResultHead.put("businessType", object.get("businessType"));
     manResultHead.put("businessNo", object.get("businessNo"));
     manResultHead.put("processTime", "2019-03-1415:59:10");
     manResultHead.put("processResult", "S");
     Map<String, String> manResultDetailList = new HashMap<>();
     List<Object> list = new ArrayList();
     manResultDetailList.put("information", "success");
     list.add(manResultDetailList);
     Map<String, Object> map = new HashMap<>();
     map.put("manResultHead", manResultHead);
     map.put("manResultDetailList", list);
     response.getWriter().write(JSONObject.fromObject(map).toString());
   }
 }


