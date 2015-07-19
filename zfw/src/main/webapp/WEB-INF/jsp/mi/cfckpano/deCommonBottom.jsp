<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	function initPanoData(){
		$.ajax({
			type:"get",
			url:"${ctx}/mi/cfckpano/${panoId}",
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var pano = data.pano;
					for(var i in pano){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							ele.val(pano[i]);
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
						}
					}
					var preImageUrl = pano["preImageUrl"];
					if(preImageUrl){
//						$(".control-user-img").attr("src",preImageUrl);
						$(".control-img").find("img").attr("src",preImageUrl);
					}
				}
			},
			error:function(){
				alert("获取厂房仓库全景信息失败");
			}
		});
	}
	initPanoData();
</script>