<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script>
		function initPanoData(){
			$.ajax({
				type:"get",
				url:"${ctx}/mi/hxphoto/${imageId}",
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var image = data.image;
						for(var i in image){
							var ele = $("[name="+i+"]");
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
							if(ele[0]){
								ele.val(image[i]);
							}
						}
						var contentUrl = image["contentUrl"];
						if(contentUrl){
							$(".control-img").find("img").attr("src",contentUrl);
						}
					}
				},
				error:function(){
					alert("获取户型图片信息失败");
				}
			});
		}
		initPanoData();
	</script>