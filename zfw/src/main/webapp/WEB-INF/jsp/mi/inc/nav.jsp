<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
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
						<img src="${headImgUrl}" alt="Avatar">
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