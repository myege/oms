 package com.what21.action;
 
 import com.what21.model.BeAccessory;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.BeAccessoryService;
 import com.what21.service.BeUploadService;
 import com.what21.tools.Tools;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 @Controller
 @RequestMapping({"/beAccessory"})
 public class BeAccessoryAction
   extends BaseAction
 {
   @Autowired
   private BeAccessoryService beAccessoryService;
   @Autowired
   private BeUploadService beUploadService;
   
   @RequestMapping({"/findByLzdId"})
   @ResponseBody
   public DatagridResultInfo findByLzdId(int lzdId, int rows, int page) throws Exception {
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     Map<String, Object> map = new HashMap<>();
     map.put("lzdid", Integer.valueOf(lzdId));
     map.put("accessory", "accessory");
     map.put("startPage", Integer.valueOf((page - 1) * rows));
     map.put("endPage", Integer.valueOf(rows));
     int total = this.beAccessoryService.countByLzdIdAndItemName(map);
     List<BeAccessory> list = this.beAccessoryService.findByLzdIdAndItemName(map);
     datagridResultInfo.setTotal(total);
     datagridResultInfo.setRows(list);
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
     BeAccessory beAccessory = new BeAccessory();
     beAccessory.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
     beAccessory.setLzdid(Integer.valueOf(lzdId));
     beAccessory.setTarfilename(tarFilename);
     beAccessory.setResfilename(resFilename);
     beAccessory.setItemname("accessory");
     ResultInfo resultInfo = this.beAccessoryService.insert(beAccessory);
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
       BeAccessory beAccessory = this.beAccessoryService.findById(Integer.valueOf(id));
       String itemname = beAccessory.getItemname();
       if ("accessory".equals(itemname)) {
         path = this.beUploadService.findById("001").getAddress();
       } else if ("inventory".equals(itemname)) {
         path = this.beUploadService.findById("002").getAddress();
       } else if ("tallying".equals(itemname)) {
         path = this.beUploadService.findById("003").getAddress();
       } else if ("customs".equals(itemname)) {
         path = this.beUploadService.findById("004").getAddress();
       } 
       String resFilename = file.getOriginalFilename();
       String tarFilename = "be_" + System.currentTimeMillis() + resFilename.substring(resFilename.lastIndexOf("."));
       File tarFile = new File(path, tarFilename);
       if (!tarFile.exists()) {
         tarFile.mkdirs();
       }
       file.transferTo(tarFile);
       ResultInfo resultInfo = this.beAccessoryService.update(id, resFilename, tarFilename, path);
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
       BeAccessory beAccessory = this.beAccessoryService.findById(Integer.valueOf(id));
       String tarfilename = beAccessory.getTarfilename();
       String itemname = beAccessory.getItemname();
       String path = null;
       if ("accessory".equals(itemname)) {
         path = this.beUploadService.findById("001").getAddress();
       } else if ("inventory".equals(itemname)) {
         path = this.beUploadService.findById("002").getAddress();
       } else if ("tallying".equals(itemname)) {
         path = this.beUploadService.findById("003").getAddress();
       } else if ("customs".equals(itemname)) {
         path = this.beUploadService.findById("004").getAddress();
       } 
       File file = new File(path, tarfilename);
       this.beAccessoryService.deleteById(id);
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
     BeAccessory beAccessory = this.beAccessoryService.findById(Integer.valueOf(id));
     String tarfilename = beAccessory.getTarfilename();
     String resfilename = beAccessory.getResfilename();
     String itemname = beAccessory.getItemname();
     String path = null;
     if ("accessory".equals(itemname)) {
       path = this.beUploadService.findById("001").getAddress();
     } else if ("inventory".equals(itemname)) {
       path = this.beUploadService.findById("002").getAddress();
     } else if ("tallying".equals(itemname)) {
       path = this.beUploadService.findById("003").getAddress();
     } else if ("customs".equals(itemname)) {
       path = this.beUploadService.findById("004").getAddress();
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
   public int deleteByIds(int[] ids) throws Exception {
     try {
       List<File> list = new ArrayList<>(); byte b; int i, arrayOfInt[];
       for (i = (arrayOfInt = ids).length, b = 0; b < i; ) { int id = arrayOfInt[b];
         BeAccessory beAccessory = this.beAccessoryService.findById(Integer.valueOf(id));
         String tarfilename = beAccessory.getTarfilename();
         String itemname = beAccessory.getItemname();
         String path = null;
         if ("accessory".equals(itemname)) {
           path = this.beUploadService.findById("001").getAddress();
         } else if ("inventory".equals(itemname)) {
           path = this.beUploadService.findById("002").getAddress();
         } else if ("tallying".equals(itemname)) {
           path = this.beUploadService.findById("003").getAddress();
         } else if ("customs".equals(itemname)) {
           path = this.beUploadService.findById("004").getAddress();
         } 
         File file = new File(path, tarfilename);
         list.add(file); b++; }
       
       this.beAccessoryService.deleteByIds(ids);
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
 
   
   @RequestMapping({"/findBgByLzdId"})
   @ResponseBody
   public DatagridResultInfo findBgByLzdId(int lzdId, int rows, int page) throws Exception {
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     Map<String, Object> map = new HashMap<>();
     map.put("lzdid", Integer.valueOf(lzdId));
     map.put("inventory", "inventory");
     map.put("startPage", Integer.valueOf((page - 1) * rows));
     map.put("endPage", Integer.valueOf(rows));
     int total = this.beAccessoryService.countByLzdIdAndItemName(map);
     List<BeAccessory> list = this.beAccessoryService.findByLzdIdAndItemName(map);
     datagridResultInfo.setTotal(total);
     datagridResultInfo.setRows(list);
     return datagridResultInfo;
   }
 
   
   @RequestMapping({"/addBg"})
   @ResponseBody
   public ResultInfo add(int lzdId, Integer bglx, MultipartFile file) throws Exception {
     if (bglx == null) {
       return new ResultInfo(0, "上传报告类型没选择！");
     }
     if (file.getSize() == 0L) {
       return new ResultInfo(0, "文件不能为空！");
     }
     String path = null;
     String itemname = null;
     if (bglx.intValue() == 1) {
       path = this.beUploadService.findById("002").getAddress();
       itemname = "inventory";
     } else if (bglx.intValue() == 2) {
       path = this.beUploadService.findById("003").getAddress();
       itemname = "tallying";
     } else if (bglx.intValue() == 3) {
       path = this.beUploadService.findById("004").getAddress();
       itemname = "customs";
     } 
     
     String resFilename = file.getOriginalFilename();
     String tarFilename = "be_" + System.currentTimeMillis() + resFilename.substring(resFilename.lastIndexOf("."));
     File tarFile = new File(path, tarFilename);
     if (!tarFile.exists()) {
       tarFile.mkdirs();
     }
     file.transferTo(tarFile);
     BeAccessory beAccessory = new BeAccessory();
     beAccessory.setCreatetime(Tools.format("yyyy-MM-dd HH:mm:ss", new Date()));
     beAccessory.setLzdid(Integer.valueOf(lzdId));
     beAccessory.setTarfilename(tarFilename);
     beAccessory.setResfilename(resFilename);
     beAccessory.setItemname(itemname);
     ResultInfo resultInfo = this.beAccessoryService.insert(beAccessory);
     if (resultInfo.getCode() != 1) {
       tarFile.delete();
     }
     
     return resultInfo;
   }
 }


