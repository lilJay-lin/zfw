<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mimi.zfw.Constants"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>后台欢迎您 <shiro:principal/>!</h1><br/>
<h2>Hello World!</h2>
				${sn }
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
					</div>
       			</c:forEach>
       			<a href="<c:url value="/user/"/>">个人中心</a>
       			<img src="${pageContext.request.contextPath}/assets/img/zhy1_02.jpg"/>
				<script src="${pageContext.request.contextPath}/assets/js/testJs.js"></script>
       			<a href="${pageContext.request.contextPath}/assets/js/testJs.js">${pageContext.request.contextPath}/assets/js/testJs.js</a>
       			<form name="upload" action="<c:url value="/upload"/>" enctype="multipart/form-data" method="post">
				  <input type="file" name="theFile" /> <input type="submit" value="上传文件" />
				 </form>
</body>
</html>
