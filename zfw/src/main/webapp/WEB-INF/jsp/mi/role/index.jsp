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
							<h2>用户管理</h2>
						</div>
						<div class="box-cnt">
							<div class="datatable" id="roleinfo">
								<div class="datatabls-filter">
									<label>
									<!--搜索：-->
										<input type="text" id="searchbyname"/>
										<input type="button" class="btn" value="搜索" id="search-role"/>
									</label>
								</div>
								<table class="datatable-table">
									<thead>
										<tr>
											<th>
												<input type="checkbox"  id="selectAll"/>
											</th>
											<th>角色名称</th>
											<th>描述</th>
											<!--<th>创建人</th>
											<th>最近编辑人</th>
											<th>创建日期</th>
											<th>更新日期</th>-->
											<th>操作</th>
										</tr>
									</thead>
									<tbody class="page-data-list">
									</tbody>
								</table>
								<div class="datatable-toolbar">
									<div class="toolbar">
										<select id="batch_option">
											<option value="del" selected="selected">删除</option>
											<option value="unlock">解冻</option>
											<option value="lock">锁定</option>
										</select>
										<a class="btn" href="javascript:;" onclick="batchOperation(this);">批量操作</a>
										<a class="btn" href="${ctx}/mi/role/add">新增</a>
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
				<ul class="slider-nav skin">
					<li class="submenu active">
						<a href="javascript:void(0)">
							<i class="icon-key"></i>
							<span class="hidden-tablet"> 系统管理</span>
							<span class="label">2</span>
						</a>
						<ul class="subNav" >
							<li>
								<a  href="user.html">
									<i class="icon-user"></i>
									<span > 用户管理</span>
								</a>
							</li>
							<li>
								<a  href="role.html">
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
		
	</body>
	<script type="text/x-handlebars" id = "role-template">
		{{#each this}}
		<tr>
			<td>
				<input type="checkbox" value="{{id}}"/>
			</td>
			<td>{{name}}</td>
			<td>{{description}}</td>
			<!--<td>{{creater}}</td>
			<td>{{lastEditor}}</td>
			<td>{{createDate}}</td>
			<td>{{updateDate}}</td>-->
			<td>
				<a class="btn btn-info" href="${ctx}/mi/role/{{id}}/detail">
					<i class="icon-zoom-in "></i>                                            
				</a>
				<a class="btn btn-info" href="${ctx}/mi/role/{{id}}/edit">
					<i class="icon-edit "></i>                                            
				</a>
				<a class="btn btn-danger" href="javascript:;" onclick="del(this);return false;" data-id="{{id}}">
					<i class="icon-trash "></i> 
				</a>
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
	  			alert("请选择需要批量更新的角色")
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
	  	function update(e,ids,obj){
	  		var $e = $(this);
	  		if( $e.data("lazy")){
	  			return ;
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
	  	
	  	$("#search-user").click(function(){
	  		var name = encodeURIComponent($("#searchbyname").val());
	  		page.setData({"name":name})
	  		page.init();
	  	})
	  	
	  	
	</script>
</html>