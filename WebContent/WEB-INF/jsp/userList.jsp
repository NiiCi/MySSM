<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale=1.0,maximum-scale=1.0,                                       
user-scalable=no"/>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script src="js/jquery-3.2.1.js" type="text/javascript" charset="UTF-8"></script>
		<script src="js/bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
<title>Insert title here</title>
<style type="text/css">
			input{
				width:150px;
				}
		</style>
</head>
<body>
 	 <%-- <%@ include file="session.jsp" %> --%>
		<div class="container">
			<div class="row">
				<div class="span12">
					<ul class="nav nav-tabs">
						<li class="disabled">
							<a>欢迎:${sessionScope.name}
							</a>
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

			<div class="row">
				<%@ include file="left.jsp" %>
				

				<div class="span10">
					<form class="form-search" style="margin-left:50px;">
						<input class="input-medium search-query" type="text" />
						<button type="submit" class="btn btn-default">查找</button>
						<a id="#edit" href="#edit" role="button" class="btn btn-primary" data-toggle="modal">新增</a>
				</form>
			
				
			<!--模态框 -->
			<div id="edit" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="myModalLabel">
						
					</h4>
				</div>
					<div class="modal-body">
					<form id="myform" action="UserServlet" method="post" class="form-horizontal">
					    <fieldset>
					 		<div class="control-group">
					          <label class="control-label" for="bid">编号:</label>
					          <div class="controls">
					            <input id="bid" name="id" type="text" placeholder="" readonly="readonly">
					            <p class="help-block"></p>
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="pdname">用户名:</label>
					          <div class="controls">
					            <input id="pdname" name="name" type="text" placeholder="">
					            <p class="help-block"></p>
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="putpwd">密码:</label>
					          <div class="controls">
					            <input id="putpwd" name="pwd"  type="password" placeholder="">
					            <p class="help-block"></p>
					          </div>
					        </div>


								<div class="control-group">
									<label class="control-label" >性别:</label>
									<div class="controls">
										<label class="radio inline"> 
										<input  type="radio"
											value="1" name="sex" checked="checked"> 男
										</label> 
										<label class="radio inline"> 
										<input  type="radio"
											value="0" name="sex"> 女
										</label>
									</div>
								</div>


							

								<div class="modal-footer">
									<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
									<button type="button" class="btn btn-primary save">保存</button>
								</div>

							</fieldset>
					  </form>
					</div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>
									编号
								</th>
								<th>
									用户名
								</th>
								<th>
									密码
								</th>
								<th>
									性别
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							
							<%-- <c:forEach items="${sessionScope.userListByPageIndex}" var="user">
							<tr>
								<td>${pageScope.user.id}</td>
								<td>${user.name}</td>
								<td>${user.pwd}</td>
								<td>
									<c:if test="${user.sex==1}" >男</c:if>
									<c:if test="${user.sex==0}">女</c:if>
								</td>
		                        <td>
		                        	<input type='button' id='#edit' href='#edit' class='btn btn-success btn-small' data-toggle='modal' value='修改'/>
		                      	  	<a href="UserServlet?id=${user.id}" role="button" class='btn btn-warning btn-small' >删除</a>
		                        </td>
	                        </tr>
	                        </c:forEach> --%>
						</tbody>
					</table>
					<div class="pagination text-right">
						<ul id="pages">
						</ul>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
				</div>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">	
		var $update;
		$(document).ready(function(){
			getData(1);
		});
		//Ajax 查询数据
		var pages;
		function getData(pageIndex){
			pages = pageIndex;
			$.ajax({
				type:"get",
				url:"UserServlet?pageIndex="+pageIndex,
				success:function(result){
					showData(result);
					showPage(result);
				}
			});
		}
		
		
		//解析并显示数据
		function showData(result){
			var Json = JSON.parse(result);
			console.log(Json.userList);
			$("tbody").empty();
			$.each(Json.userList,function(index,item){
				var $tr = $("<tr>"+
						"<td>"+item.id+"</td>"+
						"<td>"+item.name+"</td>"+
						"<td>"+item.pwd+"</td>"+
						"<td class='hide'>"+item.sex+"</td>"+
							/* <c:if test="${user.sex==1}" >男</c:if>
							<c:if test="${user.sex==0}">女</c:if> */
							
						"<td></td>" +
		                "<td>"+
		                	"<input type='button' id='#edit' href='#edit' class='btn btn-success btn-small' data-toggle='modal' value='修改'/>"+
		              	  	" <a href='#' role='button' class='btn btn-warning btn-small del' >删除</a>"+
		                "</td></tr>");
				if (item.sex == 1) {
					$tr.find("td").eq(4).text("男");
				}else{
					$tr.find("td").eq(4).text("女");
				}
				$("tbody").append($tr);
				$("tbody tr:odd").removeClass();
				$("tbody tr:even").addClass("info");
			});
			
			//Ajax 删除数据
			$(".del").click(function delData(){
				var id = $(this).parent().parent().find("td").eq(0).text();
				if(confirm("您确定要删除此用户吗?")){
					$.ajax({
						type:"get",
						url:"UserServlet",
						data:{"id":id},
						success:function(flag){
							if (flag == "error") {
								alert("删除失败！");
							}else if(flag == "success"){
								alert("删除成功！");
								if (Json.userList.length == 1) {
									getData(pages - 1);
								}else{
									getData(pages);
								}
							}
						}
					});
				}
			});
			
			//将表单td中的内容赋值到模态框中
			
			$("[value='修改']").click(function (){
				$("#myModalLabel").html($(this).prop("value")+"用户");
				$(".control-group").eq(0).show();
				$update = $(this);
				$.each($(".form-horizontal .controls :input:not(:radio)"),
					function(index,item) {
						$(this).removeAttr("disabled");
						item.value = $update.parent().parent().find("td").eq(index).text();
					}
				);
				if($update.parent().parent().find("td").eq(3).text().trim() == "1"){
					$("[type='radio']").eq(0).prop("checked",true);
					$("[type='radio']").eq(1).prop("checked","");
				}else{
					$("[type='radio']").eq(0).prop("checked","");
					$("[type='radio']").eq(1).prop("checked",true);
				}
			});
		}
		
		
		//解析并显示分页导航
		function showPage(result){
			var Json = JSON.parse(result);
			var pageCount = Json.pageCount;
			var pageIndex = Json.pageIndex;
			var userList = Json.userList;
			$("#pages").empty();
			//上一页
			if(pageIndex-1>=1){
				$("#pages").append("<li><a title='"+(pageIndex-1)+"'>上一页</a></li>");
				$("#pages").append("<li><a title='"+(pageIndex-1)+"'>"+(pageIndex-1)+"</a></li>");
			}
			//当前页
			$("#pages").append("<li><a name='currPage' style='color:red' title='"+pageIndex+"'>"+pageIndex+"</a></li>");
			//下一页
			if(pageIndex+1<=pageCount){
				$("#pages").append("<li><a title='"+(pageIndex+1)+"'>"+(pageIndex+1)+"</a></li>");
				$("#pages").append("<li><a title='"+(pageIndex+1)+"'>下一页</a></li>");
			}
			$("#pages").find("a").click(function(){
				getData($(this).attr("title"));
			});
		}
		
		
		
		
			
		//点击关闭,清空模态框中的内容
		$("[data-dismiss='modal']").click(function(){
			$(".form-horizontal :input:not(:radio)").val("");
		});
		
		//点击新增
		$(".form-search a").click(function(){
			$(".form-horizontal :input:not(:radio)").val("");
			$("#myModalLabel").html($(this).text()+"用户");
			$(".control-group").eq(0).hide();
		});
		//新增判断用户名是否存在;
		$("#pdname").blur(function(){
		/* 	console.log($(this).val()); */
			$.ajax({
				type:"get",
				url:"UserServlet",
				data:{"name":$(this).val()},
				success:function(result){
/* 					console.log(result); */
					if (result == "error") {
						alert("该用户已存在");
					}
				}
			});	
		});
		
		//Ajax提交
		var flag;
		 $(".save").click(function saveData(){
			if($("#myModalLabel").html() == "修改用户"){
				$.each($(".form-horizontal .controls :input:not(:radio)"),
					function(index,item){
						if(index > 0){
							if(item.value == $update.parent().parent().find("td").eq(index).text()){
								$(item).attr("disabled","disabled");
							}
						}
					}
				);
				if($("[disabled='disabled']").length == 2){
					alert("请修改后再提交");
					$(".form-horizontal .controls :input").removeAttr("disabled");
					return false;
				}else{	
					//Ajax 修改数据
					$(".form-horizontal .controls :input").removeAttr("disabled");
					$.ajax({
						type:"post",
						url:"UserServlet",
						data:$("#myform").serialize(),
						success:function(result){	
							if (result == "success") {								
								alert("修改用户成功");
								getData(pages);
							}else{
								alert("修改用户失败");
							}
							$("#edit").modal("hide");
						}	
					});
				}
			}else{
					var name = $("#pdname").val();
					var pwd = $("#putpwd").val();
					console.log(name);
					console.log(pwd);
					if (name == "") {
						alert("请输入用户名");
						return;
					}
					else if (pwd == "") {
						alert("请输入密码");
						return;
					}else{
						
						//Ajax 新增数据
						$.ajax({
							type:"post",
							url:"UserServlet",
							data:$("#myform").serialize(),
							success:function(result){	
								if (result == "success") {								
									alert("新增用户成功");
									getData(pages);
								}else{
									alert("新增用户失败");
								}
								$("#edit").modal("hide");
							}	
						});
						}
		 			}
		});
	
		

	</script>
</html>