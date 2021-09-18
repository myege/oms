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
<title>订单管理待清关</title>
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
										<option value="receiveMan">收件人姓名</option>
										<option value="pc">备注</option>
										<option value="itemName">主要商品</option>
										<option value="mailNo">运单号</option>
										<option value="payNumber">支付流水号</option>
									</select>
		  							</div>
									<input type="text" class="form-control" id="getValue" placeholder="请输入关键词" style="height: 34px" onkeydown='if(event.keyCode==13){return false;}'/>
									<span class="input-group-btn"><!--不换行，与相邻元素内联-->
		      						<a class="btn btn-primary" id="search">
		      							<i class="fa fa-search"></i>
		      							&nbsp;搜索
		      						</a>
									</span>
								</div>
							</li>
<!-- 							<li><a class="btn btn-primary btn-rounded btn-sm" -->
<!-- 								id="search"><i class="fa fa-search"></i>&nbsp;搜索</a></li> -->

						</ul>
					</div>
				</form>
			</div>

					<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
<!-- 				<a class="btn btn-success" id="add" onclick="$.operate.add(600,600)"> <i class="fa fa-plus"></i> 新增 -->
<!-- 				</a> <a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.edit(600,600)"> <i class="fa fa-edit"></i> -->
<!-- 					修改 -->
<!-- 				</a> -->
				 <a class="btn btn-danger btn-del" onclick="$.operate.del()"> <i class="fa fa-remove"></i>
					删除
				</a>
				
				 <a class="btn btn-primary btn-edit" onclick="orderBonded();"> <i class="fa fa-download"></i>
					导出
				</a>
				 <a class="btn btn-primary btn-edit" onclick="wlpush();"> <i class="fa fa-download"></i>
					物流报送
				</a>
				 <a class="btn btn-primary btn-del" onclick="ddpush();"> <i class="fa fa-download"></i>
					订单报送
				</a>
				<a class="btn btn-primary btn-edit" onclick="qdpush();"> <i class="fa fa-download"></i>
					清单报送
				</a>
				<a class="btn btn-primary btn-edit" onclick="ptpush();"> <i class="fa fa-download"></i>
					平台报送
				</a>
				<a class="btn btn-primary btn-del" onclick="fydpush();"> <i class="fa fa-download"></i>
					分运单
				</a>
				<a class="btn btn-primary btn-del" onclick="zydpush();"> <i class="fa fa-download"></i>
					总运单
				</a>
				<a class="btn btn-primary btn-del" onclick="CCqdpush();"> <i class="fa fa-download"></i>
					报关单
				</a>
				<a class="btn btn-primary btn-edit" onclick="wlgjpush();"> <i class="fa fa-download"></i>
					物流轨迹
				</a>
				
				<a class="btn btn-primary btn-edit" onclick="hctbpush();"> <i class="fa fa-download"></i>
					海仓同步
				</a>
				
				 <a class="btn btn-primary btn-del" onclick="ctddpush();"> <i class="fa fa-download"></i>
					传统订单报送
				</a>
				<a class="btn btn-primary btn-edit" onclick="ctqdpush();"> <i class="fa fa-download"></i>
					传统清单报送
				</a>
			<!-- 	
				<button type="button" id="order_bonded_btn_10" style="margin-top: 10px;" class="btn btn-success" onclick="pushQd()"><i class="fa fa-credit-card"></i>
				物流报送
				</button> -->
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table" name="bt"></table>
			</div>
		</div>
	</div>
	<form id="formidOrderBonded" action="<%=path%>/orderBonded/exportOrder.do" method="post">
	    <input type="hidden" name="orderBonded" id="orderBonded" value="">
	    <input type="hidden" name="value" id="value" value="">
	    <input type="hidden" name="name" id="name" value="">
	    <input type="hidden" name="auditstatus" id="auditstatus" value="">
    </form>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">

	/**
	 进行查询 刷新表格 进入 initTable（） 然后根据条件查询  
	**/
	$('#search').click(function(){
		 var ps = $("#bootstrap-table").bootstrapTable("getOptions").pageSize;//获取分页的pagesize
		$('#bootstrap-table').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:ps});//刷新表格  还原到第一页
	})
	 function orderBonded() {
			
		 var row = $("#bootstrap-table").bootstrapTable('getSelections');
		 var ids = "";
		 var value = $("#getValue").val();
		 var name = $('#serachname option:selected') .val();	
		 if (row.length == 0) {
			 layer.open({
				  content: '确认导出?'
				  ,btn: ['确认', '取消']
				  ,yes: function(index, layero){
				    //按钮【按钮一】的回调
					  ids = "cxqbdc";	
		 				var v = document.getElementById("orderBonded");
		 				v.value = ids;
		 				//搜索值	
		 				var v1 = document.getElementById("value");
		 				v1.value = value; 
		 			    //搜索类型
		 			    var v2 = document.getElementById("name");
		 				v2.value = name;
		 				
		 				var v3 = document.getElementById("auditstatus");
		 				v3.value = 4;
		 				
		 				var f = document.getElementById("formidOrderBonded");
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
			  for (var i = 0; i < row.length; i++){
				  if(i==0){
					  ids =  row[i].id;
				  }
      			ids = ids + ","+row[i].id ;
           }
			  layer.open({
				  content: '确认导出?'
				  ,btn: ['确认', '取消']
				  ,yes: function(index, layero){
				    //按钮【按钮一】的回调
					  console.log("in 1.0");
		 				var v = document.getElementById("orderBonded");
		 				v.value = ids;
		 				
		 				var v3 = document.getElementById("auditstatus");
		 				v3.value = 4;
		 				
		 				var f = document.getElementById("formidOrderBonded");
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
	//推送运单
	function wlpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/wlpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	//平台推单
	function ptpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/ptpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		}
	//订单订单
	function ddpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/ddpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
										//	layer.msg(ret, {
				                			  icon: 1,
				                			  time: 51000 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	//传统订单订单
	function ctddpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/ctddpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
										//	layer.msg(ret, {
				                			  icon: 1,
				                			  time: 51000 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 	
	//海仓同步
	function hctbpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/hctbpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
										//	layer.msg(ret, {
				                			  icon: 1,
				                			  time: 51000 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	//推送CC分运单
	function fydpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/fydpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	//推送CC总运单
	function zydpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/zydpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	
	//推送CC清单
	function CCqdpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/CCqdpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	
	//推送物流轨迹
	function wlgjpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/wlgjpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	//推送清单
	function qdpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/qdpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	//传统推送清单
	function ctqdpush(){
			var rows = $("#bootstrap-table").bootstrapTable('getSelections');
			var length =rows.length;
		
			if(length==0){
				$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
			}else{
				$.modal.confirm('确定要推送这<font color=red>'+rows.length+'条记录吗?',function(){
		       	//	layerMsg("删除中...");
							var ids="";
							
							for(var i=0;i<length;i++){
								if(i==0){
									ids += rows[i].id;
								}else{
									ids += ","+ rows[i].id;
								}
							}
							
							$.post('<%=path%>/szqgorder/ctqdpush.do',{ids:ids},
									function(ret){
										layer.msg(decodeURIComponent(ret), {
				                			  icon: 1,
				                			  time: 2100 //2秒关闭（如果不配置，默认是3秒）
				                			}, function(index){
				                			  //do something
				                				$("#bootstrap-table").bootstrapTable("refresh", {
				          					    silent: true //静态刷新
				          					});
				                		})
						
									}
							)
					}		
				)	
			}
		} 
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/szqgorder/findAll.do',
				//删除地址     
				deleteUrl : '<%=path%>/orderBonded/deleteOrderBonded.do',
				//排序字段名
				sortName : "createTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
				      {
					field : 'ddguid',
					title : '订单申报唯一码',
					align : 'center'
					
					},{
					field : 'qdguid',
					title : '清单申报唯一码',
					align : 'center'
					
						},{
					field : 'ydzt',
					title : '运单状态',
					align : 'center'
					
						}, {
						field : 'orderNo',
						title : '订单号',
						align : 'center'
						
					}, {
						field : 'consignee',
						title : '收货人姓名',
						align : 'center'
					}, {
						field : 'consigneeTelephone',
						title : '商品中文名',
						align : 'center'
					}, {
						field : 'consigneePrvince',
						title : '收件人省',
						align : 'center'
					}, {
						field : 'consigneeCity',
						title : '收件人市',
						align : 'center'
					}, {
						field : 'consigneeCounty',
						title : '收件人区县',
						align : 'center'
					}, {
						field : 'consigneeAddress',
						title : '收件地址',
						align : 'center'
					}, {
						field : 'goodsValue',
						title : '商品价格',
						align : 'center'
					}, {
						field : 'buyerName',
						title : '订购人姓名',
						align : 'center'
					}, {
						field : 'buyerIdNumber',
						title : '订购人证件号码',
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
					},
					{
						field : 'buyerIdNumber',
						title : '订购人证件号码',
						align : 'center'
					},
					{
						field : 'buyerName',
						title : '订购人姓名',
						align : 'center'
					},{
						field : 'auditstatus',
						title : '订单状态',
						align : 'center',
						formatter : function(value) {
							if(value == 0){
	                		  	return '待审核';
	                	   	}else if(value == 1){
		                		return '已审核未匹配';
		                   	}else if(value == 2){
		                		return '已审核已匹配';
		                   	}else if(value == 3){
		                		return '未匹配到商品';
		                   	}else if(value == 4){
		                		return '库存不足';
		                   	}else if(value == 8){
		                		return '取消订单';
		                   	}else if(value == 9){
		                		return '已完结';
		                   	}
		               	}
					}]
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
            temp["status"] = "4";
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