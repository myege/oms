 package com.what21.action;
 
 import com.what21.model.Address;
 import com.what21.model.Tree;
 import com.what21.service.AddressService;
 import java.util.ArrayList;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import net.sf.json.JSONArray;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 @Controller
 @RequestMapping({"/area"})
 public class AddressAction
   extends BaseAction
 {
   @Autowired
   private AddressService addressService;
   
   @RequestMapping({"/addAdress"})
   @ResponseBody
   public String addAdress(HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception {
     List<Address> list = null;
     List<Tree> trees = null;
     if (id == null || "".equals(id)) {
       list = this.addressService.getProvincesOrChildren();
       trees = new ArrayList<>();
       for (Address address : list) {
         Tree tree = new Tree(address.getId().toString(), new String(address.getName()), this.addressService.findChild(address.getId().longValue()), false);
         trees.add(tree);
       } 
     } else {
       list = this.addressService.getProvincesOrChildren(id.intValue());
       trees = new ArrayList<>();
       for (Address address : list) {
         Tree tree = new Tree(address.getId().toString(), new String(address.getName()), this.addressService.findChild(address.getId().longValue()), false);
         trees.add(tree);
       } 
     } 
     
     response.addHeader("Access-Control-Allow-Origin", "*");
     return JSONArray.fromObject(trees).toString();
   }
   
   @RequestMapping({"/appendAddress"})
   @ResponseBody
   public String addAdress(HttpServletRequest request, HttpServletResponse response, Long parent_id, String name) throws Exception {
     System.out.println(String.valueOf(name) + "-----------" + parent_id);
     Address address = new Address();
     address.setParent_id(parent_id);
     address.setName(name);
     this.addressService.add(address);
     
     response.addHeader("Access-Control-Allow-Origin", "*");
     return address.getId().toString();
   }
   
   @RequestMapping({"/removeAdress"})
   @ResponseBody
   public String removeAdress(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
     int result = this.addressService.delete(id.longValue());
     response.addHeader("Access-Control-Allow-Origin", "*");
     return (new StringBuilder(String.valueOf(result))).toString();
   }
   
   @RequestMapping({"/updateAddress"})
   @ResponseBody
   public String updateAddress(HttpServletRequest request, HttpServletResponse response, Long id, String name) throws Exception {
     System.out.println(String.valueOf(name) + "------*******-----" + id);
     Address address = new Address();
     address.setId(id);
     address.setName(name);
     
     int result = this.addressService.update(address);
     
     response.addHeader("Access-Control-Allow-Origin", "*");
     return (new StringBuilder(String.valueOf(result))).toString();
   }
 }


