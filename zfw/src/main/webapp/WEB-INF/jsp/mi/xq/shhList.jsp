<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="box-cnt js-rc-shh-container" style="display:none">
	<div class="datatable" id="shhList">
		<div class="datatabls-filter">
			<label> <!--搜索：--> <input type="text" class="js-search-text"  placeholder="名称"/>
				<input type="button" class="btn js-search-btn" value="搜索"/>
			</label>
		</div>
		<table class="datatable-table">
			<thead>
				<th><input type="checkbox" id="selectAll" /></th>
				<th>名称</th>
				<th>描述</th>
				<th>优先级</th>
				<th>价格</th>
				<th>最后修改时间</th>
				<th>操作</th>
			</thead>
			<tbody class="page-data-list">
			</tbody>
		</table>
		<div class="datatable-toolbar js-edit-content">
			<div class="toolbar">
				<select id="batch_option">
					<option value="del" selected="selected">删除</option>
				</select> <a class="btn" href="javascript:;" onclick="shhBatchOperation(this);">批量操作</a>
				<a class="btn" href="${ctx}/mi/${rcId}/esf/add">新增</a>
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
		var shhList = $("#shhList");
		var shhCheckList = shhList.find(".page-data-list");
		var shhDeling = false;
	  	//checkbox 全选
	  	shhList.find("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			shhCheckList.find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			shhCheckList.find("input[type='checkbox']").prop("checked",false);
	  		}
	  	});
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function shhBatchOperation(e){
	  		var shhIds = "";
	  		shhCheckList.find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			shhIds==""?shhIds=$(item).val():shhIds+="/"+$(item).val();
	  			}
	  		});
	  		if(shhIds == ""){
	  			alert("请选择需要处理的二手房");
	  		}
	  		delShh(e,shhIds);
	  	}
	  	function delShh(e,shhIds){
	  		if(shhDeling){
	  			alert("正在删除二手房,请稍后再操作");
	  			return;
	  		}
	  		shhDeling = true;
	  		var url = "${ctx}/mi/esf/batchDel";
	  		$.ajax({
	  			type:"post",
	  			data:{"shhIds":shhIds,"rcId":"${rcId}"},
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						shhPage.reloadPage();
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("删除失败");
	  			},
	  			complete:function(){
	  				shhDeling = false;
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var shhPage = new Page({
	  			container:"#shhList",
	  			template:"#shh-template",
	  			url:"${ctx}/mi/${rcId}/esf/page/",
	  			data:{pagesize:10}
	  	});
	  	
	  	shhList.find(".js-search-btn").click(function(){
	  		var name = shhList.find(".js-search-text").val();
	  		shhPage.setData({"name":name});
	  		shhPage.init();
	  	});
	</script>