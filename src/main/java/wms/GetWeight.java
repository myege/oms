 package wms;
 
 import java.util.HashMap;
 import java.util.List;
 import java.util.Timer;
 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;
 public class GetWeight
   implements ServletContextListener
 {
   private Timer timer = null;
 
   
   public void contextDestroyed(ServletContextEvent event) {}
 
   
   public void contextInitialized(ServletContextEvent event) {
     String com = "COM3";
     HashMap<String, Comparable> params = new HashMap<>();
     params.put("port name", com);
     params.put("rate", Integer.valueOf(9600));
     params.put("timeout", Integer.valueOf(1000));
     params.put("delay read", Integer.valueOf(200));
     params.put("data bits", Integer.valueOf(8));
     params.put("stop bits", Integer.valueOf(1));
     params.put("parity", Integer.valueOf(0));
     SerialReader sr = new SerialReader(params);
     
     CommDataObserver bob = new CommDataObserver("荤菜");
 
     
     sr.addObserver(bob);
     SerialReader.listPorts();
   }
 
   
   public static void main(String[] args) {
     String com = "COM3";
     HashMap<String, Comparable> params = new HashMap<>();
     params.put("port name", com);
     params.put("rate", Integer.valueOf(9600));
     params.put("timeout", Integer.valueOf(1000));
     params.put("delay read", Integer.valueOf(200));
     params.put("data bits", Integer.valueOf(8));
     params.put("stop bits", Integer.valueOf(1));
     params.put("parity", Integer.valueOf(0));
     SerialReader sr = new SerialReader(params);
     
     CommDataObserver bob = new CommDataObserver("荤菜");
 
     
     sr.addObserver(bob);
     SerialReader.listPorts();
   }
   
   public static void setSerParams(List<String> goodsName) {
     String com = SerialReader.getPortName();
     if (!"".equals(com)) {
       HashMap<String, Comparable> params = new HashMap<>();
       params.put("port name", com);
       params.put("rate", Integer.valueOf(9600));
       params.put("timeout", Integer.valueOf(1000));
       params.put("delay read", Integer.valueOf(200));
       params.put("data bits", Integer.valueOf(8));
       params.put("stop bits", Integer.valueOf(1));
       params.put("parity", Integer.valueOf(0));
       SerialReader sr = new SerialReader(params);
       
       initData(sr, goodsName);
     } 
   }
   
   public static void initData(SerialReader sr, List<String> goodNames) {
     for (int i = 0; i < goodNames.size(); i++) {
       CommDataObserver bob = new CommDataObserver(goodNames.get(i));
       sr.addObserver(bob);
     } 
   }
 }


