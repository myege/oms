<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>拣货</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				<a class="btn btn-success" id="add" onclick="$.operate.add('40%','60%')"> <i class="fa fa-plus"></i> 新增
				</a> <a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.edit('40%','60%')"> <i class="fa fa-edit"></i>
					修改
				</a>
				 <a class="btn btn-danger btn-del" onclick="$.operate.removeAll()"> <i class="fa fa-remove"></i>删除 </a> 
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">

<!-- 入口方法 -->
$(function(){
	var options = {
			//初始显示所有地址
			url : '<%=path%>/jjUser/findAll.do',
			//进入修改页面的地址
			editUrl : '<%=path%>/jjUser/editUi.do',
			//进入添加页面的地址
			addUrl : '<%=path%>/jjUser/editUi.do',
			//删除地址     
			removeUrl : '<%=path%>/jjUser/remove.do',
			//排序字段名
			sortName : "id",
			//显示字段
			columns : [{field:'column',checkbox:true,align : 'center'},
				 {
					field : 'name',
					title : '拣货员名称',
					align : 'center'
				},   {
					field : 'type',
					title : '类型',
					align : 'center'
				}]
			}
		//进入初始化表格方法
		$.table.init(options);
});
</script>
</body>
</html>