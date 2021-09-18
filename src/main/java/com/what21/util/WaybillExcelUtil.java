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
 public class WaybillExcelUtil
 {
   public static Map<Integer, Object[]> read(String xlsPath) {
     try {
       InputStream is = new FileInputStream(xlsPath);
       XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
       
       Map<Integer, Object[]> items = (Map)new HashMap<>();
 
 
       
       for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
         
         XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if (hssfSheet != null)
         {
 
 
           
           for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
 
             
             XSSFRow row = hssfSheet.getRow(rowNum);
             
             if (row != null) {
 
 
 
               
               Object[] item = new Object[hssfSheet.getRow(0).getLastCellNum()];
 
 
               
               for (int i = 0; i < hssfSheet.getRow(0).getLastCellNum(); i++) {
                 
                 XSSFCell cell = row.getCell(i);
 
                 
                 if (cell == null) {
                   item[i] = null;
                 } else if (cell.toString().trim().isEmpty()) {
                   item[i] = getValue(cell);
                 }
                 else {
                   
                   String str2 = getValue(cell).replaceAll(" ", "");
                   
                   item[i] = str2;
                 } 
               } 
 
               
               items.put(Integer.valueOf(rowNum), item);
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
 }


