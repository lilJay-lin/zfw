<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../inc/taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/font-awesome/css/font-awesome.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/mi.css"/>
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
				<ul class="slider-nav skin">
					<li class="submenu active">
						<a href="javascript:void(0)">
							<i class="icon-key"></i>
							<span class="hidden-tablet"> 系统管理</span>
							<span class="label">2</span>
						</a>
						<ul class="subNav" >
							<li>
								<a  href="${ctx}/mi/users">
									<i class="icon-user"></i>
									<span > 用户管理</span>
								</a>
							</li>
							<li>
								<a  href="/roles">
									<i class="icon-hdd"></i>
									<span > 角色管理</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			
			<!-- 左边侧边栏区域结束     -->
		</div>
		
		<!-- 底部区域开始     -->
		<div class="footer">
			<p>@copyright-------------------</p>
		</div>
	</body>
	<script src="${ctx}/assets/js/jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctx}/assets/js/style.js" type="text/javascript" charset="utf-8"></script>

</html>