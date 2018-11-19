<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row">
				<div class="span12">
					<ul class="nav nav-tabs">
						<li class="disabled message">
							<a></a>
						</li>
						<li class="disabled">
							<a>欢迎:<%= session.getAttribute("name") %></a>
						</li>
						<li>
							<a href="logout">注销</a>
						</li>
						<li class="active">
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#">资料</a>
						</li>
						<li class="disabled">
							<a href="#">信息</a>
						</li>

						<li class="dropdown pull-right">
							<a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="#">操作</a>
								</li>
								<li>
									<a href="#">设置栏目</a>
								</li>
								<li>
									<a href="#">更多设置</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">分割线</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
</body>
<script type="text/javascript">
var $currentDate;
$(document).ready(function(){
	function showDate(){
		var t=new Date();
		var year = t.getFullYear();
		var month = t.getMonth()+1;
		var day = t.getDate();
		var h = t.getHours();
		var m = t.getMinutes();
		var s = t.getSeconds();
		if (m<10) {
			m = "0" + m;
		}
		if (s<10) {
			s = "0" + s;
		}
		$currentDate = year+'-'+month+"-"+day+" "+h+':'+m+":"+s;
		
		$(".message").find("a").eq(0).text("访问时间: "+$currentDate);
	}
	 setInterval(showDate,1);
});
</script>
</html>