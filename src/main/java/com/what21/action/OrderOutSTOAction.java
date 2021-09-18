 package com.what21.action;
 
 import com.what21.model.OrderOutSTOCustom;
 import com.what21.model.OrderOutSTOQueryVo;
 import com.what21.model.PageQuery;
 import com.what21.result.DatagridResultInfo;
 import com.what21.service.OrderOutSTOService;
 import com.what21.util.GeneralResult;
 import java.io.File;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.ModelAndView;
 @Controller
 @RequestMapping({"/orderOutSTO"})
 public class OrderOutSTOAction
   extends BaseAction
 {
   @Autowired
   private OrderOutSTOService orderOutSTOService;
   
   @RequestMapping({"/findAll"})
   @ResponseBody
   public DatagridResultInfo findAll(OrderOutSTOQueryVo orderOutSTOQueryVo, int rows, int page, HttpSession session) throws Exception {
     this.orderOutSTOService.deleteNotNeed();
     String username = session.getAttribute("userName").toString();
     Integer userid = Integer.valueOf(Integer.parseInt(session.getAttribute("userId").toString()));
     
     if (!"admin".equals(username)) {
       OrderOutSTOCustom orderOutSTOCustom = (orderOutSTOQueryVo.getOrderOutSTOCustom() == null) ? new OrderOutSTOCustom() : orderOutSTOQueryVo.getOrderOutSTOCustom();
       orderOutSTOCustom.setUserid(userid);
       orderOutSTOQueryVo.setOrderOutSTOCustom(orderOutSTOCustom);
     } 
     PageQuery pageQuery = new PageQuery(page, rows);
     orderOutSTOQueryVo.setPageQuery(pageQuery);
     int total = this.orderOutSTOService.countAll(orderOutSTOQueryVo).intValue();
     List<OrderOutSTOCustom> list = this.orderOutSTOService.findAll(orderOutSTOQueryVo);
     DatagridResultInfo datagridResultInfo = new DatagridResultInfo();
     datagridResultInfo.setRows(list);
     datagridResultInfo.setTotal(total);
     return datagridResultInfo;
   }
 
 
 
   
   @RequestMapping({"/importList"})
   public ModelAndView importStojsQd(MultipartFile excelFile, HttpSession session) {
     Integer id = (Integer)session.getAttribute("stojsid");
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("/tools/commonMsg");
     GeneralResult result = new GeneralResult();
     try {
       String path = String.valueOf(System.getProperty("user.home")) + File.separator + "upload/";
       String fileName = "offline_" + System.currentTimeMillis() + ".xlsx";
       File targetFile = new File(path, fileName);
       if (!targetFile.exists()) {
         targetFile.mkdirs();
       }
       excelFile.transferTo(targetFile);
       result = this.orderOutSTOService.importOrderNew(id, String.valueOf(path) + fileName);
       modelAndView.addObject("result", result);
       return modelAndView;
     } catch (Exception ex) {
       ex.printStackTrace();
       result.setMessage(ex.getMessage());
       modelAndView.addObject("result", result);
       
       return modelAndView;
     } 
   } @RequestMapping({"/pushSTO"})
   @ResponseBody
   public List<String> pushSTO(int[] ids) throws Exception {
     return this.orderOutSTOService.pushSTO(ids);
   }
   @RequestMapping({"/pushkouAn"})
   @ResponseBody
   public List<String> pushkouAn(int[] ids, HttpServletRequest request) throws Exception {
     String totalWayBill = request.getParameter("totalWayBill");
     return this.orderOutSTOService.pushkouAn(ids, totalWayBill);
   }
   @RequestMapping({"/deleteByIds"})
   @ResponseBody
   public int deleteByIds(int[] ids) throws Exception {
     try {
       this.orderOutSTOService.deleteByIds(ids);
       return 1;
     } catch (Exception e) {
       e.printStackTrace();
       return 0;
     } 
   }
 }


