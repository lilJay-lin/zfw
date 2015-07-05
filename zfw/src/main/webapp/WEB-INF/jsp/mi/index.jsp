<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../inc/taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
         <%@include file="inc/header.jsp" %>
         <title>后台管理</title>
	</head>
	<body>
		
		<!-- 头部导航条开始     -->
		<div class="navbar skin">
			<a class="navbar-brand skin">后台管理</a>
			<div class="navbar-inner">
				<ul class="nav">
					<li ><a href="#" class="btn"><i class="icon-tasks"></i></a></li>
					<li><a href="#" class="btn"><i class="icon-envelope"></i></a></li>
					<li><a href="#" class="btn"><i class="icon-wrench"></i></a></li>
					<li>
						<a href="#">
							<div class="nav-avatar">
								<img src="${headImgUrl }" alt="Avatar">
							</div>
							<div class="nav-user">
								<p>Welcome!</p>
								<p> <shiro:principal/></p>
							</div>
						</a> 
					</li>
				</ul>
			</div>
		</div>
		<!-- 头部导航条结束     -->
		<div class="clearfix"></div>
		
		<div class="container">
			<!-- 右边内容区域开始     -->
			<div class="main skin">
				<div class="content">
					<div class="box">
											
					</div>
				</div>
			</div>
			<!-- 右边内容区域结束     -->
			
			
			
			<!-- 左边侧边栏区域开始     -->
			<div class="slider skin">
				<div class="clearfix">&nbsp</div>
				<div class="clearfix">&nbsp</div>
				<%@include file="inc/left.jsp" %>
			</div>
			
			<!-- 左边侧边栏区域结束     -->
		</div>
		<!-- 底部区域开始     -->
		<div class="footer">
			<p>@copyright-------------------</p>
		</div>
	</body>
</html>
