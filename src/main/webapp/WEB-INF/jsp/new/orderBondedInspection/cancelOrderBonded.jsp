<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
										<option value="merchantNum">商家编码</option>
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
<!-- 							<li><a class="btn btn-primary btn-rounded" -->
<!-- 								id="search"><i class="fa fa-search"></i>&nbsp;搜索</a></li> -->

						</ul>
					</div>
				</form>
			</div>

			<div class="btn-group-sm" role="group">
				<button type="button" class="btn btn-success" style="margin-top: 10px;" id="add" onclick="exportOrder()"> <i class="fa fa-file-excel-o"></i> 
				导出
				</button>
				<button type="button" class="btn btn-success" style="margin-top: 10px;" id="add" onclick="exportOrder2()"> <i class="fa fa-file-excel-o"></i> 
				导出2
				</button>
				<button type="button" id="order_bonded_btn_2" style="margin-top: 10px;" class="btn btn-success" onclick="updateAuditstatus0()">  <i class="fa fa-plus-square-o"></i> 
				转为正常单
				</button>
				<button type="button" id="order_bonded_btn_2" style="margin-top: 10px;" class="btn btn-success" onclick="deleteByIds()"><i class="fa fa-plus-square-o"></i>
				删除订单
				</button>
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
	
	<form id="formid" action="<%=path%>/orderBonded/exportOrder.do" method="post">
			    <input type="hidden" name="orderBonded" id="orderBonded" value="">
			    <input type="hidden" name="value" id="value" value="">
			    <input type="hidden" name="name" id="name" value="">
			    <input type="hidden" name="auditstatus" id="auditstatus" value="">
    </form>
    <form id="formid2" action="<%=path%>/orderBonded/exportOrder2.do" method="post">
			    <input type="hidden" name="orderBonded2" id="orderBonded2" value="">
			    <input type="hidden" name="value" id="value2" value="">
			    <input type="hidden" name="name" id="name2" value="">
			     <input type="hidden" name="auditstatus" id="auditstatus2" value="">
    </form>
	
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
//删除订单
function deleteByIds(){
		var rows = $("#bootstrap-table").bootstrapTable('getSelections');
		var length =rows.length;
		if(length==0){
			$.modal.alertWarning("请至少选择一条记录");
			return false;
		}else{
			$.modal.confirm('确定要审核这<font color=red>'+rows.length+'条数据吗?',function(){
					layerMsg("审核中...");
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
//转为正常订单
function updateAuditstatus0(){
		var row = $("#bootstrap-table").bootstrapTable('getSelections');
		var length =row.length;
		if(length==0){
			$.modal.alertWarning("请至少选择一条记录");
			return false;
		}else{
			$.modal.confirm('确定要修改这<font color=red>'+row.length+'条数据吗?',function(){
				layerMsg("修改中...");
						var ids="";
						for(var i=0;i<length;i++){
							if(i==0){
								ids += row[i].id;
							}else{
								ids += ","+ row[i].id;
							}
						}
						$.post('<%=path%>/orderBonded/updateAuditstatus0.do',{ids:ids},
								function(ret){
								layer.close(layer.index);
									if(ret==1){
										layer.msg("修改成功", {
					              			  icon: 1,
					              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
					              			}, function(index){
					              				layer.close(index);
					              			  //do something
					              				$("#bootstrap-table").bootstrapTable("refresh", {
										   	 silent: true //静态刷新
											}); 
					              			})
									}else{
										layer.msg("修改失败", {
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
						)
				}		
			)	
		}
	}
//导出2
function exportOrder2() {
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
	 var ids = "";
	if (row.length == 0) {
		$.modal.confirm('确定要导出全部查询数据吗?',function(){
        	//查询全部标识符
        ids = "cxqbdc";		
		var v = document.getElementById("orderBonded2");
		v.value = ids;
		//搜索值	
		var v1 = document.getElementById("value2");
		v1.value = $('#getValue').val(); 
	    //搜索类型
		var v2 = document.getElementById("name2");
		v2.value = $('#serachname').val();
		//
		var v3 = document.getElementById("auditstatus2");
		v3.value = '8';
		//提交表单可以这样  
		var f = document.getElementById("formid2");
		f.submit();
     });
	  }else{ 
		  
		  for (var i = 0; i < row.length; i++){
			var ids = ids + row[i].id +",";
     }
		  $.modal.confirm('确定要导出吗?',function(){
				var v = document.getElementById("orderBonded");
				v.value = ids;
				var v3 = document.getElementById("auditstatus");
				v3.value = '8';
				//提交表单可以这样  
				var f = document.getElementById("formid2");
				f.submit();
	      }); 
  }  
}

//导出1
function exportOrder() {
	var row = $("#bootstrap-table").bootstrapTable('getSelections');
	 var ids = "";
	if (row.length == 0) {
		$.modal.confirm('确定要导出全部查询数据吗?',function(){
	        	//查询全部标识符
	        ids = "cxqbdc";		
			var v = document.getElementById("orderBonded");
			v.value = ids;
			//搜索值	
			var v1 = document.getElementById("value");
			v1.value = $('#getValue').val(); 
		    //搜索类型
			var v2 = document.getElementById("name");
			v2.value = $('#serachname').val();
			//
			var v3 = document.getElementById("auditstatus");
			v3.value = '8';
			//提交表单可以这样  
			var f = document.getElementById("formid");
			f.submit();
	     });
	  }else{ 
		  
		  var ids;
		  for (var i = 0; i < row.length; i++){
			  if(i==0){
				  ids =  row[i].id;
			  }
 			ids = ids + ","+row[i].id ;
      }
		  $.modal.confirm('确定要导出吗?',function(){
				var v = document.getElementById("orderBonded");
				v.value = ids;
				var v3 = document.getElementById("auditstatus");
				v3.value = '8';
				//提交表单可以这样  
				var f = document.getElementById("formid");
				f.submit();
	      }); 
   }  
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
						field : 'receiveManAddress',
						title : '地址',
						align : 'center'
					}, {
						field : 'receiveProvince',
						title : '省',
						align : 'center'
					}, {
						field : 'receiveCity',
						title : '市',
						align : 'center'
					}, {
						field : 'receiveCounty',
						title : '区县',
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
						field : 'markDestination',
						title : '格口',
						align : 'center'
					},   {
						field : 'billProvideSiteName',
						title : '始发中心',
						align : 'center'
					}, {
						field : 'billProvideSiteCode',
						title : '始发编码',
						align : 'center'
					},  {
						field : 'worth',
						title : '总价值',
						align : 'center'
					}, {
						field : 'preEntryNumber',
						title : '预录入编号',
						align : 'center'
					}, {
						field : 'tradeCountry',
						title : '启运国（地区）',
						align : 'center'
					}, {
						field : 'buyerIdNumber',
						title : '订购人证件号码',
						align : 'center'
					}, {
						field : 'buyerName',
						title : '订购人姓名',
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
					}, {
						field : 'pc',
						title : '备注',
						align : 'center'
					},{
						field : 'isPushToWms',
						title : '推送WMS',
						align : 'center',
						formatter : function(value) {
							if(value == 0){
	                	   		return '未推送';
		                   	}else if(value == 1){
		                		return '已推送';
		                   	}
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
           	    	  area : ['80%','90px'],
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
            temp["auditstatus"] = "8";
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