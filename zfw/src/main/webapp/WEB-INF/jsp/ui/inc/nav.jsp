<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div class="popShadow none" onclick="hideOrOpenNav()"></div>
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
								<td width="24%"><a href="${ctx }/xf">新房</a></td>
								<td width="24%"><a href="${ctx }/esf">二手房</a></td>
								<td width="24%"><a href="${ctx }/zf">租房</a></td>
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