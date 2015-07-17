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
							<h2>用户详情</h2>
						</div>
						<div class="box-cnt">
							<form class="form" enctype="multipart/form-data">
								<fieldset>
									<input type="hidden" name="publicExponent" id="publicExponent" value="${publicExponent }" />
									<input type="hidden" name="modulus" id="modulus" value="${modulus }"  />
									<input type="hidden" id="userid" name ="id" value="${userid}" />
									<div class="control-group">
										<label class="control-label">图像</label>
										<div class="control control-img-box">
											<img class="control-user-img" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="control">
											<input type="text" name="name" max="16" min="4" maxlength="16" error="用户名长度4~16只能包含小写字母、数字、下划线并以小写字母开头" 
					patterns = "^[a-z]([a-zA-Z0-9_]){3,15}$" require="require" require_msg ="用户名不能为空"  placeholder="输入用户名"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">密码</label>
										<div class="control">
											<input type="password"  name="pwd" id="pwd" value="111111" max="32" min="6"  error="密码长度6~32只能包含大小写字母、数字、部分特殊符号 !@#$%^&*()" 
					require="require" require_msg ="密码不能为空" patterns = "^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]*$" placeholder="输入密码"  />
											<input type="hidden"  name="password" id="password"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">邮箱</label>
										<div class="control">
											<input type="text" name="email" require="require" requrie_msg = "邮箱不能为空" error="邮箱格式不正确" pattern="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">手机号码</label>
										<div class="control">
											<input type="text" name="phoneNum"  require="require"  patterns="^1[0-9]{10}$"  error="手机号码格式不正确"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">状态</label>
										<div class="control">
											<select name="locked" disabled="disabled">
												<option value="false" checked>正常</option>
												<option value="true">锁定</option>
											</select>
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
											<h2>已担当角色</h2>
										</div>
										<div class="box-cnt">
											<ul class="relation">
												
											</ul>
											<div class="clearfix"></div>
										</div>
									</div>
									
									<div class="form-actions">
<%-- 										<a href="${ctx}/mi/user/${userid}/edit" class="btn btn-primary" id="submit">编辑</a> --%>
									  <button type="reset" class="btn cancle">返回</button>
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
		<script type="text/x-handlebars-template" id="role-template">
		{{#each this}}
		<tr>
			<td>{{name}}</td>
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
					<!--<a class="btn btn-rel btn-remove-relation" data-id="{{id}}">
						<i class="icon-remove"></i>
					</a>-->
				</div>
			</li>
			{{/this}}
		</script>
	</body>
	<%
	    request.setAttribute("encrypUrl", request.getContextPath()
						+ "/assets/tools/encryption");
	%>
	<script type="text/javascript" src="${encrypUrl}/RSA.js"></script>
	<script type="text/javascript" src="${encrypUrl}/BigInt.js"></script>
	<script type="text/javascript" src="${encrypUrl}/Barrett.js"></script>
	<script type="text/javascript" src="${encrypUrl}/md5.js"></script>
	<script>
		
		function template(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
		}
		
		/*
		 *分页功能 
		 */
	  	var page = new Page({
	  			container:"#roleinfo",
	  			template:"#role-template",
	  			url:"${ctx}/mi/roles/page/"
	  	})
	  	page.init();	  	
	  	$("#search-role").click(function(){
	  		var name = encodeURIComponent($("#searchbyname").val());
	  		page.setData({"name":name,"all":true})
	  		page.init();
	  	})
		
		/*
		 * 编辑页面，渲染页面数据
		 * 
		 */
		var form = $(".form");
		form.find("input").prop("disabled","disabled");
		form.find("textarea").prop("disabled","disabled");
		var id = $("#userid").val();
		var getUserUrl = "${ctx}/mi/user/"+id;
		$.ajax({
			type:"get",
			url:getUserUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var user = data.user;
					var relationroles = data.relationroles;
					for(var i in user){
						form.find("[name="+i+"]").length>0&&form.find("[name="+i+"]").val(user[i]);
					}
					if(!!user.headImgUrl){
						$(".control-user-img").attr("src",user.headImgUrl)
					}
					
					if(relationroles){
						var originalRelation = [];
						for(var i=0,l = relationroles.length;i<l;i++){
							originalRelation.push(relationroles[i].id);
						}
						$(".relation").append(template("#relation-info-template",relationroles))
					}
				}
			},
			error:function(){
				
			}
		});
		/*
		 * 返回
		 */
		$(".cancle").on("click",function(){
				window.location.href = "${ctx}/mi/users";
		})
	</script>
</html>
