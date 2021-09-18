 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.result.ResultInfo;
 import com.zt.kjybd.GetWLGJ;
 import com.zt.kjybd.PushtoWQ;
 import java.io.BufferedReader;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLEncoder;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.servlet.http.HttpServletRequest;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/sto"})
 public class StoAction
   extends BaseAction
 {
   @RequestMapping({"/sto"})
   @ResponseBody
   public List<ResultInfo> sto(String string) throws Exception {
     List<ResultInfo> resultInfos = new ArrayList<>();
     String str = GetWLGJ.queryWlgj(string);
     Pattern pattern = Pattern.compile("<root>(.*)</root>");
     Matcher matcher = pattern.matcher(str);
     if (matcher.find()) {
       str = matcher.group(0);
     }
     Document document = DocumentHelper.parseText(str);
     Element root = document.getRootElement();
     List<Element> list = root.elements("track");
     for (Element element : list) {
       StringBuffer sb = new StringBuffer();
       List<Element> list2 = element.elements("detail");
       Element billcode = element.element("billcode");
       for (Element element2 : list2) {
         Element time = element2.element("time");
         Element scantype = element2.element("scantype");
         Element memo = element2.element("memo");
         sb.append("<font size='4'>" + time.getText() + "  " + scantype.getText() + "</font><br/>");
         sb.append("<font size='3'>" + memo.getText() + "</font><br/><br/>");
       } 
       if (sb.toString().length() == 0) {
         resultInfos.add(new ResultInfo(0, "<p align='center'><font size='4'>很抱歉！您所查询的快递单号:" + billcode.getText() + " 可能不存在或还没有物流轨迹</font></p>")); continue;
       } 
       resultInfos.add(new ResultInfo(1, sb.insert(0, "<h3>" + billcode.getText() + "</h3>").toString()));
     } 
 
     
     return resultInfos;
   }
   @RequestMapping({"/acs"})
   @ResponseBody
   public List<ResultInfo> acs(String string) throws Exception {
     List<ResultInfo> resultInfos = new ArrayList<>();
     URL url = new URL("http://118.163.107.199:8901/ACSAPI.aspx?jobno=" + string);
     HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
     InputStream is = httpURLConnection.getInputStream();
     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
     String ret = bufferedReader.readLine();
     Document document = DocumentHelper.parseText(ret);
     Element root = document.getRootElement();
     List<Element> list = root.elements("manifest");
     for (Element element : list) {
       Element ishas = element.element("ishas");
       Element jobno = element.element("jobno");
       StringBuffer sb = new StringBuffer("<h3>" + jobno.getText() + "</h3>");
       if ("false".equals(ishas.getText())) {
         resultInfos.add(new ResultInfo(0, "<p align='center'><font size='4'>很抱歉！您所查询的快递单号:" + jobno.getText() + "可能不存在或还没有物流轨迹</font></p>")); continue;
       } 
       Element podlist = element.element("podlist");
       List<Element> lists = podlist.elements("list");
       for (Element list3 : lists) {
         Element date = list3.element("date");
         Element loc_name = list3.element("loc_name");
         Element stat_name = list3.element("stat_name");
         sb.append("<font size='4'>" + date.getText() + "  " + loc_name.getText() + "</font><br/>");
         sb.append("<font size='3'>" + stat_name.getText() + "</font><br/><br/>");
       } 
       resultInfos.add(new ResultInfo(1, sb.toString()));
     } 
     
     return resultInfos;
   }
   
   @RequestMapping({"matchSDM"})
   @ResponseBody
   public String matchSDM(HttpServletRequest request, String User, String Password, String billno, String Sign, String province, String city, String area, String address, String person, String orderno, String tel) throws Exception {
     try {
       SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
       String data = df2.format(new Date());
       if (User == null) {
         return URLEncoder.encode("匹配失败，请填写用户名User!", "UTF-8");
       }
       if (Password == null) {
         return URLEncoder.encode("匹配失败，请填写密码Password!", "UTF-8");
       }
       if (billno == null) {
         return URLEncoder.encode("匹配失败，请填写运单号billno!", "UTF-8");
       }
       if (Sign == null) {
         return URLEncoder.encode("匹配失败，请填写前面Sign!", "UTF-8");
       }
       if (!Sign.equals("0888A1AFB593666C658BF5C315B3E3F3")) {
         return URLEncoder.encode("匹配失败，Sign异常!", "UTF-8");
       }
       if (province == null) {
         return URLEncoder.encode("匹配失败，请填写省份province!", "UTF-8");
       }
       if (city == null) {
         return URLEncoder.encode("匹配失败，请填写城市city!", "UTF-8");
       }
       if (address == null) {
         return URLEncoder.encode("匹配失败，请填写地址address!", "UTF-8");
       }
       if (area == null) {
         return URLEncoder.encode("匹配失败，请填写地区area!", "UTF-8");
       }
       if (person == null) {
         return URLEncoder.encode("匹配失败，请填写收件人person!", "UTF-8");
       }
       if (orderno == null) {
         return URLEncoder.encode("匹配失败，请填写订单号orderno!", "UTF-8");
       }
       if (tel == null) {
         return URLEncoder.encode("匹配失败，请填写电话tel!", "UTF-8");
       }
       
       String url = "http://vip.sto.cn/PreviewInterfaceAction.action";
       
       String sj = "code=vip0007&data_digest=053c044e0389874dcf6ff44f7c5e6673&cuspwd=zhao2016/&data=[{'sendtel':'','sendcus':'" + User + "','weight':'0.224'" + 
         ",'receivecity':'" + city + "','sendaddress':'杭州保税区','sendacode':'','remark':'','receiveperson':'" + person + "'," + 
         "'receiveaddress':'" + address + "','orderno':'" + orderno + "','lasteditdate':'','receiveccode':'','bigchar':'','sendccode':''," + 
         "'receivecus':'" + person + "','inputperson':'浙江杭州仓储部','senddate':'" + data + "'," + 
         "'productcode':'','inputdate':'" + data + "','lasteditperson':'','sendcity':'杭州','receiveacode':'','sendarea':'萧山区'," + 
         "'receivetel':'" + tel + "','goodsname':'跨进商品','inputsite':'浙江杭州仓储部','receivepcode':'','sendpcode':'','lasteditsite':'','sendprovince':'浙江'," + 
         "'receivearea':'" + area + "','sendsite':'浙江杭州仓储部','billno':'" + billno + "','receiveprovince':'" + province + "','sendperson':'" + person + "'}]";
       
       String str = PushtoWQ.sendPost2(url, sj.toString());
       
       System.out.println("str=" + str);
       
       return URLEncoder.encode(str, "UTF-8");
     
     }
     catch (Exception e) {
       e.printStackTrace();
       return URLEncoder.encode("匹配失败：出现位置错误！", "UTF-8");
     } 
   }
 
 
   
   public static String sendPost(String url, String param) {
     PrintWriter out = null;
     BufferedReader in = null;
     String result = "";
     try {
       URL realUrl = new URL(url);
       
       URLConnection conn = realUrl.openConnection();
       conn.setRequestProperty("accept", "*/*");
       conn.setRequestProperty("connection", "Keep-Alive");
       conn.setRequestProperty("user-agent", 
           "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
       
       conn.setDoOutput(true);
       conn.setDoInput(true);
 
       
       out = new PrintWriter(conn.getOutputStream());
       
       out.print(param);
       
       out.flush();
 
       
       in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
       String line;
       while ((line = in.readLine()) != null) {
         result = String.valueOf(result) + line;
       }
     } catch (Exception e) {
       System.out.println("发送 POST 请求出现异常！" + e);
       e.printStackTrace();
     } finally {
 
       
       try {
         if (out != null) {
           out.close();
         }
         if (in != null) {
           in.close();
         }
       }
       catch (IOException ex) {
         ex.printStackTrace();
       } 
     } 
     return result;
   }
   
   public static String sendPost2(String url, String po) {
     String result = "";
 
     
     try {
       String charset = "UTF-8";
       URL urla = null;
       
       HttpURLConnection connection = null;
       urla = new URL(url);
       
       connection = (HttpURLConnection)urla.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       out.write(po.getBytes(charset));
 
 
       
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
       result = result.replaceAll("(null)\\s*,", "\"\",");
       
       System.out.println("result=" + result);
     }
     catch (Exception ex) {
       ex.printStackTrace();
     } 
     return result;
   }
   
   public static void main(String[] args) {
     SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
     String date = df2.format(new Date());
 
     
     String url = "http://vip.sto.cn/PreviewInterfaceAction.action";
     
     String parm12 = "code=vip0007&data_digest=053c044e0389874dcf6ff44f7c5e6673&cuspwd=zhao2016/&data=[{'sendtel':'','sendcus':'HZHT','weight':'0.224','receivecity':'绍兴市','sendaddress':'杭州保税区','sendacode':'','remark':'','receiveperson':'郑菁菁','receiveaddress':'市民大道上百万和城2楼东门上妍美丽+','orderno':'BSCS002','lasteditdate':'','receiveccode':'','bigchar':'','sendccode':'','receivecus':'郑菁菁','inputperson':'浙江杭州仓储部','senddate':'2018-05-11','productcode':'','inputdate':'2018-05-11','lasteditperson':'','sendcity':'杭州','receiveacode':'','sendarea':'萧山区','receivetel':'null','goodsname':'跨进商品','inputsite':'浙江杭州仓储部','receivepcode':'','sendpcode':'','lasteditsite':'','sendprovince':'浙江','receivearea':'上虞市','sendsite':'浙江杭州仓储部','billno':'221232406490','receiveprovince':'浙江省','sendperson':'郑菁菁'}]";
     String resout = sendPost(url, parm12);
     
     System.out.println("resout=" + resout);
     JSONObject jsonObject = JSONObject.parseObject(resout);
     String flag = jsonObject.get("success").toString();
     if (!"false".equals(flag))
     {
       if ("true".equals(flag)) {
         JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.getJSONArray("data").get(0).toString());
         String billProvideSiteCode = jsonObject2.get("bigchar").toString();
         System.out.println(billProvideSiteCode);
       } 
     }
   }
 }


