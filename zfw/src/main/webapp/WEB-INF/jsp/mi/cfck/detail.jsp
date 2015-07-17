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
						<div class="box-hd" onclick="openCloseDetail('js-warehouse-detail-container')">
							<h2>厂房仓库信息</h2>
						</div>
						<%@include file="commonBody.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseDetail('js-warehouse-image-container')" >
							<h2>厂房仓库图片信息</h2>
						</div>
						<%@include file="photoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseDetail('js-warehouse-panos-container')">
							<h2>厂房仓库全景信息</h2>
						</div>
						<%@include file="panoList.jsp" %>
					</div>
					<div class="form-actions">
			  			<button type="reset" class="btn cancle" >返回</button>
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
	<script type="text/x-handlebars" id="photo-template">
		{{#each this}}
		<tr>
			<td><img src="{{contentUrl}}" style="width:100px"></td>
			<td>{{name}}</td>
			<td>{{description}}</td>
			<td>
				<a class="btn btn-info" href="${ctx}/mi/${warehouseId}/cfckphoto/{{id}}/detail">
					<i class="icon-zoom-in "></i>                                            
				</a>
			</td>
		</tr>
		{{/each}}
	</script>
	<script type="text/x-handlebars" id="pano-template">
		{{#each this}}
		<tr>
			<td><img src="{{preImageUrl}}" style="width:100px"></td>
			<td>{{name}}</td>
			<td>{{contentUrl}}</td>
			<td>{{description}}</td>
			<td>
				<a class="btn btn-info" href="${ctx}/mi/${warehouseId}/cfckpano/{{id}}/detail">
					<i class="icon-zoom-in "></i>                                            
				</a>
			</td>
		</tr>
		{{/each}}
	
	</script>		
	</body>
	<%@include file="commonBottom.jsp" %>
	<%@include file="aeCommonBottom.jsp" %>
	<%@include file="deCommonBottom.jsp" %>
	<script>
	inDetail = true;
	openCloseDetail('js-warehouse-detail-container');
	$(".js-not-detail").hide();
	$(".js-detail-only").show();
	</script>
</html>