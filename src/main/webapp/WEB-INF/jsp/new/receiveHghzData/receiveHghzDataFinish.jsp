<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单回执已处理</title>
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
								<li><input type="text" id="time3" name="time3"
									class="form-control" placeholder="开始时间"/></li>
								<li><span>-</span></li>
								<li><input type="text" id="time4" name="time4"
									class="form-control" placeholder="结束时间" /></li>
								</li>
							<li><a class="btn btn-primary btn-rounded btn-sm"
								id="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
							</li>
						</ul>
					</div>
				</form>
			</div>
			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
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
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/receiveHghzData/findAll.do',
				//删除地址     
				deleteUrl : '<%=path%>/orderBonded/deleteOrderBonded.do',
				//排序字段名
				sortName : "createTime",
				//查询条件
				queryParams : queryParams,
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'contentData',
						title : '异常报文',
						align : 'center',
						cellStyle:{
							css:{
							"max-width": "200px",
							"overflow": "hidden",
							"text-overflow": "ellipsis",
							"white-space": "nowrap",
							 "table-layout":"fixed"
							}
						}
						
					}, {
						field : 'createTime',
						title : '创建时间',
						align : 'center'
					}, {
						field : 'isHandle',
						title : '是否处理',
						align : 'center',
						formatter:function(value){ 
							if(value == 0){
	                	   		return '未处理';
		                   	}else if(value == 1){
		                		return '已处理';
		                   	}
		                } 
					}],
		               onDblClickRow: function (row) {
		            	   document.getElementById("contentData").innerText = row.contentData;
		            	   layer.open({
		           	    	  title: '回执信息',
		           	    	  type: 1,
		           	    	  closeBtn: 2,
		           	    	  content:$('#contentDataDialog'),
		           	    	  isOutAnim : true,
		           	    	  maxmin  : true,
		           	    	  anim : 2 ,
		           	    	  area : ['70%','95%']
		           	    	}); 
			            },
				}
		//进入初始化表格方法
			//加载时间控件
			$.operate.time("time3");
			$.operate.time("time4");
			$.table.init(options);
	});
	
	//查询条件
	function queryParams(params){
			var temp = $("#data-form").serializeJsonObject();
            temp["rows"] = params.limit;                        //页面大小
            temp["page"] = (params.offset / params.limit) + 1;  //页码
            temp["sort"] = params.sort;                         //排序列名
            temp["sortOrder"] = params.order;                   //排位命令（desc，asc）
            temp["startTime"] = $("#time3").val();
            temp["endTime"] = $("#time4").val();
            temp["isHandle"] = "1";
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
<div style="display: none;" id="contentDataDialog"><span id="contentData" style="font-size:20px"></span></div>
</body>
</html>