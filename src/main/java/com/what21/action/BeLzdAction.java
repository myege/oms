 package com.what21.action;
 
 import com.what21.model.BeAccessory;
 import com.what21.model.BeLzd;
 import com.what21.model.BeLzdCustom;
 import com.what21.model.BeLzdQueryVo;
 import com.what21.model.BeQgf;
 import com.what21.model.Collect;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.BeAccessoryService;
 import com.what21.service.BeLzdService;
 import com.what21.service.BeQgfService;
 import com.what21.service.BeUploadService;
 import com.what21.service.CollectService;
 import com.what21.tools.Tools;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.BeanUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/beLzd"})
 public class BeLzdAction
   extends BaseAction
 {
   @Autowired
   private BeAccessoryService beAccessoryService;
   @Autowired
   private BeLzdService beLzdService;
   @Autowired
   private BeQgfService beQgfService;
   @Autowired
   private CollectService collectService;
   @Autowired
   private BeUploadService beUploadService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int rows, int page, BeLzdQueryVo beLzdQueryVo) throws Exception {
     beLzdQueryVo = (beLzdQueryVo == null) ? new BeLzdQueryVo() : beLzdQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     beLzdQueryVo.setPageQuery(pageQuery);
     int total = this.beLzdService.countAll(beLzdQueryVo);
     List<BeLzd> list = this.beLzdService.findAll(beLzdQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
   
   @RequestMapping({"/addUI"})
   public String addUI() {
     return "addBeLzd";
   }
   @RequestMapping({"/add"})
   @ResponseBody
   public ResultInfo add(BeLzdQueryVo beLzdQueryVo) throws Exception {
     beLzdQueryVo = (beLzdQueryVo == null) ? new BeLzdQueryVo() : beLzdQueryVo;
     BeLzdCustom beLzdCustom = beLzdQueryVo.getBeLzdCustom();
     beLzdQueryVo.setBeLzdCustom(beLzdCustom);
     ResultInfo resultInfo = this.beLzdService.insert(beLzdQueryVo);
     return resultInfo;
   }
 
 
 
   
   @RequestMapping({"/download"})
   public void download(int id, String xzlx, HttpServletResponse response) throws Exception {
     BeLzd beLzd = this.beLzdService.findById(Integer.valueOf(id));
     File tarFile = null;
     String fileName = null;
     Map<String, Object> map = new HashMap<>();
     map.put("lzdid", Integer.valueOf(id));
     if (StringUtils.isNotEmpty(xzlx)) {
       if ("fj".equals(xzlx)) {
         map.put("itemname", "accessory");
         List<BeAccessory> list = this.beAccessoryService.findByLzdIdAndItemName2(map);
         String path = this.beUploadService.findById("001").getAddress();
         if (list != null) {
           if (list.size() == 1) {
             String tarfilename = ((BeAccessory)list.get(0)).getTarfilename();
             fileName = ((BeAccessory)list.get(0)).getResfilename();
             tarFile = new File(path, tarfilename);
           } else {
             List<File> files = new ArrayList<>();
             List<String> filenames = new ArrayList<>();
             Map<String, Integer> map1 = new HashMap<>();
             for (BeAccessory beAccessory : list) {
               String tarfilename = beAccessory.getTarfilename();
               String resfilename = beAccessory.getResfilename();
               Integer i = map1.get(resfilename);
               if (i == null) {
                 filenames.add(resfilename);
                 map1.put(resfilename, Integer.valueOf(1));
               } else {
                 String suffix = resfilename.substring(resfilename.lastIndexOf("."));
                 String prefix = resfilename.substring(0, resfilename.lastIndexOf("."));
                 filenames.add(String.valueOf(prefix) + "副本" + i + suffix);
                 map1.put(resfilename, Integer.valueOf(i.intValue() + 1));
               } 
               files.add(new File(path, tarfilename));
             } 
             
             fileName = "附件_" + beLzd.getTdh() + ".rar";
             tarFile = new File(path, fileName);
             Tools.packageLoad(files, filenames, response, tarFile, beLzd.getTdh());
             
             return;
           } 
         }
       } else if ("bgbg".equals(xzlx)) {
         map.put("itemname", "customs");
         List<BeAccessory> list = this.beAccessoryService.findByLzdIdAndItemName2(map);
         String path = this.beUploadService.findById("004").getAddress();
         if (list != null) {
           if (list.size() == 1) {
             String tarfilename = ((BeAccessory)list.get(0)).getTarfilename();
             fileName = ((BeAccessory)list.get(0)).getResfilename();
             tarFile = new File(path, tarfilename);
           } else {
             List<File> files = new ArrayList<>();
             List<String> filenames = new ArrayList<>();
             Map<String, Integer> map1 = new HashMap<>();
             for (BeAccessory beAccessory : list) {
               String tarfilename = beAccessory.getTarfilename();
               String resfilename = beAccessory.getResfilename();
               Integer i = map1.get(resfilename);
               if (i == null) {
                 filenames.add(resfilename);
                 map1.put(resfilename, Integer.valueOf(1));
               } else {
                 String suffix = resfilename.substring(resfilename.lastIndexOf("."));
                 String prefix = resfilename.substring(0, resfilename.lastIndexOf("."));
                 filenames.add(String.valueOf(prefix) + "副本" + i + suffix);
               } 
               files.add(new File(path, tarfilename));
             } 
             fileName = "报关报告_" + beLzd.getTdh() + ".rar";
             tarFile = new File(path, fileName);
             Tools.packageLoad(files, filenames, response, tarFile, beLzd.getTdh());
             
             return;
           } 
         }
       } else if ("qdbg".equals(xzlx)) {
         map.put("itemname", "inventory");
         List<BeAccessory> list = this.beAccessoryService.findByLzdIdAndItemName2(map);
         String path = this.beUploadService.findById("002").getAddress();
         if (list != null) {
           if (list.size() == 1) {
             String tarfilename = ((BeAccessory)list.get(0)).getTarfilename();
             fileName = ((BeAccessory)list.get(0)).getResfilename();
             tarFile = new File(path, tarfilename);
           } else {
             List<File> files = new ArrayList<>();
             List<String> filenames = new ArrayList<>();
             Map<String, Integer> map1 = new HashMap<>();
             for (BeAccessory beAccessory : list) {
               String tarfilename = beAccessory.getTarfilename();
               String resfilename = beAccessory.getResfilename();
               Integer i = map1.get(resfilename);
               if (i == null) {
                 filenames.add(resfilename);
                 map1.put(resfilename, Integer.valueOf(1));
               } else {
                 String suffix = resfilename.substring(resfilename.lastIndexOf("."));
                 String prefix = resfilename.substring(0, resfilename.lastIndexOf("."));
                 filenames.add(String.valueOf(prefix) + "副本" + i + suffix);
               } 
               files.add(new File(path, tarfilename));
             } 
             fileName = "清单报告_" + beLzd.getTdh() + ".rar";
             tarFile = new File(path, fileName);
             Tools.packageLoad(files, filenames, response, tarFile, beLzd.getTdh());
             return;
           } 
         }
       } else if ("lhbg".equals(xzlx)) {
         map.put("itemname", "tallying");
         List<BeAccessory> list = this.beAccessoryService.findByLzdIdAndItemName2(map);
         String path = this.beUploadService.findById("003").getAddress();
         if (list != null) {
           if (list.size() == 1) {
             String tarfilename = ((BeAccessory)list.get(0)).getTarfilename();
             fileName = ((BeAccessory)list.get(0)).getResfilename();
             tarFile = new File(path, tarfilename);
           } else {
             List<File> files = new ArrayList<>();
             List<String> filenames = new ArrayList<>();
             Map<String, Integer> map1 = new HashMap<>();
             for (BeAccessory beAccessory : list) {
               String tarfilename = beAccessory.getTarfilename();
               String resfilename = beAccessory.getResfilename();
               Integer i = map1.get(resfilename);
               if (i == null) {
                 filenames.add(resfilename);
                 map1.put(resfilename, Integer.valueOf(1));
               } else {
                 String suffix = resfilename.substring(resfilename.lastIndexOf("."));
                 String prefix = resfilename.substring(0, resfilename.lastIndexOf("."));
                 filenames.add(String.valueOf(prefix) + "副本" + i + suffix);
               } 
               files.add(new File(path, tarfilename));
             } 
             fileName = "理货报告_" + beLzd.getTdh() + ".rar";
             tarFile = new File(path, fileName);
             Tools.packageLoad(files, filenames, response, tarFile, beLzd.getTdh());
             return;
           } 
         }
       } 
     }
     if (tarFile != null) {
       InputStream is = new FileInputStream(tarFile);
       int total = is.available();
       byte[] b = new byte[total];
       is.read(b, 0, total);
       is.close();
       fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
       response.reset();
       response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
       response.addHeader("Content-Length", total+"");
       response.setContentType("application/octet-stream;charset=UTF-8");
       OutputStream outputStream = new BufferedOutputStream((OutputStream)response.getOutputStream());
       outputStream.write(b);
       outputStream.flush();
       outputStream.close();
     } 
   }
 
   
   @RequestMapping({"/addZgxxUI"})
   public String addZgxxUI(int id, Model model) throws Exception {
     BeLzd beLzd = this.beLzdService.findById(Integer.valueOf(id));
     String ywbh = beLzd.getYwbh();
     Collect collect = this.collectService.findByYwbh(ywbh);
     BeQgf beQgf = this.beQgfService.findByPramid(collect.getId().intValue());
     BeLzdCustom beLzdCustom = new BeLzdCustom();
     BeanUtils.copyProperties(beLzd, beLzdCustom);
     model.addAttribute("beQgf", beQgf);
     model.addAttribute("beLzdCustom", beLzdCustom);
     return "addZgxx";
   }
   @RequestMapping({"/addZgxx"})
   @ResponseBody
   public int addZgxxUI(BeLzdQueryVo beLzdQueryVo) throws Exception {
     try {
       this.beLzdService.insertZgxx(beLzdQueryVo);
     } catch (Exception e) {
       return -1;
     } 
     return 1;
   }
 
   
   @RequestMapping({"/uploadBgUI"})
   public String uploadUI(int id, Model model) throws Exception {
     BeLzd beLzd = this.beLzdService.findById(Integer.valueOf(id));
     model.addAttribute("beLzd", beLzd);
     return "uploadBg";
   }
 
   
   @RequestMapping({"/updateFlagByIds"})
   @ResponseBody
   public int updateFlagByIds(String ids, int flag) {
     try {
       String[] str = ids.split(",");
       this.beLzdService.updateFlagByIds(str, flag);
     } catch (Exception e) {
       return -1;
     } 
     return 1;
   }
   
   @RequestMapping({"/uploadFjUI"})
   public String uploadFjUI(int id, Model model) throws Exception {
     BeLzd beLzd = this.beLzdService.findById(Integer.valueOf(id));
     model.addAttribute("beLzd", beLzd);
     return "uploadFj";
   }
 
 
   
   @RequestMapping({"/updateUI"})
   public String updateUI(int id, Model model) throws Exception {
     BeLzd beLzd = this.beLzdService.findById(Integer.valueOf(id));
     model.addAttribute("beLzd", beLzd);
     return "updateBeLzd";
   }
   @RequestMapping({"/update"})
   @ResponseBody
   public int update(BeLzdQueryVo beLzdQueryVo) {
     try {
       this.beLzdService.updateLzd(beLzdQueryVo);
     } catch (Exception e) {
       e.printStackTrace();
       return -1;
     } 
     return 1;
   }
 
   
   @RequestMapping({"/updateChsj"})
   @ResponseBody
   public int updateChsj(int id, String chsj) {
     try {
       BeLzd beLzd = this.beLzdService.findById(Integer.valueOf(id));
       beLzd.setChsj(chsj);
       this.beLzdService.update(beLzd);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return -1;
     } 
   }
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public int deleteByIds(int[] ids) throws Exception {
     try {
       this.beLzdService.deleteByIds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
     return 1;
   }
 }


