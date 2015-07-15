<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script>
		function initPanoData(){
			$.ajax({
				type:"get",
				url:"${ctx}/mi/ad/${adId}",
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var ad = data.ad;
						for(var i in ad){
							var ele = $("[name="+i+"]");
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
							if(i=="active"){
								$("[name="+i+"]").val(String(ad[i]));
								continue;
							}
							if(ele[0]){
								ele.val(ad[i]);
							}
						}
						var preImageUrl = ad["preImageUrl"];
						if(preImageUrl){
							$(".control-img").find("img").attr("src",preImageUrl);
						}
					}
				},
				error:function(){
					alert("获取广告信息失败");
				}
			});
		}
		initPanoData();
	</script>