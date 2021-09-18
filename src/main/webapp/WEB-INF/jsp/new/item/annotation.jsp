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
<title>核注清单</title>
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
										<option value="annotationCustom.jobFormId">核放单编号</option>
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
				<!-- <a class="btn btn-primary btn-edit" onclick="$.operate.actts()"> <i class="fa fa-edit"></i>
					导出核注清单
				</a> 
			//	 <a class="btn btn-danger btn-del" onclick="$.operate.planRemoveAll()"> <i class="fa fa-remove"></i>删除 </a> -->
				 <a class="btn btn-warning" onclick="$.operate.importModel('50%','50%','<%=path%>/import/importAnnotation.jsp')"> <i class="fa fa-download"></i> 导入单号
				</a>
				<a style='color:blue' href='<%=path%>/xls/annotation.xlsx'>下载导入模板</a>
			
			<a class="btn btn-primary btn-edit"  onclick="exportOrder()"> <i class="fa fa-download"></i>
 					导出
				</a> 
			</div>
			<form id="editform" action="<%=path%>/annotation/exportba.do" method="post">
					<input type="hidden" id="ids" name="ids">
					<input type="hidden" id="queryName" name="queryName">
					<input type="hidden" id="queryValue" name="queryValue">
			</form>
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
				url : '<%=path%>/annotation/findAll2.do',
				//推送地址
				pushPrUrl : '<%=path%>/annotation/actts.do',
				//删除地址     
				removeUrl : '<%=path%>/annotation/actdelete.do',
				//导入地址
				importUrl : '<%=path%>/annotation/inmera.do',
				//排序字段名
				sortName : "id",
				//查询条件
				queryParams : queryParams,
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'jobFormId',
						title : '系统核注编号',
						align : 'center'
					}, {
						field : 'datatime',
						title : '状态',
						align : 'center'
					},],
					onDblClickRow: function (row) {
		            	   layer.open({
		            	    	  title: '查看明细',
		            	    	  type: 2,
		            	    	  closeBtn: 2,
		            	    	  content: '<%=path%>/annotation/arDetailedUi.do?jobFormId='+row.jobFormId,
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
	
	//导出
	function exportOrder(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids="";
			if(rows.length > 0){
				for(var i = 0 ; i < rows.length ; i ++ ){
					if(i == 0){
						ids+=rows[i].id;
					}else{
						ids+=","+rows[i].id;
					}
				}
				var v = document.getElementById("ids");
				v.value = ids;
				//提交表单可以这样  
				//	$.modal.confirm(ids);
				var f = document.getElementById("editform");
				//$.modal.confirm("是否导出勾选记录！");
				f.submit(); 
				
			}else{
			
			$.modal.confirm("请先勾选数据!");
			
			}
	}
	
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