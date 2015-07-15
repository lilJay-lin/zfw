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
							<div class="datatable" id="userinfo">
								<div class="datatabls-filter">
									<label>
										<!--搜索：-->
										<input type="text" id="searchbyname" />
										<input type="button" class="btn" id="search-user" value="搜索" />
									</label>
								</div>
								<table class="datatable-table">
									<thead>
										<th>
											<input type="checkbox"  id="selectAll"/>
										</th>
										<th>名称</th>
										<th>邮箱</th>
										<th>手机号码</th>
										<th>状态</th>
										<th>描述</th>
										<!--<th>创建者</th>-->
										<!--<th>上次编辑</th>
										<th>创建日期</th>-->
										<th>最后修改时间</th>
										<th>操作</th>
									</thead>
									<tbody class="page-data-list">
									</tbody>
								</table>
								<div class="datatable-toolbar disabled">
									<div class="toolbar">
										<select id="batch_option">
											<option value="del" selected="selected">删除</option>
											<option value="unlock">解冻</option>
											<option value="lock">锁定</option>
										</select>
										<a class="btn" href="javascript:;" onclick="batchOperation(this);">批量操作</a>
										<a class="btn" href="${ctx}/mi/user/add">新增</a>
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
		<script type="text/x-handlebars" id = "user-info-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td>{{name}}</td>
				<td>{{email}}</td>
				<td>{{phoneNum}}</td>
				{{#if locked}}<td>锁定{{else}}<td>正常</td>{{/if}}
				<td>{{description}}</td>
				<!--<td>{{creater}}</td>
				<td>{{create}}</td>
				<td>{{lastEditor}}</td>
				<td>{{createDate}}</td>-->
				{{#with updateDate}}
				<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<a class="btn btn-info" href="${ctx}/mi/user/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="${ctx}/mi/user/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delUser(this);return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
	</body>
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
	  		var userids = "";
	  		$(".page-data-list").find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			userids==""?userids=$(item).val():userids+="/"+$(item).val();
	  			}
	  		})
	  		if(userids == ""){
	  			alert("请选择需要更新的用户")
	  		}
			var user = {};
			var msg = "确认更新用户?";
	  		if(option =="del"){
	  			user.delFlag = true;
	  			msg = "确认删除用户?";
	  		}else if(option == "unlock"){
	  			user.locked = false;
	  		}else if(option == "lock"){
	  			user.locked = true;
	  		}
	  		if(!window.confirm(msg)){
	  			return ;
	  		}
	  		if(deling){
	  			alert("正在更新用户,请稍后再操作");
	  			return;
	  		}
	  		updateUser(e,userids,user);
	  	}
	  	function delUser(e){
	  		var id = $(e).data("id");
	  		var user = {delFlag:true};
	  		if(!window.confirm("确认删除用户?")){
	  			return ;
	  		}
	  		if(deling){
	  			alert("正在删除用户,请稍后再操作");
	  			return;
	  		}
	  		updateUser(e,id,user);
	  	}
	  	var deling = false;
	  	function updateUser(e,userids,user){
	  		var $e = $(this);
	  		var url = "${ctx}/mi/users";
	  		$.ajax({
	  			type:"post",
	  			data:$.extend({"userids":userids},user),
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
	  				alert("操作失败")
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
	  			container:"#userinfo",
	  			template:"#user-info-template",
	  			url:"${ctx}/mi/users/page/",
	  			data:{pagesize:4}
	  	})
	  	page.init();
	  	
	  	$("#search-user").click(function(){
	  		var name = encodeURIComponent($("#searchbyname").val());
	  		page.setData({"name":name})
	  		page.init();
	  	})
	  	
	  	
	</script>
</html>
