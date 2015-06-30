<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../../inc/taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/font-awesome/css/font-awesome.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/mi.css"/>
		<title>后台管理</title>
	</head>
	<body>
		
		<!-- 头部导航条开始     -->
		<div class="navbar skin">
			<a class="navbar-brand skin">后台管理</a>
			<div class="navbar-inner">
				<ul class="nav">
					<li ><a href="#" class="btn"><i class="icon-tasks"></i></a></li>
					<li><a href="#" class="btn"><i class="icon-envelope"></i></a></li>
					<li><a href="#" class="btn"><i class="icon-wrench"></i></a></li>
					<li>
						<a href="#">
							<div class="nav-avatar">
								<img src="${headImgUrl }" alt="Avatar">
							</div>
							<div class="nav-user">
								<p>Welcome!</p>
								<p> <shiro:principal/></p>
							</div>
						</a> 
					</li>
				</ul>
			</div>
		</div>
		<!-- 头部导航条结束     -->
		<div class="clearfix"></div>
		
		<div class="container">
			<!-- 右边内容区域开始     -->
			<div class="main skin">
				<div class="content">
					<div class="box">
						<div class="box-hd">
							<h2>用户管理</h2>
						</div>
						<div class="box-cnt">
							<div class="datatable">
								<div class="datatabls-filter">
									<label>
										<!--搜索：-->
										<input type="text" />
										<input type="button" class="btn" value="搜索" />
									</label>
								</div>
								<table class="datatable-table">
									<thead>
										<th>
											<input type="checkbox" />
										</th>
										<th>名称</th>
										<th>邮箱</th>
										<th>手机号码</th>
										<th>状态</th>
										<th>描述</th>
										<!--<th>创建者</th>
										<th>上次编辑</th>
										<th>创建日期</th>
										<th>更新日期</th>-->
										<th>操作</th>
									</thead>
									<tbody>
									</tbody>
								</table>
								<div class="datatable-toolbar disabled">
									<div class="toolbar">
										<select id="selectError3">
											<option>删除</option>
											<option>解冻</option>
											<option>锁定</option>
										</select>
										<a class="btn">批量操作</a>
									</div>
								</div>
								<div class="datatable-footer">
									<div class="datatable-info">
										共32条 当前展示第1条到第10条
									</div>
									<div class="center">
										<div class="datatable-pagination">
											<ul id="pagination">
												
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
				<div class="clearfix">&nbsp</div>
				<div class="clearfix">&nbsp</div>
				<ul class="slider-nav skin">
					<li class="submenu active">
						<a href="javascript:void(0)">
							<i class="icon-key"></i>
							<span class="hidden-tablet"> 系统管理</span>
							<span class="label">2</span>
						</a>
						<ul class="subNav" >
							<li>
								<a  href="${ctx}/mi/users">
									<i class="icon-user"></i>
									<span > 用户管理</span>
								</a>
							</li>
							<li>
								<a  href="/roles">
									<i class="icon-hdd"></i>
									<span > 角色管理</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			
			<!-- 左边侧边栏区域结束     -->
		</div>
		
		<!-- 底部区域开始     -->
		<div class="footer">
			<p>@copyright-------------------</p>
		</div>
		<!-- 底部区域结束     -->	
		<script type="text/x-handlebars" id = "user-info-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" />
				</td>
				<td>{{name}}</td>
				<td>{{email}}</td>
				<td>{{phoneNum}}</td>
				{{#if locked}}<td>锁定{{else}}<td>正常</td>{{/if}}
				<td>{{description}}</td>
				<!--<td>{{create}}</td>
				<td>{{lastEditor}}</td>
				<td>{{createDate}}</td>
				<td>{{updateDate}}</td>-->
				<td>
					<a class="btn btn-info" href="mi/user/{{id}}">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="mi/user/{{id}}">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="mi/user/{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
	</body>
	<script src="${ctx}/assets/js//handlebars-v3.0.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctx}/assets/js/jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctx}/assets/js/style.js" type="text/javascript" charset="utf-8"></script>

	<script>
		function template(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
		}
		function getPage(index){
			var p = index || 1;
			var url = "${ctx}/mi/users/page/"+p;
			$.ajax({
				type:"GET",
				url:url,
				dataType:"json",
				async:true,
				data:{pagesize:"2"},
				success:function(data){
					var items = data.items;
					console.log(items.length);
					var pageinfo = data.pageinfo;
					$(".datatable-table tbody").html(template("#user-info-template",items));
					pagination(pageinfo);
//					$("#datatable-toolbar").html(template("#pagination_tpl",pageinfo))
				},
				error:function(){
					
				}
			});
		}
		function prePage(){
			var page = $("#pagination").data("curpage")||0;
			console.log(page);
			page = (page-1>0?page-1:1);
			getPage(page)
		}
		function nextPage(){
			var page = $("#pagination").data("curpage")||0;
			var totalpage = $("#pagination").data("totalpage")||0;
			page = (page+1>totalpage?totalpage:(page+1));
			getPage(page)
		}
		
		function pagination(pageinfo){
			var pagesize = pageinfo.pagesize||10;
			var start = (pageinfo.curpage-1)*pagesize + 1;
			var end = pageinfo.curpage*pagesize >=pageinfo.totalrows?pageinfo.totalrows:pageinfo.curpage*pagesize;
			$(".datatable-info").html("共"+pageinfo.totalrows+"条 当前展示第"+start+"条到第"+end+"条");
			var html = '<li class="prev disabled"><a href="javascript:;" onclick="prePage()">← 上一页</a>'
			for(var i=0;i<pageinfo.totalpage;i++){
				html+='<li '+(i+1==pageinfo.curpage?'class="active"':'')+'><a href="javascript:;" onclick="getPage('+(i+1)+')">'+(i+1)+'</a></li>';
			}
			html +='<li class="next"><a href="javascript:;" onclick="nextPage()">下一页 → </a></li>';
			$("#pagination").html(html);
			$("#pagination").data("curpage",pageinfo.curpage);
			$("#pagination").data("totalpage",pageinfo.totalpage);
		}
		getPage();
	</script>
</html>