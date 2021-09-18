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
<title>快递规则</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
<!-- 		<div class="row"> -->
<!-- 			<div class="col-sm-12 search-collapse"> -->
<!-- 				<form id="data-form"> -->
<!-- 					<div class="select-list"> -->
<!-- 						<ul> -->
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
<!-- 							<li> -->
<!-- 								 <div class="input-group">保持内联，消除边框，类似于form-inline -->
<!-- 		 						<div class="input-group-btn">不换行，与相邻元素内联，包含dropdown -->
<!-- 			  						<select id="serachname" name="serachname" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false"> -->
<!-- 										<option value="">--请选择查询条件--</option> -->
<!-- 										<option value="carrier">快递公司</option> -->
<!-- 										<option value="province">省份</option> -->
<!-- 									</select> -->
<!-- 		  							</div> -->
<!-- 									<input type="text" class="form-control" id="getValue" placeholder="请输入关键词" style="height: 34px"/> -->
<!-- 									<span class="input-group-btn">不换行，与相邻元素内联 -->
<!-- 		      						<a class="btn btn-primary" id="search"> -->
<!-- 		      							<i class="fa fa-search"></i> -->
<!-- 		      							&nbsp;搜索 -->
<!-- 		      						</a> -->
<!-- 									</span> -->
<!-- 								</div> -->
<!-- 							</li> -->
<!-- 							<li><a class="btn btn-primary btn-rounded btn-sm" -->
<!-- 								id="search"><i class="fa fa-search"></i>&nbsp;搜索</a></li> -->

<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 				</form> -->
<!-- 			</div> -->

			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				<a class="btn btn-success" id="add" onclick="$.operate.add('50%','50%')"> <i class="fa fa-plus"></i> 新增
				</a> <a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.edit('50%','50%')"> <i class="fa fa-edit"></i>
					修改
				</a>
				 <a class="btn btn-danger btn-del" onclick="deleteOrderBondedRuleByIds()"> <i class="fa fa-remove"></i>
					删除
				</a>
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
function deleteOrderBondedRuleByIds(){
	var rows = $("#bootstrap-table").bootstrapTable('getSelections');
	var length = rows.length;
	if(length == 0){
		$.modal.alertWarning("请至少选择一条记录");
	}else{
		$.modal.confirm('确定要删除这<font color=red>'+rows.length+'条数据吗?',function(){
    		//调用小窗口阻止操作
    		 layerMsg("删除中...");
					var ids = "";
					for(var i=0;i<length;i++){
						if(i==0){
							ids = ids+ rows[i].id;
						}else{
							ids = ids+','+ rows[i].id;
						}
					}
					$.post('<%=path%>/orderBondedRule/deleteByIds.do?',{ids:ids},
							function(result){
						layer.close(layer.index);
			               if(result.code == 1){
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
		}		
		)
		
	}
	
}
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
				url : '<%=basePath%>/orderBondedRule/findAll.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/orderBonded/ruleEditUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/orderBonded/ruleEditUi.do',
				//排序字段名
				sortName : "createDateTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [
					{field:'column',checkbox:true,align : 'center'},
					{field :'carrier', title :'快递公司', align :'center'},
					{field :'province', title :'省份', align :'center'},
					{field :'weight', title :'重量(克)(限制最高量)',align :'center'},
					{field :'createtime', title :'创建时间',  align :'center',
						formatter:function(value){
							return formatDate(value);
						}
					},	]
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