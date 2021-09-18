<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
request.getSession().setAttribute("tz", request.getAttribute("tz") );  //获取完结单与不完结单的区别值
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>完结订单</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="col-sm-12 search-collapse">
				<form id="data-form">
					<div class="select-list">
						<ul>
<!-- 							<li><input type="text" id="text3" name="text3" -->
<!-- 								class="form-control" placeholder="商品名称" /></li> -->
<!-- 							<li><input type="text" id="text4" name="text4" -->
<!-- 								class="form-control" placeholder="商家编码" /></li> -->
<!-- 							<li><input type="text" id="text5" name="text5" -->
<!-- 								class="form-control" placeholder="SKU" /></li> -->
<!-- 							<li><input type="text" id="time3" name="time3" -->
<!-- 								class="form-control" placeholder="开始时间"/></li> -->
<!-- 							<li><span>-</span></li> -->
<!-- 							<li><input type="text" id="time4" name="time4" -->
<!-- 								class="form-control" placeholder="结束时间" /></li> -->
							<li>
								 <div class="input-group"><!--保持内联，消除边框，类似于form-inline-->
		 						<div class="input-group-btn"><!--不换行，与相邻元素内联，包含dropdown-->
			  						<select id="serachname" name="serachname" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
										<option value="">--请选择查询条件--</option>
										<option value="txLogisticID">客户订单号</option>
										<option value="carrier">快递公司</option>
										<option value="mailNo">运单号</option>
										<option value="totalMailNo">提运单号</option>
										<option value="fightNumber">航班号</option>
										<option value="payNumber">支付流水号</option>
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
				<button type="button" id="order_bonded_btn_16" style="margin-top: 10px;" class="btn btn-success" onclick="goBackOrder()"> 
				退回审单
				</button>
			
				<button type="button" id="order_bonded_btn_16" style="margin-top: 10px;" class="btn btn-success" onclick="outMailOrder()"><i class="fa fa-download"></i> 
				导出
				</button>
				<button type="button" id="order_bonded_btn_16" style="margin-top: 10px;" class="btn btn-success" onclick="outMailOrderHZ()"><i class="fa fa-download"></i> 
				导出货物明细汇总(请精确输入提运单号)
				</button>
				<button type="button" id="order_bonded_btn_16" style="margin-top: 10px;" class="btn btn-success" onclick="outMailOrder_TY()"><i class="fa fa-download"></i> 
				提运单号导出(请精确输入提运单号)
				</button>
				
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
	
	<form id="mailId" action="<%=path%>/orderMail/exportOrderMail.do" method="post">
			    <input type="hidden" name="orderMailed" id="orderMailed" value="">
			    <input type="hidden" name="mailValue" id="mailValue" value="">
			    <input type="hidden" name="mailName" id="mailName" value="">
			    <input type="hidden" name="auditstatus" id="auditstatus" value="">
    </form>
    
    <form id="totalMailNo" action="<%=path%>/orderMail/exporttotalMailNo.do" method="post">
			    <input type="hidden" name="totalMailed" id="totalMailed" value="">
			    <input type="hidden" name="totalValue" id="totalValue" value="">
			    <input type="hidden" name="totalName" id="totalName" value="">
			     <input type="hidden" name="auditstatus" id="auditstatus" value="">
    </form>
    
    <form id="HZmail" action="<%=path%>/orderMail/HZimport.do" method="post">
			    <input type="hidden" name="hzMailed" id="hzMailed" value="">
			    <input type="hidden" name="hzValue" id="hzValue" value="">
			    <input type="hidden" name="hzName" id="hzName" value="">
			     <input type="hidden" name="auditstatus" id="auditstatus" value="">
    </form>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
