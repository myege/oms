<%@page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div>
     <c:choose>
        <c:when test="${result.code==0}">
           <div>  ${result.message} </div>
              <form action="<%=path%>/waybill/exportNoSucces.do" method="post">
                    <div><input type="submit" style="width: 120px;margin-top: 20px" value="导出不成功数据"></div> 
                    <input type="hidden" style="width: 120px;margin-top: 20px" value="${result.set}" name="set" >
                    <input type="hidden" style="width: 500px;margin-top: 20px" value="${result.url}" name="url" >
              </form>  
        </c:when>
        <c:otherwise>
              ${result.message}  
        </c:otherwise>
     </c:choose>   
</div>
</body>
</html>