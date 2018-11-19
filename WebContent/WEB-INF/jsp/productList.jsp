<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
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
			 select{
				width:165px;
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
						<shiro:hasRole name="productAdmin">
							<a id="#edit" href="#edit" role="button" class="btn btn-primary" data-toggle="modal">新增</a>
						</shiro:hasRole>
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
					          <label class="control-label" for="pdid">编号:</label>
					          <div class="controls">
					            <input id="pdid" name="productID" type="text" placeholder="" readonly="readonly">
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="pdname">产品名:</label>
					          <div class="controls">
					            <input id="pdname" name="product_name" type="text" placeholder="">
					            <span></span>
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="incomeprice">进价:</label>
					          <div class="controls">
					            <input id="incomeprice" name="income_price"  type="text" placeholder="">
					            <span></span>
					          </div>
					        </div>
   							<div class="control-group">
					          <label class="control-label" for="providerid">供应商名:</label>
					          <div class="controls pv">
					           <!--  <input id="providerid" name="pvid"  type="text" placeholder="">
					            <p class="help-block"></p> -->
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="salesprice">售价:</label>
					          <div class="controls">
					            <input id="salesprice" name="sales_price"  type="text" placeholder="">
					           <span></span>
					          </div>
					        </div>
							 <div class="control-group">
					          <label class="control-label" for="quantity">数量:</label>
					          <div class="controls">
					            <input id="quantity" name="quantity"  type="text" placeholder="">
					           <span></span>
					          </div>
					        </div>
							<div class="control-group ">
					          <label class="control-label" for="categoryid">类别名:</label>
					          <div class="controls cates">
					          <!--   <input id="categoryid" name="cateid"  type="text" placeholder=""> -->
					            
					         
					          </div>
					        </div>
					        <div class="control-group">
					          <label class="control-label" for="incomedate">采购日期:</label>
					          <div class="controls">
					           <input  id="incomedate" name="income_time" type="text">
					           <img onclick="WdatePicker({el:'incomedate'})" style="cursor: pointer;" src="My97DatePicker/skin/datePicker.gif" width="20" height="26" />
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
									产品名
								</th>
								<th>
									售价
								</th>
								<th>
									数量
								</th>
								<th>
									采购时间
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
						
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
			getProvider();
			getCates();
		});
		//动态添加类别下拉列表
		var $_cate_select;
		function getCates(){
			$.ajax({
				type:"get",
				url:"categoryController/getCate",
				success:function(msg){
					console.log(msg);
					$_cate_select = $("<select id='cate' name='cg.categoryID'></select>");
					$_cate_select.append("<option>请选择</option>");
					$.each(msg,function(index,item){
						$_cate_select.append($("<option value='"+item.categoryID+"'>"+item.category_name+"</option>"))
					})
				}
			})
		}
		
		//动态添加供应商下拉列表
		var $_pv_select;
		function getProvider(){
			$.ajax({
				type:"get",
				url:"providerController/getProvider",
				success:function(msg){
					$_pv_select = $("<select id='pv' name='pv.providerID'></select>");
					$_pv_select.append("<option>请选择</option>");
					$.each(msg,function(index,item){
						$_pv_select.append($("<option value='"+item.providerID+"'>"+item.provider_name+"</option>"));
					})
				}
			})
		}
		//Ajax 查询数据
		var pages;
		function getData(pageIndex){
			pages = pageIndex;
			$.ajax({
				type:"get",
				url:"productController",
				data:{"pageIndex":pageIndex},
				success:function(result){
					showData(result);
					showPage(result);
				}
			});
		}
		
		var Json;
		//解析并显示数据
		function showData(Json){
			console.log(Json.list);
			$("tbody").empty();
			$.each(Json.list,function(index,item){
				var $tr = $("<tr>"+
						"<td>"+item.productID+"</td>"+
						"<td>"+item.product_name+"</td>"+
						"<td class='hide'>"+item.income_price+"</td>"+
						"<td class='hide'>"+item.pv.providerID+"</td>"+
						"<td>"+item.sales_price+"</td>"+
						"<td>"+item.quantity+"</td>"+
						"<td class='hide'>"+item.cg.categoryID+"</td>"+
						"<td>"+item.income_time+"</td>"+
							
		                "<td>"+
		                	/* "<input type='button' id='#detail' href='#detail' class='btn btn-info btn-small' data-toggle='modal' value='详情'/>"+ */
		                	"&nbsp;<shiro:hasPermission name='products:update'><input type='button' id='#edit' href='#edit' class='btn btn-success btn-small update' data-toggle='modal' value='修改'/></shiro:hasPermission>"+
		              	  	"&nbsp;<shiro:hasPermission name='products:delete'><a href='#' role='button' class='btn btn-warning btn-small del' >删除</a></shiro:hasPermission>"+
		                "</td></tr>");
				$("tbody").append($tr);
				$("tbody tr:odd").removeClass();
				$("tbody tr:even").addClass("info");
			});
			
			//Ajax 删除数据
			$(".del").click(function delData(){
				var id = $(this).parent().parent().find("td").eq(0).text();
				if(confirm("您确定要删除此用户吗?")){
					$.ajax({
						type:"delete",
						url:"productController/"+id,
						success:function(flag){
							console.log(flag);	
							if(flag == "success"){
								alert("删除成功！");
								if (Json.list.length == 1) {
									getData(pages - 1);
								}else{
									getData(pages);
								}
							}else {
								alert("该产品存在外键关联不能删除！");
							}
						}
					});
				}
			});
			
			//将表单td中的内容赋值到模态框中
			$(".update").click(function (){
				$(".control-group").find("span").html("");
			/* 	if ($(this).val() == "修改") {
					console.log($(this).val());
					$(this).val("确定");
				}else {
					$(this).val("修改");
				} */
				$(".pv").append($_pv_select);
				$(".cates").append($_cate_select);
				
				$("#myModalLabel").html($(this).prop("value")+"产品");
				$(".control-group").eq(0).show();
				
				$update = $(this);
				console.log($update.parent().parent().find("td"));
				var $cate;
				$cate = $(".cates option:selected").val();
				console.log($cate);
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
			$("#myModalLabel").html($(this).text()+"产品");
			$(".control-group").eq(0).find("input").val("0");
			$(".control-group").eq(0).hide();
			$(".pv").append($_pv_select);
			$(".cates").append($_cate_select);
			$(".control-group").find("span").html("");
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
			if($("#myModalLabel").html() == "修改产品"){
				$.each($(".form-horizontal .controls :input:not(:radio)"),
					function(index,item){
						if(index > 0){
							
							if(item.value == $update.parent().parent().find("td").eq(index).text()){
								
								$(item).attr("disabled","disabled");
								console.log($(item));
							}
						}
					}
				);
				if($("[disabled='disabled']").length == 7){
					alert("请修改后再提交");
					$(".form-horizontal .controls :input").removeAttr("disabled");
					return false;
				}else{	
					//Ajax 修改数据
					/* $(".form-horizontal .controls :input").removeAttr("disabled"); */
					$.ajax({
						type:"put",
						url:"productController",
						data:$("#myform").serialize(),
						success:function(result){	
							console.log(result);
							$.each(result,function(index,item){
								console.log(item);
								$(".control-group").find("span").eq(index-2).html(item).css({"color":"red","font-size":"12px"});
							});
							if (result == "success") {								
								alert("修改成功");
								getData(pages);
								$("#edit").modal("hide");
							}else{
								alert("修改失败");
							}
						}	
					});
				}
			}else{
					var name = $("#pdname").val();
					var incomeprice  = $("#incomeprice").val();
					var pv  = $("#pv").text();
					var salesprice   = $("#salesprice ").val();
					var quantity   = $("#quantity").val();
					var cate  = $("#cate:selected").val();
					var quantity  = $("#quantity").val();
					
					/* if (name == "") {
						alert("请输入用户名");
						return;
					}
					else if (pwd == "") {
						alert("请输入密码");
						return;
					}else{ */
						//Ajax 新增数据
						$.ajax({
							type:"post",
							url:"productController",
							data:$("#myform").serialize(),
							success:function(result){
								console.log(result);
								$.each(result,function(index,item){
									console.log(item);
									$(".control-group").find("span").eq(index-2).html(item).css({"color":"red","font-size":"12px"});
								});
								if (result == "success") {								
									alert("新增成功");
									getData(pages);
									$("#edit").modal("hide");
								}else{
									alert("新增失败");
								}
							}	
						});
						}
		 			/* } */
		});
	
		

	</script>
</html>