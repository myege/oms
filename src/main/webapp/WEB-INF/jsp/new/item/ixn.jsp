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
<title>玄鸟备案</title>
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
										<option value="text5">商家ID</option>
										<option value="text4">条形码</option>
										<option value="text3">sku编号</option>
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
		     	<a class="btn btn-success" id="add" onclick="zhuaqu()"> <i class="fa fa-plus"></i> 抓取备案信息
				</a> 
				<a class="btn btn-success" id="add" onclick="tg()"> <i class="fa fa-edit"></i> 通过
				</a> 
				<a class="btn btn-success" id="add"  onclick="bohui()"> <i class="fa fa-edit"></i>
					驳回
				</a>
				
					<a class="btn btn-success" id="add"  onclick="exportOrder()"> <i class="fa fa-edit"></i>
					导出
				</a>
	      
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
			
			<form id="editform" action="<%=path%>/jdxn/exportba.do" method="post">
					<input type="hidden" id="ids" name="ids">
					<input type="hidden" id="queryName" name="queryName">
					<input type="hidden" id="queryValue" name="queryValue">
			</form>
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

	function importItem() {
		var openUrl = "<%=path%>/import/importItem.jsp";//弹出窗口的url
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
				url : '<%=path%>/jdxn/findAll.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/jdxn/editUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/jdxn/addUi.do',
				//导入地址
				importUrl : '<%=path%>/jdxn/importItem.do',
				//排序字段名
				sortName : "createDateTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
				           {
					field : 'xtzt',
					title : '系统状态',
					align : 'center',
					formatter : function(value) {
						if (value == 0) {
							return "待处理"
						} else if (value == 1) {
							return "审核通过"
						}else if (value == 2) {
							return "驳回"
						}
					}
				    } ,  {
						field : 'note',
						title : '同步回执',
						align : 'center'
					},  {
						field : 'rejectInfo',
						title : '驳回原因',
						align : 'center'
					}, {
						field : 'venderId',
						title : '商家ID',
						align : 'center'
					}, {
						field : 'upc',
						title : '条形码',
						align : 'center'
					}, {
						field : 'skuId',
						title : 'sku编号',
						align : 'center'
					}, {
						field : 'spe',
						title : '规格',
						align : 'center'
					}, {
						field : 'goodsName',
						title : '商品名称',
						align : 'center'
					}, {
						field : 'brandEn',
						title : '品牌',
						align : 'center'
					} , {
						field : 'createtime',
						title : '创建时间',
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
				var f = document.getElementById("editform");
				f.submit(); 
				
			}else{
				ids = "cxqb";
				var value = $("#getValue").val();
				var name = $('#serachname option:selected') .val();
				var v = document.getElementById("queryName");
				v.value = name;
				var v1 = document.getElementById("queryValue");
				v1.value = value;
				var v2 = document.getElementById("ids");
				v2.value = ids;
				//提交表单可以这样  
				var f = document.getElementById("editform");
				f.submit(); 
			}
	}
	//推送
	function zhuaqu(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');
	   	var ids=1;
	        $.modal.confirm('抓取数据，耗时较长 ，稍等！',function(){
	       		layerMsg("抓取中..");
	       	
	       		//alert(ids);
	   			  $.post('<%=basePath%>jdxn/Zq.do',{
	               				"ids":ids 
	               			}, 
	           			function(result){
	           				layer.close(layer.index);
		                  	if (result == 1){ 
		                  		layer.msg("抓取成功", {
		                			  icon: 1,
		                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(index){
		                			  //do something
		                				$("#bootstrap-table").bootstrapTable("refresh", {
		          					    silent: true //静态刷新
		          					});
		                		})
		                    } else { 
		                    	layer.msg("抓取失败", {
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
	//推送
	function tg(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');
	   		if(row.length==0){  
	   			$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
	   		}
	   		
	 	    var ids = ""; 
	 	   for (var i = 0; i < row.length; i++){
				var ids = ids + row[i].skuId +",";
	          /* 	if(row[i].auditstatus != 2){
	          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
		   			return false;
		   		} */
	          
	       	}
	       	ids = ids.substring(0, ids.length-1); 
	    
	        $.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
	       		layerMsg("推送中..");
	       		//alert(ids);
	   			  $.post('<%=basePath%>jdxn/Tg.do',{
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
	
	//推送
	function bohui(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');
	   		if(row.length==0){  
	   			$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
	   		}
	   		
	 	    var ids = ""; 
	 	   for (var i = 0; i < row.length; i++){
				var ids = ids + row[i].skuId +",";
	          /* 	if(row[i].auditstatus != 2){
	          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
		   			return false;
		   		} */
	          
	       	}
	       	ids = ids.substring(0, ids.length-1); 
	    
	        $.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
	       		layerMsg("推送中..");
	      // 		alert(ids);
	   			  $.post('<%=basePath%>jdxn/Bh.do',{
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