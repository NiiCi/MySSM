<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	 		Object isloginmsg = session.getAttribute("isLogin");
			if(isloginmsg==null||!isloginmsg.toString().equals("true")){
				response.sendRedirect("login.jsp");
			}
%> --%>