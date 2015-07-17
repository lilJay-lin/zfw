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
							<h2>编辑资讯</h2>
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
		/*
		 * 
		 * 新增关联
		 * 
		 */
		var originalRelation = [];
		var addRelation = [];
		var delRelation = [];
		$(".datatable-table").on("click",".btn-add-relation",function(){
			var id = $(this).data("id");
			var name = $(this).data("name");
			var didx = $.inArray(id,delRelation);//不在已删除
			var oidx = $.inArray(id,originalRelation);
			didx>-1&&delRelation.splice(didx,1);
			if(oidx>-1&&didx==-1||$.inArray(id,addRelation)>-1){//原来有，未删除不加
				return;
			}
			addRelation.push(id);
			$(".relation").append(template("#relation-info-template",{"id":id,"name":name}));
			return false;
		});
		
		/*
		 * 删除关联
		 */
		$(".relation").on("click",".btn-remove-relation",function(){
			var id = $(this).data("id");
			var aidx = $.inArray(id,addRelation);//不在已删除
			aidx>-1&&addRelation.splice(aidx,1);
			if($.inArray(id,delRelation)==-1){//del中没有，则存入
				delRelation.push(id);
			}
			$(this).parent().parent().remove();
			return false;
		});
		
		/*
		 * 保存
		 * 
		 */
		/*
		 * 处理密码，密码加密，设置到password
		 */
		$("#submit").click(function(){
			if(!!uploading){
				alert("图像正在上传，请稍后..");
				return ;
			}
			var btn = $(this);
			var form = $(".form");
			var res = form.validate();
			var infoId = $("#infoId").val();
			if(res){
				var info = {
					id:"",
					name:"",
					author:"",
					type:"",
					summary:"",
					content:"",
					description:"",
					priority:"",
					tags:"",
					preImageUrl:""
				};
			   for(var i in info){
			   		var value = $("[name="+i+"]").val();
			   		if(i=="content"){
			   			value = UE.getEditor('UEContainer').getContent();
			   		}
			   		info[i]=value;
			   }
			   var relation = {addREPs:"",delREPs:''};//mixRelationOperation(originalRelation,addRelation,delRelation);
			   if(addRelation.length>0){
			   		relation.addREPs = addRelation.join("/");
			   }
			   if(delRelation.length>0){
			   		relation.delREPs = delRelation.join("/");
			   }
			   var url = "${ctx}/mi/info/"+infoId;
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	dataType:"json",
			   	data:$.extend(relation,info),
			   	success:function(data){
		   			var name = data.field;
		   			if(!data.success){
		   				if(name){
		   					var p = form.find("input[name='"+name+"']");
		   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show());
		   				}else{
		   					alert(data.msg);
		   				}
		   			}else{
		   				alert(data.msg);
		   				window.location.href="${ctx}/mi/info";
			   		}
			   	},
			   	error:function(){
			   		alert("修改资讯失败!");
			   	},
			   	complete:function(){
					btn.removeAttr("disabled");
					btn.removeClass("disabled");
			   	}
			   });
			}else{
				$("body").scrollTop(0);
			}
		});
		
		
		/*
		 * 编辑页面，渲染页面数据
		 * 
		 */
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
						$("[name="+i+"]")[0]&&$("[name="+i+"]").val(info[i]);
					}
					var UEContent = info["content"];
					if(UEContent){
			   			if(ue.isReady){
				   			ue.setContent(UEContent);
			   			}else{
					        ue.ready(function() {
					            ue.setContent(UEContent);
					        });
			   			}
					}
					var preImageUrl = info["preImageUrl"];
					if(preImageUrl){
						$(".control-img").find("img").attr("src",preImageUrl);
					}
					if(relationREPs){
						for(var i=0,l = relationREPs.length;i<l;i++){
							originalRelation.push(relationREPs[i].id);
						}
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