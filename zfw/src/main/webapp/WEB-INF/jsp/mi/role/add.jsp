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
										<div class="control">
											<input type="text" name="name" require="require" require_msg ="角色名称不能为空"  placeholder="输入角色名名称"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control">
											<textarea name="description">
												
											</textarea>
										</div>
									</div>
									<div class="box box-inline">
										<div class="box-hd">
											<h2>添加关联关系</h2>
										</div>
										<div class="box-cnt">
											<div class="datatable" id="permission">
												<div class="datatabls-filter">
													<label>
														<!--搜索：-->
														权限名称：<input type="text" id="searchbyname"/>
														权限编码：<input type="text" id="searchbycode"/>
														<input type="button" class="btn" value="搜索" id="search-btn"/>
													</label>
												</div>
												<table class="datatable-table">
													<thead>
														<tr>
															<th>权限名称</th>
															<th>权限编码</th>
															<th>描述</th>
														</tr>
													</thead>
													<tbody class="page-data-list">
														
													</tbody>
												</table>
												<div class="datatable-footer">
													<div class="datatable-info">
														<div></div>
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
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
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
					<a class="btn btn-rel btn-remove-relation"  data-id="{{id}}">
						<i class="icon-remove"></i>
					</a>
				</div>
			</li>
			{{/this}}
		</script>
	</body>
	
	<script>
		/*
		 * 
		 * 新增关联
		 * 
		 */
		var addRelation = [];
		var delRelation = [];
		$(".datatable-table").on("click",".btn-add-relation",function(){
			var id = $(this).data("id");
			var name = $(this).data("name");
			if($.inArray(id,addRelation)>-1){
				return;
			}
			addRelation.push(id);
			$(".relation").append(template("#relation-info-template",{"id":id,"name":name}))
			return false;
		})
		
		/*
		 * 删除关联
		 */
		$(".relation").on("click",".btn-remove-relation",function(){
			var id = $(this).data("id");
			var idx = $.inArray(id,addRelation);
			if(idx>-1){
				addRelation.splice(idx,1)
			}
			$(this).parent().parent().remove();
			return false;
		})
		
		
		function template(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
		}
		
		/*
		 *分页功能 
		 */
	  	var page = new Page({
	  			container:"#permission",
	  			template:"#permission-template",
	  			url:"${ctx}/mi/permissions/page/",
	  	})
	  	page.init();
	  	
	  	$("#search-btn").click(function(){
	  		var name = encodeURIComponent($("#searchbyname").val());
	  		var code = encodeURIComponent($("#searchbycode").val());
	  		page.setData({"name":name,"code":code})
	  		page.init();
	  	})
		
		/*
		 * 保存
		 * 
		 */
		
		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
			var res = form.validate();
			if(res){
				var role = {
					name:"",
					description:"",
					delFlag:false
				}
			    for(var i in role){
			   		var value = form.find("input[name="+i+"]").val();
			   		role[i]=value;
			   }
			   role['description']  = form.find("textarea[name='description']").val();
			   
			   var permissions = addRelation.join("/");
			   var data = {"permissions":permissions};
			   var url = "${ctx}/mi/role";
			btn.attr("disabled","disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:$.extend(data,role),
			   	dataType:"json",
			   	success:function(data){
			   		if(data){
			   			if(!data.success){
			   				var name = data.field;
			   				if(name){
			   					var p = form.find("input[name='"+name+"']");
			   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show())
			   				}else{
			   					alert(data.msg)
			   				}
			   				btn.removeAttr("disabled");
							$("body").scrollTop(0);
			   			}else{
			   				alert(data.msg)
			   				window.location.href="${ctx}/mi/roles";
			   			}
			   		}
			   	},
			   	error:function(){
					btn.prop("disabled","false");
					btn.removeClass("disabled");
			   		alert("新增用户失败!");
			   	}
			   });
			   
			}
		})
		
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