<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
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

.input {
	width: 80px;
}

.smallinput {
	width: 40px;
	margin-top: 10px;
	line-height: 64px;
	text-align: center;
}

.smallselect {
	width: 90px;
	margin-top: 10px;
	line-height: 64px;
	text-align: center;
}

.liover {
	background-color: gray;
}

#detail {
	width: 800px;
	margin-left: -385px;
}

option {
	text-align: center;
}
</style>
</head>
<body>
	<%-- <%@ include file="session.jsp" %> --%>
	<div class="container">
		<%@include file="head.jsp" %>
		<div class="row">
			<%@ include file="left.jsp"%>


			<div class="span10">
				<form name="queryForm" class="form-search clearfix"
					style="margin-left: 50px;">
					<div class="pull-left" style="margin-right: 10px">顾客名:</div>
					<div class="pull-left" style="margin-right: 10px">雇员名:</div>
					<input class="input-medium search-query pull-left" type="text"
						placeholder="请输入订单时间" style="margin-right: 5px" /> <input
						id="queryCus" type="button"
						class="btn btn-default  querybycus pull-left"
						style="margin-right: 5px" value="查找" /> <a id="#edit"
						href="#edit" role="button" class="btn btn-primary pull-left"
						data-toggle="modal">新增</a>
				</form>


				<!--新增模态框 -->
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
									<label class="control-label" for="bid">编号:</label>
									<div class="controls">
										<input id="bid" name="id" type="text" placeholder=""
											readonly="readonly">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="pdname">顾客名:</label>
									<div class="controls">
										<input id="cusname" name="cusname" type="text">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="putpwd">雇员名:</label>
									<div class="controls">
										<input id="empname" name="empname" type="text">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="putpwd">购买日期:</label>
									<div class="controls">
										<input id="buydate" name="buydate" type="text"> <img
											onclick="WdatePicker({el:'buydate'})"
											style="cursor: pointer;"
											src="My97DatePicker/skin/datePicker.gif" width="20"
											height="26" />
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
				<!-- 订单详情模态框 -->
				<div id="detail" class="modal hide fade" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<a id="addDetail" role="button" class="btn  btn-primary "
						data-toggle="modal">添加</a>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 id="myModalLabel">订单详情</h4>
					</div>
					<div class="modal-body">
						<table data-toggle="table" id="detail_table" border="1"
							style="width: 100%; text-align: center;">
							<thead>
								<tr>
									<th>订单号</th>
									<th>商品名</th>
									<th>类别名</th>
									<th>供应商名</th>
									<th>数量</th>
									<th>折扣</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>



				<table class="table" data-toggle="table">
					<thead>
						<tr>
							<th>订单号</th>
							<th>订单时间</th>
							<th>顾客名</th>
							<th>雇员名</th>
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
	</div>
</body>

