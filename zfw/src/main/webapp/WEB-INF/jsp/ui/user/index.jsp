<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main whitebg">
		<div class="popShadow none" onclick="hideOrOpenNav()"></div>
		<header>
			<div class="left">
				<a href="${ctx }/index" class="hLogo"></a>
			</div>
			<div class="cent">个人中心</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<div class="newNav none">
			<div class="nav-box mt10">
				<div class="nav-tit">
					<a href="${ctx }/user"><span id="userinfo">个人中心</span></a> <strong>频道导航</strong>
				</div>
				<div class="nav-menu">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td width="24%"><a href="${ctx }/index">首页</a></td>
								<td width="24%"><a href="/xf/bj.html">新房</a></td>
								<td width="24%"><a href="/esf/bj/">二手房</a></td>
								<td width="24%"><a href="/zf/bj/">租房</a></td>
							</tr>
							<tr>
								<td><a href="/jiaju/bj.html">商铺</a></td>
								<td><a href="/zixun/bj.html#tt">写字楼</a></td>
								<td><a href="/jiaju/bj.html">仓库</a></td>
								<td><a href="/zixun/bj.html#tt">厂房</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<div class="personCenter">
			<div class="pc-banner">
				<shiro:guest>
				<div id="nologin">
					<div class="nologin pd10">
						<p class="f16">欢迎登录肇房网</p>
						<div class="btns">
							<a href="${ctx }/user/register">注册</a> <a href="${ctx }/user/login">登录</a>
						</div>
					</div>
				</div>
				</shiro:guest>
				<shiro:notAuthenticated>
					<div id="login">
						<a id="userhref" href="${ctx }/user/detail"
							class="logined">
							<dl>
								<dt>
									<div id="userphoto">
										<img
											src="${headImgUrl }"
											width="50" height="50px">
									</div>
								</dt>
								<dd>
									<p id="phone" class="f18"><shiro:principal></shiro:principal></p>
								</dd>
							</dl>
						</a>
					</div>
				</shiro:notAuthenticated>
				<shiro:authenticated>
					<div id="login">
						<a id="userhref" href="${ctx }/user/detail"
							class="logined">
							<dl>
								<dt>
									<div id="userphoto">
										<img
											src="${headImgUrl }"
											width="50" height="50px">
									</div>
								</dt>
								<dd>
									<p id="phone" class="f18"><shiro:principal></shiro:principal></p>
								</dd>
							</dl>
						</a>
					</div>
				</shiro:authenticated>
			</div>
			<div class="pc-content-list">
				<a class="pc-content-item" href="/user.d?m=myesfpage&amp;city=bj">
					<div class="bor">
						<span class="flol icon esf"></span> <span class="flol">我的二手房</span>
					</div>
				</a> <a class="pc-content-item" href="/user.d?m=myesfpage&amp;city=bj">
					<div class="bor">
						<span class="flol icon zf"></span> <span class="flol">我的租房</span>
					</div>
				</a> <a class="pc-content-item" href="/user.d?m=myesfpage&amp;city=bj">
					<div class="bor">
						<span class="flol icon sp"></span> <span class="flol">我的商铺</span>
					</div>
				</a> <a class="pc-content-item" href="/user.d?m=myesfpage&amp;city=bj">
					<div class="bor">
						<span class="flol icon xzl"></span> <span class="flol">我的写字楼</span>
					</div>
				</a> <a class="pc-content-item" href="/user.d?m=myesfpage&amp;city=bj">
					<div class="bor">
						<span class="flol icon ck"></span> <span class="flol">我的仓库</span>
					</div>
				</a> <a class="pc-content-item" href="/user.d?m=myesfpage&amp;city=bj">
					<div class="bor">
						<span class="flol icon cf"></span> <span class="flol">我的厂房</span>
					</div>
				</a>
			</div>
		</div>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
	function hideOrOpenNav() {
		var navObj = $(".newNav");
		var shadow = $(".popShadow");
		if (navObj.hasClass("none")) {
			navObj.removeClass("none");
			shadow.removeClass("none");
		} else {
			navObj.addClass("none");
			shadow.addClass("none");
		}
	}
</script>
</html>