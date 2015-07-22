<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function openCloseHTDetail(clazz){
	var ele = $("."+clazz);
	if(ele.is(':hidden')){
		ele.show();
	}else{
		ele.hide();
	}
	if(!ele.attr("first")){
		ele.attr("first",2);
		if("js-ht-detail-container"==clazz){
			initHTData();
		}else if("js-ht-panos-container"==clazz){
			panoPage.init();
		}else if("js-ht-photos-container"==clazz){
		  	photoPage.init();
		}else if("js-ht-rings-container"==clazz){
		  	ringPage.init();
		}
	}
}

function initHTData(){
	var id = $("#htId").val();
	var getRepUrl = "${ctx}/mi/hx/"+id;
	$.ajax({
		type:"get",
		url:getRepUrl,
		async:true,
		dataType:"json",
		success:function(data){
			if(data){
				var ht = data.ht;
				for(var i in ht){
					var ele = $("[name="+i+"]");
					if(ele[0] && inDetail){
						ele.attr("readonly","readonly");
						ele.attr("disabled","disabled");
					}
					if(i=="grossFloorArea" || i=="insideArea"){
						var num = Number(ht[i]);
						if(num){
							$("[name="+i+"]").val(Math.round(num*100)/100);
						}
						continue;
					}
					if(i=="onSaleDate"){
				     	if(ht[i]){
					     	var data;
					     	data= new Date(ht[i].time);
							var str = data.getFullYear()+"-"+(data.getMonth()+1)+"-"+data.getDate();;
							$("[name="+i+"]").datepicker( "setDate", str );
				     	}
						continue;
					}
					if(ele[0]){
						ele.val(ht[i]);
					}
				}
				var preImageUrl = ht["preImageUrl"];
				if(preImageUrl){
					$(".control-img").find("img").attr("src",preImageUrl);
				}
			}
		},
		error:function(){
			alert("获取户型信息失败");
		}
	});
}
</script>