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
							<h2>厂房/仓库管理</h2>
						</div>
						<div class="box-cnt">
							<div class="datatable" id="cfckList">
								<div class="datatabls-filter">
									<!--搜索：-->
									<input type="text" id="searchbyname" />
									<select id="searchbytype">
										<option value="">类型不限</option>
										<option value="厂房">厂房</option>
										<option value="仓库">仓库</option>
									</select>
									<input type="button" class="btn" id="search" value="搜索" />
								</div>
								<table class="datatable-table">
									<thead>
										<shiro:hasPermission name="warehouse:del">
										<th>
											<input type="checkbox"  id="selectAll"/>
										</th>
										</shiro:hasPermission>
										<th>名称</th>
										<th>类别</th>
										<th>地址</th>
										<th>优先级</th>
										<th>最后修改时间</th>
										<th>操作</th>
									</thead>
									<tbody class="page-data-list">
									</tbody>
								</table>
								<div class="datatable-toolbar disabled">
									<div class="toolbar">
										<shiro:hasPermission name="warehouse:del">
										<select id="batch_option">
											<option value="del" selected="selected">删除</option>
										</select>
										<a class="btn" href="javascript:;" onclick="batchOperation(this);">批量操作</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="warehouse:add">
										<a class="btn" href="${ctx}/mi/cfck/add">新增</a>
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
		<script type="text/x-handlebars" id = "cfck-template">
			{{#each this}}
			<tr>
				<shiro:hasPermission name="warehouse:del">
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				</shiro:hasPermission>
				<td>{{name}}</td>
				<td>{{type}}</td>
				<td>{{address}}</td>
				<td>{{priority}}</td>
				{{#with updateDate}}
				<td>{{dateformat time 3}}</td>
				{{/with}}
				<td>
					<shiro:hasPermission name="warehouse:view">
					<a class="btn btn-info" href="${ctx}/mi/cfck/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="warehouse:update">
					<a class="btn btn-info" href="${ctx}/mi/cfck/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="warehouse:del">
					<a class="btn btn-danger" href="javascript:;" onclick="delObject(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</shiro:hasPermission>
					</a>
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
	  		var ids = "";
	  		$(".page-data-list").find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			ids==""?ids=$(item).val():ids+="/"+$(item).val();
	  			}
	  		});
	  		if(ids == ""){
	  			alert("请选择需要处理的厂房/仓库");
	  		}
	  		
	  		delObject(e,ids);
	  	}
	  	function delObject(e,ids){
	  		
	  		if(!window.confirm("确认删除?")){
	  			return ;
	  		}
	  		
	  		if(deling){
	  			alert("正在删除厂房/仓库,请稍后再操作");
	  			return;
	  		}
	  		var obj = {};
	  		obj.delFlag = true;
	  		deling = true;
	  		var url = "${ctx}/mi/cfcks";
	  		$.ajax({
	  			type:"post",
	  			data:$.extend({"warehouseids":ids},obj),
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
	  			container:"#cfckList",
	  			template:"#cfck-template",
	  			url:"${ctx}/mi/cfck/page/",
	  			data:{pagesize:10}
	  	});
	  	page.init();
	  	
	  	$("#search").click(function(){
	  		var name = $("#searchbyname").val();
	  		var type = $("#searchbytype").val();
	  		page.setData({"name":name,"type":type});
	  		page.init();
	  	});
	</script>
</html>
