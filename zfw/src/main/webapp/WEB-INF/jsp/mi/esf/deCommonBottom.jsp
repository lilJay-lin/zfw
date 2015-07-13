<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function openCloseSHHDetail(clazz){
	var ele = $("."+clazz);
	if(ele.is(':hidden')){
		ele.show();
	}else{
		ele.hide();
	}
	if(!ele.attr("first")){
		ele.attr("first",2);
		if("js-shh-detail-container"==clazz){
			initSHHData();
		}else if("js-shh-panos-container"==clazz){
			panoPage.init();
		}else if("js-shh-photos-container"==clazz){
		  	photoPage.init();
		}else if("js-shh-rings-container"==clazz){
		  	ringPage.init();
		}
	}
}

function initSHHData(){
	$.ajax({
		type:"get",
		url:"${ctx}/mi/esf/${shhId}",
		async:true,
		dataType:"json",
		success:function(data){
			if(data){
				var shh = data.shh;
				for(var i in shh){
					var ele = $("[name="+i+"]");
					if(ele[0] && inDetail){
						ele.attr("readonly","readonly");
						ele.attr("disabled","disabled");
					}
					if(i=="grossFloorArea"){
						var num = Number(shh[i]);
						if(num){
							$("[name="+i+"]").val(Math.round(num*100)/100);
						}
						continue;
					}
					if(i=="outOfDate"){
						if(shh[i]==true){
							$("[name="+i+"]").val("true");
						}
						continue;
					}
					if(ele[0]){
						ele.val(shh[i]);
					}
				}
				var preImageUrl = shh["preImageUrl"];
				if(preImageUrl){
					$(".control-img").find("img").attr("src",preImageUrl);
				}
			}
		},
		error:function(){
			alert("获取二手房信息失败");
		}
	});
}
</script>