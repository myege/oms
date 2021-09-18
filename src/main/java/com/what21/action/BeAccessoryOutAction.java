 package com.what21.action;
 
 import com.what21.model.BeAccessoryOut;
 import com.what21.model.BeAccessoryOutQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.BeAccessoryOutService;
 import com.what21.service.BeUploadService;
 import com.what21.tools.Tools;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/beAccessoryOut"})
 public class BeAccessoryOutAction
   extends BaseAction
 {
   @Autowired
   private BeAccessoryOutService beAccessoryOutService;
   @Autowired
   private BeUploadService beUploadService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findByLzdId(BeAccessoryOutQueryVo beAccessoryOutQueryVo, int rows, int page) throws Exception {
     beAccessoryOutQueryVo = (beAccessoryOutQueryVo == null) ? new BeAccessoryOutQueryVo() : beAccessoryOutQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     beAccessoryOutQueryVo.setPageQuery(pageQuery);
     int total = this.beAccessoryOutService.countAll(beAccessoryOutQueryVo).intValue();
     List<BeAccessoryOut> list = this.beAccessoryOutService.findAll(beAccessoryOutQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
   
   @RequestMapping({"/add"})
   @ResponseBody
   public ResultInfo add(int lzdId, MultipartFile file) throws Exception {
     if (file.getSize() == 0L) {
       return new ResultInfo(0, "文件不能为空！");
     }
     String path = this.beUploadService.findById("001").getAddress();
     String resFilename = file.getOriginalFilename();
     String tarFilename = "be_" + System.currentTimeMillis() + resFilename.substring(resFilename.lastIndexOf("."));
     File tarFile = new File(path, tarFilename);
     if (!tarFile.exists()) {
       tarFile.mkdirs();
     }
     file.transferTo(tarFile);
     BeAccessoryOut beAccessoryOut = new BeAccessoryOut();
     beAccessoryOut.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
     beAccessoryOut.setLzdid(Integer.valueOf(lzdId));
     beAccessoryOut.setTarfilename(tarFilename);
     beAccessoryOut.setResfilename(resFilename);
     beAccessoryOut.setItemname("accessory");
     ResultInfo resultInfo = this.beAccessoryOutService.insert(beAccessoryOut);
     if (resultInfo.getCode() != 1) {
       tarFile.delete();
     }
     return resultInfo;
   }
   
   @RequestMapping({"/update"})
   @ResponseBody
   public ResultInfo update(int id, MultipartFile file) throws Exception {
     try {
       if (file.getSize() == 0L) {
         return new ResultInfo(0, "文件不能为空！");
       }
       String path = null;
       BeAccessoryOut beAccessoryOut = this.beAccessoryOutService.findById(Integer.valueOf(id));
       String itemname = beAccessoryOut.getItemname();
       if ("lading".equals(itemname)) {
         path = this.beUploadService.findById("005").getAddress();
       } else if ("inventory_out".equals(itemname)) {
         path = this.beUploadService.findById("006").getAddress();
       } 
       String resFilename = file.getOriginalFilename();
       String tarFilename = "be_" + System.currentTimeMillis() + resFilename.substring(resFilename.lastIndexOf("."));
       File tarFile = new File(path, tarFilename);
       if (!tarFile.exists()) {
         tarFile.mkdirs();
       }
       file.transferTo(tarFile);
       ResultInfo resultInfo = this.beAccessoryOutService.update(id, resFilename, tarFilename, path);
       if (resultInfo.getCode() != 1) {
         tarFile.delete();
       }
       return resultInfo;
     }
     catch (Exception e) {
       return new ResultInfo(0, "修改失败！");
     } 
   }
 
   
   @RequestMapping({"/delete"})
   @ResponseBody
   public int delete(int id) throws Exception {
     try {
       BeAccessoryOut beAccessoryOut = this.beAccessoryOutService.findById(Integer.valueOf(id));
       String tarfilename = beAccessoryOut.getTarfilename();
       String itemname = beAccessoryOut.getItemname();
       String path = null;
       if ("lading".equals(itemname)) {
         path = this.beUploadService.findById("005").getAddress();
       } else if ("inventory_out".equals(itemname)) {
         path = this.beUploadService.findById("006").getAddress();
       } 
       File file = new File(path, tarfilename);
       this.beAccessoryOutService.deleteById(id);
       if (file.exists()) {
         file.delete();
       }
       return 1;
     } catch (Exception e) {
       return -1;
     } 
   }
 
   
   @RequestMapping({"/download"})
   public void download(int id, HttpServletResponse response) throws Exception {
     BeAccessoryOut beAccessoryOut = this.beAccessoryOutService.findById(Integer.valueOf(id));
     String tarfilename = beAccessoryOut.getTarfilename();
     String resfilename = beAccessoryOut.getResfilename();
     String itemname = beAccessoryOut.getItemname();
     String path = null;
     if ("lading".equals(itemname)) {
       path = this.beUploadService.findById("005").getAddress();
     } else if ("inventory_out".equals(itemname)) {
       path = this.beUploadService.findById("006").getAddress();
     } 
     File file = new File(path, tarfilename);
     InputStream is = new FileInputStream(file);
     int length = is.available();
     OutputStream os = new BufferedOutputStream((OutputStream)response.getOutputStream());
     byte[] b = new byte[length];
     is.read(b, 0, length);
     is.close();
     String filename = new String(resfilename.getBytes("UTF-8"), "ISO-8859-1");
     response.reset();
     response.setContentType("application/octet-stream;charset=UTF-8");
     response.addHeader("Content-Length", length+"");
     response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
     os.write(b);
     os.flush();
     os.close();
   }
   
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public int deleteByIds(Integer[] ids) throws Exception {
     try {
       List<File> list = new ArrayList<>(); byte b; int i; Integer[] arrayOfInteger;
       for (i = (arrayOfInteger = ids).length, b = 0; b < i; ) { Integer id = arrayOfInteger[b];
         BeAccessoryOut beAccessoryOut = this.beAccessoryOutService.findById(id);
         String tarfilename = beAccessoryOut.getTarfilename();
         String itemname = beAccessoryOut.getItemname();
         String path = null;
         if ("lading".equals(itemname)) {
           path = this.beUploadService.findById("005").getAddress();
         } else if ("inventory_out".equals(itemname)) {
           path = this.beUploadService.findById("006").getAddress();
         } 
         File file = new File(path, tarfilename);
         list.add(file); b++; }
       
       this.beAccessoryOutService.deleteByIds(ids);
       for (File file : list) {
         if (file.exists()) {
           file.delete();
         }
       } 
       return 1;
     } catch (Exception e) {
       return -1;
     } 
   }
   
   @RequestMapping({"/addBg"})
   @ResponseBody
   public ResultInfo addBg(Integer lzdId, Integer bglxOut, MultipartFile file) throws Exception {
     if (bglxOut == null) {
       return new ResultInfo(0, "上传报告类型没选择！");
     }
     if (file.getSize() == 0L) {
       return new ResultInfo(0, "文件不能为空！");
     }
     String path = null;
     String itemname = null;
     if (bglxOut.intValue() == 1) {
       path = this.beUploadService.findById("005").getAddress();
       itemname = "lading";
     } else if (bglxOut.intValue() == 2) {
       path = this.beUploadService.findById("006").getAddress();
       itemname = "inventory_out";
     } 
     
     String resFilename = file.getOriginalFilename();
     String tarFilename = "be_" + System.currentTimeMillis() + resFilename.substring(resFilename.lastIndexOf("."));
     File tarFile = new File(path, tarFilename);
     if (!tarFile.exists()) {
       tarFile.mkdirs();
     }
     file.transferTo(tarFile);
     BeAccessoryOut beAccessoryOut = new BeAccessoryOut();
     beAccessoryOut.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
     beAccessoryOut.setLzdid(lzdId);
     beAccessoryOut.setTarfilename(tarFilename);
     beAccessoryOut.setResfilename(resFilename);
     beAccessoryOut.setItemname(itemname);
     ResultInfo resultInfo = this.beAccessoryOutService.insert(beAccessoryOut);
     if (resultInfo.getCode() != 1) {
       tarFile.delete();
     }
     
     return resultInfo;
   }
 }


