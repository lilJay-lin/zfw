<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mimi.zfw.Constants"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>后台欢迎您 <shiro:principal/>!</h1><br/>
<h2>Hello World!</h2>
  		共${fn:length(page)}共用户<br/>
       			<c:forEach items="${page}" var="t" varStatus="status">
       				<div class="searchResultItem">
						<span class="sri1">
						${t.id}
						</span>
						<span class="sri2">
							<a href="javascript:void(0)">${t.name }</a>
						</span>
						<span class="sri6">电话：${t.phonenum }</span>
						<span class="sri3">邮箱：${t.email }</span>
						<span class="sri3">密码：${t.password }</span>
<%-- 						<span class="sri3">查看：${t.password }</span> --%>
<!-- 						编辑 删除 -->
					</div>
       			</c:forEach>
</body>
</html>
