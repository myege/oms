<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<title>OMS</title>
   <%@ include file="/static/common/head.txt" %>
    <link href="<%=path %>/static/css/login.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>
</head>
<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>OMS</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>订单管理系统</strong></h4>
                    <!-- <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong> -->
                </div>
            </div>
            <div class="col-sm-5">
                <form id="loginForm" method="post" action="<%=path%>/login/loginUser.do">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到订单管理系统</p>
                    <input type="text" class="form-control uname" name="userName" placeholder="用户名" />
                    <input type="password" name="password" class="form-control pword m-b" placeholder="密码" />
                   <!--  <a href="default.htm">忘记密码了？</a> -->
                    <button class="btn btn-success btn-block"  type="submit">登录</button>
                    <div style="color:red">
                    <label>
                        ${message}
                    </label>
                </div>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 版权所有 2019
            </div>
        </div>
    </div>
</body>
</html>