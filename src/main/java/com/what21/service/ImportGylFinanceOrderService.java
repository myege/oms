package com.what21.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService
@BindingType("http://www.w3.org/2003/05/soap/bindings/HTTP/")
public interface ImportGylFinanceOrderService {
  @WebMethod
  String importOrder(String paramString);
}

