<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mimi.zfw.Constants"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../../inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
            <form:form method="POST" >
		<ul data-role="listview">
<li class="ui-field-contain"><label for="userName" class="label">名称:</label>
	<form:input path="userName" /></li>
<li class="ui-field-contain"><label for="password" class="label">密码:</label>
	<form:input path="password" /></li>
<li class="ui-field-contain"><input type="submit" value="提交" /></li>
<li class="ui-field-contain"><a href="javascript:history.go(-1)"
	class="ui-btn ui-btn-inline ui-icon-delete ui-btn-icon-left">取消</a></li>
		</ul>
            </form:form>

 <a href="<c:url value="/user"/>">返回</a>
</body>
</html>