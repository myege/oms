 package com.zt.kjybd;
 
 import com.what21.util.AESUtil;
 import com.what21.util.RSAUtil;
 import java.io.File;
 import javax.xml.namespace.QName;
 import javax.xml.rpc.ParameterMode;
 import org.apache.axis.client.Call;
 import org.apache.axis.client.Service;
 import org.apache.axis.encoding.XMLType;
 import org.dom4j.Document;
 import org.dom4j.io.SAXReader;
		 import org.apache.commons.codec.binary.Base64;
 public class OutQd
 {
   public static String pushQd(String xmlStr) {
     String result = "";
 
     
     try {
       String endpoint = "http://122.224.69.179:8080/newyorkWS/ws/ReceiveEncryptDeclare?wsdl";
 
 
       
       Service service = new Service();
       Call call = (Call)service.createCall();
       call.setTargetEndpointAddress(endpoint);
       call.setOperationName(new QName("http://ws.newyork.zjport.gov.cn/", "receive"));
 
       
       call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
       call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
       
       call.setReturnType(XMLType.XSD_STRING);
       
       String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAki8kPRpD+qN70XPA1ypvNeoCAoDUwbZzMZ4NqeJ/Vn6m6AKjiXGxCyAIrK5yefIPtFXc6jaafcOkZ8lpfR83swIDAQABAkBlnGMCHem2pECCnIUkiph2z0vKVaaNZ7a2fHhe59M/WQjMYPNROJ3zDLtlKrIr6FdRi6HmKnx1L4NVbc2KUmdxAiEAxQWLUCQ/mHc9KANf3G1et9l+eHJEjgp6gqplbDRvYFUCIQC98b3sFOm4CHUiLSrNturKRc0tz/tLj7uXTHkdMSEf5wIgXwKRcBBK6sd1onoeHDpR1u73YjYYgTbkZWauikOvAhECIQCoXR42o/bjDIQfLiARxkBp2ONc50zdFmN2Ye+9A25UIwIgZSlgF1SCnUdTruO2iQel991LIh6n/q93MK67IRi1efI=";
       String aesKey = "x9hT/fTB484WHX8VGuhPPA==";
       
       byte[] inputContent = xmlStr.getBytes("utf-8");
       byte[] privateKeyCode = Base64.decodeBase64(privateKey);
       byte[] aesKeyCode = Base64.decodeBase64(aesKey);
       
       
       String content = new String(Base64.encodeBase64String(AESUtil.encrypt(inputContent, aesKeyCode)));
 
       
       String dataDigest = new String(Base64.encodeBase64String(RSAUtil.sign(inputContent, privateKeyCode)));
       
       String msgType = "BILL";
       String sendCode = "3316965628";
       
       result = (String)call.invoke(new Object[] { content, msgType, dataDigest, sendCode });
       
       System.out.println("result is------------>" + result);
     }
     catch (Exception e) {
       System.err.println(e.toString());
     } 
     return result;
   }
 
   
   public static void main(String[] args) throws Exception {
     try {
       File f = new File("D:\\xml\\2.xml");
       SAXReader reader = new SAXReader();
       Document doc = reader.read(f);
       
       pushQd(doc.asXML());
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
   }
 }


