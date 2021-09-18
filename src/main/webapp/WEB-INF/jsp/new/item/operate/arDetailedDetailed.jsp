<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看明细</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
<input type="hidden" name="jobFormId" value="${jobFormId}" id="jobFormId">
	<div class="container-div">
		<div class="row">
			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">

<!-- 入口方法 -->
$(function(){
	var jobFormId=$("#jobFormId").val();
	var options = {
			//初始显示所有地址
			url : '<%=path%>/annotation/arDetailed.do?jobFormId='+jobFormId,
			//排序字段名
			sortName : "id",
			//显示字段
			columns : [{field:'column',checkbox:true,align : 'center'},
				 {
					field : 'jobFormId',
					title : '编号',
					align : 'center'
				}, {
					field : 'mailno',
					title : '单号',
					align : 'center'
				}]
			}
		//进入初始化表格方法
		$.table.init(options);
});
</script>
</body>
</html>