package com.what21.service;

import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService
@BindingType("http://www.w3.org/2003/05/soap/bindings/HTTP/")
public interface ImportYtoService {
  @WebMethod
  String importOrder(String paramString) throws IOException;
}


