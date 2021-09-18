<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userRole = request.getSession().getAttribute("userRole").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计划入库单</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="col-sm-12 search-collapse">
				<form id="data-form">
					<div class="select-list">
						<ul>
							<li>
								 <div class="input-group"><!--保持内联，消除边框，类似于form-inline-->
		 						<div class="input-group-btn"><!--不换行，与相邻元素内联，包含dropdown-->
			  						<select id="serachname" name="serachname" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
										<option value="planingReceiptsCustom.planingReceiptsId">计划入库单编号</option>
										<option value="planingReceiptsCustom.companyCode">企业海关十位编号</option>
										<option value="planingReceiptsCustom.providerCode">厂商编码</option>
									</select>
		  							</div>
									<input type="text" class="form-control" id="getValue" placeholder="请输入关键词" style="height: 34px"/>
									<span class="input-group-btn"><!--不换行，与相邻元素内联-->
		      						<a class="btn btn-primary" id="search">
		      							<i class="fa fa-search"></i>
		      							&nbsp;搜索
		      						</a>
									</span>
								</div>
							</li>
						</ul>
					</div>
				</form>
			</div>

			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				</a> <a class="btn btn-primary btn-edit" onclick="$.operate.pushPr()"> <i class="fa fa-edit"></i>
					推送
				</a>
				 <a class="btn btn-danger btn-del" onclick="$.operate.planRemoveAll()"> <i class="fa fa-remove"></i>删除 </a> 
				 <a class="btn btn-warning" onclick="$.operate.importModel('50%','50%','<%=path%>/import/importPlaningReceipts.jsp')"> <i class="fa fa-download"></i> 导入计划入库单
				</a>
				<a style='color:blue' href='<%=path%>/xls/planingReceipts.xls'>下载订单模板</a>
			</div>
			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
	<script type="text/javascript">
	/**
	 进行查询 刷新表格 进入 initTable（） 然后根据条件查询  
	**/
	$('#search').click(function(){
		 var ps = $("#bootstrap-table").bootstrapTable("getOptions").pageSize;//获取分页的pagesize
		$('#bootstrap-table').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:ps});//刷新表格  还原到第一页
	});
	<!-- 入口方法 -->
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/planingReceipts/findAll2.do',
				//推送地址
				pushPrUrl : '<%=path%>/planingReceipts/pushPr.do',
				//删除地址     
				removeUrl : '<%=path%>/planingReceipts/prDelete.do',
				//导入地址
				importUrl : '<%=path%>/planingReceipts/importPr.do',
				//排序字段名
				sortName : "id",
				//查询条件
				queryParams : queryParams,
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'planingReceiptsId',
						title : '计划入库单编号',
						align : 'center'
					}, {
						field : 'manualId',
						title : '账册编码',
						align : 'center'
					}, {
						field : 'customsCode',
						title : '关区代码 ',
						align : 'center'
					}, {
						field : 'companyCode',
						title : '企业海关十位编码',
						align : 'center'
					}, {
						field : 'companyName',
						title : '企业名称',
						align : 'center'
					}, {
						field : 'grossWeight',
						title : '毛重',
						align : 'center'
					}, {
						field : 'netWeight',
						title : '净重',
						align : 'center'
					}, {
						field : 'amount',
						title : '件数',
						align : 'center'
					}, {
						field : 'wrapType',
						title : '包装种类',
						align : 'center'
					}, {
						field : 'providerCode',
						title : '厂商编码',
						align : 'center'
					}, {
						field : 'type',
						title : '业务类别',
						align : 'center'
					}, {
						field : 'datatime',
						title : '时间',
						align : 'center'
					}, {
						field : 'flag',
						title : '核放单号',
						align : 'center'
					}, {
						field : 'port',
						title : '申报关区',
						align : 'center'
					},],
					onDblClickRow: function (row) {
						console.log(row);
		            	   layer.open({
		            	    	  title: '查看明细',
		            	    	  type: 2,
		            	    	  closeBtn: 2,
		            	    	  content: '<%=path%>/planingReceipts/prDetailedUi.do?planingReceiptsId='+row.planingReceiptsId,
		            	    	  isOutAnim : true,
		            	    	  maxmin  : true,
		            	    	  anim : 2 ,
		            	    	  area : ['80%','95%']
		            	    	}); 
		 	            },
				}
		//进入初始化表格方法
			//加载时间控件
// 			$.operate.time("time3");
// 			$.operate.time("time4");
			$.table.init(options);
	});
	
	//查询条件
	function queryParams(params){
			var value = $("#getValue").val();
			var name = $('#serachname option:selected') .val();
			var temp = $("#data-form").serializeJsonObject();
			console.log(temp);
			console.log(name);
			console.log(value);
            temp["rows"] = params.limit;                        //页面大小
            temp["page"] = (params.offset / params.limit) + 1;  //页码
            temp["sort"] = params.sort;                         //排序列名
            temp["sortOrder"] = params.order;                   //排位命令（desc，asc） 
            //特殊格式的条件处理
            temp[name] = $("#getValue").val();
	        return temp;
	}
	
    //自定义函数处理queryParams的批量增加
    $.fn.serializeJsonObject = function () {
        var json = {};
        var form = this.serializeArray();
        $.each(form, function () {
            if (json[this.name]) {
                if (!json[this.name].push) {
                    json[this.name] = [json[this.name]];
                }
                json[this.name].push();
            } else {
                json[this.name] = this.value || '';
            }
        });
        return json;
    }
	</script>

</body>
</html>