<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script>
		function initPanoData(){
			$.ajax({
				type:"get",
				url:"${ctx}/mi/hxring/${ringId}",
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var ring = data.ring;
						for(var i in ring){
							var ele = $("[name="+i+"]");
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
							if(ele[0]){
								ele.val(ring[i]);
							}
						}
						var preImageUrl = ring["preImageUrl"];
						if(preImageUrl){
							$(".control-img").find("img").attr("src",preImageUrl);
						}
					}
				},
				error:function(){
					alert("获取户型三维信息失败");
				}
			});
		}
		initPanoData();
	</script>