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
							<h2>新增厂房/仓库图片</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
					</div>
					<div class="form-actions">
					  <button type="button" class="btn btn-primary" id="submit">保存</button>
					  <button type="reset" class="btn cancle">返回</button>
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
	<%@include file="aeCommonBottom.jsp" %>
	<script>
		
		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
			var res = form.validate();
			if(res){
				var image = getImageData();
			   var url = "${ctx}/mi/cfckphoto/${imageId}";
			btn.attr("disabled","disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:image,
			   	dataType:"json",
			   	success:function(data){
			   		if(data){
			   			if(!data.success){
			   				var name = data.field;
			   				if(name){
			   					var p = form.find("input[name='"+name+"']");
			   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show());
			   				}else{
			   					alert(data.msg);
			   				}
			   			}else{
			   				alert(data.msg);
			   				window.location.href="${ctx}/mi/cfck/${warehouseId}/edit";
			   			}
			   		}
			   	},
			   	error:function(){
			   		alert("更新厂房/仓库图片信息失败!");
			   	},
			   	complete:function(){
			   		btn.removeAttr("disabled");
			   	}
			   });
			}else{
				$("body").scrollTop(0);
			}
		});
		function initImageData(){
			var id = $("#imageId").val();
			var getImageUrl = "${ctx}/mi/cfckphoto/"+id;
			$.ajax({
				type:"get",
				url:getImageUrl,
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var image = data.image;
						for(var i in image){
							$("[name="+i+"]")[0]&&$("[name="+i+"]").val(image[i]);
						}
						var contentUrl = image["contentUrl"];
						if(contentUrl){
							$(".control-user-img").attr("src",contentUrl);
						}
					}
				},
				error:function(){
					alert("获取厂房/仓库图片信息失败");
				}
			});
		}
		initImageData();
	</script>
</html>