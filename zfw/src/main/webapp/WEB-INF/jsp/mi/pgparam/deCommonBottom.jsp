<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function openCloseAPDetail(clazz){
	var ele = $("."+clazz);
	if(ele.is(':hidden')){
		ele.show();
	}else{
		ele.hide();
	}
	if(!ele.attr("first")){
		ele.attr("first",2);
		if("js-ap-detail-container"==clazz){
			initAPData();
		}
	}
}

function initAPData(){
	$.ajax({
		type:"get",
		url:"${ctx}/mi/pgparam/${apId}",
		async:true,
		dataType:"json",
		success:function(data){
			if(data){
				var ap = data.ap;
				for(var i in ap){
					var ele = $("[name="+i+"]");
					if(ele[0] && inDetail){
						ele.attr("readonly","readonly");
						ele.attr("disabled","disabled");
					}
					if(ele[0]){
						ele.val(ap[i]);
					}
				}
			}
		},
		error:function(){
			alert("获取评估参数信息失败");
		}
	});
}
</script>