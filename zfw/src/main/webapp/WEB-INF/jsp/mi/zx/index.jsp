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
							<h2>资讯管理</h2>
						</div>
						<div class="box-cnt">
							<div class="datatable" id="infoList">
								<div class="datatabls-filter">
									<!--搜索：-->
									<input type="text" id="searchbyname" />
									<select id="infoType">
										<option value="">类型不限</option>
										<option value="房产">房产</option>
										<option value="综合">综合</option>
									</select>
									<input type="button" class="btn btn-primary" id="search-info" value="搜索" />
								</div>
								<table class="datatable-table">
									<thead>
										<shiro:hasPermission name="info:del">
										<th>
											<input type="checkbox"  id="selectAll"/>
										</th>
										</shiro:hasPermission>
										<th class="name">名称</th>
										<th class="author">作者</th>
										<th class="summary">提要</th>
										<th>类型</th>
										<th>优先级</th>
										<th class="time">最后修改时间</th>
										<th class="operation">操作</th>
									</thead>
									<tbody class="page-data-list">
									</tbody>
								</table>
								<div class="datatable-toolbar disabled">
									<div class="toolbar">
										<shiro:hasPermission name="info:del">
										<select id="batch_option">
											<option value="del" selected="selected">删除</option>
										</select>
										<a class="btn btn-primary" href="javascript:;" onclick="batchOperation(this);">批量操作</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="info:add">
										<a class="btn btn-primary" href="${ctx}/mi/info/add">新增</a>
										</shiro:hasPermission>
									</div>
								</div>
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
		<script type="text/x-handlebars" id = "info-template">
			{{#each this}}
			<tr>
				<shiro:hasPermission name="info:del">
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				</shiro:hasPermission>
				<td>{{name}}</td>
				<td>{{author}}</td>
				<td>{{summary}}</td>
				<td>{{type}}</td>
				<td>{{priority}}</td>
				{{#with updateDate}}
					<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<shiro:hasPermission name="info:view">
					<a class="btn btn-info" href="${ctx}/mi/info/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="info:update">
					<a class="btn btn-info" href="${ctx}/mi/info/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="info:del">
					<a class="btn btn-danger" href="javascript:;" onclick="delInfo(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
					</shiro:hasPermission>
				</td>
			</tr>
			{{/each}}
		</script>
	</body>
	<script>
		var deling = false;
	  	//checkbox 全选
	  	$("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			$(".page-data-list").find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			$(".page-data-list").find("input[type='checkbox']").prop("checked",false);
	  		}
	  	});
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function batchOperation(e){
	  		var infoIds = "";
	  		$(".page-data-list").find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			infoIds==""?infoIds=$(item).val():infoIds+="/"+$(item).val();
	  			}
	  		});
	  		if(infoIds == ""){
	  			alert("请选择需要处理的资讯");
	  			return ;
	  		}
	  		delInfo(e,infoIds);
	  	}
	  	function delInfo(e,infoIds){	  		
			if(!window.confirm("确认删除?")){
	  			return ;
	  		}
	  		if(deling){
	  			alert("正在删除资讯,请稍后再操作");
	  			return;
	  		}
	  		deling = true;
	  		var url = "${ctx}/mi/info/batchDel";
	  		$.ajax({
	  			type:"post",
	  			data:{"infoIds":infoIds},
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						page.reloadPage();
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("删除失败");
	  			},
	  			complete:function(){
	  				deling = false;
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var page = new Page({
	  			container:"#infoList",
	  			template:"#info-template",
	  			url:"${ctx}/mi/info/page/",
	  			data:{pagesize:10}
	  	});
	  	page.init();
	  	
	  	$("#search-info").click(function(){
// 	  		var name = encodeURIComponent($("#searchbyname").val());
// 	  		var infoType = encodeURIComponent($("#infoType").val());
	  		var name = $("#searchbyname").val();
	  		var infoType = $("#infoType").val();
	  		page.setData({"name":name,"type":infoType});
	  		page.init();
	  	});
	  	
	  	
	</script>
</html>