//退回审单
function goBackOrder(){
	var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
	if(row.length==0){  
			$.modal.alertWarning("请至少选择一行数据!");
	    	return false;  
	}
	var ids='';
	$.modal.confirm('确定要退回这<font color=red>'+row.length+'条订单吗?',function(){
   		layerMsg("审核中...");   	
   		for(var i=0;i<row.length;i++){
   			if(i == 0){
   				ids=row[i].id;
   			}else{
   				ids+=","+row[i].id;
   			}
   		}
   		$.post('<%=basePath%>orderMail/goBackOrder.do',{
       				"ids":ids 
       			},
       			function(result){
       				layer.close(layer.index);
                  	if (result > 0){ 
                  		layer.msg("退回审核成功", {
                			  icon: 1,
                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
                			}, function(index){
                			  //do something
                				$("#bootstrap-table").bootstrapTable("refresh", {
          					    silent: true //静态刷新
          					});
                		})
                    }else{
                    	layer.msg("退回审核失败", {
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
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
   		
 	    var paramJson = "{'paramList':["; 
 	    for (var i = 0; i < row.length; i++){
			var param = "{'id':"+row[i].id+",'mailNo':'"+row[i].mailNo+"','txLogisticID':'"+row[i].txLogisticID+"','worth':'"+row[i].worth+"','buyerName':'"+row[i].buyerName+"','buyerIdNumber':'"+row[i].buyerIdNumber+"'},";
 	    	paramJson = paramJson + param;
          	if(row[i].auditstatus == 0||row[i].auditstatus == 3){
	   			
	   		}else{
	   			$.modal.alertWarning("已审核的订单无需重复审核，请重新选择！");
	   			return false;
	   		}
       	}
       	paramJson = paramJson.substring(0, paramJson.length-1); 
       	paramJson = paramJson + "]}";
       	$.modal.confirm('确定要审核这<font color=red>'+row.length+'条订单吗?',function(){
       		layerMsg("审核中...");   	
       		$.post('<%=basePath%>orderMail/auditOrder.do',{
           				"paramJson":paramJson 
           			},
           			function(result){
           				layer.close(layer.index);
	                  	if (result == 1){ 
	                  		layer.msg("审核成功", {
	                			  icon: 1,
	                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
	                    } else if(result == -3){ 
	                    	layer.msg("商家未充值", {
	                			  icon: 5,
	                			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
	                    }else if(result == -2){
	                    	layer.msg("金额不足请充值", {
	                			  icon: 5,
	                			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
	                    }else{
	                    	layer.msg("审核失败", {
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
//提运单号修改
function UpdatetotalMailNo(){
	layer.open({
	  	  title: '提运单号修改',
	  	  type: 2,
	  	  closeBtn: 2,
	  	  content: '<%=path%>/orderMail/UpdatetotalMailNoUi.do',
	  	  btn: ['修改', '取消'],
	  	  isOutAnim : true,
	  	  maxmin  : true,
	  	  anim : 2 ,
	  	  area : ['50%','50%'],
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
//提运单号导出
function outMailOrder_TY(){
	//获取输入框选择的条件
	var name=$('#serachname option:selected') .val();
	//获取输入框输入的值
	var value=$("#getValue").val();
	    	//获取选中行数
	    	var row = $("#bootstrap-table").bootstrapTable('getSelections');

	    	if(name!="totalMailNo"){
	    		$.modal.alertWarning('请选择查询提运单号!');
	    		return;
	    	}
	    	var ids;
	    	if(row.length == 0){
	    		$.modal.confirm('确定导出吗?',function(){
	    				ids = "cxqbdc";		
	    				var v = document.getElementById("totalMailed");
	    				v.value = ids;
	    				//搜索值	
	    				var v1 = document.getElementById("totalValue");
	    				v1.value =value;
	    				//auditstatus
	    				var v3 = document.getElementById("auditstatus");
	    				v3.value = 9;
	    				//提交表单可以这样  
	    				var f = document.getElementById("totalMailNo");
	    				f.submit(); 
	    		})
	    	}else{
	    		var ids = "";
    	  		for (var i = 0; i < row.length; i++) 
    	              {
    	                   if (i == 0) 
    	                  {
    	                        ids += row[i].id;
    	                    
    	                  }else{
    	                        ids += ',' + row[i].id ;

    	                 }
    	              }
    	  		$.modal.confirm('确定要导出?',function(){
	    				var v = document.getElementById("totalMailed");
	    				v.value = ids;
	    				//auditstatus
	    				var v3 = document.getElementById("auditstatus");
	    				v3.value = 9;
	    				//提交表单可以这样  
	    				var f = document.getElementById("totalMailNo");
	    				f.submit();
	    		})
	    	}
	    	
	    }
	    
//导出货物
function outMailOrderHZ() {
		//获取输入框选择的条件
    	var name=$('#serachname option:selected') .val();
    	//获取输入框输入的值
    	var value=$("#getValue").val();
    	//获取选中行数
    	if(name!="totalMailNo"){
    		$.modal.alertWarning('请选择查询提运单号!');
    		return;
    	}
    	
    	if(value == null || value == ""){
    		$.modal.alertWarning('请填写查询提运单号!');
    		return;
    	}

    	$.modal.confirm('确定导出?',function(){
				var l = document.getElementById("hzValue");
				l.value = value;
				//auditstatus
				var v3 = document.getElementById("auditstatus");
				v3.value = 9;
				//提交表单可以这样  
				var f = document.getElementById("HZmail");
				f.submit();
	      }); 
    }

//导出
function outMailOrder() {
		var row = $("#bootstrap-table").bootstrapTable('getSelections');
		var ids = "";
		if (row.length == 0) {
			 layer.open({
				content: '确认导出?'
				,btn: ['确认', '取消']
				,yes: function(index, layero){
				    //按钮【按钮一】的回调
	 	        ids = "cxqbdc";		
				var v = document.getElementById("orderMailed");
				v.value = ids;
				//搜索值	
				var v1 = document.getElementById("mailValue");
				v1.value = $("#getValue").val();
			    //搜索类型
				var v2 = document.getElementById("mailName");
				v2.value = $('#serachname option:selected') .val();
				//auditstatus
				var v3 = document.getElementById("auditstatus");
				v3.value = 9;
				//提交表单可以这样  
				var f = document.getElementById("mailId");
				f.submit(); 
				layer.close(index);
 	            } ,btn2: function(index, layero){
				    //按钮【按钮二】的回调
				      layer.close(index);
				    //return false 开启该代码可禁止点击该按钮关闭
				}
 	   	 });
		}else{ 
			for (var i = 0; i < row.length; i++){
				if(i==row.length-1){
					var ids = ids + row[i].id;
				}else{
					var ids = ids + row[i].id +",";
				}
	    		
	        }
			$.modal.confirm('确定导出?',function(){ 
				var v = document.getElementById("orderMailed");
				v.value = ids;
				//提交表单可以这样  
				var v3 = document.getElementById("auditstatus");
				v3.value = 9;
				var f = document.getElementById("mailId");
				f.submit();
	      }); 
       }  
}
    
//删除
	function deleteOrderMail(){  
	   	var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
	   	if(row.length==0){  
	   		$.modal.alertWarning("请选择数据!");
	   	    return false;  
	   	}
	    var ids = "";  
   	  	for (var i = 0; i < row.length; i++){
   	        ids = ids + row[i].id + ",";
   	   	}
   		$.modal.confirm('确定要删除这<font color=red>'+row.length+'条订单吗?',function(){
   			layerMsg("删除中...");   
   			$.post('<%=basePath%>orderMail/deleteOrderMail.do', {
   				ids:ids
	    	    	}, 
	    	    	function(result){
	    	    		layer.close(layer.index);
		                if(result != null){
		                   	layer.msg("删除成功", {
	                			  icon: 1,
	                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	                			}, function(index){
	                			  //do something
	                				$("#bootstrap-table").bootstrapTable("refresh", {
	          					    silent: true //静态刷新
	          					});
	                		})
		               	} else {
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
	    	    );
		});
    } 

//推送邦付宝
function pushBfb(){
		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
		var ids="";
		for(var i = 0; i < row.length; i++){
			console.log(row[i].id);
			if(i==0){
				ids += row[i].id;
			}else{
				ids += ","+ row[i].id;
			}
			
		}
		$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
			var z="1";
			layerMsg("推送中...");
				$.post('<%=basePath%>orderMail/pushBfb.do',{"id":ids,"no":z},
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
		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
   		var ids="";
		for(var i = 0; i < row.length; i++){
			console.log(row[i].id);
			if(i==0){
				ids += row[i].id;
			}else{
				ids += ","+ row[i].id;
			}
			
		}
		$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条订单吗?',function(){
			layerMsg("匹配中...");
			var z="2";
			$.post('<%=basePath%>orderMail/pushBfb.do',{"id":ids,"no":z},
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

//推送电商大师
	function pushOrderToDsds(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
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
          		$.modal.alertWarning("已推送网趣的订单请不要重复推送，请重新选择！");
	   			return false;
	   		}
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
       		layerMsg("推送中...");
           		$.post('<%=basePath%>orderMail/pushOrderToDsds.do',{
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

//合利宝
function helipay(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
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
   		$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条订单吗?',function(){
   			layerMsg("匹配中...");
           		$.post('<%=basePath%>helipay/pay.do',{"ids":ids },
           			function(result){
           			var date=eval('('+result+')');
           			layer.close(layer.index);
	                  	if (date.errorCode == "0000"){ 
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
	                    	var message = "删除失败,失败原因:"+date.errorCode;
	                    	layer.msg(message, {
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

//匹配国际圆通订单号
function payNumberYto(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
   		var carrier;
   		var ids = ""; 
 		for(var i = 0;i<row.length;i++){
 			carrier = row[i].carrier;
 			if(carrier == "YTOGJ"){
 				console.log(!row[i].mailNo);
 				if(!row[i].mailNo){
 					var ids = ids + row[i].id +",";
 				}else{
 					$.modal.alertWarning("单号为空才能匹配");
 	 				return false;
 				}
 				
 			}else{
 				$.modal.alertWarning("存在不属于YTOGJ数据,请选择YTOGJ");
 				return false;
 			}
 		}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条订单吗?',function(){
       		layerMsg("匹配中...");	
       		$.post('<%=basePath%>yTOPayAction/Yto.do',{
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

//匹配通联支付流水号
function payNumberTL(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条订单吗?',function(){
       			layerMsg("匹配中...");
           		$.post('<%=basePath%>orderMail/payNumberTL.do',{
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
 function payNumber() {
			var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
	   		var txLogisticID = ""; 
			
	 	    for (var i = 0; i < row.length; i++){
	 	    	// if( row[i].payNumber == undefined || row[i].payNumber ==''){
	 	    		var txLogisticID = txLogisticID + row[i].txLogisticID +",";
		   		/* }else{
		   			$.messager.confirm('系统提示',"<font color=red>已匹配支付流水号不能再次进行匹配！");
		   			return false;
		   		}  */
				    
	       	}
	 	    
	 	    txLogisticID = txLogisticID.substring(0, txLogisticID.length-1); 
	 	   $.modal.confirm('确定要匹配这<font color=red>'+row.length+'条订单吗?',function(){
	 		  layerMsg("匹配中...");
	 		   $.post('<%=basePath%>orderMail/getPayNumber.do',{
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

//推送换单
function deliverchange(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请选择一行数据!");
   	    	return false;  
   		}
   		for(var j=0;j<row.length;j++){
   			if(row[j].isPrint == "1"){
   				$.modal.alertWarning("请选择未推送过得数据");
   	   			return false;
   	   		}
   		}
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
			if(row[i].mailNo == ''){
				$.modal.alertWarning("运单号为空，请获取运单号后再推送到换单！");
	   			return false;
	   		}
	   		if(row[i].txLogisticID == ''){
	   			$.modal.alertWarning("订单号为空，请获取订单号后再推送到换单！");
	   			return false;
	   		} 
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
       		layer.open({
       	  	  title: '推送换单',
       	  	  type: 2,
       	  	  closeBtn: 2,
       	  	  content: '<%=path%>/orderMail/deliveChangeUI.do?ids='+ids,
       	  	  btn: ['推送', '取消'],
       	  	  isOutAnim : true,
       	  	  maxmin  : true,
       	  	  anim : 2 ,
       	  	  area : ['60%','45%'],
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
       	}); 
	}

//推送回执状态
function showPushCzStatus(){
	layer.open({
  	  title: '推送回执状态',
  	  type: 2,
  	  closeBtn: 2,
  	  content: '<%=path%>/orderMail/showPushCz.do',
  	  btn: ['推送', '取消'],
  	  isOutAnim : true,
  	  maxmin  : true,
  	  anim : 2 ,
  	  area : ['60%','45%'],
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

// 推送场站
function showPushCz(){
	layer.open({
  	  title: '推送场站',
  	  type: 2,
  	  closeBtn: 2,
  	  content: '<%=path%>/orderMail/pushCZUI.do',
  	  btn: ['推送', '取消'],
  	  isOutAnim : true,
  	  maxmin  : true,
  	  anim : 2 ,
  	  area : ['60%','45%'],
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


// 推送3单
function push3(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		}
          /* 	if(row[i].isCustoms == 1){
	   			$.messager.confirm('系统提示',"已推送的运单不要重复报关，请重新选择！");
	   			return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
       		layerMsg("推送中...");	
       		$.post('<%=basePath%>orderMail/push3.do',{
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

// 运单申报
function customs(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
          	if(row[i].auditstatus != 2){
          		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送，请重新选择！");
	   			return false;
	   		}
          /* 	if(row[i].isCustoms == 1){
	   			$.messager.confirm('系统提示',"已推送的运单不要重复报关，请重新选择！");
	   			return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
	 		layerMsg("推送中...");	
	 		$.post('<%=basePath%>orderMail/customs.do',{
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

// 清单申报
function pushQd(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一行数据!");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
		 	var ids = ids + row[i].id +",";
            if(row[i].auditstatus != 2){
            	$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送清单，请重新选择！");
	   			return false;
	   		}    
          	/* if(row[i].isPushQd == 1){
	   			$.messager.confirm('系统提示',"已推送的订单不要重复推送，请重新选择！");
	   			return false;
	   		} */
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
       		layerMsg("推送中...");	
       		$.post('<%=basePath%>orderMail/pushQd.do',{
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
	                    } */else { 
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
	
// 订单申报
function pushDd(){  
   		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
   		if(row.length==0){  
   			$.modal.alertWarning("请至少选择一条记录");
   	    	return false;  
   		}
   		
 	    var ids = ""; 
 	    for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
           	if(row[i].auditstatus != 2){
           		$.modal.alertWarning("审核状态为已审核已匹配的订单才可推送订单，请重新选择！");
	   			return false;
	   		} 
          	/* if(row[i].isPushDd == 1){
	   			$.messager.confirm('系统提示',"已推送的订单不要重复推送，请重新选择！");
	   			return false;
	   		}  */
       	}
       	ids = ids.substring(0, ids.length-1); 
       	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
       		layerMsg("推送中...");	
       		$.post('<%=basePath%>orderMail/pushDd.do',{
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

// 推送网趣平台
function pushOrder(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
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
      		$.modal.alertWarning("已推送网趣的订单请不要重复推送，请重新选择！");
   			return false;
   		}
   	}
   	ids = ids.substring(0, ids.length-1); 
   	$.modal.confirm('确定要推送这<font color=red>'+row.length+'条订单吗?',function(){
   			layerMsg("推送中...");
       		$.post('<%=basePath%>orderMail/pushOrder.do',{"ids":ids},
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

	<!-- 入口方法 -->
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/orderMail/findAll.do',
				//排序字段名
				sortName : "createDateTime",
				//查询条件
				queryParams : queryParams,
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
				       	{field :'receiveMan', title :'收件人',align :'left'},
	                  	{field :'receiveManPhone', title :'电话', align :'left'},
	                  /*  	{field :'receiveManAddress', title :'地址', align :'left'},  */
	                 /*  	{field :'receiveProvince', title :'省',  align :'left'}, */
	                  	{field :'receiveCity', title :'收件城市',  align :'left'},
	                  	{field :'receiveCounty', title :'收件区县',  align :'left'},
	                  	{field :'txLogisticID', title :'订单号',  align :'left'},
	                  /* 	{field :'itemName', title :'主要商品',  align :'left'}, */
	                  	{field :'itemWeight', title :'重量',  align :'left'},
	                  	{field :'itemCount', title :'总数量',  align :'left'},
	                  	{field :'carrier', title :'快递', align :'left'},
	                  	{field :'mailNo', title :'运单号',align :'left'},
	                  	{field :'worth', title :'总价值', align :'left'},
	                  	{field :'tradeCountry', title :'启运国（地区）', align :'left'},
	                  	{field :'buyerIdNumber', title :'订购人证件号码', align :'left'},
	                  	{field :'buyerName', title :'订购人姓名', align :'left'},
	                  	{field :'payNumber', title :'支付流水号',  align :'left'},
		                {field :'totalMailNo', title :'提运单号', align :'left'},
		                {field :'fightNumber', title :'航班号', align :'left'},
		                {field :'destinationPort', title :'指运港',  align :'left'},
	                  	/* {field :'pc', title :'备注', width :40, align :'center'}, */
					{field :'isPrint', title :'推送订单', align :'center',formatter:function(value){ 
							if(value == 0){
	                	   		return '未推送';
		                   	}else if(value == 1){
		                		return '已推送';
		                   	}
		                } 
		            },
					{field :'isPushQd', title :'推送清单', align :'center',formatter:function(value){ 
							if(value == 0){
	                	   		return '未推送';
		                   	}else if(value == 1){
		                		return '已推送';
		                   	}
		                } 
		            },
					{field :'isCustoms', title :'推送运单',align :'center',formatter:function(value){ 
						if(value == 0){
                	   		return '未推送';
	                   	}else if(value == 1){
	                		return '已推送';
	                   	}
		                } 
		            },
                  	{field :'ispost', title :'推送网趣', align :'center',formatter:function(value){ 
							if(value == 0){
	                	   		return '未推送';
		                   	}else if(value == 1){
		                		return '已推送';
		                   	}
		                } 
		            },
                  	{field :'isPushCz', title :'推送场站',  align :'center',formatter:function(value){ 
							if(value == 0){
	                	   		return '未推送';
		                   	}else if(value == 1){
		                		return '已推送';
		                   	}
		                } 
		            },
                  	{field :'auditstatus', title :'审核状态',  align :'center',formatter:function(value){ 
	                	   	if(value == 0){
	                	   		return '未审核';
		                   	}else if(value == 1){
		                		return '已审核未匹配';
		                   	}else if(value == 2){
		                		return '已审核已匹配';
		                   	}else if(value == 3){
				                return '未匹配到商品';
		                   	}else if(value == 4){
			                	return '待支付';
		                   	}else if(value == 5){
			                	return '已支付';
		                   	}else if(value == 9){
		                		return '已完结';
		                   	}
	                 	} 
                  	},
		            {field :'isPrint', title :'推送换单', align :'center',formatter:function(value){ 
							if(value == 0){
	                	   		return '未推送';
		                   	}else if(value == 1){
		                		return '已推送';
		                   	}
		                } 
		            },
		            {field :'returncode', title :'回执状态', align :'center',formatter:function(value,row,index){ 
                	   	if(value == 0){
                	   		return '待处理';
	                   	}else if(value == 1){
	                		return "<a href='javascript:detailMail("+row.id+")'>回执状态</a>";
	                   	}else if(value == 2){
	                		return '待抵运';
	                   	}else if(value == 3){
	                		return '查验';
	                   	}else if(value == 9){
	                		return '放行';
	                   	}
                 	} 
               }],
               onDblClickRow: function (row) {
            	   layer.open({
           	    	  title: row.txLogisticID+'的订单详情',
           	    	  type: 2,
           	    	  closeBtn: 2,
           	    	  content: '<%=basePath%>orderMail/editMatterUI.do?id='+row.id,
           	    	  isOutAnim : true,
           	    	  maxmin  : true,
           	    	  anim : 2 ,
           	    	  area : ['95%','95%']
           	    	}); 
	            },
			}
		//进入初始化表格方法
			//加载时间控件
// 			$.operate.time("time3");
// 			$.operate.time("time4");
			$.table.init(options);
	});
	
	$('#search').click(function(){
		 var ps = $("#bootstrap-table").bootstrapTable("getOptions").pageSize;//获取分页的pagesize
		$('#bootstrap-table').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:ps});//刷新表格  还原到第一页
	})
	
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
            temp["auditstatus"] = "9";
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