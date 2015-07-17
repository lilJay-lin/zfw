<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script src="${ctx }/assets/tools/jquery-ui/jquery-ui.min.js"></script>
  	<link rel="stylesheet" href="${ctx }/assets/tools/jquery-ui/jquery-ui.min.css">
	<script>
		var inDetail = false;
		var inAdd = false;
		var inEdit = false;
		  $(function() {
		    $( "#onSaleDate" ).datepicker({
		    	dateFormat: 'yy-mm-dd' 
		    });
		  });
		$(".cancle").on("click",function(){
// 			if(inDetail){
				window.history.back(-1);
// 			}else if(window.confirm("确定不保存返回？")){
// 				window.location.href = "${ctx}/mi/xf/${rcId}/edit";
// 			}
		});
	 </script>