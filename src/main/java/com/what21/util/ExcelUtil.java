 package com.what21.util;
 
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.text.DecimalFormat;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.poi.xssf.usermodel.XSSFCell;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 public class ExcelUtil
 {
   public static Map<Integer, Object[]> read(String xlsPath) {
     try {
       InputStream is = new FileInputStream(xlsPath);
       XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
       
       Map<Integer, Object[]> items = (Map)new HashMap<>();
       int cellNum = 0;
       
       for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
         
         XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if (hssfSheet != null)
         {
 
 
           
           for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
             
             try {
               XSSFRow row = hssfSheet.getRow(rowNum);
               
               if (row != null)
               
               { 
                 if (rowNum == 0) {
                   cellNum = row.getLastCellNum();
                 }
                 Object[] item = new Object[cellNum];
 
                 
                 for (int i = 0; i < cellNum; i++) {
                   
                   XSSFCell cell = row.getCell(i);
                   if (cell != null) {
                     item[i] = getValue(cell);
                   }
                 } 
                 
                 items.put(Integer.valueOf(rowNum), item); } 
             } catch (Exception e) {
               e.printStackTrace();
               throw new IllegalArgumentException("第" + rowNum + "行数据格式错误！");
             } 
           } 
         }
       } 
       return items;
     } catch (Exception e) {
       throw new IllegalArgumentException("读取Excel文件失败，请确保格式正确工整！");
     } 
   }
   
   public static Map<Integer, Object[]> read2(String xlsPath) {
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
                       cell.setCellType(1);
                       item[i] = cell.getStringCellValue();
                     } 
                   } 
                   items.put(Integer.valueOf(rowNum), item); } 
               } catch (Exception e) {
                 e.printStackTrace();
                 message = e.getMessage();
                 throw new IllegalArgumentException("第" + rowNum + "行数据格式错误！ ");
               } 
             }  } 
         } 
       } 
       return items;
     } catch (Exception e) {
       throw new IllegalArgumentException("读取Excel文件失败，请确保格式正确工整！ " + message);
     } 
   }
   
   public static Map<Integer, Object[]> read_Item(String xlsPath) {
     try {
       InputStream is = new FileInputStream(xlsPath);
       XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
       
       Map<Integer, Object[]> items = (Map)new HashMap<>();
       int cellNum = 0;
       
       for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
         
         XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if (hssfSheet != null)
         {
 
 
           
           for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
             
             try {
               XSSFRow row = hssfSheet.getRow(rowNum);
               
               if (row != null)
               
               { 
                 if (rowNum == 0) {
                   cellNum = row.getLastCellNum();
                 }
                 Object[] item = new Object[cellNum];
 
                 
                 for (int i = 0; i < cellNum; i++) {
                   XSSFCell cell = row.getCell(i);
                   
                   if (cell != null)
                   {
                     if (i == 10 || i == 11) {
                       item[i] = getDoubleValue(cell);
                     } else {
                       item[i] = getValue(cell);
                     } 
                   }
                 } 
                 
                 items.put(Integer.valueOf(rowNum), item); } 
             } catch (Exception e) {
               e.printStackTrace();
               throw new IllegalArgumentException("第" + rowNum + "行数据格式错误！");
             } 
           } 
         }
       } 
       return items;
     } catch (Exception e) {
       throw new IllegalArgumentException("读取Excel文件失败，请确保格式正确工整！");
     } 
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


