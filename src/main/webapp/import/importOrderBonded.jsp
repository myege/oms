<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/static/common/head.txt"%>
</head>
<body>
	<form id="editform" method="post" enctype="multipart/form-data">
		<input type="file" id="excelFile" name="excelFile">
	</form>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
function submitHandler() {
		var editurl ='<%=path%>/orderBonded/importOrderBonded.do';
		var form = new FormData(document.getElementById("editform"));
		$.operate.import_save(editurl, form);
}
</script>
</body>
</html>