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
<form class="layui-form"id="oneImgEdit">
	<div class="col-xs-12">
			<label class="col-sm-3 control-label">内容：</label>
			<input class="form-control" type="text" id="casesOne2" value="${Img1}">
	</div>
	<div class="col-xs-12">
			<label class="col-sm-3 control-label">图片：</label><br/>
             <input type="file" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*"/>
           <P align="center"><img src="${Img2}"  id="xmTanImg"  height="300" width="300"></p>
            <div id="xmTanDiv"></div>
	</div>
</form>
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
    console.log(obj);console.log(file);
    console.log("file.size = " + file.size);  //file.size 单位为byte
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
function submitHandler() {
	var fromdata = new FormData();
	var imgFile = document.getElementById("xdaTanFileImg");
	var obj = imgFile.files[0];
	var oneFont1 = document.getElementById("casesOne2").value;
	fromdata.append("file",obj);
	fromdata.append("casesOne2",oneFont1);
	var editurl ='<%=path%>/Welcome/ThreeEdit.do';
	
	$.ajax({
		url:editurl,
		type:'post',
		data:fromdata,
		async:false,
		processData : false,
		contentType : false,
		success:function(r){
			$.operate.successCallback("保存成功");
		}
	})

	

	
}
</script>
</body>
</html>