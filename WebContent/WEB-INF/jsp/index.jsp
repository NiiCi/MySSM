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
	<script src="My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
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


				<%--  <div class="span10">
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
					<form action="UserServlet" method="post" class="form-horizontal">
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
					            <input id="putpwd" name="pwd" type="password" placeholder="">
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
									<button type="submit" class="btn btn-primary save">保存</button>
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
							<c:forEach items="${sessionScope.userList}" var="user">
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
	                        </c:forEach>
						</tbody>
					</table>
					<div class="pagination text-right">
					
						<ul>
							<li>
								<a href="#">上一页</a>
							</li>
							<li>
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">4</a>
							</li>
							<li>
								<a href="#">5</a>
							</li>
							<li>
								<a href="#">下一页</a>
							</li>
						</ul>
					</div>
				</div>
			</div> --%>
		</div> 
	</body>
	<script type="text/javascript">
		
		$(document).ready(function() {
			//循环遍历节点
		/* 	for(var i=0;i<5;i++){
				var $tr=$("<tr><td>"+(i+1)+"</td>"+
							"<td>TB -"+(i+1)+"</td>"+
							"<td>"+(i+1)+"/04/2012</td>"+
							"<td>Default</td>"+
	                        "<td>"+
	                        "<input type='button' id='#edit' href='#edit' class='btn btn-success btn-small' data-toggle='modal' value='修改'/>"+
	                        " <input type='button' class='btn btn-warning btn-small' value='删除'/></td></tr>");
				$("tbody").append($tr);
			} */
			
			$("tbody tr:odd").removeClass();
			$("tbody tr:even").addClass("info");
			
			//删除事件
			$("[value='删除']").click(function(){
				if (confirm("确定删除?")) {
					$(this).parent().parent().detach();
					$("tbody tr:odd").removeClass();
					$("tbody tr:even").addClass("info");
				}
			});
			
			//将表单td中的内容赋值到模态框中
			$("[value='修改']").click(function(){
				$(this).prop("value");
				$("#myModalLabel").html($(this).prop("value")+"产品");
				$(".control-group").eq(0).show();
				
				var $update = $(this);
				$.each($(".form-horizontal .controls :input:not(:radio)"),
					function(index,item) {
						item.value = $update.parent().parent().find("td").eq(index).text();
						console.log(item);
					}
				
				);
				console.log($update.parent().parent().find("td:eq(3)").text().trim());
				if($update.parent().parent().find("td").eq(3).text().trim() == "男"){
					$("[type='radio']").eq(0).prop("checked",true);
					$("[type='radio']").eq(1).prop("checked","");
				}else{
					$("[type='radio']").eq(0).prop("checked","");
					$("[type='radio']").eq(1).prop("checked",true);
				}
			});
		});
			
		
		$("[data-dismiss='modal']").click(function(){
			$(".form-horizontal :input:not(:checked)").val("");
		});
		
		
		$(".form-search a").click(function(){
			$(".form-horizontal :input:not(:checked)").val("");
			$(this).text();
			$("#myModalLabel").html($(this).text()+"产品");
			$(".control-group").eq(0).hide();
			console.log($("[type='radio']").val());
		});
		
	
		
	/* 	$(".radio inline").click(function(){
			$(this).children().prop("checked",true);
		});
		
		$(".save").click(function(){
			$(".modal-body form").submit();
		}); */
	
		
/* 		$("[type='submit']").click(function(){
			$("#edit").modal("hide");
			switch($("#myModalLabel").html()){
				case "新增产品":
					var $tr=$("tbody").find("tr:eq(0)").clone(true);
					$.each($(".form-horizontal :input"),
						function(index,item){
							$tr.find("td").get(index).innerHTML=$(item).val();
							if($(item).val().length > 0){
								$tr.appendTo("tbody");
							}
							 
						}
					);
					$("tbody tr:odd").removeClass();
					$("tbody tr:even").addClass("info");
				break;
				case "修改产品":
					var $update = $("[value='修改']");
					$.each($(".form-horizontal :input"),
					function(index,item) {
						$($update.get(index)).parent().parent().find("td").get(index).innerHTML=$(item).val();
					}
				);
				break;
			}
		
			
		}); */
	</script>
</html>