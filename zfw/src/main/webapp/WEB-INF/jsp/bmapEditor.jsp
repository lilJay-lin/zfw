<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#l-map{height:500px;width:100%;}
		#r-result{width:100%;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=44843934aa23b524f4456723fea8dbdf"></script>
	<title>设置点是否可拖拽</title>
</head>
<body>
	<div id="l-map"></div>
	<div id="r-result">
		<input type="button" onclick="marker.enableDragging();" value="可拖拽" />
		<input type="button" onclick="marker.disableDragging();" value="不可拖拽" />
      <input type="button" onclick="getLocation();" value="zuojiao"/>
	</div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("l-map");
	var point = new BMap.Point(112.475916,23.06011);
	map.centerAndZoom(point, 12);
	var marker = new BMap.Marker(point);// 创建标注
	map.addOverlay(marker);             // 将标注添加到地图中
	marker.disableDragging();           // 不可拖拽
  
  function getLocation(){
    try{
      
    var point2 = marker.getPosition();
   alert(marker+"_"+point2+"_"+point2.lng+"_"+point2.lat); 
    }catch(e){
      alert(e)
    }
  }
</script>
