<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>京东采购单验收</title>
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
										<option value="text5">报检单号</option>
										<option value="text4">报关单号</option>
										<option value="text3">京东采购单</option>
									</select>
		  							</div>
									<input type="text" class="form-control" id="getValue" onkeydown='if(event.keyCode==13){return false;}' placeholder="请输入关键词" style="height: 34px"/>
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
				<a class="btn btn-success" id="add" onclick="push()"> <i class="fa fa-plus"></i> 推送湾流</a>
		
				<a class="btn btn-warning" onclick="importcgdys()"> <i class="fa fa-download"></i> 导入</a>
				<a style='color:blue' href='<%=path%>/xls/ijdcgdys.xlsx'>下载验收模板</a>
		<a class="btn btn-success" id="add" onclick="deleteByIds()"> <i class="fa fa-plus"></i> 删除</a>
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

	function importcgdys() {
		var openUrl = "<%=path%>/import/importCgdys.jsp";//弹出窗口的url
		var title = '商家导入EXCEL';//弹出窗口的title
		var iWidth = 1000; //弹出窗口的宽度;
		var iHeight = 400; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		window.open(openUrl, title, 'height=' + iHeight + ',width='
				+ iWidth + ',left=' + iLeft + ',top=' + iTop
				+ ',toolbar=no,menubar=yes,scrollbars=no');
	 }
	
	<!-- 入口方法 -->
	$(function(){

		var options = {
				//初始显示所有地址
				url : '<%=path%>/jdwl/findAllcgdys.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/jdwl/editUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/jdwl/addUi.do',
				//导入地址
				importUrl : '<%=path%>/jdwl/importItem.do',
				//排序字段名
				sortName : "createtime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
				           {
					field : 'status',
					title : '状态',
					align : 'center',
					formatter : function(value) {
						if(value == 1) {
							return "成功"
						}else if (value == 2) {
							return "失败"
						}
					}
				    } , 
				           {
						field : 'notes',
						title : '回执',
						align : 'center'
					}, {
						field : 'poNo',
						title : '京东采购单',
						align : 'center'
					}, {
						field : 'corrDocNo',
						title : '报关单号',
						align : 'center'
					}, {
						field : 'corrDocTime',
						title : '报关时间',
						align : 'center'
					}, {
						field : 'declNo',
						title : '报检单号',
						align : 'center'
					}, {
						field : 'declTime',
						title : '报检时间',
						align : 'center'
					}, {
						field : 'provider',
						title : '服务商简码',
						align : 'center'
					}, {
						field : 'createtime',
						title : '时间',
						align : 'center',
						formatter:function(value){
                  			return formatDate(value);
                  		}
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
            temp["rows"] = params.limit;                        //页面大小
            temp["page"] = (params.offset / params.limit) + 1;  //页码
            temp["sort"] = params.sort;                         //排序列名
            temp["sortOrder"] = params.order;                   //排位命令（desc，asc） 
            //特殊格式的条件处理
            temp[name] = $("#getValue").val();
	        return temp;
	}
	
	
	//推送
	function push(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');
	   		if(row.length==0){  
	   			$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
	   		}
	   		
	 	    var ids = ""; 
	 	   for (var i = 0; i < row.length; i++){
				var ids = ids + row[i].poNo +",";
	          /* 	if(row[i].auditstatus != 2){
	          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
		   			return false;
		   		} */
	          
	       	}
	       	ids = ids.substring(0, ids.length-1); 
	    	
	        $.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
	       		layerMsg("推送中..");
	       		
	   			  $.post('<%=basePath%>jdwl/pushcgdys.do',{
	               				"ids":ids 
	               			}, 
	           			function(result){
	           				layer.close(layer.index);
		                  	if (result == 1){ 
		                  		layer.msg("推送成功", {
		                			  icon: 1,
		                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(index){
		                			  //do something
		                				$("#bootstrap-table").bootstrapTable("refresh", {
		          					    silent: true //静态刷新
		          					});
		                		})
		                    } else { 
		                    	layer.msg("推送失败", {
		                			  icon: 5,
		                			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(index){
		                			  //do something
		                				$("#bootstrap-table").bootstrapTable("refresh", {
		          					    silent: true //静态刷新
		          					});
		                		})
		                    }
	                	}
	                );
	       	});  
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
    //删除记录
    function deleteByIds(){
    	var rows = $("#bootstrap-table").bootstrapTable('getSelections');
    		var length =rows.length;
    		if(length==0){
    			$.modal.alertWarning("请至少选择一条记录");
       	    	return false;  
    		}else{
    			$.modal.confirm('确定要删除这<font color=red>'+rows.length+'条记录吗?',function(){
    	       		layerMsg("删除中...");
    						var ids="";
    						for(var i=0;i<length;i++){
    							if(i==0){
    								ids += rows[i].poNo;
    							}else{
    								ids += ","+ rows[i].poNo;
    							}
    						}
    						$.post('<%=path%>/jdwl/deleteysd.do',{ids:ids},
    								function(ret){
    							layer.close(layer.index);
    									if(ret>0){
    										layer.msg("删除成功", {
    				                			  icon: 1,
    				                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
    				                			}, function(index){
    				                			  //do something
    				                				$("#bootstrap-table").bootstrapTable("refresh", {
    				          					    silent: true //静态刷新
    				          					});
    				                		})
    									}else{
    										layer.msg("删除失败", {
    				                			  icon: 5,
    				                			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
    				                			}, function(index){
    				                			  //do something
    				                				$("#bootstrap-table").bootstrapTable("refresh", {
    				          					    silent: true //静态刷新
    				          					});
    				                		})
    									}
    								}
    						)
    				}		
    			)	
    		}
    	} 
  //遮罩
	function layerMsg(text){
		layer.open({
	    	  type: 1,
	    	  closeBtn: 0,
	    	  content: text,
	    	  area : ['10%','10%'],
	    }); 
	}
	</script>

</body>
</html>