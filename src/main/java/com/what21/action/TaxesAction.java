 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.Taxes;
 import com.what21.service.TaxesService;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 @Controller
 @RequestMapping({"/taxes"})
 public class TaxesAction
   extends BaseAction
 {
   @Autowired
   private TaxesService taxesService;
   
   @RequestMapping({"/query"})
   public void query(HttpServletResponse response, HttpServletRequest request) throws Exception {
     String n = request.getParameter("tax_a");
     String merchantnum = request.getParameter("tax_b");
     
     Map<String, Object> map = new HashMap<>();
     map.put("tax_a", n);
     map.put("tax_b", merchantnum);
     
     List<Taxes> resa = this.taxesService.MByY(map);
     String str = JSONObject.toJSONString(resa);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"/zsjz"})
   public void zsjz(HttpServletResponse response, HttpServletRequest request) throws Exception {
     String n = request.getParameter("tax_a");
     String merchantnum = request.getParameter("tax_b");
     
     Map<String, Object> map = new HashMap<>();
     map.put("tax_a", n);
     map.put("tax_b", merchantnum);
     
     List<Taxes> resb = this.taxesService.zsj(map);
     String sra = JSONObject.toJSONString(resb);
     response.getWriter().write(sra);
   }
   
   @RequestMapping({"/ssstjsj"})
   public void ssstjsj(HttpServletResponse response, HttpServletRequest request) throws Exception {
     Map<String, Object> map = new HashMap<>();
     Taxes taxes = new Taxes();
     List<Taxes> resa = this.taxesService.ddsj(map);
     for (Taxes n : resa) {
       taxes.setTxLogisticID(n.getTxLogisticID());
       n.getTxLogisticID();
       taxes.setMul(n.getMul());
       taxes.setItemsku(n.getItemsku());
       this.taxesService.updatesj(taxes);
     } 
     
     List<Taxes> refz = this.taxesService.ddfz(map);
     for (Taxes k : refz) {
       
       taxes.setTxLogisticID(k.getTxLogisticID());
       taxes.setZmul(k.getZmul());
       this.taxesService.updatetjsj(taxes);
     } 
   }
 }


