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
<title>商品管理</title>
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
									<input type="text" class="form-control" id="getValue" placeholder="请输入关键词" style="height: 34px"/>
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
				<a class="btn btn-success" id="add" onclick="$.operate.add(600,600)"> <i class="fa fa-plus"></i> 新增
				</a> <a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.edit(600,600)"> <i class="fa fa-edit"></i>
					修改
				</a>
<!-- 				 <a class="btn btn-danger btn-del"> <i class="fa fa-remove"></i> -->
<!-- 					删除 -->
<!-- 				</a> -->
				 <a class="btn btn-warning" onclick="importItem()"> <i class="fa fa-download"></i> 导入
				</a>
				<a style='color:blue' href='<%=path%>/xls/itemNew.xlsx'>下载商品模板</a>
<!-- 				 <a class="btn btn-primary btn-edit"> <i class="fa fa-download"></i> -->
<!-- 					导出 -->
<!-- 				</a> -->
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
				url : '<%=path%>/item/findAll.do',
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
						field : 'itemSKU',
						title : 'SKU',
						align : 'center'
					}, {
						field : 'itemName',
						title : '商品名称',
						align : 'center'
					}, {
						field : 'itemSpec',
						title : '规格',
						align : 'center'
					}, {
						field : 'itemcode',
						title : '商品条码',
						align : 'center'
					}, {
						field : 'unitWeight',
						title : '重量',
						align : 'center'
					}, {
						field : 'unitDesc',
						title : '单位',
						align : 'center'
					}, {
						field : 'companyCode',
						title : '商家编码',
						align : 'center'
					}, {
						field : 'hscode',
						title : '海关备案编码',
						align : 'center'
					}, {
						field : 'oneUnitDesc',
						title : '第一计量单位',
						align : 'center'
					}, {
						field : 'twoUnitDesc',
						title : '第二计量单位',
						align : 'center'
					}, {
						field : 'country',
						title : '原产国',
						align : 'center'
					}, {
						field : 'listPrice',
						title : '标价/售价',
						align : 'center'
					}, {
						field : 'costPrice',
						title : '成本价',
						align : 'center'
					}, {
						field : 'createDateTime',
						title : '时间',
						align : 'center'
					},   {
						field : 'internalNumber',
						title : '内部编码',
						align : 'center'
					}, {
						field : 'taxRate',
						title : '税率',
						align : 'center'
					}, {
						field : 'wmstype',
						title : 'wms是否记录',
						align : 'center',
						formatter : function(value) {
							if (value == 0) {
								return "未记录"
							} else if (value == 1) {
								return "已记录"
							}
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