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
						<div class="box-hd" onclick="openCloseAIDetail('js-ai-detail-container')">
							<h2>报名维护</h2>
						</div>
						<div class="box-cnt">
							<div class="form">
								<fieldset>
									<div class="control-group">
										<label class="control-label">报名栏标题</label>
										<div class="control">
											<input type="text"  name="singUpFormTitle" id="singUpFormTitle" max="32" require="require" require_msg ="户型名不能为空" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">是否显示</label>
										<div class="control">
											<select name="showSignUpForm">
												<option value="true">true</option>
												<option value="false">false</option>
											</select>
										</div>
									</div>
									<shiro:hasPermission name="su:update">
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="rBtn" onclick="toRefresh()">重置</button>
									  <button type="reset" class="btn btn-primary" id="eBtn" onClick="toSave()">修改</button>
									</div>
									</shiro:hasPermission>
								</fieldset>
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
	</body>
	<script>

		function toSave(){
			var btn = $("#eBtn");
			btn.attr("disabled","disabled");
			btn.html("正在提交...");
			$.ajax({
				url : "${ctx}/mi/su/edit",
				type : "POST",
				dataType:'json',
				data:{singUpFormTitle:$("[name=singUpFormTitle]").val(),showSignUpForm:$("[name=showSignUpForm]").val()},
				success: function(json){
					if(json.success){
						if(json.showSignUpForm!=null){
							$("[name=singUpFormTitle]").val(json.singUpFormTitle);
						}
						if(json.showSignUpForm!=null){
							$("[name=showSignUpForm]").val(String(json.showSignUpForm));
						}
						alert("保存成功！");
					}else if(json.msg){
						alert(json.msg);
					}
				},
				error:function(e){
					alert("请求失败！");
				},
				complete:function(e){
					btn.html("修改");
					btn.removeAttr("disabled");
				}
			}); 
		}
		function toRefresh(){
			var btn = $("#rBtn");
			btn.attr("disabled","disabled");
			btn.html("正在重置...");
			$.ajax({
				url : "${ctx}/mi/su/detail",
				type : "GET",
				dataType:'json',
				success: function(json){
					if(json.success){
						if(json.showSignUpForm!=null){
							$("[name=singUpFormTitle]").val(json.singUpFormTitle);
						}
						if(json.showSignUpForm!=null){
							$("[name=showSignUpForm]").val(String(json.showSignUpForm));
						}
					}else if(json.msg){
						alert(json.msg);
					}
				},
				error:function(e){
					alert("请求失败！");
				},
				complete:function(e){
					btn.html("重置");
					btn.removeAttr("disabled");
				}
			}); 
		}
		toRefresh();
	<shiro:lacksPermission name="su:update">
		$("[name=singUpFormTitle]").attr("disabled","disabled");
		$("[name=singUpFormTitle]").attr("readonly","readonly");
		$("[name=showSignUpForm]").attr("disabled","disabled");
		$("[name=showSignUpForm]").attr("readonly","readonly");
	</shiro:lacksPermission>
	</script>
</html>