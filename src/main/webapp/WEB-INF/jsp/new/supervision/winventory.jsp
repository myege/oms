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
<title>库存记录</title>
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
										<option value="winventoryCustom.manualId">账册编号</option>
										<option value="winventoryCustom.goodsUnit">商品单位</option>
										<option value="winventoryCustom.sourceNo">料号</option>
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
				</a> <a class="btn btn-primary btn-edit" onclick="wts()"> <i class="fa fa-edit"></i>
					推送
				</a>
				 <a class="btn btn-danger btn-del" onclick="$.operate.planRemoveAll()"> <i class="fa fa-remove"></i>删除 </a> 
				 <a class="btn btn-warning" onclick="$.operate.importModel('50%','50%','<%=path%>/import/importWinventory.jsp')"> <i class="fa fa-download"></i> 导入实收入库单
				</a>
				<a style='color:blue' href='<%=path%>/xls/winventory1.xlsx'>下载订单模板</a>
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
	})
	
	<!-- 入口方法 -->
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/winventory/findAll2.do',
				//导入地址
				importUrl : '<%=path%>/winventory/inmerw.do',
				//删除地址     
				removeUrl : '<%=path%>/winventory/wdelete.do',
				//排序字段名
				sortName : "id",
				//查询条件
				queryParams : queryParams,
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'manualId',
						title : '账册编号',
						align : 'center'
					}, {
						field : 'sourceNo',
						title : '料号',
						align : 'center'
					}, {
						field : 'goodsQuantity',
						title : '库存数量',
						align : 'center'
					}, {
						field : 'itemType',
						title : '料件性质',
						align : 'center'
					}, {
						field : 'goodsUnit',
						title : '商品单位',
						align : 'center'
					}, {
						field : 'storageLocation',
						title : '库位',
						align : 'center'
					}, {
						field : 'inventoryTime',
						title : '库存申报时间',
						align : 'center'
					}, {
						field : 'remark',
						title : '备注',
						align : 'center'
					}, {
						field : 'flag',
						title : '口岸回执',
						align : 'center'
					},]
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
    
    /**推送*/
	function wts(){
		$.modal.loading("正在推送数据，请稍后...");
           		$.post('<%=basePath%>winventory/wints.do',{
           			},
           			function(result){
	                  	if (result == '1'){ 
	                  		$.operate.success("推送成功");
	                  		$.modal.closeLoading();
	                    } else { 
	                    	layer.open({
	                    		  title:"失败结果", 
	                    		  type: 1,
	                    		  area: ['500px', '300px'],
	                    		  content: result //这里content是一个普通的String
	                    		  ,cancel: function(){ 
	                    			  $.table.refresh();
		                    	}
	                    		});
	                    	$.modal.closeLoading();
	                    }
                	}
                );
	}
	</script>

</body>
</html>