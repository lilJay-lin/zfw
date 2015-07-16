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
							<h2>资讯详情</h2>
						</div>
						<div class="box-cnt">
							<div class="form">
								<fieldset>
									<input type="hidden" id="infoId" name ="id" value="${infoId}" />
									<div class="control-group">
										<label class="control-label">标题</label>
										<div class="control error">
											<input type="text" name="name" max="16" maxlength="16" error="资讯名长度少于16个字" 
											require="require" require_msg ="资讯名不能为空"  placeholder="输入资讯标题"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">作者</label>
										<div class="control error">
											<input type="text"  name="author" id="author" max="32" error="作者不能为空" 
									require="require" require_msg ="作者不能为空" placeholder="输入作者"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">类型</label>
										<div class="control error">
											<select name="type">
												<option value="房产">房产</option>
												<option value="综合">综合</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">优先级</label>
										<div class="control error">
											<input type="text"  name="priority" id="priority" max="4"  error="优先级范围0-9999" 
							patterns = "^[0-9]*$"  placeholder="输入优先级 0-9999" value="0" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">标签</label>
										<div class="control error">
											<input type="text"  name="tags" id="tags" max="24" maxlength="24" 
								 placeholder="输入标签"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<form enctype="multipart/form-data" method="post" id="uploadForm">
									<div class="control-group">
										<label class="control-label">上传预览图像</label>
										<div class="control error">
											<div class="control-img">
												<img src=""/>
											</div>
										</div>
									</div>
									</form>
									<div class="control-group">
										<label class="control-label">提要</label>
										<div class="control error">
											<textarea name="summary" style="height:100px"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">内容</label>
										<div class="control error">
											<div name="UEContent"></div>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control error">
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
									  <button type="reset" class="btn" id="cancle">返回</button>
									</div>
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
		<script type="text/x-handlebars-template" id="relation-info-template">
			{{#this}}
			<li>
				<div class="relation-info">
					<span>
						{{name}}
					</span>
				</div>
			</li>
			{{/this}}
		</script>
	<script>

	function template(id,data){
		var tpl = Handlebars.compile($(id).html());
		return tpl(data);
	}

	$("#cancle").on("click",function(){
		window.location.href = "${ctx}/mi/info";
	});
	$(".uploader").hide();
	
		var id = $("#infoId").val();
		var getInfoUrl = "${ctx}/mi/info/"+id;
		$.ajax({
			type:"get",
			url:getInfoUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var info = data.info;
					var relationREPs = data.relationREPs;
					for(var i in info){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							ele.val(info[i]);
							ele.attr("readonly","readonly");
							ele.attr("disabled","disabled");
						}
					}
					var UEContent = info["content"];
					if(UEContent){
						$("[name=UEContent]").html(UEContent);
					}
					var preImageUrl = info["preImageUrl"];
					if(preImageUrl){
						$(".control-img").find("img").attr("src",preImageUrl);
					}
					if(relationREPs){
						$(".relation").append(template("#relation-info-template",relationREPs));
					}
				}
			},
			error:function(){
				alert("获取资讯信息失败");
			}
		});
	</script>
</html>