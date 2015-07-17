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
							<h2>评估维护</h2>
						</div>
						<div class="box-cnt">
							<div class="form">
								<fieldset>
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" onclick="toRefresh();" >刷新</button>
									</div>
									<div class="control-group">
										<label class="control-label">上一次执行开始时间：</label>
										<input type="text" readonly value="<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${lastStart}" />" />
									</div>
									<div class="control-group">
										<label class="control-label">上一次执行结束时间：</label>
											<input type="text" readonly value="<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${lastEnd}" />" />
									</div>
									<div class="control-group">
										<label class="control-label">上一次执行结果： </label>
											<c:choose>
												<c:when test="${lastSuccess}">
													<input type="text" readonly value="正常" />
												</c:when>
												<c:otherwise>
													<input type="text" readonly value="异常" />
												</c:otherwise>
											</c:choose>
									</div>
									<div class="control-group">
										<label class="control-label">当前状态： </label>
											<c:choose>
												<c:when test="${computing}">
													<input type="text" readonly value="正在计算" />
												</c:when>
												<c:otherwise>
													<input type="text" readonly value="空闲" />
												</c:otherwise>
											</c:choose>
									</div>
									<shiro:hasPermission name="am:compute">
									<c:choose>
										<c:when test="${computing}">
											<div class="form-actions">
											  <button type="button" class="btn btn-primary" disabled">正在计算中...</button>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-actions">
											  <button type="button" class="btn btn-primary" id="cBtn" onclick="toCompute();">立刻执行回归方程计算</button>
											</div>
										</c:otherwise>
									</c:choose>
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
		function toCompute(){
			$("#cBtn").attr("disabled","disabled");
			$("#cBtn").html("正在执行...");
			$.ajax({
				url : "${ctx}/mi/pg/compute",
				type : "POST",
				dataType:'json',
				success: function(json){
					if(json.success){
						alert("完成统计！");
						window.location.href = "${ctx}/mi/pg";
					}else if(json.msg){
						alert(json.msg);
					}
				},
				error:function(e){
// 					alert("请求失败！");
				},
				complete:function(e){
					$("#cBtn").html("立刻执行回归方程计算");
					$("#cBtn").removeAttr("disabled");
				}
			}); 
		}
		function toRefresh(){
			window.location.href = "${ctx}/mi/pg";
		}
	</script>
</html>