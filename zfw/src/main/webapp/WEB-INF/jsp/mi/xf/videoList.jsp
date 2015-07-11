<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="box-cnt js-rep-videos-container" style="display:none">
	<div class="datatable" id="videoList">
		<div class="datatabls-filter">
			<label> <!--搜索：--> <input type="text" class="js-search-text"  placeholder="名称"/>
				<input type="button" class="btn js-search-btn" value="搜索"/>
			</label>
		</div>
		<table class="datatable-table">
			<thead>
				<th><input type="checkbox" id="selectAll" /></th>
				<th>预览</th>
				<th>名称</th>
				<th>内容路径</th>
				<th>描述</th>
				<th>操作</th>
			</thead>
			<tbody class="page-data-list">
			</tbody>
		</table>
		<div class="datatable-toolbar disabled">
			<div class="toolbar">
				<select id="batch_option">
					<option value="del" selected="selected">删除</option>
				</select> <a class="btn" href="javascript:;" onclick="videoBatchOperation(this);">批量操作</a>
				<a class="btn" href="${ctx}/mi/${repId }/xfvideo/add">新增</a>
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
<script type="text/x-handlebars" id="video-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{preImageUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{contentUrl}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfvideo/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfvideo/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delVideo(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}

		</script>
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
	  		}
	  		delVideo(e,videoIds);
	  	}
	  	function delVideo(e,videoIds){
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