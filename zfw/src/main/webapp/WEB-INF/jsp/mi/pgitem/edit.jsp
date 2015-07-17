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
						<div class="box-hd" onclick="openCloseAIDetail('js-ai-detail-container')">
							<h2>编辑评估项</h2>
						</div>
						<%@include file="commonBody.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseAIDetail('js-ai-ap-container')">
							<h2>管理评估值</h2>
						</div>
						<%@include file="apList.jsp" %>
					</div>
					<div class="form-actions">
					  <button type="reset" class="btn cancle">返回</button>
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
	<%@include file="aeCommonBottom.jsp" %>
	<%@include file="deCommonBottom.jsp" %>
	<script>
		inEdit = true;
		$(".js-edit-only").show();
	</script>
		<script type="text/x-handlebars" id="ap-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td>{{name}}</td>
				<td>{{value}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${aiId}/pgparam/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="${ctx}/mi/${aiId}/pgparam/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delPano(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
</html>