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
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-detail-container')">
							<h2>编辑楼盘</h2>
						</div>
						<%@include file="commonBody.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-ht-container')">
							<h2>管理楼盘户型</h2>
						</div>
						<%@include file="htList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-photos-container')">
							<h2>管理楼盘相册</h2>
						</div>
						<%@include file="photoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-panos-container')">
							<h2>管理楼盘全景</h2>
						</div>
						<%@include file="panoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-videos-container')">
							<h2>管理楼盘视频</h2>
						</div>
						<%@include file="videoList.jsp" %>
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
		$(".form-actions").show();
	</script>
		<script type="text/x-handlebars" id="pano-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{preImageUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{contentUrl}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfpano/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfpano/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delPano(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="photo-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{contentUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{type}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfphoto/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfphoto/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delPhoto(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="video-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{preImageUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{contentUrl}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfvideo/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfvideo/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delVideo(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="ht-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td>{{name}}</td>
				<td>{{description}}</td>
				<td>{{priority}}</td>
				<td>{{saleStatus}}</td>
				<td>{{averagePrice}}</td>
				{{#with updateDate}}
					<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/hx/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/hx/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delHt(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
</html>