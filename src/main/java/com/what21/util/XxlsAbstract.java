 package com.what21.util;
 
 import java.io.InputStream;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import org.apache.poi.openxml4j.opc.OPCPackage;
 import org.apache.poi.xssf.eventusermodel.XSSFReader;
 import org.apache.poi.xssf.model.SharedStringsTable;
 import org.apache.poi.xssf.usermodel.XSSFRichTextString;
 import org.xml.sax.Attributes;
 import org.xml.sax.InputSource;
 import org.xml.sax.SAXException;
 import org.xml.sax.XMLReader;
 import org.xml.sax.helpers.DefaultHandler;
 import org.xml.sax.helpers.XMLReaderFactory;
 public abstract class XxlsAbstract
   extends DefaultHandler
 {
   private SharedStringsTable sst;
   private String lastContents;
   private boolean nextIsString;
   private int sheetIndex = -1;
   private List<String> rowlist = new ArrayList<>();
   private int curRow = 0;
   private int curCol = 0;
 
 
 
   
   public abstract void optRows(int paramInt1, int paramInt2, List<String> paramList) throws SQLException;
 
 
   
   public void processOneSheet(String filename, int sheetId) throws Exception {
     OPCPackage pkg = OPCPackage.open(filename);
     XSSFReader r = new XSSFReader(pkg);
     SharedStringsTable sst = r.getSharedStringsTable();
     
     XMLReader parser = fetchSheetParser(sst);
 
 
     
     InputStream sheet2 = r.getSheet("rId" + sheetId);
     this.sheetIndex++;
     InputSource sheetSource = new InputSource(sheet2);
     parser.parse(sheetSource);
     sheet2.close();
   }
 
 
 
   
   public void process(String filename) throws Exception {
     OPCPackage pkg = OPCPackage.open(filename);
     XSSFReader r = new XSSFReader(pkg);
     SharedStringsTable sst = r.getSharedStringsTable();
     
     XMLReader parser = fetchSheetParser(sst);
     
     Iterator<InputStream> sheets = r.getSheetsData();
     while (sheets.hasNext()) {
       this.curRow = 0;
       this.sheetIndex++;
       InputStream sheet = sheets.next();
       InputSource sheetSource = new InputSource(sheet);
       parser.parse(sheetSource);
       sheet.close();
     } 
   }
 
   
   public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
     XMLReader parser = 
       XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
     this.sst = sst;
     parser.setContentHandler(this);
     return parser;
   }
 
 
   
   public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
     if (name.equals("c")) {
       
       String cellType = attributes.getValue("t");
       if (cellType != null && cellType.equals("s")) {
         this.nextIsString = true;
       } else {
         this.nextIsString = false;
       } 
     } 
     
     this.lastContents = "";
   }
 
 
 
   
   public void endElement(String uri, String localName, String name) throws SAXException {
     if (this.nextIsString) {
       try {
         int idx = Integer.parseInt(this.lastContents);
         this.lastContents = (new XSSFRichTextString(this.sst.getEntryAt(idx)))
           .toString();
       } catch (Exception exception) {}
     }
     
     if (name.equals("v")) {
       String value = this.lastContents.trim();
       value = value.equals("") ? " " : value;
       this.rowlist.add(this.curCol, value);
       this.curCol++;
     
     }
     else if (name.equals("row")) {
       try {
         optRows(this.sheetIndex, this.curRow, this.rowlist);
       } catch (SQLException e) {
         e.printStackTrace();
       } 
       this.rowlist.clear();
       this.curRow++;
       this.curCol = 0;
     } 
   }
 
 
 
   
   public void characters(char[] ch, int start, int length) throws SAXException {
     this.lastContents = String.valueOf(this.lastContents) + new String(ch, start, length);
   }
 }


