 package org.yu;
 
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 public class dom4jxml2forhto
 {
   public static void toxml() {
     String driver = "com.mysql.jdbc.Driver";
 
     
     String url = "jdbc:mysql://114.55.105.214:3306/ztz-zhengshi";
     
     String user = "root";
     
     String password = "ztgyl2016";
 
     
     try {
       Class.forName(driver);
       
       Connection con = DriverManager.getConnection(url, user, password);
       if (!con.isClosed()) {
         System.out.println("Succeeded connecting to the Database!");
       }
       Statement statement = con.createStatement();
       Statement statement2 = con.createStatement();
 
       
       String sql = " SELECT * from t_order_bonded  where txlogisticid like '%BSKX%'  and   auditstatus='2'";
       
       ResultSet rs = statement.executeQuery(sql);
       
       while (rs.next()) {
         System.out.println("ppp");
         
         Element root = DocumentHelper.createElement("mo");
         Document document = DocumentHelper.createDocument(root);
         root.addAttribute("version", "1.0.0");
         
         Element element1 = root.addElement("head");
         element1.addElement("businessType").addText("IMPORTBILL");
         
         Element element2 = root.addElement("body");
         Element element3 = element2.addElement("wayBillList");
         Element element4 = element3.addElement("wayBill");
         
         Element element5 = element4.addElement("jkfSign");
         element5.addElement("companyCode").addText("WL15041401");
         element5.addElement("businessNo").addText(rs.getString("mailNo"));
         element5.addElement("businessType").addText("IMPORTBILL");
         element5.addElement("declareType").addText("1");
         element5.addElement("cebFlag").addText("03");
         element5.addElement("note");
         Element element6 = element4.addElement("wayBillImportDto");
         element6.addElement("wayBill").addText(rs.getString("mailNo"));
 
         
         element6.addElement("packNo").addText("1");
         element6.addElement("grossWeight").addText("3");
         element6.addElement("netWeight").addText("1");
         element6.addElement("goodsName").addText(rs.getString("itemName"));
         element6.addElement("sendArea").addText("下沙出口加工区");
         element6.addElement("consigneeArea").addText(rs.getString("receiveManAddress"));
         element6.addElement("consigneeTel").addText(rs.getString("receiveManPhone"));
         element6.addElement("consignee").addText(rs.getString("receiveMan"));
         element6.addElement("consigneeAddress").addText(rs.getString("receiveManAddress"));
         element6.addElement("zipCode").addText("311321");
         element6.addElement("customsCode").addText("2991");
         element6.addElement("worth").addText(rs.getString("worth"));
         element6.addElement("importDateStr").addText(rs.getString("createTime"));
         element6.addElement("currCode").addText("142");
 
         
         element6.addElement("totalWayBill").addText("");
         element6.addElement("logisCompanyCode").addText("WL15041401");
         element6.addElement("logisCompanyName").addText("杭州百世网络技术有限公司");
         
         element6.addElement("insureAmount").addText("0");
         element6.addElement("feeAmount").addText("0");
         
         System.out.println(document.asXML());
         if (zjpost.toZJyd_jm(document.asXML()).indexOf("处理成功") != -1) {
           System.out.println("处理成功");
         }
       } 
 
       
       con.close();
     } catch (ClassNotFoundException e) {
       
       System.out.println("Sorry,can`t find the Driver!");
       e.printStackTrace();
     } catch (SQLException e) {
       
       e.printStackTrace();
     } catch (Exception e) {
       
       e.printStackTrace();
     } finally {
       System.out.println("数据库数据成功获取！！");
     } 
   }
   
   public static void main(String[] args) {
     toxml();
   }
 }


