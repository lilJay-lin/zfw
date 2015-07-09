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
							<h2>新增角色</h2>
						</div>
						<div class="box-cnt">
							<form class="form">
								<fieldset>
									<div class="control-group">
										<label class="control-label">角色名称</label>
										<input type="hidden" id="id" name ="id" value="${id}" />
										<div class="control">
											<input type="text" name="name" require="require" require_msg ="角色名称不能为空"  placeholder="输入角色名名称"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control">
											<textarea name="description"></textarea>
										</div>
									</div>
									
									
									<div class="box box-inline">
										<div class="box-hd">
											<h2>已选关联关系</h2>
										</div>
										<div class="box-cnt">
											<ul class="relation">
												
											</ul>
											<div class="clearfix"></div>
										</div>
									</div>
									
									<div class="form-actions">
									  <a href="${ctx}/mi/role/${id}/edit" class="btn btn-primary" id="submit">编辑</a>
									  <button type="reset" class="btn" id="cancle">返回</button>
									</div>
								</fieldset>
							</form>
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
		<script type="text/x-handlebars-template" id="permission-template">
		{{#each this}}
		<tr>
			<td>{{name}}</td>
			<td>{{code}}</td>
			<td>{{desciption}}</td>
			<td>
				<a class="btn btn-info btn-add-relation" href="javascript:;" data-id="{{id}}" data-name="{{name}}">
					<i class="icon-plus "></i>                                            
				</a>
			</td>
		</tr>
		{{/each}}
		</script>
		
		<script type="text/x-handlebars-template" id="relation-info-template">
			{{#this}}
			<li>
				<div class="relation-info">
					<span>
						{{name}}
					</span>
					<!--<a class="btn btn-rel btn-remove-relation"  data-id="{{id}}">
						<i class="icon-remove"></i>
					</a>-->
				</div>
			</li>
			{{/this}}
		</script>
	</body>
	
	<script>
		
		
		function template(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
		}
		
		/*
		 * 编辑页面，渲染页面数据
		 * 
		 */
				var form = $(".form");
		form.find("input").prop("disabled","disabled");
		form.find("textarea").prop("disabled","disabled");
		var id = $("#id").val();
		var getRoleUrl = "${ctx}/mi/role/"+id;
		$.ajax({
			type:"get",
			url:getRoleUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var role = data.role;
					var relations = data.relations;
					
					var form = $(".form");
					for(var i in role){
						var e = form.find("input[name="+i+"]");
						e.length>0&&e.val(role[i]);
					}
					var d = form.find("textarea[name='description']");
					d.length>0&&d.val(role['description']);
					if(relations){
						$(".relation").append(template("#relation-info-template",relations))
					}
				}
			},
			error:function(){
				
			}
		});
		
		/*
		 * 返回
		 */
		$("#cancle").on("click",function(){
			if(window.confirm("是否确定返回？")){
				window.location.href = "${ctx}/mi/roles";
			}
		})
	</script>
</html>