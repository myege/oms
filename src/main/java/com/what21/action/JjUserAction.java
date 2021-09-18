 package com.what21.action;
 
 import com.what21.model.JjUser;
 import com.what21.model.JjUserVo;
 import com.what21.model.PageQuery;
 import com.what21.result.AjaxResult;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfoUtil;
 import com.what21.service.JjUserService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"jjUser"})
 public class JjUserAction
   extends BaseAction
 {
   @Autowired
   private JjUserService jjUserService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(int page, int rows, JjUserVo jjUserVo) throws Exception {
     PageQuery pageQuery = new PageQuery(page, rows);
     jjUserVo.setPageQuery(pageQuery);
     int total = this.jjUserService.count(jjUserVo);
     List<JjUser> list = this.jjUserService.findAll(jjUserVo);
     return ResultInfoUtil.createDatagrid(list, total);
   }
   @RequestMapping({"/editUi"})
   public String editUi(Model model, Integer id) {
     JjUser jjUser = this.jjUserService.findById(id);
     model.addAttribute("jjUser", jjUser);
     return "/jjuser/editjjUser";
   }
   
   @ResponseBody
   @RequestMapping({"/edit"})
   public int edit(JjUser jjUser) {
     int i = 0;
     if (jjUser.getId() == null) {
       i = this.jjUserService.save(jjUser);
     } else {
       i = this.jjUserService.updateById(jjUser);
     } 
     return i;
   }
   @RequestMapping({"/remove"})
   @ResponseBody
   public AjaxResult remove(String[] ids) {
     try {
       return toAjax(this.jjUserService.deleteByIds(ids));
     } catch (Exception e) {
       return error(e.getMessage());
     } 
   }
 }


