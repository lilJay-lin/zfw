<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
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
			<li>
				<a  href="${ctx }/mi/info">
					<i class="icon-hdd"></i>
					<span > 资讯管理</span>
				</a>
			</li>
		</ul>
	</li>
</ul>