<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="box-cnt js-rep-videos-container" style="display:none">
	<div class="datatable" id="videoList">
		<div class="datatabls-filter">
			<!--搜索：--> 
			<input type="text" class="js-search-text"  placeholder="名称"/>
			<input type="button" class="btn js-search-btn" value="搜索"/>
		</div>
		<table class="datatable-table">
			<thead>
				<th class="js-not-detail checkarea"><input type="checkbox" id="selectAll"/></th>
				<th>预览</th>
				<th class="name">名称</th>
				<th class="contentUrl">内容路径</th>
				<th class="description">描述</th>
				<th class="operation">操作</th>
			</thead>
			<tbody class="page-data-list">
			</tbody>
		</table>
		<div class="datatable-toolbar js-not-detail">
			<div class="toolbar">
				<select id="batch_option">
					<option value="del" selected="selected">删除</option>
				</select> <a class="btn btn-primary" href="javascript:;" onclick="videoBatchOperation(this);">批量操作</a>
				<a class="btn btn-primary" href="${ctx}/mi/${repId }/xfvideo/add">新增</a>
			</div>
		</div>
		<div class="datatable-footer">
			<div class="datatable-info">共0条</div>
			<div class="center">
				<div class="datatable-pagination">
					<ul class="pagination">
	
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
		<script>
		var videoList = $("#videoList");
		var videoCheckList = videoList.find(".page-data-list");
		var videoDeling = false;
	  	//checkbox 全选
	  	videoList.find("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			videoCheckList.find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			videoCheckList.find("input[type='checkbox']").prop("checked",false);
	  		}
	  	});
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function videoBatchOperation(e){
	  		var videoIds = "";
	  		videoCheckList.find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			videoIds==""?videoIds=$(item).val():videoIds+="/"+$(item).val();
	  			}
	  		});
	  		if(videoIds == ""){
	  			alert("请选择需要处理的视频");
				return ;
	  		}
	  		delVideo(e,videoIds);
	  	}
	  	function delVideo(e,videoIds){
	  		if(!window.confirm("确认删除?")){
	  			return ;
	  		}
	  		if(videoDeling){
	  			alert("正在删除视频,请稍后再操作");
	  			return;
	  		}
	  		videoDeling = true;
	  		var url = "${ctx}/mi/xfvideo/batchDel";
	  		$.ajax({
	  			type:"post",
	  			data:{"videoIds":videoIds},
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						videoPage.reloadPage();
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("删除失败");
	  			},
	  			complete:function(){
	  				videoDeling = false;
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var videoPage = new Page({
	  			container:"#videoList",
	  			template:"#video-template",
	  			url:"${ctx}/mi/${repId}/xfvideo/page/",
	  			data:{pageSize:10}
	  	});
	  	
	  	videoList.find(".js-search-btn").click(function(){
	  		var name = videoList.find(".js-search-text").val();
	  		videoPage.setData({"name":name});
	  		videoPage.init();
	  	});
	</script>