<script type="text/javascript">	
		var $update;
		var orderID;
		var order_date;
		var customerID = 0;
		var empID;
		var employeeID = 0;
		var date;
		$(document).ready(function(){
			getData(0,0,null,1);
			getEmps();
			getCus();
			getCates();
		});
		
		//获取下拉别表
	/* 	function getSelect(){
		;
		console.log("-----------div: " + $("[name='queryForm']").find("div").eq(0).text());
		
		
		
		} */
		
		//动态添加雇员下拉列表
		var $_emp_select;
		function getEmps(){
			$.ajax({
				type:"get",
				url:"employeeController",
				success:function(msg){
					console.log(msg);
					$_emp_select=$("<select id='emp'></select>");
					$_emp_select.addClass("input");
					$_emp_select.append("<option>请选择</option>");
					$.each(msg,function(index,item){
						$_emp_select.append($("<option value='"+item.empID+"'>"+item.emp_name+"</option>"));
					});
					$("[name='queryForm']").find("div").eq(1).append($_emp_select);
				}
			})
		}
		
		//动态添加顾客编号下拉列表
		var $_cus_select;
		function getCus(){
			$.ajax({
				type:"get",
				url:"customerController",
				success:function(msg){
					console.log(msg);
					$_cus_select = $("<select id='cus'></select>");
					$_cus_select.addClass("input");
					$_cus_select.append("<option>请选择</option>");
					$.each(msg,function(index,item){
						$_cus_select.append($("<option value='"+item.customerID+"'>"+item.customer_name+"</option>"))
					});
					$("[name='queryForm']").find("div").eq(0).append($_cus_select);
				}
			});
			
		}
		
		//动态添加类别下拉列表
		var $_cate_select;
		function getCates(){
			$.ajax({
				type:"get",
				url:"categoryController/getCate",
				success:function(msg){
					$_cate_select = $("<select id='cate' name='cate'></select>"); 
					$_cate_select.append("<option>请选择</option>");
					$.each(msg,function(index,item){
						$_cate_select.append($("<option value='"+item.categoryID+"' >"+item.category_name+"</option>"));
					});
					
					//根据分类查询对应商品
					$($_cate_select).change(function (){
						console.log($(this).val());
						var $select = $(this);
						if ($(this).val=="请选择") {
							return;
						}
						 $.ajax({
							type:"get",
							url:"productController/categoryID",
							data:{"categoryID":$(this).val()},
							success:function(msg){
								console.log(msg);
								var $_div = $("<div></div>");
								$.each(msg,function(index,item){
									console.log("下拉别表-----: " + item.cg.categoryID);
									$_div.append($("<li  title='"+item.cg.categoryID+"'>"+item.product_name+"</li>"));
								});
								$_div.find("li").click(function(){
									 $select.parent().prev().prev().find("input").val($(this).text());
									//根据商品名字查找供应商
								 	getProvider($(this));
								}).hover(function(){
									$(this).toggleClass("liover");
								})
								console.log("div-----: " +  $select.parent().prev().prev().html() + "");
								$select.parent().prev().prev().find("input").after($_div);
							} 
								
						 });
							
						}); 
				}
			});
		}
		
		//通过产品名 查询供应商下拉别表
		var $_pv_select;
		var prodID;
		function getProvider(pdname){
			$.ajax({
				type:"get",
				url:"productController/getPvByName",		
				data:{"product_name":pdname.text()},
				 success:function(msg){
					 console.log(msg);
					 pdname.parent().parent().parent().find("td").eq(7).find("select").empty();
					 pdname.parent().parent().parent().find("td").eq(7).find("select").append("<option>请选择</option>");
					$.each(msg,function(index,item){
						
						pdname.parent().parent().parent().find("td").eq(7).find("select").append("<option value='"+item.productID+"'>"+item.pv.provider_name+"</option>");
					})
					//删除自动补全的商品div
					pdname.parent().remove();
				 }
			})
		}
	
		//Ajax 查询数据
		var pages;
		function getData(customerID,employeeID,date,pageIndex){
			pages = pageIndex;
			$.ajax({
				type : "get",
				url : "orderController",
				data : {
					"cusID" : customerID,
					"empID" : employeeID,
					"date"  : date,
					"pageIndex" : pageIndex
				},
				success : function(result) {
					showData(result);
					showPage(result);
				}
			});

		}

		//模糊查询
		
		$("#queryCus").click(function() {
			customerID = $(this).parent().find("select").eq(0).val();
			employeeID = $(this).parent().find("select").eq(1).val();
			date = $(this).prev().val();
			console.log("customerID: "+customerID);
			console.log("employeeID: "+employeeID);
			console.log("date: "+date);
			console.log("pages: "+pages);
			getData(customerID, employeeID, date, pages);
		});

		//Ajax 显示订单详情
		function showDetail(orderID) {	
			$.ajax({		
				type : "get",	
				url : "orderDetailController",
				data : {
					"orderID" : orderID
				},
				success : function(msg) {
					$("tbody").eq(0).empty();
					$.each(msg,function(index, item) {		
						var $tr = $("<tr>"+ "<td class='hide'>"+ item.id + "</td>"				
								+ "<td>"+ item.orderID+ "</td>"
								+ "<td class='hide'>"+ item.pd.productID + "</td>"
								+ "<td>"+ item.pd.product_name+ "</td>"		
								+ "<td class='hide'>"+ item.cg.categoryID + "</td>"
								+ "<td>"+ item.cg.category_name+ "</td>"
								+ "<td class='hide'>"+ item.pv.providerID + "</td>"
								+ "<td>"+ item.pv.provider_name + "</td>"				
								+ "<td class='quan'>"+ item.quantity + "</td>"					
								+ "<td class='disc'>"+ item.discount + "</td>"			
								+ "<td>"
								+ "&nbsp;<input type='button' class='btn btn-success btn-small updateDetail' value='修改'/>"			
								+ "&nbsp;<a href='#' role='button' class='btn btn-warning btn-small delDetail' >删除</a>"
								+ "</td></tr>");
						$("tbody").eq(0).append($tr);				
					});	
					var quantity;
					var discount;
					var detailID;
					//修改订单详情开始
					$(".updateDetail").click(function() {					
						if ($(this).val() == "修改") {				
							$(this).val("确定");				
							detailID = $(this).parent().parent().find("td").eq(0).text();
							console.log("detailID: "+detailID);
							quantity = $(this).parent().parent().find("td").eq(8).text();					
							discount = $(this).parent().parent().find("td").eq(9).text();		
							$(this).parent().parent().find("td").eq(8).html(							
									"<input type='text' value='"+quantity+"'>").find("input").addClass("smallinput");
													
							$(this).parent().parent().find("td").eq(9).html(
									"<input type='text' value='"+discount+"'>").find("input").addClass("smallinput");				
						} else if ($(this).val("确定")) {				
							quantity = $(this).parent().parent().find("td").eq(8).find("input").val();		
							discount = $(this).parent().parent().find("td").eq(9).find("input").val();					
							var reg = /^\d{1,}$/;					
							var flag = reg.test(quantity);	 			
							if (quantity < 0) {						
								alert("产品数量必须大于0");						
								return;						
							} else if (discount > 1 || discount < 0) {					
								alert("折扣只能在0到1之间");					
								return;			
							} else if (!flag) {
								alert("产品数量必须是整数");					
								return;		
							} else {				
								if (confirm("您确定要修改此订单详情吗?")) {				
									$.ajax({							
										type : "put",					
										url : "orderDetailController",							
										data : {							
											"quantity" : quantity,				
											"discount" : discount,
											"id" : detailID
										},
										success : function(msg) {							
											if (msg == "success") {	
												$(this).val("修改");
												showDetail(orderID);	
												alert("修改成功");									
											} else {						
												alert("修改失败");																		
											}																	
										}															
									});														
								}	
								showDetail(orderID);													
							}																								
						}											
					});
							
					//修改订单详情结束

					//删除订单详情开始
							
					$(".delDetail").click(function() {								
						detailID = $(this).parent().parent().find("td").eq(0).text();										
						console.log("订单详情号:  " + detailID);									
						if (confirm("您确定要删除此订单详情吗?")) {											
							$.ajax({												
								type : "delete",												
								url : "orderDetailController/"+detailID,
								success : function(msg) {				
									if (msg == "success") {
										alert("删除成功");
									} else {
										alert("删除失败");		
									}
								},
								complete:function(){
									showDetail(orderID);
								}
							});					
						}
					});	
					//删除订单详情结束
				}
			})
		}
		//Ajax 添加订单详情
		$("#addDetail").click(function() {			
			var $trs = $("<tr>"			
					+ "<td class='hide'></td>"	
					+ "<td></td>"
					+ "<td class='hide'></td>"
					+ "<td><input type='text' /></td>"
					+ "<td class='hide'></td>"
					+ "<td></td>"
					+ "<td class='hide'></td>"								
					+ "<td><select id='pv' name ='pv'></select></td>"								
					+ "<td class='quan'><input type='text'></td>"								
					+ "<td class='disc'><input type='text'></td>"									
					+ "<td>"									
					+ "&nbsp;<input type='button' class='btn btn-success btn-small subDetail' value='提交'/>"									
					+ "&nbsp;<input type='button' class='btn btn-warning btn-small' value='重置'/>"									
					+ "</td></tr>");							
			$trs.find("td").eq(1).append(orderID);							
			$trs.find("td").eq(5).append($_cate_select);							
			$trs.find("td").find("select").addClass("smallselect");							
			$trs.find("td").find("input:not([type='button'])").addClass("smallinput");							
			$trclone = $trs.clone(true);
			$("tbody").eq(0).append($trclone);
			$trclone.find("#pv").change(function() {
				console.log($(this).val());								
				prodID = $(this).val();							
			});

			//键盘输入时选中相应的类别
							
			$trclone.find("td").eq(3).find("input").keyup(function() {	
				console.log($(this).val());
				var $_input = $(this);
				if ($(this).val() == "") {
					return;	
				}								
				$.ajax({
					type : "get",
					url : "productController/getPdByName",
					data:{"product_name":$(this).val()},
					success : function(msg) {	
						console.log(msg);
						$_input.next().remove();			
						var $_div = $("<div></div>");						
						$.each(msg,function(index,item) {														
							$_div.append(
									$("<li title='"+item.cg.categoryID+"'>"
									+ item.product_name + "</li>"));											
						});									
						$_div.find("li").click(function() {													
							$_input.val($(this).text());														
							//自动识别分类
							var cateID = $(this).attr("title");													
							console.log("cateID:  " + cateID);														
							$.each(	$_input.parent().next().next().find("option"),																
									function(index,item) {																	
								if (item.value == cateID) {																
									$(item).prop("selected","selected");																	
								}																	
							});													
							//根据产品名查找相应的供应商													
							getProvider($(this));													
						}).hover(function() {
							$(this).toggleClass("liover");										
						});								
						$_input.after($_div);								
					}							
				});					
			});
		
			//Ajax 提交详情			
			$(".subDetail").click(function() {
										
				var quan = $(this).parent().prev().prev().find("input").val();						
				var disc = $(this).parent().prev().find("input").val();										
				console.log(quan);									
				console.log(disc);										
				$.ajax({						
					type : "post",					
					url : "orderDetailController",				
					data : {					
						"pd.productID" : prodID,				
						"orderID" : orderID,			
						"quantity" : quan,	
						"discount" : disc				
					},			
					success : function(msg) {			
						if (msg == "success") {			
							alert("新增成功");				
						} else {				
							alert("新增失败");			
						}			
					},
					complete:function(){
						showDetail(orderID);
					}
				});				
						
			});	
		});
		
		var Json;
		//解析并显示数据
		function showData(result) {
			Json = result;
			console.log(Json);
			$("tbody").eq(1).empty();
			$.each(Json.list,function(index, item) {			
				var $tr = $("<tr>"				
						+ "<td>"+ item.orderID + "</td>"			
						+ "<td>"+ item.order_date + "</td>"
						+ "<td>"+ item.cus.customer_name + "</td>"			
						+ "<td>"+ item.emp.emp_name + "</td>"		
						+ "<td>"									
						+ "<input type='button'  href='#detail' class='btn btn-info btn-small detail' data-toggle='modal' value='详情'/>"							
						+ "&nbsp;<input type='button' class='btn btn-success btn-small update'  value='修改'/>"								
						+ "&nbsp;<a href='#' role='button' class='btn btn-warning btn-small del' >删除</a>"									
						+ "</td></tr>");					
				$("tbody").eq(1).append($tr);				
				$("tbody:eq(1) tr:odd").removeClass();					
				$("tbody:eq(1) tr:even").addClass("info");
			});

			//Ajax 显示订单详情数据
			$(".detail").click(function() {
				orderID = $(this).parent().parent().find("td").eq(0).text();
				showDetail(orderID);
			});

			//Ajax 删除数据
			$(".del").click(function delData() {
				var id = $(this).parent().parent().find("td").eq(0).text();
				if (confirm("您确定要删除此订单吗?")) {
					$.ajax({
						type : "delete",
						url : "orderController/"+id,
						success : function(flag) {
							if (flag == "error") {
								alert("删除失败！");
							} else if (flag == "success") {
								alert("删除成功！");
								console.log(Json);
								if (Json.list.length == 1) {
									getData(customerID,employeeID,date, pages - 1);
								} else {
									getData(customerID,employeeID,date, pages);
								}
							}
						}
					});
				}
			});

			//将表单td中的内容赋值到模态框中
			$(".update").click(function() {
								$update = $(this);
								id = $(this).parent().parent().find("td").eq(0).text();
								order_date = $(this).parent().parent().find("td").eq(1).text();
								customerName = $(this).parent().parent().find("td").eq(2).text();
								empName = $(this).parent().parent().find("td").eq(3).text();
								if ($(this).val() == "修改") {
									/* ($(this).val()); */

									$(this).val("确定");
									$(this).parent().parent().find("td").eq(1).html("").append(
													"<input class='input' name='indate' type='text' onclick='WdatePicker()' value='"
															+ order_date
															+ "'/>"
											// "<img onclick='WdatePicker({el:"+'incomedate'+"})' style='cursor: pointer;' src='My97DatePicker/skin/datePicker.gif' width='20' height='26' align='absmiddle'/>"
											);
									$(this).parent().parent().find("td").eq(2).html("").append($_cus_select);
									$(this).parent().parent().find("td").eq(3).html("").append($_emp_select);
									$.each($("#cus option"), function(index,item) {
										if (customerName == item.innerText) {
											$("#cus option").eq(index).attr(
													"selected", "selected");
										}
									});
									
									$.each($("#emp option"), function(index,
											item) {
										if (empName == item.innerText) {
											$("#emp option").eq(index).attr(
													"selected", "selected");
										}
									});
								} else if ($(this).val("确定")) {
									order_date = $(this).parent().parent()
											.find("td").eq(1).find("input")
											.val();
									customID = $(this).parent().parent()
											.find("td").find(
													"#cus option:selected")
											.val();
									customerName = $(this).parent().parent()
											.find("td").find(
													"#cus option:selected")
											.text();
									empID = $(this).parent().parent()
											.find("td").find(
													"#emp option:selected")
											.val();
									empName = $(this).parent().parent().find(
											"td").find("#emp option:selected")
											.text();
									if (confirm("您确定要修改此订单吗?")) {
										$.ajax({
											type : "put",
											url : "orderController",
											data : {
												"odID" : id,
												"odDate" : order_date,
												"cusID" : customID,
												"cusName" : customerName,
												"empID" : empID,
												"empName" : empName
											},
											success : function(msg) {
												if (msg == "success") {										
													alert("修改成功");
													$(this).val("修改");
												} else {
													alert("修改失败");
												}
											},
											complete:function(){
												getData(customerID,employeeID,date,pages);
											}
										});
									}
									getData(customerID,employeeID,date,pages);
									console.log("-------page: " + pages);
								}
							});
		}

		//解析并显示分页导航
		function showPage(result) {
			$("#pages").empty();
			var pageCount = Json.pages;
			var pageIndex = Json.pageNum;
			console.log("页数:   " + pageCount);
			console.log("页码:   " + pageIndex);
			//上一页
			if (pageIndex - 1 >= 1) {
				$("#pages").append("<li><a title='" + (pageIndex - 1) + "'>上一页</a></li>");
			}
			//导航页
			$.each(Json.navigatepageNums, function(index, item) {
				if (item == pageIndex) {
					$("#pages").append("<li><a name='currPage' style='color:red' title='"+item+"'>"	
							+ item + "</a></li>");
				} else {
					$("#pages").append("<li><a name='currPage'  title='"+item+"'>" + item		
							+ "</a></li>");
				}
			});

			//下一页
			if (pageIndex + 1 <= pageCount) {
				$("#pages").append(
						"<li><a title='" + (pageIndex + 1) + "'>下一页</a></li>");
			}
			$("#pages").find("a").click(function() {
				getData(customerID,employeeID,date, $(this).attr("title"));
			});
		}

		//点击关闭,清空模态框中的内容
		$("[data-dismiss='modal']").click(function() {
			$(".form-horizontal :input:not(:radio)").val("");
		});

		//点击新增
		$(".form-search a").click(function() {
			$(".form-horizontal :input:not(:radio)").val("");
			$("#myModalLabel").html($(this).text() + "用户");
			$(".control-group").eq(0).hide();
		});
		//新增判断顾客名是否存在;
		$("#pdname").blur(function() {
			/* 	($(this).val()); */
			$.ajax({
				type : "get",
				url : "OrderServlet",
				data : {
					"name" : $(this).val()
				},
				success : function(result) {
					if (result == "error") {
						alert("该用户已存在");
					}
				}
			});
		});
		var flagcus;
		var flagemp;
		var cusid;
		$("#cusname").blur(function() {
			$.ajax({
				type : "get",
				url : "customerController/"+$(this).val(),
				success : function(msg) {
					console.log(msg);
					if (msg > 0) {
						cusid = msg;
						flagcus = true;
						console.log("cusid:   " + cusid);
					} else {
						flagcus = false;
						alert("该客户不存在,青重新输入!");
					}
				}
			});
		});
		var empid;
		$("#empname").blur(function() {
			$.ajax({
				type :"get",
				url : "employeeController/"+$(this).val(),
				success : function(msg) {
					console.log(msg);
					if (msg > 0) {
						empid = msg;
						flagemp = true;
						console.log("empid:   " + empid);
					} else {
						flagemp = false;
						alert("该雇员不存在,青重新输入!");
					}
				}
			});
		});
		//Ajax提交

		$(".save").click(function saveData() {
			var cusname = $("#cusname").val();
			var empname = $("#empname").val();
			var buydate = $("#buydate").val();

			if (cusname == "") {
				alert("请输入顾客名");
				return;
			} else if (empname == "") {
				alert("请输入雇员名");
				return;
			} else if (!flagcus) {
				alert("该顾客不存在,无法提交!");
				return;
			} else if (!flagemp) {
				alert("该雇员不存在,无法提交!");
				$("#edit").modal("hide");
				return;
			} else {
				$(".form-horizontal .controls :input").removeAttr("disabled");
				//Ajax 新增数据
				$.ajax({
					type : "post",
					url : "orderController",
					data : {
						"odDate" : buydate,
						"cusID" : cusid,
						"empID" : empid
					},
					success : function(result) {
						if (result == "success") {
							alert("新增成功");
							getData(customerID,employeeID,date,pages);
						} else {
							alert("新增失败");
						}
						$("#edit").modal("hide");
					}
				});
			}
		});
	</script>
</html>