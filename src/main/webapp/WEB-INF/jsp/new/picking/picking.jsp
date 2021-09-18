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
				<a class="btn btn-success" id="distribute" onclick="$.operate.openDistribute('70%','70%','<%=path %>/JjPicking/distribute.do','派单','保存')"> <i class="fa fa-plus"></i> 派单
				</a>
				<a class="btn btn-success" id="add"  onclick="exportOrder()"> <i class="fa fa-edit"></i>
					导出
				</a>
				
				 <a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.edit('50%','50%')"> <i class="fa fa-edit"></i>
					修改
				</a>
				<%-- <a class="btn btn-warning" onclick="$.operate.importModel('50%','50%','<%=path%>/import/importDistributeLeaflets.jsp')"> <i class="fa fa-download"></i> 导入
				</a>
				<a style='color:blue' href='<%=path%>/xls/picking.xlsx'>下载导入模板</a> --%>
			</div>
			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
			
				<form id="editform" action="<%=path%>/JjPicking/exportba.do" method="post">
					<!-- <input type="hidden" id="ids" name="ids">
					<input type="hidden" id="queryName" name="queryName">
					<input type="hidden" id="queryValue" name="queryValue"> -->
			</form>
		</div>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">

<!-- 入口方法 -->
$(function(){
	var options = {
			//初始显示所有地址
			url : '<%=path%>/JjPicking/findAll.do',
			//进入修改页面的地址
			editUrl : '<%=path%>/JjPicking/editUi.do',
			//排序字段名
			sortName : "id",
			//显示字段
			columns : [{field:'column',checkbox:true,align : 'center'},
			    {
					field : 'pick',
					title : '拣货单号',
					align : 'center'
				},   {
					field : 'picksum',
					title : '订单数',
					align : 'center'
				},   {
					field : 'shop',
					title : '商品数',
					align : 'center'
				}, {
					field : 'pickname',
					title : '拣货员',
					align : 'center',
				},{
					field : 'lx',
					title : '类型',
					align : 'center',
				}, {
					field : 'picktime',
					title : '领单时间',
					align : 'center',
					formatter :function (value, row, index) {
		        		return createTime(row.picktime);
				}
				},],
					onDblClickRow: function (row) {
	            	   layer.open({
	            	    	  title: row.pick+'拣货单号详情',
	            	    	  type: 2,
	            	    	  closeBtn: 2,
	            	    	  content: '<%=path%>/jjPickingSku/findSkuUi.do?pick='+row.pick,
	            	    	  isOutAnim : true,
	            	    	  maxmin  : true,
	            	    	  anim : 2 ,
	            	    	  area : ['80%','95%']
	            	    	}); 
	 	            },
			}
		//进入初始化表格方法
		$.table.init(options);
});

//导出
function exportOrder(){
	layer.open({
	  	  title: '选填导出时间段',
	  	  type: 2,
	  	  closeBtn: 2,
	  	  content: '<%=path%>/JjPicking/betweenOldNewUi.do',
	  	  btn: ['确认', '取消'],
	  	  isOutAnim : true,
	  	  maxmin  : true,
	  	  anim : 2 ,
	  	  area : ['50%','70%'],
	  	  yes: function(index, layero){
	  		  	//按钮【按钮一】的回调
	  		  var iframeWin = layero.find('iframe')[0];
		      iframeWin.contentWindow.submitHandler();
		      
	  	  },
	  	  btn2: function(index, layero){
	  		  	//按钮【按钮二】的回调
	  		  layer.close(index);
	  		  //return false 开启该代码可禁止点击该按钮关闭
	  	  }
	  }); 
			/* var f = document.getElementById("editform");
			f.submit();  */
	//	}
}
</script>
</body>
</html>