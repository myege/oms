 package com.what21.action;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.WebUi;
 import com.what21.service.WebUiService;
 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Enumeration;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.multipart.MultipartHttpServletRequest;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/Welcome"})
 public class WebUiAction
   extends BaseAction
 {
   @Autowired
   WebUiService WebUiService;
   
   @RequestMapping({"/getAboutusMx"})
   public void getAboutusMx(HttpServletResponse response) throws IOException {
     List<WebUi> aboutusMx = this.WebUiService.getAboutusMx();
     String str = JSONArray.toJSONString(aboutusMx);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/info"})
   public String info(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
     HttpSession session = request.getSession();
     List<WebUi> aboutusMx = this.WebUiService.getAboutusMx();
     for (WebUi ui : aboutusMx) {
       session.setAttribute(ui.getCode(), ui.getData());
     }
     
     return "webIndex";
   }
   
   @RequestMapping({"/findAll"})
   public void findAll(WebUi orderSearchDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
     List<WebUi> aboutusMx = this.WebUiService.findAll(orderSearchDto);
     Integer i = this.WebUiService.count(orderSearchDto);
     Map<String, Object> map = new HashMap<>();
     
     map.put("rows", aboutusMx);
     map.put("total", i);
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/webOneIndexUi"})
   public String editUi(String oneCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
     List<WebUi> ui = this.WebUiService.findByCode(oneCode);
     request.getSession().setAttribute("OneCode", oneCode);
     request.setAttribute("Img1", ((WebUi)ui.get(0)).getData());
     request.setAttribute("Img2", ((WebUi)ui.get(1)).getData());
     int i = 0;
     if (oneCode.equals("oneFont")) {
       i = 1;
     } else if (oneCode.equals("towFont")) {
       i = 2;
     } else if (oneCode.equals("threeFont")) {
       i = 3;
     } else if (oneCode.equals("fourFont")) {
       i = 4;
     } 
     request.setAttribute("Img3", "wp1_" + i + ".jpg");
     return "webUI/editUi";
   }
   
   @RequestMapping({"/webThreeIndexUi"})
   public String webThreeIndexUi(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
     List<WebUi> ui = this.WebUiService.findByCodeThree(id);
     request.getSession().setAttribute("OneCode", ((WebUi)ui.get(1)).getCode());
     request.setAttribute("Img1", ((WebUi)ui.get(1)).getData());
     request.setAttribute("Img2", ((WebUi)ui.get(0)).getData());
     return "webUI/threeEnditUi";
   }
   
   @RequestMapping({"/webFourIndexUi"})
   public String webFourIndexUi(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
     List<WebUi> ui = this.WebUiService.findByCodeThree(id);
     request.getSession().setAttribute("OneCode", ((WebUi)ui.get(0)).getCode());
     request.setAttribute("Img2", ((WebUi)ui.get(0)).getData());
     return "webUI/fourEnditUi";
   }
   @RequestMapping({"/webFiveIndexUi"})
   public String webFiveIndexUi(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
     List<WebUi> ui = this.WebUiService.findByBegCode((new StringBuilder(String.valueOf(id))).toString());
     request.getSession().setAttribute("OneCode", ((WebUi)ui.get(0)).getCode());
     request.setAttribute("Img2", ((WebUi)ui.get(0)).getData());
     return "webUI/fiveEnditUi";
   }
 
   
   @RequestMapping({"/OneEdit"})
   public void OneEdit(String oneFont1, String oneFont2, HttpServletRequest request, HttpServletResponse response) throws IOException {
     String str = (String)request.getSession().getAttribute("OneCode");
     List<WebUi> list = new ArrayList<>();
     WebUi obj1 = new WebUi();
     obj1.setCode(String.valueOf(str) + "1");
     obj1.setBegCode("1");
     obj1.setData(oneFont1);
     WebUi obj2 = new WebUi();
     obj2.setCode(String.valueOf(str) + "2");
     obj2.setBegCode("1");
     obj2.setData(oneFont2);
     list.add(obj1);
     list.add(obj2);
     this.WebUiService.update(list);
     
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
     List<MultipartFile> files = multipartRequest.getFiles("file");
     if (files.size() != 0) {
       MultipartFile file = files.get(0);
       
       String contextPath = request.getSession().getServletContext().getRealPath("");
       String filename = file.getOriginalFilename();
       
       String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
       String fileName = "";
       if (str.equals("oneFont")) {
         fileName = "\\images\\wp1_1";
       } else if (str.equals("towFont")) {
         fileName = "\\images\\wp1_2";
       } else if (str.equals("threeFont")) {
         fileName = "\\images\\wp1_3";
       } else if (str.equals("fourFont")) {
         fileName = "\\images\\wp1_4";
       } 
       
       File File = new File(String.valueOf(contextPath) + fileName + "." + extensionName);
       if (!File.exists())
         File.mkdirs(); 
       file.transferTo(new File(File.toString()));
     } 
     response.getWriter().write("ok");
   }
 
   
   @RequestMapping({"/ThreeEdit"})
   public void ThreeEdit(String casesOne2, HttpServletRequest request, HttpServletResponse response) throws IOException {
     String contextPath = request.getSession().getServletContext().getRealPath("");
     String str = (String)request.getSession().getAttribute("OneCode");
     str = str.substring(0, str.length() - 1);
     List<WebUi> list = new ArrayList<>();
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
     List<MultipartFile> files = multipartRequest.getFiles("file");
     WebUi obj2 = new WebUi();
     obj2.setCode(String.valueOf(str) + "2");
     obj2.setBegCode("3");
     obj2.setData(casesOne2);
     list.add(obj2);
     this.WebUiService.update(list);
     
     if (files.size() != 0) {
       MultipartFile file = files.get(0);
       String filename = file.getOriginalFilename();
       
       String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
       String fileName = "";
       if (str.equals("casesOne")) {
         fileName = "\\images\\casesOne";
       } else if (str.equals("casesTow")) {
         fileName = "\\images\\casesTow";
       } else if (str.equals("casesThree")) {
         fileName = "\\images\\casesThree";
       } 
       File File = new File(String.valueOf(contextPath) + fileName + "." + extensionName);
       System.out.println(String.valueOf(contextPath) + fileName + "." + extensionName);
       if (!File.exists())
         File.mkdirs(); 
       file.transferTo(new File(File.toString()));
     }     
     response.getWriter().write("ok");
   }
 
 
   
   @RequestMapping({"/EightEdit"})
   public void EightEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
     Enumeration<String> enu = request.getParameterNames();
     List<WebUi> list = new ArrayList<>();
     String attribute = request.getParameter("begCode");
     while (enu.hasMoreElements()) {
       WebUi web = new WebUi();
       String paraName = enu.nextElement();
       if (!"begCode".equals(paraName)) {
         
         web.setBegCode(attribute);
         web.setCode(paraName);
         web.setData(request.getParameter(paraName));
       } 
       list.add(web);
     } 
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
     List<MultipartFile> files = multipartRequest.getFiles("file");
     if (files.size() != 0) {
       MultipartFile file = files.get(0);
       String filename = file.getOriginalFilename();
       
       String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
       String fileName = "\\images\\wechat_code." + extensionName;
       
       this.WebUiService.update(list);
       String contextPath = request.getSession().getServletContext().getRealPath("");
       System.out.println(contextPath);
       File File = new File(String.valueOf(contextPath) + fileName);
       if (!File.exists())
         File.mkdirs(); 
       file.transferTo(new File(File.toString()));
     } 
     response.getWriter().write("ok");
   }
 
 
   
   @RequestMapping({"/fourEdit"})
   public void fourEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String contextPath = request.getSession().getServletContext().getRealPath("");
     String str = (String)request.getSession().getAttribute("OneCode");
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
     List<MultipartFile> files = multipartRequest.getFiles("file");
     if (files.size() != 0) {
       MultipartFile file = files.get(0);
       String filename = file.getOriginalFilename();
 
       
       String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
       String fileName = "\\images\\" + str;
       
       List<WebUi> list = new ArrayList<>();
       WebUi obj1 = new WebUi();
       obj1.setCode(str);
       obj1.setBegCode("4");
       obj1.setData("/ztznew" + fileName.replace("\\", "/") + "." + extensionName);
       list.add(obj1);
       this.WebUiService.update(list);
       File File = new File(String.valueOf(contextPath) + fileName + "." + extensionName);
       if (!File.exists())
         File.mkdirs(); 
       file.transferTo(new File(File.toString()));
     } 
     response.getWriter().write("ok");
   }
 
   
   @RequestMapping({"/init"})
   public ModelAndView init(String pageName, String zt) {
     if (StringUtils.isEmpty(pageName)) {
       return new ModelAndView("login");
     }
     Map<String, String> map = new HashMap<>();
     List<WebUi> findByCode = this.WebUiService.findAll(null);
     for (WebUi bb : findByCode) {
       map.put(bb.getCode(), bb.getData());
     }
     return new ModelAndView(pageName, map);
   }
   
   @RequestMapping({"editTowFont"})
   public void editTowFont(HttpServletRequest request, HttpServletResponse response) throws Exception {
     Enumeration<String> enu = request.getParameterNames();
     List<WebUi> list = new ArrayList<>();
     String attribute = request.getParameter("begCode");
     while (enu.hasMoreElements()) {
       WebUi web = new WebUi();
       String paraName = enu.nextElement();
       if (!"begCode".equals(paraName)) {
         
         web.setBegCode(attribute);
         web.setCode(paraName);
         web.setData(request.getParameter(paraName));
       } 
       list.add(web);
     } 
     this.WebUiService.update(list);
     response.getWriter().write("ok");
   }
 
   
   @RequestMapping({"threeWeb"})
   public void findThreeWeb(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
     List<WebUi> list = this.WebUiService.findByCode(code);
     for (int i = 0; i < list.size(); i++) {
       if (!((WebUi)list.get(i)).getCode().substring(((WebUi)list.get(i)).getCode().length() - 1).equals("1"))
         list.remove(i); 
     } 
     Map<String, Object> map = new HashMap<>();
     map.put("rows", list);
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   @RequestMapping({"fourWeb"})
   public void fourWeb(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
     List<WebUi> list = this.WebUiService.findByBegCode(code);
     Map<String, Object> map = new HashMap<>();
     map.put("rows", list);
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
 }


