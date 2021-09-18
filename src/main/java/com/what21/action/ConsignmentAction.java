 package com.what21.action;
 
 import com.alibaba.fastjson.JSONObject;
 import com.what21.model.ConsignmentQueryVo;
 import com.what21.model.JjDelivery;
 import com.what21.model.JjPickingSku;
 import com.what21.service.ConsignmentService;
 import com.what21.tools.Tools;
 import java.io.IOException;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 
 @Controller
 @RequestMapping({"/consignment"})
 public class ConsignmentAction
   extends BaseAction
 {
   @Autowired
   private ConsignmentService consignmentService;
   
   @RequestMapping({"/consignmentEditUi"})
   public String consignmentEditUi() {
     return "/consignment/operate/consignmentEdit";
   }
   
   @RequestMapping({"findAll"})
   public void findAll(ConsignmentQueryVo consignmentQueryVo, HttpServletRequest request, HttpServletResponse response) throws IOException {
     int page = Integer.parseInt(request.getParameter("page"));
     int rows = Integer.parseInt(request.getParameter("rows"));
     consignmentQueryVo.setStartPage((page - 1) * rows);
     consignmentQueryVo.setEndPage(rows);
     List<JjDelivery> list = this.consignmentService.findAll(consignmentQueryVo);
     int total = this.consignmentService.findCount();
     Map<String, Object> map = new HashMap<>();
     map.put("rows", list);
     map.put("total", Integer.valueOf(total));
     String str = JSONObject.toJSONString(map);
     response.getWriter().write(str);
   }
   
   @RequestMapping({"inserDelivery"})
   @ResponseBody
   public Map<String, ConsignmentQueryVo> inserDelivery(JjDelivery delivery) throws Exception {
     Map<String, ConsignmentQueryVo> map = new HashMap<>();
     ConsignmentQueryVo consignmentQueryVo = new ConsignmentQueryVo();
     int j = this.consignmentService.checkBillInDelivery(delivery);
     if (j > 0) {
       consignmentQueryVo.setCode(500);
       consignmentQueryVo.setErroMsg("该物流单号已经发货");
     } else {
       JjPickingSku sku = this.consignmentService.checkBillInPickingSku(delivery);
       if (sku == null) {
         consignmentQueryVo.setCode(500);
         consignmentQueryVo.setErroMsg("该物流单号未拣货派单");
       } else {
         delivery.setPickname(sku.getPickname());
         delivery.setPick(sku.getPick());
         delivery.setFhtime(Tools.getCurrentTime());
         consignmentQueryVo.setDelivery(delivery);
         int i = this.consignmentService.insertJjDelivery(consignmentQueryVo);
         if (i > 0) {
           consignmentQueryVo.setCode(202);
           consignmentQueryVo.setErroMsg("发货成功");
         } else {
           consignmentQueryVo.setCode(500);
           consignmentQueryVo.setErroMsg("发货失败");
         } 
       } 
     } 
     map.put("consignmentQueryVo", consignmentQueryVo);
     return map;
   }
 }


