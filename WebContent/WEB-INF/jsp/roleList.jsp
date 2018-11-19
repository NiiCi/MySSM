<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width"
	,initial-scale=1.0,maximum-scale=1.0, user-scalable=no "/>
<link rel="stylesheet" href="css/bootstrap-2.3.2.css" />
<script src="js/jquery-1.8.3.js" type="text/javascript" charset="UTF-8"></script>
<script src="js/bootstrap-2.3.2.js" type="text/javascript"
	charset="UTF-8"></script>
<script src="My97DatePicker/WdatePicker.js" type="text/javascript"
	charset="UTF-8"></script>
<title>Insert title here</title>
<style type="text/css">
input {
	width: 150px;
}

input[type="file"] {
	height: 25px;
	line-height: 30px;
	width: 200px;
}
</style>
</head>
<body>
	<%-- <%@ include file="session.jsp" %> --%>
	<div class="container">
		<%@include file="head.jsp"%>
		<div class="row">
			<%@ include file="left.jsp"%>
			<div class="span10">
				<form id="fileForm" class="form-search" method="post"
					enctype="multipart/form-data" style="margin-left: 50px;">
					<input class="input-medium search-query" type="text" />
					<button type="submit" class="btn btn-default">查找</button>
					<a id="#edit" href="#edit" role="button" class="btn btn-primary"
						data-toggle="modal">新增</a> <a id="upload"
						class="btn btn-default  btn-small pull-right">导入</a> <input
						style="margin: 1px 5px 0px" class="pull-right" id="fileInput"
						type="file" name="uploadfile"> <a href="download"
						class="btn btn-default  btn-small pull-right">导出</a>
				</form>





				<!--模态框 -->
				<div id="edit" class="modal hide fade" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 id="myModalLabel"></h4>
					</div>
					<div class="modal-body">
						<form id="myform" action="UserServlet" method="post"
							class="form-horizontal">
							<fieldset>
								<div class="control-group">
									<label class="control-label" for="roleid">ID:</label>
									<div class="controls">
										<input id="roleid" name="roleid" type="text" placeholder=""
											readonly="readonly">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="rolename">名称:</label>
									<div class="controls">
										<input id="rolename" name="roleName" type="text"
											placeholder=""> <span></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="roledesc">描述:</label>
									<div class="controls">
										<input id="roledesc" name="roleDesc" type="text"
											placeholder=""> <span></span>
									</div>

								</div>
								<div class="control-group">
									<label class="control-label" for="rolecode">代码:</label>
									<div class="controls">
										<input id="rolecode" name="roleCode" type="text"
											placeholder=""> <span></span>
									</div>
								</div>

								<div class="control-group">
									<label class="control-label">设置权限</label>
									<div class="controls">
										<!-- <label class="checkbox"> <input type="checkbox"
											name="roleid" value="1"> 超级管理员
										</label> <label class="checkbox"> <input type="checkbox"
											name="roleid" value="2"> 产品管理员
										</label> <label class="checkbox"> <input type="checkbox"
											name="roleid" value="3"> 供应商管理员
										</label> <label class="checkbox"> <input type="checkbox"
											name="roleid" value="4"> 类别管理员
										</label> <label class="checkbox"> <input type="checkbox"
											name="roleid" value="5"> 订单管理员
										</label> -->
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
							<th>ID</th>
							<th>名称</th>
							<th>描述</th>
							<th>代码</th>
							<th>拥有权限</th>
							<th>操作</th>
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
			<div class="span12"></div>
		</div>
	</div>
</body>

