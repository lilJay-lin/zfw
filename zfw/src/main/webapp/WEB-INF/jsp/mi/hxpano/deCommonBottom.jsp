<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script>
		function initPanoData(){
			$.ajax({
				type:"get",
				url:"${ctx}/mi/hxpano/${panoId}",
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var pano = data.pano;
						for(var i in pano){
							var ele = $("[name="+i+"]");
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
							if(ele[0]){
								ele.val(pano[i]);
							}
						}
						var preImageUrl = pano["preImageUrl"];
						if(preImageUrl){
							$(".control-img").find("img").attr("src",preImageUrl);
						}
					}
				},
				error:function(){
					alert("获取户型全景信息失败");
				}
			});
		}
		initPanoData();
	</script>