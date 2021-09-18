<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增/修改</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">

<div class="container-div">
		<div class="row">
			<div class="col-sm-12 search-collapse">
				<!-- tab选项卡 -->
				<section class="content container-fluid" id="">
				<div class="row">
					<div class="col-md-12">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tow" data-toggle="tab">业务</a></li>
								<li class=""><a href="#five" data-toggle="tab">品质</a></li>
								<li class=""><a href="#six" data-toggle="tab">增值</a></li>
								<li class=""><a href="#Seven" data-toggle="tab">关于</a></li>
								<li class=""><a href="#Eight" data-toggle="tab">联系</a></li>
								<li class=""><a href="#totalEdit" data-toggle="tab">公司</a></li>
							</ul>
							<div class="tab-content">
							
							
								<div class="tab-pane active" id="tow">
									 <form method="post" class="form-horizontal" id="towOptions">
									 <input name= "begCode" value="2" hidden="">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第一个标题名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="pcText1"
														name="pcText1" value="${pcText1}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第一个标题内容：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="pcText2"
														name="pcText2" value="${pcText2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第二个标题名称：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="mobiText1"
														name="mobiText1" value="${mobiText1}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第二个标题内容：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="mobiText2"
														name="mobiText2" value="${mobiText2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第三个标题名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="sysText1"
														name="sysText1" value="${sysText1}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第三个标题内容：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="sysText2"
														name="sysText2" value="${sysText2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第四个标题名称：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="appText1"
														name="appText1" value="${appText1}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第四个标题内容：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="appText2"
														name="appText2" value="${appText2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第五个标题标题：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hostText1"
														name="hostText1" value="${hostText1}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第五个标题内容：</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hostText2"
														name="hostText2" value="${hostText2}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="editImgText('towOptions')">保存设置</button>
											</div>
										</div>
										</form> 
								</div>
								
								
								<!-- 百世获取单号设置 -->
								<div class="tab-pane" id="five">
									<form method="post" class="form-horizontal" id="fiveOptions">
									 <input name= "begCode" value="5" hidden="">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第一个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="qualityText1"
														name="qualityText1" value="${qualityText1}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第二个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="qualityText2"
														name="qualityText2" value="${qualityText2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第三个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="qualityText3"
														name="qualityText3" value="${qualityText3}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="editImgText('fiveOptions')">保存设置</button>
											</div>
										</div>
										</form> 
								</div>
								
								<div class="tab-pane" id="six">
									 <form method="post" class="form-horizontal" id="sixOptions">
										 <input type="text" hidden="" name="begCode" value="6">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第一个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="marketingText1"
														name="marketingText1" value="${marketingText1}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第二个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="marketingText2"
														name="marketingText2" value="${marketingText2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第三个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="marketingText3"
														name="marketingText3" value="${marketingText3}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第四个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="marketingText4"
														name="marketingText4" value="${marketingText4}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第五个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="marketingText5"
														name="marketingText5" value="${marketingText5}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">第六个标签内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="marketingText6"
														name="marketingText6" value="${marketingText6}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="editImgText('sixOptions')">保存设置</button>
											</div>
										</div>
									</form> 
								</div>
								
								<div class="tab-pane" id="Seven">
									 <form method="post" class="form-horizontal" id="SevenOptions">
										 <input type="text" hidden="" name="begCode" value="7">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业思想标题</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText1"
														name="aboutusText1" value="${aboutusText1}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业思想内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText2"
														name="aboutusText2" value="${aboutusText2}">
												</div>
											</div>
											
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业思想内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText3"
														name="aboutusText3" value="${aboutusText3}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业关于标题</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText4"
														name="aboutusText4" value="${aboutusText4}">
												</div>
											</div>
											
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业关于内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText5"
														name="aboutusText5" value="${aboutusText5}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业关于内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText6"
														name="aboutusText6" value="${aboutusText6}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业荣誉标题</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText7"
														name="aboutusText7" value="${aboutusText7}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">获得荣誉世界</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText8"
														name="aboutusText8" value="${aboutusText8}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">荣誉内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText9"
														name="aboutusText9" value="${aboutusText9}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">获得荣誉世界</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText10"
														name="aboutusText10" value="${aboutusText10}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">荣誉内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText11"
														name="aboutusText11" value="${aboutusText11}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">获得荣誉世界</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText12"
														name="aboutusText12" value="${aboutusText12}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">荣誉内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText13"
														name="aboutusText13" value="${aboutusText13}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">获得荣誉世界</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText14"
														name="aboutusText14" value="${aboutusText14}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">荣誉内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText15"
														name="aboutusText15" value="${aboutusText15}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">获得荣誉世界</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText16"
														name="aboutusText16" value="${aboutusText16}">
												</div>
											</div>
												<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">荣誉内容</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="aboutusText17"
														name="aboutusText17" value="${aboutusText17}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="editImgText('SevenOptions')">保存设置</button>
											</div>
										</div>
									</form> 
								</div> 
								
								
								<div class="tab-pane" id="Eight">
									 <form method="post" class="form-horizontal" id="EightOptions">
										 <input type="text" hidden="" id="EightbegCode" value="8">
										<div class="box-body">
											
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">中国</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="company"
														name="company" value="${company}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">城市</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="city"
														name="city" value="${city}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">园区</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="park"
														name="park" value="${park}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">电话</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hotline"
														name="hotline" value="${hotline}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">手机</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hotline2"
														name="hotline2" value="${hotline2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">地址</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="address"
														name="address" value="${address}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">邮编</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="zip"
														name="zip" value="${zip}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">Email</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="Email"
														name="Email" value="${Email}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">中文域名</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="domainName"
														name="domainName" value="${domainName}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">Copyright</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="Copyright"
														name="Copyright" value="${Copyright}">
												</div>
											</div>
											<div class="form-group">
												<div class="col-lg-4 col-sm-8">
														<label class="col-sm-3 control-label">微信：</label><br/>
											             <input type="file" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*"/>
											           <P align="center"><img src="${wechat_code}"  id="xmTanImg"  height="300" width="300"></p>
											            <div id="xmTanDiv"></div>
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="editImgText('EightOptions')">保存设置</button>
											</div>
										</div>
									</form> 
								</div>
								
								<div class="tab-pane" id="totalEdit">
									 <form method="post" class="form-horizontal" id="totalEditfrom">
										 <input type="text" hidden="" id="tolobegCode" value="0">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">total</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="total"
														name="total" value="${total}">
												</div>
											</div>
										
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="editImgText('totalEditfrom')">保存设置</button>
											</div>
										</div>
									</form> 
								</div>
							</div>
						</div>
					</div>
				</div>
				</section>
			</div>
		</div>
	</div>


