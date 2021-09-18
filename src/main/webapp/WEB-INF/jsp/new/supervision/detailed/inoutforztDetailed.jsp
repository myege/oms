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
<input type="hidden" name="planingReceiptsId" value="${manualid}" id="manualid">
<input type="hidden" name="planingReceiptsId" value="${sourceno}" id="sourceno">
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
	var manualids=$("#manualid").val();
	var sourcenos = $("#sourceno").val();
	var options = {
			//初始显示所有地址
			url : '<%=path%>/inoutforzt/findSku.do?manualid='+manualids+'&sourceno='+sourcenos,
			//排序字段名
			sortName : "id",
			//显示字段
			columns : [{field:'column',checkbox:true,align : 'center'},
				 {
					field : 'inOutSeq',
					title : '出入库记录流水号',
					align : 'center'
				}, {
					field : 'inOutNo',
					title : '出入库记录编号',
					align : 'center'
				}, {
					field : 'manualId',
					title : '账册编号',
					align : 'center'
				}, {
					field : 'sourceNo',
					title : '料号',
					align : 'center'
				}, {
					field : 'itemType',
					title : '料件性质',
					align : 'center'
				}, {
					field : 'inOutFlag',
					title : '出入库标志',
					align : 'center'
				}, {
					field : 'inOutAmount',
					title : '出入库数量',
					align : 'center'
				}, {
					field : 'inOutTime',
					title : '出入库时间',
					align : 'center',
					formatter : formatDate2
				}, {
					field : 'wayBillNo',
					title : '运单编号',
					align : 'center'
				}, {
					field : 'jobFormId',
					title : '核放单编号',
					align : 'center'
				}, {
					field : 'flag',
					title : '口岸回执',
					align : 'center'
				},]
			}
		//进入初始化表格方法
		$.table.init(options);
});
</script>
</body>
</html>