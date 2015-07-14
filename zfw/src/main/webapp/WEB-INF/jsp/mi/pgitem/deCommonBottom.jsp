<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function openCloseAIDetail(clazz){
	var ele = $("."+clazz);
	if(ele.is(':hidden')){
		ele.show();
	}else{
		ele.hide();
	}
	if(!ele.attr("first")){
		ele.attr("first",2);
		if("js-ai-detail-container"==clazz){
			initAIData();
		}else if("js-ai-ap-container"==clazz){
			apPage.init();
		}
	}
}

function initAIData(){
	$.ajax({
		type:"get",
		url:"${ctx}/mi/pgitem/${aiId}",
		async:true,
		dataType:"json",
		success:function(data){
			if(data){
				var ai = data.ai;
				for(var i in ai){
					var ele = $("[name="+i+"]");
					if(ele[0] && inDetail){
						ele.attr("readonly","readonly");
						ele.attr("disabled","disabled");
					}
					if(ele[0]){
						ele.val(ai[i]);
					}
				}
			}
		},
		error:function(){
			alert("获取评估项信息失败");
		}
	});
}
</script>