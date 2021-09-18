 package com.what21.action;
 
 import com.what21.dao.IdcheckDao;
 import com.what21.model.Idcheck;
 import com.what21.tools.Tools;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.codec.binary.Base64;
 @Controller
 @RequestMapping({"/idcheckAll"})
 public class IdCardNameCheckAction
   extends BaseAction
 {
   @Autowired
   public IdcheckDao idcheckDao;
   
   @RequestMapping({"/idcheck"})
   public void receiveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
     String name = request.getParameter("name");
     String idCard = request.getParameter("idCard");
     String from = request.getParameter("fromto");
     
     System.out.println(String.valueOf(name) + "==" + idCard);
     String ret = "你好";
     
     if (name.equals("") || name == null || idCard.equals("") || idCard == null) {
       ret = "参数校验失败";
     }
     if (from.equals("rbstvip01")) {
       List<Idcheck> list = new ArrayList<>();
       Idcheck ic = new Idcheck();
       ic.setName(name);
       ic.setIdCard(idCard);
       ic.setFromto(from);
       ic.setCreateDateTime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
       ic.setStatus("0");
       
       String data = "userKey=Cy32Dishew3291NdsweO&name=" + name + "&idCard=" + idCard;
       ret = doPost("http://lzkjwork.com:18081/lzDataservice/system/dataservice/idCardNameCheckF", data);
       
       ic.setBack(ret);
       
       list.add(ic);
       this.idcheckDao.insertBatch(list);
     } else {
       
       ret = "fromto身份校验失败";
     }     
     response.getWriter().write(ret);
   }
   public static String doPost(String url1, String data) throws Exception {
     URL url = new URL(url1);
     
     String result = "";
     try {
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
             
       out.write(data.getBytes(charset));
       out.flush();
       out.close();
       BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       
       reader.close();
       result = buffer.toString();
     } catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
 }


