 package wms;
 
 import gnu.io.CommPort;
 import gnu.io.CommPortIdentifier;
 import gnu.io.NoSuchPortException;
 import gnu.io.PortInUseException;
 import gnu.io.SerialPort;
 import gnu.io.SerialPortEvent;
 import gnu.io.SerialPortEventListener;
 import gnu.io.UnsupportedCommOperationException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.Enumeration;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Observable;
 import java.util.TooManyListenersException;
 
 
 public class SerialReader
   extends Observable
   implements Runnable, SerialPortEventListener
 {
   static CommPortIdentifier portId;
   int delayRead = 200;
   int numBytes;
   private static byte[] readBuffer = new byte[4096];
   
   static Enumeration portList;
   
   InputStream inputStream;
   
   SerialPort serialPort;
   
   HashMap serialParams;
   
   public static final String PARAMS_DELAY = "delay read";
   
   public static final String PARAMS_TIMEOUT = "timeout";
   
   public static final String PARAMS_PORT = "port name";
   
   public static final String PARAMS_DATABITS = "data bits";
   
   public static final String PARAMS_STOPBITS = "stop bits";
   
   public static final String PARAMS_PARITY = "parity";
   
   public static final String PARAMS_RATE = "rate";
   private String temp1;
   
   private void init() {
     try {
       int timeout = Integer.parseInt(this.serialParams.get("timeout")
           .toString());
       int rate = Integer.parseInt(this.serialParams.get("rate")
           .toString());
       int dataBits = Integer.parseInt(this.serialParams.get("data bits")
           .toString());
       int stopBits = Integer.parseInt(this.serialParams.get("stop bits")
           .toString());
       int parity = Integer.parseInt(this.serialParams.get("parity")
           .toString());
       this.delayRead = Integer.parseInt(this.serialParams.get("delay read")
           .toString());
       String port = this.serialParams.get("port name").toString();
       
       portId = CommPortIdentifier.getPortIdentifier(port);
       EbamUtils.ISOPENPORT = "1";
       this.serialPort = (SerialPort)portId.open("SerialReader", timeout);
       this.inputStream = this.serialPort.getInputStream();
       this.serialPort.addEventListener(this);
       this.serialPort.notifyOnDataAvailable(true);
       this.serialPort.setSerialPortParams(rate, dataBits, stopBits, parity);
     } catch (PortInUseException e) {
       
       e.printStackTrace();
     } catch (TooManyListenersException e) {
       
       e.printStackTrace();
     } catch (UnsupportedCommOperationException e) {
       
       e.printStackTrace();
     } catch (NoSuchPortException e) {
       
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     } 
     Thread readThread = new Thread(this);
     readThread.start();
   }
   
   public void run() {
     try {
       Thread.sleep(1000L);
     } catch (InterruptedException interruptedException) {}
   }
 
 
 
   
   public void serialEvent(SerialPortEvent event) {
     try {
       Thread.sleep(this.delayRead);
     }
     catch (InterruptedException e) {
       e.printStackTrace();
     } 
     switch (event.getEventType()) {
       
       case 1:
         
         try {
           
           while (this.inputStream.available() > 0) {
             this.numBytes = this.inputStream.read(readBuffer);
           }
           
           changeMessage(readBuffer, this.numBytes);
         } catch (IOException e) {
           e.printStackTrace();
         } 
         break;
     } 
   }
 
   
   public SerialReader() {
     this.temp1 = ""; } public SerialReader(HashMap params) { this.temp1 = "";
     this.serialParams = params;
     init(); } public void changeMessage(byte[] message, int length) { setChanged();
     byte[] temp = new byte[length];
     System.arraycopy(message, 0, temp, 0, length);
     
     if (!"".equals(this.temp1)) {
       if (!this.temp1.equals((new String(temp)).trim())) {
         this.temp1 = (new String(temp)).trim();
         notifyObservers(temp);
       } 
     } else {
       
       this.temp1 = (new String(temp)).trim();
       notifyObservers(temp);
     }  }
 
   
   static void listPorts() {
     Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
     while (portEnum.hasMoreElements()) {
       CommPortIdentifier commPortIdentifier = portEnum.nextElement();
     }
   }
   
   public static String getPortName() {
     Enumeration<CommPortIdentifier> en = CommPortIdentifier.getPortIdentifiers();
     while (en.hasMoreElements()) {
       CommPortIdentifier portId = en.nextElement();
       if (portId.getPortType() == 1) {
         return portId.getName();
       }
     } 
     return "";
   }
   
   public static void closePort() {
     Enumeration<CommPortIdentifier> en = CommPortIdentifier.getPortIdentifiers();
     while (en.hasMoreElements()) {
       CommPortIdentifier portId = en.nextElement();
       portId.getPortType();
     } 
   }
 
 
   
   static String getPortTypeName(int portType) {
     switch (portType) {
       case 3:
         return "I2C";
       case 2:
         return "Parallel";
       case 5:
         return "Raw";
       case 4:
         return "RS485";
       case 1:
         return "Serial";
     } 
     return "unknown type";
   }
 
   
   public static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
     HashSet<CommPortIdentifier> h = new HashSet<>();
     Enumeration<CommPortIdentifier> thePorts = CommPortIdentifier.getPortIdentifiers();
     while (thePorts.hasMoreElements()) {
       CommPortIdentifier com = thePorts.nextElement();
       switch (com.getPortType()) {
         case 1:
           try {
             CommPort thePort = com.open("CommUtil", 50);
             thePort.close();
             h.add(com);
           } catch (PortInUseException portInUseException) {
           
           } catch (Exception exception) {}
       } 
 
     
     } 
     return h;
   }
 }


