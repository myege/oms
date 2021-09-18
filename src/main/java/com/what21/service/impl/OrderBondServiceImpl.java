 package com.what21.service.impl;
 
 import com.what21.dao.CompanyDao;
 import com.what21.dao.OrderBondDao;
 import com.what21.dao.UserDao;
 import com.what21.model.Company;
 import com.what21.model.OrderBond;
 import com.what21.model.OrderBondSku;
 import com.what21.model.Users;
 import com.what21.service.OrderBondService;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 @Service
 public class OrderBondServiceImpl
   implements OrderBondService
 {
   @Autowired
   private OrderBondDao orderBondDao;
   @Autowired
   private CompanyDao companyDao;
   @Autowired
   private UserDao UserDao;
   
   public int save(OrderBond orderBond) {
     if (this.orderBondDao.findCom(orderBond.getCompanybm()) == null) {
       if (orderBond.getSurplusMamey() == null) {
         orderBond.setSurplusMamey(orderBond.getBondManey());
         orderBond.setFrostMamey("0");
         orderBond.setUseManey("0");
       } 
       return this.orderBondDao.save(orderBond);
     } 
     return 0;
   }
 
 
 
   
   public int delete(int id) {
     return this.orderBondDao.delete(id);
   }
 
   
   public OrderBond findObjectById(String id) {
     return this.orderBondDao.findObjectById(id);
   }
 
   
   public List<OrderBond> findAll(Map<String, Object> map) {
     return this.orderBondDao.findAll(map);
   }
 
   
   public Integer count(Map<String, Object> map) {
     return this.orderBondDao.count(map);
   }
 
   
   public int update(OrderBond orderBond) {
     OrderBond bond = this.orderBondDao.findCom(orderBond.getCompanybm());
     BigDecimal bondMoney = new BigDecimal(orderBond.getBondManey());
     BigDecimal money = new BigDecimal(bond.getBondManey());
     BigDecimal surplusMamey = new BigDecimal(bond.getSurplusMamey());
     bond.setBondManey(bondMoney.add(money).toString());
     bond.setSurplusMamey(surplusMamey.add(bondMoney).toString());
 
     
     OrderBondSku orderBondSku = new OrderBondSku();
     Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String creatTime = formatter.format(date);
     orderBondSku.setCreatTime(creatTime);
     orderBondSku.setCompanybm(orderBond.getCompanybm());
     orderBondSku.setSyMamey(surplusMamey.add(bondMoney).toString());
     orderBondSku.setUpMamey(bondMoney.toString());
     
     BigDecimal bbMoney = new BigDecimal(bond.getBondManey());
     int compareTo = bbMoney.compareTo(new BigDecimal("0"));
     if (compareTo == -1) {
       return -1;
     }
     
     int i = this.orderBondDao.saveBm(orderBondSku);
     int j = this.orderBondDao.updete(bond);
 
     
     return i + j;
   }
   
   public List<OrderBondSku> findBm(Map<String, Object> map) {
     return this.orderBondDao.findBm(map);
   }
 
   
   public int countBm(Map<String, Object> map) {
     return this.orderBondDao.countBm(map);
   }
 
   
   public int saveBm(OrderBondSku orderBondSku) {
     return this.orderBondDao.saveBm(orderBondSku);
   }
 
   
   public void updateSurplus(OrderBond orderBond) {
     this.orderBondDao.updateSurplus(orderBond);
   }
 
   
   public int frostAndF(OrderBond orderBond, double updateMamey) {
     OrderBond bond = this.orderBondDao.findCom(orderBond.getCompanybm());
     BigDecimal upMoney = new BigDecimal((new StringBuilder(String.valueOf(updateMamey))).toString());
     BigDecimal surplusMamey = new BigDecimal(bond.getSurplusMamey());
     BigDecimal frostMoney = new BigDecimal(bond.getFrostMamey());
     String sm = surplusMamey.subtract(upMoney).toString();
     String fm = frostMoney.add(upMoney).toString();
     bond.setSurplusMamey(sm);
     bond.setFrostMamey(fm);
     return this.orderBondDao.frostAndF(bond);
   }
 
   
   public List<Company> findByCompany() {
     return this.companyDao.findByCompany();
   }
 
   
   public Company findByCompanyBm(String company) {
     return this.companyDao.findByCompanyBm(company);
   }
 
   
   public List<Users> findByUserId() {
     return this.UserDao.findByUserId();
   }
 
   
   public int deleteBm(String companybm) {
     return this.orderBondDao.deleteBm(companybm);
   }
 }


