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
<title>修改内容</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform">
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">姓名：</label>
			<input class="form-control bg-warning" type="text" id="receiveMan" name="receiveMan" value="${orderMail.receiveMan }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">电话：</label>
			<input class="form-control bg-warning" type="text" id="receiveManPhone" name="receiveManPhone" value="${orderMail.receiveManPhone }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">省：</label>
			<input class="form-control bg-warning" type="text" id="receiveProvince" name="receiveProvince" value="${orderMail.receiveProvince }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">市：</label>	
			<input class="form-control bg-warning" type="text" id="receiveCity" name="receiveCity" value="${orderMail.receiveCity }"/>
		</div>
		<div class="col-xs-6">	
			<label class="col-sm-3 control-label ">区：</label>	
			<input class="form-control bg-warning" type="text" id="receiveCounty" name="receiveCounty" value="${orderMail.receiveCounty }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">地址：</label>
			<input class="form-control bg-warning" type="text" id="receiveManAddress" name="receiveManAddress" value="${orderMail.receiveManAddress }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">订单号：</label>
			<input class="form-control bg-warning" readonly="readonly" type="text" id="txLogisticID" name="txLogisticID" value="${orderMail.txLogisticID }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">运单号：</label>
			<input class="form-control bg-warning" type="text" id="mailNo" name="mailNo" value="${orderMail.mailNo }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">起运国：</label>
			<input class="form-control bg-warning" type="text" id="tradeCountry" name="tradeCountry" value="${orderMail.tradeCountry }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-5 control-label ">提运单号：</label>
			<input class="form-control bg-warning" type="text" id="totalMailNo" name="totalMailNo" value="${orderMail.totalMailNo }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">航班号：</label>
			<input class="form-control bg-warning" type="text" id="fightNumber" name="fightNumber" value="${orderMail.fightNumber }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">指运港：</label>
			<input class="form-control bg-warning" type="text" id="destinationPort" name="destinationPort" value="${orderMail.destinationPort }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-5 control-label ">订购人姓名：</label>
			<input class="form-control bg-warning" type="text" id="buyerName" name="buyerName" value="${orderMail.buyerName }"/>
		</div>
		<div class="col-xs-6">
			<label class="col-sm-6 control-label ">订购人身份证：</label>
			<input class="form-control bg-warning" type="text" id="buyerIdNumber" name="buyerIdNumber" value="${orderMail.buyerIdNumber }"/>
		</div>
		<!-- 主表详情 -->
			<div  class="col-xs-12" align="right">
				<button type="button" class="btn btn-success" style="margin-top: 10px;" id="add" onclick="submitHandler()"> <i class="fa fa-plus-square-o"></i> 
					保存
				</button>
			</div>
</form>
			<div  class="col-xs-12" align="right">
			</div>
			<div  class="col-xs-12">
				<table id="bootstrap-table_2"></table>
			</div>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
$(function(){
	$('#bootstrap-table_2').bootstrapTable({
		url: '<%=path%>/orderMail/Detailed.do',
        contentType: "application/x-www-form-urlencoded",   // 编码类型
        method: 'post',                                     // 请求方式（*）
        cache: false,                                       // 是否使用缓存
       // striped: _striped,                                  // 是否显示行间隔色
        sortable: true,                                     // 是否启用排序
        sortStable: true,                                   // 设置为 true 将获得稳定的排序
        pageNumber: 1,                                      // 初始化加载第一页，默认第一页
        pageSize: 3,                                       // 每页的记录行数（*） 
       // escape: _escape,                                    // 转义HTML字符串
        showFooter: false,                            // 是否显示表尾
        iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
         toolbar: '#toolbar',                                // 指定工作栏
        sidePagination: "server",                           // 启用服务端分页
        uniqueId: "id",									 //每一行的唯一标识，一般为主键列
        sortName: 'txLogisticID',						 // 排序名称
        sortOrder: "desc",                   				 // 排序方式
        pagination : true, 								 // 是否分页
		locale : 'zh-CN', 									 // 中文支持
		queryParams : queryParams,
		columns : [					
			{
				field : 'itemsku',
				title : '商品SKU',
				align : 'center'
			},{
				field : 'txLogisticID',
				title : '订单编号 ',
				align : 'center'
			},{
				field : 'itemName',
				title : '商品名称',
				align : 'center'
			},{
				field : 'itemWeight',
				title : '重量',
				align : 'center'
			},{
				field : 'itemCount',
				title : '数量',
				align : 'center'
			},{
				field : 'unitPrice',
				title : '单价',
				align : 'center'
			},{
				field : 'allPrice',
				title : '总价',
				align : 'center'
			},{
				field : 'hz',
				title : '货主',
				align : 'center'
			},
			
		]
	})
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
        return temp;
}
		function submitHandler() {
				var editurl ='<%=path%>/orderMail/editMatterM.do';
				if ($.validate.form()) {
						$.operate.save(editurl, $('#editform').serialize());
	        		
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