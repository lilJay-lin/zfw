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
							<h2>角色管理</h2>
						</div>
						<div class="box-cnt">
							<div class="datatable" id="roleinfo">
								<div class="datatabls-filter">
									<!--搜索：-->
									<input type="text" id="searchbyname"/>
									<input type="button" class="btn btn-primary" value="搜索" id="search-role"/>
								</div>
								<table class="datatable-table">
									<thead>
										<tr>
											<shiro:hasPermission name="role:del">
											<th>
												<input type="checkbox"  id="selectAll"/>
											</th>
											</shiro:hasPermission>
											<th class="name">角色名称</th>
											<th class="description">描述</th>
											<th>最后修改时间</th>
											<th class="operation">操作</th>
										</tr>
									</thead>
									<tbody class="page-data-list">
									</tbody>
								</table>
								<div class="datatable-toolbar">
									<div class="toolbar">
										<shiro:hasPermission name="role:del">
										<select id="batch_option">
											<option value="del" selected="selected">删除</option>
											<!--<option value="unlock">解冻</option>
											<option value="lock">锁定</option>-->
										</select>
										<a class="btn btn-primary" href="javascript:;" onclick="batchOperation(this);">批量操作</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="role:add">
										<a class="btn btn-primary" href="${ctx}/mi/role/add">新增</a>
										</shiro:hasPermission>
									</div>
								</div>
								<div class="datatable-footer">
									<div class="datatable-info">
										<div>共32条 当前展示第1条到第10条</div>
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
				<div class="clearfix">&nbsp</div>
				<div class="clearfix">&nbsp</div>
				<%@include file="../inc/left.jsp" %>
				
			</div>
			
			<!-- 左边侧边栏区域结束     -->
		</div>
		
		<!-- 底部区域开始     -->
		<div class="footer">
			<p>@copyright-------------------</p>
		</div>
		<!-- 底部区域结束     -->
		
	</body>
	<script type="text/x-handlebars" id = "role-template">
		{{#each this}}
		<tr>
			<shiro:hasPermission name="role:del">
			<td>
				<input type="checkbox" value="{{id}}"/>
			</td>
			</shiro:hasPermission>
			<td>{{name}}</td>
			<td>{{description}}</td>
			<!--<td>{{creater}}</td>
			<td>{{lastEditor}}</td>
			<td>{{createDate}}</td>-->
			{{#with updateDate}}
			<td>{{dateformat time 3}}</td>
			{{/with}}
			<td>
				<shiro:hasPermission name="role:view">
				<a class="btn btn-info" href="${ctx}/mi/role/{{id}}/detail">
					<i class="icon-zoom-in "></i>                                            
				</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:update">
				<a class="btn btn-info" href="${ctx}/mi/role/{{id}}/edit">
					<i class="icon-edit "></i>                                            
				</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:del">
				<a class="btn btn-danger" href="javascript:;" onclick="del(this);return false;" data-id="{{id}}">
					<i class="icon-trash "></i> 
				</a>
				</shiro:hasPermission>
			</td>
		</tr>
		{{/each}}
	</script>
	<script>
	  	//checkbox 全选
	  	$("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			$(".page-data-list").find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			$(".page-data-list").find("input[type='checkbox']").prop("checked",false);
	  		}
	  	})
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function batchOperation(e){
	  		var option = $("#batch_option").val();
	  		var ids = "";
	  		$(".page-data-list").find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			ids==""?ids=$(item).val():ids+="/"+$(item).val();
	  			}
	  		})
	  		if(ids == ""){
	  			alert("请选择需要批量更新的角色");
				return ;
	  		}
			var obj = {};
	  		if(option =="del"){
	  			obj.delFlag = true;
	  		}
//	  		else if(option == "unlock"){
//	  			role.locked = false;
//	  		}else if(option == "lock"){
//	  			role.locked = true;
//	  		}
	  		update(e,ids,obj);
	  	}
	  	function del(e){
	  		var id = $(e).data("id");
	  		var obj = {delFlag:true};
	  		update(e,id,obj);
	  	}
	  	var deling = false;
	  	function update(e,ids,obj){
	  		if(!window.confirm("确认删除?")){
	  			return ;
	  		}
	  		var $e = $(this);
	  		if( $e.data("lazy")){
	  			alert("正在删除角色,请稍后再操作");
	  			return;
	  		}
	  		 $e.data("lazy",1);
	  		var url = "${ctx}/mi/roles";
	  		$.ajax({
	  			type:"post",
	  			data:$.extend({"roleids":ids},obj),
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						page.reloadPage()
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("更新失败")
	  			},
	  			complete:function(){
	  				 $e.data("lazy",0);
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var page = new Page({
	  			container:"#roleinfo",
	  			template:"#role-template",
	  			url:"${ctx}/mi/roles/page/",
	  			data:{}
	  	})
	  	page.init();
	  	
	  	$("#search-role").click(function(){
	  		var name = encodeURIComponent($("#searchbyname").val());
	  		page.setData({"name":name})
	  		page.init();
	  	})
	  	
	  	
	</script>
</html>
