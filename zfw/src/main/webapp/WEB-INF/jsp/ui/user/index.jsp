<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main whitebg">
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
		<%@include file="../inc/nav.jsp" %>
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
				<shiro:user>
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
				</shiro:user>
			</div>
			<div class="pc-content-list">
				<a class="pc-content-item" href="${ctx }/user/esf">
					<div class="bor">
						<span class="flol icon esf"></span> <span class="flol">我的二手房</span>
					</div>
				</a> <a class="pc-content-item" href="${ctx }/user/zf">
					<div class="bor">
						<span class="flol icon zf"></span> <span class="flol">我的租房</span>
					</div>
				</a> <a class="pc-content-item" href="${ctx }/user/sp">
					<div class="bor">
						<span class="flol icon sp"></span> <span class="flol">我的商铺</span>
					</div>
				</a> <a class="pc-content-item" href="${ctx }/user/xzl">
					<div class="bor">
						<span class="flol icon xzl"></span> <span class="flol">我的写字楼</span>
					</div>
				</a> <a class="pc-content-item" href="${ctx }/user/cfck">
					<div class="bor">
						<span class="flol icon ck"></span> <span class="flol">我的厂房仓库</span>
					</div>
				</a>
			</div>
		</div>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
</html>