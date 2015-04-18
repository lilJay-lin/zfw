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
user

       			<a href="<c:url value="/index"/>">首页</a>
       			<a href="<c:url value="/user/login"/>">登录</a>
       			<a href="<c:url value="/user/register"/>">注册</a>
       			<a href="<c:url value="/user/logout"/>">退出</a>
       			<a href="<c:url value="/user/view"/>">view</a>
       			<a href="<c:url value="/user/add"/>">add</a>
       			<a href="<c:url value="/user/del"/>">del</a>
       			<a href="<c:url value="/user/update"/>">update</a>
       			<shiro:hasPermission name="user:add"><br/>you can add</shiro:hasPermission>
       			<shiro:hasPermission name="user:view"><br/>you can view</shiro:hasPermission>
       			<shiro:hasPermission name="user"><br/>you can user</shiro:hasPermission>
</body>
</html>