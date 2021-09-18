<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登录</title>
	<style>
	/* .logincss{
		background:#478E13 url(../images/login.png)  no-repeat; 
		background-position:top center;
		color:red; 
		width:100%;
	}
	.logbox { width:766px; margin:0 auto; height:389px; margin-top:149px; position:relative;}
	.loginbox { width:400px; height:254px; position:absolute; right:208px; top:300px;} */
	
	body {
			
            padding-top: 185px;
            padding-bottom: 185px;
            background-color: #eee;
            
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 150px auto;
           
            
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin .checkbox {
            font-weight: normal;
        }
        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="text"] {
            margin-bottom: 5px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
	
	</style>
	<link href="<%=path%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
</head>
<body style="background-image:url(<%=path%>/images/newlogin4.jpg) ;background-repeat:no-repeat ;background-size:100% 100% ;height:90%">
<div class="container" style="width:80%" >
    <div class="row" >
        <div class="col-xs-8" >
            <!-- <img src="medias/images/Banner.jpg" style="opacity:0.8" class="img-responsive img-rounded" alt="Cinque Terre"> -->
        </div>
        <div class="col-xs-4" style="background-image:url(<%=path%>/images/loginpad.jpg) ;background-repeat:no-repeat ;background-size:100% 100% ;opacity:0.8 ">
        	<div style="height:80%">
            <form class="form-signin" id="loginForm" method="post" action="<%=path%>/login/loginUser.do" >
                <h2 class="form-signin-heading">请登录</h2>
                <label for="mobile" class="sr-only">账号</label>
                <input type="text" name="userName" class="form-control" placeholder="账号" required autofocus>
                <label for="password" class="sr-only">密码</label>
                <input type="password" name="password" class="form-control" placeholder="密码" required>
                <div style="color:red">
                    <label>
                        ${message}
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" >登录<tton>
            </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>