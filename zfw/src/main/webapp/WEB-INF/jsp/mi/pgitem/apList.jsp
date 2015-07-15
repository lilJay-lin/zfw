<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="box-cnt js-ai-ap-container" style="display:none">
	<div class="datatable" id="apList">
		<div class="datatabls-filter">
			<label> <!--搜索：--> <input type="text" class="js-search-text"  placeholder="名称"/>
				<input type="button" class="btn js-search-btn" value="搜索"/>
			</label>
		</div>
		<table class="datatable-table">
			<thead>
				<th><input type="checkbox" id="selectAll" /></th>
				<th>名称</th>
				<th>值</th>
				<th>描述</th>
				<th>操作</th>
			</thead>
			<tbody class="page-data-list">
			</tbody>
		</table>
		<div class="datatable-toolbar js-edit-content">
			<div class="toolbar">
				<select id="batch_option">
					<option value="del" selected="selected">删除</option>
				</select> <a class="btn" href="javascript:;" onclick="apBatchOperation(this);">批量操作</a>
				<a class="btn" href="${ctx}/mi/${aiId }/pgparam/add">新增</a>
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
		var apList = $("#apList");
		var apCheckList = apList.find(".page-data-list");
		var apDeling = false;
	  	//checkbox 全选
	  	apList.find("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			apCheckList.find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			apCheckList.find("input[type='checkbox']").prop("checked",false);
	  		}
	  	});
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function apBatchOperation(e){
	  		var apIds = "";
	  		apCheckList.find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			apIds==""?apIds=$(item).val():apIds+="/"+$(item).val();
	  			}
	  		});
	  		if(apIds == ""){
	  			alert("请选择需要处理的评估参数");
	  		}
	  		delPano(e,apIds);
	  	}
	  	function delPano(e,apIds){
	  		if(apDeling){
	  			alert("正在删除评估参数,请稍后再操作");
	  			return;
	  		}
	  		apDeling = true;
	  		var url = "${ctx}/mi/pgparam/batchDel";
	  		$.ajax({
	  			type:"post",
	  			data:{"apIds":apIds},
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						apPage.reloadPage();
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("删除失败");
	  			},
	  			complete:function(){
	  				apDeling = false;
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var apPage = new Page({
	  			container:"#apList",
	  			template:"#ap-template",
	  			url:"${ctx}/mi/${aiId}/pgparam/page/",
	  			data:{pageSize:10}
	  	});
	  	
	  	apList.find(".js-search-btn").click(function(){
	  		var name = apList.find(".js-search-text").val();
	  		apPage.setData({"name":name});
	  		apPage.init();
	  	});
	</script>