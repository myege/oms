<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>京东理货单</title>
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
										<option value="text5">商品编号</option>
										<option value="text4">货主编码</option>
										<option value="text3">京东采购单</option>
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
				<a class="btn btn-success" id="add" onclick="push()"> <i class="fa fa-plus"></i> 抓取理货单</a>
				
	
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
				url : '<%=path%>/jdwl/findAlllhd.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/jdwl/editUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/jdwl/addUi.do',
				//导入地址
				importUrl : '<%=path%>/jdwl/importItem.do',
				//排序字段名
				sortName : "createtime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
				    {
						field : 'seqNo',
						title : '防重码',
						align : 'center'
					}, {
						field : 'poNo',
						title : '京东采购单',
						align : 'center'
					}, {
						field : 'warehouseName',
						title : '库房名称',
						align : 'center'
					}, {
						field : 'ownerName',
						title : '货主名称',
						align : 'center'
					}, {
						field : 'goodsid',
						title : '商品编号',
						align : 'center'
					},  {
						field : 'goodsName',
						title : '商品名称',
						align : 'center'
					},  {
						field : 'sellerRecord',
						title : '货号',
						align : 'center'
					},  {
						field : 'expectedQty',
						title : '采购数量',
						align : 'center'
					},  {
						field : 'qty',
						title : '理货数量 ',
						align : 'center'
					}, {
						field : 'createtime',
						title : '时间',
						align : 'center',
						formatter:function(value){
                  			return formatDate(value);
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
	
	//推送
	function push(){  
		layer.open({
		  	  title: '获取理货单',
		  	  type: 2,
		  	  closeBtn: 2,
		  	  content: '<%=path%>/jdwl/getlhd.do',
		  	  btn: ['确认', '取消'],
		  	  isOutAnim : true,
		  	  maxmin  : true,
		  	  anim : 2 ,
		  	  area : ['50%','50%'],
		  	  yes: function(index, layero){
		  		  	//按钮【按钮一】的回调
		  		  var iframeWin = layero.find('iframe')[0];
			          iframeWin.contentWindow.submitHandler();
		  	  },
		  	  btn2: function(index, layero){
		  		  	//按钮【按钮二】的回调
		  		  layer.close(index);
		  		  //return false 开启该代码可禁止点击该按钮关闭
		  	  }
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
    
  //遮罩
	function layerMsg(text){
		layer.open({
	    	  type: 1,
	    	  closeBtn: 0,
	    	  content: text,
	    	  area : ['10%','10%'],
	    }); 
	}
	</script>

</body>
</html>