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
<input type="hidden" name="planingReceiptsId" value="${planingReceiptsId}" id="planingReceiptsId">
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
	var planingReceiptsId=$("#planingReceiptsId").val();
	var options = {
			//初始显示所有地址
			url : '<%=path%>/planingReceipts/prDetailed.do?planingReceiptsId='+planingReceiptsId,
			//排序字段名
			sortName : "id",
			//显示字段
			columns : [{field:'column',checkbox:true,align : 'center'},
				 {
					field : 'planingReceiptsId',
					title : '计划入库单编号',
					align : 'center'
				}, {
					field : 'sourceNo',
					title : '料号',
					align : 'center'
				}, {
					field : 'itemNo',
					title : '项号',
					align : 'center'
				}, {
					field : 'itemType',
					title : '料件性质',
					align : 'center'
				}, {
					field : 'goodsNo',
					title : '商品编码',
					align : 'center'
				}, {
					field : 'declareAmount',
					title : '申报数量',
					align : 'center'
				}, {
					field : 'totalPrice',
					title : '申报总价',
					align : 'center'
				}, {
					field : 'price',
					title : '单价',
					align : 'center'
				}, {
					field : 'countryCode',
					title : '原产国',
					align : 'center'
				}, {
					field : 'currency',
					title : '币制',
					align : 'center'
				}, {
					field : 'datatime',
					title : '时间',
					align : 'center'
				},]
			}
		//进入初始化表格方法
		$.table.init(options);
});
</script>
</body>
</html>