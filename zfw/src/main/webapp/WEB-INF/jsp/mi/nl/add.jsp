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
							<h2>新增电商团购</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
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
				var nl = getSaveData();
			   var url = "${ctx}/mi/nl";
			  btn.attr("disabled","disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:nl,
			   	dataType:"json",
			   	success:function(data){
			   		if(data){
			   			if(!data.success){
			   				var name = data.field;
			   				if(name){
			   					var p = form.find("[name='"+name+"']");
			   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show());
			   				}else{
			   					alert(data.msg);
			   				}
			   				btn.removeAttr("disabled");
							$("body").scrollTop(0);
			   			}else{
			   				alert(data.msg);
			   				window.location.href="${ctx}/mi/nl";
			   			}
			   		}
			   	},
			   	error:function(){
			   		btn.removeAttr("disabled");
			   		alert("新增电商团购失败!");
			   	}
			   });
			}else{
				$("body").scrollTop(0);
			}
		});
	</script>
</html>
