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
<title>出入库统计</title>
<%@ include file="/static/common/head.txt"%>
</head>

<body class="gray-bg">

	<div class="container-div">
		<div class="row">
			<div class="col-sm-12 search-collapse">
				<form id="user-form">
					<div class="select-list">
						<ul>
							<li>
								<select id="manualid" name="manualid" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
										<option value="">请选择</option>
										<option value="W29926000140">W29926000140</option>
										<option value="W29926000126">W29926000126</option>
								</select>
							</li>
							<li>
								料号：<input type="text" id="sourceno"  placeholder="请输入料号"/>
							</li>
							<li>
								<span class="input-group-btn"><a class="btn btn-primary" id="search">
								<i class="fa fa-search"></i>
		      					&nbsp;搜索</a></span>
							</li>
						</ul>
					</div>
				</form>
			</div>

			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				 <a class="btn btn-danger btn-del" onclick="pull()"> <i class="fa fa-search"></i>拉取 </a> 
				 <a class="btn btn-warning" onclick="exportforzt()"><i class="fa fa-download"></i>导出</a>
			</div>
			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
	<form id="f_supplies_export" action="<%=path%>/inoutforzt/exportforzt.do" method="post">
			<input type="hidden" name="idsu" id="idsu"/>
			<input type="hidden" name="ids" id="ids"/>
			<input type="hidden" name="exmanualid" id="exmanualid"/>
			<input type="hidden" name="exsourceno" id="exsourceno"/>
		</form> 
	
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
	/**
	 进行查询 刷新表格 进入 initTable（） 然后根据条件查询  
	**/
	$('#search').click(function(){
		 var ps = $("#bootstrap-table").bootstrapTable("getOptions").pageSize;//获取分页的pagesize
		$('#bootstrap-table').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:ps});//刷新表格  还原到第一页
	});
	$(function(){
			$('#bootstrap-table').bootstrapTable({
					 url: "<%=path%>/inoutforzt/findAll.do",                                   // 请求后台的URL（*）
	                 contentType: "application/x-www-form-urlencoded",   // 编码类型
	                 method: 'post',                                     // 请求方式（*）
	                 cache: false,                                       // 是否使用缓存
	                 //singleSelect :true,									//禁止多选
	                // striped: _striped,                                  // 是否显示行间隔色
	                 sortable: true,                                     // 是否启用排序
	                 sortStable: true,                                   // 设置为 true 将获得稳定的排序
	                 showRefresh: true,                  //是否显示刷新按钮
	                 pageNumber: 1,                                      // 初始化加载第一页，默认第一页
	                 pageSize: 50,                                       // 每页的记录行数（*） 
	                 pageList: [50, 100, 150,200,500],                             // 可供选择的每页的行数（*）
	                // escape: _escape,                                    // 转义HTML字符串
	                 showFooter: true,                            // 是否显示表尾
	                 iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
	     	         toolbar: '#toolbar',                                // 指定工作栏
	                 sidePagination: "server",                           // 启用服务端分页
	                 uniqueId: "id",									 //每一行的唯一标识，一般为主键列
	                 sortName : "id",						 // 排序名称
	                 detailFormatter:"detailFormatter",
	                 showFooter:false,
	                 sortOrder: "desc",                   				 // 排序方式
	                 pagination : true, 								 // 是否分页
	                 paginationVAlign:'bottom',
	                 height: 700,
	     			 detailView:true,
	     			queryParams :queryParams,
	     			//显示字段
	 				columns : [{field:'column',checkbox:true,align : 'center'},
	 					{
	 						field : 'manualid',
	 						title : '账册编号',
	 						align : 'center'
	 					}, {
	 						field : 'sourceno',
	 						title : '料号',
	 						align : 'center'
	 					}, {
	 						field : 'f1',
	 						title : 'F1(正常出库（快递出区）)',
	 						align : 'center'
	 					}, {
	 						field : 'a1',
	 						title : 'A1(正常进库（先进后报）)',
	 						align : 'center'
	 					}, {
	 						field : 'a3',
	 						title : 'A3(正常进库（区内转入）)',
	 						align : 'center'
	 					}, {
	 						field : 'f4',
	 						title : 'F4(正常出库（区内转出）)',
	 						align : 'center'
	 					}, {
	 						field : 'creattime',
	 						title : '创建时间',
	 						align : 'center',
	 						formatter : formatDate2
	 					},],
	 					onExpandRow: function (index, row, $detail) {
	 						//查询子表需要的参数
	 					 var manualid = row.manualid;    
	 					 var sourceno = row.sourceno;  
	 					 var cur_table = $detail.html("<table id='tableSku'></table>").find('table'); 
	 					 var url="<%=path%>/inoutforzt/findSku.do"; 
	 					 $(cur_table).bootstrapTable({    
	 						 url:url,        
	 						 method:'post',   
	 						 contentType: "application/x-www-form-urlencoded", 
	 						 queryParams: function queryParamsSku(params){
	 							var temp ={
	 							rows : params.limit,                   //页面大小
	 				            page : (params.offset / params.limit) + 1,    //页码
	 				            //查询子表需要的参数
	 				           	manualid : manualid,
	 				          	sourceno : sourceno,
	 							};
	 							return temp ;
	 						 },   
	 						 //ajaxOptions:{sourceno:sourceno,manualid:manualid}, 
	 						 clickToSelect:true,
	 						 singleSelect :false,
	 						 //detailView:true,       
	 						 uniqueId:"id", 
	 						 pagination: true, //是否显示分页（*）
	 						 pageNumber: 1,
	 						 pageSize:50,          
	 						 pageList:[50,100,1000], 
	 						 sidePagination: "server",
	 						/*  //查询条件
	 						 queryParams : queryParamsSku(), */
	 						 columns:[ 
	 							{
	 					            checkbox: true
	 					        },
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
	 						 });  
	 				       },
				})
	});
	
	//查询条件
	function queryParams(params){
			var manualid = $('#manualid option:selected') .val();
			var temp = $("#data-form").serializeJsonObject();
            temp["rows"] = params.limit;                        //页面大小
            temp["page"] = (params.offset / params.limit) + 1;  //页码
            temp["sort"] = params.sort;                         //排序列名
            temp["sortOrder"] = params.order;                   //排位命令（desc，asc） 
            //特殊格式的条件处理
            temp["sourceno"] = $("#sourceno").val();
            temp["manualid"] = manualid;
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
    
  
    //拉取
    function pull(){
    	$.modal.loading("正在拉取中，请稍后...");
		 $.post('<%=path%>/inoutforzt/pull.do',
		 function(){
			$.operate.success("数据拉取完成");
       		$.modal.closeLoading();
		}		
		)
	}
    //导出
    function exportforzt(){
    	var rows = $("#bootstrap-table").bootstrapTable('getSelections');
		 if (rows.length != 1) {
   			$.modal.alertWarning("必须并且只能选择一条主信息进行导出");
   			return;
   		 }
		 var skurows = $("#tableSku").bootstrapTable('getSelections');
	     var exmanualid = rows[0].manualid;
		 var exsourceno = rows[0].sourceno;
		 var skulength=skurows.length;
		 if(skulength!=0){
		 layer.open({
			  content: "确定导出这<font color='red'>"+skulength+"</font>条数据？"
			  ,btn: ['确认', '取消']
			  ,yes: function(index, layero){
				  var idsu=rows[0].id;
					var ids="";
					for(var i=0;i<skulength;i++){
						if(i==0){
							ids += skurows[i].id;
						}else{
							ids += ','+skurows[i].id;
						}						
					}
					$("#ids").val(ids);
					$("#idsu").val(idsu);
					$("#exmanualid").val(exmanualid);
			        $("#exsourceno").val(exsourceno);
			        $("#f_supplies_export")[0].submit();
			        layer.close(index);
			  }
		 });
		 }else{
			 var id=rows[0].id;
			 $("#ids").val(null);
				$("#idsu").val(id);
				$("#exmanualid").val(exmanualid);
		         $("#exsourceno").val(exsourceno);
		         layer.open({
					  content: "确定导出?"
					  ,btn: ['确认', '取消']
					  ,yes: function(index, layero){
						  $("#f_supplies_export")[0].submit();  
						  layer.close(index);
					  }
		         });
		 }
    }
	</script>
	
</body>

</html>