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
					<input id="keyWord" type="search" name="keyWord" value="${keyWord }"
						placeholder="楼盘名/户型名/地名等" autocomplete="off"><a
						href="javascript:void(0);" class="off" style="display: none;"></a>
				</div>
				<a href="javascript:search();" class="btn" rel="nofollow"><i></i></a>
			</div>
		</form>
		<div class="searList"  >
			<ul>
				<div class="searTips">共${totalNum }个搜索结果，请选择类别查看</div>
				<li><a href="${ctx }/xf/${keyWord }------楼盘-default/search"><span class="flor f999">${xfNum }条</span><span
						class="searchListName" >新房</span></a></li>
				<li><a href="${ctx }/esf/-${keyWord }-----default/search"><span class="flor f999">${esfNum }条</span><span
						class="searchListName" data-ywtype="爱上,出售,住宅,,,,">二手房</span></a></li>
				<li><a href="${ctx }/zf/-${keyWord }-----default/search"><span class="flor f999">${zfNum }条</span><span
						class="searchListName" >租房</span></a></li>
				<li><a href="${ctx }/sp/${keyWord }-------default/search"><span class="flor f999">${spNum }条</span><span
						class="searchListName" >商铺</span></a></li>
				<li><a href="${ctx }/xzl/${keyWord }-------default/search"><span class="flor f999">${xzlNum }条</span><span
						class="searchListName" >写字楼</span></a></li>
				<li><a href="${ctx }/cfck/${keyWord }-------default/search"><span class="flor f999">${cfckNum }条</span><span
						class="searchListName" >厂房仓库</span></a></li>
			</ul>
		</div>
	</div>
</body>
<script>
	function search(){
		top.location = "${ctx}/"+$("#keyWord").val()+"-/search";
	}
</script>
</html>