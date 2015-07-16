<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function openCloseRCDetail(clazz){
	var ele = $("."+clazz);
	if(ele.is(':hidden')){
		ele.show();
	}else{
		ele.hide();
	}
	if(!ele.attr("first")){
		ele.attr("first",2);
		if("js-rc-detail-container"==clazz){
			initRCData();
		}else if("js-rc-shh-container"==clazz){
			shhPage.init();
		}else if("js-rc-photos-container"==clazz){
		  	photoPage.init();
		}else if("js-rc-rh-container"==clazz){
		  	rhPage.init();
		}
	}
}

function initRCData(){
	$.ajax({
		type:"get",
		url:"${ctx}/mi/xq/${rcId}",
		async:true,
		dataType:"json",
		success:function(data){
			if(data){
				var rc = data.rc;
				for(var i in rc){
					var ele = $("[name="+i+"]");
					if(ele[0] && inDetail){
						ele.attr("readonly","readonly");
						ele.attr("disabled","disabled");
					}
					if(rc[i]==null){
						continue;
					}
					if(i=="buildingType"){
						var buildingTypes = rc["buildingType"];
						if(buildingTypes){
							var bts = buildingTypes.split(",");
							$("[name=buildingType]").each(function(){
								if($.inArray($(this).val(),bts)>-1){
									this.checked = true;
								}
							});
						}
						continue;
					}
					if(i=="active"){
						$("[name="+i+"]").val(String(rc[i]));
						continue;
					}
					if(i=="greenRate" || i=="floorAreaRatio" || i=="propertyFee"){
						var num = Number(rc[i]);
						if(num){
							$("[name="+i+"]").val(Math.round(num*100)/100);
						}
						continue;
					}
					if(i=="onSaleDate"){
				     	var data = new Date(rc[i].time);
						var str = data.getFullYear()+"-"+(data.getMonth()+1)+"-"+data.getDate();;
						$("[name="+i+"]").datepicker( "setDate", str );
						continue;
					}
					if(ele[0]){
						ele.val(rc[i]);
					}
				}
				var preImageUrl = rc["preImageUrl"];
				if(preImageUrl){
					$(".control-img").find("img").attr("src",preImageUrl);
				}
				var lon = rc["longitude"];
				var lat = rc["latitude"];
				if(lon && lat){
					setTimeout(function(){
						var tp = new BMap.Point(lon,lat);
						marker.setPosition(tp);
						map.centerAndZoom(tp, 15);
					}, 1000);
				}else{
					setTimeout(function(){
						map.centerAndZoom(point, 15);
					},500);
				}
			}
		},
		error:function(){
			alert("获取小区信息失败");
		}
	});
}
</script>