<%@ include file="/static/common/footer.txt"%>
<script>   

//判断浏览器是否支持FileReader接口
if (typeof FileReader == 'undefined') {
    document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
    document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
}
//选择图片，马上预览
function xmTanUploadImg(obj) {
    var file = obj.files[0];
    var reader = new FileReader();
    //读取文件过程方法
    reader.onloadstart = function (e) {
        console.log("开始读取....");
    }
    reader.onprogress = function (e) {
        console.log("正在读取中....");
    }
    reader.onabort = function (e) {
        console.log("中断读取....");
    }
    reader.onerror = function (e) {
        console.log("读取异常....");
    }
    reader.onload = function (e) {
        console.log("成功读取....");
     
       	var img = document.getElementById("xmTanImg");
        img.src = e.target.result;
    }
    reader.readAsDataURL(file)
} 




function editImgText(id) {
	if(id=="EightOptions"){
		var fromdata = new FormData();
		 document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
		var imgFile = document.getElementById("xdaTanFileImg");
		var obj = imgFile.files[0];
		var  arr=obj.name.split('.');
		var wechat_code = "/ztznew/images/wechat_code."+arr[arr.length-1];
		var hotline2 = document.getElementById("hotline2").value;
		var hotline = document.getElementById("hotline").value;
		var company = document.getElementById("company").value;
		var city = document.getElementById("city").value;
		var park = document.getElementById("park").value;
		var address = document.getElementById("address").value;
		var zip = document.getElementById("zip").value;
		var Email = document.getElementById("Email").value;
		var domainName = document.getElementById("domainName").value;
		var Copyright = document.getElementById("Copyright").value;
		var begCode = document.getElementById("EightbegCode").value;
		fromdata.append("begCode",begCode);
		fromdata.append("wechat_code",wechat_code);
		fromdata.append("hotline2",hotline2);
		fromdata.append("hotline",hotline);
		fromdata.append("company",company);
		fromdata.append("city",city);
		fromdata.append("park",park);
		fromdata.append("address",address);
		fromdata.append("zip",zip);
		fromdata.append("Email",Email);
		fromdata.append("domainName",domainName);
		fromdata.append("Copyright",Copyright);
		fromdata.append("file",obj);
		var editurl ='<%=path%>/Welcome/EightEdit.do';
		$.ajax({
			url:editurl,
			type:'post',
			data:fromdata,
			async:false,
			processData : false,
			contentType : false,
			success:function(r){
				if(r=="ok"){
					layer.msg("保存成功", {
	      			  icon: 1,
	      			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	      			}, function(index){
	      			
	      	})
				}
			}
		})
	}<%-- else if(id=="totalEditfrom"){
		var fromdata = new FormData();
		 document.getElementById("logoFileImg").setAttribute("disabled", "disabled");
		var imgFile = document.getElementById("logoFileImg");
		
		var obj = imgFile.files[0];
		var total = document.getElementById("total").value;
		var begCode = document.getElementById("tolobegCode").value;
		fromdata.append("begCode",begCode);
		fromdata.append("loginImg","loginImg");
		fromdata.append("total",total);
		fromdata.append("file",obj);
		var editurl ='<%=path%>/Welcome/EightEdit.do';
		$.ajax({
			url:editurl,
			type:'post',
			data:fromdata,
			async:false,
			processData : false,
			contentType : false,
			success:function(r){
				if(r=="ok"){
					layer.msg("保存成功", {
	      			  icon: 1,
	      			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	      			}, function(index){
	      			
	      	})
				}
			}
		})
	}else --%>else{
		var editurl ='<%=path%>/Welcome/editTowFont.do';
		$.ajax({
			url:editurl,
			type:'post',
			data:$('#'+id).serialize(),
			success:function(r){
				if(r=="ok"){
					layer.msg("保存成功", {
	      			  icon: 1,
	      			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	      			}, function(index){
	      			  //do something
	      			
	      	})
				}
			}
		})
	}
}
	
</script>
</body>
</html>