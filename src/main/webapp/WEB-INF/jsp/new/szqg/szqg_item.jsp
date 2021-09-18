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
<title>商品备案</title>
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
										<option value="text5">SKU</option>
										<option value="text4">商家编码</option>
										<option value="text3">商品名称</option>
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
<!-- 							<li><a class="btn btn-primary btn-rounded btn-sm" -->
<!-- 								id="search"><i class="fa fa-search"></i>&nbsp;搜索</a></li> -->

						</ul>
					</div>
				</form>
			</div>

			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				<a class="btn btn-success" id="add" onclick="$.operate.add('50%','50%')"> <i class="fa fa-plus"></i> 新2442增
				</a> <a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.edit('50%','50%')"> <i class="fa fa-edit"></i>
					修改
				</a>
				  <a class="btn btn-danger btn-del" onclick="deleteByIds()"> <i class="fa fa-remove"></i>
			            删除 
				</a>  
				 <a class="btn btn-warning" onclick="importItem()"> <i class="fa fa-download"></i> 导入
				</a>
				<a style='color:blue' href='<%=path%>/xls/agentItem.xlsx'>下载商品备案模板</a>
				
				<a class="btn btn-success" onclick="pushewtp()"> <i class="fa fa-plus"></i>
			            EWTP报送
				</a> 
				
				<a class="btn btn-success" onclick="hctb()"> <i class="fa fa-plus"></i>
			                           海仓同步
				</a> 
			</div>
				<form id="editform" action="<%=path%>/item/exportba.do" method="post">
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

	function importItem() {
		var openUrl = "<%=path%>/import/importAgentItem.jsp";//弹出窗口的url
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
				url : '<%=path%>/szqgitem/findAll.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/item/editUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/item/addUi.do',
				//导入地址
				importUrl : '<%=path%>/item/importItem.do',
				//排序字段名
				sortName : "createDateTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
				           {
					field : 'id',
					title : 'ID',
					align : 'center'
				},
				           {
						field : 'guid',
						title : 'guid',
						align : 'center'
					}, {
						field : 'commitTime',
						title : '提交时间',
						align : 'center'
					}, {
						field : 'coCode',
						title : '商户备案编码',
						align : 'center'
					}, {
						field : 'skuCode',
						title : '申报商品 SKU',
						align : 'center'
					}, {
						field : 'codeTs',
						title : '商品归类码',
						align : 'center'
					}, {
						field : 'barCode',
						title : '商品条形码',
						align : 'center'
					}, {
						field : 'goodsName',
						title : '商品中文名',
						align : 'center'
					}, {
						field : 'goodsNameEn',
						title : '商品英文名',
						align : 'center'
					}, {
						field : 'goodsNameOther',
						title : '其它名',
						align : 'center'
					}, /* {
						field : 'goodsDesc',
						title : '商品描述',
						align : 'center'
					}, */ {
						field : 'goodsComposition',
						title : '商品成份',
						align : 'center'
					}, {
						field : 'goodsFrom',
						title : '商品产地',
						align : 'center'
					}, {
						field : 'goodsUnit',
						title : '商品单位',
						align : 'center'
					}, {
						field : 'goodsSpec',
						title : '商品规格',
						align : 'center',
					
					},   {
						field : 'goodsModel',
						title : '商品型号',
						align : 'center'
					}, {
						field : 'hsCode',
						title : 'hsCode',
						align : 'center'
					}, {
						field : 'firstUnit',
						title : '第一计量单位',
						align : 'center',
					}, {
						field : 'secondUnit',
						title : '第二计量单位',
						align : 'center',
					}, {
						field : 'highPrice',
						title : '商品最高价',
						align : 'center',
					}, {
						field : 'lowPrice',
						title : '商品最低价',
						align : 'center',
					}, {
						field : 'supplier',
						title : '供应商',
						align : 'center',
					}, {
						field : 'standardCountry',
						title : '适用标准国别',
						align : 'center',
					}, {
						field : 'brand',
						title : '品牌中文名称',
						align : 'center',
					}, {
						field : 'brandEn',
						title : '品牌英文名称',
						align : 'center',
					}, {
						field : 'busRegion',
						title : '首次进口区域',
						align : 'center',
					}, {
						field : 'message',
						title : '回执',
						align : 'center',
					}, {
						field : 'createDateTime',
						title : '创建时间',
						align : 'center',
					},]
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
				var f = document.getElementById("editform");
				//$.modal.confirm("是否导出勾选记录！");
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
			//	$.modal.confirm("是否到处全部数据!");
				f.submit(); 
			}
	}
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
	function pushewtp(){ 
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
						
						$.post('<%=path%>/szqgitem/toewtp.do',{ids:ids},
								function(ret){
						//	$.modal.alertWarning(decodeURIComponent(ret));
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
	
	function hctb(){ 
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
						
						$.post('<%=path%>/szqgitem/hctb.do',{ids:ids},
								function(ret){
						//	$.modal.alertWarning(decodeURIComponent(ret));
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
	
	//删除记录
    function deleteByIds(){
    	var rows = $("#bootstrap-table").bootstrapTable('getSelections');
    		var length =rows.length;
    	
    		if(length==0){
    			$.modal.alertWarning("请至少选择一条记录");
       	    	return false;  
    		}else{
    			$.modal.confirm('确定要删除这<font color=red>'+rows.length+'条记录吗?',function(){
    	       	//	layerMsg("删除中...");
    						var ids="";
    						
    						for(var i=0;i<length;i++){
    							if(i==0){
    								ids += rows[i].id;
    							}else{
    								ids += ","+ rows[i].id;
    							}
    						}
    						
    						$.post('<%=path%>/item/deleteItem1.do',{ids:ids},
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