<script type="text/javascript">	
		var $update;
		var $currentDate;
		var oldChecked = new Array();
		var newChecked = new Array();
		var $checkboxVal;
		var $perList;
		var $checkedLength;
		var $pers;
		var roleid;
		$(document).ready(function(){
			getData(1);
			$.ajax({
				type:"get",
				url:"perController",
				success:function(msg){
					console.log(msg);
					$.each(msg,function(index,item){
						$pers = $('<label class="checkbox">' +
								'<input type="checkbox" name="perid" value="'+item.perid+'">'+item.perDesc+
								"&nbsp;&nbsp;"+((item.isNav == 1 ) ?'<span style="color:red">导航节点</span>':'<span>非导航节点</span>')+
								'</label>');
						
						$(".controls").eq(4).append($pers);
					});
					
				}
			})
		});
		//Ajax 查询数据
		var pages;
		function getData(pageIndex){
			pages = pageIndex;
			$.ajax({
				type:"get",
				url:"roleController",
				data:{"pageIndex":pageIndex},
				success:function(result){
					console.log(result);
					showData(result);
					showPage(result);
				}
			});
		}
		
		//上传文件
		$("#upload").click(function(){
			$.ajax({
				type:"post",
				url:"upload",
				cache:false,
				data:new FormData($("#fileForm")[0]),
				processData:false,
				contentType:false,
				success:function(msg){
					console.log(msg);
					if (msg!="success") {
						alert("成功: "+msg.okCount+"\n"+"失败: "+msg.errCount);
						$.each(msg.errors,function(index,item){
							alert("第"+index+"条记录:错误原因-"+item);
						});
					}
					getData(pages);
				}
			});
		});
		
		//解析并显示数据
		function showData(Json){
			console.log(Json);
			$("tbody").empty();
			$.each(Json.list,function(index,item){
				var $tr = $("<tr>"+
						"<td>"+item.roleid+"</td>"+
						"<td>"+item.roleName+"</td>"+
						"<td>"+item.roleDesc+"</td>"+
						"<td>"+item.roleCode+"</td>"+
						"<td><a class='showPer'>查看所有权限</a></td>"+
		                "<td>"+	
		                	"<input type='button' id='#edit' href='#edit' class='btn btn-success btn-small' data-toggle='modal' value='修改'/>"+
		              	  	" <a href='#' role='button' class='btn btn-warning btn-small del' >删除</a>"+
		                "</td></tr>");
				$("tbody").append($tr);
				$("tbody tr:odd").removeClass();
				$("tbody tr:even").addClass("info");
				
			});
			//点击显示所有权限按钮,显示权限
			
			$(".showPer").click(function(){
				$show = $(this).parent();
				var $per;
				roleid = $(this).parent().parent().find("td").eq(0).text();
				console.log(roleid);
				$(this).hide();
				$.ajax({
					type:"get",
					url:"roleController/"+roleid,
					success:function(msg){
						console.log(msg);
						$.each(msg,function(index,item){
							$per = $(
								'<span>'+item.perDesc+'</span></br>');
							$show.append($per);
						});
						$show.append($('<a class="hiddenPer">隐藏所有权限</a>'));
						$(".hiddenPer").click(function(){
							$(this).hide();
							$(this).parent().parent().find("br").remove();
							$(this).parent().parent().find("span").html("");
							$(this).parent().parent().find(".showPer").show();
						});
					}
				})
				
			});
			//Ajax 删除数据
			$(".del").click(function delData(){
				var id = $(this).parent().parent().find("td").eq(0).text();
				if(confirm("您确定要删除此管理员吗?")){
					$.ajax({
						type : "delete",
						url : "roleController/" + id,
						success : function(msg) {
							if (msg == "error") {
								alert("删除失败！");
							} else if (msg == "success") {
								alert("删除成功！");
								if (Json.list.length == 1) {
									getData(pages - 1);
								} else {
									getData(pages);
								}
							}
						}
					});
				}
			});

		//点击修改,将表单td中的内容赋值到模态框中

		$("[value='修改']").click(function() {
			$(".control-group").find("span").not("label span").html("");
			$("#myModalLabel").html($(this).prop("value") + "角色");
			$(".control-group").eq(0).show();
			$update = $(this);
			roleid = $(this).parent().parent().find("td").eq(0).text();
			$.ajax({
				type : "get",
				url : "roleController/" + roleid,
				success : function(msg) {
					$perList = msg;
					$.each($(".form-horizontal .controls :input:checkbox"),
							function(checkBoxIndex,checkBoxItem) {
						$(this).removeAttr("checked");
						$checkboxVal = $(this);
						$.each($perList,function(perIndex,perItem) {
							if (perItem.perid == $checkboxVal.val()) {					
								$checkboxVal.attr("checked","checked");
							}
						});
						/* var checkedFlag = false;
						$(checkBoxItem).change(function(){
							if ($(this).attr("checked")=="checked") {
								checkedFlag = true;
							}else{
								checkedFlag = false;
							}
							console.log("checkedFlag: "+checkedFlag);
						}); */										
						console.log(" ----- ");
						oldChecked[checkBoxIndex] = $checkboxVal.attr("checked");
						$checkedLength = $("[checked='checked']").length;
					});
					console.log("未修改前checked的长度-----: "+ $checkedLength);
				}
			});
			$.each($(".form-horizontal .controls :input:not(:checkbox)"),					
					function(index, item) {								
				$(this).removeAttr("disabled");								
				item.value = $update.parent().parent().find("td").eq(index).text();
			});
		});
	}
	//解析并显示分页导航
	function showPage(Json) {
		var pageCount = Json.pages;
		var pageIndex = Json.pageNum;
		var adminList = Json.list;
		$("#pages").empty();
		//上一页
		if (pageIndex - 1 >= 1) {
			$("#pages").append(
				"<li><a title='" + (pageIndex - 1) + "'>上一页</a></li>");
			/* $("#pages").append("<li><a title='"+(pageIndex-1)+"'>"+(pageIndex-1)+"</a></li>"); */
		}
		//导航页
		$.each(Json.navigatepageNums, function(index, item) {
			if (item == pageIndex) {
				$("#pages").append(
					"<li><a name='currPage' style='color:red' title='"+item+"'>"
						+ item + "</a></li>");
			} else {
				$("#pages").append(
					"<li><a name='currPage'  title='"+item+"'>" + item
						+ "</a></li>");
			}
		});

		//下一页
		if (pageIndex + 1 <= pageCount) {
			/* $("#pages").append("<li><a title='"+(pageIndex+1)+"'>"+(pageIndex+1)+"</a></li>"); */
			$("#pages").append(
				"<li><a title='" + (pageIndex + 1) + "'>下一页</a></li>");
		}
		$("#pages").find("a").click(function() {
			getData($(this).attr("title"));
		});
	}

	//点击关闭,清空模态框中的内容
	$("[data-dismiss='modal']").click(function() {
		$(".form-horizontal :input:not(:checkbox)").val("");
	});

	//点击新增
	$(".form-search a").click(function() {
		/* $(".form-horizontal :input:not(:radio)").val(""); */
		$(".form-horizontal :input:not(:checkbox)").val("");
		$("#myModalLabel").html($(this).text() + "角色");
		$(".control-group").eq(0).find("input").val("0");
		$(".control-group").eq(0).hide();
		$(".control-group").find()
		$(".control-group").find("span").not("label span").html("");
		$("#myform").find(":checkbox").removeAttr("checked");
	});
	//新增判断管理员姓名是否存在;
	var flag;
	/* $("#adname").blur(function(){
		console.log("-----管理员名: " + $(this).val());
		$.ajax({
			type:"get",
			url:"AdminServlet",
			data:{"name":$(this).val()},
			success:function(result){
				if (result == "error") {
					alert("该管理员已存在");
					flag = false;
				}else{
					flag = true;
				}
			}
		});	
	}); */

	//Ajax提交
	$(".save").click(function saveData() {			
		$(".control-group").find("span").not("label span").html("");
		if ($("#myModalLabel").html() == "修改角色") {
						
			$.each($(".form-horizontal .controls :input:not(:checkbox)"),						
					function(index, item) {								
				if (index > 0) {						
					if (item.value == $update.parent().parent().find("td").eq(index).text()) {								
						$(item).attr("disabled","disabled");		
					}	
				}
			});
			var count = 0;		
			$.each($(".form-horizontal .controls :checkbox"),
					function(checkedIndex, checkedItem) {		
				newChecked[checkedIndex] = $(checkedItem).attr("checked");							
				if (oldChecked[checkedIndex] != newChecked[checkedIndex]) {						
					count++;					
				}			
			});				
			console.log(oldChecked);
			console.log(newChecked);			
			console.log("count-----: " + count);		
			/* console.log("修改后checked的长度-----: "+$("[checked='checked']").length); */	
			console.log("修改角色个数-----: " + count);	
			if ($("[disabled='disabled']").length == 3 && count == 0) {
				alert("请修改后再提交");		
				$(".form-horizontal .controls :input").removeAttr("disabled");		
				return false;	
			} else if ($("[disabled='disabled']").length != 3 && count == 0) {		
				//Ajax 修改数据			
				$(".form-horizontal .controls :input").removeAttr("disabled");		
				$.ajax({			
					type : "put",		
					url : "roleController/updateInfo",
					data : $("#myform").serialize(),	
					success : function(result) {
						console.log(result);
						$.each(result, function(index, item) {	
							console.log(item);	
							$(".control-group").find("span").not("label span").eq(index - 2).html(item).css({"color" : "red","font-size" : "12px"});		
						});		
						if (result == "success") {										
							getData(pages);									
							$("#edit").modal("hide");											
							alert("修改成功");										
						} else {										
							alert("修改失败");									
						}									
					}
				});
			} else if ($("[disabled='disabled']").length == 3 && count != 0) {								
				$(".form-horizontal .controls :input").removeAttr("disabled");		
				$.ajax({	
					type : "put",	
					url : "roleController/updatePer",
					data : $("#myform").serialize(),
					success : function(result) {
						console.log(result);
						$.each(result, function(index, item) {
							console.log(item);
							$(".control-group").find("span").not("label span").eq(index - 2).html(item).css({"color" : "red","font-size" : "12px"});		
						});	
						if (result == "success") {										
							getData(pages);
							$("#edit").modal("hide");
							alert("修改成功");
						} else {			
							alert("修改失败");		
						}
					}		
				});		
			} else {			
				$(".form-horizontal .controls :input").removeAttr("disabled");			
				$.ajax({
					type : "put",		
					url : "adminController/updateAll",	
					data : $("#myform").serialize(),
					success : function(result) {
						console.log(result);
						$.each(result, function(index, item) {
							console.log(item);
							$(".control-group").find("span").not("label span").eq(index - 2).html(item).css({"color" : "red","font-size" : "12px"});
						});
						if (result == "success") {				
							getData(pages);					
							$("#edit").modal("hide");				
							alert("修改成功");			
						} else {		
							alert("修改失败");			
						}			
					}				
				});
			}		
		} else {			
			var name = $("#adname").val();			
			var pwd = $("#adpwd").val();			
			var date = $("#adDate").val();			
			console.log($("#myform").find(":checkbox"));			
			console.log(name);		
			console.log(pwd);		
			console.log(date);
							/* if (name == "") {
								alert("请输入管理员姓名");
								return;
							}
							else if (pwd == "") {
								alert("请输入密码");
								return;
							}else if(date == ""){
								alert("请输入出生日期");
								return; */
							/* } *//* else if(!flag){
													alert("该管理员已存在,请重新输入!");
													
												} *//* else{ */
							//Ajax 新增数据
							$.ajax({
								type : "post",
								url : "roleController",
								data : $("#myform").serialize(),
								success : function(result) {
									console.log("------: " + result);
									$.each(result, function(index, item) {
										console.log(item);
										$(".control-group").find("span").not("label span").eq(
												index - 2).html(item).css({
											"color" : "red",
											"font-size" : "12px"
										});
									});
									if (result == "success") {
										alert("新增成功");
										getData(pages);
										$(".control-group").find("span").not("label span").html(
												"");
										$("#edit").modal("hide");

									} else {
										alert("新增失败");
									}
								}
							});
						}
					});
</script>
</html>