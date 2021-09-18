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
<title>出入库记录</title>
<%@ include file="/static/common/head.txt"%>
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
			  						<select id="flag" name="inoutCustom.flag" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
										<option value="0">全部</option>
										<option value="1">已推送</option>
										<option value="2" selected="selected">未推送</option>
										<option value="3">处理失败</option>
									</select>
		  							</div>
		 						<div class="input-group-btn"><!--不换行，与相邻元素内联，包含dropdown-->
			  						<select id="serachname" name="serachname" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
										<option value="inoutCustom.inOutNo">出入库记录编号</option>
										<option value="inoutCustom.inOutFlag">出入库标志</option>
										<option value="inoutCustom.jobFormId">核放单编号</option>
										<option value="inoutCustom.sourceNo">料号</option>
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
				</a> <a class="btn btn-primary btn-edit" onclick="its()"> <i class="fa fa-edit"></i>
					推送
				</a>
				 <a class="btn btn-danger btn-del" onclick="$.operate.planRemoveAll()"> <i class="fa fa-remove"></i>删除 </a> 
				 <a class="btn btn-warning" onclick="$.operate.importModel('50%','50%','<%=path%>/import/importInout.jsp')"> <i class="fa fa-download"></i> 导入
				</a>
				<a style='color:blue' href='<%=path%>/xls/inout.xls'>下载导入模板</a>
				 <a class="btn btn-warning" onclick="exportinout()"> <i class="fa fa-download"></i>导出 
				</a>
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
				url : '<%=path%>/inout/findAll2.do',
				//删除地址     
				removeUrl : '<%=path%>/inout/indelete.do',
				//导入地址
				importUrl : '<%=path%>/inout/inmeri.do',
				//排序字段名
				sortName : "id",
				//查询条件
				queryParams : queryParams,
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
						title : '账册编号 ',
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
						align : 'center'
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
			//加载时间控件
// 			$.operate.time("time3");
// 			$.operate.time("time4");
			$.table.init(options);
	});
	
	//查询条件
	function queryParams(params){
			var value = $("#getValue").val();
			var name = $('#serachname option:selected').val();
			var temp = $("#data-form").serializeJsonObject();
			var flag = $("#flag option:selected").val();
            temp["rows"] = params.limit;                        //页面大小
            temp["page"] = (params.offset / params.limit) + 1;  //页码
            temp["sort"] = params.sort;                         //排序列名
            temp["sortOrder"] = params.order; 
            temp["inoutCustom.flag"]=flag;
            //排位命令（desc，asc） 
            //特殊格式的条件处理
            temp[name] = $("#getValue").val();
            console.log(temp);
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
	function its(){
		$.modal.loading("正在推送数据，请稍后...");
           	$.post('<%=basePath%>inout/ints.do',{
           			},
           			function(result){
	                  	if (result == 1){ 
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
    
	//导出
    function  exportinout() {
    	 var row = $("#bootstrap-table").bootstrapTable('getSelections');
		 var length=row.length;
		 var ids = "";
		 var value = $("#getValue").val();
		 var name = $('#serachname option:selected') .val();
		if (length == 0) {
			layer.open({
				  content: '确认导出?'
				  ,btn: ['确认', '取消']
				  ,yes: function(index, layero){
	  	       	//查询全部标识符
		  	        ids = "cxqbdc";		
		 			var v1 = document.getElementById("ids");
		 			v1.value = ids;
		 			//设置查询字段值
		 			document.getElementById("name").name=name;
		 			$("#name").attr("value",value);
	 				//下拉框的值
	 				var v3 =document.getElementById("flags");
	 				v3.value=$('#flag').val();
		 			//提交表单可以这样  
		 			var f = document.getElementById("f_inout_export");
		 			f.submit(); 
		 			layer.close(index);
	  	            }
				,btn2: function(index, layero){
				    //按钮【按钮二】的回调
				      layer.close(index);
				    //return false 开启该代码可禁止点击该按钮关闭
				  }
			});
		  }else{ 
			  var ids;
			  console.log(row.length);
			  for (var i = 0; i < row.length; i++){
				  var ids = ids + row[i].id +",";
           }
			  console.log(ids);
			  layer.open({
				  content: "确定要导出这<font color='red'>"+length+"</font>条数据？"
				  ,btn: ['确认', '取消']
				  ,yes: function(index, layero){
				    //按钮【按钮一】的回调
						var v = document.getElementById("ids");
				    	v.value = ids;
				        	//提交表单可以这样  
						var f = document.getElementById("f_inout_export");
						f.submit();
						layer.close(index);
		   		}
			  ,btn2: function(index, layero){
				    //按钮【按钮二】的回调
				      layer.close(index);
				    //return false 开启该代码可禁止点击该按钮关闭
				  }
		      }); 
        }  
	}
	</script>
  <form id="f_inout_export" action="<%=path%>/inout/export2.do" method="post">
			<input type="hidden" name="inoutCustom.ids" id="ids" value=""/>
			<input type="input" name="" id="name" value=""/>
			<input type="hidden" name="inoutCustom.value" id="value" value=""/>
			<input type="hidden" name="inoutCustom.flag" id="flags" value=""/>
		</form>
</body>
</html>