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
						<div class="box-hd">
							<h2>广告管理</h2>
						</div>
						<div class="box-cnt">
							<div class="datatable" id="adList">
								<div class="datatabls-filter">
									<!--搜索：-->
									<input type="text" id="searchbyname" />
									<select id="location">
										<option value="">位置不限</option>
										<option value="首页顶端">首页顶端</option>
										<option value="首页中间小型">首页中间小型</option>
										<option value="首页中间大型">首页中间大型</option>
										<option value="资讯页">资讯页</option>
									</select>
									<input type="button" class="btn" id="search-ad" value="搜索" />
								</div>
								<table class="datatable-table">
									<thead>
										<th>预览</th>
										<th>名称</th>
										<th>位置</th>
										<th>显示</th>
										<th>优先级</th>
										<th>最后修改时间</th>
										<th>操作</th>
									</thead>
									<tbody class="page-data-list">
									</tbody>
								</table>
								<div class="datatable-footer">
									<div class="datatable-info">
										共0条 
									</div>
									<div class="center">
										<div class="datatable-pagination">
											<ul class="pagination">
												
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
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
		<script type="text/x-handlebars" id = "ad-template">
			{{#each this}}
			<tr>
				<td><img src="{{preImageUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{location}}</td>
				<td>{{active}}</td>
				<td>{{priority}}</td>
				{{#with updateDate}}
					<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<shiro:hasPermission name="ad:view">
					<a class="btn btn-info" href="${ctx}/mi/ad/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="ad:update">
					<a class="btn btn-info" href="${ctx}/mi/ad/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					</shiro:hasPermission>
				</td>
			</tr>
			{{/each}}
		</script>
	</body>
	<script>
	  	var page = new Page({
	  			container:"#adList",
	  			template:"#ad-template",
	  			url:"${ctx}/mi/ad/page/",
	  			data:{pagesize:10}
	  	});
	  	page.init();
	  	$("#search-ad").click(function(){
	  		var name = $("#searchbyname").val();
	  		var location = $("#location").val();
	  		page.setData({"name":name,"location":location});
	  		page.init();
	  	});
	</script>
</html>
