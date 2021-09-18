<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>保税审单</title>
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
										<option value="">--请选择查询条件--</option>
										<option value="txLogisticID">客户订单号</option>
										<option value="receiveMan">收件人姓名</option>
										<option value="pc">备注</option>
										<option value="itemName">主要商品</option>
										<option value="mailNo">运单号</option>
										<option value="payNumber">支付流水号</option>
										<option value="merchantNum">商家编码</option>
									</select>
		  							</div>
									<input type="text" class="form-control" onkeydown='if(event.keyCode==13){return false;}' id="getValue" placeholder="请输入关键词" style="height: 34px"/>
									<span class="input-group-btn"><!--不换行，与相邻元素内联-->
		      						<a class="btn btn-primary" id="search">
		      							<i class="fa fa-search"></i>
		      							&nbsp;搜索
		      						</a>
									</span>
								</div>
							</li>
<!-- 							<li><a class="btn btn-primary btn-rounded" -->
<!-- 								id="search"><i class="fa fa-search"></i>&nbsp;搜索</a></li> -->

						</ul>
					</div>
				</form>
			</div>

			<div class="btn-group-sm" role="group">
				<button type="button" class="btn btn-success" style="margin-top: 10px;" id="add" onclick="auditOrder()"> <i class="fa fa-plus-square"></i> 
				审核
				</button>
				<button type="button" class="btn btn-success" style="margin-top: 10px;" id="add" onclick="matchMailNo()"> <i class="fa fa-plus-square-o"></i> 
				匹配运单号
				</button>
				<button type="button" id="order_bonded_btn_2" style="margin-top: 10px;" class="btn btn-success" onclick="jzht()">  <i class="fa fa-plus-square-o"></i> 
				介舟回填
				</button> 
				<button type="button" id="order_bonded_btn_4" style="margin-top: 10px;" class="btn btn-success"  onclick="customs()"><i class="fa fa-credit-card"></i>
				推送运单
				</button>
				<button type="button" id="order_bonded_btn_8" style="margin-top: 10px;" class="btn btn-success"  onclick="pushOrderToWms()"><i class="fa fa-credit-card"></i>
				推送WMS
				</button>
			<!-- 	<button type="button" id="order_bonded_btn_9" style="margin-top: 10px;" class="btn btn-success" onclick="pushDd()"><i class="fa fa-credit-card"></i>
				推送订单
				</button>
			
				<button type="button" id="order_bonded_btn_5" style="margin-top: 10px;" class="btn btn-success" onclick="deliverGoods()"><i class="fa fa-plus-square"></i> 
				完结
				</button> -->
			<!-- 	 <button type="button" id="order_bonded_btn_5" style="margin-top: 10px;" class="btn btn-success" onclick="pushGoods()"><i class="fa fa-print"></i> 
				推送打印
				</button>  
		 -->
				<button type="button" id="order_bonded_btn_14" style="margin-top: 10px;" class="btn btn-success" onclick="cancleOrderBonded()"><i class="fa fa-plus-square"></i> 
				取消订单
				</button>
				<button type="button" id="order_bonded_btn_14" style="margin-top: 10px;" class="btn btn-success" onclick="deleteByIds()"><i class="fa fa-plus-square"></i> 
				删除订单
				</button>
			
				<button type="button" class="btn btn-success" style="margin-top: 10px;" onclick="exportOrder()"> <i class="fa fa-plus-square"></i> 
				导出
				</button>
				
				
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
			<form id="editform" action="<%=basePath%>orderBonded/exportOrderBonded_2.do" method="post">
					<input type="hidden" id="ids" name="ids">
					<input type="hidden" id="queryName" name="queryName">
					<input type="hidden" id="queryValue" name="queryValue">
			</form>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
