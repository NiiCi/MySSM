<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",initial-scale=1.0,maximum-scale=1.0,                                       
user-scalable=no"/>
	<link rel="stylesheet" href="css/bootstrap-2.3.2.css" />	
	<script src="js/jquery-1.8.3.js" type="text/javascript" charset="UTF-8"></script>
	<script src="js/bootstrap-2.3.2.js" type="text/javascript" charset="UTF-8"></script>
	<script src="My97DatePicker/WdatePicker.js" type="text/java script" charset="UTF-8"></script>
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
			<%@include file="head.jsp" %>
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
					          <label class="control-label" for="pvid">编号:</label>
					          <div class="controls">
					            <input id="pvid" name="providerID" type="text" placeholder="" readonly="readonly">
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="pvname">姓名:</label>
					          <div class="controls">
					            <input id="pvname" name="provider_name" type="text" placeholder="">
					            <span></span>
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="pvaddr">地址:</label>
					          <div class="controls">
					            <input id="pvaddr" name="provider_add"  type="text" placeholder="">
					            <span></span>
					          </div>
					        </div>
					         <div class="control-group">
					          <label class="control-label" for="pvaddr">联系方式:</label>
					          <div class="controls">
					            <input id="pvtel" name="provider_tel"  type="text" placeholder="">
					            <span></span>
					          </div>
					        </div>
					         <div class="control-group">
					          <label class="control-label" for="pvacc">卡号:</label>
					          <div class="controls">
					            <input id="pvacc" name="account"  type="text" placeholder="">
					            <span></span>
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="pvemail">邮箱:</label>
					          <div class="controls">
					            <input id="pvemail" name="email"  type="text" placeholder="">
					            <span></span>
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
									姓名
								</th>
								<th>
									地址
								</th>
								<th>
									联系方式
								</th>
								<th>
									卡号
								</th>
								<th>
									邮箱
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
				url:"providerController",
				data:{"pageIndex":pageIndex},
				success:function(result){
					showData(result);
					showPage(result);
				}
			});
		}
		
		
		//解析并显示数据
		function showData(Json){
			console.log(Json.list);
			$("tbody").empty();
			$.each(Json.list,function(index,item){
				var $tr = $("<tr>"+
						"<td>"+item.providerID+"</td>"+
						"<td>"+item.provider_name+"</td>"+
						"<td>"+item.provider_add+"</td>"+
						"<td>"+item.provider_tel+"</td>"+
						"<td>"+item.account+"</td>"+
						"<td>"+item.email+"</td>"+
							/* <c:if test="${user.sex==1}" >男</c:if>
							<c:if test="${user.sex==0}">女</c:if> */
		                "<td>"+
		                	"<input type='button' id='#edit' href='#edit' class='btn btn-success btn-small' data-toggle='modal' value='修改'/>"+
		              	  	" <a href='#' role='button' class='btn btn-warning btn-small del' >删除</a>"+
		                "</td></tr>");
				$("tbody").append($tr);
				$("tbody tr:odd").removeClass();
				$("tbody tr:even").addClass("info");
			});
			
			//Ajax 删除数据
			$(".del").click(function delData(){
				var id = $(this).parent().parent().find("td").eq(0).text();
				if(confirm("您确定要删除此供应商吗?")){
					$.ajax({
						type:"delete",
						url:"providerController/"+id,
						success:function(flag){
							if (flag == "error") {
								alert("有商品来源该供应商不能删除！");
							}else if(flag == "success"){
								alert("删除成功！");
								if (Json.list.length == 1) {
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
				$(".control-group").find("span").html("");
				$("#myModalLabel").html($(this).prop("value")+"类别");
				$(".control-group").eq(0).show();
				$update = $(this);
				$.each($(".form-horizontal .controls :input:not(:radio)"),
					function(index,item) {
						$(this).removeAttr("disabled");
						item.value = $update.parent().parent().find("td").eq(index).text();
					}
				);
			});
		}
		
		
		//解析并显示分页导航
		function showPage(Json){
			var pageCount = Json.pages;
			var pageIndex = Json.pageNum;
			var cgList = Json.cgList;
			$("#pages").empty();
			//上一页
			if(pageIndex-1>=1){
				$("#pages").append("<li><a title='"+(pageIndex-1)+"'>上一页</a></li>");
				
			}
			//当前页
			$.each(Json.navigatepageNums,function(index,item){
				if (item == pageIndex) {
					$("#pages").append("<li><a name='currPage' style='color:red' title='"+item+"'>"+item+"</a></li>");
				}else{
					$("#pages").append("<li><a name='currPage'  title='"+item+"'>"+item+"</a></li>");
				}
			});
			//下一页
			if(pageIndex+1<=pageCount){
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
			$(".form-horizontal .controls :input").removeAttr("disabled");
			$(".form-horizontal :input:not(:radio)").val("");
			$("#myModalLabel").html($(this).text()+"类别");
			$(".control-group").eq(0).find("input").val("0");
			$(".control-group").eq(0).hide();
			$(".control-group").find("span").html("");
		});
		//新增判断类别是否存在;
		$("#cgname").blur(function (){
		 	console.log($(this).val());
			$.ajax({
				type:"get",
				url:"ProviderServlet",
				data:{"name":$(this).val()},
				success:function(result){
					console.log(result);
					if (result == "error") {
						alert("该类别已存在");
					}
				}
			});	
		});
		
		//Ajax提交
		var flag;
		 $(".save").click(function saveData(){
			if($("#myModalLabel").html() == "修改类别"){
				$.each($(".form-horizontal .controls :input"),
					function(index,item){
						if(index > 0){
							if(item.value == $update.parent().parent().find("td").eq(index).text()){
								$(item).attr("disabled","disabled");
							}
						}
					}
				);
				if($("[disabled='disabled']").length == 5){
					alert("请修改后再提交");
					$(".form-horizontal .controls :input").removeAttr("disabled");
					return false;
				}else{	
					//Ajax 修改数据
					
					$.ajax({
						type:"put",
						url:"providerController",
						data:$("#myform").serialize(),
						success:function(result){	
							console.log("------: " + result);
							$.each(result,function(index,item){
								console.log(item);
								$(".control-group").find("span").eq(index-2).html(item).css({"color":"red","font-size":"12px"});
							});
							if (result == "success") {								
								alert("修改成功");
								getData(pages);
								$(".control-group").find("span").html("");
								$("#edit").modal("hide");
							}else{
								alert("修改失败");
							}
						}	
					});
				}
			}else{
					var name = $("#pvname").val();
					var addr = $("#pvaddr").val();
					var tel = $("#pvtel").val();
					var acc = $("#pvacc").val();
					var email = $("#pvemail").val();
					/* if (name == "") {
						alert("请输入供应商姓名");
						return;
					}
					else if (addr == "") {
						alert("请输入供应商地址");
						return;
					}
					else if (tel == "") {
						alert("请输入供应商联系方式");
						return;
					}
					else if (acc == "") {
						alert("请输入供应商卡号");
						return;
					}
					else if (email == "") {
						alert("请输入供应商邮箱");
						return;
					}
					else{ */
						//Ajax 新增数据
						$.ajax({
							type:"post",
							url:"providerController",
							data:$("#myform").serialize(),
							success:function(result){	
								console.log("------: " + result);
								$.each(result,function(index,item){
									console.log(item);
									$(".control-group").find("span").eq(index-2).html(item).css({"color":"red","font-size":"12px"});
								});
								if (result == "success") {								
									alert("新增成功");
									getData(pages);
									$(".control-group").find("span").html("");
									$("#edit").modal("hide");
								}else{
									alert("新增失败");
								}
							}	
						});
					}}/* } */
		);
	</script>
</html>