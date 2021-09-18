 package com.what21.action;
 
 import com.what21.model.CustomsDepositCustom;
 import com.what21.model.CustomsDepositQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.model.TCustomsDeposit;
 import com.what21.result.DatagridResultInfo;
 import com.what21.result.ResultInfo;
 import com.what21.service.CustomsDepositService;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 @Controller
 @RequestMapping({"/customsDeposit"})
 public class CustomsDepositAction
   extends BaseAction
 {
   @Autowired
   private CustomsDepositService customsDepositService;
   
   @RequestMapping({"/find"})
   @ResponseBody
   public DatagridResultInfo findAll(int rows, int page, CustomsDepositQueryVo customDepositQueryVo) throws Exception {
     customDepositQueryVo = (customDepositQueryVo == null) ? new CustomsDepositQueryVo() : customDepositQueryVo;
     PageQuery pageQuery = new PageQuery(page, rows);
     customDepositQueryVo.setPageQuery(pageQuery);
     int total = this.customsDepositService.count(customDepositQueryVo).intValue();
     List<CustomsDepositCustom> list = this.customsDepositService.find(customDepositQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
   
   @RequestMapping({"/addUI"})
   public String addUI() {
     return "addCustomsDeposit";
   }
   @RequestMapping({"/add"})
   @ResponseBody
   public ResultInfo add(CustomsDepositQueryVo customDepositQueryVo) throws Exception {
     try {
       return this.customsDepositService.insert((TCustomsDeposit)customDepositQueryVo.getCustomsDepositCustom());
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
 
   
   @RequestMapping({"/topUp"})
   @ResponseBody
   public ResultInfo topUp(CustomsDepositCustom customsDepositCustom) throws Exception {
     try {
       return this.customsDepositService.topUp(customsDepositCustom);
     } catch (Exception e) {
       e.printStackTrace();
       return new ResultInfo(0, "未知错误！");
     } 
   }
 }