//从什么时候到什么时候地址
function saveQueryTime(){
	
	layer.open({
	  	  title: '选填查询时间,间隔不超过4天',
	  	  type: 2,
	  	  closeBtn: 2,
	  	  content: '<%=path%>/orderBonded/betweenOldNewUi.do',
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
}
//从什么时候到什么时候地址
function savekjTime(){
	
	layer.open({
	  	  title: '选填月份',
	  	  type: 2,
	  	  closeBtn: 2,
	  	  content: '<%=path%>/orderBonded/betweenOldNewUi2.do',
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
}
function savesjTime(){
	
	layer.open({
	  	  title: '选择时间',
	  	  type: 2,
	  	  closeBtn: 2,
	  	  content: '<%=path%>/orderBonded/betweenOldNewUi3.do',
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
function pushOrderToYf(){  

	var row = $("#bootstrap-table").bootstrapTable('getSelections');
	var length =row.length;
	if(length==0){
		$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
	}
	    var ids = ""; 
	    for (var i = 0; i < row.length; i++){
		var ids = ids + row[i].id +",";
       if(row[i].payNumber != null||row[i].payNumber==''){
    	   $.modal.alertWarning("系统提示:已有支付流水号，无需推送平台！");
   			return false;
   		}
      	 if(row[i].auditstatus != 2){
      		$.modal.alertWarning("系统提示:审核状态为已审核已匹配的订单才可推送，请重新选择！");
   			return false;
   		}
   	}
  ids = ids.substring(0, ids.length-1); 
  $.modal.alertWarning('系统提示:确定要推送这<font color=red>'+row.length+'条数据吗?',function(r){
    	if (r){
       		$.post('<%=basePath%>orderBonded/pushOrderToYf.do',{
       				"ids":ids 
       			},
       			function(result){
                  	reload("#bootstrap-table");
                  	if (result == 1){ 
                   		$.messager.show({
                    		title: '提示',
                    		msg: '推送成功'
                    	});
                    } else { 
                    	$.messager.show({ 
                    		title: 'Error',
                    		msg: '推送失败'
                    	});
                    }
            	}
            );
       	}  
   	});  
}
//删除订单
function deleteByIds(){
	var rows = $("#bootstrap-table").bootstrapTable('getSelections');
		var length =rows.length;
		if(length==0){
			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
		}else{
			$.modal.confirm('确定要删除这<font color=red>'+rows.length+'条订单吗?',function(){
	       		layerMsg("删除中...");
						var ids="";
						for(var i=0;i<length;i++){
							if(i==0){
								ids += rows[i].id;
							}else{
								ids += ","+ rows[i].id;
							}
						}
						$.post('<%=path%>/orderBonded/deleteOrderBonded.do',{ids:ids},
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
//取消订单
function cancleOrderBonded(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].isPostQd == 1){
          		$.modal.alertWarning("清单已推送的不能进行取消操作!");
	   			return false;
	   		}
       	}
       	ids = ids.substring(0, ids.length-1); 
    	$.modal.confirm('确定要取消这<font color=red>'+row.length+'条订单吗?',function(){
       		layerMsg("取消中...");
           		$.post('<%=basePath%>orderBonded/cancleOrder.do',{
           				"ids":ids 
           			},
           			function(result){
           				layer.close(layer.index);
	                  	var code = result[0];
						var message = result[1];
						if(code==1){								
							layer.msg("取消成功", {
	                			  icon: 1,
	                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
						}else{								
							layer.msg("取消失败", {
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
           	})
	}
//推送电商大师
function pushOrderToDsds(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		}
          	if(row[i].ispost == 1){
          		$.modal.alertWarning("已推送的订单请不要重复推送，请重新选择！");
	   			return false;
	   		}
       	}
       	ids = ids.substring(0, ids.length-1); 
    	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/pushOrderToDsds.do',{
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
//匹配代收货款运单号
function matchDSHKMailNo(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
		var ids = ""; 
	    for (var i = 0; i < row.length; i++){
		var ids = ids + row[i].id +",";
		
		if(row[i].auditstatus == 0){
			$.modal.alertWarning("未审核的订单不能进行匹配！");
   			return false;
   		}
		if(row[i].auditstatus == 2){
			$.modal.alertWarning("已匹配的订单不能进行匹配！");
   			return false;
   		}
      	if(row[i].auditstatus == 9){
      		$.modal.alertWarning("已匹配的订单不能进行匹配！");
   			return false;
   		}
		if(row[i].mailNo != undefined && row[i].mailNo != ""){
			$.modal.alertWarning("快递为STO且运单号为空的订单才能进行匹配！");
   			return false;
   		} 
   		/* if(row[i].carrier != "HTO"){
   			$.messager.confirm('系统提示',"快递为HTO且运单号为空的订单才能进行匹配！");
   			return false;
   		} */
   	}
   	ids = ids.substring(0, ids.length-1); 
   	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
   		layerMsg("匹配中...");
       		$.post('<%=basePath%>orderBonded/matchDSHKMailNo.do',{
       				"ids":ids 
       			},
       			function(result){
       				layer.close(layer.index);
                  	if (result == 1){ 
                  		layer.msg("匹配成功", {
              			  icon: 1,
              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
              			}, function(index){
              			  //do something
              				$("#bootstrap-table").bootstrapTable("refresh", {
        					    silent: true //静态刷新
        					});
              		})
                    } else { 
                    	layer.msg("匹配失败", {
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
//推送CFM
function pushCFM(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
		var ids="";
	for(var i = 0; i < row.length; i++){
		if(row[i].ispost == 1){
			$.modal.alertWarning("已推送的订单请不要重复推送，请重新选择！");
   			return false;
   		}
		if(i==0){
			ids += row[i].id;
		}else{
			ids += ","+ row[i].id;
		}
		
	}
	
	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
   		layerMsg("推送中...");
		$.post('<%=basePath%>orderBonded/pushForCFM.do',{"ids":ids},
				function(result){
			layer.close(layer.index);
			if(result == "success"){
				layer.msg("推送成功", {
        			  icon: 1,
        			  time: 700 //2秒关闭（如果不配置，默认是3秒）
        			}, function(index){
        			  //do something
        				$("#bootstrap-table").bootstrapTable("refresh", {
  					    silent: true //静态刷新
  					});
        		})
			}else{
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
		})
	})
}
//匹配邦付宝流水号
function payNumberBfb(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		var ids="";
		for(var i = 0; i < row.length; i++){
			if(i==0){
				ids += row[i].id;
			}else{
				ids += ","+ row[i].id;
			}
			
		}
		$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
	   		layerMsg("匹配中...");
	   		var z="2";
			$.post('<%=basePath%>orderBonded/pushBfb.do',{"id":ids,"no":z},
					function(result){
				layer.close(layer.index);
				if(result == "success"){
					layer.msg("匹配成功", {
	          			  icon: 1,
	          			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	          			}, function(index){
	          			  //do something
	          				$("#bootstrap-table").bootstrapTable("refresh", {
	    					    silent: true //静态刷新
	    					});
	          		})
				}else{
					layer.msg("匹配失败", {
	          			  icon: 5,
	          			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
	          			}, function(index){
	          			  //do something
	          				$("#bootstrap-table").bootstrapTable("refresh", {
	    					    silent: true //静态刷新
	    					});
	          		})
				}
			})
		})
	}
//推送联动
function pushBfb(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
	var ids="";
	for(var i = 0; i < row.length; i++){
		if(i==0){
			ids += row[i].id;
		}else{
			ids += ","+ row[i].id;
		}
		
	}
	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
	   		layerMsg("推送中...");
			var z="1";
			$.post('<%=basePath%>orderBonded/pushBfb.do',{"id":ids,"no":z},
					function(result){
				layer.close(layer.index);
				if(result == "success"){
					layer.msg("推送成功", {
          			  icon: 1,
          			  time: 700 //2秒关闭（如果不配置，默认是3秒）
          			}, function(index){
          			  //do something
          				$("#bootstrap-table").bootstrapTable("refresh", {
    					    silent: true //静态刷新
    					});
          		})
				}else{
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
			})
	})
}
//推送联动支付重新支付推送
function pushBfbzf(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
	if(row.length==0){  
		$.modal.alertWarning("请至少选择一条记录");
    	return false;  
	}
var ids="";
for(var i = 0; i < row.length; i++){
	if(i==0){
		ids += row[i].id;
	}else{
		ids += ","+ row[i].id;
	}
	
}
$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
   		layerMsg("推送中...");
		var z="3";
		$.post('<%=basePath%>orderBonded/pushBfb.do',{"id":ids,"no":z},
				function(result){
			layer.close(layer.index);
			if(result == "success"){
				layer.msg("推送成功", {
      			  icon: 1,
      			  time: 700 //2秒关闭（如果不配置，默认是3秒）
      			}, function(index){
      			  //do something
      				$("#bootstrap-table").bootstrapTable("refresh", {
					    silent: true //静态刷新
					});
      		})
			}else{
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
		})
})
}
//双乾支付zf
function pushSqzf(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
	var ids="";
	for(var i = 0; i < row.length; i++){
		if(i==0){
			ids += row[i].id;
		}else{
			ids += ","+ row[i].id;
		}
		
	}
	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
	   		layerMsg("推送中...");
			var z="1";
			$.post('<%=basePath%>orderBonded/pushSqzf.do',{"id":ids,"no":z},
					function(result){
				layer.close(layer.index);
				if(result == "success"){
					layer.msg("推送成功", {
          			  icon: 1,
          			  time: 700 //2秒关闭（如果不配置，默认是3秒）
          			}, function(index){
          			  //do something
          				$("#bootstrap-table").bootstrapTable("refresh", {
    					    silent: true //静态刷新
    					});
          		})
				}else{
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
			})
	})
}

//匹配双乾流水号
function payNumbersqzf(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		var ids="";
		for(var i = 0; i < row.length; i++){
			if(i==0){
				ids += row[i].id;
			}else{
				ids += ","+ row[i].id;
			}
			
		}
		$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
	   		layerMsg("匹配中...");
	   		var z="2";
			$.post('<%=basePath%>orderBonded/payNumberSQ.do',{"id":ids,"no":z},
					function(result){
				layer.close(layer.index);
				if(result == "success"){
					layer.msg("匹配成功", {
	          			  icon: 1,
	          			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	          			}, function(index){
	          			  //do something
	          				$("#bootstrap-table").bootstrapTable("refresh", {
	    					    silent: true //静态刷新
	    					});
	          		})
				}else{
					layer.msg("匹配失败", {
	          			  icon: 5,
	          			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
	          			}, function(index){
	          			  //do something
	          				$("#bootstrap-table").bootstrapTable("refresh", {
	    					    silent: true //静态刷新
	    					});
	          		})
				}
			})
		})
	}
//合利宝
 function helipay(){  
	 var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
   		if(row.length==0){
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
 		}else{
	   		var ids="";
			for(var i=0;i<row.length;i++){
				if(i==0){
					ids += row[i].id;
				}else{
					ids += ","+ row[i].id;
				}
			}
   		}
   		$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
 	   		layerMsg("匹配中...");
           		$.post('<%=basePath%>helipay/payOrderaudit.do',{"ids":ids },
           			function(result){
           			layer.close(layer.index);
	                  	var date=eval('('+result+')');
	                  	if (date.errorCode == "0000"){ 
	                  		layer.msg("成功", {
	                			  icon: 1,
	                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
	                    } else { 
	                    	layer.msg("失败", {
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
//匹配支付宝流水号
function payNumberKQ(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
 	   		layerMsg("匹配中...");
           		$.post('<%=basePath%>orderBonded/payNumberKQ.do',{
           				"ids":ids 
           			},
           			function(result){
           				layer.close(layer.index);
	                  	if (result == 1){ 
	                  		layer.msg("匹配成功", {
	                			  icon: 1,
	                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
	                    } else { 
	                    	layer.msg("匹配失败", {
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
//匹配支付流水号
 function payNumber1() {
	 var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
	 if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
	   		var txLogisticID = ""; 
			
	 	    for (var i = 0; i < row.length; i++){
	 	    	
	 	    	// if(row[i].payNumber == undefined || row[i].payNumber ==''){
	 	    		var txLogisticID = txLogisticID + row[i].txLogisticID +",";
		   	/* 	} else{
		   			$.messager.confirm('系统提示',"<font color=red>已匹配支付流水号不能再次进行匹配！");
		   			return false;
		   		}  */
				    
	       	}
	 	    
	 	    txLogisticID = txLogisticID.substring(0, txLogisticID.length-1); 
	 	   $.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
	 	   		layerMsg("匹配中...");
	           		$.post('<%=basePath%>orderBonded/getPayNumber1.do',{
	           				"txLogisticID":txLogisticID 
	           			},
	           			function(result){
	           				layer.close(layer.index);
		                  	if (result == 1){ 
		                  		layer.msg("匹配成功", {
		                			  icon: 1,
		                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(index){
		                			  //do something
		                				$("#bootstrap-table").bootstrapTable("refresh", {
		          					    silent: true //静态刷新
		          					});
		                		})
		                    } else if(result == 0){
		                    	layer.msg("<font color=red>数据已全部匹配完成！", {
		                			  icon: 1,
		                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(index){
		                			  //do something
		                				$("#bootstrap-table").bootstrapTable("refresh", {
		          					    silent: true //静态刷新
		          					});
		                		})
		                    	
		                    }else { 
		                    	layer.msg("匹配失败", {
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
//推送打印
function pushGoods(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
		
	    var ids = ""; 
	    for (var i = 0; i < row.length; i++){
		var ids = ids + row[i].id +",";
   		if(row[i].auditstatus == 9){
   			$.modal.alertWarning("已完结的订单不要重复发货，请重新选择！");
   			return false;
   		}
      	if(row[i].auditstatus != 2){
      		$.modal.alertWarning("已审核已匹配的订单才可推送，请重新选择！");
   			return false;
   		}
   	}
   	ids = ids.substring(0, ids.length-1); 
   	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
   		layerMsg("推送中...");
       		$.post('<%=basePath%>orderBonded/pushGoods.do',{
       				"ids":ids 
       			},
       			function(result){
       				layer.close(layer.index);
                    if(result==2){
                    	layer.msg("已推送的数据不能重复推送", {
                			  icon: 5,
                			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
                			}, function(index){
                			  //do something
                				$("#bootstrap-table").bootstrapTable("refresh", {
          					    silent: true //静态刷新
          					});
                		})
                    }
                  	else { 
                  		layer.msg("推送成功", {
              			  icon: 1,
              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
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
//完结
function deliverGoods(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
		
	    var ids = ""; 
	    for (var i = 0; i < row.length; i++){
		var ids = ids + row[i].id +",";
   		if(row[i].auditstatus == 9){
   			$.modal.alertWarning("已完结的订单不要重复发货，请重新选择！");
   			return false;
   		}
      	if(row[i].auditstatus != 2){
      		$.modal.alertWarning("已审核已匹配的订单才可发货，请重新选择！");
   			return false;
   		}
   	}
   	ids = ids.substring(0, ids.length-1); 
   	$.modal.confirm('确定要完结这<font color=red>'+row.length+'条数据吗?',function(){
   		layerMsg("完结中...");
       		$.post('<%=basePath%>orderBonded/deliverGoods.do',{
       				"ids":ids 
       			},
       			function(result){
       				layer.close(layer.index);
                  	if (result == 1){ 
                  		layer.msg("发货成功", {
              			  icon: 1,
              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
              			}, function(index){
              			  //do something
              				$("#bootstrap-table").bootstrapTable("refresh", {
        					    silent: true //静态刷新
        					});
              		})
                    } else if(result == -2){
                    	layer.msg("库存不足", {
              			  icon: 5,
              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
              			}, function(index){
              			  //do something
              				$("#bootstrap-table").bootstrapTable("refresh", {
        					    silent: true //静态刷新
        					});
              		})
                    }else { 
                    	layer.msg("发货失败", {
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
//推送三单
function push3(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections'); 
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		}
          	/* if(row[i].isPushQd == 1){
	   			$.messager.confirm('系统提示',"已推送的清单不要重复报关，请重新选择！");
	   			return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
     	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/push3.do',{
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
//推送运单
function customs(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	/* if(row[i].auditstatus != 2){
	   			$.messager.confirm('系统提示',"审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		} */
          	/* if(row[i].isCustoms == 1){
	   			$.messager.confirm('系统提示',"是否");
	   			//return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/customs.do',{
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
//推送清单
function pushQd(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		}
          	/* if(row[i].isPushQd == 1){
	   			$.messager.confirm('系统提示',"已推送的清单不要重复报关，请重新选择！");
	   			return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/pushQd.do',{
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
	                    }/* else if(result == 9) { 
	                    	$.messager.show({ 
	                    		title: '提示',
	                    		msg: '推送清单接口被占用，请稍后再推送！'
	                    	});
	                    } */ else { 
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
//推送订单
function pushDd(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		}
          	/* if(row[i].isPushDd == 1){
	   			$.messager.confirm('系统提示',"已推送的订单不要重复报关，请重新选择！");
	   			return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
    	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/pushDd.do',{
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
//推送WMS
function pushOrderToWms(){  
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
         /*  	if(row[i].auditstatus < 2){
          		$.modal.alertWarning("已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		} */
          	if(row[i].isPushToWms == 1){
          		$.modal.alertWarning("已推送到WMS的订单请不要重复推送，请重新选择！");
	   			return false;
	   		}
          	if((!row[i].billProvideSiteCode) && row[i].merchantNum=='XGGJ' ){
          		$.modal.alertWarning("西狗的请先匹配三段码！");
	   			return false;
          	}
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/pushOrderToWms.do',{
           				"ids":ids 
           			},
           			function(result){
           				layer.close(layer.index);
	                  	if(result == "推送成功！") {
							layer.msg("推送成功", {
	                			  icon: 1,
	                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
	                  	}else {
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
//推送支付宝支付
	function pushOrderZFB(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择!");
	   			return false;
	   		}
          	if(row[i].ispost == 1){
          		$.modal.alertWarning("已推送的订单请不要重复推送，请重新选择！");
	   			return false;
	   		}
       	}
       	ids = ids.substring(0, ids.length-1); 
    	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/pushOrderZFB.do',{
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
	
//推送平台
function pushOrder(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		}
          	if(row[i].ispost == 1){
          		$.modal.alertWarning("已推送的订单请不要重复推送，请重新选择！");
	   			return false;
	   		}
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderBonded/pushOrder.do',{
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
//匹配汇通直发
function matchMailNo3ForHTO() {
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
	if(row.length==0){  
		$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
		var ids = ""; 
	    for (var i = 0; i < row.length; i++){
		var ids = ids + row[i].id +",";
		
		if(row[i].txLogisticID.indexOf("Z")==-1){
			$.modal.alertWarning("这单子不是直发单子！不能匹配直发单号!");
   			return false;
   		}
		if(row[i].auditstatus == 0){
			$.modal.alertWarning("未审核的订单不能进行匹配！");
   			return false;
   		}
		if(row[i].auditstatus == 2){
			$.modal.alertWarning("已匹配的订单不能进行匹配！");
   			return false;
   		}
      	if(row[i].auditstatus == 9){
      		$.modal.alertWarning("已完结的订单不能进行匹配！");
   			return false;
   		}
		if(row[i].mailNo != undefined && row[i].mailNo != ""){
			$.modal.alertWarning("快递为HTO且运单号为空的订单才能进行匹配！");
   			return false;
   		}
		if(row[i].carrier != "HTO"){
			$.modal.alertWarning("快递为HTO且运单号为空的订单才能进行匹配！");
   			return false;
   		}
		
   		/* if(row[i].carrier != "HTO"){
   			$.messager.confirm('系统提示',"快递为HTO且运单号为空的订单才能进行匹配！");
   			return false;
   		} */
   	}
   	ids = ids.substring(0, ids.length-1); 
   	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
   		layerMsg("匹配中...");
       		$.post('<%=basePath%>orderBonded/matchMailNo3ForHTO.do',{
       				"ids":ids 
       			},
       			function(result){
       				layer.close(layer.index);
                  	if (result == 1){ 
                  		
						layer.msg("匹配成功", {
                			  icon: 1,
                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
                			}, function(index){
                			  //do something
                				$("#bootstrap-table").bootstrapTable("refresh", {
          					    silent: true //静态刷新
          					});
                		})
                    } else { 
						layer.msg("匹配失败", {
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
//匹配汇通代收货款
function matchMailNo2ForHTO() {
		var row = $("#bootstrap-table").bootstrapTable('getSelections');  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
			
			if(row[i].txLogisticID.indexOf("D")==-1){
				$.modal.alertWarning("这单子不是COD单子！不能匹配代收货款!");
	   			return false;
	   		}
			if(row[i].auditstatus == 0){
				$.modal.alertWarning("未审核的订单不能进行匹配");
	   			return false;
	   		}
			if(row[i].auditstatus == 2){
				$.modal.alertWarning("已匹配的订单不能进行匹配！");
	   			return false;
	   		}
          	if(row[i].auditstatus == 9){
          		$.modal.alertWarning("已完结的订单不能进行匹配！");
	   			return false;
	   		}
			if(row[i].mailNo != undefined && row[i].mailNo != ""){
				$.modal.alertWarning("这单子不是COD单子！不能匹配代收货款!");
	   			$.messager.confirm('系统提示',"");
	   			return false;
	   		}
			if(row[i].carrier != "HTO"){
				$.modal.alertWarning("这单子不是COD单子！不能匹配代收货款!");
	   			return false;
	   		}
			
	   		/* if(row[i].carrier != "HTO"){
	   			$.messager.confirm('系统提示',"快递为HTO且运单号为空的订单才能进行匹配！");
	   			return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
    	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
    			layerMsg("匹配中...");
           		$.post('<%=basePath%>orderBonded/matchMailNo2ForHTO.do',{
           				"ids":ids 
           			},
           			function(result){
	                  	if (result == 1){ 
	                  		layer.close(layer.index);
							layer.msg("匹配成功", {
	                			  icon: 1,
	                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
	                    } else { 
	                    	layer.close(layer.index);
							layer.msg("匹配失败", {
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
//介舟回填
function jzht(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
	var length =row.length;
	if(length==0){
		$.modal.alertWarning("请至少选择一条记录");
		return false;
	}else{
		$.modal.confirm('确定要推送这<font color=red>'+row.length+'条数据吗?',function(){
					var ids="";
					for(var i=0;i<length;i++){
						if(i==0){
							ids += row[i].id;
						}else{
							ids += ","+ row[i].id;
						}
			         
					}
					layerMsg("推送中...");
					$.post('<%=path%>/orderBonded/matchSDM.do',{ids:ids},
							function(ret){
								if(ret==1){
									layer.close(layer.index);
									layer.msg("推送成功", {
			                			  icon: 1,
			                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
			                			}, function(index){
			                			  //do something
			                				$("#bootstrap-table").bootstrapTable("refresh", {
			          					    silent: true //静态刷新
			          					});
			                		})
								}else{
									layer.close(layer.index);
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
					)
					
				})
			}
	}
//匹配运单号
function matchMailNo() {
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
	    	return false;  
		}
		var ids = ""; 
	    for (var i = 0; i < row.length; i++){
		var ids = ids + row[i].id +",";
	
		if(row[i].auditstatus == 0){
			$.modal.alertWarning("未审核的单子不能匹配");
   			return false;
   		}
		if(row[i].auditstatus == 2){
			$.modal.alertWarning("已匹配的单子不能匹配");
   			return false;
   		}
      	if(row[i].auditstatus == 9){
      		$.modal.alertWarning("已完结的单子不能匹配");
   			return false;
   		}
		
   		/* if(row[i].carrier != "HTO"){
   			$.messager.confirm('系统提示',"快递为HTO且运单号为空的订单才能进行匹配！");
   			return false;
   		} */
   	}
   	ids = ids.substring(0, ids.length-1); 
	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条数据吗?',function(){
			layerMsg("匹配中...");
       		$.post('<%=basePath%>orderBonded/matchMailNo.do',{
       				"ids":ids 
       			},
       			function(result){
       				layer.close(layer.index);
                  	if (result == 1){ 
                  		layer.msg("匹配成功", {
                			  icon: 1,
                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
                			}, function(index){
                			  //do something
                				$("#bootstrap-table").bootstrapTable("refresh", {
          					    silent: true //静态刷新
          					});
                		})
                    } else { 
                    	layer.msg("匹配失败", {
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



//审核
function auditOrder(){ 
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一条记录");
			return false;
		}
	    var paramJson = "{'paramList':["; 
	    for (var i = 0; i < row.length; i++){
		var param = "{'id':"+row[i].id+",'mailNo':'"+row[i].mailNo+"','txLogisticID':'"+row[i].txLogisticID+"','itemCount':'"+row[i].itemCount+"','merchantNum':'"+row[i].merchantNum+"','worth':'"+row[i].worth+"','buyerName':'"+row[i].buyerName+"','buyerIdNumber':'"+row[i].buyerIdNumber+"'},";
	    	paramJson = paramJson + param;
      	if(row[i].auditstatus != 0 && row[i].auditstatus != 3 && row[i].auditstatus != 4){
      		$.modal.alertWarning("已审核的订单无需重复审核，请重新选择！");
   			return false;
   		}
   	}
   	paramJson = paramJson.substring(0, paramJson.length-1); 
   	paramJson = paramJson + "]}";
   	$.modal.confirm('确定要审核这<font color=red>'+row.length+'条数据吗?',function(){
    		//调用小窗口阻止操作
    		 layerMsg("审核中...");
    		//$("#audit_order_bxd").dialog("open").dialog("setTitle","提示");
       		$.post('<%=basePath%>orderBonded/auditOrder.do',{
       				"paramJson":paramJson},
       			function(result){ 
       				layer.close(layer.index);
       				$("#bootstrap-table").bootstrapTable("refresh", {
					    silent: true //静态刷新
					});
                  	if (result == -1){ 
                  		layer.msg("审核成功", {
              			  icon: 1,
              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
              			}, function(index){
              			  //do something
              				$("#bootstrap-table").bootstrapTable("refresh", {
        					    silent: true //静态刷新
        					});
              			})
                    }else if(result == -2){
                    	layer.msg("剩余金额不足", {
                			  icon: 5,
                			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
                			}, function(index){
                			  //do something
                				$("#bootstrap-table").bootstrapTable("refresh", {
            					    silent: true //静态刷新
            					});
                			})
                    }else if(result == -3){
                    	layer.msg("商家未充值", {
              			  icon: 5,
              			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
              			}, function(index){
              				layer.close(index);
              			  //do something
              				$("#bootstrap-table").bootstrapTable("refresh", {
					   	 silent: true //静态刷新
						}); 
              			})
                    }else if(result == -4){
                    	layer.msg("已审核订单不能重复审核", {
              			  icon: 5,
              			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
              			}, function(index){
              				layer.close(index);
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
				url : '<%=path%>/orderBonded/findAll.do',
				//排序字段名
				sortName : "createDateTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'merchantNum',
						title : '商家编码',
						align : 'center'
					}, {
						field : 'receiveMan',
						title : '姓名',
						align : 'center'
					}, {
						field : 'receiveManPhone',
						title : '电话',
						align : 'center'
					}, {
						field : 'txLogisticID',
						title : '订单号',
						align : 'center'
					}, {
						field : 'itemName',
						title : '主要商品',
						align : 'center'
					}, {
						field : 'itemWeight',
						title : '重量',
						align : 'center'
					}, {
						field : 'itemCount',
						title : '总数量',
						align : 'center'
					}, {
						field : 'carrier',
						title : '快递',
						align : 'center'
					}, {
						field : 'mailNo',
						title : '运单号',
						align : 'center'
					}, {
						field : 'worth',
						title : '总价值',
						align : 'center'
					}, {
						field : 'payNumber',
						title : '支付流水号',
						align : 'center'
					}, {
						field : 'createTime',
						title : '创建时间',
						align : 'center',
						formatter:function(value){
                  			return formatDate(value);
                  		}	
					},{field :'ispost', title :'推送平台', width :40, align :'center',formatter:function(value){ 
						if(value == 0){
                	   		return '未推送';
	                   	}else if(value == 1){
	                		return '已推送';
	                   	}
	                } 
	            },
	            {field :'isPushDd', title :'推送订单', width :40, align :'center',formatter:function(value){ 
						if(value == 0){
                	   		return '未推送';
	                   	}else if(value == 1){
	                		return '已推送';
	                   	}
	                } 
	            },
	            {field :'isPushQd', title :'推送清单', width :40, align :'center',formatter:function(value){ 
						if(value == 0){
                	   		return '未推送';
	                   	}else if(value == 1){
	                		return '已推送';
	                   	}
	                } 
	            },
				{field :'isCustoms', title :'推送运单', width :40, align :'center',formatter:function(value){ 
						if(value == 0){
                	   		return '未推送';
	                   	}else if(value == 1){
	                		return '已推送';
	                   	}else if(value == 2){
	                		return '海关放行';
	                   	}
	                } 
	            },
              	{field :'auditstatus', title :'审核状态', width :60, align :'center',formatter:function(value){ 
                	   	if(value == 0){
                	   		return '未审核';
	                   	}else if(value == 1){
	                		return '已审核未匹配';
	                   	}else if(value == 2){
	                		return '已审核已匹配';
	                   	}else if(value == 3){
	                		return '未匹配到商品';
	                   	}else if(value == 4){
	                		return '库存不足';
	                   	}else if(value == 7){
	                		return '异常订单';
	                   	}else if(value == 8){
	                		return '取消订单';
	                   	}else if(value == 9){
	                		return '已完结';
	                   	}
                 	} 
              	},
	            {field :'returncode', title :'回执状态', width :60, align :'center',formatter:function(value,row,index){ 
            	   	if(value == 0){
            	   		return '待处理';
                   	}else if(value == 1){
                		return "<a href='javascript:detailBonded("+row.id+")'>回执状态</a>";
                   	}else if(value == 2){
                		return '待抵运';
                   	}else if(value == 3){
                		return '查验';
                   	}else if(value == 9){
                		return '放行';
                   	}
             	},
               }],
               onDblClickRow: function (row) {
            	   layer.open({
           	    	  title: row.txLogisticID+'的订单详情',
           	    	  type: 2,
           	    	  closeBtn: 2,
           	    	  content: '<%=path%>/orderBonded/findInfoUi.do?txLogisticID='+row.txLogisticID,
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
            temp["rows"] = params.limit;                        //页面大小
            temp["page"] = (params.offset / params.limit) + 1;  //页码
            temp["sort"] = params.sort;                         //排序列名
            temp["sortOrder"] = params.order;                   //排位命令（desc，asc） 
            //特殊格式的条件处理
            temp[name] = $("#getValue").val();
            temp["auditstatus"] = "4";
	        return temp;
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