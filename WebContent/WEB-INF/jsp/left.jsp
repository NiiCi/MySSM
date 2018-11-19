<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.accordion-inner a {
	margin-left: 20px;
	color: #000;
}
</style>
</head>
<body>
	<div class="span2">
		<div class="accordion" id="accordion-675694">
			<c:forEach items="${navList}" var="item" varStatus="vs">
			<shiro:hasRole name="${item.roleCode}">
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle  collapsed" data-toggle="collapse" data-parent="#accordion-675694" href="#div${vs.index}">${item.roleDesc}</a>
					</div>
					<div id="div${vs.index}" class="accordion-body collapse">

						<c:forEach items="${item.pers }" var="per">
							<shiro:hasPermission name="${per.perCode}">
								<div class="accordion-inner">
									<a href="${ per.url}">${per.perDesc}</a>
								</div>
							</shiro:hasPermission>
						</c:forEach>
					</div>
				</div>
				</shiro:hasRole>
			</c:forEach>
		</div>
	</div>
</body>

<script type="text/javascript">
<%-- 	$(document).ready(function(){
		var name = '<%= session.getAttribute("name")%>';
		var perDesc;
		var perCode;
		var perid;
		var url;
		var $nav;
		var $display;
		console.log(name);
		$.ajax({
			type:"post",
			url:"navController",
			data:{"admin_name":name},
			success:function(msg){
				
			 	$.each(msg,function(index,item){
			 	
			 		$.each(item.pers,function(index,item2){
			 			perDesc = item2.perDesc;
			 			perCode = item2.perCode;
			 			perid = item2.perid;
			 			url = item2.url;
			 			console.log(perid);
			 			$nav = $(	
								'<div class="accordion-group">'+
									'<div class="accordion-heading">'+
										'<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion-675694" href="#'+perid+'">'+item.roleDesc+'</a>'+
									'</div>'+
									'<div id="'+perid+'" class="accordion-body collapse">'+
										
										'<div class="accordion-inner">'+
											'<a href="'+url+'">'+perDesc+'</a>'+
										'</div>'+
									'</div>'+
								'</div>');
			 			
			 			});
			 		$(".accordion").append($nav);
						
	
				}) 
			}
		});
		
	}); --%>
	
</script>
</html>