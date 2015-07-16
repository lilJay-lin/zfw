<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function openCloseREPDetail(clazz){
	var ele = $("."+clazz);
	if(ele.is(':hidden')){
		ele.show();
	}else{
		ele.hide();
	}
	if(!ele.attr("first")){
		ele.attr("first",2);
		if("js-rep-detail-container"==clazz){
			initREPData();
		}else if("js-rep-panos-container"==clazz){
			panoPage.init();
		}else if("js-rep-photos-container"==clazz){
		  	photoPage.init();
		}else if("js-rep-videos-container"==clazz){
		  	videoPage.init();
		}else if("js-rep-ht-container"==clazz){
		  	htPage.init();
		}
	}
}

function initREPData(){
	var id = $("#repId").val();
	var getRepUrl = "${ctx}/mi/xf/"+id;
	$.ajax({
		type:"get",
		url:getRepUrl,
		async:true,
		dataType:"json",
		success:function(data){
			if(data){
				var rep = data.rep;
				var relationUserList = data.relationUserList;
				var relationInfoList = data.relationInfoList;
				for(var i in rep){
					var ele = $("[name="+i+"]");
					if(ele[0] && inDetail){
						ele.attr("readonly","readonly");
						ele.attr("disabled","disabled");
					}
					if(i=="buildingType"){
						var buildingTypes = rep["buildingType"];
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
					if(i=="greenRate" || i=="floorAreaRatio" || i=="propertyFee"){
						var num = Number(rep[i]);
						if(num){
							$("[name="+i+"]").val(Math.round(num*100)/100);
						}
						continue;
					}
					if(i=="onSaleDate" || i=="onReadyDate"){
				     	var data = new Date(rep[i].time);
						var str = data.getFullYear()+"-"+(data.getMonth()+1)+"-"+data.getDate();;
						$("[name="+i+"]").datepicker( "setDate", str );
						continue;
					}
					if(ele[0]){
						ele.val(rep[i]);
					}
				}
				var preImageUrl = rep["preImageUrl"];
				if(preImageUrl){
					$(".control-img").find("img").attr("src",preImageUrl);
				}
				var lon = rep["longitude"];
				var lat = rep["latitude"];
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
				if(relationUserList){
					$("#user-relation").append(template("#relation-user-template",relationUserList));
				}
				if(relationInfoList){
					$("#info-relation").append(template("#relation-info-template",relationInfoList));
				}
			}
		},
		error:function(){
			alert("获取楼盘信息失败");
		}
	});
}
</script>