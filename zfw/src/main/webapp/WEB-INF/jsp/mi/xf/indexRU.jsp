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
							<h2>关联楼盘管理</h2>
						</div>
						<div class="box-cnt">
							<div class="datatable" id="repList">
								<div class="datatabls-filter">
									<!--搜索：-->
									<input type="text" id="searchbyname" />
									<input type="button" class="btn" id="search-rep" value="搜索" />
								</div>
								<table class="datatable-table">
									<thead>
										<th>名称</th>
										<th>地址</th>
										<th>描述</th>
										<th>最后修改时间</th>
										<th>操作</th>
									</thead>
									<tbody class="page-data-list">
									</tbody>
								</table>
								<div class="datatable-footer">
									<div class="datatable-info">
										共32条 当前展示第1条到第10条
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
		<script type="text/x-handlebars" id = "rep-template">
			{{#each this}}
			<tr>
				<td>{{name}}</td>
				<td>{{address}}</td>
				<td>{{description}}</td>
				{{#with updateDate}}
				<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<shiro:hasPermission name="rep:view">
					<a class="btn btn-info" href="${ctx}/mi/xfru/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="rep:update">
					<a class="btn btn-info" href="${ctx}/mi/xfru/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					</shiro:hasPermission>
				</td>
			</tr>
			{{/each}}
		</script>
	</body>
	<script>
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var page = new Page({
	  			container:"#repList",
	  			template:"#rep-template",
	  			url:"${ctx}/mi/xfru/page/",
	  			data:{pagesize:10}
	  	});
	  	page.init();
	  	
	  	$("#search-rep").click(function(){
	  		var name = $("#searchbyname").val();
	  		page.setData({"name":name});
	  		page.init();
	  	});
	</script>
</html>
