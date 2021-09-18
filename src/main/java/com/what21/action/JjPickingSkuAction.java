 package com.what21.action;
 
 import com.what21.model.JjPickingSku;
 import com.what21.model.JjPickingSkuVo;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.JjPickingSkuService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @RequestMapping({"/jjPickingSku"})
 @Controller
 public class JjPickingSkuAction
   extends BaseAction
 {
   @Autowired
   private JjPickingSkuService jjPickingSkuService;
   
   @RequestMapping({"findSkuUi"})
   public String findSkuUi(String pick, Model model) {
     model.addAttribute("pick", pick);
     return "/picking/findsku";
   }
   @RequestMapping({"/findSku"})
   @ResponseBody
   public DatagridResultInfo findSku(int page, int rows, JjPickingSkuVo jjPickingSkuVo) {
     PageQuery pageQuery = new PageQuery(page, rows);
     jjPickingSkuVo.setPageQuery(pageQuery);
     int total = this.jjPickingSkuService.count(jjPickingSkuVo);
     List<JjPickingSku> list = this.jjPickingSkuService.findAll(jjPickingSkuVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
 }


