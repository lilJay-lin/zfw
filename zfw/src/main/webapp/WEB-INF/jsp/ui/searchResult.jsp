<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="inc/header.jsp" %>
</head>
<body>
	<div class="main whitebg">
		<header>
			<div class="left">
				<a href="${ctx }/index" class="hLogo"></a>
			</div>
			<div class="cent">搜索结果</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="inc/nav.jsp" %>

		<form class="search0620_new flexbox" name="wapSearchForm" action=""
			onsubmit="return false;" method="get" autocomplete="off">
			<div class="searbox_new">
				<div class="ipt" >
					<input id="keyWord" type="search" name="keyWord" value=""
						placeholder="楼盘名/户型名/地名等" autocomplete="off"><a
						href="javascript:void(0);" class="off" style="display: none;"></a>
				</div>
				<a href="javascript:search();" class="btn" rel="nofollow"><i></i></a>
			</div>
		</form>
		<div class="searList"  >
			<ul>
				<div class="searTips">共663个搜索结果，请选择类别查看</div>
				<li><a href="javascript:;"><span class="flor f999">1条</span><span
						class="searchListName" data-ywtype="爱上,新房,住宅,,,,">新房</span></a></li>
				<li><a href="javascript:;"><span class="flor f999">约91条</span><span
						class="searchListName" data-ywtype="爱上,出售,住宅,,,,">二手房</span></a></li>
				<li><a href="javascript:;"><span class="flor f999">约11条</span><span
						class="searchListName" data-ywtype="爱上,出租,住宅,,,,">租房</span></a></li>
				<li><a href="javascript:;"><span class="flor f999">7条</span><span
						class="searchListName" data-ywtype="爱上,出售,别墅,,,,">别墅出售</span></a></li>
			</ul>
		</div>
	</div>
</body>
<script>
	function search(){
		top.location = "${ctx}/"+$("#keyWord").val()+"/search";
	}
</script>
</html>