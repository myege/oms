<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品出口导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/pageload.js"></script>
</head>
<body onunload="reloadParent('#ItemForCk_csgd')">
	<form method="post" enctype="multipart/form-data" action="<%=path%>/itemForCk/importItemForCk.do">
		<input type="file" id="excelFile" name="excelFile">
		<input type="submit" value="上传并导入" id="btnSubmit" name="btnSubmit">
	</form>
</body>
</html>