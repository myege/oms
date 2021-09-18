 package com.what21.util;
 
 import com.what21.model.TCustomsParam;
 import com.what21.tools.Tools;
 import java.sql.SQLException;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 
 
 
 public class ImportXxlsForCustomsParam
   extends XxlsAbstract
 {
   private List<TCustomsParam> list;
   private StringBuffer sb;
   private Date now;
   
   public ImportXxlsForCustomsParam() throws Exception {
     this.list = new ArrayList<>();
     
     this.sb = new StringBuffer();
     
     this.now = null;
     this.now = Tools.getCurrentTime();
   }
   public void optRows(int sheetIndex, int curRow, List<String> rowlist) throws SQLException {
     if (curRow != 0) {
 
       
       DecimalFormat df = new DecimalFormat("0.00");
       if (rowlist.size() != 11) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行的字段数量不一致 并且必须都有数值<br/>");
         return;
       } 
       String hscode = rowlist.get(0);
       String name = rowlist.get(1);
       String licencecode = rowlist.get(2);
       if (hscode == null) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行编码不能为空<br/>");
         return;
       } 
       if (name == null) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行名字不能为空<br/>");
         
         return;
       } 
       String ordinaryrate = rowlist.get(3);
       double d_ordinaryrate = 0.0D;
       try {
         d_ordinaryrate = Double.parseDouble(ordinaryrate);
       } catch (Exception e) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行普通税率并且必须为数字(可精确到两位小数)<br/>");
         return;
       } 
       String preferentialrate = rowlist.get(4);
       double d_preferentialrate = 0.0D;
       try {
         d_preferentialrate = Double.parseDouble(preferentialrate);
       } catch (Exception e) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行优惠税率并且必须为数字(可精确到两位小数)<br/>");
         
         return;
       } 
       String remark = rowlist.get(5);
       String exportrate = rowlist.get(6);
       double d_exportrate = 0.0D;
       try {
         d_exportrate = Double.parseDouble(exportrate);
       } catch (Exception e) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行出口税率并且必须为数字(可精确到两位小数)<br/>");
         
         return;
       } 
       String consumptionrate = rowlist.get(7);
       double d_consumptionrate = 0.0D;
       try {
         d_consumptionrate = Double.parseDouble(consumptionrate);
       } catch (Exception e) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行消费税率并且必须为数字(可精确到两位小数)<br/>");
         
         return;
       } 
       String valueaddedrate = rowlist.get(8);
       double d_valueaddedrate = 0.0D;
       try {
         d_valueaddedrate = Double.parseDouble(valueaddedrate);
       } catch (Exception e) {
         this.sb.append("导入失败，失败原因：第" + (curRow + 1) + "行增值税率并且必须为数字(可精确到两位小数)<br/>");
         
         return;
       } 
       String oneunitdesc = rowlist.get(9);
       String twounitdesc = rowlist.get(10);
       
       TCustomsParam customsParam = new TCustomsParam();
       customsParam.setCreatetime(this.now);
       customsParam.setHscode(hscode);
       customsParam.setName(name);
       customsParam.setLicencecode(licencecode);
       customsParam.setOrdinaryrate(df.format(d_ordinaryrate));
       customsParam.setPreferentialrate(df.format(d_preferentialrate));
       customsParam.setRemark(remark);
       customsParam.setExportrate(df.format(d_exportrate));
       customsParam.setConsumptionrate(df.format(d_consumptionrate));
       customsParam.setValueaddedrate(df.format(d_valueaddedrate));
       customsParam.setOneunitdesc(oneunitdesc);
       customsParam.setTwounitdesc(twounitdesc);
       this.list.add(customsParam);
     } 
   }
 
 
   
   public StringBuffer getSb() {
     return this.sb;
   }
 
   
   public void setSb(StringBuffer sb) {
     this.sb = sb;
   }
 
   
   public List<TCustomsParam> getList() {
     return this.list;
   }
 
   
   public void setList(List<TCustomsParam> list) {
     this.list = list;
   }
 
   
   public static void main(String[] args) throws Exception {
     ImportXxlsForCustomsParam howto = new ImportXxlsForCustomsParam();
     howto.processOneSheet("C:/Users/Administrator/Desktop/sss.xlsx", 1);
   }
 }


