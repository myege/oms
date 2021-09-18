<%@page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/pageload.js"></script>
</head>
<body onunload="reloadParent('#outshiptoTab')">
	<form id="importStorageForm" method="post" enctype="multipart/form-data" action="<%=path%>/outshipto/importShip.do">
		<input type="file" id="excelFile" name="excelFile">
		<input type="submit" value="上传并导入" id="btnSubmit" name="btnSubmit">
	</form>
</body>
</html>