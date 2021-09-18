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
<title>自动审单</title>
<%@ include file="/static/common/head.txt"%>
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
										<option value="autoCheckList.companybm">公司编码</option>
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
				url : '<%=path%>/autochecklist/findAll.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/autochecklist/editUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/autochecklist/editUi.do',
				//删除地址     
				removeUrl : '<%=path%>/autochecklist/remove.do',
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
						field : 'companyName',
						title : '公司名称',
						align : 'center'
					}, {
						field : 'autoGainOrder',
						title : '自动捞单',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'orderPreview',
						title : '订单预审',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'downPlatform',
						title : '下发平台',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'getStream',
						title : '获取流水',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'grabNumbers',
						title : '抓取单号',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'orderSubmitted',
						title : '订单报送',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'logisticsSubmitted',
						title : '物流报送',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'listSubmitted',
						title : '清单报送',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					}, {
						field : 'downWarehouse',
						title : '下发仓库',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
						}
					},  {
						field : 'automaticCompletion',
						title : '自动完结',
						align : 'center',
						formatter :function (value, row, index) {
							if(value==0){
								return "未开启";
							}else if(value==1){
								return "已开启";
							}
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