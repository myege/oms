 package wms;
 
 import java.util.Observable;
 import java.util.Observer;
 
 class CommDataObserver implements Observer {
   String name;
   
   public CommDataObserver(String name) {
     this.name = name;
   }
   public CommDataObserver() {}
   public void update(Observable o, Object arg) {
     String weight = (new String((byte[])arg)).trim();
     StringBuilder sb = new StringBuilder(weight);
     
     weight = weight.replaceAll(" kg", "");
 
 
     
     EbamUtils.GOODWEIGHT = weight;
   }
 }


