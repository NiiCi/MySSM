<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="javax.servlet.http.Cookie" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap-2.3.2.css"/>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="js/bootstrap-2.3.2.js" type="text/javascript"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<title>Insert title here</title>
<style>
	.msg {
		color: red;
	}
	p {
		font-size: 10px;
		line-height: 10px;
	}
	body{
		background: url("image/5-120601094K8.gif"),no-repeat;
	}
	.controls > input{
		text-indent: 5px;
	}
</style>
</head>

<body>	
	<%-- <%
		Cookie cookies[] = request.getCookies();
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("nameCookie")){
						request.setAttribute("name", URLDecoder.decode(cookies[i].getValue(),"utf-8"));
						request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
		}	
	%> --%>
	<!-- 统计人数 -->
<%-- 	<%
		Set<String> ips;
		Object obj_ips = application.getAttribute("ips");		
		if(obj_ips==null){
			ips = new HashSet<String>();
		}else{
			ips = (HashSet)obj_ips;
		}
		ips.add(request.getRemoteAddr());
		application.setAttribute("ips", ips);
		out.print(ips.size());	
	%> --%>
	<div class="container" style="margin: 100px auto;">
		<div class="row">
			<div class="span6"></div>
			<div class="span6 " style="padding: 50px 0;">
				<form action="userLogin" method="post" class="form-horizontal">
					<div class="control-group" style="margin-bottom: 10px;">
						<label class="control-label" for="inputUser"><f:message key="username"></f:message></label>
						<div class="controls">
							<input id="inputUser" type="text" name="admin_name"/>
							<p class="help-block"><f:message key="nameMessage"></f:message></p>
						</div>
					</div>
					<div class="control-group" style="margin-bottom: 10px;">
						<label class="control-label" for="inputPassword"><f:message key="password"></f:message></label>
						<div class="controls">
							<input id="inputPassword" type="password" name="admin_password"/>
							<p class="help-block"><f:message key="passwordMessage"></f:message></p>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">请选择身份</label>
						<div class="controls">
							<select>
								<option>管理员</option>
								<option>普通用户</option>
								<option selected="selected">游客</option>
							</select>
						</div>
					</div>
					<div class="controls">
						<p class="errMessage">
						${sessionScope.err}
						</p>
					</div>
					<div class="control-group ">
						<div class="controls " style="margin-bottom: 15px;">
							<label class="checkbox inline">
								<input type="checkbox"  name="remeberpwd"  style="padding:0px;margin-left:41px;"/>
								<f:message key="remeberpwd"></f:message>
							</label>
							<label class="checkbox inline" >
								<input type="checkbox" name="autoLogin"/>
								<f:message key="autoLogin"></f:message>
							</label>
						</div>
						<div class="text-right" style="margin:0 60px 10px 0;">
							<input type="submit" value='<f:message key="sign"></f:message>' class="btn btn-primary"
								style="margin-right: 10px;"/>
							<a href="register.jsp" role="button" class="btn btn-primary" ><f:message key="register"></f:message></a>
						</div>
						<a href="changeLanguage?locale=zh_CN" style="margin:0 10px 0 182px;">中文版</a>
						<a href="changeLanguage?locale=en_US">English</a>
					</div>
				</form>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript">
		$(document).ready(function(){
			var nameCookie = $.cookie("nameCookie");
			var pwdCookie = $.cookie("pwdCookie");
			var checkCookie = $.cookie("checkCookie");
			var checkAuto = $.cookie("checkAuto");
			var autoLogin = $(".inline").eq(1).find("input").val();
			console.log("checkAuto------: " + checkAuto);
			console.log("nameCookie------: "+nameCookie);
			console.log("pwdCookie------: " + $.cookie("pwdCookie"));
			console.log("checkCookie------: " + $.cookie("checkCookie"));
			$("#inputUser").val(nameCookie);
			if (checkCookie != undefined && checkCookie == "on") {
				$("#inputPassword").val(pwdCookie);
				console.log($("#inputPassword").val());
			}else{
				$("#inputPassword").val("");
			}
			/* if (checkAuto != undefined && checkAuto == "on") {
				$("#inputUser").val(nameCookie);
				console.log("nameCookie------:" + nameCookie);
				
				$("#inputPassword").val(pwdCookie);
				$("form").submit();
			} */
		})
		$(".controls input").focus(function(){
			$(".errMessage").html(" ");
		});
		$(":input").blur(function(){
			switch($(this).prop("id")){
				case "inputUser":
				var reg = /^[\u4e00-\u9fa5]{2,}$/;
				var flag = reg.test($(this).val());
				if (!flag) {
					$(this).next().html("用户名格式不正确！");
					$(this).next().addClass("msg");
				} else{
					$(this).next().removeClass("msg");
					$(this).next().html("用户名必须为汉字");
				}
				return flag;
				break;
				case "inputPassword":
				var reg = /^[\w!@#$%^&*()_+~]{6,16}$/;
				var flag = reg.test($(this).val());
				if ($(this).val() == "") {
				$(this).next().html("密码不能为空！");
				$(this).next().addClass("msg");
				} else if($(this).val().length < 6){
					$(this).next().addClass("msg");
					$(this).next().html("密码必须等于或大于6个字符");
					return false;
				}else{
					$(this).next().removeClass("msg");
					$(this).next().html("密码为6到16位字符");
					return true;
				}
				break;
			}
		});
		$("form").submit(function(){
			$(":input").trigger("blur");
			if ($(".msg").length > 0) {
				return false;
			} else{
				return true;
			}
		});
			
	</script>
</html>