<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<div class="navbar skin">
	<a class="navbar-brand skin">后台管理</a>
	<div class="navbar-inner">
		<ul class="nav">
<%-- 			<li><a href="${ctx}/mi/user/${miCurrentUserId}/person"class="btn btn-primary"><i class="icon-wrench"></i></a></li> --%>
			<li ><a href="${ctx}/mi/user/logout" onclick="logout()" class="btn btn-primary"><i class="icon-unlock"></i></a></li>
			<li>
				<a href="${ctx}/mi/user/${miCurrentUserId}/person">
					<div class="nav-avatar">
						<img src="${miCurrentHeadImgUrl}" id="mi-cur-headImageUrl" alt="Avatar">
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
<script type="text/javascript">
	function logout(){
		util.cookie("miPassword",null,-1);
	}
</script>