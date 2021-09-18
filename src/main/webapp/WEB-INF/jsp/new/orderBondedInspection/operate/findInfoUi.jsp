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
<title>详情显示</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
	<form id="editform">
		<div class="row">
			<div class="col-xs-6">
				订单号:
				<input class="form-control bg-warning" type="text" readonly="readonly" id="txLogisticID" name="txLogisticID" value="${order.txLogisticID}"/>
			</div>
			<div class="col-xs-6">
				收件人姓名:
				<input class="form-control bg-warning" type="text" id="receiveMan" name="receiveMan" value="${order.receiveMan}"/>
			</div>
			<div class="col-xs-6">
				收件人省：
				<input class="form-control bg-warning" type="text" id="receiveProvince" name="receiveProvince" value="${order.receiveProvince}"/>
			</div>
			<div class="col-xs-6">
				收件人市：
				<input class="form-control bg-warning" type="text" id="receiveCity" name="receiveCity" value="${order.receiveCity}"/>
			</div>
			<div class="col-xs-6">
				收件人区县：
				<input class="form-control bg-warning" type="text" id="receiveCounty" name="receiveCounty" value="${order.receiveCounty}"/>
			</div>
			<div class="col-xs-6">
			          收件人地址：
				<input class="form-control bg-warning" type="text" id="receiveManAddress" name="receiveManAddress" value="${order.receiveManAddress}"/>
			</div>
			<div class="col-xs-6">
				收件人电话：
				<input class="form-control bg-warning" type="text" id="receiveManPhone" name="receiveManPhone" value="${order.receiveManPhone}"/>
			</div>
			<div class="col-xs-6">
				商品名称：
				<input class="form-control bg-warning" type="text" id="itemName" name="itemName" value="${order.itemName}"/>
			</div>
			<div class="col-xs-6">
				商品总数量：
				<input class="form-control bg-warning" type="text" id="itemCount" name="itemCount" value="${order.itemCount}"/>
			</div>
			<div class="col-xs-6">
				商品重量：
				<input class="form-control bg-warning" type="text" id="itemWeight" name="itemWeight" value="${order.itemWeight}"/>
			</div>
			<div class="col-xs-6">
				运费：
				<input class="form-control bg-warning" type="text" id="feeAmount" name="feeAmount" value="${order.feeAmount}"/>
			</div>
			<div class="col-xs-6">
				保费：
				<input class="form-control bg-warning" type="text" id="insureAmount" name="insureAmount" value="${order.insureAmount}"/>
			</div>
			<div class="col-xs-6">
				购买人身份证：
				<input class="form-control bg-warning" type="text" id="buyerIdNumber" name="buyerIdNumber" value="${order.buyerIdNumber}"/>
			</div>
				<div class="col-xs-6">
				购买人姓名：
				<input class="form-control bg-warning" type="text" id="buyerName" name="buyerName" value="${order.buyerName}"/>
			</div>
				<div class="col-xs-6">
				快递公司：
				<input class="form-control bg-warning" type="text" id="carrier" name="carrier" value="${order.carrier}"/>
			</div>
				<div class="col-xs-6">
				发件仓库：
				<input class="form-control bg-warning" type="text" id="sendWarehouse" name="sendWarehouse" value="${order.sendWarehouse}"/>
			</div>
				<div class="col-xs-6">
				商家编码：
				<input class="form-control bg-warning" type="text" id="merchantNum" name="merchantNum" value="${order.merchantNum}"/>
			</div>
			<div class="col-xs-6">
				快递单号：
				<input class="form-control bg-warning" type="text" id="mailNo" name="mailNo" value="${order.mailNo}"/>
			</div>
			<div class="col-xs-6">
				备注：
				<input class="form-control bg-warning" type="text" id="pc" name="pc" value="${order.pc}"/>
			</div>
			<div class="col-xs-6">
				支付流水号：
				<input class="form-control bg-warning" type="text" id="payNumber" name="payNumber" value="${order.payNumber}"/>
			</div>
		</form>
			<!-- 主表详情 -->
			<div  class="col-xs-12" align="right">
				<button type="button" class="btn btn-success" style="margin-top: 10px;" id="add" onclick="submitHandler()"> <i class="fa fa-plus-square-o"></i> 
					保存
				</button>
			</div>
			<div  class="col-xs-12" align="right">
			</div>
			<table id="bootstrap-table_2"></table>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
$(function(){
			$('#bootstrap-table_2').bootstrapTable({
				url: '<%=path%>/orderBonded/Detailed.do',
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
				queryParams :queryParams2,						 //查询条件
				columns : [					
					{
						field : 'itemsku',
						title : '商品SKU',
						align : 'center'
					},{
						field : 'itemName',
						title : '商品名称',
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
			function queryParams2(params){
				var value = $("#getValue").val();
				var name = $('#serachname option:selected') .val();
				var temp = $("#data-form").serializeJsonObject();
	            temp["rows"] = params.limit;                        //页面大小
	            temp["page"] = (params.offset / params.limit) + 1;  //页码
	            temp["sort"] = params.sort;                         //排序列名
	            temp["sortOrder"] = params.order;                   //排位命令（desc，asc） 
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
	 
    function submitHandler() {
		var editurl ='<%=basePath%>orderBonded/updateAll.do';
			/* var id=$("#id").val();
			console.log(id == "");
			if(id == ""){
				$.operate.save(addurl, $('#editform').serialize());
			}else{ */
				$.operate.save_OrderBonded(editurl, $('#editform').serialize());
			/* } */
}
	</script>

</body>
</html>