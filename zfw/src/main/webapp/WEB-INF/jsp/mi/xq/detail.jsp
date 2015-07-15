<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../../inc/taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
         <%@include file="../inc/header.jsp" %>
		<title>后台管理</title>
	</head>
	<body>
		
		<!-- 头部导航条开始     -->
		<%@include file="../inc/nav.jsp" %>
		<!-- 头部导航条结束     -->
		<div class="clearfix"></div>
		
		<div class="container">
			<!-- 右边内容区域开始     -->
			<div class="main skin">
				<div class="content">
					<div class="box">
						<div class="box-hd" onclick="openCloseRCDetail('js-rc-detail-container')">
							<h2>小区详情</h2>
						</div>
						<%@include file="commonBody.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseRCDetail('js-rc-photos-container')">
							<h2>小区相册</h2>
						</div>
						<%@include file="photoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseRCDetail('js-rc-shh-container')">
							<h2>二手房</h2>
						</div>
						<%@include file="shhList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseRCDetail('js-rc-rh-container')">
							<h2>租房</h2>
						</div>
						<%@include file="rhList.jsp" %>
					</div>
				</div>
			</div>
			<!-- 右边内容区域结束     -->
			
			<!-- 左边侧边栏区域开始     -->
			<div class="slider skin">
				<div class="clearfix">&nbsp;</div>
				<div class="clearfix">&nbsp;</div>
				<%@include file="../inc/left.jsp" %>
			</div>
			
			<!-- 左边侧边栏区域结束     -->
		</div>
		
		<!-- 底部区域开始     -->
		<%@include file="../inc/footer.jsp" %>
		<!-- 底部区域结束     -->
	</body>
	<%@include file="commonBottom.jsp" %>
	<%@include file="deCommonBottom.jsp" %>
	<script>
		openCloseRCDetail("js-rc-detail-container");
		$("#submit").hide();
		$(".uploader").hide();
		$(".js-relation-select-box").hide();
		$(".js-edit-content").hide();
		inDetail = true;
	</script>
		<script type="text/x-handlebars" id="photo-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{contentUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${rcId}/xqphoto/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="shh-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td>{{name}}</td>
				<td>{{description}}</td>
				<td>{{priority}}</td>
				<td>{{totalPrice}}</td>
				{{#with updateDate}}
					<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${rcId}/esf/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="rh-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td>{{name}}</td>
				<td>{{description}}</td>
				<td>{{priority}}</td>
				<td>{{averagePrice}}</td>
				{{#with updateDate}}
					<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${rcId}/zf/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
</html>