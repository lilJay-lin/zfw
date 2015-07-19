<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	function initImageData(){
		$.ajax({
			type:"get",
			url:"${ctx}/mi/spphoto/${imageId}",
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var image = data.image;
					for(var i in image){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							ele.val(image[i]);
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
						}
					}
					var contentUrl = image["contentUrl"];
					if(contentUrl){
//						$(".control-user-img").attr("src",contentUrl);
						$(".control-img").find("img").attr("src",contentUrl);
					}
				}
			},
			error:function(){
				alert("获取商铺图片信息失败");
			}
		});
	}
	initImageData();
</script>