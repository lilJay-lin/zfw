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
							<h2>编辑电商团购</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
					</div>
					<div class="form-actions">
					  <button type="button" class="btn btn-primary" id="submit">保存</button>
					  <button type="reset" class="btn" id="cancle">返回</button>
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
				var data = getSaveData();
			   var url = "${ctx}/mi/nl/${id}";
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:data,
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
							$("body").scrollTop(0);
			   			}else{
			   				alert(data.msg);
//			   				window.location.href="${ctx}/mi/nl/${repId}/edit";
			   			}
			   		}
			   	},
			   	error:function(){
			   		btn.removeAttr("disabled");
			   		alert("新增电商团购失败!");
			   	},
			   	complete:function(){
			   		btn.removeAttr("disabled");
			   	}
			   });
			}else{
				$("body").scrollTop(0);
			}
		});
		function initnlData(){
			var id = $("#nlId").val();
			var getDataUrl = "${ctx}/mi/nl/"+id;
			$.ajax({
				type:"get",
				url:getDataUrl,
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var nl = data.nl;
						for(var i in nl){
							$("[name="+i+"]")[0]&&$("[name="+i+"]").val(nl[i]);
						}
					}
				},
				error:function(){
					alert("获取电商团购信息失败");
				}
			});
		}
	initnlData();
	</script>
</html>