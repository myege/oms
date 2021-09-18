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
<title>商家管理</title>
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
										<option value="company.companybm">公司编码</option>
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
				<a class="btn btn-success" id="add" onclick="$.operate.add('50%','80%')"> <i class="fa fa-plus"></i> 新增
				</a> <a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.edit('50%','80%')"> <i class="fa fa-edit"></i>
					修改
				</a>
				 <a class="btn btn-danger btn-del" onclick="$.operate.removeAll()"> <i class="fa fa-remove"></i>删除 </a> 
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
				url : '<%=path%>/company/findAll.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/company/editUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/company/editUi.do',
				//删除地址     
				removeUrl : '<%=path%>/company/remove.do',
				//排序字段名
				sortName : "id",
				//查询条件
				queryParams : queryParams,
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'companybm',
						title : '公司编码',
						align : 'center'
					}, {
						field : 'companytszt',
						title : '推送主体编码',
						align : 'center'
					}, {
						field : 'companyName',
						title : '电商平台名称',
						align : 'center'
					}, {
						field : 'companyCode',
						title : '电商平台代码',
						align : 'center'
					}, {
						field : 'payCompanyCode',
						title : '支付公司编码',
						align : 'center'
					}, {
						field : 'eCommerceName',
						title : '电商企业名称',
						align : 'center'
					}, {
						field : 'eCommerceCode',
						title : '电商企业编码',
						align : 'center'
					}, {
						field : 'logisCompanyName',
						title : '物流企业名称',
						align : 'center'
					}, {
						field : 'logisCompanyCode',
						title : '物流企业编号',
						align : 'center'
					}, {
						field : 'declareCompanyName',
						title : '申报企业名称',
						align : 'center'
					}, {
						field : 'declareCompanyCode',
						title : '申报企业代码',
						align : 'center'
					}, {
						field : 'applicationFormNo',
						title : '申请单编号',
						align : 'center'
					}, {
						field : 'internalAreaCompanyNo',
						title : '区内企业代码',
						align : 'center'
					}, {
						field : 'internalAreaCompanyName',
						title : '区内企业名称',
						align : 'center'
					},   {
						field : 'assureCode',
						title : '担保企业编号',
						align : 'center'
					}, {
						field : 'customsField',
						title : '监管场所代码',
						align : 'center'
					}, {
						field : 'declPort',
						title : '申报地海关代码',
						align : 'center'
					}, {
						field : 'accountBookNo',
						title : '账册编号',
						align : 'center'
					}, {
						field : 'country',
						title : '起运国',
						align : 'center'
					}, {
						field : 'sendName',
						title : '发件人',
						align : 'center'
					}, {
						field : 'sendTel',
						title : '发件人电话',
						align : 'center'
					}, {
						field : 'sendAddress',
						title : '发件人地址',
						align : 'center'
					},  {
						field : 'isauto',
						title : '是否开启自动审单',
						align : 'center',
						formatter :function (value, row, index) {
			        		return statusauto(row);
					}
					}, ]
				}
			//进入初始化表格方法
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
	/**是否开始自动审单状态显示*/
		function statusauto(row) {
		    if (row.isauto == 0) {
    			return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="enable(\'' + row.id + '\')"></i> ';
    		} else {
    			return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="disable(\'' + row.id + '\')"></i> ';
    		}
		}
	//停用自动审单
	function disable(id) {
		$.modal.confirm("确认要停用自动审单吗？", function() {
			$.operate.post("<%=path%>/company/changeAuto.do", { "id": id, "isauto": 0 });
	    })
	}	
	/**启用自动审单*/
	function enable(id) {
		$.modal.confirm("确认要启用自动审单吗？", function() {
			$.operate.post("<%=path%>/company/changeAuto.do", { "id": id, "isauto": 1 });
	    })
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