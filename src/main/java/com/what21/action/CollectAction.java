 package com.what21.action;
 
 import com.what21.model.Collect;
 import com.what21.model.CollectCustom;
 import com.what21.model.CollectQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.CollectService;
 import com.what21.tools.Tools;
 import java.util.Date;
 import java.util.List;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang.StringUtils;
 import org.springframework.beans.BeanUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/collect"})
 public class CollectAction
   extends BaseAction
 {
   @Autowired
   private CollectService collectService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int rows, int page, CollectQueryVo collectQueryVo) throws Exception {
     collectQueryVo = (collectQueryVo == null) ? new CollectQueryVo() : collectQueryVo;
     CollectCustom collectCustom = collectQueryVo.getCollectCustom();
     collectCustom = (collectCustom == null) ? new CollectCustom() : collectCustom;
     collectCustom.setZyflag(Integer.valueOf(1));
     collectQueryVo.setCollectCustom(collectCustom);
     PageQuery pageQuery = new PageQuery(page, rows);
     collectQueryVo.setPageQuery(pageQuery);
     int total = this.collectService.count(collectQueryVo);
     List<Collect> list = this.collectService.findAll(collectQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setTotal(total);
     datagridResultInfo.setRows(list);
     return datagridResultInfo;
   }
 
 
 
   
   @RequestMapping({"/exportCollect"})
   public void exportCollect(int[] ids, HttpServletResponse response) throws Exception {}
 
 
 
   
   @ResponseBody
   @RequestMapping({"/deleteById"})
   public int deleteById(@RequestParam(value = "ids", required = true) int[] ids, HttpServletResponse response) throws Exception {
     try {
       this.collectService.deleteByIds(ids);
     } catch (Exception e) {
       e.printStackTrace();
       return -1;
     } 
     return 1;
   }
   
   @RequestMapping({"/findOne"})
   public String findOne(int id, Model model) throws Exception {
     Collect collect = this.collectService.findById(id);
     CollectCustom collectCustom = new CollectCustom();
     BeanUtils.copyProperties(collect, collectCustom);
     String strWtrq = collectCustom.getWtrq();
     if (StringUtils.isNotEmpty(strWtrq)) {
       Date dWtrq = Tools.parse("yyyy-MM-dd", strWtrq);
       strWtrq = Tools.format("yyyy-MM-dd", dWtrq);
       collectCustom.setStrWtrq(strWtrq);
     } 
     model.addAttribute("collectCustom", collectCustom);
     return "queryCollect";
   }
   
   @RequestMapping({"/addUI"})
   public String addUI() {
     return "addCollect";
   }
   @ResponseBody
   @RequestMapping({"/add"})
   public int add(CollectQueryVo collectQueryVo) throws Exception {
     CollectCustom collectCustom = collectQueryVo.getCollectCustom();
     if (collectCustom != null) {
       String sj = Tools.format("yyyy-MM-dd HH:mm:ss", new Date());
       String strWtrq = collectCustom.getStrWtrq();
       if (strWtrq != null && !"".equals(strWtrq)) {
         Date wtrq = Tools.parse("yyyy-MM-dd HH:mm:ss", strWtrq);
         strWtrq = Tools.format("yyyy-MM-dd HH:mm:ss", wtrq);
         collectCustom.setWtrq(strWtrq);
       } 
       collectCustom.setSj(sj);
       this.collectService.insert((Collect)collectCustom);
       return 1;
     } 
     return -1;
   }
   
   @RequestMapping({"/findAllBs"})
   @ResponseBody
   public DatagridResultInfo findAllBs(int rows, int page, CollectQueryVo collectQueryVo) throws Exception {
     collectQueryVo = (collectQueryVo == null) ? new CollectQueryVo() : collectQueryVo;
     CollectCustom collectCustom = collectQueryVo.getCollectCustom();
     collectCustom = (collectCustom == null) ? new CollectCustom() : collectCustom;
     collectCustom.setBsflag(Integer.valueOf(1));
     collectQueryVo.setCollectCustom(collectCustom);
     PageQuery pageQuery = new PageQuery(page, rows);
     collectQueryVo.setPageQuery(pageQuery);
     int total = this.collectService.count(collectQueryVo);
     List<Collect> list = this.collectService.findAll(collectQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setTotal(total);
     datagridResultInfo.setRows(list);
     return datagridResultInfo;
   }
   
   @RequestMapping({"/addBsjsUI"})
   public String addBsjsUI() throws Exception {
     return "addBsjs";
   }
   @RequestMapping({"/addBsjs"})
   @ResponseBody
   public int addBsjs(CollectQueryVo collectQueryVo) {
     try {
       this.collectService.insertBsjs(collectQueryVo);
     } catch (Exception e) {
       return -1;
     } 
     return 1;
   }
 }


