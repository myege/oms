 package com.what21.tools;
 
 import com.what21.model.Collect;
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.DataOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.security.InvalidKeyException;
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 import java.text.DateFormat;
 import java.text.DecimalFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import java.util.zip.ZipEntry;
 import java.util.zip.ZipOutputStream;
 import javax.crypto.Mac;
 import javax.crypto.spec.SecretKeySpec;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang.StringUtils;
 public class Tools
 {
   public static boolean flagID(String id) {
     Calendar now = Calendar.getInstance();
     int year_now = now.get(1);
     int month_now = now.get(2) + 1;
     int day_now = now.get(5);
     String rq = id.trim().substring(6, 14);
     int year = Integer.parseInt(rq.substring(0, 4));
     int month = Integer.parseInt(rq.substring(4, 6));
     int day = Integer.parseInt(rq.substring(6, 8));
     boolean flag = true;
     if (year_now - year < 18) {
       flag = false;
     } else if (year_now - year == 18) {
       if (month_now - month < 0) {
         flag = false;
       } else if (month_now - month == 0 && 
         day_now - day < 0) {
         flag = false;
       }
     
     } else if (year_now - year > 60) {
       flag = false;
     } else if (year_now - year == 60) {
       if (month_now - month > 0) {
         flag = false;
       } else if (month_now - month == 0 && 
         day_now - day > 0) {
         flag = false;
       } 
     } 
     
     return flag;
   }
   
   public static String format(String pattern, Date date) {
     DateFormat dateFormat = new SimpleDateFormat(pattern);
     return dateFormat.format(date);
   }
   
   public static Date parse(String pattern, String str_date) throws ParseException {
     DateFormat dateFormat = new SimpleDateFormat(pattern);
     return dateFormat.parse(str_date);
   }
   
   public static Date getCurrentTime() throws Exception {
     String str = format("yyyy-MM-dd HH:mm:ss", new Date());
     Date date = parse("yyyy-MM-dd HH:mm:ss", str);
     return date;
   }
   
   public static void saveHj(Collect collect) {
     DecimalFormat df = new DecimalFormat("0.00");
     float hj = 0.0F;
     float pttsf = 0.0F;
     String str_pttsf = collect.getPttsf();
     if (StringUtils.isEmpty(str_pttsf)) {
       pttsf = 0.0F;
     } else {
       pttsf = Float.parseFloat(str_pttsf);
     } 
     float yf = 0.0F;
     String str_yf = collect.getYf();
     if (StringUtils.isEmpty(str_yf)) {
       yf = 0.0F;
     } else {
       yf = Float.parseFloat(str_yf);
     } 
     float qgf = 0.0F;
     String str_qgf = collect.getQgf();
     if (StringUtils.isEmpty(str_qgf)) {
       qgf = 0.0F;
     } else {
       qgf = Float.parseFloat(str_qgf);
     } 
     float hcf = 0.0F;
     String str_hcf = collect.getHcf();
     if (StringUtils.isEmpty(str_hcf)) {
       hcf = 0.0F;
     } else {
       hcf = Float.parseFloat(str_hcf);
     } 
     float sf = 0.0F;
     String str_sf = collect.getSf();
     if (StringUtils.isEmpty(str_sf)) {
       sf = 0.0F;
     } else {
       sf = Float.parseFloat(str_sf);
     } 
     hj = pttsf + yf + qgf + hcf + sf;
     String str_hj = df.format(hj);
     collect.setHj(str_hj);
   }
   
   public static String parseYwbh(String maxYwbh, String prefix) {
     if (StringUtils.isEmpty(maxYwbh)) {
       String str = format("yyyyMM", new Date());
       return String.valueOf(prefix) + str + "000001";
     } 
     String max = maxYwbh.substring(2, 8);
     String cur = format("yyyyMM", new Date());
     StringBuffer sb = new StringBuffer();
     if (max.equals(cur)) {
       String str_num = maxYwbh.substring(8);
       int num = Integer.parseInt(str_num);
       num++;
       str_num = (new StringBuilder(String.valueOf(num))).toString();
       sb.append(maxYwbh.substring(0, 8));
       for (int i = 0; i < 6 - str_num.length(); i++) {
         sb.append("0");
       }
       sb.append(str_num);
       return sb.toString();
     } 
     sb.append(String.valueOf(prefix) + cur + "000001");
     return sb.toString();
   }
 
 
 
   
   public static void packageLoad(List<File> files, List<String> filenames, HttpServletResponse response, File tarFile, String tyd) throws Exception {
     if (!tarFile.exists()) {
       tarFile.createNewFile();
     }
     response.reset();
     OutputStream os = new FileOutputStream(tarFile);
     ZipOutputStream zos = new ZipOutputStream(os);
     for (int i = 0; i < files.size(); i++) {
       File file = files.get(i);
       if (file.exists() && 
         file.isFile()) {
         InputStream is = new FileInputStream(file);
         BufferedInputStream bufferedInputStream = new BufferedInputStream(is, 512);
         ZipEntry zipEntry = new ZipEntry(filenames.get(i));
         zos.putNextEntry(zipEntry);
         int len = -1;
         byte[] b = new byte[512];
         while ((len = is.read(b)) != -1) {
           zos.write(b, 0, len);
         }
         bufferedInputStream.close();
         is.close();
       } 
     } 
 
     
     zos.close();
     os.close();
     
     BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tarFile));
     byte[] buffer = new byte[bis.available()];
     bis.read(buffer);
     bis.close();
     response.reset();
     String filename = new String(tarFile.getName().getBytes("GBK"), "ISO-8859-1");
     BufferedOutputStream bos = new BufferedOutputStream((OutputStream)response.getOutputStream());
     response.setContentType("application/octet-stream");
     response.addHeader("Content-Length", buffer.length+"");
     response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
     bos.write(buffer);
     bos.flush();
     bos.close();
     tarFile.delete();
   }
 
 
   
   public static String removeNull(Object obj) {
     if (obj == null) {
       return "";
     }
     if (obj.getClass() == String.class) {
       return obj.toString();
     }
     return (String)obj;
   }
 
 
   
   public static String removeSpecialCharacter(String str) {
     String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
     Pattern p = Pattern.compile(regEx);
     Matcher m = p.matcher(str);
     str = m.replaceAll("").trim();
     return str;
   }
   
   public static String hamcsha1(byte[] data, byte[] key) {
     try {
       SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
       Mac mac = Mac.getInstance("HmacSHA1");
       mac.init(signingKey);
       return byte2hex(mac.doFinal(data));
     } catch (NoSuchAlgorithmException e) {
       e.printStackTrace();
     } catch (InvalidKeyException e) {
       e.printStackTrace();
     } 
     return null;
   }
 
   
   public static String byte2hex(byte[] b) {
     StringBuilder hs = new StringBuilder();
     
     for (int n = 0; b != null && n < b.length; n++) {
       String stmp = Integer.toHexString(b[n] & 0xFF);
       if (stmp.length() == 1)
         hs.append('0'); 
       hs.append(stmp);
     } 
     return hs.toString().toUpperCase();
   }
   
   public static String getMd5Str(String str) throws Exception {
     MessageDigest md5 = MessageDigest.getInstance("MD5");
     byte[] bytes = str.getBytes();
     byte[] digest = md5.digest(bytes);
     return byte2hex(digest);
   }
 
   
   public static void fileLog(String str) throws Exception {
     BufferedWriter out = null;
     
     try {
       String date = format("yyyy-MM-dd", getCurrentTime());
       String now = format("yyyy-MM-dd HH:mm:ss", getCurrentTime());
       File dir = new File("D:" + File.separator + "log");
       if (!dir.exists()) {
         dir.mkdir();
       }
       File file = new File("D:\\log\\" + date + ".txt");
       if (!file.exists()) {
         file.createNewFile();
         out = new BufferedWriter(new FileWriter(file));
         out.write(String.valueOf(now) + "：" + str + System.getProperty("line.separator"));
         out.close();
       } else {
         out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
         out.write(String.valueOf(now) + "：" + str + System.getProperty("line.separator"));
         out.close();
       } 
     } finally {
       if (out != null) {
         out.close();
       }
     } 
   }
   
   public static String getCellString(Object o) {
     if (o == null) {
       return null;
     }
     if ("".equals(o.toString().trim())) {
       return null;
     }
     return o.toString().trim();
   }
 
 
   
   public static String getDouble2(Double num) {
     DecimalFormat df = new DecimalFormat("0.00");
     return df.format(num);
   }
   
   public static String getDouble3(Double num) {
     DecimalFormat df = new DecimalFormat("0.000");
     return df.format(num);
   }
   
   public static String sendPost(String url, String param) {
     String result = "";
     try {
       URL url2 = new URL(url);
       String charset = "UTF-8";
       HttpURLConnection connection = null;
       connection = (HttpURLConnection)url2.openConnection();
       connection.setConnectTimeout(600000);
       connection.setReadTimeout(600000);
       connection.setDoOutput(true);
       connection.setDoInput(true);
       connection.setRequestMethod("POST");
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       connection.connect();
       
       DataOutputStream out = new DataOutputStream(connection.getOutputStream());
       
       out.write(param.getBytes(charset));
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
       System.out.println("result====" + result);
     } catch (Exception ex) {
       ex.printStackTrace();
       return result;
     } 
     return result;
   }
 }


