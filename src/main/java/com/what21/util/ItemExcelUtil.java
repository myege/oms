 package com.what21.util;
 
 import com.what21.tools.Tools;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.poi.xssf.usermodel.XSSFCell;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
 
 
 public class ItemExcelUtil
 {
   public static Map<Integer, Object[]> read(String xlsPath) {
     String message = "";
     try {
       InputStream is = new FileInputStream(xlsPath);
       XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
       
       Map<Integer, Object[]> items = (Map)new HashMap<>();
       int cellNum = 0;
       
       for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
         
         XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if (hssfSheet != null) {
 
           
           int lastRowNum = hssfSheet.getLastRowNum();
           
           if (lastRowNum != 0)
           {
             
             for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
               
               try {
                 XSSFRow row = hssfSheet.getRow(rowNum);
                 if (rowNum == 0)
                 { cellNum = row.getLastCellNum();
 
                   
                    }
                 
                 else if (row != null)
                 
                 { 
                   Object[] item = new Object[cellNum];
                   
                   for (int i = 0; i < cellNum; i++) {
                     XSSFCell cell = row.getCell(i);
                     if (cell != null) {
                       if (i == 8) {
                         String str = getValue(cell);
                         SimpleDateFormat sdf = null;
 
                         
                         Tools.parse("yyyy-MM-dd HH:mm:ss", str);
                         
                         item[8] = str;
                       }
                       else if (i == 16) {
                         String str = getValue(cell);
                         SimpleDateFormat sdf = null;
 
                         
                         Tools.parse("yyyy-MM-dd HH:mm:ss", str);
                         
                         item[16] = str;
                       } else {
                         
                         item[i] = getValue(cell);
                       } 
                     }
                   } 
                   
                   items.put(Integer.valueOf(rowNum), item); } 
               } catch (Exception e) {
                 e.printStackTrace();
                 message = "第<font color='red'>" + rowNum + "</font>行时间格式时间格式为yyyy-MM-dd HH:mm:ss错误，请更正后重试";
                 throw new IllegalArgumentException("第" + rowNum + "行数据格式错误！ ");
               } 
             } 
           }
         } 
       } 
       return items;
     } catch (Exception e) {
       throw new IllegalArgumentException("读取Excel文件失败， " + message);
     } 
   }
 
   
   public static Map<Integer, Object[]> readForGK(String xlsPath) {
     String message = "";
     try {
       InputStream is = new FileInputStream(xlsPath);
       XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
       
       Map<Integer, Object[]> items = (Map)new HashMap<>();
       int cellNum = 0;
       DecimalFormat decimalFormat = new DecimalFormat("#.000");
       DecimalFormat cf = new DecimalFormat("0.00");
       
       for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
         
         XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if (hssfSheet != null) {
 
           
           int lastRowNum = hssfSheet.getLastRowNum();
           
           if (lastRowNum != 0)
           {
             
             for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
               
               try {
                 XSSFRow row = hssfSheet.getRow(rowNum);
                 if (rowNum == 0)
                 { cellNum = row.getLastCellNum();
 
                   
                    }
                 
                 else if (row != null)
                 
                 { 
                   Object[] item = new Object[cellNum];
                   
                   for (int i = 0; i < cellNum; i++) {
                     XSSFCell cell = row.getCell(i);
 
                     
                     if (cell != null) {
                       if (i == 1) {
                         String doubleNum = getDoubleValue(cell);
                         
                         item[i] = doubleNum;
                       } else if (i == 2) {
                         String doubleNum = getThreeValue(cell);
                         item[i] = doubleNum;
                       } else {
                         item[i] = getValue(cell);
                       } 
                     }
                   } 
 
                   
                   items.put(Integer.valueOf(rowNum), item); } 
               } catch (Exception e) {
                 e.printStackTrace();
                 message = "第<font color='red'>" + rowNum + "</font>行错误，请更正后重试";
                 throw new IllegalArgumentException("第" + rowNum + "行数据格式错误！ ");
               } 
             } 
           }
         } 
       } 
       return items;
     } catch (Exception e) {
       throw new IllegalArgumentException("读取Excel文件失败， " + message);
     } 
   }
   
   private static Date getDateValue(XSSFCell cell) {
     Date date = null;
     try {
       date = cell.getDateCellValue();
     } catch (Exception e) {
       throw new IllegalArgumentException("日期格式不对");
     } 
     return date;
   }
   
   public static String getValue(XSSFCell cell) {
     DecimalFormat df = new DecimalFormat("0");
     if (cell.getCellType() == 4)
       return String.valueOf(cell.getBooleanCellValue()); 
     if (cell.getCellType() == 0)
       return String.valueOf(df.format(cell.getNumericCellValue())); 
     if (cell.getCellType() == 3) {
       return null;
     }
     return String.valueOf(cell.getStringCellValue());
   }
   
   public static String getDoubleValue(XSSFCell cell) {
     DecimalFormat df = new DecimalFormat("0.00");
     if (cell.getCellType() == 4)
       return String.valueOf(cell.getBooleanCellValue()); 
     if (cell.getCellType() == 0)
       return String.valueOf(df.format(cell.getNumericCellValue())); 
     if (cell.getCellType() == 3) {
       return null;
     }
     return String.valueOf(cell.getStringCellValue());
   }
   
   public static String getThreeValue(XSSFCell cell) {
     DecimalFormat df = new DecimalFormat("0.000");
     if (cell.getCellType() == 4)
       return String.valueOf(cell.getBooleanCellValue()); 
     if (cell.getCellType() == 0)
       return String.valueOf(df.format(cell.getNumericCellValue())); 
     if (cell.getCellType() == 3) {
       return null;
     }
     return String.valueOf(cell.getStringCellValue());
   }
 }


