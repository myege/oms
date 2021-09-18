 package com.what21.util;
 
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.math.RoundingMode;
 import java.text.DecimalFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.poi.xssf.usermodel.XSSFCell;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 public class BeLzdOutExcelUtil
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
               
               XSSFRow row = hssfSheet.getRow(rowNum);
               if (rowNum == 0) {
                 cellNum = row.getLastCellNum();
 
 
               
               }
               else if (row != null) {
 
                 
                 Object[] item = new Object[cellNum];
                 
                 for (int i = 0; i < cellNum; i++) {
                   XSSFCell cell = row.getCell(i);
                   if (cell != null) {
                     if (i == 9) {
                       item[i] = getDoubleValue(cell);
                     } else if (i == 14) {
                       item[i] = getDoubleValue2(cell);
                     } else {
                       item[i] = getValue(cell);
                     } 
                   }
                 } 
                 
                 items.put(Integer.valueOf(rowNum), item);
               } 
             }  } 
         } 
       }  return items;
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
     DecimalFormat df = new DecimalFormat("0.000");
     df.setRoundingMode(RoundingMode.HALF_UP);
     if (cell.getCellType() == 4)
       return String.valueOf(cell.getBooleanCellValue()); 
     if (cell.getCellType() == 0)
       return String.valueOf(df.format(cell.getNumericCellValue())); 
     if (cell.getCellType() == 3) {
       return null;
     }
     return String.valueOf(cell.getStringCellValue());
   }
 
 
   
   public static String getDoubleValue2(XSSFCell cell) {
     DecimalFormat df = new DecimalFormat("0.00");
     df.setRoundingMode(RoundingMode.HALF_UP);
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


