<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function openCloseRHDetail(clazz){
	var ele = $("."+clazz);
	if(ele.is(':hidden')){
		ele.show();
	}else{
		ele.hide();
	}
	if(!ele.attr("first")){
		ele.attr("first",2);
		if("js-rh-detail-container"==clazz){
			initRHData();
		}else if("js-rh-panos-container"==clazz){
			panoPage.init();
		}else if("js-rh-photos-container"==clazz){
		  	photoPage.init();
		}else if("js-rh-rings-container"==clazz){
		  	ringPage.init();
		}
	}
}

function initRHData(){
	$.ajax({
		type:"get",
		url:"${ctx}/mi/zf/${rhId}",
		async:true,
		dataType:"json",
		success:function(data){
			if(data){
				var rh = data.rh;
				for(var i in rh){
					var ele = $("[name="+i+"]");
					if(ele[0] && inDetail){
						ele.attr("readonly","readonly");
						ele.attr("disabled","disabled");
					}
					if(i=="grossFloorArea"){
						var num = Number(rh[i]);
						if(num){
							$("[name="+i+"]").val(Math.round(num*100)/100);
						}
						continue;
					}
					if(i=="outOfDate"){
						if(rh[i]==true){
							$("[name="+i+"]").val("true");
						}
						continue;
					}
					if(i.indexOf("facility")>-1){
						$("[name="+i+"]")[0].checked = rh[i];
						continue;
					}
					if(ele[0]){
						ele.val(rh[i]);
					}
				}
				var preImageUrl = rh["preImageUrl"];
				if(preImageUrl){
					$(".control-img").find("img").attr("src",preImageUrl);
				}
			}
		},
		error:function(){
			alert("获取租房信息失败");
		}
	});
}
</script>