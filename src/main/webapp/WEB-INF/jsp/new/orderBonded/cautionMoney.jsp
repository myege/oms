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
<title>保证金</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				<a class="btn btn-success" id="add" onclick="$.operate.add('50%','50%')"> <i class="fa fa-plus"></i> 新增
				</a> 
				 <a class="btn btn-danger btn-del" onclick="$.operate.del()"> <i class="fa fa-remove"></i>
					删除
				</a>
				 <a class="btn btn-primary" onclick="$.operate.editForMoreUrl('40%','50%','<%=path %>/orderBond/recharge.do')"> <i class="fa fa-edit"></i> 充值
				</a>
				<a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.editForMoreUrl('40%','59%','<%=path %>/orderBond/freeze.do')"> <i class="fa fa-remove"></i>
					冻结
				</a>
				<a class="btn btn-primary btn-edit" id="edit" onclick="$.operate.editForMoreUrl('40%','59%','<%=path %>/orderBond/release.do')"> <i class="fa fa-remove"></i>
					释放
				</a>
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table"></table>
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

	
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/orderBond/findAll.do',
				//添加页面的地址
				addUrl : '<%=path%>/orderBond/addUi.do',
				//删除地址     
				deleteUrl : '<%=path%>/orderBond/deleteBond.do',
				//排序字段名
				sortName : "createTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'companybm',
						title : '公司编码',
						align : 'center'
					}, {
						field : 'bondManey',
						title : '总保证金',
						align : 'center'
					}, {
						field : 'frostMamey',
						title : '冻结金额',
						align : 'center'
					},{
						field : 'surplusMamey',
						title : '可用金额',
						align : 'center'
					},{
						field : 'useManey',
						title : '已用金额',
						align : 'center'
					},{
						title : '操作',
						align : 'center',
						formatter : function(value, row, index) {
							var url = '<%=path %>/orderBond/details.do?companybm='+row.companybm;
							var html ="<a class='btn btn-primary btn-edit' id='edit' onclick='$.operate.showTableById(1000,700,&quot;"+url+"&quot;)'><i></i>查看详情</a>";
							console.log(html);
							return html;
						},
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
            temp["auditstatus"] = "4";
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